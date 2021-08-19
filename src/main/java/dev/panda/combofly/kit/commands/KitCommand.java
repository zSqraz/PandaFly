package dev.panda.combofly.kit.commands;

import dev.panda.combofly.ComboFly;
import dev.panda.combofly.utilities.CC;
import dev.risas.panda.command.BaseCommand;
import dev.risas.panda.command.Command;
import dev.risas.panda.command.CommandArgs;
import org.bukkit.entity.Player;

public class KitCommand extends BaseCommand {

    @Command(name = "kit", permission = "pandafly.kit")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        String label = command.getLabel();
        
        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /" + label + " <kitName>"));
            player.sendMessage(CC.translate("&cAvailable Kits: " +
                    ComboFly.get().getKitManager().getKits()));
            return;
        }

        String kitName = CC.capitalize(args[0]);

        if (ComboFly.get().getKitConfig().getString("KITS." + kitName) == null) {
            player.sendMessage(CC.getKitNotFound(args[0]));
            player.sendMessage(CC.translate("&cAvailable Kits: " +
                    ComboFly.get().getKitManager().getKits()));
            return;

        }

        if (ComboFly.get().getCombatManager().hasCooldown(player)) {
            player.sendMessage(CC.translate(ComboFly.get().getMessageConfig().getString("KIT.COMBAT")));
            return;
        }

        ComboFly.get().getKitManager().giveKit(player, kitName);
    }
}
