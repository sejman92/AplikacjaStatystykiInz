/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String login;
    private String password;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Shot> shotCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Injury> injuryCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Takeover> takeoverCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Corner> cornerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Player> playerCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Game> gameCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Played> playedCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Team> teamCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Penalty> penaltyCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Faul> faulCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Defense> defenseCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Participated> participatedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Access> accessCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Swap> swapCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Passing> passingCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Assist> assistCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<Card> cardCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @XmlTransient
    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerCollection(Collection<Player> playerCollection) {
        this.playerCollection = playerCollection;
    }

    @XmlTransient
    public Collection<Game> getGameCollection() {
        return gameCollection;
    }

    public void setGameCollection(Collection<Game> gameCollection) {
        this.gameCollection = gameCollection;
    }

    @XmlTransient
    public Collection<Played> getPlayedCollection() {
        return playedCollection;
    }

    public void setPlayedCollection(Collection<Played> playedCollection) {
        this.playedCollection = playedCollection;
    }

    @XmlTransient
    public Collection<Team> getTeamCollection() {
        return teamCollection;
    }

    public void setTeamCollection(Collection<Team> teamCollection) {
        this.teamCollection = teamCollection;
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
    public Collection<Access> getAccessCollection() {
        return accessCollection;
    }

    public void setAccessCollection(Collection<Access> accessCollection) {
        this.accessCollection = accessCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.controller.User[ id=" + id + " ]";
    }
    
}
