package fr.ralmn.AutoBan;

import java.util.logging.Logger;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;


public class ABBlockListener extends BlockListener {

	public static AB plugin;
	
	public ABBlockListener(AB instance ){
		plugin = instance;
	}
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void onBlockPlace(BlockPlaceEvent e){
		
		if (!(plugin.getWorldGuard().canBuild(e.getPlayer(), e.getBlock()))){
			
			
			int lx = e.getBlock().getLocation().getBlockX();
			int ly = e.getBlock().getLocation().getBlockY();
			int lz = e.getBlock().getLocation().getBlockZ();
			String w = e.getBlock().getLocation().getWorld().getName();
			log.warning(AB.prefix+ e.getPlayer().getName() + " a essayer de placer un blocks a la possition : " + lx + ";" + ly + ";"+ lz);
			file.ecrirepl(e.getPlayer().getName(), lx, ly, lz, w, "Place");
			file.ecrireop(e.getPlayer().getName(), lx, ly, lz, w, "Place");
			
		}
	}
	public void onBlockBreak(BlockBreakEvent e){
if (!(plugin.getWorldGuard().canBuild(e.getPlayer(), e.getBlock()))){
			
			int lx = e.getBlock().getLocation().getBlockX();
			int ly = e.getBlock().getLocation().getBlockY();
			int lz = e.getBlock().getLocation().getBlockZ();
			String w = e.getBlock().getLocation().getWorld().getName();
			log.warning(AB.prefix + e.getPlayer().getName() + " a essayer de casser un blocks a la possition : " + lx + ";" + ly + ";"+ lz);
			file.ecrirepl(e.getPlayer().getName(), lx, ly, lz, w, "Break");
			file.ecrireop(e.getPlayer().getName(), lx, ly, lz, w, "Break");
			
		}
	}
	
}