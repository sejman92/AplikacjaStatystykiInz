package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CompareGamesActivity extends Activity {
	int game1_id,game2_id;
	Game game1,game2;
	public TextView goals1,goals2,missed_shots1,missed_shots2,good_passings1,good_passings2,bad_passings1,bad_passings2,red1,red2,yellow1,yellow2,fauls1,fauls2,corners1,corners2,penaltys1,penaltys2,freekicks1,freekicks2,assissts1,assissts2,injuries1,injuries2,fauls_on_player1,fauls_on_player2,name1,name2,swaps1,swaps2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		DatabaseManager dbm = new DatabaseManager(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare_games);
		this.setTitle("Porównywarka");
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    game1_id = extras.getInt("choise1");
		    game2_id = extras.getInt("choise2");
		}
		else
		{
			Toast.makeText(this, "wyst¹pi³ b³¹d", Toast.LENGTH_SHORT).show();
			finish();
			Intent result = new Intent();
			setResult(Activity.RESULT_CANCELED, result);
			finish();
		}
		name1 = (TextView)findViewById(R.id.textView1);
		name2 = (TextView)findViewById(R.id.textView2);
		goals1= (TextView)findViewById(R.id.textView3);
		goals2= (TextView)findViewById(R.id.textView29);
		missed_shots1= (TextView)findViewById(R.id.textView4);
		missed_shots2= (TextView)findViewById(R.id.textView30);
		good_passings1= (TextView)findViewById(R.id.textView5);
		good_passings2= (TextView)findViewById(R.id.textView31);
		bad_passings1= (TextView)findViewById(R.id.textView6);
		bad_passings2= (TextView)findViewById(R.id.textView32);
		red1= (TextView)findViewById(R.id.textView8);
		red2= (TextView)findViewById(R.id.textView34);
		yellow1= (TextView)findViewById(R.id.textView7);
		yellow2= (TextView)findViewById(R.id.textView33);
		fauls1= (TextView)findViewById(R.id.textView9);
		fauls2= (TextView)findViewById(R.id.textView35);
		fauls_on_player1 = (TextView)findViewById(R.id.textView42);
		fauls_on_player2 = (TextView)findViewById(R.id.textView44);
		corners1= (TextView)findViewById(R.id.textView12);
		corners2= (TextView)findViewById(R.id.textView38);
		penaltys1= (TextView)findViewById(R.id.textView11);
		penaltys2= (TextView)findViewById(R.id.textView37);
		freekicks1= (TextView)findViewById(R.id.textView13);
		freekicks2= (TextView)findViewById(R.id.textView39);
		assissts1= (TextView)findViewById(R.id.textView14);
		assissts2= (TextView)findViewById(R.id.textView40);
		injuries1= (TextView)findViewById(R.id.textView10);
		injuries2= (TextView)findViewById(R.id.textView36);
		swaps1= (TextView)findViewById(R.id.textView45);
		swaps2= (TextView)findViewById(R.id.textView47);
		
		game1 = dbm.getFullGameStats(game1_id);
		game2 = dbm.getFullGameStats(game2_id);
		
	}
	
	public void back(View v)
	{
		Intent result = new Intent();
		setResult(Activity.RESULT_OK, result);
		finish();	
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		name1.setText(game1.getOponent());
		name2.setText(game2.getOponent());
		goals1.setText(String.valueOf(game1.getGoals()));
		goals2.setText(String.valueOf(game2.getGoals()));
		missed_shots1.setText(String.valueOf(game1.getMissedShots()));
		missed_shots2.setText(String.valueOf(game2.getMissedShots()));
		good_passings1.setText(String.valueOf(game1.getGoodPassings()));
		good_passings2.setText(String.valueOf(game2.getGoodPassings()));
		bad_passings1.setText(String.valueOf(game1.getBadPassings()));
		bad_passings2.setText(String.valueOf(game2.getBadPassings()));
		red1.setText(String.valueOf(game1.getRedCards()));
		red2.setText(String.valueOf(game2.getRedCards()));
		yellow1.setText(String.valueOf(game1.getYellowCards()));
		yellow2.setText(String.valueOf(game2.getYellowCards()));
		fauls1.setText(String.valueOf(game1.getFaulsByPlayer()));
		fauls2.setText(String.valueOf(game2.getFaulsByPlayer()));
		fauls_on_player1.setText(String.valueOf(game1.getFaulsOnPlayer()));
		fauls_on_player2.setText(String.valueOf(game2.getFaulsOnPlayer()));
		corners1.setText(String.valueOf(game1.getCorners()));
		corners2.setText(String.valueOf(game2.getCorners()));
		penaltys1.setText(String.valueOf(game1.getPenaltys()));
		penaltys2.setText(String.valueOf(game2.getPenaltys()));
		freekicks1.setText(String.valueOf(game1.getFreekicks()));
		freekicks2.setText(String.valueOf(game2.getFreekicks()));
		assissts1.setText(String.valueOf(game1.getAssists()));
		assissts2.setText(String.valueOf(game2.getAssists()));
		injuries1.setText(String.valueOf(game1.getInjuries().size()));
		injuries2.setText(String.valueOf(game2.getInjuries().size()));
		swaps1.setText(String.valueOf(game1.getSwaps().size()));
		swaps2.setText(String.valueOf(game2.getSwaps().size()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare_games, menu);
		return true;
	}

}
