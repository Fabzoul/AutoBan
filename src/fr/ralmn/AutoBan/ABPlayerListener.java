package fr.ralmn.AutoBan;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.ralmn.AutoBan.Commands.Ban;

public class ABPlayerListener extends PlayerListener {

	public static AB plugin;

	public ABPlayerListener(AB instance) {
	}

	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.isOp()) {
			File f = new File("plugins/AutoBan/user/op.dat");
			if (f.exists()) {
				file.ReadopPlayer();
				p.sendMessage(ChatColor.BLUE + "Des Grief on etait Fait :");
				util.griefJoin(p);
				p.sendMessage("/AB help " + ChatColor.GOLD
						+ "pour avoir la liste des commandes");
			}else if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}

		Ban.initAvert(e.getPlayer().getName());

	}

	@SuppressWarnings("static-access")
	public void onPlayerLogin(PlayerLoginEvent e) {

		try {
			String name = e.getPlayer().getName();
			AB.log.info("Le joueur :" + name + " essaye de ce connecter");
			if (Ban.isBanned(name)) {

				AB.log.info(name + " est bannis par AutoBan");

				double a = Double.parseDouble(Ban.getEndBan(name));
				double b = Double.parseDouble(Ban.getServerTime());
				if (a > b) {
					AB.log.info("je debug l'event");
					e.disallow(e.getResult().KICK_BANNED,
							Ban.getKickMsg(e.getPlayer().getName()));

				} else {
					AB.log.info(e.getPlayer().getName()
							+ " a etait automatiquement debanis par le serveur");
				}
			}
		} catch (Exception ex) {
			e.disallow(e.getResult().KICK_OTHER, "Erreur");
			AB.log.severe("[AutoBan - login] : " + ex );
		}

	}
}
