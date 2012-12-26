package me.sinnoh.MasterPromoteKills;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MasterPromoteKills extends JavaPlugin
{
	
	public static MasterPromoteKills instance;
	private List<sPlayer> players;
	
	public void onDisable()
	{
		sUtil.saveMap();
	}
	
	
	public void onEnable()
	{
		instance = this;
		this.players = sUtil.loadMap();
		loadConfig();
		getServer().getPluginManager().registerEvents(new MPListener(), this);
	}
	
	
	public void loadConfig()
	{
		getConfig().options().copyDefaults(true);
		getConfig().addDefault("EnableRankedKills", true);
		List<String> ranks = new ArrayList<String>();
		ranks.add("Murderer,25");
		getConfig().addDefault("KillRanks", ranks);
		getConfig().addDefault("EnableRankedDeaths", false);
		ranks.clear();
		ranks.add("Victim,25");
		getConfig().addDefault("DeathRanks", ranks);
		getConfig().addDefault("CountPVEKills", false);
		getConfig().addDefault("CountPVEDeaths", false);
	}
	
	public sPlayer getsPlayer(Player player)
	{
		for(sPlayer sp : this.players)
		{
			if(sp.getName().equals(player))
			{
				return sp;
			}
		}
		sPlayer sp = new sPlayer(player);
		this.players.add(sp);
		return sp;
	}
	
	public List<sPlayer> getsPlayers()
	{
		return this.players;
	}
	
}
