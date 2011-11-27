package fr.ralmn.AutoBan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class util {

	private static Logger log = Logger.getLogger("minecraft");

	public static String getHeure() {
		String heure;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		heure = sdf.format(new Date());

		return heure;
	}

	public static String getDate() {
		String date;
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd:MM:yyyy");
		date = sdf2.format(new Date());

		return date;
	}

	public static void griefJoin(Player p) {
		ArrayList<String> al = new ArrayList<String>();;
		File f = new File("plugins/AutoBan/user/op.dat");

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				String[] b = line.split("--");
				
				p.sendMessage(ChatColor.GREEN + b[0] + ChatColor.BLUE + " - " + b[1]
				+ ChatColor.YELLOW + " - " + b[2]);		      }
			for (int j = 0; j < Integer.parseInt(line); j++) {
				String aa = br.readLine();
				log.warning(aa);
				// msg = aa.split("--")[i];
				al.add(aa);
				log.warning("ah : " + j);

			}

		} catch (Exception e) {
			log.info(e + " ");
		}
		
		
		log.info("debug 1");
		log.info(al.size() + "");
				for (int k = 0; k < al.size(); k++) {
					
			String a = al.get(k) + "";
			log.info(a);
			String[] b = a.split("--");
			
			p.sendMessage(ChatColor.GREEN + b[0] + ChatColor.BLUE + " - " + b[1]
			+ ChatColor.YELLOW + " - " + b[2]);

		}
				log.info("debug 2");
	}

	
}
