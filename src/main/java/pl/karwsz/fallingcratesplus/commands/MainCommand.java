package pl.karwsz.fallingcratesplus.commands;

import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.UtilsProvider;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainCommand extends ParentCommand {

    private final ArrayList<InternalCommand> subcommands = new ArrayList<>();

    public MainCommand(CHIBPlugin plugin) {
        super(plugin);
        subcommands.add(new ZoneCommand());
    }

    @Override
    public UtilsProvider getUtilsProvider() {
        return FallingCratesPlus.utils;
    }


    @Override
    public String getName() {
        return "fcp";
    }

    @Override
    public InternalCommand getParentCommand() {
        return null;
    }

    @Override
    public String getPermission() {
        return "FCP.Command.Main";
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        return false;
    }

    @Override
    public Collection<InternalCommand> getSubcommands() {
        return subcommands;
    }

}
