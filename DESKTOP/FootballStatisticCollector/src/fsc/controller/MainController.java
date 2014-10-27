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

/**
 *
 * @author Mateusz
 */
public class MainController implements Initializable {
    
    @FXML private Button AddPlayerBt;
    @FXML private Label timer;
    @FXML private Button createTeamBt;
    @FXML private TextField nameTeamTF;
    @FXML private ListView proba;
    @FXML private ListView teamsLV;
    @FXML private ListView playersLV;
    @FXML private TextField namePlayerTF;
    @FXML private TextField surnamePlayerTF;
    @FXML private TextField noPlayerTF;
    @FXML private ListView positionsLV;
    @FXML private Button selectTeamBt;
    @FXML private Button removeSelectedTeamBt;
    @FXML private Button createPlayerBt;
    @FXML private Button editSelectedPlayerBt;
    @FXML private Button removeSelectedPlayerBt;
    
    @FXML private ListView matchesLV;
    @FXML private ListView playersOutLV;
    @FXML private ListView playersInLV;
    //@FXML private Button moveInAllPlayersBt;
    //@FXML private Button moveOutAllPlayersBt;
    //@FXML private Button moveInSelectedPlayerBt;
    //@FXML private Button moveOutSelectedPlayerBt;
    
    private TeamsManager teamsManager;
    private ObservableList<Positions>positions;
    private Team teamSelected;
    private Player playerSelected;
    private Player playerInSelected;
    private Player playerOutSelected;
    private String matchSelected;
    private ObservableList<String>matches;
    private ObservableList<Player>playersIn;
    private ObservableList<Player>playersOut;
    
    private EntityManagerFactory emfInstance;
    private EntityManager em;
    private EntityTransaction et;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emfInstance = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU");
        em = emfInstance.createEntityManager();
        et = em.getTransaction();
        
        teamsManager = TeamsManager.getInstance();
        proba.setItems(teamsManager.getTeams());
        // TODO 
        //there i ititialize conection with db and other stuff
        setDisabledButtonsWhenTeamIsNotSelected(true);

        positions = FXCollections.observableArrayList(Positions.values());
        matches = FXCollections.observableArrayList();
        playersIn = FXCollections.observableArrayList();
        playersOut = FXCollections.observableArrayList();
        
        teamsLV.setItems(teamsManager.getTeams());
        positionsLV.setItems(positions);
        teamsManager.addTeam("Atletico de Madrid");

        teamSelected = teamsManager.getTeams().get(0);
        teamSelected.addPlayer(new Player("David","Villa",7,"CF"));
        teamSelected.addPlayer(new Player("Santi","Cazorla",21,"AMF"));
        teamSelected.addPlayer(new Player("Jesus","Navas",15,"RMF"));
        teamSelected.addPlayer(new Player("Victor","Valdes",1,"GK"));
        teamSelected.addPlayer(new Player("Sergio","Ramos",4,"CB"));
        
        playersLV.setItems(teamSelected.getPlayers());
        //playersOutLV.setItems(playersOut);
        //playersInLV.setItems(playersIn);
    }
    
    void setDisabledButtonsWhenTeamIsNotSelected(boolean isDisabled){
        createPlayerBt.setDisable(isDisabled);
        editSelectedPlayerBt.setDisable(isDisabled);
        removeSelectedPlayerBt.setDisable(isDisabled);
        //moveInAllPlayersBt.setDisable(isDisabled);
        //moveOutAllPlayersBt.setDisable(isDisabled);
        //moveInSelectedPlayerBt.setDisable(isDisabled);
        //moveOutSelectedPlayerBt.setDisable(isDisabled);
    }
 
    public void createTeamBtClick(){
        try{
            et.begin();
            em.createNativeQuery("INSERT INTO Team (name) values('" + namePlayerTF.getText() + "')").executeUpdate();
            et.commit();
            teamsManager.addTeam(nameTeamTF.getText()); 
            System.out.println("ok");
        }
        catch(Exception e){
            System.out.println("wyjatek createTeam");
            System.out.println(e.getCause());
        }
    }
    
    public void selectTeamBtClick(){
        teamSelected = (Team) teamsLV.getSelectionModel().getSelectedItem();
        nameTeamTF.setText(teamSelected.toString());
        playersLV.setItems(teamSelected.getPlayers());
        //playersOut.addAll(teamSelected.getPlayers());
        //playersIn.clear();
        setDisabledButtonsWhenTeamIsNotSelected(false);
    }
    
    public void removeSelectedTeamBtClick(){
        
    }
    
    public void createPlayerBtClick(){
        try{
            
            Player player = new Player(
                    namePlayerTF.getText(),
                    surnamePlayerTF.getText(),
                    Integer.getInteger(noPlayerTF.getText()),
                    positionsLV.getSelectionModel().getSelectedItem().toString()
            );
            System.out.println(noPlayerTF);
            System.out.println(noPlayerTF.getText());
            System.out.println(player.getNo());
            /*
            System.out.println("INSERT INTO Player (name, surname, no, role) values('" + player.getName() + "','" + player.getSurname() + "'," + player.getNo() + ",'" + player.getRole() + "')");
            et.begin();
            em.createNativeQuery("INSERT INTO Player (name, surname, no, role) values('" + player.getName() + "','" + player.getSurname() + "'," + player.getNo() + ",'" + player.getRole() + "')").executeUpdate();
            et.commit();
            */
            teamSelected.addPlayer(player);
            noPlayerTF.setText("" + teamSelected.getPlayers().size());
        }
        catch(Exception e){
            System.out.println("wyjatek createPlayer");
            System.out.println(e.getCause());
        }
    }
    
    public void editSelectedPlayerBtClick(){
        playerSelected = (Player) playersLV.getSelectionModel().getSelectedItem();
    }
 
    public void removeSelectedPlayerBtClick(){
        playerSelected = (Player) playersLV.getSelectionModel().getSelectedItem();
        if(playerSelected != null){
            teamSelected.removePlayer(playerSelected);
            //playersOut.remove(playerSelected);
            //playersIn.remove(playerSelected);
        }
    }
    
    
    public void selectMatchBtClick(){
        
    }
    
    public void moveInAllPlayersBtClick(){
        //playersIn.addAll(playersOut);
        //playersOut.clear();
    }
    
    public void moveOutAllPlayersBtClick(){
        //playersOut.addAll(playersIn);
        //playersIn.clear();
    }
    
    public void moveInSelectedPlayerBtClick(){
        playerOutSelected = (Player) playersOutLV.getSelectionModel().getSelectedItem();
        if (playerOutSelected != null){
            //playersIn.add(playerOutSelected);
            //playersOut.remove(playerOutSelected);
        }
    }
    
    public void moveOutSelectedPlayerBtClick(){
        playerInSelected = (Player) playersInLV.getSelectionModel().getSelectedItem();
        if( playerInSelected != null){
            //playersOut.add(playerInSelected);
            //playersIn.remove(playerInSelected);
        }
    }
    
    /**
     * @it is implementation of add new player into player listView
     */
    public void addPlayer(){
        /*EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU");
        EntityManager em = emfInstance.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createNativeQuery("INSERT INTO Player (name, surname, no, role, owner_id, team_id) values( 'pawel', 'fefs',10, 'forward','1','1')").executeUpdate();
        et.commit();*/
    }
}