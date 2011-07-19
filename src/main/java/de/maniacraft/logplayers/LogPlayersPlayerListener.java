package de.maniacraft.logplayers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
//import org.bukkit.event.player.PlayerKickEvent;

public class LogPlayersPlayerListener extends PlayerListener {
    private final LogPlayers plugin;
    public LogPlayersPlayerListener(LogPlayers instance) {
        plugin = instance;
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        String player = event.getPlayer().getName();
        
        DateFormat dateSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateFormat = new Date();
        String dateTime = dateSource.format(dateFormat);
        
        String IP = event.getPlayer().getAddress().getHostName().toString();
        plugin.savePlayer(dateTime, player, IP, 1);
    }
    @Override
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        String player = event.getPlayer().getName();
        
        DateFormat dateSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateFormat = new Date();
        String dateTime = dateSource.format(dateFormat);
        
        String IP = event.getPlayer().getAddress().getHostName().toString();
        plugin.savePlayer(dateTime, player, IP, 2);
    }
    /*
	@Override
	public void onPlayerKick(PlayerKickEvent event)
	{
	String player = event.getPlayer().getName();
	DateFormat dateSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date dateFormat = new Date();
	String dateTime = dateSource.format(dateFormat);
	String IP = event.getPlayer().getAddress().getHostName().toString();
	plugin.savePlayer(dateTime, player, IP, 2);
	}
	*/
}