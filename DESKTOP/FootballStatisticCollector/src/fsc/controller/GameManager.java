/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Mateusz
 */
public class GameManager {
    private static GameManager instance;
    ObservableList<Game> games;
    
    private final EntityManagerFactory emFactory;
    private final EntityManager em;
    private final EntityTransaction et;
    
    public GameManager(){
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
    }
    public static GameManager getInstance(){
        if(instance == null)
            instance = new GameManager();
        
        return instance;
    }
    public void saveGame(Game game){
        try {
            et.begin();
            if (game.getId() == null) {
                em.persist(game);
            } else {
                em.merge(game);
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
    public ObservableList<Game> getTeams(){
        return games = FXCollections.observableArrayList(em.createNamedQuery("Game.findAll").getResultList());
    }
    public Game getGame(int id){
        return em.find(Game.class, id);
    }
    
}
