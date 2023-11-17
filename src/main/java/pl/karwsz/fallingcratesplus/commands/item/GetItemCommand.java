package pl.karwsz.fallingcratesplus.commands.item;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import com.canthideinbush.utils.storing.YAMLConfig;
import org.bukkit.entity.Player;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetItemCommand extends InternalCommand  {

    @Override
    public boolean execute(Player sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-item-get-path-argument");
            return false;
        }

        String path = parser.next();

        YAMLConfig config = FallingCratesPlus.getInstance().getItemsConfig();
        if (!config.contains(path) || config.isConfigurationSection(path)) {
            sendConfigErrorMessage(sender, "command-item-get-path-nonexistent");
            return false;
        }

        sender.getInventory().addItem(config.getItemStack(path));
        sendConfigSuccessMessage(sender, "command-item-get-success");



        return true;
    }

    @Override
    public String getName() {
        return "get";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ItemParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        return args.length - 1 == getArgIndex() ?
                new ArrayList<>(FallingCratesPlus.getInstance().getItemsConfig().getKeys(true))
                : Collections.emptyList();
    }
}
