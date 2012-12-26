package me.sinnoh.MasterPromoteKills;
import java.io.Serializable;

import org.bukkit.entity.Player;


public class sPlayer implements Serializable
{

	private static final long serialVersionUID = 4041840039689294122L;
	private String name;
	private int kills;
	private int deaths;
	
	public sPlayer(Player player)
	{
		this.name = player.getName();
		this.kills = 0;
	}
	
	public void addKill()
	{
		this.kills = this.kills+1;
	}
	
	public void addDeath()
	{
		this.deaths = this.deaths+1;
	}
	
	public int getKills()
	{
		return this.kills;
	}
	
	public int getDeaths()
	{
		return this.deaths;
	}
	
	public String getName()
	{
		return this.name;
	}

}
