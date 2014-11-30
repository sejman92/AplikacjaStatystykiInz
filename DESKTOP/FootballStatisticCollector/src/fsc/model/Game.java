/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import fsc.model.actions.Takeover;
import fsc.model.actions.Card;
import fsc.model.actions.Passing;
import fsc.model.actions.Injury;
import fsc.model.actions.Shot;
import fsc.model.actions.Swap;
import fsc.model.actions.Faul;
import fsc.model.actions.Defense;
import fsc.model.enums.ColorOfCard;
import fsc.model.enums.SuccessOfShot;
import fsc.model.interfaces.IEntityElement;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb", name = "Game",schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.id = :id"),
    @NamedQuery(name = "Game.findByDate", query = "SELECT g FROM Game g WHERE g.date = :date"),
    @NamedQuery(name = "Game.findByPlace", query = "SELECT g FROM Game g WHERE g.place = :place"),
    @NamedQuery(name = "Game.findByLostGoals", query = "SELECT g FROM Game g WHERE g.lostGoals = :lostGoals"),
    @NamedQuery(name = "Game.findByScoredGoals", query = "SELECT g FROM Game g WHERE g.scoredGoals = :scoredGoals"),
    @NamedQuery(name = "Game.findByOponent", query = "SELECT g FROM Game g WHERE g.oponent = :oponent")})
public class Game implements Serializable, IEntityElement {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(length = 150)
    private String place;
    @Column(name = "lost_goals")
    private Integer lostGoals;
    @Column(name = "scored_goals")
    private Integer scoredGoals;
    @Column(length = 150)
    private String oponent;
    @OneToMany(mappedBy = "gameId")
    private List<Injury> injuryList;
    @OneToMany(mappedBy = "gameId")
    private List<Shot> shotList;
    @OneToMany(mappedBy = "gameId")
    private List<Takeover> takeoverList;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @OneToMany(mappedBy = "gameId")
    private List<Played> playedList;
    @OneToMany(mappedBy = "gameId")
    private List<Faul> faulList;
    @OneToMany(mappedBy = "gameId")
    private List<Defense> defenseList;
    @OneToMany(mappedBy = "gameId")
    private List<Participated> participatedList;
    @OneToMany(mappedBy = "gameId")
    private List<Swap> swapList;
    @OneToMany(mappedBy = "gameId")
    private List<Passing> passingList;
    @OneToMany(mappedBy = "gameId")
    private List<Card> cardList;

    public Game() {
    }

