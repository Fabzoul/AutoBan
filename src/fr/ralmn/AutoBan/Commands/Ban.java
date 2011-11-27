package fr.ralmn.AutoBan.Commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.ralmn.AutoBan.AB;
import fr.ralmn.AutoBan.util;

public class Ban extends AB {
	private static Logger log = Logger.getLogger("Minecraft");
	private static AB plugin;
	public static ArrayList<String> alAvertPlayer = new ArrayList<String>();
	public static ArrayList<Integer> alAvertint = new ArrayList<Integer>();
	public static ArrayList<String> alBanPlayer = new ArrayList<String>();
	public static ArrayList<String> alBanTime = new ArrayList<String>();

	public Ban(AB instance) {
		plugin = instance;
	}

	public static void Banpl(Player pl, Player pla, String reason) {

		String p = pla.getName();

		if (isBannedFileExit() == false) {
			createBannedFile();
		} else
			;

		try {
			// Player pla = plugin.getServer().getPlayer(p);
			alBanPlayer.add(p);
			int index = getIndexAl(p);
			alAvertint.set(index, (alAvertint.get(index) + 1));
			alBanTime.add(getEndTimeBan(p));

			if (pl.isOnline()) {

				pla.kickPlayer(getKickMsg(p, reason));

			}
			pl.sendMessage(ChatColor.BLUE + "[AutoBan] Vous avez bannis : "
					+ ChatColor.YELLOW + p + ChatColor.BLUE + ", jusqu'a  "
					+ ChatColor.YELLOW + FormatTimeBan(getEndTimeBan(p))
					+ ChatColor.BLUE + ", Raison : " + ChatColor.YELLOW + reason);
			log.info(p + " a etait bannis par : " + pl.getName());
		} catch (Exception e) {
			log.severe("[AutoBan] 1: " + e);
		}

	}

	public static String getKickMsg(String p, String reason) {

		String kickmsg = "§B Bannis par un admin";
		kickmsg = kickmsg + " §A Jusqu'au  : "
				+ FormatTimeBan(getEndTimeBan(p)) + "\n";
		kickmsg = kickmsg + " §4 Raison : " + reason + "\n";

		return kickmsg;
	}

	public static String FormatTimeBan(String t) {

		String TimeKick = t.substring(0, 2) + "-";
		TimeKick = TimeKick + t.substring(2, 4) + "-";
		TimeKick = TimeKick + t.substring(4, 8) + " -- ";
		TimeKick = TimeKick + t.substring(8, 10) + "h";
		TimeKick = TimeKick + t.substring(10, 12);

		return TimeKick;
	}

	public static String getKickMsg(String p) {

		String kickmsg = "§B Bannis par un admin";
		kickmsg = kickmsg + " §A Jusqu'au  : "
				+ FormatTimeBan(getEndTimeBan(p));

		return kickmsg;
	}

	public static int getIndexBan(String pl) {

		int index = 0;

		for (int i = 0; i < alBanPlayer.size(); i++) {
			if (alBanPlayer.get(i).equals(pl)) {
				index = i;
			}
		}
		return index;
	}

	public static String getEndBan(String pl) {

		int index = getIndexBan(pl);
		String time = alBanTime.get(index);

		return time;

	}

	public static String getServerTime() {

		String date = util.getDate();
		String[] a = date.split(":");
		int day = Integer.parseInt(a[0]);
		int month = Integer.parseInt(a[1]);
		int years = Integer.parseInt(a[2]);
		Calendar cal = new GregorianCalendar();
		String time = util.getHeure();
		String[] b = time.split(":");
		int Hour = Integer.parseInt(b[0]);
		int Min = Integer.parseInt(b[1]);
		cal.set(Calendar.HOUR, Hour);
		cal.set(Calendar.MINUTE, Min);
		cal.set(years, month, day);
		SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyyHHmm");
		String d = sdf2.format(cal.getTime());
		d = sdf2.format(cal.getTime());
		return d;

	}

	public static String getEndTimeBan(String player) {
		// int hh = getIndexAl(player);
		int m = setBanTime(player);
		int h = m / 60;
		int w = h / 24;
		Calendar cal = new GregorianCalendar();
		String date = util.getDate();
		String[] a = date.split(":");
		int day = Integer.parseInt(a[0]);
		int month = Integer.parseInt(a[1]);
		int years = Integer.parseInt(a[2]);
		String time = util.getHeure();
		String[] b = time.split(":");
		int Hour = Integer.parseInt(b[0]);
		int Min = Integer.parseInt(b[1]);
		// cal.add(Calendar.HOUR, Hour);

		cal.set(years, (month - 1), (day + w));
		cal.set(Calendar.HOUR, Hour);
		cal.set(Calendar.MINUTE, Min);
		// cal.add(Calendar.MINUTE, Min);
		// cal.add(Calendar.YEAR, years);
		// cal.add(Calendar.MONTH, month);
		// cal.add(Calendar.DAY_OF_MONTH, day);
		SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyyHHmm");
		String d = sdf2.format(cal.getTime());
		d = sdf2.format(cal.getTime());
		return d;

	}

