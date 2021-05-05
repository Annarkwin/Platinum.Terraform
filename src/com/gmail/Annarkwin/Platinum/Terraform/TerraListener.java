package com.gmail.Annarkwin.Platinum.Terraform;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.Annarkwin.Platinum.API.Events.PlayerRightClickBlockEvent;
import com.gmail.Annarkwin.Platinum.Terraform.Terraform.TerraMode;

public class TerraListener implements Listener
{

	@EventHandler
	public void buildInteract( PlayerRightClickBlockEvent e )
	{

		if (e.getItem() == null || !e.getItem().getType().isBlock())
			return;

		if (Terraform.getMode(e.getPlayer()) != TerraMode.NONE)
			e.setCancelled(true);

		if (Terraform.getMode(e.getPlayer()) == TerraMode.REPLACE)
		{

			Clipboard cb = Terraform.getAdjacent(e.getBlock(), true, false, Terraform.getRadius(e.getPlayer()));
			cb.saveClipboard(e.getPlayer());
			cb.replaceClips(e.getItem().getType());
			cb.pasteBlocks();

		}
		else if (Terraform.getMode(e.getPlayer()) == TerraMode.EX)
		{

		}
		else if (Terraform.getMode(e.getPlayer()) == TerraMode.FILL)
		{

		}
		else if (Terraform.getMode(e.getPlayer()) == TerraMode.OVERLAY)
		{

		}

	}

}
