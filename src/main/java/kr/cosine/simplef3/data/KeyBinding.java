package kr.cosine.simplef3.data;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    private static final String KEY_HQSIMPLEF3_SETTING = "key.hqsimplef3_setting";
    private static final String KEY_CATEGORIES_HQSIMPLEF3 = "key.categories.hqsimplef3";

    public static KeyMapping settingKey = new KeyMapping(KEY_HQSIMPLEF3_SETTING, GLFW.GLFW_KEY_B, KEY_CATEGORIES_HQSIMPLEF3);
}
