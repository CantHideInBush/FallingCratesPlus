package pl.karwsz.fallingcratesplus.drop.crate;

import com.canthideinbush.utils.storing.ABSave;
import com.canthideinbush.utils.storing.YAMLElement;

public class CrateParams implements ABSave {


    public CrateParams() {}

    @YAMLElement
    public String name;



}
