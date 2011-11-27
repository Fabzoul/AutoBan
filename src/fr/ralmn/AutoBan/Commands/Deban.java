package fr.ralmn.AutoBan.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.ralmn.AutoBan.AB;

public class Deban {


	public static void Unban(Player op, String player) {
		int index = Ban.getIndexBan(player);

		Ban.alBanPlayer.remove(index);
		Ban.alBanTime.remove(index);
		Ban.alAvertint.set(index, Ban.alAvertint.get(index) - 1);
		
		op.sendMessage(ChatColor.BLUE + "[AutoBan] : Vous avez debanis : " + player);
		
		AB.log.info("[AutoBan] : Le joueurs " + player
				+ " a etait debanis par : " + op.getName());

	}

}
