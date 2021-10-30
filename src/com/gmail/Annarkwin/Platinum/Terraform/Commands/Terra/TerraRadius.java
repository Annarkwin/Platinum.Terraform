package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraRadius extends PlatinumCommand
{

	public TerraRadius( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		if (args.length <= 1 || !CommandHelper.isPositiveInt(args[1]))
		{

			p.sendMessage("§4[Error]: §fEnter a positive number");
			return true;

		}

		Terraform.setRadius(p, Integer.parseInt(args[1]));

		p.sendMessage("§2[Info]: §fRadius set to " + args[1]);

		return true;
		
	}

}
