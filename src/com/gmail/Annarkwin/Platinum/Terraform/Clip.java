package com.gmail.Annarkwin.Platinum.Terraform;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

public class Clip
{

	private Material m;
	private BlockData data;
	private World w;
	private Vector location;

	public Clip( Block b )
	{

		m = b.getType();
		data = b.getBlockData();
		w = b.getWorld();
		location = b.getLocation().toVector();

	}

	public Clip( Clip c )
	{

		m = c.getMaterial();
		data = c.getBlockData();
		w = c.getLocation().getWorld();
		location = c.getLocation().toVector();

	}

	public Material getMaterial()
	{

		return m;

	}

	public BlockData getBlockData()
	{

		return data;

	}

	public void setBlock( Material type )
	{

		m = type;
		data = Bukkit.createBlockData(type);

	}

	public void setBlock( Material type, BlockData d )
	{

		m = type;
		data = d;

	}

	public Location getLocation()
	{

		return location.toLocation(w);

	}

	public void setLocation( Location l )
	{

		w = l.getWorld();
		location = l.toVector();

	}

	@Override
	public boolean equals( Object o )
	{

		return (location == ((Clip) o).getLocation().toVector());

	}

}
