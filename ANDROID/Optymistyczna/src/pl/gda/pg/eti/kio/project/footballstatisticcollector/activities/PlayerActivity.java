package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerActivity extends Activity {

	int id, number;
	String name, surname, role;
	DatabaseManager dbm = new DatabaseManager(this);
	EditText nameet, surnameet, roleet, numberet;
	Player player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
        id =Focus.focused_player.getId(); 
        name =Focus.focused_player.getName(); 
        surname =Focus.focused_player.getSurname();
        role= Focus.focused_player.getRole();
        number = Focus.focused_player.getNr();
        
        nameet=(EditText) findViewById(R.id.editText1);
        surnameet=(EditText) findViewById(R.id.editText2);
        numberet=(EditText) findViewById(R.id.editText3);
        roleet=(EditText) findViewById(R.id.editText4);
        
        nameet.setText(name);
        surnameet.setText(surname);
        numberet.setText(String.valueOf(number));
        roleet.setText(role);
        
        this.setTitle(name+" "+surname);
	}
	
	public void back(View v)
	{
		finish();
	}
	
	public void update(View v)
	{
		name=nameet.getText().toString();
		surname=surnameet.getText().toString();
		role=roleet.getText().toString();
		number=Integer.parseInt(numberet.getText().toString());
		dbm.updatePlayer(id, name, surname, role, number);
		dbm.close();
		Toast.makeText(this, "dane zawodnika zaktualizowane", Toast.LENGTH_SHORT).show();
		finish();
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
