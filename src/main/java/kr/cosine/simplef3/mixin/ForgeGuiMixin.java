package kr.cosine.simplef3.mixin;

import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeGui.class)
public abstract class ForgeGuiMixin extends Gui {

    public ForgeGuiMixin(Minecraft p_232355_, ItemRenderer p_232356_) {
        super(p_232355_, p_232356_);
    }

    @Inject(
        method = "renderHUDText",
        at = @At(
            value = "INVOKE",
            opcode = Opcodes.PUTFIELD,
            target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui$ForgeDebugScreenOverlay;update()V"
        ),
        remap = false,
        cancellable = true
    )
    public void renderHUDText(int width, int height, GuiGraphics guiGraphics, CallbackInfo ci) {
        if (SimpleF3ClientConfig.isEnabled.get()) {
            debugScreen.render(guiGraphics);
            minecraft.getProfiler().pop();
            ci.cancel();
        }
    }
}
