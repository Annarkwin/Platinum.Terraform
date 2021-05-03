package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform.TerraMode;

public class TerraEx implements Subcommand {

	private String description = "Set excavate mode";
	private MainCommand main;
	private String name = "ex";
	private String permission = "platinum.terra.ex";
	private boolean playeronly = true;
	private String usage = "/t ex <rad>";
	
	public TerraEx(MainCommand maincommand) {
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
		
		Terraform.setMode(p, (Terraform.getMode(p) == TerraMode.EX) ? TerraMode.NONE:TerraMode.EX);

		p.sendMessage("§2[Info]:§f Terra mode set to " + Terraform.getMode(p));
	}

}
