package miui.systemui.nfcphonecase;

import H0.o;
import H0.s;
import I0.AbstractC0181i;
import I0.G;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.RoundedCorner;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import c1.C0232d;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.nfcphonecase.MiuiNfcPhoneCaseService$nfcReceiver$2;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService extends Service {
    public static final String ACTION_DISMISS_NFC_PHONE_CASE = "miui.systemui.action.ACTION_DISMISS_NFC_PHONE_CASE";
    public static final String ACTION_SHOW_NFC_PHONE_CASE = "miui.systemui.action.ACTION_SHOW_NFC_PHONE_CASE";
    public static final Companion Companion = new Companion(null);
    public static final long SHOW_DELAY = 200;
    public static final String TAG = "MiuiNfcPhoneCaseService";
    private static final String WINDOW_NAME = "miui_nfc_phone_case";
    private final Binder binder = new Binder();
    private final H0.d nfcReceiver$delegate = H0.e.b(new MiuiNfcPhoneCaseService$nfcReceiver$2(this));
    private final H0.d phoneCaseView$delegate = H0.e.b(new MiuiNfcPhoneCaseService$phoneCaseView$2(this));
    private final H0.d layoutParams$delegate = H0.e.b(MiuiNfcPhoneCaseService$layoutParams$2.INSTANCE);
    private final TransitionListener mNfcTransitionListener = new MiuiNfcPhoneCaseService$mNfcTransitionListener$1(this);
    private final Map<String, Integer> mPositionMap = G.h(o.a(NFCStateKt.getSCREEN_RADIUS().getName(), 0), o.a(NFCStateKt.getBLUR_RADIUS().getName(), 1), o.a(NFCStateKt.getBLUR_SCALE().getName(), 2), o.a(NFCStateKt.getDIM_RATIO().getName(), 3), o.a(NFCStateKt.getBOX_GLOW_STRENGTH().getName(), 4), o.a(NFCStateKt.getBOX_GLOW_INTENSITY().getName(), 5), o.a(NFCStateKt.getBOX_GLOW_COLOR_R().getName(), 6), o.a(NFCStateKt.getBOX_GLOW_COLOR_G().getName(), 7), o.a(NFCStateKt.getBOX_GLOW_COLOR_B().getName(), 8), o.a(NFCStateKt.getBOX_GLOW_COLOR_A().getName(), 9), o.a(NFCStateKt.getRING_SIZE().getName(), 10), o.a(NFCStateKt.getRING_THICKNESS().getName(), 11), o.a(NFCStateKt.getRING_GLOW_STRENGTH().getName(), 12), o.a(NFCStateKt.getRING_GLOW_INTENSITY().getName(), 13), o.a(NFCStateKt.getRING_GLOW_COLOR_R().getName(), 14), o.a(NFCStateKt.getRING_GLOW_COLOR_G().getName(), 15), o.a(NFCStateKt.getRING_GLOW_COLOR_B().getName(), 16), o.a(NFCStateKt.getRING_GLOW_COLOR_A().getName(), 17));
    private final H0.d mCurState$delegate = H0.e.b(new MiuiNfcPhoneCaseService$mCurState$2(this));
    private final H0.d showAnimRunnable$delegate = H0.e.b(new MiuiNfcPhoneCaseService$showAnimRunnable$2(this));
    private final H0.d showAnim2Runnable$delegate = H0.e.b(new MiuiNfcPhoneCaseService$showAnim2Runnable$2(this));
    private final H0.d showAnim3Runnable$delegate = H0.e.b(new MiuiNfcPhoneCaseService$showAnim3Runnable$2(this));
    private final H0.d showAnim4Runnable$delegate = H0.e.b(new MiuiNfcPhoneCaseService$showAnim4Runnable$2(this));
    private final H0.d hideRunnable$delegate = H0.e.b(new MiuiNfcPhoneCaseService$hideRunnable$2(this));

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void addNfcWindowView() {
        try {
            Object systemService = getSystemService("window");
            n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            WindowManager windowManager = (WindowManager) systemService;
            if (getPhoneCaseView().isAttachedToWindow()) {
                windowManager.removeView(getPhoneCaseView());
            }
            windowManager.addView(getPhoneCaseView(), getLayoutParams());
        } catch (Exception e2) {
            Log.e(TAG, "add failed.", e2);
        }
    }

    private final void clearAnim() {
        Folme.clean(getPhoneCaseView());
        getPhoneCaseView().removeCallbacks(getShowAnimRunnable());
        getPhoneCaseView().removeCallbacks(getShowAnim2Runnable());
        getPhoneCaseView().removeCallbacks(getShowAnim3Runnable());
        getPhoneCaseView().removeCallbacks(getShowAnim4Runnable());
        getPhoneCaseView().removeCallbacks(getHideRunnable());
    }

    private final Runnable getHideRunnable() {
        return (Runnable) this.hideRunnable$delegate.getValue();
    }

    private final WindowManager.LayoutParams getLayoutParams() {
        return (WindowManager.LayoutParams) this.layoutParams$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float[] getMCurState() {
        return (float[]) this.mCurState$delegate.getValue();
    }

    private final MiuiNfcPhoneCaseService$nfcReceiver$2.AnonymousClass1 getNfcReceiver() {
        return (MiuiNfcPhoneCaseService$nfcReceiver$2.AnonymousClass1) this.nfcReceiver$delegate.getValue();
    }

    private final FrameLayout getPhoneCaseView() {
        return (FrameLayout) this.phoneCaseView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getRingSize() {
        return getResources().getDimensionPixelSize(R.dimen.miui_nfc_phone_shell_ring_size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getScreenRadius() {
        RoundedCorner roundedCorner;
        WindowInsets rootWindowInsets = getPhoneCaseView().getRootWindowInsets();
        if (rootWindowInsets == null || (roundedCorner = rootWindowInsets.getRoundedCorner(0)) == null) {
            return 0.0f;
        }
        return roundedCorner.getRadius();
    }

    private final Runnable getShowAnim2Runnable() {
        return (Runnable) this.showAnim2Runnable$delegate.getValue();
    }

    private final Runnable getShowAnim3Runnable() {
        return (Runnable) this.showAnim3Runnable$delegate.getValue();
    }

    private final Runnable getShowAnim4Runnable() {
        return (Runnable) this.showAnim4Runnable$delegate.getValue();
    }

    private final Runnable getShowAnimRunnable() {
        return (Runnable) this.showAnimRunnable$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideAnim() {
        IStateStyle iStateStyleUseValue = Folme.useValue(this);
        AnimState hideState = NFCStateKt.getHideState(getRingSize());
        AnimConfig hideAnimConfig = NFCStateKt.getHideAnimConfig();
        hideAnimConfig.addListeners(this.mNfcTransitionListener);
        s sVar = s.f314a;
        iStateStyleUseValue.to(hideState, hideAnimConfig);
    }

    private final void release() {
        removeNfcWindowView();
        clearAnim();
    }

    private final void removeNfcWindowView() {
        Object systemService = getSystemService("window");
        n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        WindowManager windowManager = (WindowManager) systemService;
        if (getPhoneCaseView().isAttachedToWindow()) {
            windowManager.removeView(getPhoneCaseView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAnim() {
        clearAnim();
        getPhoneCaseView().postDelayed(getShowAnimRunnable(), 200L);
        getPhoneCaseView().postDelayed(getShowAnim2Runnable(), 330L);
        getPhoneCaseView().postDelayed(getShowAnim3Runnable(), 320L);
        getPhoneCaseView().postDelayed(getShowAnim4Runnable(), 450L);
        getPhoneCaseView().postDelayed(getHideRunnable(), 1700L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSurface() {
        try {
            Object objInvoke = getPhoneCaseView().getClass().getMethod("getViewRootImpl", null).invoke(getPhoneCaseView(), null);
            if (objInvoke != null) {
                Object objInvoke2 = objInvoke.getClass().getMethod("getSurfaceControl", null).invoke(objInvoke, null);
                n.e(objInvoke2, "null cannot be cast to non-null type android.view.SurfaceControl");
                SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                SurfaceControl.Transaction.class.getMethod("setBoxAndRingParams", SurfaceControl.class, float[].class, float[].class, float[].class).invoke(transaction, (SurfaceControl) objInvoke2, AbstractC0181i.M(getMCurState(), new C0232d(0, 3)), AbstractC0181i.M(getMCurState(), new C0232d(4, 9)), AbstractC0181i.M(getMCurState(), new C0232d(10, 17)));
                transaction.apply();
            }
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "updateSurface failed! " + e2);
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "updateSurface failed! " + e3);
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "updateSurface failed! " + e4);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        addNfcWindowView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SHOW_NFC_PHONE_CASE);
        intentFilter.addAction(ACTION_DISMISS_NFC_PHONE_CASE);
        getBaseContext().registerReceiver(getNfcReceiver(), intentFilter, 2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        hideAnim();
        unregisterReceiver(getNfcReceiver());
        release();
    }
}
