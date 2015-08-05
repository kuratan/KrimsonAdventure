package de.kuratan.krimsonadventure.world;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class KATeleporter extends Teleporter {

    WorldServer world;

    public KATeleporter(WorldServer p_i1963_1_) {
        super(p_i1963_1_);
        this.world = p_i1963_1_;
    }

    @Override
    public boolean makePortal(Entity p_makePortal_1_) {
        int entityX = p_makePortal_1_.serverPosX;
        int entityY = p_makePortal_1_.serverPosY;
        int entityZ = p_makePortal_1_.serverPosZ;
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                for (int k = -3; k < 4; k++) {
                    if (this.world.getBlock(entityX+i, entityY+j, entityZ) != Blocks.bedrock) {
                        this.world.setBlock(entityX+i, entityY+j, entityZ+k, Blocks.air);
                    }
                }
            }
        }
        return true;
    }
}
