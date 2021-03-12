package io.github.devnull03;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Commands implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(label.equalsIgnoreCase("hitman"))){
            return false;
        }
        String helpMessage = ChatColor.YELLOW + "Commands: \n /hitman start - starts the game\n" +
                "/hitman stop - stops the game" +
                "\n /hitman setInterval <time> - sets the intervals between invincibility\n" +
                "/hitman setsurvivor <player> - sets the player to be hunted\n" +
                "/hitman interval - displays the set interval between break-times\n" +
                "/hitman gamestate - displays weather the game has started or not\n" +
                "/hitman breaktime - displays if the break-time is currently active or not\n" +
                "/hitman survivor - displays the name of the player being hunted";
        if (args.length < 1) {
            sender.sendMessage(helpMessage);
            return true;
        }
        switch (args[0].toLowerCase()){
            case ("setsurvivor"):{
                try{
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null){
                        sender.sendMessage(ChatColor.RED + "Player not found");
                        return true;
                    }
                    HitmanPlugin.survivor = player;
                    sender.sendMessage(ChatColor.GREEN + String.format("Survivor set to %s", player.getName()));
               } catch (IndexOutOfBoundsException e){
                    sender.sendMessage(ChatColor.RED + "No player specified");
                }
                return true;
            }
            case ("start"):{
                if (HitmanPlugin.started){
                    sender.sendMessage(ChatColor.YELLOW + "Already started");
                    return true;
                }
                if (HitmanPlugin.survivor == null) {
                    sender.sendMessage(ChatColor.RED + "No survivor specified\n" +
                            "Use /hitman setsurvivor <player> to set the survivor");
                    return true;
                }
                HitmanPlugin.survivor.addPotionEffect(
                        new PotionEffect(PotionEffectType.GLOWING, 9999999, 1)
                );
                HitmanPlugin.started = true;
                HitmanPlugin.task();
                sender.sendMessage("Started the game");
                return true;
            }
            case ("stop"):{
                if (!(HitmanPlugin.started)){
                    sender.sendMessage(ChatColor.YELLOW + "Game is not running");
                    return true;
                }
                HitmanPlugin.survivor.removePotionEffect(PotionEffectType.GLOWING);
                HitmanPlugin.started = false;
                HitmanPlugin.breakTime = true;
                sender.sendMessage("Stopped the game");
                return true;
            }
            case ("setinterval"):{
                try {
                    HitmanPlugin.interval = Long.parseLong(args[1]);
                    sender.sendMessage(ChatColor.GREEN + String.format("Interval set to %s", args[1]));
                    if (HitmanPlugin.started) {
                        HitmanPlugin.started = false;
                        HitmanPlugin.started = true;
                    }
                } catch (NumberFormatException e){
                    sender.sendMessage(ChatColor.RED + "Invalid time interval");
                } catch (IndexOutOfBoundsException e){
                    sender.sendMessage(ChatColor.RED + "No time specified");
                }
                return true;
            }
            case ("interval"):{
                sender.sendMessage(ChatColor.YELLOW + String.valueOf(HitmanPlugin.interval));
                return true;
            }
            case ("gamestate"):{
                sender.sendMessage(ChatColor.YELLOW + String.valueOf(HitmanPlugin.started));
                return true;
            }
            case ("breaktime"):{
                sender.sendMessage(ChatColor.YELLOW + String.valueOf(HitmanPlugin.breakTime));
                return true;
            }
            case ("survivor"):{
                sender.sendMessage(ChatColor.YELLOW + HitmanPlugin.survivor.getName());
                return true;
            }
            case ("help"):{
                sender.sendMessage(helpMessage);
                return true;
            }
            default:
                sender.sendMessage(helpMessage);
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getLabel().equalsIgnoreCase("hitman")){
            if (args.length == 1) {
                List<String> sendCommands = new ArrayList<>();
                Pattern pattern = Pattern.compile(args[0], Pattern.CASE_INSENSITIVE);
                for (String command_ : HitmanPlugin.allCommands) {
                    boolean matchFound = pattern.matcher(command_).find();
                    if (matchFound){
                        sendCommands.add(command_);
                    }
                }
                return sendCommands;
            }
        }
        return null;
    }
}
