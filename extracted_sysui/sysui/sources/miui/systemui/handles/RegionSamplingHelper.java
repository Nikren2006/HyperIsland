package miui.systemui.handles;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Handler;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import androidx.annotation.VisibleForTesting;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import miui.systemui.view.CompositionSamplingListenerCompat;
import miui.systemui.view.SurfaceControlCompatKt;
import miui.systemui.view.ViewRootImplCompatKt;

/* JADX INFO: loaded from: classes3.dex */
@TargetApi(29)
class RegionSamplingHelper implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
    private static final float NAVIGATION_LUMINANCE_CHANGE_THRESHOLD = 0.05f;
    private static final float NAVIGATION_LUMINANCE_THRESHOLD = 0.5f;
    private final Executor mBackgroundExecutor;
    private final SamplingCallback mCallback;
    private final SysuiCompositionSamplingListener mCompositionSamplingListener;
    private float mCurrentMedianLuma;
    private boolean mFirstSamplingAfterStart;
    private final Handler mHandler;
    private boolean mIsDestroyed;
    private float mLastMedianLuma;
    private final Rect mRegisteredSamplingBounds;
    private SurfaceControl mRegisteredStopLayer;
    private Runnable mRemoveDrawRunnable;
    private final View mSampledView;
    private boolean mSamplingEnabled;
    private final CompositionSamplingListenerCompat mSamplingListener;
    private boolean mSamplingListenerRegistered;
    private final Rect mSamplingRequestBounds;
    private ViewTreeObserver.OnDrawListener mUpdateOnDraw;
    private boolean mWaitingOnDraw;
    private boolean mWindowHasBlurs;
    private boolean mWindowVisible;
    private SurfaceControl mWrappedStopLayer;

    public interface SamplingCallback {
        Rect getSampledRegion(View view);

        default boolean isSamplingEnabled() {
            return true;
        }

        void onMedianLumaChanged(float f2);

        void onRegionDarknessChanged(boolean z2);
    }

    @VisibleForTesting
    public static class SysuiCompositionSamplingListener {
        public void register(CompositionSamplingListenerCompat compositionSamplingListenerCompat, int i2, SurfaceControl surfaceControl, Rect rect) {
            CompositionSamplingListenerCompat.register(compositionSamplingListenerCompat, i2, surfaceControl, rect);
        }

        public void unregister(CompositionSamplingListenerCompat compositionSamplingListenerCompat) {
            CompositionSamplingListenerCompat.unregister(compositionSamplingListenerCompat);
        }
    }

    public RegionSamplingHelper(View view, SamplingCallback samplingCallback, Executor executor) {
        this(view, samplingCallback, view.getContext().getMainExecutor(), executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$unregisterSamplingListener$1(SurfaceControl surfaceControl) {
        this.mCompositionSamplingListener.unregister(this.mSamplingListener);
        if (surfaceControl == null || !surfaceControl.isValid()) {
            return;
        }
        surfaceControl.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSamplingListener$0(SurfaceControl surfaceControl, Rect rect) {
        if (surfaceControl == null || surfaceControl.isValid()) {
            this.mCompositionSamplingListener.register(this.mSamplingListener, 0, surfaceControl, rect);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDraw() {
        if (this.mWaitingOnDraw) {
            this.mWaitingOnDraw = false;
            updateSamplingListener();
        }
    }

    private void unregisterSamplingListener() {
        if (this.mSamplingListenerRegistered) {
            this.mSamplingListenerRegistered = false;
            final SurfaceControl surfaceControl = this.mWrappedStopLayer;
            this.mRegisteredStopLayer = null;
            this.mWrappedStopLayer = null;
            this.mRegisteredSamplingBounds.setEmpty();
            this.mBackgroundExecutor.execute(new Runnable() { // from class: miui.systemui.handles.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5764a.lambda$unregisterSamplingListener$1(surfaceControl);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMedianLuma(float f2) {
        this.mCurrentMedianLuma = f2;
        this.mCallback.onMedianLumaChanged(f2);
        if (Math.abs(this.mCurrentMedianLuma - this.mLastMedianLuma) > 0.05f) {
            this.mCallback.onRegionDarknessChanged(f2 < 0.5f);
            this.mLastMedianLuma = f2;
        }
    }

    private void updateSamplingListener() {
        if (!this.mSamplingEnabled || this.mSamplingRequestBounds.isEmpty() || !this.mWindowVisible || this.mWindowHasBlurs || (!this.mSampledView.isAttachedToWindow() && !this.mFirstSamplingAfterStart)) {
            unregisterSamplingListener();
            return;
        }
        ViewRootImpl viewRootImpl = this.mSampledView.getViewRootImpl();
        SurfaceControl surfaceControl = null;
        SurfaceControl surfaceControlCompat = viewRootImpl != null ? ViewRootImplCompatKt.getSurfaceControlCompat(viewRootImpl) : null;
        if (surfaceControlCompat != null && surfaceControlCompat.isValid()) {
            surfaceControl = surfaceControlCompat;
        } else if (!this.mWaitingOnDraw) {
            this.mWaitingOnDraw = true;
            if (this.mHandler.hasCallbacks(this.mRemoveDrawRunnable)) {
                this.mHandler.removeCallbacks(this.mRemoveDrawRunnable);
            } else {
                this.mSampledView.getViewTreeObserver().addOnDrawListener(this.mUpdateOnDraw);
            }
        }
        if (!this.mSamplingRequestBounds.equals(this.mRegisteredSamplingBounds) || this.mRegisteredStopLayer != surfaceControl) {
            unregisterSamplingListener();
            this.mSamplingListenerRegistered = true;
            final SurfaceControl surfaceControlWrap = wrap(surfaceControl);
            final Rect rect = new Rect(this.mSamplingRequestBounds);
            this.mBackgroundExecutor.execute(new Runnable() { // from class: miui.systemui.handles.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5766a.lambda$updateSamplingListener$0(surfaceControlWrap, rect);
                }
            });
            this.mRegisteredSamplingBounds.set(this.mSamplingRequestBounds);
            this.mRegisteredStopLayer = surfaceControl;
            this.mWrappedStopLayer = surfaceControlWrap;
        }
        this.mFirstSamplingAfterStart = false;
    }

    public void dump(PrintWriter printWriter) {
        dump("", printWriter);
    }

    @VisibleForTesting
    public SamplingCallback getCallback() {
        return this.mCallback;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        updateSamplingRect();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        updateSamplingListener();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        stopAndDestroy();
    }

    public void setWindowHasBlurs(boolean z2) {
        this.mWindowHasBlurs = z2;
        updateSamplingListener();
    }

    public void setWindowVisible(boolean z2) {
        this.mWindowVisible = z2;
        updateSamplingListener();
    }

    public void start(Rect rect) {
        if (this.mCallback.isSamplingEnabled()) {
            if (rect != null) {
                this.mSamplingRequestBounds.set(rect);
            }
            this.mSamplingEnabled = true;
            this.mLastMedianLuma = -1.0f;
            this.mFirstSamplingAfterStart = true;
            updateSamplingListener();
        }
    }

    public void stop() {
        this.mSamplingEnabled = false;
        updateSamplingListener();
    }

    public void stopAndDestroy() {
        stop();
        Executor executor = this.mBackgroundExecutor;
        final CompositionSamplingListenerCompat compositionSamplingListenerCompat = this.mSamplingListener;
        Objects.requireNonNull(compositionSamplingListenerCompat);
        executor.execute(new Runnable() { // from class: miui.systemui.handles.c
            @Override // java.lang.Runnable
            public final void run() {
                compositionSamplingListenerCompat.destroy();
            }
        });
        this.mIsDestroyed = true;
    }

    public void updateSamplingRect() {
        Rect sampledRegion = this.mCallback.getSampledRegion(this.mSampledView);
        if (this.mSamplingRequestBounds.equals(sampledRegion)) {
            return;
        }
        this.mSamplingRequestBounds.set(sampledRegion);
        updateSamplingListener();
    }

    @VisibleForTesting
    public SurfaceControl wrap(SurfaceControl surfaceControl) {
        if (surfaceControl == null) {
            return null;
        }
        return SurfaceControlCompatKt.createSurfaceControl(surfaceControl, "regionSampling");
    }

    public RegionSamplingHelper(View view, SamplingCallback samplingCallback, Executor executor, Executor executor2) {
        this(view, samplingCallback, executor, executor2, new SysuiCompositionSamplingListener());
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.println(str + "RegionSamplingHelper:");
        printWriter.println(str + "\tsampleView isAttached: " + this.mSampledView.isAttachedToWindow());
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\tsampleView isScValid: ");
        sb.append(this.mSampledView.isAttachedToWindow() ? Boolean.valueOf(ViewRootImplCompatKt.getSurfaceControlCompat(this.mSampledView.getViewRootImpl()).isValid()) : "notAttached");
        printWriter.println(sb.toString());
        printWriter.println(str + "\tmSamplingEnabled: " + this.mSamplingEnabled);
        printWriter.println(str + "\tmSamplingListenerRegistered: " + this.mSamplingListenerRegistered);
        printWriter.println(str + "\tmSamplingRequestBounds: " + this.mSamplingRequestBounds);
        printWriter.println(str + "\tmRegisteredSamplingBounds: " + this.mRegisteredSamplingBounds);
        printWriter.println(str + "\tmLastMedianLuma: " + this.mLastMedianLuma);
        printWriter.println(str + "\tmCurrentMedianLuma: " + this.mCurrentMedianLuma);
        printWriter.println(str + "\tmWindowVisible: " + this.mWindowVisible);
        printWriter.println(str + "\tmWindowHasBlurs: " + this.mWindowHasBlurs);
        printWriter.println(str + "\tmWaitingOnDraw: " + this.mWaitingOnDraw);
        printWriter.println(str + "\tmRegisteredStopLayer: " + this.mRegisteredStopLayer);
        printWriter.println(str + "\tmWrappedStopLayer: " + this.mWrappedStopLayer);
        printWriter.println(str + "\tmIsDestroyed: " + this.mIsDestroyed);
    }

    @VisibleForTesting
    public RegionSamplingHelper(View view, SamplingCallback samplingCallback, Executor executor, Executor executor2, SysuiCompositionSamplingListener sysuiCompositionSamplingListener) {
        this.mHandler = new Handler();
        this.mSamplingRequestBounds = new Rect();
        this.mRegisteredSamplingBounds = new Rect();
        this.mSamplingEnabled = false;
        this.mSamplingListenerRegistered = false;
        this.mRegisteredStopLayer = null;
        this.mWrappedStopLayer = null;
        this.mUpdateOnDraw = new ViewTreeObserver.OnDrawListener() { // from class: miui.systemui.handles.RegionSamplingHelper.1
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public void onDraw() {
                RegionSamplingHelper.this.mHandler.post(RegionSamplingHelper.this.mRemoveDrawRunnable);
                RegionSamplingHelper.this.onDraw();
            }
        };
        this.mRemoveDrawRunnable = new Runnable() { // from class: miui.systemui.handles.RegionSamplingHelper.2
            @Override // java.lang.Runnable
            public void run() {
                RegionSamplingHelper.this.mSampledView.getViewTreeObserver().removeOnDrawListener(RegionSamplingHelper.this.mUpdateOnDraw);
            }
        };
        this.mBackgroundExecutor = executor2;
        this.mCompositionSamplingListener = sysuiCompositionSamplingListener;
        this.mSamplingListener = new CompositionSamplingListenerCompat(executor, new Consumer<Float>() { // from class: miui.systemui.handles.RegionSamplingHelper.3
            @Override // java.util.function.Consumer
            public void accept(Float f2) {
                if (RegionSamplingHelper.this.mSamplingEnabled) {
                    RegionSamplingHelper.this.updateMedianLuma(f2.floatValue());
                }
            }
        });
        this.mSampledView = view;
        view.addOnAttachStateChangeListener(this);
        view.addOnLayoutChangeListener(this);
        this.mCallback = samplingCallback;
    }
}
