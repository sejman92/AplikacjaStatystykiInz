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
import javax.persistence.Query;

/**
 *
 * @author Mateusz
 */
public class MainController implements Initializable {
    
    @FXML private Button AddPlayerBt;
    @FXML private Label timer;
    @FXML private Button createTeamBt;
    @FXML private TextField nameTeamTF;
    @FXML private ListView playersListCollectView;
    @FXML private ListView teamsLV;
    @FXML private ListView playersListViewTeamManager;
    @FXML private TextField namePlayerTF;
    @FXML private TextField surnamePlayerTF;
    @FXML private TextField noPlayerTF;
    @FXML private ListView positionsLV;
    @FXML private Button selectTeamBt;
    @FXML private Button removeSelectedTeamBt;
    @FXML private Button createPlayerBt;
    @FXML private Button editSelectedPlayerBt;
    @FXML private Button removeSelectedPlayerBt;
    @FXML private CheckBox leftFootCheckBox;
    @FXML private CheckBox rightFootCheckBox;
            
    @FXML private ListView matchesLV;
    @FXML private ListView playersOutLV;
    @FXML private ListView playersInLV;
    
    private TeamsManager teamsManager;
    private ObservableList<Positions>positions;
    private Team teamSelected;
    private Player playerSelected;
    private Player playerInSelected;
    private Player playerOutSelected;
    private String matchSelected;

    private EntityManagerFactory emFactory;
    private EntityManager em;
    private EntityTransaction et;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emFactory = Persistence.createEntityManagerFactory("FootballStatisticCollectorPU"); 
        em = emFactory.createEntityManager();
        teamsManager = new TeamsManager();
        // TODO 
        //there i ititialize conection with db and other stuff
        
        positions = FXCollections.observableArrayList(Positions.values());
        teamsLV.setItems(teamsManager.getTeams());
        positionsLV.setItems(positions);
    }
    
    
 
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
        teamSelected = (Team) teamsLV.getSelectionModel().getSelectedItem();
        nameTeamTF.setText(teamSelected.toString());
        
    }
    /*
    Took selected item in TeamListView and fill players wich is in selected team
    */
    public void teamListViewClick(){
        this.refreshPlayersListViewTeamManager();
    }
    public void removeSelectedTeamBtClick(){
        
    }
    public void createPlayerBtClick(){
        try{
            Player player = new Player();
            player.setName(namePlayerTF.getText());
            player.setSurname(surnamePlayerTF.getText());
            player.setNo(Integer.parseInt(noPlayerTF.getText()));
            player.setRole(positionsLV.getSelectionModel().getSelectedItem().toString());
            player.setTeamId(teamSelected);
            this.addPlayer(player);
            
            
        }
        catch(Exception e){
            System.out.println("wyjatek createPlayer");
            System.out.println(e.getCause());
        }
    }
    
    public void editSelectedPlayerBtClick(){
        playerSelected = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
    }
 
    public void removeSelectedPlayerBtClick(){
        /*playerSelected = (Player) playersListViewTeamManager.getSelectionModel().getSelectedItem();
        if(playerSelected != null){
            teamSelected.removePlayer(playerSelected);
        }*/
    }
    
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
                +teamSelected.getId()+");").executeUpdate();

        et.commit();
        em.close();
        this.refreshPlayersListViewTeamManager();
    }
    /*
    Download actual list players in selected team from DB
    and fill ListView
    */
    private void refreshPlayersListViewTeamManager(){
        teamSelected = (Team) teamsLV.getSelectionModel().getSelectedItem();
        em = emFactory.createEntityManager();
        et = em.getTransaction();
        et.begin();
        Query query = em.createNamedQuery("Player.findByTeamId");
        query.setParameter("team_id", teamSelected);
        teamSelected.setPlayerList(query.getResultList());
        playersListViewTeamManager.setItems(teamSelected.getPlayerObservableList());
        em.close();
    }
}