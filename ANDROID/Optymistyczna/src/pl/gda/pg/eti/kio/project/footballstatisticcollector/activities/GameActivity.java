package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Action;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Passing;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions.Shot;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GameActivity extends Activity {

	String enemy_name, time, place, date;
	long ctime;
	Chronometer chronometer;
	List<Action> action_list;
	ArrayAdapter<Player> list_adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		String[] player_list= new String[Focus.main_players_for_focused_game.size()];
		for(int i=0;i<Focus.main_players_for_focused_game.size();i++)
			player_list[i]=Focus.main_players_for_focused_game.get(i).getNr()+"  "+Focus.main_players_for_focused_game.get(i).getName()+" "+Focus.main_players_for_focused_game.get(i).getSurname();
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list );
		
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
	
	public void begin_game(View v)
	{
		chronometer.setBase(SystemClock.elapsedRealtime()+ctime);
		chronometer.start();
	}
	public void end_game(View v)
	{
		ctime=chronometer.getBase()-SystemClock.elapsedRealtime();
		chronometer.stop();
		Toast.makeText(this, String.valueOf(ctime) , Toast.LENGTH_SHORT).show();
	}	
	public void pause(View v)
	{
		ctime=chronometer.getBase()-SystemClock.elapsedRealtime();
		Toast.makeText(this, String.valueOf(ctime), Toast.LENGTH_SHORT).show();
		chronometer.stop();
		
	}
	
	public void shot(View v)
	{
		Toast.makeText(this, "shot", Toast.LENGTH_SHORT).show();
		
	}
	
	public void passing(View v)
	{
		
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.new_passing_dialog);
		dialog.setTitle("podanie");
		
		ListView view_Player_List = (ListView) dialog.findViewById(R.id.listView1);
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);

		/*Button button = (Button) dialog.findViewById(R.id.button1);
		Button button2 = (Button) dialog.findViewById(R.id.button2);*/
		final EditText edit = (EditText) dialog.findViewById(R.id.editText1);
		
		OnItemClickListener listner = new OnItemClickListener(){
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	        {
				int idp;
				String comm;
	        	idp=Focus.main_players_for_focused_game.get(position).getId();
	        	comm=edit.getText().toString();
	        	Passing passing = new Passing(idp, (int)ctime, comm);
	    		action_list.add(passing);
	    		dialog.dismiss();
	        }
		};
		view_Player_List.setOnItemClickListener(listner);
		dialog.show();		
	}
	
	public void faul(View v)
	{
		Toast.makeText(this, "faul", Toast.LENGTH_SHORT).show();
	}
	
	public void swap(View v)
	{
		Toast.makeText(this, "swap", Toast.LENGTH_SHORT).show();
	}
	
	public void penalty(View v)
	{
		Toast.makeText(this, "penalty", Toast.LENGTH_SHORT).show();
	}
	
	public void corner(View v)
	{
		Toast.makeText(this, "corner", Toast.LENGTH_SHORT).show();	
	}
	
	public void card(View v)
	{
		Toast.makeText(this, "card", Toast.LENGTH_SHORT).show();
	}
	
	public void takeover(View v)
	{
		Toast.makeText(this, "takeover", Toast.LENGTH_SHORT).show();
	}
	
	public void defense(View v)
	{
		Toast.makeText(this, "defense", Toast.LENGTH_SHORT).show();
	}
	
	public void injury(View v)
	{
		Toast.makeText(this, "injury", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
