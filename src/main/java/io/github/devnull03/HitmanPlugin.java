package io.github.devnull03;

//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public final class HitmanPlugin extends JavaPlugin {
    public static boolean started = false;
    public static long interval = 12000L;
    public static boolean breakTime = true;
    public static Player survivor = null;
    public static List<String> allCommands = new ArrayList<>();


    @Override
    public void onEnable() {
        Commands commands = new Commands();
        getServer().getPluginManager().registerEvents(new Events(), this);
        allCommands.add("start");
        allCommands.add("stop");
        allCommands.add("setInterval");
        allCommands.add("setSurvivor");
        allCommands.add("interval");
        allCommands.add("gamestate");
        allCommands.add("breaktime");
        allCommands.add("survivor");
        allCommands.add("help");

        getCommand("hitman").setExecutor(commands);
        getCommand("hitman").setTabCompleter(commands);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin activated");
    }

    public static void task(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HitmanPlugin.getPlugin(HitmanPlugin.class), new Runnable() {
            @Override
            public void run() {
                if (!started){
                    return;
                }
//                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//                    onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR,
//                            new TextComponent(ChatColor.LIGHT_PURPLE + "HALLO!"));
//                }
                if (breakTime){
                    Bukkit.broadcastMessage(ChatColor.RED +
                            String.format(ChatColor.BOLD + "- PvP je ukljucen 10 Minuta -", HitmanPlugin.interval));
                    breakTime = false;
                } else {
                    Bukkit.broadcastMessage(ChatColor.GREEN +
                            String.format(ChatColor.BOLD + "- PvP je iskljucen 10 Minuta -", HitmanPlugin.interval));
                    breakTime = true;
                }
            }
        }, 0L , interval);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin Disabled");
    }
}

