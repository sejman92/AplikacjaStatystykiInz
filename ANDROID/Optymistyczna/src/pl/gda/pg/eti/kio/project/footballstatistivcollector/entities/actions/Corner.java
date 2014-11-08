package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Corner extends Action {

	private int game_id;
	private int player_id;
	private int time;
	private String comment;
	
	public Corner(int player_id, int time, String comment)
	{
		this.comment=comment;
		this.time=time;
		this.player_id=player_id;
	}
	
	public Corner(int game_id, int player_id, int time, String comment)
	{
		this.game_id=game_id;
		this.comment=comment;
		this.time=time;
		this.player_id=player_id;
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