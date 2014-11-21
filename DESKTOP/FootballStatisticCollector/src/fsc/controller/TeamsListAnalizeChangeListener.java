/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.Team;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mateusz
 */
class TeamsListAnalizeChangeListener implements ChangeListener {
    private ObservableList<Player> players;
    private ObservableList<Team> teams;
    private ObservableList<Game> games;
    private Team selectedTeam;
    
   TeamsListAnalizeChangeListener(ObservableList<Team> teamsListAnalize, ObservableList<Player> playersListAnalize, ObservableList<Game> matchesListAnalize) {
           players = playersListAnalize;
           teams = teamsListAnalize;
           games = matchesListAnalize;
       }

       @Override
       public void changed(ObservableValue observable, Object oldValue, Object newValue) {
           int selectedId = (int)newValue;
           selectedTeam = teams.get(selectedId);
           players = FXCollections.observableArrayList(selectedTeam.getPlayerList());
       }
    
}
