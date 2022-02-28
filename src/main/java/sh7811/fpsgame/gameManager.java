package sh7811.fpsgame;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

public class gameManager {

    private boolean gameStart;
    private DataManger data;
    private List<Player> gamePlayers;
    private List<Block> chests;
    private BossBar bossBar;
    private World gameWorld;
    private Config config;

    public void gameStart(List<Player> players,DataManger data,Config config) {
        gameStart = true;
        chests = new ArrayList<>();
        this.data = data;
        this.gamePlayers = players;
        this.config = config;
        bossBar = Bukkit.createBossBar("플레이 시간 : 보급 배치중...", BarColor.GREEN, BarStyle.SOLID);
        bossBar.setProgress(1);
        bossBar.setVisible(true);
        WorldCreator wg = new WorldCreator("gameWorld");
        wg.generateStructures(config.isGenerateStructures());
        wg.seed(5661125650585929195L);
        wg.type(WorldType.NORMAL);
        gameWorld = wg.createWorld();
        Bukkit.getLogger().log(Level.INFO,"게임월드가 생성되었습니다!");
        gameWorld.getWorldBorder().setCenter(0d,0d);
        gameWorld.getWorldBorder().setSize(config.getWorldSize());
        gameWorld.setGameRule(GameRule.KEEP_INVENTORY,config.isKeepInventory());
        gameWorld.setGameRule(GameRule.DO_MOB_SPAWNING, config.isMobSpawn());
        List<String> chunks = new ArrayList<>();
        List<Location> spawns = new ArrayList<>();
        spawns.add(new Location(gameWorld,76,63,19));
        spawns.add(new Location(gameWorld,39,64,23));
        spawns.add(new Location(gameWorld,-7,63,14));
        spawns.add(new Location(gameWorld,-63,63,164));
        spawns.add(new Location(gameWorld,11,66,-151));
        spawns.add(new Location(gameWorld,135,63,-34));
        spawns.add(new Location(gameWorld,74,64,36));
        spawns.add(new Location(gameWorld,-84,63,-8));
        for (Player p : players) {
            bossBar.addPlayer(p);
            Location loc = spawns.get(new Random().nextInt(spawns.size()-1));
            spawns.remove(loc);
            p.teleport(loc);
        }
        placeChest();
        Bukkit.getLogger().log(Level.INFO,"게임월드 청크가 로드되었습니다!");
    }

    public boolean isGamestart() {
        return gameStart;
    }

