/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import static fsc.controller.GlobalVariables.MAX_SUBSTITIONS;
import fsc.model.Game;
import fsc.model.Participated;
import fsc.model.Played;
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
import fsc.model.enums.ColorOfCard;
import fsc.model.enums.CompareCriteria;

import fsc.model.enums.Kicks;
import fsc.model.enums.Legs;
import fsc.model.enums.PartsOfBody;
import fsc.model.enums.Positions;
import fsc.model.enums.SuccessOfShot;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mateusz
 */

public class MainController implements Initializable {
    
   //LoginTab elements
   
    private LoginTabController loginTabControler;
    
    @FXML Tab loginTab;
    
    @FXML TextField loginLoginTF;
    @FXML PasswordField passwordLoginPF;
    
    @FXML Label loginLoginWarningLb;
    @FXML Label passwordLoginWarningLb;
    
    @FXML TextField loginRegisterTF;
    @FXML TextField nameRegisterTF;
    @FXML TextField surnameRegisterTF;
    @FXML PasswordField passwordRegisterPF;
    @FXML PasswordField repeatPasswordRegisterPF;
    
    @FXML Label loginRegisterWarningLb;
    @FXML Label nameRegisterWarningLb;
    @FXML Label surnameRegisterWarningLb;
    @FXML Label passwordRegisterWarningLb;
    @FXML Label repeatPasswordRegisterWarningLb;
    
    @FXML Button loginBt;
    @FXML Button registerBt;
    
    @FXML Label infoLoginLb;
    @FXML Label infoRegisterLb;
    
    //Other Tabs elements
    
    @FXML Tab teamsManagerTab;
    @FXML Tab analizeTab;
    
   @FXML private Label timer;
   @FXML private Label teamNameCollectView;
   @FXML private Label goalScoredLb;
   @FXML private Label goalLostLb;
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
   @FXML private TextField matchNameTF;
   
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
   @FXML Tab beginMatchTab;
   
   private DatabaseManager databaseManager;
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
   User owner;
   /*
   ANALYZE params
   */
   @FXML private ComboBox teamsCBAnalize;
   @FXML private ComboBox gamesCBAnalize;
   @FXML private ComboBox criteriaCBAnalize;
   @FXML private ListView toComparePlayersLV;
   @FXML private ListView playersLVAnalize;
   @FXML private BarChart barChartAnalize;
   
   private Team selectedTeamAnalize;
   private Game selectedGameAnalize;
   private Player selectedPlayerAnalize;
   private ObservableList<Player> playersListAnalize;
   private ObservableList<Game> gamesListAnalize;
   private ObservableList<Team> teamsListAnalize;
   
   @FXML private Label title1LbAnalize;
   @FXML private Label score1LbAnalize;
   @FXML private Label shots1LbAnalize;
   @FXML private Label accurateShots1LbAnalize;
   @FXML private Label passes1LbAnalize;
   @FXML private Label accuracyPasses1LbAnalize;
   @FXML private Label yellowCards1LbAnalize;
   @FXML private Label redCards1LbAnalize;
   @FXML private Label fauls1LbAnalize;
   @FXML private Label takeover1LbAnalize;
   @FXML private Label freekicks1LbAnalize;
   @FXML private Label corners1LbAnalize;
   @FXML private Label penalties1LbAnalize;
   
   @FXML private Label title2LbAnalize;
   @FXML private Label score2LbAnalize;
   @FXML private Label shots2LbAnalize;
   @FXML private Label accurateShots2LbAnalize;
   @FXML private Label passes2LbAnalize;
   @FXML private Label accuracyPasses2LbAnalize;
   @FXML private Label yellowCards2LbAnalize;
   @FXML private Label redCards2LbAnalize;
   @FXML private Label fauls2LbAnalize;
   @FXML private Label takeover2LbAnalize;
   @FXML private Label freekicks2LbAnalize;
   @FXML private Label corners2LbAnalize;
   @FXML private Label penalties2LbAnalize;
   
   /*
   ANALYZE PARAMS FINISH
   */
   private AnalizeController AC;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginTabControler = LoginTabController.getInstance(this);
        databaseManager = DatabaseManager.getInstance();
        actionManager = ActionManager.getInstance();
        // TODO 
        //there i ititialize conection with db and other stuff
        //owner = new User(1);
        //databaseManager.saveEntityElement(owner);
        
        positions = FXCollections.observableArrayList(Positions.values());
        
        actionList = FXCollections.observableArrayList();
        teamsLV.setItems(databaseManager.getTeams(owner));
        
