package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticmanager.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;

import com.example.footballstatisticmanager.R;
import com.example.footballstatisticmanager.R.layout;
import com.example.footballstatisticmanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ChosePlayersForGameActivity extends Activity {

	DatabaseManager dbm = new DatabaseManager(this);
	List<Player> listPlayer;
	ListView view_Player_List;
	ArrayAdapter<Player> list_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_players_for_game);
		//refresh_player_list();
		
	}
	
	OnItemClickListener listner = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
			if(Focus.isInPlayersList(listPlayer.get(position).getId(), Focus.backup_players_for_focused_game))
				Focus.removePlayerFromListForGame(listPlayer.get(position).getId(), Focus.backup_players_for_focused_game);
        	if(Focus.isInPlayersList(listPlayer.get(position).getId(), Focus.main_players_for_focused_game))
        		Focus.removePlayerFromListForGame(listPlayer.get(position).getId(), Focus.main_players_for_focused_game);
			else
				Focus.main_players_for_focused_game.add(listPlayer.get(position));
        	refresh_player_list();
        }
	};
	
	OnItemLongClickListener long_listener = new OnItemLongClickListener(){

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View a,int position, long id) 
		{
			if(Focus.isInPlayersList(listPlayer.get(position).getId(), Focus.main_players_for_focused_game))
        		Focus.removePlayerFromListForGame(listPlayer.get(position).getId(), Focus.main_players_for_focused_game);
			if(Focus.isInPlayersList(listPlayer.get(position).getId(), Focus.backup_players_for_focused_game))
				Focus.removePlayerFromListForGame(listPlayer.get(position).getId(), Focus.backup_players_for_focused_game);
			else
				Focus.backup_players_for_focused_game.add(listPlayer.get(position));
			refresh_player_list();
			return true;
			
		}
		
	};
	
	public void refresh_player_list()
	{		
		view_Player_List = (ListView) findViewById(R.id.playerListViewChosingForGame);
		listPlayer = dbm.getAllPlayersFromTeam(Focus.focused_team.getId());
		dbm.close();
		String[] player_list= new String[listPlayer.size()];
		String chosen="";
		for(int i=0;i<listPlayer.size();i++)
		{
			if(Focus.isInPlayersList(listPlayer.get(i).getId(), Focus.main_players_for_focused_game) )
				chosen=getResources().getString(R.string.basic);
			else
				if(Focus.isInPlayersList(listPlayer.get(i).getId(), Focus.backup_players_for_focused_game) )
					chosen=getResources().getString(R.string.backup);
				else
					chosen="";
			player_list[i]=listPlayer.get(i).getNr()+"  "+listPlayer.get(i).getName()+" "+listPlayer.get(i).getSurname()+"  "+chosen;
		}
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,player_list );
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		view_Player_List.setOnItemClickListener( listner);
		view_Player_List.setOnItemLongClickListener(long_listener);
	}
	

	public void ready(View v)
	{
		this.finish();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_chose_players_for_game);
		refresh_player_list();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chose_players_for_game, menu);
		return true;
	}

}
