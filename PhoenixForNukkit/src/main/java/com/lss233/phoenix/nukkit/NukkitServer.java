package com.lss233.phoenix.nukkit;

import cn.nukkit.Server;
import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.logging.Logger;
import com.lss233.phoenix.world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.lss233.phoenix.nukkit.NukkitMain.getTransformer;

/**
 *
 */
public class NukkitServer implements Phoenix.Server {
    private final Server server;
    public NukkitServer(Server server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return server.getName();
    }

    @Override
    public String getVersion() {
        return server.getVersion();
    }

    @Override
    public String getIp() {
        return server.getIp();
    }

    @Override
    @Deprecated
    public String getServerName() {
        return server.getServerUniqueId().toString();
    }

    @Override
    @Deprecated
    public String getServerId() {
        return server.getServerUniqueId().toString();
    }

    @Override
    public int getMaxPlayers() {
        return server.getMaxPlayers();
    }

    @Override
    public int getViewDistance() {
        return server.getViewDistance();
    }

    @Override
    public boolean hasAllowNether() {
        return false; // TODO What the hell?
    }

    @Override
    public boolean hasWhitelist() {
        return server.hasWhitelist();
    }

    @Override
    public boolean hasGenerateStructures() {
        return server.getGenerateStructures();
    }

    @Override
    public List<World> getWorlds() {
        List<World> worlds = new ArrayList<>();
        server.getWorlds().forEach((world)-> worlds.add(getTransformer().toPhoenix(world)));
        return worlds;
    }

    @Override
    public List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();
        server.getOnlinePlayers().forEach((junk,player)-> players.add(getTransformer().toPhoenix(player)));
        return players;
    }

    @Override
    public File getPhoenixDataDir() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public Interface getInterface() {
        return null;
    }
}
