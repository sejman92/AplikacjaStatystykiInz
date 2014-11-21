package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;

public class Shot extends Action {
	private int id;
	private int player_id;
	private int game_id;
	private int time;
	private String comment;
	private String succes;
	private int corner;
	private int freekick;
	private int penalty;

	public Shot(int id, int game_id, int player_id, int time, String comment, String succes, int corner, int freekick, int penalty)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
		this.succes=succes;
		this.penalty=penalty;
		this.freekick=freekick;
		this.corner=corner;
	}
	
	public Shot(int player_id, String comment, int time, String succes)
	{
		this.id=0;
		this.game_id=0;
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
		this.succes=succes;
		this.corner=0;
		this.penalty=0;
		this.freekick=0;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getGame_id() {
		return game_id;
	}
	@Override
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSucces() {
		return succes;
	}
	public void setSucces(String succes) {
		this.succes = succes;
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

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	@Override
	public int addToDataBase(DatabaseManager dbm) {
		this.id=dbm.addShot(this.game_id,this.player_id, this.time, this.comment, this.succes,this.penalty, this.corner, this.freekick);
		return this.id;
	}

}
