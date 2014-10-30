/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;
import fsc.model.Player;
import fsc.model.Team;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gruby
 */
public class TeamsManager {
    private static TeamsManager instance;
    ObservableList<Team> teams;
    private EntityManagerFactory emFactory;
    private EntityManager em;
    private EntityTransaction et;
    TeamsManager() {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("Team.findAll");
        List<Team> l = query.getResultList();
        convertToObservableList(l);
        et.commit();
        em.close();
    }

    public static TeamsManager getInstance(){
        if(instance == null)
            instance = new TeamsManager();
        
        return instance;
    }
    
    public void addTeam(Integer id){
        if(id != null)
        {
            Team team = new Team(id);
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

    private void convertToObservableList(List<Team> l) {
         teams = FXCollections.observableArrayList();
         for(Team t: l){
          teams.add(t);
         }
    }
}
