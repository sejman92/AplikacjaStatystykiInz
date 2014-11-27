package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import pl.gda.pg.kio.project.footballstatisticcollector.R.layout;
import pl.gda.pg.kio.project.footballstatisticcollector.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChosePlayersForCompareActivity extends Activity {
	
	DatabaseManager dbm = new DatabaseManager(this);
	List<Player> listPlayer;
	ListView view_Player_List;
	ArrayAdapter<Player> list_adapter;
	List<Player> listPlayer2;
	ListView view_Player_List2;
	ArrayAdapter<Player> list_adapter2;
	
	int chosen1=-1,chosen2=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_players_for_compare);
	}
	
	public void refreshPlayersLists()
	{
		view_Player_List = (ListView) findViewById(R.id.listView1);
		listPlayer = dbm.getAllPlayersFromTeam(Focus.focused_team.getId());
		view_Player_List2 = (ListView) findViewById(R.id.listView2);
		listPlayer2 = dbm.getAllPlayersFromTeam(Focus.focused_team.getId());
		dbm.close();

		String[] player_list= new String[listPlayer.size()];
		String[] player_list2= new String[listPlayer.size()];
		for(int i=0;i<listPlayer.size();i++)
		{
			player_list[i]=listPlayer.get(i).getNr()+"  "+listPlayer.get(i).getName()+" "+listPlayer.get(i).getSurname()+"  ";
			player_list2[i]=listPlayer.get(i).getNr()+"  "+listPlayer.get(i).getName()+" "+listPlayer.get(i).getSurname()+"  ";
			if(chosen1==i)
				player_list[i]+=" Wybrany";
			if(chosen2==i)
				player_list2[i]+=" Wybrany";
		}
		
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,player_list );
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		view_Player_List.setOnItemClickListener( listner1);

		list_adapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,player_list2 );
		view_Player_List2.refreshDrawableState();
		view_Player_List2.setAdapter(list_adapter2);
		view_Player_List2.setOnItemClickListener( listner2);
		
	}
	
	OnItemClickListener listner1 = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
			if(position==chosen1)
				chosen1=-1;
			else
				chosen1=position;
        	refreshPlayersLists();
        }
	};
	
	OnItemClickListener listner2 = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
			if(position==chosen1)
				chosen2=-1;
			else
				chosen2=position;
        	refreshPlayersLists();
        }
	};
	
	public void done(View v)
	{
		if(chosen1==chosen2)
			Toast.makeText(this, "Nie mo¿na porónaæ zawodnika z samym sob¹.", Toast.LENGTH_SHORT).show();
		else if(chosen1==-1 || chosen2==-1)
			Toast.makeText(this, "Wybierz drugiego zawodnika do porównania.", Toast.LENGTH_SHORT).show();
		else
		{
			Focus.focused_player_for_compare1=listPlayer.get(chosen1);
			Focus.focused_player_for_compare2=listPlayer.get(chosen2);
			finish();
		}
		
	}

	public void back(View v)
	{
		
		finish();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_chose_players_for_compare);
		refreshPlayersLists();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chose_players_for_compare, menu);
		return true;
	}

}
