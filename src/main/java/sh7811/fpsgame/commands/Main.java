package sh7811.fpsgame.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sh7811.fpsgame.Config;
import sh7811.fpsgame.DataManger;
import sh7811.fpsgame.gameManager;

import java.util.ArrayList;
import java.util.List;

public class Main implements CommandExecutor {

    DataManger data;
    gameManager gm;
    public Main(DataManger data , gameManager gm) {this.data = data;this.gm = gm;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!gm.isGamestart()) {
            List<Player> players = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                players.add(p);
            }
            gm.gameStart(players, data, new Config());
        } else {
            gm.gameStop();
        }
        return false;
    }
}
