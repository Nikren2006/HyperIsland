package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ClippableRoundedCornerLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Path f2047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2048b;

    public ClippableRoundedCornerLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (this.f2047a == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.f2047a);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    public float getCornerRadius() {
        return this.f2048b;
    }
}
