package sh7811.fpsgame;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManger {
    private FPSGame plugin;
    private FileConfiguration dataconfig = null;
    private File configFile = null;

    public DataManger(FPSGame plugin) {
        this.plugin = plugin;
        // saves/initializes the Config
        saveDefaultConfig();
    }

    public void reloadconfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        this.dataconfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataconfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataconfig == null)
            reloadconfig();

        return this.dataconfig;
    }

    public void saveConfig () {
        if (this.dataconfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, this.configFile + "에 config를 저장할수 없습니다.", e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        if (!this.configFile.exists()) {
            this.plugin.saveResource("data.yml", false);
        }
    }

}
