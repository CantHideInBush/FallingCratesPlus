package pl.karwsz.fallingcratesplus.commands.zones.settings;

import com.canthideinbush.utils.Reflector;
import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import com.canthideinbush.utils.storing.ArgParser;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.commands.zones.ZoneCommand;
import pl.karwsz.fallingcratesplus.drop.zone.DropZoneSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EditSettingsCommand extends InternalCommand  {


    @Override
    public boolean execute(CommandSender sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        DropZoneSettings settings;
        if ((settings = DropZoneSettings.byId(parser.next())) == null) {
            sendConfigErrorMessage(sender, "command-zone-settings-nonexistent");
            return false;
        }

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String field = parser.next();

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }


        String successMessage = "command-zone-settings-edit-success";


        try {
            switch (field.toLowerCase()) {
                case "dropcooldown" -> settings.dropCooldown = parser.nextInt();
                case "maxactivedrops" -> settings.maxActiveDrops = parser.nextInt();
                case "rightclickopen" -> settings.rightClickOpen = parser.nextBoolean();
                case "minimalplayersrequired" -> settings.minimalPlayersRequired = parser.nextInt();
                case "belowminimalcooldown" -> settings.belowMinimalCooldown = parser.nextInt();
                case "dropterrain" -> {
                    Material m = parser.nextMaterial();
                    if (settings.dropTerrain.contains(m)) {
                        settings.dropTerrain.remove(m);
                        successMessage = "command-zone-settings-edit-material-removed";
                    } else {
                        settings.dropTerrain.add(m);
                        successMessage = "command-zone-settings-edit-material-added";
                    }
                }
                case "unbreakableradius" -> settings.unbreakableRadius = parser.nextInt();
                case "fallAnimation" -> settings.fallAnimation = parser.nextBoolean();
                default -> {
                    sendConfigErrorMessage(sender, "command-zone-settings-edit-setting-nonexistent");
                    return false;
                }
            }
        } catch (IllegalArgumentException e) {
            sendConfigErrorMessage(sender, "command-zone-settings-edit-incorrect-data-type");
            return false;
        }

        settings.save();

        sendConfigSuccessMessage(sender, successMessage, field, args[getArgIndex() + 2]);

        return true;
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return SettingsParentCommand.class;
    }

    @Override
    public String getPermission() {
        return getName();
    }

    private static final List<String> settings = Arrays.asList("dropCooldown",
            "maxActiveDrops",
            "rightClickOpen",
            "minimalPlayersRequired",
            "belowMinimalCooldown",
            "dropTerrain",
            "unbreakableRadius",
            "fallAnimation");

    @Override
    public List<String> complete(String[] args) {

        if (args.length == getArgIndex() + 1) {
            return DropZoneSettings.getIdList();
        }
        else if (args.length == getArgIndex() + 2) {
            return settings;
        }
        else if (args.length == getArgIndex() + 3) {
            String id = args[getArgIndex()];
            String field = args[getArgIndex() + 1];
            DropZoneSettings settings;
            if ((settings = DropZoneSettings.byId(id)) != null) {
                if (field.equalsIgnoreCase("dropTerrain"))
                return Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList());
                else {
                    return Reflector.containsField(field, DropZoneSettings.class) ? Collections.singletonList(Reflector.getValue(field, settings).toString()) : Collections.emptyList();
                }
            }


           return Collections.singletonList(" ");
        }


        return Collections.emptyList();
    }
}
