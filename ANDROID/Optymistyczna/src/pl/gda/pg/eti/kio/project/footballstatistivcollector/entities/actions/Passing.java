package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Passing extends Action {
	private int id;
	private int game_id;
	private int player_id;
	private int time;
	private int success;
	private String comment;
	
	public Passing(int id, int game_id, int player_id, int time, int success, String comment)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.success=success;
		this.comment=comment;
	}
	
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public Passing(int player_id, int time, int success, String comment)
	{
		this.player_id=player_id;
		this.time=time;
		this.success=success;
		this.comment=comment;
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

}
