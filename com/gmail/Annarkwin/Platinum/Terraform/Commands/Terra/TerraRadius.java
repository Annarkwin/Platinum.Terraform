package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.CommandHelper;
import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraRadius implements Subcommand {

	private String description = "Set terra radius";
	private MainCommand main;
	private String name = "radius";
	private String permission = "platinum.terra.radius";
	private boolean playeronly = true;
	private String usage = "/t radius <size>";
	
	public TerraRadius(MainCommand maincommand) {
		main = maincommand;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MainCommand getMainCommand() {
		return main;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public boolean isPlayerOnly() {
		return playeronly;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		
		if (args.length <= 1 || !CommandHelper.isPositiveInt(args[1])) {p.sendMessage("§4[Error]: §fEnter a positive number"); return;}
		
		Terraform.setRadius(p, Integer.parseInt(args[1]));
		
		p.sendMessage("§2[Info]: §fRadius set to " + args[1]);
	}
}

