/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;
import fsc.model.Team;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author Gruby
 */
public class TeamsManager {
    private static TeamsManager instance;
    ObservableList<Team> teams;

    TeamsManager() {
        teams = FXCollections.observableArrayList(
                new Team("manchester"));
    }
    
    public static TeamsManager getInstance(){
        if(instance == null)
            instance = new TeamsManager();
        
        return instance;
    }
    
    public void addTeam(String name){
        if(name != null)
        {
            Team team = new Team(name);
            teams.add(team);
        }
    }
    public void editTeam(Team team){
        
    }
    
    public void removeTeam(Team team){
        teams.remove(team);
    }
    
    public ObservableList<Team> getTeams(){
        return teams;
    }
}
