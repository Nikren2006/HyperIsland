package miui.systemui.dynamicisland.anim;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationCallbackType {
    private static final /* synthetic */ O0.a $ENTRIES;
    private static final /* synthetic */ DynamicIslandAnimationCallbackType[] $VALUES;
    public static final DynamicIslandAnimationCallbackType ANIM_START = new DynamicIslandAnimationCallbackType("ANIM_START", 0);
    public static final DynamicIslandAnimationCallbackType ANIM_FINISH = new DynamicIslandAnimationCallbackType("ANIM_FINISH", 1);
    public static final DynamicIslandAnimationCallbackType ANIM_CANCEL = new DynamicIslandAnimationCallbackType("ANIM_CANCEL", 2);

    private static final /* synthetic */ DynamicIslandAnimationCallbackType[] $values() {
        return new DynamicIslandAnimationCallbackType[]{ANIM_START, ANIM_FINISH, ANIM_CANCEL};
    }

    static {
        DynamicIslandAnimationCallbackType[] dynamicIslandAnimationCallbackTypeArr$values = $values();
        $VALUES = dynamicIslandAnimationCallbackTypeArr$values;
        $ENTRIES = O0.b.a(dynamicIslandAnimationCallbackTypeArr$values);
    }

    private DynamicIslandAnimationCallbackType(String str, int i2) {
    }

    public static O0.a getEntries() {
        return $ENTRIES;
    }

    public static DynamicIslandAnimationCallbackType valueOf(String str) {
        return (DynamicIslandAnimationCallbackType) Enum.valueOf(DynamicIslandAnimationCallbackType.class, str);
    }

    public static DynamicIslandAnimationCallbackType[] values() {
        return (DynamicIslandAnimationCallbackType[]) $VALUES.clone();
    }
}
