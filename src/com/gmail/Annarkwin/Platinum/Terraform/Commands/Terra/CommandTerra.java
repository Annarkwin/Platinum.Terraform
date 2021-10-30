package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.API.PlatinumMainCommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform.TerraMode;

public class CommandTerra extends PlatinumMainCommand
{


	public CommandTerra( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		boolean isplayer = sender instanceof Player;
		if (args.length > 0)
			for (PlatinumCommand command : getChildren())
			{

				if (command.getName().equalsIgnoreCase(args[0]) && (!command.isPlayerOnly() || isplayer))
				{

					if (sender.hasPermission(command.getPermissionHook()))
						command.run(sender, cmdname, args);
					else
						sender.sendMessage("§4[Error]:§f You don't have permission for that command");

				}

			}
		else {
			if (isplayer) {
				Player p = (Player) sender;
				Terraform.setMode(p, TerraMode.NONE);
				p.sendMessage("§2[Info]: §fReset Terraform Mode to " + TerraMode.NONE);
				
			}
		}
		return false;

	}

}
