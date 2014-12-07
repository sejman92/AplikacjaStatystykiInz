/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.interfaces;

import fsc.model.Game;
import fsc.model.User;
import fsc.model.enums.Kicks;

/**
 *
 * @author Gruby
 */
public interface IAction extends IEntityElement{
    public int getIdTypeOfAction(); //allow to get diffrent id to every type
    public String getComment(); //get Comment for action
    public void setOwnerId(User ownerId); //set owner of the action
    public void setGameId(Game gameId); //set game, which the action belongs
    public void setComment(String comment); //set comment to the action
    public String getActionName(); //get polish name of the action
    
}
