package com.android.systemui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.MiPlayDeviceVolumeController;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.folme.ViewAnimExtentionsKt;
import com.android.systemui.miplay.R;
import com.miui.miplay.audio.data.DeviceInfo;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import m0.C0465C;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.concurrency.ConcurrencyModule;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;
import systemui.plugin.eventtracking.utils.RPCEventTracker;

/* JADX INFO: loaded from: classes.dex */
public class QSControlMiPlayDetailContent extends LinearLayout implements LifecycleOwner {
    private static final String ACTION_MIPLAY_SHOW_STATE_CHANGED = "android.intent.action.MIPLAY_SHOW_STATE_CHANGED";
    private static final String EXTRA_MIPLAY_SHOW_STATE = "extra_miplay_show_state";
    public static final int MAX_SHOWN_ITEM_COUNT_FLIP_EXTERNAL = 3;
    public static final int MAX_SHOWN_ITEM_COUNT_FLIP_INNER = 5;
    public static final int MAX_SHOWN_ITEM_COUNT_LANDSCAPE = 6;
    public static final int MAX_SHOWN_ITEM_COUNT_PORTRAIT = 4;
    private static final String TAG = "QSControlMiPlayDetailContent";
    private final H0.d mAnim$delegate;
    private final AnimConfig mAnimConfig;
    private final HashSet<m0.i> mExposedDevices;
    private boolean mFirstRefresh;
    private final H0.d mHeader$delegate;
    private boolean mIsDetailShowing;
    private final LifecycleRegistry mLifecycle;
    private ArrayList<ListItem> mListItems;
    private boolean mPendingRefreshDeviceList;
    private final H0.d mRecycler$delegate;
    private int mTargetHeight;
    private int maxHeight;
    public static final Companion Companion = new Companion(null);
    private static final float ANIM_DURING = 400.0f;
    private static final AnimConfig sSpeedConfig = new AnimConfig().setFromSpeed(0.0f).setEase(0, ANIM_DURING, 0.8f, 0.6666f);

    public final class Adapter2 extends ListAdapter<ListItem, RecyclerView.ViewHolder> {
        private MiPlayDeviceVolumeController deviceVolumeController;
        private int mediaType;

        /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$Adapter2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
            public AnonymousClass1() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((MediaMetaData) obj);
                return H0.s.f314a;
            }

