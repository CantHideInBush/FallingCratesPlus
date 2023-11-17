package pl.karwsz.fallingcratesplus.commands.zones;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.drop.zone.DropWorld;
import pl.karwsz.fallingcratesplus.drop.zone.DropZone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayZonesCommand extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {


        DropWorld dropWorld;

        if ((dropWorld = FallingCratesPlus.getInstance().getZoneManager().findByKey(sender.getWorld())) == null || dropWorld.getObjects().isEmpty()) {
            sendConfigSuccessMessage(sender, "command-zone-display-none");
            return false;
        }

        ArrayList<String> zones = (ArrayList<String>) dropWorld.getZones().stream()
                .map(DropZone::getKey).collect(Collectors.toList());


        sender.sendMessage(zones.toString());


        return true;
    }

    @Override
    public String getName() {
        return "display";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ZoneCommand.class;
    }

    @Override
    public String getPermission() {
        return "display";
    }

    @Override
    public List<String> complete(String[] args) {
        return Collections.emptyList();
    }
}
