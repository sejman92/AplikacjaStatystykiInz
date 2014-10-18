/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Mateusz
 */
public class MainController implements Initializable {
    
    @FXML private Button AddPlayerBt;
    @FXML private Label timer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        //there i ititialize conection with db and other stuff
     
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
