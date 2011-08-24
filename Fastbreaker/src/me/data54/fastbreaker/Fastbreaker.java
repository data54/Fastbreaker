package me.data54.fastbreaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;


public class Fastbreaker extends JavaPlugin {
	private boolean UsePermissions;
	public static PermissionHandler Permissions;
	public static Fastbreaker plugin;
	public final Logger logger=Logger.getLogger("Minecraft");
	private final FBBlockListener blockListener = new FBBlockListener(this);
	//defined block listener
	public final HashMap<Player, ArrayList<Block>> fbUsers= new HashMap();
	//creating hashmap
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	//create hashmap debugee

	private void setupPermissions()
	{
		Plugin test = getServer().getPluginManager().getPlugin("Permissions");
		if (Permissions == null){
			if (test != null) {
				/*if (!Boolean.parseBoolean(Config.getProperty("UsePermissions"))) {
					logger.info("[Fastbreaker] Permission system disabled in config, using config.txt for users!");
					this.UsePermissions = false;
					return;
				}*/
				this.UsePermissions = true;
				Permissions = ((Permissions)test).getHandler();
				System.out.println("[Fastbreaker] Permissions system detected!");
			} else {
				logger.info("[Fastbreaker] Permission system not detected, using config.txt for users!");
				this.UsePermissions = false;
			}
		}
	}
	public boolean canUseEnable(Player p)
	{
		if(this.UsePermissions){
			return Permissions.has(p, "fastbreaker.enable");
		}
		return Boolean.parseBoolean(Config.getProperty(p.getName()));
	}
	public boolean canUseRedstone(Player p)
	{
		if(this.UsePermissions){
			return Permissions.has(p, "fastbreaker.redstone");
		}
		return Boolean.parseBoolean(Config.getProperty("Redstone"));
	}
	public boolean canUseFences(Player p)
	{
		if(this.UsePermissions){
			return Permissions.has(p, "fastbreaker.fences");
		}
		return Boolean.parseBoolean(Config.getProperty("Fences"));
	}
	public boolean canUseObsidian(Player p)
	{
		if(this.UsePermissions){
			return Permissions.has(p, "fastbreaker.obsidian");
		}
		return Boolean.parseBoolean(Config.getProperty("Obsidian"));
	}



	@Override
	public void onDisable() {
		this.logger.info("Fastbreaker Disabled");
	}

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, this.blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdfFile=this.getDescription();
		this.logger.info("["+pdfFile.getName()+"]" + " version " + pdfFile.getVersion() + " is enabled");
		setupPermissions();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if((commandLabel.equalsIgnoreCase("fastbreaker")||commandLabel.equalsIgnoreCase("fb"))){
			if(canUseEnable((Player) sender)){
				toggleVision((Player) sender);
			}
			else{
				Player player=(Player)sender;
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command");

			}		
		}
		return true;
	}


	public boolean isDebugging(final Player player) {
		if(debugees.containsKey(player)){
			return debugees.get(player);
		}else{
			return false;
		}
	}
	public void setDebuggin(final Player player, final boolean value){
		debugees.put(player, value);
	}
	public boolean penabled(){
		return UsePermissions;
	}
	//method enabled to check if player in hash map

	public boolean enabled(Player player){
		return this.fbUsers.containsKey(player);
	}
	public void toggleVision(Player player){
		if(enabled(player)){
			this.fbUsers.remove(player);
			player.sendMessage("Fastbreaker disabled");
		} else{
			this.fbUsers.put(player,null);
			player.sendMessage("Fastbreaker enabled");
		}
	}
}
