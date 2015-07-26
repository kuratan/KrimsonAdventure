package de.kuratan.krimsonadventure;

import de.kuratan.krimsonadventure.config.Config;
import de.kuratan.krimsonadventure.world.KATeleporter;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;

public class KATeleportCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "katp";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/katp [<player>}";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 2) {
            throw new WrongUsageException("katp [<player>}", new Object[0]);
        }
        EntityPlayerMP target = getPlayer(sender, sender.getCommandSenderName());
        if (args.length > 0) {
            target = getPlayer(sender, args[0]);
        }
        target.mcServer.getConfigurationManager().transferPlayerToDimension(target, Config.World.dimensionId, new KATeleporter(target.mcServer.worldServerForDimension(Config.World.dimensionId)));
    }
}
