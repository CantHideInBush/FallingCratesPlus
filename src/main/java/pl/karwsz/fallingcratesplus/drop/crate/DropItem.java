package pl.karwsz.fallingcratesplus.drop.crate;

import com.canthideinbush.utils.ChanceMap;
import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.serialization.SerializableAs;
import pl.karwsz.fallingcratesplus.FallingCratesPlus;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("DropItem")
public class DropItem implements ABSave {

    public DropItem(String id, double chance) {
        this.id = id;
        this.chance = chance;
    }

    public DropItem(Map<String, Object> map) {
        deserializeFromMap(map);
        updateDropMap();
    }


    @YAMLElement
    private String id;


    @YAMLElement
    public Map<Integer, Double> dropChances = new HashMap<>();



    @YAMLElement
    public double chance;

    public double getChance() {
        return chance;
    }

    private final ChanceMap<Integer> dropMap = new ChanceMap<>();

    private void updateDropMap() {
        dropMap.clear();
        dropChances.forEach(dropMap::add);
    }

    public void addChance(Integer amount, Double chance) {
        dropChances.put(amount, chance);
        updateDropMap();
    }

    public void removeChance(Integer amount) {
        dropChances.remove(amount);
        updateDropMap();
    }

    public String getId() {
        return id;
    }

    public ItemStack getItem() {
        return FallingCratesPlus.getInstance().getSavedItem(id);
    }

    public ItemStack getRandomAmount() {
        ItemStack item = getItem();
        item.setAmount(dropMap.getRandom());
        return item;
    }


}
