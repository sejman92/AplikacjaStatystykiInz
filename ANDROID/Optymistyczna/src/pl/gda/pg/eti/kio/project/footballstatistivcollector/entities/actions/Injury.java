package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Injury extends Action {
	private int game_id;
	private int player_id;
	private int time;
	private int duration;
	private String kind;
	private int faul_id;
	private String comment;
	private Faul faul;
	
	public Injury(int game_id, int player_id, int time, int duration, String kind, int faul_id, String comment)
	{
		this.game_id=game_id;
		this.player_id=player_id;
		this.time=time;
		this.duration=duration;
		this.kind=kind;
		this.faul_id=faul_id;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getFaul_id() {
		return faul_id;
	}

	public void setFaul_id(int faul_id) {
		this.faul_id = faul_id;
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
	

}
