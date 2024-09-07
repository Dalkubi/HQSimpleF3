package kr.cosine.simplef3.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import kr.cosine.simplef3.config.SimpleF3ClientSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    private static final Identifier CROSSHAIR_TEXTURE = new Identifier("hud/crosshair");

    @Shadow
    private static final Identifier CROSSHAIR_ATTACK_INDICATOR_FULL_TEXTURE = new Identifier("hud/crosshair_attack_indicator_full");

    @Shadow
    private static final Identifier CROSSHAIR_ATTACK_INDICATOR_BACKGROUND_TEXTURE = new Identifier("hud/crosshair_attack_indicator_background");

    @Shadow
    private static final Identifier CROSSHAIR_ATTACK_INDICATOR_PROGRESS_TEXTURE = new Identifier("hud/crosshair_attack_indicator_progress");

    @Shadow
    public MinecraftClient client;

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Shadow
    public abstract boolean shouldRenderSpectatorCrosshair(HitResult hitResult);

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void renderCrosshair(DrawContext context, CallbackInfo callbackInfo) {
        SimpleF3ClientSetting simpleF3ClientSetting = SimpleF3ClientConfig.getSimpleF3ClientSetting();
        if (simpleF3ClientSetting.isEnabled) {
            GameOptions gameOptions = this.client.options;
            if (gameOptions.getPerspective().isFirstPerson()) {
                if (this.client.interactionManager.getCurrentGameMode() != GameMode.SPECTATOR || this.shouldRenderSpectatorCrosshair(this.client.crosshairTarget)) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ONE_MINUS_DST_COLOR, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
                    context.drawGuiTexture(CROSSHAIR_TEXTURE, (this.scaledWidth - 15) / 2, (this.scaledHeight - 15) / 2, 15, 15);
                    if (this.client.options.getAttackIndicator().getValue() == AttackIndicator.CROSSHAIR) {
                        float f = this.client.player.getAttackCooldownProgress(0.0F);
                        boolean bl = false;
                        if (this.client.targetedEntity != null && this.client.targetedEntity instanceof LivingEntity && f >= 1.0F) {
                            bl = this.client.player.getAttackCooldownProgressPerTick() > 5.0F;
                            bl &= this.client.targetedEntity.isAlive();
                        }
                        int j = this.scaledHeight / 2 - 7 + 16;
                        int k = this.scaledWidth / 2 - 8;
                        if (bl) {
                            context.drawGuiTexture(CROSSHAIR_ATTACK_INDICATOR_FULL_TEXTURE, k, j, 16, 16);
                        } else if (f < 1.0F) {
                            int l = (int) (f * 17.0F);
                            context.drawGuiTexture(CROSSHAIR_ATTACK_INDICATOR_BACKGROUND_TEXTURE, k, j, 16, 4);
                            context.drawGuiTexture(CROSSHAIR_ATTACK_INDICATOR_PROGRESS_TEXTURE, 16, 4, 0, 0, k, j, l, 4);
                        }
                    }
                    RenderSystem.defaultBlendFunc();
                }
            }
            callbackInfo.cancel();
        }
    }
}
