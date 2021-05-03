package com.gmail.Annarkwin.Platinum.Terraform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.Cube;
import com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra.CommandTerra;
import com.gmail.Annarkwin.Platinum.Terraform.Commands.Weed.CommandWeed;


public class Terraform extends JavaPlugin {

	public enum TerraMode {
		EX,
		FILL,
		OVERLAY,
		REPLACE,
		NONE;
	}
	
	private static HashMap<UUID, TerraMode> modes = new HashMap<UUID, TerraMode>();
	private static HashMap<UUID, Integer> radii = new HashMap<UUID, Integer>();
	private static HashMap<UUID, Clipboard> clipboards = new HashMap<UUID, Clipboard>();
	
	@Override
	public void onEnable(){		
		//Load configuration serializable classes
		registerSerializables();
		
		//Retrieve file data
		
		//Enable plugin features
		enableListeners();
		enableCommands();
		
		//Initialize update event
	}
	
	@Override
	public void onDisable(){
		
	}
	
	private void registerSerializables() {
		
	}
	
	public void enableCommands(){
		getCommand("Terra").setExecutor(new CommandTerra());
		getCommand("Weed").setExecutor(new CommandWeed());
	}
	
	public void enableListeners(){
		getServer().getPluginManager().registerEvents(new TerraListener(), this);
	}
	
	public static TerraMode getMode(Player p) {
		if (!modes.containsKey(p.getUniqueId())) modes.put(p.getUniqueId(), TerraMode.NONE);
		return modes.get(p.getUniqueId());
	}
	
	public static void setMode(Player p, TerraMode tm) {
		modes.put(p.getUniqueId(), tm);
	}
	
	public static int getRadius(Player p) {
		if (!radii.containsKey(p.getUniqueId())) radii.put(p.getUniqueId(), 20);
		return radii.get(p.getUniqueId());
	}
	
	public static void setRadius(Player p, Integer rad) {
		radii.put(p.getUniqueId(), rad);
	}
	
	public static Clipboard getClipboard(Player p) {
		return clipboards.get(p.getUniqueId());
	}
	
	public static void setClipboard(Player p, Clipboard tm) {
		clipboards.put(p.getUniqueId(), tm);
	}
	
	public static Clipboard getArea(Cube sel) {
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		World world = min.getWorld();
		Clipboard cb = new Clipboard();
		
		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int y = min.getBlockY(); y <= max.getBlockY(); y ++) {
				for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
					cb.addClip(world.getBlockAt(x, y, z));
				}
			}
		}
		
		return cb;
	}
	
	public static Clipboard getWalls(Cube sel){
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		int xmin = min.getBlockX(), xmax = max.getBlockX(), zmin = min.getBlockZ(), zmax = max.getBlockZ();
		World world = min.getWorld();
		Clipboard cb = new Clipboard();

		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int y = min.getBlockY(); y <= max.getBlockY(); y ++) {
				for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
					if (x == xmin || x == xmax || z == zmin || z == zmax) cb.addClip(world.getBlockAt(x, y, z));
				}
			}
		}
		
		return cb;
	}
	
	public static Clipboard getFloor(Cube sel){
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		int y = min.getBlockY();
		World world = min.getWorld();
		Clipboard cb = new Clipboard();

		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
				cb.addClip(world.getBlockAt(x, y, z));
			}
		}

		return cb;
	}
	
	public static Clipboard getCeiling(Cube sel){
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		int y = max.getBlockY();
		World world = min.getWorld();
		Clipboard cb = new Clipboard();

		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
				cb.addClip(world.getBlockAt(x, y, z));
			}
		}

		return cb;
	}
	
	public static Clipboard getBox(Cube sel) {
		Clipboard cb = new Clipboard();
		for (Clip c : getFloor(sel).getClips()) {
			cb.addClip(c);
		}
		for (Clip c : getCeiling(sel).getClips()) {
			cb.addClip(c);
		}
		for (Clip c : getWalls(sel).getClips()) {
			cb.addClip(c);
		}
		return cb;
	}
	
	public static Clipboard getOutline(Cube sel) {
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		World world = min.getWorld();
		int xmin = min.getBlockX(), xmax = max.getBlockX(), zmin = min.getBlockZ(), zmax = max.getBlockZ(), ymin = min.getBlockY(), ymax = max.getBlockY();
		Clipboard cb = new Clipboard();
		
		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int y = min.getBlockY(); y <= max.getBlockY(); y ++) {
				for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
					int extremes = 0;
					if (x == xmin || x == xmax) extremes++;
					if (y == ymin || y == ymax) extremes++;
					if (z == zmin || z == zmax) extremes++;
					
					if (extremes >= 2) cb.addClip(world.getBlockAt(x, y, z));;
				}
			}
		}
		
		return cb;
	}
	
	public static Clipboard getAdjacent(Block start, boolean sametype, boolean samedata, int radius){
		Clipboard cb = new Clipboard();
		
		HashSet<Block> complete = new HashSet<Block>();
		HashSet<Block> currents = new HashSet<Block>();
		HashSet<Block> to_add = new HashSet<Block>();
		currents.add(start);
		while (!currents.isEmpty()) {
			for (Block b : currents) {
				Block[] adj_blocks = {b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN), b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.WEST), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.SOUTH)};
				for (Block adj : adj_blocks) {
					if (sametype && start.getType() != adj.getType()) continue;
					if (samedata && start.getBlockData() != adj.getBlockData()) continue;
					if (!complete.contains(adj) && !currents.contains(adj) && !to_add.contains(adj) && start.getLocation().distance(adj.getLocation()) <= radius) to_add.add(adj);
				}
			}
			
			complete.addAll(currents);
			currents.clear();
			currents.addAll(to_add);
			to_add.clear();
		}
		
		for (Block b : complete) {
			cb.addClip(b);
		}
		return cb;
	}
	
	public static Clipboard getAdjacentAtY(Block start, boolean sametype, boolean samedata, int radius){
		return null;
		
	}
	
	public static Clipboard getAdjacentBelowY(Block start, boolean sametype, boolean samedata, int radius){
		return null;
		
	}
	
	public static Clipboard getAdjacentAboveY(Block start, boolean sametype, boolean samedata, int radius){
		return null;
		
	}
	
	public static Clipboard getOverlay(Block start, boolean sametype, boolean samedata, int radius){
		return null;
		
	}
}
