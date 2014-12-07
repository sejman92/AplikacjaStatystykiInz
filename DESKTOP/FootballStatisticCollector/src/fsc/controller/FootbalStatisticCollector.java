/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author Mateusz
 */
public class FootbalStatisticCollector extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource(GlobalVariables.guiResource));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            launch(args);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Połączenie z bazą zakończone niepowodzeniem\n"
                    + "Upewnij się, że jesteś połączony z internetem i spróbuj ponownie");
        }
    }
    
}
