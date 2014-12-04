/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.Team;
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

        List<String> plName = new ArrayList();
        List<XYChart.Series> series = new ArrayList();
        for( Player p: this.getSelectedPlayers()){
            plName.add(p.getName() + " " + p.getSurname());
        }
        if (this.isShotCheckBox()){       
            XYChart.Series s = new XYChart.Series();
            if (this.isSumCheckBox()){
                if(this.isInAllMatchesCheckBox()) s = doSeriesShotInAllMatchesByPlayers(!sumCheckBox);
                if(this.isInSelectedMatchCheckBox()) s = doSeriesShotInSelectedMatchByPlayers(!sumCheckBox);
                
            } else {
                if(this.isInAllMatchesCheckBox()) s = doSeriesShotInAllMatchesByPlayers(!sumCheckBox);
                if(this.isInSelectedMatchCheckBox()) s = doSeriesShotInSelectedMatchByPlayers(!sumCheckBox);
            }
            series.add(s);
        }
        if(this.isPassCheckBox()){
             XYChart.Series s = new XYChart.Series();
             if( this.isSumCheckBox()){
                 if(this.isInAllMatchesCheckBox()) s = doSeriesPassInAllMatchesByPlayers(!sumCheckBox);
                 if(this.isInSelectedMatchCheckBox()) s = doSeriesPassInSelectedMatchByPlayers(!sumCheckBox);
             } else {
                 if(this.isInAllMatchesCheckBox()) s = doSeriesPassInAllMatchesByPlayers(!sumCheckBox);
                 if(this.isInSelectedMatchCheckBox()) s = doSeriesPassInSelectedMatchByPlayers(!sumCheckBox);
             }
             series.add(s);
             
        }
        //todo others
        
        StackPane layer = new StackPane();
        
       ValueAxis x = new NumberAxis();
        
        CategoryAxis y = new CategoryAxis();
        BarChart<Integer, String> chart = new BarChart<Integer, String>(x,y);
        chart.setTitle("Wykres");
        
        for(XYChart.Series s: series){
            chart.getData().add(s);
        }

        layer.getChildren().add(chart);
        Scene scene = new Scene (layer, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    void drawChartForTeam() {
         
        /*List<Integer> value = new ArrayList();
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
        bc.getData().add(series1);
        secondLay.getChildren().add(bc);     
        Scene sScene = new Scene(secondLay, 400,300);
        Stage sSt = new Stage();
        sSt.setScene(sScene);
        sSt.show();*/
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

    private XYChart.Series doSeriesShotInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = p.getShotList().size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                    s.getData().add(d);
            }
            s.setName("Suma strzałów z wszystkich meczy");
        } else {
            //todo liczymy średni strzał
            s.setName("Średnia liczba strzałów na mecz");
        }
        
        return s;
    }

    private XYChart.Series doSeriesShotInSelectedMatchByPlayers(boolean average) {
        
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getShotsPlayer(p).size();
                    
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                    s.getData().add(d);
            }
            s.setName("Suma strzałów w meczu: " + this.selectedGame.getOponent());
        } else {
            //todo liczymy średnią
            s.setName("Średnia strzałów w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

    private XYChart.Series doSeriesPassInSelectedMatchByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = this.selectedGame.getPassesPlayer(p).size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                    s.getData().add(d);
            }
            s.setName("Suma podań w meczu: " + this.selectedGame.getOponent());
        } else {
            //todo liczymy średnią
            s.setName("Średnia podań w meczu: " + this.selectedGame.getOponent());
        }
        return s;
    }

    private XYChart.Series doSeriesPassInAllMatchesByPlayers(boolean average) {
        XYChart.Series s = new XYChart.Series();
        if(!average){
            for(int i = 0; i<this.getSelectedPlayers().size(); i++){
                    Player p = getSelectedPlayers().get(i);
                    int value = p.getPassingList().size();
                    XYChart.Data d = new XYChart.Data(value,p.getName()+" "+p.getSurname()/*+ " ("+value+")"*/);
                    s.getData().add(d);
            }
            s.setName("Suma podań z wszystkich meczy");
        } else {
            //todo liczymy średni strzał
            s.setName("Średnia liczba podań na mecz");
        }
        
        return s;
    }

    

    

}
