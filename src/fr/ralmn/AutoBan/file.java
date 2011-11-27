package fr.ralmn.AutoBan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

public class file {

	private static Logger log = Logger.getLogger("Minecraft");

	public static void ecrirepl(String player, int x, int y, int z,
			String world, String action) {

		try {
			File d = new File("plugins/AutoBan/user/");
			d.mkdirs();
			File f = new File("plugins/AutoBan/user/" + player + ".dat");

			if (!f.exists()) {
				f.createNewFile();

				log.info(AB.prefix + "Fichier joueur : " + player
						+ ".dat creer");
			}

			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(action + "--" + util.getDate() + " - " + util.getHeure()
					+ " - " + x + ";" + y + ";" + z + " : " + world);
			bw.flush();
			bw.newLine();
			bw.close();

			log.info(AB.prefix + "Action enregistrer");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void ecrireop(String player, int x, int y, int z,
			String world, String action) {

		try {
			File d = new File("plugins/AutoBan/user/");
			d.mkdirs();
			File f = new File("plugins/AutoBan/user/op.dat");

			if (!f.exists()) {
				f.createNewFile();

				log.info(AB.prefix + "Fichier joueur : " + player
						+ ".dat creer");
			}

			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(player + "--" + action + "--" + util.getDate() + " - " + util.getHeure()
					+ " -- " + x + ";" + y + ";" + z + ":" + world);
			bw.flush();
			bw.newLine();
			bw.close();

			log.info(AB.prefix + "Action enregistrer");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public static void read(Player p, String player) {

		File f = new File("plugins/AutoBan/user/" + player + ".dat");

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			p.sendMessage("AB :  " + br.readLine());

		} catch (Exception e) {
			log.info(e +"");
		}

		
	}
	public static ArrayList<String> alopGrief = new ArrayList<String>();
	public static void ReadopPlayer(){
		File f = new File("plugins/AutoBan/user/op.dat");

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			LineNumberReader l = new LineNumberReader(br);
			int k = l.getLineNumber();

			for (int j = 0; j < k; j++) {
				String aa = br.readLine();
				log.warning(aa);
				// msg = aa.split("--")[i];
				alopGrief.add(aa);
				log.warning("ah : " + j);

			}

		} catch (Exception e) {
			log.info(e + " ");
		}
		
		
		
	}
	
}
