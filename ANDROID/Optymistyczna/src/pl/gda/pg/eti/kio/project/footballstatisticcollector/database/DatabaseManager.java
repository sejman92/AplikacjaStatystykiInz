package pl.gda.pg.eti.kio.project.footballstatisticcollector.database;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Team;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Defense;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Faul;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Injury;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Swap;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Takeover;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper{
	//SQLiteDatabase db = getWritableDatabase();

	public DatabaseManager(Context context) {
		super(context, "baza_danych.db", null,1);
		//QLiteDatabase db = getWritableDatabase();
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] creaty = new String[12];
		creaty[0]="CREATE TABLE Team "+
			"( "+
				"ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"NAME TEXT "+
			") ";
		creaty[1]="CREATE TABLE Player"+
			"("+
				"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"name TEXT,"+
				"surname TEXT,"+
				"number INTEGER,"+
				"role TEXT,"+
				"team_id INTEGER"+
				")";
		creaty[2]="CREATE TABLE Game "+
				"("+
				"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"data TEXT,"+
				"place TEXT,"+
				"lost_goals INTEGER,"+
				"scored_goals INTEGER,"+
				"oponent TEXT,"+
				"comment TEXT"+
			")";
		creaty[3]="CREATE TABLE Participated"+
			"("+
				"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"player_id INTEGER,"+
				"game_id INTEGER"+
				")";	
		creaty[4]="CREATE TABLE Played "+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"team_id INTEGER, "+
					"game_id INTEGER"+
					")";
		creaty[5]="CREATE TABLE Shot"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGERT, "+
					"success TEXT,"+
					"time INTEGER,"+
					"corner INTEGER,"+
					"freekick INTEGER,"+
					"penalty INTEGER,"+
					"game_id INTEGER,"+
					"comment TEXT"+
					")";
		creaty[6]="CREATE TABLE Faul"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_victim_id INTEGER,"+
					"player_ofender_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT,"+
					"injury_id INTEGER,"+
					"card_id INTEGER"+
					")	";
		creaty[7]="CREATE TABLE Card"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_id INTEGER,"+
					"time INTEGER,"+
					"kind TEXT,"+
					"swap_id INTEGER,"+
					"comment TEXT,"+
					"faul_id INTEGER"+
					")";
		creaty[8]="CREATE TABLE Swap"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_in_id INTEGER,"+
					"player_out_id INTEGER,"+
					"time INTEGER,"+
					"injury_id INTEGER,"+
					"comment INTEGER"+
					")";
		creaty[9]="CREATE TABLE Injury"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER, "+
					"swap_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT"+
					")";
		creaty[10]="CREATE TABLE Defense"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT"+
					")";
		creaty[11]="CREATE TABLE Passing"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_id INTEGER,"+
					"success INTEGER,"+
					"time INTEGER,"+
					"assist INTEGER,"+
					"corner INTEGER,"+
					"freekick INTEGER,"+
					"comment TEXT)";
		creaty[12]="CREATE TABLE Takeover"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT"+
					")";
		
		for(int i=0;i<12;i++)
		{
			try
			{ 
				db.execSQL(creaty[i]);
				Log.d("baza","utworzono tabele "+i);
			}
			catch(SQLException e)
			{
				Log.d("blad",e.toString());
			}			
		}
		
	}

	public List<Team> getAllTeams()
	{
		List<Team> list = new LinkedList<Team>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		try
		{
			String kolumns[]={"ID","name"};
			cursor = db.query("Team", kolumns, null,null,null,null,null);
			
			while(cursor.moveToNext())
			{
				Team team = new Team();
				team.setId(cursor.getInt(0));
				team.setName(cursor.getString(1));
				list.add(team);
			}
		}
		catch(SQLException e)
		{
			Log.d("Baza",e.toString());
		}
		return list;
	}
	public void addTeam(String name)
	{
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			//db.rawQuery("INSERT INTO druzyna (nazwa) VALUES ('"+name+"') ;",null);
			ContentValues wartosci = new ContentValues();
			wartosci.put("name", name);
			db.insertOrThrow("Team", null, wartosci);
		}catch(SQLException e)
		{
			Log.d("blad", e.toString());
		}
		
		Log.d("Baza","dodano rekord do druzyny o nazwie"+name);
	}
	public void deleteTeam(int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String[] arg={""+id};
		try{
			db.delete("Team", "ID=?", arg);
		}catch(SQLException e)
		{
			Log.d("baza",e.toString());
		}
		deletePlayerByTeamId(id);
		
	}
	public void updateTeam(int id, String name)
	{
		SQLiteDatabase db = getWritableDatabase();
		String query ="UPDATE Team SET name='"+name+"' WHERE id="+id;
		try{
			db.execSQL(query);
		}catch(SQLException e)
		{
			Log.d("Team", e.getMessage());
		}
	}
	
	public List<Player> getAllPlayersFromTeam(int team_id)
	{
		List<Player> list = new LinkedList<Player>();
		List<Integer> id_list = new LinkedList<Integer>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		try
		{
			String[] columns={"ID", "name", "surname", "number", "role", "team_id"};
			String[] args={String.valueOf(team_id)};
			cursor = db.query("Player", columns, "team_id=?", args, null, null, null);
			//db.close();
			while(cursor.moveToNext())
			{
				//Player player=new Player(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
				//list.add(getPlayer(cursor.getInt(0)));
				id_list.add(cursor.getInt(0));
			}
			db.close();
			for(int i : id_list)
			{
				list.add(getPlayer(i));
			}
		}catch(SQLException e)
		{
			Log.d("Baza",e.toString());
		}
		return list;		
	}
	public Player getPlayer(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Player player;
		try
		{
			String[] columns={"ID", "name", "surname", "number", "role", "team_id"};
			String[] args={String.valueOf(id)};
			
			cursor = db.query("Player", columns, "ID=?", args, null, null, null);
			cursor.moveToNext();
			player=new Player(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
			
			player.setGames(getGamesForPlayer(id));
			player.setPassings(getPassingsForPlayer(id));
			player.setCards(getCardsForPlayer(id));
			player.setDefense(getDefenseForPlayer(id));
			player.setFauls(getFaulsForPlayer(id));
			player.setInjuries(getInjuriesForPlayer(id));
			player.setShots(getShotsForPlayer(id));
			player.setTakeovers(getTakeoversForPlayer(id));
			
			
			//return player;
		}catch(SQLException e)
		{
			Log.d("Baza",e.toString());
			return null;
		}
		return player;
	}
	public void addPlayer(String name, String surname, int no, String role, int id_team)
	{
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("surname", surname);
			values.put("number", no);
			values.put("role", role);
			values.put("team_id", id_team);
			db.insertOrThrow("Player", null, values);
		}catch(SQLException e)
		{
			Log.d("blad", e.toString());
		}
		
		Log.d("Baza","dodano rekord do zawodnika o nazwie"+name);
	}
	public void deletePlayerById(int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String[] arg={""+id};
		try{
			db.delete("Player", "ID=?", arg);
		}catch(SQLException e)
		{
			Log.d("baza",e.toString());
		}
		//TODO dodaæ usuniêcie wszystkich innych wydarzen zwi¹zanych z tym zawodnikiem
	}
	public void deletePlayerByTeamId(int team_id)
	{
		SQLiteDatabase db = getWritableDatabase();
		String[] arg={""+team_id};
		try{
			db.delete("Player", "team_id=?", arg);
		}catch(SQLException e)
		{
			Log.d("baza",e.toString());
		}
		//TODO dodaæ usuniêcie wszystkich innych wydarzen zwi¹zanych z tym zawodnikiem
	}
	public void updatePlayer(int id, String name, String surname, String role, int number)
	{
		SQLiteDatabase db = getWritableDatabase();
		String query = "UPDATE Player SET name='"+name+"', surname='"+surname+"',role='"+role+"',number="+String.valueOf(number)+" WHERE id="+id;
		try{
			db.execSQL(query);
		}catch(SQLException e)
		{
			Log.d("player", e.getMessage());
		}
	}
	
	public int addGame(Game game, List<Player> players_list, List<Player>swaped_players_list, int team_id)
	{
		int game_id=0;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("data", game.getDate());
			values.put("place", game.getPlace());
			values.put("lost_goals",game.getLost_goals());
			values.put("scored_goals",game.getScored_goals());
			values.put("oponent", game.getOponent());
			values.put("comment",game.getComment());
			game_id=(int) db.insertOrThrow("Game", null, values);
			Log.d("Baza Game", "dodano gre");
			
		}catch(SQLException e)
		{
			Log.d("Baza.Game", e.getMessage().toString() );
		}
		
		try
		{
			ContentValues values = new ContentValues();
			values.put("team_id", team_id);
			values.put("game_id", game_id);
			db.insertOrThrow("Played", null, values);	
			Log.d("Baza Game", "druzyne do gry");
		}catch(SQLException e)
		{
			Log.d("Baza.Played", e.getMessage().toString() );
		
		}
		
		try
		{
			for(int i=0;i<players_list.size();i++)
			{
				ContentValues values = new ContentValues();
				values.put("game_id", game_id);
				values.put("player_id",players_list.get(i).getId());
				db.insertOrThrow("Participated", null, values);
				Log.d("Baza Game", "dodano gracza do gry");
			}
		}catch(SQLException e)
		{
			Log.d("Baza.Participated", e.getMessage().toString() );
		
		}
		
		try
		{
			for(int i=0;i<swaped_players_list.size();i++)
			{
				ContentValues values = new ContentValues();
				values.put("game_id", game_id);
				values.put("player_id",swaped_players_list.get(i).getId());
				db.insertOrThrow("Participated", null, values);
				Log.d("Baza Game", "dodano graczy do gry");
			}
		}catch(SQLException e)
		{
			Log.d("Baza.Participated", e.getMessage().toString() );	
		}
		
		return game_id;
	}
	public Game getGame(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Game game=null;
		try
		{
			String columns_game[]={"ID","data","place","lost_goals","scored_goals","oponent","comment"};
			String[] args2={String.valueOf(id)};
			cursor=db.query("Game", columns_game, "ID=?", args2,null, null, null);
			cursor.moveToNext();
			game=new Game(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6));
		}catch(SQLException e)
		{
			Log.d("Baza game", e.getMessage().toString());
		}
		return game;
	}
	public List<Game> getGamesForPlayer(int player_id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		List<Integer> participated_list = new LinkedList<Integer>();
		String[] columns_participated={"ID","player_id","game_id"};
		List<Game> games=new LinkedList<Game>();
		cursor = db.query("Participated", columns_participated, "player_id=?", args, null, null, null);
		while(cursor.moveToNext())
			participated_list.add(cursor.getInt(2));
		for(int i : participated_list)
			games.add(getGame(i));
		return games;
	}
	
	public int addShot(int game_id, int player_id, int time, String comment, String success, int penalty, int corner, int freekick )
	{
		int id=-1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id", game_id);
			values.put("player_id", player_id);
			values.put("time", time);
			values.put("success", success);
			values.put("comment", comment);
			values.put("penalty", penalty);
			values.put("corner", corner);
			values.put("freekick", freekick);
			id=(int)db.insertOrThrow("Shot", null, values);
			Log.d("shot", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad shot", e.toString());
		}
		return id;
	}
	public Shot getShot(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Shot shot=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","player_id","success","time","corner","freekick","penalty","game_id","comment"};
		try
		{
			cursor = db.query("Shot", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			shot = new Shot(cursor.getInt(0),cursor.getInt(7),cursor.getInt(1),cursor.getInt(3),cursor.getString(8),cursor.getString(2),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6));
		}catch(SQLException e)
		{
			Log.d("Baza shot", e.getMessage().toString());
		}
		return shot;
	}
	public List<Shot> getShotsForPlayer(int player_id)
	{
		List<Shot> shots=new LinkedList<Shot>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","player_id","success","time","corner","freekick","penalty","game_id","comment"};
		cursor = db.query("Shot", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			shots.add(getShot(cursor.getInt(0)));
		return shots;
	}
	
	public int addPassing(int game_id, int player_id, int time, int success, String comment, int assist, int corner, int freekick)
	{
		int id = -1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_id", player_id);
			values.put("time", time);
			values.put("comment", comment);
			values.put("success", success);
			values.put("corner", corner);
			values.put("assist", assist);
			values.put("freekick",freekick);
			id=(int)db.insertOrThrow("Passing", null, values);
			Log.d("passing", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad passing", e.toString());
		}
		return id;
	}
	public Passing getPassing(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Passing passing=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","game_id","player_id","time","success","assist","corner","freekick","comment"};
		try
		{
			cursor = db.query("Passing", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			passing = new Passing(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(8),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));
		}catch(SQLException e)
		{
			Log.d("Baza Passing", e.getMessage().toString());
		}
		return passing;
	}
	public List<Passing> getPassingsForPlayer(int player_id)
	{
		List<Passing> passings = new LinkedList<Passing>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","game_id","player_id","time","success","assist","corner","freekick","comment"};
		cursor = db.query("Passing", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			passings.add(getPassing(cursor.getInt(0)));
		return passings;
	}
	
	public int addDefense(int game_id, int player_id, int time, String comment)
	{
		int id=-1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id", game_id);
			values.put("player_id", player_id);
			values.put("time",time);
			values.put("comment",comment);
			id=(int)db.insertOrThrow("Defense", null, values);
			Log.d("defense", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad defense", e.toString());
		}
		return id;
	}
	public Defense getDefense(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Defense defense=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		try
		{
			cursor = db.query("Defense", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			defense = new Defense(cursor.getInt(0),cursor.getInt(2),cursor.getInt(1),cursor.getInt(3),cursor.getString(4));
		}catch(SQLException e)
		{
			Log.d("Baza Defense", e.getMessage().toString());
		}
		return defense;
	}
	public List<Defense> getDefenseForPlayer(int player_id)
	{
		List<Defense> defenses = new LinkedList<Defense>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		cursor = db.query("Defense", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			defenses.add(getDefense(cursor.getInt(0)));
		return defenses;
	}
	
	public int addCard(int game_id,int player_id, int time, String kind, String comment)
	{
		int id = -1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_id", player_id);
			values.put("time", time);
			values.put("comment",comment );
			values.put("kind",kind);
			id = (int)db.insertOrThrow("Card", null, values);
			Log.d("card", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad card", e.toString());
		}
		return id;
	}
	public Card getCard(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Card card=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","game_id","player_id","time","kind","comment"};
		try
		{
			cursor = db.query("Card", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			card = new Card(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5));
		}catch(SQLException e)
		{
			Log.d("Baza Card", e.getMessage().toString());
		}
		return card;
	}
	public List<Card> getCardsForPlayer(int player_id)
	{
		List<Card> cards = new LinkedList<Card>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","game_id","player_id","time","kind","comment"};
		cursor = db.query("Card", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			cards.add(getCard(cursor.getInt(0)));
		return cards;
	}
	
	public int addFaul(int game_id, int player_victim_id, int player_ofender_id, int time, String comment,int card_id, int injury_id)
	{
		int id = -1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_victim_id", player_victim_id);
			values.put("player_ofender_id", player_ofender_id);
			values.put("time", time);
			values.put("comment",comment );
			values.put("card_id",card_id);
			values.put("injury_id",injury_id);
			id=(int)db.insertOrThrow("Faul", null, values);
			Log.d("faul", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad", e.toString());
		}
		return id;
	}
	public Faul getFaul(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Faul faul=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","game_id","player_victim_id","player_ofender_id","time","comment","injury_id","card_id"};
		try
		{
			cursor = db.query("Faul", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			faul = new Faul(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7));
		}catch(SQLException e)
		{
			Log.d("Baza Faul", e.getMessage().toString());
		}
		return faul;
	}
	public List<Faul> getFaulsForPlayer(int player_id)
	{
		List<Faul> fauls = new LinkedList<Faul>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","game_id","player_victim_id","player_ofender_id","time","comment","injury_id","card_id"};
		cursor = db.query("Faul", columns, "player_victim_id=?", args,null, null, null);
		while(cursor.moveToNext())
			fauls.add(getFaul(cursor.getInt(0)));
		cursor = db.query("Faul", columns, "player_ofender_id=?", args,null, null, null);
		while(cursor.moveToNext())
			fauls.add(getFaul(cursor.getInt(0)));
		return fauls;
	}
	
	public int addInjury(int game_id, int player_id, int time, String comment)
	{
		int id = -1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_id", player_id);
			values.put("time", time);
			values.put("comment",comment);
			id=(int)db.insertOrThrow("Injury", null, values);
			Log.d("injury", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad", e.toString());
		}
		return id;
	}
	public Injury getInjury(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Injury injury=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		try
		{
			cursor = db.query("Injury", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			injury = new Injury(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4));
		}catch(SQLException e)
		{
			Log.d("Baza Injury", e.getMessage().toString());
		}
		return injury;
	}
	public List<Injury> getInjuriesForPlayer(int player_id)
	{
		List<Injury> injuries = new LinkedList<Injury>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		cursor = db.query("Injury", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			injuries.add(getInjury(cursor.getInt(0)));
		return injuries;
	}
	
	public int addTakeover(int game_id, int player_id, int time, String comment)
	{
		int id =-1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_id", player_id);
			values.put("time", time);
			values.put("comment",comment);
			id = (int)db.insertOrThrow("Injury", null, values);
			Log.d("takeover", "dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad takeover", e.toString());
		}
		return id;
	}
	public Takeover getTakeover(int id)
	{
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		Takeover takeover=null;
		String[] args={String.valueOf(id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		try
		{
			cursor = db.query("Takeover", columns, "ID=?", args,null, null, null);
			cursor.moveToNext();
			takeover = new Takeover(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4));
		}catch(SQLException e)
		{
			Log.d("Baza Takeover", e.getMessage().toString());
		}
		return takeover;
	}
	public List<Takeover> getTakeoversForPlayer(int player_id)
	{
		List<Takeover> takeovers = new LinkedList<Takeover>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		String[] args={String.valueOf(player_id)};
		String[] columns={"ID","player_id","game_id","time","comment"};
		cursor = db.query("Takeover", columns, "player_id=?", args,null, null, null);
		while(cursor.moveToNext())
			takeovers.add(getTakeover(cursor.getInt(0)));
		return takeovers;
	}
	
	public int addSwap(int game_id, int player_in_id, int player_out_id, int time, String comment)
	{
		int id =-1;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("game_id",game_id);
			values.put("player_in_id", player_in_id);
			values.put("player_out_id", player_out_id);
			values.put("time", time);
			values.put("comment",comment);
			id = (int)db.insertOrThrow("Swap", null, values);
			Log.d("dwap","dodano rekord");
		}catch(SQLException e)
		{
			Log.d("blad", e.toString());
		}
		return id;
	}
		
	public void beginTransaction()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();
	}
	
	public void commit()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.setTransactionSuccessful();
		db.endTransaction();
	}
	
	public void rollback()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.endTransaction();
	}

	public void zmiany()
	{
		SQLiteDatabase db = getWritableDatabase();
		String query="DROP TABLE Passing";
		String query2="CREATE TABLE Passing"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_id INTEGER,"+
					"success INTEGER,"+
					"time INTEGER,"+
					"assist INTEGER,"+
					"corner INTEGER,"+
					"freekick INTEGER,"+
					"comment TEXT)";
		try{
			db.execSQL(query);
		}catch(SQLException e)
		{
			Log.d("nie pyklo","dupa");
		}
		try{
			db.execSQL(query2);
		}catch(SQLException e)
		{
			Log.d("nie pyklo","dupa2");
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase dbo, int old, int NEW) {

			
	}
}
