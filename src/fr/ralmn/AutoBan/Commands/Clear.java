package fr.ralmn.AutoBan.Commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.ralmn.AutoBan.AB;


public class Clear {

	public static void ClearOP(Player p){
		
		File f = new File("plugins/AutoBan/user/op.dat");
		f.delete();
		AB.log.info(p.getName() + " a suprimer le fichier OP.dat");
		p.sendMessage(ChatColor.BLUE + "[AutoBan] : Le fichier op.dat a etait suprimer !");
	}
	
	public static void ClearPl(Player p, String player){
		
		File f = new File("plugins/AutoBan/user/" + player + ".dat");
		
		if(f.exists()){
			f.delete();
			AB.log.info(p.getName() + " a surpimer le fichier : " + player + ".dat");
			p.sendMessage(ChatColor.BLUE + "[AutoBan] : Le fichier " + player + ".dat a etait suprimer !");
		}
		
		
	}

}
