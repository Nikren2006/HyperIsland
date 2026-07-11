package com.mi.widget.core;

import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.ShaderDriver;
import d1.InterfaceC0324c;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public class ShaderDriver {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "ShaderDriver";
    private float mGlowTime;
    private static final a Companion = new a(null);
    public static final int $stable = 8;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private final CopyOnWriteArrayList<c> mShaderDriven = new CopyOnWriteArrayList<>();

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private final Map<InterfaceC0324c, IDriverShareStructure> mShaderDriverData = new LinkedHashMap();

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private long mStartTime = SystemClock.elapsedRealtime();
    private long mLastTime = SystemClock.elapsedRealtime();

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static final class b implements IDriverShareStructure {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2352a = new b();

        @Override // com.mi.widget.core.IDriverShareStructure
        public boolean isInitialized() {
            return false;
        }
    }

    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final IShaderDriven f2353a;

        public c(IShaderDriven shaderDriven) {
            n.g(shaderDriven, "shaderDriven");
            this.f2353a = shaderDriven;
        }

        public final IShaderDriven a() {
            return this.f2353a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof c) && n.c(this.f2353a, ((c) obj).f2353a);
        }

        public int hashCode() {
            return this.f2353a.hashCode();
        }

        public String toString() {
            return "ShaderDrivenInfo(shaderDriven=" + this.f2353a + ")";
        }
    }

    public static final class d extends o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IShaderDriven f2354a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(IShaderDriven iShaderDriven) {
            super(1);
            this.f2354a = iShaderDriven;
        }

        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Boolean invoke(c cVar) {
            return Boolean.valueOf(n.c(cVar.a(), this.f2354a));
        }
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMShaderDriven$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMShaderDriverData$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMStartTime$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeShaderDriven$lambda$2(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized boolean addShaderDriven(IShaderDriven shaderDriven) {
        try {
            n.g(shaderDriven, "shaderDriven");
            CopyOnWriteArrayList<c> copyOnWriteArrayList = this.mShaderDriven;
            if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
                Iterator<T> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    if (n.c(((c) it.next()).a(), shaderDriven)) {
                        return false;
                    }
                }
            }
            if (!this.mShaderDriven.isEmpty()) {
                if (shaderDriven.getDriverShareStrategy() == DriverShareStrategy.NOT_SUPPORTED) {
                    throw new IllegalArgumentException("Shader driven " + z.b(shaderDriven.getClass()).c() + " doesn't support share driver.");
                }
                CopyOnWriteArrayList<c> copyOnWriteArrayList2 = this.mShaderDriven;
                if (copyOnWriteArrayList2 == null || !copyOnWriteArrayList2.isEmpty()) {
                    Iterator<T> it2 = copyOnWriteArrayList2.iterator();
                    while (it2.hasNext()) {
                        if (((c) it2.next()).a().getDriverShareStrategy() != shaderDriven.getDriverShareStrategy()) {
                            throw new IllegalArgumentException("Shader driven " + z.b(shaderDriven.getClass()).c() + " only support share driver with " + shaderDriven.getDriverShareStrategy() + " strategy.");
                        }
                    }
                }
            }
            Log.d(LOG_TAG, "add shader driven instance=" + shaderDriven);
            return this.mShaderDriven.add(new c(shaderDriven));
        } catch (Throwable th) {
            throw th;
        }
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized void clear() {
        reset();
        this.mShaderDriven.clear();
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void driveShader() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (this.mStartTime <= 0) {
            this.mStartTime = SystemClock.elapsedRealtime();
            this.mLastTime = SystemClock.elapsedRealtime();
        }
        this.mGlowTime += ((SystemClock.elapsedRealtime() - this.mLastTime) / 1000.0f) / 2.0f;
        this.mLastTime = SystemClock.elapsedRealtime();
        for (c cVar : this.mShaderDriven) {
            int iIntValue = ((Number) linkedHashMap.getOrDefault(z.b(cVar.a().getClass()), 0)).intValue();
            DriverShareStrategy driverShareStrategy = cVar.a().getDriverShareStrategy();
            DriverShareStrategy driverShareStrategy2 = DriverShareStrategy.SHARE_SAME_TYPE;
            IDriverShareStructure iDriverShareStructureOnDriveFrameParameters = cVar.a().onDriveFrameParameters(iIntValue == 0, this.mStartTime, this.mGlowTime, driverShareStrategy != driverShareStrategy2 ? b.f2352a : this.mShaderDriverData.getOrDefault(z.b(cVar.a().getClass()), b.f2352a));
            linkedHashMap.put(z.b(cVar.a().getClass()), Integer.valueOf(iIntValue + 1));
            if (cVar.a().getDriverShareStrategy() == driverShareStrategy2) {
                this.mShaderDriverData.put(z.b(cVar.a().getClass()), iDriverShareStructureOnDriveFrameParameters);
            }
        }
    }

    public final CopyOnWriteArrayList<c> getMShaderDriven$hyper_widget_1_0_7_pluginRelease() {
        return this.mShaderDriven;
    }

    public final Map<InterfaceC0324c, IDriverShareStructure> getMShaderDriverData$hyper_widget_1_0_7_pluginRelease() {
        return this.mShaderDriverData;
    }

    public final long getMStartTime$hyper_widget_1_0_7_pluginRelease() {
        return this.mStartTime;
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized void init() {
        this.mGlowTime = 0.0f;
        this.mStartTime = SystemClock.elapsedRealtime();
        this.mLastTime = SystemClock.elapsedRealtime();
        Iterator<T> it = this.mShaderDriven.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a().onInitFrameParameters();
        }
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized boolean removeShaderDriven(IShaderDriven shaderDriven) {
        boolean zRemoveIf;
        try {
            n.g(shaderDriven, "shaderDriven");
            CopyOnWriteArrayList<c> copyOnWriteArrayList = this.mShaderDriven;
            final d dVar = new d(shaderDriven);
            zRemoveIf = copyOnWriteArrayList.removeIf(new Predicate() { // from class: f0.g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ShaderDriver.removeShaderDriven$lambda$2(dVar, obj);
                }
            });
            CopyOnWriteArrayList<c> copyOnWriteArrayList2 = this.mShaderDriven;
            if (copyOnWriteArrayList2 == null || !copyOnWriteArrayList2.isEmpty()) {
                Iterator<T> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    if (((c) it.next()).a().getClass() == shaderDriven.getClass()) {
                        break;
                    }
                }
            }
            this.mShaderDriverData.remove(z.b(shaderDriven.getClass()));
            Log.i(LOG_TAG, "remove shader driver data for " + z.b(shaderDriven.getClass()).c());
            Log.d(LOG_TAG, "remove shader driven instance=" + shaderDriven + ", result=" + zRemoveIf);
        } catch (Throwable th) {
            throw th;
        }
        return zRemoveIf;
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized void reset() {
        this.mShaderDriverData.clear();
        Iterator<T> it = this.mShaderDriven.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a().onResetFrameParameters();
        }
    }

    public final void setMStartTime$hyper_widget_1_0_7_pluginRelease(long j2) {
        this.mStartTime = j2;
    }

    @AnyThread
    public final synchronized void synchronize(ShaderDriver driver) {
        try {
            n.g(driver, "driver");
            CopyOnWriteArrayList<c> copyOnWriteArrayList = driver.mShaderDriven;
            if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
                Iterator<T> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    if (((c) it.next()).a().getDriverShareStrategy() != DriverShareStrategy.SHARE_COMMON_TYPE) {
                        break;
                    }
                }
            }
            CopyOnWriteArrayList<c> copyOnWriteArrayList2 = this.mShaderDriven;
            if (copyOnWriteArrayList2 == null || !copyOnWriteArrayList2.isEmpty()) {
                Iterator<T> it2 = copyOnWriteArrayList2.iterator();
                while (it2.hasNext()) {
                    if (((c) it2.next()).a().getDriverShareStrategy() != DriverShareStrategy.SHARE_COMMON_TYPE) {
                        throw new IllegalAccessException("Synchronize only support between ShaderDrivers with SHARE_COMMON_TYPE shader driven.");
                    }
                }
            }
            Log.d(LOG_TAG, "synchronize with shader driven instance=" + driver);
            this.mStartTime = driver.mStartTime;
            this.mLastTime = driver.mLastTime;
            this.mGlowTime = driver.mGlowTime;
        } catch (Throwable th) {
            throw th;
        }
    }
}
