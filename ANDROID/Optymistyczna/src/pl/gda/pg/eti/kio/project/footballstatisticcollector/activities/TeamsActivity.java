package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Team;
import pl.gda.pg.kio.project.footballstatisticcollector.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TeamsActivity extends Activity {

	DatabaseManager dbm = new DatabaseManager(this);
	List<Team> listTeam;
	ListView view_Team_List;
	ArrayAdapter<Team> list_adapter;
	
	OnItemClickListener listner = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
			Focus.focused_team=listTeam.get(position);
        	
        	Intent intent = new Intent(TeamsActivity.this,TeamActivity.class);      	
        	startActivity(intent);
        }
	};
	
	@Override	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams);
		
		refresh_team_list();
	}
	
	public void refresh_team_list()
	{		
		view_Team_List = (ListView) findViewById(R.id.listView1);
		listTeam = dbm.getAllTeams();
		dbm.close();
		String[] team_list= new String[listTeam.size()];
		for(int i=0;i<listTeam.size();i++)
			team_list[i]=listTeam.get(i).getName();
		list_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, team_list);
		view_Team_List.refreshDrawableState();
		view_Team_List.setAdapter(list_adapter);
		view_Team_List.setOnItemClickListener( listner);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_teams);
		refresh_team_list();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.teams, menu);
		
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
	
		int id=item.getItemId();
		if(id==R.id.add_team)
		{	
			final Dialog dialog = new Dialog(TeamsActivity.this);
			dialog.setContentView(R.layout.add_team_dialog);
			dialog.setTitle(R.string.add_team_teams_menu);
			Button button = (Button) dialog.findViewById(R.id.button1);
			Button button2 = (Button) dialog.findViewById(R.id.button2);
			OnClickListener l = new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					EditText edit =(EditText) dialog.findViewById(R.id.editText1);
					String text = edit.getText().toString();
					dialog.dismiss();
					dbm.addTeam(text);
					dbm.close();
					refresh_team_list();
				}
				
			};
			OnClickListener l2 = new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					dialog.dismiss();				
				}
				
			};
			button.setOnClickListener(l);
			button2.setOnClickListener(l2);
			dialog.show();		
		}
		return true;
	
	}

}
