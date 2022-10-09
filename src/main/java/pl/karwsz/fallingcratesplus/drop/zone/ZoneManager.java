package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.managers.KeyedStorage;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collection;

public class ZoneManager implements KeyedStorage<DropWorld> {

    private ArrayList<DropWorld> worlds;

    @Override
    public Collection<DropWorld> getObjects() {
        return worlds;
    }
}
