package miui.systemui.notification;

import android.content.Context;
import android.icu.text.MeasureFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.IllegalFormatException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.notification.NotificationChronometerManager;
import miui.systemui.notification.NotificationTimeKeeper;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.StatusProgressLayout;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationTimeKeeper {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "NotificationTimeKeeper";
    public static final int TIMER_TYPE_COUNT_DOWN_PAUSE = -2;
    public static final int TIMER_TYPE_COUNT_DOWN_RUN = -1;
    public static final int TIMER_TYPE_COUNT_PAUSE = 2;
    public static final int TIMER_TYPE_COUNT_RUN = 1;
    public static final int TIMER_TYPE_NONE = 0;
    private final int HOUR_IN_SEC;
    private final int MIN_IN_SEC;
    private long base;
    private final Map<TextView, ListenerWrapper> chronometerListenerWrapperMap;
    private final Map<TextView, StatusProgressLayout> chronometerProgressMap;
    private final Map<TextView, Boolean> chronometerVisibleMap;
    private boolean committed;
    private final Context context;
    private boolean countDown;
    private long currentSeconds;
    private String displayedText;
    private boolean dynamicIslandIn;
    private boolean focusNotificationIn;
    private String format;
    private StringBuilder formatBuilder;
    private Formatter formatter;
    private final Object[] formatterArgs;
    private Locale formatterLocale;
    private final Handler handler;
    private boolean isFullAod;
    private boolean mLogged;
    private long mNow;
    private final Runnable mTickRunnable;
    private boolean progressBarIn;
    private boolean progressIncluded;
    private final StringBuilder recycle;
    private boolean running;
    private boolean started;
    private long timerSystemCurrent;
    private long timerTotal;
    private int timerType;
    private long timerWhen;
    private int visibleChronometerSize;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final class ListenerWrapper implements View.OnAttachStateChangeListener {
        private final ViewTreeObserver.OnPreDrawListener onPreDrawListener;
        final /* synthetic */ NotificationTimeKeeper this$0;
        private final WeakReference<TextView> viewWeakReference;

        public ListenerWrapper(final NotificationTimeKeeper notificationTimeKeeper, WeakReference<TextView> viewWeakReference) {
            n.g(viewWeakReference, "viewWeakReference");
            this.this$0 = notificationTimeKeeper;
            this.viewWeakReference = viewWeakReference;
            this.onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: miui.systemui.notification.f
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    return NotificationTimeKeeper.ListenerWrapper.onPreDrawListener$lambda$1(this.f5795a, notificationTimeKeeper);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean onPreDrawListener$lambda$1(ListenerWrapper this$0, NotificationTimeKeeper this$1) {
            n.g(this$0, "this$0");
            n.g(this$1, "this$1");
            TextView textView = this$0.viewWeakReference.get();
            if (textView != null) {
                Boolean bool = (Boolean) this$1.chronometerVisibleMap.get(textView);
                boolean zIsShown = textView.isShown();
                if (!n.c(bool, Boolean.valueOf(zIsShown))) {
                    int i2 = this$1.visibleChronometerSize;
                    this$1.setVisibleChronometerSize(zIsShown ? i2 + 1 : i2 - 1);
                    this$1.chronometerVisibleMap.put(textView, Boolean.valueOf(zIsShown));
                    if (zIsShown) {
                        textView.setText(this$1.displayedText);
                    }
                }
            }
            return true;
        }

        public final ViewTreeObserver.OnPreDrawListener getOnPreDrawListener() {
            return this.onPreDrawListener;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v2) {
            n.g(v2, "v");
            v2.getViewTreeObserver().addOnPreDrawListener(this.onPreDrawListener);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v2) {
            n.g(v2, "v");
            v2.getViewTreeObserver().removeOnPreDrawListener(this.onPreDrawListener);
        }

        public final void release() {
            TextView textView = this.viewWeakReference.get();
            if (textView != null) {
                textView.getViewTreeObserver().removeOnPreDrawListener(this.onPreDrawListener);
            }
        }
    }

    public NotificationTimeKeeper(Context context, Handler handler) {
        n.g(context, "context");
        n.g(handler, "handler");
        this.context = context;
        this.handler = handler;
        init();
        this.timerWhen = System.currentTimeMillis();
        this.timerSystemCurrent = System.currentTimeMillis();
        this.chronometerVisibleMap = new LinkedHashMap();
        this.chronometerProgressMap = new LinkedHashMap();
        this.chronometerListenerWrapperMap = new LinkedHashMap();
        this.formatterArgs = new Object[1];
        this.recycle = new StringBuilder(8);
        this.mTickRunnable = new Runnable() { // from class: miui.systemui.notification.NotificationTimeKeeper$mTickRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.running) {
                    this.this$0.updateText(SystemClock.elapsedRealtime());
                    this.this$0.handler.postDelayed(this, 1000L);
                }
            }
        };
        this.MIN_IN_SEC = 60;
        this.HOUR_IN_SEC = 60 * 60;
    }

    public static /* synthetic */ void addChronometerFromView$default(NotificationTimeKeeper notificationTimeKeeper, View view, int i2, boolean z2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            z2 = false;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        notificationTimeKeeper.addChronometerFromView(view, i2, z2, i3);
    }

    private final void clearListenerWrapper() {
        for (Map.Entry<TextView, ListenerWrapper> entry : this.chronometerListenerWrapperMap.entrySet()) {
            TextView key = entry.getKey();
            ListenerWrapper value = entry.getValue();
            value.release();
            key.removeOnAttachStateChangeListener(value);
        }
        this.chronometerListenerWrapperMap.clear();
    }

    private final void debugLog(String str, String str2) {
        Log.d(str, str2);
    }

    private final String formatDuration(long j2) {
        int i2;
        int i3;
        int i4 = (int) (j2 / 1000);
        if (i4 < 0) {
            i4 = -i4;
        }
        int i5 = this.HOUR_IN_SEC;
        if (i4 >= i5) {
            i2 = i4 / i5;
            i4 -= i5 * i2;
        } else {
            i2 = 0;
        }
        int i6 = this.MIN_IN_SEC;
        if (i4 >= i6) {
            i3 = i4 / i6;
            i4 -= i6 * i3;
        } else {
            i3 = 0;
        }
        ArrayList arrayList = new ArrayList();
        if (i2 > 0) {
            arrayList.add(new Measure(Integer.valueOf(i2), MeasureUnit.HOUR));
        }
        if (i3 > 0) {
            arrayList.add(new Measure(Integer.valueOf(i3), MeasureUnit.MINUTE));
        }
        arrayList.add(new Measure(Integer.valueOf(i4), MeasureUnit.SECOND));
        MeasureFormat measureFormat = MeasureFormat.getInstance(Locale.getDefault(), MeasureFormat.FormatWidth.WIDE);
        Measure[] measureArr = (Measure[]) arrayList.toArray(new Measure[0]);
        String measures = measureFormat.formatMeasures((Measure[]) Arrays.copyOf(measureArr, measureArr.length));
        n.f(measures, "formatMeasures(...)");
        return measures;
    }

    private final float getProgresses() {
        long j2 = this.timerTotal;
        if (j2 > 0) {
            return (this.currentSeconds / j2) * 100;
        }
        return -1.0f;
    }

    private final void init() {
        debugLog(TAG, "init time keeper for managing each chronometer");
        setupFormat();
        this.base = SystemClock.elapsedRealtime();
    }

    private final void resetFlags() {
        this.focusNotificationIn = false;
        this.dynamicIslandIn = false;
        this.progressBarIn = false;
    }

    private final void setCommitted(boolean z2) {
        this.committed = z2;
        updateRunning();
    }

    private final void setCountDown(boolean z2) {
        this.countDown = z2;
        updateText(SystemClock.elapsedRealtime());
    }

    private final void setDisplayedText(String str) {
        this.displayedText = str;
        updateChronometersText(str);
    }

    private final void setStarted(boolean z2) {
        this.started = z2;
        updateRunning();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVisibleChronometerSize(int i2) {
        if (i2 == 0) {
            setCommitted(false);
        } else if (this.visibleChronometerSize == 0 && i2 > 0) {
            setCommitted(true);
        }
        this.visibleChronometerSize = i2;
        Log.d(TAG, "update visibleChronometerSize = " + i2);
    }

    private final void setupFormat() {
        setFormat(null);
    }

    private final boolean shouldBeStarted() {
        int i2 = this.timerType;
        return i2 == 1 || i2 == -1;
    }

    private final void updateChronometersText(String str) {
        StatusProgressLayout statusProgressLayout;
        if (str != null) {
            float progresses = getProgresses();
            String duration = formatDuration(this.currentSeconds);
            Map<TextView, Boolean> map = this.chronometerVisibleMap;
            if (map != null) {
                for (Map.Entry<TextView, Boolean> entry : map.entrySet()) {
                    TextView key = entry.getKey();
                    boolean zBooleanValue = entry.getValue().booleanValue();
                    key.setContentDescription(duration);
                    if (zBooleanValue) {
                        key.setText(str);
                    }
                    if (progresses >= 0.0f && !CommonUtils.NOT_SUPPORT_LOTTIE && (statusProgressLayout = this.chronometerProgressMap.get(key)) != null) {
                        statusProgressLayout.setProgress(progresses);
                    }
                }
            }
        }
    }

    private final void updateFullAodText(long j2) {
        if (this.isFullAod) {
            long j3 = 60;
            if (((int) (((j2 / ((long) 1000)) / j3) / j3)) <= 0) {
                setDisplayedText("--:--");
            } else {
                setDisplayedText("--:--:--");
            }
        }
    }

    private final void updateRunning() {
        boolean z2 = this.started;
        boolean z3 = z2 && this.committed;
        debugLog(TAG, "updateRunning --- started =" + z2 + " committed =" + this.committed + " runnning=" + this.running);
        if (z3 != this.running) {
            if (z3) {
                updateText(SystemClock.elapsedRealtime());
                this.handler.postDelayed(this.mTickRunnable, 1000L);
            } else {
                this.handler.removeCallbacks(this.mTickRunnable);
            }
            this.running = z3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateText(long j2) {
        this.mNow = j2;
        long j3 = this.countDown ? this.base - j2 : j2 - this.base;
        this.currentSeconds = j3;
        if (this.isFullAod) {
            updateFullAodText(j3);
            return;
        }
        long j4 = j3 / ((long) 1000);
        if (j4 < 0) {
            j4 = -j4;
        }
        String elapsedTime = DateUtils.formatElapsedTime(this.recycle, j4);
        if (this.format != null) {
            Locale locale = Locale.getDefault();
            if (this.formatter == null || !n.c(locale, this.formatterLocale)) {
                this.formatterLocale = locale;
                this.formatter = new Formatter(this.formatBuilder, locale);
            }
            StringBuilder sb = this.formatBuilder;
            n.d(sb);
            sb.setLength(0);
            this.formatterArgs[0] = elapsedTime;
            try {
                Formatter formatter = this.formatter;
                n.d(formatter);
                String str = this.format;
                Object[] objArr = this.formatterArgs;
                formatter.format(str, Arrays.copyOf(objArr, objArr.length));
                elapsedTime = String.valueOf(this.formatBuilder);
            } catch (IllegalFormatException unused) {
                if (!this.mLogged) {
                    Log.w(TAG, "Illegal format string: " + this.format);
                    this.mLogged = true;
                }
            }
        }
        setDisplayedText(elapsedTime);
    }

    public final void addChronometerFromView(View view, int i2, boolean z2, int i3) {
        TextView textView;
        StatusProgressLayout statusProgressLayout;
        if (view == null || (textView = (TextView) view.findViewById(i2)) == null) {
            return;
        }
        ListenerWrapper listenerWrapper = this.chronometerListenerWrapperMap.get(textView);
        if (listenerWrapper != null) {
            listenerWrapper.release();
            textView.removeOnAttachStateChangeListener(listenerWrapper);
        }
        ListenerWrapper listenerWrapper2 = new ListenerWrapper(this, new WeakReference(textView));
        textView.addOnAttachStateChangeListener(listenerWrapper2);
        if (textView.isAttachedToWindow()) {
            textView.getViewTreeObserver().addOnPreDrawListener(listenerWrapper2.getOnPreDrawListener());
        }
        boolean zIsShown = textView.isShown();
        Boolean bool = this.chronometerVisibleMap.get(textView);
        boolean zBooleanValue = bool != null ? bool.booleanValue() : !zIsShown;
        this.chronometerVisibleMap.put(textView, Boolean.valueOf(zIsShown));
        this.chronometerListenerWrapperMap.put(textView, listenerWrapper2);
        if (z2 && (statusProgressLayout = (StatusProgressLayout) view.findViewById(i3)) != null) {
            n.d(statusProgressLayout);
            statusProgressLayout.setProgress(100.0f);
            this.chronometerProgressMap.put(textView, statusProgressLayout);
        }
        if (zIsShown && !zBooleanValue) {
            setVisibleChronometerSize(this.visibleChronometerSize + 1);
        }
        debugLog(TAG, "add chronometer vis=" + zIsShown + " up chronometer Size to " + this.chronometerVisibleMap.size() + " " + textView);
    }

    public final void fullAodStatusChanged(boolean z2) {
        if (this.isFullAod != z2) {
            this.isFullAod = z2;
            updateText(this.mNow);
        }
    }

    public final boolean getAvailable() {
        return this.timerType != 0;
    }

    public final boolean getDynamicIslandIn() {
        return this.dynamicIslandIn;
    }

    public final boolean getFocusNotificationIn() {
        return this.focusNotificationIn;
    }

    public final String getFormat() {
        return this.format;
    }

    public final boolean getProgressBarIn() {
        return this.progressBarIn;
    }

    public final void hitMainChronometer() {
        setStarted(shouldBeStarted());
    }

    public final void reset() {
        setStarted(false);
        this.chronometerVisibleMap.clear();
        this.chronometerProgressMap.clear();
        clearListenerWrapper();
        resetFlags();
        setVisibleChronometerSize(0);
        this.currentSeconds = 0L;
        debugLog(TAG, "reset finished");
    }

    public final void setDynamicIslandIn(boolean z2) {
        this.dynamicIslandIn = z2;
    }

    public final void setFocusNotificationIn(boolean z2) {
        this.focusNotificationIn = z2;
    }

    public final void setFormat(String str) {
        this.format = str;
        if (str == null || this.formatBuilder != null) {
            return;
        }
        this.formatBuilder = new StringBuilder(str.length() * 2);
    }

    public final void setProgressBarIn(boolean z2) {
        this.progressBarIn = z2;
    }

    public final void updateInfo(NotificationChronometerManager.TimerInfo timerInfo) {
        Long timerSystemCurrent;
        n.g(timerInfo, "timerInfo");
        this.timerType = timerInfo.getTimerType();
        long jAbs = 0;
        if (timerInfo.getTimerTotal() > 0) {
            this.timerTotal = timerInfo.getTimerTotal();
        }
        Long timerWhen = timerInfo.getTimerWhen();
        if (timerWhen != null) {
            this.timerWhen = timerWhen.longValue();
        }
        this.timerSystemCurrent = (shouldBeStarted() || (timerSystemCurrent = timerInfo.getTimerSystemCurrent()) == null) ? System.currentTimeMillis() : timerSystemCurrent.longValue();
        if (this.timerTotal <= 0) {
            Long timerWhen2 = timerInfo.getTimerWhen();
            if (timerWhen2 != null) {
                long jLongValue = timerWhen2.longValue();
                Long timerSystemCurrent2 = timerInfo.getTimerSystemCurrent();
                Long lValueOf = timerSystemCurrent2 != null ? Long.valueOf(timerSystemCurrent2.longValue() - jLongValue) : null;
                if (lValueOf != null) {
                    jAbs = Math.abs(lValueOf.longValue());
                }
            }
            this.timerTotal = jAbs;
        }
        debugLog(TAG, "updateInfo {timerType=" + this.timerType + ", timerWhen=" + this.timerWhen + ", timerTotal=" + this.timerTotal + ", timerSystemCurrent=" + this.timerSystemCurrent + "}");
        this.base = (SystemClock.elapsedRealtime() + this.timerWhen) - this.timerSystemCurrent;
        setCountDown(this.timerType < 0);
        hitMainChronometer();
    }
}
