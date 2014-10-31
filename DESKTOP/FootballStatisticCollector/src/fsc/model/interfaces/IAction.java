/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.interfaces;

/**
 *
 * @author Gruby
 */
public interface IAction {
    public Integer getId(); //getId in DB
    public int getIdTypeOfAction(); //allow to get diffrent id to every type
    public String getActionName(); //get polish name of action
}
