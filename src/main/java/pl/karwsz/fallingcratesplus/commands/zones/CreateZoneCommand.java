package pl.karwsz.fallingcratesplus.commands.zones;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.commands.zones.settings.SettingsParentCommand;
import pl.karwsz.fallingcratesplus.drop.zone.DropWorld;
import pl.karwsz.fallingcratesplus.drop.zone.DropZone;
import pl.karwsz.fallingcratesplus.drop.zone.DropZoneSettings;

import java.util.Collections;
import java.util.List;

public class CreateZoneCommand extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String region = parser.next();


        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String settings = parser.next();

        World world = sender.getWorld();

        DropWorld dropWorld = FallingCratesPlus.getInstance().getZoneManager().findByKey(world);

        if (dropWorld == null) {
            FallingCratesPlus.getInstance().getZoneManager().register(dropWorld = new DropWorld(world));
        }

        if (dropWorld.findByKey(region) != null) {
            sendConfigErrorMessage(sender, "command-zone-create-already-exists", region);
            return false;
        }
        dropWorld.register(new DropZone(region, DropZoneSettings.byId(settings)));

        sendConfigSuccessMessage(sender, "command-zone-create-success");

        return true;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ZoneCommand.class;
    }

    @Override
    public String getPermission() {
        return "create";
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length == getArgIndex() + 1) {
            return Collections.singletonList(FallingCratesPlus.getInstance().getUtilsProvider().chat.getMessage("command-zone-create-tabc-name"));
        }
        else if (args.length == getArgIndex() + 2) {
            return DropZoneSettings.getIdList();
            //return Collections.singletonList(FallingCratesPlus.getInstance().getUtilsProvider().chat.getMessage("command-zone-create-tabc-preset"));
        }
        return Collections.emptyList();
    }
}
