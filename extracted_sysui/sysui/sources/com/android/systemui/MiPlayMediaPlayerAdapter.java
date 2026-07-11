package com.android.systemui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.android.systemui.miplay.R;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0369g;
import java.util.HashMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import m0.C0466a;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.controlcenter.media.MediaPlayerIconsInfo;
import miui.systemui.controlcenter.media.MediaPlayerMetaData;
import miui.systemui.coroutines.CoroutineScopeKt;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.CommonUtils;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayMediaPlayerAdapter implements MediaPlayerAdapter, LifecycleOwner {
    public static final Companion Companion = new Companion(null);
    private static final long PREV_NEXT_TOUCH_INTERVAL = 2000;
    private static final String TAG = "MiPlayMediaPlayerAdapter";
    private boolean _listening;
    private final Context context;
    private final MiPlayDetailAdapter detailAdapter;
    private final MediaPlayerIconsInfo iconsInfo;
    private final LifecycleRegistry lifecycleRegistry;
    private MediaPlayerAdapter.MediaInfoListener mediaInfoListener;
    private long prevNextTouchTime;

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            MediaPlayerIconsInfo mediaPlayerIconsInfo = MiPlayMediaPlayerAdapter.this.iconsInfo;
            Integer value = MiPlayDetailViewModel.INSTANCE.getMCastingDeviceIcon().getValue();
            mediaPlayerIconsInfo.setDeviceRes(value == null ? R.drawable.miplay_select_device : value.intValue());
            MiPlayMediaPlayerAdapter._init_$updateIconsInfo(MiPlayMediaPlayerAdapter.this);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Boolean) obj);
            return H0.s.f314a;
        }

        public final void invoke(Boolean bool) {
            MediaPlayerIconsInfo mediaPlayerIconsInfo = MiPlayMediaPlayerAdapter.this.iconsInfo;
            kotlin.jvm.internal.n.d(bool);
            mediaPlayerIconsInfo.setDeviceRes(bool.booleanValue() ? R.drawable.miplay_select_device_working : R.drawable.miplay_select_device);
            MiPlayMediaPlayerAdapter._init_$updateIconsInfo(MiPlayMediaPlayerAdapter.this);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$3, reason: invalid class name */
    public static final class AnonymousClass3 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((MediaMetaData) obj);
            return H0.s.f314a;
        }

        public final void invoke(MediaMetaData mediaMetaData) {
            MiPlayMediaPlayerAdapter.this.updateIconsInfoByMediaType(mediaMetaData != null ? mediaMetaData.getMediaType() : 0);
            MediaPlayerAdapter.MediaInfoListener mediaInfoListener = MiPlayMediaPlayerAdapter.this.mediaInfoListener;
            if (mediaInfoListener != null) {
                mediaInfoListener.updateIconsInfo(MiPlayMediaPlayerAdapter.this.iconsInfo);
            }
            MediaPlayerAdapter.MediaInfoListener mediaInfoListener2 = MiPlayMediaPlayerAdapter.this.mediaInfoListener;
            if (mediaInfoListener2 != null) {
                mediaInfoListener2.updateMetaData(mediaMetaData != null ? Companion.convert$default(MiPlayMediaPlayerAdapter.Companion, mediaMetaData, null, 1, null) : null);
            }
            if (mediaMetaData != null) {
                MiPlayMediaPlayerAdapter.this.updateMediaControllerState();
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$4, reason: invalid class name */
    public static final class AnonymousClass4 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Boolean) obj);
            return H0.s.f314a;
        }

        public final void invoke(Boolean bool) {
            Log.d(MiPlayMediaPlayerAdapter.TAG, "playingAdvertisement:" + bool + ",:" + MiPlayMediaPlayerAdapter.this.mediaInfoListener);
            if (MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue() != null) {
                MiPlayMediaPlayerAdapter.this.updateMediaControllerState();
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$5, reason: invalid class name */
    public static final class AnonymousClass5 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ kotlin.jvm.internal.y $iconsUpdateJob;
        final /* synthetic */ g1.E $uiScope;

        /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$5$1, reason: invalid class name */
        @N0.f(c = "com.android.systemui.MiPlayMediaPlayerAdapter$5$1", f = "MiPlayMediaPlayerAdapter.kt", l = {125}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends N0.l implements Function2 {
            final /* synthetic */ kotlin.jvm.internal.y $iconsUpdateJob;
            int label;
            final /* synthetic */ MiPlayMediaPlayerAdapter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(MiPlayMediaPlayerAdapter miPlayMediaPlayerAdapter, kotlin.jvm.internal.y yVar, L0.d dVar) {
                super(2, dVar);
                this.this$0 = miPlayMediaPlayerAdapter;
                this.$iconsUpdateJob = yVar;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new AnonymousClass1(this.this$0, this.$iconsUpdateJob, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(g1.E e2, L0.d dVar) {
                return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = M0.c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    H0.k.b(obj);
                    long jElapsedRealtime = MiPlayMediaPlayerAdapter.PREV_NEXT_TOUCH_INTERVAL - (SystemClock.elapsedRealtime() - this.this$0.prevNextTouchTime);
                    this.label = 1;
                    if (g1.M.b(jElapsedRealtime, this) == objC) {
                        return objC;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    H0.k.b(obj);
                }
                MiPlayMediaPlayerAdapter._init_$updateIconsInfo(this.this$0);
                this.$iconsUpdateJob.f5059a = null;
                return H0.s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(kotlin.jvm.internal.y yVar, g1.E e2) {
            super(1);
            this.$iconsUpdateJob = yVar;
            this.$uiScope = e2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            MiPlayMediaPlayerAdapter.this.iconsInfo.setPlayRes(MiPlayDetailViewModel.INSTANCE.isLocalPlaying() ? R.drawable.ic_media_pause_animation : R.drawable.ic_media_play_animation);
            if (SystemClock.elapsedRealtime() - MiPlayMediaPlayerAdapter.this.prevNextTouchTime >= MiPlayMediaPlayerAdapter.PREV_NEXT_TOUCH_INTERVAL) {
                MiPlayMediaPlayerAdapter._init_$updateIconsInfo(MiPlayMediaPlayerAdapter.this);
            } else {
                kotlin.jvm.internal.y yVar = this.$iconsUpdateJob;
                if (yVar.f5059a == null) {
                    yVar.f5059a = AbstractC0369g.b(this.$uiScope, null, null, new AnonymousClass1(MiPlayMediaPlayerAdapter.this, yVar, null), 3, null);
                }
            }
            MiPlayMediaPlayerAdapter.this.updateMediaControllerState();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$6, reason: invalid class name */
    public static final class AnonymousClass6 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass6() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            MiPlayMediaPlayerAdapter.this.updateMediaControllerState();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$7, reason: invalid class name */
    public static final class AnonymousClass7 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass7() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HashMap<String, Drawable>) obj);
            return H0.s.f314a;
        }

        public final void invoke(HashMap<String, Drawable> map) {
            MediaPlayerAdapter.MediaInfoListener mediaInfoListener;
            MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
            if (value == null || (mediaInfoListener = MiPlayMediaPlayerAdapter.this.mediaInfoListener) == null) {
                return;
            }
            mediaInfoListener.updateMetaData(Companion.convert$default(MiPlayMediaPlayerAdapter.Companion, value, null, 1, null));
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ MediaPlayerMetaData convert$default(Companion companion, MediaMetaData mediaMetaData, MediaPlayerMetaData mediaPlayerMetaData, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                mediaPlayerMetaData = null;
            }
            return companion.convert(mediaMetaData, mediaPlayerMetaData);
        }

        public final MediaPlayerMetaData convert(MediaMetaData mediaMetaData, MediaPlayerMetaData mediaPlayerMetaData) {
            kotlin.jvm.internal.n.g(mediaMetaData, "<this>");
            if (mediaPlayerMetaData == null) {
                return new MediaPlayerMetaData(mediaMetaData.getArtist(), mediaMetaData.getAlbum(), mediaMetaData.getTitle(), mediaMetaData.getArt(), mediaMetaData.getDuration(), mediaMetaData.getMediaId(), mediaMetaData.getTVId());
            }
            mediaPlayerMetaData.setArtist(mediaMetaData.getArtist());
            mediaPlayerMetaData.setAlbum(mediaMetaData.getAlbum());
            mediaPlayerMetaData.setTitle(mediaMetaData.getTitle());
            mediaPlayerMetaData.setArt(mediaMetaData.getArt());
            mediaPlayerMetaData.setDuration(mediaMetaData.getDuration());
            mediaPlayerMetaData.setMediaId(mediaMetaData.getMediaId());
            mediaPlayerMetaData.setTvId(mediaMetaData.getTVId());
            return mediaPlayerMetaData;
        }

        private Companion() {
        }
    }

    public static final class MiPlayDetailAdapter implements DetailAdapter {
        private final Context context;

        public MiPlayDetailAdapter(Context context) {
            kotlin.jvm.internal.n.g(context, "context");
            this.context = context;
        }

        public View createDetailView(Context context, View view, ViewGroup viewGroup) {
            return view != null ? view : CommonUtils.INSTANCE.getForceVertical() ? MiPlayController.INSTANCE.createMiPlayDetailView() : MiPlayController.INSTANCE.createMiPlayDetailViewSupportLand();
        }

        public int[] getBackgroundBlendColors() {
            return this.context.getResources().getIntArray(R.array.miplay_detail_background_blend_colors);
        }

        public int getBackgroundColor() {
            return this.context.getColor(R.color.miplay_detail_background_color);
        }

        public int getMetricsCategory() {
            return 10010;
        }

        public Intent getSettingsIntent() {
            return null;
        }

        public CharSequence getTitle() {
            return "MiPlay";
        }

        public Boolean getToggleState() {
            return Boolean.FALSE;
        }

        public boolean hasHeader() {
            return false;
        }

        public void setToggleState(boolean z2) {
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayMediaPlayerAdapter$setListening$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayMediaPlayerAdapter$setListening$1", f = "MiPlayMediaPlayerAdapter.kt", l = {}, m = "invokeSuspend")
    public static final class C02391 extends N0.l implements Function2 {
        int label;

        public C02391(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02391(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02391) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            MiPlayDetailViewModel.INSTANCE.refreshAudioSession(false);
            MiPlayEventTracker.trackExpose(MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            return H0.s.f314a;
        }
    }

    public MiPlayMediaPlayerAdapter(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        this.context = context;
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        this.lifecycleRegistry = lifecycleRegistry;
        this.detailAdapter = new MiPlayDetailAdapter(context);
        this.iconsInfo = new MediaPlayerIconsInfo(R.drawable.ic_media_play_animation, R.drawable.ic_media_prev, R.drawable.ic_media_next, R.drawable.ic_media_device_default);
        updateIconsInfoByMediaType(isVideoMediaType() ? 1 : 0);
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMCastingDeviceIcon().observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass1()));
        miPlayDetailViewModel.getMMediaMetaData().observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass3()));
        miPlayDetailViewModel.isPlayingAdvertisement().observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass4()));
        g1.E eMainScope = CoroutineScopeKt.MainScope();
        miPlayDetailViewModel.getMPlaybackState().observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass5(new kotlin.jvm.internal.y(), eMainScope)));
        miPlayDetailViewModel.getCpStateLiveData().observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass6()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMMediaDataManagerArt()).observe(this, new MiPlayMediaPlayerAdapter$sam$androidx_lifecycle_Observer$0(new AnonymousClass7()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$updateIconsInfo(MiPlayMediaPlayerAdapter miPlayMediaPlayerAdapter) {
        MediaPlayerAdapter.MediaInfoListener mediaInfoListener = miPlayMediaPlayerAdapter.mediaInfoListener;
        if (mediaInfoListener != null) {
            mediaInfoListener.updateIconsInfo(miPlayMediaPlayerAdapter.iconsInfo);
        }
    }

    private final boolean allowControlRemoteTV() {
        return MiPlayDetailViewModel.INSTANCE.allowControlRemoteTV();
    }

    private final boolean isShowCtaPage() {
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        if (miPlayController.isInterconnectionCTAAgree(this.context)) {
            return false;
        }
        miPlayController.showCtaPage();
        return true;
    }

    private final boolean isVideoMediaType() {
        MediaMetaData value;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        return miPlayDetailViewModel.hasMediaData() && (value = miPlayDetailViewModel.getMMediaMetaData().getValue()) != null && value.getMediaType() == 1;
    }

    private final void set_listening(boolean z2) {
        Lifecycle.State state;
        if (this._listening == z2) {
            return;
        }
        this._listening = z2;
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (z2) {
            MiPlayController.INSTANCE.ensureService();
            state = Lifecycle.State.STARTED;
        } else {
            state = Lifecycle.State.CREATED;
        }
        lifecycleRegistry.setCurrentState(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateIconsInfoByMediaType(int i2) {
        if (i2 == 1) {
            this.iconsInfo.setPrevRes(R.drawable.miplay_video_rewind_15_second_icon);
            this.iconsInfo.setNextRes(R.drawable.miplay_video_fast_forward_15_second_icon);
        } else {
            this.iconsInfo.setPrevRes(R.drawable.ic_media_prev);
            this.iconsInfo.setNextRes(R.drawable.ic_media_next);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMediaControllerState() {
        if (allowControlRemoteTV()) {
            MediaPlayerAdapter.MediaInfoListener mediaInfoListener = this.mediaInfoListener;
            if (mediaInfoListener != null) {
                mediaInfoListener.enableMediaController();
                return;
            }
            return;
        }
        MediaPlayerAdapter.MediaInfoListener mediaInfoListener2 = this.mediaInfoListener;
        if (mediaInfoListener2 != null) {
            mediaInfoListener2.disableMediaController();
        }
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void onDeviceClicked() {
        Log.d(TAG, "selectDevice click");
        if (isShowCtaPage()) {
            return;
        }
        MediaPlayerAdapter.MediaInfoListener mediaInfoListener = this.mediaInfoListener;
        if (mediaInfoListener != null) {
            mediaInfoListener.showDetail(this.detailAdapter);
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CAST, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void onNextClicked() {
        m0.w wVarB;
        m0.w wVarB2;
        Log.d(TAG, "next click");
        this.prevNextTouchTime = SystemClock.elapsedRealtime();
        if (isVideoMediaType()) {
            C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
            if (targetSession$default != null && (wVarB2 = targetSession$default.b()) != null) {
                wVarB2.f();
            }
            MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_FAST_FORWARD, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            return;
        }
        C0466a targetSession$default2 = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default2 != null && (wVarB = targetSession$default2.b()) != null) {
            wVarB.o();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NEXT, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void onPlayClicked() {
        m0.w wVarB;
        String cpDeepLink;
        Log.d(TAG, "onPlayClicked");
        this.prevNextTouchTime = 0L;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        C0466a value = miPlayDetailViewModel.getMActiveAudioSession().getValue();
        if (value == null || (wVarB = value.b()) == null) {
            wVarB = null;
        } else {
            if (!miPlayDetailViewModel.allowControlRemoteTV() && (cpDeepLink = miPlayDetailViewModel.getCpDeepLink()) != null && cpDeepLink.length() != 0) {
                QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
                if (companion.hasDeepLinkPermission()) {
                    companion.jumpLastPlayingApp(this.context);
                    MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
                    return;
                }
            }
            if (miPlayDetailViewModel.isLocalPlaying()) {
                wVarB.p();
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            } else {
                wVarB.q();
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            }
        }
        if (wVarB == null) {
            QSControlMiPlayDetailHeader.Companion.jumpLastPlayingApp(this.context);
            if (miPlayDetailViewModel.isLocalPlaying()) {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            } else {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            }
        }
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void onPlayerClicked() {
        Log.d(TAG, "view click");
        if (isShowCtaPage()) {
            return;
        }
        MediaPlayerAdapter.MediaInfoListener mediaInfoListener = this.mediaInfoListener;
        if (mediaInfoListener != null) {
            mediaInfoListener.showDetail(this.detailAdapter);
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CARD, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void onPrevClicked() {
        m0.w wVarB;
        m0.w wVarB2;
        Log.d(TAG, "prev click");
        this.prevNextTouchTime = SystemClock.elapsedRealtime();
        if (isVideoMediaType()) {
            C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
            if (targetSession$default != null && (wVarB2 = targetSession$default.b()) != null) {
                wVarB2.w();
            }
            MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_REWIND, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            return;
        }
        C0466a value = MiPlayDetailViewModel.INSTANCE.getMActiveAudioSession().getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.t();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PREV, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void setListening(boolean z2) {
        set_listening(z2);
        Log.d(TAG, "setListening " + z2);
        if (this._listening) {
            AbstractC0369g.b(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.INSTANCE.getDefault(), null, new C02391(null), 2, null);
        }
    }

    @Override // miui.systemui.controlcenter.media.MediaPlayerAdapter
    public void setMediaInfoListener(MediaPlayerAdapter.MediaInfoListener mediaInfoListener) {
        this.mediaInfoListener = mediaInfoListener;
    }
}
