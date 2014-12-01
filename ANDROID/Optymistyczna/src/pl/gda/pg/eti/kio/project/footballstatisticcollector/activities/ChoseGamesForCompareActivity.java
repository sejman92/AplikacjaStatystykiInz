package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChoseGamesForCompareActivity extends Activity {
	
	private  DatabaseManager dbm = new DatabaseManager(this);
	private List<Game> listGame;
	private ListView view_Game_List;
	private ArrayAdapter<Game> list_adapter;
	private List<Integer>choises;
	private int choise=-1,choise2=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose_games_for_compare);
		choises= new LinkedList<Integer>();
		refreshGameList();
		setTitle("Mecze do porównania");
	}

	public void refreshGameList()
	{		
		view_Game_List = (ListView) findViewById(R.id.listView1);
		listGame = dbm.getGamesForTeam(Focus.focused_team.getId());
		dbm.close();
		String[] game_list= new String[listGame.size()];
		for(int i=0;i<listGame.size();i++)
		{
			
			game_list[i]=listGame.get(i).getDate()+" "+listGame.get(i).getOponent();
			if(choises.contains(Integer.valueOf(i)))
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
        	if(choises.contains(Integer.valueOf(position)))
        		choises.remove(Integer.valueOf(position));
        	else if(choises.size()<2)
        		choises.add(Integer.valueOf(position));
        	refreshGameList();
        }
	};
	
	public void back(View v)
	{
		finish();
	}
	
	public void done(View v)
	{
		if(choises.size()!=2)
		{
			Toast.makeText(this, "Prosze wybraæ dwa mecze do porównania", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent(ChoseGamesForCompareActivity.this,CompareGamesActivity.class);
		intent.putExtra("choise1", listGame.get(choises.get(0)).getId());
		intent.putExtra("choise2", listGame.get(choises.get(1)).getId());
		startActivityForResult(intent,2);
	}
	protected void onActivityResult(int requestCode, int resultCode,Intent data) 
	{
        if (requestCode == 2) 
        {
            if (resultCode == RESULT_CANCELED) {
                finish();
            }
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chose_games_for_compare, menu);
		return true;
	}

}
