package miui.systemui.miplay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.Observer;
import com.android.systemui.MiPlayController;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.MediaDataManagerInterface;
import com.android.systemui.plugins.PluginDependency;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.controls.MiPlayCastingCallback;
import com.android.systemui.plugins.miui.controls.MiPlayEntranceViewCallback;
import com.android.systemui.plugins.miui.controls.MiPlayPlugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.n;
import m0.C0465C;
import miui.systemui.dagger.PluginComponent;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.plugin.R;
import miui.systemui.plugins.PluginBase;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceStateManagerCompat;

/* JADX INFO: loaded from: classes3.dex */
@Requirements({@Requires(target = MiPlayPlugin.class, version = 1), @Requires(target = ActivityStarter.class, version = 2), @Requires(target = MediaDataManagerInterface.class, version = 1)})
public final class MiPlayPluginImpl extends PluginBase implements MiPlayPlugin {
    private DeviceStateManagerCompat.FoldStateCallback foldStateCallback;
    private Object foldStateListener;
    private Context mContext;
    private View miPlayView;
    private final String TAG = "MiPlayPluginImpl";
    private final Object deviceStateManager = DeviceStateManagerCompat.INSTANCE.getDeviceStateManagerInstance();
    private final ArrayList<DeviceStateManagerCompat.FoldStateCallback> foldStateCallbacks = new ArrayList<>();
    private final Observer<Boolean> updateIsCastingObserver = new Observer() { // from class: miui.systemui.miplay.c
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            MiPlayPluginImpl.updateIsCastingObserver$lambda$5(this.f5775a, ((Boolean) obj).booleanValue());
        }
    };
    private final Observer<Integer> updateCastingDeviceIconObserver = new Observer() { // from class: miui.systemui.miplay.d
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            MiPlayPluginImpl.updateCastingDeviceIconObserver$lambda$6(this.f5776a, ((Integer) obj).intValue());
        }
    };
    private final Observer<Integer> updateMediaDataObserver = new Observer() { // from class: miui.systemui.miplay.e
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            MiPlayPluginImpl.updateMediaDataObserver$lambda$7(this.f5777a, ((Integer) obj).intValue());
        }
    };
    private final Observer<String> updateCastDescriptionObserver = new Observer() { // from class: miui.systemui.miplay.f
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            MiPlayPluginImpl.updateCastDescriptionObserver$lambda$8(this.f5778a, (String) obj);
        }
    };
    private final CopyOnWriteArrayList<MiPlayCastingCallback> callbacks = new CopyOnWriteArrayList<>();

    private final void addFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        if (this.foldStateCallbacks.contains(foldStateCallback)) {
            return;
        }
        this.foldStateCallbacks.add(foldStateCallback);
    }

    private final View createDetailViewImpl() {
        return CommonUtils.isFlipDevice() ? MiPlayController.INSTANCE.createMiPlayDetailViewFlip() : MiPlayController.INSTANCE.createMiPlayDetailView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreated$lambda$1(MiPlayPluginImpl this$0, Boolean folded) {
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

    private final void removeFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        this.foldStateCallbacks.remove(foldStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateCastDescriptionObserver$lambda$8(MiPlayPluginImpl this$0, String it) {
        n.g(this$0, "this$0");
        n.g(it, "it");
        for (MiPlayCastingCallback miPlayCastingCallback : this$0.callbacks) {
            miPlayCastingCallback.onCastingChanged(this$0.isAudioCasting(), this$0.getCastDescription());
            try {
                miPlayCastingCallback.onCastInfoChanged(this$0.getCastingDeviceIcon());
            } catch (Throwable unused) {
                Log.e(this$0.TAG, "MiPlayCastingCallback.onCastInfoChanged called failed because systemui version is low");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateCastingDeviceIconObserver$lambda$6(MiPlayPluginImpl this$0, int i2) {
        n.g(this$0, "this$0");
        Iterator<MiPlayCastingCallback> it = this$0.callbacks.iterator();
        while (it.hasNext()) {
            try {
                it.next().onCastInfoChanged(this$0.getCastingDeviceIcon());
            } catch (Throwable unused) {
                Log.e(this$0.TAG, "MiPlayCastingCallback.onCastInfoChanged called failed because systemui version is low");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateIsCastingObserver$lambda$5(MiPlayPluginImpl this$0, boolean z2) {
        n.g(this$0, "this$0");
        Iterator<MiPlayCastingCallback> it = this$0.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onCastingChanged(this$0.isAudioCasting(), this$0.getCastDescription());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMediaDataObserver$lambda$7(MiPlayPluginImpl this$0, int i2) {
        n.g(this$0, "this$0");
        Iterator<MiPlayCastingCallback> it = this$0.callbacks.iterator();
        while (it.hasNext()) {
            try {
                it.next().onMediaDataChanged(this$0.getCastingMediaData());
            } catch (Throwable unused) {
                Log.e(this$0.TAG, "MiPlayCastingCallback.updateMediaDataObserver called failed because systemui version is low");
            }
        }
    }

    public View createMiPlayDetailView() {
        return createDetailViewImpl();
    }

    public String getCastDescription() {
        String value = MiPlayDetailViewModel.INSTANCE.getMCastDescription().getValue();
        return value == null ? "" : value;
    }

    public Map<String, Object> getCastingDeviceIcon() {
        Drawable drawable;
        HashMap map = new HashMap();
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        Context context = null;
        if (miPlayDetailViewModel.getMCastingDeviceIcon().getValue() != null) {
            Context context2 = this.mContext;
            if (context2 == null) {
                n.w("mContext");
            } else {
                context = context2;
            }
            Integer value = miPlayDetailViewModel.getMCastingDeviceIcon().getValue();
            n.d(value);
            drawable = context.getDrawable(value.intValue());
        } else {
            Context context3 = this.mContext;
            if (context3 == null) {
                n.w("mContext");
            } else {
                context = context3;
            }
            drawable = context.getDrawable(R.drawable.ic_media_device_default);
        }
        if (drawable != null) {
            map.put("device_icon", drawable);
        }
        String value2 = miPlayDetailViewModel.getMCastDescription().getValue();
        if (value2 == null) {
            value2 = "";
        }
        map.put("device_des", value2);
        String value3 = miPlayDetailViewModel.getMCastDeviceType().getValue();
        map.put("device_type", value3 != null ? value3 : "");
        Log.d(this.TAG, "getCastingDeviceIcon: " + map.get("device_type"));
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Map<java.lang.String, java.lang.Object> getCastingMediaData() {
        /*
            r4 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            com.android.systemui.MiPlayDetailViewModel r1 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
            androidx.lifecycle.MutableLiveData r1 = r1.getMCurrMediaType()
            java.lang.Object r1 = r1.getValue()
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L14
            goto L1c
        L14:
            int r1 = r1.intValue()
            r2 = 1
            if (r1 != r2) goto L1c
            goto L1d
        L1c:
            r2 = 0
        L1d:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            java.lang.String r2 = "cast_media_type"
            r0.put(r2, r1)
            java.lang.String r4 = r4.TAG
            java.lang.Object r1 = r0.get(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getCastingMediaData: "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            android.util.Log.d(r4, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.miplay.MiPlayPluginImpl.getCastingMediaData():java.util.Map");
    }

    public View getMiPlayView(MiPlayEntranceViewCallback miPlayEntranceViewCallback) {
        n.g(miPlayEntranceViewCallback, "miPlayEntranceViewCallback");
        if (this.miPlayView == null) {
            this.miPlayView = MiPlayController.INSTANCE.getEntranceView(miPlayEntranceViewCallback);
        }
        return this.miPlayView;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public void hideMiPlayDetailView(View view) {
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = view instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view : null;
        if (qSControlMiPlayDetailContent != null) {
            QSControlMiPlayDetailContent.setDetailShowing$default(qSControlMiPlayDetailContent, false, null, false, 4, null);
        }
    }

    public void hideMiPlayView() {
    }

    public boolean isAudioCasting() {
        Boolean value = MiPlayDetailViewModel.INSTANCE.getMIsCasting().getValue();
        if (value == null) {
            return false;
        }
        return value.booleanValue();
    }

    public boolean isInterconnectionCTAAgree(Context context) {
        if (context != null) {
            return MiPlayController.INSTANCE.isInterconnectionCTAAgree(context);
        }
        Log.d(this.TAG, "isInterconnectionCTAAgree:defaultValue false");
        return false;
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        this.mContext = getPluginContext();
        PluginComponent pluginComponent = PluginComponentInitializer.getPluginComponent();
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        pluginComponent.inject(miPlayController);
        miPlayController.setActivityStarter((ActivityStarter) PluginDependency.get(this, ActivityStarter.class));
        miPlayController.setMediaDataManager((MediaDataManagerInterface) PluginDependency.get(this, MediaDataManagerInterface.class));
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.onCreate();
        miPlayDetailViewModel.getMIsCasting().observeForever(this.updateIsCastingObserver);
        miPlayDetailViewModel.getMCastingDeviceIcon().observeForever(this.updateCastingDeviceIconObserver);
        miPlayDetailViewModel.getMCastDescription().observeForever(this.updateCastDescriptionObserver);
        miPlayDetailViewModel.getMCurrMediaType().observeForever(this.updateMediaDataObserver);
        C0465C miplay_audio_manager = miPlayController.getMIPLAY_AUDIO_MANAGER();
        DeviceStateManagerCompat.FoldStateCallback foldStateCallback = null;
        if (miplay_audio_manager != null) {
            miplay_audio_manager.l(miPlayController.getSERVICE_LISTENER(), null);
        }
        C0465C miplay_audio_manager2 = miPlayController.getMIPLAY_AUDIO_MANAGER();
        if (miplay_audio_manager2 != null) {
            miplay_audio_manager2.l(miPlayDetailViewModel, null);
        }
        C0465C miplay_audio_manager3 = miPlayController.getMIPLAY_AUDIO_MANAGER();
        if (miplay_audio_manager3 != null) {
            miplay_audio_manager3.f(getSysuiContext());
        }
        if (CommonUtils.isFlipDevice() || CommonUtils.INSTANCE.getIS_FOLD()) {
            this.foldStateCallback = new DeviceStateManagerCompat.FoldStateCallback() { // from class: miui.systemui.miplay.MiPlayPluginImpl.onCreated.1
                @Override // miui.systemui.util.DeviceStateManagerCompat.FoldStateCallback
                public void onFoldStateChanged(boolean z2) {
                    Log.d(MiPlayPluginImpl.this.getTAG(), "onFoldStateChanged: " + z2);
                    MiPlayDetailViewModel.INSTANCE.setLastFoldState(z2);
                }
            };
            DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
            Context context = this.mContext;
            if (context == null) {
                n.w("mContext");
                context = null;
            }
            this.foldStateListener = deviceStateManagerCompat.getFoldStateListenerInstance(context, new Consumer() { // from class: miui.systemui.miplay.g
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    MiPlayPluginImpl.onCreated$lambda$1(this.f5779a, (Boolean) obj);
                }
            });
            Object obj = this.deviceStateManager;
            Context context2 = this.mContext;
            if (context2 == null) {
                n.w("mContext");
                context2 = null;
            }
            Executor mainExecutor = context2.getMainExecutor();
            n.f(mainExecutor, "getMainExecutor(...)");
            deviceStateManagerCompat.registerCallbackCompat(obj, mainExecutor, this.foldStateListener);
            DeviceStateManagerCompat.FoldStateCallback foldStateCallback2 = this.foldStateCallback;
            if (foldStateCallback2 == null) {
                n.w("foldStateCallback");
            } else {
                foldStateCallback = foldStateCallback2;
            }
            addFoldStateCallback(foldStateCallback);
        }
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        this.callbacks.clear();
        if (CommonUtils.isFlipDevice() || CommonUtils.INSTANCE.getIS_FOLD()) {
            DeviceStateManagerCompat.FoldStateCallback foldStateCallback = this.foldStateCallback;
            if (foldStateCallback == null) {
                n.w("foldStateCallback");
                foldStateCallback = null;
            }
            removeFoldStateCallback(foldStateCallback);
            DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.deviceStateManager, this.foldStateListener);
        }
        this.miPlayView = null;
        C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
        if (miplay_audio_manager != null) {
            miplay_audio_manager.m();
        }
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMIsCasting().removeObserver(this.updateIsCastingObserver);
        miPlayDetailViewModel.getMCastingDeviceIcon().removeObserver(this.updateCastingDeviceIconObserver);
        miPlayDetailViewModel.getMCastDescription().removeObserver(this.updateCastDescriptionObserver);
        miPlayDetailViewModel.getMCurrMediaType().removeObserver(this.updateMediaDataObserver);
        miPlayDetailViewModel.onDestroy();
    }

    public void registerCastingCallback(MiPlayCastingCallback callback) {
        n.g(callback, "callback");
        this.callbacks.add(callback);
        callback.onCastingChanged(isAudioCasting(), getCastDescription());
        try {
            callback.onCastInfoChanged(getCastingDeviceIcon());
            callback.onMediaDataChanged(getCastingMediaData());
        } catch (Throwable unused) {
            Log.e(this.TAG, "MiPlayCastingCallback.onCastInfoChanged called failed because systemui version is low");
        }
    }

    public void showCtaPage() {
        MiPlayController.INSTANCE.showCtaPage();
    }

    public void showMiPlayDetailView(View view, String ref) {
        n.g(ref, "ref");
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = view instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view : null;
        if (qSControlMiPlayDetailContent != null) {
            QSControlMiPlayDetailContent.setDetailShowing$default(qSControlMiPlayDetailContent, true, ref, false, 4, null);
        }
    }

    public boolean supportMiPlayAudio() {
        return MiPlayController.INSTANCE.supportMiPlayAudio();
    }

    public void unregisterCastingCallback(MiPlayCastingCallback miPlayCastingCallback) {
        this.callbacks.remove(miPlayCastingCallback);
    }
}
