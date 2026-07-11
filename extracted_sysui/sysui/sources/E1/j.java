package E1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public enum j {
    INTERNAL_POWERSOUND(1),
    INTERNAL_PANORAMA(2),
    EXTERNAL_HEADSET(3),
    EXTERNAL_MRC(4),
    INTERNAL_CUSTOM1(5),
    INTERNAL_CUSTOM2(6),
    INTERNAL_CUSTOM3(7),
    INTERNAL_CUSTOM4(8),
    INTERNAL_CUSTOM5(9);


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f160a;

    j(int i2) {
        this.f160a = i2;
    }

    public static j a(int i2) {
        for (j jVar : Arrays.asList(values())) {
            if (jVar.f160a == i2) {
                return jVar;
            }
        }
        throw new IllegalArgumentException("unsupported value: " + i2);
    }

    public int b() {
        return this.f160a;
    }
}
