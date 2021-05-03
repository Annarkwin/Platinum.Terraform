package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform.TerraMode;

public class TerraReplace implements Subcommand {

	private String description = "Set replace mode";
	private MainCommand main;
	private String name = "replace";
	private String permission = "platinum.terra.replace";
	private boolean playeronly = true;
	private String usage = "/t replace <rad>";
	
	public TerraReplace(MainCommand maincommand) {
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
		
		Terraform.setMode(p, (Terraform.getMode(p) == TerraMode.REPLACE) ? TerraMode.NONE:TerraMode.REPLACE);

		p.sendMessage("§2[Info]:§f Terra mode set to " + Terraform.getMode(p));
	}

}
