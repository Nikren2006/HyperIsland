package miui.systemui.dagger;

import android.content.Context;
import android.util.AttributeSet;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class ViewAttributeProvider {
    private final AttributeSet mAttrs;
    private final Context mContext;

    public ViewAttributeProvider(Context mContext, AttributeSet mAttrs) {
        n.g(mContext, "mContext");
        n.g(mAttrs, "mAttrs");
        this.mContext = mContext;
        this.mAttrs = mAttrs;
    }

    public final AttributeSet provideAttributeSet() {
        return this.mAttrs;
    }

    public final Context provideContext() {
        return this.mContext;
    }
}
