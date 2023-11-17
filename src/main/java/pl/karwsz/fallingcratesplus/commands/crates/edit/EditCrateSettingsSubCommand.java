package pl.karwsz.fallingcratesplus.commands.crates.edit;

import com.canthideinbush.utils.commands.InternalCommand;
import org.bukkit.command.CommandSender;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;

import java.util.Collections;
import java.util.List;

public abstract class EditCrateSettingsSubCommand extends InternalCommand {
    public abstract boolean execute(CommandSender sender, String[] args, CrateSettings settings);

}
