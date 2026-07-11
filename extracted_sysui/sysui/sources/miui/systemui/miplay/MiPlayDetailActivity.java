package miui.systemui.miplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.systemui.MiPlayController;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.dagger.MiPlayModule;
import com.android.systemui.miplay.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import m0.C0465C;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.util.BlurUtils;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.settings.GlobalSettingsImpl;
import miuix.appcompat.app.AppCompatActivity;
import miuix.core.util.MiuiBlurUtils;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes3.dex */
public final class MiPlayDetailActivity extends AppCompatActivity {
    public static final int CONTAINER_RADIUS = 275;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_PARAM_REF = "ref";
    public static final String EXTRA_REF_UNKNOWN = "unknown";
    public static final String TAG = "MiPlayDetailActivity";
    private QSControlMiPlayDetailContent detailContent;
    private DeviceStateManagerCompat.FoldStateCallback foldStateCallback;
    private Object foldStateListener;
    private BlurUtils mBlurUtils;
    private final Object deviceStateManager = DeviceStateManagerCompat.INSTANCE.getDeviceStateManagerInstance();
    private final ArrayList<DeviceStateManagerCompat.FoldStateCallback> foldStateCallbacks = new ArrayList<>();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class NoLazy implements E0.a {
        private final C0465C audioManager;

        public NoLazy(C0465C audioManager) {
            n.g(audioManager, "audioManager");
            this.audioManager = audioManager;
        }

        @Override // E0.a
        public C0465C get() {
            return this.audioManager;
        }
    }

    private final void adaptCutout() {
        if ("babylon".equals(Build.DEVICE)) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
    }

