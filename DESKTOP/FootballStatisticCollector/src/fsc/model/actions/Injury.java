/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.actions;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.User;
import fsc.model.interfaces.IAction;
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
import javax.persistence.Lob;
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
@Table(catalog = "fsmdb",name = "Injury", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Injury.findAll", query = "SELECT i FROM Injury i"),
    @NamedQuery(name = "Injury.findById", query = "SELECT i FROM Injury i WHERE i.id = :id"),
    @NamedQuery(name = "Injury.findByTime", query = "SELECT i FROM Injury i WHERE i.time = :time")})
public class Injury implements Serializable, IAction {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Temporal(TemporalType.TIME)
    private Date time;
    @Lob
    @Column(length = 65535)
    private String comment;
    @JoinColumn(name = "swap_id", referencedColumnName = "id")
    @ManyToOne
    private Swap swapId;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerId;
    @OneToMany(mappedBy = "injuryId")
    private List<Faul> faulList;

    public Injury() {
    }

    public Injury(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Swap getSwapId() {
        return swapId;
    }

    public void setSwapId(Swap swapId) {
        this.swapId = swapId;
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
    public List<Faul> getFaulList() {
        return faulList;
    }

    public void setFaulList(List<Faul> faulList) {
        this.faulList = faulList;
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
        if (!(object instanceof Injury)) {
            return false;
        }
        Injury other = (Injury) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "kontuzja [";
        if(getId() != null)
            result += getId();
        result += "]: ";
        if(getPlayerId() != null)
            result += getPlayerId();
        
        return result;
    }

    @Override
    public int getIdTypeOfAction() {
        return 9;
    }

    @Override
    public String getActionName() {
        return "kontuzja";
    }
    
}
