package com.gmail.Annarkwin.Platinum.Terraform;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Clipboard
{

	private HashSet<Clip> clips = new HashSet<Clip>();

	public Clipboard()
	{

	}

	public void addClip( Block b )
	{

		clips.add(new Clip(b));

	}

	public void addClip( Clip c )
	{

		clips.add(c);

	}

	public void transposeClips( Vector v )
	{

		for (Clip c : clips)
		{

			c.setLocation(c.getLocation().add(v));

		}

	}

	public void replaceClips( Material m )
	{

		if (m == Material.STICK) m = Material.AIR;
		for (Clip c : clips)
		{

			c.setBlock(m, Bukkit.createBlockData(m));

		}

	}

	public void replaceClips( Material m, BlockData data )
	{
		if (m == Material.STICK) m = Material.AIR;
		for (Clip c : clips)
		{

			c.setBlock(m, data);

		}

	}

	public void saveClipboard( Player p )
	{

		Terraform.setClipboard(p, clone());

	}

	public void pasteBlocks()
	{

		for (Clip c : clips)
		{

			Block b = c.getLocation().getBlock();
			b.setType(c.getMaterial());
			b.setBlockData(c.getBlockData());

		}

	}

	public HashSet<Clip> getClips()
	{

		return clips;

	}

	public Clipboard clone()
	{

		Clipboard cb = new Clipboard();

		for (Clip c : clips)
		{

			cb.addClip(new Clip(c));

		}

		return cb;

	}

}
