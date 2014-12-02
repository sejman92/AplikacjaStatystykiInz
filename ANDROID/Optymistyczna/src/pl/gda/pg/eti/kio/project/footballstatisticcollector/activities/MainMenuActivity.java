package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainMenuActivity extends Activity {
	
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		edit = (EditText) findViewById(R.id.przycisk);
		Focus.main_players_for_focused_game= new LinkedList<Player>();
		Focus.backup_players_for_focused_game= new LinkedList<Player>();
		Focus.swaped_players_for_focused_game= new LinkedList<Player>();
		Focus.players_from_focused_team = new LinkedList<Player>();
		
	}
	
	public void buttonDruzyny(View v)
	{
		Intent i = new Intent(getApplicationContext(),TeamsActivity.class);
		startActivity(i);
	}
	
	public void klik(View v)
	{
		String ok= edit.getText().toString();
		Toast.makeText(this, ok, Toast.LENGTH_LONG).show();
		DatabaseManager dbm = new DatabaseManager(this);
		dbm.zmiany();
	}
	
	public void buttonUstawienia(View v)
	{
		Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
