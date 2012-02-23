package de.maniacraft.logplayers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogPlayersPlayerListener implements Listener {
	private final LogPlayers plugin;

	public LogPlayersPlayerListener(LogPlayers instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		String player = event.getPlayer().getName();

		DateFormat dateSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFormat = new Date();
		String dateTime = dateSource.format(dateFormat);

		String IP = event.getPlayer().getAddress().getHostName().toString();
		plugin.savePlayer(dateTime, player, IP, 1);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		String player = event.getPlayer().getName();

		DateFormat dateSource = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFormat = new Date();
		String dateTime = dateSource.format(dateFormat);

		String IP = event.getPlayer().getAddress().getHostName().toString();
		plugin.savePlayer(dateTime, player, IP, 2);
	}
}