    public Game(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getLostGoals() {
        return lostGoals;
    }

    public void setLostGoals(Integer lostGoals) {
        this.lostGoals = lostGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public String getOponent() {
        return oponent;
    }

    public void setOponent(String oponent) {
        this.oponent = oponent;
    }

    @XmlTransient
    public List<Injury> getInjuryList() {
        return injuryList;
    }

    public void setInjuryList(List<Injury> injuryList) {
        this.injuryList = injuryList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
    }

    public void setTakeoverList(List<Takeover> takeoverList) {
        this.takeoverList = takeoverList;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @XmlTransient
    public List<Played> getPlayedList() {
        return playedList;
    }

    public void setPlayedList(List<Played> playedList) {
        this.playedList = playedList;
    }

    public void setFaulList(List<Faul> faulList) {
        this.faulList = faulList;
    }

    @XmlTransient
    public List<Defense> getDefenseList() {
        return defenseList;
    }

    public void setDefenseList(List<Defense> defenseList) {
        this.defenseList = defenseList;
    }

    @XmlTransient
    public List<Participated> getParticipatedList() {
        return participatedList;
    }

    public void setParticipatedList(List<Participated> participatedList) {
        this.participatedList = participatedList;
    }

    @XmlTransient
    public List<Swap> getSwapList() {
        return swapList;
    }

    public void setSwapList(List<Swap> swapList) {
        this.swapList = swapList;
    }
    
    public void setPassingList(List<Passing> passingList) {
        this.passingList = passingList;
    }

    @XmlTransient
    public List<Card> getCardList() {
        return cardList;
    }
    
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        if( this.getDate()!=null)
            return this.getOponent()+ " " +  df.format(this.getDate());
        else return this.getOponent();
    }
    
    //Team analize
    
    public Integer getScoredGoals() {
        return scoredGoals;
    }
    
    public List<Shot> getGoalList() {
        List<Shot>goals = new ArrayList();
        for(Shot s: getShotList()){
            if(s.getSuccess() != null && s.getSuccess().equals(SuccessOfShot.GOL.toString()))
                goals.add(s);
        }
        return goals;
    }
        
    @XmlTransient
    public List<Shot> getShotList() {
        return shotList;
    }
   
    public List<Shot> getAccurateShotList() {
        List<Shot>accurateShots = getGoalList();
        
        for(Shot s: getShotList()){
            if(s.getSuccess()!=null && s.getSuccess().equals(SuccessOfShot.CELNY))
                accurateShots.add(s);
        }
        return accurateShots;
    }
    
    @XmlTransient
    public List<Passing> getPassingList() {
        return passingList;
    }
    
    public List<Passing> getAccuratePassesList(){
        List<Passing>accuratePasses = new ArrayList();
        
        for(Passing p: getPassingList()){
            if(p.getSuccessful())
                accuratePasses.add(p);
        }
        return accuratePasses;
    }

    public double getAccuracyPasses() {
        if(getPassingList().isEmpty())
            return 0;
        return (100*getAccuratePassesList().size())/getPassingList().size();
    }
    
    public List<Card> getYellowCards() {
        List<Card>yellowCards = new ArrayList();
        
        for(Card c: getCardList()){
            if(c.getKind() != null && c.getKind().equals(ColorOfCard.ŻÓŁTA))
                yellowCards.add(c);
        }
        return yellowCards;
    }
    
    public List<Card> getRedCards() {
        List<Card>redCards = new ArrayList();
        
        for(Card c: getCardList()){
            if(c.getKind() != null && c.getKind().equals(ColorOfCard.CZERWONA))
                redCards.add(c);
        }
        return redCards;
    }
    
    @XmlTransient
    public List<Faul> getFaulList() {
        return faulList;
    }
    
    @XmlTransient
    public List<Takeover> getTakeoverList() {
        return takeoverList;
    }
    
    public int getNumberOfFreeKicks(){
        int numberOfFreeKicks = 0;
        
        for(Shot s: getShotList()){
            if(s.getFreekick())
                numberOfFreeKicks++;
        }
        
        for(Passing p: getPassingList()){
            if(p.getFreekick())
                numberOfFreeKicks++;
        }
        
        return numberOfFreeKicks;
    }
    
    public int getNumberOfCorners(){
        int numberOfCorners = 0;
        
        for(Shot s: getShotList()){
            if(s.getCorner())
                numberOfCorners++;
        }
        
        for(Passing p: getPassingList()){
            if(p.getCorner())
                numberOfCorners++;
        }
        
        return numberOfCorners;
    }
    
    public int getNumberOfPenalties(){
        int numberOfPenalties = 0;
        
        for(Shot s: getShotList()){
            if(s.getPenalty())
                numberOfPenalties++;
        }
        
        return numberOfPenalties;
    }
      
    //Player analize
     
    public List<Shot> getGoalsPlayer(Player player){
        List<Shot>goals = new ArrayList();
        
        for(Shot g: getGoalList()){
            if(g.getPlayerId().equals(player))
                goals.add(g);
        }
        return goals;
    }
    
    public List<Shot> getShotsPlayer(Player player){
        List<Shot>shots = new ArrayList();
        
        for(Shot s: getShotList()){
            if(s.getPlayerId().equals(player))
                shots.add(s);
        }
        return shots;
    }
    
    public List<Shot> getAccurateShotsPlayer(Player player){
        List<Shot>accurateShots = new ArrayList();
        
        for(Shot s: getAccurateShotList()){
            if(s.getPlayerId().equals(player))
                accurateShots.add(s);
        }
        return accurateShots;
    }
    
    public List<Passing> getPassesPlayer(Player player){
        List<Passing>passes = new ArrayList();
        
        for(Passing p: getPassingList()){
            if(p.getPlayerPassingId().equals(player))
                passes.add(p);
        }
        return passes;
    }
    
    public List<Passing> getAccuratePassesPlayer(Player player){
        List<Passing>accuratePasses = new ArrayList();
        
        for(Passing p: getAccuratePassesList()){
            if(p.getPlayerPassingId().equals(player))
                accuratePasses.add(p);
        }
        return accuratePasses;
    }
    
    public double getAccuracyPassesPlayer(Player player){
        if(getPassesPlayer(player).isEmpty())
            return 0;
        return (100*getAccuratePassesPlayer(player).size())/getPassesPlayer(player).size();
    }
    
    public List<Card> getYellowCardsPlayer(Player player){
        List<Card>yellowCards = new ArrayList();
        
        for(Card c: getYellowCards()){
            if(c.getPlayerId().equals(player))
                yellowCards.add(c);
        }
        return yellowCards;
    }
 
    public List<Card> getRedCardsPlayer(Player player){
        List<Card>redCards = new ArrayList();
        
        for(Card c: getRedCards()){
            if(c.getPlayerId().equals(player))
                redCards.add(c);
        }
        return redCards;
    }
  
    public List<Faul> getFaulsPlayer(Player player){
        List<Faul>fauls = new ArrayList();
        
        for(Faul f: getFaulList()){
            if(f.getPlayerVictimId()!= null && f.getPlayerVictimId().equals(player))
                fauls.add(f);
        }
        return fauls;
    }
    
    public List<Takeover> getTakeoversPlayer(Player player){
        List<Takeover>takeovers = new ArrayList();
        
        for(Takeover t: getTakeoverList()){
            if(t.getPlayerId().equals(player))
                takeovers.add(t);
        }
        return takeovers;
    }
    
    public int getNumberOfFreekicksPlayer(Player player){
        int numberOfFreeKicks = 0;
        
        for(Shot s: getShotList()){
            if(s.getCorner() && s.getPlayerId().equals(player))
                numberOfFreeKicks++;
        }
        
        for(Passing p: getPassingList()){
            if(p.getCorner() && p.getPlayerPassingId().equals(player))
                numberOfFreeKicks++;
        }
        
        return numberOfFreeKicks;
    }
    
    public int getNumberOfCornersPlayer(Player player){
        int numberOfCorners = 0;
        
        for(Shot s: getShotList()){
            if(s.getCorner() && s.getPlayerId().equals(player))
                numberOfCorners++;
        }
        
        for(Passing p: getPassingList()){
            if(p.getCorner() && p.getPlayerPassingId().equals(player))
                numberOfCorners++;
        }
        
        return numberOfCorners;
    }
    
    public int getNumberOfPenaltiesPlayer(Player player){
        int numberOfPenalties = 0;
        
        for(Shot s: getShotList()){
            if(s.getCorner() && s.getPlayerId().equals(player))
                numberOfPenalties++;
        }
        
        return numberOfPenalties;
    }
        
    public int getNumberOfDefenses(){
        return this.defenseList.size();
    }
    
    public int getNumberOfDefensesPlayer(Player player){
        int numberOfDefenses = 0;
        for (Defense d: defenseList){
            if(d.getPlayerId().getId() == player.getId())
                numberOfDefenses++;
        }
        return numberOfDefenses;
    }
}
