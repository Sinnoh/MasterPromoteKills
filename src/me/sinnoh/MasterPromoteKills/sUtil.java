package me.sinnoh.MasterPromoteKills;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.sinnoh.MasterPromote.MasterPromote;
import me.sinnoh.MasterPromote.Events.PlayerPromoteEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class sUtil 
{
	
	public static MasterPromoteKills plugin = MasterPromoteKills.instance;
	
	@SuppressWarnings("unchecked")
	public static List<sPlayer> loadMap()
	{
		try
		{
		File file = new File(plugin.getDataFolder(), "/db.kills");
		List<sPlayer> list = new ArrayList<sPlayer>();
		if(!file.exists())
		{
			file.getParentFile().mkdir();
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		}
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (List<sPlayer>) ois.readObject();
		ois.close();
		return list;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public static void saveMap()
	{
		try
		{
		File file = new File(plugin.getDataFolder(), "/db.kills");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(plugin.getsPlayers());
		oos.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void checkPlayer(Player player)
	{
		sPlayer sp = plugin.getsPlayer(player);
		if(plugin.getConfig().getBoolean("EnableRankedKills"))
		{
			for(String s : plugin.getConfig().getStringList("KillRanks"))
			{
				String[] split = s.split(",");
				String group = split[0];
				int needed = Integer.valueOf(split[1]);
				if(needed == sp.getKills())
				{
					MasterPromote mp = (MasterPromote) Bukkit.getPluginManager().getPlugin("MasterPromote");
					mp.getPermissionsHandler().promote(player, group, PlayerPromoteEvent.PROMOTIONTYPE.KILLS);
				}
			}
		}
		if(plugin.getConfig().getBoolean("EnableRankedDeaths"))
		{
			for(String s : plugin.getConfig().getStringList("DeathRanks"))
			{
				String[] split = s.split(",");
				String group = split[0];
				int needed = Integer.valueOf(split[1]);
				if(needed == sp.getDeaths())
				{
					MasterPromote mp = (MasterPromote) Bukkit.getPluginManager().getPlugin("MasterPromote");
					mp.getPermissionsHandler().promote(player, group, PlayerPromoteEvent.PROMOTIONTYPE.DEATHS);
				}
			}
		}
	}
	
	public static void log(String msg)
	{
		Bukkit.getConsoleSender().sendMessage(msg);
	}

}
