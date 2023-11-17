package pl.karwsz.fallingcratesplus;

import com.projectkorra.projectkorra.ProjectKorra;
import org.bukkit.Bukkit;

public class Hooks {

    private ProjectKorra projectKorra;
    public void initialize() {
        if (Bukkit.getPluginManager().isPluginEnabled("ProjectKorra")) projectKorra = (ProjectKorra) Bukkit.getPluginManager().getPlugin("ProjectKorra");
    }

    public boolean isProjectKorraHooked() {
        return projectKorra != null;
    }

}
