package com.miui.blur.sdk.backdrop;

import android.content.Context;
import android.graphics.Outline;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewRootImpl;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public interface ViewBlurDrawInfo extends BlurDrawInfo {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.miui.blur.sdk.backdrop.BlurDrawInfo
    @RequiresApi(api = 21)
    default void getBlurOutline(Outline outline) {
        if (getOutlineProvider() == null || !(this instanceof View)) {
            outline.setRect(0, 0, getWidth(), getHeight());
        } else {
            getOutlineProvider().getOutline((View) this, outline);
        }
    }

    @Override // com.miui.blur.sdk.backdrop.BlurDrawInfo
    @RequiresApi(api = 30)
    default k getBlurStyle() {
        return (getContext().getResources().getConfiguration().uiMode & 48) == 32 ? getBlurStyleNightMode() : getBlurStyleDayMode();
    }

    @RequiresApi(api = 30)
    default k getBlurStyleDayMode() {
        return k.f2514f;
    }

    @RequiresApi(api = 30)
    default k getBlurStyleNightMode() {
        return k.f2517i;
    }

    @Override // com.miui.blur.sdk.backdrop.BlurDrawInfo
    default ViewRootImpl getBlurViewRootImpl() {
        return getViewRootImpl();
    }

    Context getContext();

    ViewOutlineProvider getOutlineProvider();

    default SurfaceControl getSurfaceControl() {
        Method methodA;
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl != null && (methodA = m.a(viewRootImpl.getClass(), "getSurfaceControl", new Class[0])) != null) {
            methodA.setAccessible(true);
            Object objB = m.b(viewRootImpl, methodA, new Object[0]);
            if (objB instanceof SurfaceControl) {
                return (SurfaceControl) objB;
            }
        }
        return null;
    }

    ViewRootImpl getViewRootImpl();
}
