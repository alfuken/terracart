package com.lime.terracart;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = TerraCart.MODID, name = TerraCart.MODNAME, version = TerraCart.VERSION)
public class TerraCart {
    public static final String MODID = "terracart";
    public static final String MODNAME = "TerraCart";
    public static final String VERSION = "1.11.2-3";
    public static Logger logger;
    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        TerraCart.logger = e.getModLog();
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), TerraCart.MODID+".cfg"));
        Config.readConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new RailsClickHandler());
        MinecraftForge.EVENT_BUS.register(new DismountHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

}
