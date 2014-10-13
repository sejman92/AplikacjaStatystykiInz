package pl.gda.pg.eti.kio.project.footballstatisticmanager.database;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Team;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

	public DatabaseManager(Context context) {
		super(context, "baza_danyc.db", null,1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] creaty = new String[16];
		creaty[0]="CREATE TABLE druzyna "+
			"( "+
				"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"nazwa TEXT "+
			") ";
		creaty[1]="CREATE TABLE zawodnik"+
			"("+
				"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"imie TEXT,"+
				"nazwisko TEXT,"+
				"numer INTEGER,"+
				"pozycja TEXT,"+
				"id_druzyny INTEGER"+
				")";
		creaty[2]="CREATE TABLE mecz "+
			"("+
				"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"data DATE,"+
				"miejsce TEXT,"+
				"wynik TEXT,"+
				"gole_strzelone INTEGER,"+
				"gole_stracone INTEGER,"+
				"przeciwnik TEXT"+
			")";
		creaty[3]="CREATE TABLE gral"+
			"("+
				"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"id_zawodnika INTEGER,"+
				"id_meczu INTEGER"+
				")";	
		creaty[4]="CREATE TABLE rozegral "+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_druzyny INTEGER, "+
					"id_meczu INTEGER"+
					")";
		creaty[5]="CREATE TABLE strzal"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGERT, "+
					"efekt TEXT,"+
					"id_meczu INTEGER"+
					")";
		creaty[6]="CREATE TABLE asysta"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_strzal INTEGER, "+
					"id_zawodnik INTEGER,"+
					"id_mecz INTEGER"+
					")";
		creaty[7]="CREATE TABLE faul"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_meczu INTEGER,"+
					"id_zawodnika_faulujacego INTEGER,"+
					"id_zawodnika_faulowanego INTEGER,"+
					"czas INTEGER"+
					")	";
		creaty[8]="CREATE TABLE kartka"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_meczu INTEGER,"+
					"id_zawodnika INTEGER,"+
					"czas INTEGER,"+
					"kolor TEXT"+
					")";
		creaty[9]="CREATE TABLE zmiana"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_meczu INTEGER,"+
					"id_zawodnika_schodzacego INTEGER,"+
					"id_zawodnika_wchodzacego INTEGER,"+
					"czas INTEGER,"+
					"przyczyna TEXT"+
					")";
		creaty[10]="CREATE TABLE kontuzja"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGER,"+
					"id_meczu INTEGER, "+
					"id_zmiany INTEGER,"+
					"czas INTEGER,"+
					"czas_trwania TEXT,"+
					"rodzaj TEXT,"+
					"id_faulu INTEGER"+
					")";
		creaty[11]="CREATE TABLE obrony"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGER,"+
					"id_meczu INTEGER,"+
					"czas INTEGER)";
		creaty[12]="CREATE TABLE podania"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_meczu INTEGER,"+
					"id_zawodnika_podajacego INTEGER,"+
					"id_zawodnika_odbiorcy INTEGER,"+
					"czas INTEGER,"+
					"udane TEXT)";
		creaty[13]="CREATE TABLE przejecia"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGER,"+
					"id_meczu INTEGER,"+
					"czas INTEGER)";
		creaty[14]="CREATE TABLE rzut_karny"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGER,"+
					"id_meczu INTEGER,"+
					"czas INTEGER,"+
					"id_strzalu INTEGER,"+
					"czy_bramka TEXT)";
		creaty[15]="CREATE TABLE rzut_rozny"+
				"("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
					"id_zawodnika INTEGER,"+
					"id_meczu INTEGER,"+
					"czas INTEGER)";
		
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
			String kolumns[]={"id","nazwa"};
			cursor = db.query("druzyna", kolumns, null,null,null,null,null);
			
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
			wartosci.put("nazwa", name);
			db.insertOrThrow("druzyna", null, wartosci);
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
			db.delete("druzyna", "id=?", arg);
		}catch(SQLException e)
		{
			Log.d("baza",e.toString());
		}
	}

	public List<Player> getAllPlayersFromTeam(int team_id)
	{
		List<Player> list = new LinkedList<Player>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor;
		try
		{
			String[] columns={"id", "imie", "nazwisko", "numer", "pozycja", "id_druzyny"};
			String[] args={String.valueOf(team_id), ""};
			cursor = db.query("zawodnik", columns, "id_druzyny=?", args, null, null, null);
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
	public void addPlayer()
	{
		
	}
	public void deletePlayer(int id)
	{
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
