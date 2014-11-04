/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import fsc.model.actions.Corner;
import fsc.model.actions.Takeover;
import fsc.model.actions.Shot;
import fsc.model.actions.Card;
import fsc.model.actions.Assist;
import fsc.model.actions.Swap;
import fsc.model.actions.Injury;
import fsc.model.actions.Faul;
import fsc.model.actions.Passing;
import fsc.model.actions.Defense;
import fsc.model.actions.Penalty;
import java.io.Serializable;
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
@Table(catalog = "fsmdb",name = "Game", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.id = :id"),
    @NamedQuery(name = "Game.findByDate", query = "SELECT g FROM Game g WHERE g.date = :date"),
    @NamedQuery(name = "Game.findByPlace", query = "SELECT g FROM Game g WHERE g.place = :place"),
    @NamedQuery(name = "Game.findByScore", query = "SELECT g FROM Game g WHERE g.score = :score"),
    @NamedQuery(name = "Game.findByLostGoals", query = "SELECT g FROM Game g WHERE g.lostGoals = :lostGoals"),
    @NamedQuery(name = "Game.findByScoredGoals", query = "SELECT g FROM Game g WHERE g.scoredGoals = :scoredGoals"),
    @NamedQuery(name = "Game.findByOponent", query = "SELECT g FROM Game g WHERE g.oponent = :oponent")})
public class Game implements Serializable {
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
    @Column(length = 10)
    private String score;
    @Column(name = "lost_goals")
    private Integer lostGoals;
    @Column(name = "scored_goals")
    private Integer scoredGoals;
    @Column(length = 150)
    private String oponent;
    @OneToMany(mappedBy = "gameId")
    private List<Shot> shotList;
    @OneToMany(mappedBy = "gameId")
    private List<Injury> injuryList;
    @OneToMany(mappedBy = "gameId")
    private List<Takeover> takeoverList;
    @OneToMany(mappedBy = "gameId")
    private List<Corner> cornerList;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @OneToMany(mappedBy = "gameId")
    private List<Played> playedList;
    @OneToMany(mappedBy = "gameId")
    private List<Penalty> penaltyList;
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
    private List<Assist> assistList;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getLostGoals() {
        return lostGoals;
    }

    public void setLostGoals(Integer lostGoals) {
        this.lostGoals = lostGoals;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
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
    public List<Shot> getShotList() {
        return shotList;
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
    }

    @XmlTransient
    public List<Injury> getInjuryList() {
        return injuryList;
    }

    public void setInjuryList(List<Injury> injuryList) {
        this.injuryList = injuryList;
    }

    @XmlTransient
    public List<Takeover> getTakeoverList() {
        return takeoverList;
    }

    public void setTakeoverList(List<Takeover> takeoverList) {
        this.takeoverList = takeoverList;
    }

    @XmlTransient
    public List<Corner> getCornerList() {
        return cornerList;
    }

    public void setCornerList(List<Corner> cornerList) {
        this.cornerList = cornerList;
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

    @XmlTransient
    public List<Penalty> getPenaltyList() {
        return penaltyList;
    }

    public void setPenaltyList(List<Penalty> penaltyList) {
        this.penaltyList = penaltyList;
    }

    @XmlTransient
    public List<Faul> getFaulList() {
        return faulList;
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

    @XmlTransient
    public List<Passing> getPassingList() {
        return passingList;
    }

    public void setPassingList(List<Passing> passingList) {
        this.passingList = passingList;
    }

    @XmlTransient
    public List<Assist> getAssistList() {
        return assistList;
    }

    public void setAssistList(List<Assist> assistList) {
        this.assistList = assistList;
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
        return "fsc.model.Game[ id=" + id + " ]";
    }
    
}
