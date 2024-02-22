package kr.cosine.simplef3.event;

import kr.cosine.simplef3.HQSimpleF3;
import kr.cosine.simplef3.data.KeyBinding;
import kr.cosine.simplef3.gui.SimpleF3SettingScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = HQSimpleF3.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.settingKey);
        }
    }

    @Mod.EventBusSubscriber(modid = HQSimpleF3.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        private static Minecraft minecraft;

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (minecraft == null) {
                minecraft = Minecraft.getInstance();
            }
            if (KeyBinding.settingKey.consumeClick()) {
                minecraft.setScreen(new SimpleF3SettingScreen());
            }
        }
    }
}
