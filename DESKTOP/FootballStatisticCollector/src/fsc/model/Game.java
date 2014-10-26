/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(catalog = "fsmdb", schema = "")
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
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String place;
    private String score;
    @Column(name = "lost_goals")
    private Integer lostGoals;
    @Column(name = "scored_goals")
    private Integer scoredGoals;
    private String oponent;
    @OneToMany(mappedBy = "gameId")
    private Collection<Shot> shotCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Injury> injuryCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Takeover> takeoverCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Corner> cornerCollection;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @OneToMany(mappedBy = "gameId")
    private Collection<Played> playedCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Penalty> penaltyCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Faul> faulCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Defense> defenseCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Participated> participatedCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Swap> swapCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Passing> passingCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Assist> assistCollection;
    @OneToMany(mappedBy = "gameId")
    private Collection<Card> cardCollection;

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
    public Collection<Shot> getShotCollection() {
        return shotCollection;
    }

    public void setShotCollection(Collection<Shot> shotCollection) {
        this.shotCollection = shotCollection;
    }

    @XmlTransient
    public Collection<Injury> getInjuryCollection() {
        return injuryCollection;
    }

    public void setInjuryCollection(Collection<Injury> injuryCollection) {
        this.injuryCollection = injuryCollection;
    }

    @XmlTransient
    public Collection<Takeover> getTakeoverCollection() {
        return takeoverCollection;
    }

    public void setTakeoverCollection(Collection<Takeover> takeoverCollection) {
        this.takeoverCollection = takeoverCollection;
    }

    @XmlTransient
    public Collection<Corner> getCornerCollection() {
        return cornerCollection;
    }

    public void setCornerCollection(Collection<Corner> cornerCollection) {
        this.cornerCollection = cornerCollection;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @XmlTransient
    public Collection<Played> getPlayedCollection() {
        return playedCollection;
    }

    public void setPlayedCollection(Collection<Played> playedCollection) {
        this.playedCollection = playedCollection;
    }

    @XmlTransient
    public Collection<Penalty> getPenaltyCollection() {
        return penaltyCollection;
    }

    public void setPenaltyCollection(Collection<Penalty> penaltyCollection) {
        this.penaltyCollection = penaltyCollection;
    }

    @XmlTransient
    public Collection<Faul> getFaulCollection() {
        return faulCollection;
    }

    public void setFaulCollection(Collection<Faul> faulCollection) {
        this.faulCollection = faulCollection;
    }

    @XmlTransient
    public Collection<Defense> getDefenseCollection() {
        return defenseCollection;
    }

    public void setDefenseCollection(Collection<Defense> defenseCollection) {
        this.defenseCollection = defenseCollection;
    }

    @XmlTransient
    public Collection<Participated> getParticipatedCollection() {
        return participatedCollection;
    }

    public void setParticipatedCollection(Collection<Participated> participatedCollection) {
        this.participatedCollection = participatedCollection;
    }

    @XmlTransient
    public Collection<Swap> getSwapCollection() {
        return swapCollection;
    }

    public void setSwapCollection(Collection<Swap> swapCollection) {
        this.swapCollection = swapCollection;
    }

    @XmlTransient
    public Collection<Passing> getPassingCollection() {
        return passingCollection;
    }

    public void setPassingCollection(Collection<Passing> passingCollection) {
        this.passingCollection = passingCollection;
    }

    @XmlTransient
    public Collection<Assist> getAssistCollection() {
        return assistCollection;
    }

    public void setAssistCollection(Collection<Assist> assistCollection) {
        this.assistCollection = assistCollection;
    }

    @XmlTransient
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
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
        return "fsc.controller.Game[ id=" + id + " ]";
    }
    
}
