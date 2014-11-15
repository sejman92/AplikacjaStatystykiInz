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
import fsc.model.interfaces.IEntityElement;
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
@Table(catalog = "fsmdb", name = "Shot",schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shot.findAll", query = "SELECT s FROM Shot s"),
    @NamedQuery(name = "Shot.findById", query = "SELECT s FROM Shot s WHERE s.id = :id"),
    @NamedQuery(name = "Shot.findByPalce", query = "SELECT s FROM Shot s WHERE s.palce = :palce"),
    @NamedQuery(name = "Shot.findBySuccess", query = "SELECT s FROM Shot s WHERE s.success = :success")})
public class Shot implements Serializable, IAction {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Lob
    @Column(length = 65535)
    private String comment;
    @Column(length = 50)
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
    private List<Penalty> penaltyList;
    @OneToMany(mappedBy = "shotId")
    private List<Assist> assistList;

    public Shot() {
    }

    public Shot(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    @Override
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

    @Override
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
    public List<Penalty> getPenaltyList() {
        return penaltyList;
    }

    public void setPenaltyList(List<Penalty> penaltyList) {
        this.penaltyList = penaltyList;
    }

    @XmlTransient
    public List<Assist> getAssistList() {
        return assistList;
    }

    public void setAssistList(List<Assist> assistList) {
        this.assistList = assistList;
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
    public int getIdTypeOfAction(){
        return 1;
    }
    
    @Override
    public String getActionName(){
        return "strzał";
    }

    @Override
    public String toString() {
        String result = "strzał [";
        if(getId() != null)
            result += getId();
        result += "]: ";
        if(getPlayerId() != null)
            result += getPlayerId() + " ";
        if(getPalce() != null)
            result += getPalce();
               
        return result;
    }  
}
