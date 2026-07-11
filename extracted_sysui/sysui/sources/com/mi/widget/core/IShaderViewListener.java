package com.mi.widget.core;

import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface IShaderViewListener {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Function1 f2344a;

        public a(Function1 visibleChange) {
            n.g(visibleChange, "visibleChange");
            this.f2344a = visibleChange;
        }

        public final Function1 a() {
            return this.f2344a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && n.c(this.f2344a, ((a) obj).f2344a);
        }

        public int hashCode() {
            return this.f2344a.hashCode();
        }

        public String toString() {
            return "Listener(visibleChange=" + this.f2344a + ")";
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    boolean addListener(a aVar);

    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    boolean notifyVisibleChanged(View view, View view2, int i2);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    boolean removeListener(a aVar);
}
