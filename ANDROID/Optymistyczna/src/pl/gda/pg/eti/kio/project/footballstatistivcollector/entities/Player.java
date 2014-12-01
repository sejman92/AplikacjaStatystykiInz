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

	public int getGoals()
	{
		int goals=0;
		for(Shot shot : shots)
			if(shot.getSucces()=="goal")
				goals++;
		return goals;
	}
	public int getMissedShots()
	{
		int missed=0;
		for(Shot shot : shots)
			if(shot.getSucces()!="goal")
				missed++;
		return missed;
	}
	public int getPenaltys()
	{
		int penalty=0;
		for(Shot shot : shots)
			if(shot.getSucces()=="goal"&& shot.getPenalty()==1)
				penalty++;
		return penalty;
	}
	public int getAssists()
	{
		int assists=0;
		for(Passing p : passings)
			if(p.getAssist()==1)
				assists++;
		return assists;
	}
	public int getCorners()
	{
		int corners=0;
		for(Passing p : passings)
			if(p.getCorner()==1)
				corners++;
		return corners;
	}
	public int getFreekicks()
	{
		int freekicks=0;
		for(Passing p : passings)
			if(p.getFreekick()==1)
				freekicks++;
		return freekicks;
	}
	public int getGoodPassings()
	{
		int passing=0;
		for(Passing p : passings)
			if(p.getSuccess()==1)
				passing++;
		return passing;
	}
	public int getBadPassings()
	{
		int passing=0;
		for(Passing p : passings)
			if(p.getSuccess()==0)
				passing++;
		return passing;
	}
	public int getYellowCards()
	{
		int card=0;
		for(Card c : cards)
			if(c.getKind()=="yellow")
				card++;
		return card;
	}
	public int getRedCards()
	{
		int card=0;
		for(Card c : cards)
			if(c.getKind()=="red")
				card++;
		return card;
	}
	public int getFaulsByPlayer()
	{
		int faul=0;
		for(Faul f :fauls)
			if(f.getPlayer_victim_id()==0)
				faul++;
		return faul;
	}
	public int getFaulsOnPlayer()
	{
		int faul=0;
		for(Faul f :fauls)
			if(f.getPlayer_ofender_id()==0)
				faul++;
		return faul;
	}
	
	public int getGoalsForGame(int game_id)
	{
		int goals=0;
		for(Shot shot : shots)
			if(shot.getSucces()=="goal" && shot.getGame_id()==game_id)
				goals++;
		return goals;
	}
	public int getMissedShotsForGame(int game_id)
	{
		int missed=0;
		for(Shot shot : shots)
			if(shot.getSucces()!="goal" && shot.getGame_id()==game_id)
				missed++;
		return missed;
	}
	public int getPenaltysForGame(int game_id)
	{
		int penalty=0;
		for(Shot shot : shots)
			if(shot.getSucces()=="goal"&& shot.getPenalty()==1 && shot.getGame_id()==game_id)
				penalty++;
		return penalty;
	}
	public int getAssistsForGame(int game_id)
	{
		int assists=0;
		for(Passing p : passings)
			if(p.getAssist()==1 && p.getGame_id()==game_id)
				assists++;
		return assists;
	}
	public int getCornersForGame(int game_id)
	{
		int corners=0;
		for(Passing p : passings)
			if(p.getCorner()==1 && p.getGame_id()==game_id)
				corners++;
		return corners;
	}
	public int getFreekicksForGame(int game_id)
	{
		int freekicks=0;
		for(Passing p : passings)
			if(p.getFreekick()==1 && p.getGame_id()==game_id)
				freekicks++;
		return freekicks;
	}
	public int getGoodPassingsForGame(int game_id)
	{
		int passing=0;
		for(Passing p : passings)
			if(p.getSuccess()==1 && p.getGame_id()==game_id)
				passing++;
		return passing;
	}
	public int getBadPassingsForGame(int game_id)
	{
		int passing=0;
		for(Passing p : passings)
			if(p.getSuccess()==0 && p.getGame_id()==game_id)
				passing++;
		return passing;
	}
	public int getYellowCardsForGame(int game_id)
	{
		int card=0;
		for(Card c : cards)
			if(c.getKind()=="yellow" && c.getGame_id()==game_id)
				card++;
		return card;
	}
	public int getRedCardsForGame(int game_id)
	{
		int card=0;
		for(Card c : cards)
			if(c.getKind()=="red" && c.getGame_id()==game_id)
				card++;
		return card;
	}
	public int getFaulsByPlayerForGame(int game_id)
	{
		int faul=0;
		for(Faul f : fauls)
			if(f.getGame_id()==game_id && f.getPlayer_victim_id()==0)
				faul++;
		return faul;
	}
	public int getFaulsOnPlayerForGame(int game_id)
	{
		int faul=0;
		for(Faul f : fauls)
			if(f.getGame_id()==game_id && f.getPlayer_ofender_id()==0)
				faul++;
		return faul;
	}
	public int getInjuriesForGame(int game_id)
	{
		int injury=0;
		for(Injury i : injuries)
			if(i.getGame_id()==game_id)
				injury++;
		return injury;
	}

}
