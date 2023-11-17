package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.commands.crates.CratesParentCommand;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EditCrateSettingsCommand extends ParentCommand {

    public EditCrateSettingsCommand() {
        subCommands.add(new EditCrateDisplayNameCommand());
        subCommands.add(new EditCratePermissionCommand());
        subCommands.add(new EditCrateDropCommand());
        subCommands.add(new EditCrateDropQuantityCommand());
    }



    @Override
    public boolean execute(CommandSender sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();
        CrateSettings settings;
        if ((settings = CrateSettings.getById(id)) == null) {
            sendConfigErrorMessage(sender, "command-crate-edit-nonexistent");
            return false;
        }

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String field = parser.next();
        InternalCommand subCommand;
        if ((subCommand = getSubCommand(field)) == null) {
            sendConfigErrorMessage(sender, "command-crate-edit-setting-nonexistent");
            return false;
        }

        return ((EditCrateSettingsSubCommand) subCommand).execute(sender, args, settings);
    }


    @Override
    public int getArgCount() {
        return 2;
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return CratesParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {

        if (args.length == getArgIndex() + 1) {
            return CrateSettings.getIdList();
        }

        else if (args.length == getArgIndex() + 2) {
            return getSubcommands().stream().map(InternalCommand::getName).collect(Collectors.toList());
        }

        else if (args.length >= getArgIndex() + 3) {
            InternalCommand command;
            return (command = getSubCommand(args[getArgIndex() + 1])) != null ? command.complete(args) : Collections.emptyList();
        }


        return Collections.emptyList();
    }
}
