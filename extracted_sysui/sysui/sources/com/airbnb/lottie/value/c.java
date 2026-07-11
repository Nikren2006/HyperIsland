package com.airbnb.lottie.value;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import g.AbstractC0355a;

/* JADX INFO: loaded from: classes.dex */
public class c {

    @Nullable
    private AbstractC0355a animation;
    private final b frameInfo;

    @Nullable
    protected Object value;

    public c() {
        this.frameInfo = new b();
        this.value = null;
    }

    public Object getValue(b bVar) {
        return this.value;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final Object getValueInternal(float f2, float f3, Object obj, Object obj2, float f4, float f5, float f6) {
        return getValue(this.frameInfo.h(f2, f3, obj, obj2, f4, f5, f6));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setAnimation(@Nullable AbstractC0355a abstractC0355a) {
        this.animation = abstractC0355a;
    }

    public final void setValue(@Nullable Object obj) {
        this.value = obj;
        AbstractC0355a abstractC0355a = this.animation;
        if (abstractC0355a != null) {
            abstractC0355a.k();
        }
    }

    public c(Object obj) {
        this.frameInfo = new b();
        this.value = obj;
    }
}
