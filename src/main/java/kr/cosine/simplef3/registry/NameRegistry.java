package kr.cosine.simplef3.registry;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.HashMap;
import java.util.Map;

public class NameRegistry {

    private static Map<Direction, String> directionKoreanNameMap = new HashMap<>() {{
        put(Direction.EAST, "동쪽");
        put(Direction.WEST, "서쪽");
        put(Direction.SOUTH, "남쪽");
        put(Direction.NORTH, "북쪽");
    }};

    private static Map<ResourceKey<Biome>, String> biomeKoreanNameMap = new HashMap<>() {{
        put(Biomes.SAVANNA_PLATEAU, "사바나 고원");
        put(Biomes.COLD_OCEAN, "차가운 바다");
        put(Biomes.END_BARRENS, "엔드 불모지");
        put(Biomes.SNOWY_SLOPES, "눈 덮인 비탈");
        put(Biomes.CHERRY_GROVE, "벚나무 숲");
        put(Biomes.BAMBOO_JUNGLE, "대나무 정글");
        put(Biomes.FROZEN_PEAKS, "얼어붙은 봉우리");
        put(Biomes.MUSHROOM_FIELDS, "버섯 들판");
        put(Biomes.WINDSWEPT_SAVANNA, "바람이 세찬 사바나");
        put(Biomes.DRIPSTONE_CAVES, "점적석 동굴");
        put(Biomes.BADLANDS, "악지");
        put(Biomes.RIVER, "강");
        put(Biomes.MEADOW, "목초지");
        put(Biomes.SNOWY_TAIGA, "눈 덮인 타이가");
        put(Biomes.GROVE, "산림");
        put(Biomes.DESERT, "사막");
        put(Biomes.FOREST, "숲");
        put(Biomes.DEEP_DARK, "깊은 어둠");
        put(Biomes.SUNFLOWER_PLAINS, "해바라기 평원");
        put(Biomes.OLD_GROWTH_BIRCH_FOREST, "자작나무 원시림");
        put(Biomes.THE_VOID, "공허");
        put(Biomes.BASALT_DELTAS, "현무암 삼각주");
        put(Biomes.SPARSE_JUNGLE, "듬성듬성한 정글");
        put(Biomes.PLAINS, "평원");
        put(Biomes.SNOWY_BEACH, "눈 덮인 해변");
        put(Biomes.SOUL_SAND_VALLEY, "영혼 모래 골짜기");
        put(Biomes.END_HIGHLANDS, "엔드 고지");
        put(Biomes.DEEP_FROZEN_OCEAN, "깊고 얼어붙은 바다");
        put(Biomes.DEEP_OCEAN, "깊은 바다");
        put(Biomes.WARM_OCEAN, "따뜻한 바다");
        put(Biomes.OLD_GROWTH_PINE_TAIGA, "소나무 원시 타이가");
        put(Biomes.SAVANNA, "사바나");
        put(Biomes.STONY_PEAKS, "돌 봉우리");
        put(Biomes.JAGGED_PEAKS, "뾰족한 봉우리");
        put(Biomes.BIRCH_FOREST, "자작나무 숲");
        put(Biomes.ICE_SPIKES, "역고드름");
        put(Biomes.END_MIDLANDS, "엔드 중지");
        put(Biomes.WARPED_FOREST, "뒤틀린 숲");
        put(Biomes.WOODED_BADLANDS, "나무가 우거진 악지");
        put(Biomes.FLOWER_FOREST, "꽃 숲");
        put(Biomes.LUKEWARM_OCEAN, "미지근한 바다");
        put(Biomes.WINDSWEPT_HILLS, "바람이 세찬 언덕");
        put(Biomes.SWAMP, "늪");
        put(Biomes.ERODED_BADLANDS, "침식된 악지");
        put(Biomes.THE_END, "디 엔드");
        put(Biomes.DEEP_LUKEWARM_OCEAN, "깊고 미지근한 바다");
        put(Biomes.TAIGA, "타이가");
        put(Biomes.JUNGLE, "정글");
        put(Biomes.FROZEN_RIVER, "얼어붙은 강");
        put(Biomes.SNOWY_PLAINS, "눈 덮인 평원");
        put(Biomes.OLD_GROWTH_SPRUCE_TAIGA, "가문비나무 원시 타이가");
        put(Biomes.DEEP_COLD_OCEAN, "깊고 차가운 바다");
        put(Biomes.STONY_SHORE, "돌 해안");
        put(Biomes.DARK_FOREST, "어두운 숲");
        put(Biomes.WINDSWEPT_FOREST, "바람이 세찬 숲");
        put(Biomes.NETHER_WASTES, "네더 황무지");
        put(Biomes.WINDSWEPT_GRAVELLY_HILLS, "바람이 세찬 자갈투성이 언덕");
        put(Biomes.BEACH, "해변");
        put(Biomes.LUSH_CAVES, "무성한 동굴");
        put(Biomes.FROZEN_OCEAN, "얼어붙은 바다");
        put(Biomes.MANGROVE_SWAMP, "맹그로브 늪");
        put(Biomes.CRIMSON_FOREST, "진홍빛 숲");
        put(Biomes.SMALL_END_ISLANDS, "작은 엔드 섬");
        put(Biomes.OCEAN, "바다");
    }};

    public static String findDirectionKoreanName(Direction direction) {
        return directionKoreanNameMap.getOrDefault(direction, direction.getName());
    }

    public static String findBiomeKoreanName(ResourceKey<Biome> biomeResourceKey) {
        return biomeKoreanNameMap.getOrDefault(biomeResourceKey, biomeResourceKey.toString());
    }
}
