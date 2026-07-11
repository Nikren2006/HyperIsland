package miui.systemui.flashlight.controller;

import androidx.annotation.CallSuper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public class LifecycleController implements LifecycleEventObserver {
    private final Lifecycle lifecycle;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LifecycleController(Lifecycle lifecycle) {
        n.g(lifecycle, "lifecycle");
        this.lifecycle = lifecycle;
        addObserver();
    }

    private final void addObserver() {
        this.lifecycle.addObserver(this);
    }

    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public void onCreate() {
    }

    @CallSuper
    public void onDestroy() {
        this.lifecycle.removeObserver(this);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        n.g(source, "source");
        n.g(event, "event");
        switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
            case 1:
                onCreate();
                break;
            case 2:
                onStart();
                break;
            case 3:
                onResume();
                break;
            case 4:
                onPause();
                break;
            case 5:
                onStop();
                break;
            case 6:
                onDestroy();
                break;
        }
    }

    public void onStop() {
    }
}
