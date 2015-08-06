package de.kuratan.krimsonadventure.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.krimsonadventure.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class KABlocks {
    public static Block dirt;
    public static Block grass;


    public static void initialization() {
        dirt = new KABlockDirt().setBlockTextureName("dirt");
        grass = new KABlockGrass().setBlockTextureName("grass");

        GameRegistry.registerBlock(dirt, Reference.getUnlocalizedInternalName(dirt));
        GameRegistry.registerBlock(grass, Reference.getUnlocalizedInternalName(grass));
    }

    public static int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        int r = 200 - (int)(y / 256.0 * 100);
        int g = 0;
        int b = 200 - (int)(y / 256.0 * 100);
        return (r & 255) << 16 | (g & 255) << 8 | (b & 255);
    }
}
