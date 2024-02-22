package kr.cosine.simplef3;

import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import kr.cosine.simplef3.data.KeyBinding;
import kr.cosine.simplef3.gui.SimpleF3SettingScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(HQSimpleF3.MOD_ID)
public class HQSimpleF3 {

    public static final String MOD_ID = "hqsimplef3";

    public HQSimpleF3() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, SimpleF3ClientConfig.spec, MOD_ID + "-client.toml");
        modLoadingContext.registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new SimpleF3SettingScreen())
        );
    }
}
