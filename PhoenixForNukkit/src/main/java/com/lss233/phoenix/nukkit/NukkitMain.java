package com.lss233.phoenix.nukkit;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.nukkit.utils.Transform;
import com.lss233.phoenix.nukkit.utils.TransformUtil;
/**
 *
 */
public class NukkitMain extends PluginBase{
    private static NukkitMain instance;
    private static Server server;
    private static Transform transformer = new TransformUtil();
    public static Transform getTransformer() { return  transformer; }
    public static Server getNukkitServer() { return  server; }
    @Override
    public void onLoad() {
        super.onLoad();
        instance = this;
        server = getServer();
        NukkitServer server = new NukkitServer(getServer());
        Phoenix.setServer(server);
        initNukkitSide();
    }

    private void initNukkitSide() {
        if (!getDataFolder().exists()) {
            if(!getDataFolder().mkdirs()){
                Phoenix.getLogger("Phoenix").warn("Failed to create data folder, Phoenix Framework wont working.");
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
