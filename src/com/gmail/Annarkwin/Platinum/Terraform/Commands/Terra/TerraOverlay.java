package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform.TerraMode;

public class TerraOverlay extends PlatinumCommand
{

	public TerraOverlay( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		Terraform.setMode(p, (Terraform.getMode(p) == TerraMode.OVERLAY) ? TerraMode.NONE : TerraMode.OVERLAY);

		p.sendMessage("?2[Info]:?f Terra mode set to " + Terraform.getMode(p));
		
		return true;

	}

}
