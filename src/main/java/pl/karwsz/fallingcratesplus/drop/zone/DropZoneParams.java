package pl.karwsz.fallingcratesplus.drop.zone;

import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;


@SerializableAs("DropZone")
public class DropZoneParams implements ABSave {

    static {ConfigurationSerialization.registerClass(DropZoneParams.class);}


    @YAMLElement

    private String regionId;







    public DropZoneParams() {}

    public DropZoneParams(Map<String, Object> map) {
        deserializeFromMap(map);
    }



    public static DropZoneParams deserialize(Map<String, Object> map) {
        return new DropZoneParams(map);
    }




}
