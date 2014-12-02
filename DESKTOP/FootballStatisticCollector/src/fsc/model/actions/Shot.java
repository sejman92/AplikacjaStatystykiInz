/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.actions;

import fsc.model.Game;
import fsc.model.Player;
import fsc.model.User;
import fsc.model.enums.Kicks;
import fsc.model.interfaces.IAction;
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
@Table(catalog = "fsmdb",name = "Shot", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shot.findAll", query = "SELECT s FROM Shot s"),
    @NamedQuery(name = "Shot.findById", query = "SELECT s FROM Shot s WHERE s.id = :id"),
    @NamedQuery(name = "Shot.findBySuccess", query = "SELECT s FROM Shot s WHERE s.success = :success"),
    @NamedQuery(name = "Shot.findByCorner", query = "SELECT s FROM Shot s WHERE s.corner = :corner"),
    @NamedQuery(name = "Shot.findByFreekick", query = "SELECT s FROM Shot s WHERE s.freekick = :freekick"),
    @NamedQuery(name = "Shot.findByPenalty", query = "SELECT s FROM Shot s WHERE s.penalty = :penalty"),
    @NamedQuery(name = "Shot.findByBodyPart", query = "SELECT s FROM Shot s WHERE s.bodyPart = :bodyPart")})
public class Shot implements Serializable, IAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Lob
    @Column(length = 65535)
    private String comment;
    @Column(length = 20)
    private String success;
    private Boolean corner;
    private Boolean freekick;
    private Boolean penalty;
    @Column(length = 20)
    private String bodyPart;
    private Integer time;
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User ownerId;
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @ManyToOne
    private Game gameId;
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @ManyToOne
    private Player playerId;

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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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
        String result = this.time.toString() + "min: ";
        if( (this.getSuccess()!= null) && (this.getSuccess().contains("GOL"))){
            if(getPlayerId() != null)
                result += getPlayerId() + "zdobył bramkę!";
        } else {
            if(getPlayerId() != null)
                result += getPlayerId() + " oddał "; 
            result += this.getActionName() + " ";
            result += this.getSuccess() + " ";
            if(this.getCorner()) result += "[Rzut rożny] ";
            if(this.getPenalty()) result += "[Rzut karny] ";
            if(this.getFreekick()) result += "[Rzut wolny] ";  
        }
        return result;
    }

    @Override
    public int getIdTypeOfAction() {
        return 1;
    }

    @Override
    public String getActionName() {
        return "STRZAŁ";
    }

}
