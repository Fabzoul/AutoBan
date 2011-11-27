package fr.ralmn.AutoBan.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
	private static ArrayList<String> alcmds = new ArrayList<String>();
	private static ArrayList<String> aldes = new ArrayList<String>();

	public static void HelpCmds(Player p) {

		Add("" , "Affiche les commandes");
		Add("tp [numero]" , "Permet de se teleporter au block grief");
		Add("ban <player> [raison]" , "Permet de banir un joueurs");
		Add("deban <player>", "Permet de débanir un joueurs");
		Add("reset [player]", "Permet de reset le nombre            d'avertisement d'un joueurs");
		Add("clear [player]" , "Permet de vider le fichier joueurs  ou op");
		Add("rem <ligne>", "Permet de retirer une ligne de la liste  des griefs");
		
		p.sendMessage("[AutoBan] : Voici les commandes : ");
		for(int i = 0; i < alcmds.size(); i++ ){
			
			p.sendMessage(ChatColor.BLUE + "/autoban " + alcmds.get(i) + ChatColor.YELLOW + " : " + aldes.get(i) + ".");
			
			
		}
		p.sendMessage(ChatColor.BLUE + "<> : " + ChatColor.GREEN + "Obligatoire");
		p.sendMessage(ChatColor.BLUE + "[] : " + ChatColor.GREEN + "Facultatif");
		p.sendMessage(ChatColor.YELLOW + "----------------------------------");
		
	}
	
	private static void Add(String cmd, String des){
		
		alcmds.add(cmd);
		aldes.add(des);
		
	}

}
