package com.lss233.phoenix.nukkit;

import cn.nukkit.Server;
import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.channel.MessageListener;
import com.lss233.phoenix.command.Command;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.item.enchantment.Enchantment;
import com.lss233.phoenix.item.inventory.Inventory;
import com.lss233.phoenix.item.inventory.ItemStack;
import com.lss233.phoenix.logging.Logger;
import com.lss233.phoenix.module.Module;
import com.lss233.phoenix.nukkit.utils.nukkit.item.enchantment.EnchantmentTypeHelper;
import com.lss233.phoenix.world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return true;
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
        server.getLevels().values().forEach((world)-> worlds.add(getTransformer().toPhoenix(world)));
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
        return new File("modules").getParentFile();
    }

    @Override
    public Logger getLogger() {
        return new Logger() {
            @Override
            public void info(Object s) {
                server.getLogger().info(String.valueOf(s));
            }

            @Override
            public void warn(Object s) {
                server.getLogger().warning(String.valueOf(s));
            }

            @Override
            public void debug(Object s) {
                server.getLogger().debug(String.valueOf(s));
            }

            @Override
            public void severe(Object s) {
                server.getLogger().critical(String.valueOf(s));
            }
        };
    }

    @Override
    public Interface getInterface() {
        return new Interface() {
            @Override
            public void loadModules() {
                File moduleDir = new File("modules");
                if (!moduleDir.exists()) {
                    if(!moduleDir.mkdirs()){
                        Phoenix.getLogger("Phoenix").warn("Failed to create module folder, Phoenix Framework wont working.");
                    }
                    return;
                }

                for (File file : Objects.requireNonNull(moduleDir.listFiles())) {
                    if (!file.getName().endsWith(".jar"))
                        continue;
                    try {
                        Phoenix.getModuleManager().loadModule(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void registerCommand(Command command) {

            }

            @Override
            public MessageChannelManager getMessageChannelManager() {
                return new MessageChannelManager() {
                    @Override
                    public void registerOutgoingPluginChannel(Module module, String s) {
                        //Not supported yet.
                    }

                    @Override
                    public void registerIncomingPluginChannel(Module module, String s, MessageListener messageListener) {
                        //Not supported yet.
                    }
                };
            }

            @Override
            public Inventory registerInventory(Inventory.Builder builder) {
                // TODO
                return null;
            }

            @Override
            public ItemStack registerItemStack(ItemStack.Builder builder) {
                // TODO
                return null;
            }

            @Override
            public Enchantment registerEnchantment(Enchantment.Builder builder) {
                return getTransformer().toPhoenix(cn.nukkit.item.enchantment.Enchantment.get(EnchantmentTypeHelper.getEnchantmentId(builder.getType()))
                        .setLevel(builder.getLevel()));
            }
        };
    }
}
