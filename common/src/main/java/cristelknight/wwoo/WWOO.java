package cristelknight.wwoo;

import cristelknight.wwoo.config.configs.BannedBiomesConfig;
import cristelknight.wwoo.config.configs.WWOOConfig;
import cristelknight.wwoo.utils.BiomeReplace;
import cristelknight.wwoo.utils.Updater;
import cristelknight.wwoo.utils.Util;
import net.cristellib.CristelLibExpectPlatform;
import net.cristellib.builtinpacks.BuiltInDataPacks;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WWOO {

    public static final String MODID = "wwoo";
    public static final Logger LOGGER = LogManager.getLogger("WWOO");
    public static final String WWOOVersion = WWOOExpectPlatform.getVersionForMod(MODID);
    private static final Updater updater = new Updater(WWOOVersion);
    public static Mode currentMode = Util.getMode(WWOOConfig.DEFAULT.getConfig().mode());
    public static final String LINK_H = "https://www.curseforge.com/minecraft/mc-mods/william-wythers-overhauled-overworld";
    public static final String LINK_DC = "https://discord.com/invite/yJng7sC44x";

    public static final String backupVersionNumber = "1.20.1";
    public static final String minTerraBlenderVersion = "3.0.0.167";


    public static void init() {
        LOGGER.info("Loading William Wythers' Overhauled Overworld");
        updater.checkForUpdates();

        BannedBiomesConfig config = BannedBiomesConfig.DEFAULT.getConfig();
        if(config.enableBiomes() && currentMode.equals(Mode.DEFAULT)) BiomeReplace.replace();

        BuiltInDataPacks.registerPack(new WWOORL("resources/wwoo_default"), MODID, Component.literal("FUNNY WWOO DEFAULT PACK"), () -> currentMode.equals(Mode.DEFAULT));
        BuiltInDataPacks.registerPack(new WWOORL("resources/wwoo_force_large_biomes"), MODID, Component.literal("Forcing some LARGE biomes"), () -> WWOOConfig.DEFAULT.getConfig().forceLargeBiomes() && currentMode.equals(Mode.DEFAULT));
    }

    public static Updater getUpdater(){
        return updater;
    }


    public static boolean isTerraBlenderLoaded(){
        return CristelLibExpectPlatform.isModLoadedWithVersion("terrablender", minTerraBlenderVersion);
    }

    public enum Mode {
        COMPATIBLE,
        DEFAULT
    }
}