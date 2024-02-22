package kr.cosine.simplef3.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SimpleF3ClientConfig {

    public static ForgeConfigSpec.Builder builder;

    public static ForgeConfigSpec spec;

    public static ForgeConfigSpec.ConfigValue<Boolean> isEnabled;

    public static ForgeConfigSpec.ConfigValue<Boolean> fps;

    public static ForgeConfigSpec.ConfigValue<Boolean> fpsBelowSpace;

    public static ForgeConfigSpec.ConfigValue<Boolean> coordinateSingleLine;

    public static ForgeConfigSpec.ConfigValue<Boolean> yawAndPitch;

    public static ForgeConfigSpec.ConfigValue<Boolean> koreanDirection;

    public static ForgeConfigSpec.ConfigValue<Boolean> koreanBiome;

    static {
        builder = new ForgeConfigSpec.Builder();
        builder.push("클라이언트 설정");

        isEnabled = builder.comment("F3을 심플하게 보여줍니다.")
            .define("enabled", true);

        fps = builder.comment("FPS를 보여줍니다.")
            .define("fps", false);

        fpsBelowSpace = builder.comment("FPS 아래 여백을 넣습니다.")
            .define("fps-below-space", false);

        coordinateSingleLine = builder.comment("좌표를 한 줄로 표기합니다.")
            .define("coordinate-single-line", false);

        yawAndPitch = builder.comment("Yaw와 Pitch를 보여줍니다.")
            .define("yaw-and-pitch", false);

        koreanDirection = builder.comment("방향을 한국어로 보여줍니다.")
            .define("korean-direction", true);

        koreanBiome = builder.comment("바이옴을 한국어로 보여줍니다.")
            .define("korean-biome", true);

        builder.pop();
        spec = builder.build();
    }
}
