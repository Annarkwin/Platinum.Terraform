package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraUndo extends PlatinumCommand
{

	public TerraUndo( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		Terraform.getClipboard(p).pasteBlocks();

		p.sendMessage("§2[Info]: §fChange reverted");

		return true;
		
	}

}
