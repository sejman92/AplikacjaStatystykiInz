package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticmanager.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Team;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;

import com.example.footballstatisticmanager.R;
import com.example.footballstatisticmanager.R.layout;
import com.example.footballstatisticmanager.R.menu;

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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TeamActivity extends Activity {

	
	static int id;
	String name;
	DatabaseManager dbm = new DatabaseManager(this);
	List<Player> listPlayer;
	ListView view_Player_List;
	ArrayAdapter<Player> list_adapter;
	
	OnItemClickListener listner = new OnItemClickListener(){
		@Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
        {
        	int idt=listPlayer.get(position).getId();
        	Focus.focused_player=listPlayer.get(position);
        	
        	Intent intent = new Intent(TeamActivity.this,PlayerActivity.class);
        	/*intent.putExtra("id", idt);
        	intent.putExtra("name",listPlayer.get(position).getName() );
        	intent.putExtra("surname",listPlayer.get(position).getSurname() );*/
        	startActivity(intent);
        	//Toast.makeText(getApplicationContext(), "Wybrano gracza o ID = "+String.valueOf(idt), Toast.LENGTH_SHORT).show();
        }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		/*Intent intentInput = getIntent(); 
        Bundle bundleInputData = intentInput.getExtras(); */
        id = Focus.focused_team.getId();//bundleInputData.getInt("id");
        name = Focus.focused_team.getName();//bundleInputData.getString("name");
        this.setTitle(name);
        
        refresh_player_list();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		setContentView(R.layout.activity_team);
		refresh_player_list();
	}
	
	public void gameSetup(View v)
	{
		//Toast.makeText(getApplicationContext(), "zaczynamy gre", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(TeamActivity.this,GameSetupActivity.class);     	
    	startActivity(intent);
	}
	
	public void refresh_player_list()
	{		
		view_Player_List = (ListView) findViewById(R.id.playerlistView);
		listPlayer = dbm.getAllPlayersFromTeam(id);
		dbm.close();
		String[] player_list= new String[listPlayer.size()];
		for(int i=0;i<listPlayer.size();i++)
			player_list[i]=listPlayer.get(i).getNr()+"  "+listPlayer.get(i).getName()+" "+listPlayer.get(i).getSurname();
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,player_list );
		view_Player_List.refreshDrawableState();
		view_Player_List.setAdapter(list_adapter);
		view_Player_List.setOnItemClickListener( listner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id=item.getItemId();
		
		if(id==R.id.delete_team)
		{
			Toast.makeText(getApplicationContext(),getResources().getString(R.string.team)+" "+name+" "+getResources().getString(R.string.team_was_deleted), Toast.LENGTH_SHORT).show();
			dbm.deleteTeam(this.id);
			dbm.close();
			finish();
		}
		if(id==R.id.add_player)
		{
			final Dialog dialog = new Dialog(TeamActivity.this);
			dialog.setContentView(R.layout.add_player_dialog);
			dialog.setTitle(R.string.add_player);
			Button button = (Button) dialog.findViewById(R.id.add_player_dialog_ok_button);
			Button button2 = (Button) dialog.findViewById(R.id.add_player_dialog_cancel_button);
			OnClickListener l = new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					EditText name = (EditText) dialog.findViewById(R.id.add_player_dialog_name_edittext);
					EditText surname = (EditText) dialog.findViewById(R.id.add_player_dialog_surname_edittext);
					EditText number = (EditText) dialog.findViewById(R.id.add_player_dialog_number_edittext);
					EditText role = (EditText) dialog.findViewById(R.id.add_player_dialog_role_edittext);
					
					String sName = name.getText().toString();
					String sSurname = surname.getText().toString();
					String iNumber = number.getText().toString();
					String sRole = role.getText().toString();
					dialog.dismiss();
					dbm.addPlayer(sName, sSurname, Integer.parseInt(iNumber), sRole, TeamActivity.id );
					dbm.close();
					refresh_player_list();
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
