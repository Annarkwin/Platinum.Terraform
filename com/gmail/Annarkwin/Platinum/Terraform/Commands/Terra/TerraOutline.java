package com.gmail.Annarkwin.Platinum.Terraform.Commands.Terra;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;
import com.gmail.Annarkwin.Platinum.Terraform.Clipboard;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform;

public class TerraOutline implements Subcommand {

	private String description = "Create a cubic outline";
	private MainCommand main;
	private String name = "outline";
	private String permission = "platinum.terra.outline";
	private boolean playeronly = true;
	private String usage = "/t outline";
	
	public TerraOutline(MainCommand maincommand) {
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
		Selection sel = SelectionManager.getSelection(p.getUniqueId());
		ItemStack mainhand = p.getInventory().getItemInMainHand();
		
		if (sel.getArea() == null) 								{p.sendMessage("§4[Error]:§f No selection"); return;}
		if (!mainhand.getType().isBlock())				{p.sendMessage("§4[Error]:§f Hold the block you'd like to use"); return;}
		
		Clipboard cb = Terraform.getOutline(sel.getArea());
		cb.saveClipboard(p);
		cb.replaceClips(mainhand.getType());
		cb.pasteBlocks();
		
		p.sendMessage("§2[Info]:§f Blocks set");
	}
}
