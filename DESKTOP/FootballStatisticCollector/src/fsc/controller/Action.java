/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.actions.Passing;
import fsc.model.Player;
import fsc.model.actions.Shot;
import fsc.model.interfaces.IAction;
import fsc.model.enums.PartsOfBody;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Gruby
 */
public class Action {
    private static Action instance;
    private final DatabaseManager databaseManager;
    private IAction action;
    private Player player;
    private PartsOfBody partOfBody;
    private int successful; //1 - success, -1 - unsuccess, 0 - unknown
    
    private Action(){
        databaseManager = DatabaseManager.getInstance();
        successful = 0;
    }
    
    public static Action getInstance(){
        if(instance == null)
            instance = new Action();
        
        return instance;
    }
    
    public void setAction(IAction action){
        this.action = action;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public void setPartOfBody(PartsOfBody partOfBody){
        this.partOfBody = partOfBody;
    }
    
    public void setSuccessful(int successful){
        this.successful = successful;
    }
    
    /*
    accept current action and add query to database 
    */
    IAction saveAction(){
        try {
            switch(action.getIdTypeOfAction())
            {
                case 1:{
                    ((Shot)action).setPlayerId(player);
                    if(partOfBody != null)
                        ((Shot)action).setPalce(partOfBody.toString());
                    break;
                }
                case 2:{
                    ((Passing)action).setPlayerPassingId(player);
                    if(successful > 0){
                        ((Passing)action).setSuccessful(true);
                    }else if(successful < 0){
                        ((Passing)action).setSuccessful(false);
                    }                  
                    break;
                }
                default:
                    System.out.println("bledny typ akcji");
                    return null;
            }
        } catch (Exception ex) {
            System.out.println("blad w akcji");
            return null;
        }
        
        return (IAction) databaseManager.saveEntityElement(action);
    }
    
    /*
    reset current action
    */    
    void cancelAction(){
        action = null;
        partOfBody = null;
        successful = 0;
    }

    /*
    get current insert
    */    
    public String getInsert(){
        String result = "";
        if(action != null)
            result += action.getActionName() + ": ";
        if(player != null)
            result += player + " ";
        if(partOfBody != null)
            result += partOfBody + " ";
        if(successful > 0)
            result += "celne";
        else if(successful < 0)
            result += "niecelne";
        
        return result;
    }
}