package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Swap extends Action {
	private int id;
	private int game_id;
	private int player_in_id;
	private int player_out_id;
	private int time;
	private String comment;
	
	public Swap(int id, int game_id, int player_in_id, int player_out_id, int time, String comment)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_in_id=player_in_id;
		this.player_out_id=player_out_id;
		this.time=time;
		this.comment=comment;		
	}
	
	public Swap(int player_in_id, int player_out_id, int time, String comment)
	{
		this.player_in_id=player_in_id;
		this.player_out_id=player_out_id;
		this.time=time;
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

	public int getPlayer_in_id() {
		return player_in_id;
	}

	public void setPlayer_in_id(int player_in_id) {
		this.player_in_id = player_in_id;
	}

	public int getPlayer_out_id() {
		return player_out_id;
	}

	public void setPlayer_out_id(int player_out_id) {
		this.player_out_id = player_out_id;
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
