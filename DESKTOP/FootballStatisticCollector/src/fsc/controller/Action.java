/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Passing;
import fsc.model.Shot;
import fsc.model.interfaces.IAction;
import fsc.model.enums.PartOfBody;
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
    private IAction action;
    private PartOfBody partOfBody;
    private int successful; //1 - success, -1 - unsuccess, 0 - unknown
    private final EntityManagerFactory emFactory;
    private final EntityManager em;
    private final EntityTransaction et;
    
    private Action(){
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
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
    
    public void setPartOfBody(PartOfBody partOfBody){
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
                    if(partOfBody != null)
                        ((Shot)action).setPalce(partOfBody.toString());
                    break;
                }
                case 2:{
                    if(successful > 1){
                        ((Passing)action).setSuccessful(true);
                    }else if(successful < 1){
                        ((Passing)action).setSuccessful(false);
                    }                  
                    break;
                }
                default:
                    System.out.println("bledny typ akcji");
                    break;
            }
            et.begin();
            if (action.getId() == null) {
                em.persist(action);
            } else {
                em.merge(action);
            }
            et.commit();
            
            return action;
        } catch (Exception ex) {
            System.out.println("blad w transakcji");
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku");
            }
        }
        return null;
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
            result += action.getActionName() + " ";
        if(partOfBody != null)
            result += partOfBody + " ";
        if(successful > 0)
            result += "celne";
        else if(successful < 0)
            result += "niecelne";
        
        return result;
    }
}