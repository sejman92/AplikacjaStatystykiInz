package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities.actions;

import pl.gda.pg.eti.kio.project.footballstatisticcollector.database.DatabaseManager;

public abstract class Action {

	public abstract void setGame_id(int game_id);
	public abstract int addToDataBase(DatabaseManager dbm);
}
