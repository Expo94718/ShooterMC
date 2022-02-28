package sh7811.fpsgame;

import org.bukkit.plugin.java.JavaPlugin;
import sh7811.fpsgame.commands.Main;

public final class FPSGame extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        DataManger data = new DataManger(this);
        gameManager gm = new gameManager();
        this.getCommand("게임").setExecutor(new Main(data,gm));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (new gameManager().isGamestart()) {
            new gameManager().gameStop();
        }
    }
}
