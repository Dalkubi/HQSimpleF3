package kr.cosine.simplef3.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow
    public static ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/icons.png");

    @Shadow
    public Minecraft minecraft;

    @Shadow
    public int screenWidth;

    @Shadow
    public int screenHeight;

    @Shadow
    public abstract boolean canRenderCrosshairForSpectator(HitResult p_93025_);

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void renderCrosshair(GuiGraphics p_282828_, CallbackInfo ci) {
        if (SimpleF3ClientConfig.isEnabled.get()) {
            Options options = this.minecraft.options;
            if (options.getCameraType().isFirstPerson()) {
                if (this.minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR || this.canRenderCrosshairForSpectator(this.minecraft.hitResult)) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    p_282828_.blit(GUI_ICONS_LOCATION, (this.screenWidth - 15) / 2, (this.screenHeight - 15) / 2, 0, 0, 15, 15);
                    if (this.minecraft.options.attackIndicator().get() == AttackIndicatorStatus.CROSSHAIR) {
                        float f = this.minecraft.player.getAttackStrengthScale(0.0F);
                        boolean flag = false;
                        if (this.minecraft.crosshairPickEntity != null && this.minecraft.crosshairPickEntity instanceof LivingEntity && f >= 1.0F) {
                            flag = this.minecraft.player.getCurrentItemAttackStrengthDelay() > 5.0F;
                            flag &= this.minecraft.crosshairPickEntity.isAlive();
                        }
                        int j = this.screenHeight / 2 - 7 + 16;
                        int k = this.screenWidth / 2 - 8;
                        if (flag) {
                            p_282828_.blit(GUI_ICONS_LOCATION, k, j, 68, 94, 16, 16);
                        } else if (f < 1.0F) {
                            int l = (int) (f * 17.0F);
                            p_282828_.blit(GUI_ICONS_LOCATION, k, j, 36, 94, 16, 4);
                            p_282828_.blit(GUI_ICONS_LOCATION, k, j, 52, 94, l, 4);
                        }
                    }
                    RenderSystem.defaultBlendFunc();
                }
            }
            ci.cancel();
        }
    }
}
