package pl.karwsz.fallingcratesplus.commands.zones;

import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.MainCommand;

import java.util.ArrayList;
import java.util.Collection;

public class ZoneCommand extends ParentCommand {

    private final ArrayList<InternalCommand> subCommands = new ArrayList<>();

    @Override
    public String getName() {
        return "zone";
    }

    @Override
    public InternalCommand getParentCommand() {
        return CHIBCommandsRegistry.get(MainCommand.class);
    }

    @Override
    public String getPermission() {
        return getName();
    }

    @Override
    public Collection<InternalCommand> getSubcommands() {
        return subCommands;
    }

}
