/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(catalog = "fsmdb", name = "User",schema = "")
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
    @Column(nullable = false)
    private Integer id;
    @Column(length = 50)
    private String login;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @OneToMany(mappedBy = "ownerId")
    private List<Shot> shotList;
    @OneToMany(mappedBy = "ownerId")
    private List<Injury> injuryList;
    @OneToMany(mappedBy = "ownerId")
    private List<Takeover> takeoverList;
    @OneToMany(mappedBy = "ownerId")
    private List<Corner> cornerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private List<Player> playerList;
    @OneToMany(mappedBy = "ownerId")
    private List<Game> gameList;
    @OneToMany(mappedBy = "ownerId")
    private List<Played> playedList;
    @OneToMany(mappedBy = "ownerId")
    private List<Team> teamList;
    @OneToMany(mappedBy = "ownerId")
    private List<Penalty> penaltyList;
    @OneToMany(mappedBy = "ownerId")
    private List<Faul> faulList;
    @OneToMany(mappedBy = "ownerId")
    private List<Defense> defenseList;
    @OneToMany(mappedBy = "ownerId")
    private List<Participated> participatedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Access> accessList;
    @OneToMany(mappedBy = "ownerId")
    private List<Swap> swapList;
    @OneToMany(mappedBy = "ownerId")
    private List<Passing> passingList;
    @OneToMany(mappedBy = "ownerId")
    private List<Assist> assistList;
    @OneToMany(mappedBy = "ownerId")
    private List<Card> cardList;

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

    @XmlTransient
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @XmlTransient
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @XmlTransient
    public List<Played> getPlayedList() {
        return playedList;
    }

    public void setPlayedList(List<Played> playedList) {
        this.playedList = playedList;
    }

    @XmlTransient
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
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
    public List<Access> getAccessList() {
        return accessList;
    }

    public void setAccessList(List<Access> accessList) {
        this.accessList = accessList;
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
        return "fsc.model.User[ id=" + id + " ]";
    }
    
}
