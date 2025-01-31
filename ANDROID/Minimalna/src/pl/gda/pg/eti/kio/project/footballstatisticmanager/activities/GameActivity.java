package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;

import com.example.footballstatisticmanager.R;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class GameActivity extends Activity {

	String enemy_name,place,date,time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Intent input_intent=getIntent();
		Bundle input_bundle = input_intent.getExtras();
		enemy_name=input_bundle.getString("enemy_name");
		place = input_bundle.getString("place");
		date = input_bundle.getString("date");
		time = input_bundle.getString("time");
		this.setTitle(Focus.focused_team.getName()+" vs. "+enemy_name);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
