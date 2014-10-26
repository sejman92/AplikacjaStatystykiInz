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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Shot.findAll", query = "SELECT s FROM Shot s"),
    @NamedQuery(name = "Shot.findById", query = "SELECT s FROM Shot s WHERE s.id = :id"),
    @NamedQuery(name = "Shot.findByPalce", query = "SELECT s FROM Shot s WHERE s.palce = :palce"),
    @NamedQuery(name = "Shot.findBySuccess", query = "SELECT s FROM Shot s WHERE s.success = :success")})
public class Shot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Lob
    private String comment;
    private String palce;
    private Boolean success;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerId;
    @OneToMany(mappedBy = "shotId")
    private Collection<Penalty> penaltyCollection;
    @OneToMany(mappedBy = "shotId")
    private Collection<Assist> assistCollection;

    public Shot() {
    }

    public Shot(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPalce() {
        return palce;
    }

    public void setPalce(String palce) {
        this.palce = palce;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    @XmlTransient
    public Collection<Penalty> getPenaltyCollection() {
        return penaltyCollection;
    }

    public void setPenaltyCollection(Collection<Penalty> penaltyCollection) {
        this.penaltyCollection = penaltyCollection;
    }

    @XmlTransient
    public Collection<Assist> getAssistCollection() {
        return assistCollection;
    }

    public void setAssistCollection(Collection<Assist> assistCollection) {
        this.assistCollection = assistCollection;
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
        if (!(object instanceof Shot)) {
            return false;
        }
        Shot other = (Shot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.controller.Shot[ id=" + id + " ]";
    }
    
}
