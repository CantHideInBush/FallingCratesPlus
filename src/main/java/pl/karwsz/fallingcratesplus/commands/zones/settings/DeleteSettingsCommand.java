package pl.karwsz.fallingcratesplus.commands.zones.settings;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;
import pl.karwsz.fallingcratesplus.drop.zone.DropZoneSettings;

import java.util.Collections;
import java.util.List;

public class DeleteSettingsCommand extends InternalCommand  {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();

        if (DropZoneSettings.byId(id) == null) {
            sendConfigErrorMessage(sender, "command-zone-settings-delete-id-nonexistent");
            return false;
        }


        DropZoneSettings.remove(id);
        sendConfigSuccessMessage(sender, "command-zone-settings-delete-success");


        return true;
    }


    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return SettingsParentCommand.class;
    }

    @Override
    public String getPermission() {
        return getName();
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return DropZoneSettings.getIdList();
        }
        return Collections.emptyList();
    }
}
