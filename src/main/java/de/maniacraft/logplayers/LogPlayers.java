package de.maniacraft.logplayers;

import de.maniacraft.logplayers.LogPlayersPlayerListener;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;


public class LogPlayers extends JavaPlugin {
static final Logger log = Logger.getLogger("Minecraft");

    private static File folder;
    private static PluginDescriptionFile desc;
    private static String playerFile;
    
    private final LogPlayersPlayerListener playerListener = new LogPlayersPlayerListener(this);
    @Override
    public void onEnable() {
     //playerFile = folder.getAbsolutePath() + File.separator + "players.txt";
     folder = getDataFolder();
        desc = this.getDescription();
                
        if (!setupFiles()) return;
        PluginManager pm = getServer().getPluginManager();
        // pm.registerEvent(Event.Type.PLAYER_KICK, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
         
        log.log(Level.INFO,'[' + desc.getName() + "] version " + desc.getVersion() + " enabled!");
        // log.log(Level.INFO, folder.getAbsolutePath() + File.separator + "players.txt");
    }
    
    @Override
    public void onDisable() {
        log.log(Level.INFO,'[' + desc.getName() + "] version " + desc.getVersion() + " disabled!");
    }
    
    public boolean savePlayer(String time, String player, String IP, int type)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter((folder.getAbsolutePath() + File.separator + "players.txt"), true));
            if (type == 1) {
             writer.write(time + " [JOIN] " + player + "(" + IP + ")");
            }
            else if (type == 2) {
             writer.write(time + " [QUIT] " + player + "(" + IP + ")");
            }
            writer.newLine();
            writer.close();
            } catch (Exception e)
            {
             System.out.println('[' + desc.getName() + "] version " + desc.getVersion() + " Something went wrong: " + e);
                return false;
            }
            return true;
    }
    
    private static boolean setupFiles() {
    
     File playerfolder = new File(folder.getAbsolutePath());
        if (!playerfolder.exists()) {
         try {
         new File(folder.getAbsolutePath()).mkdir();
         } catch (Exception e) {
                log.log(Level.WARNING,'[' + desc.getName() + "] version " + desc.getVersion() + " Failed to create LogPlayers folder!" + e);
                return false;
         }
          
        }
        
        File file = new File(folder.getAbsolutePath() + File.separator + "players.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                log.log(Level.WARNING,'[' + desc.getName() + "] version " + desc.getVersion() + " Failed to create players.txt file!" + e);
                return false;
            }
        }
        return true;
    }
    
    public static String getVersion() {
        return desc.getVersion();
    }

    public static String getPluginName() {
        return desc.getName();
    }

}