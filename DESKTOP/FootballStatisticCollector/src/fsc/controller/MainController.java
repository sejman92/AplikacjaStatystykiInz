/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Player;
import fsc.model.enums.Positions;
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
    @FXML private Button createPlayerBt;
    
    private TeamsManager teamsManager;
    private ObservableList<Positions>positions;
    private Team teamSelected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        teamsManager = TeamsManager.getInstance();
        proba.setItems(teamsManager.getTeams());
        // TODO 
        //there i ititialize conection with db and other stuff

        positions = FXCollections.observableArrayList(Positions.values());
        
        teamsLV.setItems(teamsManager.getTeams());
        positionsLV.setItems(positions);
        teamsManager.addTeam("atletico");

    }    

    public void createTeamBtClick()
    {
        teamsManager.addTeam(nameTeamTF.getText());
    }
    public void selectTeamBtClick()
    {
        teamSelected = (Team) teamsLV.getSelectionModel().getSelectedItem();
        nameTeamTF.setText(teamSelected.toString());
        playersLV.setItems((ObservableList) teamSelected.getPlayerCollection());
    }
    
    public void createPlayerBtClick()
    {
        try{
            /*Player player = new Player(
                    5,
                    namePlayerTF.getText(),
                    surnamePlayerTF.getText(),
                    5,
                    positionsLV.getSelectionModel().getSelectedItem().toString()
            );
            teamSelected.addPlayer(player);*/
        }
        catch(Exception e){
            
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