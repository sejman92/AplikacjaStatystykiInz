/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.Player;
import fsc.model.enums.CompareCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
    /*
    * default constructor
    */
    public AnalizeController(BarChart bc){
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
        s.setName(name);
        for(int i = 0; i<value.size(); i++){
            XYChart.Data d = new XYChart.Data((Number)value.get(i), playerName.get(i));
            s.getData().add(d);
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

}
