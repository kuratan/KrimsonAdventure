package de.kuratan.krimsonadventure.world;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;

import java.util.List;
import java.util.Random;

public class ChunkProviderKrimsonLands implements IChunkProvider {

    public static final int CHUNK_X = 16;
    public static final int CHUNK_Z = 16;

    BiomeGenBase[] biomesForGeneration;
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenBase ravineGenerator = new MapGenRavine();
    NoiseGeneratorOctaves noiseGeneratorOctaves0;
    NoiseGeneratorOctaves noiseGeneratorOctaves1;
    NoiseGeneratorOctaves noiseGeneratorOctaves2;
    NoiseGeneratorOctaves noiseGeneratorOctaves3;
    NoiseGeneratorOctaves noiseGeneratorOctaves4;
    NoiseGeneratorOctaves noiseGeneratorOctaves5;
    NoiseGeneratorOctaves noiseGeneratorOctaves6;
    NoiseGeneratorOctaves noiseGeneratorOctaves7;
    NoiseGeneratorOctaves noiseGeneratorOctaves8;
    NoiseGeneratorOctaves noiseGeneratorOctaves9;
    NoiseGeneratorPerlin noiseGeneratorPerlin0;
    NoiseGeneratorPerlin noiseGeneratorPerlin1;
    NoiseGeneratorSimplex noiseGeneratorSimplex0;
    Random random;
    World worldObj;
    long seed;
    boolean mapFeaturesEnabled;
    double[] noiseField;
    float[] parabolicField;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    private double[] stoneNoise = new double[256];

    ChunkProviderKrimsonLands(World world, long seed, boolean mapFeaturesEnabled) {
        this.worldObj = world;
        this.seed = seed;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.random = new Random(seed);
        this.noiseGeneratorOctaves0 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGeneratorOctaves1 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGeneratorOctaves2 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGeneratorOctaves3 = new NoiseGeneratorOctaves(this.random, 10);
        this.noiseGeneratorOctaves4 = new NoiseGeneratorOctaves(this.random, 8);
        this.noiseGeneratorOctaves5 = new NoiseGeneratorOctaves(this.random, 8);
        this.noiseGeneratorOctaves6 = new NoiseGeneratorOctaves(this.random, 6);
        this.noiseGeneratorOctaves7 = new NoiseGeneratorOctaves(this.random, 6);
        this.noiseGeneratorOctaves8 = new NoiseGeneratorOctaves(this.random, 4);
        this.noiseGeneratorOctaves9 = new NoiseGeneratorOctaves(this.random, 4);
        this.noiseGeneratorPerlin0 = new NoiseGeneratorPerlin(this.random, 8);
        this.noiseGeneratorPerlin1 = new NoiseGeneratorPerlin(this.random, 4);
        this.noiseGeneratorSimplex0 = new NoiseGeneratorSimplex(this.random);
        this.noiseField = new double[825];
        this.parabolicField = new float[25];

        for(int noiseGens = -2; noiseGens <= 2; ++noiseGens) {
            for(int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.sqrt_float((float)(noiseGens * noiseGens + k * k) + 0.2F);
                this.parabolicField[noiseGens + 2 + (k + 2) * 5] = f;
            }
        }
    }

