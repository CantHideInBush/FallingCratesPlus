package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.Map;

@SerializableAs("DropWorld")
public class DropWorld implements Keyed<World>, ABSave {

    static {ConfigurationSerialization.registerClass(DropWorld.class);}

    @YAMLElement
    private World world;

    @Override
    public World getKey() {
        return world;
    }

    @YAMLElement
    private ArrayList<DropZone> zones;


    public DropWorld(Map<String, Object> map) {
        deserializeFromMap(map);
    }


    public DropWorld(World world) {
        this.world = world;
        this.zones = new ArrayList<>();
    }


    public World getWorld() {
        return world;
    }

    public ArrayList<DropZone> getZones() {
        return zones;
    }



}
