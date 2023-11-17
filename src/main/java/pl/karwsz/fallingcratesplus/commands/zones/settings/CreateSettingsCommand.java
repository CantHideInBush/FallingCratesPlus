package pl.karwsz.fallingcratesplus.commands.zones.settings;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;
import pl.karwsz.fallingcratesplus.drop.zone.DropZoneSettings;

import java.util.Collections;
import java.util.List;

public class CreateSettingsCommand extends InternalCommand {


    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();

        if (DropZoneSettings.byId(id) != null) {
            sendConfigErrorMessage(sender, "command-zone-settings-create-id-taken");
            return false;
        }


        sendConfigSuccessMessage(sender, "command-zone-settings-create-success");

        new DropZoneSettings(id).save();

        return true;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return SettingsParentCommand.class;
    }

    @Override
    public String getPermission() {
        return "create";
    }

    @Override
    public List<String> complete(String[] args) {

        if (args.length == getArgIndex() + 1) {
            return Collections.singletonList(" ");
        }


        return Collections.emptyList();
    }
}
