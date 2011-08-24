package me.data54.fastbreaker;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;


public class FBBlockListener extends BlockListener {
	public static Fastbreaker plugin;
	public FBBlockListener(Fastbreaker instance){
		plugin=instance;
	}
	Boolean re=Boolean.valueOf(Boolean.parseBoolean(Config.getProperty("Redstone")));
	Boolean fe=Boolean.valueOf(Boolean.parseBoolean(Config.getProperty("Fences")));
	Boolean oe=Boolean.valueOf(Boolean.parseBoolean(Config.getProperty("Obsidian")));
	
	
	public void onBlockDamage(BlockDamageEvent event) {
		if(plugin.penabled()){
			re=false;
			fe=false;
			oe=false;}
		
		if(plugin.enabled(event.getPlayer())){
			Block block=event.getBlock();
			Material hand=event.getPlayer().getItemInHand().getType();
			Player p=event.getPlayer();
			Location loc = new Location(block.getWorld(), block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());

			if((plugin.canUseRedstone(p)||re)&&(((block.getTypeId()==73)||(block.getTypeId()==74))&&(hand.equals(Material.DIAMOND_PICKAXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
			}//redstone
			else if((plugin.canUseFences(p)||fe)&&((block.getTypeId()==85)&&(hand.equals(Material.DIAMOND_AXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(85, 1));
			}//fences
			else if((plugin.canUseObsidian(p)||oe)&&((block.getTypeId()==49)&&(hand.equals(Material.DIAMOND_PICKAXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(49, 1));
			}//obsidian
		}

	}
}
