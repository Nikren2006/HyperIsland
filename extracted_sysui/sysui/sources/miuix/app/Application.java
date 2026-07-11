package miuix.app;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.List;
import miuix.core.R;
import miuix.core.util.EnvStateManager;
import miuix.core.util.HyperMaterialUtils;

/* JADX INFO: loaded from: classes4.dex */
public class Application extends android.app.Application {
    private ComponentCallbacksWrapper mComponentCallbacksWrapper;
    private LifecycleCallbacksWrapper mLifecycleCallbacksWrapper;
    private Object mLifecycleLock = new Object();
    private Object mComponentLock = new Object();

    public static class ComponentCallbacksWrapper implements ComponentCallbacks {
        private List<ComponentCallbacks> mComponentSubCallbacks;
        private final Context mContext;

        public ComponentCallbacksWrapper(Context context) {
            this.mContext = context;
        }

        private void forAllComponentCallbacks(Consumer<ComponentCallbacks> consumer) {
            synchronized (this) {
                List<ComponentCallbacks> list = this.mComponentSubCallbacks;
                if (list != null && !list.isEmpty()) {
                    int size = this.mComponentSubCallbacks.size();
                    ComponentCallbacks[] componentCallbacksArr = new ComponentCallbacks[size];
                    this.mComponentSubCallbacks.toArray(componentCallbacksArr);
                    for (int i2 = 0; i2 < size; i2++) {
                        consumer.accept(componentCallbacksArr[i2]);
                    }
                }
            }
        }

        public int getSize() {
            return this.mComponentSubCallbacks.size();
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(@NonNull final Configuration configuration) {
            forAllComponentCallbacks(new Consumer() { // from class: miuix.app.b
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    ((ComponentCallbacks) obj).onConfigurationChanged(configuration);
                }
            });
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            forAllComponentCallbacks(new Consumer() { // from class: miuix.app.a
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    ((ComponentCallbacks) obj).onLowMemory();
                }
            });
        }

        public void registerCallBacks(@NonNull ComponentCallbacks componentCallbacks) {
            if (this.mComponentSubCallbacks == null) {
                this.mComponentSubCallbacks = new ArrayList();
            }
            this.mComponentSubCallbacks.add(componentCallbacks);
        }

        public void unregisterCallBacks(@NonNull ComponentCallbacks componentCallbacks) {
            List<ComponentCallbacks> list = this.mComponentSubCallbacks;
            if (list == null || list.isEmpty()) {
                return;
            }
            this.mComponentSubCallbacks.remove(componentCallbacks);
        }
    }

    public static class LifecycleCallbacksWrapper implements Application.ActivityLifecycleCallbacks {
        private ArrayList<Application.ActivityLifecycleCallbacks> mActivitySubLifecycleCallbacks = new ArrayList<>();

        private Object[] collectActivityLifecycleSubCallbacks() {
            Object[] array;
            synchronized (this.mActivitySubLifecycleCallbacks) {
                try {
                    array = this.mActivitySubLifecycleCallbacks.size() > 0 ? this.mActivitySubLifecycleCallbacks.toArray() : null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return array;
        }

        public boolean add(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            return this.mActivitySubLifecycleCallbacks.add(activityLifecycleCallbacks);
        }

        public int getSize() {
            return this.mActivitySubLifecycleCallbacks.size();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityCreated(activity, bundle);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityDestroyed(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityPaused(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityResumed(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivitySaveInstanceState(activity, bundle);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityStarted(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
            Object[] objArrCollectActivityLifecycleSubCallbacks = collectActivityLifecycleSubCallbacks();
            if (objArrCollectActivityLifecycleSubCallbacks != null) {
                for (Object obj : objArrCollectActivityLifecycleSubCallbacks) {
                    ((Application.ActivityLifecycleCallbacks) obj).onActivityStopped(activity);
                }
            }
        }

        public boolean remove(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            return this.mActivitySubLifecycleCallbacks.remove(activityLifecycleCallbacks);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        HyperMaterialUtils.clearFeatureEnable();
        EnvStateManager.markEnvStateDirty(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Application
    public void onCreate() {
        EnvStateManager.init(this);
        super.onCreate();
        Resources resources = getApplicationContext().getResources();
        if (resources.getInteger(R.integer.miuix_theme_use_third_party_theme) == 2) {
            UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
            uiModeManager.setApplicationNightMode(0);
            if (uiModeManager.getNightMode() == 1) {
                if (resources.getBoolean(R.bool.miuix_theme_use_light_theme_in_light)) {
                    return;
                }
                uiModeManager.setApplicationNightMode(2);
            } else if (resources.getBoolean(R.bool.miuix_theme_use_light_theme_in_dark)) {
                uiModeManager.setApplicationNightMode(1);
            }
        }
    }

    public void registerActivityLifecycleSubCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        synchronized (this.mLifecycleLock) {
            try {
                if (this.mLifecycleCallbacksWrapper == null) {
                    LifecycleCallbacksWrapper lifecycleCallbacksWrapper = new LifecycleCallbacksWrapper();
                    this.mLifecycleCallbacksWrapper = lifecycleCallbacksWrapper;
                    registerActivityLifecycleCallbacks(lifecycleCallbacksWrapper);
                }
                this.mLifecycleCallbacksWrapper.add(activityLifecycleCallbacks);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void registerComponentSubCallbacks(ComponentCallbacks componentCallbacks) {
        synchronized (this.mComponentLock) {
            try {
                if (this.mComponentCallbacksWrapper == null) {
                    ComponentCallbacksWrapper componentCallbacksWrapper = new ComponentCallbacksWrapper(this);
                    this.mComponentCallbacksWrapper = componentCallbacksWrapper;
                    registerComponentCallbacks(componentCallbacksWrapper);
                }
                this.mComponentCallbacksWrapper.registerCallBacks(componentCallbacks);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterActivityLifecycleSubCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        synchronized (this.mLifecycleLock) {
            try {
                LifecycleCallbacksWrapper lifecycleCallbacksWrapper = this.mLifecycleCallbacksWrapper;
                if (lifecycleCallbacksWrapper != null) {
                    lifecycleCallbacksWrapper.remove(activityLifecycleCallbacks);
                    if (this.mLifecycleCallbacksWrapper.getSize() == 0) {
                        unregisterActivityLifecycleCallbacks(this.mLifecycleCallbacksWrapper);
                        this.mLifecycleCallbacksWrapper = null;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterComponentSubCallbacks(ComponentCallbacks componentCallbacks) {
        synchronized (this.mComponentLock) {
            try {
                ComponentCallbacksWrapper componentCallbacksWrapper = this.mComponentCallbacksWrapper;
                if (componentCallbacksWrapper != null) {
                    componentCallbacksWrapper.unregisterCallBacks(componentCallbacks);
                    if (this.mComponentCallbacksWrapper.getSize() == 0) {
                        unregisterComponentCallbacks(this.mComponentCallbacksWrapper);
                        this.mComponentCallbacksWrapper = null;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
