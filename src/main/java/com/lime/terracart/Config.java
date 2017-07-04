package com.lime.terracart;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {
    private static final String C = TerraCart.MODID;
    public static boolean use_vanilla_cart = false;

    public static void readConfig() {
        Configuration cfg = TerraCart.config;
        try {
            cfg.load();

            use_vanilla_cart = cfg.getBoolean("Use vanilla cart", C, use_vanilla_cart, "Use vanilla cart instead of brakeless terracart");

        } catch (Exception e1) {
            TerraCart.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
}
