package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;


import pl.gda.pg.eti.kio.project.footballstatisticmanager.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;
import com.example.footballstatisticmanager.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class GameSetupActivity extends Activity {

	DatePicker datepicker; 
	TimePicker timepicker;
	DatabaseManager dbm = new DatabaseManager(this);
	public EditText enemy_name;
	public EditText date;
	public EditText time;
	public EditText place;
	public String enemy_name_s;
	public String date_s;
	public String time_s;
	public String place_s;
	public Button but;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setup);
		enemy_name = (EditText) findViewById(R.id.enemy_name_activity_game_setup );
		date = (EditText) findViewById(R.id.date);
		time = (EditText) findViewById(R.id.time);
		place = (EditText) findViewById(R.id.place);
		this.setTitle(getResources().getString(R.string.preparing_game));
	}
	

	
	public void chose_players_button(View v)
	{
		enemy_name_s=enemy_name.getText().toString();
		date_s=date.getText().toString();
		time_s = time.getText().toString();
		place_s=place.getText().toString();
		Intent intent = new Intent(GameSetupActivity.this,ChosePlayersForGameActivity.class);     	
    	startActivity(intent);
	}
	
	public void begin_game(View v)
	{
		if(enemy_name.getText().toString().equals(""))
		{
			Toast.makeText(this, "Wpisz nazwê dru¿yny przeciwniej", Toast.LENGTH_LONG).show();
			return;
		}
		if(Focus.main_players_for_focused_game.size()!=11)
		{
			Toast.makeText(this, "Wybierz 11 podstawowych zawodników",Toast.LENGTH_LONG).show();
			return;
		}
		Intent intent = new Intent(GameSetupActivity.this,GameActivity.class);
		intent.putExtra("enemy", enemy_name.getText().toString());
		intent.putExtra("date", date.getText().toString());
		intent.putExtra("time", time.getText().toString());
		intent.putExtra("place", place.getText().toString());
		
		startActivity(intent);	
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_game_setup);
		date.setText("12.12.1231");
		time.setText(time_s);
		place.setText("dupa");
		enemy_name.setText(enemy_name_s);
		if(Focus.game_ended==true)
		{
			Focus.game_ended=false;
			finish();
		}

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_setup, menu);
		return true;
	}

}
