package pl.karwsz.fallingcratesplus.commands.item;

import com.canthideinbush.utils.commands.CHIBCommandsRegistry;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.commands.ParentCommand;
import pl.karwsz.fallingcratesplus.commands.MainCommand;

public class ItemParentCommand extends ParentCommand  {

    public ItemParentCommand() {
        subCommands.add(new CreateItemCommand());
        subCommands.add(new DeleteItemPathCommand());
        subCommands.add(new GetItemCommand());
    }

    @Override
    public String getName() {
        return "item";
    }

    @Override
    public Class<? extends InternalCommand> getParentCommandClass() {
        return MainCommand.class;
    }

}
