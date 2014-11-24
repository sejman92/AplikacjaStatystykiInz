package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities;

public class Game {
	private int id;
	private String date;
	private String place;
	private int lost_goals;
	private int scored_goals;
	private String oponent;
	private String comment;
	
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
}
