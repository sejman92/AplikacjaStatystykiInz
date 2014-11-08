package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Penalty extends Action {
	
	private int game_id;
	private int player_id;
	private int time;
	private int shot_id;
	private int success;
	private String comment;
	private Shot shot;
	
	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}

	public Penalty(int player_id, int time, String comment, int success,Shot shot)
	{
		this.comment=comment;
		this.success=success;
		this.time=time;
		this.shot=shot;
		this.player_id=player_id;
	}
	
	public Penalty(int game_id, int player_id, int time, String comment, int success, int shot_id)
	{
		this.game_id=game_id;
		this.success=success;
		this.comment=comment;
		this.shot_id=shot_id;
		this.time=time;
		this.player_id=player_id;
	}

	public int getShot_id() {
		return shot_id;
	}

	public void setShot_id(int shot_id) {
		this.shot_id = shot_id;
	}
	
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
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
