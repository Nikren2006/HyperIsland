package com.airbnb.lottie.value;

import com.miui.maml.folme.AnimatedProperty;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1415a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f1416b;

    public d(float f2, float f3) {
        this.f1415a = f2;
        this.f1416b = f3;
    }

    public boolean a(float f2, float f3) {
        return this.f1415a == f2 && this.f1416b == f3;
    }

    public float b() {
        return this.f1415a;
    }

    public float c() {
        return this.f1416b;
    }

    public void d(float f2, float f3) {
        this.f1415a = f2;
        this.f1416b = f3;
    }

    public String toString() {
        return b() + AnimatedProperty.PROPERTY_NAME_X + c();
    }

    public d() {
        this(1.0f, 1.0f);
    }
}
