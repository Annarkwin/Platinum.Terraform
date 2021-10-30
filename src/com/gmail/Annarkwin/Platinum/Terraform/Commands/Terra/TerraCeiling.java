package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;
import com.gmail.Annarkwin.Platinum.Terraform.Clipboard;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraCeiling extends PlatinumCommand
{

	public TerraCeiling( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Selection sel = SelectionManager.getSelection(p.getUniqueId());
		ItemStack mainhand = p.getInventory().getItemInMainHand();

		if (sel.getArea() == null)
		{

			p.sendMessage("§4[Error]:§f No selection");
			return true;

		}

		if (!mainhand.getType().isBlock())
		{

			p.sendMessage("§4[Error]:§f Hold the block you'd like to use");
			return true;

		}

		Clipboard cb = Terraform.getCeiling(sel.getArea());
		cb.saveClipboard(p);
		cb.replaceClips(mainhand.getType());
		cb.pasteBlocks();

		p.sendMessage("§2[Info]:§f Blocks set");
		return true;

	}

}
