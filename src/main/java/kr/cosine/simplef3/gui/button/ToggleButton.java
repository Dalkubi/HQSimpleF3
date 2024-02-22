package kr.cosine.simplef3.gui.button;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Function;

public class ToggleButton extends AbstractButton {

    private final ForgeConfigSpec.ConfigValue<Boolean> configValue;
    private final Function<Boolean, Component> component;

    public ToggleButton(int x, int y, int width, int height, ForgeConfigSpec.ConfigValue<Boolean> entry, Function<Boolean, Component> component) {
        super(x, y, width, height, Component.empty());
        this.configValue = entry;
        this.component = component;
        updateText();
    }

    private void updateText() {
        setMessage(component.apply(configValue.get()));
    }

    @Override
    public void onPress() {
        configValue.set(!configValue.get());
        configValue.save();
        updateText();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}