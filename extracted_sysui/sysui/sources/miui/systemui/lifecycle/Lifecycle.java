package miui.systemui.lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import java.util.ArrayList;
import java.util.List;
import miui.systemui.lifecycle.events.OnAttach;
import miui.systemui.lifecycle.events.OnCreate;
import miui.systemui.lifecycle.events.OnCreateOptionsMenu;
import miui.systemui.lifecycle.events.OnDestroy;
import miui.systemui.lifecycle.events.OnOptionsItemSelected;
import miui.systemui.lifecycle.events.OnPause;
import miui.systemui.lifecycle.events.OnPrepareOptionsMenu;
import miui.systemui.lifecycle.events.OnResume;
import miui.systemui.lifecycle.events.OnSaveInstanceState;
import miui.systemui.lifecycle.events.OnStart;
import miui.systemui.lifecycle.events.OnStop;
import miui.systemui.lifecycle.events.SetPreferenceScreen;
import miui.systemui.util.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class Lifecycle extends LifecycleRegistry {
    private static final String TAG = "LifecycleObserver";
    private final List<LifecycleObserver> mObservers;
    private final LifecycleProxy mProxy;

    /* JADX INFO: renamed from: miui.systemui.lifecycle.Lifecycle$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public class LifecycleProxy implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void onLifecycleEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            switch (AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()]) {
                case 2:
                    Lifecycle.this.onStart();
                    break;
                case 3:
                    Lifecycle.this.onResume();
                    break;
                case 4:
                    Lifecycle.this.onPause();
                    break;
                case 5:
                    Lifecycle.this.onStop();
                    break;
                case 6:
                    Lifecycle.this.onDestroy();
                    break;
                case 7:
                    Log.wtf(Lifecycle.TAG, "Should not receive an 'ANY' event!");
                    break;
            }
        }

        private LifecycleProxy() {
        }
    }

    public Lifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        this.mObservers = new ArrayList();
        LifecycleProxy lifecycleProxy = new LifecycleProxy();
        this.mProxy = lifecycleProxy;
        addObserver(lifecycleProxy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDestroy() {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnDestroy) {
                ((OnDestroy) lifecycleObserver).onDestroy();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPause() {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnPause) {
                ((OnPause) lifecycleObserver).onPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResume() {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnResume) {
                ((OnResume) lifecycleObserver).onResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStart() {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnStart) {
                ((OnStart) lifecycleObserver).onStart();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStop() {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnStop) {
                ((OnStop) lifecycleObserver).onStop();
            }
        }
    }

    @Override // androidx.lifecycle.LifecycleRegistry, androidx.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver lifecycleObserver) {
        ThreadUtils.ensureMainThread();
        super.addObserver(lifecycleObserver);
        if (lifecycleObserver != null) {
            this.mObservers.add(lifecycleObserver);
        }
    }

    public void onAttach(Context context) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnAttach) {
                ((OnAttach) lifecycleObserver).onAttach();
            }
        }
    }

    public void onCreate(Bundle bundle) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnCreate) {
                ((OnCreate) lifecycleObserver).onCreate(bundle);
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, @Nullable MenuInflater menuInflater) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnCreateOptionsMenu) {
                ((OnCreateOptionsMenu) lifecycleObserver).onCreateOptionsMenu(menu, menuInflater);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if ((lifecycleObserver instanceof OnOptionsItemSelected) && ((OnOptionsItemSelected) lifecycleObserver).onOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnPrepareOptionsMenu) {
                ((OnPrepareOptionsMenu) lifecycleObserver).onPrepareOptionsMenu(menu);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof OnSaveInstanceState) {
                ((OnSaveInstanceState) lifecycleObserver).onSaveInstanceState(bundle);
            }
        }
    }

    @Override // androidx.lifecycle.LifecycleRegistry, androidx.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver lifecycleObserver) {
        ThreadUtils.ensureMainThread();
        super.removeObserver(lifecycleObserver);
        if (lifecycleObserver != null) {
            this.mObservers.remove(lifecycleObserver);
        }
    }

    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        int size = this.mObservers.size();
        for (int i2 = 0; i2 < size; i2++) {
            LifecycleObserver lifecycleObserver = this.mObservers.get(i2);
            if (lifecycleObserver instanceof SetPreferenceScreen) {
                ((SetPreferenceScreen) lifecycleObserver).setPreferenceScreen(preferenceScreen);
            }
        }
    }
}
