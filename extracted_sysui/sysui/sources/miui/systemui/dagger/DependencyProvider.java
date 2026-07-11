package miui.systemui.dagger;

import android.app.INotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ServiceManager;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.WindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.LockPatternUtils;
import miui.systemui.Prefs;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.Plugin;

/* JADX INFO: loaded from: classes.dex */
public class DependencyProvider {
    public static final String TIME_TICK_HANDLER_NAME = "time_tick_handler";

    public DisplayMetrics provideDisplayMetrics(Context context, WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public Handler provideHandler() {
        return new Handler();
    }

    public INotificationManager provideINotificationManager() {
        return INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
    }

    public LockPatternUtils provideLockPatternUtils(Context context) {
        return new LockPatternUtils(context);
    }

    public MetricsLogger provideMetricsLogger() {
        return new MetricsLogger();
    }

    @Main
    public SharedPreferences provideSharePreferences(@Plugin Context context) {
        return Prefs.get(context);
    }

    public Handler provideTimeTickHandler() {
        HandlerThread handlerThread = new HandlerThread("TimeTick");
        handlerThread.start();
        return new Handler(handlerThread.getLooper());
    }

    public LayoutInflater providerLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

    public Choreographer providesChoreographer() {
        return Choreographer.getInstance();
    }
}
