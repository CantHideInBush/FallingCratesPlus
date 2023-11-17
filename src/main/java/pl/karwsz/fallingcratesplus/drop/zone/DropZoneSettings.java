package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.bukkit.Material.COBBLESTONE;
import static org.bukkit.Material.DIRT;
import static org.bukkit.Material.GRASS_BLOCK;
import static org.bukkit.Material.SAND;
import static org.bukkit.Material.SANDSTONE;
import static org.bukkit.Material.STONE;


@SerializableAs("DropZoneSettings")
public class DropZoneSettings implements ABSave {



    public DropZoneSettings(String id) {
        this.id = id;
    }

    private String id;


    //Time unit: MINECRAFT TICK

    @YAMLElement
    public int dropCooldown = 20 * 60;

    @YAMLElement
    public int maxActiveDrops = 5;

    @YAMLElement
    public boolean rightClickOpen = true;

    @YAMLElement
    public int minimalPlayersRequired = 2;

    @YAMLElement
    public int belowMinimalCooldown = 20 * 180;

    @YAMLElement
    public Collection<Material> dropTerrain = new ArrayList<>(Arrays.asList(GRASS_BLOCK, DIRT, STONE, COBBLESTONE, SAND, SANDSTONE));

    @YAMLElement
    public int unbreakableRadius = 2;

    @YAMLElement
    public boolean fallAnimation = true;


    public String getId() {
        return id;
    }

    public DropZoneSettings(Map<String, Object> map) {
        deserializeFromMap(map);
    }





    public void save() {
        FallingCratesPlus.getInstance().getZonesConfig().set("ZoneSettings." + id, this);
    }

    public void remove() {
        remove(id);
    }

    public static void remove(String id) {
        FallingCratesPlus.getInstance().getZonesConfig().set("ZoneSettings." + id, null);
    }

    public static DropZoneSettings byId(String id) {
        DropZoneSettings settings = (DropZoneSettings) FallingCratesPlus.getInstance().getZonesConfig().get("ZoneSettings." + id);
        if (settings != null) {
            settings.id = id;
        }
        return settings;
    }

    public static List<String> getIdList() {
        if (FallingCratesPlus.getInstance().getZonesConfig().contains("ZoneSettings"))
            return new ArrayList<>(FallingCratesPlus.getInstance().getZonesConfig().getConfigurationSection("ZoneSettings").getKeys(false));

        return Collections.emptyList();
    }




}
