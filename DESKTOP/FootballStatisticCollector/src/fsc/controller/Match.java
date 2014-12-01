/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Player;
import fsc.model.Team;
import javafx.collections.ObservableList;

/**
 *
 * @author Mateusz
 * 
 * This class hold players list, and team object for the current match
 */
public class Match {
    private ObservableList<Player> startingLineup;
    private ObservableList<Player> reserveLineup;
    private Team team;
    
    public Match (ObservableList<Player> start,ObservableList<Player> reserve, Team t ){
        this.startingLineup = start;
        this.reserveLineup = reserve;
        this.team = t;
    }

    /**
     * @return the startingLineup
     */
    public ObservableList<Player> getStartingLineup() {
        return startingLineup;
    }

    /**
     * @param startingLineup the startingLineup to set
     */
    public void setStartingLineup(ObservableList<Player> startingLineup) {
        this.startingLineup = startingLineup;
    }

    /**
     * @return the reserveLineup
     */
    public ObservableList<Player> getReserveLineup() {
        return reserveLineup;
    }

    /**
     * @param reserveLineup the reserveLineup to set
     */
    public void setReserveLineup(ObservableList<Player> reserveLineup) {
        this.reserveLineup = reserveLineup;
    }

    /**
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}