	public static boolean isAvertFileExit() {

		File f = new File("plugins/AutoBan/user/averts.dat");
		boolean fe = false;
		if (f.exists()) {
			fe = true;
		} else if (!f.exists()) {
			fe = false;
		}
		return fe;
	}

	public static boolean isBannedFileExit() {
		File f = new File("plugins/AutoBan/banned.dat");
		boolean fe = false;
		if (f.exists()) {
			fe = true;
		} else if (!f.exists()) {
			fe = false;
		}
		return fe;
	}

	public static void createBannedFile() {

		try {
			File d = new File("plugins/AutoBan");
			File f = new File("plugins/AutoBan/banned.dat");
			d.mkdirs();
			f.createNewFile();

		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);
		}
	}

	public static void createAvertFile() {

		try {
			File d = new File("plugins/AutoBan/user");
			File f = new File("plugins/AutoBan/user/averts.dat");
			d.mkdirs();
			f.createNewFile();

		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);

		}
	}

	public static void remAvertFile() {

		try {

			File f = new File("plugins/AutoBan/user/averts.dat");
			f.delete();

		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);
		}

	}

	public static void SaveAvert() {
		remAvertFile();

		if (isAvertFileExit() == false) {
			createAvertFile();
		}
		try {
			File f = new File("plugins/AutoBan/user/averts.dat");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);

			int size = alAvertPlayer.size();
			bw.write(size);

			bw.newLine();

			for (int i = 0; i < size; i++) {

				bw.write(alAvertPlayer.get(i));
				bw.newLine();

			}
			for (int y = 0; y < size; y++) {

				bw.write(alAvertint.get(y).toString());
				bw.newLine();

			}
			log.info("[AutoBan] : Le nombre d'avertisement a etait sauvegarge !");
			bw.close();
			fw.close();
		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);
		}

	}

	public static void LoadAvert() {
		alAvertPlayer.clear();
		alAvertint.clear();
		if (isAvertFileExit() == false) {
			createAvertFile();
		}
		try {
			File f = new File("plugins/AutoBan/user/averts.dat");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			int size = br.read();
			br.readLine();

			for (int i = 0; i < size; i++) {

				String a = br.readLine();
				alAvertPlayer.add(a);
			}

			for (int y = 0; y < size; y++) {
				String a = br.readLine();
				alAvertint.add(Integer.parseInt(a));
			}
			br.close();

			fr.close();
		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);

		}

	}

	public static void SaveBanned() {
		if (isBannedFileExit() == false) {
			createBannedFile();
		}
		try {
			File f = new File("plugins/AutoBan/banned.dat");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			int size = alBanPlayer.size();
			if (size != 0) {
				bw.write(size);
				bw.newLine();

				for (int i = 0; i < size; i++) {

					bw.write(alBanPlayer.get(i));
					bw.newLine();

				}
				for (int i = 0; i < size; i++) {
					bw.write(alBanTime.get(i));
					bw.newLine();

				}
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			log.severe("[AutoBan] : " + e);
		}

	}

	public static void LoadBanned() {
		alBanPlayer.clear();
		alBanTime.clear();
		if (isBannedFileExit() == false) {
			createBannedFile();
		}

		try {

			File f = new File("plugins/AutoBan/banned.dat");

			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			int size = br.read();
			if (size != 0) {
				br.readLine();
				for (int i = 0; i < size; i++) {
					alBanPlayer.add(br.readLine());
				}
				for (int i = 0; i < size; i++) {
					alBanTime.add(br.readLine());

				}
			}
			br.close();

			fr.close();
		} catch (Exception e) {
			log.severe("[AutoBan]  : " + e);

		}

	}

	public static void initAvert(String p) {

		int r = 0;
		for (int i = 0; i < alAvertPlayer.size(); i++) {
			if (alAvertPlayer.get(i).equals(p)) {
				r++;
			}
		}

		if (r == 0) {
			alAvertPlayer.add(p);
			alAvertint.add(1);
			log.info("[AutoBan] : " + p
					+ " a etait ajouter automatiquement au Avert.dat");
		} else
			;

	}

	public static int getAvert(String p) {
		String get;
		int r = 0;
		for (int i = 0; i < alAvertPlayer.size(); i++) {
			get = alAvertPlayer.get(i);
			if (p.equals(get)) {
				r = alAvertint.get(i);
			}
		}
		return r;
	}

	public static int setBanTime(String player) {

		int b = getAvert(player);
		int cal = 0;
		if (b == 1) {
			cal = 1440;
		} else if (b == 2) {
			cal = 2880;
		} else if (b == 3) {
			cal = 10080;
		} else if (b == 4) {
			plugin.getServer().getPlayer(player).setBanned(true);

		} else {
			log.warning("[AutoBan] : Erreur d'avertisement pour le joueur : "
					+ player);

		}
		return cal;

	}

	public static int getIndexAl(String player) {
		int x = 0;

		for (int y = 0; y < alAvertPlayer.size(); y++) {
			if (alAvertPlayer.get(y).equals(player)) {
				x = y;
			}
		}
		return x;
	}

	public static boolean isBanned(String pl) {
		boolean a = false;

		if (alBanPlayer.contains(pl)) {
			a = true;
		}

		return a;
	}
}
