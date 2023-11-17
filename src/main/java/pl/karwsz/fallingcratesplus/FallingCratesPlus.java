package pl.karwsz.fallingcratesplus;

import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.storing.YAMLConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import pl.karwsz.fallingcratesplus.commands.MainCommand;
import pl.karwsz.fallingcratesplus.drop.crate.CrateSettings;
import pl.karwsz.fallingcratesplus.drop.crate.DropItem;
import pl.karwsz.fallingcratesplus.drop.zone.DropWorld;
import pl.karwsz.fallingcratesplus.drop.zone.DropZoneSettings;
import pl.karwsz.fallingcratesplus.drop.zone.ZoneManager;

public class FallingCratesPlus extends CHIBPlugin {

    static { ConfigurationSerialization.registerClass(DropZoneSettings.class);
        ConfigurationSerialization.registerClass(DropWorld.class);
        ConfigurationSerialization.registerClass(CrateSettings.class);
        ConfigurationSerialization.registerClass(DropItem.class);
    }


    private Hooks hooks;
    private static FallingCratesPlus instance;

    private YAMLConfig messagesConfig;

    private YAMLConfig zonesConfig;

    private YAMLConfig itemsConfig;

    private YAMLConfig dropConfig;


    private ZoneManager zoneManager;





    @Override
    public void onEnable() {
        FallingCratesPlus.instance = this;

        (hooks = new Hooks()).initialize();

        loadStorages();
        CHIBInit();
        loadManagers();


        loadCommands();
    }


    @Override
    public void onDisable() {
        saveManagers();
        saveStorages();
    }


    private void loadStorages() {
        messagesConfig = new YAMLConfig(this, "messages", true);
        zonesConfig = new YAMLConfig(this, "zones");
        itemsConfig = new YAMLConfig(this, "items");
        dropConfig = new YAMLConfig(this, "dropconfig");
    }

    private void saveStorages() {
        messagesConfig.save();
        zonesConfig.save();
        itemsConfig.save();
        dropConfig.save();
    }


    private void loadManagers() {
        this.zoneManager = new ZoneManager();

    }

    private void saveManagers() {
        this.zoneManager.save();
    }






    public String getPrefix() {
        return String.format("%s[%sFCP%s]", ChatColor.GOLD, ChatColor.GRAY, ChatColor.GOLD);
    }

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    @Override
    public YAMLConfig getMessageConfig() {
        return messagesConfig;
    }

    public YAMLConfig getZonesConfig() {
        return zonesConfig;
    }

    public YAMLConfig getItemsConfig() {
        return itemsConfig;
    }

    public YAMLConfig getDropConfig() {
        return dropConfig;
    }

    public static FallingCratesPlus getInstance() {
        return FallingCratesPlus.instance;
    }


    public Hooks getHooks() {
        return hooks;
    }

    private void loadCommands() {
        new MainCommand(this);
    }

    public ItemStack getSavedItem(String id) {
        return itemsConfig.getItemStack(id, new ItemStack(Material.AIR));
    }





}