    private final void addFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        if (this.foldStateCallbacks.contains(foldStateCallback)) {
            return;
        }
        this.foldStateCallbacks.add(foldStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(MiPlayDetailActivity this$0, Boolean folded) {
        n.g(this$0, "this$0");
        n.g(folded, "folded");
        if (this$0.foldStateCallbacks.isEmpty()) {
            return;
        }
        Iterator<T> it = this$0.foldStateCallbacks.iterator();
        while (it.hasNext()) {
            ((DeviceStateManagerCompat.FoldStateCallback) it.next()).onFoldStateChanged(folded.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(MiPlayDetailActivity this$0, View view) {
        n.g(this$0, "this$0");
        this$0.finish();
    }

    private final String reflectGetReferrer() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mReferrer");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            n.e(obj, "null cannot be cast to non-null type kotlin.String");
            return (String) obj;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknown";
        }
    }

    private final void removeFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        this.foldStateCallbacks.remove(foldStateCallback);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent2;
        Bundle extras;
        super.onCreate(bundle);
        adaptCutout();
        setContentView(R.layout.miplay_detail_activity_root);
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent3 = null;
        if (CommonUtils.isFlipDevice() || CommonUtils.INSTANCE.getIS_FOLD()) {
            this.foldStateCallback = new DeviceStateManagerCompat.FoldStateCallback() { // from class: miui.systemui.miplay.MiPlayDetailActivity.onCreate.1
                @Override // miui.systemui.util.DeviceStateManagerCompat.FoldStateCallback
                public void onFoldStateChanged(boolean z2) {
                    Log.d(MiPlayDetailActivity.TAG, "onFoldStateChanged: " + z2);
                    MiPlayDetailViewModel.INSTANCE.setLastFoldState(z2);
                }
            };
            DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
            this.foldStateListener = deviceStateManagerCompat.getFoldStateListenerInstance(this, new Consumer() { // from class: miui.systemui.miplay.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MiPlayDetailActivity.onCreate$lambda$1(this.f5773a, (Boolean) obj);
                }
            });
            Object obj = this.deviceStateManager;
            Executor mainExecutor = getMainExecutor();
            n.f(mainExecutor, "getMainExecutor(...)");
            deviceStateManagerCompat.registerCallbackCompat(obj, mainExecutor, this.foldStateListener);
            DeviceStateManagerCompat.FoldStateCallback foldStateCallback = this.foldStateCallback;
            if (foldStateCallback == null) {
                n.w("foldStateCallback");
                foldStateCallback = null;
            }
            addFoldStateCallback(foldStateCallback);
        }
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        if (!miPlayController.isInterconnectionCTAAgree(this)) {
            miPlayController.showCtaPageAC(this);
        }
        Context applicationContext = getApplicationContext();
        n.f(applicationContext, "getApplicationContext(...)");
        miPlayController.setContext(applicationContext);
        if (!miPlayController.isInitialized()) {
            C0465C c0465cProvideMiPlayAudioManager = new MiPlayModule().provideMiPlayAudioManager(getApplicationContext());
            n.f(c0465cProvideMiPlayAudioManager, "provideMiPlayAudioManager(...)");
            miPlayController.set_MIPLAY_AUDIO_MANAGER(new NoLazy(c0465cProvideMiPlayAudioManager));
            C0465C miplay_audio_manager = miPlayController.getMIPLAY_AUDIO_MANAGER();
            if (miplay_audio_manager != null) {
                miplay_audio_manager.l(MiPlayDetailViewModel.INSTANCE, null);
            }
            HandlerThread handlerThread = new HandlerThread("PluginBg", 10);
            handlerThread.start();
            Context applicationContext2 = getApplicationContext();
            n.f(applicationContext2, "getApplicationContext(...)");
            Handler handler = new Handler(getMainLooper());
            Looper looper = handlerThread.getLooper();
            n.f(looper, "getLooper(...)");
            miPlayController.setBroadcastDispatcher(new BroadcastDispatcher(applicationContext2, handler, looper));
            BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
            Context applicationContext3 = getApplicationContext();
            n.f(applicationContext3, "getApplicationContext(...)");
            companion.setEventTracker(new EventTracker(applicationContext3));
            miPlayController.setGlobalSettings(new GlobalSettingsImpl(getApplicationContext().getContentResolver(), TAG));
        }
        MiPlayDetailViewModel.INSTANCE.onCreate();
        if (CommonUtils.isFlipDevice()) {
            View viewCreateMiPlayDetailViewFlip = miPlayController.createMiPlayDetailViewFlip();
            n.e(viewCreateMiPlayDetailViewFlip, "null cannot be cast to non-null type com.android.systemui.QSControlMiPlayDetailContent");
            qSControlMiPlayDetailContent = (QSControlMiPlayDetailContent) viewCreateMiPlayDetailViewFlip;
        } else {
            View viewCreateMiPlayDetailView = miPlayController.createMiPlayDetailView();
            n.e(viewCreateMiPlayDetailView, "null cannot be cast to non-null type com.android.systemui.QSControlMiPlayDetailContent");
            qSControlMiPlayDetailContent = (QSControlMiPlayDetailContent) viewCreateMiPlayDetailView;
        }
        this.detailContent = qSControlMiPlayDetailContent;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(this)) {
            MiuiBlurUtils.setPassWindowBlurEnabled(frameLayout, true);
            MiuiBlurUtils.setBackgroundBlur((View) frameLayout, 275, false);
            QSControlMiPlayDetailContent qSControlMiPlayDetailContent4 = this.detailContent;
            if (qSControlMiPlayDetailContent4 == null) {
                n.w("detailContent");
                qSControlMiPlayDetailContent4 = null;
            }
            qSControlMiPlayDetailContent4.setContentBgBlurForActivity();
        } else {
            QSControlMiPlayDetailContent qSControlMiPlayDetailContent5 = this.detailContent;
            if (qSControlMiPlayDetailContent5 == null) {
                n.w("detailContent");
                qSControlMiPlayDetailContent5 = null;
            }
            qSControlMiPlayDetailContent5.setBackgroundResource(R.drawable.miplay_detail_round_bg);
            BlurUtils blurUtils = new BlurUtils(this);
            this.mBlurUtils = blurUtils;
            blurUtils.setBackgroundBlur(frameLayout, Float.valueOf(1.0f), getWindow(), R.color.miplay_detail_xiaomi_music_blur_bg_color, R.color.miplay_detail_xiaomi_music_low_end_device_blur_bg_color);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.miplay_detail_content_width), -2);
        layoutParams.gravity = 17;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent6 = this.detailContent;
        if (qSControlMiPlayDetailContent6 == null) {
            n.w("detailContent");
            qSControlMiPlayDetailContent6 = null;
        }
        frameLayout.addView(qSControlMiPlayDetailContent6, layoutParams);
        Intent intent = getIntent();
        String string = (intent == null || (extras = intent.getExtras()) == null) ? null : extras.getString(EXTRA_PARAM_REF);
        if (TextUtils.isEmpty(string)) {
            string = reflectGetReferrer();
        }
        String str = string;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent7 = this.detailContent;
        if (qSControlMiPlayDetailContent7 == null) {
            n.w("detailContent");
            qSControlMiPlayDetailContent2 = null;
        } else {
            qSControlMiPlayDetailContent2 = qSControlMiPlayDetailContent7;
        }
        QSControlMiPlayDetailContent.setDetailShowing$default(qSControlMiPlayDetailContent2, true, str, false, 4, null);
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent8 = this.detailContent;
        if (qSControlMiPlayDetailContent8 == null) {
            n.w("detailContent");
        } else {
            qSControlMiPlayDetailContent3 = qSControlMiPlayDetailContent8;
        }
        qSControlMiPlayDetailContent3.setClickable(true);
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.miplay.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiPlayDetailActivity.onCreate$lambda$2(this.f5774a, view);
            }
        });
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent;
        Bundle extras;
        super.onDestroy();
        Intent intent = getIntent();
        DeviceStateManagerCompat.FoldStateCallback foldStateCallback = null;
        String string = (intent == null || (extras = intent.getExtras()) == null) ? null : extras.getString(EXTRA_PARAM_REF);
        if (TextUtils.isEmpty(string)) {
            string = reflectGetReferrer();
        }
        String str = string;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent2 = this.detailContent;
        if (qSControlMiPlayDetailContent2 == null) {
            n.w("detailContent");
            qSControlMiPlayDetailContent = null;
        } else {
            qSControlMiPlayDetailContent = qSControlMiPlayDetailContent2;
        }
        QSControlMiPlayDetailContent.setDetailShowing$default(qSControlMiPlayDetailContent, false, str, false, 4, null);
        BlurUtils blurUtils = this.mBlurUtils;
        if (blurUtils != null) {
            blurUtils.destroy();
        }
        MiPlayDetailViewModel.INSTANCE.onDestroy();
        if (CommonUtils.isFlipDevice() || CommonUtils.INSTANCE.getIS_FOLD()) {
            DeviceStateManagerCompat.FoldStateCallback foldStateCallback2 = this.foldStateCallback;
            if (foldStateCallback2 == null) {
                n.w("foldStateCallback");
            } else {
                foldStateCallback = foldStateCallback2;
            }
            removeFoldStateCallback(foldStateCallback);
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.deviceStateManager, this.foldStateListener);
        }
    }
}
