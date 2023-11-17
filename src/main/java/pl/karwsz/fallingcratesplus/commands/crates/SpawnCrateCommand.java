package pl.karwsz.fallingcratesplus.commands.crates;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.ArgParser;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;
import pl.karwsz.fallingcratesplus.drop.crate.Crate;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;
import pl.karwsz.fallingcratesplus.drop.zone.DropZone;

import java.util.Collections;
import java.util.List;

public class SpawnCrateCommand extends InternalCommand {

    @Override
    public boolean execute(Player sender, String[] args) {

        ArgParser parser = new ArgParser(args, getArgIndex());

        if (!parser.hasNext()) {
            sendConfigErrorMessage(sender, "command-arguments-insufficient");
            return false;
        }

        String id = parser.next();
        CrateSettings settings;
        if ((settings = CrateSettings.getById(id)) == null) {
            sendConfigErrorMessage(sender, "command-crate-spawn-crate_nonexistent");
            return false;
        }

        DropZone zone = FallingCratesPlus.getInstance().getZoneManager().findByLocation(sender.getLocation());

        if (zone == null) {
            sendConfigErrorMessage(sender, "command-crate-spawn-zone_nonexistent");
            return false;
        }


        new Crate(zone, settings, sender.getTargetBlock(5).getRelative(BlockFace.UP)).spawn();


        return true;
    }

    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return CratesParentCommand.class;
    }

    @Override
    public List<String> complete(String[] args) {
        if (args.length - 1 == getArgIndex()) return CrateSettings.getIdList();
        return Collections.emptyList();
    }
}
