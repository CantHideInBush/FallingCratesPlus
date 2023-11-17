package pl.karwsz.fallingcratesplus.commands.crates;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public class DeleteCrateSettingsCommand extends InternalCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();
        if (!CrateSettings.deleteById(id)) {
            sendConfigErrorMessage(sender, "command-crate-delete-nonexistent");
            return false;
        }

        sendConfigSuccessMessage(sender, "command-crate-delete-success");

        return true;
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return CratesParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return CrateSettings.getIdList();
        }
        return Collections.emptyList();
    }
}
