package com.mi.widget.utils;

import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewsKt {
    @Keep
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final float dP(View view, float f2) {
        n.g(view, "<this>");
        return f2 * view.getContext().getResources().getDisplayMetrics().density;
    }
}
