package com.android.systemui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import com.android.systemui.miplay.R;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.MediaDataManagerInterface;
import com.android.systemui.plugins.miui.controls.MiPlayEntranceViewCallback;
import java.util.List;
import m0.C0465C;
import m0.C0466a;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ReflectBuilderUtil;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"StaticFieldLeak"})
public final class MiPlayController extends BaseMiPlayController {
    private static final String ACTION_CTA_ACTIVITY = "com.milink.service.newCta";
    private static final String APP_NAME_MIPLAY = "miplay";
    private static final int DISABLE = 0;
    private static final int ENABLE = 1;
    private static final String KEY_APP_NAME = "app_name";
    private static final String SETTINGS_KEY_INTERCONNECTION_PRIVACY_STATE = "settings_key_interconnection_privacy_state";
    private static ActivityStarter activityStarter;
    private static int mReconnectCount;
    private static MediaDataManagerInterface mediaDataManager;
    public static final MiPlayController INSTANCE = new MiPlayController();
    private static final int MAX_RECONNECT_COUNT = 5;
    private static final Runnable RECONNECT_ACTION = new Runnable() { // from class: com.android.systemui.j
        @Override // java.lang.Runnable
        public final void run() {
            MiPlayController.RECONNECT_ACTION$lambda$3();
        }
    };
    private static final m0.D SERVICE_LISTENER = new m0.D() { // from class: com.android.systemui.MiPlayController$SERVICE_LISTENER$1
        @Override // m0.D
        public void onActiveAudioSessionChange(List<C0466a> list) {
        }

        @Override // m0.D
        public void onAudioDeviceListChange(List<m0.i> list) {
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onAudioParingStateChange() {
            super.onAudioParingStateChange();
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onAudioShareFinish() {
            super.onAudioShareFinish();
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onBluetoothDeviceConnectFail(String str) {
            super.onBluetoothDeviceConnectFail(str);
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onBluetoothDeviceConnectSuccess(String str) {
            super.onBluetoothDeviceConnectSuccess(str);
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onCastStateChange(boolean z2) {
            super.onCastStateChange(z2);
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onDeviceStartPlaying(Bundle bundle) {
            super.onDeviceStartPlaying(bundle);
        }

        @Override // m0.D
        public void onError(int i2, String str) {
        }

        @Override // m0.D
        public void onProjectionStateChange(int i2) {
        }

        @Override // m0.D
        public void onServiceStateChange(int i2) {
            C0465C miplay_audio_manager;
            MiPlayController miPlayController = MiPlayController.INSTANCE;
            miPlayController.setMState(i2);
            Log.d(miPlayController.getTAG(), "onServiceStateChange(): mState = " + miPlayController.getMState());
            if (miPlayController.getMState() != 6) {
                if (i2 == 2) {
                    miPlayController.setMReconnectCount(0);
                    if (!kotlin.jvm.internal.n.c(MiPlayDetailViewModel.INSTANCE.getMIsListShowing().getValue(), Boolean.TRUE) || (miplay_audio_manager = miPlayController.getMIPLAY_AUDIO_MANAGER()) == null) {
                        return;
                    }
                    miplay_audio_manager.n();
                    return;
                }
                return;
            }
            if (miPlayController.getMReconnectCount() < miPlayController.getMAX_RECONNECT_COUNT()) {
                Log.d(miPlayController.getTAG(), "miplay service state invalid = " + miPlayController.getMState());
                miPlayController.getMainHandler().removeCallbacks(miPlayController.getRECONNECT_ACTION());
                miPlayController.getMainHandler().postDelayed(miPlayController.getRECONNECT_ACTION(), ((long) miPlayController.getMReconnectCount()) * 150);
                miPlayController.setMReconnectCount(miPlayController.getMReconnectCount() + 1);
            }
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onVideoCastModeChange(int i2, int i3) {
            super.onVideoCastModeChange(i2, i3);
        }

        @Override // m0.D
        public /* bridge */ /* synthetic */ void onVideoCpAppStateChange(int i2, String str) {
            super.onVideoCpAppStateChange(i2, str);
        }
    };

    private MiPlayController() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RECONNECT_ACTION$lambda$3() {
        ((C0465C) INSTANCE.get_MIPLAY_AUDIO_MANAGER().get()).k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _set_mediaDataManager_$lambda$0(String str, Drawable drawable) {
        MiPlayDetailViewModel.INSTANCE.onMediaDataLoaded(str, drawable);
    }

    public final void cancelReconnectAction() {
        getMainHandler().removeCallbacks(RECONNECT_ACTION);
    }

    public final View createMiPlayDetailView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.qs_control_detail_miplay_content, (ViewGroup) null);
        kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
        return viewInflate;
    }

    public final View createMiPlayDetailViewFlip() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.qs_control_detail_miplay_content_support_flip, (ViewGroup) null);
        kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
        return viewInflate;
    }

    public final View createMiPlayDetailViewSupportLand() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.qs_control_detail_miplay_content_support_land, (ViewGroup) null);
        kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
        return viewInflate;
    }

    public final ActivityStarter getActivityStarter() {
        return activityStarter;
    }

    public final View getEntranceView(MiPlayEntranceViewCallback miPlayEntranceViewCallback) {
        kotlin.jvm.internal.n.g(miPlayEntranceViewCallback, "miPlayEntranceViewCallback");
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.miplay_entrance_view, (ViewGroup) null);
        ControlCenterMiPlayView controlCenterMiPlayView = viewInflate instanceof ControlCenterMiPlayView ? (ControlCenterMiPlayView) viewInflate : null;
        if (controlCenterMiPlayView != null) {
            controlCenterMiPlayView.setMiPlayEntranceViewCallback(miPlayEntranceViewCallback);
        }
        kotlin.jvm.internal.n.f(viewInflate, "also(...)");
        return viewInflate;
    }

    public final View getEntranceView2(MiPlayEntranceViewCallback miPlayEntranceViewCallback) {
        kotlin.jvm.internal.n.g(miPlayEntranceViewCallback, "miPlayEntranceViewCallback");
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.miplay_view, (ViewGroup) null);
        MiPlayView miPlayView = viewInflate instanceof MiPlayView ? (MiPlayView) viewInflate : null;
        if (miPlayView != null) {
            miPlayView.setMiPlayEntranceViewCallback(miPlayEntranceViewCallback);
        }
        kotlin.jvm.internal.n.f(viewInflate, "also(...)");
        return viewInflate;
    }

