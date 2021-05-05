package com.gmail.Annarkwin.Platinum.Terraform.Commands.Weed;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWeed implements CommandExecutor
{

	private static HashMap<Material, Boolean> weedlist;

	static
	{

		weedlist = new HashMap<Material, Boolean>();

		// Blocks that are set to always be weeded
		weedlist.put(Material.GRASS, true);
		weedlist.put(Material.TALL_GRASS, true);
		weedlist.put(Material.FERN, true);
		weedlist.put(Material.LARGE_FERN, true);
		weedlist.put(Material.LILY_PAD, true);
		weedlist.put(Material.DEAD_BUSH, true);
		weedlist.put(Material.BROWN_MUSHROOM, true);
		weedlist.put(Material.RED_MUSHROOM, true);
		weedlist.put(Material.CRIMSON_ROOTS, true);
		weedlist.put(Material.WARPED_ROOTS, true);
		weedlist.put(Material.CRIMSON_FUNGUS, true);
		weedlist.put(Material.WARPED_FUNGUS, true);
		weedlist.put(Material.DANDELION, true);
		weedlist.put(Material.POPPY, true);
		weedlist.put(Material.BLUE_ORCHID, true);
		weedlist.put(Material.ALLIUM, true);
		weedlist.put(Material.AZURE_BLUET, true);
		weedlist.put(Material.ORANGE_TULIP, true);
		weedlist.put(Material.PINK_TULIP, true);
		weedlist.put(Material.RED_TULIP, true);
		weedlist.put(Material.WHITE_TULIP, true);
		weedlist.put(Material.OXEYE_DAISY, true);
		weedlist.put(Material.CORNFLOWER, true);
		weedlist.put(Material.LILY_OF_THE_VALLEY, true);
		weedlist.put(Material.SUNFLOWER, true);
		weedlist.put(Material.LILAC, true);
		weedlist.put(Material.ROSE_BUSH, true);
		weedlist.put(Material.PEONY, true);

		// Blocks that are only weeded if flagged with -a
		weedlist.put(Material.MELON_STEM, true);
		weedlist.put(Material.PUMPKIN_STEM, true);
		weedlist.put(Material.WHEAT, true);
		weedlist.put(Material.BEETROOTS, true);
		weedlist.put(Material.CARROTS, true);
		weedlist.put(Material.POTATOES, true);
		weedlist.put(Material.ACACIA_SAPLING, true);
		weedlist.put(Material.BIRCH_SAPLING, true);
		weedlist.put(Material.DARK_OAK_SAPLING, true);
		weedlist.put(Material.JUNGLE_SAPLING, true);
		weedlist.put(Material.OAK_SAPLING, true);
		weedlist.put(Material.SPRUCE_SAPLING, true);
		weedlist.put(Material.SWEET_BERRY_BUSH, true);
		weedlist.put(Material.NETHER_WART, true);

	}

	@Override
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
	{

		if (!(sender instanceof Player))
		{

			sender.sendMessage("§4[Error]:§f This command is for players");
			return true;

		}

		Player p = (Player) sender;

		if (args.length > 0)
		{

			if (!p.hasPermission("Platinum.weed"))
			{

				p.sendMessage("§4[Error]:§f No permission");
				return true;

			}

			if (!isNumber(args[0]))
			{

				p.sendMessage("§4[Error]:§f Please enter a range");
				return true;

			}

			int range = getNumber(args[0]);
			Location loc = p.getLocation();

			for (int x = loc.getBlockX() - range; x < loc.getBlockX() + range; x++)
			{

				for (int z = loc.getBlockZ() - range; z < loc.getBlockZ() + range; z++)
				{

					Block block = loc.getWorld().getHighestBlockAt(x, z).getRelative(BlockFace.UP);
					Boolean flagged = weedlist.get(block.getType());

					if (flagged == null)
						continue;
					else if (flagged)
					{

						block.setType(Material.AIR);

					}
					else if (!flagged)
						;

				}

			}

			p.sendMessage("§2[Info]:§f The top layer of blocks has been weeded.");

		}
		else
		{

			String[] help =
			{
					"help"
			};
			this.onCommand(sender, cmd, label, help);

		}

		return true;

	}

	public boolean isNumber( String arg )
	{

		try
		{

			Integer.parseInt(arg);
			return true;

		}
		catch (Exception e)
		{

			return false;

		}

	}

	public int getNumber( String arg )
	{

		try
		{

			int number = Integer.parseInt(arg);
			return number;

		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;

		}

	}

}
