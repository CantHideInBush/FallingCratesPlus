package pl.karwsz.fallingcratesplus.commands.zones;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.MainCommand;
import pl.karwsz.fallingcratesplus.commands.zones.settings.CreateSettingsCommand;
import pl.karwsz.fallingcratesplus.commands.zones.settings.SettingsParentCommand;

import java.util.ArrayList;
import java.util.Collection;

public class ZoneCommand extends ParentCommand {

    public ZoneCommand() {
        this.subCommands.add(new CreateZoneCommand());
        this.subCommands.add(new SettingsParentCommand());
        this.subCommands.add(new DisplayZonesCommand());
    }

    @Override
    public String getName() {
        return "zone";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }
    @Override
    public String getPermission() {
        return getName();
    }


}
