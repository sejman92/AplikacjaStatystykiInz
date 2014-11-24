package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Defense;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Faul;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Injury;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Takeover;

public class Player {
	private int id;
	private String name;
	private String surname;
	private int nr;
	private String role;
	private int team_id;
	private List<Passing> passings;
	private List<Game> games;
	private List<Card> cards;
	private List<Defense> defense;
	private List<Faul> fauls;
	private List<Injury> injuries;
	private List<Shot> shots;
	private List<Takeover> takeovers;
	
	public Player(int id, String name, String surname, int nr, String role, int team_id)
	{
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.nr=nr;
		this.role=role;
		this.team_id=team_id;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getId() {
		return id;
	}

	public List<Passing> getPassings() {
		return passings;
	}

	public void setPassings(List<Passing> passings) {
		this.passings = passings;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Defense> getDefense() {
		return defense;
	}

	public void setDefense(List<Defense> defense) {
		this.defense = defense;
	}

	public List<Faul> getFauls() {
		return fauls;
	}

	public void setFauls(List<Faul> fauls) {
		this.fauls = fauls;
	}

	public List<Injury> getInjuries() {
		return injuries;
	}

	public void setInjuries(List<Injury> injuries) {
		this.injuries = injuries;
	}

	public List<Shot> getShots() {
		return shots;
	}

	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}

	public List<Takeover> getTakeovers() {
		return takeovers;
	}

	public void setTakeovers(List<Takeover> takeovers) {
		this.takeovers = takeovers;
	}

	public void setId(int id) {
		this.id = id;
	}
}
