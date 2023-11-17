package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public class EditCrateDisplayNameCommand extends EditCrateSettingsSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args, CrateSettings settings) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        StringBuilder builder = new StringBuilder();
        while (parser.hasNext()) {
            builder.append(parser.next());
        }


        settings.displayName = LegacyComponentSerializer.legacyAmpersand().deserialize(builder.toString());

        sendConfigSuccessMessage(sender, "command-crate-edit-displayName-success");

        return true;
    }


    @Override
    public String getName() {
        return "displayName";
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
