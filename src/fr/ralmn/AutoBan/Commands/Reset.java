package fr.ralmn.AutoBan.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Reset {


	public static void ResetPl(Player op, String pl) {

		int index = Ban.getIndexAl(pl);
		Ban.alAvertint.set(index, 1);
		op.sendMessage(ChatColor.BLUE
				+ "Vous avez remis par default le nombre d'avertisement du joueurs : "
				+ ChatColor.YELLOW + pl);

	}

	public static void ResetAll(Player op){
		
		for (int i =0; i < Ban.alAvertint.size(); i++){
			Ban.alAvertint.set(i, 1);
			
		}
		
		op.sendMessage(ChatColor.BLUE
					+ "Vous avez remis par default le nombre d'avertisement de tous les joueurs");
		
	}
}
