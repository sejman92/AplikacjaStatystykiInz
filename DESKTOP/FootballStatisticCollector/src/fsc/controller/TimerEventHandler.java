/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

/**
 *
 * @author Mateusz
 */
public class TimerEventHandler implements EventHandler<ActionEvent> {
    private Label timer;
    //private int min;
    //private int sec;
    public TimerEventHandler(Label timer){
        this.timer = timer;
    }
    @Override
        public void handle(ActionEvent event) {
            String []time  = timer.getText().split(":");
               Integer minutes = Integer.parseInt(time[0]);
               Integer seconds = Integer.parseInt(time[1]);
               if(++seconds == 60){
                   minutes++;
                   seconds = 0;
               }
               if( seconds < 10) {
                time[1] = "0"+seconds;   
               }
               else time[1] = seconds.toString();
               if( minutes < 10){
                   time[0] = "0"+minutes;
               }
               else time[0] = minutes.toString(); 
               String s = time[0]+":"+time[1];
               timer.setText(s);
        }   
}
