package pl.karwsz.fallingcratesplus.commands.crates;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public class CreateCrateSettingsCommand extends InternalCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();

        if (CrateSettings.getById(id) != null) {
            sendConfigErrorMessage(sender, "command-crate-create-id-taken");
            return false;
        }

        new CrateSettings(id).save();

        sendConfigSuccessMessage(sender, "command-crate-create-success");

        return true;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return CratesParentCommand.class;
    }
    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList(" ");
        }
        return Collections.emptyList();
    }
}
