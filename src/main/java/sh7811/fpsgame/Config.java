package sh7811.fpsgame;

import org.bukkit.Difficulty;

public class Config {
    private int weaponCooldown = 30;
    private boolean canRespawn = false;
    private boolean canNether = false;
    private boolean canEnder = false;
    private boolean mobSpawn = true;
    private boolean canExplosion = true;
    private boolean refillchest = true;
    private boolean keepInventory = false;
    private boolean generateStructures = true;
    private Difficulty difficulty = Difficulty.EASY;
    private boolean hardcore = false;
    private boolean rankGame = false;
    private int maxHealth = 20;

    public boolean isGenerateStructures() {
        return generateStructures;
    }

    public float getWorldSize() {
        return 400;
    }

    public boolean isCanEnder() {
        return canEnder;
    }

    public boolean isCanExplosion() {
        return canExplosion;
    }

    public boolean isCanNether() {
        return canNether;
    }

    public boolean isCanRespawn() {
        return canRespawn;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public boolean isKeepInventory() {
        return keepInventory;
    }

    public boolean isMobSpawn() {
        return mobSpawn;
    }

    public boolean isRankGame() {
        return rankGame;
    }

    public boolean isRefillchest() {
        return refillchest;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWeaponCooldown() {
        return weaponCooldown;
    }

    public void setGenerateStructures(boolean generateStructures) {
        this.generateStructures = generateStructures;
    }

    public void setCanEnder(boolean canEnder) {
        this.canEnder = canEnder;
    }

    public void setCanExplosion(boolean canExplosion) {
        this.canExplosion = canExplosion;
    }

    public void setCanNether(boolean canNether) {
        this.canNether = canNether;
    }

    public void setCanRespawn(boolean canRespawn) {
        this.canRespawn = canRespawn;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public void setKeepInventory(boolean keepInventory) {
        this.keepInventory = keepInventory;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMobSpawn(boolean mobSpawn) {
        this.mobSpawn = mobSpawn;
    }

    public void setRankGame(boolean rankGame) {
        this.rankGame = rankGame;
    }

    public void setRefillchest(boolean refillchest) {
        this.refillchest = refillchest;
    }

    public void setWeaponCooldown(int weaponCooldown) {
        this.weaponCooldown = weaponCooldown;
    }
}
