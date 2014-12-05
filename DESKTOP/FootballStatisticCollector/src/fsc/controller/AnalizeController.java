/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.Team;
import fsc.model.actions.Card;
import fsc.model.actions.Defense;
import fsc.model.actions.Faul;
import fsc.model.actions.Passing;
import fsc.model.actions.Shot;
import fsc.model.enums.CompareCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mateusz
 */
public class AnalizeController {
    private ObservableList<CompareCriteria> criteriaList;
    private ObservableList<Player> selectedPlayers;
    private BarChart chart;
    private NumberAxis xAxis;
    private CategoryAxis yAxis;
    private ArrayList<XYChart.Series> series;
    private CompareCriteria selectedCriteria;
    private Team selectedTeam;
    private Game selectedGame;
    private boolean shotCheckBox;
    private boolean passCheckBox;
    private boolean faulCheckBox;
    private boolean freeKickCheckBox;
    private boolean penaltyKickCheckBox;
    private boolean cornerKickCheckBox;
    private boolean goalCheckBox;
    private boolean defenseCheckBox;
    private boolean yellowCardsCheckBox;
    private boolean redCardsCheckBox;
    private boolean selectedPlayersCheckBox;
    private boolean selectedTeamCheckBox;
    private boolean inSelectedMatchCheckBox;
    private boolean inAllMatchesCheckBox;
    private boolean averageCheckBox;
    private boolean sumCheckBox;
    private boolean unsuccessCheckBox;
    private boolean successCheckBox;
    /*
    * default constructor
    */
    public AnalizeController(){
        this.selectedPlayers = FXCollections.observableArrayList();
        this.criteriaList = getDeclaredCriteria();
        this.xAxis = new NumberAxis();
        this.yAxis = new CategoryAxis();
        this.series = new ArrayList();
        this.chart = new BarChart<Number,String>(getxAxis(), getyAxis());
    }

    private ObservableList<CompareCriteria> getDeclaredCriteria() {
        return FXCollections.observableArrayList(CompareCriteria.values());      
    }
    
    public void setYAxisCategory(String category){
        this.setyAxis(new CategoryAxis());
        getyAxis().setLabel(category);
    }
    public void setXAxis(String label){
        this.setxAxis(new NumberAxis());
        getxAxis().setLabel(label);
        getxAxis().setTickLabelRotation(90); //easier to read
    }
    
    public void setChartTitle(String title){
        getChart().setTitle(title);
    }
    public void addSeries(String name, List<Integer> value, List<String> playerName){
        XYChart.Series s = new XYChart.Series();
        for(int i = 0; i<value.size(); i++){
            
            XYChart.Data d = new XYChart.Data((Number)value.get(i), playerName.get(i));
            s.getData().add(d);
            series.add(s);
        }
        
        getChart().getData().add(s);
    }
    
    public void addPlayerToSelected(Player sel) {
        this.selectedPlayers.add(sel);
    }
    public void removePlayerFromSelected(Player p){
        this.selectedPlayers.remove(p);
    }
    /**
     * @return the chart
     */
    public BarChart getChart() {
        return chart;
    }

    /**
     * @param chart the chart to set
     */
    public void setChart(BarChart chart) {
        this.chart = chart;
    }

    /**
     * @return the xAxis
     */
    public NumberAxis getxAxis() {
        return xAxis;
    }

