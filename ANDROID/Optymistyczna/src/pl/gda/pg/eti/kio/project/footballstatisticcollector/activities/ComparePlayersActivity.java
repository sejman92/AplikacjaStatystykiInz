package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ComparePlayersActivity extends Activity {
	public TextView goals1,goals2,missed_shots1,missed_shots2,good_passings1,good_passings2,bad_passings1,bad_passings2,red1,red2,yellow1,yellow2,fauls1,fauls2,corners1,corners2,penaltys1,penaltys2,freekicks1,freekicks2,assissts1,assissts2,games1,games2, injuries1,injuries2,fauls_on_player1,fauls_on_player2,name1,name2;
	int goals1_i,goals2_i,missed_shots1_i,missed_shots2_i,good_passings1_i,good_passings2_i,bad_passings1_i,bad_passings2_i,red1_i,red2_i,yellow1_i,yellow2_i,fauls1_i,fauls2_i,corners1_i,corners2_i,penaltys1_i,penaltys2_i,freekicks1_i,freekicks2_i,assissts1_i,assissts2_i,games1_i,games2_i,injuries1_i, injuries2_i;
	boolean game_b;
	Game game;
	Player player1, player2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare_players);
		this.setTitle("Porównywarka");
		
		
		name1 = (TextView)findViewById(R.id.dupadupa);
		name1.refreshDrawableState();
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
		games1= (TextView)findViewById(R.id.textView15);
		games2= (TextView)findViewById(R.id.textView41);
		injuries1= (TextView)findViewById(R.id.textView10);
		injuries2= (TextView)findViewById(R.id.textView36);
	}
	
	public void back(View v)
	{
		finish();
	}
	
	public void pickPlayers(View v)
	{
		Intent intent = new Intent(ComparePlayersActivity.this,ChosePlayersForCompareActivity.class);
		startActivity(intent);
	}
	
	public void pickGame(View v)
	{
		if(Focus.focused_player_for_compare1!=null && Focus.focused_player_for_compare2!=null)
		{
			Intent intent = new Intent(ComparePlayersActivity.this,ChoseGameForCompareActivity.class);
			startActivity(intent);
		}
		else
			Toast.makeText(this, "Prosze najpierw wybraæ graczy", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		if(Focus.focused_player_for_compare1!=null && Focus.focused_player_for_compare2!=null)
			if(Focus.focused_game_for_compare==null)
				setValuesForNoGame();
			else
				setValuesForGame();
	}
	
	public void setValuesForNoGame()
	{
		name1.setText(Focus.focused_player_for_compare1.getName()+" "+Focus.focused_player_for_compare1.getSurname());
		name2.setText(Focus.focused_player_for_compare2.getName()+" "+Focus.focused_player_for_compare2.getSurname());
		goals1.setText(String.valueOf(Focus.focused_player_for_compare1.getGoals()));
		goals2.setText(String.valueOf(Focus.focused_player_for_compare2.getGoals()));
		if(Focus.focused_player_for_compare1.getGoals()!=Focus.focused_player_for_compare2.getGoals())
			if(Focus.focused_player_for_compare1.getGoals()>Focus.focused_player_for_compare2.getGoals())
			{
				goals1.setBackgroundColor(Color.GREEN);
				goals2.setBackgroundColor(Color.RED);
			}else
			{
				goals2.setBackgroundColor(Color.GREEN);
				goals1.setBackgroundColor(Color.RED);
			}
		
		missed_shots1.setText(String.valueOf(Focus.focused_player_for_compare1.getMissedShots()));
		missed_shots2.setText(String.valueOf(Focus.focused_player_for_compare2.getMissedShots()));
		if(Focus.focused_player_for_compare1.getMissedShots()!=Focus.focused_player_for_compare2.getMissedShots())
			if(Focus.focused_player_for_compare1.getMissedShots()>Focus.focused_player_for_compare2.getMissedShots())
			{
				missed_shots1.setBackgroundColor(Color.GREEN);
				missed_shots2.setBackgroundColor(Color.RED);
			}else
			{
				missed_shots2.setBackgroundColor(Color.GREEN);
				missed_shots1.setBackgroundColor(Color.RED);
			}
		
		good_passings1.setText(String.valueOf(Focus.focused_player_for_compare1.getGoodPassings()));
		good_passings2.setText(String.valueOf(Focus.focused_player_for_compare2.getGoodPassings()));
		if(Focus.focused_player_for_compare1.getGoodPassings()!=Focus.focused_player_for_compare2.getGoodPassings())
			if(Focus.focused_player_for_compare1.getGoodPassings()>Focus.focused_player_for_compare2.getGoodPassings())
			{
				good_passings1.setBackgroundColor(Color.GREEN);
				good_passings2.setBackgroundColor(Color.RED);
			}else
			{
				good_passings2.setBackgroundColor(Color.GREEN);
				good_passings1.setBackgroundColor(Color.RED);
			}
		
		bad_passings1.setText(String.valueOf(Focus.focused_player_for_compare1.getBadPassings()));
		bad_passings2.setText(String.valueOf(Focus.focused_player_for_compare2.getBadPassings()));
		if(Focus.focused_player_for_compare1.getBadPassings()!=Focus.focused_player_for_compare2.getBadPassings())
			if(Focus.focused_player_for_compare1.getBadPassings()>Focus.focused_player_for_compare2.getBadPassings())
			{
				bad_passings1.setBackgroundColor(Color.GREEN);
				bad_passings2.setBackgroundColor(Color.RED);
			}else
			{
				bad_passings2.setBackgroundColor(Color.GREEN);
				bad_passings1.setBackgroundColor(Color.RED);
			}
		
		red1.setText(String.valueOf(Focus.focused_player_for_compare1.getRedCards()));
		red2.setText(String.valueOf(Focus.focused_player_for_compare2.getRedCards()));
		if(Focus.focused_player_for_compare1.getRedCards()!=Focus.focused_player_for_compare2.getRedCards())
			if(Focus.focused_player_for_compare1.getRedCards()>Focus.focused_player_for_compare2.getRedCards())
			{
				red1.setBackgroundColor(Color.GREEN);
				red2.setBackgroundColor(Color.RED);
			}else
			{
				red2.setBackgroundColor(Color.GREEN);
				red1.setBackgroundColor(Color.RED);
			}
		
		yellow1.setText(String.valueOf(Focus.focused_player_for_compare1.getYellowCards()));
		yellow2.setText(String.valueOf(Focus.focused_player_for_compare2.getYellowCards()));
		if(Focus.focused_player_for_compare1.getYellowCards()!=Focus.focused_player_for_compare2.getYellowCards())
			if(Focus.focused_player_for_compare1.getYellowCards()>Focus.focused_player_for_compare2.getYellowCards())
			{
				yellow1.setBackgroundColor(Color.GREEN);
				yellow2.setBackgroundColor(Color.RED);
			}else
			{
				yellow2.setBackgroundColor(Color.GREEN);
				yellow1.setBackgroundColor(Color.RED);
			}
		
		
		fauls1.setText(String.valueOf(Focus.focused_player_for_compare1.getFaulsByPlayer()));
		fauls2.setText(String.valueOf(Focus.focused_player_for_compare2.getFaulsByPlayer()));
		if(Focus.focused_player_for_compare1.getFaulsByPlayer()!=Focus.focused_player_for_compare2.getFaulsByPlayer())
			if(Focus.focused_player_for_compare1.getFaulsByPlayer()>Focus.focused_player_for_compare2.getFaulsByPlayer())
			{
				fauls1.setBackgroundColor(Color.GREEN);
				fauls2.setBackgroundColor(Color.RED);
			}else
			{
				fauls2.setBackgroundColor(Color.GREEN);
				fauls1.setBackgroundColor(Color.RED);
			}
		
		fauls_on_player1.setText(String.valueOf(Focus.focused_player_for_compare1.getFaulsOnPlayer()));
		fauls_on_player2.setText(String.valueOf(Focus.focused_player_for_compare2.getFaulsOnPlayer()));
		if(Focus.focused_player_for_compare1.getFaulsOnPlayer()!=Focus.focused_player_for_compare2.getFaulsOnPlayer())
			if(Focus.focused_player_for_compare1.getFaulsOnPlayer()>Focus.focused_player_for_compare2.getFaulsOnPlayer())
			{
				fauls_on_player1.setBackgroundColor(Color.GREEN);
				fauls_on_player2.setBackgroundColor(Color.RED);
			}else
			{
				fauls_on_player2.setBackgroundColor(Color.GREEN);
				fauls_on_player1.setBackgroundColor(Color.RED);
			}
		
		corners1.setText(String.valueOf(Focus.focused_player_for_compare1.getCorners()));
		corners2.setText(String.valueOf(Focus.focused_player_for_compare2.getCorners()));
		if(Focus.focused_player_for_compare1.getCorners()!=Focus.focused_player_for_compare2.getCorners())
			if(Focus.focused_player_for_compare1.getCorners()>Focus.focused_player_for_compare2.getCorners())
			{
				corners1.setBackgroundColor(Color.GREEN);
				corners2.setBackgroundColor(Color.RED);
			}else
			{
				corners2.setBackgroundColor(Color.GREEN);
				corners1.setBackgroundColor(Color.RED);
			}
		
		penaltys1.setText(String.valueOf(Focus.focused_player_for_compare1.getPenaltys()));
		penaltys2.setText(String.valueOf(Focus.focused_player_for_compare2.getPenaltys()));
		if(Focus.focused_player_for_compare1.getPenaltys()!=Focus.focused_player_for_compare2.getPenaltys())
			if(Focus.focused_player_for_compare1.getPenaltys()>Focus.focused_player_for_compare2.getPenaltys())
			{
				penaltys1.setBackgroundColor(Color.GREEN);
				penaltys2.setBackgroundColor(Color.RED);
			}else
			{
				penaltys2.setBackgroundColor(Color.GREEN);
				penaltys1.setBackgroundColor(Color.RED);
			}
		
		freekicks1.setText(String.valueOf(Focus.focused_player_for_compare1.getFreekicks()));
		freekicks2.setText(String.valueOf(Focus.focused_player_for_compare2.getFreekicks()));
		if(Focus.focused_player_for_compare1.getFreekicks()!=Focus.focused_player_for_compare2.getFreekicks())
			if(Focus.focused_player_for_compare1.getFreekicks()>Focus.focused_player_for_compare2.getFreekicks())
			{
				freekicks1.setBackgroundColor(Color.GREEN);
				freekicks2.setBackgroundColor(Color.RED);
			}else
			{
				freekicks2.setBackgroundColor(Color.GREEN);
				freekicks1.setBackgroundColor(Color.RED);
			}
		
		assissts1.setText(String.valueOf(Focus.focused_player_for_compare1.getAssists()));
		assissts2.setText(String.valueOf(Focus.focused_player_for_compare2.getAssists()));
		if(Focus.focused_player_for_compare1.getAssists()!=Focus.focused_player_for_compare2.getAssists())
			if(Focus.focused_player_for_compare1.getAssists()>Focus.focused_player_for_compare2.getAssists())
			{
				assissts1.setBackgroundColor(Color.GREEN);
				assissts2.setBackgroundColor(Color.RED);
			}else
			{
				assissts2.setBackgroundColor(Color.GREEN);
				assissts1.setBackgroundColor(Color.RED);
			}
		
		games1.setText(String.valueOf(Focus.focused_player_for_compare1.getGames().size()));
		games2.setText(String.valueOf(Focus.focused_player_for_compare2.getGames().size()));
		if(Focus.focused_player_for_compare1.getGames().size()!=Focus.focused_player_for_compare2.getGames().size())
			if(Focus.focused_player_for_compare1.getGames().size()>Focus.focused_player_for_compare2.getGames().size())
			{
				games1.setBackgroundColor(Color.GREEN);
				games2.setBackgroundColor(Color.RED);
			}else
			{
				games2.setBackgroundColor(Color.GREEN);
				games1.setBackgroundColor(Color.RED);
			}
		
		injuries1.setText(String.valueOf(Focus.focused_player_for_compare1.getInjuries().size()));
		injuries2.setText(String.valueOf(Focus.focused_player_for_compare2.getInjuries().size()));
		if(Focus.focused_player_for_compare1.getInjuries().size()!=Focus.focused_player_for_compare2.getInjuries().size())
			if(Focus.focused_player_for_compare1.getInjuries().size()>Focus.focused_player_for_compare2.getInjuries().size())
			{
				injuries1.setBackgroundColor(Color.GREEN);
				injuries2.setBackgroundColor(Color.RED);
			}else
			{
				injuries2.setBackgroundColor(Color.GREEN);
				injuries1.setBackgroundColor(Color.RED);
			}
		
	}
	
	
	public void setValuesForGame()
	{
		name1.setText(Focus.focused_player_for_compare1.getName()+" "+Focus.focused_player_for_compare1.getSurname());
		name2.setText(Focus.focused_player_for_compare2.getName()+" "+Focus.focused_player_for_compare2.getSurname());
		name1.setTextSize(20);
		name2.setTextSize(20);
		
		goals1.setText(String.valueOf(Focus.focused_player_for_compare1.getGoalsForGame(Focus.focused_game_for_compare.getId())));
		goals2.setText(String.valueOf(Focus.focused_player_for_compare2.getGoalsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getGoalsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getGoalsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getGoalsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getGoalsForGame(Focus.focused_game_for_compare.getId()))
			{
				goals1.setBackgroundColor(Color.GREEN);
				goals2.setBackgroundColor(Color.RED);
			}else
			{
				goals2.setBackgroundColor(Color.GREEN);
				goals1.setBackgroundColor(Color.RED);
			}
		
		missed_shots1.setText(String.valueOf(Focus.focused_player_for_compare1.getMissedShotsForGame(Focus.focused_game_for_compare.getId())));
		missed_shots2.setText(String.valueOf(Focus.focused_player_for_compare2.getMissedShotsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getMissedShotsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getMissedShotsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getMissedShotsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getMissedShotsForGame(Focus.focused_game_for_compare.getId()))
			{
				missed_shots1.setBackgroundColor(Color.GREEN);
				missed_shots2.setBackgroundColor(Color.RED);
			}else
			{
				missed_shots2.setBackgroundColor(Color.GREEN);
				missed_shots1.setBackgroundColor(Color.RED);
			}
		
		good_passings1.setText(String.valueOf(Focus.focused_player_for_compare1.getGoodPassingsForGame(Focus.focused_game_for_compare.getId())));
		good_passings2.setText(String.valueOf(Focus.focused_player_for_compare2.getGoodPassingsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getGoodPassingsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getGoodPassingsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getGoodPassingsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getGoodPassingsForGame(Focus.focused_game_for_compare.getId()))
			{
				good_passings1.setBackgroundColor(Color.GREEN);
				good_passings2.setBackgroundColor(Color.RED);
			}else
			{
				good_passings2.setBackgroundColor(Color.GREEN);
				good_passings1.setBackgroundColor(Color.RED);
			}
		
		bad_passings1.setText(String.valueOf(Focus.focused_player_for_compare1.getBadPassingsForGame(Focus.focused_game_for_compare.getId())));
		bad_passings2.setText(String.valueOf(Focus.focused_player_for_compare2.getBadPassingsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getBadPassingsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getBadPassingsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getBadPassingsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getBadPassingsForGame(Focus.focused_game_for_compare.getId()))
			{
				bad_passings1.setBackgroundColor(Color.GREEN);
				bad_passings2.setBackgroundColor(Color.RED);
			}else
			{
				bad_passings2.setBackgroundColor(Color.GREEN);
				bad_passings1.setBackgroundColor(Color.RED);
			}
		
		red1.setText(String.valueOf(Focus.focused_player_for_compare1.getRedCardsForGame(Focus.focused_game_for_compare.getId())));
		red2.setText(String.valueOf(Focus.focused_player_for_compare2.getRedCardsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getRedCardsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getRedCardsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getRedCardsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getRedCardsForGame(Focus.focused_game_for_compare.getId()))
			{
				red1.setBackgroundColor(Color.GREEN);
				red2.setBackgroundColor(Color.RED);
			}else
			{
				red2.setBackgroundColor(Color.GREEN);
				red1.setBackgroundColor(Color.RED);
			}
		
		yellow1.setText(String.valueOf(Focus.focused_player_for_compare1.getYellowCardsForGame(Focus.focused_game_for_compare.getId())));
		yellow2.setText(String.valueOf(Focus.focused_player_for_compare2.getYellowCardsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getYellowCardsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getYellowCardsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getYellowCardsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getYellowCardsForGame(Focus.focused_game_for_compare.getId()))
			{
				yellow1.setBackgroundColor(Color.GREEN);
				yellow2.setBackgroundColor(Color.RED);
			}else
			{
				yellow2.setBackgroundColor(Color.GREEN);
				yellow1.setBackgroundColor(Color.RED);
			}
		
		fauls1.setText(String.valueOf(Focus.focused_player_for_compare1.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId())));
		fauls2.setText(String.valueOf(Focus.focused_player_for_compare2.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getFaulsByPlayerForGame(Focus.focused_game_for_compare.getId()))
			{
				fauls1.setBackgroundColor(Color.GREEN);
				fauls2.setBackgroundColor(Color.RED);
			}else
			{
				fauls2.setBackgroundColor(Color.GREEN);
				fauls1.setBackgroundColor(Color.RED);
			}
		
		fauls_on_player1.setText(String.valueOf(Focus.focused_player_for_compare1.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId())));
		fauls_on_player2.setText(String.valueOf(Focus.focused_player_for_compare2.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getFaulsOnPlayerForGame(Focus.focused_game_for_compare.getId()))
			{
				fauls_on_player1.setBackgroundColor(Color.GREEN);
				fauls_on_player2.setBackgroundColor(Color.RED);
			}else
			{
				fauls_on_player2.setBackgroundColor(Color.GREEN);
				fauls_on_player1.setBackgroundColor(Color.RED);
			}
		
		corners1.setText(String.valueOf(Focus.focused_player_for_compare1.getCornersForGame(Focus.focused_game_for_compare.getId())));
		corners2.setText(String.valueOf(Focus.focused_player_for_compare2.getCornersForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getCornersForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getCornersForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getCornersForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getCornersForGame(Focus.focused_game_for_compare.getId()))
			{
				corners1.setBackgroundColor(Color.GREEN);
				corners2.setBackgroundColor(Color.RED);
			}else
			{
				corners2.setBackgroundColor(Color.GREEN);
				corners1.setBackgroundColor(Color.RED);
			}
		
		penaltys1.setText(String.valueOf(Focus.focused_player_for_compare1.getPenaltysForGame(Focus.focused_game_for_compare.getId())));
		penaltys2.setText(String.valueOf(Focus.focused_player_for_compare2.getPenaltysForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getPenaltysForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getPenaltysForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getPenaltysForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getPenaltysForGame(Focus.focused_game_for_compare.getId()))
			{
				penaltys1.setBackgroundColor(Color.GREEN);
				penaltys2.setBackgroundColor(Color.RED);
			}else
			{
				penaltys2.setBackgroundColor(Color.GREEN);
				penaltys1.setBackgroundColor(Color.RED);
			}
		
		freekicks1.setText(String.valueOf(Focus.focused_player_for_compare1.getFreekicksForGame(Focus.focused_game_for_compare.getId())));
		freekicks2.setText(String.valueOf(Focus.focused_player_for_compare2.getFreekicksForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getFreekicksForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getFreekicksForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getFreekicksForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getFreekicksForGame(Focus.focused_game_for_compare.getId()))
			{
				freekicks1.setBackgroundColor(Color.GREEN);
				freekicks2.setBackgroundColor(Color.RED);
			}else
			{
				freekicks2.setBackgroundColor(Color.GREEN);
				freekicks1.setBackgroundColor(Color.RED);
			}
		
		assissts1.setText(String.valueOf(Focus.focused_player_for_compare1.getAssistsForGame(Focus.focused_game_for_compare.getId())));
		assissts2.setText(String.valueOf(Focus.focused_player_for_compare2.getAssistsForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getAssistsForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getAssistsForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getAssistsForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getAssistsForGame(Focus.focused_game_for_compare.getId()))
			{
				assissts1.setBackgroundColor(Color.GREEN);
				assissts2.setBackgroundColor(Color.RED);
			}else
			{
				assissts2.setBackgroundColor(Color.GREEN);
				assissts1.setBackgroundColor(Color.RED);
			}
		
		games1.setText(String.valueOf(Focus.focused_game_for_compare.getDate()));
		games2.setText(String.valueOf(Focus.focused_game_for_compare.getDate()));
		
		injuries1.setText(String.valueOf(Focus.focused_player_for_compare1.getInjuriesForGame(Focus.focused_game_for_compare.getId())));
		injuries2.setText(String.valueOf(Focus.focused_player_for_compare2.getInjuriesForGame(Focus.focused_game_for_compare.getId())));
		if(Focus.focused_player_for_compare1.getInjuriesForGame(Focus.focused_game_for_compare.getId()) != Focus.focused_player_for_compare2.getInjuriesForGame(Focus.focused_game_for_compare.getId()))
			if(Focus.focused_player_for_compare1.getInjuriesForGame(Focus.focused_game_for_compare.getId()) > Focus.focused_player_for_compare2.getInjuriesForGame(Focus.focused_game_for_compare.getId()))
			{
				injuries1.setBackgroundColor(Color.GREEN);
				injuries2.setBackgroundColor(Color.RED);
			}else
			{
				injuries2.setBackgroundColor(Color.GREEN);
				injuries1.setBackgroundColor(Color.RED);
			}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compare_players, menu);
		return true;
	}

}
