package com.android.systemui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.mediarouter.media.MediaRouter;
import com.android.systemui.folme.ImageAlphaSwitchAnimation;
import com.android.systemui.folme.ViewAnimExtentionsKt;
import com.android.systemui.miplay.R;
import com.android.systemui.plugins.ActivityStarter;
import com.miui.circulate.device.api.Constant;
import com.miui.miplay.audio.data.AppMetaData;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.InterfaceC0380l0;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import m0.C0465C;
import m0.C0466a;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.concurrency.ConcurrencyModule;
import miuix.animation.IStateStyle;
import miuix.transition.FlipAnimation;
import miuix.view.HapticFeedbackConstants;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailHeader extends LinearLayout implements LifecycleOwner {
    private static final String APPLICATION_MALL_PACKAGE_NAME = "com.xiaomi.market";
    private static final String APPLICATION_MALL_XIAOMI_MUSIC_URI = "market://details?id=com.miui.player";
    private static final int PREV_NEXT_TOUCH_INTERVAL = 2000;
    private static final int SEEK_BAR_TOUCH_INTERVAL = 1000;
    private static final String TAG = "QSControlMiPlayDetailHeader";
    private static final String XIAOMI_MUSIC_PACKAGE_NAME = "com.miui.player";
    private final int TAG_PLAY_STATE;
    private InterfaceC0380l0 animUpdateCoverJob;
    private ImageView appIcon;
    private ViewGroup audioContainer;
    public View containerLayout;
    private ImageView cover;
    private ViewGroup coverParent;
    private float coverRadius;
    private ImageView deviceIconView;
    private FlipAnimation flipAnimation;
    private final H0.d folmeAnim$delegate;
    private boolean hasCover;
    private HeaderSizeCallback headerSizeCallback;
    private final ImageAlphaSwitchAnimation imageAlphaSwitchAnimation;
    private boolean isVolumeBarInit;
    private boolean lastTvSelected;
    private final LifecycleRegistry mLifecycle;
    private String mRef;
    private final Handler mainHandler;
    private MediaTimeTextView mediaElapsedTime;
    private MediaTimeTextView mediaTotalTime;
    private MiPlayVolumeIconAnim miPlayVolumeIconAnim;
    private ImageView next;
    private boolean nextMedia;
    private ImageView openRemoteTvView;
    private boolean panelAnimHiding;
    private ImageView play;
    private ImageView prev;
    private long prevNextTouchTime;
    private ConstraintLayout progressContainer;
    private MiPlaySeekBar seekBar;
    private long seekBarTouchTime;
    private TextView speed;
    private TextView subtitle;
    private TextView title;
    private TextView titleNoPlayback;
    private boolean trackingStarted;
    private final QSControlMiPlayDetailHeader$tvControlVisibilityRunnable$1 tvControlVisibilityRunnable;
    private View tvController;
    private TVControllerVisibilityCallback tvControllerVisibilityCallback;
    private TvManger tvManager;
    private InterfaceC0380l0 updateDefaultCoverJob;
    private boolean useCoverAnim;
    private ViewGroup videoContainer;
    private View videoFastForward;
    private ImageView videoNext;
    private ImageView videoPlay;
    private int videoPlaySpeedIndex;
    private View videoRewind;
    private MiPlayVolumeBar volumeBar;
    private View volumeBarContainer;
    private VolumeColumn volumeColumn;
    private ImageView volumeIcon;
    public static final Companion Companion = new Companion(null);
    private static List<Float> SPEED_ARRAYS = I0.m.k(Float.valueOf(1.0f));

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isInstalledAndVisible(Context context, String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            PackageManager packageManager = context.getPackageManager();
            kotlin.jvm.internal.n.d(str);
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            Companion companion = QSControlMiPlayDetailHeader.Companion;
            ComponentName component = launchIntentForPackage.getComponent();
            return companion.isLauncherVisible(context, str, component != null ? component.getClassName() : null);
        }

        private final boolean isLauncherVisible(Context context, String str, String str2) {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startAPPWithDeepLink(Context context) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            Log.d(QSControlMiPlayDetailHeader.TAG, "jumpCpdeepLink: " + miPlayDetailViewModel.getCpDeepLink());
            AppMetaData value = miPlayDetailViewModel.getMAppMetadata().getValue();
            Log.d(QSControlMiPlayDetailHeader.TAG, "startAPPWithDeepLink: " + (value != null ? value.getPackageName() : null));
            if (value != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(miPlayDetailViewModel.getCpDeepLink()));
                intent.setPackage(value.getPackageName());
                intent.addFlags(268468224);
                QSControlMiPlayDetailHeader.Companion.collapseAndJump(context, intent);
            }
        }

        public final void collapseAndJump(Context context, String packageName) {
            kotlin.jvm.internal.n.g(context, "context");
            kotlin.jvm.internal.n.g(packageName, "packageName");
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntentForPackage != null) {
                QSControlMiPlayDetailHeader.Companion.collapseAndJump(context, launchIntentForPackage);
            }
        }

        public final Object getLastPlayingAppPackageName(Context context, L0.d dVar) {
            return AbstractC0367f.c(Dispatchers.INSTANCE.getIO(), new QSControlMiPlayDetailHeader$Companion$getLastPlayingAppPackageName$2(context, null), dVar);
        }

        public final boolean hasDeepLinkPermission() {
            MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
            return value != null && value.getMediaType() == 1;
        }

        public final void jumpLastPlayingApp(Context context) {
            kotlin.jvm.internal.n.g(context, "context");
            AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailHeader$Companion$jumpLastPlayingApp$1(context, null), 3, null);
        }

        private Companion() {
        }

        public final void collapseAndJump(Context context, Intent intent) {
            kotlin.jvm.internal.n.g(context, "context");
            kotlin.jvm.internal.n.g(intent, "intent");
            try {
                CommonUtils.INSTANCE.collapseControlCenter();
                ActivityStarter activityStarter = MiPlayController.INSTANCE.getActivityStarter();
                if (activityStarter != null) {
                    activityStarter.postStartActivityDismissingKeyguard(intent, 350);
                } else {
                    context.startActivity(intent);
                }
            } catch (Exception e2) {
                Log.e(QSControlMiPlayDetailHeader.TAG, "collapseAndJump", e2);
            }
        }
    }

    public interface HeaderSizeCallback {
        void onSizeChanged();
    }

    public interface TVControllerVisibilityCallback {
        void onTvControllerVisibilityChanged(boolean z2);
    }

    public static final class VolumeColumn {
        private ImageView icon;

        public final ImageView getIcon() {
            return this.icon;
        }

        public final void setIcon(ImageView imageView) {
            this.icon = imageView;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((List<Float>) obj);
            return H0.s.f314a;
        }

        public final void invoke(List<Float> list) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            Log.d(QSControlMiPlayDetailHeader.TAG, "sdk speedList:" + list + ",currentSpeed:" + miPlayDetailViewModel.getMPlaySpeed().getValue());
            Companion companion = QSControlMiPlayDetailHeader.Companion;
            Float fValueOf = Float.valueOf(1.0f);
            if (list == null) {
                list = I0.m.k(fValueOf);
            }
            QSControlMiPlayDetailHeader.SPEED_ARRAYS = list;
            if (QSControlMiPlayDetailHeader.SPEED_ARRAYS.size() == 0) {
                QSControlMiPlayDetailHeader.SPEED_ARRAYS = I0.m.k(fValueOf);
            }
            QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
            List list2 = QSControlMiPlayDetailHeader.SPEED_ARRAYS;
            Float value = miPlayDetailViewModel.getMPlaySpeed().getValue();
            kotlin.jvm.internal.n.d(value);
            qSControlMiPlayDetailHeader.resetVideoSpeedIndex(list2, value.floatValue());
            QSControlMiPlayDetailHeader.this.setSpeedText();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$10, reason: invalid class name */
    public static final class AnonymousClass10 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass10() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            MiplayConstant miplayConstant = MiplayConstant.INSTANCE;
            int cast_mode_success = miplayConstant.getCAST_MODE_SUCCESS();
            if (num == null || num.intValue() != cast_mode_success) {
                int cast_mode_idle = miplayConstant.getCAST_MODE_IDLE();
                if (num == null || num.intValue() != cast_mode_idle) {
                    return;
                }
            }
            QSControlMiPlayDetailHeader.this.initVideoSpeed();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((MediaMetaData) obj);
            return H0.s.f314a;
        }

        public final void invoke(MediaMetaData mediaMetaData) {
            ImageView imageView = null;
            if (mediaMetaData == null) {
                InterfaceC0380l0 interfaceC0380l0 = QSControlMiPlayDetailHeader.this.updateDefaultCoverJob;
                if (interfaceC0380l0 != null) {
                    InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
                }
                QSControlMiPlayDetailHeader.this.hasCover = false;
                QSControlMiPlayDetailHeader.updateMediaTypeContainerVisibility$default(QSControlMiPlayDetailHeader.this, 0, 1, null);
                QSControlMiPlayDetailHeader.this.setTvControlVisibility(false);
                TextView textView = QSControlMiPlayDetailHeader.this.title;
                if (textView == null) {
                    kotlin.jvm.internal.n.w("title");
                    textView = null;
                }
                textView.setVisibility(8);
                TextView textView2 = QSControlMiPlayDetailHeader.this.titleNoPlayback;
                if (textView2 == null) {
                    kotlin.jvm.internal.n.w("titleNoPlayback");
                    textView2 = null;
                }
                textView2.setVisibility(0);
                TextView textView3 = QSControlMiPlayDetailHeader.this.subtitle;
                if (textView3 == null) {
                    kotlin.jvm.internal.n.w("subtitle");
                    textView3 = null;
                }
                textView3.setVisibility(8);
                ImageView imageView2 = QSControlMiPlayDetailHeader.this.cover;
                if (imageView2 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
                    imageView2 = null;
                }
                imageView2.setImageResource(0);
                ImageView imageView3 = QSControlMiPlayDetailHeader.this.prev;
                if (imageView3 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
                    imageView3 = null;
                }
                imageView3.setEnabled(false);
                ImageView imageView4 = QSControlMiPlayDetailHeader.this.play;
                if (imageView4 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                    imageView4 = null;
                }
                imageView4.setEnabled(true);
                QSControlMiPlayDetailHeader.this.setPreNextBlur(true);
                QSControlMiPlayDetailHeader.this.updatePlayButton();
                ImageView imageView5 = QSControlMiPlayDetailHeader.this.play;
                if (imageView5 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                    imageView5 = null;
                }
                imageView5.setContentDescription(QSControlMiPlayDetailHeader.this.getContext().getString(R.string.miplay_accessibility_play));
                ImageView imageView6 = QSControlMiPlayDetailHeader.this.next;
                if (imageView6 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
                    imageView6 = null;
                }
                imageView6.setEnabled(false);
                MiPlaySeekBar miPlaySeekBar = QSControlMiPlayDetailHeader.this.seekBar;
                if (miPlaySeekBar == null) {
                    kotlin.jvm.internal.n.w("seekBar");
                    miPlaySeekBar = null;
                }
                miPlaySeekBar.setEnabled(false);
                QSControlMiPlayDetailHeader.this.setSeekbarColor();
                MiPlaySeekBar miPlaySeekBar2 = QSControlMiPlayDetailHeader.this.seekBar;
                if (miPlaySeekBar2 == null) {
                    kotlin.jvm.internal.n.w("seekBar");
                    miPlaySeekBar2 = null;
                }
                miPlaySeekBar2.setProgress(0);
                MediaTimeTextView mediaTimeTextView = QSControlMiPlayDetailHeader.this.mediaElapsedTime;
                if (mediaTimeTextView == null) {
                    kotlin.jvm.internal.n.w("mediaElapsedTime");
                    mediaTimeTextView = null;
                }
                mediaTimeTextView.setMediaTime(0L);
                MediaTimeTextView mediaTimeTextView2 = QSControlMiPlayDetailHeader.this.mediaTotalTime;
                if (mediaTimeTextView2 == null) {
                    kotlin.jvm.internal.n.w("mediaTotalTime");
                    mediaTimeTextView2 = null;
                }
                mediaTimeTextView2.setMediaTime(0L);
                ImageView imageView7 = QSControlMiPlayDetailHeader.this.videoNext;
                if (imageView7 == null) {
                    kotlin.jvm.internal.n.w("videoNext");
                } else {
                    imageView = imageView7;
                }
                imageView.setEnabled(false);
                return;
            }
            TextView textView4 = QSControlMiPlayDetailHeader.this.title;
            if (textView4 == null) {
                kotlin.jvm.internal.n.w("title");
                textView4 = null;
            }
            textView4.setVisibility(0);
            TextView textView5 = QSControlMiPlayDetailHeader.this.titleNoPlayback;
            if (textView5 == null) {
                kotlin.jvm.internal.n.w("titleNoPlayback");
                textView5 = null;
            }
            textView5.setVisibility(8);
            TextView textView6 = QSControlMiPlayDetailHeader.this.title;
            if (textView6 == null) {
                kotlin.jvm.internal.n.w("title");
                textView6 = null;
            }
            CharSequence text = textView6.getText();
            Context context = QSControlMiPlayDetailHeader.this.getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            if (!kotlin.jvm.internal.n.c(text, MiPlayExtentionsKt.betterTitle(mediaMetaData, context)) && !QSControlMiPlayDetailHeader.this.getPanelAnimHiding()) {
                TextView textView7 = QSControlMiPlayDetailHeader.this.title;
                if (textView7 == null) {
                    kotlin.jvm.internal.n.w("title");
                    textView7 = null;
                }
                Context context2 = QSControlMiPlayDetailHeader.this.getContext();
                kotlin.jvm.internal.n.f(context2, "getContext(...)");
                textView7.setText(MiPlayExtentionsKt.betterTitle(mediaMetaData, context2));
            }
            TextView textView8 = QSControlMiPlayDetailHeader.this.subtitle;
            if (textView8 == null) {
                kotlin.jvm.internal.n.w("subtitle");
                textView8 = null;
            }
            textView8.setVisibility(0);
            String strBetterArtistAlbum = MiPlayExtentionsKt.betterArtistAlbum(mediaMetaData);
            if (!QSControlMiPlayDetailHeader.this.getPanelAnimHiding()) {
                TextView textView9 = QSControlMiPlayDetailHeader.this.subtitle;
                if (textView9 == null) {
                    kotlin.jvm.internal.n.w("subtitle");
                    textView9 = null;
                }
                textView9.setText(strBetterArtistAlbum);
            }
            if (strBetterArtistAlbum == null || strBetterArtistAlbum.length() == 0) {
                TextView textView10 = QSControlMiPlayDetailHeader.this.subtitle;
                if (textView10 == null) {
                    kotlin.jvm.internal.n.w("subtitle");
                    textView10 = null;
                }
                textView10.setImportantForAccessibility(2);
            } else {
                TextView textView11 = QSControlMiPlayDetailHeader.this.subtitle;
                if (textView11 == null) {
                    kotlin.jvm.internal.n.w("subtitle");
                    textView11 = null;
                }
                textView11.setImportantForAccessibility(1);
            }
            QSControlMiPlayDetailHeader.this.updateMediaTypeContainerVisibility(mediaMetaData.getMediaType());
            QSControlMiPlayDetailHeader.this.handleCoverUpdate(mediaMetaData);
            ImageView imageView8 = QSControlMiPlayDetailHeader.this.prev;
            if (imageView8 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
                imageView8 = null;
            }
            imageView8.setEnabled(true);
            ImageView imageView9 = QSControlMiPlayDetailHeader.this.play;
            if (imageView9 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                imageView9 = null;
            }
            imageView9.setEnabled(true);
            ImageView imageView10 = QSControlMiPlayDetailHeader.this.next;
            if (imageView10 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
                imageView10 = null;
            }
            imageView10.setEnabled(true);
            QSControlMiPlayDetailHeader.this.setPreNextBlur(false);
            int mediaType = mediaMetaData.getMediaType();
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            Integer value = miPlayDetailViewModel.getMCurrMediaType().getValue();
            if (value == null || mediaType != value.intValue()) {
                miPlayDetailViewModel.getMCurrMediaType().setValue(Integer.valueOf(mediaType));
                QSControlMiPlayDetailHeader.this.updatePlayButtonWithoutAnim();
            }
            MiPlaySeekBar miPlaySeekBar3 = QSControlMiPlayDetailHeader.this.seekBar;
            if (miPlaySeekBar3 == null) {
                kotlin.jvm.internal.n.w("seekBar");
                miPlaySeekBar3 = null;
            }
            miPlaySeekBar3.setEnabled(miPlayDetailViewModel.allowControlRemoteTV());
            QSControlMiPlayDetailHeader.this.setSeekbarColor();
            ImageView imageView11 = QSControlMiPlayDetailHeader.this.videoNext;
            if (imageView11 == null) {
                kotlin.jvm.internal.n.w("videoNext");
            } else {
                imageView = imageView11;
            }
            imageView.setEnabled(mediaMetaData.isSequel());
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$3, reason: invalid class name */
    public static final class AnonymousClass3 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HashMap<String, Drawable>) obj);
            return H0.s.f314a;
        }

        public final void invoke(HashMap<String, Drawable> map) {
            MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
            if (value != null) {
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
                Log.d(QSControlMiPlayDetailHeader.TAG, "mMediaDataManagerArt: art");
                qSControlMiPlayDetailHeader.handleCoverUpdate(value);
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4, reason: invalid class name */
    public static final class AnonymousClass4 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ kotlin.jvm.internal.y $updateAppIconJob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(kotlin.jvm.internal.y yVar) {
            super(1);
            this.$updateAppIconJob = yVar;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AppMetaData) obj);
            return H0.s.f314a;
        }

        public final void invoke(AppMetaData appMetaData) {
            H0.s sVar;
            if (appMetaData != null) {
                kotlin.jvm.internal.y yVar = this.$updateAppIconJob;
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
                InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) yVar.f5059a;
                if (interfaceC0380l0 != null) {
                    InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
                }
                yVar.f5059a = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailHeader$addObservers$4$1$1(qSControlMiPlayDetailHeader, appMetaData, null), 3, null);
                sVar = H0.s.f314a;
            } else {
                sVar = null;
            }
            if (sVar == null) {
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader2 = QSControlMiPlayDetailHeader.this;
                kotlin.jvm.internal.y yVar2 = this.$updateAppIconJob;
                InterfaceC0380l0 interfaceC0380l02 = (InterfaceC0380l0) yVar2.f5059a;
                if (interfaceC0380l02 != null) {
                    InterfaceC0380l0.a.a(interfaceC0380l02, null, 1, null);
                }
                yVar2.f5059a = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailHeader$addObservers$4$2$1(qSControlMiPlayDetailHeader2, null), 3, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$5, reason: invalid class name */
    public static final class AnonymousClass5 extends kotlin.jvm.internal.o implements Function1 {
        final /* synthetic */ kotlin.jvm.internal.y $updateJob;

        /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$5$1, reason: invalid class name */
        @N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$addObservers$5$1", f = "QSControlMiPlayDetailHeader.kt", l = {PointerIconCompat.TYPE_GRAB}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends N0.l implements Function2 {
            final /* synthetic */ kotlin.jvm.internal.y $updateJob;
            int label;
            final /* synthetic */ QSControlMiPlayDetailHeader this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, kotlin.jvm.internal.y yVar, L0.d dVar) {
                super(2, dVar);
                this.this$0 = qSControlMiPlayDetailHeader;
                this.$updateJob = yVar;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new AnonymousClass1(this.this$0, this.$updateJob, dVar);
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
                    long jCurrentTimeMillis = ((long) QSControlMiPlayDetailHeader.PREV_NEXT_TOUCH_INTERVAL) - (System.currentTimeMillis() - this.this$0.prevNextTouchTime);
                    this.label = 1;
                    if (g1.M.b(jCurrentTimeMillis, this) == objC) {
                        return objC;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    H0.k.b(obj);
                }
                this.this$0.updatePlayButton();
                this.$updateJob.f5059a = null;
                return H0.s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(kotlin.jvm.internal.y yVar) {
            super(1);
            this.$updateJob = yVar;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            if (System.currentTimeMillis() - QSControlMiPlayDetailHeader.this.prevNextTouchTime >= 2000) {
                QSControlMiPlayDetailHeader.this.updatePlayButton();
            } else {
                kotlin.jvm.internal.y yVar = this.$updateJob;
                if (yVar.f5059a == null) {
                    yVar.f5059a = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(QSControlMiPlayDetailHeader.this, this.$updateJob, null), 3, null);
                }
            }
            QSControlMiPlayDetailHeader.this.resetMediaControllerAbility();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$6, reason: invalid class name */
    public static final class AnonymousClass6 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass6() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Long) obj);
            return H0.s.f314a;
        }

        public final void invoke(Long l2) {
            MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
            if (value != null) {
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
                MiPlaySeekBar miPlaySeekBar = qSControlMiPlayDetailHeader.seekBar;
                MediaTimeTextView mediaTimeTextView = null;
                if (miPlaySeekBar == null) {
                    kotlin.jvm.internal.n.w("seekBar");
                    miPlaySeekBar = null;
                }
                miPlaySeekBar.setMax(100);
                if (qSControlMiPlayDetailHeader.trackingStarted || System.currentTimeMillis() - qSControlMiPlayDetailHeader.seekBarTouchTime < 1000) {
                    return;
                }
                if (value.getDuration() != 0) {
                    MiPlaySeekBar miPlaySeekBar2 = qSControlMiPlayDetailHeader.seekBar;
                    if (miPlaySeekBar2 == null) {
                        kotlin.jvm.internal.n.w("seekBar");
                        miPlaySeekBar2 = null;
                    }
                    miPlaySeekBar2.setProgress((int) ((l2.longValue() / value.getDuration()) * 100), false);
                } else {
                    MiPlaySeekBar miPlaySeekBar3 = qSControlMiPlayDetailHeader.seekBar;
                    if (miPlaySeekBar3 == null) {
                        kotlin.jvm.internal.n.w("seekBar");
                        miPlaySeekBar3 = null;
                    }
                    miPlaySeekBar3.setProgress(0, false);
                }
                MediaTimeTextView mediaTimeTextView2 = qSControlMiPlayDetailHeader.mediaElapsedTime;
                if (mediaTimeTextView2 == null) {
                    kotlin.jvm.internal.n.w("mediaElapsedTime");
                    mediaTimeTextView2 = null;
                }
                kotlin.jvm.internal.n.d(l2);
                mediaTimeTextView2.setMediaTime(l2.longValue());
                MediaTimeTextView mediaTimeTextView3 = qSControlMiPlayDetailHeader.mediaTotalTime;
                if (mediaTimeTextView3 == null) {
                    kotlin.jvm.internal.n.w("mediaTotalTime");
                } else {
                    mediaTimeTextView = mediaTimeTextView3;
                }
                mediaTimeTextView.setMediaTime(value.getDuration());
            }
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$7, reason: invalid class name */
    public static final class AnonymousClass7 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass7() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            MiPlayVolumeIconAnim miPlayVolumeIconAnim = null;
            if (QSControlMiPlayDetailHeader.this.isVolumeBarInit) {
                QSControlMiPlayDetailHeader.this.getFolmeAnim().setTo(MiPlayExtentionsKt.getVIEW_PROPERTY_PROGRESS(), Float.valueOf(num.intValue()));
            } else {
                QSControlMiPlayDetailHeader.this.getFolmeAnim().setTo(MiPlayExtentionsKt.getVIEW_PROPERTY_PROGRESS(), Float.valueOf(num.intValue()));
                MiPlayVolumeBar miPlayVolumeBar = QSControlMiPlayDetailHeader.this.volumeBar;
                if (miPlayVolumeBar == null) {
                    kotlin.jvm.internal.n.w("volumeBar");
                    miPlayVolumeBar = null;
                }
                kotlin.jvm.internal.n.d(num);
                miPlayVolumeBar.setProgress(num.intValue());
                QSControlMiPlayDetailHeader.this.isVolumeBarInit = true;
            }
            MiPlayVolumeIconAnim miPlayVolumeIconAnim2 = QSControlMiPlayDetailHeader.this.miPlayVolumeIconAnim;
            if (miPlayVolumeIconAnim2 == null) {
                kotlin.jvm.internal.n.w("miPlayVolumeIconAnim");
            } else {
                miPlayVolumeIconAnim = miPlayVolumeIconAnim2;
            }
            kotlin.jvm.internal.n.d(num);
            miPlayVolumeIconAnim.updateMediaIconAnimProgress(num.intValue());
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$8, reason: invalid class name */
    public static final class AnonymousClass8 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass8() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Float) obj);
            return H0.s.f314a;
        }

        public final void invoke(Float f2) {
            Log.d(QSControlMiPlayDetailHeader.TAG, "sdk speedList:" + QSControlMiPlayDetailHeader.SPEED_ARRAYS + ",currentSpeed:" + MiPlayDetailViewModel.INSTANCE.getMPlaySpeed().getValue());
            QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
            List list = QSControlMiPlayDetailHeader.SPEED_ARRAYS;
            kotlin.jvm.internal.n.d(f2);
            qSControlMiPlayDetailHeader.resetVideoSpeedIndex(list, f2.floatValue());
            QSControlMiPlayDetailHeader.this.setSpeedText();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$addObservers$9, reason: invalid class name */
    public static final class AnonymousClass9 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass9() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Boolean) obj);
            return H0.s.f314a;
        }

        public final void invoke(Boolean bool) {
            if (!kotlin.jvm.internal.n.c(Boolean.valueOf(QSControlMiPlayDetailHeader.this.lastTvSelected), bool)) {
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
                kotlin.jvm.internal.n.d(bool);
                qSControlMiPlayDetailHeader.lastTvSelected = bool.booleanValue();
                QSControlMiPlayDetailHeader.this.mainHandler.removeCallbacks(QSControlMiPlayDetailHeader.this.tvControlVisibilityRunnable);
                QSControlMiPlayDetailHeader.this.mainHandler.postDelayed(QSControlMiPlayDetailHeader.this.tvControlVisibilityRunnable.setMessage(bool.booleanValue()), 500L);
            }
            QSControlMiPlayDetailHeader.this.resetMediaControllerAbility();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$handleCoverUpdate$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$handleCoverUpdate$1", f = "QSControlMiPlayDetailHeader.kt", l = {MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, m = "invokeSuspend")
    public static final class C02431 extends N0.l implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        public C02431(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            C02431 c02431 = QSControlMiPlayDetailHeader.this.new C02431(dVar);
            c02431.L$0 = obj;
            return c02431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02431) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            H0.s sVar;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                this.L$0 = (g1.E) this.L$0;
                this.label = 1;
                if (g1.M.b(1000L, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
            ImageView imageView = null;
            if (value != null) {
                QSControlMiPlayDetailHeader.updateCover$default(QSControlMiPlayDetailHeader.this, value, false, 2, null);
                sVar = H0.s.f314a;
            } else {
                sVar = null;
            }
            if (sVar == null) {
                QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = QSControlMiPlayDetailHeader.this;
                ImageView imageView2 = qSControlMiPlayDetailHeader.cover;
                if (imageView2 == null) {
                    kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
                } else {
                    imageView = imageView2;
                }
                imageView.setImageResource(0);
                qSControlMiPlayDetailHeader.hasCover = false;
            }
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$initTvPanelListener$4, reason: invalid class name and case insensitive filesystem */
    public static final class C02444 extends kotlin.jvm.internal.o implements Function1 {
        public C02444() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Boolean) obj);
            return H0.s.f314a;
        }

        public final void invoke(Boolean bool) {
            QSControlMiPlayDetailHeader.this.resetMediaControllerAbility();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$initTvPanelListener$5, reason: invalid class name and case insensitive filesystem */
    public static final class C02455 extends kotlin.jvm.internal.o implements Function1 {
        public C02455() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Integer) obj);
            return H0.s.f314a;
        }

        public final void invoke(Integer num) {
            QSControlMiPlayDetailHeader.this.resetMediaControllerAbility();
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.QSControlMiPlayDetailHeader$updateCover$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$updateCover$1", f = "QSControlMiPlayDetailHeader.kt", l = {HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP}, m = "invokeSuspend")
    public static final class C02541 extends N0.l implements Function2 {
        final /* synthetic */ Bitmap $bitmap;
        final /* synthetic */ boolean $isSameMedia;
        final /* synthetic */ String $mediaDataId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02541(boolean z2, Bitmap bitmap, String str, L0.d dVar) {
            super(2, dVar);
            this.$isSameMedia = z2;
            this.$bitmap = bitmap;
            this.$mediaDataId = str;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return QSControlMiPlayDetailHeader.this.new C02541(this.$isSameMedia, this.$bitmap, this.$mediaDataId, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((C02541) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                this.label = 1;
                if (g1.M.b(500L, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            ViewGroup viewGroup = QSControlMiPlayDetailHeader.this.coverParent;
            if (viewGroup == null) {
                kotlin.jvm.internal.n.w("coverParent");
                viewGroup = null;
            }
            if (!FlipAnimation.isInFlipAnimation(viewGroup)) {
                if (this.$isSameMedia) {
                    QSControlMiPlayDetailHeader.setCover$default(QSControlMiPlayDetailHeader.this, this.$bitmap, false, false, 6, null);
                } else {
                    QSControlMiPlayDetailHeader.this.performFlip(this.$bitmap);
                }
                MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                miPlayDetailViewModel.setMMediaIDForCover(this.$mediaDataId);
                miPlayDetailViewModel.setMFirstLoadCover(false);
            }
            return H0.s.f314a;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final void addObservers() {
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMPlaySpeedList()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass1()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMMediaMetaData()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass2()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMMediaDataManagerArt()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass3()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMAppMetadata()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass4(new kotlin.jvm.internal.y())));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMPlaybackState()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass5(new kotlin.jvm.internal.y())));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMPosition()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass6()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMOverAllVolumeProgress()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass7()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMPlaySpeed()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass8()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getMTvSelectStatus()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass9()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getCastMode()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new AnonymousClass10()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Drawable getApplicationIconSafely(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Companion companion = Companion;
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (!companion.isInstalledAndVisible(context, str)) {
            return null;
        }
        try {
            return getContext().getPackageManager().getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException e2) {
            Log.d(TAG, "getDrawable err:" + e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IStateStyle getFolmeAnim() {
        Object value = this.folmeAnim$delegate.getValue();
        kotlin.jvm.internal.n.f(value, "getValue(...)");
        return (IStateStyle) value;
    }

    private final Drawable getMediaCover() {
        return MiPlayDetailViewModel.INSTANCE.getMediaDataManagerArt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleCoverUpdate(MediaMetaData mediaMetaData) {
        if (this.hasCover && mediaMetaData.getArt() == null) {
            this.updateDefaultCoverJob = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C02431(null), 3, null);
            return;
        }
        InterfaceC0380l0 interfaceC0380l0 = this.updateDefaultCoverJob;
        if (interfaceC0380l0 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
        updateCover$default(this, mediaMetaData, false, 2, null);
    }

    private final void init() {
        initUI();
        initListener();
        initMediaTypeContainerVisibility();
        addObservers();
        initSeekbar();
        this.tvManager = new TvManger(MiPlayDetailViewModel.INSTANCE);
    }

    private final void initColumn() {
        VolumeColumn volumeColumn = new VolumeColumn();
        this.volumeColumn = volumeColumn;
        ImageView imageView = this.volumeIcon;
        VolumeColumn volumeColumn2 = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w("volumeIcon");
            imageView = null;
        }
        volumeColumn.setIcon(imageView);
        VolumeColumn volumeColumn3 = this.volumeColumn;
        if (volumeColumn3 == null) {
            kotlin.jvm.internal.n.w("volumeColumn");
            volumeColumn3 = null;
        }
        ImageView icon = volumeColumn3.getIcon();
        if (icon != null) {
            icon.setImageResource(R.drawable.ic_miplay_volume_media_animate_icon);
        }
        VolumeColumn volumeColumn4 = this.volumeColumn;
        if (volumeColumn4 == null) {
            kotlin.jvm.internal.n.w("volumeColumn");
            volumeColumn4 = null;
        }
        MiPlayVolumeIconAnim miPlayVolumeIconAnim = new MiPlayVolumeIconAnim(volumeColumn4);
        this.miPlayVolumeIconAnim = miPlayVolumeIconAnim;
        VolumeColumn volumeColumn5 = this.volumeColumn;
        if (volumeColumn5 == null) {
            kotlin.jvm.internal.n.w("volumeColumn");
        } else {
            volumeColumn2 = volumeColumn5;
        }
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        miPlayVolumeIconAnim.updateMediaIconAnimParams(volumeColumn2, context);
    }

    private final void initListener() {
        initOnClickListener();
        initTvPanelListener();
    }

    private final void initMediaTypeContainerVisibility() {
        updateMediaTypeContainerVisibility$default(this, 0, 1, null);
    }

    private final void initOnClickListener() {
        ImageView imageView = this.play;
        View view = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.P
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$6(this.f1435a, view2);
            }
        });
        ImageView imageView2 = this.prev;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$7(this.f1436a, view2);
            }
        });
        ImageView imageView3 = this.next;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            imageView3 = null;
        }
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.S
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$8(this.f1437a, view2);
            }
        });
        View view2 = this.videoRewind;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view2 = null;
        }
        view2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.T
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$9(this.f1438a, view3);
            }
        });
        ImageView imageView4 = this.videoPlay;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w("videoPlay");
            imageView4 = null;
        }
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$10(this.f1439a, view3);
            }
        });
        View view3 = this.videoFastForward;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view3 = null;
        }
        view3.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.K
            @Override // android.view.View.OnClickListener
            public final void onClick(View view4) {
                QSControlMiPlayDetailHeader.initOnClickListener$lambda$11(this.f1429a, view4);
            }
        });
        View view4 = this.videoRewind;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view4 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(view4);
        ImageView imageView5 = this.videoPlay;
        if (imageView5 == null) {
            kotlin.jvm.internal.n.w("videoPlay");
            imageView5 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView5);
        View view5 = this.videoFastForward;
        if (view5 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
        } else {
            view = view5;
        }
        ViewAnimExtentionsKt.setTouchAnim(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$10(QSControlMiPlayDetailHeader this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.onClickPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$11(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "videoFastForward click");
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.f();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_FAST_FORWARD, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$6(QSControlMiPlayDetailHeader this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.onClickPlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$7(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "prev click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        ImageView imageView = this$0.prev;
        if (imageView == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView = null;
        }
        this$0.startPlayAnim(imageView);
        this$0.nextMedia = false;
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.t();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PREV, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$8(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "next click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        ImageView imageView = this$0.next;
        if (imageView == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            imageView = null;
        }
        this$0.startPlayAnim(imageView);
        this$0.nextMedia = true;
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.o();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NEXT, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initOnClickListener$lambda$9(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "videoRewind click");
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.w();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_REWIND, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private final void initProgressSeekbar() {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.QSControlMiPlayDetailHeader$initProgressSeekbar$seekBarChangeListener$1
            private int trackingProgress = -1;

            public final int getTrackingProgress() {
                return this.trackingProgress;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
                if (this.this$0.trackingStarted && z2) {
                    this.trackingProgress = i2;
                    MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
                    if (value != null) {
                        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.this$0;
                        long duration = (long) ((i2 / 100) * value.getDuration());
                        MediaTimeTextView mediaTimeTextView = qSControlMiPlayDetailHeader.mediaElapsedTime;
                        MediaTimeTextView mediaTimeTextView2 = null;
                        if (mediaTimeTextView == null) {
                            kotlin.jvm.internal.n.w("mediaElapsedTime");
                            mediaTimeTextView = null;
                        }
                        mediaTimeTextView.setMediaTime(duration);
                        MediaTimeTextView mediaTimeTextView3 = qSControlMiPlayDetailHeader.mediaTotalTime;
                        if (mediaTimeTextView3 == null) {
                            kotlin.jvm.internal.n.w("mediaTotalTime");
                        } else {
                            mediaTimeTextView2 = mediaTimeTextView3;
                        }
                        mediaTimeTextView2.setMediaTime(value.getDuration());
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                this.this$0.trackingStarted = true;
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                List listI;
                C0466a c0466a;
                MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
                if (value != null) {
                    QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.this$0;
                    if (this.trackingProgress >= 0) {
                        qSControlMiPlayDetailHeader.seekBarTouchTime = System.currentTimeMillis();
                        Number numberValueOf = value.getDuration() == 0 ? 0 : Float.valueOf((this.trackingProgress / 100) * value.getDuration());
                        C0465C miplay_audio_manager = MiPlayController.INSTANCE.getMIPLAY_AUDIO_MANAGER();
                        if (miplay_audio_manager != null && (listI = miplay_audio_manager.i()) != null && (c0466a = (C0466a) I0.u.M(listI)) != null) {
                            c0466a.b().x(numberValueOf.longValue());
                            MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_SEEK_BAR, MiPlayEventsKt.PAGE_MIPLAY_CARD, qSControlMiPlayDetailHeader.mRef);
                        }
                    }
                }
                this.this$0.trackingStarted = false;
                this.trackingProgress = -1;
            }

            public final void setTrackingProgress(int i2) {
                this.trackingProgress = i2;
            }
        };
        MiPlaySeekBar miPlaySeekBar = this.seekBar;
        if (miPlaySeekBar == null) {
            kotlin.jvm.internal.n.w("seekBar");
            miPlaySeekBar = null;
        }
        miPlaySeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private final void initSeekbar() {
        initProgressSeekbar();
        initVolumeSeekbar();
    }

    @SuppressLint({"SetTextI18n"})
    private final void initTvPanelListener() {
        TextView textView = this.speed;
        ImageView imageView = null;
        if (textView == null) {
            kotlin.jvm.internal.n.w("speed");
            textView = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(textView);
        TextView textView2 = this.speed;
        if (textView2 == null) {
            kotlin.jvm.internal.n.w("speed");
            textView2 = null;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.J
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QSControlMiPlayDetailHeader.initTvPanelListener$lambda$12(this.f1428a, view);
            }
        });
        ImageView imageView2 = this.openRemoteTvView;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w("openRemoteTvView");
            imageView2 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView2);
        ImageView imageView3 = this.openRemoteTvView;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w("openRemoteTvView");
            imageView3 = null;
        }
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.L
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QSControlMiPlayDetailHeader.initTvPanelListener$lambda$13(this.f1430a, view);
            }
        });
        ImageView imageView4 = this.videoNext;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w("videoNext");
            imageView4 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView4);
        ImageView imageView5 = this.videoNext;
        if (imageView5 == null) {
            kotlin.jvm.internal.n.w("videoNext");
        } else {
            imageView = imageView5;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QSControlMiPlayDetailHeader.initTvPanelListener$lambda$14(this.f1431a, view);
            }
        });
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.isPlayingAdvertisement()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new C02444()));
        MiPlayExtentionsKt.toMediator(miPlayDetailViewModel.getCpStateLiveData()).observe(this, new QSControlMiPlayDetailHeader$sam$androidx_lifecycle_Observer$0(new C02455()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initTvPanelListener$lambda$12(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (SPEED_ARRAYS.size() == 0) {
            Log.d(TAG, "speed valued failed");
            return;
        }
        if (this$0.videoPlaySpeedIndex >= Integer.MAX_VALUE) {
            this$0.videoPlaySpeedIndex = 0;
        }
        int i2 = this$0.videoPlaySpeedIndex + 1;
        this$0.videoPlaySpeedIndex = i2;
        Log.d(TAG, "click tv speed:" + i2);
        this$0.setSpeedText();
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            List<Float> list = SPEED_ARRAYS;
            wVarB.y(list.get(this$0.videoPlaySpeedIndex % list.size()).floatValue());
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_SPEEDS, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initTvPanelListener$lambda$13(QSControlMiPlayDetailHeader this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        TvManger tvManger = this$0.tvManager;
        TvManger tvManger2 = null;
        if (tvManger == null) {
            kotlin.jvm.internal.n.w("tvManager");
            tvManger = null;
        }
        String tvId = tvManger.getTvId();
        TvManger tvManger3 = this$0.tvManager;
        if (tvManger3 == null) {
            kotlin.jvm.internal.n.w("tvManager");
            tvManger3 = null;
        }
        String tvName = tvManger3.getTvName();
        TvManger tvManger4 = this$0.tvManager;
        if (tvManger4 == null) {
            kotlin.jvm.internal.n.w("tvManager");
        } else {
            tvManger2 = tvManger4;
        }
        String mac = tvManger2.getMac();
        Log.d(TAG, "open_remote_tv_view tv id:" + tvId + ",name:" + tvName + ",tvMac:" + mac);
        if (!TextUtils.isEmpty(tvId) && !TextUtils.isEmpty(tvName)) {
            MLCardManager mLCardManager = MLCardManager.INSTANCE;
            Context context = MiPlayController.INSTANCE.getContext();
            kotlin.jvm.internal.n.d(tvId);
            kotlin.jvm.internal.n.d(tvName);
            mLCardManager.openTvCard(context, tvId, (28 & 4) != 0 ? Constant.DeviceCategory.NEARBY : null, (28 & 8) != 0 ? Constant.DeviceType.ANDROID_TV : null, (28 & 16) != 0 ? "DEVICE_TV_SECOND" : null, tvName, (28 & 64) != 0 ? null : mac);
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_TV_CONTROLLER, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initTvPanelListener$lambda$14(QSControlMiPlayDetailHeader this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "next click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.o();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NEXT, MiPlayEventsKt.PAGE_MIPLAY_CARD, this$0.mRef);
    }

    private final void initUI() {
        View viewRequireViewById = requireViewById(R.id.cover);
        kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
        this.cover = (ImageView) viewRequireViewById;
        View viewRequireViewById2 = requireViewById(R.id.coverParent);
        kotlin.jvm.internal.n.f(viewRequireViewById2, "requireViewById(...)");
        this.coverParent = (ViewGroup) viewRequireViewById2;
        setCoverRadius(getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_cover_radius));
        ImageView imageView = this.cover;
        ImageView imageView2 = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
            imageView = null;
        }
        imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUI.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                kotlin.jvm.internal.n.g(view, "view");
                kotlin.jvm.internal.n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), QSControlMiPlayDetailHeader.this.getCoverRadius());
            }
        });
        ViewGroup viewGroup = this.coverParent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup = null;
        }
        viewGroup.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUI.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                kotlin.jvm.internal.n.g(view, "view");
                kotlin.jvm.internal.n.g(outline, "outline");
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), QSControlMiPlayDetailHeader.this.getCoverRadius());
            }
        });
        ImageView imageView3 = this.cover;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
            imageView3 = null;
        }
        imageView3.setClipToOutline(true);
        ViewGroup viewGroup2 = this.coverParent;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup2 = null;
        }
        viewGroup2.setClipToOutline(true);
        View viewRequireViewById3 = requireViewById(R.id.app_icon);
        kotlin.jvm.internal.n.f(viewRequireViewById3, "requireViewById(...)");
        this.appIcon = (ImageView) viewRequireViewById3;
        View viewRequireViewById4 = requireViewById(R.id.title);
        kotlin.jvm.internal.n.f(viewRequireViewById4, "requireViewById(...)");
        this.title = (TextView) viewRequireViewById4;
        View viewRequireViewById5 = requireViewById(R.id.title_no_play_back_history);
        kotlin.jvm.internal.n.f(viewRequireViewById5, "requireViewById(...)");
        this.titleNoPlayback = (TextView) viewRequireViewById5;
        View viewRequireViewById6 = requireViewById(R.id.subtitle);
        kotlin.jvm.internal.n.f(viewRequireViewById6, "requireViewById(...)");
        this.subtitle = (TextView) viewRequireViewById6;
        View viewRequireViewById7 = requireViewById(R.id.container_audio);
        kotlin.jvm.internal.n.f(viewRequireViewById7, "requireViewById(...)");
        this.audioContainer = (ViewGroup) viewRequireViewById7;
        View viewRequireViewById8 = requireViewById(R.id.prev);
        kotlin.jvm.internal.n.f(viewRequireViewById8, "requireViewById(...)");
        this.prev = (ImageView) viewRequireViewById8;
        View viewRequireViewById9 = requireViewById(R.id.play);
        kotlin.jvm.internal.n.f(viewRequireViewById9, "requireViewById(...)");
        this.play = (ImageView) viewRequireViewById9;
        View viewRequireViewById10 = requireViewById(R.id.next);
        kotlin.jvm.internal.n.f(viewRequireViewById10, "requireViewById(...)");
        this.next = (ImageView) viewRequireViewById10;
        View viewRequireViewById11 = requireViewById(R.id.tv_controller_panel);
        kotlin.jvm.internal.n.f(viewRequireViewById11, "requireViewById(...)");
        this.tvController = viewRequireViewById11;
        View viewRequireViewById12 = requireViewById(R.id.speed);
        kotlin.jvm.internal.n.f(viewRequireViewById12, "requireViewById(...)");
        this.speed = (TextView) viewRequireViewById12;
        View viewRequireViewById13 = requireViewById(R.id.open_remote_tv_view);
        kotlin.jvm.internal.n.f(viewRequireViewById13, "requireViewById(...)");
        this.openRemoteTvView = (ImageView) viewRequireViewById13;
        View viewRequireViewById14 = requireViewById(R.id.video_next);
        kotlin.jvm.internal.n.f(viewRequireViewById14, "requireViewById(...)");
        this.videoNext = (ImageView) viewRequireViewById14;
        View viewRequireViewById15 = requireViewById(R.id.media_progress_bar);
        kotlin.jvm.internal.n.f(viewRequireViewById15, "requireViewById(...)");
        this.seekBar = (MiPlaySeekBar) viewRequireViewById15;
        View viewRequireViewById16 = requireViewById(R.id.container_progress);
        kotlin.jvm.internal.n.f(viewRequireViewById16, "requireViewById(...)");
        this.progressContainer = (ConstraintLayout) viewRequireViewById16;
        View viewRequireViewById17 = requireViewById(R.id.media_elapsed_time);
        kotlin.jvm.internal.n.f(viewRequireViewById17, "requireViewById(...)");
        this.mediaElapsedTime = (MediaTimeTextView) viewRequireViewById17;
        View viewRequireViewById18 = requireViewById(R.id.media_total_time);
        kotlin.jvm.internal.n.f(viewRequireViewById18, "requireViewById(...)");
        this.mediaTotalTime = (MediaTimeTextView) viewRequireViewById18;
        View viewRequireViewById19 = requireViewById(R.id.volume_row_slider);
        kotlin.jvm.internal.n.f(viewRequireViewById19, "requireViewById(...)");
        this.volumeBar = (MiPlayVolumeBar) viewRequireViewById19;
        View viewRequireViewById20 = requireViewById(R.id.volume_row_icon);
        kotlin.jvm.internal.n.f(viewRequireViewById20, "requireViewById(...)");
        this.volumeIcon = (ImageView) viewRequireViewById20;
        View viewRequireViewById21 = requireViewById(R.id.volume_bar_container);
        kotlin.jvm.internal.n.f(viewRequireViewById21, "requireViewById(...)");
        this.volumeBarContainer = viewRequireViewById21;
        View viewRequireViewById22 = requireViewById(R.id.container_video);
        kotlin.jvm.internal.n.f(viewRequireViewById22, "requireViewById(...)");
        this.videoContainer = (ViewGroup) viewRequireViewById22;
        View viewRequireViewById23 = requireViewById(R.id.video_rewind);
        kotlin.jvm.internal.n.f(viewRequireViewById23, "requireViewById(...)");
        this.videoRewind = viewRequireViewById23;
        View viewRequireViewById24 = requireViewById(R.id.video_play);
        kotlin.jvm.internal.n.f(viewRequireViewById24, "requireViewById(...)");
        this.videoPlay = (ImageView) viewRequireViewById24;
        View viewRequireViewById25 = requireViewById(R.id.video_fastForward);
        kotlin.jvm.internal.n.f(viewRequireViewById25, "requireViewById(...)");
        this.videoFastForward = viewRequireViewById25;
        View viewFindViewById = findViewById(R.id.device_icon);
        kotlin.jvm.internal.n.f(viewFindViewById, "findViewById(...)");
        this.deviceIconView = (ImageView) viewFindViewById;
        View viewRequireViewById26 = requireViewById(R.id.meta_data_info);
        kotlin.jvm.internal.n.f(viewRequireViewById26, "requireViewById(...)");
        setContainerLayout(viewRequireViewById26);
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context)) {
            TextView textView = this.speed;
            if (textView == null) {
                kotlin.jvm.internal.n.w("speed");
                textView = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(textView, 1);
            TextView textView2 = this.speed;
            if (textView2 == null) {
                kotlin.jvm.internal.n.w("speed");
                textView2 = null;
            }
            MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
            MiBlurCompat.setMiBackgroundBlendColors(textView2, miuiColorBlendToken.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
            ImageView imageView4 = this.openRemoteTvView;
            if (imageView4 == null) {
                kotlin.jvm.internal.n.w("openRemoteTvView");
                imageView4 = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(imageView4, 1);
            ImageView imageView5 = this.openRemoteTvView;
            if (imageView5 == null) {
                kotlin.jvm.internal.n.w("openRemoteTvView");
                imageView5 = null;
            }
            MiBlurCompat.setMiBackgroundBlendColors(imageView5, miuiColorBlendToken.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
            ImageView imageView6 = this.videoNext;
            if (imageView6 == null) {
                kotlin.jvm.internal.n.w("videoNext");
                imageView6 = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(imageView6, 1);
            ImageView imageView7 = this.videoNext;
            if (imageView7 == null) {
                kotlin.jvm.internal.n.w("videoNext");
                imageView7 = null;
            }
            MiBlurCompat.setMiBackgroundBlendColors(imageView7, miuiColorBlendToken.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
            MiPlaySeekBar miPlaySeekBar = this.seekBar;
            if (miPlaySeekBar == null) {
                kotlin.jvm.internal.n.w("seekBar");
                miPlaySeekBar = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(miPlaySeekBar, 2);
            MiPlaySeekBar miPlaySeekBar2 = this.seekBar;
            if (miPlaySeekBar2 == null) {
                kotlin.jvm.internal.n.w("seekBar");
                miPlaySeekBar2 = null;
            }
            MiBlurCompat.setMiBackgroundBlendColors(miPlaySeekBar2, miuiColorBlendToken.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
            MiPlayVolumeBar miPlayVolumeBar = this.volumeBar;
            if (miPlayVolumeBar == null) {
                kotlin.jvm.internal.n.w("volumeBar");
                miPlayVolumeBar = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(miPlayVolumeBar, 2);
            MiPlayVolumeBar miPlayVolumeBar2 = this.volumeBar;
            if (miPlayVolumeBar2 == null) {
                kotlin.jvm.internal.n.w("volumeBar");
                miPlayVolumeBar2 = null;
            }
            MiBlurCompat.setMiBackgroundBlendColors(miPlayVolumeBar2, miuiColorBlendToken.getCC_DETAIL_PANEL_MORE_BUTTON_BLEND_COLORS());
            ImageView imageView8 = this.volumeIcon;
            if (imageView8 == null) {
                kotlin.jvm.internal.n.w("volumeIcon");
                imageView8 = null;
            }
            MiBlurCompat.setMiViewBlurModeCompat(imageView8, 3);
            ImageView imageView9 = this.volumeIcon;
            if (imageView9 == null) {
                kotlin.jvm.internal.n.w("volumeIcon");
                imageView9 = null;
            }
            MiBlurCompat.setMiBackgroundBlendColors(imageView9, miuiColorBlendToken.getCC_DETAIL_PANEL_SLIDER_ICON_BLEND_COLORS());
        } else {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            TextView textView3 = this.speed;
            if (textView3 == null) {
                kotlin.jvm.internal.n.w("speed");
                textView3 = null;
            }
            commonUtils.clearMiBlurBlendEffect(textView3);
            ImageView imageView10 = this.openRemoteTvView;
            if (imageView10 == null) {
                kotlin.jvm.internal.n.w("openRemoteTvView");
                imageView10 = null;
            }
            commonUtils.clearMiBlurBlendEffect(imageView10);
            ImageView imageView11 = this.videoNext;
            if (imageView11 == null) {
                kotlin.jvm.internal.n.w("videoNext");
                imageView11 = null;
            }
            commonUtils.clearMiBlurBlendEffect(imageView11);
            MiPlaySeekBar miPlaySeekBar3 = this.seekBar;
            if (miPlaySeekBar3 == null) {
                kotlin.jvm.internal.n.w("seekBar");
                miPlaySeekBar3 = null;
            }
            commonUtils.clearMiBlurBlendEffect(miPlaySeekBar3);
            MiPlayVolumeBar miPlayVolumeBar3 = this.volumeBar;
            if (miPlayVolumeBar3 == null) {
                kotlin.jvm.internal.n.w("volumeBar");
                miPlayVolumeBar3 = null;
            }
            commonUtils.clearMiBlurBlendEffect(miPlayVolumeBar3);
            MiPlayVolumeBar miPlayVolumeBar4 = this.volumeBar;
            if (miPlayVolumeBar4 == null) {
                kotlin.jvm.internal.n.w("volumeBar");
                miPlayVolumeBar4 = null;
            }
            Resources resources = getContext().getResources();
            int i2 = R.color.miplay_volume_bar_bg_color;
            miPlayVolumeBar4.setBackgroundPrimaryColor(resources.getColor(i2), getContext().getResources().getColor(i2));
            ImageView imageView12 = this.volumeIcon;
            if (imageView12 == null) {
                kotlin.jvm.internal.n.w("volumeIcon");
                imageView12 = null;
            }
            commonUtils.clearMiBlurBlendEffect(imageView12);
        }
        ImageView imageView13 = this.prev;
        if (imageView13 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView13 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView13);
        ImageView imageView14 = this.play;
        if (imageView14 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
            imageView14 = null;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView14);
        ImageView imageView15 = this.next;
        if (imageView15 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
        } else {
            imageView2 = imageView15;
        }
        ViewAnimExtentionsKt.setTouchAnim(imageView2);
        initColumn();
        initUIAccessibility();
    }

    private final void initUIAccessibility() {
        View view = this.videoRewind;
        ImageView imageView = null;
        if (view == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view = null;
        }
        view.setContentDescription(getContext().getString(R.string.miplay_accessibility_video_rewind, 15));
        View view2 = this.videoFastForward;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view2 = null;
        }
        view2.setContentDescription(getContext().getString(R.string.miplay_accessibility_video_fastForward, 15));
        ImageView imageView2 = this.deviceIconView;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w("deviceIconView");
            imageView2 = null;
        }
        imageView2.setContentDescription(" ");
        TextView textView = this.speed;
        if (textView == null) {
            kotlin.jvm.internal.n.w("speed");
            textView = null;
        }
        textView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUIAccessibility.1
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setText("");
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
        View view3 = this.videoRewind;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view3 = null;
        }
        view3.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUIAccessibility.2
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
        View view4 = this.videoFastForward;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view4 = null;
        }
        view4.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUIAccessibility.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
        ImageView imageView3 = this.prev;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView3 = null;
        }
        imageView3.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUIAccessibility.4
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
        ImageView imageView4 = this.next;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
        } else {
            imageView = imageView4;
        }
        imageView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initUIAccessibility.5
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initVideoSpeed() {
        m0.w wVarB;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
        if (value != null && value.getMediaType() == 0) {
            Log.e(TAG, "initVideoSpeed failed,is audio meta");
            return;
        }
        resetSpeedEnable();
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(miPlayDetailViewModel, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.l();
        }
        Float value2 = miPlayDetailViewModel.getMPlaySpeed().getValue();
        kotlin.jvm.internal.n.d(value2);
        float fFloatValue = value2.floatValue();
        initVideoSpeedArrays();
        resetVideoSpeedIndex(SPEED_ARRAYS, fFloatValue);
        setSpeedText();
    }

    private final void initVideoSpeedArrays() {
        m0.w wVarB;
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(MiPlayDetailViewModel.INSTANCE, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            wVarB.i();
        }
        SPEED_ARRAYS = I0.m.k(Float.valueOf(1.0f));
    }

    private final void initVolumeSeekbar() {
        MiPlayDeviceVolumeBar miPlayDeviceVolumeBar = MiPlayDeviceVolumeBar.INSTANCE;
        MiPlayVolumeBar miPlayVolumeBar = this.volumeBar;
        MiPlayVolumeBar miPlayVolumeBar2 = null;
        if (miPlayVolumeBar == null) {
            kotlin.jvm.internal.n.w("volumeBar");
            miPlayVolumeBar = null;
        }
        miPlayDeviceVolumeBar.setTotalVolumeBar(miPlayVolumeBar);
        MiPlayVolumeBar miPlayVolumeBar3 = this.volumeBar;
        if (miPlayVolumeBar3 == null) {
            kotlin.jvm.internal.n.w("volumeBar");
        } else {
            miPlayVolumeBar2 = miPlayVolumeBar3;
        }
        miPlayVolumeBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.QSControlMiPlayDetailHeader.initVolumeSeekbar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i2, boolean z2) {
                if (z2) {
                    MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
                    miPlayDetailViewModel.getMOverAllVolumeProgress().setValue(Integer.valueOf(i2));
                    miPlayDetailViewModel.doSetOverallVolume(i2);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MiPlayDetailViewModel.INSTANCE.setMOverAllVolumeBarUserTouching(true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                MiPlayDetailViewModel.INSTANCE.setMOverAllVolumeBarUserTouching(false);
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_VOLUME_BAR, MiPlayEventsKt.PAGE_MIPLAY_CARD, QSControlMiPlayDetailHeader.this.mRef);
            }
        });
    }

    private final boolean isVideoMediaType() {
        MediaMetaData value;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        return miPlayDetailViewModel.hasMediaData() && (value = miPlayDetailViewModel.getMMediaMetaData().getValue()) != null && value.getMediaType() == 1;
    }

    private final void onClickPlay() {
        m0.w wVarB;
        String cpDeepLink;
        Log.d(TAG, "play click");
        this.prevNextTouchTime = 0L;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        m0.w wVar = null;
        C0466a targetSession$default = MiPlayDetailViewModel.getTargetSession$default(miPlayDetailViewModel, null, 1, null);
        if (targetSession$default != null && (wVarB = targetSession$default.b()) != null) {
            if (!miPlayDetailViewModel.allowControlRemoteTV() && (cpDeepLink = miPlayDetailViewModel.getCpDeepLink()) != null && cpDeepLink.length() != 0) {
                Companion companion = Companion;
                if (companion.hasDeepLinkPermission()) {
                    Context context = getContext();
                    kotlin.jvm.internal.n.f(context, "getContext(...)");
                    companion.jumpLastPlayingApp(context);
                    MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_MIPLAY_CARD, this.mRef);
                    return;
                }
            }
            if (miPlayDetailViewModel.isLocalPlaying()) {
                wVarB.p();
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_MIPLAY_CARD, this.mRef);
            } else {
                wVarB.q();
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_MIPLAY_CARD, this.mRef);
            }
            wVar = wVarB;
        }
        if (wVar == null) {
            Companion companion2 = Companion;
            Context context2 = getContext();
            kotlin.jvm.internal.n.f(context2, "getContext(...)");
            companion2.jumpLastPlayingApp(context2);
            if (miPlayDetailViewModel.isLocalPlaying()) {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_MIPLAY_CARD, this.mRef);
            } else {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_MIPLAY_CARD, this.mRef);
            }
        }
    }

    private static final int onOrientationChanged$lambda$16$getTopMargin(int i2, ConstraintLayout constraintLayout, QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        if (i2 == 1) {
            Context context = constraintLayout.getContext();
            if (context == null || (resources3 = context.getResources()) == null) {
                return 0;
            }
            return resources3.getDimensionPixelSize(R.dimen.miplay_detail_header_seek_bar_margin_top);
        }
        View view = qSControlMiPlayDetailHeader.tvController;
        if (view == null) {
            kotlin.jvm.internal.n.w("tvController");
            view = null;
        }
        if (view.getVisibility() == 0) {
            Context context2 = constraintLayout.getContext();
            if (context2 == null || (resources2 = context2.getResources()) == null) {
                return 0;
            }
            return resources2.getDimensionPixelSize(R.dimen.miplay_detail_header_seek_bar_margin_top_land_with_tv_control_panel_visible);
        }
        Context context3 = constraintLayout.getContext();
        if (context3 == null || (resources = context3.getResources()) == null) {
            return 0;
        }
        return resources.getDimensionPixelSize(R.dimen.miplay_detail_header_seek_bar_margin_top_with_tv_control_panel_hide);
    }

    private static final int onOrientationChanged$lambda$25$getTopMargin$24(int i2, View view, QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        if (i2 == 1) {
            Context context = view.getContext();
            if (context == null || (resources3 = context.getResources()) == null) {
                return 0;
            }
            return resources3.getDimensionPixelSize(R.dimen.miplay_detail_header_volume_bar_margin_top_land);
        }
        View view2 = qSControlMiPlayDetailHeader.tvController;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("tvController");
            view2 = null;
        }
        if (view2.getVisibility() == 0) {
            Context context2 = view.getContext();
            if (context2 == null || (resources2 = context2.getResources()) == null) {
                return 0;
            }
            return resources2.getDimensionPixelSize(R.dimen.miplay_detail_header_volume_bar_margin_top_with_tv_panel_visible);
        }
        Context context3 = view.getContext();
        if (context3 == null || (resources = context3.getResources()) == null) {
            return 0;
        }
        return resources.getDimensionPixelSize(R.dimen.miplay_detail_header_volume_bar_margin_top_with_tv_panel_hide);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void performFlip(final android.graphics.Bitmap r6) {
        /*
            r5 = this;
            miuix.transition.FlipAnimation r0 = r5.flipAnimation
            com.android.systemui.MiPlayDetailViewModel r1 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
            boolean r2 = r1.isHighDevice()
            r3 = 1
            if (r2 == 0) goto L1c
            android.content.Context r2 = r5.getContext()
            java.lang.String r4 = "getContext(...)"
            kotlin.jvm.internal.n.f(r2, r4)
            boolean r2 = miui.systemui.util.MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(r2)
            if (r2 == 0) goto L1c
            r2 = r3
            goto L1d
        L1c:
            r2 = 0
        L1d:
            r0.setBlurEnabled(r2)
            boolean r0 = r1.isHighDevice()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "performFlip: start flip "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "QSControlMiPlayDetailHeader"
            android.util.Log.d(r1, r0)
            miuix.transition.FlipAnimation r0 = r5.flipAnimation
            android.view.ViewGroup r1 = r5.coverParent
            if (r1 != 0) goto L46
            java.lang.String r1 = "coverParent"
            kotlin.jvm.internal.n.w(r1)
            r1 = 0
        L46:
            boolean r2 = r5.nextMedia
            r0.flip(r1, r2)
            miuix.transition.FlipAnimation r0 = r5.flipAnimation
            com.android.systemui.O r1 = new com.android.systemui.O
            r1.<init>()
            r0.setFlipListener(r1)
            r5.nextMedia = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader.performFlip(android.graphics.Bitmap):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void performFlip$lambda$0(QSControlMiPlayDetailHeader this$0, Bitmap bitmap, boolean z2, float f2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "performFlip: flip change");
        setCover$default(this$0, bitmap, true, false, 4, null);
    }

    private final void removeViewFromParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetMediaControllerAbility() {
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        boolean zAllowControlRemoteTV = miPlayDetailViewModel.allowControlRemoteTV();
        resetSpeedEnable();
        ImageView imageView = this.openRemoteTvView;
        MiPlaySeekBar miPlaySeekBar = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w("openRemoteTvView");
            imageView = null;
        }
        imageView.setEnabled(zAllowControlRemoteTV);
        MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
        boolean z2 = false;
        boolean zIsSequel = value != null ? value.isSequel() : false;
        ImageView imageView2 = this.videoNext;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w("videoNext");
            imageView2 = null;
        }
        if (zAllowControlRemoteTV && zIsSequel) {
            z2 = true;
        }
        imageView2.setEnabled(z2);
        View view = this.videoRewind;
        if (view == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view = null;
        }
        view.setEnabled(zAllowControlRemoteTV);
        ImageView imageView3 = this.videoPlay;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w("videoPlay");
            imageView3 = null;
        }
        imageView3.setEnabled(true);
        View view2 = this.videoFastForward;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view2 = null;
        }
        view2.setEnabled(zAllowControlRemoteTV);
        setVideoPreNextBlur(!zAllowControlRemoteTV);
        MiPlaySeekBar miPlaySeekBar2 = this.seekBar;
        if (miPlaySeekBar2 == null) {
            kotlin.jvm.internal.n.w("seekBar");
        } else {
            miPlaySeekBar = miPlaySeekBar2;
        }
        miPlaySeekBar.setEnabled(zAllowControlRemoteTV);
        setSeekbarColor();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void resetSpeedEnable() {
        /*
            r4 = this;
            com.android.systemui.MiPlayDetailViewModel r0 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
            boolean r0 = r0.allowControlRemoteTV()
            android.widget.TextView r1 = r4.speed
            java.lang.String r2 = "speed"
            r3 = 0
            if (r1 != 0) goto L11
            kotlin.jvm.internal.n.w(r2)
            r1 = r3
        L11:
            if (r0 == 0) goto L25
            com.android.systemui.TvManger r0 = r4.tvManager
            if (r0 != 0) goto L1d
            java.lang.String r0 = "tvManager"
            kotlin.jvm.internal.n.w(r0)
            r0 = r3
        L1d:
            boolean r0 = r0.supportVideoFlowCapacity()
            if (r0 == 0) goto L25
            r0 = 1
            goto L26
        L25:
            r0 = 0
        L26:
            r1.setEnabled(r0)
            android.widget.TextView r0 = r4.speed
            if (r0 != 0) goto L31
            kotlin.jvm.internal.n.w(r2)
            r0 = r3
        L31:
            android.content.Context r1 = r4.getContext()
            android.widget.TextView r4 = r4.speed
            if (r4 != 0) goto L3d
            kotlin.jvm.internal.n.w(r2)
            goto L3e
        L3d:
            r3 = r4
        L3e:
            boolean r4 = r3.isEnabled()
            if (r4 == 0) goto L47
            int r4 = com.android.systemui.miplay.R.color.miplay_detail_item_tv_control_panel_speed_text_color
            goto L49
        L47:
            int r4 = com.android.systemui.miplay.R.color.miplay_detail_item_tv_control_panel_speed_text_color_disable
        L49:
            int r4 = r1.getColor(r4)
            r0.setTextColor(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader.resetSpeedEnable():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetVideoSpeedIndex(List<Float> list, float f2) {
        int iIndexOf = list.indexOf(Float.valueOf(f2));
        this.videoPlaySpeedIndex = iIndexOf;
        if (iIndexOf == -1) {
            this.videoPlaySpeedIndex = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setCover(android.graphics.Bitmap r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            java.lang.String r2 = "QSControlMiPlayDetailHeader"
            r3 = 0
            java.lang.String r4 = "cover"
            if (r8 == 0) goto L87
            boolean r5 = r7.hasCover
            if (r5 != 0) goto L54
            com.android.systemui.folme.ImageAlphaSwitchAnimation$Companion r5 = com.android.systemui.folme.ImageAlphaSwitchAnimation.Companion
            android.widget.ImageView r6 = r7.cover
            if (r6 != 0) goto L17
            kotlin.jvm.internal.n.w(r4)
            r6 = r3
        L17:
            boolean r5 = r5.isInAlphaAnim(r6)
            if (r5 != 0) goto L54
            if (r9 != 0) goto L54
            if (r10 == 0) goto L54
            java.lang.String r9 = "setCover: use alpha anim"
            android.util.Log.d(r2, r9)
            com.android.systemui.folme.ImageAlphaSwitchAnimation r9 = r7.imageAlphaSwitchAnimation
            com.android.systemui.MiPlayDetailViewModel r10 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
            boolean r10 = r10.isHighDevice()
            if (r10 == 0) goto L41
            android.content.Context r10 = r7.getContext()
            java.lang.String r2 = "getContext(...)"
            kotlin.jvm.internal.n.f(r10, r2)
            boolean r10 = miui.systemui.util.MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(r10)
            if (r10 == 0) goto L41
            r10 = r0
            goto L42
        L41:
            r10 = r1
        L42:
            r9.setBlurEnabled(r10)
            com.android.systemui.folme.ImageAlphaSwitchAnimation r9 = r7.imageAlphaSwitchAnimation
            android.widget.ImageView r10 = r7.cover
            if (r10 != 0) goto L4f
            kotlin.jvm.internal.n.w(r4)
            goto L50
        L4f:
            r3 = r10
        L50:
            r9.switchWithValue(r3, r8)
            goto La5
        L54:
            if (r9 == 0) goto L75
            java.lang.String r9 = "setCover: fromFlip"
            android.util.Log.d(r2, r9)
            com.android.systemui.folme.ImageAlphaSwitchAnimation r9 = r7.imageAlphaSwitchAnimation
            android.widget.ImageView r10 = r7.cover
            if (r10 != 0) goto L65
            kotlin.jvm.internal.n.w(r4)
            r10 = r3
        L65:
            r9.reset(r10)
            android.widget.ImageView r9 = r7.cover
            if (r9 != 0) goto L70
            kotlin.jvm.internal.n.w(r4)
            goto L71
        L70:
            r3 = r9
        L71:
            r3.setImageBitmap(r8)
            goto La5
        L75:
            java.lang.String r9 = "setCover: not fromFlip"
            android.util.Log.d(r2, r9)
            android.widget.ImageView r9 = r7.cover
            if (r9 != 0) goto L82
            kotlin.jvm.internal.n.w(r4)
            goto L83
        L82:
            r3 = r9
        L83:
            r3.setImageBitmap(r8)
            goto La5
        L87:
            java.lang.String r9 = "setCover: bitmap is null"
            android.util.Log.d(r2, r9)
            com.android.systemui.folme.ImageAlphaSwitchAnimation r9 = r7.imageAlphaSwitchAnimation
            android.widget.ImageView r10 = r7.cover
            if (r10 != 0) goto L96
            kotlin.jvm.internal.n.w(r4)
            r10 = r3
        L96:
            r9.reset(r10)
            android.widget.ImageView r9 = r7.cover
            if (r9 != 0) goto La1
            kotlin.jvm.internal.n.w(r4)
            goto La2
        La1:
            r3 = r9
        La2:
            r3.setImageResource(r1)
        La5:
            if (r8 == 0) goto La8
            goto La9
        La8:
            r0 = r1
        La9:
            r7.hasCover = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader.setCover(android.graphics.Bitmap, boolean, boolean):void");
    }

    public static /* synthetic */ void setCover$default(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, Bitmap bitmap, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        qSControlMiPlayDetailHeader.setCover(bitmap, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPreNextBlur(boolean z2) {
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        ImageView imageView = null;
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context) || !z2) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            ImageView imageView2 = this.prev;
            if (imageView2 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
                imageView2 = null;
            }
            commonUtils.clearMiBlurBlendEffect(imageView2);
            ImageView imageView3 = this.next;
            if (imageView3 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            } else {
                imageView = imageView3;
            }
            commonUtils.clearMiBlurBlendEffect(imageView);
            return;
        }
        ImageView imageView4 = this.prev;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView4 = null;
        }
        MiBlurCompat.setMiViewBlurModeCompat(imageView4, 3);
        ImageView imageView5 = this.prev;
        if (imageView5 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView5 = null;
        }
        MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
        MiBlurCompat.setMiBackgroundBlendColors(imageView5, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
        ImageView imageView6 = this.next;
        if (imageView6 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            imageView6 = null;
        }
        MiBlurCompat.setMiViewBlurModeCompat(imageView6, 3);
        ImageView imageView7 = this.next;
        if (imageView7 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
        } else {
            imageView = imageView7;
        }
        MiBlurCompat.setMiBackgroundBlendColors(imageView, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeekbarColor() {
        MiPlaySeekBar miPlaySeekBar = this.seekBar;
        MiPlaySeekBar miPlaySeekBar2 = null;
        if (miPlaySeekBar == null) {
            kotlin.jvm.internal.n.w("seekBar");
            miPlaySeekBar = null;
        }
        if (miPlaySeekBar.isEnabled()) {
            MiPlaySeekBar miPlaySeekBar3 = this.seekBar;
            if (miPlaySeekBar3 == null) {
                kotlin.jvm.internal.n.w("seekBar");
                miPlaySeekBar3 = null;
            }
            miPlaySeekBar3.setForegroundPrimaryColor(getContext().getResources().getColor(R.color.miplay_media_progress_bar_foreground_primary_color));
            MiPlaySeekBar miPlaySeekBar4 = this.seekBar;
            if (miPlaySeekBar4 == null) {
                kotlin.jvm.internal.n.w("seekBar");
            } else {
                miPlaySeekBar2 = miPlaySeekBar4;
            }
            miPlaySeekBar2.setForegroundPrimaryDisableColor(getContext().getResources().getColor(R.color.miplay_media_progress_bar_foreground_primary_disable_color));
            return;
        }
        MiPlaySeekBar miPlaySeekBar5 = this.seekBar;
        if (miPlaySeekBar5 == null) {
            kotlin.jvm.internal.n.w("seekBar");
            miPlaySeekBar5 = null;
        }
        Resources resources = getContext().getResources();
        int i2 = R.color.transparent;
        miPlaySeekBar5.setForegroundPrimaryColor(resources.getColor(i2));
        MiPlaySeekBar miPlaySeekBar6 = this.seekBar;
        if (miPlaySeekBar6 == null) {
            kotlin.jvm.internal.n.w("seekBar");
        } else {
            miPlaySeekBar2 = miPlaySeekBar6;
        }
        miPlaySeekBar2.setForegroundPrimaryDisableColor(getContext().getResources().getColor(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setShowing$lambda$15(QSControlMiPlayDetailHeader this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.setFocusable(true);
        this$0.setFocusableInTouchMode(true);
        this$0.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SetTextI18n"})
    public final void setSpeedText() {
        List<Float> list = SPEED_ARRAYS;
        float fFloatValue = list.get(this.videoPlaySpeedIndex % list.size()).floatValue();
        TextView textView = this.speed;
        TextView textView2 = null;
        if (textView == null) {
            kotlin.jvm.internal.n.w("speed");
            textView = null;
        }
        textView.setText(fFloatValue + "X");
        TextView textView3 = this.speed;
        if (textView3 == null) {
            kotlin.jvm.internal.n.w("speed");
        } else {
            textView2 = textView3;
        }
        textView2.setContentDescription(getContext().getString(R.string.miplay_video_speed, String.valueOf(fFloatValue)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTvControlVisibility(boolean z2) {
        View view = this.tvController;
        if (view == null) {
            kotlin.jvm.internal.n.w("tvController");
            view = null;
        }
        view.setVisibility(z2 ? 0 : 8);
        TVControllerVisibilityCallback tVControllerVisibilityCallback = this.tvControllerVisibilityCallback;
        if (tVControllerVisibilityCallback != null) {
            tVControllerVisibilityCallback.onTvControllerVisibilityChanged(z2);
        }
    }

    private final void setVideoPreNextBlur(boolean z2) {
        Context context = getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        View view = null;
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(context) || !z2) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View view2 = this.videoRewind;
            if (view2 == null) {
                kotlin.jvm.internal.n.w("videoRewind");
                view2 = null;
            }
            commonUtils.clearMiBlurBlendEffect(view2);
            View view3 = this.videoFastForward;
            if (view3 == null) {
                kotlin.jvm.internal.n.w("videoFastForward");
            } else {
                view = view3;
            }
            commonUtils.clearMiBlurBlendEffect(view);
            return;
        }
        View view4 = this.videoRewind;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view4 = null;
        }
        MiBlurCompat.setMiViewBlurModeCompat(view4, 3);
        View view5 = this.videoRewind;
        if (view5 == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view5 = null;
        }
        MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
        MiBlurCompat.setMiBackgroundBlendColors(view5, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
        View view6 = this.videoFastForward;
        if (view6 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view6 = null;
        }
        MiBlurCompat.setMiViewBlurModeCompat(view6, 3);
        View view7 = this.videoFastForward;
        if (view7 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
        } else {
            view = view7;
        }
        MiBlurCompat.setMiBackgroundBlendColors(view, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
    }

    private final void startPlayAnim(ImageView imageView) {
        Drawable drawable = imageView != null ? imageView.getDrawable() : null;
        Animatable2 animatable2 = drawable instanceof Animatable2 ? (Animatable2) drawable : null;
        if (animatable2 != null) {
            animatable2.start();
        }
    }

    private final void updateCover(MediaMetaData mediaMetaData, boolean z2) {
        MediaMetaData mediaMetaData2;
        Integer numValueOf;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
        if (value != null) {
            numValueOf = Integer.valueOf(value.getMediaType());
            mediaMetaData2 = mediaMetaData;
        } else {
            mediaMetaData2 = mediaMetaData;
            numValueOf = null;
        }
        String mediaID = MiPlayExtentionsKt.getMediaID(mediaMetaData2, numValueOf);
        Log.d(TAG, "updateCover: art " + mediaMetaData.getArt() + " id " + mediaID + "  " + miPlayDetailViewModel.getMMediaIDForCover() + " " + z2);
        Bitmap art = mediaMetaData.getArt();
        boolean zC = kotlin.jvm.internal.n.c(miPlayDetailViewModel.getMMediaIDForCover(), mediaID);
        boolean z3 = z2 && !zC && !miPlayDetailViewModel.isLowDevice() && this.useCoverAnim;
        ViewGroup viewGroup = this.coverParent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup = null;
        }
        if (FlipAnimation.isInFlipAnimation(viewGroup)) {
            Log.d(TAG, "updateCover is in flip anim");
            InterfaceC0380l0 interfaceC0380l0 = this.animUpdateCoverJob;
            if (interfaceC0380l0 != null) {
                InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
            }
            this.animUpdateCoverJob = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new C02541(zC, art, mediaID, null), 3, null);
            return;
        }
        InterfaceC0380l0 interfaceC0380l02 = this.animUpdateCoverJob;
        if (interfaceC0380l02 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l02, null, 1, null);
        }
        if (z3) {
            miPlayDetailViewModel.setMMediaIDForCover(mediaID);
            performFlip(art);
            return;
        }
        Log.d(TAG, "updateCover default " + miPlayDetailViewModel.isLowDevice());
        setCover$default(this, art, false, z2, 2, null);
        miPlayDetailViewModel.setMMediaIDForCover(mediaID);
        miPlayDetailViewModel.setMFirstLoadCover(false);
    }

    public static /* synthetic */ void updateCover$default(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, MediaMetaData mediaMetaData, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = !MiPlayDetailViewModel.INSTANCE.getMFirstLoadCover();
        }
        qSControlMiPlayDetailHeader.updateCover(mediaMetaData, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMediaTypeContainerVisibility(int i2) {
        boolean zIsAudioType = MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(i2));
        ViewGroup viewGroup = this.audioContainer;
        ViewGroup viewGroup2 = null;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("audioContainer");
            viewGroup = null;
        }
        viewGroup.setVisibility(zIsAudioType ? 0 : 8);
        ViewGroup viewGroup3 = this.videoContainer;
        if (viewGroup3 == null) {
            kotlin.jvm.internal.n.w("videoContainer");
        } else {
            viewGroup2 = viewGroup3;
        }
        viewGroup2.setVisibility(zIsAudioType ? 8 : 0);
    }

    public static /* synthetic */ void updateMediaTypeContainerVisibility$default(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        qSControlMiPlayDetailHeader.updateMediaTypeContainerVisibility(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d8 A[PHI: r0
      0x00d8: PHI (r0v4 android.widget.ImageView) = (r0v3 android.widget.ImageView), (r0v5 android.widget.ImageView) binds: [B:52:0x00dc, B:48:0x00d2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updatePlayButton() {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader.updatePlayButton():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePlayButtonWithoutAnim() {
        Log.i(TAG, "updatePlayButtonForMediaTypeChange");
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        ImageView imageView = null;
        if (miPlayDetailViewModel.isLocalPlaying()) {
            ImageView imageView2 = this.play;
            if (imageView2 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                imageView2 = null;
            }
            int i2 = R.drawable.miplay_detail_pause;
            imageView2.setImageResource(i2);
            ImageView imageView3 = this.play;
            if (imageView3 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                imageView3 = null;
            }
            Context context = getContext();
            int i3 = R.string.miplay_accessibility_pause;
            imageView3.setContentDescription(context.getString(i3));
            ImageView imageView4 = this.videoPlay;
            if (imageView4 == null) {
                kotlin.jvm.internal.n.w("videoPlay");
                imageView4 = null;
            }
            imageView4.setImageResource(i2);
            ImageView imageView5 = this.videoPlay;
            if (imageView5 == null) {
                kotlin.jvm.internal.n.w("videoPlay");
            } else {
                imageView = imageView5;
            }
            imageView.setContentDescription(getContext().getString(i3));
            setTag(this.TAG_PLAY_STATE, Boolean.TRUE);
            return;
        }
        if (miPlayDetailViewModel.isLocalPausing()) {
            ImageView imageView6 = this.play;
            if (imageView6 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                imageView6 = null;
            }
            int i4 = R.drawable.miplay_detail_play;
            imageView6.setImageResource(i4);
            ImageView imageView7 = this.play;
            if (imageView7 == null) {
                kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
                imageView7 = null;
            }
            Context context2 = getContext();
            int i5 = R.string.miplay_accessibility_play;
            imageView7.setContentDescription(context2.getString(i5));
            ImageView imageView8 = this.videoPlay;
            if (imageView8 == null) {
                kotlin.jvm.internal.n.w("videoPlay");
                imageView8 = null;
            }
            imageView8.setImageResource(i4);
            ImageView imageView9 = this.videoPlay;
            if (imageView9 == null) {
                kotlin.jvm.internal.n.w("videoPlay");
            } else {
                imageView = imageView9;
            }
            imageView.setContentDescription(getContext().getString(i5));
            setTag(this.TAG_PLAY_STATE, Boolean.FALSE);
        }
    }

    public final void adaptFlipDevice(boolean z2) {
        int i2 = R.id.container_audio;
        View viewRequireViewById = requireViewById(i2);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        kotlin.jvm.internal.n.d(viewRequireViewById);
        CommonUtils.setLayoutWidth$default(commonUtils, viewRequireViewById, z2 ? -1 : 0, false, 2, null);
        MiPlaySeekBar miPlaySeekBar = this.seekBar;
        ImageView imageView = null;
        if (miPlaySeekBar == null) {
            kotlin.jvm.internal.n.w("seekBar");
            miPlaySeekBar = null;
        }
        miPlaySeekBar.setVisibility(z2 ? 8 : 0);
        MediaTimeTextView mediaTimeTextView = this.mediaElapsedTime;
        if (mediaTimeTextView == null) {
            kotlin.jvm.internal.n.w("mediaElapsedTime");
            mediaTimeTextView = null;
        }
        mediaTimeTextView.setVisibility(z2 ? 8 : 0);
        MediaTimeTextView mediaTimeTextView2 = this.mediaTotalTime;
        if (mediaTimeTextView2 == null) {
            kotlin.jvm.internal.n.w("mediaTotalTime");
            mediaTimeTextView2 = null;
        }
        mediaTimeTextView2.setVisibility(z2 ? 8 : 0);
        int dimensionPixelSize = getResources().getDimensionPixelSize(z2 ? R.dimen.miplay_detail_header_cover_size_n8 : R.dimen.miplay_detail_header_cover_size);
        ViewGroup viewGroup = this.coverParent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup = null;
        }
        commonUtils.setLayoutSize(viewGroup, dimensionPixelSize, dimensionPixelSize, true);
        ImageView imageView2 = this.cover;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
        } else {
            imageView = imageView2;
        }
        commonUtils.setLayoutSize(imageView, dimensionPixelSize, dimensionPixelSize, true);
        if (z2) {
            removeViewFromParent(viewRequireViewById);
            CommonUtils.setMarginHorizontal$default(commonUtils, viewRequireViewById, 0, false, 2, null);
            addView(viewRequireViewById, 1);
            return;
        }
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_detail_header_audio_container_margin_start);
        int dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_detail_header_audio_container_margin_end);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.constrainWidth(i2, 0);
        constraintSet.constrainHeight(i2, -2);
        int i3 = R.id.coverParent;
        constraintSet.connect(i2, 6, i3, 7, dimensionPixelSize2);
        constraintSet.connect(i2, 7, 0, 7, dimensionPixelSize3);
        constraintSet.connect(i2, 4, i3, 4);
        if (getContainerLayout() instanceof ConstraintLayout) {
            View containerLayout = getContainerLayout();
            kotlin.jvm.internal.n.e(containerLayout, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
            ConstraintLayout constraintLayout = (ConstraintLayout) containerLayout;
            removeViewFromParent(viewRequireViewById);
            constraintLayout.addView(viewRequireViewById);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        kotlin.jvm.internal.n.g(event, "event");
        int keyCode = event.getKeyCode();
        if (event.getAction() != 0 || (keyCode != 24 && keyCode != 25)) {
            return super.dispatchKeyEvent(event);
        }
        MiPlayDetailViewModel.INSTANCE.doAdjustVolume(event.getKeyCode() == 24);
        return true;
    }

    public final View getContainerLayout() {
        View view = this.containerLayout;
        if (view != null) {
            return view;
        }
        kotlin.jvm.internal.n.w("containerLayout");
        return null;
    }

    public final float getCoverRadius() {
        return this.coverRadius;
    }

    public final HeaderSizeCallback getHeaderSizeCallback() {
        return this.headerSizeCallback;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final boolean getPanelAnimHiding() {
        return this.panelAnimHiding;
    }

    public final TVControllerVisibilityCallback getTvControllerVisibilityCallback() {
        return this.tvControllerVisibilityCallback;
    }

    public final boolean getUseCoverAnim() {
        return this.useCoverAnim;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void hiddenAnim() {
        this.panelAnimHiding = false;
    }

    public final boolean isAudioPlaying() {
        MediaTypeUtils mediaTypeUtils = MediaTypeUtils.INSTANCE;
        MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
        return mediaTypeUtils.isAudioType(Integer.valueOf(value != null ? value.getMediaType() : 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
        requestFocus();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
        TextView textView = this.speed;
        MiPlayVolumeIconAnim miPlayVolumeIconAnim = null;
        if (textView == null) {
            kotlin.jvm.internal.n.w("speed");
            textView = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(textView);
        ImageView imageView = this.videoNext;
        if (imageView == null) {
            kotlin.jvm.internal.n.w("videoNext");
            imageView = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView);
        ImageView imageView2 = this.openRemoteTvView;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w("openRemoteTvView");
            imageView2 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView2);
        ImageView imageView3 = this.prev;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView3 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView3);
        ImageView imageView4 = this.play;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
            imageView4 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView4);
        ImageView imageView5 = this.next;
        if (imageView5 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            imageView5 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView5);
        View view = this.videoRewind;
        if (view == null) {
            kotlin.jvm.internal.n.w("videoRewind");
            view = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(view);
        ImageView imageView6 = this.videoPlay;
        if (imageView6 == null) {
            kotlin.jvm.internal.n.w("videoPlay");
            imageView6 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(imageView6);
        View view2 = this.videoFastForward;
        if (view2 == null) {
            kotlin.jvm.internal.n.w("videoFastForward");
            view2 = null;
        }
        ViewAnimExtentionsKt.clearTouchAnim(view2);
        MiPlayVolumeIconAnim miPlayVolumeIconAnim2 = this.miPlayVolumeIconAnim;
        if (miPlayVolumeIconAnim2 == null) {
            kotlin.jvm.internal.n.w("miPlayVolumeIconAnim");
        } else {
            miPlayVolumeIconAnim = miPlayVolumeIconAnim2;
        }
        miPlayVolumeIconAnim.release();
        this.imageAlphaSwitchAnimation.release();
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void onOrientationChanged(int i2) {
        View view;
        View view2;
        Resources resources;
        Resources resources2;
        Resources resources3;
        Resources resources4;
        Resources resources5;
        Resources resources6;
        Resources resources7;
        Resources resources8;
        Resources resources9;
        Resources resources10;
        Log.d(TAG, "onOrientationChanged orientation " + i2);
        ConstraintLayout constraintLayout = this.progressContainer;
        if (constraintLayout == null) {
            kotlin.jvm.internal.n.w("progressContainer");
            constraintLayout = null;
        }
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        commonUtils.setMarginTop(constraintLayout, onOrientationChanged$lambda$16$getTopMargin(i2, constraintLayout, this), true);
        View view3 = this.tvController;
        if (view3 == null) {
            kotlin.jvm.internal.n.w("tvController");
            view = null;
        } else {
            view = view3;
        }
        Context context = view.getContext();
        int dimensionPixelSize = 0;
        CommonUtils.setMarginTop$default(commonUtils, view, (context == null || (resources10 = context.getResources()) == null) ? 0 : resources10.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_top_margin), false, 2, null);
        ImageView imageView = this.openRemoteTvView;
        if (imageView == null) {
            kotlin.jvm.internal.n.w("openRemoteTvView");
            imageView = null;
        }
        Context context2 = imageView.getContext();
        CommonUtils.setLayoutWidth$default(commonUtils, imageView, (context2 == null || (resources9 = context2.getResources()) == null) ? 0 : resources9.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_tv_icon_width_land), false, 2, null);
        Context context3 = imageView.getContext();
        CommonUtils.setLayoutHeight$default(commonUtils, imageView, (context3 == null || (resources8 = context3.getResources()) == null) ? 0 : resources8.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_icon_height_land), false, 2, null);
        TextView textView = this.speed;
        if (textView == null) {
            kotlin.jvm.internal.n.w("speed");
            textView = null;
        }
        Context context4 = textView.getContext();
        CommonUtils.setLayoutWidth$default(commonUtils, textView, (context4 == null || (resources7 = context4.getResources()) == null) ? 0 : resources7.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_icon_width_land), false, 2, null);
        Context context5 = textView.getContext();
        CommonUtils.setLayoutHeight$default(commonUtils, textView, (context5 == null || (resources6 = context5.getResources()) == null) ? 0 : resources6.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_icon_height_land), false, 2, null);
        ImageView imageView2 = this.videoNext;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w("videoNext");
            imageView2 = null;
        }
        Context context6 = imageView2.getContext();
        CommonUtils.setLayoutWidth$default(commonUtils, imageView2, (context6 == null || (resources5 = context6.getResources()) == null) ? 0 : resources5.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_icon_width_land), false, 2, null);
        Context context7 = imageView2.getContext();
        CommonUtils.setLayoutHeight$default(commonUtils, imageView2, (context7 == null || (resources4 = context7.getResources()) == null) ? 0 : resources4.getDimensionPixelSize(R.dimen.miplay_detail_item_tv_control_panel_icon_height_land), false, 2, null);
        TextView textView2 = this.title;
        if (textView2 == null) {
            kotlin.jvm.internal.n.w("title");
            textView2 = null;
        }
        Context context8 = textView2.getContext();
        commonUtils.setMarginStart(textView2, (context8 == null || (resources3 = context8.getResources()) == null) ? 0 : resources3.getDimensionPixelSize(R.dimen.miplay_detail_header_meta_left_margin_land), true);
        TextView textView3 = this.titleNoPlayback;
        if (textView3 == null) {
            kotlin.jvm.internal.n.w("titleNoPlayback");
            textView3 = null;
        }
        Context context9 = textView3.getContext();
        commonUtils.setMarginStart(textView3, (context9 == null || (resources2 = context9.getResources()) == null) ? 0 : resources2.getDimensionPixelSize(R.dimen.miplay_detail_header_meta_left_margin_land), true);
        TextView textView4 = this.subtitle;
        if (textView4 == null) {
            kotlin.jvm.internal.n.w("subtitle");
            textView4 = null;
        }
        Context context10 = textView4.getContext();
        if (context10 != null && (resources = context10.getResources()) != null) {
            dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.miplay_detail_header_meta_left_margin_land);
        }
        commonUtils.setMarginStart(textView4, dimensionPixelSize, true);
        View view4 = this.volumeBarContainer;
        if (view4 == null) {
            kotlin.jvm.internal.n.w("volumeBarContainer");
            view2 = null;
        } else {
            view2 = view4;
        }
        CommonUtils.setMarginTop$default(commonUtils, view2, onOrientationChanged$lambda$25$getTopMargin$24(i2, view2, this), false, 2, null);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        HeaderSizeCallback headerSizeCallback = this.headerSizeCallback;
        if (headerSizeCallback != null) {
            headerSizeCallback.onSizeChanged();
        }
    }

    public final void prepareHidePanel() {
        Log.d(TAG, "prepareHidePanel");
        this.panelAnimHiding = true;
        Integer value = MiPlayDetailViewModel.INSTANCE.getMCastingDeviceIcon().getValue();
        if (value == null) {
            value = Integer.valueOf(R.drawable.ic_media_device_default);
        }
        int iIntValue = value.intValue();
        ImageView imageView = this.deviceIconView;
        ImageView imageView2 = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w("deviceIconView");
            imageView = null;
        }
        imageView.setImageResource(iIntValue);
        this.useCoverAnim = false;
        FlipAnimation flipAnimation = this.flipAnimation;
        ViewGroup viewGroup = this.coverParent;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup = null;
        }
        flipAnimation.reset(viewGroup);
        ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.imageAlphaSwitchAnimation;
        ImageView imageView3 = this.cover;
        if (imageView3 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
        } else {
            imageView2 = imageView3;
        }
        imageAlphaSwitchAnimation.reset(imageView2);
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0289  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void prepareShowPanel() {
        /*
            Method dump skipped, instruction units count: 777
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.QSControlMiPlayDetailHeader.prepareShowPanel():void");
    }

    public final void setContainerLayout(View view) {
        kotlin.jvm.internal.n.g(view, "<set-?>");
        this.containerLayout = view;
    }

    public final void setCoverRadius(float f2) {
        if (this.coverRadius == f2) {
            return;
        }
        this.coverRadius = f2;
        ImageView imageView = this.cover;
        ViewGroup viewGroup = null;
        if (imageView == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
            imageView = null;
        }
        imageView.invalidateOutline();
        ViewGroup viewGroup2 = this.coverParent;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("coverParent");
        } else {
            viewGroup = viewGroup2;
        }
        viewGroup.invalidateOutline();
    }

    public final void setHeaderSizeCallback(HeaderSizeCallback headerSizeCallback) {
        this.headerSizeCallback = headerSizeCallback;
    }

    public final void setPanelAnimHiding(boolean z2) {
        this.panelAnimHiding = z2;
    }

    public final void setShowing(boolean z2, String str) {
        if (z2) {
            this.useCoverAnim = true;
            postDelayed(new Runnable() { // from class: com.android.systemui.N
                @Override // java.lang.Runnable
                public final void run() {
                    QSControlMiPlayDetailHeader.setShowing$lambda$15(this.f1432a);
                }
            }, 300L);
            this.mRef = str;
            this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
            return;
        }
        this.mLifecycle.setCurrentState(Lifecycle.State.CREATED);
        this.useCoverAnim = false;
        FlipAnimation flipAnimation = this.flipAnimation;
        ViewGroup viewGroup = this.coverParent;
        ImageView imageView = null;
        if (viewGroup == null) {
            kotlin.jvm.internal.n.w("coverParent");
            viewGroup = null;
        }
        flipAnimation.reset(viewGroup);
        ImageAlphaSwitchAnimation imageAlphaSwitchAnimation = this.imageAlphaSwitchAnimation;
        ImageView imageView2 = this.cover;
        if (imageView2 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_COVER);
        } else {
            imageView = imageView2;
        }
        imageAlphaSwitchAnimation.reset(imageView);
    }

    public final void setTvControllerVisibilityCallback(TVControllerVisibilityCallback tVControllerVisibilityCallback) {
        this.tvControllerVisibilityCallback = tVControllerVisibilityCallback;
    }

    public final void setUseCoverAnim(boolean z2) {
        this.useCoverAnim = z2;
    }

    public final void startHidePanel() {
        Log.d(TAG, "startHidePanel");
        TextView textView = this.title;
        TextView textView2 = null;
        if (textView == null) {
            kotlin.jvm.internal.n.w("title");
            textView = null;
        }
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        textView.setText(miPlayDetailViewModel.getFirstPanelTitle());
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            kotlin.jvm.internal.n.w("subtitle");
        } else {
            textView2 = textView3;
        }
        textView2.setText(miPlayDetailViewModel.getFirstPanelArtist());
    }

    public final void updateSize() {
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ViewGroup viewGroup;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_action_size);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        ImageView imageView4 = this.prev;
        TextView textView = null;
        if (imageView4 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
            imageView = null;
        } else {
            imageView = imageView4;
        }
        CommonUtils.setLayoutSize$default(commonUtils, imageView, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        ImageView imageView5 = this.play;
        if (imageView5 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
            imageView2 = null;
        } else {
            imageView2 = imageView5;
        }
        CommonUtils.setLayoutSize$default(commonUtils, imageView2, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        ImageView imageView6 = this.next;
        if (imageView6 == null) {
            kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
            imageView3 = null;
        } else {
            imageView3 = imageView6;
        }
        CommonUtils.setLayoutSize$default(commonUtils, imageView3, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_detail_header_audio_container_margin_start);
        int dimensionPixelSize3 = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_detail_header_audio_container_margin_end);
        ViewGroup viewGroup2 = this.audioContainer;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.n.w("audioContainer");
            viewGroup = null;
        } else {
            viewGroup = viewGroup2;
        }
        CommonUtils.setMargins$default(commonUtils, viewGroup, dimensionPixelSize2, 0, dimensionPixelSize3, 0, false, 26, null);
        TextView textView2 = this.title;
        if (textView2 == null) {
            kotlin.jvm.internal.n.w("title");
            textView2 = null;
        }
        Resources resources = getContext().getResources();
        int i2 = R.dimen.miplay_detail_header_title_text_size;
        textView2.setTextSize(0, resources.getDimension(i2));
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            kotlin.jvm.internal.n.w("subtitle");
            textView3 = null;
        }
        textView3.setTextSize(0, getContext().getResources().getDimension(R.dimen.miplay_detail_header_subtitle_text_size));
        TextView textView4 = this.titleNoPlayback;
        if (textView4 == null) {
            kotlin.jvm.internal.n.w("titleNoPlayback");
        } else {
            textView = textView4;
        }
        textView.setTextSize(0, getContext().getResources().getDimension(i2));
    }

    public final void updateTextAppearance() {
        TextView textView = this.titleNoPlayback;
        if (textView == null) {
            kotlin.jvm.internal.n.w("titleNoPlayback");
            textView = null;
        }
        textView.setText(getResources().getString(R.string.miplay_detail_header_no_song));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ QSControlMiPlayDetailHeader(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.folmeAnim$delegate = H0.e.b(new QSControlMiPlayDetailHeader$folmeAnim$2(this));
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.mLifecycle = new LifecycleRegistry(this);
        this.flipAnimation = new FlipAnimation();
        this.nextMedia = true;
        this.useCoverAnim = true;
        this.imageAlphaSwitchAnimation = new ImageAlphaSwitchAnimation();
        this.TAG_PLAY_STATE = 1088484745;
        this.tvControlVisibilityRunnable = new QSControlMiPlayDetailHeader$tvControlVisibilityRunnable$1(this);
    }
}
