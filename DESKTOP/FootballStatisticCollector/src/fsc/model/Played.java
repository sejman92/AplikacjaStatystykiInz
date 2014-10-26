/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Played.findAll", query = "SELECT p FROM Played p"),
    @NamedQuery(name = "Played.findById", query = "SELECT p FROM Played p WHERE p.id = :id"),
    @NamedQuery(name = "Played.findByTeamId", query = "SELECT p FROM Played p WHERE p.teamId = :teamId")})
public class Played implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "team_id")
    private Integer teamId;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;

    public Played() {
    }

    public Played(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Played)) {
            return false;
        }
        Played other = (Played) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.controller.Played[ id=" + id + " ]";
    }
    
}
