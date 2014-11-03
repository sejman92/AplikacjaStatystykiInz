package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Shot extends Action {
	private int id;
	private int player_id;
	private int game_id;
	private int time;
	private String comment;
	private String succes;

	public Shot(int id, int game_id, int player_id, int time, String comment, String succes)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
		this.succes=succes;
	}
	
	public Shot(int player_id, String comment, int time, String succes)
	{
		this.player_id=player_id;
		this.time=time;
		this.comment=comment;
		this.succes=succes;
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
}
