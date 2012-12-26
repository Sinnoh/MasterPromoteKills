package me.sinnoh.MasterPromoteKills;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import me.sinnoh.MasterPromoteKills.sPlayer;

public class MPListener implements Listener
{
	
	public MasterPromoteKills  plugin = MasterPromoteKills.instance;
	
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent event)
	{
		EntityDamageEvent devent = event.getEntity().getLastDamageCause();
		if(devent instanceof EntityDamageByEntityEvent)
		{
			if(event.getEntity().getType().equals(EntityType.PLAYER) && event.getEntity().getKiller().getType().equals(EntityType.PLAYER))//PVP
			{
				Player killer = (Player) event.getEntity().getKiller();
				Player victim = (Player) event.getEntity();
				sPlayer skiller = plugin.getsPlayer(killer);
				sPlayer svictim = plugin.getsPlayer(victim);
				if(!killer.hasPermission("MasterPromoteKills.bypass.kills") && !killer.hasPermission("MasterPromoteKills.bypass.*"))
				{
					skiller.addKill();
				}
				if(!victim.hasPermission("MasterPromote.bypass.deaths") && !victim.hasPermission("MasterPromoteKills.bypass.*"))
				{
					svictim.addDeath();
				}
				sUtil.checkPlayer(killer);
				sUtil.checkPlayer(victim);
			}
			else if(event.getEntity().getKiller().getType().equals(EntityType.PLAYER) && plugin.getConfig().getBoolean("CountPVEKills"))//PVE Player killed mob
			{
				Player killer = (Player) event.getEntity().getKiller();
				sPlayer skiller = plugin.getsPlayer(killer);
				skiller.addKill();
				sUtil.checkPlayer(killer);
			}
		}
		else if(event.getEntity().getType().equals(EntityType.PLAYER) && plugin.getConfig().getBoolean("CountPVEDeaths"))//PVE Mob/Enviroement killed Player
		{
			Player victim = (Player) event.getEntity();
			sPlayer svictim = plugin.getsPlayer(victim);
			svictim.addDeath();
			sUtil.log(svictim.getName() + svictim.getDeaths());
			sUtil.checkPlayer(victim);
		}
	}

}
