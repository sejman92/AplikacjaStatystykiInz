/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb", name = "Player",schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findById", query = "SELECT p FROM Player p WHERE p.id = :id"),
    @NamedQuery(name = "Player.findByName", query = "SELECT p FROM Player p WHERE p.name = :name"),
    @NamedQuery(name = "Player.findBySurname", query = "SELECT p FROM Player p WHERE p.surname = :surname"),
    @NamedQuery(name = "Player.findByNo", query = "SELECT p FROM Player p WHERE p.no = :no"),
    @NamedQuery(name = "Player.findByRole", query = "SELECT p FROM Player p WHERE p.role = :role"),
    @NamedQuery(name = "Player.findByPreferedLeg", query = "SELECT p FROM Player p WHERE p.preferedLeg = :preferedLeg"),
    @NamedQuery(name = "Player.findByTeamId", query = "SELECT p from Player p WHERE p.teamId = :team_id")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 20)
    private String name;
    @Column(length = 30)
    private String surname;
    private Integer no;
    @Column(length = 20)
    private String role;
    @Column(name = "prefered_leg", length = 20)
    private String preferedLeg;
    @OneToMany(mappedBy = "playerId")
    private List<Shot> shotList;
    @OneToMany(mappedBy = "playerId")
    private List<Injury> injuryList;
    @OneToMany(mappedBy = "playerId")
    private List<Takeover> takeoverList;
    @OneToMany(mappedBy = "playerId")
    private List<Corner> cornerList;
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Team teamId;
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(mappedBy = "playerId")
    private List<Penalty> penaltyList;
    @OneToMany(mappedBy = "playerOfenderId")
    private List<Faul> faulList;
    @OneToMany(mappedBy = "playerVictimId")
    private List<Faul> faulList1;
    @OneToMany(mappedBy = "playerId")
    private List<Defense> defenseList;
    @OneToMany(mappedBy = "playerId")
    private List<Participated> participatedList;
    @OneToMany(mappedBy = "playerOutId")
    private List<Swap> swapList;
    @OneToMany(mappedBy = "playerInId")
    private List<Swap> swapList1;
    @OneToMany(mappedBy = "playerGettingId")
    private List<Passing> passingList;
    @OneToMany(mappedBy = "playerPassingId")
    private List<Passing> passingList1;
    @OneToMany(mappedBy = "playerId")
    private List<Assist> assistList;
    @OneToMany(mappedBy = "playerId")
    private List<Card> cardList;

    public Player() {
    }

    public Player(Integer id) {
        this.id = id;
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

    public String getPreferedLeg() {
        return preferedLeg;
    }

    public void setPreferedLeg(String preferedLeg) {
        this.preferedLeg = preferedLeg;
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
    public List<Faul> getFaulList1() {
        return faulList1;
    }

    public void setFaulList1(List<Faul> faulList1) {
        this.faulList1 = faulList1;
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
    public List<Swap> getSwapList1() {
        return swapList1;
    }

    public void setSwapList1(List<Swap> swapList1) {
        this.swapList1 = swapList1;
    }

    @XmlTransient
    public List<Passing> getPassingList() {
        return passingList;
    }

    public void setPassingList(List<Passing> passingList) {
        this.passingList = passingList;
    }

    @XmlTransient
    public List<Passing> getPassingList1() {
        return passingList1;
    }

    public void setPassingList1(List<Passing> passingList1) {
        this.passingList1 = passingList1;
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
        return (this.no).toString() + " "+this.name+" "+this.surname ;
    }
    
}
