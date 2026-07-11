package miuix.responsive.wrapper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Factory2Wrapper implements LayoutInflater.Factory2 {

    @Nullable
    private LayoutInflater.Factory2 mOriginFactory2;

    @Override // android.view.LayoutInflater.Factory2
    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        LayoutInflater.Factory2 factory2 = this.mOriginFactory2;
        if (factory2 != null) {
            return factory2.onCreateView(view, str, context, attributeSet);
        }
        return null;
    }

    public void onDestroy() {
        this.mOriginFactory2 = null;
    }

    public void setOriginFactory2(LayoutInflater.Factory2 factory2) {
        this.mOriginFactory2 = factory2;
    }

    @Override // android.view.LayoutInflater.Factory
    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        LayoutInflater.Factory2 factory2 = this.mOriginFactory2;
        if (factory2 != null) {
            return factory2.onCreateView(str, context, attributeSet);
        }
        return null;
    }
}
