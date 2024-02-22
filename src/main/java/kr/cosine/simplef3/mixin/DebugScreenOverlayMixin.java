package kr.cosine.simplef3.mixin;

import com.google.common.base.Strings;
import com.mojang.datafixers.util.Pair;
import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import kr.cosine.simplef3.registry.NameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    private static final int hexColor = Integer.decode("0xffe880");

    @Shadow
    public Minecraft minecraft;

    @Shadow
    public Font font;

    @Shadow
    public abstract void renderLines(GuiGraphics p_286519_, List<String> p_286665_, boolean p_286644_);

    @Inject(method = "drawGameInformation", at = @At("HEAD"), cancellable = true)
    public void drawGameInformation(GuiGraphics p_281525_, CallbackInfo callbackInfo) {
        if (SimpleF3ClientConfig.isEnabled.get()) {
            List<String> list = new ArrayList<>();

            Entity entity = minecraft.getCameraEntity();
            BlockPos blockPos = entity.blockPosition();
            Direction direction = entity.getDirection();
            Level level = minecraft.level;

            if (SimpleF3ClientConfig.fps.get()) {
                list.add(String.format("%d FPS", minecraft.getFps()));
            }
            if (SimpleF3ClientConfig.fpsBelowSpace.get()) {
                list.add("");
            }
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            if (SimpleF3ClientConfig.coordinateSingleLine.get()) {
                list.add(String.format("XYZ : %.1f §7/§r %.1f §7/§r %.1f", x, y, z));
            } else {
                list.add(String.format("X : %.1f", x));
                list.add(String.format("Y : %.1f", y));
                list.add(String.format("Z : %.1f", z));
            }
            if (SimpleF3ClientConfig.yawAndPitch.get()) {
                list.add(String.format("Yaw : %.1f", Mth.wrapDegrees(entity.getYRot())));
                list.add(String.format("Pitch : %.1f", Mth.wrapDegrees(entity.getXRot())));
            }

            String directionText;
            if (SimpleF3ClientConfig.koreanDirection.get()) {
                directionText = NameRegistry.findDirectionKoreanName(direction);
            } else {
                directionText = direction.getName();
            }
            list.add("방향 : " + directionText);
            list.add("바이옴 : " + getBiomeName(level.getBiome(blockPos), SimpleF3ClientConfig.koreanBiome.get()));

            renderLines(p_281525_, list, true);
            callbackInfo.cancel();
        }
    }

    private String getBiomeName(Holder<Biome> holder, boolean korean) {
        return holder.unwrap().map(
            (biome) -> korean ? NameRegistry.findBiomeKoreanName(biome) : biome.location().toString(),
            (biome) -> "unregistered:" + biome
        );
    }

    @Inject(method = "drawSystemInformation", at = @At("HEAD"), cancellable = true)
    public void drawSystemInformation(GuiGraphics p_281261_, CallbackInfo callbackInfo) {
        if (SimpleF3ClientConfig.isEnabled.get()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "renderLines", at = @At("HEAD"), cancellable = true)
    public void renderLines(GuiGraphics p_286519_, List<String> p_286665_, boolean p_286644_, CallbackInfo callbackInfo) {
        if (SimpleF3ClientConfig.isEnabled.get()) {
            int i = 9;
            for (int j = 0; j < p_286665_.size(); ++j) {
                String s = p_286665_.get(j);
                if (!Strings.isNullOrEmpty(s)) {
                    int k = font.width(s);
                    int l = p_286644_ ? 2 : p_286519_.guiWidth() - 2 - k;
                    int i1 = 2 + i * j;
                    p_286519_.fill(l - 1, i1 - 1, l + k + 1, i1 + i - 1, -1873784752);
                }
            }
            for (int j1 = 0; j1 < p_286665_.size(); ++j1) {
                String s1 = p_286665_.get(j1);
                if (!Strings.isNullOrEmpty(s1)) {
                    int k1 = font.width(s1);
                    int l1 = p_286644_ ? 2 : p_286519_.guiWidth() - 2 - k1;
                    int i2 = 2 + i * j1;
                    String[] split = s1.split(" : ");
                    if (split.length > 1) {
                        String key = split[0];
                        String value = split[1];
                        int valueWidth = font.width(value) - 2;
                        p_286519_.drawString(font, key + " §7: ", l1, i2, 16777215, false);
                        p_286519_.drawString(font, value, k1 - valueWidth, i2, hexColor, false);
                    } else {
                        p_286519_.drawString(font, s1, l1, i2, 16777215, false);
                    }
                }
            }
            callbackInfo.cancel();
        }
    }
}
