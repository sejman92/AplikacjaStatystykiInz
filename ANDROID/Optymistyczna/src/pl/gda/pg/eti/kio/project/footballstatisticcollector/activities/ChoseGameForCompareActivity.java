package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import pl.gda.pg.kio.project.footballstatisticcollector.R.layout;
import pl.gda.pg.kio.project.footballstatisticcollector.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ChoseGameForCompareActivity extends Activity {

	private  DatabaseManager dbm = new DatabaseManager(this);
	private List<Game> listGame;
	private ListView view_Game_List;
	private ArrayAdapter<Game> list_adapter;
	private int choise=-1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_game_for_compare);
		refreshGameList();
		setTitle("Mecz do porównania");
	}

	public void refreshGameList()
	{		
		view_Game_List = (ListView) findViewById(R.id.listView1);
		listGame = dbm.getGamesForPlayers(Focus.focused_player_for_compare1.getId(), Focus.focused_player_for_compare2.getId());
		dbm.close();
		String[] game_list= new String[listGame.size()];
		for(int i=0;i<listGame.size();i++)
		{
			
			game_list[i]=listGame.get(i).getDate()+" "+listGame.get(i).getOponent();
			if(i==choise)
				game_list[i]+=" wybrano";
		}
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,game_list );
		view_Game_List.refreshDrawableState();
		view_Game_List.setAdapter(list_adapter);
		view_Game_List.setOnItemClickListener(listner);
	}
	
	OnItemClickListener listner = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
        	if(choise==position)
        		choise=-1;
        	else
        		choise=position;
        	refreshGameList();
        }
	};
	
	public void done(View v)
	{
		if(choise==-1)
			Focus.focused_game_for_compare=null;
		else
			Focus.focused_game_for_compare=listGame.get(choise);
		finish();
	}
	
	public void back(View v)
	{
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chose_game_for_compare, menu);
		return true;
	}

}
