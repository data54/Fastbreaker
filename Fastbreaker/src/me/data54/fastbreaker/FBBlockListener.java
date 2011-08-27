package me.data54.fastbreaker;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;


public class FBBlockListener extends BlockListener {
	public static Fastbreaker plugin;
	public FBBlockListener(Fastbreaker instance){
		plugin=instance;
	}
	//Boolean re=plugin.re;
	//Boolean fe=plugin.fe;
	//Boolean oe=plugin.oe;


	public void onBlockDamage(BlockDamageEvent event) {
		Block block=event.getBlock();
		Material hand=event.getPlayer().getItemInHand().getType();
		Player pl=event.getPlayer();
		Location loc = new Location(block.getWorld(), block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());

		if(plugin.enabled(pl,plugin.fbUsers)){
			//Block block=event.getBlock();
			//Material hand=event.getPlayer().getItemInHand().getType();
			//Player p=event.getPlayer();
			//Location loc = new Location(block.getWorld(), block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());

			if(hand.equals(Material.DIAMOND_PICKAXE)){
				if((plugin.canUseRedstone(pl))&&((block.getTypeId()==73)||(block.getTypeId()==74))){
					block.setTypeId(0);
					block.getWorld().dropItem(loc, new ItemStack(331, 1));
					block.getWorld().dropItem(loc, new ItemStack(331, 1));
					block.getWorld().dropItem(loc, new ItemStack(331, 1));
					block.getWorld().dropItem(loc, new ItemStack(331, 1));
					block.getWorld().dropItem(loc, new ItemStack(331, 1));
				}//redstone
				else if((plugin.canUseObsidian(pl))&&(block.getTypeId()==49)){
					block.setTypeId(0);
					block.getWorld().dropItem(loc, new ItemStack(49, 1));
				}//obsidian
			}//things with diamond pickaxe
			else if(hand.equals(Material.DIAMOND_AXE)){
				if((plugin.canUseFences(pl))&&(block.getTypeId()==85)){
					block.setTypeId(0);
					block.getWorld().dropItem(loc, new ItemStack(85, 1));
				}//Fences
			}//things with diamond axe

			/*
			if((plugin.canUseRedstone(p))&&(((block.getTypeId()==73)||(block.getTypeId()==74))&&(hand.equals(Material.DIAMOND_PICKAXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
				block.getWorld().dropItem(loc, new ItemStack(331, 1));
			}//redstone
			else if((plugin.canUseFences(p))&&((block.getTypeId()==85)&&(hand.equals(Material.DIAMOND_AXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(85, 1));
			}//fences
			else if((plugin.canUseObsidian(p))&&((block.getTypeId()==49)&&(hand.equals(Material.DIAMOND_PICKAXE)))){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(49, 1));
			}//obsidian */
		}
		else if(plugin.enabled(pl,plugin.bbUsers)){
			if((plugin.canUseBedrock(pl))&&(block.getTypeId()==7)){
				block.setTypeId(0);
				block.getWorld().dropItem(loc, new ItemStack(7, 1));
			}//bedrock breaker
		}
	}
	
}
