package pl.gda.pg.eti.kio.project.footballstatisticcollector.activities;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.focus.Focus;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.kio.project.footballstatisticcollector.R;
import pl.gda.pg.kio.project.footballstatisticcollector.R.layout;
import pl.gda.pg.kio.project.footballstatisticcollector.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CommentsForPlayerActivity extends Activity {
	
	Button button;
	ListView view_Comment_List;
	ArrayAdapter<Player> list_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments_for_player);
		setTitle("Komentarze do akcji "+Focus.focused_player.getName()+" "+Focus.focused_player.getSurname());
		view_Comment_List = (ListView) findViewById(R.id.listView1);
		refresh_comment_list();
	}
	
	public void back(View v)
	{
		finish();
	}
	
	public void refresh_comment_list()
	{		
		String[] comment_list= new String[Focus.focused_player.getCommentsForActions().size()];
		for(int i=0;i<Focus.focused_player.getCommentsForActions().size();i++)
			comment_list[i]=Focus.focused_player.getCommentsForActions().get(i);
		list_adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,comment_list );
		view_Comment_List.refreshDrawableState();
		view_Comment_List.setAdapter(list_adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comments_for_player, menu);
		return true;
	}

}
