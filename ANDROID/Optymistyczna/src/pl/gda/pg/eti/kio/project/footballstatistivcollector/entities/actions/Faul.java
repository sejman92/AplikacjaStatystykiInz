package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

public class Faul extends Action {
	private int game_id;
	private int player_victim_id;
	private int player_ofender_id;
	private int time;
	private String comment;
	private int card_id;
	private int injury_id;
	private Card card;
	private Injury injury;
	
	public Faul(int game_id, int player_victim_id, int player_ofender_id, int time, String comment,int card_id, int injury_id)
	{
		this.game_id=game_id;
		this.player_victim_id=player_victim_id;
		this.player_ofender_id=player_ofender_id;
		this.time=time;
		this.comment=comment;
		this.card_id=card_id;
		this.injury_id=injury_id;
	}
	
	public Faul(int player_victim_id, int player_ofender_id, int time, String comment,Card card, Injury injury)
	{
		this.player_victim_id=player_victim_id;
		this.player_ofender_id=player_ofender_id;
		this.time=time;
		this.comment=comment;
		this.card=card;
		this.injury=injury;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getPlayer_victim_id() {
		return player_victim_id;
	}

	public void setPlayer_victim_id(int player_victim_id) {
		this.player_victim_id = player_victim_id;
	}

	public int getPlayer_ofender_id() {
		return player_ofender_id;
	}

	public void setPlayer_ofender_id(int player_ofender_id) {
		this.player_ofender_id = player_ofender_id;
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

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getInjury_id() {
		return injury_id;
	}

	public void setInjury_id(int injury_id) {
		this.injury_id = injury_id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Injury getInjury() {
		return injury;
	}

	public void setInjury(Injury injury) {
		this.injury = injury;
	}
	

}
