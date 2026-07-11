package i0;

import android.content.Context;
import android.os.Bundle;
import kotlin.jvm.internal.n;
import s.AbstractC0736a;

/* JADX INFO: renamed from: i0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0407e {
    public static final Bundle a() {
        Bundle bundle = new Bundle();
        Long CARD_FRAME_VERSION = AbstractC0736a.f6435a;
        n.f(CARD_FRAME_VERSION, "CARD_FRAME_VERSION");
        bundle.putLong("keyCardFrameVersion", CARD_FRAME_VERSION.longValue());
        return bundle;
    }

    public static final void b(Context context) {
        n.g(context, "context");
        k0.b.e("[other]:", context.getPackageName() + " use card-frame-sdk code=" + AbstractC0736a.f6435a + "  name=1.0.25");
    }
}