    public final int getMAX_RECONNECT_COUNT() {
        return MAX_RECONNECT_COUNT;
    }

    public final int getMReconnectCount() {
        return mReconnectCount;
    }

    public final MediaDataManagerInterface getMediaDataManager() {
        return mediaDataManager;
    }

    public final Runnable getRECONNECT_ACTION() {
        return RECONNECT_ACTION;
    }

    public final m0.D getSERVICE_LISTENER() {
        return SERVICE_LISTENER;
    }

    public final boolean isInterconnectionCTAAgree(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        int i2 = Settings.Secure.getInt(context.getContentResolver(), SETTINGS_KEY_INTERCONNECTION_PRIVACY_STATE, 0);
        Log.d(getTAG(), "isInterconnectionCTAAgree  status =" + i2);
        return i2 == 1;
    }

    public final void setActivityStarter(ActivityStarter activityStarter2) {
        activityStarter = activityStarter2;
    }

    public final void setMReconnectCount(int i2) {
        mReconnectCount = i2;
    }

    public final void setMediaDataManager(MediaDataManagerInterface mediaDataManagerInterface) {
        MediaDataManagerInterface mediaDataManagerInterface2 = mediaDataManager;
        if (mediaDataManagerInterface2 != null) {
            mediaDataManagerInterface2.addArtListener(new MediaDataManagerInterface.ArtListener() { // from class: com.android.systemui.i
                public final void onMediaDataLoaded(String str, Drawable drawable) {
                    MiPlayController._set_mediaDataManager_$lambda$0(str, drawable);
                }
            });
        }
        mediaDataManager = mediaDataManagerInterface;
    }

    public final void showCtaPage() {
        Log.d(getTAG(), "showCtaPage");
        try {
            CommonUtils.INSTANCE.collapseControlCenter();
            Intent intent = new Intent(ACTION_CTA_ACTIVITY);
            intent.putExtra("app_name", APP_NAME_MIPLAY);
            intent.addFlags(268468224);
            getContext().startActivity(intent);
        } catch (Exception e2) {
            Log.e(getTAG(), "showCtaPage", e2);
        }
    }

    public final void showCtaPageAC(@NonNull ComponentActivity activity) {
        kotlin.jvm.internal.n.g(activity, "activity");
        Log.d(getTAG(), "showCtaPageAC");
        try {
            Intent intent = new Intent(ACTION_CTA_ACTIVITY);
            intent.putExtra("app_name", APP_NAME_MIPLAY);
            intent.addFlags(268468224);
            activity.startActivity(intent);
            activity.finish();
        } catch (Exception e2) {
            Log.e(getTAG(), "showCtaPageAC", e2);
        }
    }

    public final boolean supportMiPlayAudio() {
        ReflectBuilderUtil.getCurrentUserId();
        return C0465C.r(getContext()) || MiCastUtils.INSTANCE.useInternationalCast(getContext());
    }
}
