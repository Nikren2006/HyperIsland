package com.miui.blur.sdk.backdrop;

import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import com.miui.blur.sdk.backdrop.k;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f2507a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Outline f2508b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f2509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2510d;

    public j() {
        Paint paint = new Paint();
        this.f2507a = paint;
        this.f2508b = new Outline();
        this.f2509c = new Rect();
        this.f2510d = false;
        paint.setAntiAlias(true);
    }

    public void a(Canvas canvas, BlurDrawInfo blurDrawInfo, float f2) {
        if (!this.f2510d || blurDrawInfo.getBlurStyle() == null || blurDrawInfo.getBlurStyle().d() == null) {
            return;
        }
        blurDrawInfo.getBlurOutline(this.f2508b);
        Outline outline = this.f2508b;
        int i2 = outline.mMode;
        if (i2 == 2) {
            c(canvas, outline.mPath, blurDrawInfo);
        } else if (i2 != 1) {
            b(canvas, blurDrawInfo);
        } else {
            outline.getRect(this.f2509c);
            d(canvas, this.f2509c, f2, blurDrawInfo);
        }
    }

    public final void b(Canvas canvas, BlurDrawInfo blurDrawInfo) {
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            f(aVar.f2521a, aVar.f2522b);
            canvas.drawRect(0.0f, 0.0f, blurDrawInfo.getWidth(), blurDrawInfo.getHeight(), this.f2507a);
        }
    }

    public final void c(Canvas canvas, Path path, BlurDrawInfo blurDrawInfo) {
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            f(aVar.f2521a, aVar.f2522b);
            canvas.drawPath(path, this.f2507a);
        }
    }

    public final void d(Canvas canvas, Rect rect, float f2, BlurDrawInfo blurDrawInfo) {
        for (k.a aVar : blurDrawInfo.getBlurStyle().d()) {
            f(aVar.f2521a, aVar.f2522b);
            canvas.drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, f2, f2, this.f2507a);
        }
    }

    public void e(boolean z2) {
        this.f2510d = z2;
    }

    public final void f(int i2, BlendMode blendMode) {
        this.f2507a.setColor(i2);
        this.f2507a.setBlendMode(blendMode);
    }
}
