package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public class EditCrateDropQuantityCommand extends EditCrateSettingsSubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args, CrateSettings settings) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        int quantity;
        double chance;


        try {
            quantity = parser.nextInt();
            if (!parser.hasNext()) {
                sendConfigErrorMessage(sender, "command-arguments-insufficient");
                return false;
            }
            chance = parser.nextDouble();

        } catch (NumberFormatException e) {
            sendConfigErrorMessage(sender, "incorrect_data_type");
            return false;
        }

        if (quantity <= 0 || quantity > 54) {
            sendConfigErrorMessage(sender, "command-crate-edit-quantity-incorrect_quantity");
            return false;
        }

        if (chance > 100) {
            sendConfigErrorMessage(sender, "command-crate-edit-quantity-too_high_chance");
            return false;
        }
        else if (chance <= 0) {
            settings.dropQuantity.remove(quantity);
            sendConfigSuccessMessage(sender, "command-crate-edit-quantity-removed");
            return true;
        }

        settings.dropQuantity.put(quantity, chance);
        sendConfigSuccessMessage(sender, "command-crate-edit-quantity-success");


        return true;
    }

    @Override
    public String getName() {
        return "quantity";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return EditCrateSettingsCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_amount"));
        }
        else if (args.length - 1 == getArgIndex() + 1) {
            return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_chance"));
        }

        return Collections.emptyList();
    }

}
