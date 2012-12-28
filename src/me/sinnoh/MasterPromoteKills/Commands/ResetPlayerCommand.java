package me.sinnoh.MasterPromoteKills.Commands;

import me.sinnoh.MasterPromoteKills.MasterPromoteKills;
import me.sinnoh.MasterPromoteKills.sPlayer;
import me.sinnoh.MasterPromoteKills.sUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetPlayerCommand implements CommandExecutor
{
	
	public MasterPromoteKills plugin = MasterPromoteKills.instance;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(!player.hasPermission("MasterPromoteKills.resetplayer"))
			{
				player.sendMessage(plugin.getMasterPromote().messages.getString("NoPermissions").replace("&", "\247"));
				return true;
			}
			if(args.length != 2)
			{
				return false;
			}
			sPlayer sp = null;
			if(Bukkit.getPlayer(args[0]) != null)
			{
				sp = plugin.getsPlayer(Bukkit.getPlayer(args[0]));
			}
			else
			{
				if(plugin.getsPlayer(args[0]) == null)
				{
					player.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				sp = plugin.getsPlayer(args[0]);
			}
			if(args[1].equalsIgnoreCase("kills"))
			{
				sp.setKills(0);
				player.sendMessage(ChatColor.GOLD + "You've set the kills of " + sp.getName() + " to 0");
				return true;
			}
			else if(args[1].equalsIgnoreCase("deaths"))
			{
				sp.setDeaths(0);
				player.sendMessage(ChatColor.GOLD + "You've set the deaths of " + sp.getName() + " to 0");
				return true;
			}
			else if(args[1].equalsIgnoreCase("both"))
			{
				sp.setKills(0);
				sp.setDeaths(0);
				player.sendMessage(ChatColor.GOLD + "You've set the kills and deaths of " + sp.getName() + " to 0");
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(args.length != 1)
			{
				return false;
			}
			sPlayer sp = null;
			if(Bukkit.getPlayer(args[0]) != null)
			{
				sp = plugin.getsPlayer(Bukkit.getPlayer(args[0]));
			}
			else
			{
				if(plugin.getsPlayer(args[0]) == null)
				{
					sUtil.log(ChatColor.RED + "Player not found!");
					return true;
				}
				sp = plugin.getsPlayer(args[0]);
			}
			if(args[1].equalsIgnoreCase("kills"))
			{
				sp.setKills(0);
				sUtil.log(ChatColor.GOLD + "You've set the kills of " + sp.getName() + " to 0");
				return true;
			}
			else if(args[1].equalsIgnoreCase("deaths"))
			{
				sp.setDeaths(0);
				sUtil.log(ChatColor.GOLD + "You've set the deaths of " + sp.getName() + " to 0");
				return true;
			}
			else if(args[1].equalsIgnoreCase("both"))
			{
				sp.setKills(0);
				sp.setDeaths(0);
				sUtil.log(ChatColor.GOLD + "You've set the kills and deaths of " + sp.getName() + " to 0");
				return true;
			}
			else
			{
				return false;
			}
		}
	}

}
