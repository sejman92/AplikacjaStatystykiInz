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
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class GameSetupActivity extends Activity {

	DatePicker datepicker; 
	TimePicker timepicker;
	DatabaseManager dbm = new DatabaseManager(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Calendar calendar = Calendar.getInstance();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setup);
		datepicker = (DatePicker) findViewById(R.id.datePicker1);
		timepicker = (TimePicker) findViewById(R.id.timePicker1);
		timepicker.setIs24HourView(true);
		timepicker.setCurrentHour(calendar.get(calendar.HOUR_OF_DAY));
		

	}
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_setup, menu);
		return true;
	}

}
