package me.sinnoh.MasterPromoteKills;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import me.sinnoh.MasterPromoteKills.sPlayer;

public class MPListener implements Listener
{
	
	public MasterPromoteKills  plugin = MasterPromoteKills.instance;
	
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent event)
	{
		if(event.getEntity().getType().equals(EntityType.PLAYER) && event.getEntity().getKiller().getType().equals(EntityType.PLAYER))//PVP
		{
			Player killer = (Player) event.getEntity().getKiller();
			Player victim = (Player) event.getEntity();
			sPlayer skiller = plugin.getsPlayer(killer);
			sPlayer svictim = plugin.getsPlayer(victim);
			skiller.addKill();
			svictim.addDeath();
		}
		else if(event.getEntity().getKiller().getType().equals(EntityType.PLAYER) && plugin.getConfig().getBoolean("CountPVEKills"))//PVE Player killed mob
		{
			Player killer = (Player) event.getEntity().getKiller();
			sPlayer skiller = plugin.getsPlayer(killer);
			skiller.addKill();
		}
		else if(event.getEntity().getType().equals(EntityType.PLAYER) && plugin.getConfig().getBoolean("CountPVEDeaths"))//PVE Mob killed Player
		{
			Player victim = (Player) event.getEntity();
			sPlayer svictim = plugin.getsPlayer(victim);
			svictim.addDeath();
		}
	}

}
