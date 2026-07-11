package com.android.systemui.folme;

import android.view.View;
import kotlin.jvm.internal.n;
import miuix.animation.Folme;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
public final class ViewAnimExtentionsKt {
    public static final void clearTouchAnim(View view) {
        n.g(view, "<this>");
        Folme.clean(view);
    }

    public static final void setTouchAlphaAnim(View view) {
        n.g(view, "<this>");
        Folme.useAt(view).touch().setScale(1.0f, ITouchStyle.TouchType.DOWN).setScale(1.0f, ITouchStyle.TouchType.UP).setTint(0.08f, 0.0f, 0.0f, 0.0f).handleTouchOf(view, new AnimConfig[0]);
    }

    public static final void setTouchAnim(View view) {
        n.g(view, "<this>");
        ITouchStyle iTouchStyle = Folme.useAt(view).touch();
        ITouchStyle.TouchType touchType = ITouchStyle.TouchType.DOWN;
        ITouchStyle scale = iTouchStyle.setScale(1.0f, touchType);
        ITouchStyle.TouchType touchType2 = ITouchStyle.TouchType.UP;
        scale.setScale(1.0f, touchType2).setAlpha(0.5f, touchType).setAlpha(1.0f, touchType2).handleTouchOf(view, new AnimConfig());
    }
}
