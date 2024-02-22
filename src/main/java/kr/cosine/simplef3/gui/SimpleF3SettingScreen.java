package kr.cosine.simplef3.gui;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import kr.cosine.simplef3.HQSimpleF3;
import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import kr.cosine.simplef3.data.KeyBinding;
import kr.cosine.simplef3.gui.button.ToggleButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class SimpleF3SettingScreen extends AbstractSimpleF3SettingScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(HQSimpleF3.MOD_ID, "textures/gui/setting.png");
    private static final Component TITLE = Component.translatable("key.hqsimplef3_setting");

    private static final Component ENABLED = Component.translatable("message.hqsimplef3.enabled");
    private static final Component DISABLED = Component.translatable("message.hqsimplef3.disabled");

    public SimpleF3SettingScreen() {
        super(TITLE, 190, 175);
    }

    @Override
    protected void init() {
        super.init();

        int x = guiLeft + 10;
        int y = guiTop + 20;
        int width = xSize - 20;
        int height = 20;

        ToggleButton simpleF3Button = new ToggleButton(x, y, width, height,
            SimpleF3ClientConfig.isEnabled,
            enabled -> Component.translatable("message.hqsimplef3.simple_f3_enabled", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(simpleF3Button);

        ToggleButton fpsButton = new ToggleButton(x, y + 21, width, height,
            SimpleF3ClientConfig.fps,
            enabled -> Component.translatable("message.hqsimplef3.fps", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(fpsButton);

        ToggleButton fpsBelowSpaceButton = new ToggleButton(x, y + 42, width, height,
            SimpleF3ClientConfig.fpsBelowSpace,
            enabled -> Component.translatable("message.hqsimplef3.fps_below_space", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(fpsBelowSpaceButton);

        ToggleButton coordinateSingleLineButton = new ToggleButton(x, y + 63, width, height,
            SimpleF3ClientConfig.coordinateSingleLine,
            enabled -> Component.translatable("message.hqsimplef3.coordinate_single_line", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(coordinateSingleLineButton);

        ToggleButton yawAndPitchButton = new ToggleButton(x, y + 84, width, height,
            SimpleF3ClientConfig.yawAndPitch,
            enabled -> Component.translatable("message.hqsimplef3.yaw_and_pitch", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(yawAndPitchButton);

        ToggleButton koreanDirectionButton = new ToggleButton(x, y + 105, width, height,
            SimpleF3ClientConfig.koreanDirection,
            enabled -> Component.translatable("message.hqsimplef3.korean_direction", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(koreanDirectionButton);

        ToggleButton koreanBiomeButton = new ToggleButton(x, y + 126, width, height,
            SimpleF3ClientConfig.koreanBiome,
            enabled -> Component.translatable("message.hqsimplef3.korean_biome", enabled ? ENABLED : DISABLED)
        );
        addRenderableWidget(koreanBiomeButton);
    }

    @Override
    public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
        if (KeyBinding.settingKey.isActiveAndMatches(InputConstants.getKey(p_96552_, p_96553_))) {
            minecraft.setScreen(null);
            return true;
        }
        return super.keyPressed(p_96552_, p_96553_, p_96554_);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        if (isIngame()) {
            guiGraphics.blit(TEXTURE, guiLeft, guiTop, 0, 0, xSize, ySize);
        }
    }

    @Override
    public void renderForeground(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        int titleWidth = font.width(TITLE);
        guiGraphics.drawString(font, TITLE.getVisualOrderText(), guiLeft + (xSize - titleWidth) / 2, guiTop + 7, getFontColor(), false);
    }
}