package kr.cosine.simplef3.registry;

import net.minecraft.util.math.Direction;

import java.util.HashMap;
import java.util.Map;

public class NameRegistry {

    private static Map<Direction, String> directionKoreanNameMap = new HashMap<>() {{
        put(Direction.EAST, "동쪽");
        put(Direction.WEST, "서쪽");
        put(Direction.SOUTH, "남쪽");
        put(Direction.NORTH, "북쪽");
    }};

    public static String findDirectionKoreanName(Direction direction) {
        return directionKoreanNameMap.getOrDefault(direction, direction.getName());
    }
}