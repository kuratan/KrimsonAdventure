package de.kuratan.krimsonadventure.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    protected static void loadConfiguration() {
        Config.World.dimensionId = configuration.getInt("world.dimensionid", Configuration.CATEGORY_GENERAL, 92, -999, 999, "Dimension Id");
        Config.World.worldProviderId = configuration.getInt("world.worldproviderid", Configuration.CATEGORY_GENERAL, -999, -999, 999, "Provider Id");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
