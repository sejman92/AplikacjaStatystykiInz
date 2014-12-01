package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;

public class Injury extends Action {
	private int id;
	private int game_id;
	private int player_id;
	private int time;
	private String comment;
	private Faul faul;
	
	public Injury(int id, int game_id, int player_id, int time, String comment)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
	}
	
	public Injury(int player_id, int time, String comment)
	{
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
	}

	public int getGame_id() {
		return game_id;
	}
	@Override
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Faul getFaul() {
		return faul;
	}

	public void setFaul(Faul faul) {
		this.faul = faul;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int addToDataBase(DatabaseManager dbm) {
		id= dbm.addInjury(game_id, player_id, time, comment);
		return id;
	}
	

}
