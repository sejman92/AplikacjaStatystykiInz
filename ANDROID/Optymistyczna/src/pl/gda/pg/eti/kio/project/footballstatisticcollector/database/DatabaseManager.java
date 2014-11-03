package pl.gda.pg.eti.kio.project.footballstatisticcollector.database;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Team;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper{

	public DatabaseManager(Context context) {
		super(context, "baza_danych.db", null,1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] creaty = new String[16];
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
				"result TEXT,"+
				"lost_goals INTEGER,"+
				"scored_goals INTEGER,"+
				"oponent TEXT,"+
				"comment TEXT"+
			")";
		creaty[3]="CREATE TABLE Participated"+
			"("+
				"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"player_id INTEGER,"+
				"game_is INTEGER"+
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
					"game_id INTEGER,"+
					"comment TEXT"+
					")";
		creaty[6]="CREATE TABLE Assist"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"shot_id INTEGER, "+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"comment TEXT"+
					")";
		creaty[7]="CREATE TABLE Faul"+
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
		creaty[8]="CREATE TABLE Card"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_id INTEGER,"+
					"time INTEGER,"+
					"kind TEXT,"+
					"comment TEXT,"+
					"swap_id INTEGER,"+
					"faul_id INTEGER"+
					")";
		creaty[9]="CREATE TABLE Swap"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_in_id INTEGER,"+
					"player_out_id INTEGER,"+
					"time INTEGER,"+
					"injury_id INTEGER,"+
					"comment INTEGER"+
					")";
		creaty[10]="CREATE TABLE Injury"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER, "+
					"swap_id INTEGER,"+
					"time INTEGER,"+
					"duration INTEGER,"+
					"kind TEXT,"+
					"faul_id INTEGER,"+
					"comment TEXT"+
					")";
		creaty[11]="CREATE TABLE Defense"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT"+
					")";
		creaty[12]="CREATE TABLE Passing"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"game_id INTEGER,"+
					"player_id INTEGER,"+
					"success INTEGER,"+
					"time INTEGER,"+
					"comment TEXT)";
		creaty[13]="CREATE TABLE Takeover"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT"+
					")";
		creaty[14]="CREATE TABLE Corner"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT)";
		creaty[15]="CREATE TABLE Penalty"+
				"("+
					"ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"player_id INTEGER,"+
					"game_id INTEGER,"+
					"time INTEGER,"+
					"comment TEXT)";
		
		for(int i=0;i<16;i++)
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
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		try
		{
			String[] columns={"ID", "name", "surname", "number", "role", "team_id"};
			String[] args={String.valueOf(team_id)};
			cursor = db.query("Player", columns, "team_id=?", args, null, null, null);
			while(cursor.moveToNext())
			{
				Player player=new Player(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
				list.add(player);				
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
		try
		{
			String[] columns={"ID", "name", "surname", "number", "role", "team_id"};
			String[] args={String.valueOf(id)};
			cursor = db.query("Player", columns, "ID=?", args, null, null, null);
			Player player=new Player(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
			return player;
		}catch(SQLException e)
		{
			Log.d("Baza",e.toString());
			return null;
		}
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
	
	public void addGame(String date, String place, int lost_goals, int scored_goals, String oponent, String comment, List<Player> players_list, int team_id)
	{
		int game_id=0;
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			ContentValues values = new ContentValues();
			values.put("data", date);
			values.put("place", place);
			values.put("result", scored_goals+":"+lost_goals);
			values.put("lost_goals",lost_goals);
			values.put("scored_goals",scored_goals);
			values.put("oponent", oponent);
			values.put("comment",comment);
			game_id=(int) db.insertOrThrow("Game", null, values);
			
		}catch(SQLException e)
		{
			Log.d("Baza.Game", e.getMessage().toString() );
			return;
		}
		
		try
		{
			ContentValues values = new ContentValues();
			values.put("team_id", team_id);
			values.put("game_id", game_id);
			db.insertOrThrow("Played", null, values);			
		}catch(SQLException e)
		{
			Log.d("Baza.Played", e.getMessage().toString() );
			return;
		}
		
		try
		{
			for(int i=0;i<players_list.size();i++)
			{
				ContentValues values = new ContentValues();
				values.put("game_id", game_id);
				values.put("player_id",players_list.get(i).getId());
				db.insertOrThrow("Participated", null, values);
			}
		}catch(SQLException e)
		{
			Log.d("Baza.Participated", e.getMessage().toString() );
			return;
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int old, int NEW) {
		
	}
}
