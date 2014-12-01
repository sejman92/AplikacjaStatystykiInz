package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import pl.gda.pg.kio.project.footballstatisticcollector.R.layout;
import pl.gda.pg.kio.project.footballstatisticcollector.R.menu;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreGameActivity extends Activity {

	EditText enemy_name;
	EditText date;
	EditText time;
	EditText place;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pre_game);
		
		enemy_name=(EditText) findViewById(R.id.enemy_name_edittext);
		date=(EditText) findViewById(R.id.date_edittext);
		time = (EditText) findViewById(R.id.time_edittext);
		place= (EditText)findViewById(R.id.place_edittext);
	}
	
	private boolean valid_name(String name)
	{
		if(name!="")
			return true;
		else
			return false;
	}
	private boolean valid_time(String time)
	{
		String times[]=null;
		if(!time.contains(":"))
			return false;
		times = time.split(":",2);
		if(times[1].contains(":"))
			return false;
		int hour=Integer.parseInt(times[0]);
		int minute=Integer.parseInt(times[1]);
		if(times[0]==""||times[1]=="")
			return false;
		if(hour>24&&hour<0 && minute>60 &&minute<0)
			return false;	
		return true;
	}
	@SuppressLint("SimpleDateFormat")
	private boolean valid_date(String date)
	{
		Date dat;
		try{
		dat= new SimpleDateFormat("dd.MM.yyyy").parse(date);
		}catch(ParseException e)
		{
			return false;
		}
		return dat != null;					
	}
	public void begin_game(View v)
	{
		boolean valid;
		String name=enemy_name.getText().toString();
		String place=this.place.getText().toString();
		String date=this.date.getText().toString();
		String time=this.time.getText().toString();
		valid=valid_name(name) && valid_time(time) && valid_date(date) && valid_name(place);
		if(valid)
		{
			
			if(Focus.main_players_for_focused_game.size()!=11)
			{
				Toast.makeText(this, "Proszê wybraæ 11 zawodników do podstawowego skadu", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent = new Intent(PreGameActivity.this,GameActivity.class);
				intent.putExtra("enemy_name",name);
				intent.putExtra("palce", place);
				intent.putExtra("date",date);
				intent.putExtra("time",time);
				startActivityForResult(intent,1);
			}	
		}
		else
			Toast.makeText(this,"z³e dane", Toast.LENGTH_SHORT).show();
	}
	
	protected void onActivityResult(int requestCode, int resultCode,Intent data) 
	{
        if (requestCode == 1) 
        {
            if (resultCode == RESULT_OK) {
            	Focus.clearLists();
                finish();
            }
        }
    }
	
	public void chose_players(View v)
	{
		Intent intent = new Intent(PreGameActivity.this,ChosePlayersForGameActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pre_game, menu);
		return true;
	}

}
