package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities;

public class Game {
	private int id;
	private String date;
	private String place;
	private String score;
	private int lost_goals;
	private int scored_goals;
	private String oponent;
	private int owner_id;
	
	public Game(int id,String date, String place, String score, int lost_goals,int scored_goals, String oponent, int owner_id)
	{
		this.id=id;
		this.date=date;
		this.place=place;
		this.score=score;
		this.lost_goals=lost_goals;
		this.scored_goals=scored_goals;
		this.oponent=oponent;
		this.owner_id=owner_id;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
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
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
}
