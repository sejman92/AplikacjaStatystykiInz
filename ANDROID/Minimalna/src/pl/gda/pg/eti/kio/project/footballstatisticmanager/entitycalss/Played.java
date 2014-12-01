package pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss;

public class Played {
	private int id;
	private int game_id;
	private int team_id;
	private int owner;
	
	public Played(int id, int game_id, int team_id, int owner)
	{
		this.id=id;
		this.game_id=game_id;
		this.team_id=team_id;
		this.owner=owner;
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
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
}
