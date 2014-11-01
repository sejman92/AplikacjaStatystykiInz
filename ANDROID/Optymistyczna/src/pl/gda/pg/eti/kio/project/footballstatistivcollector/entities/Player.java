package pl.gda.pg.eti.kio.project.footballstatistivcollector.entities;

public class Player {
	private int id;
	private String name;
	private String surname;
	private int nr;
	private String role;
	private int team_id;
	
	public Player(int id, String name, String surname, int nr, String role, int team_id)
	{
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.nr=nr;
		this.role=role;
		this.team_id=team_id;	
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getId() {
		return id;
	}
}
