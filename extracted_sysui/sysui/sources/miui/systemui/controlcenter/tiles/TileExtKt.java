package miui.systemui.controlcenter.tiles;

/* JADX INFO: loaded from: classes.dex */
public final class TileExtKt {
    public static final String getTileStateToString(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "UNKNOWN" : "STATE_ACTIVE" : "STATE_INACTIVE" : "STATE_UNAVAILABLE";
    }
}
