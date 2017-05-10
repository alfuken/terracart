package com.lime.terracart;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = TerraCart.MODID, name = TerraCart.MODNAME, version = TerraCart.VERSION)
public class TerraCart {
    public static final String MODID = "terracart";
    public static final String MODNAME = "TerraCart";
    public static final String VERSION = "1.7.10-2";

    @Mod.Instance
    public static TerraCart instance = new TerraCart();

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new RailsClickHandler());
    }

}
