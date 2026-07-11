package com.android.systemui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.android.systemui.TvManger;
import com.android.systemui.activity.BluetoothAudioShareActivity;
import com.android.systemui.miplay.R;
import com.miui.miplay.audio.data.AppMetaData;
import com.miui.miplay.audio.data.DeviceInfo;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.InterfaceC0380l0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function2;
import m0.C0465C;
import m0.C0466a;
import m0.InterfaceC0464B;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.concurrency.ConcurrencyModule;
import miui.systemui.util.settings.GlobalSettings;
import org.json.JSONObject;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;
import systemui.plugin.eventtracking.utils.OaidUtils;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDetailViewModel implements m0.D, InterfaceC0464B, TvManger.TvInfoCallback {
    private static final long DEVICE_REFRESH_TIME_INTERVAL = 300;
    public static final long FLIP_ANIMATION_TRICK_TIME = 500;
    public static final String KEY_LAST_PLAYING_PACKAGE_NAME = "xiaomi_mi_play_last_playing_package_name";
    private static final HashSet<Integer> PAUSED_MEDIA_STATES;
    public static final String TAG = "MiPlayDetailViewModel";
    public static final long UPDATE_DEFAULT_COVER_TIME = 1000;
    private static boolean audioShareSecondaryEarphoneSeeking;
    private static ArrayMap<m0.i, Long> blueToothDeviceSelectTime;
    private static MutableLiveData<Integer> castMode;
    private static String cpDeepLink;
    private static final CpState cpState;
    private static final MutableLiveData<Integer> cpStateLiveData;
    private static ArrayMap<String, Long> deviceSelectTime;
    private static String firstPanelArtist;
    private static String firstPanelTitle;
    private static MutableLiveData<Boolean> isPlayingAdvertisement;
    private static InterfaceC0380l0 job;
    private static boolean lastFoldState;
    private static long lastTimeReceiverDevices;
    private static ArrayList<m0.i> mAudioShareSelectedDevices;
    private static final MutableLiveData<String> mCastDescription;
    private static final MutableLiveData<String> mCastDeviceType;
    private static final MutableLiveData<Integer> mCastingDeviceIcon;
    private static final MutableLiveData<VolumeController> mController;
    private static MutableLiveData<Integer> mCurrMediaType;
    private static final MutableLiveData<List<m0.i>> mDevices;
    private static final MutableLiveData<Integer> mErrorCodeEvent;
    private static boolean mFirstLoadCover;
    private static final ArrayList<m0.i> mHeadsetDevices;
    private static final MutableLiveData<Boolean> mIsCasting;
    private static final MutableLiveData<Boolean> mIsListShowing;
    private static m0.i mLastLocalOrBluetoothDevice;
    private static final ArrayList<m0.i> mLocalSpeakerDevices;
    private static final MutableLiveData<HashMap<String, Drawable>> mMediaDataManagerArt;
    private static String mMediaIDForCardCover;
    private static String mMediaIDForCover;
    private static final ArrayList<m0.i> mMiPlayDevices;
    private static final MiPlayRef mMiPlayRef;
    private static final ArrayList<m0.i> mOtherDevices;
    private static boolean mOverAllVolumeBarUserTouching;
    private static final MutableLiveData<Integer> mOverAllVolumeProgress;
    private static final MutableLiveData<Float> mPlaySpeed;
    private static final MutableLiveData<List<Float>> mPlaySpeedList;
    private static final MutableLiveData<Integer> mPlaybackState;
    private static final ArrayList<m0.i> mSelectedDevices;
    private static final ArrayList<m0.i> mSelectedMiPlayDevices;
    private static final MutableLiveData<Boolean> mTvSelectStatus;
    private static List<m0.i> originDeviceList;
    private static final Runnable refreshDeviceRunnable;
    private static LinkedHashMap<m0.i, Long> scanDeviceTimeMap;
    private static final HashMap<m0.i, Long> selectedDevicesTimeMap;
    private static long startScanDeviceTime;
    private static Boolean supportAudioShared;
    public static final MiPlayDetailViewModel INSTANCE = new MiPlayDetailViewModel();
    private static final MutableLiveData<C0466a> mActiveAudioSession = new MutableLiveData<>();
    private static final MutableLiveData<AppMetaData> mAppMetadata = new MutableLiveData<>();
    private static final MutableLiveData<MediaMetaData> mMediaMetaData = new MutableLiveData<>();
    private static final MutableLiveData<Long> mPosition = new MutableLiveData<>();

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDetailViewModel$getPlayingPackageName$2, reason: invalid class name */
    @N0.f(c = "com.android.systemui.MiPlayDetailViewModel$getPlayingPackageName$2", f = "MiPlayDetailViewModel.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        int label;

        public AnonymousClass2(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass2(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            MiPlayController miPlayController = MiPlayController.INSTANCE;
            miPlayController.getGlobalSettings();
            return miPlayController.getGlobalSettings().getString(MiPlayDetailViewModel.KEY_LAST_PLAYING_PACKAGE_NAME);
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDetailViewModel$onAudioDeviceListChange$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayDetailViewModel$onAudioDeviceListChange$2", f = "MiPlayDetailViewModel.kt", l = {639}, m = "invokeSuspend")
    public static final class C02342 extends N0.l implements Function2 {
        int label;

        public C02342(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02342(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02342) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                this.label = 1;
                if (g1.M.b(300L, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            MiPlayDetailViewModel.refreshDeviceRunnable.run();
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDetailViewModel$savePlayingPackageName$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayDetailViewModel$savePlayingPackageName$2", f = "MiPlayDetailViewModel.kt", l = {}, m = "invokeSuspend")
    public static final class C02352 extends N0.l implements Function2 {
        final /* synthetic */ String $packageName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02352(String str, L0.d dVar) {
            super(2, dVar);
            this.$packageName = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02352(this.$packageName, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02352) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            MiPlayController miPlayController = MiPlayController.INSTANCE;
            GlobalSettings globalSettings = miPlayController.getGlobalSettings();
            miPlayController.getGlobalSettings().putString(MiPlayDetailViewModel.KEY_LAST_PLAYING_PACKAGE_NAME, this.$packageName);
            return globalSettings;
        }
    }

    static {
        MutableLiveData<HashMap<String, Drawable>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(new HashMap<>());
        mMediaDataManagerArt = mutableLiveData;
        selectedDevicesTimeMap = new HashMap<>();
        mPlaySpeed = new MutableLiveData<>(Float.valueOf(1.0f));
        mPlaySpeedList = new MutableLiveData<>();
        mMiPlayRef = new MiPlayRef();
        castMode = new MutableLiveData<>(Integer.MIN_VALUE);
        Boolean bool = Boolean.FALSE;
        isPlayingAdvertisement = new MutableLiveData<>(bool);
        mTvSelectStatus = new MutableLiveData<>(bool);
        cpState = new CpState();
        cpStateLiveData = new MutableLiveData<>(-1);
        mCurrMediaType = new MutableLiveData<>(0);
        mFirstLoadCover = true;
        C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
        supportAudioShared = miplay_audio_manager != null ? Boolean.valueOf(miplay_audio_manager.q()) : null;
        mAudioShareSelectedDevices = new ArrayList<>();
        deviceSelectTime = new ArrayMap<>();
        blueToothDeviceSelectTime = new ArrayMap<>();
        scanDeviceTimeMap = new LinkedHashMap<>();
        mDevices = new MutableLiveData<>(new ArrayList());
        mSelectedDevices = new ArrayList<>();
        mMiPlayDevices = new ArrayList<>();
        mSelectedMiPlayDevices = new ArrayList<>();
        mLocalSpeakerDevices = new ArrayList<>();
        mHeadsetDevices = new ArrayList<>();
        mOtherDevices = new ArrayList<>();
        mIsCasting = new MutableLiveData<>(bool);
        mCastingDeviceIcon = new MutableLiveData<>();
        mCastDescription = new MutableLiveData<>();
        mCastDeviceType = new MutableLiveData<>();
        mIsListShowing = new MutableLiveData<>();
        mOverAllVolumeProgress = new MutableLiveData<>();
        MutableLiveData<VolumeController> mutableLiveData2 = new MutableLiveData<>();
        Transformations.switchMap(mutableLiveData2, MiPlayDetailViewModel$mController$1$1.INSTANCE).observeForever(new MiPlayDetailViewModelKt$sam$androidx_lifecycle_Observer$0(new MiPlayDetailViewModel$mController$1$2$1(mutableLiveData2)));
        mController = mutableLiveData2;
        mErrorCodeEvent = new MutableLiveData<>();
        refreshDeviceRunnable = new Runnable() { // from class: com.android.systemui.k
            @Override // java.lang.Runnable
            public final void run() {
                MiPlayDetailViewModel.refreshDeviceRunnable$lambda$21();
            }
        };
        mPlaybackState = new MutableLiveData<>();
        Set setD = I0.K.d(2, 1, 6, 7, 8, 0);
        kotlin.jvm.internal.n.e(setD, "null cannot be cast to non-null type java.util.HashSet<kotlin.Int>{ kotlin.collections.TypeAliasesKt.HashSet<kotlin.Int> }");
        PAUSED_MEDIA_STATES = (HashSet) setD;
    }

    private MiPlayDetailViewModel() {
    }

    private final void cancelRefreshJob() {
        InterfaceC0380l0 interfaceC0380l0 = job;
        if (interfaceC0380l0 != null) {
            interfaceC0380l0.a(null);
        }
    }

    private final ArrayList<m0.i> forecastNextSelectedDevices(m0.i iVar, boolean z2) {
        if (MiPlayExtentionsKt.isLocalSpeaker(iVar) || MiPlayExtentionsKt.isBluetoothTv(iVar) || MiPlayExtentionsKt.isBluetoothHeadset(iVar) || MiPlayExtentionsKt.isBluetoothDevice(iVar)) {
            return I0.m.f(iVar);
        }
        ArrayList<m0.i> arrayList = mSelectedDevices;
        ArrayList<m0.i> arrayList2 = new ArrayList<>(arrayList);
        if (z2) {
            arrayList2.add(iVar);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : arrayList) {
                if (!MiPlayExtentionsKt.isMiPlayDevice((m0.i) obj)) {
                    arrayList3.add(obj);
                }
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                arrayList2.remove((m0.i) it.next());
            }
        } else {
            arrayList2.remove(iVar);
            if (arrayList2.isEmpty()) {
                m0.i iVar2 = mLastLocalOrBluetoothDevice;
                if (iVar2 != null) {
                    arrayList2.add(iVar2);
                } else {
                    arrayList2.addAll(mLocalSpeakerDevices);
                }
            }
        }
        return arrayList2;
    }

    private final int getIcon(List<? extends m0.i> list) {
        Iterator<? extends m0.i> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().k().isAudioSharing()) {
                return R.drawable.ic_miplay_bluetooth_share_light;
            }
        }
        Map mapL = I0.G.l(I0.u.g0(I0.I.q(selectedDevicesTimeMap), new Comparator() { // from class: com.android.systemui.MiPlayDetailViewModel$getIcon$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t3) {
                return K0.a.a(Long.valueOf(((Number) ((H0.i) t2).b()).longValue()), Long.valueOf(((Number) ((H0.i) t3).b()).longValue()));
            }
        }));
        int i2 = R.drawable.ic_miplay_groups_sprakers_tv_light;
        for (Map.Entry entry : mapL.entrySet()) {
            m0.i iVar = (m0.i) entry.getKey();
            long jLongValue = ((Number) entry.getValue()).longValue();
            Log.d(TAG, "getIcon: device.deviceInfo.name = " + iVar.k().getName() + " ,time = " + jLongValue);
        }
        Iterator it2 = mapL.entrySet().iterator();
        while (it2.hasNext()) {
            m0.i iVar2 = (m0.i) ((Map.Entry) it2.next()).getKey();
            if (list.contains(iVar2)) {
                if (MiPlayExtentionsKt.isCar(iVar2)) {
                    return R.drawable.ic_miplay_car_light;
                }
                DeviceInfo deviceInfoK = iVar2.k();
                kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
                int miPlayDeviceType = MiPlayExtentionsKt.getMiPlayDeviceType(deviceInfoK);
                return miPlayDeviceType != 2 ? miPlayDeviceType != 3 ? (miPlayDeviceType == 4 || miPlayDeviceType == 16) ? R.drawable.ic_miplay_speaker_light : R.drawable.ic_miplay_groups_sprakers_tv_light : R.drawable.ic_miplay_laptop_light : R.drawable.ic_miplay_tv_light;
            }
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ C0466a getTargetSession$default(MiPlayDetailViewModel miPlayDetailViewModel, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
            list = miplay_audio_manager != null ? miplay_audio_manager.i() : null;
        }
        return miPlayDetailViewModel.getTargetSession(list);
    }

    private final boolean isMiuiMusic() {
        MiPlayRef miPlayRef = mMiPlayRef;
        return kotlin.jvm.internal.n.c(miPlayRef.getMRef(), MiPlayExtentionsKt.REF_MIUIMUSIC_NOWPLAYING) || miPlayRef.refMatchNotification();
    }

    public static /* synthetic */ void refreshAudioSession$default(MiPlayDetailViewModel miPlayDetailViewModel, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        miPlayDetailViewModel.refreshAudioSession(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshDeviceRunnable$lambda$21() {
        List<m0.i> list = originDeviceList;
        if (list != null) {
            Log.d(TAG, "onAudioDeviceListChange(): realUpdate");
            updateDevicesList$default(INSTANCE, list, false, true, 2, null);
        }
    }

    private final void resetTvSelectedStatus() {
        Object next;
        MediaMetaData value;
        Iterator<T> it = mSelectedMiPlayDevices.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            DeviceInfo deviceInfoK = ((m0.i) next).k();
            kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
            if (MiPlayExtentionsKt.isTelevision(deviceInfoK)) {
                break;
            }
        }
        boolean z2 = false;
        boolean z3 = next != null;
        MutableLiveData<Boolean> mutableLiveData = mTvSelectStatus;
        if (z3 && (value = mMediaMetaData.getValue()) != null && value.getMediaType() == 1) {
            z2 = true;
        }
        mutableLiveData.setValue(Boolean.valueOf(z2));
        Log.d(TAG, "resetTvSelectedStatus: " + mutableLiveData.getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object savePlayingPackageName(String str, L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new C02352(str, null), dVar);
    }

    private final String toStatDeviceId(ArrayList<m0.i> arrayList) {
        if (arrayList.size() > 1) {
            ArrayList arrayList2 = new ArrayList();
            for (m0.i iVar : arrayList) {
                DeviceInfo deviceInfoK = iVar.k();
                kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
                H0.i iVarA = H0.o.a("device_type", MiPlayExtentionsKt.getMiPlayDeviceSubTypeStatName(deviceInfoK));
                DeviceInfo deviceInfoK2 = iVar.k();
                kotlin.jvm.internal.n.f(deviceInfoK2, "getDeviceInfo(...)");
                arrayList2.add(I0.G.h(iVarA, H0.o.a("device_id", MiPlayExtentionsKt.getMacMd5(deviceInfoK2))));
            }
            return new JSONObject(I0.G.h(new H0.i("group_id", "miplay-audio-group"), new H0.i("speaker_list", arrayList2.toArray(new Map[0])))).toString();
        }
        if (arrayList.size() != 1) {
            return "unknown";
        }
        m0.i iVar2 = arrayList.get(0);
        kotlin.jvm.internal.n.d(iVar2);
        if (MiPlayExtentionsKt.isLocalSpeaker(iVar2)) {
            return OaidUtils.INSTANCE.getOaid();
        }
        DeviceInfo deviceInfoK3 = iVar2.k();
        kotlin.jvm.internal.n.f(deviceInfoK3, "getDeviceInfo(...)");
        if (!MiPlayExtentionsKt.isGroupStereoDevice(deviceInfoK3)) {
            DeviceInfo deviceInfoK4 = iVar2.k();
            kotlin.jvm.internal.n.f(deviceInfoK4, "getDeviceInfo(...)");
            return MiPlayExtentionsKt.getMacMd5(deviceInfoK4);
        }
        ArrayList arrayList3 = new ArrayList();
        List<DeviceInfo> stereoDeviceList = iVar2.k().getStereoDeviceList();
        kotlin.jvm.internal.n.f(stereoDeviceList, "getStereoDeviceList(...)");
        for (DeviceInfo deviceInfo : stereoDeviceList) {
            kotlin.jvm.internal.n.d(deviceInfo);
            arrayList3.add(MiPlayExtentionsKt.getMacMd5(deviceInfo));
        }
        return new JSONObject(I0.G.h(new H0.i("stereo_id", iVar2.k().getGroupId()), new H0.i("speaker_list", arrayList3.toArray(new String[0])))).toString();
    }

    private final String toStatProtocolType(ArrayList<m0.i> arrayList) {
        Iterator<T> it = arrayList.iterator();
        if (!it.hasNext()) {
            return "miplay";
        }
        m0.i iVar = (m0.i) it.next();
        return MiPlayExtentionsKt.isLocalSpeaker(iVar) ? "本机" : MiPlayExtentionsKt.isBluetoothDevice(iVar) ? MiPlayEventsKt.DEVICE_TYPE_BT : "miplay";
    }

    private final void updateAudioSession(C0466a c0466a, boolean z2) {
        H0.s sVar;
        H0.s sVar2;
        m0.w wVarB;
        Log.d(TAG, "updateAudioSession " + z2);
        MutableLiveData<C0466a> mutableLiveData = mActiveAudioSession;
        C0466a value = mutableLiveData.getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.A(this);
        }
        if (!z2 || !kotlin.jvm.internal.n.c(Looper.myLooper(), Looper.getMainLooper())) {
            MiPlayExtentionsKt.postIfChanged$default(mutableLiveData, c0466a, null, 2, null);
            if (c0466a != null) {
                c0466a.b().u(this, new Handler(Looper.getMainLooper()));
                MiPlayExtentionsKt.postIfChanged$default(mAppMetadata, c0466a.a(), null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mMediaMetaData, c0466a.b().h(), null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mPosition, Long.valueOf(c0466a.b().k()), null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mPlaybackState, Integer.valueOf(c0466a.b().j()), null, 2, null);
                if (c0466a.b().j() == 3) {
                    AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new MiPlayDetailViewModel$updateAudioSession$3$1(c0466a, null), 3, null);
                }
                MediaMetaData mediaMetaDataH = c0466a.b().h();
                if (!kotlin.jvm.internal.n.c(mediaMetaDataH != null ? Integer.valueOf(mediaMetaDataH.getMediaType()) : null, mCurrMediaType.getValue())) {
                    MutableLiveData<Integer> mutableLiveData2 = mCurrMediaType;
                    MediaMetaData mediaMetaDataH2 = c0466a.b().h();
                    MiPlayExtentionsKt.postIfChanged$default(mutableLiveData2, Integer.valueOf(mediaMetaDataH2 != null ? mediaMetaDataH2.getMediaType() : 0), null, 2, null);
                }
                sVar = H0.s.f314a;
            } else {
                sVar = null;
            }
            if (sVar == null) {
                MiPlayExtentionsKt.postIfChanged$default(mAppMetadata, null, null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mMediaMetaData, null, null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mPosition, 0L, null, 2, null);
                MiPlayExtentionsKt.postIfChanged$default(mPlaybackState, 0, null, 2, null);
                return;
            }
            return;
        }
        mutableLiveData.setValue(c0466a);
        C0466a value2 = mutableLiveData.getValue();
        if (value2 != null) {
            value2.b().u(this, null);
            mAppMetadata.setValue(value2.a());
            MutableLiveData<MediaMetaData> mutableLiveData3 = mMediaMetaData;
            mutableLiveData3.setValue(value2.b().h());
            mPosition.setValue(Long.valueOf(value2.b().k()));
            MutableLiveData<Integer> mutableLiveData4 = mPlaybackState;
            mutableLiveData4.setValue(Integer.valueOf(value2.b().j()));
            Integer value3 = mutableLiveData4.getValue();
            if (value3 != null && value3.intValue() == 3) {
                AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new MiPlayDetailViewModel$updateAudioSession$1$1(value2, null), 3, null);
            }
            MediaMetaData value4 = mutableLiveData3.getValue();
            if (!kotlin.jvm.internal.n.c(value4 != null ? Integer.valueOf(value4.getMediaType()) : null, mCurrMediaType.getValue())) {
                MutableLiveData<Integer> mutableLiveData5 = mCurrMediaType;
                MediaMetaData value5 = mutableLiveData3.getValue();
                mutableLiveData5.setValue(value5 != null ? Integer.valueOf(value5.getMediaType()) : 0);
            }
            sVar2 = H0.s.f314a;
        } else {
            sVar2 = null;
        }
        if (sVar2 == null) {
            mAppMetadata.setValue(null);
            mMediaMetaData.setValue(null);
            mPosition.setValue(0L);
            mPlaybackState.setValue(0);
        }
    }

    public static /* synthetic */ void updateAudioSession$default(MiPlayDetailViewModel miPlayDetailViewModel, C0466a c0466a, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        miPlayDetailViewModel.updateAudioSession(c0466a, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0142  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateDevicesList(java.util.List<? extends m0.i> r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 513
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.MiPlayDetailViewModel.updateDevicesList(java.util.List, boolean, boolean):void");
    }

    public static /* synthetic */ void updateDevicesList$default(MiPlayDetailViewModel miPlayDetailViewModel, List list, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        miPlayDetailViewModel.updateDevicesList(list, z2, z3);
    }

    public final boolean allowControlRemoteTV() {
        return cpState.allowControlRemoteTV();
    }

    public final void doAdjustVolume(boolean z2) {
        VolumeController value = mController.getValue();
        if (value != null) {
            value.doAdjustVolume(z2);
        }
    }

    public final void doSetOverallVolume(int i2) {
        VolumeController value = mController.getValue();
        if (value != null) {
            value.doSetVolume(value.progressToVolume(i2));
        }
    }

    public final void doUpdateDeviceVolume(m0.i device, int i2) {
        MiPlayDeviceVolumeController miPlayDeviceVolumeController;
        kotlin.jvm.internal.n.g(device, "device");
        VolumeController value = mController.getValue();
        if (!(value instanceof MiPlayOverallVolumeController) || (miPlayDeviceVolumeController = ((MiPlayOverallVolumeController) value).getDeviceControllers().get(device)) == null || device.k().isAudioSharing()) {
            return;
        }
        miPlayDeviceVolumeController.doSetVolume(miPlayDeviceVolumeController.progressToVolume(i2));
    }

    public final boolean getAudioShareSecondaryEarphoneSeeking() {
        return audioShareSecondaryEarphoneSeeking;
    }

    public final ArrayMap<m0.i, Long> getBlueToothDeviceSelectTime() {
        return blueToothDeviceSelectTime;
    }

    public final MutableLiveData<Integer> getCastMode() {
        return castMode;
    }

    public final String getCpDeepLink() {
        return cpDeepLink;
    }

    public final CpState getCpState() {
        return cpState;
    }

    public final MutableLiveData<Integer> getCpStateLiveData() {
        return cpStateLiveData;
    }

    public final ArrayList<m0.i> getCurrentSelectedDevice() {
        return mSelectedDevices;
    }

    public final ArrayMap<String, Long> getDeviceSelectTime() {
        return deviceSelectTime;
    }

    public final String getFirstPanelArtist() {
        return firstPanelArtist;
    }

    public final String getFirstPanelTitle() {
        return firstPanelTitle;
    }

    public final boolean getLastFoldState() {
        return lastFoldState;
    }

    public final MutableLiveData<C0466a> getMActiveAudioSession() {
        return mActiveAudioSession;
    }

    public final MutableLiveData<AppMetaData> getMAppMetadata() {
        return mAppMetadata;
    }

    public final ArrayList<m0.i> getMAudioShareSelectedDevices() {
        return mAudioShareSelectedDevices;
    }

    public final MutableLiveData<String> getMCastDescription() {
        return mCastDescription;
    }

    public final MutableLiveData<String> getMCastDeviceType() {
        return mCastDeviceType;
    }

    public final MutableLiveData<Integer> getMCastingDeviceIcon() {
        return mCastingDeviceIcon;
    }

    public final MutableLiveData<VolumeController> getMController() {
        return mController;
    }

    public final MutableLiveData<Integer> getMCurrMediaType() {
        return mCurrMediaType;
    }

    public final MutableLiveData<List<m0.i>> getMDevices() {
        return mDevices;
    }

    public final MutableLiveData<Integer> getMErrorCodeEvent() {
        return mErrorCodeEvent;
    }

    public final boolean getMFirstLoadCover() {
        return mFirstLoadCover;
    }

    public final ArrayList<m0.i> getMHeadsetDevices() {
        return mHeadsetDevices;
    }

    public final MutableLiveData<Boolean> getMIsCasting() {
        return mIsCasting;
    }

    public final MutableLiveData<Boolean> getMIsListShowing() {
        return mIsListShowing;
    }

    public final m0.i getMLastLocalOrBluetoothDevice() {
        return mLastLocalOrBluetoothDevice;
    }

    public final ArrayList<m0.i> getMLocalSpeakerDevices() {
        return mLocalSpeakerDevices;
    }

    public final MutableLiveData<HashMap<String, Drawable>> getMMediaDataManagerArt() {
        return mMediaDataManagerArt;
    }

    public final String getMMediaIDForCardCover() {
        return mMediaIDForCardCover;
    }

    public final String getMMediaIDForCover() {
        return mMediaIDForCover;
    }

    public final MutableLiveData<MediaMetaData> getMMediaMetaData() {
        return mMediaMetaData;
    }

    public final ArrayList<m0.i> getMMiPlayDevices() {
        return mMiPlayDevices;
    }

    public final MiPlayRef getMMiPlayRef() {
        return mMiPlayRef;
    }

    public final ArrayList<m0.i> getMOtherDevices() {
        return mOtherDevices;
    }

    public final boolean getMOverAllVolumeBarUserTouching() {
        return mOverAllVolumeBarUserTouching;
    }

    public final MutableLiveData<Integer> getMOverAllVolumeProgress() {
        return mOverAllVolumeProgress;
    }

    public final MutableLiveData<Float> getMPlaySpeed() {
        return mPlaySpeed;
    }

    public final MutableLiveData<List<Float>> getMPlaySpeedList() {
        return mPlaySpeedList;
    }

    public final MutableLiveData<Integer> getMPlaybackState() {
        return mPlaybackState;
    }

    public final MutableLiveData<Long> getMPosition() {
        return mPosition;
    }

    public final ArrayList<m0.i> getMSelectedDevices() {
        return mSelectedDevices;
    }

    public final ArrayList<m0.i> getMSelectedMiPlayDevices() {
        return mSelectedMiPlayDevices;
    }

    public final MutableLiveData<Boolean> getMTvSelectStatus() {
        return mTvSelectStatus;
    }

    public final Drawable getMediaDataManagerArt() {
        AppMetaData value = mAppMetadata.getValue();
        String packageName = value != null ? value.getPackageName() : null;
        HashMap<String, Drawable> value2 = mMediaDataManagerArt.getValue();
        if (value2 == null) {
            return null;
        }
        kotlin.jvm.internal.n.d(value2);
        return value2.get(packageName);
    }

    public final String getMediaType() {
        MediaMetaData value = mMediaMetaData.getValue();
        if (value != null) {
            return MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(value.getMediaType())) ? MiPlayController.INSTANCE.getContext().getResources().getString(R.string.event_track_content_type_audio) : MiPlayController.INSTANCE.getContext().getResources().getString(R.string.event_track_content_type_video);
        }
        return MiPlayController.INSTANCE.getContext().getResources().getString(R.string.event_track_content_type_empty);
    }

    public final Object getPlayingPackageName(L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new AnonymousClass2(null), dVar);
    }

    public final LinkedHashMap<m0.i, Long> getScanDeviceTimeMap() {
        return scanDeviceTimeMap;
    }

    public final HashMap<m0.i, Long> getSelectedDevicesTimeMap() {
        return selectedDevicesTimeMap;
    }

    public final String getSourcePackage() {
        String packageName;
        AppMetaData value = mAppMetadata.getValue();
        if (value == null || (packageName = value.getPackageName()) == null) {
            return null;
        }
        return packageName;
    }

    public final long getStartScanDeviceTime() {
        return startScanDeviceTime;
    }

    public final Boolean getSupportAudioShared() {
        return supportAudioShared;
    }

    public final C0466a getTargetSession(List<? extends C0466a> list) {
        C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
        int iE = miplay_audio_manager != null ? miplay_audio_manager.e() : 1;
        Log.d(TAG, "isMiuiMusic:" + isMiuiMusic() + " " + mMiPlayRef.getMRef() + ",releaseVersion:" + iE);
        Object obj = null;
        if (!isMiuiMusic() && iE != 1) {
            if (list != null) {
                return (C0466a) I0.u.M(list);
            }
            return null;
        }
        if (list == null) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            MediaMetaData mediaMetaDataH = ((C0466a) next).b().h();
            if (mediaMetaDataH != null && mediaMetaDataH.getMediaType() == 0) {
                obj = next;
                break;
            }
        }
        return (C0466a) obj;
    }

    public final boolean hasMediaData() {
        return mMediaMetaData.getValue() != null;
    }

    public final boolean isAudioShare() {
        return EarPhoneUtils.INSTANCE.isAudioShare(mAudioShareSelectedDevices);
    }

    public final boolean isHighDevice() {
        return DeviceUtils.isHighLevel() || DeviceUtils.isUltraLevel();
    }

    public final boolean isLocalPausing() {
        return I0.u.F(PAUSED_MEDIA_STATES, mPlaybackState.getValue());
    }

    public final boolean isLocalPlaying() {
        return !I0.u.F(PAUSED_MEDIA_STATES, mPlaybackState.getValue());
    }

    public final boolean isLowDevice() {
        return DeviceUtils.isLowLevel() || DeviceUtils.isMidLowLevel();
    }

    public final MutableLiveData<Boolean> isPlayingAdvertisement() {
        return isPlayingAdvertisement;
    }

    @Override // m0.D
    public void onActiveAudioSessionChange(List<C0466a> list) {
        Log.d(TAG, "onActiveAudioSessionChange(): audioSessionList?.size =" + (list != null ? Integer.valueOf(list.size()) : null));
        kotlin.jvm.internal.n.e(list, "null cannot be cast to non-null type kotlin.collections.List<com.miui.miplay.audio.api.ActiveAudioSession>");
        updateAudioSession$default(this, getTargetSession(list), false, 2, null);
    }

    @Override // m0.D
    public void onAudioDeviceListChange(List<m0.i> list) {
        Log.d(TAG, "onAudioDeviceListChange(): devices.size = " + (list != null ? Integer.valueOf(list.size()) : null));
        originDeviceList = list;
        if (list != null) {
            for (m0.i iVar : list) {
                if (!scanDeviceTimeMap.containsKey(iVar) && !MiPlayExtentionsKt.isLocalSpeaker(iVar)) {
                    scanDeviceTimeMap.put(iVar, Long.valueOf(SystemClock.elapsedRealtime()));
                }
            }
        }
        if (System.currentTimeMillis() - lastTimeReceiverDevices <= 300) {
            cancelRefreshJob();
            job = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C02342(null), 3, null);
        } else {
            cancelRefreshJob();
            lastTimeReceiverDevices = System.currentTimeMillis();
            refreshDeviceRunnable.run();
        }
    }

    @Override // m0.D
    public void onAudioParingStateChange() throws IOException {
        super.onAudioParingStateChange();
        Log.d(TAG, "receive audioParingStateChange close miplay");
        CommonUtils.INSTANCE.collapseControlCenter();
        MiPlayController.INSTANCE.getContext().sendBroadcast(new Intent(BluetoothAudioShareActivity.ACTION_CLOSE_ACT));
    }

    @Override // m0.D
    public void onAudioShareFinish() {
        super.onAudioShareFinish();
        Log.d(TAG, "onAudioShareFinish 已结束一起听");
    }

    @Override // m0.D
    public void onBluetoothDeviceConnectFail(String str) {
        Log.d(TAG, "onBluetoothDeviceConnectFail: " + str);
        trackBlueToothConnect(str, false);
    }

    @Override // m0.D
    public void onBluetoothDeviceConnectSuccess(String str) {
        Log.d(TAG, "onBluetoothDeviceConnectSuccess: " + str);
        trackBlueToothConnect(str, true);
    }

    @Override // m0.InterfaceC0464B
    public void onBufferStateChange(int i2) {
    }

    @Override // m0.InterfaceC0464B
    public void onCastModeChange(int i2, int i3) {
        super.onCastModeChange(i2, i3);
        setCastMode(i2, i3);
    }

    @Override // m0.D
    public /* bridge */ /* synthetic */ void onCastStateChange(boolean z2) {
        super.onCastStateChange(z2);
    }

    @Override // m0.InterfaceC0464B
    public void onCpAppStateChange(int i2, String str) {
        super.onCpAppStateChange(i2, str);
        Log.d(TAG, "onCpAppStateChange state:" + i2 + ",pk:" + str);
        cpState.setAppState(i2);
        cpStateLiveData.setValue(Integer.valueOf(i2));
    }

    @Override // m0.InterfaceC0464B
    public void onCpStateChange(String str, int i2) {
        super.onCpStateChange(str, i2);
        cpDeepLink = str;
        Log.d(TAG, "onCpStateChange() deepLink:" + str + ",event:" + i2);
    }

    public final void onCreate() {
        Log.d(TAG, "onCreate(): this = " + this);
        SystemVolumeController.INSTANCE.init();
    }

    public final void onDestroy() {
        Log.d(TAG, "onDestroy(): this = " + this);
        SystemVolumeController.INSTANCE.release();
        MiPlayDeviceVolumeBar.INSTANCE.release();
    }

    @Override // m0.D
    public void onDeviceStartPlaying(Bundle bundle) {
        super.onDeviceStartPlaying(bundle);
        MediaMetaData value = mMediaMetaData.getValue();
        int mediaType = value != null ? value.getMediaType() : 0;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String string = bundle != null ? bundle.getString(MiPlayEventsKt.START_PLAYING_DEVICE_ID) : null;
        String string2 = bundle != null ? bundle.getString("peer_model") : null;
        String str = string2 == null ? "" : string2;
        String string3 = bundle != null ? bundle.getString("peer_rom_version") : null;
        String str2 = string3 == null ? "" : string3;
        Integer numValueOf = bundle != null ? Integer.valueOf(bundle.getInt("deviceType")) : null;
        Long l2 = deviceSelectTime.get(string);
        if (l2 == null) {
            l2 = 0L;
        }
        long jLongValue = l2.longValue();
        long j2 = jElapsedRealtime - jLongValue;
        if (jLongValue != 0 && numValueOf != null) {
            MiPlayEventTracker.trackPeerVoiceTime(j2, mediaType, numValueOf.intValue(), str, str2);
        }
        deviceSelectTime.remove(string);
    }

    @Override // m0.D
    public void onError(int i2, String str) {
        MutableLiveData<Integer> mutableLiveData = mErrorCodeEvent;
        mutableLiveData.setValue(Integer.valueOf(i2));
        mutableLiveData.setValue(null);
    }

    @Override // com.android.systemui.TvManger.TvInfoCallback
    public boolean onGetSupportVideoFlowCapacity() {
        m0.w wVarB;
        MediaMetaData mediaMetaDataH;
        C0466a value = mActiveAudioSession.getValue();
        if (value != null && (wVarB = value.b()) != null && (mediaMetaDataH = wVarB.h()) != null) {
            int mediaType = mediaMetaDataH.getMediaType();
            if (mediaType == 0) {
                Log.d(TAG, "onGetSupportVideoFlowCapacity mediaType error");
                return false;
            }
            List<m0.i> list = originDeviceList;
            if (list != null) {
                for (m0.i iVar : list) {
                    if (MiPlayExtentionsKt.isSelectedDevice(iVar, Integer.valueOf(mediaType))) {
                        boolean zSupportVideoFlowCapacity = iVar.k().supportVideoFlowCapacity();
                        Log.d(TAG, "onGetSupportVideoFlowCapacity: " + zSupportVideoFlowCapacity);
                        return zSupportVideoFlowCapacity;
                    }
                }
            }
            Log.d(TAG, "onGetSupportVideoFlowCapacity default value:false");
        }
        return false;
    }

    @Override // com.android.systemui.TvManger.TvInfoCallback
    public String onGetTvId() {
        m0.w wVarB;
        MediaMetaData mediaMetaDataH;
        C0466a value = mActiveAudioSession.getValue();
        if (value != null && (wVarB = value.b()) != null && (mediaMetaDataH = wVarB.h()) != null) {
            int mediaType = mediaMetaDataH.getMediaType();
            if (mediaType == 0) {
                Log.d(TAG, "onGetTvId mediaType error:" + mediaType);
                return null;
            }
            List<m0.i> list = originDeviceList;
            if (list != null) {
                for (m0.i iVar : list) {
                    if (MiPlayExtentionsKt.isSelectedDevice(iVar, Integer.valueOf(mediaType))) {
                        Log.d(TAG, "onGetTvId selected deviceName:" + iVar.k().getName() + ",isTv:" + MiPlayExtentionsKt.isMiPlayTv(iVar) + ",lyraId:" + iVar.k().getLyraId() + ",idHash:" + iVar.k().getIdHash());
                        return TextUtils.isEmpty(iVar.k().getLyraId()) ? iVar.k().getIdHash() : iVar.k().getLyraId();
                    }
                }
            }
        }
        return null;
    }

    @Override // com.android.systemui.TvManger.TvInfoCallback
    public String onGetTvMac() {
        m0.w wVarB;
        MediaMetaData mediaMetaDataH;
        C0466a value = mActiveAudioSession.getValue();
        if (value != null && (wVarB = value.b()) != null && (mediaMetaDataH = wVarB.h()) != null) {
            int mediaType = mediaMetaDataH.getMediaType();
            if (mediaType == 0) {
                Log.d(TAG, "onGetTvMac mediaType error:" + mediaType);
                return null;
            }
            List<m0.i> list = originDeviceList;
            if (list != null) {
                for (m0.i iVar : list) {
                    if (MiPlayExtentionsKt.isSelectedDevice(iVar, Integer.valueOf(mediaType))) {
                        Log.d(TAG, "onGetTvMac selected deviceName:" + iVar.k().getName() + ",isTv:" + MiPlayExtentionsKt.isMiPlayTv(iVar) + ",lyraId:" + iVar.k().getLyraId() + ",idHash:" + iVar.k().getIdHash() + ",mac:" + iVar.k().getMac());
                        return iVar.k().getMac();
                    }
                }
            }
        }
        return null;
    }

    @Override // com.android.systemui.TvManger.TvInfoCallback
    public String onGetTvName() {
        m0.w wVarB;
        MediaMetaData mediaMetaDataH;
        C0466a value = mActiveAudioSession.getValue();
        if (value != null && (wVarB = value.b()) != null && (mediaMetaDataH = wVarB.h()) != null) {
            int mediaType = mediaMetaDataH.getMediaType();
            if (mediaType == 0) {
                Log.d(TAG, "onGetTvName mediaType error:" + mediaType);
                return null;
            }
            List<m0.i> list = originDeviceList;
            if (list != null) {
                for (m0.i iVar : list) {
                    if (MiPlayExtentionsKt.isSelectedDevice(iVar, Integer.valueOf(mediaType))) {
                        Log.d(TAG, "onGetTvName selected deviceName:" + iVar.k().getName() + ",isTv:" + MiPlayExtentionsKt.isMiPlayTv(iVar));
                        return iVar.k().getName();
                    }
                }
            }
        }
        return null;
    }

    public final void onMediaDataLoaded(String str, Drawable drawable) {
        HashMap<String, Drawable> value;
        if (str != null) {
            if (drawable != null) {
                MutableLiveData<HashMap<String, Drawable>> mutableLiveData = mMediaDataManagerArt;
                HashMap<String, Drawable> value2 = mutableLiveData.getValue();
                if (value2 != null) {
                    kotlin.jvm.internal.n.d(value2);
                    value2.put(str, drawable);
                }
                mutableLiveData.setValue(mutableLiveData.getValue());
                return;
            }
            HashMap<String, Drawable> value3 = mMediaDataManagerArt.getValue();
            String str2 = null;
            if (value3 != null) {
                kotlin.jvm.internal.n.d(value3);
                String str3 = null;
                for (Map.Entry<String, Drawable> entry : value3.entrySet()) {
                    String key = entry.getKey();
                    entry.getValue();
                    if (f1.o.v(str, key, false, 2, null)) {
                        str3 = key;
                    }
                }
                str2 = str3;
            }
            if (str2 == null || (value = mMediaDataManagerArt.getValue()) == null) {
                return;
            }
            value.remove(str2);
        }
    }

    @Override // m0.InterfaceC0464B
    public void onMediaMetaChange(MediaMetaData metaData) {
        kotlin.jvm.internal.n.g(metaData, "metaData");
        Log.d(TAG, "onMediaMetaChange(): metaData.title = " + metaData);
        mMediaMetaData.setValue(metaData);
    }

    @Override // m0.InterfaceC0464B
    public void onPlaySpeedChange(float f2) {
        Log.d(TAG, "onPlaySpeedChange:" + f2);
        mPlaySpeed.postValue(Float.valueOf(f2));
    }

    @Override // m0.InterfaceC0464B
    public void onPlaySpeedListChange(List<Float> list) {
        Log.d(TAG, "onPlaySpeedListChange:" + list);
        if (list != null) {
            mPlaySpeedList.postValue(list);
        }
    }

    @Override // m0.InterfaceC0464B
    public void onPlaybackStateChange(int i2) {
        Log.d(TAG, "onPlaybackStateChange(): state = " + i2);
        mPlaybackState.setValue(Integer.valueOf(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    @Override // m0.InterfaceC0464B
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPlayingAdvertisementChange(com.miui.miplay.audio.data.AdvertisementParam r5) {
        /*
            r4 = this;
            java.lang.String r0 = "param"
            kotlin.jvm.internal.n.g(r5, r0)
            super.onPlayingAdvertisementChange(r5)
            int r4 = r5.getState()
            int r0 = r5.getMediaType()
            java.lang.String r1 = r5.getPkg()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onPlayingAdvertisementChange state:"
            r2.append(r3)
            r2.append(r4)
            java.lang.String r4 = ",type:"
            r2.append(r4)
            r2.append(r0)
            java.lang.String r4 = ",pkg:"
            r2.append(r4)
            r2.append(r1)
            java.lang.String r4 = r2.toString()
            java.lang.String r0 = "MiPlayDetailViewModel"
            android.util.Log.d(r0, r4)
            com.android.systemui.CpState r4 = com.android.systemui.MiPlayDetailViewModel.cpState
            int r0 = r5.getState()
            r1 = 1
            if (r0 != r1) goto L64
            int r0 = r5.getMediaType()
            if (r0 != r1) goto L64
            java.lang.String r5 = r5.getPkg()
            androidx.lifecycle.MutableLiveData<com.miui.miplay.audio.data.AppMetaData> r0 = com.android.systemui.MiPlayDetailViewModel.mAppMetadata
            java.lang.Object r0 = r0.getValue()
            com.miui.miplay.audio.data.AppMetaData r0 = (com.miui.miplay.audio.data.AppMetaData) r0
            if (r0 == 0) goto L5c
            java.lang.String r0 = r0.getPackageName()
            goto L5d
        L5c:
            r0 = 0
        L5d:
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L64
            goto L65
        L64:
            r1 = 0
        L65:
            r4.setPlayingAdvertisement(r1)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = com.android.systemui.MiPlayDetailViewModel.isPlayingAdvertisement
            boolean r4 = r4.isPlayingAdvertisement()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r5.postValue(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.MiPlayDetailViewModel.onPlayingAdvertisementChange(com.miui.miplay.audio.data.AdvertisementParam):void");
    }

    @Override // m0.InterfaceC0464B
    public void onPositionChange(long j2) {
        MutableLiveData<Long> mutableLiveData = mPosition;
        Long value = mutableLiveData.getValue();
        if (value != null && value.longValue() == j2) {
            return;
        }
        Log.d(TAG, "onPositionChangeSelf position = " + j2);
        mutableLiveData.setValue(Long.valueOf(j2));
    }

    @Override // m0.D
    public void onProjectionStateChange(int i2) {
    }

    @Override // m0.D
    public void onServiceStateChange(int i2) {
    }

    @Override // m0.D
    public void onVideoCastModeChange(int i2, int i3) {
        super.onVideoCastModeChange(i2, i3);
        setCastMode(i2, i3);
    }

    @Override // m0.D
    public void onVideoCpAppStateChange(int i2, String str) {
        super.onVideoCpAppStateChange(i2, str);
        Log.d(TAG, "onVideoCpAppStateChange state:" + i2 + ",pk:" + str);
        cpState.setAppState(i2);
        cpStateLiveData.setValue(Integer.valueOf(i2));
    }

    public final void refreshAudioSession(boolean z2) {
        updateAudioSession(getTargetSession$default(this, null, 1, null), z2);
    }

    public final void refreshSystemVolume() {
        SystemVolumeController systemVolumeController = SystemVolumeController.INSTANCE;
        MutableLiveData<Integer> volumeLiveData = systemVolumeController.getVolumeLiveData();
        if (volumeLiveData == null) {
            return;
        }
        volumeLiveData.setValue(Integer.valueOf(systemVolumeController.getMAudioManager().getStreamVolume(3)));
    }

    public final void reloadDevices() {
        MediaMetaData value = mMediaMetaData.getValue();
        int mediaType = value != null ? value.getMediaType() : 0;
        C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
        if (miplay_audio_manager != null) {
            miplay_audio_manager.j(mediaType);
        }
    }

    public final void setAudioShareSecondaryEarphoneSeeking(boolean z2) {
        audioShareSecondaryEarphoneSeeking = z2;
    }

    public final void setBlueToothDeviceSelectTime(ArrayMap<m0.i, Long> arrayMap) {
        kotlin.jvm.internal.n.g(arrayMap, "<set-?>");
        blueToothDeviceSelectTime = arrayMap;
    }

    public final void setCastMode(MutableLiveData<Integer> mutableLiveData) {
        kotlin.jvm.internal.n.g(mutableLiveData, "<set-?>");
        castMode = mutableLiveData;
    }

    public final void setCpDeepLink(String str) {
        cpDeepLink = str;
    }

    public final void setDeviceSelectTime(ArrayMap<String, Long> arrayMap) {
        kotlin.jvm.internal.n.g(arrayMap, "<set-?>");
        deviceSelectTime = arrayMap;
    }

    public final void setFirstPanelArtist(String str) {
        firstPanelArtist = str;
    }

    public final void setFirstPanelTitle(String str) {
        firstPanelTitle = str;
    }

    public final void setLastFoldState(boolean z2) {
        lastFoldState = z2;
    }

    public final void setMAudioShareSelectedDevices(ArrayList<m0.i> arrayList) {
        kotlin.jvm.internal.n.g(arrayList, "<set-?>");
        mAudioShareSelectedDevices = arrayList;
    }

    public final void setMCurrMediaType(MutableLiveData<Integer> mutableLiveData) {
        kotlin.jvm.internal.n.g(mutableLiveData, "<set-?>");
        mCurrMediaType = mutableLiveData;
    }

    public final void setMFirstLoadCover(boolean z2) {
        mFirstLoadCover = z2;
    }

    public final void setMLastLocalOrBluetoothDevice(m0.i iVar) {
        mLastLocalOrBluetoothDevice = iVar;
    }

    public final void setMMediaIDForCardCover(String str) {
        mMediaIDForCardCover = str;
    }

    public final void setMMediaIDForCover(String str) {
        mMediaIDForCover = str;
    }

    public final void setMOverAllVolumeBarUserTouching(boolean z2) {
        mOverAllVolumeBarUserTouching = z2;
    }

    public final void setPlayingAdvertisement(MutableLiveData<Boolean> mutableLiveData) {
        kotlin.jvm.internal.n.g(mutableLiveData, "<set-?>");
        isPlayingAdvertisement = mutableLiveData;
    }

    public final void setScanDeviceTimeMap(LinkedHashMap<m0.i, Long> linkedHashMap) {
        kotlin.jvm.internal.n.g(linkedHashMap, "<set-?>");
        scanDeviceTimeMap = linkedHashMap;
    }

    public final void setStartScanDeviceTime(long j2) {
        startScanDeviceTime = j2;
    }

    public final void setSupportAudioShared(Boolean bool) {
        supportAudioShared = bool;
    }

    public final String toStatDeviceType(ArrayList<m0.i> arrayList) {
        kotlin.jvm.internal.n.g(arrayList, "<this>");
        if (arrayList.size() > 1) {
            return "group";
        }
        if (arrayList.size() != 1) {
            return "unknown";
        }
        m0.i iVar = arrayList.get(0);
        kotlin.jvm.internal.n.f(iVar, "get(...)");
        return MiPlayExtentionsKt.getDeviceSubTypeStatName(iVar);
    }

    public final void trackBlueToothConnect(String str, boolean z2) {
        Object next;
        Set<m0.i> setKeySet = blueToothDeviceSelectTime.keySet();
        kotlin.jvm.internal.n.f(setKeySet, "<get-keys>(...)");
        Iterator<T> it = setKeySet.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (kotlin.jvm.internal.n.c(((m0.i) next).j(), str)) {
                    break;
                }
            }
        }
        m0.i iVar = (m0.i) next;
        if (iVar != null) {
            Long l2 = blueToothDeviceSelectTime.get(iVar);
            if (l2 == null) {
                l2 = 0L;
            }
            long jLongValue = l2.longValue();
            if (jLongValue == 0) {
                return;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() - jLongValue;
            if (blueToothDeviceSelectTime.containsKey(iVar)) {
                MiPlayEventTracker.trackBluetoothConnect(jElapsedRealtime, z2, iVar.k().getBluetoothDeviceModel(), String.valueOf(iVar.k().getBleCustomDeviceType()));
                blueToothDeviceSelectTime.remove(iVar);
            }
        }
    }

    public final void trackExposeDevice(m0.i device, int i2, String str) {
        kotlin.jvm.internal.n.g(device, "device");
        MutableLiveData<MediaMetaData> mutableLiveData = mMediaMetaData;
        MediaMetaData value = mutableLiveData.getValue();
        boolean zIsSelectedDevice = MiPlayExtentionsKt.isSelectedDevice(device, value != null ? Integer.valueOf(value.getMediaType()) : null);
        int type = device.k().getType();
        String deviceSubTypeStatName = MiPlayExtentionsKt.getDeviceSubTypeStatName(device);
        MediaMetaData value2 = mutableLiveData.getValue();
        MiPlayEventTracker.trackDeviceExpose(zIsSelectedDevice, type, deviceSubTypeStatName, MiPlayExtentionsKt.hasPlayingInfo(device, value2 != null ? Integer.valueOf(value2.getMediaType()) : null), i2, str);
    }

    public final void trackSelectDevice(m0.i device, boolean z2, String str, String str2, String resCode, ArrayList<m0.i> current) {
        kotlin.jvm.internal.n.g(device, "device");
        kotlin.jvm.internal.n.g(resCode, "resCode");
        kotlin.jvm.internal.n.g(current, "current");
        DeviceInfo deviceInfoK = device.k();
        MiPlayDetailViewModel miPlayDetailViewModel = INSTANCE;
        ArrayList<m0.i> arrayListForecastNextSelectedDevices = miPlayDetailViewModel.forecastNextSelectedDevices(device, z2);
        Bundle extra = device.k().getExtra();
        String string = extra != null ? extra.getString(DeviceInfo.EXTRA_KEY_DEVICE_MAC) : null;
        if (TextUtils.isEmpty(string)) {
            DeviceInfo deviceInfoK2 = device.k();
            kotlin.jvm.internal.n.f(deviceInfoK2, "getDeviceInfo(...)");
            string = MiPlayExtentionsKt.getBluetoothMac(deviceInfoK2);
        }
        MiPlayEventTracker.trackSelectDevice(z2, str2, resCode, deviceInfoK.getType(), MiPlayExtentionsKt.getDeviceSubTypeStatName(device), deviceInfoK.isGroupDevice(), deviceInfoK.isBluetoothHeadset(), deviceInfoK.isTv(), str, HashUtils.SHA1(string), miPlayDetailViewModel.toStatProtocolType(current), miPlayDetailViewModel.toStatDeviceType(current), miPlayDetailViewModel.toStatDeviceId(current), miPlayDetailViewModel.toStatProtocolType(arrayListForecastNextSelectedDevices), miPlayDetailViewModel.toStatDeviceType(arrayListForecastNextSelectedDevices), miPlayDetailViewModel.toStatDeviceId(arrayListForecastNextSelectedDevices));
    }

    public final void updateDeviceListNotCache() {
        List<m0.i> value = mDevices.getValue();
        if (value != null) {
            updateDevicesList$default(INSTANCE, value, false, false, 4, null);
        }
    }

    public final void updateDeviceListNotCache2Connection() {
        List<m0.i> value = mDevices.getValue();
        if (value != null) {
            INSTANCE.updateDevicesList(value, false, true);
        }
    }

    public final void updateOverAllVolumeProgress(int i2) {
        MutableLiveData<Integer> mutableLiveData = mOverAllVolumeProgress;
        Integer value = mutableLiveData.getValue();
        Log.d(TAG, "updateOverAllVolumeProgress progress " + i2 + " oldValue " + value);
        if (value == null || Math.abs(i2 - value.intValue()) >= 10 || i2 == 1000 || i2 == 0) {
            mutableLiveData.setValue(Integer.valueOf(i2));
        }
    }

    private final void setCastMode(int i2, int i3) {
        Log.d(TAG, "onCastModeChange() protocolType:" + i2 + ",mode:" + i3 + ",oldValue:" + castMode.getValue());
        Integer value = castMode.getValue();
        if (value != null && value.intValue() == i3) {
            return;
        }
        castMode.setValue(Integer.valueOf(i3));
    }

    private final int getIcon(m0.i iVar) {
        if (iVar.k().isGroupDevice()) {
            return R.drawable.ic_miplay_speaker_light;
        }
        if (MiPlayExtentionsKt.isCar(iVar)) {
            return R.drawable.ic_miplay_car_light;
        }
        int type = iVar.k().getType();
        if (type != 0) {
            if (type != 1) {
                if (type == 2) {
                    if (iVar.k().isBluetoothHeadset()) {
                        return R.drawable.ic_miplay_earphones_light;
                    }
                    if (MiPlayExtentionsKt.isCar(iVar)) {
                        return R.drawable.ic_miplay_car_light;
                    }
                    if (iVar.k().isBluetoothGlasses()) {
                        return R.drawable.ic_miplay_glasses_light;
                    }
                    if (iVar.k().getBleCustomDeviceType() != 0) {
                        switch (iVar.k().getBleCustomDeviceType()) {
                            case 10:
                                return R.drawable.ic_miplay_default_light;
                            case 11:
                                return R.drawable.ic_miplay_earphones_light;
                            case 12:
                                return R.drawable.ic_miplay_speaker_light;
                            case 13:
                                return R.drawable.ic_miplay_car_light;
                            case 14:
                                return R.drawable.ic_miplay_hearing_aid_light;
                            case 15:
                                return R.drawable.ic_miplay_watch_light;
                            default:
                                return R.drawable.ic_miplay_default_light;
                        }
                    }
                    return R.drawable.ic_miplay_default_light;
                }
                if (type != 3) {
                    return R.drawable.miplay_select_device;
                }
            }
            DeviceInfo deviceInfoK = iVar.k();
            kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
            int miPlayDeviceType = MiPlayExtentionsKt.getMiPlayDeviceType(deviceInfoK);
            if (miPlayDeviceType == 1) {
                return R.drawable.miplay_select_device;
            }
            if (miPlayDeviceType == 2) {
                return R.drawable.ic_miplay_tv_light;
            }
            if (miPlayDeviceType != 3) {
                if (miPlayDeviceType != 4) {
                    switch (miPlayDeviceType) {
                        case 16:
                            break;
                        case 17:
                            return R.drawable.ic_miplay_watch_light;
                        case 18:
                            return R.drawable.ic_miplay_pad_light;
                        case 19:
                            return R.drawable.ic_miplay_speaker_light;
                        default:
                            return R.drawable.ic_miplay_default_light;
                    }
                }
                return R.drawable.ic_miplay_speaker_light;
            }
            return R.drawable.ic_miplay_laptop_light;
        }
        return R.drawable.ic_media_device_default;
    }
}
