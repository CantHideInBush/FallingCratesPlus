package pl.karwsz.fallingcratesplus.drop.crate;

import com.canthideinbush.utils.ChanceMap;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLConfig;
import com.canthideinbush.utils.storing.YAMLElement;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.serialization.SerializableAs;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SerializableAs("CrateSettings")
public class CrateSettings implements ABSave {


    public CrateSettings(String id) {
        this.id = id;
    }

    public CrateSettings(Map<String, Object> map) {
        deserializeFromMap(map);
        updateChanceMap();
    }

    public String id;

    @YAMLElement
    public Component displayName = Component.text("");

    @YAMLElement
    public String permission = "";

    @YAMLElement
    public HashMap<Integer, Double> dropQuantity = new HashMap<>();

    @YAMLElement
    private List<DropItem> drop = new ArrayList<>();

    public List<DropItem> getDrop() {
        return drop;
    }

    private final ChanceMap<DropItem> dropMap = new ChanceMap<>();

    private void updateChanceMap() {
        dropMap.clear();
        drop.forEach(d -> dropMap.add(d, d.getChance()));
    }

    public void addDropItem(DropItem dropItem) {
        drop.add(dropItem);
        updateChanceMap();
    }

    public DropItem getDropItem(String id) {
        return drop.stream().filter(i -> i.getId().equalsIgnoreCase(id)).findAny().orElse(null);
    }

    public DropItem getRandomDropItem() {

        return dropMap.getRandom();
    }

    public void removeDropItem(String id) {
        drop.removeIf(d -> d.getId().equalsIgnoreCase(id));
        updateChanceMap();
    }


    public int getRandomDropQuantity() {
        return new ChanceMap<>(dropQuantity).getRandom();
    }



    private static final String CRATES_PATH = "Crates.";
    private static final String DROP_ITEMS_PATH = "DropItem.";

    public void save() {
        FallingCratesPlus.getInstance().getDropConfig().set(CRATES_PATH + id, this);
    }

    public static CrateSettings getById(String id) {
        CrateSettings settings = FallingCratesPlus.getInstance().getDropConfig().getObject(CRATES_PATH + id, CrateSettings.class);
        if (settings != null) {
            settings.id = id;
        }
        return settings;
    }

    public static boolean deleteById(String id) {
        if (FallingCratesPlus.getInstance().getDropConfig().contains(CRATES_PATH + id)) {
            FallingCratesPlus.getInstance().getDropConfig().set(CRATES_PATH + id, null);
            return true;
        }
        return false;
    }

    public static List<String> getIdList() {
        YAMLConfig config = FallingCratesPlus.getInstance().getDropConfig();
        if (config.contains("Crates")) {
            return new ArrayList<>(config.getConfigurationSection("Crates").getKeys(false));
        }
        return Collections.emptyList();
    }

}
