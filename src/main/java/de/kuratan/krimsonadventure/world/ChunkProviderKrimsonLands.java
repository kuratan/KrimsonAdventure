package de.kuratan.krimsonadventure.world;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;
import java.util.Random;

public class ChunkProviderKrimsonLands implements IChunkProvider {

    Random random;
    World worldObj;
    long seed;
    boolean mapFeaturesEnabled;

    ChunkProviderKrimsonLands(World world, long seed, boolean mapFeaturesEnabled) {
        this.worldObj = world;
        this.seed = seed;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.random = new Random(seed);
    }

    @Override
    public boolean chunkExists(int cx, int cz) {
        return true;
    }

    @Override
    public Chunk provideChunk(int cx, int cz) {
        Block[] blockStorage= new Block[16 * 16 * 256];
        byte[] metaStorage = new byte[16 * 16 * 256];

        for (int i = 0; i < blockStorage.length/2; i++) {
            blockStorage[i] = Blocks.dirt;
        }

        Chunk chunk = new Chunk(this.worldObj, blockStorage, metaStorage, cx, cz);
        return chunk;
    }

    @Override
    public Chunk loadChunk(int cx, int cz) {
        return this.provideChunk(cx, cz);
    }

    @Override
    public void populate(IChunkProvider iChunkProvider, int cx, int cz) {

    }

    @Override
    public boolean saveChunks(boolean b, IProgressUpdate iProgressUpdate) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public String makeString() {
        return "RandomLevelSource";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType enumCreatureType, int i, int i1, int i2) {
        return null;
    }

    @Override
    public ChunkPosition func_147416_a(World world, String s, int i, int i1, int i2) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int cx, int cz) {

    }

    @Override
    public void saveExtraData() {

    }
}
