/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.controller.GlobalVariables;
import fsc.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mateusz
 */
public class Lineup {
    private ObservableList<Player> startingLineup;
    private ObservableList<Player> reserveLineup;
    
    public Lineup() {
        this.startingLineup = FXCollections.observableArrayList();
        this.reserveLineup = FXCollections.observableArrayList();
    }
    public boolean isOnStartingLineup(Player p){
        return this.startingLineup.contains(p);
    }

    public boolean isOnReserveLineup(Player p){
        return this.reserveLineup.contains(p);
    }

    public void moveFromStartingToReserve(Player p){
        this.startingLineup.remove(p);
        this.reserveLineup.add(p);
    }
    public void moveFromReserveToStarting(Player p){
        this.reserveLineup.remove(p);
        this.startingLineup.add(p);
    }

    public void insertIntoStartingLineup(Player p){
        if(this.getStartingLineup().size() < GlobalVariables.MAX_PLAYERS_IN_LINEUP)  //we cannot put more then 11 players in starting lineup
            this.getStartingLineup().add(p);
    }
    
    public void removeFromStartingLineup(Player p){
        this.getStartingLineup().remove(p);
    }
    
    public void insertIntoReserveLineup(Player p){
        this.getReserveLineup().add(p);
    }
    
    public void removeFromReserveLineup(Player p){
        this.getReserveLineup().remove(p);
    }
    public boolean isCorrect(){
        if(this.getStartingLineup().size() >= GlobalVariables.MIN_PLAYERS_IN_LINEUP) //game may start only when in starting lineup is min. 7 players
            return true;
        else
            return false;
    }
    /**
     * @return the startingLineup
     */
    public ObservableList<Player> getStartingLineup() {
        return startingLineup;
    }

    /**
     * @return the reserveLineup
     */
    public ObservableList<Player> getReserveLineup() {
        return reserveLineup;
    }

    /**
     * @param startingLineup the startingLineup to set
     */
    public void setStartingLineup(ObservableList<Player> startingLineup) {
        this.startingLineup = startingLineup;
    }

    /**
     * @param reserveLineup the reserveLineup to set
     */
    public void setReserveLineup(ObservableList<Player> reserveLineup) {
        this.reserveLineup = reserveLineup;
    }   
    
}