            public final void invoke(MediaMetaData mediaMetaData) {
                Adapter2.this.mediaType = mediaMetaData != null ? mediaMetaData.getMediaType() : 0;
            }
        }

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ListType.values().length];
                try {
                    iArr[ListType.TYPE_DEVICE_LOCAL_SPEAKER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ListType.TYPE_DEVICE_HEADSET.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ListType.TYPE_DEVICE_OTHER.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Adapter2() {
            super(AudioDeviceCallback.INSTANCE);
            MiPlayExtentionsKt.toMediator(MiPlayDetailViewModel.INSTANCE.getMMediaMetaData()).observe(QSControlMiPlayDetailContent.this, new QSControlMiPlayDetailContent$sam$androidx_lifecycle_Observer$0(new AnonymousClass1()));
        }

        private final int getIcon(m0.i iVar) {
            if (iVar.k().isGroupDevice()) {
                return R.drawable.ic_miplay_speaker;
            }
            if (MiPlayExtentionsKt.isCar(iVar)) {
                return R.drawable.ic_miplay_car;
            }
            int type = iVar.k().getType();
            if (type == 0) {
                return R.drawable.ic_miplay_phone;
            }
            if (type != 1) {
                if (type == 2) {
                    if (MiPlayExtentionsKt.isBluetoothTv(iVar)) {
                        return R.drawable.ic_miplay_tv;
                    }
                    if (iVar.k().isBluetoothHeadset()) {
                        return R.drawable.ic_miplay_headset;
                    }
                    if (iVar.k().isBluetoothGlasses()) {
                        return R.drawable.ic_miplay_glasses;
                    }
                    if (iVar.k().getBleCustomDeviceType() == 0) {
                        return R.drawable.ic_miplay_default;
                    }
                    switch (iVar.k().getBleCustomDeviceType()) {
                        case 10:
                            return R.drawable.ic_miplay_default;
                        case 11:
                            return R.drawable.ic_miplay_headset;
                        case 12:
                            return R.drawable.ic_miplay_speaker;
                        case 13:
                            return R.drawable.ic_miplay_car;
                        case 14:
                            return R.drawable.ic_miplay_hearing_aid;
                        case 15:
                            return R.drawable.ic_miplay_watch;
                        default:
                            return R.drawable.ic_miplay_default;
                    }
                }
                if (type != 3) {
                    return R.drawable.ic_miplay_default;
                }
            }
            DeviceInfo deviceInfoK = iVar.k();
            kotlin.jvm.internal.n.f(deviceInfoK, "getDeviceInfo(...)");
            return MiPlayExtentionsKt.getMiPlayDeviceIcon(deviceInfoK);
        }

        private final boolean isVolumeBarEnabled(m0.i iVar) {
            return !iVar.k().isAudioSharing() || iVar.k().supportAbsoluteVolume();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x004d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final void onBindViewHolder$lambda$4(com.android.systemui.QSControlMiPlayDetailContent.ListItem r5, com.android.systemui.QSControlMiPlayDetailContent.Adapter2 r6, m0.i r7, android.view.View r8) {
            /*
                java.lang.String r8 = "this$0"
                kotlin.jvm.internal.n.g(r6, r8)
                java.lang.String r8 = "$device"
                kotlin.jvm.internal.n.g(r7, r8)
                com.android.systemui.QSControlMiPlayDetailContent$DeviceListItem r5 = (com.android.systemui.QSControlMiPlayDetailContent.DeviceListItem) r5
                m0.i r8 = r5.getDevice()
                int r0 = r6.mediaType
                int r8 = r8.m(r0)
                com.miui.miplay.audio.data.DeviceInfo r0 = r7.k()
                java.lang.String r0 = r0.getName()
                int r1 = r6.mediaType
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "item onClick deviceName:"
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = ",:selectStatus = "
                r2.append(r0)
                r2.append(r8)
                java.lang.String r0 = ",mediaType="
                r2.append(r0)
                r2.append(r1)
                java.lang.String r0 = r2.toString()
                java.lang.String r1 = "QSControlMiPlayDetailContent"
                android.util.Log.d(r1, r0)
                r0 = 3
                if (r8 == r0) goto L4d
                r0 = 1
                if (r8 == r0) goto L4d
                goto L4e
            L4d:
                r0 = 0
            L4e:
                if (r0 == 0) goto L87
                com.android.systemui.MiPlayDetailViewModel r8 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
                android.util.ArrayMap r1 = r8.getDeviceSelectTime()
                m0.i r2 = r5.getDevice()
                java.lang.String r2 = r2.j()
                long r3 = android.os.SystemClock.elapsedRealtime()
                java.lang.Long r3 = java.lang.Long.valueOf(r3)
                r1.put(r2, r3)
                com.miui.miplay.audio.data.DeviceInfo r7 = r7.k()
                int r7 = r7.getType()
                r1 = 2
                if (r7 != r1) goto L87
                android.util.ArrayMap r7 = r8.getBlueToothDeviceSelectTime()
                m0.i r8 = r5.getDevice()
                long r1 = android.os.SystemClock.elapsedRealtime()
                java.lang.Long r1 = java.lang.Long.valueOf(r1)
                r7.put(r8, r1)
            L87:
                m0.i r5 = r5.getDevice()
                r6.selectDevice(r5, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailContent.Adapter2.onBindViewHolder$lambda$4(com.android.systemui.QSControlMiPlayDetailContent$ListItem, com.android.systemui.QSControlMiPlayDetailContent$Adapter2, m0.i, android.view.View):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int progressToVolume(int i2) {
            MiPlayDeviceVolumeController.Companion companion = MiPlayDeviceVolumeController.Companion;
            return MiPlayDetailViewModelKt.progressToVolume(i2, companion.getMIPLAY_DEVICE_VOLUME_MIN(), companion.getMIPLAY_DEVICE_VOLUME_MAX());
        }

        private final void selectDevice(m0.i iVar, boolean z2) {
            int iE;
            String str;
            MediaMetaData value;
            if (!z2) {
                iE = iVar.e();
                Log.d(QSControlMiPlayDetailContent.TAG, "onBindViewHolder(): cancelSelectDevice res =" + iE + " ");
                str = MiPlayEventsKt.VALUE_CANCEL_SELECT;
            } else {
                if (InvisibleModeUtil.checkInvisibleMode(QSControlMiPlayDetailContent.this.getContext(), MiPlayExtentionsKt.isMiPlayDevice(iVar))) {
                    if (InvisibleModeUtil.showInvisibleModeHint()) {
                        return;
                    }
                    AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailContent$Adapter2$selectDevice$1(null), 3, null);
                    return;
                }
                MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                MediaMetaData value2 = miPlayDetailViewModel.getMMediaMetaData().getValue();
                Integer value3 = miPlayDetailViewModel.getCastMode().getValue();
                int cast_mode_success = MiplayConstant.INSTANCE.getCAST_MODE_SUCCESS();
                if (value3 != null && value3.intValue() == cast_mode_success && (value = miPlayDetailViewModel.getMMediaMetaData().getValue()) != null && value.getMediaType() == 1) {
                    Log.d(QSControlMiPlayDetailContent.TAG, "请退出电视投屏后重试");
                    Toast.makeText(QSControlMiPlayDetailContent.this.getContext(), R.string.miplay_tv_cast_retry_toast, 0).show();
                    return;
                }
                if (value2 != null) {
                    iE = iVar.v(miPlayDetailViewModel.getMMiPlayRef().refToStatType(), value2);
                    Log.d(QSControlMiPlayDetailContent.TAG, "onBindViewHolder(): selectDeviceWithMetaData res =" + iE + " ");
                } else {
                    iE = iVar.w(miPlayDetailViewModel.getMMiPlayRef().refToStatType());
                    Log.d(QSControlMiPlayDetailContent.TAG, "onBindViewHolder(): selectDeviceWithType res =" + iE + " ");
                }
                str = MiPlayEventsKt.VALUE_SELECT;
            }
            RPCEventTracker rPCEventTracker = RPCEventTracker.INSTANCE;
            String str2 = "QSControlMiPlayDetailContent_mipLay_select_track" + iVar.j() + str;
            Object objClone = MiPlayDetailViewModel.INSTANCE.getCurrentSelectedDevice().clone();
            kotlin.jvm.internal.n.e(objClone, "null cannot be cast to non-null type java.util.ArrayList<*>{ kotlin.collections.TypeAliasesKt.ArrayList<*> }");
            rPCEventTracker.start(str2, 5000L, (ArrayList) objClone, new QSControlMiPlayDetailContent$Adapter2$selectDevice$5(iVar, z2, iE));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int volumeToProgress(int i2) {
            MiPlayDeviceVolumeController.Companion companion = MiPlayDeviceVolumeController.Companion;
            return MiPlayDetailViewModelKt.volumeToProgress(i2, companion.getMIPLAY_DEVICE_VOLUME_MIN(), companion.getMIPLAY_DEVICE_VOLUME_MAX());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            return getItem(i2).getType().ordinal();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
            H0.s sVar;
            MiPlayDeviceVolumeController miPlayDeviceVolumeController;
            MutableLiveData<Integer> volumeLiveData;
            Integer value;
            DeviceInfo deviceInfoK;
            MutableLiveData<Integer> volumeLiveData2;
            MediatorLiveData mediator;
            MiPlayDeviceVolumeController miPlayDeviceVolumeController2;
            MutableLiveData<Integer> volumeLiveData3;
            MediatorLiveData mediator2;
            MediatorLiveData mediator3;
            MediatorLiveData mediator4;
            kotlin.jvm.internal.n.g(viewHolder, "viewHolder");
            final ListItem item = getItem(i2);
            if (!(viewHolder instanceof DeviceItemHolder) || !(item instanceof DeviceListItem)) {
                viewHolder.itemView.setClickable(false);
                return;
            }
            DeviceListItem deviceListItem = (DeviceListItem) item;
            final m0.i device = deviceListItem.getDevice();
            QSControlMiPlayDetailContent qSControlMiPlayDetailContent = QSControlMiPlayDetailContent.this;
            final DeviceItemHolder deviceItemHolder = (DeviceItemHolder) viewHolder;
            m0.i currDevice = deviceItemHolder.getCurrDevice();
            if (currDevice != null) {
                MutableLiveData<MediaMetaData> liveData = MiPlayDeviceMetaDataCache.INSTANCE.getLiveData(currDevice);
                if (liveData != null) {
                    liveData.removeObserver(deviceItemHolder.getMetaDataObserver());
                }
                MutableLiveData<Integer> liveData2 = MiPlayDevicePlaybackStateCache.INSTANCE.getLiveData(currDevice);
                if (liveData2 != null) {
                    liveData2.removeObserver(deviceItemHolder.getPlaybackStateObserver());
                }
                MutableLiveData<H0.i> liveData3 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(currDevice);
                if (liveData3 != null) {
                    liveData3.removeObserver(deviceItemHolder.getConnectionStateObserver());
                }
            }
            deviceItemHolder.setCurrDevice(device);
            MutableLiveData<MediaMetaData> liveData4 = MiPlayDeviceMetaDataCache.INSTANCE.getLiveData(device);
            if (liveData4 != null && (mediator4 = MiPlayExtentionsKt.toMediator(liveData4)) != null) {
                mediator4.observe(qSControlMiPlayDetailContent, deviceItemHolder.getMetaDataObserver());
            }
            MutableLiveData<Integer> liveData5 = MiPlayDevicePlaybackStateCache.INSTANCE.getLiveData(device);
            if (liveData5 != null && (mediator3 = MiPlayExtentionsKt.toMediator(liveData5)) != null) {
                mediator3.observe(qSControlMiPlayDetailContent, deviceItemHolder.getPlaybackStateObserver());
            }
            MutableLiveData<H0.i> liveData6 = MiPlayDeviceConnectionStateCache.INSTANCE.getLiveData(device);
            if (liveData6 != null && (mediator2 = MiPlayExtentionsKt.toMediator(liveData6)) != null) {
                mediator2.observe(qSControlMiPlayDetailContent, deviceItemHolder.getConnectionStateObserver());
            }
            TextView title = deviceItemHolder.getTitle();
            Context context = qSControlMiPlayDetailContent.getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            title.setText(MiPlayExtentionsKt.getFullName(device, context));
            Bitmap icon = device.k().getIcon();
            if (icon != null) {
                deviceItemHolder.getIcon().setImageBitmap(icon);
                sVar = H0.s.f314a;
            } else {
                sVar = null;
            }
            if (sVar == null) {
                deviceItemHolder.getIcon().setImageResource(getIcon(device));
            }
            Context context2 = QSControlMiPlayDetailContent.this.getContext();
            kotlin.jvm.internal.n.f(context2, "getContext(...)");
            if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context2)) {
                View itemView = viewHolder.itemView;
                kotlin.jvm.internal.n.f(itemView, "itemView");
                MiBlurCompat.setMiViewBlurModeCompat(itemView, 1);
                View itemView2 = viewHolder.itemView;
                kotlin.jvm.internal.n.f(itemView2, "itemView");
                MiBlurCompat.setMiBackgroundBlendColors(itemView2, MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_LIST_BLEND_COLORS());
                viewHolder.itemView.setBackgroundResource(R.drawable.selector_qs_control_miplay_device_item_bg_blur);
            } else {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                View itemView3 = viewHolder.itemView;
                kotlin.jvm.internal.n.f(itemView3, "itemView");
                commonUtils.clearMiBlurBlendEffect(itemView3);
                viewHolder.itemView.setBackgroundResource(R.drawable.selector_qs_control_miplay_device_item_bg);
            }
            deviceItemHolder.updateSelectedState();
            viewHolder.itemView.setContentDescription(QSControlMiPlayDetailContent.this.getContext().getString(R.string.miplay_device_type) + " " + QSControlMiPlayDetailContent.this.getContext().getString(MiPlayExtentionsKt.getDeviceTypeName(device)) + " " + ((Object) deviceItemHolder.getTitle().getText()));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.B
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QSControlMiPlayDetailContent.Adapter2.onBindViewHolder$lambda$4(item, this, device, view);
                }
            });
            QSControlMiPlayDetailContent qSControlMiPlayDetailContent2 = QSControlMiPlayDetailContent.this;
            if (!deviceListItem.getShowVolumeBar()) {
                m0.i currDevice2 = deviceItemHolder.getCurrDevice();
                if (currDevice2 != null && (miPlayDeviceVolumeController = MiPlayOverallVolumeController.INSTANCE.getDeviceControllers().get(currDevice2)) != null && (volumeLiveData = miPlayDeviceVolumeController.getVolumeLiveData()) != null) {
                    volumeLiveData.removeObserver(deviceItemHolder.getVolumeObserver());
                }
                deviceItemHolder.getVolumeBar().setVisibility(8);
                return;
            }
            deviceItemHolder.getVolumeBar().setVisibility(0);
            deviceItemHolder.getVolumeBar().refreshDrawableState();
            deviceItemHolder.getVolumeBar().setEnabled(isVolumeBarEnabled(device));
            m0.i currDevice3 = deviceItemHolder.getCurrDevice();
            if (currDevice3 != null && (miPlayDeviceVolumeController2 = MiPlayOverallVolumeController.INSTANCE.getDeviceControllers().get(currDevice3)) != null && (volumeLiveData3 = miPlayDeviceVolumeController2.getVolumeLiveData()) != null) {
                volumeLiveData3.removeObserver(deviceItemHolder.getVolumeObserver());
            }
            MiPlayOverallVolumeController miPlayOverallVolumeController = MiPlayOverallVolumeController.INSTANCE;
            MiPlayDeviceVolumeController miPlayDeviceVolumeController3 = miPlayOverallVolumeController.getDeviceControllers().get(device);
            if (miPlayDeviceVolumeController3 != null && (volumeLiveData2 = miPlayDeviceVolumeController3.getVolumeLiveData()) != null && (mediator = MiPlayExtentionsKt.toMediator(volumeLiveData2)) != null) {
                mediator.observe(qSControlMiPlayDetailContent2, deviceItemHolder.getVolumeObserver());
            }
            MiPlayDeviceVolumeBar.INSTANCE.getMiPlayDeviceVolumeBarMap().put(device, deviceItemHolder.getVolumeBar());
            deviceItemHolder.getVolumeBar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.QSControlMiPlayDetailContent$Adapter2$onBindViewHolder$3$2
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i3, boolean z2) {
                    DeviceInfo deviceInfoK2;
                    if (z2) {
                        deviceItemHolder.getFolmeAnim().setTo(MiPlayExtentionsKt.getVIEW_PROPERTY_PROGRESS(), Float.valueOf(i3));
                        MiPlayDeviceVolumeCache miPlayDeviceVolumeCache = MiPlayDeviceVolumeCache.INSTANCE;
                        miPlayDeviceVolumeCache.putValue(device, Integer.valueOf(this.progressToVolume(i3)));
                        if (device.k().isAudioSharing()) {
                            MiPlayDeviceVolumeController miPlayDeviceVolumeController4 = null;
                            if (this.deviceVolumeController == null) {
                                this.deviceVolumeController = new MiPlayDeviceVolumeController(device);
                            } else {
                                MiPlayDeviceVolumeController miPlayDeviceVolumeController5 = this.deviceVolumeController;
                                if (miPlayDeviceVolumeController5 == null) {
                                    kotlin.jvm.internal.n.w("deviceVolumeController");
                                    miPlayDeviceVolumeController5 = null;
                                }
                                miPlayDeviceVolumeController5.setDevice(device);
                            }
                            MiPlayDeviceVolumeController miPlayDeviceVolumeController6 = this.deviceVolumeController;
                            if (miPlayDeviceVolumeController6 == null) {
                                kotlin.jvm.internal.n.w("deviceVolumeController");
                            } else {
                                miPlayDeviceVolumeController4 = miPlayDeviceVolumeController6;
                            }
                            miPlayDeviceVolumeController4.doSetVolume(this.progressToVolume(i3));
                            return;
                        }
                        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                        miPlayDetailViewModel.doUpdateDeviceVolume(device, i3);
                        Integer volume = MiPlayOverallVolumeController.INSTANCE.getVolume();
                        if (volume != null) {
                            QSControlMiPlayDetailContent.DeviceItemHolder deviceItemHolder2 = deviceItemHolder;
                            QSControlMiPlayDetailContent.Adapter2 adapter2 = this;
                            int iIntValue = volume.intValue();
                            m0.i currDevice4 = deviceItemHolder2.getCurrDevice();
                            if (currDevice4 == null || (deviceInfoK2 = currDevice4.k()) == null || !deviceInfoK2.isAudioSharing()) {
                                miPlayDetailViewModel.getMOverAllVolumeProgress().setValue(Integer.valueOf(adapter2.volumeToProgress(iIntValue)));
                            }
                        }
                        miPlayDeviceVolumeCache.calVisualMax();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    MiPlayDetailViewModel.INSTANCE.setAudioShareSecondaryEarphoneSeeking(device.k().isAudioSharing());
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    MiPlayDetailViewModel.INSTANCE.setAudioShareSecondaryEarphoneSeeking(device.k().isAudioSharing());
                }
            });
            MiPlayDeviceVolumeCache miPlayDeviceVolumeCache = MiPlayDeviceVolumeCache.INSTANCE;
            MutableLiveData<Integer> liveData7 = miPlayDeviceVolumeCache.getLiveData(device);
            if (liveData7 == null || (value = liveData7.getValue()) == null) {
                return;
            }
            kotlin.jvm.internal.n.d(value);
            deviceItemHolder.getVolumeBar().setProgress(miPlayOverallVolumeController.volumeToProgress(value.intValue()));
            Integer volume = miPlayOverallVolumeController.getVolume();
            if (volume != null) {
                int iIntValue = volume.intValue();
                m0.i currDevice4 = deviceItemHolder.getCurrDevice();
                if (currDevice4 == null || (deviceInfoK = currDevice4.k()) == null || !deviceInfoK.isAudioSharing()) {
                    MiPlayDetailViewModel.INSTANCE.getMOverAllVolumeProgress().setValue(Integer.valueOf(volumeToProgress(iIntValue)));
                }
            }
            miPlayDeviceVolumeCache.calVisualMax();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
            kotlin.jvm.internal.n.g(parent, "parent");
            int i3 = WhenMappings.$EnumSwitchMapping$0[ListType.values()[i2].ordinal()];
            if (i3 != 1 && i3 != 2 && i3 != 3) {
                throw new H0.g();
            }
            View viewInflate = LayoutInflater.from(QSControlMiPlayDetailContent.this.getContext()).inflate(R.layout.qs_control_detail_miplay_device_item, parent, false);
            kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
            return new DeviceItemHolder(viewInflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            kotlin.jvm.internal.n.g(holder, "holder");
            super.onViewRecycled(holder);
            DeviceItemHolder deviceItemHolder = holder instanceof DeviceItemHolder ? (DeviceItemHolder) holder : null;
            if (deviceItemHolder != null) {
                deviceItemHolder.onViewRecycled();
            }
        }
    }

    public static final class AudioDeviceCallback extends DiffUtil.ItemCallback<ListItem> {
        public static final AudioDeviceCallback INSTANCE = new AudioDeviceCallback();

        private AudioDeviceCallback() {
        }

        private final boolean isDeviceIconChanged(m0.i iVar, m0.i iVar2) {
            return (iVar.k().getType() == 2 && iVar.k().getType() == iVar2.k().getType() && iVar.k().getBleCustomDeviceType() != iVar2.k().getBleCustomDeviceType()) ? false : true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(ListItem oldItem, ListItem newItem) {
            H0.i value;
            H0.i value2;
            H0.i value3;
            H0.i value4;
            kotlin.jvm.internal.n.g(oldItem, "oldItem");
            kotlin.jvm.internal.n.g(newItem, "newItem");
            if ((oldItem instanceof DeviceListItem) && (newItem instanceof DeviceListItem)) {
                DeviceListItem deviceListItem = (DeviceListItem) oldItem;
                DeviceListItem deviceListItem2 = (DeviceListItem) newItem;
                if (kotlin.jvm.internal.n.c(deviceListItem.getDevice(), deviceListItem2.getDevice())) {
                    MiPlayDeviceConnectionStateCache miPlayDeviceConnectionStateCache = MiPlayDeviceConnectionStateCache.INSTANCE;
                    MutableLiveData<H0.i> liveData = miPlayDeviceConnectionStateCache.getLiveData(deviceListItem.getDevice());
                    Integer num = (liveData == null || (value4 = liveData.getValue()) == null) ? null : (Integer) value4.d();
                    MutableLiveData<H0.i> liveData2 = miPlayDeviceConnectionStateCache.getLiveData(deviceListItem2.getDevice());
                    Integer num2 = (liveData2 == null || (value3 = liveData2.getValue()) == null) ? null : (Integer) value3.d();
                    MutableLiveData<H0.i> liveData3 = miPlayDeviceConnectionStateCache.getLiveData(deviceListItem.getDevice());
                    Integer num3 = (liveData3 == null || (value2 = liveData3.getValue()) == null) ? null : (Integer) value2.e();
                    MutableLiveData<H0.i> liveData4 = miPlayDeviceConnectionStateCache.getLiveData(deviceListItem2.getDevice());
                    Integer num4 = (liveData4 == null || (value = liveData4.getValue()) == null) ? null : (Integer) value.e();
                    MiPlayDevicePlaybackStateCache miPlayDevicePlaybackStateCache = MiPlayDevicePlaybackStateCache.INSTANCE;
                    MutableLiveData<Integer> liveData5 = miPlayDevicePlaybackStateCache.getLiveData(deviceListItem.getDevice());
                    Integer value5 = liveData5 != null ? liveData5.getValue() : null;
                    MutableLiveData<Integer> liveData6 = miPlayDevicePlaybackStateCache.getLiveData(deviceListItem2.getDevice());
                    Integer value6 = liveData6 != null ? liveData6.getValue() : null;
                    int iM = deviceListItem.getDevice().m(0);
                    int iM2 = deviceListItem2.getDevice().m(0);
                    int iM3 = deviceListItem.getDevice().m(1);
                    int iM4 = deviceListItem2.getDevice().m(1);
                    m0.i device = deviceListItem.getDevice();
                    MiPlayController miPlayController = MiPlayController.INSTANCE;
                    String fullName = MiPlayExtentionsKt.getFullName(device, miPlayController.getContext());
                    String fullName2 = MiPlayExtentionsKt.getFullName(deviceListItem2.getDevice(), miPlayController.getContext());
                    String strJ = deviceListItem.getDevice().j();
                    kotlin.jvm.internal.n.f(strJ, "getDeviceId(...)");
                    String strJ2 = deviceListItem2.getDevice().j();
                    kotlin.jvm.internal.n.f(strJ2, "getDeviceId(...)");
                    return deviceListItem.getDevice().k().isAudioSharing() == deviceListItem2.getDevice().k().isAudioSharing() && kotlin.jvm.internal.n.c(num, num2) && kotlin.jvm.internal.n.c(value5, value6) && iM3 == iM4 && kotlin.jvm.internal.n.c(num3, num4) && kotlin.jvm.internal.n.c(strJ, strJ2) && iM == iM2 && kotlin.jvm.internal.n.c(fullName, fullName2) && kotlin.jvm.internal.n.c(deviceListItem.getDevice().k(), deviceListItem2.getDevice().k()) && (deviceListItem.getShowVolumeBar() == deviceListItem2.getShowVolumeBar()) && isDeviceIconChanged(deviceListItem.getDevice(), deviceListItem2.getDevice()) && deviceListItem.getDevice().t();
                }
            }
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(ListItem oldItem, ListItem newItem) {
            kotlin.jvm.internal.n.g(oldItem, "oldItem");
            kotlin.jvm.internal.n.g(newItem, "newItem");
            return (oldItem instanceof DeviceListItem) && (newItem instanceof DeviceListItem) && kotlin.jvm.internal.n.c(((DeviceListItem) oldItem).getDevice(), ((DeviceListItem) newItem).getDevice());
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class DeviceItemHolder extends RecyclerView.ViewHolder {
        private final View bluetoothShareMusicCancelBtn;
        private final View bluetoothShareMusicTogetherBtn;
        private CheckBox checkBox;
        private final Observer<H0.i> connectionStateObserver;
        private m0.i currDevice;
        private final H0.d folmeAnim$delegate;
        private final ImageView icon;
        private final Observer<MediaMetaData> metaDataObserver;
        private final Observer<Integer> playbackStateObserver;
        private final View playingInfoContainer;
        private final H0.d playingInfoDrawable$delegate;
        private final ImageView playingInfoIcon;
        private final TextView playingInfoTitle;
        private final ProgressBar progressBar;
        private final ImageView selected;
        private final ViewGroup stateContainer;
        private final TextView title;
        private final MiPlayVolumeBar volumeBar;
        private final Observer<Integer> volumeObserver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeviceItemHolder(View root) {
            super(root);
            kotlin.jvm.internal.n.g(root, "root");
            View viewFindViewById = root.findViewById(R.id.device_icon);
            kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
            this.icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = root.findViewById(R.id.device_title);
            kotlin.jvm.internal.n.f(viewFindViewById2, "findViewById(...)");
            this.title = (TextView) viewFindViewById2;
            View viewFindViewById3 = root.findViewById(R.id.device_playing_info);
            kotlin.jvm.internal.n.f(viewFindViewById3, "findViewById(...)");
            this.playingInfoContainer = viewFindViewById3;
            View viewFindViewById4 = root.findViewById(R.id.device_playing_info_title);
            kotlin.jvm.internal.n.f(viewFindViewById4, "findViewById(...)");
            this.playingInfoTitle = (TextView) viewFindViewById4;
            View viewFindViewById5 = root.findViewById(R.id.device_playing_info_icon);
            kotlin.jvm.internal.n.f(viewFindViewById5, "findViewById(...)");
            this.playingInfoIcon = (ImageView) viewFindViewById5;
            View viewFindViewById6 = root.findViewById(R.id.state_container);
            kotlin.jvm.internal.n.f(viewFindViewById6, "findViewById(...)");
            this.stateContainer = (ViewGroup) viewFindViewById6;
            View viewFindViewById7 = root.findViewById(R.id.progress);
            kotlin.jvm.internal.n.f(viewFindViewById7, "findViewById(...)");
            this.progressBar = (ProgressBar) viewFindViewById7;
            this.checkBox = (CheckBox) root.findViewById(R.id.checkbox);
            View viewFindViewById8 = root.findViewById(R.id.device_selected);
            kotlin.jvm.internal.n.f(viewFindViewById8, "findViewById(...)");
            this.selected = (ImageView) viewFindViewById8;
            this.playingInfoDrawable$delegate = H0.e.b(new QSControlMiPlayDetailContent$DeviceItemHolder$playingInfoDrawable$2(root));
            View viewFindViewById9 = root.findViewById(R.id.device_volume_bar);
            MiPlayVolumeBar miPlayVolumeBar = (MiPlayVolumeBar) viewFindViewById9;
            miPlayVolumeBar.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailContent$DeviceItemHolder$volumeBar$1$1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setSelected(false);
                }
            });
            kotlin.jvm.internal.n.f(viewFindViewById9, "apply(...)");
            this.volumeBar = miPlayVolumeBar;
            View viewFindViewById10 = root.findViewById(R.id.bluetooth_share_music_together_btn);
            Folme.useAt(viewFindViewById10).touch().handleTouchOf(viewFindViewById10, new AnimConfig[0]);
            viewFindViewById10.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.C
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.bluetoothShareMusicTogetherBtn$lambda$2$lambda$1(view);
                }
            });
            viewFindViewById10.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailContent$DeviceItemHolder$bluetoothShareMusicTogetherBtn$1$2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(true);
                    info.setFocusable(true);
                    info.setSelected(false);
                }
            });
            kotlin.jvm.internal.n.f(viewFindViewById10, "apply(...)");
            this.bluetoothShareMusicTogetherBtn = viewFindViewById10;
            View viewFindViewById11 = root.findViewById(R.id.bluetooth_share_music_cancel_btn);
            Folme.useAt(viewFindViewById11).touch().handleTouchOf(viewFindViewById11, new AnimConfig[0]);
            viewFindViewById11.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailContent$DeviceItemHolder$bluetoothShareMusicCancelBtn$1$1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    kotlin.jvm.internal.n.g(host, "host");
                    kotlin.jvm.internal.n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(true);
                    info.setFocusable(true);
                    info.setSelected(false);
                }
            });
            viewFindViewById11.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.D
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.bluetoothShareMusicCancelBtn$lambda$5$lambda$4(this.f1422a, view);
                }
            });
            kotlin.jvm.internal.n.f(viewFindViewById11, "apply(...)");
            this.bluetoothShareMusicCancelBtn = viewFindViewById11;
            this.folmeAnim$delegate = H0.e.b(new QSControlMiPlayDetailContent$DeviceItemHolder$folmeAnim$2(this));
            this.volumeObserver = new Observer() { // from class: com.android.systemui.E
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.volumeObserver$lambda$6(this.f1423a, ((Integer) obj).intValue());
                }
            };
            View itemView = this.itemView;
            kotlin.jvm.internal.n.f(itemView, "itemView");
            ViewAnimExtentionsKt.setTouchAlphaAnim(itemView);
            this.metaDataObserver = new Observer() { // from class: com.android.systemui.F
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.metaDataObserver$lambda$8(this.f1424a, (MediaMetaData) obj);
                }
            };
            this.playbackStateObserver = new Observer() { // from class: com.android.systemui.G
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.playbackStateObserver$lambda$9(this.f1425a, ((Integer) obj).intValue());
                }
            };
            this.connectionStateObserver = new Observer() { // from class: com.android.systemui.H
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    QSControlMiPlayDetailContent.DeviceItemHolder.connectionStateObserver$lambda$10(this.f1426a, (H0.i) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bluetoothShareMusicCancelBtn$lambda$5$lambda$4(DeviceItemHolder this$0, View view) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            m0.i iVar = this$0.currDevice;
            Log.d(QSControlMiPlayDetailContent.TAG, "bluetoothShareMusicCancelBtn cancel result: " + (iVar != null ? Integer.valueOf(iVar.e()) : null) + " ");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bluetoothShareMusicTogetherBtn$lambda$2$lambda$1(View view) {
            if (kotlin.jvm.internal.n.c(MiPlayDetailViewModel.INSTANCE.getSupportAudioShared(), Boolean.FALSE)) {
                Log.d(QSControlMiPlayDetailContent.TAG, "bluetoothShareMusicTogetherBtn is clicked,but rom doesn't support");
            } else {
                AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailContent$DeviceItemHolder$bluetoothShareMusicTogetherBtn$1$1$1(null), 3, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void connectionStateObserver$lambda$10(DeviceItemHolder this$0, H0.i it) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            kotlin.jvm.internal.n.g(it, "it");
            this$0.updateSelectedState();
        }

        private final boolean enableShowBlueToothShareMusicTogetherBtn() {
            return (CommonUtils.isFlipDevice() && MiPlayDetailViewModel.INSTANCE.getLastFoldState()) ? false : true;
        }

        private final void ensureCheckBox() {
            if (this.checkBox == null) {
                Context context = MiPlayController.INSTANCE.getContext();
                String packageName = context.getPackageName();
                Resources resources = context.getResources();
                Integer numValueOf = resources != null ? Integer.valueOf(resources.getIdentifier("Theme.DayNight", "style", packageName)) : null;
                if (numValueOf != null && numValueOf.intValue() == 0) {
                    Resources resources2 = context.getResources();
                    numValueOf = resources2 != null ? Integer.valueOf(resources2.getIdentifier("Theme.AppCompat.DayNight", "style", packageName)) : null;
                }
                kotlin.jvm.internal.n.d(numValueOf);
                CheckBox checkBox = new CheckBox(new ContextThemeWrapper(context, numValueOf.intValue()));
                this.checkBox = checkBox;
                checkBox.setClipToOutline(false);
                CheckBox checkBox2 = this.checkBox;
                if (checkBox2 != null) {
                    checkBox2.setButtonDrawable(ContextCompat.getDrawable(context, R.drawable.miplay_check_box));
                }
                CheckBox checkBox3 = this.checkBox;
                if (checkBox3 != null) {
                    checkBox3.setClickable(false);
                }
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.miplay_check_box_size);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
                CheckBox checkBox4 = this.checkBox;
                if (checkBox4 != null) {
                    checkBox4.setImportantForAccessibility(2);
                }
                layoutParams.addRule(13);
                this.stateContainer.addView(this.checkBox, layoutParams);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void metaDataObserver$lambda$8(DeviceItemHolder this$0, MediaMetaData mediaMetaData) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            this$0.updatePlayingInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void playbackStateObserver$lambda$9(DeviceItemHolder this$0, int i2) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            this$0.updatePlayingInfo();
            this$0.updateSelectedState();
        }

        private final void updatePlayingInfo() {
            String strBetterTitle;
            String str;
            MediaMetaData value;
            MediaMetaData value2;
            m0.i iVar = this.currDevice;
            if (iVar != null) {
                MiPlayDeviceMetaDataCache miPlayDeviceMetaDataCache = MiPlayDeviceMetaDataCache.INSTANCE;
                MutableLiveData<MediaMetaData> liveData = miPlayDeviceMetaDataCache.getLiveData(iVar);
                if (liveData == null || (value2 = liveData.getValue()) == null) {
                    strBetterTitle = null;
                } else {
                    kotlin.jvm.internal.n.d(value2);
                    Context context = this.itemView.getContext();
                    kotlin.jvm.internal.n.f(context, "getContext(...)");
                    strBetterTitle = MiPlayExtentionsKt.betterTitle(value2, context);
                }
                MutableLiveData<Integer> liveData2 = MiPlayDevicePlaybackStateCache.INSTANCE.getLiveData(iVar);
                Integer value3 = liveData2 != null ? liveData2.getValue() : null;
                boolean z2 = true;
                if (iVar.l() != 3 && iVar.l() != 1) {
                    z2 = false;
                }
                MutableLiveData<MediaMetaData> liveData3 = miPlayDeviceMetaDataCache.getLiveData(iVar);
                int mediaType = (liveData3 == null || (value = liveData3.getValue()) == null) ? 0 : value.getMediaType();
                if (TextUtils.isEmpty(strBetterTitle) || z2 || MiPlayExtentionsKt.isDeviceLoading(iVar, Integer.valueOf(mediaType)) || iVar.k().getType() == 0 || ((value3 == null || value3.intValue() != 3) && (value3 == null || value3.intValue() != 2))) {
                    this.playingInfoContainer.setVisibility(8);
                    Drawable drawable = this.playingInfoIcon.getDrawable();
                    AnimatedVectorDrawable animatedVectorDrawable = drawable instanceof AnimatedVectorDrawable ? (AnimatedVectorDrawable) drawable : null;
                    if (animatedVectorDrawable != null) {
                        animatedVectorDrawable.clearAnimationCallbacks();
                    }
                    if (animatedVectorDrawable != null) {
                        animatedVectorDrawable.stop();
                    }
                    str = "";
                } else {
                    this.playingInfoContainer.setVisibility(0);
                    this.playingInfoTitle.setText(strBetterTitle);
                    MiPlayExtentionsKt.setImageDrawableOnce(this.playingInfoIcon, getPlayingInfoDrawable());
                    Drawable drawable2 = this.playingInfoIcon.getDrawable();
                    kotlin.jvm.internal.n.e(drawable2, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
                    final AnimatedVectorDrawable animatedVectorDrawable2 = (AnimatedVectorDrawable) drawable2;
                    animatedVectorDrawable2.clearAnimationCallbacks();
                    if (value3 != null && value3.intValue() == 3) {
                        animatedVectorDrawable2.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: com.android.systemui.QSControlMiPlayDetailContent$DeviceItemHolder$updatePlayingInfo$1$1
                            @Override // android.graphics.drawable.Animatable2.AnimationCallback
                            public void onAnimationEnd(Drawable drawable3) {
                                super.onAnimationEnd(drawable3);
                                animatedVectorDrawable2.start();
                            }
                        });
                        animatedVectorDrawable2.start();
                        str = this.itemView.getContext().getString(R.string.miplay_device_play_state_playing) + " " + strBetterTitle;
                    } else {
                        animatedVectorDrawable2.stop();
                        str = this.itemView.getContext().getString(R.string.miplay_device_play_state_pause) + " " + strBetterTitle;
                    }
                }
                View view = this.itemView;
                view.setContentDescription(view.getContext().getString(R.string.miplay_device_type) + " " + this.itemView.getContext().getString(MiPlayExtentionsKt.getDeviceTypeName(iVar)) + " " + ((Object) this.title.getText()) + " " + str);
            }
        }

        private final void updateVolume() {
            MutableLiveData<Integer> volumeLiveData;
            Integer value;
            m0.i iVar = this.currDevice;
            if (iVar != null) {
                MiPlayOverallVolumeController miPlayOverallVolumeController = MiPlayOverallVolumeController.INSTANCE;
                MiPlayDeviceVolumeController miPlayDeviceVolumeController = miPlayOverallVolumeController.getDeviceControllers().get(iVar);
                if (miPlayDeviceVolumeController == null || (volumeLiveData = miPlayDeviceVolumeController.getVolumeLiveData()) == null || (value = volumeLiveData.getValue()) == null) {
                    return;
                }
                kotlin.jvm.internal.n.d(value);
                int iVolumeToProgress = miPlayOverallVolumeController.volumeToProgress(value.intValue());
                if (iVolumeToProgress != this.volumeBar.getProgress()) {
                    this.volumeBar.setProgress(iVolumeToProgress);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void volumeObserver$lambda$6(DeviceItemHolder this$0, int i2) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            this$0.updateVolume();
        }

        public final CheckBox getCheckBox() {
            return this.checkBox;
        }

        public final Observer<H0.i> getConnectionStateObserver() {
            return this.connectionStateObserver;
        }

        public final m0.i getCurrDevice() {
            return this.currDevice;
        }

        public final IStateStyle getFolmeAnim() {
            Object value = this.folmeAnim$delegate.getValue();
            kotlin.jvm.internal.n.f(value, "getValue(...)");
            return (IStateStyle) value;
        }

        public final ImageView getIcon() {
            return this.icon;
        }

        public final Observer<MediaMetaData> getMetaDataObserver() {
            return this.metaDataObserver;
        }

        public final Observer<Integer> getPlaybackStateObserver() {
            return this.playbackStateObserver;
        }

        public final Drawable getPlayingInfoDrawable() {
            return (Drawable) this.playingInfoDrawable$delegate.getValue();
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final MiPlayVolumeBar getVolumeBar() {
            return this.volumeBar;
        }

        public final Observer<Integer> getVolumeObserver() {
            return this.volumeObserver;
        }

        public final void onViewRecycled() {
            View itemView = this.itemView;
            kotlin.jvm.internal.n.f(itemView, "itemView");
            ViewAnimExtentionsKt.clearTouchAnim(itemView);
        }

        public final void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public final void setCurrDevice(m0.i iVar) {
            this.currDevice = iVar;
        }

        public final void updateSelectedState() {
            DeviceInfo deviceInfoK;
            DeviceInfo deviceInfoK2;
            DeviceInfo deviceInfoK3;
            m0.i iVar;
            DeviceInfo deviceInfoK4;
            ensureCheckBox();
            m0.i iVar2 = this.currDevice;
            if (iVar2 != null) {
                MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
                if (!MiPlayExtentionsKt.isDeviceConnecting(iVar2, value != null ? Integer.valueOf(value.getMediaType()) : null)) {
                    MediaMetaData value2 = miPlayDetailViewModel.getMMediaMetaData().getValue();
                    if (!MiPlayExtentionsKt.isDeviceDisconnecting(iVar2, value2 != null ? Integer.valueOf(value2.getMediaType()) : null)) {
                        MediaMetaData value3 = miPlayDetailViewModel.getMMediaMetaData().getValue();
                        int iM = iVar2.m(value3 != null ? value3.getMediaType() : 0);
                        if (iM == 1) {
                            this.itemView.setSelected(true);
                            this.itemView.setActivated(true);
                            this.progressBar.setVisibility(8);
                            CheckBox checkBox = this.checkBox;
                            if (checkBox != null) {
                                checkBox.setChecked(true);
                            }
                            this.selected.setVisibility(4);
                            this.bluetoothShareMusicTogetherBtn.setVisibility(8);
                            m0.i iVar3 = this.currDevice;
                            if (iVar3 == null || (deviceInfoK2 = iVar3.k()) == null || !deviceInfoK2.isAudioSharing()) {
                                this.bluetoothShareMusicCancelBtn.setVisibility(8);
                                m0.i iVar4 = this.currDevice;
                                if (iVar4 == null || (deviceInfoK = iVar4.k()) == null || 2 != deviceInfoK.getType() || !miPlayDetailViewModel.isAudioShare()) {
                                    CheckBox checkBox2 = this.checkBox;
                                    if (checkBox2 != null) {
                                        checkBox2.setVisibility(0);
                                    }
                                } else {
                                    CheckBox checkBox3 = this.checkBox;
                                    if (checkBox3 != null) {
                                        checkBox3.setVisibility(8);
                                    }
                                }
                            } else if (kotlin.jvm.internal.n.c(miPlayDetailViewModel.getSupportAudioShared(), Boolean.TRUE)) {
                                this.bluetoothShareMusicCancelBtn.setVisibility(0);
                                CheckBox checkBox4 = this.checkBox;
                                if (checkBox4 != null) {
                                    checkBox4.setVisibility(8);
                                }
                            } else {
                                this.bluetoothShareMusicCancelBtn.setVisibility(8);
                                CheckBox checkBox5 = this.checkBox;
                                if (checkBox5 != null) {
                                    checkBox5.setVisibility(0);
                                }
                            }
                            RPCEventTracker.INSTANCE.end("QSControlMiPlayDetailContent_mipLay_select_track" + iVar2.j() + MiPlayEventsKt.VALUE_SELECT);
                            return;
                        }
                        if (iM == 2) {
                            this.itemView.setSelected(false);
                            this.itemView.setActivated(true);
                            this.progressBar.setVisibility(8);
                            CheckBox checkBox6 = this.checkBox;
                            if (checkBox6 != null) {
                                checkBox6.setVisibility(0);
                            }
                            CheckBox checkBox7 = this.checkBox;
                            if (checkBox7 != null) {
                                checkBox7.setChecked(false);
                            }
                            this.selected.setVisibility(4);
                            this.bluetoothShareMusicTogetherBtn.setVisibility(8);
                            this.bluetoothShareMusicCancelBtn.setVisibility(8);
                            RPCEventTracker.INSTANCE.end("QSControlMiPlayDetailContent_mipLay_select_track" + iVar2.j() + MiPlayEventsKt.VALUE_CANCEL_SELECT);
                            return;
                        }
                        if (iM != 3) {
                            this.itemView.setSelected(false);
                            this.itemView.setActivated(true);
                            this.progressBar.setVisibility(8);
                            CheckBox checkBox8 = this.checkBox;
                            if (checkBox8 != null) {
                                checkBox8.setVisibility(4);
                            }
                            this.selected.setVisibility(4);
                            this.bluetoothShareMusicTogetherBtn.setVisibility(8);
                            this.bluetoothShareMusicCancelBtn.setVisibility(8);
                            RPCEventTracker.INSTANCE.end("QSControlMiPlayDetailContent_mipLay_select_track" + iVar2.j() + MiPlayEventsKt.VALUE_CANCEL_SELECT);
                            return;
                        }
                        this.itemView.setSelected(true);
                        this.itemView.setActivated(true);
                        this.progressBar.setVisibility(8);
                        CheckBox checkBox9 = this.checkBox;
                        if (checkBox9 != null) {
                            checkBox9.setVisibility(4);
                        }
                        this.selected.setVisibility(4);
                        this.selected.setSelected(true);
                        m0.i iVar5 = this.currDevice;
                        if (iVar5 == null || (deviceInfoK3 = iVar5.k()) == null || 2 != deviceInfoK3.getType() || (iVar = this.currDevice) == null || (deviceInfoK4 = iVar.k()) == null || !deviceInfoK4.isBluetoothHeadset() || !kotlin.jvm.internal.n.c(miPlayDetailViewModel.getSupportAudioShared(), Boolean.TRUE) || !enableShowBlueToothShareMusicTogetherBtn()) {
                            this.bluetoothShareMusicTogetherBtn.setVisibility(8);
                        } else {
                            this.bluetoothShareMusicTogetherBtn.setVisibility(0);
                        }
                        this.bluetoothShareMusicCancelBtn.setVisibility(8);
                        RPCEventTracker.INSTANCE.end("QSControlMiPlayDetailContent_mipLay_select_track" + iVar2.j() + MiPlayEventsKt.VALUE_SELECT);
                        return;
                    }
                }
                this.itemView.setSelected(false);
                this.itemView.setActivated(true);
                this.progressBar.setVisibility(0);
                Object indeterminateDrawable = this.progressBar.getIndeterminateDrawable();
                Animatable animatable = indeterminateDrawable instanceof Animatable ? (Animatable) indeterminateDrawable : null;
                if (animatable != null) {
                    animatable.start();
                }
                CheckBox checkBox10 = this.checkBox;
                if (checkBox10 != null) {
                    checkBox10.setVisibility(4);
                }
                CheckBox checkBox11 = this.checkBox;
                if (checkBox11 != null) {
                    checkBox11.setChecked(false);
                }
                this.selected.setVisibility(4);
                this.bluetoothShareMusicTogetherBtn.setVisibility(8);
            }
        }
    }

    public static abstract class DeviceListItem extends ListItem {
        private final m0.i device;
        private final boolean showVolumeBar;

        public /* synthetic */ DeviceListItem(ListType listType, m0.i iVar, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(listType, iVar, (i2 & 4) != 0 ? false : z2);
        }

        public final m0.i getDevice() {
            return this.device;
        }

        public final boolean getShowVolumeBar() {
            return this.showVolumeBar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeviceListItem(ListType type, m0.i device, boolean z2) {
            super(type);
            kotlin.jvm.internal.n.g(type, "type");
            kotlin.jvm.internal.n.g(device, "device");
            this.device = device;
            this.showVolumeBar = z2;
        }
    }

    public static final class HeadsetDeviceListItem extends DeviceListItem {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeadsetDeviceListItem(m0.i device, boolean z2) {
            super(ListType.TYPE_DEVICE_HEADSET, device, z2);
            kotlin.jvm.internal.n.g(device, "device");
        }
    }

    public static abstract class ListItem {
        private final ListType type;

        public ListItem(ListType type) {
            kotlin.jvm.internal.n.g(type, "type");
            this.type = type;
        }

        public final ListType getType() {
            return this.type;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class ListType {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ ListType[] $VALUES;
        public static final ListType TYPE_DEVICE_LOCAL_SPEAKER = new ListType("TYPE_DEVICE_LOCAL_SPEAKER", 0);
        public static final ListType TYPE_DEVICE_HEADSET = new ListType("TYPE_DEVICE_HEADSET", 1);
        public static final ListType TYPE_DEVICE_OTHER = new ListType("TYPE_DEVICE_OTHER", 2);

        private static final /* synthetic */ ListType[] $values() {
            return new ListType[]{TYPE_DEVICE_LOCAL_SPEAKER, TYPE_DEVICE_HEADSET, TYPE_DEVICE_OTHER};
        }

        static {
            ListType[] listTypeArr$values = $values();
            $VALUES = listTypeArr$values;
            $ENTRIES = O0.b.a(listTypeArr$values);
        }

        private ListType(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static ListType valueOf(String str) {
            return (ListType) Enum.valueOf(ListType.class, str);
        }

        public static ListType[] values() {
            return (ListType[]) $VALUES.clone();
        }
    }

    public static final class LocalSpeakerDeviceItem extends DeviceListItem {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LocalSpeakerDeviceItem(m0.i device) {
            super(ListType.TYPE_DEVICE_LOCAL_SPEAKER, device, false, 4, null);
            kotlin.jvm.internal.n.g(device, "device");
        }
    }

    public static final class MarginItemDecoration extends RecyclerView.ItemDecoration {
        private final int topMargin;

        public MarginItemDecoration() {
            this(0, 1, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            kotlin.jvm.internal.n.g(outRect, "outRect");
            kotlin.jvm.internal.n.g(view, "view");
            kotlin.jvm.internal.n.g(parent, "parent");
            kotlin.jvm.internal.n.g(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.top = this.topMargin;
            }
        }

        public final int getTopMargin() {
            return this.topMargin;
        }

        public MarginItemDecoration(int i2) {
            this.topMargin = i2;
        }

        public /* synthetic */ MarginItemDecoration(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i2);
        }
    }

    public static final class OtherDeviceListItem extends DeviceListItem {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OtherDeviceListItem(m0.i device, boolean z2) {
            super(ListType.TYPE_DEVICE_OTHER, device, z2);
            kotlin.jvm.internal.n.g(device, "device");
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$hideDynamicIsland$1, reason: invalid class name */
    @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$hideDynamicIsland$1", f = "QSControlMiPlayDetailContent.kt", l = {1084}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ boolean $miplayShowState;
        int label;
        final /* synthetic */ QSControlMiPlayDetailContent this$0;

        /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$hideDynamicIsland$1$1, reason: invalid class name and collision with other inner class name */
        @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$hideDynamicIsland$1$1", f = "QSControlMiPlayDetailContent.kt", l = {}, m = "invokeSuspend")
        public static final class C00491 extends N0.l implements Function2 {
            final /* synthetic */ boolean $miplayShowState;
            int label;
            final /* synthetic */ QSControlMiPlayDetailContent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00491(boolean z2, QSControlMiPlayDetailContent qSControlMiPlayDetailContent, L0.d dVar) {
                super(2, dVar);
                this.$miplayShowState = z2;
                this.this$0 = qSControlMiPlayDetailContent;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C00491(this.$miplayShowState, this.this$0, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(g1.E e2, L0.d dVar) {
                return ((C00491) create(e2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
                Log.d(QSControlMiPlayDetailContent.TAG, "hideDynamicIsland " + this.$miplayShowState);
                Intent intent = new Intent("android.intent.action.MIPLAY_SHOW_STATE_CHANGED");
                intent.putExtra("extra_miplay_show_state", this.$miplayShowState);
                intent.setPackage("com.android.systemui");
                this.this$0.getContext().sendBroadcast(intent);
                return H0.s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z2, QSControlMiPlayDetailContent qSControlMiPlayDetailContent, L0.d dVar) {
            super(2, dVar);
            this.$miplayShowState = z2;
            this.this$0 = qSControlMiPlayDetailContent;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.$miplayShowState, this.this$0, dVar);
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
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                C00491 c00491 = new C00491(this.$miplayShowState, this.this$0, null);
                this.label = 1;
                if (AbstractC0367f.c(io, c00491, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$onFinishInflate$4, reason: invalid class name */
    public static final class AnonymousClass4 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((List<? extends m0.i>) obj);
            return H0.s.f314a;
        }

        public final void invoke(List<? extends m0.i> list) {
            QSControlMiPlayDetailContent qSControlMiPlayDetailContent = QSControlMiPlayDetailContent.this;
            qSControlMiPlayDetailContent.refreshDeviceList(qSControlMiPlayDetailContent.mFirstRefresh);
            kotlin.jvm.internal.n.d(list);
            if (list.isEmpty()) {
                return;
            }
            QSControlMiPlayDetailContent.this.mFirstRefresh = false;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$onFinishInflate$5, reason: invalid class name */
    public static final class AnonymousClass5 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass5() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            if (num != null && num.intValue() == -108) {
                Toast.makeText(QSControlMiPlayDetailContent.this.getContext(), R.string.miplay_service_timeout_toast, 0).show();
                return;
            }
            if (num != null && num.intValue() == -109) {
                Toast.makeText(QSControlMiPlayDetailContent.this.getContext(), R.string.miplay_headset_lc3_hint, 0).show();
                return;
            }
            if (num != null && num.intValue() == -111) {
                Toast.makeText(QSControlMiPlayDetailContent.this.getContext(), R.string.miplay_not_support_hint, 0).show();
            } else {
                if (num == null || num.intValue() != -112 || QSControlMiPlayDetailContent.this.getContext() == null) {
                    return;
                }
                Toast.makeText(QSControlMiPlayDetailContent.this.getContext(), R.string.miplay_switch_local_device_when_in_audio_share_hint, 0).show();
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$setDetailShowing$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$setDetailShowing$1", f = "QSControlMiPlayDetailContent.kt", l = {1054, 1058}, m = "invokeSuspend")
    public static final class C02411 extends N0.l implements Function2 {
        final /* synthetic */ String $ref;
        final /* synthetic */ boolean $waitForAnim;
        int label;
        final /* synthetic */ QSControlMiPlayDetailContent this$0;

        /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$setDetailShowing$1$1, reason: invalid class name and collision with other inner class name */
        @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$setDetailShowing$1$1", f = "QSControlMiPlayDetailContent.kt", l = {}, m = "invokeSuspend")
        public static final class C00501 extends N0.l implements Function2 {
            final /* synthetic */ String $ref;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00501(String str, L0.d dVar) {
                super(2, dVar);
                this.$ref = str;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C00501(this.$ref, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(g1.E e2, L0.d dVar) {
                return ((C00501) create(e2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
                MiPlayEventTracker.trackExpose(MiPlayEventsKt.PAGE_MIPLAY_CARD, this.$ref);
                return H0.s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02411(boolean z2, QSControlMiPlayDetailContent qSControlMiPlayDetailContent, String str, L0.d dVar) {
            super(2, dVar);
            this.$waitForAnim = z2;
            this.this$0 = qSControlMiPlayDetailContent;
            this.$ref = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C02411(this.$waitForAnim, this.this$0, this.$ref, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02411) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                if (this.$waitForAnim) {
                    this.this$0.mPendingRefreshDeviceList = true;
                    this.label = 1;
                    if (g1.M.b(300L, this) == objC) {
                        return objC;
                    }
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    H0.k.b(obj);
                    return H0.s.f314a;
                }
                H0.k.b(obj);
            }
            this.this$0.mPendingRefreshDeviceList = false;
            QSControlMiPlayDetailContent.refreshDeviceList$default(this.this$0, false, 1, null);
            AbstractC0360b0 abstractC0360b0 = Dispatchers.INSTANCE.getDefault();
            C00501 c00501 = new C00501(this.$ref, null);
            this.label = 2;
            if (AbstractC0367f.c(abstractC0360b0, c00501, this) == objC) {
                return objC;
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$trackFindDeviceList$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$trackFindDeviceList$1", f = "QSControlMiPlayDetailContent.kt", l = {1117}, m = "invokeSuspend")
    public static final class C02421 extends N0.l implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailContent$trackFindDeviceList$1$1, reason: invalid class name and collision with other inner class name */
        @N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$trackFindDeviceList$1$1", f = "QSControlMiPlayDetailContent.kt", l = {}, m = "invokeSuspend")
        public static final class C00511 extends N0.l implements Function2 {
            int label;
            final /* synthetic */ QSControlMiPlayDetailContent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00511(QSControlMiPlayDetailContent qSControlMiPlayDetailContent, L0.d dVar) {
                super(2, dVar);
                this.this$0 = qSControlMiPlayDetailContent;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C00511(this.this$0, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(g1.E e2, L0.d dVar) {
                return ((C00511) create(e2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
                MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                MiPlayEventTracker.trackFindDeviceList(miPlayDetailViewModel.getScanDeviceTimeMap(), this.this$0.getContext());
                miPlayDetailViewModel.getScanDeviceTimeMap().clear();
                return H0.s.f314a;
            }
        }

        public C02421(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return QSControlMiPlayDetailContent.this.new C02421(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02421) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                C00511 c00511 = new C00511(QSControlMiPlayDetailContent.this, null);
                this.label = 1;
                if (AbstractC0367f.c(io, c00511, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            return H0.s.f314a;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final IStateStyle getMAnim() {
        return (IStateStyle) this.mAnim$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshDeviceList(boolean z2) {
        if (this.mPendingRefreshDeviceList) {
            return;
        }
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        ArrayList<ListItem> arrayList = new ArrayList<>();
        Iterator<T> it = miPlayDetailViewModel.getMLocalSpeakerDevices().iterator();
        while (it.hasNext()) {
            arrayList.add(new LocalSpeakerDeviceItem((m0.i) it.next()));
        }
        for (m0.i iVar : miPlayDetailViewModel.getMHeadsetDevices()) {
            arrayList.add(new HeadsetDeviceListItem(iVar, shouldShowDeviceItemVolumeBar(iVar)));
        }
        for (m0.i iVar2 : miPlayDetailViewModel.getMOtherDevices()) {
            arrayList.add(new OtherDeviceListItem(iVar2, shouldShowDeviceItemVolumeBar(iVar2)));
        }
        Log.d(MiPlayDetailViewModel.TAG, "refreshDeviceList(): size = " + arrayList.size());
        getMRecycler().setVerticalScrollBarEnabled(arrayList.size() > 1);
        RecyclerView.Adapter adapter = getMRecycler().getAdapter();
        kotlin.jvm.internal.n.e(adapter, "null cannot be cast to non-null type com.android.systemui.QSControlMiPlayDetailContent.Adapter2");
        ((Adapter2) adapter).submitList(arrayList);
        updateHeight$default(this, arrayList, !z2, false, 4, null);
        MiPlayDeviceVolumeCache.INSTANCE.calVisualMax();
        this.mListItems = arrayList;
    }

    public static /* synthetic */ void refreshDeviceList$default(QSControlMiPlayDetailContent qSControlMiPlayDetailContent, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: refreshDeviceList");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        qSControlMiPlayDetailContent.refreshDeviceList(z2);
    }

    public static /* synthetic */ void setDetailShowing$default(QSControlMiPlayDetailContent qSControlMiPlayDetailContent, boolean z2, String str, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setDetailShowing");
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        qSControlMiPlayDetailContent.setDetailShowing(z2, str, z3);
    }

    private final boolean shouldShowDeviceItemVolumeBar(m0.i iVar) {
        if (iVar.l() == 1) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
            if (MiPlayExtentionsKt.isSelectedDevice(iVar, value != null ? Integer.valueOf(value.getMediaType()) : null) && miPlayDetailViewModel.getMSelectedDevices().size() > 1) {
                return true;
            }
        }
        return false;
    }

    private final void trackFindDeviceList() {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C02421(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryExposeDevice() {
        ArrayList<ListItem> arrayList;
        int iF;
        if (MiPlayDetailViewModel.INSTANCE.getMMiPlayRef().getMRef() == null || (arrayList = this.mListItems) == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = getMRecycler().getLayoutManager();
        kotlin.jvm.internal.n.e(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int iFindLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if (iFindFirstCompletelyVisibleItemPosition < 0 || iFindLastCompletelyVisibleItemPosition < 0 || iFindFirstCompletelyVisibleItemPosition > (iF = c1.f.f(iFindLastCompletelyVisibleItemPosition, arrayList.size() - 1))) {
            return;
        }
        while (true) {
            ListItem listItem = arrayList.get(iFindFirstCompletelyVisibleItemPosition);
            DeviceListItem deviceListItem = listItem instanceof DeviceListItem ? (DeviceListItem) listItem : null;
            if (deviceListItem != null && !this.mExposedDevices.contains(deviceListItem.getDevice())) {
                this.mExposedDevices.add(deviceListItem.getDevice());
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (obj instanceof DeviceListItem) {
                        arrayList2.add(obj);
                    }
                }
                int iIndexOf = arrayList2.indexOf(deviceListItem);
                MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                miPlayDetailViewModel.trackExposeDevice(deviceListItem.getDevice(), iIndexOf, miPlayDetailViewModel.getMMiPlayRef().getMRef());
            }
            if (iFindFirstCompletelyVisibleItemPosition == iF) {
                return;
            } else {
                iFindFirstCompletelyVisibleItemPosition++;
            }
        }
    }

    public static /* synthetic */ void updateHeight$default(QSControlMiPlayDetailContent qSControlMiPlayDetailContent, ArrayList arrayList, boolean z2, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateHeight");
        }
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        qSControlMiPlayDetailContent.updateHeight(arrayList, z2, z3);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final QSControlMiPlayDetailHeader getMHeader() {
        Object value = this.mHeader$delegate.getValue();
        kotlin.jvm.internal.n.f(value, "getValue(...)");
        return (QSControlMiPlayDetailHeader) value;
    }

    public final ArrayList<ListItem> getMListItems() {
        return this.mListItems;
    }

    public final RecyclerView getMRecycler() {
        return (RecyclerView) this.mRecycler$delegate.getValue();
    }

    public final int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMaxItemCount() {
        return 4;
    }

    public int getRVMaxHeight() {
        return this.maxHeight - getResources().getDimensionPixelSize(kotlin.jvm.internal.n.c(Boolean.TRUE, MiPlayDetailViewModel.INSTANCE.getMTvSelectStatus().getValue()) ? R.dimen.miplay_detail_header_height_has_tvController : R.dimen.miplay_detail_header_height_no_tvController);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void hideDynamicIsland(boolean z2) {
        AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(z2, this, null), 3, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this instanceof QSControlMiPlayDetailContentSupportLand) {
            return;
        }
        this.maxHeight = resetMaxHeight();
        ArrayList<ListItem> arrayList = this.mListItems;
        if (arrayList != null) {
            updateHeight(arrayList, false, true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setOutlineProvider(new ViewOutlineProvider(this) { // from class: com.android.systemui.QSControlMiPlayDetailContent.onFinishInflate.1
            private final int radius;

            {
                this.radius = this.getContext().getResources().getDimensionPixelSize(R.dimen.miplay_parent_corner_radius);
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                kotlin.jvm.internal.n.g(view, "view");
                kotlin.jvm.internal.n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.radius);
            }

            public final int getRadius() {
                return this.radius;
            }
        });
        setClipToOutline(true);
        getMRecycler().setLayoutManager(new LinearLayoutManager(getContext()));
        getMRecycler().setItemAnimator(null);
        getMRecycler().setAdapter(new Adapter2());
        RecyclerView mRecycler = getMRecycler();
        MiScrollbarDrawable miScrollbarDrawable = new MiScrollbarDrawable(getMRecycler().getVerticalScrollbarThumbDrawable(), 0);
        miScrollbarDrawable.setVerticalOffset(getContext().getResources().getDimensionPixelOffset(R.dimen.miplay_detail_recycler_scrollbar_offset));
        mRecycler.setVerticalScrollbarThumbDrawable(miScrollbarDrawable);
        getMRecycler().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.android.systemui.QSControlMiPlayDetailContent.onFinishInflate.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, i2, i3);
                QSControlMiPlayDetailContent.this.tryExposeDevice();
            }
        });
        getMRecycler().addItemDecoration(new MarginItemDecoration(getContext().getResources().getDimensionPixelOffset(R.dimen.miplay_detail_item_divider_height)));
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMDevices()).observe(this, new QSControlMiPlayDetailContent$sam$androidx_lifecycle_Observer$0(new AnonymousClass4()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMErrorCodeEvent()).observe(this, new QSControlMiPlayDetailContent$sam$androidx_lifecycle_Observer$0(new AnonymousClass5()));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
            int size = View.MeasureSpec.getSize(i3);
            int i4 = this.maxHeight;
            if (size > i4) {
                i3 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(i2, i3);
    }

    public void prepareShowPanel() {
    }

    public int resetMaxHeight() {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        return commonUtils.getControlCenterDetailMaxHeight(context);
    }

    public final void setContentBgBlurForActivity() {
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            MiBlurCompat.setMiViewBlurModeCompat(this, 1);
            MiBlurCompat.setMiBackgroundBlendColors(this, MiuiColorBlendToken.INSTANCE.getCC_DETAIL_PANEL_BACKGROUND_BLEND_COLORS());
        }
    }

    public final void setDetailShowing(boolean z2, String str, boolean z3) {
        Log.d(TAG, "setDetailShowing: " + z2 + " " + str);
        this.mIsDetailShowing = z2;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMMiPlayRef().setMRef(str);
        miPlayDetailViewModel.getMIsListShowing().setValue(Boolean.valueOf(z2));
        miPlayDetailViewModel.setMFirstLoadCover(true);
        if (!kotlin.jvm.internal.n.c(str, "controlcenter")) {
            hideDynamicIsland(z2);
        }
        if (z2) {
            getMHeader().setUseCoverAnim(true);
            this.mFirstRefresh = true;
            MiPlayController miPlayController = MiPlayController.INSTANCE;
            C0465C miplay_audio_manager = miPlayController.getMIPLAY_AUDIO_MANAGER();
            if (miplay_audio_manager != null) {
                miplay_audio_manager.g(0);
            }
            C0465C miplay_audio_manager2 = miPlayController.getMIPLAY_AUDIO_MANAGER();
            if (miplay_audio_manager2 != null) {
                miplay_audio_manager2.o(miPlayDetailViewModel.getMMiPlayRef().refToMiplaySDK());
            }
            miPlayDetailViewModel.getScanDeviceTimeMap().clear();
            miPlayDetailViewModel.setStartScanDeviceTime(SystemClock.elapsedRealtime());
            MiPlayDetailViewModel.refreshAudioSession$default(miPlayDetailViewModel, false, 1, null);
            miPlayDetailViewModel.reloadDevices();
            miPlayDetailViewModel.refreshSystemVolume();
            this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
            tryExposeDevice();
            AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C02411(z3, this, str, null), 3, null);
            MLCardManager.INSTANCE.init();
        } else {
            MiPlayController miPlayController2 = MiPlayController.INSTANCE;
            C0465C miplay_audio_manager3 = miPlayController2.getMIPLAY_AUDIO_MANAGER();
            if (miplay_audio_manager3 != null) {
                miplay_audio_manager3.h(0);
            }
            C0465C miplay_audio_manager4 = miPlayController2.getMIPLAY_AUDIO_MANAGER();
            if (miplay_audio_manager4 != null) {
                miplay_audio_manager4.p();
            }
            trackFindDeviceList();
            this.mExposedDevices.clear();
            miPlayDetailViewModel.getBlueToothDeviceSelectTime().clear();
            this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
            MLCardManager.INSTANCE.destroy();
        }
        getMHeader().setShowing(z2, str);
    }

    public final void setMListItems(ArrayList<ListItem> arrayList) {
        this.mListItems = arrayList;
    }

    public final void setMaxHeight(int i2) {
        this.maxHeight = i2;
    }

    public final void updateHeight(ArrayList<ListItem> listItems, boolean z2, boolean z3) {
        kotlin.jvm.internal.n.g(listItems, "listItems");
        int i2 = !CommonUtils.isFlipDevice() ? getContext().getResources().getConfiguration().orientation == 1 ? 4 : 6 : MiPlayDetailViewModel.INSTANCE.getLastFoldState() ? 3 : 5;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_item_height);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_item_volume_bar_height);
        int dimensionPixelOffset = 0;
        int i3 = 0;
        for (ListItem listItem : listItems) {
            if (listItem instanceof DeviceListItem) {
                dimensionPixelOffset += dimensionPixelSize;
                if (shouldShowDeviceItemVolumeBar(((DeviceListItem) listItem).getDevice())) {
                    dimensionPixelOffset += dimensionPixelSize2;
                }
                i3++;
            }
            if (i3 == i2) {
                break;
            }
        }
        if (i3 > 1) {
            dimensionPixelOffset += getContext().getResources().getDimensionPixelOffset(R.dimen.miplay_detail_item_divider_height) * (i3 - 1);
        }
        int dimensionPixelSize3 = dimensionPixelOffset + getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_recycler_padding_vertical);
        int rVMaxHeight = getRVMaxHeight();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (commonUtils.getInVerticalMode(context)) {
            if (dimensionPixelSize3 > rVMaxHeight) {
                while (i3 > 0 && dimensionPixelSize3 > rVMaxHeight) {
                    dimensionPixelSize3 -= dimensionPixelSize;
                    i3--;
                }
            }
        } else if (this instanceof QSControlMiPlayDetailContentSupportLand) {
            dimensionPixelSize3 = this.maxHeight;
        } else if (dimensionPixelSize3 > rVMaxHeight) {
            dimensionPixelSize3 = rVMaxHeight;
        }
        int i4 = this.mTargetHeight;
        if (i4 != dimensionPixelSize3 || z3) {
            if (i4 < 0 || !z2) {
                getMRecycler().getLayoutParams().height = dimensionPixelSize3;
                requestLayout();
            } else {
                getMAnim().cancel();
                getMAnim().add((FloatProperty) ViewProperty.HEIGHT, dimensionPixelSize3).to(sSpeedConfig, this.mAnimConfig);
            }
            this.mTargetHeight = dimensionPixelSize3;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ QSControlMiPlayDetailContent(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.mHeader$delegate = H0.e.b(new QSControlMiPlayDetailContent$mHeader$2(this));
        this.mExposedDevices = new HashSet<>();
        this.mRecycler$delegate = H0.e.b(new QSControlMiPlayDetailContent$mRecycler$2(this));
        this.mAnimConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: com.android.systemui.QSControlMiPlayDetailContent$mAnimConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                this.this$0.getMRecycler().setVerticalScrollBarEnabled(false);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                this.this$0.getMRecycler().setVerticalScrollBarEnabled(true);
            }
        });
        this.mAnim$delegate = H0.e.b(new QSControlMiPlayDetailContent$mAnim$2(this));
        this.maxHeight = resetMaxHeight();
        this.mTargetHeight = -1;
        this.mFirstRefresh = true;
        this.mLifecycle = new LifecycleRegistry(this);
    }
}
