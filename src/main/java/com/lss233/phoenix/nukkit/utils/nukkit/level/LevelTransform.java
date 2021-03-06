package com.lss233.phoenix.nukkit.utils.nukkit.level;

import cn.nukkit.Server;
import cn.nukkit.level.GameRule;
import cn.nukkit.level.Level;
import cn.nukkit.math.SimpleAxisAlignedBB;
import cn.nukkit.network.protocol.SetDifficultyPacket;
import com.lss233.phoenix.block.Block;
import com.lss233.phoenix.entity.Entity;
import com.lss233.phoenix.entity.EntityType;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.math.Vector;
import com.lss233.phoenix.world.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public interface LevelTransform {
    default World toPhoenix(Level level){
        return new World() {
            @Override
            public Optional<Entity> createEntity(EntityType entityType, Vector vector) {
                return Optional.empty();
            }

            @Override
            public List<Entity> getNearbyEntities(Vector vector, double v) {
                return Arrays.stream(level.getNearbyEntities(new SimpleAxisAlignedBB(
                        vector.getX() - v,
                        vector.getY() - v,
                        vector.getZ() - v,
                        vector.getX() + v,
                        vector.getY() + v,
                        vector.getZ() + v)))
                        .map(entity -> getTransformer().toPhoenix(entity))
                        .collect(Collectors.toList());
            }

            @Override
            public List<Entity> getEntities() {
                return Arrays.stream(level.getEntities())
                        .map(entity -> getTransformer().toPhoenix(entity))
                        .collect(Collectors.toList());
            }

            @Override
            public Optional<Entity> getEntity(UUID uuid) {
                for (Entity entity : getEntities()) {
                    if(entity.getUniqueId().equals(uuid))
                        return Optional.of(entity);
                }
                return Optional.empty();
            }

            @Override
            public String getName() {
                return level.getName();
            }

            @Override
            public List<Player> getPlayers() {
                List<Player> players = new ArrayList<>();
                level.getPlayers().forEach((junk,player)-> players.add(getTransformer().toPhoenix(player)));
                return players;
            }

            @Override
            public UUID getUniqueId() {
                return new UUID(level.getId(),level.getId());
            }

            @Override
            public WorldProperties getProperties() {
                return new WorldProperties() {
                    @Override
                    public Difficulty getDifficulty() {
                        switch (Server.getInstance().getDifficulty()){
                            default:
                                return Difficulty.PEACEFUL;
                            case 1:
                                return Difficulty.EASY;
                            case 2:
                                return Difficulty.NORMAL;
                            case 3:
                                return Difficulty.HARD;
                        }
                    }

                    @Override
                    public void setDifficulty(Difficulty difficulty) {
                        // Code from https://github.com/Nukkit/Nukkit/blob/b04061b912ec16c4c9c753f2e475f6e2e9514402/src/main/java/cn/nukkit/command/defaults/DifficultyCommand.java#L42
                        int diff = Server.getDifficultyFromString(difficulty.toString());

                        if (Server.getInstance().isHardcore()) {
                            diff = 3;
                        }

                        if (diff != -1) {
                            Server.getInstance().setPropertyInt("difficulty", diff);

                            SetDifficultyPacket pk = new SetDifficultyPacket();
                            pk.difficulty = Server.getInstance().getDifficulty();
                            Server.broadcastPacket(new ArrayList<>(Server.getInstance().getOnlinePlayers().values()), pk);
                        }
                    }

                    @Override
                    public boolean isHardcore() {
                        return Server.getInstance().isHardcore();
                    }

                    @Override
                    public void setHardcore(boolean hardcore) {
                        Server.getInstance().setPropertyBoolean("hardcore", hardcore);
                    }

                    @Override
                    public long getSeed() {
                        return level.getSeed();
                    }

                    @Override
                    public void setSeed(long seed) {
                        level.setSeed((int)seed);
                    }

                    @Override
                    public long getTotalTime() {
                        return level.getTime();
                    }

                    @Override
                    public long getWorldTime() {
                        return level.getTime();
                    }

                    @Override
                    public void setWorldTime(long time) {
                        level.setTime((int)time);
                    }

                    @Override
                    public int getThunderDuration() {
                        return level.getThunderTime();
                    }

                    @Override
                    public void setThunderDuration(int thunderDuration) {
                        level.setThunderTime(thunderDuration);
                    }

                    @Override
                    public boolean isThundering() {
                        return level.isThundering();
                    }

                    @Override
                    public void setThundering(boolean thundering) {
                        level.setThundering(thundering);
                    }

                    @Override
                    public int getRainDuration() {
                        return level.getRainTime();
                    }

                    @Override
                    public void setRainDuration(int rainDuration) {
                        level.setRainTime(rainDuration);
                    }

                    @Override
                    public boolean isRaining() {
                        return level.isRaining();
                    }

                    @Override
                    public void setRaining(boolean raining) {
                        level.setRaining(raining);
                    }

                    @Override
                    public Optional<String> getGameRules(String gameRule) {
                        return Optional.ofNullable(level.getGameRules().getString(GameRule.valueOf(gameRule)));
                    }

                    @Override
                    public void setGameRule(String key, String value) {
                        level.getGameRules().setGameRules(GameRule.valueOf(key), value);
                    }

                    @Override
                    public Location getSpawnLocation() {
                        return getTransformer().toPhoenix(new cn.nukkit.level.Location(level.getSpawnLocation().getX(),
                                level.getSpawnLocation().getY(),
                                level.getSpawnLocation().getZ(),
                                level));
                    }

                    @Override
                    public void setSpawnLocation(Location spawnLocation) {
                        level.setSpawnLocation(getTransformer().toNukkit(spawnLocation));
                    }

                    @Override
                    public WorldBorder getWorldBorderCenter() {
                        // Not supported for now.
                        return new WorldBorder() {
                            @Override
                            public Location getCenter() {
                                return getSpawnLocation();
                            }

                            @Override
                            public double getDamageAmount() {
                                return 0;
                            }

                            @Override
                            public double getDamageThreshold() {
                                return 0;
                            }

                            @Override
                            public double getDiameter() {
                                return 0;
                            }

                            @Override
                            public int getWarningDistance() {
                                return 0;
                            }

                            @Override
                            public int getWarningTime() {
                                return 0;
                            }

                            @Override
                            public void setCenter(Location location) {

                            }

                            @Override
                            public void setCenter(double v, double v1) {

                            }

                            @Override
                            public void setDamageAmount(double v) {

                            }

                            @Override
                            public void setDamageThreshold(double v) {

                            }

                            @Override
                            public void setDiameter(double v) {

                            }

                            @Override
                            public void setWarningDistance(int i) {

                            }

                            @Override
                            public void setWarningTime(int i) {

                            }
                        };
                    }
                };
            }

            @Override
            public boolean setBlock(Block block, boolean b) {
                return false; // TODO
            }

            @Override
            public Location getSpawnLocation() {
                return new Location(this, level.getSpawnLocation().getX(), level.getSpawnLocation().getY(), level.getSpawnLocation().getZ());
            }

            @Override
            public int getSeaLevel() {
                return 0;
            }

            @Override
            public void save() {
                level.save();
            }

            @Override
            public Chunk getChunk(int x, int z) {
                return getTransformer().toPhoenix(level.getChunk(x, z), this);
            }

            @Override
            public Chunk getChunk(Location location) {
                return getTransformer().toPhoenix(
                        level.getChunk(location.getBlockX() / 16, location.getBlockZ() / 16), this);
            }

            @Override
            public boolean loadChunk(int i, int i1, boolean b) {
                return level.loadChunk(i, i1, b);
            }

            @Override
            public boolean unloadChunk(Chunk chunk) {
                return level.unloadChunk(chunk.getX(), chunk.getZ());
            }

            @Override
            public void createExplosion(double v, double v1, double v2, float v3) {
                //TODO
            }

            @Override
            public List<Chunk> getLoadedChunks() {
                return level.getChunks().values().stream()
                        .map(chunk -> getTransformer().toPhoenix(chunk, this))
                        .collect(Collectors.toList());
            }
        };
    }

    default Level toNukkit(World world){
        return Server.getInstance().getLevelByName(world.getName());
    }
}
