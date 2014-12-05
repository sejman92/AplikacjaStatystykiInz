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
import fsc.model.enums.KindsOfActive;
import fsc.model.enums.Legs;
import fsc.model.enums.PartsOfBody;
import fsc.model.enums.Positions;
import fsc.model.enums.SuccessOfShot;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

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
   @FXML private Button freeKickBt;
   @FXML private Button cornerKickBt;
   @FXML private Button penaltyKickBt;
   @FXML private Button injuryBt;
   @FXML private Button faulBt;
   @FXML private Button defenseBt;
   @FXML private Button swapBt;
   @FXML private Button yelloCardBt;
   @FXML private Button redCardBt;
   @FXML private Button takeoverBt;
   @FXML private Button stopMatchBt;
   @FXML private Button startMatchBt;
   @FXML private Button goalPositiveBt;
   @FXML private Button goalNegativeBt;
   @FXML private Button pauseMatchBt;
   @FXML private ListView historyLV;
   @FXML private TextArea commentTA;
   @FXML private Label curInsertTA;  
   @FXML private Label oponentTeamNameLb;
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
   
   @FXML private ListView formerPlayersLV;
   @FXML private Label operationOnPlayerWarningTeamsManagerLb;
   @FXML private Button comeBackPlayerToTeamBt;
   
   private ObservableList<Player> formerPlayers;
   private Player selectedFormerPlayer;
   
   private DatabaseManager databaseManager;
   private ObservableList<Positions>positions;
   private Team selectedTeam;
   private Player selectedPlayer;
   private ObservableList<Player> areNotSelectedToLineup;
   private Player selectedPlayerToAction;
   private Player selectedReservePlayerToAction;
   private Lineup lineup;
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
   //@FXML private ComboBox criteriaCBAnalize;
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
   
   @FXML private Button loadTeamAs1Bt;
   @FXML private Button loadTeamAs2Bt;
   @FXML private Button loadPlayerAs1Bt;
   @FXML private Button loadPlayerAs2Bt; 
   
   @FXML private CheckBox shotCheckBox;
   @FXML private CheckBox passCheckBox;
   @FXML private CheckBox faulCheckBox;
   @FXML private CheckBox freeKickCheckBox;
   @FXML private CheckBox penaltyKickCheckBox;
   @FXML private CheckBox cornerKickCheckBox;
   @FXML private CheckBox goalCheckBox;
   @FXML private CheckBox defenseCheckBox;
   @FXML private CheckBox yellowCardsCheckBox;
   @FXML private CheckBox redCardsCheckBox;
   @FXML private CheckBox selectedPlayersCheckBox;
   @FXML private CheckBox selectedTeamCheckBox;
   @FXML private CheckBox inSelectedMatchCheckBox;
   @FXML private CheckBox inAllMatchesCheckBox;
   @FXML private CheckBox averageCheckBox;
   @FXML private CheckBox sumCheckBox;
   
   
   
   
   
   
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
        
        formerPlayers = FXCollections.observableArrayList();
        
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
            
            lineup.setTeamName(((Team)teamsLV.getSelectionModel().getSelectedItem()).getName());
            
            selectedPlayer = getSelectedPlayerInTeamsManager();
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
                addToStartingLineupButton.setDisable(true);
            }
            else
            {
                loadLineupButton.setDisable(true);
                addToStartingLineupButton.setDisable(false);
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
            selectedPlayer = getSelectedPlayerInTeamsManager();
            if(selectedPlayer == null)
                return;
            
            lineup.insertIntoReserveLineup(selectedPlayer);
            areNotSelectedToLineup.remove(selectedPlayer);
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
            reserveLineupListView.setItems(lineup.getReserveLineup());
            setAddRemoveButtonEnabling();
        }catch(Exception e){
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
        enable collect Tab
    */
    public void enableCollectViewTab(){
        if (this.lineup.isCorrect()){
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
        clearStartReserveLineups();
        selectedTeamDisableButtons();
        if(selectedTeam != null){
            nameTeamTF.setText(selectedTeam.getName());
            areNotSelectedToLineup = databaseManager.findActivePlayersFromTeam(selectedTeam);
            formerPlayers = databaseManager.findFormerPlayersFromTeam(selectedTeam);       
        }else{
            nameTeamTF.setText("");
            areNotSelectedToLineup = null;
            formerPlayers = null;
        }
        playersListViewTeamManager.setItems(areNotSelectedToLineup);
        playersListViewTeamManager.getSelectionModel().clearSelection();
        formerPlayersLV.setItems(formerPlayers);
        formerPlayersLV.getSelectionModel().clearSelection();
        //lineup = new Lineup(); #TODO
        
        selectedPlayer = getSelectedPlayerInTeamsManager();
        operationOnPlayerWarningTeamsManagerLb.setText("");
        selectedTeamDisableButtons();
    }
    
    public void playersListViewTeamManagerClick(){
        selectedPlayer = getSelectedPlayerInTeamsManager();
        if(selectedPlayer != null){
            //enable or disable add/remove to/from starting lineup button
            setAddRemoveButtonEnabling();
            editSelectedPlayerBt.setDisable(false);
            removeSelectedPlayerBt.setDisable(false);
        }
        operationOnPlayerWarningTeamsManagerLb.setText("");
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
            
            int answer = JOptionPane.showConfirmDialog(null, "Czy chcesz usunąć drużynę?\n Jej usunięcie spowoduje trwałe skasowanie jej wszystkich zawodników, meczów i statystyk?\n", "Usunięcie drużyny", JOptionPane.YES_NO_OPTION);
       
            if(answer == 0){
                databaseManager.removeEntityElement(selectedTeam);           
                teamsLV.setItems(databaseManager.getTeams(owner));
                playersListViewTeamManager.setItems(null);
            }
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
            player.setActive(KindsOfActive.AKTYWNY);
            
            if(databaseManager.getPlayersNumbersFromTeam(selectedTeam).contains(player.getNo())){
                operationOnPlayerWarningTeamsManagerLb.setText("Zawodnik z numerem " + player.getNo() + " już występuje w zespole");
                return;
            }
            
            if (databaseManager.saveEntityElement(player) != null){
                areNotSelectedToLineup.add(player);
                playersListViewTeamManager.setItems(areNotSelectedToLineup);
            }
            //playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
            selectedPlayer = getSelectedPlayerInTeamsManager();
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
        selectedPlayer = getSelectedPlayerInTeamsManager();
        if(selectedPlayer != null){
            selectedPlayer.setName(namePlayerTF.getText());
            selectedPlayer.setSurname(surnamePlayerTF.getText());
            
            Integer formerNo = selectedPlayer.getNo();
            
            selectedPlayer.setNo(Integer.parseInt(noPlayerTF.getText()));
            selectedPlayer.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            
            if(leftFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.LEWA.toString());
            else if(rightFootCheckBox.isSelected())
                selectedPlayer.setPreferedLeg(Legs.PRAWA.toString());
            
            if(formerNo.equals(selectedPlayer.getNo()) == false && databaseManager.getPlayersNumbersFromTeam(selectedPlayer.getTeamId()).contains(selectedPlayer.getNo())){
                operationOnPlayerWarningTeamsManagerLb.setText("Zawodnik z numerem " + selectedPlayer.getNo() + " już występuje w zespole");
                selectedPlayer = (Player) databaseManager.findPlayer(selectedPlayer.getId());
                return;
            }
            
            databaseManager.saveEntityElement(selectedPlayer);
            areNotSelectedToLineup.remove(selectedPlayer); //remove selected item from listView
            areNotSelectedToLineup.add(selectedPlayer); //add changed item to list :D
            playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
            //playersListViewTeamManager.setItems(databaseManager.findPlayersFromTeam(selectedTeam));
        }
    }
    /*
        come back player to team
    */
    
    public void formerPlayersLVClick(){
        operationOnPlayerWarningTeamsManagerLb.setText("");
        selectedFormerPlayer = getSelectedFormerPlayerInTeamsManager();
    }
    
    public void comeBackPlayerToTeamBtClick(){
        selectedFormerPlayer.setActive(KindsOfActive.AKTYWNY);
        databaseManager.saveEntityElement(selectedFormerPlayer);
        
        formerPlayers.remove(selectedFormerPlayer);
        formerPlayersLV.setItems(formerPlayers);
        areNotSelectedToLineup.add(selectedFormerPlayer);
        playersListViewTeamManager.setItems(areNotSelectedToLineup);
        
        selectedFormerPlayer = getSelectedFormerPlayerInTeamsManager();
    }
    /*
    when we select player and want delete him
    */
    public void removeSelectedPlayerBtClick(){
        selectedPlayer = getSelectedPlayerInTeamsManager();
        if(selectedPlayer == null){
            System.out.println("player to remove was not selected");
            return;
        }
        
        if(databaseManager.findParticipatedListForPlayer(selectedPlayer).isEmpty() == true){
            databaseManager.removeEntityElement(selectedPlayer);
            operationOnPlayerWarningTeamsManagerLb.setText("Usunięto zawodnika z drużyny");
        }else{
            selectedPlayer.setActive(KindsOfActive.NIEAKTYWNY);
            databaseManager.saveEntityElement(selectedPlayer);
            formerPlayers.add(selectedPlayer);
            formerPlayersLV.setItems(formerPlayers);
            operationOnPlayerWarningTeamsManagerLb.setText("Zawodnik rozegrał w drużynie mecz \nOznaczono go jako zawodnik nieaktywny");
        }
        
        areNotSelectedToLineup = databaseManager.findActivePlayersFromTeam(selectedTeam);
        areNotSelectedToLineup.removeAll(this.startingLineupListView.getItems());
        areNotSelectedToLineup.removeAll(this.reserveLineupListView.getItems());
        playersListViewTeamManager.setItems(areNotSelectedToLineup);
            
        selectedPlayerToAction = null;
        selectedReservePlayerToAction = null;
        actionManager.setPlayer(null);
        actionManager.setReservePlayer(null);
        
        selectedPlayer = getSelectedPlayerInTeamsManager();
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
    clear LV with start and reserve lineups
    clear Lineup lists as well
    */
    private void clearStartReserveLineups() {
        this.lineup.getStartingLineup().clear();
        this.lineup.getReserveLineup().clear();
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
        
        matchNameTF.setMaxWidth(0);
        matchNameTF.setMinWidth(0);
        matchNameTF.setText("");
        oponentTeamNameLb.setText(this.game.getOponent());
        oponentTeamNameLb.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        //enable the start/stop/pause/resume buttons
        startMatchBt.setDisable(true); 
        pauseMatchBt.setDisable(false);
        stopMatchBt.setDisable(false);
    }
    
    /*
    Just set flag if opponent name is typed int text field to let us start game  
    */
    public void validateOpponentName(){
        if( !matchNameTF.getText().isEmpty()){
            startMatchBt.setDisable(false);
        } else startMatchBt.setDisable(true);
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
        timeline.pause();
        int answer = JOptionPane.showConfirmDialog(null, "Czy chcesz zakończyć mecz?\n Jeżeli to zrobisz, nie będzie możliwości jego edycji.", "Koniec meczu", JOptionPane.YES_NO_OPTION);
       
        if(answer == 0){
            this.disableAllButtonInCollectView();
            databaseManager.saveEntityElement(game);
            game = databaseManager.getGame(game.getId());
            timeline.stop();
        } else {
            timeline.play();
        }
    }
    /*
    Fill content in collect view - player lists and team name
    */
    public void fillContentCollectView(){
        teamNameCollectView.setText(this.lineup.getTeamName());
        playersStartListCollectView.setItems(this.lineup.getStartingLineup());
        playersReserveListCollectView.setItems(this.lineup.getReserveLineup());
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
        if( (actionManager.getPartOfBody()==null) || ( !actionManager.getPartOfBody().equals(PartsOfBody.LEWA_NOGA)) ){
            actionManager.setPartOfBody(PartsOfBody.LEWA_NOGA);
        } else {
            actionManager.setPartOfBody(null);
        }
        curInsertTA.setText(actionManager.getInsert());
        
    }

    /*
    Add right foot in current action
    */
    public void rightFootBtClick(){
        if( (actionManager.getPartOfBody()==null) || ( !actionManager.getPartOfBody().equals(PartsOfBody.PRAWA_NOGA))){
            actionManager.setPartOfBody(PartsOfBody.PRAWA_NOGA);
        } else {
            actionManager.setPartOfBody(null);
        }
        curInsertTA.setText(actionManager.getInsert());       
    }
        
    /*
    Add head in current action
    */  
    public void headBtClick(){
        if( (actionManager.getPartOfBody()==null) || ( !actionManager.getPartOfBody().equals(PartsOfBody.GŁOWA))){
            actionManager.setPartOfBody(PartsOfBody.GŁOWA);
        } else {
            actionManager.setPartOfBody(null);
        }
        curInsertTA.setText(actionManager.getInsert());
    }
    
    /*
    Add chest in current action
    */            
    public void chestBtClick(){        
        if( (actionManager.getPartOfBody()==null) || ( !actionManager.getPartOfBody().equals(PartsOfBody.KLATKA))){
            actionManager.setPartOfBody(PartsOfBody.KLATKA);
        } else {
            actionManager.setPartOfBody(null);
        }
        curInsertTA.setText(actionManager.getInsert());
    }
    
    /*
    Add other part of body in current action
    */
                
    public void otherBtClick(){
        if( (actionManager.getPartOfBody()==null) || ( !actionManager.getPartOfBody().equals(PartsOfBody.INNA))){
            actionManager.setPartOfBody(PartsOfBody.INNA);
        } else {
            actionManager.setPartOfBody(null);
        }
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
        this.historyLV.getItems().add(new String("["+this.getCurrentMinute()+"min] Strata bramki"));
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
        curInsertTA.setText(actionManager.getInsert());
        this.faulButtonFlag = false;
        setSuccessButtonContent();
    }
    /*
    accept current action and add query to database 
    */
    public void acceptBtClick(){

        
        actionManager.setComment(commentTA.getText());
        actionManager.setGame(game);
        actionManager.setTime(getCurrentMinute());
        
        IAction result = actionManager.saveAction();
        game = databaseManager.getGame(game.getId()); 
        if(result != null){
            
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
            actionList.add(result);
            actionManager.cancelAction();
            curInsertTA.setText("");
            this.kickType = Kicks.NONE;
            
        }else{
            curInsertTA.setText("Wystąpił błąd. Nie dodano akcji!");
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
        curInsertTA.setText("Anulowano");
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
        getSelectedTeamAnalize();
    }
    
    public void gamesCBAnalizeAction(){
        getSelectedGameAnalize();
        
    }
    public void playersLVAnalizeClick(){
        getSelectedPlayerAnalize();
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
    /*public void criteriaCBClick(){
        this.AC.setSelectedCriteria((CompareCriteria) criteriaCBAnalize.getSelectionModel().getSelectedItem());
        this.AC.setChartTitle(AC.getSelectedCriteria().toString());

    }*/
    /*
    There we initialize params for analyze views
    */
    public void analizeTabClick(){
        teamsCBAnalize.setItems(databaseManager.getTeams(owner));
        getSelectedTeamAnalize();
        //criteriaCBAnalize.setItems(AC.getCriteriaList());
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
        AC.setSelectedPlayers(this.toComparePlayersLV.getItems());
        AC.setSelectedTeam((Team) this.teamsCBAnalize.getSelectionModel().getSelectedItem());
        AC.setSelectedGame((Game) this.gamesCBAnalize.getSelectionModel().getSelectedItem());
        setAnalyzeCriteria();
        if((this.toComparePlayersLV.getItems()!= null) && (this.selectedPlayersCheckBox.isSelected())){
            AC.drawChartForPlayers();
        } else if ((this.teamsCBAnalize.getSelectionModel().getSelectedItem() != null)&&(this.selectedTeamCheckBox.isSelected())){
            AC.drawChartForTeam();
        }
        
    }
    /*
    set flags in AnalyzeController based on checkboxes
    
    */
    void setAnalyzeCriteria(){
        AC.setShotCheckBox(this.shotCheckBox.isSelected());
        AC.setPassCheckBox(this.passCheckBox.isSelected());
        AC.setFaulCheckBox(this.faulCheckBox.isSelected());
        AC.setFreeKickCheckBox(this.freeKickCheckBox.isSelected());
        AC.setCornerKickCheckBox(this.cornerKickCheckBox.isSelected());
        AC.setPenaltyKickCheckBox(this.penaltyKickCheckBox.isSelected());
        AC.setGoalCheckBox(this.goalCheckBox.isSelected());
        AC.setYellowCardsCheckBox(this.yellowCardsCheckBox.isSelected());
        AC.setRedCardsCheckBox(this.redCardsCheckBox.isSelected());
        AC.setDefenseCheckBox(this.defenseCheckBox.isSelected());
        AC.setSumCheckBox(this.sumCheckBox.isSelected());
        AC.setAverageCheckBox(this.averageCheckBox.isSelected());
        AC.setInAllMatchesCheckBox(this.inAllMatchesCheckBox.isSelected());
        AC.setInSelectedMatchCheckBox(this.inSelectedMatchCheckBox.isSelected());
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
    
    //Helpful functions to select Team and Player in TeamsManager
    
    private Player getSelectedPlayerInTeamsManager(){
        selectedPlayer = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        
        if(selectedPlayer == null){
            editSelectedPlayerBt.setDisable(true);
            removeSelectedPlayerBt.setDisable(true);
                                
            namePlayerTF.setText("");
            surnamePlayerTF.setText("");
            
            noPlayerTF.setText("");
            positionsLV.getSelectionModel().clearSelection();
            leftFootCheckBox.setSelected(false);
            rightFootCheckBox.setSelected(false);
            
            addToStartingLineupButton.setDisable(true);
            addToReserveLineupButton.setDisable(true);            
            
        }else{
            editSelectedPlayerBt.setDisable(false);
            removeSelectedPlayerBt.setDisable(false);
            
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
            if( this.lineup.getStartingLineup().size() < GlobalVariables.MAX_PLAYERS_IN_LINEUP )
                addToStartingLineupButton.setDisable(false);
            else
                addToStartingLineupButton.setDisable(true);
            addToReserveLineupButton.setDisable(false);     
        }
        
        return selectedPlayer;
    }
    
    private Player getSelectedFormerPlayerInTeamsManager(){
        selectedFormerPlayer = (Player) formerPlayersLV.getSelectionModel().getSelectedItem();
        
        if(selectedFormerPlayer == null){
            comeBackPlayerToTeamBt.setDisable(true);
        }else{
            comeBackPlayerToTeamBt.setDisable(false);
        }
        
        return selectedFormerPlayer;
    }
    
    private Team getSelectedTeamAnalize(){
        selectedTeamAnalize = (Team)teamsCBAnalize.getSelectionModel().getSelectedItem();
            
        gamesCBAnalize.setItems(databaseManager.findGamesForTeam(selectedTeamAnalize));
            
        getSelectedGameAnalize();
        
        return selectedTeamAnalize;
    }
    
    private Game getSelectedGameAnalize(){
        selectedGameAnalize = (Game)gamesCBAnalize.getSelectionModel().getSelectedItem();
        
        playersLVAnalize.setItems(databaseManager.findPlayersForGame(selectedGameAnalize));
                    
        if(selectedGameAnalize == null){
            loadTeamAs1Bt.setDisable(true);
            loadTeamAs2Bt.setDisable(true);
        }else{
            loadTeamAs1Bt.setDisable(false);
            loadTeamAs2Bt.setDisable(false);
        }
        
        getSelectedPlayerAnalize();
        playersLVAnalize.getSelectionModel().clearSelection();
        
        return selectedGameAnalize;
    }
    
    private Player getSelectedPlayerAnalize(){
        selectedPlayerAnalize = (Player) playersLVAnalize.getSelectionModel().getSelectedItem();
        
        if(selectedPlayerAnalize == null){
            loadPlayerAs1Bt.setDisable(true);
            loadPlayerAs2Bt.setDisable(true);
        }else{
            loadPlayerAs1Bt.setDisable(false);
            loadPlayerAs2Bt.setDisable(false);
        }
        
        return selectedPlayerAnalize;
    }

    
}