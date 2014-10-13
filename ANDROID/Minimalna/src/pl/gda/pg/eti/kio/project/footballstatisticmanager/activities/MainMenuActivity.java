package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;

import com.example.footballstatisticmanager.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}
	
	public void buttonDruzyny(View v)
	{
		Intent i = new Intent(getApplicationContext(),TeamsActivity.class);
		startActivity(i);
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
