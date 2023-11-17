package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public class EditCratePermissionCommand extends EditCrateSettingsSubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args, CrateSettings settings) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        settings.permission = parser.next();

        sendConfigSuccessMessage(sender, "command-crate-edit-permission-success");


        return true;
    }

    @Override
    public String getName() {
        return "permission";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return EditCrateSettingsCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList(" ");
        }
        return Collections.emptyList();
    }
}
