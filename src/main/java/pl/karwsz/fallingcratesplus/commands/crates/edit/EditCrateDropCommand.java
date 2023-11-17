package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;
import pl.karwsz.fallingcratesplus.drop.crate.DropItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EditCrateDropCommand extends EditCrateSettingsSubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args, CrateSettings settings) {

        ArgParser parser = new ArgParser(args, getArgIndex());


        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String arg1 = parser.next();
        switch (arg1.toLowerCase()) {
            case "add" -> {
                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }
                String id = parser.next();
                if (settings.getDropItem(id) != null) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-add-id_taken", settings.id);
                    return false;
                }

                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }

                double chance;
                try {
                    chance = parser.nextDouble();
                } catch (NumberFormatException e) {
                    sendConfigErrorMessage(sender, "incorrect_data_type");
                    return false;
                }

                if (chance > 100 || chance <= 0) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-add-soe_100_gt_0");
                    return false;
                }

                settings.addDropItem(new DropItem(id, chance));
                sendConfigSuccessMessage(sender, "command-crate-edit-drop-add-success");
                return true;
            }
            case "delete" -> {
                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }

                DropItem item;
                if ((item = settings.getDropItem(parser.next())) == null) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-drop_nonexistent");
                    return false;
                }
                settings.removeDropItem(item.getId());
                sendConfigSuccessMessage(sender, "command-crate-edit-drop-delete-success");

                return true;
            }
            case "chance" -> {
                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }

                DropItem item;
                if ((item = settings.getDropItem(parser.next())) == null) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-drop_nonexistent");
                    return false;
                }


                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }

                double chance;
                try {
                    chance = parser.nextDouble();
                } catch (NumberFormatException e) {
                    sendConfigErrorMessage(sender, "incorrect_data_type");
                    return false;
                }

                if (chance > 100 || chance <= 0) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-add-soe_100_gt_0");
                    return false;
                }

                item.chance = chance;

                sendConfigSuccessMessage(sender, "command-crate-edit-drop-chance-success");

                return true;

            }

            case "amountchance" -> {
                if (!parser.hasNext()) {
                    sendConfigErrorMessage(sender, "command-arguments-insufficient");
                    return false;
                }

                DropItem item;
                if ((item = settings.getDropItem(parser.next())) == null) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-drop_nonexistent");
                    return false;
                }

                int amount;
                double chance;
                try {
                    if (!parser.hasNext()) {
                        sendConfigErrorMessage(sender, "command-arguments-insufficient");
                        return false;
                    }
                    amount = parser.nextInt();
                    if (!parser.hasNext()) {
                        sendConfigErrorMessage(sender, "command-arguments-insufficient");
                        return false;
                    }
                    chance = parser.nextDouble();


                } catch (NumberFormatException e) {
                    sendConfigErrorMessage(sender, "incorrect_data_type");
                    return false;
                }

                if (chance > 100 || chance <= 0) {
                    sendConfigErrorMessage(sender, "command-crate-edit-drop-soe_100_gt_0");
                    return false;
                }

                if (chance == 0) {
                    item.removeChance(amount);
                }
                else item.addChance(amount, chance);

                sendConfigSuccessMessage(sender, "command-crate-edit-drop-amountChance-success");

                return true;
            }



        }

        return true;
    }

    @Override
    public String getName() {
        return "drop";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return EditCrateSettingsCommand.class;
    }

    private static final List<String> argOptions = Arrays.asList("add", "delete", "chance", "amountchance");

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) {
            return argOptions;
        }
        String option;
        if (args.length - 1 > getArgIndex()) {
            option = args[getArgIndex()];
            if (!argOptions.contains(option.toLowerCase())) return Collections.emptyList();

            if (option.equalsIgnoreCase("add")) {
                if (args.length - 1 == getArgIndex() + 1) {
                    return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_id"));
                }
                else if (args.length - 1 == getArgIndex() + 2) {
                    return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_chance"));
                }
            }
            else if (option.equalsIgnoreCase("delete")) {
                if (args.length - 1 == getArgIndex() + 1) {
                    CrateSettings settings = CrateSettings.getById(args[getParentCommand().getArgIndex()]);
                    return settings.getDrop().stream().map(DropItem::getId).collect(Collectors.toList());
                }
            }
            else if (option.equalsIgnoreCase("chance")) {
                if (args.length - 1 == getArgIndex() + 1) {
                    CrateSettings settings = CrateSettings.getById(args[getParentCommand().getArgIndex()]);
                    return settings.getDrop().stream().map(DropItem::getId).collect(Collectors.toList());
                }
                else if (args.length - 1 == getArgIndex() + 2) {
                    return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_chance"));
                }
            }
            else if (option.equalsIgnoreCase("amountChance")) {
                if (args.length - 1 == getArgIndex() + 1) {
                    CrateSettings settings = CrateSettings.getById(args[getParentCommand().getArgIndex()]);
                    return settings.getDrop().stream().map(DropItem::getId).collect(Collectors.toList());
                }
                else if (args.length - 1 == getArgIndex() + 2) {
                    return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_amount"));
                }
                else if (args.length - 1 == getArgIndex() + 3) {
                    return Collections.singletonList(getUtilsProvider().chat.getMessage("command-crate-edit-drop-tabcomplete_chance"));
                }
            }

        }


        return Collections.emptyList();
    }

}
