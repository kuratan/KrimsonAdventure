package de.kuratan.krimsonadventure.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderKrimsonLands extends WorldProviderSurface {
    ChunkProviderKrimsonLands chunkProviderKrimsonLands;

    public WorldProviderKrimsonLands() {
        super();
        this.terrainType = WorldType.LARGE_BIOMES;
    }

    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 192.0F;
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    @Override
    protected void registerWorldChunkManager() {
        super.registerWorldChunkManager();
    }

    @Override
    public String getDimensionName() {
        return "Krimson Lands";
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering Krimson Lands";
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        if (chunkProviderKrimsonLands == null) {
            chunkProviderKrimsonLands = new ChunkProviderKrimsonLands(this.worldObj, this.worldObj.getSeed(), false);
        }
        return chunkProviderKrimsonLands;
    }
}
