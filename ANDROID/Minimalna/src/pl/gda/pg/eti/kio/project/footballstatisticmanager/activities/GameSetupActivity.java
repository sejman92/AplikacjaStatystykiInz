package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;


import java.util.Calendar;



import pl.gda.pg.eti.kio.project.footballstatisticmanager.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;

import com.example.footballstatisticmanager.R;
import com.example.footballstatisticmanager.R.layout;
import com.example.footballstatisticmanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class GameSetupActivity extends Activity {

	DatePicker datepicker; 
	TimePicker timepicker;
	DatabaseManager dbm = new DatabaseManager(this);
	TextView warning;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Calendar calendar = Calendar.getInstance();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setup);
		datepicker = (DatePicker) findViewById(R.id.datePicker1);
		warning = (TextView) findViewById(R.id.warning_not_enought_players_in_basic_team);
		//timepicker = (TimePicker) findViewById(R.id.timePicker1);
		//timepicker.setIs24HourView(true);
		//timepicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		if(Focus.main_players_for_focused_game.size()!=11)
			warning.setText(getResources().getString(R.string.not_enought_players));
		else
			warning.setText("nie ma");
		

	}
	
	public void chose_players_button(View v)
	{
		Intent intent = new Intent(GameSetupActivity.this,ChosePlayersForGameActivity.class);     	
    	startActivity(intent);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_game_setup);
		if(Focus.main_players_for_focused_game.size()!=11)
			warning.setText(getResources().getString(R.string.not_enought_players));
		else
			warning.setText("nie ma");
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_setup, menu);
		return true;
	}

}
