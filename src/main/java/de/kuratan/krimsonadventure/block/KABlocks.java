package de.kuratan.krimsonadventure.block;

import cpw.mods.fml.common.registry.GameRegistry;
import de.kuratan.krimsonadventure.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class KABlocks {
    public static Block dirt;
    public static Block grass;


    public static void initialization() {
        dirt = new KABlockDirt();
        grass = new KABlockGrass();

        GameRegistry.registerBlock(dirt, Reference.getUnlocalizedInternalName(dirt));
        GameRegistry.registerBlock(grass, Reference.getUnlocalizedInternalName(grass));
    }

    public static int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        int m = (x + y) % 16;
        if (m > 7) {
            m = 15 - m;
        }
        int r = y;
        int g = 64 + m * 16;
        int b = y;
        return (r & 255) << 16 | (g & 255) << 8 | (b & 255);
    }
}
