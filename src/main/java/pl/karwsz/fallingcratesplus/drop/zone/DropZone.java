package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.managers.Keyed;
import com.canthideinbush.utils.managers.KeyedStorage;
import com.canthideinbush.utils.storing.YAMLElement;
import pl.karwsz.fallingcratesplus.drop.crate.Crate;

import java.util.ArrayList;
import java.util.Collection;

public class DropZone implements Keyed<String>, KeyedStorage<Crate> {



    public DropZone(String regionId, DropZoneSettings settings) {
        this.regionId = regionId;
        this.settings = settings;
    }

    private final ArrayList<Crate> crates = new ArrayList<>();

    private DropZoneSettings settings;

    public DropZoneSettings getSettings() {
        return settings;
    }

    @YAMLElement
    private String regionId;

    public String getRegionId() {
        return regionId;
    }

    @Override
    public String getKey() {
        return regionId;
    }

    @Override
    public Collection<Crate> getObjects() {
        return crates;
    }
}
