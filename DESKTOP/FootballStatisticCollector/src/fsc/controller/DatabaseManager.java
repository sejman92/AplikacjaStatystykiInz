 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.Participated;
import fsc.model.Played;
import fsc.model.Player;
import fsc.model.Team;
import fsc.model.User;
import fsc.model.enums.KindsOfActive;
import fsc.model.interfaces.IEntityElement;
import java.util.ArrayList;
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
            if(entityElement instanceof Player){
                for(Participated p: findParticipatedListForPlayer((Player) entityElement)){
                    removeEntityElement(p);
                }    
            }
            if(entityElement instanceof Team){
                for(Player p: findPlayersFromTeam((Team) entityElement)){
                    removeEntityElement(p);
                }
                for(Played p: findPlayedListForTeam((Team) entityElement)){
                    removeEntityElement(p);
                }
                for(Game g: findGamesForTeam((Team) entityElement)){
                    removeEntityElement(g);
                }   
            }
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
    
    public ObservableList<Team> getAllTeams(){
        return FXCollections.observableArrayList(em.createNamedQuery("Team.findAll").getResultList());
    }
    
    public ObservableList<Team> getTeams(User owner){
        ObservableList<Team> teams = FXCollections.observableArrayList();
        
        for(Team t: getAllTeams()){
            if(t.getOwnerId() != null && t.getOwnerId().equals(owner)){
                teams.add(t);
            }
        }
        
        return teams;
    }
    
    public Team getTeam(int id){
        return em.find(Team.class, id);
    }
    
    public ObservableList<Played> findPlayedListForTeam(Team team){
        if(team == null)
            return null;
                
        return FXCollections.observableArrayList(em.createNamedQuery("Played.findByTeamId").setParameter("teamId", team.getId()).getResultList());
    }
    
    public ObservableList<Game> findGamesForTeam(Team team){
        if(team == null)
            return null;
                
        ObservableList<Played>playedList = findPlayedListForTeam(team);
        
        ObservableList<Game>games = FXCollections.observableArrayList();
        
        for(Played p: playedList){
            games.add(p.getGameId());
        }
        
        return games;
    }
    
    public ObservableList<Player> findFormerPlayersFromTeam(Team team) {
        
        ObservableList<Player>players = FXCollections.observableArrayList( em.createNamedQuery("Player.findByTeamId").setParameter("teamId", team).getResultList());
        
        ObservableList<Player>formerPlayers = FXCollections.observableArrayList();
        
        for(Player p: players){
            
            if(p.getActive() == null){
                p.setActive(KindsOfActive.AKTYWNY);
                saveEntityElement(p);
            }
            if(p.getActive().equals(KindsOfActive.NIEAKTYWNY.toString()) == true){
                formerPlayers.add(p);
            }
        }
        return formerPlayers;
    }
    
        public ObservableList<Player> findActivePlayersFromTeam(Team team) {
        
        ObservableList<Player>players = FXCollections.observableArrayList( em.createNamedQuery("Player.findByTeamId").setParameter("teamId", team).getResultList());
        
        ObservableList<Player>activePlayers = FXCollections.observableArrayList();
        
        for(Player p: players){
            
            if(p.getActive() == null){
                p.setActive(KindsOfActive.AKTYWNY);
                saveEntityElement(p);
            }
            if(p.getActive().equals(KindsOfActive.AKTYWNY.toString()) == true){
                activePlayers.add(p);
            }
        }
        return activePlayers;
    }
    
    public ObservableList<Player> findPlayersFromTeam(Team team) {
        return FXCollections.observableArrayList(em.createNamedQuery("Player.findByTeamId").setParameter("teamId", team).getResultList());
    }
    
    public ObservableList<Participated> findParticipatedListForGame(Game game){
        return FXCollections.observableArrayList(em.createNamedQuery("Participated.findByGameId").setParameter("gameId", game).getResultList());
    }
    
    public ObservableList<Participated> findParticipatedListForPlayer(Player player){
        return FXCollections.observableArrayList(em.createNamedQuery("Participated.findByPlayerId").setParameter("playerId", player).getResultList());
    }
    
    public ObservableList<Player> findPlayersForGame(Game game) {
        ObservableList<Participated>participatedList = findParticipatedListForGame(game);
        
        ObservableList<Player>players = FXCollections.observableArrayList();
        
        for(Participated p: participatedList){
            players.add(p.getPlayerId());
        }
        
        return players;
    }
    
    public List<Integer> getPlayersNumbersFromTeam(Team team){
        List<Integer>numbers = new ArrayList<>();
        
        for(Player p: findPlayersFromTeam(team)){
            numbers.add(p.getNo());
        }
        return numbers;
    }

    public List<Player> findAllPlayers() {
        return FXCollections.observableArrayList(em.createNamedQuery("Player.findAll").getResultList());
    }

    public Player findPlayer(int id) {
        return em.find(Player.class, id);
    }
    
    public ObservableList<Game> getGames(){
        return FXCollections.observableArrayList(em.createNamedQuery("Game.findAll").getResultList());
    }
    
    public Game getGame(int id){
        return em.find(Game.class, id);
    }
    
    public IEntityElement refresh(IEntityElement elem){
        em.refresh(elem);
        return elem;
    }
    
    //Logintab element's method
    public User getUser(String login){
        List<User>users = new ArrayList<>(em.createNamedQuery("User.findByLogin").setParameter("login", login).getResultList());
        
        if(users.isEmpty())
            return null;
        
        return users.get(0);
    }
}
