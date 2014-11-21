package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Action;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Defense;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Faul;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Injury;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Swap;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Takeover;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.SQLException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GameActivity extends Activity {

	DatabaseManager dbm = new DatabaseManager(this);
	String enemy_name, time, place, date;
	long ctime;
	int lost_goals, scored_goals;
	boolean play=false;
	Chronometer chronometer;
	List<Action> action_list;
	ArrayAdapter<Player> list_adapter;
	ArrayAdapter<Player> list_adapter_backup;
	TextView score;
	
	int swaped=-1;
	int to_swap=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		lost_goals=scored_goals=0;
		
		refreshPlayerLists();
		
		action_list= new LinkedList<Action>();
		chronometer =(Chronometer) findViewById(R.id.chronometer1);
		score = (TextView)findViewById(R.id.textView1);
		chronometer.setTextSize(50);
		score.setTextSize(50);
		ctime=0;
		Intent input_intent=getIntent();
		Bundle input_bundle = input_intent.getExtras();
		enemy_name=input_bundle.getString("enemy_name");
		place = input_bundle.getString("place");
		date = input_bundle.getString("date");
		time = input_bundle.getString("time");
		this.setTitle(Focus.focused_team.getName()+" vs. "+enemy_name);
	}
	
	private void refreshPlayerLists()
	{
		String[] player_list= new String[Focus.main_players_for_focused_game.size()];
		String[] player_list_backup = new String[Focus.backup_players_for_focused_game.size()];
		for(int i=0;i<Focus.main_players_for_focused_game.size();i++)
				player_list[i]=Focus.main_players_for_focused_game.get(i).getNr()+"  "+Focus.main_players_for_focused_game.get(i).getName()+" "+Focus.main_players_for_focused_game.get(i).getSurname();
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list );
		
		for(int i=0;i<Focus.backup_players_for_focused_game.size();i++)
				player_list_backup[i]=Focus.backup_players_for_focused_game.get(i).getNr()+" "+Focus.backup_players_for_focused_game.get(i).getName()+" "+Focus.backup_players_for_focused_game.get(i).getSurname();
		list_adapter_backup = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list_backup);
		
	}
	
	public void beginGame(View v)
	{
		if(!play)
		{
			chronometer.setBase(SystemClock.elapsedRealtime()+ctime);
			chronometer.start();
			play=true;
		}
	}
	public void endGame(View v)
	{
		
			final Dialog dialog = new Dialog(GameActivity.this);
			dialog.setContentView(R.layout.end_game_dialog);
			dialog.setTitle("koniec gry");
			
			final Button yes =(Button) dialog.findViewById(R.id.button2);
			final Button cancel = (Button) dialog.findViewById(R.id.button1);
			final EditText comment = (EditText) dialog.findViewById(R.id.editText1);
			
			OnClickListener listener = new OnClickListener()
			{
				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
					endOfGame(comment.getText().toString());
					Intent result = new Intent();
					setResult(RESULT_OK, result);
					Focus.game_ended=true;
					finish();
					
				}
				
			};
			OnClickListener listener2 = new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
				}				
			};
			
			yes.setOnClickListener(listener);
			cancel.setOnClickListener(listener2);
			dialog.show();
	}	
	public void pause(View v)
	{
		if(play)
		{
			ctime=chronometer.getBase()-SystemClock.elapsedRealtime();
			play=false;
			Toast.makeText(this, String.valueOf(ctime/-60000+1), Toast.LENGTH_SHORT).show();
			chronometer.stop();
		}
	}
	
	private void endOfGame(String comment)
	{
		int game_id;
		DatabaseManager dbm = new DatabaseManager(this);
		dbm.beginTransaction();
		Game game = new Game(date, place, lost_goals, scored_goals, enemy_name,comment);
		game_id=dbm.addGame(game, Focus.main_players_for_focused_game, Focus.backup_players_for_focused_game, Focus.focused_team.getId());
		game.setId(game_id);
		for(Action action : action_list)
		{
			
			action.setGame_id(game_id);
			try
			{
				action.addToDataBase(dbm);
			}
			catch(SQLException e)
			{
				dbm.rollback();
				Toast.makeText(this, "Nie powiód³ siê zapis do bazy danych", Toast.LENGTH_LONG).show();
				Log.d("badatase error", e.getMessage());
				return;
			}
		}
		dbm.commit();
		dbm.close();
	}
	
	public void enemyGoal(View v)
	{
		this.lost_goals++;
		score.setText(String.valueOf(scored_goals)+":"+String.valueOf(lost_goals));
	}
	
	public void shot(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_shot_dialog);
		dialog.setTitle("podanie");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		final CheckBox box2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
		final CheckBox box3 = (CheckBox) dialog.findViewById(R.id.checkBox3);
		final CheckBox box4 = (CheckBox) dialog.findViewById(R.id.checkBox4);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				String success="missed";
				int idp;
				if(box.isChecked())
				{
					success="goal";
					scored_goals++;
					score.setText(String.valueOf(scored_goals)+":"+String.valueOf(lost_goals));
				}
				if(box2.isChecked())
					success="pole";
				if(box3.isChecked())
					success="save";
				if(box4.isChecked())
					success="missed";
								
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	Shot shot = new Shot(idp, edit.getText().toString(), (int)ctime/-60000+1, success );
	    		action_list.add(shot);
	    		Log.d("shot", idp+" "+edit.getText().toString()+" "+String.valueOf(ctime/-60000+1)+" "+success);
	    		dialog.dismiss();
	        }
		};		
		OnCheckedChangeListener listener_box = new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				box2.setChecked(false);
				box3.setChecked(false);
				box4.setChecked(false);
				
			}
			
		};
		OnCheckedChangeListener listener_box2 = new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				box.setChecked(false);
				box2.setChecked(false);
				box4.setChecked(false);
				
			}
			
		};
		OnCheckedChangeListener listener_box3 = new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				box.setChecked(false);
				box2.setChecked(false);
				box3.setChecked(false);
				
			}
			
		};
		OnCheckedChangeListener listener_box4 = new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				box2.setChecked(false);
				box3.setChecked(false);
				box4.setChecked(false);
				
			}
			
		};
		
		view_Player_List.setOnItemClickListener(listner);
		box.setOnCheckedChangeListener(listener_box);
		box2.setOnCheckedChangeListener(listener_box2);
		box3.setOnCheckedChangeListener(listener_box3);
		box4.setOnCheckedChangeListener(listener_box4);
		dialog.show();
		
	}
	
	public void passing(View v)
	{
		
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_passing_dialog);
		dialog.setTitle("podanie");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);

		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		final CheckBox box2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp, success=0;
				String comm;
				if(box.isChecked())
					success=1;
				else
					success=0;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Passing passing = new Passing(idp, (int)ctime/-60000+1, success, comm);
	        	if(box2.isChecked())
					passing.setAssist(1);
	    		action_list.add(passing);
	    		Log.d("passing", idp+" "+String.valueOf(ctime/-60000+1)+" "+success+" "+comm);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();		
	}
	
	public void faul(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_faul_dialog);
		dialog.setTitle("faul");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		final CheckBox box2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
		final CheckBox box3 = (CheckBox) dialog.findViewById(R.id.checkBox3);
		final CheckBox box4 = (CheckBox) dialog.findViewById(R.id.checkBox4);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				Card card=null;
				Injury injury=null;
				int idpo,idpv;
				if(box.isChecked())
					card = new Card(Focus.main_players_for_focused_game.get(position).getId(),(int)ctime/-60000+1,"yellow",edit.getText().toString());
				if(box4.isChecked())
					card = new Card(Focus.main_players_for_focused_game.get(position).getId(),(int)ctime/-60000+1,"red",edit.getText().toString());
				if(box2.isChecked())
					injury = new Injury(Focus.main_players_for_focused_game.get(position).getId(), (int)ctime/-60000+1, edit.getText().toString());
				if(!box3.isChecked())
				{
					idpo=Focus.main_players_for_focused_game.get(position).getId();
					idpv=0;
				}
				else
				{
					idpv=Focus.main_players_for_focused_game.get(position).getId();
					idpo=0;
				}
				
	        	Faul faul = new Faul(idpv, idpo, (int)ctime/-60000+1, edit.getText().toString(), card, injury);
	    		action_list.add(faul);
	    		if(box.isChecked())
	    			Log.d("faul", idpo+" "+edit.getText().toString()+" "+String.valueOf(ctime/-60000+1)+" "+card.getKind());
	    		if(box2.isChecked())
	    			Log.d("faul", idpo+" "+edit.getText().toString()+" "+String.valueOf(ctime/-60000+1)+" "+String.valueOf(injury.getPlayer_id()));
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();
	}
	
	public void swap(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_swap_dialog);
		dialog.setTitle("zmiana");
		to_swap=-1;
		swaped=-1;
		
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		ListView view_Player_List_backup = (ListView) dialog.findViewById(R.id.listView2);
		view_Player_List_backup.refreshDrawableState();
		view_Player_List_backup.setAdapter(list_adapter_backup);
		
		Button button = (Button) dialog.findViewById(R.id.button1);
		
		OnItemClickListener listner_main = new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				if(to_swap==position)
					to_swap=-1;
				else
					to_swap=position;					
				//refresh_player_lists();
		}			
		};
		
		OnItemClickListener listner_backup = new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				if(swaped==position)
					swaped=-1;
				else
					swaped=position;
				//refresh_player_lists();
			}
			
		};
		
		OnClickListener listner_button = new OnClickListener()
		{

			@Override
			public void onClick(View arg0) {
				if(to_swap==-1 || swaped==-1)
				{
					Log.d("no swap", String.valueOf(to_swap)+" "+String.valueOf(swaped)+" "+String.valueOf(ctime/-60000+1));
					to_swap=-1;
					swaped=-1;
					dialog.dismiss();
					return;
				}
				else
				{
					int ido=Focus.main_players_for_focused_game.get(to_swap).getId();
					int idi=Focus.backup_players_for_focused_game.get(swaped).getId();
					Focus.swaped_players_for_focused_game.add(Focus.main_players_for_focused_game.get(to_swap));
					Focus.removePlayerFromListForGame(Focus.main_players_for_focused_game.get(to_swap).getId(), Focus.main_players_for_focused_game);
					Focus.main_players_for_focused_game.add(Focus.backup_players_for_focused_game.get(swaped));
					Focus.removePlayerFromListForGame(Focus.backup_players_for_focused_game.get(swaped).getId(), Focus.backup_players_for_focused_game);
					Swap swap = new Swap(idi, ido, (int)ctime/-60000+1);
					action_list.add(swap);
					swaped=0;
					to_swap=0;
					Log.d("swap", String.valueOf(swap.getPlayer_in_id())+" "+String.valueOf(swap.getPlayer_out_id())+" "+String.valueOf(ctime/-60000+1));
					dialog.dismiss();
					refreshPlayerLists();
				}

			}

			
		};
		view_Player_List.setOnItemClickListener(listner_main);
		view_Player_List_backup.setOnItemClickListener(listner_backup);
		button.setOnClickListener(listner_button);
		dialog.show();
	}
	
	public void penalty(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_penalty_dialog);
		dialog.setTitle("rzut karny");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp, success;
				String comm;
				Shot shot=null;
				if(box.isChecked())
					success=1;
				else
					success=0;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	
	        	if(success==1)
	        	{
	        		scored_goals++;
	        		score.setText(String.valueOf(scored_goals)+":"+String.valueOf(lost_goals));
	        		shot = new Shot(idp, comm, (int)ctime/-60000+1, "goal");
	        	}
	        	else
	        	{
	        		shot = new Shot(idp, comm, (int)ctime/-60000+1, "saved");
	        	}

	        	shot.setPenalty(1);
	    		action_list.add(shot);
	    		if(success==0)
	    			Log.d("penalty", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(success));
	    		else
	    			Log.d("penalty", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(success)+" "+shot.getSucces());
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();
	}
	
	public void corner(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_corner_dialog);
		dialog.setTitle("rzut ro¿ny");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
				int success;
				if(box.isChecked())
					success=1;
				else
					success=0;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Passing passing = new Passing(idp, (int)ctime/-60000+1,success, comm);
	        	passing.setAssist(success);
	        	passing.setCorner(1);
	    		action_list.add(passing);
	    		Log.d("corner", idp+" "+String.valueOf(ctime/-60000+1)+comm);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();	
	}
	
	public void card(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_card_dialog);
		dialog.setTitle("kartka");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		final CheckBox box2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
		box2.setChecked(true);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				String kind="";
				int idp;
				if(box.isChecked())
				{
					kind="red";
					box2.setChecked(false);
				}
				if(box2.isChecked())
				{
					kind="yellow";
					box.setChecked(false);
				}
				
				
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	Card card = new Card(idp, (int)ctime/-60000+1, edit.getText().toString(), kind );
	    		action_list.add(card);
	    		Log.d("card", idp+" "+edit.getText().toString()+" "+String.valueOf(ctime/-60000+1)+" "+kind);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();
	}
	
	public void takeover(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_takeover_dialog);
		dialog.setTitle("przejêcie pi³ki");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Takeover takeover = new Takeover(idp, (int)ctime/-60000+1, comm);
	    		action_list.add(takeover);
	    		Log.d("takeover", idp+" "+String.valueOf(ctime/-60000+1)+comm);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();
	}
	
	public void defense(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_defense_dialog);
		dialog.setTitle("obrona");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Defense defense = new Defense(idp, (int)ctime/-60000+1, comm);
	    		action_list.add(defense);
	    		Log.d("defense", idp+" "+String.valueOf(ctime/-60000+1)+comm);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();	
	}
	
	public void freekick(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_freekick_dialog);
		dialog.setTitle("rzut wolny");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		final CheckBox box = (CheckBox) dialog.findViewById(R.id.checkBox1);
		final CheckBox box2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp, goal, assist;
				String comm;
				Shot shot=null;
				if(box.isChecked())
				{
					goal=1;
					scored_goals++;
					score.setText(String.valueOf(scored_goals)+":"+String.valueOf(lost_goals));
				}
				else
					goal=0;
				if(box2.isChecked())
					assist=1;
				else
					assist=0;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	
	        	if(goal==1)
	        	{
	        		shot = new Shot(idp, comm,(int)ctime/-60000+1,"goal" );
	        		shot.setFreekick(1);
	        		scored_goals++;
	        		action_list.add(shot);
	        	}
	        	else
	        	{
	        		shot = new Shot(idp, comm,(int)ctime/-60000+1,"goal" );
	        		shot.setFreekick(0);
	        		action_list.add(shot);
	        	}
	        	if(assist==1)
	        	{
	        		Passing passing = new Passing(idp,(int)ctime/-60000+1,0,comm);
	        		passing.setFreekick(1);
	        		passing.setAssist(assist);
	        		action_list.add(passing);
	        	}
	        	
	    		
	    		if(goal==0)
	    			Log.d("freekick", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(goal));
	    		else
	    			Log.d("freekick", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(goal)+" "+shot.getSucces());
	    		dialog.dismiss();
	        }
		};
		OnCheckedChangeListener listener_box1 = new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(box.isChecked())
					box2.setChecked(false);
				
			}

		};
		OnCheckedChangeListener listener_box2 = new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if(box2.isChecked())
					box.setChecked(false);
				
			}

		};
		view_Player_List.setOnItemClickListener(listner);
		box.setOnCheckedChangeListener(listener_box1);
		box2.setOnCheckedChangeListener(listener_box2);
		dialog.show();
	}
	
	public void injury(View v)
	{
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_injury_dialog);
		dialog.setTitle("kontuzja");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Injury injury = new Injury(idp, (int)ctime/-60000+1, comm);
	    		action_list.add(injury);
	    		Log.d("injury", idp+" "+String.valueOf(ctime/-60000+1)+comm);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