    public void placeChest() {
        int maxX = 13;
        int maxZ = 13;
        Random random = new Random();
        if (!chests.isEmpty()) {
            for (Block b : chests) {
                b.setType(Material.AIR);
                chests.remove(b);
            }
        }
        for (int X = -13;X <= maxX;X++) {
            for (int Z = -13; Z <= maxZ; Z++) {
                if (random.nextBoolean()) {
                    int ChunkX = random.nextInt(16);
                    int ChunkZ = random.nextInt(16);
                    if (gameWorld.getHighestBlockAt(16 * X + ChunkX, 16 * Z + ChunkZ).getType() != Material.WATER) {
                        Block block = gameWorld.getBlockAt(gameWorld.getHighestBlockAt(16 * X + ChunkX, 16 * Z + ChunkZ).getLocation().add(0, 1, 0));
                        block.setType(Material.CHEST);
                        System.out.println(block.getLocation().toString());
                        chests.add(block);
                        System.out.println(block.getLocation());
                        Chest chest = (Chest) block.getState();
                        chest.setCustomName("보급상자");
                        chest.update(true);
                        int itemCount = random.nextInt(4) + 2;
                        itemCount++;
                        while (itemCount != 0) {
                            int item = random.nextInt(10);
                            int amount = 1;
                            ItemStack result = new ItemStack(Material.AIR);
                            ItemMeta meta = result.getItemMeta();
                            if (item == 0) {
                                result = new ItemStack(Material.ARROW);
                                meta = result.getItemMeta();
                                int type = random.nextInt(7);
                                amount = random.nextInt(5);
                                amount++;
                                if (type == 1) {
                                    meta.setDisplayName("폭탄화살");
                                }
                                if (type == 2) {
                                    meta.setDisplayName("용암화살");
                                }
                                if (type == 3) {
                                    meta.setDisplayName("구속화살");
                                }
                                if (type == 4) {
                                    meta.setDisplayName("공중부양 화살");
                                }
                                if (type == 5) {
                                    meta.setDisplayName("호박 화살");
                                }
                                if (type == 6) {
                                    meta.setDisplayName("증발 화살");
                                }
                                result.setItemMeta(meta);
                            }
                            if (item == 1) {
                                if (random.nextBoolean()) {
                                    result = new ItemStack(Material.CROSSBOW);
                                    meta = result.getItemMeta();
                                    meta.addEnchant(Enchantment.QUICK_CHARGE,5,true);
                                    result.setItemMeta(meta);
                                } else {
                                    amount = random.nextInt(10);
                                    amount++;
                                    result = new ItemStack(Material.SNOWBALL);
                                    meta = result.getItemMeta();
                                    meta.setDisplayName("수류탄");
                                    result.setItemMeta(meta);
                                }
                            }
                            if (item == 2) {
                                if (random.nextBoolean()) {
                                    if (random.nextBoolean()) {
                                        result = new ItemStack(Material.TURTLE_HELMET);
                                    } else {
                                        result = new ItemStack(Material.NETHERITE_HELMET);
                                    }
                                } else {
                                    if (random.nextBoolean()) {
                                        result = new ItemStack(Material.DIAMOND_HELMET);
                                    } else {
                                        result = new ItemStack(Material.IRON_HELMET);
                                    }
                                }
                            }
                            if (item == 3) {
                                if (random.nextBoolean()) {
                                    result = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                                } else {
                                    result = new ItemStack(Material.IRON_CHESTPLATE);
                                }
                            }
                            if (item == 4) {
                                if (random.nextBoolean()) {
                                    result = new ItemStack(Material.LEATHER_BOOTS);
                                } else {
                                    result = new ItemStack(Material.NETHERITE_BOOTS);
                                }
                            }
                            if (item == 5) {
                                if (random.nextBoolean()) {
                                    if (random.nextBoolean()) {
                                        result = new ItemStack(Material.ROTTEN_FLESH);
                                        amount = random.nextInt(9);
                                        amount++;
                                    } else {
                                        result = new ItemStack(Material.COOKED_CHICKEN);
                                        amount = random.nextInt(3);
                                        amount++;
                                    }
                                } else {
                                    result = new ItemStack(Material.COOKED_BEEF);
                                    amount = random.nextInt(3);
                                    amount++;
                                }
                            }
                            if (item == 6) {
                                if (random.nextBoolean()) {
                                    result = new ItemStack(Material.IRON_LEGGINGS);
                                } else {
                                    result = new ItemStack(Material.LEATHER_LEGGINGS);
                                }
                            }
                            if (item == 7) {
                                amount = random.nextInt(3);
                                amount++;
                                result = new ItemStack(Material.BLACK_CARPET);
                                meta = result.getItemMeta();
                                meta.setDisplayName("지뢰");
                                if (random.nextBoolean()) {
                                    if (random.nextBoolean()) {
                                        result.setType(Material.BROWN_CARPET);
                                    } else {
                                        result.setType(Material.LIGHT_GRAY_CARPET);
                                    }
                                } else {
                                    result.setType(Material.GREEN_CARPET);
                                }
                                result.setItemMeta(meta);
                            }
                            if (item == 8) {
                                amount = random.nextInt(16);
                                amount++;
                                result = new ItemStack(Material.OAK_PLANKS);
                            }
                            if (item == 9) {
                                amount = random.nextInt(16);
                                amount++;
                                result = new ItemStack(Material.TORCH);
                            }
                            result.setAmount(amount);
                            chest.getInventory().addItem(result);
                            System.out.println(amount);
                            itemCount--;
                        }
                    }
                }
            }
        }
    }

    public void gameStop() {
        gameStart = false;
        File worldFile = gameWorld.getWorldFolder();
        for (Player p : gamePlayers) {
            p.getInventory().clear();
            Location loc = p.getLocation();
            loc.setWorld(Bukkit.getWorlds().get(0));
            p.teleport(loc);
        }
        Bukkit.unloadWorld(gameWorld,false);
        DeleteFolder(worldFile);
        worldFile.delete();
        bossBar.removeAll();
        config = null;
        data = null;
        gamePlayers = null;
    }


    public void DeleteFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                DeleteFolder(file);
            } else {
                file.delete();
            }
        }
        folder.delete();
    }
}
