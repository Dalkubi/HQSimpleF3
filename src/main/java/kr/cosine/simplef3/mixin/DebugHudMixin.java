package kr.cosine.simplef3.mixin;

import com.google.common.base.Strings;
import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import kr.cosine.simplef3.config.SimpleF3ClientSetting;
import kr.cosine.simplef3.registry.NameRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {

    private static final int hexColor = Integer.decode("0xffe880");

    @Shadow
    public MinecraftClient client;

    @Shadow
    public TextRenderer textRenderer;

    @Shadow
    public abstract void drawText(DrawContext context, List<String> text, boolean left);

    @Inject(method = "drawLeftText", at = @At("HEAD"), cancellable = true)
    public void drawLeftText(DrawContext context, CallbackInfo callbackInfo) {
        SimpleF3ClientSetting simpleF3ClientSetting = SimpleF3ClientConfig.getSimpleF3ClientSetting();
        if (simpleF3ClientSetting.isEnabled) {
            List<String> list = new ArrayList<>();

            Entity entity = client.getCameraEntity();
            BlockPos blockPos = entity.getBlockPos();
            Direction direction = entity.getHorizontalFacing();
            ClientWorld world = client.world;

            if (simpleF3ClientSetting.fps) {
                list.add(String.format("%d FPS", client.getCurrentFps()));
            }
            if (simpleF3ClientSetting.fpsBelowSpace) {
                list.add("");
            }
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            if (simpleF3ClientSetting.coordinateSingleLine) {
                list.add(String.format("XYZ : %.1f §7/§r %.1f §7/§r %.1f", x, y, z));
            } else {
                list.add(String.format("X : %.1f", x));
                list.add(String.format("Y : %.1f", y));
                list.add(String.format("Z : %.1f", z));
            }
            if (simpleF3ClientSetting.yawAndPitch) {
                list.add(String.format("Yaw : %.1f", MathHelper.wrapDegrees(entity.getYaw())));
                list.add(String.format("Pitch : %.1f", MathHelper.wrapDegrees(entity.getPitch())));
            }

            String directionText;
            if (simpleF3ClientSetting.koreanDirection) {
                directionText = NameRegistry.findDirectionKoreanName(direction);
            } else {
                directionText = direction.getName();
            }
            list.add("방향 : " + directionText);
            list.add("바이옴 : " + getBiomeName(world.getBiome(blockPos), simpleF3ClientSetting.koreanBiome));

            drawText(context, list, true);
            callbackInfo.cancel();
        }
    }

    private String getBiomeName(RegistryEntry<Biome> biome, boolean korean) {
        return biome.getKeyOrValue().map(
            (biomeKey) -> korean ? NameRegistry.findBiomeKoreanName(biomeKey) : biomeKey.getValue().toString(),
            (biomeKey) -> "unregistered:" + biomeKey
        );
    }

    @Inject(method = "drawRightText", at = @At("HEAD"), cancellable = true)
    public void drawRightText(DrawContext context, CallbackInfo callbackInfo) {
        SimpleF3ClientSetting simpleF3ClientSetting = SimpleF3ClientConfig.getSimpleF3ClientSetting();
        if (simpleF3ClientSetting.isEnabled) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "drawText", at = @At("HEAD"), cancellable = true)
    public void drawText(DrawContext context, List<String> text, boolean left, CallbackInfo callbackInfo) {
        SimpleF3ClientSetting simpleF3ClientSetting = SimpleF3ClientConfig.getSimpleF3ClientSetting();
        int scaledWindowWidth = context.getScaledWindowWidth();
        if (simpleF3ClientSetting.isEnabled) {
            int i = 9;
            for (int j = 0; j < text.size(); ++j) {
                String s = text.get(j);
                if (!Strings.isNullOrEmpty(s)) {
                    int k = textRenderer.getWidth(s);
                    int l = left ? 2 : scaledWindowWidth - 2 - k;
                    int i1 = 2 + i * j;
                    context.fill(l - 1, i1 - 1, l + k + 1, i1 + i - 1, -1873784752);
                }
            }
            for (int j1 = 0; j1 < text.size(); ++j1) {
                String s1 = text.get(j1);
                if (!Strings.isNullOrEmpty(s1)) {
                    int k1 = textRenderer.getWidth(s1);
                    int l1 = left ? 2 : scaledWindowWidth - 2 - k1;
                    int i2 = 2 + i * j1;
                    String[] split = s1.split(" : ");
                    if (split.length > 1) {
                        String key = split[0];
                        String value = split[1];
                        int valueWidth = textRenderer.getWidth(value) - 2;
                        context.drawText(textRenderer, key + " §7: ", l1, i2, 16777215, false);
                        context.drawText(textRenderer, value, k1 - valueWidth, i2, hexColor, false);
                    } else {
                        context.drawText(textRenderer, s1, l1, i2, 16777215, false);
                    }
                }
            }
            callbackInfo.cancel();
        }
    }
}
