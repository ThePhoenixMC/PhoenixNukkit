package com.lss233.phoenix.nukkit;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.nukkit.listener.PlayerListener;
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
        Phoenix.setServer(new NukkitServer(getServer()));
    }

    private void initNukkitSide() {
        getNukkitServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        initNukkitSide();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
