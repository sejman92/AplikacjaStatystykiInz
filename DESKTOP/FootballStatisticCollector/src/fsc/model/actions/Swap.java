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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(catalog = "fsmdb", name = "Swap",schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Swap.findAll", query = "SELECT s FROM Swap s"),
    @NamedQuery(name = "Swap.findById", query = "SELECT s FROM Swap s WHERE s.id = :id"),
    @NamedQuery(name = "Swap.findByInjuryId", query = "SELECT s FROM Swap s WHERE s.injuryId = :injuryId")})
public class Swap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Lob
    @Column(length = 65535)
    private String comment;
    @Column(name = "injury_id")
    private Integer injuryId;
    @OneToMany(mappedBy = "swapId")
    private List<Injury> injuryList;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_out_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerOutId;
    @JoinColumn(name = "player_in_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerInId;
    @OneToMany(mappedBy = "swapId")
    private List<Card> cardList;

    public Swap() {
    }

    public Swap(Integer id) {
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

    public Integer getInjuryId() {
        return injuryId;
    }

    public void setInjuryId(Integer injuryId) {
        this.injuryId = injuryId;
    }

    @XmlTransient
    public List<Injury> getInjuryList() {
        return injuryList;
    }

    public void setInjuryList(List<Injury> injuryList) {
        this.injuryList = injuryList;
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

    public Player getPlayerOutId() {
        return playerOutId;
    }

    public void setPlayerOutId(Player playerOutId) {
        this.playerOutId = playerOutId;
    }

    public Player getPlayerInId() {
        return playerInId;
    }

    public void setPlayerInId(Player playerInId) {
        this.playerInId = playerInId;
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
        if (!(object instanceof Swap)) {
            return false;
        }
        Swap other = (Swap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fsc.model.Swap[ id=" + id + " ]";
    }
    
}
