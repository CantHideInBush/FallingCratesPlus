package pl.karwsz.fallingcratesplus.commands;

import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.crates.CratesParentCommand;
import pl.karwsz.fallingcratesplus.commands.item.ItemParentCommand;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;

import java.util.ArrayList;
import java.util.Collection;

public class MainCommand extends ParentCommand {

    private final ArrayList<InternalCommand> subcommands = new ArrayList<>();

    public MainCommand(CHIBPlugin plugin) {
        super(plugin);
        subcommands.add(new ZoneCommand());
        subcommands.add(new ItemParentCommand());
        subcommands.add(new CratesParentCommand());
    }


    @Override
    public String getName() {
        return "fcp";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return null;
    }

    @Override
    public String getPermission() {
        return "FCP.Command.Main";
    }

    @Override
    public Collection<InternalCommand> getSubcommands() {
        return subcommands;
    }

}
