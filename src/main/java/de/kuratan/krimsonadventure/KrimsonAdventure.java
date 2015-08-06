package de.kuratan.krimsonadventure;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import de.kuratan.krimsonadventure.block.KABlocks;
import de.kuratan.krimsonadventure.config.Config;
import de.kuratan.krimsonadventure.config.ConfigurationHandler;
import de.kuratan.krimsonadventure.lib.Reference;
import de.kuratan.krimsonadventure.world.WorldProviderKrimsonLands;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;


@Mod(modid = Reference.MOD_ID, version = Reference.MOD_VERSION, name = Reference.MOD_NAME)
public class KrimsonAdventure {

    public static CreativeTabs tab = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(KABlocks.grass);
        }
    };

    @Mod.Instance(Reference.MOD_ID)
    public static KrimsonAdventure instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        KABlocks.initialization();
        DimensionManager.registerProviderType(Config.World.worldProviderId, WorldProviderKrimsonLands.class, false);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (!DimensionManager.isDimensionRegistered(Config.World.dimensionId)) {
            DimensionManager.registerDimension(Config.World.dimensionId, Config.World.worldProviderId);
        }
    }

    @Mod.EventHandler
    public void startServer(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new KATeleportCommand());
    }
}
