package com.android.systemui;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.android.systemui.miplay.R;
import com.miui.miplay.audio.data.DeviceInfo;
import com.miui.miplay.audio.data.MediaMetaData;
import com.xiaomi.onetrack.api.au;
import g1.AbstractC0367f;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.jvm.functions.Function2;
import miui.os.Build;
import miui.systemui.controlcenter.media.MediaPlayerMetaData;
import miui.systemui.coroutines.Dispatchers;
import miuix.animation.property.ViewProperty;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayExtentionsKt {
    public static final String MIPLAY_CAST_DEVICE_TYPE_HEADSET = "headset";
    public static final String MIPLAY_CAST_DEVICE_TYPE_LOCAL = "local";
    public static final String MIPLAY_CAST_DEVICE_TYPE_OTHERS = "others";
    public static final int MIPLAY_DEVICE_TYPE_CAR = 5;
    public static final int MIPLAY_DEVICE_TYPE_PAD = 18;
    public static final int MIPLAY_DEVICE_TYPE_PC = 3;
    public static final int MIPLAY_DEVICE_TYPE_PHONE = 1;
    public static final int MIPLAY_DEVICE_TYPE_SPEAKER = 4;
    public static final int MIPLAY_DEVICE_TYPE_SPEAKER_SCREEN = 16;
    public static final int MIPLAY_DEVICE_TYPE_SURROUND = 19;
    public static final int MIPLAY_DEVICE_TYPE_TV = 2;
    public static final int MIPLAY_DEVICE_TYPE_WATCH = 17;
    public static final String REF_MIUIMUSIC_NOWPLAYING = "miuimusic_nowplaying";
    public static final String REF_NFC = "nfc";
    private static final ViewProperty VIEW_PROPERTY_PROGRESS = new ViewProperty() { // from class: com.android.systemui.MiPlayExtentionsKt$VIEW_PROPERTY_PROGRESS$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(View view) {
            kotlin.jvm.internal.n.g(view, "view");
            if ((view instanceof ProgressBar ? (ProgressBar) view : null) != null) {
                return r1.getProgress();
            }
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(View view, float f2) {
            kotlin.jvm.internal.n.g(view, "view");
            ProgressBar progressBar = view instanceof ProgressBar ? (ProgressBar) view : null;
            if (progressBar == null) {
                return;
            }
            progressBar.setProgress((int) f2);
        }
    };

    /* JADX INFO: renamed from: com.android.systemui.MiPlayExtentionsKt$fetchConnectionState$2, reason: invalid class name */
    @N0.f(c = "com.android.systemui.MiPlayExtentionsKt$fetchConnectionState$2", f = "MiPlayExtentions.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends N0.l implements Function2 {
        final /* synthetic */ m0.i $this_fetchConnectionState;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(m0.i iVar, int i2, L0.d dVar) {
            super(2, dVar);
            this.$this_fetchConnectionState = iVar;
            this.$type = i2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass2(this.$this_fetchConnectionState, this.$type, dVar);
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
            return N0.b.c(this.$this_fetchConnectionState.i(this.$type));
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayExtentionsKt$fetchMediaMetaData$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayExtentionsKt$fetchMediaMetaData$2", f = "MiPlayExtentions.kt", l = {}, m = "invokeSuspend")
    public static final class C02362 extends N0.l implements Function2 {
        final /* synthetic */ m0.i $this_fetchMediaMetaData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02362(m0.i iVar, L0.d dVar) {
            super(2, dVar);
            this.$this_fetchMediaMetaData = iVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02362(this.$this_fetchMediaMetaData, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02362) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            return this.$this_fetchMediaMetaData.n().h();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayExtentionsKt$fetchPlaybackState$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayExtentionsKt$fetchPlaybackState$2", f = "MiPlayExtentions.kt", l = {}, m = "invokeSuspend")
    public static final class C02372 extends N0.l implements Function2 {
        final /* synthetic */ m0.i $this_fetchPlaybackState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02372(m0.i iVar, L0.d dVar) {
            super(2, dVar);
            this.$this_fetchPlaybackState = iVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02372(this.$this_fetchPlaybackState, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02372) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            return N0.b.c(this.$this_fetchPlaybackState.n().j());
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayExtentionsKt$fetchVolume$2, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.MiPlayExtentionsKt$fetchVolume$2", f = "MiPlayExtentions.kt", l = {}, m = "invokeSuspend")
    public static final class C02382 extends N0.l implements Function2 {
        final /* synthetic */ m0.i $this_fetchVolume;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02382(m0.i iVar, L0.d dVar) {
            super(2, dVar);
            this.$this_fetchVolume = iVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02382(this.$this_fetchVolume, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02382) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
            return N0.b.c(this.$this_fetchVolume.o());
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayExtentionsKt$postIfChanged$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(Object obj, Object obj2) {
            return Boolean.valueOf(kotlin.jvm.internal.n.c(obj, obj2));
        }
    }

    public static final String betterArtistAlbum(MediaMetaData mediaMetaData) {
        kotlin.jvm.internal.n.g(mediaMetaData, "<this>");
        if (TextUtils.isEmpty(mediaMetaData.getArtist()) || TextUtils.isEmpty(mediaMetaData.getAlbum())) {
            return !TextUtils.isEmpty(mediaMetaData.getArtist()) ? mediaMetaData.getArtist() : !TextUtils.isEmpty(mediaMetaData.getAlbum()) ? mediaMetaData.getAlbum() : "";
        }
        return mediaMetaData.getArtist() + " - " + mediaMetaData.getAlbum();
    }

    public static final String betterTitle(MediaMetaData mediaMetaData, Context context) {
        kotlin.jvm.internal.n.g(mediaMetaData, "<this>");
        kotlin.jvm.internal.n.g(context, "context");
        return TextUtils.isEmpty(mediaMetaData.getTitle()) ? context.getResources().getString(R.string.miplay_song_title_empty) : mediaMetaData.getTitle();
    }

    public static final Object fetchConnectionState(m0.i iVar, int i2, L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new AnonymousClass2(iVar, i2, null), dVar);
    }

    public static final Object fetchMediaMetaData(m0.i iVar, L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new C02362(iVar, null), dVar);
    }

    public static final Object fetchPlaybackState(m0.i iVar, L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new C02372(iVar, null), dVar);
    }

    public static final Object fetchVolume(m0.i iVar, L0.d dVar) {
        return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new C02382(iVar, null), dVar);
    }

    public static final String getBluetoothMac(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        Bundle extra = deviceInfo.getExtra();
        String string = extra != null ? extra.getString("bluetoothMac", "") : null;
        return string == null ? "" : string;
    }

    public static final String getCastDeviceTypeForNotification(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        int type = iVar.k().getType();
        return type != 0 ? type != 2 ? MIPLAY_CAST_DEVICE_TYPE_OTHERS : (!iVar.k().isBluetoothHeadset() && (iVar.k().getBleCustomDeviceType() == 0 || iVar.k().getBleCustomDeviceType() != 11)) ? MIPLAY_CAST_DEVICE_TYPE_OTHERS : "headset" : MIPLAY_CAST_DEVICE_TYPE_LOCAL;
    }

    public static final String getDeviceSubTypeStatName(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        if (iVar.k().isGroupDevice()) {
            return "stereo";
        }
        int type = iVar.k().getType();
        if (type == 0) {
            return Build.IS_TABLET ? "pad" : au.f2924d;
        }
        if (type != 1) {
            return type != 2 ? "unknown" : isBluetoothTv(iVar) ? "tv" : iVar.k().isBluetoothHeadset() ? "headset" : "speaker";
        }
        DeviceInfo deviceInfoK = iVar.k();
        kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
        return getMiPlayDeviceSubTypeStatName(deviceInfoK);
    }

    public static final int getDeviceTypeName(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        if (iVar.k().isGroupDevice()) {
            return R.string.miplay_device_type_name_speaker;
        }
        if (isCar(iVar)) {
            return R.string.miplay_device_type_name_car;
        }
        int type = iVar.k().getType();
        if (type == 0) {
            return R.string.miplay_device_type_name_phone;
        }
        if (type != 1) {
            if (type == 2) {
                if (isBluetoothTv(iVar)) {
                    return R.string.miplay_device_type_name_tv;
                }
                if (iVar.k().isBluetoothHeadset()) {
                    return R.string.miplay_device_type_name_headSet;
                }
                if (iVar.k().isBluetoothGlasses()) {
                    return R.string.miplay_device_type_name_glasses;
                }
                if (iVar.k().getBleCustomDeviceType() == 0) {
                    return R.string.miplay_device_type_name_default;
                }
                switch (iVar.k().getBleCustomDeviceType()) {
                    case 10:
                        return R.string.miplay_device_type_name_default;
                    case 11:
                        return R.string.miplay_device_type_name_headSet;
                    case 12:
                        return R.string.miplay_device_type_name_speaker;
                    case 13:
                        return R.string.miplay_device_type_name_car;
                    case 14:
                        return R.string.miplay_device_type_name_hearing_aid;
                    case 15:
                        return R.string.miplay_device_type_name_watch;
                    default:
                        return R.string.miplay_device_type_name_default;
                }
            }
            if (type != 3) {
                return R.drawable.ic_miplay_default;
            }
        }
        DeviceInfo deviceInfoK = iVar.k();
        kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
        int miPlayDeviceType = getMiPlayDeviceType(deviceInfoK);
        if (miPlayDeviceType == 1) {
            return R.string.miplay_device_type_name_phone;
        }
        if (miPlayDeviceType == 2) {
            return R.string.miplay_device_type_name_tv;
        }
        if (miPlayDeviceType == 3) {
            return R.string.miplay_device_type_name_laptop;
        }
        if (miPlayDeviceType != 4) {
            if (miPlayDeviceType == 5) {
                return R.string.miplay_device_type_name_car;
            }
            switch (miPlayDeviceType) {
                case 16:
                    break;
                case 17:
                    return R.string.miplay_device_type_name_watch;
                case 18:
                    return R.string.miplay_device_type_name_pad;
                case 19:
                    return R.string.miplay_device_type_name_surround;
                default:
                    return R.string.miplay_device_type_name_default;
            }
        }
        return R.string.miplay_device_type_name_speaker;
    }

    public static final String getFullName(m0.i iVar, Context context) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        kotlin.jvm.internal.n.g(context, "context");
        if (isLocalSpeaker(iVar)) {
            String string = context.getString(R.string.deice_local);
            kotlin.jvm.internal.n.d(string);
            return string;
        }
        if (TextUtils.isEmpty(getRoomName(iVar))) {
            String name = iVar.k().getName();
            kotlin.jvm.internal.n.d(name);
            return name;
        }
        String string2 = context.getString(R.string.miplay_device_full_name, getRoomName(iVar), iVar.k().getName());
        kotlin.jvm.internal.n.d(string2);
        return string2;
    }

    public static final String getGroupStereoId(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        Bundle extra = deviceInfo.getExtra();
        String string = extra != null ? extra.getString(DeviceInfo.EXTRA_KEY_GROUP_ID, "") : null;
        return string == null ? "" : string;
    }

    public static final String getMacMd5(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        String strMD5 = HashUtils.MD5(getBluetoothMac(deviceInfo));
        kotlin.jvm.internal.n.f(strMD5, "MD5(...)");
        return strMD5;
    }

    public static final String getMediaID(MediaMetaData mediaMetaData, Integer num) {
        kotlin.jvm.internal.n.g(mediaMetaData, "<this>");
        return MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(num != null ? num.intValue() : 0)) ? mediaMetaData.getMediaId() : mediaMetaData.getTVId();
    }

    public static /* synthetic */ String getMediaID$default(MediaMetaData mediaMetaData, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return getMediaID(mediaMetaData, num);
    }

    public static final int getMiPlayDeviceIcon(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        int miPlayDeviceType = getMiPlayDeviceType(deviceInfo);
        if (miPlayDeviceType == 1) {
            return R.drawable.ic_miplay_phone;
        }
        if (miPlayDeviceType == 2) {
            return R.drawable.ic_miplay_tv;
        }
        if (miPlayDeviceType == 3) {
            return R.drawable.ic_miplay_laptop;
        }
        if (miPlayDeviceType != 4) {
            if (miPlayDeviceType == 5) {
                return R.drawable.ic_miplay_car;
            }
            switch (miPlayDeviceType) {
                case 16:
                    break;
                case 17:
                    return R.drawable.ic_miplay_watch;
                case 18:
                    return R.drawable.ic_miplay_pad;
                case 19:
                    return R.drawable.ic_miplay_surround;
                default:
                    return R.drawable.ic_miplay_default;
            }
        }
        return R.drawable.ic_miplay_speaker;
    }

    public static final String getMiPlayDeviceSubTypeStatName(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        int miPlayDeviceType = getMiPlayDeviceType(deviceInfo);
        if (miPlayDeviceType == 1) {
            return au.f2924d;
        }
        if (miPlayDeviceType == 2) {
            return "tv";
        }
        if (miPlayDeviceType == 3) {
            return "pc";
        }
        if (miPlayDeviceType == 4) {
            return "speaker";
        }
        switch (miPlayDeviceType) {
        }
        return "speaker";
    }

    public static final int getMiPlayDeviceType(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        Bundle extra = deviceInfo.getExtra();
        if (extra != null) {
            return extra.getInt(DeviceInfo.EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0);
        }
        return 0;
    }

    public static final String getRoomName(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        Bundle extra = iVar.k().getExtra();
        if (extra != null) {
            return extra.getString(DeviceInfo.EXTRA_KEY_ROOM_NAME);
        }
        return null;
    }

    public static final ViewProperty getVIEW_PROPERTY_PROGRESS() {
        return VIEW_PROPERTY_PROGRESS;
    }

    public static final boolean hasPlayingInfo(m0.i iVar, Integer num) {
        MediaMetaData value;
        kotlin.jvm.internal.n.g(iVar, "<this>");
        MutableLiveData<MediaMetaData> liveData = MiPlayDeviceMetaDataCache.INSTANCE.getLiveData(iVar);
        String title = (liveData == null || (value = liveData.getValue()) == null) ? null : value.getTitle();
        MutableLiveData<Integer> liveData2 = MiPlayDevicePlaybackStateCache.INSTANCE.getLiveData(iVar);
        Integer value2 = liveData2 != null ? liveData2.getValue() : null;
        int iIntValue = num != null ? num.intValue() : 0;
        boolean z2 = iVar.m(iIntValue) == 3 || iVar.m(iIntValue) == 1;
        if (TextUtils.isEmpty(title) || z2 || iVar.k().getType() == 0) {
            return false;
        }
        return (value2 != null && value2.intValue() == 3) || (value2 != null && value2.intValue() == 2);
    }

    public static /* synthetic */ boolean hasPlayingInfo$default(m0.i iVar, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return hasPlayingInfo(iVar, num);
    }

    public static final boolean isBluetoothDevice(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getType() == 2;
    }

    public static final boolean isBluetoothGlasses(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        Bundle extra = iVar.k().getExtra();
        if (extra != null) {
            return extra.getBoolean(DeviceInfo.EXTRA_KEY_IS_BLUETOOTH_GLASSES, false);
        }
        return false;
    }

    public static final boolean isBluetoothHeadset(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        Bundle extra = iVar.k().getExtra();
        if (extra != null) {
            return extra.getBoolean(DeviceInfo.EXTRA_KEY_IS_BLUETOOTH_HEADSET, false);
        }
        return false;
    }

    public static final boolean isBluetoothTv(DeviceInfo deviceInfo) {
        BluetoothClass bluetoothClass;
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        Bundle extra = deviceInfo.getExtra();
        return (extra == null || (bluetoothClass = (BluetoothClass) extra.getParcelable(DeviceInfo.EXTRA_KEY_BLUETOOTH_CLASS)) == null || 272 != bluetoothClass.getDeviceClass()) ? false : true;
    }

    public static final String isCacheFound(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        Bundle extra = iVar.k().getExtra();
        Object obj = extra != null ? extra.get(DeviceInfo.EXTRA_KEY_IS_CACHE_FOUND) : null;
        return kotlin.jvm.internal.n.c(obj, Boolean.TRUE) ? "1" : kotlin.jvm.internal.n.c(obj, Boolean.FALSE) ? "0" : MiPlayEventsKt.DEVICE_IF_CACHE_NULL;
    }

    public static final boolean isCar(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().isCar() || iVar.k().isMiCar();
    }

    public static final boolean isDeviceConnecting(m0.i iVar, Integer num) {
        H0.i value;
        H0.i value2;
        kotlin.jvm.internal.n.g(iVar, "<this>");
        int iIntValue = num != null ? num.intValue() : 0;
        Integer num2 = null;
        if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(iIntValue))) {
            MutableLiveData<H0.i> liveData = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData != null && (value2 = liveData.getValue()) != null) {
                num2 = (Integer) value2.d();
            }
        } else {
            MutableLiveData<H0.i> liveData2 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData2 != null && (value = liveData2.getValue()) != null) {
                num2 = (Integer) value.e();
            }
        }
        int iIntValue2 = num2 != null ? num2.intValue() : iVar.i(iIntValue);
        int iM = iVar.m(iIntValue);
        return ((iM != 3 && iM != 1) || iIntValue2 == 1 || isLocalSpeaker(iVar)) ? false : true;
    }

    public static /* synthetic */ boolean isDeviceConnecting$default(m0.i iVar, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return isDeviceConnecting(iVar, num);
    }

    public static final boolean isDeviceDisconnecting(m0.i iVar, Integer num) {
        H0.i value;
        H0.i value2;
        kotlin.jvm.internal.n.g(iVar, "<this>");
        int iIntValue = num != null ? num.intValue() : 0;
        Integer num2 = null;
        if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(iIntValue))) {
            MutableLiveData<H0.i> liveData = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData != null && (value2 = liveData.getValue()) != null) {
                num2 = (Integer) value2.d();
            }
        } else {
            MutableLiveData<H0.i> liveData2 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData2 != null && (value = liveData2.getValue()) != null) {
                num2 = (Integer) value.e();
            }
        }
        int iIntValue2 = num2 != null ? num2.intValue() : iVar.i(iIntValue);
        int iM = iVar.m(iIntValue);
        return (iM == 0 || iM == 2) && iIntValue2 == 4;
    }

    public static /* synthetic */ boolean isDeviceDisconnecting$default(m0.i iVar, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return isDeviceDisconnecting(iVar, num);
    }

    public static final boolean isDeviceLoading(m0.i iVar, Integer num) {
        H0.i value;
        H0.i value2;
        kotlin.jvm.internal.n.g(iVar, "<this>");
        Integer num2 = null;
        if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(num != null ? num.intValue() : 0))) {
            MutableLiveData<H0.i> liveData = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData != null && (value2 = liveData.getValue()) != null) {
                num2 = (Integer) value2.d();
            }
        } else {
            MutableLiveData<H0.i> liveData2 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData2 != null && (value = liveData2.getValue()) != null) {
                num2 = (Integer) value.e();
            }
        }
        int iIntValue = num2 != null ? num2.intValue() : iVar.h();
        return iIntValue == 3 || iIntValue == 4;
    }

    public static /* synthetic */ boolean isDeviceLoading$default(m0.i iVar, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return isDeviceLoading(iVar, num);
    }

    public static final boolean isForeignBlueToothDevice(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getType() == 2 && Build.IS_INTERNATIONAL_BUILD;
    }

    public static final boolean isForeignCastDevice(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getType() == 3;
    }

    public static final boolean isGroupStereoDevice(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        Bundle extra = deviceInfo.getExtra();
        if (extra != null) {
            return extra.getBoolean(DeviceInfo.EXTRA_KEY_IS_GROUP_DEVICE, false);
        }
        return false;
    }

    public static final boolean isHeadset(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return isBluetoothHeadset(iVar) || (isThirdPartyHeadset(iVar) && !isBluetoothGlasses(iVar));
    }

    public static final boolean isLocalSpeaker(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getType() == 0;
    }

    public static final boolean isMiPlayDevice(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getType() == 1;
    }

    public static final boolean isMiPlayTv(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        return getMiPlayDeviceType(deviceInfo) == 2;
    }

    public static final boolean isSelectedDevice(m0.i iVar, Integer num) {
        H0.i value;
        H0.i value2;
        kotlin.jvm.internal.n.g(iVar, "<this>");
        int iIntValue = num != null ? num.intValue() : 0;
        Integer num2 = null;
        if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(iIntValue))) {
            MutableLiveData<H0.i> liveData = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData != null && (value2 = liveData.getValue()) != null) {
                num2 = (Integer) value2.d();
            }
        } else {
            MutableLiveData<H0.i> liveData2 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(iVar);
            if (liveData2 != null && (value = liveData2.getValue()) != null) {
                num2 = (Integer) value.e();
            }
        }
        int iIntValue2 = num2 != null ? num2.intValue() : iVar.h();
        int iM = iVar.m(iIntValue);
        return (iM == 3 || iM == 1) && iIntValue2 == 1;
    }

    public static /* synthetic */ boolean isSelectedDevice$default(m0.i iVar, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return isSelectedDevice(iVar, num);
    }

    public static final boolean isTelevision(DeviceInfo deviceInfo) {
        kotlin.jvm.internal.n.g(deviceInfo, "<this>");
        return isBluetoothTv(deviceInfo) || isMiPlayTv(deviceInfo);
    }

    public static final boolean isThirdPartyHeadset(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().getBleCustomDeviceType() == 11;
    }

    public static final boolean isTv(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        return iVar.k().isTv();
    }

    public static final void log(String tag, String msg) {
        kotlin.jvm.internal.n.g(tag, "tag");
        kotlin.jvm.internal.n.g(msg, "msg");
        if (Log.isLoggable("miplay", 3)) {
            Log.d(tag, msg);
        }
    }

    public static final <T> void postIfChanged(MutableLiveData<T> mutableLiveData, T t2, Function2 comparator) {
        kotlin.jvm.internal.n.g(mutableLiveData, "<this>");
        kotlin.jvm.internal.n.g(comparator, "comparator");
        if (((Boolean) comparator.invoke(mutableLiveData.getValue(), t2)).booleanValue()) {
            return;
        }
        mutableLiveData.postValue(t2);
    }

    public static /* synthetic */ void postIfChanged$default(MutableLiveData mutableLiveData, Object obj, Function2 function2, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        postIfChanged(mutableLiveData, obj, function2);
    }

    public static final void setImageDrawableOnce(ImageView imageView, Drawable drawable) {
        kotlin.jvm.internal.n.g(imageView, "<this>");
        if (imageView.getDrawable() == null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public static final void sortByPriority(ArrayList<m0.i> arrayList) {
        kotlin.jvm.internal.n.g(arrayList, "<this>");
        if (arrayList.size() > 1) {
            I0.q.r(arrayList, new Comparator() { // from class: com.android.systemui.MiPlayExtentionsKt$sortByPriority$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    return K0.a.a(Integer.valueOf(((m0.i) t2).k().getPriority()), Integer.valueOf(((m0.i) t3).k().getPriority()));
                }
            });
        }
    }

    public static final <T> MediatorLiveData<T> toMediator(MutableLiveData<T> mutableLiveData) {
        kotlin.jvm.internal.n.g(mutableLiveData, "<this>");
        final MediatorLiveData<T> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(mutableLiveData, new Observer() { // from class: com.android.systemui.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MiPlayExtentionsKt.toMediator$lambda$2$lambda$1(mediatorLiveData, obj);
            }
        });
        return mediatorLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toMediator$lambda$2$lambda$1(MediatorLiveData this_apply, Object obj) {
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        this_apply.setValue(obj);
    }

    public static /* synthetic */ String getMediaID$default(MediaPlayerMetaData mediaPlayerMetaData, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = 0;
        }
        return getMediaID(mediaPlayerMetaData, num);
    }

    public static final boolean isBluetoothTv(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        DeviceInfo deviceInfoK = iVar.k();
        kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
        return isBluetoothTv(deviceInfoK);
    }

    public static final boolean isMiPlayTv(m0.i iVar) {
        kotlin.jvm.internal.n.g(iVar, "<this>");
        DeviceInfo deviceInfoK = iVar.k();
        kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
        return isMiPlayTv(deviceInfoK);
    }

    public static final String getMediaID(MediaPlayerMetaData mediaPlayerMetaData, Integer num) {
        kotlin.jvm.internal.n.g(mediaPlayerMetaData, "<this>");
        if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(num != null ? num.intValue() : 0))) {
            return mediaPlayerMetaData.getMediaId();
        }
        return mediaPlayerMetaData.getTvId();
    }
}
