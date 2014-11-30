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
@Table(catalog = "fsmdb",name = "Passing", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passing.findAll", query = "SELECT p FROM Passing p"),
    @NamedQuery(name = "Passing.findById", query = "SELECT p FROM Passing p WHERE p.id = :id"),
    @NamedQuery(name = "Passing.findByTime", query = "SELECT p FROM Passing p WHERE p.time = :time"),
    @NamedQuery(name = "Passing.findBySuccessful", query = "SELECT p FROM Passing p WHERE p.successful = :successful"),
    @NamedQuery(name = "Passing.findByAssist", query = "SELECT p FROM Passing p WHERE p.assist = :assist"),
    @NamedQuery(name = "Passing.findByCorner", query = "SELECT p FROM Passing p WHERE p.corner = :corner"),
    @NamedQuery(name = "Passing.findByFreekick", query = "SELECT p FROM Passing p WHERE p.freekick = :freekick")})
public class Passing implements Serializable, IAction {
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
    private Boolean successful;
    private Boolean assist;
    private Boolean corner;
    private Boolean freekick;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_passing_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerPassingId;

    public Passing() {
    }

    public Passing(Integer id) {
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

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Boolean getAssist() {
        return assist;
    }

    public void setAssist(Boolean assist) {
        this.assist = assist;
    }

    public Boolean getCorner() {
        return corner;
    }

    public void setCorner(Boolean corner) {
        this.corner = corner;
    }

    public Boolean getFreekick() {
        return freekick;
    }

    public void setFreekick(Boolean freekick) {
        this.freekick = freekick;
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

    public Player getPlayerPassingId() {
        return playerPassingId;
    }

    public void setPlayerPassingId(Player playerPassingId) {
        this.playerPassingId = playerPassingId;
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
        if (!(object instanceof Passing)) {
            return false;
        }
        Passing other = (Passing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       // String result = this.time.toString() + " ";
        String result = getActionName() + " ";
        //if(getId() != null)
        //    result += getId();
        //result += "]: ";
        if(getPlayerPassingId()!= null)
            result += getPlayerPassingId() + " ";
        if(getSuccessful()!=null){
            if(getSuccessful())            
                result += "celne ";
            else
                result += "niecelne ";
        }
        if(this.getCorner()) result += "Rzut rożny ";
        if(this.getFreekick()) result += "Rzut wolny ";
        return result;
    }

    @Override
    public int getIdTypeOfAction() {
        return 2;
    }

    @Override
    public String getActionName() {
        return "Podanie ";
    }
    
}
