/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id"),
    @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name"),
    @NamedQuery(name = "Player.findBySurname", query = "SELECT p FROM Player p WHERE p.surname = :surname"),
    @NamedQuery(name = "Player.findByNo", query = "SELECT p FROM Player p WHERE p.no = :no"),
    @NamedQuery(name = "Player.findByRole", query = "SELECT p FROM Player p WHERE p.role = :role")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String name;
    private String surname;
    private Integer no;
    private String role;
    @OneToMany(mappedBy = "playerId")
    private Collection<Shot> shotCollection;
    @OneToMany(mappedBy = "playerId")
    private Collection<Injury> injuryCollection;
    @OneToMany(mappedBy = "playerId")
    private Collection<Takeover> takeoverCollection;
    @OneToMany(mappedBy = "playerId")
    private Collection<Corner> cornerCollection;
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Team teamId;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(mappedBy = "playerId")
    private Collection<Penalty> penaltyCollection;
    @OneToMany(mappedBy = "playerOfenderId")
    private Collection<Faul> faulCollection;
    @OneToMany(mappedBy = "playerVictimId")
    private Collection<Faul> faulCollection1;
    @OneToMany(mappedBy = "playerId")
    private Collection<Defense> defenseCollection;
    @OneToMany(mappedBy = "playerId")
    private Collection<Participated> participatedCollection;
    @OneToMany(mappedBy = "playerOutId")
    private Collection<Swap> swapCollection;
    @OneToMany(mappedBy = "playerInId")
    private Collection<Swap> swapCollection1;
    @OneToMany(mappedBy = "playerGettingId")
    private Collection<Passing> passingCollection;
    @OneToMany(mappedBy = "playerPassingId")
    private Collection<Passing> passingCollection1;
    @OneToMany(mappedBy = "playerId")
    private Collection<Assist> assistCollection;
    @OneToMany(mappedBy = "playerId")
    private Collection<Card> cardCollection;
    private static int lastId = 0;
    
    public Player() {
        id = ++lastId;
    }
    
    public Player(String name, String surname, Integer no, String role)
    {
        id = ++lastId;
        this.name = name;
        this.surname = surname;
        this.no = no;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
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
    public Collection<Faul> getFaulCollection1() {
        return faulCollection1;
    }

    public void setFaulCollection1(Collection<Faul> faulCollection1) {
        this.faulCollection1 = faulCollection1;
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
    public Collection<Swap> getSwapCollection1() {
        return swapCollection1;
    }

    public void setSwapCollection1(Collection<Swap> swapCollection1) {
        this.swapCollection1 = swapCollection1;
    }

    @XmlTransient
    public Collection<Passing> getPassingCollection() {
        return passingCollection;
    }

    public void setPassingCollection(Collection<Passing> passingCollection) {
        this.passingCollection = passingCollection;
    }

    @XmlTransient
    public Collection<Passing> getPassingCollection1() {
        return passingCollection1;
    }

    public void setPassingCollection1(Collection<Passing> passingCollection1) {
        this.passingCollection1 = passingCollection1;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname;
    }
    
}
