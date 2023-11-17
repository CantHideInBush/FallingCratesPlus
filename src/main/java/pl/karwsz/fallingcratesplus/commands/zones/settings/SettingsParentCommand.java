package pl.karwsz.fallingcratesplus.commands.zones.settings;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;
import pl.karwsz.fallingcratesplus.commands.zones.settings.CreateSettingsCommand;

import java.util.ArrayList;
import java.util.Collection;

public class SettingsParentCommand extends ParentCommand {

    private final ArrayList<InternalCommand> subCommands = new ArrayList<>();

    public SettingsParentCommand() {
        subCommands.add(new CreateSettingsCommand());
        subCommands.add(new DeleteSettingsCommand());
        subCommands.add(new EditSettingsCommand());
    }


    @Override
    public Collection<InternalCommand> getSubcommands() {
        return subCommands;
    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return ZoneCommand.class;
    }

    @Override
    public String getPermission() {
        return getName();
    }
}
