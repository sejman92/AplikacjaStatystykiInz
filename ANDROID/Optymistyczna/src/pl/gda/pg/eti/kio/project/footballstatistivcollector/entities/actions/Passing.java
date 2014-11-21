package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;

public class Passing extends Action {
	private int id;
	private int game_id;
	private int player_id;
	private int time;
	private int success;
	private int assist;
	private int corner;
	private int freekick;
	private String comment;
	
	public Passing(int id, int game_id, int player_id, int time, int success, String comment, int assist, int corner, int freekick)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.success=success;
		this.comment=comment;
		this.assist=assist;
		this.corner=corner;
		this.freekick=freekick;
	}
	
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public Passing(int player_id, int time, int success, String comment)
	{
		this.id=0;
		this.game_id=0;
		this.player_id=player_id;
		this.time=time;
		this.success=success;
		this.comment=comment;
		this.assist=0;
		this.corner=0;
		this.freekick=0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getCorner() {
		return corner;
	}

	public void setCorner(int corner) {
		this.corner = corner;
	}

	public int getFreekick() {
		return freekick;
	}

	public void setFreekick(int freekick) {
		this.freekick = freekick;
	}

	@Override
	public int addToDataBase(DatabaseManager dbm) {
		id= dbm.addPassing(game_id, player_id, time, success, comment, assist, corner, freekick);
		return id;
	}

}
