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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb",name = "Card", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @NamedQuery(name = "Card.findById", query = "SELECT c FROM Card c WHERE c.id = :id"),
    @NamedQuery(name = "Card.findByTime", query = "SELECT c FROM Card c WHERE c.time = :time"),
    @NamedQuery(name = "Card.findByKind", query = "SELECT c FROM Card c WHERE c.kind = :kind")})
public class Card implements Serializable, IAction {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(length = 250)
    private String kind;
    @Lob
    @Column(length = 65535)
    private String comment;
    @JoinColumn(name = "faul_id", referencedColumnName = "id")
    @ManyToOne
    private Faul faulId;
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

    public Card() {
        
    }

    public Card(Integer id) {
        this.id = id;
    }
    
    public Card(String kind){
        this.kind = kind;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Faul getFaulId() {
        return faulId;
    }

    public void setFaulId(Faul faulId) {
        this.faulId = faulId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = this.getActionName() + " "; 
        if(getKind() != null)
            result += getKind() +"[";
        if(getId() != null)
            result += getId();
        result += "]: ";
        if(getPlayerId() != null)
            result += getPlayerId() + " ";
        return result;
    }

    @Override
    public int getIdTypeOfAction() {
        return 7;
    }

    @Override
    public String getActionName() {
        return "Kartka";
    }
    
}
