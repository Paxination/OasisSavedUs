package oasis;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class OasisSavedUs extends JavaPlugin {

	private NMS nmsHandler;
	
	@Override
	public void onEnable(){
		String packageName = this.getServer().getClass().getPackage().getName();
		String version = packageName.substring(packageName.lastIndexOf('.') + 1 );
		
		try {
			if (version.contains("v1_8")) {
				final Class<?> clazz = Class.forName("oasis.nms.v1_8_R1.NMSHandler");
				if (NMS.class.isAssignableFrom(clazz)) {
					this.nmsHandler = (NMS) clazz.getConstructor()
							.newInstance();
				}
			}
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.getLogger().severe("Could not find support for this CraftBukkit version.");
            this.getLogger().info("Tell Norm to update this shit.....");
            this.setEnabled(false);
            return;
		}
		
		this.getLogger().info("Loading support for " + version);
		
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.nmsHandler.saveWorlds(sender);
        return true;
    }
}
