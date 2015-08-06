package de.kuratan.krimsonadventure.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.kuratan.krimsonadventure.KrimsonAdventure;
import de.kuratan.krimsonadventure.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class KABlockGrass extends Block implements IGrowable {
    @SideOnly(Side.CLIENT)
    private IIcon blockIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon blockIconSideSnow;
    @SideOnly(Side.CLIENT)
    private IIcon blockIconSideOverlay;

    protected KABlockGrass() {
        super(Material.grass);
        this.setBlockName(Reference.modPrefix("grass"));
        this.setBlockTextureName(Reference.modPrefix("grass"));
        this.setHardness(0.6F);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(KrimsonAdventure.tab);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.blockIconTop = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.blockIconSideSnow = iconRegister.registerIcon(this.getTextureName() + "_side_snowed");
        this.blockIconSideOverlay = iconRegister.registerIcon(this.getTextureName() + "_side_overlay");
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        if(!world.isRemote) {
            if(world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2) {
                world.setBlock(x, y, z, KABlocks.dirt);
            } else if(world.getBlockLightValue(x, y + 1, z) >= 9) {
                for(int l = 0; l < 4; ++l) {
                    int rX = x + random.nextInt(3) - 1;
                    int rY = y + random.nextInt(5) - 3;
                    int rZ = z + random.nextInt(3) - 1;
                    world.getBlock(rX, rY + 1, rZ);
                    if(world.getBlock(rX, rY, rZ) == KABlocks.dirt && world.getBlockMetadata(rX, rY, rZ) == 0 && world.getBlockLightValue(rX, rY + 1, rZ) >= 4 && world.getBlockLightOpacity(rX, rY + 1, rZ) <= 2) {
                        world.setBlock(rX, rY, rZ, KABlocks.grass);
                    }
                }
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1?this.blockIconTop:(p_149691_1_ == 0?KABlocks.dirt.getBlockTextureFromSide(p_149691_1_):this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        if(ForgeDirection.UP.equals(direction)) {
            return this.blockIconTop;
        } else if(ForgeDirection.DOWN.equals(direction)) {
            return KABlocks.dirt.getBlockTextureFromSide(side);
        } else {
            Material material = blockAccess.getBlock(x, y + 1, z).getMaterial();
            return material != Material.snow && material != Material.craftedSnow?this.blockIcon:this.blockIconSideSnow;
        }
    }

    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        return KABlocks.colorMultiplier(blockAccess, x, y, z);
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean b) {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return true;
    }

    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        return;
    }
}
