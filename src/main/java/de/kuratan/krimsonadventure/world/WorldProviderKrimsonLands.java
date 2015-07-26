package de.kuratan.krimsonadventure.world;

import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderKrimsonLands extends WorldProviderSurface {
    ChunkProviderKrimsonLands chunkProviderKrimsonLands;

    @Override
    public String getDimensionName() {
        return "Krimson Lands";
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        if (chunkProviderKrimsonLands == null) {
            chunkProviderKrimsonLands = new ChunkProviderKrimsonLands(this.worldObj, this.worldObj.getSeed(), false);
        }
        return chunkProviderKrimsonLands;
    }
}
