package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import pl.gda.pg.kio.project.footballstatisticcollector.R.layout;
import pl.gda.pg.kio.project.footballstatisticcollector.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerStatsActivity extends Activity {

	private TextView played_games,goals,missed_shots,assists,good_passings, bad_passings,penaltys, corners, freekick, fauls, injuries, red_cards, yellow_cards;
	private int goals_i=0, missed_shots_i=0,assists_i=0, good_passings_i=0, bad_passings_i=0, penaltys_i=0, corners_i=0, freekicks_i=0, red_cards_i=0, yellow_cards_i=0;  
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
		injuries= (TextView) findViewById(R.id.textView24);
		red_cards= (TextView) findViewById(R.id.textView20);
		yellow_cards= (TextView) findViewById(R.id.textView18);

		
		played_games.setText(String.valueOf(Focus.focused_player.getGames().size()));
		fauls.setText(String.valueOf(Focus.focused_player.getFauls().size()));
		injuries.setText(String.valueOf(Focus.focused_player.getInjuries().size()));
		
		for(Shot s : Focus.focused_player.getShots())
		{
			if(s.getSucces()=="goal")
				goals_i++;
			else
				missed_shots_i++;
			if(s.getPenalty()==1)
				penaltys_i++;
		}
		
		goals.setText(String.valueOf(goals_i));
		missed_shots.setText(String.valueOf(missed_shots_i));
		penaltys.setText(String.valueOf(penaltys_i));
		
		for(Passing p : Focus.focused_player.getPassings())
		{
			if(p.getAssist()==1)
				assists_i++;
			if(p.getCorner()==1)
				corners_i++;
			if(p.getFreekick()==1)
				freekicks_i++;
			if(p.getSuccess()==1)
				good_passings_i++;
			else
				bad_passings_i++;
		}
		
		assists.setText(String.valueOf(assists_i));
		corners.setText(String.valueOf(corners_i));
		freekick.setText(String.valueOf(freekicks_i));
		good_passings.setText(String.valueOf(good_passings_i));
		bad_passings.setText(String.valueOf(bad_passings_i));
		
		for(Card c : Focus.focused_player.getCards())
		{
			if(c.getKind()=="yellow")
				yellow_cards_i++;
			else
				red_cards_i++;
		}
		
		yellow_cards.setText(String.valueOf(yellow_cards_i));
		red_cards.setText(String.valueOf(red_cards_i));
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