    /**
     * @param xAxis the xAxis to set
     */
    public void setxAxis(NumberAxis xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * @return the yAxis
     */
    public CategoryAxis getyAxis() {
        return yAxis;
    }

    /**
     * @param yAxis the yAxis to set
     */
    public void setyAxis(CategoryAxis yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * @return the series
     */
    public ArrayList<XYChart.Series> getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(ArrayList<XYChart.Series> series) {
        this.series = series;
    }
    /**
     * @return the criteriaList
     */
    public ObservableList<CompareCriteria> getCriteriaList() {
        return criteriaList;
    }

    /**
     * @param criteriaList the criteriaList to set
     */
    public void setCriteriaList(ObservableList<CompareCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     * @return the selectedCriteria
     */
    public CompareCriteria getSelectedCriteria() {
        return selectedCriteria;
    }

    /**
     * @param selectedCriteria the selectedCriteria to set
     */
    public void setSelectedCriteria(CompareCriteria selectedCriteria) {
        this.selectedCriteria = selectedCriteria;
        //this.chart.setTitle(selectedCriteria.toString());
    }

    /**
     * @return the selectedPlayers
     */
    public ObservableList<Player> getSelectedPlayers() {
        return selectedPlayers;
    }

    /**
     * @param selectedPlayers the selectedPlayers to set
     */
    public void setSelectedPlayers(ObservableList<Player> selectedPlayers) {
        this.selectedPlayers = selectedPlayers;
    }

    void drawChartForPlayers() {
        List<XYChart.Series> series = new ArrayList();
        if (this.isShotCheckBox()){       
            if(this.isInAllMatchesCheckBox()){
                if( this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesShotInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesShotInAllMatchesByPlayers(!sumCheckBox,-1));
                } else if( this.isSuccessCheckBox()) {
                    series.add(doSeriesShotInAllMatchesByPlayers(!sumCheckBox,1));
                } else if (this.isUnsuccessCheckBox()){
                    series.add(doSeriesShotInAllMatchesByPlayers(!sumCheckBox,-1));
                } else { //sum succ and unsucc actions
                    series.add(doSeriesShotInAllMatchesByPlayers(!sumCheckBox,0));
                }                
            }
            if(this.isInSelectedMatchCheckBox()){
                if( this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesShotInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesShotInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else if(this.isSuccessCheckBox()){
                    series.add(doSeriesShotInSelectedMatchByPlayers(!sumCheckBox,1));
                } else if(this.isUnsuccessCheckBox()){
                    series.add(doSeriesShotInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesShotInSelectedMatchByPlayers(!sumCheckBox,0));
                }
                
            }
        }
        if(this.isPassCheckBox()){
            if(this.isInAllMatchesCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesPassInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesPassInAllMatchesByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesPassInAllMatchesByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesPassInAllMatchesByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesPassInAllMatchesByPlayers(!sumCheckBox,0));
                }
            }
            if(this.isInSelectedMatchCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesPassInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesPassInSelectedMatchByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesPassInSelectedMatchByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesPassInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesPassInSelectedMatchByPlayers(!sumCheckBox,0));
                }
            }            
        }
        if(this.isFaulCheckBox()){
                
            if(this.isInAllMatchesCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesFaulInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesFaulInAllMatchesByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesFaulInAllMatchesByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesFaulInAllMatchesByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesFaulInAllMatchesByPlayers(!sumCheckBox,0));
                }
            }
            if(this.isInSelectedMatchCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesFaulInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesFaulInSelectedMatchByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesFaulInSelectedMatchByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesFaulInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesFaulInSelectedMatchByPlayers(!sumCheckBox,0));
                }
            }
        }
        if(this.isFreeKickCheckBox()){
                
            if(this.isInAllMatchesCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesFreeKickInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesFreeKickInAllMatchesByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesFreeKickInAllMatchesByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesFreeKickInAllMatchesByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesFreeKickInAllMatchesByPlayers(!sumCheckBox,0));
                }
                
            }
            if(this.isInSelectedMatchCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesFreeKickInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesFreeKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesFreeKickInSelectedMatchByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesFreeKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesFreeKickInSelectedMatchByPlayers(!sumCheckBox,0));
                }
            }
        }
        if( this.isCornerKickCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){

                }else if( this.isSuccessCheckBox()){

                }else if( this.isUnsuccessCheckBox()){

                } else {

                }
            if(this.isInAllMatchesCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesCornerKickInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesCornerKickInAllMatchesByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesCornerKickInAllMatchesByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesCornerKickInAllMatchesByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesCornerKickInAllMatchesByPlayers(!sumCheckBox,0));
                }      
            }
            if(this.isInSelectedMatchCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesCornerKickInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesCornerKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesCornerKickInSelectedMatchByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesCornerKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesCornerKickInSelectedMatchByPlayers(!sumCheckBox,0));
                }
            }
        }
        if( this.isPenaltyKickCheckBox()){
                
            if(this.isInAllMatchesCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInAllMatchesByPlayers(!sumCheckBox,1));
                    series.add(doSeriesPenaltyKickInAllMatchesByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInAllMatchesByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInAllMatchesByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesPenaltyKickInAllMatchesByPlayers(!sumCheckBox,0));
                }
            }
            if(this.isInSelectedMatchCheckBox()){
                if(this.isSuccessCheckBox() && this.isUnsuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInSelectedMatchByPlayers(!sumCheckBox,1));
                    series.add(doSeriesPenaltyKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                }else if( this.isSuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInSelectedMatchByPlayers(!sumCheckBox,1));
                }else if( this.isUnsuccessCheckBox()){
                    series.add(doSeriesPenaltyKickInSelectedMatchByPlayers(!sumCheckBox,-1));
                } else {
                    series.add(doSeriesPenaltyKickInSelectedMatchByPlayers(!sumCheckBox,0));
                }
            }
        }
        if(this.isGoalCheckBox()){
            XYChart.Series s = new XYChart.Series();
            if(this.isInAllMatchesCheckBox()) s = doSeriesGoalInAllMatchesByPlayers(!sumCheckBox);
            if(this.isInSelectedMatchCheckBox()) s = doSeriesGoalInSelectedMatchByPlayers(!sumCheckBox);
            series.add(s);
        }
        if(this.isDefenseCheckBox()){
            XYChart.Series s = new XYChart.Series();
            if(this.isInAllMatchesCheckBox()) s = doSeriesDefenseInAllMatchesByPlayers(!sumCheckBox);
            if(this.isInSelectedMatchCheckBox()) s = doSeriesDefenseInSelectedMatchByPlayers(!sumCheckBox);
            series.add(s);
        }
        if(this.isYellowCardsCheckBox()){
            XYChart.Series s = new XYChart.Series();
            if(this.isInAllMatchesCheckBox()) s = doSeriesYellowCardsInAllMatchesByPlayers(!sumCheckBox);
            if(this.isInSelectedMatchCheckBox()) s = doSeriesYellowCardsInSelectedMatchByPlayers(!sumCheckBox);
            series.add(s);
        }
        if(this.isRedCardsCheckBox()){
            XYChart.Series s = new XYChart.Series();
            if(this.isInAllMatchesCheckBox()) s = doSeriesRedCardsInAllMatchesByPlayers(!sumCheckBox);
            if(this.isInSelectedMatchCheckBox()) s = doSeriesRedCardsInSelectedMatchByPlayers(!sumCheckBox);
            series.add(s);
        }
        //todo others
        
        StackPane layer = new StackPane();
        
        NumberAxis x = new NumberAxis();
        
        CategoryAxis y = new CategoryAxis();
        BarChart<Number, String> chart = new BarChart<Number, String>(x,y);
        chart.setTitle("Wykres");
        
        for(XYChart.Series s: series){
            chart.getData().add(s);
        }
        /*
        Add tooltip based on: https://community.oracle.com/thread/2345433?tstart=0
        */
        for (final Series<Number, String>ser : chart.getData()) {
            for (final Data<Number, String> data : ser.getData()) {
                 Tooltip tooltip = new Tooltip();
                 tooltip.setText(data.getXValue().toString());
                 Tooltip.install(data.getNode(), tooltip);   
            }
        }
        layer.getChildren().add(chart);
        Scene scene = new Scene (layer, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @return the selectedTeam
     */
    public Team getSelectedTeam() {
        return selectedTeam;
    }

    /**
     * @param selectedTeam the selectedTeam to set
     */
    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }

    /**
     * @return the selectedGame
     */
    public Game getSelectedGame() {
        return selectedGame;
    }

    /**
     * @param selectedGame the selectedGame to set
     */
    public void setSelectedGame(Game selectedGame) {
        this.selectedGame = selectedGame;
    }

    /**
     * @return the shotCheckBox
     */
    public boolean isShotCheckBox() {
        return shotCheckBox;
    }

    /**
     * @param shotCheckBox the shotCheckBox to set
     */
    public void setShotCheckBox(boolean shotCheckBox) {
        this.shotCheckBox = shotCheckBox;
    }

    /**
     * @return the passCheckBox
     */
    public boolean isPassCheckBox() {
        return passCheckBox;
    }

    /**
     * @param passCheckBox the passCheckBox to set
     */
    public void setPassCheckBox(boolean passCheckBox) {
        this.passCheckBox = passCheckBox;
    }

    /**
     * @return the faulCheckBox
     */
    public boolean isFaulCheckBox() {
        return faulCheckBox;
    }

    /**
     * @param faulCheckBox the faulCheckBox to set
     */
    public void setFaulCheckBox(boolean faulCheckBox) {
        this.faulCheckBox = faulCheckBox;
    }

    /**
     * @return the freeKickCheckBox
     */
    public boolean isFreeKickCheckBox() {
        return freeKickCheckBox;
    }

    /**
     * @param freeKickCheckBox the freeKickCheckBox to set
     */
    public void setFreeKickCheckBox(boolean freeKickCheckBox) {
        this.freeKickCheckBox = freeKickCheckBox;
    }

    /**
     * @return the penaltyKickCheckBox
     */
    public boolean isPenaltyKickCheckBox() {
        return penaltyKickCheckBox;
    }

    /**
     * @param penaltyKickCheckBox the penaltyKickCheckBox to set
     */
    public void setPenaltyKickCheckBox(boolean penaltyKickCheckBox) {
        this.penaltyKickCheckBox = penaltyKickCheckBox;
    }

    /**
     * @return the cornerKickCheckBox
     */
    public boolean isCornerKickCheckBox() {
        return cornerKickCheckBox;
    }

    /**
     * @param cornerKickCheckBox the cornerKickCheckBox to set
     */
    public void setCornerKickCheckBox(boolean cornerKickCheckBox) {
        this.cornerKickCheckBox = cornerKickCheckBox;
    }

    /**
     * @return the goalCheckBox
     */
    public boolean isGoalCheckBox() {
        return goalCheckBox;
    }

    /**
     * @param goalCheckBox the goalCheckBox to set
     */
    public void setGoalCheckBox(boolean goalCheckBox) {
        this.goalCheckBox = goalCheckBox;
    }

    /**
     * @return the defenseCheckBox
     */
    public boolean isDefenseCheckBox() {
        return defenseCheckBox;
    }

    /**
     * @param defenseCheckBox the defenseCheckBox to set
     */
    public void setDefenseCheckBox(boolean defenseCheckBox) {
        this.defenseCheckBox = defenseCheckBox;
    }

    /**
     * @return the yellowCardsCheckBox
     */
    public boolean isYellowCardsCheckBox() {
        return yellowCardsCheckBox;
    }

    /**
     * @param yellowCardsCheckBox the yellowCardsCheckBox to set
     */
    public void setYellowCardsCheckBox(boolean yellowCardsCheckBox) {
        this.yellowCardsCheckBox = yellowCardsCheckBox;
    }

    /**
     * @return the redCardsCheckBox
     */
    public boolean isRedCardsCheckBox() {
        return redCardsCheckBox;
    }

    /**
     * @param redCardsCheckBox the redCardsCheckBox to set
     */
    public void setRedCardsCheckBox(boolean redCardsCheckBox) {
        this.redCardsCheckBox = redCardsCheckBox;
    }

    /**
     * @return the selectedPlayersCheckBox
     */
    public boolean isSelectedPlayersCheckBox() {
        return selectedPlayersCheckBox;
    }

    /**
     * @param selectedPlayersCheckBox the selectedPlayersCheckBox to set
     */
    public void setSelectedPlayersCheckBox(boolean selectedPlayersCheckBox) {
        this.selectedPlayersCheckBox = selectedPlayersCheckBox;
    }

    /**
     * @return the selectedTeamCheckBox
     */
    public boolean isSelectedTeamCheckBox() {
        return selectedTeamCheckBox;
    }

    /**
     * @param selectedTeamCheckBox the selectedTeamCheckBox to set
     */
    public void setSelectedTeamCheckBox(boolean selectedTeamCheckBox) {
        this.selectedTeamCheckBox = selectedTeamCheckBox;
    }

    /**
     * @return the inSelectedMatchCheckBox
     */
    public boolean isInSelectedMatchCheckBox() {
        return inSelectedMatchCheckBox;
    }

    /**
     * @param inSelectedMatchCheckBox the inSelectedMatchCheckBox to set
     */
    public void setInSelectedMatchCheckBox(boolean inSelectedMatchCheckBox) {
        this.inSelectedMatchCheckBox = inSelectedMatchCheckBox;
    }

    /**
     * @return the inAllMatchesCheckBox
     */
    public boolean isInAllMatchesCheckBox() {
        return inAllMatchesCheckBox;
    }

    /**
     * @param inAllMatchesCheckBox the inAllMatchesCheckBox to set
     */
    public void setInAllMatchesCheckBox(boolean inAllMatchesCheckBox) {
        this.inAllMatchesCheckBox = inAllMatchesCheckBox;
    }


    /**
     * @return the averageCheckBox
     */
    public boolean isAverageCheckBox() {
        return averageCheckBox;
    }

    /**
     * @param averageCheckBox the averageCheckBox to set
     */
    public void setAverageCheckBox(boolean averageCheckBox) {
        this.averageCheckBox = averageCheckBox;
    }

    /**
     * @return the sumCheckBox
     */
    public boolean isSumCheckBox() {
        return sumCheckBox;
    }

    /**
     * @param sumCheckBox the sumCheckBox to set
     */
    public void setSumCheckBox(boolean sumCheckBox) {
        this.sumCheckBox = sumCheckBox;
    }

        /**
     * @return the unsuccessCheckBox
     */
    public boolean isUnsuccessCheckBox() {
        return unsuccessCheckBox;
    }

    /**
     * @param unsuccessCheckBox the unsuccessCheckBox to set
     */
    public void setUnsuccessCheckBox(boolean unsuccessCheckBox) {
        this.unsuccessCheckBox = unsuccessCheckBox;
    }

    /**
     * @return the successCheckBox
     */
    public boolean isSuccessCheckBox() {
        return successCheckBox;
    }

    /**
     * @param successCheckBox the successCheckBox to set
     */
    public void setSuccessCheckBox(boolean successCheckBox) {
        this.successCheckBox = successCheckBox;
    }
    
    /*
    *About all doSeries**** methods :
    * @param average - if true we want calc average stats
    * @param SU : 
    if -1 = unsuccess stats
    if 1 = success only
    if 0 = sum of both in 1 bar
    */
    private XYChart.Series doSeriesShotInAllMatchesByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0; 
                            
                            for( Shot shot : p.getShotList()){
                                if(shot.getSuccess()== null || shot.getSuccess().contains("NIECELNY"))  value++;
                            }  
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów niecelnych ze wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0; 
                            
                            for( Shot shot : p.getShotList()){
                                if(shot.getSuccess()== null || shot.getSuccess().contains("NIECELNY"))  value++;
                            }  
                            
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba strzałów niecelnych na mecz");
                }
            break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0; 
                            for( Shot shot : p.getShotList()){
                                if(shot.getSuccess()!= null && (shot.getSuccess().contains("CELNY") || shot.getSuccess().contains("GOL")))  value++;
                            }  
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów celnych ze wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0; 
                            for( Shot shot : p.getShotList()){
                                if(shot.getSuccess()!= null && (shot.getSuccess().contains("CELNY") || shot.getSuccess().contains("GOL")))  value++;
                            }  
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba strzałów celnych na mecz");
                }
            break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getShotList().size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów z wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getShotList().size();
                            double v = value/(double)p.getParticipatedList().size();
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba strzałów na mecz");
                }
            break;
        }
        return s;
    }

    private XYChart.Series doSeriesShotInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1: //unsuccess
                 if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot: this.selectedGame.getShotsPlayer(p)){
                                if( shot.getSuccess()== null || shot.getSuccess()== "NIECELNY") value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów niecelnych w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia strzałów niecelnych w meczu: " + this.selectedGame.getOponent());
                }
            break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot: this.selectedGame.getShotsPlayer(p)){
                                if(shot.getSuccess() != null && (shot.getSuccess()== "CELNY" || shot.getSuccess()== "GOL")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów celnych w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia strzałów celnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
                
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getShotsPlayer(p).size();

                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma strzałów w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia strzałów w meczu: " + this.selectedGame.getOponent());
                }
            break;
        }
       return s;
    }

    private XYChart.Series doSeriesPassInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass : this.selectedGame.getPassesPlayer(p)){
                                if(pass.getSuccessful()== null || !pass.getSuccessful()) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych podań w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia niecelnych podań w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass : this.selectedGame.getPassesPlayer(p)){
                                if(pass != null && pass.getSuccessful()) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych podań w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia celnych podań w meczu: " + this.selectedGame.getOponent());
                }
                break;
                
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getPassesPlayer(p).size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma podań w meczu: " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia podań w meczu: " + this.selectedGame.getOponent());
                }
                break;
        }
        
        return s;
    }

    private XYChart.Series doSeriesPassInAllMatchesByPlayers(boolean average, int SU) {
        
        XYChart.Series s = new XYChart.Series();
        switch(SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass: p.getPassingList()){
                                if(pass.getSuccessful() == null || !pass.getSuccessful()) value ++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma podań niecelnych z wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass: p.getPassingList()){
                                if(pass.getSuccessful() ==null || !pass.getSuccessful()) value ++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba podań niecelnych na mecz");
                }
            break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass: p.getPassingList()){
                                if(pass.getSuccessful()!= null && pass.getSuccessful()) value ++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma podań celnych z wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Passing pass: p.getPassingList()){
                                if(pass.getSuccessful()!= null && pass.getSuccessful()) value ++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba podań celnych na mecz");
                }
            break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getPassingList().size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Suma podań z wszystkich meczy");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getPassingList().size();
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba podań na mecz");
                }
            break;
        }
        
        
        return s;
    }

    private XYChart.Series doSeriesFaulInAllMatchesByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                        Player p = getSelectedPlayers().get(i);
                        int value = p.getFaulList1().size();
                        XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                        s.getData().add(d);
                    }
                    s.setName("Suma popełnionych fauli we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                        Player p = getSelectedPlayers().get(i);
                        int value = p.getFaulList1().size();
                        XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                        s.getData().add(d);
                    }
                    s.setName("Średnia liczba popełnionych fauli na mecz");
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                        Player p = getSelectedPlayers().get(i);
                        int value = p.getFaulList().size();
                        XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                        s.getData().add(d);
                    }
                    s.setName("Suma popełnionych otrzymanych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                        Player p = getSelectedPlayers().get(i);
                        int value = p.getFaulList().size();
                        XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                        s.getData().add(d);
                    }
                    s.setName("Średnia liczba otrzymanych fauli na mecz");
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getFaulList().size() + p.getFaulList1().size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma fauli we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = p.getFaulList().size() + p.getFaulList1().size();
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba fauli na mecz");
                }
            break;
                
        }        
        return s;   
    }

    private XYChart.Series doSeriesFaulInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch(SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getFaulsCommitedPlayer(p).size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma fauli popełnionych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba fauli popełnionych w meczu: " + this.selectedGame.getOponent());
                }
                
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getFaulsPlayer(p).size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma fauli otrzymanych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba fauli otrzymanych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getFaulsPlayer(p).size()+ this.selectedGame.getFaulsCommitedPlayer(p).size();
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma fauli w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba fauli w meczu: " + this.selectedGame.getOponent());
                }
                break;
                
        }
                
        return s;
    }

    private XYChart.Series doSeriesFreeKickInAllMatchesByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if( shot.getFreekick() && (shot.getSuccess()==null || shot.getSuccess().contains("NIECELNY"))){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów wolnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if( shot.getFreekick() && (shot.getSuccess()==null || shot.getSuccess().contains("NIECELNY"))){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba niecelnych rzutów wolnych na mecz");
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if(shot.getFreekick() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów wolnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if(shot.getFreekick() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba celnych rzutów wolnych na mecz");
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if( shot.getFreekick()){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów wolnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            List<Shot> shotL = p.getShotList();
                            int value = 0;
                            for( Shot shot : shotL){
                                if( shot.getFreekick()){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba rzutów wolnych na mecz");
                }
                break;
        }
        return s;   
    }

    private XYChart.Series doSeriesFreeKickInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getFreekick() &&( shot.getSuccess()==null || shot.getSuccess().contains("NIECELNY"))) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów wolnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba niecelnych rzutów wolnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getFreekick() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów wolnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba celnych rzutów wolnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getNumberOfFreekicksPlayer(p);
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów wolnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba rzutów wolnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
        }         
        return s;
    }

    private XYChart.Series doSeriesCornerKickInAllMatchesByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner() &&(shot.getSuccess()==null || shot.getSuccess().contains("NIECELNY"))){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów rożnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner() &&(shot.getSuccess()==null || shot.getSuccess().contains("NIECELNY"))){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba niecelnych rzutów rożnych na mecz");
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów rożnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba celnych rzutów rożnych na mecz");
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner()){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów rożnych we wszystkich meczach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : p.getShotList()){
                                if( shot.getCorner()){
                                    value++;
                                }
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba rzutów rożnych na mecz");
                }
                break;
        }
                
        
        return s;
    }

    private XYChart.Series doSeriesCornerKickInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getCorner() &&(shot.getSuccess()== null ||  shot.getSuccess().contains("NIECELNY"))) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów rożnych w meczu " + this.selectedGame.getOponent());
                } else {
                   //never occur... we cannot count average for single match
                    s.setName("Średnia liczba niecelnych rzutów rożnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getCorner() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów rożnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba celnych rzutów rożnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getNumberOfCornersPlayer(p);
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów rożnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba rzutów rożnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
        }
                
        return s;
    }

    private XYChart.Series doSeriesPenaltyKickInAllMatchesByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && (shot.getSuccess()== null ||  shot.getSuccess().contains("NIECELNY"))) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów karnych we wszystkich mechach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && (shot.getSuccess()== null ||  shot.getSuccess().contains("NIECELNY"))) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba niecelnych rzutów karnych na mecz");
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów karnych we wszystkich mechach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba rzutów karnych na mecz");
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && shot.getSuccess()!="NIECELNY") value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów karnych we wszystkich mechach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if( shot.getPenalty() && shot.getSuccess()!="NIECELNY") value++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba celnych rzutów karnych na mecz");
                }
                break;
        }
        return s;   
    }

    private XYChart.Series doSeriesPenaltyKickInSelectedMatchByPlayers(boolean average, int SU) {
        XYChart.Series s = new XYChart.Series();
        switch (SU){
            case -1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getPenalty() && (shot.getSuccess()== null ||  shot.getSuccess().contains("NIECELNY"))) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma niecelnych rzutów karnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba niecelnych rzutów karnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 1:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = 0;
                            for( Shot shot : this.selectedGame.getShotsPlayer(p)){
                                if(shot.getPenalty() && shot.getSuccess()!=null && !shot.getSuccess().contains("NIE")) value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma celnych rzutów karnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba celnych rzutów karnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
            case 0:
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value = this.selectedGame.getNumberOfPenaltiesPlayer(p);
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma rzutów karnych w meczu " + this.selectedGame.getOponent());
                } else {
                    //never occur... we cannot count average for single match
                    s.setName("Średnia liczba rzutów karnych w meczu: " + this.selectedGame.getOponent());
                }
                break;
        }
        
        return s;
    }

    private XYChart.Series doSeriesGoalInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
                if(!average){
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if(shot.getSuccess()!=null && shot.getSuccess()=="GOL") value++;
                            }
                            XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Suma goli we wszystkich mechach");
                } else {
                    for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                            Player p = getSelectedPlayers().get(i);
                            int value =0;
                            List<Shot> shotL= p.getShotList();
                            for(Shot shot : shotL){
                                if(shot.getSuccess()!=null && shot.getSuccess()=="GOL") value++;
                            }
                            XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                            s.getData().add(d);
                    }
                    s.setName("Średnia liczba goli na mecz");
                }
        
        return s;  
    }

    private XYChart.Series doSeriesGoalInSelectedMatchByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();

        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getGoalsPlayer(p).size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Suma goli w meczu " + this.selectedGame.getOponent());
        } else {
            //never occur... we cannot count average for single match
            s.setName("Średnia liczba goli w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

    private XYChart.Series doSeriesDefenseInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = p.getDefenseList().size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Suma obronionych strzałów we wszystkich mechach");
        } else {
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = p.getDefenseList().size();
                    XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Średnia liczba obronionych strzałów na mecz");
        }
        
        return s;  
    }

    private XYChart.Series doSeriesDefenseInSelectedMatchByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getDefensePlayer(p).size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Suma obronionych strzałów w meczu " + this.selectedGame.getOponent());
        } else {
            //never occur... we cannot count average for single match
            s.setName("Średnia liczba obronionych strzałów w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

    private XYChart.Series doSeriesYellowCardsInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();

        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value =0;
                    List<Card> cardL= p.getCardList();
                    for(Card card : cardL){
                        if(card.getKind()!= null && card.getKind()=="ŻÓŁTA") value++;
                    }
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Suma żółtych kartek we wszystkich mechach");
        } else {
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value =0;
                    List<Card> cardL= p.getCardList();
                    for(Card card : cardL){
                        if(card.getKind()!= null && card.getKind()=="ŻÓŁTA") value++;
                    }
                    XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Średnia liczba żółtych kartek na mecz");
        }
        
        return s;  
    }

    private XYChart.Series doSeriesYellowCardsInSelectedMatchByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getYellowCardsPlayer(p).size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Liczba żółtych kartek w meczu " + this.selectedGame.getOponent());
        } else {
            //never occur... we cannot count average for single match
            s.setName("Średnia liczba żółtych kartek w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

    private XYChart.Series doSeriesRedCardsInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();

        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value =0;
                    List<Card> cardL= p.getCardList();
                    for(Card card : cardL){
                        if( card.getKind()!= null && card.getKind()=="CZERWONA") value++;
                    }
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Suma czerwonych kartek we wszystkich mechach");
        } else {
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value =0;
                    List<Card> cardL= p.getCardList();
                    for(Card card : cardL){
                        if( card.getKind()!= null && card.getKind()=="CZERWONA") value++;
                    }
                    XYChart.Data d = new XYChart.Data(value/(double)p.getParticipatedList().size(),p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Średnia liczba czerwonych kartek na mecz");
        }
        
        return s;
    }

    private XYChart.Series doSeriesRedCardsInSelectedMatchByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();

        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getRedCardsPlayer(p).size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname());
                    s.getData().add(d);
            }
            s.setName("Liczba czerwonych kartek w meczu " + this.selectedGame.getOponent());
        } else {
            //never occur... we cannot count average for single match
            s.setName("Średnia liczba czerwonych kartek w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

}
