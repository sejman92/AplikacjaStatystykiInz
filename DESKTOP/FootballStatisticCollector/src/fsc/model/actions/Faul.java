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
@Table(catalog = "fsmdb",name = "Faul", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faul.findAll", query = "SELECT f FROM Faul f"),
    @NamedQuery(name = "Faul.findById", query = "SELECT f FROM Faul f WHERE f.id = :id"),
    @NamedQuery(name = "Faul.findByTime", query = "SELECT f FROM Faul f WHERE f.time = :time"),
    @NamedQuery(name = "Faul.findByCardId", query = "SELECT f FROM Faul f WHERE f.cardId = :cardId")})
public class Faul implements Serializable {
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
    @Column(name = "card_id")
    private Integer cardId;
    @JoinColumn(name = "injury_id", referencedColumnName = "id")
    @ManyToOne
    private Injury injuryId;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "player_ofender_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerOfenderId;
    @JoinColumn(name = "player_victim_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerVictimId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @OneToMany(mappedBy = "faulId")
    private List<Card> cardList;

    public Faul() {
    }

    public Faul(Integer id) {
        this.id = id;
    }

    @Override
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

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Injury getInjuryId() {
        return injuryId;
    }

    public void setInjuryId(Injury injuryId) {
        this.injuryId = injuryId;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public Player getPlayerOfenderId() {
        return playerOfenderId;
    }

    public void setPlayerOfenderId(Player playerOfenderId) {
        this.playerOfenderId = playerOfenderId;
    }

    public Player getPlayerVictimId() {
        return playerVictimId;
    }

    public void setPlayerVictimId(Player playerVictimId) {
        this.playerVictimId = playerVictimId;
    }

    public Game getGameId() {
        return gameId;
    }

    @Override
    public void setGameId(Game gameId) {
        this.gameId = gameId;
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
        if (!(object instanceof Faul)) {
            return false;
        }
        Faul other = (Faul) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.model.Faul[ id=" + id + " ]";
    }
    
}
