package oasis.nms.v1_8_R2;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R2.ExceptionWorldConflict;
import net.minecraft.server.v1_8_R2.MinecraftServer;
import net.minecraft.server.v1_8_R2.WorldServer;
import oasis.NMS;

public class NMSHandler implements NMS{
	
	private MinecraftServer nms;

	@Override
	public void saveWorlds(CommandSender sender) {
		nms = MinecraftServer.getServer();
		nms.getPlayerList().savePlayers();
		for(int j = 0; j < nms.worlds.size();j++){
			WorldServer worldserver = (WorldServer)nms.worlds.get(j);
			if(worldserver != null){
				Bukkit.getServer().getLogger().info("Saving chunks for level '" + worldserver.getWorldData().getName() + "'/" + worldserver.worldProvider.getName());
				if (sender instanceof Player) {
					sender.sendMessage("Saving chunks for level '"
							+ worldserver.getWorldData().getName() + "'/"
							+ worldserver.worldProvider.getName());
				}
				try {
					worldserver.save(true, null);
				} catch (ExceptionWorldConflict e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				worldserver.saveLevel();
			}
		}
	}

}
