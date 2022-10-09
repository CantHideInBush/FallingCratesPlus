package pl.karwsz.fallingcratesplus;

import com.canthideinbush.utils.CHIBPlugin;
import com.canthideinbush.utils.UtilsProvider;
import com.canthideinbush.utils.chat.ChatUtils;
import com.canthideinbush.utils.commands.InternalCommand;
import com.canthideinbush.utils.storing.YAMLConfig;
import org.bukkit.ChatColor;
import pl.karwsz.fallingcratesplus.commands.MainCommand;

import java.util.Arrays;

public class FallingCratesPlus extends CHIBPlugin {

    private static FallingCratesPlus instance;

    private YAMLConfig messagesConfig;


    public static UtilsProvider utils;

    private static void initUtils() {
        utils = new UtilsProvider();
        utils.setChatUtils(new ChatUtils(instance));
    }


    @Override
    public void onEnable() {
        FallingCratesPlus.instance = this;
        initUtils();
        loadStorages();


        loadCommands();
    }


    @Override
    public void onDisable() {
        saveStorages();
    }


    private void loadStorages() {
        messagesConfig = new YAMLConfig(this, "messages", true);
    }

    private void saveStorages() {
        messagesConfig.save();
    }


    public String getPrefix() {
        return String.format("%s[%sFCP%s]", ChatColor.GOLD, ChatColor.GRAY, ChatColor.GOLD);
    }

    @Override
    public YAMLConfig getMessageConfig() {
        return messagesConfig;
    }

    @Override
    public UtilsProvider getUtilsProvider() {
        return utils;
    }

    public static FallingCratesPlus getInstance() {
        return FallingCratesPlus.instance;
    }

    public static YAMLConfig getMessagesConfig() {
        return FallingCratesPlus.getMessagesConfig();
    }



    private void loadCommands() {
        new MainCommand(this);
    }





}
