package miuix.container;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface ExtraPaddingProcessor {
    void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver);

    @Nullable
    ExtraPaddingPolicy getExtraPaddingPolicy();

    boolean isExtraHorizontalPaddingEnable();

    void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver);

    void setExtraHorizontalPaddingEnable(boolean z2);

    void setExtraHorizontalPaddingInitEnable(boolean z2);

    void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy);
}
