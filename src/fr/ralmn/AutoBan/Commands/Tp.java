package fr.ralmn.AutoBan.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.ralmn.AutoBan.AB;

public class Tp {

	private static Logger log = Logger.getLogger("Minecraft");

	public Tp(AB instance) {
	}

	public static void Tpl(Player p, String i) {

		int j = 0;
		j = Integer.parseInt(i);

		if (getLoc(p, j) == null) {
			p.sendMessage(ChatColor.DARK_RED + "L'entrée choissis n'exite pas ");
		} else {
			p.teleport(getLoc(p, j));
			p.sendMessage("Vous avez etait les teloporter !");

		}
	}

	public static void list(Player p) {
		try {
			File f = new File("plugins/AutoBan/user/op.dat");
			BufferedReader br = new BufferedReader(new FileReader(f));

			
			if(!f.exists()){
				File d = new File("plugins/AutoBan/user");
				d.mkdirs();
				f.createNewFile();
			}
			
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {

				
				String[] b = line.split("--");

				p.sendMessage(ChatColor.LIGHT_PURPLE + "[" + i + "] "
						+ ChatColor.GREEN + b[0] + ChatColor.BLUE + " - "
						+ b[1] + ChatColor.YELLOW + " - " + b[2]);
				i++;

			}
			if (br.readLine() == null){
				p.sendMessage(ChatColor.BLUE + "[AutoBan] : Rien a Signaler.");
			}
		} catch (Exception e) {
			AB.log.severe(e + "");
		}
	}

	private static String getLine(int i) {
		String number = null;
		ArrayList<String> al = new ArrayList<String>();
		try {
			File f = new File("plugins/AutoBan/user/op.dat");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String size;
			new LineNumberReader(br);
			while ((size = br.readLine()) != null) {

				al.add(size);

			}

			number = al.get(i);

		} catch (Exception e) {
			log.severe(e + "");
		}

		return number;
	}

	private static String getRes(int i) {

		String b = getLine(i);
		String a = b.split("--")[3];

		String res = a;
		return res;

	}

	private static double getX(int i) {
		String a = getRes(i);

		String aa = a.split(";")[0];
		double x = Double.parseDouble(aa);
		return x;
	}

	private static double getY(int i) {
		String a = getRes(i) + "";

		double y = Double.parseDouble(a.split(";")[1]);
		return y;
	}

	private static double getZ(int i) {
		String a = getRes(i) + "";
		String aa = a.split(";")[2];
		String b = aa.split(":")[0];
		double z = Double.parseDouble(b);
		return z;
	}


	private static Location getLoc(Player p, int i) {
		Location loc = new Location(p.getWorld(), getX(i), getY(i), getZ(i));
		return loc;
	}

}
