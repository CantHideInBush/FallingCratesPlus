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

public class DeleteItemPathCommand extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-item-delete-path-path-argument");
            return false;
        }

        String path = parser.next();

        YAMLConfig config = FallingCratesPlus.getInstance().getItemsConfig();
        if (!config.contains(path)) {
            sendConfigErrorMessage(sender, "command-item-delete-path-path-nonexistent");
            return false;
        }


        config.set(path, null);
        sendConfigSuccessMessage(sender, "command-item-delete-path-success");

        return true;
    }

    @Override
    public String getName() {
        return "deletepath";
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
