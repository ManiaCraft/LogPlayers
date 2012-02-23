package de.maniacraft.logplayers;

import de.maniacraft.logplayers.LogPlayersPlayerListener;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.BufferedWriter;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;


public class LogPlayers extends JavaPlugin {
static final Logger log = Logger.getLogger("Minecraft");

    private static File folder;
    private static PluginDescriptionFile desc;
    
    private final LogPlayersPlayerListener playerListener = new LogPlayersPlayerListener(this);
    @Override
    public void onEnable() {
     //playerFile = folder.getAbsolutePath() + File.separator + "players.txt";
     folder = getDataFolder();
        desc = this.getDescription();
                
        if (!setupFiles()) return;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
    }
    
    @Override
    public void onDisable() {
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
                return false;
         }
          
        }
        
        File file = new File(folder.getAbsolutePath() + File.separator + "players.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
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