package fr.ralmn.AutoBan;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.ralmn.AutoBan.Commands.Ban;
import fr.ralmn.AutoBan.Commands.Clear;
import fr.ralmn.AutoBan.Commands.Deban;
import fr.ralmn.AutoBan.Commands.Help;
import fr.ralmn.AutoBan.Commands.Rem;
import fr.ralmn.AutoBan.Commands.Reset;
import fr.ralmn.AutoBan.Commands.Tp;

public class AB extends JavaPlugin {
	final ABBlockListener bl = new ABBlockListener(this);
	final ABPlayerListener pl = new ABPlayerListener(this);
	public static Logger log = Logger.getLogger("Minecraft");
	static String prefix = "[AutoBan] : ";

	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			log.warning(prefix + "WorldGuard est nécessaire !");

			return null; // Maybe you want throw an exception instead
		}

		return (WorldGuardPlugin) plugin;
	}

	@Override
	public void onDisable() {
		log.info(prefix + "Desactiver");
		Ban.SaveAvert();
		Ban.SaveBanned();
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile desc = getDescription();
		Ban.LoadAvert();
		Ban.LoadBanned();
		log.info(prefix + "Activer");
		log.info(prefix + "Version : " +desc.getVersion());
		
		log.info(prefix + "Developer par RALMN");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, bl, Event.Priority.Normal,
				this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, bl, Event.Priority.Normal,
				this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, pl, Event.Priority.Normal,
				this);
		pm.registerEvent(Event.Type.PLAYER_LOGIN, pl, Event.Priority.Normal,
				this);

		// Les commandes :

		// getCommand("autoban").setExecutor(new ABCommandExecutor(this));
	}

	public boolean onCommand(CommandSender sender, Command cmds, String label,
			String[] args) {
		boolean Return = false;
		if (!(sender instanceof Player)) {
			sender.sendMessage("Seulement en jeu");
		} else
			;

		Player player = (Player) sender;

		if (label.equalsIgnoreCase("autoban")
				&& (player.hasPermission("autoban.admin") || player.isOp())) {

			if (args.length == 0) {
				Help.HelpCmds(player);
			} else if (args[0].equals("clear") && args.length == 1) {

				Clear.ClearOP(player);

			} else if (args[0].equals("clear") && args.length == 2) {

				Clear.ClearPl(player, args[1]);

			} else if (args[0].equals("tp") && args.length == 1) {
				AB.log.info("a");
				Tp.list(player);

			} else if (args[0].equals("tp") && args.length == 2) {
				Tp.Tpl(player, args[1].trim());

			} else if (args[0].equals("ban") && args.length == 3) {
				Ban.Banpl(player, getServer().getPlayer(args[1]), args[2]);
			} else if (args[0].equals("ban") && args.length == 2) {
				Ban.Banpl(player, getServer().getPlayer(args[1]), "Iconnue");
			} else if (args[0].equals("deban") && args.length == 2) {
				Deban.Unban(player, args[1]);
			} else if (args[0].equals("reset") && args.length == 1) {
				Reset.ResetAll(player);
			} else if (args[0].equals("reset") && args.length == 2) {
				Reset.ResetPl(player, args[1]);
			} else if (args[0].equals("rem") && args.length == 2) {
				Rem.Reml(args[1]);
			}

			Return = true;
		} else
			;

		return Return;
	}

}
