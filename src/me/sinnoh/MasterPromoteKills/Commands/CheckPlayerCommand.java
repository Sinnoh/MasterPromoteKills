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

public class CheckPlayerCommand implements CommandExecutor
{
	public MasterPromoteKills plugin = MasterPromoteKills.instance;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(!player.hasPermission("MasterPromoteKills.checkplayer"))
			{
				player.sendMessage(plugin.getMasterPromote().messages.getString("NoPermissions").replace("&", "\247"));
				return true;
			}
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
					player.sendMessage(ChatColor.RED + "Player not found!");
					return true;
				}
				sp = plugin.getsPlayer(args[0]);
			}
			player.sendMessage(ChatColor.DARK_GRAY + "===  " + ChatColor.GOLD + "Stats for " + sp.getName() + ChatColor.DARK_GRAY + " ===");
			player.sendMessage(ChatColor.GOLD + "Kills: " + sp.getKills() + ChatColor.DARK_GRAY + " | " + ChatColor.GOLD + "Deaths: " + sp.getDeaths());
			return true;
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
			sUtil.log(ChatColor.DARK_GRAY + "===  " + ChatColor.GOLD + "Stats for " + sp.getName() + ChatColor.DARK_GRAY + " ===");
			sUtil.log(ChatColor.GOLD + "Kills: " + sp.getKills() + ChatColor.DARK_GRAY + " | " + ChatColor.GOLD + "Deaths: " + sp.getDeaths());
			return true;
		}
	}

}
