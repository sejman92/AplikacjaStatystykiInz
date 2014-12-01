package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;

public class Faul extends Action {
	private int id;
	private int game_id;
	private int player_victim_id;
	private int player_ofender_id;
	private int time;
	private String comment;
	private int card_id;
	private int injury_id;
	private Card card;
	private Injury injury;
	
	public Faul(int id, int game_id, int player_victim_id, int player_ofender_id, int time, String comment,int card_id, int injury_id)
	{
		this.id=id;
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
	@Override
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int addToDataBase(DatabaseManager dbm) {
		if(injury!=null)
			injury_id=dbm.addInjury(game_id, player_victim_id, time, comment);
		else
			injury_id=0;
		if(card!=null)
			card_id=dbm.addCard(game_id, player_ofender_id, time, card.getKind(), comment);
		else
			card_id=0;
		id=dbm.addFaul(game_id, player_victim_id, player_ofender_id, time, comment, card_id, injury_id);
		return id;
	}
	

}
