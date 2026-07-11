package miui.systemui.dynamicisland.touch;

import O0.a;
import O0.b;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class Direction {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ Direction[] $VALUES;
    public static final Direction UNKNOWN = new Direction("UNKNOWN", 0);
    public static final Direction UP = new Direction("UP", 1);
    public static final Direction DOWN = new Direction("DOWN", 2);
    public static final Direction LEFT = new Direction("LEFT", 3);
    public static final Direction RIGHT = new Direction("RIGHT", 4);

    private static final /* synthetic */ Direction[] $values() {
        return new Direction[]{UNKNOWN, UP, DOWN, LEFT, RIGHT};
    }

    static {
        Direction[] directionArr$values = $values();
        $VALUES = directionArr$values;
        $ENTRIES = b.a(directionArr$values);
    }

    private Direction(String str, int i2) {
    }

    public static a getEntries() {
        return $ENTRIES;
    }

    public static Direction valueOf(String str) {
        return (Direction) Enum.valueOf(Direction.class, str);
    }

    public static Direction[] values() {
        return (Direction[]) $VALUES.clone();
    }

    public final boolean isDown() {
        return this == DOWN;
    }

    public final boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }

    public final boolean isLeft() {
        return this == LEFT;
    }

    public final boolean isRight() {
        return this == RIGHT;
    }

    public final boolean isUp() {
        return this == UP;
    }

    public final boolean isVertical() {
        return this == UP || this == DOWN;
    }
}
