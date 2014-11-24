package pl.gda.pg.eti.kio.project.footballstatisticcollector.focus;

import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Game;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Player;
import pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.Team;


public class Focus {
	static public Team focused_team;
	static public Player focused_player;
	static public Game focused_game;
	static public List<Player> main_players_for_focused_game;
	static public List<Player> backup_players_for_focused_game;
	static public List<Player> swaped_players_for_focused_game;
	//static public String enemy_name_for_new_game;
	static public boolean game_ended=false;
		

	
	static public boolean isInPlayersList(int id,List<Player> list)
	{
		if(list==null)
			return false;
		for(int i=0;i<list.size();i++)
			if(list.get(i).getId()==id )
				return true;
		return false;		
	}
	
	static public boolean removePlayerFromListForGame(int id, List<Player>list)
	{
		for(int i=0;i<list.size();i++)
			if(list.get(i).getId()==id )
				return list.remove(list.get(i));
		return false;	
	}

	static public void clearLists()
	{
		main_players_for_focused_game.clear();
		backup_players_for_focused_game.clear();
		swaped_players_for_focused_game.clear();
	}

	static public Player getFocusedPlayer()
	{
		return focused_player;
	}
}
