package de.kuratan.krimsonadventure;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.kuratan.krimsonadventure.lib.Reference;


@Mod(modid = Reference.MOD_ID, version = Reference.MOD_VERSION, name = Reference.MOD_NAME)
public class KrimsonAdventure {

    @Mod.Instance(Reference.MOD_ID)
    public static KrimsonAdventure instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
