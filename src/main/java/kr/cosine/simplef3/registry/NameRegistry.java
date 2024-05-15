package kr.cosine.simplef3.registry;


import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.HashMap;
import java.util.Map;

public class NameRegistry {

    private static Map<Direction, String> directionKoreanNameMap = new HashMap<>() {{
        put(Direction.EAST, "동쪽");
        put(Direction.WEST, "서쪽");
        put(Direction.SOUTH, "남쪽");
        put(Direction.NORTH, "북쪽");
    }};

    private static Map<RegistryKey<Biome>, String> biomeKoreanNameMap = new HashMap<>() {{
        put(BiomeKeys.SAVANNA_PLATEAU, "사바나 고원");
        put(BiomeKeys.COLD_OCEAN, "차가운 바다");
        put(BiomeKeys.END_BARRENS, "엔드 불모지");
        put(BiomeKeys.SNOWY_SLOPES, "눈 덮인 비탈");
        put(BiomeKeys.CHERRY_GROVE, "벚나무 숲");
        put(BiomeKeys.BAMBOO_JUNGLE, "대나무 정글");
        put(BiomeKeys.FROZEN_PEAKS, "얼어붙은 봉우리");
        put(BiomeKeys.MUSHROOM_FIELDS, "버섯 들판");
        put(BiomeKeys.WINDSWEPT_SAVANNA, "바람이 세찬 사바나");
        put(BiomeKeys.DRIPSTONE_CAVES, "점적석 동굴");
        put(BiomeKeys.BADLANDS, "악지");
        put(BiomeKeys.RIVER, "강");
        put(BiomeKeys.MEADOW, "목초지");
        put(BiomeKeys.SNOWY_TAIGA, "눈 덮인 타이가");
        put(BiomeKeys.GROVE, "산림");
        put(BiomeKeys.DESERT, "사막");
        put(BiomeKeys.FOREST, "숲");
        put(BiomeKeys.DEEP_DARK, "깊은 어둠");
        put(BiomeKeys.SUNFLOWER_PLAINS, "해바라기 평원");
        put(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, "자작나무 원시림");
        put(BiomeKeys.THE_VOID, "공허");
        put(BiomeKeys.BASALT_DELTAS, "현무암 삼각주");
        put(BiomeKeys.SPARSE_JUNGLE, "듬성듬성한 정글");
        put(BiomeKeys.PLAINS, "평원");
        put(BiomeKeys.SNOWY_BEACH, "눈 덮인 해변");
        put(BiomeKeys.SOUL_SAND_VALLEY, "영혼 모래 골짜기");
        put(BiomeKeys.END_HIGHLANDS, "엔드 고지");
        put(BiomeKeys.DEEP_FROZEN_OCEAN, "깊고 얼어붙은 바다");
        put(BiomeKeys.DEEP_OCEAN, "깊은 바다");
        put(BiomeKeys.WARM_OCEAN, "따뜻한 바다");
        put(BiomeKeys.OLD_GROWTH_PINE_TAIGA, "소나무 원시 타이가");
        put(BiomeKeys.SAVANNA, "사바나");
        put(BiomeKeys.STONY_PEAKS, "돌 봉우리");
        put(BiomeKeys.JAGGED_PEAKS, "뾰족한 봉우리");
        put(BiomeKeys.BIRCH_FOREST, "자작나무 숲");
        put(BiomeKeys.ICE_SPIKES, "역고드름");
        put(BiomeKeys.END_MIDLANDS, "엔드 중지");
        put(BiomeKeys.WARPED_FOREST, "뒤틀린 숲");
        put(BiomeKeys.WOODED_BADLANDS, "나무가 우거진 악지");
        put(BiomeKeys.FLOWER_FOREST, "꽃 숲");
        put(BiomeKeys.LUKEWARM_OCEAN, "미지근한 바다");
        put(BiomeKeys.WINDSWEPT_HILLS, "바람이 세찬 언덕");
        put(BiomeKeys.SWAMP, "늪");
        put(BiomeKeys.ERODED_BADLANDS, "침식된 악지");
        put(BiomeKeys.THE_END, "디 엔드");
        put(BiomeKeys.DEEP_LUKEWARM_OCEAN, "깊고 미지근한 바다");
        put(BiomeKeys.TAIGA, "타이가");
        put(BiomeKeys.JUNGLE, "정글");
        put(BiomeKeys.FROZEN_RIVER, "얼어붙은 강");
        put(BiomeKeys.SNOWY_PLAINS, "눈 덮인 평원");
        put(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, "가문비나무 원시 타이가");
        put(BiomeKeys.DEEP_COLD_OCEAN, "깊고 차가운 바다");
        put(BiomeKeys.STONY_SHORE, "돌 해안");
        put(BiomeKeys.DARK_FOREST, "어두운 숲");
        put(BiomeKeys.WINDSWEPT_FOREST, "바람이 세찬 숲");
        put(BiomeKeys.NETHER_WASTES, "네더 황무지");
        put(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, "바람이 세찬 자갈투성이 언덕");
        put(BiomeKeys.BEACH, "해변");
        put(BiomeKeys.LUSH_CAVES, "무성한 동굴");
        put(BiomeKeys.FROZEN_OCEAN, "얼어붙은 바다");
        put(BiomeKeys.MANGROVE_SWAMP, "맹그로브 늪");
        put(BiomeKeys.CRIMSON_FOREST, "진홍빛 숲");
        put(BiomeKeys.SMALL_END_ISLANDS, "작은 엔드 섬");
        put(BiomeKeys.OCEAN, "바다");
    }};

    public static String findDirectionKoreanName(Direction direction) {
        return directionKoreanNameMap.getOrDefault(direction, direction.getName());
    }

    public static String findBiomeKoreanName(RegistryKey<Biome> biomeResourceKey) {
        return biomeKoreanNameMap.getOrDefault(biomeResourceKey, biomeResourceKey.toString());
    }
}