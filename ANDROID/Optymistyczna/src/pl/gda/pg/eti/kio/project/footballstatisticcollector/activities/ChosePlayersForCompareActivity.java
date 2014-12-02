package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;
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
	ListView view_Player_List;
	ArrayAdapter<Player> list_adapter;
	
	private List<Integer>choises;
	//private int choise=-1,choise2=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_players_for_compare);
		choises= new LinkedList<Integer>();
		setTitle("Wybór zawodników");
	}
	
	public void refreshPlayersLists()
	{
		view_Player_List = (ListView) findViewById(R.id.listView1);
		dbm.close();

		String[] player_list= new String[Focus.players_from_focused_team.size()];
		for(int i=0;i<Focus.players_from_focused_team.size();i++)
		{
			player_list[i]=Focus.players_from_focused_team.get(i).getNr()+"  "+Focus.players_from_focused_team.get(i).getName()+" "+Focus.players_from_focused_team.get(i).getSurname()+"  ";
			if(choises.contains(Integer.valueOf(i)))
				player_list[i]+=" wybrany";

		}
		
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,player_list );
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		view_Player_List.setOnItemClickListener( listner1);

		
	}
	
	OnItemClickListener listner1 = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
			if(choises.contains(Integer.valueOf(position)))
        		choises.remove(Integer.valueOf(position));
        	else if(choises.size()<2)
        		choises.add(Integer.valueOf(position));
        	refreshPlayersLists();
        }
	};
		
	public void done(View v)
	{
		if(choises.size()!=2)
			Toast.makeText(this, "Prosze wybraæ dwóch zawodnikó do porónania", Toast.LENGTH_SHORT).show();
		else
		{
			Focus.focused_player_for_compare1=Focus.players_from_focused_team.get(choises.get(0));
			Focus.focused_player_for_compare2=Focus.players_from_focused_team.get(choises.get(1));
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
