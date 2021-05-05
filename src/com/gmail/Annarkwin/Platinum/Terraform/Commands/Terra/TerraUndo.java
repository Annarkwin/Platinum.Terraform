package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraUndo implements Subcommand
{

	private String description = "Undo last change";
	private MainCommand main;
	private String name = "undo";
	private String permission = "platinum.terra.undo";
	private boolean playeronly = true;
	private String usage = "/t undo";

	public TerraUndo( MainCommand maincommand )
	{

		main = maincommand;

	}

	@Override
	public String getDescription()
	{

		return description;

	}

	@Override
	public MainCommand getMainCommand()
	{

		return main;

	}

	@Override
	public String getName()
	{

		return name;

	}

	@Override
	public String getPermission()
	{

		return permission;

	}

	@Override
	public String getUsage()
	{

		return usage;

	}

	@Override
	public boolean isPlayerOnly()
	{

		return playeronly;

	}

	@Override
	public void run( CommandSender sender, String[] args )
	{

		Player p = (Player) sender;

		Terraform.getClipboard(p).pasteBlocks();

		p.sendMessage("§2[Info]: §fChange reverted");

	}

}
