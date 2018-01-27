package com.lss233.phoenix.nukkit.utils.nukkit.level;

import cn.nukkit.level.Level;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.world.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public interface LevelTransform {
    default World toPhoenix(Level level){
        return new World() {
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

                    }

                    @Override
                    public void setDifficulty(Difficulty difficulty) {

                    }

                    @Override
                    public boolean isHardcore() {
                        return false;
                    }

                    @Override
                    public void setHardcore(boolean hardcore) {

                    }

                    @Override
                    public long getSeed() {
                        return 0;
                    }

                    @Override
                    public void setSeed(long seed) {

                    }

                    @Override
                    public long getTotalTime() {
                        return 0;
                    }

                    @Override
                    public long getWorldTime() {
                        return 0;
                    }

                    @Override
                    public void setWorldTime(long time) {

                    }

                    @Override
                    public int getThunderDuration() {
                        return 0;
                    }

                    @Override
                    public void setThunderDuration(int thunderDuration) {

                    }

                    @Override
                    public boolean isThundering() {
                        return false;
                    }

                    @Override
                    public void setThundering(boolean thundering) {

                    }

                    @Override
                    public int getRainDuration() {
                        return 0;
                    }

                    @Override
                    public void setRainDuration(int rainDuration) {

                    }

                    @Override
                    public boolean isRaining() {
                        return false;
                    }

                    @Override
                    public void setRaining(boolean raining) {

                    }

                    @Override
                    public String getGameRules(String gameRule) {
                        return null;
                    }

                    @Override
                    public void setGameRule(String key, String value) {

                    }

                    @Override
                    public Location getSpawnLocation() {
                        return null;
                    }

                    @Override
                    public void setSpawnLocation(Location spawnLocation) {

                    }

                    @Override
                    public WorldBorder getWorldBorderCenter() {
                        return null;
                    }
                };
            }
        }
    }
}
