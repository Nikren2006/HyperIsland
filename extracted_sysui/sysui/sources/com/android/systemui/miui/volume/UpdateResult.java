package com.android.systemui.miui.volume;

/* JADX INFO: loaded from: classes2.dex */
public final class UpdateResult {
    private final boolean isContinue;
    private final float result;

    public UpdateResult(boolean z2, float f2) {
        this.isContinue = z2;
        this.result = f2;
    }

    public final float getResult() {
        return this.result;
    }

    public final boolean isContinue() {
        return this.isContinue;
    }
}
