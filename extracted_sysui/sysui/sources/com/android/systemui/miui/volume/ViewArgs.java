package com.android.systemui.miui.volume;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewArgs {
    private final long delay;
    private long delayAlpha;
    private long delayScale;
    private long delayX;
    private final float[] ease;
    private float[] easeAlpha;
    private float[] easeScale;
    private float[] easeX;
    private float fAlpha;
    private float fScale;
    private float fX;
    private float tAlpha;
    private float tScale;
    private float tX;

    public ViewArgs() {
        this(null, 0L, 3, null);
    }

    public final long getDelay() {
        return this.delay;
    }

    public final long getDelayAlpha() {
        return this.delayAlpha;
    }

    public final long getDelayScale() {
        return this.delayScale;
    }

    public final long getDelayX() {
        return this.delayX;
    }

    public final float[] getEase() {
        return this.ease;
    }

    public final float[] getEaseAlpha() {
        return this.easeAlpha;
    }

    public final float[] getEaseScale() {
        return this.easeScale;
    }

    public final float[] getEaseX() {
        return this.easeX;
    }

    public final float getFAlpha() {
        return this.fAlpha;
    }

    public final float getFScale() {
        return this.fScale;
    }

    public final float getFX() {
        return this.fX;
    }

    public final float getTAlpha() {
        return this.tAlpha;
    }

    public final float getTScale() {
        return this.tScale;
    }

    public final float getTX() {
        return this.tX;
    }

    public final void setDelayAlpha(long j2) {
        this.delayAlpha = j2;
    }

    public final void setDelayScale(long j2) {
        this.delayScale = j2;
    }

    public final void setDelayX(long j2) {
        this.delayX = j2;
    }

    public final void setEaseAlpha(float[] fArr) {
        kotlin.jvm.internal.n.g(fArr, "<set-?>");
        this.easeAlpha = fArr;
    }

    public final void setEaseScale(float[] fArr) {
        kotlin.jvm.internal.n.g(fArr, "<set-?>");
        this.easeScale = fArr;
    }

    public final void setEaseX(float[] fArr) {
        kotlin.jvm.internal.n.g(fArr, "<set-?>");
        this.easeX = fArr;
    }

    public final void setFAlpha(float f2) {
        this.fAlpha = f2;
    }

    public final void setFScale(float f2) {
        this.fScale = f2;
    }

    public final void setFX(float f2) {
        this.fX = f2;
    }

    public final void setTAlpha(float f2) {
        this.tAlpha = f2;
    }

    public final void setTScale(float f2) {
        this.tScale = f2;
    }

    public final void setTX(float f2) {
        this.tX = f2;
    }

    public String toString() {
        String string = Arrays.toString(this.ease);
        kotlin.jvm.internal.n.f(string, "toString(...)");
        long j2 = this.delay;
        float f2 = this.fX;
        float f3 = this.tX;
        float f4 = this.fScale;
        float f5 = this.tScale;
        float f6 = this.fAlpha;
        float f7 = this.tAlpha;
        String string2 = Arrays.toString(this.easeX);
        kotlin.jvm.internal.n.f(string2, "toString(...)");
        String string3 = Arrays.toString(this.easeScale);
        kotlin.jvm.internal.n.f(string3, "toString(...)");
        String string4 = Arrays.toString(this.easeAlpha);
        kotlin.jvm.internal.n.f(string4, "toString(...)");
        return "ViewArgs(ease=" + string + ", delay=" + j2 + ", fX=" + f2 + ", tX=" + f3 + ", fScale=" + f4 + ", tScale=" + f5 + ", fAlpha=" + f6 + ", tAlpha=" + f7 + ", easeX=" + string2 + ", easeScale=" + string3 + ", easeAlpha=" + string4 + ", delayX=" + this.delayX + ", delayScale=" + this.delayScale + ", delayAlpha=" + this.delayAlpha + ")";
    }

    public ViewArgs(float[] ease, long j2) {
        kotlin.jvm.internal.n.g(ease, "ease");
        this.ease = ease;
        this.delay = j2;
        this.easeX = ease;
        this.easeScale = ease;
        this.easeAlpha = ease;
        this.delayX = j2;
        this.delayScale = j2;
        this.delayAlpha = j2;
    }

    public /* synthetic */ ViewArgs(float[] fArr, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? new float[]{1.0f, 0.35f} : fArr, (i2 & 2) != 0 ? 0L : j2);
    }
}
