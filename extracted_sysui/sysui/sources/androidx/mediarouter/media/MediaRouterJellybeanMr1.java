package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouterJellybean;
import com.miui.circulate.device.api.Constant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(17)
final class MediaRouterJellybeanMr1 {
    private static final String TAG = "MediaRouterJellybeanMr1";

    public static final class ActiveScanWorkaround implements Runnable {
        private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
        private boolean mActivelyScanningWifiDisplays;
        private final DisplayManager mDisplayManager;
        private final Handler mHandler;
        private Method mScanWifiDisplaysMethod;

        public ActiveScanWorkaround(@NonNull Context context, @NonNull Handler handler) {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Runnable
        @SuppressLint({"BanUncheckedReflection"})
        public void run() {
            if (this.mActivelyScanningWifiDisplays) {
                try {
                    this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager, null);
                } catch (IllegalAccessException e2) {
                    Log.w(MediaRouterJellybeanMr1.TAG, "Cannot scan for wifi displays.", e2);
                } catch (InvocationTargetException e3) {
                    Log.w(MediaRouterJellybeanMr1.TAG, "Cannot scan for wifi displays.", e3);
                }
                this.mHandler.postDelayed(this, Constant.MAX_DISCOVERY_WAIT_DURATION);
            }
        }

        public void setActiveScanRouteTypes(int i2) {
            if ((i2 & 2) == 0) {
                if (this.mActivelyScanningWifiDisplays) {
                    this.mActivelyScanningWifiDisplays = false;
                    this.mHandler.removeCallbacks(this);
                    return;
                }
                return;
            }
            if (this.mActivelyScanningWifiDisplays) {
                return;
            }
            if (this.mScanWifiDisplaysMethod == null) {
                Log.w(MediaRouterJellybeanMr1.TAG, "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
            } else {
                this.mActivelyScanningWifiDisplays = true;
                this.mHandler.post(this);
            }
        }
    }

    public interface Callback extends MediaRouterJellybean.Callback {
        void onRoutePresentationDisplayChanged(@NonNull Object obj);
    }

    public static class CallbackProxy<T extends Callback> extends MediaRouterJellybean.CallbackProxy<T> {
        public CallbackProxy(T t2) {
            super(t2);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRoutePresentationDisplayChanged(android.media.MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            ((Callback) this.mCallback).onRoutePresentationDisplayChanged(routeInfo);
        }
    }

    public static final class IsConnectingWorkaround {
        private Method mGetStatusCodeMethod;
        private int mStatusConnecting;

        public IsConnectingWorkaround() {
            throw new UnsupportedOperationException();
        }

        @SuppressLint({"BanUncheckedReflection"})
        public boolean isConnecting(@NonNull Object obj) {
            MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) obj;
            Method method = this.mGetStatusCodeMethod;
            if (method == null) {
                return false;
            }
            try {
                return ((Integer) method.invoke(routeInfo, null)).intValue() == this.mStatusConnecting;
            } catch (IllegalAccessException | InvocationTargetException unused) {
                return false;
            }
        }
    }

    public static final class RouteInfo {
        private RouteInfo() {
        }

        @Nullable
        public static Display getPresentationDisplay(@NonNull Object obj) {
            try {
                return ((MediaRouter.RouteInfo) obj).getPresentationDisplay();
            } catch (NoSuchMethodError e2) {
                Log.w(MediaRouterJellybeanMr1.TAG, "Cannot get presentation display for the route.", e2);
                return null;
            }
        }

        public static boolean isEnabled(@NonNull Object obj) {
            return ((MediaRouter.RouteInfo) obj).isEnabled();
        }
    }

    private MediaRouterJellybeanMr1() {
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }
}
