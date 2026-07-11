package miui.systemui.dynamicisland.anim;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationType {
    private static final /* synthetic */ O0.a $ENTRIES;
    private static final /* synthetic */ DynamicIslandAnimationType[] $VALUES;
    public static final DynamicIslandAnimationType ALL = new DynamicIslandAnimationType("ALL", 0);
    public static final DynamicIslandAnimationType TO_EXPANDED = new DynamicIslandAnimationType("TO_EXPANDED", 1);
    public static final DynamicIslandAnimationType EXPANDED_TO_BIG = new DynamicIslandAnimationType("EXPANDED_TO_BIG", 2);
    public static final DynamicIslandAnimationType EXPANDED_TO_SMALL = new DynamicIslandAnimationType("EXPANDED_TO_SMALL", 3);
    public static final DynamicIslandAnimationType EXPANDED_TO_TEMP_HIDDEN = new DynamicIslandAnimationType("EXPANDED_TO_TEMP_HIDDEN", 4);
    public static final DynamicIslandAnimationType EXPANDED_TO_HIDDEN = new DynamicIslandAnimationType("EXPANDED_TO_HIDDEN", 5);
    public static final DynamicIslandAnimationType EXPANDED_TO_DELETED = new DynamicIslandAnimationType("EXPANDED_TO_DELETED", 6);
    public static final DynamicIslandAnimationType INIT_TO_BIG = new DynamicIslandAnimationType("INIT_TO_BIG", 7);
    public static final DynamicIslandAnimationType BIG_TO_DELETED = new DynamicIslandAnimationType("BIG_TO_DELETED", 8);

    private static final /* synthetic */ DynamicIslandAnimationType[] $values() {
        return new DynamicIslandAnimationType[]{ALL, TO_EXPANDED, EXPANDED_TO_BIG, EXPANDED_TO_SMALL, EXPANDED_TO_TEMP_HIDDEN, EXPANDED_TO_HIDDEN, EXPANDED_TO_DELETED, INIT_TO_BIG, BIG_TO_DELETED};
    }

    static {
        DynamicIslandAnimationType[] dynamicIslandAnimationTypeArr$values = $values();
        $VALUES = dynamicIslandAnimationTypeArr$values;
        $ENTRIES = O0.b.a(dynamicIslandAnimationTypeArr$values);
    }

    private DynamicIslandAnimationType(String str, int i2) {
    }

    public static O0.a getEntries() {
        return $ENTRIES;
    }

    public static DynamicIslandAnimationType valueOf(String str) {
        return (DynamicIslandAnimationType) Enum.valueOf(DynamicIslandAnimationType.class, str);
    }

    public static DynamicIslandAnimationType[] values() {
        return (DynamicIslandAnimationType[]) $VALUES.clone();
    }
}
