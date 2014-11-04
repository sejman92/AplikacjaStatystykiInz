/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Player;
import fsc.model.Team;
import fsc.model.interfaces.IEntityElement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Gruby
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private final EntityManagerFactory emFactory;
    private final EntityManager em;
    private final EntityTransaction et;
    
    private DatabaseManager() {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
    }
    
    public static DatabaseManager getInstance(){
        if(instance == null)
            instance = new DatabaseManager();
        
        return instance;
    }
    
    public IEntityElement saveEntityElement(IEntityElement entityElement) {
        try {
            et.begin();
            if (entityElement.getId() == null) {
                em.persist(entityElement);
            } else {
                em.merge(entityElement);
            }
            et.commit();
            
            return entityElement;
        } catch (Exception ex) {
            System.out.println("blad w transakcji " + entityElement.getClass().getName());
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku: " + entityElement.getClass().getName());
            }
        }
        return null;
    }    

    public void removeEntityElement(IEntityElement entityElement){
        try {
            et.begin();
            entityElement = em.merge(entityElement);
            em.remove(entityElement);
            et.commit();
        } catch (Exception ex) {
            System.out.println("blad w transakcji: " + entityElement.getClass().getName());
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku: "  + entityElement.getClass().getName());
            }
        }
    }
    public ObservableList<Team> getTeams(){
        return FXCollections.observableArrayList(em.createNamedQuery("Team.findAll").getResultList());
    }
    
    public Team getTeam(int id){
        return em.find(Team.class, id);
    }
    
    public ObservableList<Player> findPlayersFromTeam(Team team) {
        return FXCollections.observableArrayList(em.createNamedQuery("Player.findByTeamId").setParameter("team_id", team).getResultList());
    }
    
    public List<Player> findAllPlayers() {
        return FXCollections.observableArrayList(em.createNamedQuery("Player.findAll").getResultList());
    }

    public Player findPlayer(int id) {
        return em.find(Player.class, id);
    }
}
