package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Defense;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Faul;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Injury;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Swap;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Takeover;

public class Game {
	private int id;
	private String date;
	private String place;
	private int lost_goals;
	private int scored_goals;
	private String oponent;
	private String comment;
	private List<Passing> passings;
	private List<Card> cards;
	private List<Defense> defense;
	private List<Faul> fauls;
	private List<Injury> injuries;
	private List<Shot> shots;
	private List<Takeover> takeovers;
	private List<Swap> swaps;
	
	public Game(int id,String date, String place, int lost_goals,int scored_goals, String oponent,String comment)
	{
		this.id=id;
		this.date=date;
		this.place=place;
		this.lost_goals=lost_goals;
		this.scored_goals=scored_goals;
		this.oponent=oponent;
		this.comment=comment;
	}
	
	public Game(String date, String place, int lost_goals, int scored_goals, String oponent, String comment)
	{
		this.date=date;
		this.place=place;
		this.lost_goals=lost_goals;
		this.scored_goals=scored_goals;
		this.oponent=oponent;
		this.comment=comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getLost_goals() {
		return lost_goals;
	}
	public void setLost_goals(int lost_goals) {
		this.lost_goals = lost_goals;
	}
	public int getScored_goals() {
		return scored_goals;
	}
	public void setScored_goals(int scored_goals) {
		this.scored_goals = scored_goals;
	}
	public String getOponent() {
		return oponent;
	}
	public void setOponent(String oponent) {
		this.oponent = oponent;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Passing> getPassings() {
		return passings;
	}
	public void setPassings(List<Passing> passings) {
		this.passings = passings;
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
	public List<Swap> getSwaps() {
		return swaps;
	}
	public void setSwaps(List<Swap> swaps) {
		this.swaps = swaps;
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
	
}
