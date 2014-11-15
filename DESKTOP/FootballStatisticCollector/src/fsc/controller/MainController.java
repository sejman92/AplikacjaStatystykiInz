/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.interfaces.IAction;
import fsc.model.actions.Passing;
import fsc.model.Player;
import fsc.model.actions.Shot;
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
import fsc.model.Team;
import fsc.model.User;

import fsc.model.actions.Card;
import fsc.model.actions.Corner;
import fsc.model.actions.Defense;
import fsc.model.actions.Faul;
import fsc.model.actions.Injury;
import fsc.model.actions.Penalty;
import fsc.model.actions.Swap;
import fsc.model.actions.Takeover;

import fsc.model.enums.Legs;
import fsc.model.enums.PartsOfBody;
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
   @FXML private Button editSelectedTeamBt;
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
   @FXML private Button successfullButton;
   @FXML private Button noSuccessfullButton;
   @FXML private Button acceptBt;   
   @FXML private Button cancelBt;
   @FXML private Button flatPassBt;
   @FXML private Button crossPassBt;
   @FXML private Button freeKickBt;
   @FXML private Button cornerKickBt;
   @FXML private Button penaltyKickBt;
   @FXML private Button injuryBt;
   @FXML private Button outBt;
   @FXML private Button faulBt;
   @FXML private Button defenseBt;
   @FXML private Button handBt;
   @FXML private Button swapBt;
   @FXML private Button yelloCardBt;
   @FXML private Button redCardBt;
   @FXML private Button takeoverBt;
   @FXML private Button stopMatchBt;
   @FXML private Button goalPositiveBt;
   @FXML private Button goalNegativeBt;
   
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
   
   private DatabaseManager databaseManager;
   private GameManager gameManager;
   private ObservableList<Positions>positions;
   private Team selectedTeam;
   private Player selectedPlayer;
   private ObservableList<Player> selectedPlayers;
   private Player selectedPlayerToAction;
   private Lineup lineup;
   private Match match;
   private ActionManager action;
   private ObservableList<IAction>actionList;
   private Game game;
   
   private boolean faulButtonFlag;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseManager = DatabaseManager.getInstance();
        action = ActionManager.getInstance();
        // TODO 
        //there i ititialize conection with db and other stuff
        
        positions = FXCollections.observableArrayList(Positions.values());
        actionList = FXCollections.observableArrayList();
        teamsLV.setItems(databaseManager.getTeams());
        positionsLV.setItems(positions);
        lineup = new Lineup();
        faulButtonFlag = false;
        //beginMatchTab.setDisable(false);
    }
    

    private void setSuccessButtonContent(){
        if(faulButtonFlag){
            this.successfullButton.setText("Był faulowany");
            this.noSuccessfullButton.setText("Faulował");
        } else {
            this.successfullButton.setText("Celny");
            this.noSuccessfullButton.setText("Niecelny");
        }
    }
    /*
    like name pointing
    */
    private void disableAllButtonInCollectView(){
        this.shotBt.setDisable(true);
        this.passingBt.setDisable(true);
        this.leftFootBt.setDisable(true);
        this.rightFootBt.setDisable(true);
        this.headBt.setDisable(true);
        this.chestBt.setDisable(true);
        this.otherBt.setDisable(true);
        this.successfullButton.setDisable(true);
        this.noSuccessfullButton.setDisable(true);
        this.acceptBt.setDisable(true);   
        this.cancelBt.setDisable(true);
        this.flatPassBt.setDisable(true);
        this.crossPassBt.setDisable(true);
        this.freeKickBt.setDisable(true);
        this.cornerKickBt.setDisable(true);
        this.penaltyKickBt.setDisable(true);
        this.injuryBt.setDisable(true);
        this.outBt.setDisable(true);
        this.defenseBt.setDisable(true);
        this.faulBt.setDisable(true);
        this.handBt.setDisable(true);
        this.yelloCardBt.setDisable(true);
        this.redCardBt.setDisable(true);
        this.takeoverBt.setDisable(true);
        this.swapBt.setDisable(true);
        this.goalNegativeBt.setDisable(true);
        this.goalPositiveBt.setDisable(true);
    }
    private void setBasicActionsEnable(){
        this.disableAllButtonInCollectView();
        this.shotBt.setDisable(false);
        this.passingBt.setDisable(false);
        this.defenseBt.setDisable(false);
        this.faulBt.setDisable(false);
        this.outBt.setDisable(false);
        this.injuryBt.setDisable(false);
        this.handBt.setDisable(false);
        this.swapBt.setDisable(false);
        this.yelloCardBt.setDisable(false);
        this.redCardBt.setDisable(false);
        this.takeoverBt.setDisable(false);
        
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
        databaseManager.saveEntityElement(new Team(nameTeamTF.getText()));
        teamsLV.setItems(databaseManager.getTeams());
    }
    
    public void editSelectedTeamBtClick(){
        selectedTeam.setName(nameTeamTF.getText());
        databaseManager.saveEntityElement(selectedTeam);
        teamsLV.setItems(databaseManager.getTeams());
    }
    
    /*
    Took selected item in TeamSLV and fill players wich is in selected team
    */
    public void teamsLVClick(){
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();     
        playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        nameTeamTF.setText(selectedTeam.getName());
    }
    
    public void playersListViewTeamManagerClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            namePlayerTF.setText(selectedPlayer.getName());
            surnamePlayerTF.setText(selectedPlayer.getSurname());
            
            noPlayerTF.setText(selectedPlayer.getNo().toString());
            positionsLV.getSelectionModel().select(selectedPlayer.getPositions());
            
            if(selectedPlayer.getPreferedLegs().equals(Legs.LEWA)){
                leftFootCheckBox.setSelected(true);
                rightFootCheckBox.setSelected(false);
            }else if(selectedPlayer.getPreferedLegs().equals(Legs.PRAWA)){
                leftFootCheckBox.setSelected(false);
                rightFootCheckBox.setSelected(true);
            }else{
                leftFootCheckBox.setSelected(false);
                rightFootCheckBox.setSelected(false);
            }
        }
    }
    
    public void playersStartListCollectViewClick(){
        selectedPlayerToAction = (Player) playersStartListCollectView.getSelectionModel().getSelectedItem();
        if (selectedPlayerToAction != null){
            action.setPlayer(selectedPlayerToAction);
            curInsertTA.setText(action.getInsert());
        }
    }
    
    public void playersReserveListCollectViewClick(){
        selectedPlayerToAction = (Player) playersReserveListCollectView.getSelectionModel().getSelectedItem();
        if (selectedPlayerToAction != null){
            action.setPlayer(selectedPlayerToAction);
            curInsertTA.setText(action.getInsert());
        }       
    }
    
    public void removeSelectedTeamBtClick(){              
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();
        if(selectedTeam != null){
            databaseManager.removeEntityElement(selectedTeam);           
            teamsLV.setItems(databaseManager.getTeams());
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
            if(positionsLV.getSelectionModel().getSelectedItem() != null)
                player.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            else
                return;       
            if(leftFootCheckBox.isSelected())
                player.setPreferedLeg(Legs.LEWA.toString());
            else 
                player.setPreferedLeg(Legs.PRAWA.toString());
            player.setTeamId(selectedTeam);
            player.setOwnerId(new User(1));
            
            databaseManager.saveEntityElement(player);
            playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
        catch(Exception e){
            System.out.println("wyjatek createPlayer");
            System.out.println(e.getCause());
        }
    }
    
    public void editSelectedPlayerBtClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            selectedPlayer.setName(namePlayerTF.getText());
            selectedPlayer.setSurname(surnamePlayerTF.getText());
            
            selectedPlayer.setNo(Integer.parseInt(noPlayerTF.getText()));
            selectedPlayer.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            
            if(leftFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.LEWA.toString());
            else if(rightFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.PRAWA.toString());
            
            databaseManager.saveEntityElement(selectedPlayer);
            playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
    }
 
    public void removeSelectedPlayerBtClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            databaseManager.removeEntityElement(selectedPlayer);
            playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
        else
            System.out.println("player to remove was not selected");
    }
    
    /*
    Dont let pick 2 checkbox at the same time
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
    
    
    public void startMatchBtClick(){
        this.game = new Game();
        this.game.setScoredGoals(0);
        this.game.setLostGoals(0);
        this.gameManager = GameManager.getInstance();   
        gameManager.saveGame(game);
        setSuccessButtonContent();
        
    }
    
    public void stopMatchBtClick(){
        this.disableAllButtonInCollectView();
        this.gameManager.saveGame(game);   
    }
    /*
    Fill content in collect view - player lists and team name
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
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /*
    Add passing to current action
    */
    
    public void passingBtClick(){
        action.setAction(new Passing());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    public void penaltyBtClick(){
        action.setAction(new Penalty());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void cornerBtClick(){
        action.setAction(new Corner());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void defenseBtClick(){
        action.setAction(new Defense());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void faulBtClick(){
        action.setAction(new Faul());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = true;
        setSuccessButtonContent();
    }
    public void yellowCardBtClick(){
        action.setAction(new Card("ŻÓŁTA"));
        curInsertTA.setText(action.getInsert() + " ŻÓŁTA");
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void redCardBtClick(){
        action.setAction(new Card("CZERWONA"));
        curInsertTA.setText(action.getInsert() + " CZERWONA");
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    /*
    Add left foot in current action
    */
    public void leftFootBtClick(){
        action.setPartOfBody(PartsOfBody.LEWA_NOGA);
        curInsertTA.setText(action.getInsert());
        
    }

    /*
    Add right foot in current action
    */
    public void rightFootBtClick(){
        action.setPartOfBody(PartsOfBody.PRAWA_NOGA);
        curInsertTA.setText(action.getInsert());        
    }
        
    /*
    Add head in current action
    */  
    public void headBtClick(){
        action.setPartOfBody(PartsOfBody.GŁOWA);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add chest in current action
    */            
    public void chestBtClick(){        
        action.setPartOfBody(PartsOfBody.KLATKA);
        curInsertTA.setText(action.getInsert());
    }
    
    /*
    Add other part of body in current action
    */
                
    public void otherBtClick(){
        action.setPartOfBody(PartsOfBody.INNA);
        curInsertTA.setText(action.getInsert());
    }

    /**
     set action as injury
     */
    public void injuryBtClick(){
        action.setAction(new Injury());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /**
     set action as swap
     */
    public void swapBtClick(){
        action.setAction(new Swap());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /**
     set action as takeover
     */
    public void takeoverBtClick(){
        action.setAction(new Takeover());
        curInsertTA.setText(action.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();        
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
    insert lost goal ( just increase counter in Game obj.)
    */
    public void goalNegativeBtClick(){
        this.game.setLostGoals(this.game.getLostGoals()+1);
        gameManager.saveGame(game);
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    /*
    insert successfull shot and mark it as goal.
    */
    public void goalPositiveBtClick(){
        action.setSuccessful(1);
        action.setAction(new Shot());
        curInsertTA.setText(action.getInsert() + "ZDOBYTA BRAMKA!!!");
        this.game.setScoredGoals(this.game.getScoredGoals()+1);
        gameManager.saveGame(game);
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    /*
    accept current action and add query to database 
    */
    public void acceptBtClick(){

        //action.setGame(gameManager.getGame(game.getId()));
        action.setComment(commentTA.getText());
        action.setGame(game);

        IAction result = action.saveAction();
        if(result != null){
            actionList.add(result);
            action.cancelAction();
            curInsertTA.setText("dodano");
        }else
            curInsertTA.setText("nie dodano z powodu bledu");
        historyLV.setItems(actionList);
        commentTA.setText("");
    }
    
    /*
    reset current action
    */    
    public void cancelBtClick(){
        action.cancelAction();
        commentTA.setText("");
        curInsertTA.setText("wycofano");
    }
}