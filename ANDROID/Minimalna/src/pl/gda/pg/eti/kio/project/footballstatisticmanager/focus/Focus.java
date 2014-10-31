package pl.gda.pg.eti.kio.project.footballstatisticmanager.focus;

import java.util.LinkedList;
import java.util.List;

import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Game;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Player;
import pl.gda.pg.eti.kio.project.footballstatisticmanager.entitycalss.Team;

public class Focus {
	static public Team focused_team;
	static public Player focused_player;
	static public Game focused_game;
	static public List<Player> main_players_for_focused_game;
	static public List<Player> backup_players_for_focused_game;
		
	
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
}
