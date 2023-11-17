package pl.karwsz.fallingcratesplus.commands.crates;

import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.MainCommand;
import pl.karwsz.fallingcratesplus.commands.crates.edit.EditCrateSettingsCommand;

public class CratesParentCommand extends ParentCommand {

    public CratesParentCommand() {
        subCommands.add(new CreateCrateSettingsCommand());
        subCommands.add(new DeleteCrateSettingsCommand());
        subCommands.add(new EditCrateSettingsCommand());
        subCommands.add(new SpawnCrateCommand());
    }

    @Override
    public String getName() {
        return "crate";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }

}
