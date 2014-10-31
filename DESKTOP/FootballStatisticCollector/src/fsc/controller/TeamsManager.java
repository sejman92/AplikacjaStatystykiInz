/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;
import fsc.model.Player;
import fsc.model.Team;
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
public class TeamsManager {
    private static TeamsManager instance;
    ObservableList<Team> teams;
    private final EntityManagerFactory emFactory;
    private final EntityManager em;
    private final EntityTransaction et;
    
    private TeamsManager() {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        teams = FXCollections.observableArrayList(em.createNamedQuery("Team.findAll").getResultList());
    }

    public static TeamsManager getInstance(){
        if(instance == null)
            instance = new TeamsManager();
        
        return instance;
    }
    
    public void saveTeam(Team team) {
        try {
            et.begin();
            if (team.getId() == null) {
                em.persist(team);
            } else {
                em.merge(team);
            }
            et.commit();
        } catch (Exception ex) {
            System.out.println("blad w transakcji");
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku");
            }
        }
    }
    
    public void savePlayer(Player player){
        try {
            et.begin();
            if (player.getId() == null) {
                em.persist(player);
            } else {
                em.merge(player);
            }
            et.commit();
        } catch (Exception ex) {
            System.out.println("blad w transakcji");
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku");
            }
        }
    }
    
    public void removeTeam(Team team){
        try {
            et.begin();
            team = em.merge(team);
            em.remove(team);
            et.commit();
        } catch (Exception ex) {
            System.out.println("blad w transakcji");
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku");
            }
        }
    }
    
    public void removePlayer(Player player){
        try {
            et.begin();
            player = em.merge(player);
            em.remove(player);
            et.commit();
        } catch (Exception ex) {
            System.out.println("blad w transakcji");
            try {
                et.rollback();
            } catch (Exception ex2) {
                System.out.println("blad w rollbacku");
            }
        }
    }
    
    public ObservableList<Team> getTeams(){
        return teams = FXCollections.observableArrayList(em.createNamedQuery("Team.findAll").getResultList());
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
