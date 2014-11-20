/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.actions.Passing;
import fsc.model.Player;
import fsc.model.actions.Card;

import fsc.model.actions.Defense;
import fsc.model.actions.Faul;
import fsc.model.actions.Injury;

import fsc.model.actions.Shot;
import fsc.model.actions.Swap;
import fsc.model.actions.Takeover;
import fsc.model.enums.Kicks;
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
    private Player reservePlayer;
    private Game game;
    private String comment;
    private PartsOfBody partOfBody;
    private int successful; //1 - success, -1 - unsuccess, 0 - unknown
    private Kicks kickType; //set kickType for specified actions
    private ActionManager(){
        databaseManager = DatabaseManager.getInstance();
        comment = "";
        successful = 0;
        kickType = Kicks.NONE;
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
    
    public void setReservePlayer(Player reservePlayer){
        this.reservePlayer = reservePlayer;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public void setComment(String comment){
        
    }
    
    public void setPartOfBody(PartsOfBody partOfBody){
        this.partOfBody = partOfBody;
    }
    public void setKickType(Kicks kick){
        this.kickType = kick;
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
                    if(partOfBody != null) ((Shot)action).setBodyPart(partOfBody.toString());
                    setShotKickTypeBool((Shot)action);
                    break;
                }
                case 2:{
                    ((Passing)action).setPlayerPassingId(player);
                    if(successful > 0){
                        ((Passing)action).setSuccessful(true);
                    }else if(successful < 0){
                        ((Passing)action).setSuccessful(false);
                    }
                    setPassKickTypeBool((Passing)action);                  
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
                    ((Swap)action).setPlayerOutId(player);
                    ((Swap)action).setPlayerInId(reservePlayer);
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
        comment = "";
        successful = 0;
        if(action instanceof Swap)
        {
            player = null;
            reservePlayer = null;
        }
    }

    /*
    get current insert
    */    
    public String getInsert(){
        String result = "";
        if(action != null)
            result += action.getActionName() + ": ";
        if(reservePlayer != null && action instanceof Swap)
            result += "wchodzi " + reservePlayer + " za ";
        if(player != null)
            result += player + " ";
        if(partOfBody != null)
            result += partOfBody + " ";
        if(successful > 0){
            if( action.getIdTypeOfAction()== 6 ) result += "faulowany"; //check it is faul or other action
            else result += "celne ";
        }
        else if(successful < 0){
            if( action.getIdTypeOfAction() == 6) result += "faulował"; //check it is faul or other action
            else result += "niecelne ";
        }
        if( this.kickType != Kicks.NONE) result += kickTypeName();
        return result;
    }

    private void setShotKickTypeBool(Shot shot) {
        if( this.kickType == Kicks.CORNER){
            shot.setFreekick(Boolean.FALSE);
            shot.setPenalty(Boolean.FALSE);
            shot.setCorner(Boolean.TRUE);
        } else if ( this.kickType == Kicks.FREE){
            shot.setFreekick(Boolean.TRUE);
            shot.setPenalty(Boolean.FALSE);
            shot.setCorner(Boolean.FALSE);
        } else if ( this.kickType == Kicks.PENALTY){
            shot.setFreekick(Boolean.FALSE);
            shot.setPenalty(Boolean.TRUE);
            shot.setCorner(Boolean.FALSE);
        } else {
            shot.setFreekick(Boolean.FALSE);
            shot.setPenalty(Boolean.FALSE);
            shot.setCorner(Boolean.FALSE);
        }
    }

    private void setPassKickTypeBool(Passing passing) {
        if( this.kickType == Kicks.CORNER){
            passing.setFreekick(Boolean.FALSE);
            passing.setCorner(Boolean.TRUE);
        } else if ( this.kickType == Kicks.FREE){
            passing.setFreekick(Boolean.TRUE);
            passing.setCorner(Boolean.FALSE);
        }  else {
            passing.setFreekick(Boolean.FALSE);
            passing.setCorner(Boolean.FALSE);
        }
    }
    private String kickTypeName(){
        if( this.kickType == Kicks.CORNER) return "Rzut rożny";
        if( this.kickType == Kicks.FREE) return "Rzut wolny";
        if( this.kickType == Kicks.PENALTY) return "Rzut karny";
        else return "";
    }
}