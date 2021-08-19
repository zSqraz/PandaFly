package dev.panda.combofly.koth.commands.subcommands;

import dev.panda.combofly.koth.KoTH;
import dev.panda.combofly.koth.Selection;
import dev.panda.combofly.utilities.CC;
import dev.risas.panda.command.BaseCommand;
import dev.risas.panda.command.Command;
import dev.risas.panda.command.CommandArgs;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateCommand extends BaseCommand {

    @Command(name = "koth.create", permission = "pandafly.koth.create")
    @Override
    public void onCommand(CommandArgs commandArgs) {
        Player player = commandArgs.getPlayer();
        String[] args = commandArgs.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&cPlease insert a valid name!"));
            return;
        }

        String name = args[0];
        KoTH koth = new KoTH(name);
        Selection selection = Selection.createOrGetSelection(player);

        if (selection.isFullObject()) {
            koth.setCuboid(selection.getCuboid());
            koth.setCreator(player.getName());
            koth.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            KoTH.getKoths().add(koth);
            koth.save();
            player.sendMessage(CC.translate("&aKoTH &b" + name + " &ahas been created!"));
        } else {
            player.sendMessage(CC.translate("&cPlease select a valid higher and lower locations"));
        }
    }
}
