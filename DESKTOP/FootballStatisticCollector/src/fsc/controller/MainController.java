/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fsc.model.Team;
import fsc.model.enums.Positions;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.Observable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javax.persistence.Query;

/**
 *
 * @author Mateusz
 */
public class MainController implements Initializable {
    
    
    @FXML private Label timer;
    @FXML private Label teamNameCollectView;
    
    @FXML private TextField nameTeamTF;
    @FXML private TextField namePlayerTF;
    @FXML private TextField surnamePlayerTF;
    @FXML private TextField noPlayerTF;
    
    @FXML private Button AddPlayerBt;
    @FXML private Button createTeamBt;
    @FXML private Button selectTeamBt;
    @FXML private Button removeSelectedTeamBt;
    @FXML private Button createPlayerBt;
    @FXML private Button editSelectedPlayerBt;
    @FXML private Button removeSelectedPlayerBt;
    @FXML private Button addToStartingLineup;   
    @FXML private Button loadLineupButton;
    @FXML private CheckBox leftFootCheckBox;
    @FXML private CheckBox rightFootCheckBox;
    
    @FXML private ListView playersStartListCollectView;
    @FXML private ListView playersReserveListCollectView;
    @FXML private ListView teamsLV;
    @FXML private ListView playersListViewTeamManager;
    @FXML private ListView positionsLV;    
    @FXML private ListView matchesLV;
    @FXML private ListView playersOutLV;
    @FXML private ListView playersInLV;
    @FXML private ListView startingLineupListView;
    @FXML private ListView reserveLineupListView;
    
    @FXML private Tab beginMatchTab;
    private TeamsManager teamsManager;
    private ObservableList<Positions>positions;
    private Team selectedTeam;
    private Player selectedPlayer;
    private Lineup lineup;
    private Match match;
    