    protected void updateNoiseFields(int p1, int p2, int p3) {
        this.field_147426_g = this.noiseGeneratorOctaves0.generateNoiseOctaves(this.field_147426_g, p1, p3, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.noiseGeneratorOctaves3.generateNoiseOctaves(this.field_147427_d, p1, p2, p3, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.noiseGeneratorOctaves1.generateNoiseOctaves(this.field_147428_e, p1, p2, p3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.noiseGeneratorOctaves2.generateNoiseOctaves(this.field_147425_f, p1, p2, p3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int l = 0;
        int i1 = 0;

        for(int j1 = 0; j1 < 5; ++j1) {
            for(int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for(int d12 = -b0; d12 <= b0; ++d12) {
                    for(int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeGenBase biomeGenBase = this.biomesForGeneration[j1 + d12 + 2 + (k1 + i2 + 2) * 10];
                        float rootHeight = biomeGenBase.rootHeight;
                        float heightVariation = biomeGenBase.heightVariation;
                        if(rootHeight > 0.0F) {
                            rootHeight = rootHeight * 1.4F + 0.5F;
                            heightVariation = heightVariation * 1.7F + 0.4F;
                        }

                        float pFieldValue = this.parabolicField[d12 + 2 + (i2 + 2) * 5] / (rootHeight + 2.0F);
                        if(biomeGenBase.rootHeight > biomegenbase.rootHeight) {
                            pFieldValue /= 1.8F;
                        }

                        f += heightVariation * pFieldValue;
                        f1 += rootHeight * pFieldValue;
                        f2 += pFieldValue;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double var46 = this.field_147426_g[i1] / 9000.0D;
                if(var46 < 0.0D) {
                    var46 = -var46 * 0.3D;
                }

                var46 = var46 * 3.0D - 2.0D;
                if(var46 < 0.0D) {
                    var46 /= 2.0D;
                    if(var46 < -1.0D) {
                        var46 = -1.0D;
                    }

                    var46 /= 1.4D;
                    var46 /= 2.0D;
                } else {
                    if(var46 > 1.0D) {
                        var46 = 1.0D;
                    }

                    var46 /= 8.0D;
                }

                ++i1;
                double var47 = (double)f1;
                double var48 = (double)f;
                var47 += var46 * 0.2D;
                var47 = var47 * 8.5D / 8.0D;
                double d5 = 8.5D + var47 * 4.0D;

                for(int j2 = 0; j2 < 33; ++j2) {
                    double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / var48;
                    if(d6 < 0.0D) {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[l] / 512.0D;
                    double d8 = this.field_147425_f[l] / 512.0D;
                    double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;
                    if(j2 > 29) {
                        double d11 = (double)((float)(j2 - 29) / 3.0F);
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.noiseField[l] = d10;
                    ++l;
                }
            }
        }
    }

    protected void generateLand(int cx, int cz, Block[] blocks) {
        byte waterLevel = 71;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration,
                                                                                               cx * 4 - 2, cz * 4 - 2,
                                                                                               10, 10);
        this.updateNoiseFields(cx * 4, 0, cz * 4);

        for(int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for(int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for(int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.noiseField[k1 + k2];
                    double d2 = this.noiseField[l1 + k2];
                    double d3 = this.noiseField[i2 + k2];
                    double d4 = this.noiseField[j2 + k2];
                    double d5 = (this.noiseField[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.noiseField[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.noiseField[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.noiseField[j2 + k2 + 1] - d4) * d0;

                    for(int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for(int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for(int k3 = 0; k3 < 4; ++k3) {
                                if((d15 += d16) > 0.0D) {
                                    blocks[j3 += short1] = Blocks.stone;
                                } else if(k2 * 8 + l2 < waterLevel) {
                                    blocks[j3 += short1] = Blocks.water;
                                } else {
                                    blocks[j3 += short1] = null;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }

        // Bedrock layer
        for (int x = 0; x < CHUNK_X; x++) {
            for (int z = 0; z < CHUNK_Z; z++) {
                blocks[x << 12 | z << 8] = Blocks.bedrock;
            }
        }
    }

    public void replaceBlocksForBiome(int cx, int cz, Block[] blocks, byte[] meta, BiomeGenBase[] biomeGenBases) {
        ChunkProviderEvent.ReplaceBiomeBlocks
                event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, cx, cz, blocks, meta, biomeGenBases, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getResult() != Event.Result.DENY) {
            double d0 = 0.03125D;
            this.stoneNoise = this.noiseGeneratorPerlin1.func_151599_a(this.stoneNoise, (double)(cx * 16), (double)(cz * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

            for(int k = 0; k < 16; ++k) {
                for(int l = 0; l < 16; ++l) {
                    BiomeGenBase biomegenbase = biomeGenBases[l + k * 16];
                    biomegenbase.genTerrainBlocks(this.worldObj, this.random, blocks, meta, cx * 16 + k, cz * 16 + l, this.stoneNoise[l + k * 16]);
                }
            }

        }
    }

    @Override
    public boolean chunkExists(int cx, int cz) {
        return true;
    }

    @Override
    public Chunk provideChunk(int cx, int cz) {
        Block[] blockStorage = new Block[16 * 16 * 256];
        byte[] metaStorage = new byte[16 * 16 * 256];

        this.generateLand(cx, cz ,blockStorage);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);
        this.replaceBlocksForBiome(cx, cz, blockStorage, metaStorage, this.biomesForGeneration);
        this.caveGenerator.func_151539_a(this, this.worldObj, cx, cz, blockStorage);
        this.ravineGenerator.func_151539_a(this, this.worldObj, cx, cz, blockStorage);

        Chunk chunk = new Chunk(this.worldObj, blockStorage, metaStorage, cx, cz);
        byte[] abyte1 = chunk.getBiomeArray();

        for(int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        return chunk;
    }

    @Override
    public Chunk loadChunk(int cx, int cz) {
        return this.provideChunk(cx, cz);
    }

    @Override
    public void populate(IChunkProvider iChunkProvider, int cx, int cz) {
        BlockFalling.fallInstantly = true;
        int x = cx * 16;
        int z = cz * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
        BlockFalling.fallInstantly = false;
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
        return "KrimsonLandsSource";
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
