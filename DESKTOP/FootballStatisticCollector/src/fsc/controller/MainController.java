/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.interfaces.IAction;
import fsc.model.Passing;
import fsc.model.Player;
import fsc.model.Shot;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fsc.model.Team;
import fsc.model.User;
import fsc.model.enums.PartOfBody;
import fsc.model.enums.Positions;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

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
   
   @FXML private Button shotBt;
   @FXML private Button passingBt;
   @FXML private Button leftFootBt;
   @FXML private Button rightFootBt;
   @FXML private Button headBt;
   @FXML private Button chestBt;
   @FXML private Button otherBt;
   @FXML private Button successfulBt;
   @FXML private Button unsuccessfulBt;
   @FXML private Button acceptBt;   
   @FXML private Button cancelBt;
   @FXML private ListView historyLV;
   @FXML private TextArea commentTA;
   @FXML private TextArea curInsertTA;   
   
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
   private Action action;
   private ObservableList<IAction>actionList;
   
   private EntityManagerFactory emFactory;
   private EntityManager em;
   private EntityTransaction et;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        teamsManager = TeamsManager.getInstance();
        action = Action.getInstance();
        // TODO 
        //there i ititialize conection with db and other stuff
        
        positions = FXCollections.observableArrayList(Positions.values());
        actionList = FXCollections.observableArrayList();
        teamsLV.setItems(teamsManager.getTeams());
        positionsLV.setItems(positions);
        lineup = new Lineup();
        //beginMatchTab.setDisable(false);
    }
    
    /*
    Add selected players to starting lineup
    if selected player is on starting lineup - do nothing
    if player is on reserve lineup - move him to starting
    */
    public void addToStartingLineupButtonClick(){
        try{
            selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
            if(selectedPlayer == null)
                return;
            if(lineup.isOnStartingLineup(selectedPlayer))
                return;
            else if(lineup.isOnReserveLineup(selectedPlayer)){
                lineup.moveFromReserveToStarting(selectedPlayer);
            }else{
                lineup.insertIntoStartingLineup(selectedPlayer);
            }
            startingLineupListView.setItems(lineup.getStartingLineup());
            
            if(lineup.isCorrect())
                loadLineupButton.setDisable(false);
            else
                loadLineupButton.setDisable(true);
                
        }catch(Exception e){
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
            if(selectedPlayer == null)
                return;
            if(lineup.isOnReserveLineup(selectedPlayer))
                return;
            else if (lineup.isOnStartingLineup(selectedPlayer)){
                lineup.moveFromStartingToReserve(selectedPlayer);
            }else{
                lineup.insertIntoReserveLineup(selectedPlayer);
            }
            reserveLineupListView.setItems(lineup.getReserveLineup());
            if( lineup.isCorrect())
                loadLineupButton.setDisable(false);
            else
                loadLineupButton.setDisable(true);
        }catch(Exception e)
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
            if(lineup.isCorrect())
                loadLineupButton.setDisable(false);
            else
                loadLineupButton.setDisable(true);
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
            if(lineup.isCorrect())
                loadLineupButton.setDisable(false);
            else
                loadLineupButton.setDisable(true);
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
        teamsManager.saveTeam(new Team(nameTeamTF.getText()));
        teamsLV.setItems(teamsManager.getTeams());
    }
    
    public void selectTeamBtClick(){
    }
    
    /*
    Took selected item in TeamListView and fill players wich is in selected team
    */
    public void teamListViewClick(){
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();     
        playersListViewTeamManager.setItems(teamsManager.findPlayersFromTeam(selectedTeam));
    }
    
    public void removeSelectedTeamBtClick(){              
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();
        if(selectedTeam != null){
            teamsManager.removeTeam(selectedTeam);           
            teamsLV.setItems(teamsManager.getTeams());
            selectedTeam = null;
            playersListViewTeamManager.setItems(null);
        }
    }
    
    /**
     * @it is implementation of add new player into player listView
    */
    public void createPlayerBtClick(){
        try{
            Player player = new Player();
            player.setName(namePlayerTF.getText());
            player.setSurname(surnamePlayerTF.getText());
            player.setNo(Integer.parseInt(noPlayerTF.getText()));
            player.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            player.setTeamId(selectedTeam);
            player.setOwnerId(new User(1));
            teamsManager.savePlayer(player);
            playersListViewTeamManager.setItems(teamsManager.findPlayersFromTeam(selectedTeam));
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
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            teamsManager.removePlayer(selectedPlayer);
            playersListViewTeamManager.setItems(teamsManager.findPlayersFromTeam(selectedTeam));
        }
        else
            System.out.println("player to remove was not selected");
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
    
    /*
    COLLECT VIEW
    
    */
    
    /*
    Fill cuntetnt in collect view - player lists and team name
    */
    public void fillContentCollectView(){
        if (this.match != null){
            teamNameCollectView.setText(this.match.getTeam().getName());
            if(!this.match.getStartingLineup().isEmpty())
                playersStartListCollectView.setItems(this.match.getStartingLineup());
            if(!this.match.getReserveLineup().isEmpty())
                playersReserveListCollectView.setItems(this.match.getReserveLineup());
        }
    }
    
    /*
    Add shot to current action
    */
    
    public void shotBtClick(){
        action.setAction(new Shot());
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add passing to current action
    */
    
    public void passingBtClick(){
        action.setAction(new Passing());
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add left foot in current action
    */
    public void leftFootBtClick(){
        action.setPartOfBody(PartOfBody.LEWA_NOGA);
        curInsertTA.setText(action.getInsert());
    }

    /*
    Add right foot in current action
    */
    public void rightFootBtClick(){
        action.setPartOfBody(PartOfBody.PRAWA_NOGA);
        curInsertTA.setText(action.getInsert());        
    }
        
    /*
    Add head in current action
    */  
    public void headBtClick(){
        action.setPartOfBody(PartOfBody.GŁOWA);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add chest in current action
    */            
    public void chestBtClick(){        
        action.setPartOfBody(PartOfBody.KLATKA);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add other part of body in current action
    */
                
    public void otherBtClick(){
        action.setPartOfBody(PartOfBody.INNA);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    mark as successful action
    */
    public void successfulBtClick(){
        action.setSuccessful(1);
        curInsertTA.setText(action.getInsert());
    }

    /*
    mark as unsuccessful action
    */
    public void unsuccessfulBtClick(){
        action.setSuccessful(-1);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    accept current action and add query to database 
    */
    public void acceptBtClick(){
        IAction result = action.saveAction();
        if(result != null){
            actionList.add(result);
            action.cancelAction();
            curInsertTA.setText("dodano");
        }else
            curInsertTA.setText("nie dodano z powodu bledu");
        historyLV.setItems(actionList);
    }
    
    /*
    reset current action
    */    
    public void cancelBtClick(){
        action.cancelAction();
        curInsertTA.setText("wycofano");
    }
}