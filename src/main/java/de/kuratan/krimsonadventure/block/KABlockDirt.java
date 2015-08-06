package de.kuratan.krimsonadventure.block;

import de.kuratan.krimsonadventure.KrimsonAdventure;
import de.kuratan.krimsonadventure.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class KABlockDirt extends Block {
    protected KABlockDirt() {
        super(Material.ground);
        this.setBlockName(Reference.modPrefix("dirt"));
        this.setBlockTextureName(Reference.modPrefix("dirt"));
        this.setHardness(0.5F);
        this.setStepSound(soundTypeGravel);
        this.setCreativeTab(KrimsonAdventure.tab);
    }

    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return KABlocks.colorMultiplier(blockAccess, x, y, z);
    }
}
