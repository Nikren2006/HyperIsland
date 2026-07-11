package miuix.responsive.page.manager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import miuix.responsive.wrapper.Factory2Wrapper;

/* JADX INFO: loaded from: classes5.dex */
public class ResponsiveFactory2Wrapper extends Factory2Wrapper {
    private final WeakReference<BaseResponseStateManager> mStateManagerRef;

    public ResponsiveFactory2Wrapper(BaseResponseStateManager baseResponseStateManager) {
        this.mStateManagerRef = new WeakReference<>(baseResponseStateManager);
    }

    @Override // miuix.responsive.wrapper.Factory2Wrapper, android.view.LayoutInflater.Factory2
    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        BaseResponseStateManager baseResponseStateManager = this.mStateManagerRef.get();
        if (baseResponseStateManager != null && view != null) {
            baseResponseStateManager.parseResponsiveViews(context, view, attributeSet, str);
        }
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // miuix.responsive.wrapper.Factory2Wrapper
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // miuix.responsive.wrapper.Factory2Wrapper
    public void setOriginFactory2(LayoutInflater.Factory2 factory2) {
        super.setOriginFactory2(factory2);
    }

    @Override // miuix.responsive.wrapper.Factory2Wrapper, android.view.LayoutInflater.Factory
    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }
}
