package pl.gda.pg.eti.kio.project.footballstatisticmanager.activities;

import pl.gda.pg.eti.kio.project.footballstatisticmanager.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.focus.Focus;

import com.example.footballstatisticmanager.R;
import com.example.footballstatisticmanager.R.layout;
import com.example.footballstatisticmanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PlayerActivity extends Activity {

	int id;
	String name, surname;
	DatabaseManager dbm = new DatabaseManager(this);
	Player player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		/*Intent intentInput = getIntent(); 
        Bundle bundleInputData = intentInput.getExtras();*/ 
        id =Focus.focused_player.getId(); //bundleInputData.getInt("id");
        name =Focus.focused_player.getName(); //bundleInputData.getString("name");
        surname =Focus.focused_player.getSurname(); //bundleInputData.getString("surname");
        this.setTitle(name+" "+surname);
//		player=dbm.getPlayer(id);
		//if(player!=null)
			//Toast.makeText(getApplicationContext(), "mamy gracza", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id=item.getItemId();
		
		if(id==R.id.delete_player)
		{
			Toast.makeText(getApplicationContext(),getResources().getString(R.string.player)+" "+name+" "+surname+" "+getResources().getString(R.string.player_was_deleted), Toast.LENGTH_SHORT).show();
			dbm.deletePlayerById(this.id);
			dbm.close();
			finish();
		}
		return true;
	}

}