        positionsLV.setItems(positions);
        lineup = new Lineup();
        faulButtonFlag = false;
        paused = false;
        kickType = Kicks.NONE; //just initialize kicktype
        //beginMatchTab.setDisable(false);
       AC = new AnalizeController();
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
        this.freeKickBt.setDisable(true);
        this.cornerKickBt.setDisable(true);
        this.penaltyKickBt.setDisable(true);
        this.injuryBt.setDisable(true);
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
        Team team = new Team();
        team.setOwnerId(owner);
        team.setName(nameTeamTF.getText());
        databaseManager.saveEntityElement(team);
        teamsLV.setItems(databaseManager.getTeams(owner));
        selectedTeamDisableButtons();
    }
    
    public void editSelectedTeamBtClick(){
        selectedTeamDisableButtons();
        if(selectedTeam != null){
            selectedTeam.setName(nameTeamTF.getText());
            databaseManager.saveEntityElement(selectedTeam);
        }
        teamsLV.setItems(databaseManager.getTeams(owner));
    }
    
    /*
    Took selected item in TeamSLV and fill players wich is in selected team
    */
    public void teamsLVClick(){
        selectedTeamDisableButtons();
        if(selectedTeam != null){
            nameTeamTF.setText(selectedTeam.getName());
            areNotSelectedToLineup = databaseManager.findPlayersFromTeam(selectedTeam);
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
        }
        selectedTeamDisableButtons();
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
        selectedTeamDisableButtons();
        if(selectedTeam != null){
            databaseManager.removeEntityElement(selectedTeam);           
            teamsLV.setItems(databaseManager.getTeams(owner));
            playersListViewTeamManager.setItems(null);
        }
    }
    
    /**
     * @it is implementation of add new player into player listView
    */
    public void createPlayerBtClick(){
        try{
            Player player = new Player();
            player.setOwnerId(owner);
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
            player.setOwnerId(owner);
            
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
        this.game.setOwnerId(owner);
        this.game.setScoredGoals(0);
        this.game.setLostGoals(0);
        this.game.setOponent(matchNameTF.getText());       
        this.game.setDate(new Date(Calendar.getInstance(Locale.getDefault()).getTimeInMillis()));
        databaseManager.saveEntityElement(game);
        
        Played played = new Played();
        played.setOwnerId(owner);
        played.setTeamId(selectedTeam.getId());//zmiana optem na referencje... do poprawy jeszcze potem ! zeby dawalo druzyne zaladowana i nie zmienilo jej jezeli druzyna sie zmieni
        played.setGameId(game);
        
        databaseManager.saveEntityElement(played);
        
        Participated participated;
        
        for(Player p: lineup.getStartingLineup()){
            participated = new Participated();
            participated.setOwnerId(owner);
            participated.setGameId(game);
            participated.setPlayerId(p);
            databaseManager.saveEntityElement(participated);
        }
        
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
        databaseManager.saveEntityElement(game);
        game = databaseManager.getGame(game.getId());
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
        actionManager.setAction(new Card());
        actionManager.setColorOfCard(ColorOfCard.ŻÓŁTA);
        curInsertTA.setText(actionManager.getInsert() + " ŻÓŁTA");
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    public void redCardBtClick(){
        actionManager.setAction(new Card());
        actionManager.setColorOfCard(ColorOfCard.CZERWONA);
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
        actionManager.setSuccessOfShot(SuccessOfShot.CELNY);
        curInsertTA.setText(actionManager.getInsert());
    }

    /*
    mark as unsuccessful action
    */
    public void unsuccessfulBtClick(){
        actionManager.setSuccessful(-1);
        actionManager.setSuccessOfShot(SuccessOfShot.NIECELNY);
        curInsertTA.setText(actionManager.getInsert());
    }
    
    /*
    insert lost goal ( just increase counter in Game obj.)
    */
    public void goalNegativeBtClick(){
        this.game.setLostGoals(this.game.getLostGoals()+1);
        this.faulButtonFlag = false;
        this.goalLostLb.setText(this.game.getLostGoals().toString());
        setSuccessButtonContent();
    }
    /*
    insert successfull shot and mark it as goal.
    */
    public void goalPositiveBtClick(){
        actionManager.setSuccessOfShot(SuccessOfShot.GOL);
        actionManager.setSuccessful(1);
        actionManager.setAction(new Shot());
        curInsertTA.setText(actionManager.getInsert() + "ZDOBYTA BRAMKA!!!");
        //this.game.setScoredGoals(this.game.getScoredGoals()+1);
        this.faulButtonFlag = false;
        //this.goalScoredLb.setText(this.game.getScoredGoals().toString());
        setSuccessButtonContent();
        
    }
    /*
    accept current action and add query to database 
    */
    public void acceptBtClick(){

        //action.setGame(gameManager.getGame(game.getId()));
        actionManager.setComment(commentTA.getText());
        actionManager.setGame(game);
        actionManager.setTime(getCurrentMinute());
        IAction result = actionManager.saveAction();
        game = databaseManager.getGame(game.getId()); 
        if(result != null){
            actionList.add(result);
            if(result instanceof Swap){
                lineup.removeFromStartingLineup(((Swap)result).getPlayerOutId());
                lineup.moveFromReserveToStarting(((Swap)result).getPlayerInId());
                
                Participated participated = new Participated();
                participated.setOwnerId(owner);
                participated.setGameId(game);
                participated.setPlayerId(((Swap)result).getPlayerInId());
                databaseManager.saveEntityElement(participated);
                
                playersStartListCollectView.setItems(lineup.getStartingLineup());
                playersReserveListCollectView.setItems(lineup.getReserveLineup());
                selectedPlayerToAction = null;
                selectedReservePlayerToAction = null;
                
                if(++numberOfsubstitions >= MAX_SUBSTITIONS){
                    swapBt.setDisable(true);
                    playersReserveListCollectView.setDisable(true);
                }          
            }else if(result instanceof Card){
                if(((Card)result).getKind().equals(ColorOfCard.CZERWONA)){
                    lineup.removeFromStartingLineup(((Card)result).getPlayerId());
                }
            } else if (result instanceof Shot){
                if(((Shot)result).getSuccess().equals(SuccessOfShot.GOL.toString())){
                    this.game.setScoredGoals(this.game.getScoredGoals()+1);
                    this.goalScoredLb.setText(this.game.getScoredGoals().toString());
                    this.databaseManager.saveEntityElement(game);
                }
            }
            
            actionManager.cancelAction();
            curInsertTA.setText("dodano");
            this.kickType = Kicks.NONE;
            
        }else{
            curInsertTA.setText("nie dodano z powodu bledu");
        }
        historyLV.setItems(actionList);
        commentTA.setText("");
        game = (Game) databaseManager.refresh(game);
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
    public int getCurrentMinute(){
        int time = 0;
        String [] t = timer.getText().split(":");
        time = Integer.parseInt(t[0]);
        return ++time;
    }
    public int getCurrentSecond(){
        int time = 0;
        String [] t = timer.getText().split(":");
        time = Integer.parseInt(t[1]);
        return time;
    }
    /*
    
    
    ANALYZE CONTROLL
    
    
    
    */
    
    public void teamsCBAnalizeAction(){
        selectedTeamAnalize = (Team)teamsCBAnalize.getSelectionModel().getSelectedItem();
        if(selectedTeamAnalize != null){
            gamesCBAnalize.setItems(databaseManager.findGamesForTeam(selectedTeamAnalize));
        }else{
            gamesCBAnalize.setItems(null);
        }
    }
    
    public void gamesCBAnalizeAction(){
        selectedGameAnalize = (Game)gamesCBAnalize.getSelectionModel().getSelectedItem();
        if(selectedGameAnalize != null){
            playersLVAnalize.setItems(databaseManager.findPlayersForGame(selectedGameAnalize));        
        }else{
            playersLVAnalize.setItems(null);
        }
    }
    public void playersLVAnalizeClick(){
        selectedPlayerAnalize = (Player) playersLVAnalize.getSelectionModel().getSelectedItem();
    }
    
    public void loadTeamAs1BtClick(){
        if(selectedGameAnalize != null){
            title1LbAnalize.setText(selectedGameAnalize.getOponent());
            score1LbAnalize.setText(selectedGameAnalize.getScoredGoals().toString() + ":" + selectedGameAnalize.getLostGoals().toString());
            shots1LbAnalize.setText(String.valueOf(selectedGameAnalize.getShotList().size()));
            accurateShots1LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccurateShotList().size()));
            passes1LbAnalize.setText(String.valueOf(selectedGameAnalize.getPassingList().size()));
            accuracyPasses1LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccuracyPasses()));
            yellowCards1LbAnalize.setText(String.valueOf(selectedGameAnalize.getYellowCards().size()));
            redCards1LbAnalize.setText(String.valueOf(selectedGameAnalize.getRedCards().size()));
            fauls1LbAnalize.setText(String.valueOf(selectedGameAnalize.getFaulList().size()));
            takeover1LbAnalize.setText(String.valueOf(selectedGameAnalize.getTakeoverList().size()));
            freekicks1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfFreeKicks()));
            corners1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfCorners()));
            penalties1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfPenalties()));
        }
    }
   
    public void loadTeamAs2BtClick(){
        if(selectedGameAnalize != null){
            title2LbAnalize.setText(selectedGameAnalize.getOponent());
            score2LbAnalize.setText(selectedGameAnalize.getScoredGoals().toString() + ":" + selectedGameAnalize.getLostGoals().toString());
            shots2LbAnalize.setText(String.valueOf(selectedGameAnalize.getShotList().size()));
            accurateShots2LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccurateShotList().size()));
            passes2LbAnalize.setText(String.valueOf(selectedGameAnalize.getPassingList().size()));
            accuracyPasses2LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccuracyPasses()));
            yellowCards2LbAnalize.setText(String.valueOf(selectedGameAnalize.getYellowCards().size()));
            redCards2LbAnalize.setText(String.valueOf(selectedGameAnalize.getRedCards().size()));
            fauls2LbAnalize.setText(String.valueOf(selectedGameAnalize.getFaulList().size()));
            takeover2LbAnalize.setText(String.valueOf(selectedGameAnalize.getTakeoverList().size()));
            freekicks2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfFreeKicks()));
            corners2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfCorners()));
            penalties2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfPenalties()));
        }
    }
    
    public void loadPlayerAs1BtClick(){
        if(selectedPlayerAnalize != null){
            title1LbAnalize.setText(selectedPlayerAnalize.getName() + " " + selectedPlayerAnalize.getSurname());
            score1LbAnalize.setText(String.valueOf(selectedGameAnalize.getGoalsPlayer(selectedPlayerAnalize).size()));
            shots1LbAnalize.setText(String.valueOf(selectedGameAnalize.getShotsPlayer(selectedPlayerAnalize).size()));
            accurateShots1LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccurateShotsPlayer(selectedPlayerAnalize).size()));
            passes1LbAnalize.setText(String.valueOf(selectedGameAnalize.getPassesPlayer(selectedPlayerAnalize).size()));
            accuracyPasses1LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccuracyPassesPlayer(selectedPlayerAnalize)));
            yellowCards1LbAnalize.setText(String.valueOf(selectedGameAnalize.getYellowCardsPlayer(selectedPlayerAnalize).size()));
            redCards1LbAnalize.setText(String.valueOf(selectedGameAnalize.getRedCardsPlayer(selectedPlayerAnalize).size()));
            fauls1LbAnalize.setText(String.valueOf(selectedGameAnalize.getFaulsPlayer(selectedPlayerAnalize).size()));
            takeover1LbAnalize.setText(String.valueOf(selectedGameAnalize.getTakeoversPlayer(selectedPlayerAnalize).size()));
            freekicks1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfFreekicksPlayer(selectedPlayerAnalize)));
            corners1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfCornersPlayer(selectedPlayerAnalize)));
            penalties1LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfPenaltiesPlayer(selectedPlayerAnalize)));
        }
    }
    
    public void loadPlayerAs2BtClick(){
        if(selectedPlayerAnalize != null){
            title2LbAnalize.setText(selectedPlayerAnalize.getName() + " " + selectedPlayerAnalize.getSurname());
            score2LbAnalize.setText(String.valueOf(selectedGameAnalize.getGoalsPlayer(selectedPlayerAnalize).size()));
            shots2LbAnalize.setText(String.valueOf(selectedGameAnalize.getShotsPlayer(selectedPlayerAnalize).size()));
            accurateShots2LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccurateShotsPlayer(selectedPlayerAnalize).size()));
            passes2LbAnalize.setText(String.valueOf(selectedGameAnalize.getPassesPlayer(selectedPlayerAnalize).size()));
            accuracyPasses2LbAnalize.setText(String.valueOf(selectedGameAnalize.getAccuracyPassesPlayer(selectedPlayerAnalize)));
            yellowCards2LbAnalize.setText(String.valueOf(selectedGameAnalize.getYellowCardsPlayer(selectedPlayerAnalize).size()));
            redCards2LbAnalize.setText(String.valueOf(selectedGameAnalize.getRedCardsPlayer(selectedPlayerAnalize).size()));
            fauls2LbAnalize.setText(String.valueOf(selectedGameAnalize.getFaulsPlayer(selectedPlayerAnalize).size()));
            takeover2LbAnalize.setText(String.valueOf(selectedGameAnalize.getTakeoversPlayer(selectedPlayerAnalize).size()));
            freekicks2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfFreekicksPlayer(selectedPlayerAnalize)));
            corners2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfCornersPlayer(selectedPlayerAnalize)));
            penalties2LbAnalize.setText(String.valueOf(selectedGameAnalize.getNumberOfPenaltiesPlayer(selectedPlayerAnalize)));
        }
    }
    public void criteriaCBClick(){
        this.AC.setSelectedCriteria((CompareCriteria) criteriaCBAnalize.getSelectionModel().getSelectedItem());
        this.AC.setChartTitle(AC.getSelectedCriteria().toString());

    }
    /*
    There we initialize params for analyze views
    */
    public void analizeTabClick(){
        teamsCBAnalize.setItems(databaseManager.getTeams(owner));
        this.criteriaCBAnalize.setItems(AC.getCriteriaList());

    }
    
    public void selectedTeamDisableButtons(){
        selectedTeam = (Team) teamsLV.getSelectionModel().getSelectedItem();
        if(selectedTeam == null){
            editSelectedTeamBt.setDisable(true);
            removeSelectedTeamBt.setDisable(true);
        }else{
            editSelectedTeamBt.setDisable(false);
            removeSelectedTeamBt.setDisable(false);
        }

    }
    public void addToComparePlayerBt(){
        Player sel = (Player) playersLVAnalize.getSelectionModel().getSelectedItem();
        AC.addPlayerToSelected(sel);
        this.toComparePlayersLV.setItems(AC.getSelectedPlayers());
    }
    public void removeToComparePlayerBt(){
        Player p = (Player) toComparePlayersLV.getSelectionModel().getSelectedItem();
        AC.removePlayerFromSelected(p);
        this.toComparePlayersLV.setItems(AC.getSelectedPlayers());
    }
    public void compareBtClick(){
        
        List<Integer> value = new ArrayList();
        List<String> plName = new ArrayList();
        for( Player p: AC.getSelectedPlayers()){
            
            plName.add(p.getSurname() + " " + p.getName());
        }
        AC.addSeries(AC.getSelectedCriteria().toString(), value, plName);
        //BarChart barChart = getNewChart();
        StackPane secondLay = new StackPane();
        XYChart.Series series1 = getSeries(AC.getSelectedCriteria());
        series1.setName(AC.getSelectedCriteria().toString());
        
        NumberAxis x = new NumberAxis();
        CategoryAxis y = new CategoryAxis();
        BarChart<Number, String> bc = new BarChart<Number, String>(x,y);
        
        bc.setTitle("Porównanie");
        //bc.getData().retainAll();
        //Player p = (Player) this.toComparePlayersLV.getSelectionModel().getSelectedItem();
        //series1.getData().add(new XYChart.Data(p.getNo(),p.getName()));
        /*for ( Series s : AC.getSeries()){
            bc.getData().add(s);
        }*/
        bc.getData().add(series1);
        secondLay.getChildren().add(bc);     
        Scene sScene = new Scene(secondLay, 400,300);
        Stage sSt = new Stage();
        sSt.setScene(sScene);
        sSt.show();
    }
    //LoginTab element's actions
    
    public void loginBtClick(){
        loginTabControler.loginBtClick();
        teamsLV.setItems(databaseManager.getTeams(owner));
    }
    public void registerBtClick(){
        loginTabControler.registerBtClick();
    }

    private BarChart<Number,String> getNewChart() {
        NumberAxis x = new NumberAxis();
        CategoryAxis y = new CategoryAxis();
        BarChart<Number, String> bc = new BarChart<Number, String>(x,y);
        bc.setTitle("Porównanie");
        for ( Series s : AC.getSeries()){
            bc.getData().add(s);
        }
        return bc;
    }

    private Series getSeries(CompareCriteria selectedCriteria) {
        XYChart.Series series = new XYChart.Series();
        series.setName(selectedCriteria.toString());
        ObservableList<Player> l = this.toComparePlayersLV.getItems();
        for( Player p : l){
            series.getData().add(getData(p, selectedCriteria));       
        }
        return series;
    }
    private XYChart.Data getData(Player p, CompareCriteria c){
        XYChart.Data d;
        int val = 0;
        switch (c){
                case Strzał:
                    val = selectedGameAnalize.getShotsPlayer(p).size();
                    break;
                case Podanie:
                    val = selectedGameAnalize.getPassesPlayer(p).size();
                    break;
                case Faul:
                    val = selectedGameAnalize.getFaulsPlayer(p).size();
                    break;
                case Karny:
                    val = selectedGameAnalize.getNumberOfPenaltiesPlayer(p);
                    break;
                case Wolny:
                    val = selectedGameAnalize.getNumberOfFreekicksPlayer(p);
                    break;
                case Obrona:
                    val = selectedGameAnalize.getNumberOfDefensesPlayer(p);
                    break;
                default:
                    val = 0;
                    break;
            }
        d = new XYChart.Data(val, p.getName()+" "+p.getSurname());
        return d;
    }
}