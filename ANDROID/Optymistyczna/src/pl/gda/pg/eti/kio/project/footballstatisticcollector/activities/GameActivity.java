package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Action;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Card;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Corner;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Defense;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Faul;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Freekick;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Injury;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Penalty;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Swap;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Takeover;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GameActivity extends Activity {

	String enemy_name, time, place, date;
	long ctime;
	int lost_goals, scored_goals;
	boolean play=false;
	Chronometer chronometer;
	List<Action> action_list;
	ArrayAdapter<Player> list_adapter;
	ArrayAdapter<Player> list_adapter_backup;
	
	int swaped=-1;
	int to_swap=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		lost_goals=scored_goals=0;
		
		refresh_player_lists();
		
		action_list= new LinkedList<Action>();
		chronometer =(Chronometer) findViewById(R.id.chronometer1);
		chronometer.setTextSize(50);
		ctime=0;
		Intent input_intent=getIntent();
		Bundle input_bundle = input_intent.getExtras();
		enemy_name=input_bundle.getString("enemy_name");
		place = input_bundle.getString("place");
		date = input_bundle.getString("date");
		time = input_bundle.getString("time");
		this.setTitle(Focus.focused_team.getName()+" vs. "+enemy_name);
	}
	
	private void refresh_player_lists()
	{
		String[] player_list= new String[Focus.main_players_for_focused_game.size()];
		String[] player_list_backup = new String[Focus.backup_players_for_focused_game.size()];
		for(int i=0;i<Focus.main_players_for_focused_game.size();i++)
		{
			/*if(to_swap!=-1)
				player_list[i]=Focus.main_players_for_focused_game.get(i).getNr()+"  "+Focus.main_players_for_focused_game.get(i).getName()+" "+Focus.main_players_for_focused_game.get(i).getSurname()+" wybrany";
			else*/
				player_list[i]=Focus.main_players_for_focused_game.get(i).getNr()+"  "+Focus.main_players_for_focused_game.get(i).getName()+" "+Focus.main_players_for_focused_game.get(i).getSurname();
		}
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list );
		
		for(int i=0;i<Focus.backup_players_for_focused_game.size();i++)
		{
			/*if(swaped!=-1)
				player_list_backup[i]=Focus.backup_players_for_focused_game.get(i).getNr()+" "+Focus.backup_players_for_focused_game.get(i).getName()+" "+Focus.backup_players_for_focused_game.get(i).getSurname()+" wybrany";
			else*/
				player_list_backup[i]=Focus.backup_players_for_focused_game.get(i).getNr()+" "+Focus.backup_players_for_focused_game.get(i).getName()+" "+Focus.backup_players_for_focused_game.get(i).getSurname();
		}
		list_adapter_backup = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list_backup);
		
	}
	
	public void begin_game(View v)
	{
		if(!play)
		{
			chronometer.setBase(SystemClock.elapsedRealtime()+ctime);
			chronometer.start();
			play=true;
		}
	}
	public void end_game(View v)
	{
		
			ctime=chronometer.getBase()-SystemClock.elapsedRealtime();
			chronometer.stop();
			play=false;
			Toast.makeText(this, String.valueOf(ctime/-60000+1) , Toast.LENGTH_SHORT).show();
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
				String success="pudlo";
				int idp;
				if(box.isChecked())
				{
					success="goal";
					box2.setChecked(false);
					box3.setChecked(false);
					box4.setChecked(false);
					scored_goals++;
				}
				if(box2.isChecked())
				{
					success="post";
					box.setChecked(false);
					box3.setChecked(false);
					box4.setChecked(false);
				}
				if(box3.isChecked())
				{
					success="saved";
					box.setChecked(false);
					box2.setChecked(false);
					box4.setChecked(false);
				}
				if(box4.isChecked())
				{
					success="miss";
					box.setChecked(false);
					box3.setChecked(false);
					box2.setChecked(false);
				}
				
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	Shot shot = new Shot(idp, edit.getText().toString(), (int)ctime/-60000+1, success );
	    		action_list.add(shot);
	    		Log.d("shot", idp+" "+edit.getText().toString()+" "+String.valueOf(ctime/-60000+1)+" "+success);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
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
				{
					card = new Card(Focus.main_players_for_focused_game.get(position).getId(),(int)ctime/-60000+1,"yellow",edit.getText().toString());
					box2.setChecked(false);
					box4.setChecked(false);
				}
				if(box4.isChecked())
				{
					card = new Card(Focus.main_players_for_focused_game.get(position).getId(),(int)ctime/-60000+1,"red",edit.getText().toString());
					box2.setChecked(false);
					box.setChecked(false);
				}
				if(box2.isChecked())
				{
					injury = new Injury(Focus.main_players_for_focused_game.get(position).getId(), (int)ctime/-60000+1, edit.getText().toString());
					box.setChecked(false);
				}
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
					refresh_player_lists();
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
	        		shot = new Shot(idp, comm, (int)ctime/-60000+1, "goal");
	        		scored_goals++;
	        	}
	        	Penalty penalty = new Penalty(idp, (int)ctime/-60000+1, comm, success,shot);
	    		action_list.add(penalty);
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
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Corner corner = new Corner(idp, (int)ctime/-60000+1, comm);
	    		action_list.add(corner);
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
	    		Log.d("passing", idp+" "+String.valueOf(ctime/-60000+1)+comm);
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
	        		shot = new Shot(idp, comm, (int)ctime/-60000+1, "goal");
	        		scored_goals++;
	        	}
	        	Freekick freekick = new Freekick(idp, (int)ctime/-60000+1, comm, success,shot);
	    		action_list.add(freekick);
	    		if(success==0)
	    			Log.d("freekick", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(success));
	    		else
	    			Log.d("freekick", idp+" "+String.valueOf(ctime/-60000+1)+comm+" "+String.valueOf(success)+" "+shot.getSucces());
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
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
