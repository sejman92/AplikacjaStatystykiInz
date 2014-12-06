package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PlayerStatsActivity extends Activity {

	private TextView played_games,goals,missed_shots,assists,good_passings, bad_passings,penaltys, corners, freekick, fauls, injuries, red_cards, yellow_cards,fauls_on_player,defenses,takeovers;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_stats);
		String name = Focus.focused_player.getName();
		String surname = Focus.focused_player.getSurname();
		this.setTitle(name+" "+surname);
		played_games= (TextView) findViewById(R.id.textView2);
		goals= (TextView) findViewById(R.id.textView4);
		missed_shots= (TextView) findViewById(R.id.textView26);
		assists= (TextView) findViewById(R.id.textView6);
		good_passings= (TextView) findViewById(R.id.textView8);
		bad_passings= (TextView) findViewById(R.id.textView10);
		penaltys= (TextView) findViewById(R.id.textView12);
		corners= (TextView) findViewById(R.id.textView16);
		freekick= (TextView) findViewById(R.id.textView14);
		fauls= (TextView) findViewById(R.id.textView22);
		fauls_on_player= (TextView)findViewById(R.id.textView28);
		injuries= (TextView) findViewById(R.id.textView24);
		red_cards= (TextView) findViewById(R.id.textView20);
		yellow_cards= (TextView) findViewById(R.id.textView18);
		takeovers=(TextView) findViewById(R.id.textView30);
		defenses=(TextView) findViewById(R.id.textView32);
		
		played_games.setText(String.valueOf(Focus.focused_player.getGames().size()));
		fauls.setText(String.valueOf(Focus.focused_player.getFaulsByPlayer()));
		fauls_on_player.setText(String.valueOf(Focus.focused_player.getFaulsOnPlayer()));
		injuries.setText(String.valueOf(Focus.focused_player.getInjuries().size()));
		

		
		goals.setText(String.valueOf(Focus.focused_player.getGoals()));
		missed_shots.setText(String.valueOf(Focus.focused_player.getMissedShots()));
		penaltys.setText(String.valueOf(Focus.focused_player.getPenaltys()));		
		assists.setText(String.valueOf(Focus.focused_player.getAssists()));
		corners.setText(String.valueOf(Focus.focused_player.getCorners()));
		freekick.setText(String.valueOf(Focus.focused_player.getFreekicks()));
		good_passings.setText(String.valueOf(Focus.focused_player.getGoodPassings()));
		bad_passings.setText(String.valueOf(Focus.focused_player.getBadPassings()));
		yellow_cards.setText(String.valueOf(Focus.focused_player.getYellowCards()));
		red_cards.setText(String.valueOf(Focus.focused_player.getRedCards()));
		defenses.setText(String.valueOf(Focus.focused_player.getDefense().size()));
		takeovers.setText(String.valueOf(Focus.focused_player.getTakeovers().size()));
	}
	
	public void backButton(View v)
	{
		finish();
	}
	
	public void editPlayer(View v)
	{
		Intent intent = new Intent(PlayerStatsActivity.this,PlayerActivity.class);
    	startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_stats, menu);
		return true;
	}

}
