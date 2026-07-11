package com.mi.widget.core;

import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.IShaderViewListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ShaderViewStateListenerDelegate implements IShaderViewListener {
    public static final int $stable = 8;
    private List<IShaderViewListener.a> mListener = new ArrayList();

    @Override // com.mi.widget.core.IShaderViewListener
    public boolean addListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        if (this.mListener.contains(listener)) {
            return false;
        }
        return this.mListener.add(listener);
    }

    @Override // com.mi.widget.core.IShaderViewListener
    public boolean notifyVisibleChanged(View curView, View changedView, int i2) {
        n.g(curView, "curView");
        n.g(changedView, "changedView");
        boolean zIsShown = i2 != 0 ? false : curView.isShown();
        Iterator<T> it = this.mListener.iterator();
        while (it.hasNext()) {
            ((IShaderViewListener.a) it.next()).a().invoke(Boolean.valueOf(zIsShown));
        }
        return zIsShown;
    }

    @Override // com.mi.widget.core.IShaderViewListener
    public boolean removeListener(IShaderViewListener.a listener) {
        n.g(listener, "listener");
        return this.mListener.remove(listener);
    }
}
