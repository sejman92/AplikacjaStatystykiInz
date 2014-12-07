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
import fsc.model.enums.ColorOfCard;
import fsc.model.enums.Kicks;
import fsc.model.interfaces.IAction;
import fsc.model.enums.PartsOfBody;
import fsc.model.enums.SuccessOfShot;
import fsc.controller.MainController;
import java.sql.Date;

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
    private ColorOfCard colorOfCard;
    private SuccessOfShot successOfShot;
    private int time;
    private ActionManager(){
        databaseManager = DatabaseManager.getInstance();
        comment = "";
        successful = 0;
        successOfShot = SuccessOfShot.NIECELNY;
        kickType = Kicks.NONE;
    }
    
    public static ActionManager getInstance(){
        if(instance == null)
            instance = new ActionManager();
        
        return instance;
    }
    
    public void setAction(IAction action){
        if ( action instanceof Card){
            this.setColorOfCard(colorOfCard);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(null);
            this.setSuccessOfShot(SuccessOfShot.NIECELNY);
            this.setSuccessful(0);
        } else if (action instanceof Defense){
            this.setColorOfCard(null);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(null);
            this.setSuccessOfShot(null);
            this.setSuccessful(0);
        } else if (action instanceof Faul){
            this.setColorOfCard(null);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(null);
            this.setSuccessOfShot(null);
            this.setSuccessful(this.successful);
        } else if (action instanceof Injury){
            this.setColorOfCard(null);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(null);
            this.setSuccessOfShot(null);
            this.setSuccessful(0);
        } else if (action instanceof Passing){
            this.setColorOfCard(null);
            this.setKickType(this.kickType);
            this.setPartOfBody(this.partOfBody);
            this.setReservePlayer(null);
            this.setSuccessOfShot(null);
            this.setSuccessful(this.successful);
        } else if (action instanceof Shot){
            this.setColorOfCard(null);
            this.setKickType(this.kickType);
            this.setPartOfBody(this.partOfBody);
            this.setReservePlayer(null);
            this.setSuccessOfShot(this.successOfShot);
            this.setSuccessful(this.successful);
        } else if (action instanceof Swap){
            this.setColorOfCard(null);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(this.reservePlayer);
            this.setSuccessOfShot(null);
            this.setSuccessful(0);
        } else if (action instanceof Takeover){
            this.setColorOfCard(null);
            this.setKickType(Kicks.NONE);
            this.setPartOfBody(null);
            this.setReservePlayer(null);
            this.setSuccessOfShot(null);
            this.setSuccessful(this.successful);
        }
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
        this.comment = comment;
    }
    public void setTime(Integer minute){
        this.time = minute;
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
    
    public void setColorOfCard(ColorOfCard colorOfCard){
        this.colorOfCard = colorOfCard;
    }
    
    public void setSuccessOfShot(SuccessOfShot successOfShot){
        this.successOfShot = successOfShot;
    }
    
    /*
    accept current action and add query to database 
    */
    IAction saveAction(){
        try {
            action.setGameId(game);
            action.setComment(comment);
            action.setOwnerId(game.getOwnerId());
            
            switch(action.getIdTypeOfAction())
            {
                case 1:{
                    ((Shot)action).setPlayerId(player);
                    if(getPartOfBody() != null)
                        ((Shot)action).setBodyPart(getPartOfBody().toString());
                    setShotKickTypeBool((Shot)action);
                    if(getSuccessOfShot() != null){
                        if(getSuccessOfShot().equals(SuccessOfShot.GOL)){
                            ((Shot)action).setSuccess(getSuccessOfShot().toString());
                        } else {
                            if( this.getSuccessful() == 1) ((Shot)action).setSuccess(SuccessOfShot.CELNY.toString());
                            else ((Shot)action).setSuccess((SuccessOfShot.NIECELNY.toString()));
                        }
                    }
                    ((Shot)action).setTime(getTime());
                    break;
                }
                case 2:{
                    ((Passing)action).setPlayerPassingId(player);
                    if(getSuccessful() > 0){
                        ((Passing)action).setSuccessful(true);
                    }else if(getSuccessful() < 0){
                        ((Passing)action).setSuccessful(false);
                    }
                    setPassKickTypeBool((Passing)action);
                    ((Passing)action).setTime(getTime());
                    
                    break;
                }
                case 5:{
                    ((Defense)action).setPlayerId(player);
                    ((Defense)action).setTime(getTime());
                    break;
                }
                case 6:{
                    if(getSuccessful() > 0){
                        ((Faul)action).setPlayerVictimId(player);
                    } else if(getSuccessful() < 0){
                        ((Faul)action).setPlayerOfenderId(player);
                    } 
                    ((Faul)action).setGameId(game);
                    ((Faul)action).setTime(getTime());
                    break;
                }
                case 7: {
                    ((Card)action).setPlayerId(player);
                    ((Card)action).setKind(getColorOfCard().toString());
                    ((Card)action).setTime(getTime());
                    break;
                }
                case 8: {
                    ((Takeover)action).setPlayerId(player);
                    ((Takeover)action).setTime(getTime());
                    break;
                }
                case 9: {
                    ((Injury)action).setPlayerId(player);
                    ((Injury)action).setTime(getTime());
                    break;
                }
                case 10: {
                    ((Swap)action).setPlayerOutId(player);
                    ((Swap)action).setPlayerInId(reservePlayer);
                    ((Swap)action).setTime(getTime());
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
        setPartOfBody(null);
        comment = "";
        successful = 0;
        if(getColorOfCard() != null && getColorOfCard().equals(ColorOfCard.CZERWONA)){
            player = null;
        }
        if(action instanceof Swap){
            player = null;
            reservePlayer = null;
        }
        colorOfCard = null;
        successOfShot = null;
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
        if(getPartOfBody() != null)
            result += getPartOfBody() + " ";
        if(getSuccessful() > 0){
            if( action instanceof Faul)
                result += "był faulowany"; //check it is faul or other action
            else if (action instanceof Shot) result += "celny ";
            else result += "celne ";
        }
        else if(getSuccessful() < 0){
            if( action instanceof Faul)
                result += "faulował "; //check it is faul or other action
            else if (action instanceof Shot) result += "niecelny ";
            else result += "niecelne ";
        }
        if( this.getKickType() != Kicks.NONE)
            result += kickTypeName();
        return result;
    }

    private void setShotKickTypeBool(Shot shot) {
        if( this.getKickType() == Kicks.CORNER){
            shot.setFreekick(Boolean.FALSE);
            shot.setPenalty(Boolean.FALSE);
            shot.setCorner(Boolean.TRUE);
        } else if ( this.getKickType() == Kicks.FREE){
            shot.setFreekick(Boolean.TRUE);
            shot.setPenalty(Boolean.FALSE);
            shot.setCorner(Boolean.FALSE);
        } else if ( this.getKickType() == Kicks.PENALTY){
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
        if( this.getKickType() == Kicks.CORNER){
            passing.setFreekick(Boolean.FALSE);
            passing.setCorner(Boolean.TRUE);
        } else if ( this.getKickType() == Kicks.FREE){
            passing.setFreekick(Boolean.TRUE);
            passing.setCorner(Boolean.FALSE);
        }  else {
            passing.setFreekick(Boolean.FALSE);
            passing.setCorner(Boolean.FALSE);
        }
    }
    private String kickTypeName(){
        if( this.getKickType() == Kicks.CORNER) return "Rzut rożny";
        if( this.getKickType() == Kicks.FREE) return "Rzut wolny";
        if( this.getKickType() == Kicks.PENALTY) return "Rzut karny";
        else return "";
    }

    /**
     * @return the partOfBody
     */
    public PartsOfBody getPartOfBody() {
        return partOfBody;
    }

    /**
     * @return the successful
     */
    public int getSuccessful() {
        return successful;
    }

    /**
     * @return the kickType
     */
    public Kicks getKickType() {
        return kickType;
    }

    /**
     * @return the colorOfCard
     */
    public ColorOfCard getColorOfCard() {
        return colorOfCard;
    }

    /**
     * @return the successOfShot
     */
    public SuccessOfShot getSuccessOfShot() {
        return successOfShot;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }
}