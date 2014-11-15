/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.actions.Passing;
import fsc.model.Player;
<<<<<<< SEJMAN_po_koniec_2_Sprintu:DESKTOP/FootballStatisticCollector/src/fsc/controller/Action.java
=======
import fsc.model.actions.Card;
import fsc.model.actions.Corner;
import fsc.model.actions.Defense;
import fsc.model.actions.Faul;
import fsc.model.actions.Injury;
import fsc.model.actions.Penalty;
>>>>>>> local:DESKTOP/FootballStatisticCollector/src/fsc/controller/ActionManager.java
import fsc.model.actions.Shot;
import fsc.model.actions.Takeover;
import fsc.model.interfaces.IAction;
import fsc.model.enums.PartsOfBody;

/**
 *
 * @author Gruby
 */
public class ActionManager {
    private static ActionManager instance;
    private final DatabaseManager databaseManager;
    private IAction action;
    private Player player;
<<<<<<< SEJMAN_po_koniec_2_Sprintu:DESKTOP/FootballStatisticCollector/src/fsc/controller/Action.java
=======
    private Game game;
    private String comment;
>>>>>>> local:DESKTOP/FootballStatisticCollector/src/fsc/controller/ActionManager.java
    private PartsOfBody partOfBody;
    private int successful; //1 - success, -1 - unsuccess, 0 - unknown
    
    private ActionManager(){
        databaseManager = DatabaseManager.getInstance();
        comment = "";
        successful = 0;
    }
    
    public static ActionManager getInstance(){
        if(instance == null)
            instance = new ActionManager();
        
        return instance;
    }
    
    public void setAction(IAction action){
        this.action = action;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
<<<<<<< SEJMAN_po_koniec_2_Sprintu:DESKTOP/FootballStatisticCollector/src/fsc/controller/Action.java
=======
    public void setGame(Game game){
        this.game = game;
    }
    
    public void setComment(String comment){
        
    }
    
>>>>>>> local:DESKTOP/FootballStatisticCollector/src/fsc/controller/ActionManager.java
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
            action.setGameId(game);
            action.setComment(comment);
            //action.setOwnerId(game.getOwnerId());
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
<<<<<<< SEJMAN_po_koniec_2_Sprintu:DESKTOP/FootballStatisticCollector/src/fsc/controller/Action.java
=======
                case 3:{
                    ((Penalty)action).setPlayerId(player);
                    break;
                }
                case 4:{
                    ((Corner)action).setPlayerId(player);
                    break;
                }
                case 5:{
                    ((Defense)action).setPlayerId(player);
                    break;
                }
                case 6:{
                    if(successful > 0){
                        ((Faul)action).setPlayerVictimId(player);
                    } else if(successful < 0){
                        ((Faul)action).setPlayerOfenderId(player);
                    } 
                    ((Faul)action).setGameId(game);
                    break;
                }
                case 7: {
                    ((Card)action).setPlayerId(player);
                    break;
                }
                case 8: {
                    ((Takeover)action).setPlayerId(player);
                    break;
                }
                case 9: {
                    ((Injury)action).setPlayerId(player);
                    break;
                }
                case 10: {
                    //((Swap)action).setPlayerId(player);
                    break;
                }
                
>>>>>>> local:DESKTOP/FootballStatisticCollector/src/fsc/controller/ActionManager.java
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
        comment = "";
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