package pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss;

public class Participated {
	private int id;
	private int game_id;
	private int player_id;
	private int owner;

	public Participated(int id, int game_id, int player_id, int owner)
	{
		this.id=id;
		this.game_id=game_id;
		this.player_id=player_id;
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
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}

}