    private EntityManagerFactory emFactory;
    private EntityManager em;
    private EntityTransaction et;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        teamsManager = new TeamsManager();
        positions = FXCollections.observableArrayList(Positions.values());
        teamsLV.setItems(teamsManager.getTeams());
        positionsLV.setItems(positions);
        lineup = new Lineup();
        
    }
    
    /*
    Add selected players to starting lineup
    if selected player is on starting lineup - do nothing
    if player is on reserve lineup - move him to starting
    */
    public void addToStartingLineupButtonClick(){
        try{
            selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
            if(selectedPlayer == null) return;
            if(lineup.isOnStartingLineup(selectedPlayer)) return;
            else if(lineup.isOnReserveLineup(selectedPlayer)){
                lineup.moveFromReserveToStarting(selectedPlayer);
            }else {
                lineup.insertIntoStartingLineup(selectedPlayer);
            }
            startingLineupListView.setItems(lineup.getStartingLineup());
            if( lineup.isCorrect())loadLineupButton.setDisable(false);
            else loadLineupButton.setDisable(true);
                
        }catch (Exception e){
         System.out.println("Add to starting lineup");
         System.out.println(e.getCause());
        }
    }
    /*
    Add selected player to reserve lineup
    if selected player is on reserve lineup - do nothing
    if player is on starting lineup - move him to reserve
    */
    public void addToReserveLineupButtonClick(){
        try{
            selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
            if(selectedPlayer == null) return;
            if(lineup.isOnReserveLineup(selectedPlayer)) return;
            else if (lineup.isOnStartingLineup(selectedPlayer)){
                lineup.moveFromStartingToReserve(selectedPlayer);
            } else {
                lineup.insertIntoReserveLineup(selectedPlayer);
            }
            reserveLineupListView.setItems(lineup.getReserveLineup());
            if( lineup.isCorrect())loadLineupButton.setDisable(false);
            else loadLineupButton.setDisable(true);
        }
        catch(Exception e)
        {
            System.out.println("Add to reserve lineup");
            System.out.println(e.getCause());
        }
    }
    /*
    remove selected item from Starting LineUP
    */
    public void removeFromStartingLineupButtonClick(){
        try{
            selectedPlayer  = (Player) startingLineupListView.getSelectionModel().getSelectedItem();
            if(selectedPlayer == null) return;
            lineup.getStartingLineup().remove(selectedPlayer);
            if( lineup.isCorrect())loadLineupButton.setDisable(false);
            else loadLineupButton.setDisable(true);
        } catch (Exception e){
            System.out.println("Remove from starting lineup");
            System.out.println(e.getCause());
        }
        
    }
    /*
    remove selected item from Reserve LineUP
    */
    public void removeFromReserveLineupButtonClick(){
        
        try{
            selectedPlayer  = (Player) reserveLineupListView.getSelectionModel().getSelectedItem();
            if(selectedPlayer == null) return;
            lineup.getReserveLineup().remove(selectedPlayer);
            if( lineup.isCorrect())loadLineupButton.setDisable(false);
            else loadLineupButton.setDisable(true);
        } catch (Exception e){
            System.out.println("Remove from reserve lineup");
            System.out.println(e.getCause());
        }
    }
    /*
        load current lineup to match class,
    */
    public void loadLineupToMatch(){
        if (this.lineup.isCorrect()){
            this.match = new Match(lineup.getStartingLineup(), lineup.getReserveLineup(), selectedTeam);
            beginMatchTab.setDisable(false);
        }
    }
    /*
    Create new team and insert into database
    */
    public void createTeamBtClick(){
        try{
            et = em.getTransaction();
            et.begin();
            em.createNativeQuery("INSERT INTO Team (name) values('" + nameTeamTF.getText() + "');").executeUpdate();
            et.commit();
            em.close();
            //refreshViews();
            teamsManager = new TeamsManager();
            teamsLV.setItems(teamsManager.getTeams());
        }
        catch(Exception e){
            System.out.println("wyjatek createTeam");
            System.out.println(e.getCause());
        }
    }
    
    public void selectTeamBtClick(){
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();
        nameTeamTF.setText(selectedTeam.toString());
        
    }
    /*
    Took selected item in TeamListView and fill players wich is in selected team
    */
    public void teamListViewClick(){
        this.refreshPlayersListViewTeamManager();
        this.refreshLineupViews();
    }
    public void removeSelectedTeamBtClick(){
        
    }
    /*
    Read forms and create new Player, after this add him to database,
    refresh views
    */
    public void createPlayerBtClick(){
        try{
            Player player = new Player();
            player.setName(namePlayerTF.getText());
            player.setSurname(surnamePlayerTF.getText());
            player.setNo(Integer.parseInt(noPlayerTF.getText()));
            player.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            player.setTeamId(selectedTeam);
            this.addPlayer(player);
            
            
        }
        catch(Exception e){
            System.out.println("wyjatek createPlayer");
            System.out.println(e.getCause());
        }
    }
    
    public void editSelectedPlayerBtClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
    }
 
    public void removeSelectedPlayerBtClick(){
        /*selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            selectedTeam.removePlayer(selectedPlayer);
        }*/
    }
    /*
    Dont lent pick 2 checkbox same same time
    */
    public void leftFootSelected(){
        rightFootCheckBox.setSelected(false);
    }
    public void rightFootSelected(){
        leftFootCheckBox.setSelected(false);
    }

    
    /**
     * @it is implementation of add new player into player listView
     */
    private void addPlayer(Player p){
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        et.begin();
 
        em.createNativeQuery("INSERT INTO Player (name, surname, no, role, team_id) "
                + "values( '"+p.getName()+"','"+p.getSurname()+"',"+p.getNo()+",'"+p.getRole()+"',"
                +selectedTeam.getId()+");").executeUpdate();

        et.commit();
        em.close();
        this.refreshPlayersListViewTeamManager();
    }
    /*
    Download actual list players in selected team from DB
    and fill ListView
    */
    private void refreshPlayersListViewTeamManager(){
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("Player.findByTeamId");
        query.setParameter("team_id", selectedTeam);
        selectedTeam.setPlayerList(query.getResultList());
        playersListViewTeamManager.setItems(selectedTeam.getPlayerObservableList());
        em.close();
    }
    private void refreshLineupViews(){
        lineup.getStartingLineup().clear();
        lineup.getReserveLineup().clear();
        this.startingLineupListView.setItems(lineup.getStartingLineup());
        this.reserveLineupListView.setItems(lineup.getReserveLineup());
    }
    /*
    COLLECT VIEW
    
    */
    
    /*
    Fill cuntetnt in collect view - player lists and team name
    */
    public void fillContentCollectView(){
        if (this.match != null){
            teamNameCollectView.setText(this.match.getTeam().getName());
            if(!this.match.getStartingLineup().isEmpty())playersStartListCollectView.setItems(this.match.getStartingLineup());
            if(!this.match.getReserveLineup().isEmpty())playersReserveListCollectView.setItems(this.match.getReserveLineup());
            
        }
    }
}