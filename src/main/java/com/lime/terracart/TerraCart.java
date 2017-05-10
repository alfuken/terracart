package com.lime.terracart;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = TerraCart.MODID, name = TerraCart.MODNAME, version = TerraCart.VERSION)
public class TerraCart {
    public static final String MODID = "terracart";
    public static final String MODNAME = "TerraCart";
    public static final String VERSION = "1.10.2-2.1";

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new RailsClickHandler());
    }

}
