/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.actions;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.User;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb",name = "Assist", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assist.findAll", query = "SELECT a FROM Assist a"),
    @NamedQuery(name = "Assist.findById", query = "SELECT a FROM Assist a WHERE a.id = :id")})
public class Assist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Lob
    @Column(length = 65535)
    private String comment;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "shot_id", referencedColumnName = "id")
    @ManyToOne
    private Shot shotId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerId;

    public Assist() {
    }

    public Assist(Integer id) {
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

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public Shot getShotId() {
        return shotId;
    }

    public void setShotId(Shot shotId) {
        this.shotId = shotId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assist)) {
            return false;
        }
        Assist other = (Assist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.model.Assist[ id=" + id + " ]";
    }
    
}
