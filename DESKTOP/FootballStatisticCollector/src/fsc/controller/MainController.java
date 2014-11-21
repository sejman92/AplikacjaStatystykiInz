/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import static fsc.controller.GlobalVariables.MAX_SUBSTITIONS;
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
import fsc.model.actions.Defense;
import fsc.model.actions.Faul;
import fsc.model.actions.Injury;
import fsc.model.actions.Swap;
import fsc.model.actions.Takeover;

import fsc.model.enums.Kicks;
import fsc.model.enums.Legs;
import fsc.model.enums.PartsOfBody;
import fsc.model.enums.Positions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

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
   @FXML private Button addToStartingLineupButton;
   @FXML private Button removeFromStartingLineupButton;
   @FXML private Button addToReserveLineupButton;
   @FXML private Button removeFromReserveLineupButton;
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
   @FXML private Button pauseMatchBt;
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
   private ObservableList<Player> areNotSelectedToLineup;
   private Player selectedPlayerToAction;
   private Player selectedReservePlayerToAction;
   private Lineup lineup;
   private Match match;
   private ActionManager actionManager;
   private ObservableList<IAction>actionList;
   private Game game;
   private Kicks kickType;
   private boolean faulButtonFlag;
   private boolean paused; //is game time paused
   private Timeline timeline; //it is timer event handler or somthing like that
   private int numberOfsubstitions;
   /*
   ANALYZE params
   */
   @FXML private ChoiceBox teamsCBAnalize;
   @FXML private ChoiceBox matchesBCAnalize;
   @FXML private ListView playersLVAnalize;
   
   private Team selectedTeamAnalize;
   private Match selectedMatchAnalize;
   private Player selectedPlayerAnalize;
   private ObservableList<Player> playersListAnalize;
   private ObservableList<Game> matchesListAnalize;
   private ObservableList<Team> teamsListAnalize;
   /*
   ANALYZE PARAMS FINISH
   */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseManager = DatabaseManager.getInstance();
        actionManager = ActionManager.getInstance();
        // TODO 
        //there i ititialize conection with db and other stuff
        
        positions = FXCollections.observableArrayList(Positions.values());
        actionList = FXCollections.observableArrayList();
        teamsLV.setItems(databaseManager.getTeams());
        
        positionsLV.setItems(positions);
        lineup = new Lineup();
        faulButtonFlag = false;
        paused = false;
        kickType = Kicks.NONE; //just initialize kicktype
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
            if( lineup.getStartingLineup().size() < GlobalVariables.MAX_PLAYERS_IN_LINEUP){
                lineup.insertIntoStartingLineup(selectedPlayer); 
                areNotSelectedToLineup.remove(selectedPlayer);
                playersListViewTeamManager.setItems(areNotSelectedToLineup);
            }
            
            startingLineupListView.setItems(lineup.getStartingLineup());
            
            if(lineup.isCorrect())
            {
                loadLineupButton.setDisable(false);
            }
            else
            {
                loadLineupButton.setDisable(true);
                //addToStartingLineupButton.setDisable(false);
            }
            setAddRemoveButtonEnabling();
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
            
            lineup.insertIntoReserveLineup(selectedPlayer);
            areNotSelectedToLineup.remove(selectedPlayer);
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
            reserveLineupListView.setItems(lineup.getReserveLineup());
            setAddRemoveButtonEnabling();
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
            areNotSelectedToLineup.add(selectedPlayer); //move player to players list
            if(lineup.isCorrect())
            {
                loadLineupButton.setDisable(false);
            }
            else
            {
                loadLineupButton.setDisable(true);
            }
            setAddRemoveButtonEnabling();
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
            areNotSelectedToLineup.add(selectedPlayer); //move player to players list   
            setAddRemoveButtonEnabling();
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
        areNotSelectedToLineup = databaseManager.findPlayersFromTeam(selectedTeam);
        playersListViewTeamManager.setItems(areNotSelectedToLineup);
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
            //enable or disable add/remove to/from starting lineup button
            setAddRemoveButtonEnabling();
        }
    }
    
    public void playersStartListCollectViewClick(){
        selectedPlayerToAction = (Player) playersStartListCollectView.getSelectionModel().getSelectedItem();
        if (selectedPlayerToAction != null){
            actionManager.setPlayer(selectedPlayerToAction);
            curInsertTA.setText(actionManager.getInsert());
        }
    }
    
    public void playersReserveListCollectViewClick(){
        selectedReservePlayerToAction = (Player) playersReserveListCollectView.getSelectionModel().getSelectedItem();
        if (selectedReservePlayerToAction != null){
            actionManager.setReservePlayer(selectedReservePlayerToAction);
            curInsertTA.setText(actionManager.getInsert());
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
            
            if (databaseManager.saveEntityElement(player)!= null){
                areNotSelectedToLineup.add(player);
                playersListViewTeamManager.setItems(areNotSelectedToLineup);
            }
            //playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
        catch(Exception e){
            System.out.println("wyjatek createPlayer");
            System.out.println(e.getCause());
        }
    }
    /*
    When we choose player and want edit his data
    */
    public void editSelectedPlayerBtClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            areNotSelectedToLineup.remove(selectedPlayer); //remove selected item from listView
            selectedPlayer.setName(namePlayerTF.getText());
            selectedPlayer.setSurname(surnamePlayerTF.getText());
            
            selectedPlayer.setNo(Integer.parseInt(noPlayerTF.getText()));
            selectedPlayer.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            
            if(leftFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.LEWA.toString());
            else if(rightFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.PRAWA.toString());
            
            databaseManager.saveEntityElement(selectedPlayer);
            areNotSelectedToLineup.add(selectedPlayer); //add changed item to list :D
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
            //playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
    }
    /*
    when we select player and want delete him
    */
    public void removeSelectedPlayerBtClick(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            
            databaseManager.removeEntityElement(selectedPlayer);
            areNotSelectedToLineup = databaseManager.findPlayersFromTeam(selectedTeam);
            areNotSelectedToLineup.removeAll(this.startingLineupListView.getItems());
            areNotSelectedToLineup.removeAll(this.reserveLineupListView.getItems());
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
            selectedPlayerToAction = null;
            selectedReservePlayerToAction = null;
            actionManager.setPlayer(null);
            actionManager.setReservePlayer(null);
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
    
    private void setAddRemoveButtonEnabling() {
        if (this.playersListViewTeamManager.getItems().isEmpty())
        {
            this.addToReserveLineupButton.setDisable(true);
            this.addToStartingLineupButton.setDisable(true);
        }
        else 
        {
            this.addToReserveLineupButton.setDisable(false);
            this.addToStartingLineupButton.setDisable(false);
        }
        if( this.lineup.getStartingLineup().size() < GlobalVariables.MAX_PLAYERS_IN_LINEUP ) this.addToStartingLineupButton.setDisable(false);
        else this.addToStartingLineupButton.setDisable(true);
        if( this.lineup.getStartingLineup().isEmpty()) this.removeFromStartingLineupButton.setDisable(true);
        else this.removeFromStartingLineupButton.setDisable(false);
        if( this.lineup.getReserveLineup().isEmpty()) this.removeFromReserveLineupButton.setDisable(true);
        else this.removeFromReserveLineupButton.setDisable(false);
        
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
        startTimer();

    }
    public void pauseMatchBtClick(){
        if (paused){
            timeline.play();
            this.pauseMatchBt.setText("Pauza");
            paused = false;
        } else {
            timeline.pause();
            this.pauseMatchBt.setText("Wznów");
            paused = true;
        }  
    }
    
    public void stopMatchBtClick(){
        this.disableAllButtonInCollectView();
        this.gameManager.saveGame(game);   
        timeline.stop();
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
        actionManager.setAction(new Shot());
        actionManager.setKickType(this.kickType);
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /*
    Add passing to current action
    */
    
    public void passingBtClick(){
        actionManager.setAction(new Passing());
        actionManager.setKickType(kickType);
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    public void penaltyBtClick(){
        if( this.kickType == Kicks.PENALTY) this.kickType = Kicks.NONE;
        else this.kickType = Kicks.PENALTY;
        actionManager.setKickType(kickType);
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void cornerBtClick(){
        if( this.kickType == Kicks.CORNER) this.kickType = Kicks.NONE;
        else this.kickType = Kicks.CORNER;
        actionManager.setKickType(kickType);
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void freeKickBtClick(){
        if( this.kickType == Kicks.FREE) this.kickType = Kicks.NONE;
        else this.kickType = Kicks.FREE;
        actionManager.setKickType(kickType);
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void defenseBtClick(){
        actionManager.setAction(new Defense());
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void faulBtClick(){
        actionManager.setAction(new Faul());
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = true;
        setSuccessButtonContent();
    }
    public void yellowCardBtClick(){
        actionManager.setAction(new Card("ŻÓŁTA"));
        curInsertTA.setText(actionManager.getInsert() + " ŻÓŁTA");
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void redCardBtClick(){
        actionManager.setAction(new Card("CZERWONA"));
        curInsertTA.setText(actionManager.getInsert() + " CZERWONA");
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    /*
    Add left foot in current action
    */
    public void leftFootBtClick(){
        actionManager.setPartOfBody(PartsOfBody.LEWA_NOGA);
        curInsertTA.setText(actionManager.getInsert());
        
    }

    /*
    Add right foot in current action
    */
    public void rightFootBtClick(){
        actionManager.setPartOfBody(PartsOfBody.PRAWA_NOGA);
        curInsertTA.setText(actionManager.getInsert());        
    }
        
    /*
    Add head in current action
    */  
    public void headBtClick(){
        actionManager.setPartOfBody(PartsOfBody.GŁOWA);
        curInsertTA.setText(actionManager.getInsert());
    }
    
    /*
    Add chest in current action
    */            
    public void chestBtClick(){        
        actionManager.setPartOfBody(PartsOfBody.KLATKA);
        curInsertTA.setText(actionManager.getInsert());
    }
    
    /*
    Add other part of body in current action
    */
                
    public void otherBtClick(){
        actionManager.setPartOfBody(PartsOfBody.INNA);
        curInsertTA.setText(actionManager.getInsert());
    }

    /**
     set action as injury
     */
    public void injuryBtClick(){
        actionManager.setAction(new Injury());
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /**
     set action as swap
     */
    public void swapBtClick(){
        actionManager.setAction(new Swap());
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    
    /**
     set action as takeover
     */
    public void takeoverBtClick(){
        actionManager.setAction(new Takeover());
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();        
    }
    
    
    /*
    mark as successful action
    */
    public void successfulBtClick(){
        actionManager.setSuccessful(1);
        curInsertTA.setText(actionManager.getInsert());
    }

    /*
    mark as unsuccessful action
    */
    public void unsuccessfulBtClick(){
        actionManager.setSuccessful(-1);
        curInsertTA.setText(actionManager.getInsert());
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
        actionManager.setSuccessful(1);
        actionManager.setAction(new Shot());
        curInsertTA.setText(actionManager.getInsert() + "ZDOBYTA BRAMKA!!!");
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
        actionManager.setComment(commentTA.getText());
        actionManager.setGame(game);

        IAction result = actionManager.saveAction();
        if(result != null){
            actionList.add(result);
            if(result instanceof Swap){
                lineup.removeFromStartingLineup(((Swap)result).getPlayerOutId());
                lineup.moveFromReserveToStarting(((Swap)result).getPlayerInId());
                playersStartListCollectView.setItems(lineup.getStartingLineup());
                playersReserveListCollectView.setItems(lineup.getReserveLineup());
                selectedPlayerToAction = null;
                selectedReservePlayerToAction = null;
                
                if(++numberOfsubstitions >= MAX_SUBSTITIONS){
                    swapBt.setDisable(true);
                    playersReserveListCollectView.setDisable(true);
                }          
            }
            actionManager.cancelAction();
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
        actionManager.cancelAction();
        commentTA.setText("");
        curInsertTA.setText("wycofano");
    }

    private void startTimer() {
        this.timer.setText("00:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1),new TimerEventHandler(this.timer)));
        timeline.setCycleCount(Animation.INDEFINITE); 
        timeline.play();
    }
    private int getCurrentMinute(){
        int time = 0;
        String [] t = timer.getText().split(":");
        time = Integer.parseInt(t[0]);
        return ++time;
    }
    private int getCurrentSecond(){
        int time = 0;
        String [] t = timer.getText().split(":");
        time = Integer.parseInt(t[1]);
        return time;
    }
    /*
    
    
    ANALYZE CONTROLL
    
    
    
    */
    public void playersLVAnalizeClick(){
        selectedPlayerAnalize = (Player) playersLVAnalize.getSelectionModel().getSelectedItem();
    }
    
    public void loadAs1BtClick(){
        
    }
    
    public void loadAs2BtClick(){
        
    }
    /*
    There we initialize params for analyze views
    */
    public void analizeTabClick(){
        teamsListAnalize = databaseManager.getTeams();
        teamsCBAnalize.setItems(teamsListAnalize);
        teamsCBAnalize.setValue(teamsListAnalize.get(0));
        teamsCBAnalize.getSelectionModel().selectedIndexProperty().addListener(
                new TeamsListAnalizeChangeListener(teamsListAnalize,playersListAnalize,matchesListAnalize));
        this.playersLVAnalize.setItems(playersListAnalize);
        //this.playersLVAnalize = databaseManager.findPlayersFromTeam();
    }
  
}