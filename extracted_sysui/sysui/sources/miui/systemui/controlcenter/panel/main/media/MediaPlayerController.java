package miui.systemui.controlcenter.panel.main.media;

import H0.s;
import I0.m;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.MiCastUtils;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.MiPlayExtentionsKt;
import com.android.systemui.folme.ImageAlphaSwitchAnimation;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0369g;
import g1.InterfaceC0380l0;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.os.Build;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.MediaPlayerViewBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.controlcenter.media.MediaPlayerIconsInfo;
import miui.systemui.controlcenter.media.MediaPlayerMetaData;
import miui.systemui.controlcenter.media.MediaPlayerUtils;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController$mediaInfoListener$2;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.MediaFromView;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelDelegate;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.util.ThemeUtils;
import miui.systemui.util.concurrency.ConcurrencyModule;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.ImageView;
import miuix.animation.base.AnimConfig;
import miuix.transition.FlipAnimation;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MediaPlayerController";
    public static final int TYPE_MEDIA = 63342;
    private final E0.a contentController;
    private final HapticFeedback hapticFeedback;
    private final List<MainPanelListItem> listItems;
    private boolean listening;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private final H0.d mediaInfoListener$delegate;
    private MediaPlayerAdapter mediaPlayerAdapter;
    private final Optional<MediaPlayerAdapter> mediaPlayerAdapterOptional;
    private final int priority;
    private final boolean rightOrLeft;
    private final E0.a secondaryPanelRouter;
    private final int spanSize;
    private final SuperSaveModeController superSaveModeController;
    private final int type;
    private final IUserTracker userTracker;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class MediaPlayerViewHolder extends RotationItemViewHolder implements MediaFromView {
        private float _cornerRadius;
        private MediaPlayerAdapter adapter;
        private InterfaceC0380l0 animUpdateCoverJob;
        private final MediaPlayerViewBinding binding;
        private boolean detailAvailable;
        private int deviceRes;
        private FlipAnimation flipAnimation;
        private final HapticFeedback hapticFeedback;
        private boolean hasCover;
        private final ImageAlphaSwitchAnimation imageAlphaSwitchAnimation;
        private final E0.a mainPanelModeController;
        private boolean nextMedia;
        private int nextRes;
        private final PackageManager packageManager;
        private int playRes;
        private int prevRes;
        private InterfaceC0380l0 updateDefaultCoverJob;

        /* JADX WARN: Illegal instructions before constructor call */
        public MediaPlayerViewHolder(MediaPlayerViewBinding binding, HapticFeedback hapticFeedback, E0.a mainPanelModeController) {
            n.g(binding, "binding");
            n.g(mainPanelModeController, "mainPanelModeController");
            MediaPlayerPanel root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
            this.hapticFeedback = hapticFeedback;
            this.mainPanelModeController = mainPanelModeController;
            this.detailAvailable = true;
            this.packageManager = getContext().getPackageManager();
            this.nextMedia = true;
            this.flipAnimation = new FlipAnimation();
            this.imageAlphaSwitchAnimation = new ImageAlphaSwitchAnimation();
            final MediaPlayerPanel root2 = binding.getRoot();
            root2.setHapticFeedbackEnabled(true ^ HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE());
            root2.setTouchAnimator(this);
            root2.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.media.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaPlayerController.MediaPlayerViewHolder.lambda$4$lambda$2(this.f5415a, root2, view);
                }
            });
            root2.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.media.d
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return MediaPlayerController.MediaPlayerViewHolder.lambda$4$lambda$3(this.f5417a, root2, view);
                }
            });
            binding.prev.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.media.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaPlayerController.MediaPlayerViewHolder._init_$lambda$5(this.f5419a, view);
                }
            });
            binding.play.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.media.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaPlayerController.MediaPlayerViewHolder._init_$lambda$6(this.f5420a, view);
                }
            });
            binding.next.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.media.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaPlayerController.MediaPlayerViewHolder._init_$lambda$7(this.f5421a, view);
                }
            });
            updateRadius();
            updateDeviceIconBlur();
            initAccessibility();
            this.deviceRes = R.drawable.ic_media_device_default;
            this.prevRes = R.drawable.ic_media_prev;
            this.playRes = R.drawable.ic_media_play_animation;
            this.nextRes = R.drawable.ic_media_next;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$5(MediaPlayerViewHolder this$0, View view) {
            n.g(this$0, "this$0");
            if (!CommonUtils.isTinyScreen(this$0.getContext())) {
                ControlCenterEventTracker.Companion.trackMiPlayClickEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, true, this$0.getAppName());
            }
            this$0.nextMedia = false;
            Object drawable = this$0.binding.prev.getDrawable();
            Animatable2 animatable2 = drawable instanceof Animatable2 ? (Animatable2) drawable : null;
            if (animatable2 != null) {
                animatable2.start();
            }
            MediaPlayerAdapter mediaPlayerAdapter = this$0.adapter;
            if (mediaPlayerAdapter != null) {
                mediaPlayerAdapter.onPrevClicked();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$6(MediaPlayerViewHolder this$0, View view) {
            n.g(this$0, "this$0");
            if (!CommonUtils.isTinyScreen(this$0.getContext())) {
                ControlCenterEventTracker.Companion.trackMiPlayClickEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, true, this$0.getAppName());
            }
            MediaPlayerAdapter mediaPlayerAdapter = this$0.adapter;
            if (mediaPlayerAdapter != null) {
                mediaPlayerAdapter.onPlayClicked();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$7(MediaPlayerViewHolder this$0, View view) {
            n.g(this$0, "this$0");
            if (!CommonUtils.isTinyScreen(this$0.getContext())) {
                ControlCenterEventTracker.Companion.trackMiPlayClickEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, true, this$0.getAppName());
            }
            this$0.nextMedia = true;
            Object drawable = this$0.binding.next.getDrawable();
            Animatable2 animatable2 = drawable instanceof Animatable2 ? (Animatable2) drawable : null;
            if (animatable2 != null) {
                animatable2.start();
            }
            MediaPlayerAdapter mediaPlayerAdapter = this$0.adapter;
            if (mediaPlayerAdapter != null) {
                mediaPlayerAdapter.onNextClicked();
            }
        }

        private final String getAppName() {
            ApplicationInfo applicationInfo = getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            try {
                return this.packageManager.getApplicationLabel(applicationInfo).toString();
            } catch (Throwable unused) {
                Log.e(MainPanelItemViewHolder.TAG, "getApplicationLable failed");
                return null;
            }
        }

        private final ApplicationInfo getApplicationInfo() {
            try {
                String sourcePackage = MiPlayDetailViewModel.INSTANCE.getSourcePackage();
                if (sourcePackage != null) {
                    return this.packageManager.getApplicationInfo(sourcePackage, 0);
                }
                return null;
            } catch (Throwable unused) {
                Log.e(MainPanelItemViewHolder.TAG, "getApplicationInfo failed");
                return null;
            }
        }

        private final String getDisplayedText(TextView textView) {
            Layout layout = textView.getLayout();
            if (layout == null) {
                return textView.getText().toString();
            }
            CharSequence text = textView.getText();
            StringBuilder sb = new StringBuilder();
            int lineCount = layout.getLineCount();
            for (int i2 = 0; i2 < lineCount; i2++) {
                int lineStart = layout.getLineStart(i2);
                CharSequence charSequenceSubSequence = text.subSequence(lineStart, layout.getLineEnd(i2));
                if (layout.getEllipsisCount(i2) > 0) {
                    sb.append(text.subSequence(lineStart, layout.getEllipsisStart(i2) + lineStart));
                    sb.append("…");
                } else {
                    sb.append(charSequenceSubSequence);
                }
                if (i2 < layout.getLineCount() - 1) {
                    sb.append("\n");
                }
            }
            String string = sb.toString();
            n.f(string, "toString(...)");
            return string;
        }

        @SuppressLint({"SoonBlockedPrivateApi"})
        private final float getDrawableRadius(Drawable drawable) {
            float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.control_center_universal_corner_radius);
            if (ThemeUtils.INSTANCE.getDefaultPluginTheme()) {
                return dimensionPixelSize;
            }
            if (drawable instanceof GradientDrawable) {
                return ((GradientDrawable) drawable).getCornerRadius();
            }
            if (drawable instanceof NinePatchDrawable) {
                try {
                    Field declaredField = drawable.getClass().getDeclaredField("mOutlineRadius");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(drawable);
                    Float f2 = obj instanceof Float ? (Float) obj : null;
                    return f2 != null ? f2.floatValue() : dimensionPixelSize;
                } catch (Throwable th) {
                    Log.w(MainPanelItemViewHolder.TAG, "getDrawableRadius error.", th);
                }
            }
            return dimensionPixelSize;
        }

        private final boolean getSupportLongClick() {
            return ((MainPanelModeController) this.mainPanelModeController.get()).getInNormalMode() && !((MainPanelModeController) this.mainPanelModeController.get()).getInPendingEditMode();
        }

        private final void initAccessibility() {
            this.binding.getRoot().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setContentDescription(this.this$0.getContext().getString(R.string.media_panel_description));
                }
            });
            this.binding.deviceIcon.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(true);
                    info.setFocusable(true);
                    info.setLongClickable(true);
                    info.setContentDescription(this.this$0.getContext().getString(R.string.miplay_accessibility_cast));
                }
            });
            this.binding.emptyState.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$3
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setEnabled(true);
                    info.setFocusable(true);
                }
            });
            this.binding.cover.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$4
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setFocusable(true);
                    info.setContentDescription(this.this$0.getContext().getString(R.string.miplay_accessibility_cover));
                }
            });
            this.binding.title.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$5
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setFocusable(true);
                }
            });
            this.binding.artist.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$6
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setFocusable(true);
                }
            });
            this.binding.next.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$7
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(true);
                    info.setFocusable(true);
                }
            });
            this.binding.prev.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$initAccessibility$8
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(true);
                    info.setFocusable(true);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void lambda$4$lambda$2(MediaPlayerViewHolder this$0, MediaPlayerPanel this_apply, View view) {
            MediaPlayerAdapter mediaPlayerAdapter;
            n.g(this$0, "this$0");
            n.g(this_apply, "$this_apply");
            if (!this$0.getSupportLongClick()) {
                Log.w(MainPanelItemViewHolder.TAG, "click: not support");
                MainPanelItemViewHolder.Companion.releaseTouchNow();
                return;
            }
            if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                HapticFeedback hapticFeedback = this$0.hapticFeedback;
                if (hapticFeedback != null) {
                    hapticFeedback.postClick();
                }
            } else {
                this_apply.performHapticFeedback(1);
            }
            if (this$0.detailAvailable && (mediaPlayerAdapter = this$0.adapter) != null) {
                mediaPlayerAdapter.onPlayerClicked();
            }
            Context context = this_apply.getContext();
            n.f(context, "getContext(...)");
            if (CommonUtils.isTinyScreen(context)) {
                return;
            }
            ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
            EventTracker.Companion companion2 = EventTracker.Companion;
            Context context2 = this_apply.getContext();
            n.f(context2, "getContext(...)");
            companion.trackMiPlayClickEvent(companion2.getScreenType(context2), this_apply.getContext().getResources().getConfiguration().orientation, true, this$0.getAppName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean lambda$4$lambda$3(MediaPlayerViewHolder this$0, MediaPlayerPanel this_apply, View view) {
            MediaPlayerAdapter mediaPlayerAdapter;
            n.g(this$0, "this$0");
            n.g(this_apply, "$this_apply");
            if (!this$0.getSupportLongClick()) {
                Log.w(MainPanelItemViewHolder.TAG, "long click: not support");
                MainPanelItemViewHolder.Companion.releaseTouchNow();
                return true;
            }
            if (HapticFeedback.Companion.getIS_SUPPORT_LINEAR_MOTOR_VIBRATE()) {
                HapticFeedback hapticFeedback = this$0.hapticFeedback;
                if (hapticFeedback != null) {
                    hapticFeedback.postLongClick();
                }
            } else {
                this_apply.performHapticFeedback(0);
            }
            if (this$0.detailAvailable && (mediaPlayerAdapter = this$0.adapter) != null) {
                mediaPlayerAdapter.onPlayerClicked();
            }
            Context context = this_apply.getContext();
            n.f(context, "getContext(...)");
            if (!CommonUtils.isTinyScreen(context)) {
                ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
                EventTracker.Companion companion2 = EventTracker.Companion;
                Context context2 = this_apply.getContext();
                n.f(context2, "getContext(...)");
                companion.trackMiPlayLongClickEvent(companion2.getScreenType(context2), this_apply.getContext().getResources().getConfiguration().orientation, true, this$0.getAppName());
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void performFlip(final Bitmap bitmap) {
            FlipAnimation flipAnimation = this.flipAnimation;
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            flipAnimation.setBlurEnabled(miPlayDetailViewModel.isHighDevice() && MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(getContext()));
            Log.d(MainPanelItemViewHolder.TAG, "performFlip start flip " + miPlayDetailViewModel.isHighDevice());
            this.flipAnimation.flip(this.binding.cover, this.nextMedia);
            this.flipAnimation.setFlipListener(new FlipAnimation.FlipListener() { // from class: miui.systemui.controlcenter.panel.main.media.b
                @Override // miuix.transition.FlipAnimation.FlipListener
                public final void onFlip(boolean z2, float f2) {
                    MediaPlayerController.MediaPlayerViewHolder.performFlip$lambda$21(this.f5413a, bitmap, z2, f2);
                }
            });
            this.nextMedia = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void performFlip$lambda$21(MediaPlayerViewHolder this$0, Bitmap bitmap, boolean z2, float f2) {
            n.g(this$0, "this$0");
            Log.d(MainPanelItemViewHolder.TAG, "performFlip flip change");
            this$0.setCover(bitmap, true);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0049  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void setCover(android.graphics.Bitmap r7, boolean r8) {
            /*
                r6 = this;
                r0 = 1
                r1 = 0
                java.lang.String r2 = "MainPanelItemViewHolder"
                java.lang.String r3 = "cover"
                if (r7 == 0) goto L71
                boolean r4 = r6.hasCover
                if (r4 != 0) goto L49
                com.android.systemui.folme.ImageAlphaSwitchAnimation$Companion r4 = com.android.systemui.folme.ImageAlphaSwitchAnimation.Companion
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r5 = r6.binding
                miui.systemui.widget.ImageView r5 = r5.cover
                kotlin.jvm.internal.n.f(r5, r3)
                boolean r4 = r4.isInAlphaAnim(r5)
                if (r4 != 0) goto L49
                if (r8 != 0) goto L49
                java.lang.String r8 = "setCover: use alpha anim"
                android.util.Log.d(r2, r8)
                com.android.systemui.folme.ImageAlphaSwitchAnimation r8 = r6.imageAlphaSwitchAnimation
                com.android.systemui.MiPlayDetailViewModel r2 = com.android.systemui.MiPlayDetailViewModel.INSTANCE
                boolean r2 = r2.isHighDevice()
                if (r2 == 0) goto L38
                android.content.Context r2 = r6.getContext()
                boolean r2 = miui.systemui.util.MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(r2)
                if (r2 == 0) goto L38
                r2 = r0
                goto L39
            L38:
                r2 = r1
            L39:
                r8.setBlurEnabled(r2)
                com.android.systemui.folme.ImageAlphaSwitchAnimation r8 = r6.imageAlphaSwitchAnimation
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r2 = r6.binding
                miui.systemui.widget.ImageView r2 = r2.cover
                kotlin.jvm.internal.n.f(r2, r3)
                r8.switchWithValue(r2, r7)
                goto L89
            L49:
                if (r8 == 0) goto L64
                java.lang.String r8 = "setCover: from flip"
                android.util.Log.d(r2, r8)
                com.android.systemui.folme.ImageAlphaSwitchAnimation r8 = r6.imageAlphaSwitchAnimation
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r2 = r6.binding
                miui.systemui.widget.ImageView r2 = r2.cover
                kotlin.jvm.internal.n.f(r2, r3)
                r8.reset(r2)
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r8 = r6.binding
                miui.systemui.widget.ImageView r8 = r8.cover
                r8.setImageBitmap(r7)
                goto L89
            L64:
                java.lang.String r8 = "setCover: not fromFlip"
                android.util.Log.d(r2, r8)
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r8 = r6.binding
                miui.systemui.widget.ImageView r8 = r8.cover
                r8.setImageBitmap(r7)
                goto L89
            L71:
                java.lang.String r8 = "setCover: bitmap is null"
                android.util.Log.d(r2, r8)
                com.android.systemui.folme.ImageAlphaSwitchAnimation r8 = r6.imageAlphaSwitchAnimation
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r2 = r6.binding
                miui.systemui.widget.ImageView r2 = r2.cover
                kotlin.jvm.internal.n.f(r2, r3)
                r8.reset(r2)
                miui.systemui.controlcenter.databinding.MediaPlayerViewBinding r8 = r6.binding
                miui.systemui.widget.ImageView r8 = r8.cover
                r8.setImageResource(r1)
            L89:
                if (r7 == 0) goto L8c
                goto L8d
            L8c:
                r0 = r1
            L8d:
                r6.hasCover = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.main.media.MediaPlayerController.MediaPlayerViewHolder.setCover(android.graphics.Bitmap, boolean):void");
        }

        public static /* synthetic */ void setCover$default(MediaPlayerViewHolder mediaPlayerViewHolder, Bitmap bitmap, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            mediaPlayerViewHolder.setCover(bitmap, z2);
        }

        private final void setPreNextBlur(boolean z2) {
            Log.d(MainPanelItemViewHolder.TAG, "setPreNextBlur: " + z2);
            if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(getContext()) || !z2) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                ImageView prev = this.binding.prev;
                n.f(prev, "prev");
                commonUtils.clearMiBlurBlendEffect(prev);
                ImageView next = this.binding.next;
                n.f(next, "next");
                commonUtils.clearMiBlurBlendEffect(next);
                return;
            }
            ImageView prev2 = this.binding.prev;
            n.f(prev2, "prev");
            MiBlurCompat.setMiViewBlurModeCompat(prev2, 3);
            ImageView prev3 = this.binding.prev;
            n.f(prev3, "prev");
            MiuiColorBlendToken miuiColorBlendToken = MiuiColorBlendToken.INSTANCE;
            MiBlurCompat.setMiBackgroundBlendColors(prev3, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
            ImageView next2 = this.binding.next;
            n.f(next2, "next");
            MiBlurCompat.setMiViewBlurModeCompat(next2, 3);
            ImageView next3 = this.binding.next;
            n.f(next3, "next");
            MiBlurCompat.setMiBackgroundBlendColors(next3, miuiColorBlendToken.getCC_MIPLAY_PRE_NEXT_BLEND_COLORS());
        }

        private final void updateContentDes() {
            Integer value;
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            Integer value2 = miPlayDetailViewModel.getMCurrMediaType().getValue();
            if (value2 != null && value2.intValue() == 0) {
                this.binding.prev.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_prev));
            } else {
                this.binding.prev.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_video_rewind, 15));
            }
            Integer value3 = miPlayDetailViewModel.getMPlaybackState().getValue();
            if ((value3 != null && value3.intValue() == 0) || ((value = miPlayDetailViewModel.getMPlaybackState().getValue()) != null && value.intValue() == 2)) {
                this.binding.play.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_play));
            } else {
                Integer value4 = miPlayDetailViewModel.getMPlaybackState().getValue();
                if (value4 != null && value4.intValue() == 3) {
                    this.binding.play.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_pause));
                }
            }
            Integer value5 = miPlayDetailViewModel.getMCurrMediaType().getValue();
            if (value5 != null && value5.intValue() == 0) {
                this.binding.next.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_next));
            } else {
                this.binding.next.setContentDescription(getContext().getString(com.android.systemui.miplay.R.string.miplay_accessibility_video_fastForward, 15));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updateCover(MediaPlayerMetaData mediaPlayerMetaData) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            MediaMetaData value = miPlayDetailViewModel.getMMediaMetaData().getValue();
            String mediaID = MiPlayExtentionsKt.getMediaID(mediaPlayerMetaData, value != null ? Integer.valueOf(value.getMediaType()) : null);
            Log.d(MainPanelItemViewHolder.TAG, "art " + mediaPlayerMetaData.getArt() + " updateMetaData: " + mediaID + "  " + miPlayDetailViewModel.getMMediaIDForCardCover());
            Bitmap art = mediaPlayerMetaData.getArt();
            boolean zC = n.c(miPlayDetailViewModel.getMMediaIDForCardCover(), mediaID);
            boolean z2 = (zC || miPlayDetailViewModel.isLowDevice()) ? false : true;
            if (FlipAnimation.isInFlipAnimation(this.binding.cover)) {
                Log.d(MainPanelItemViewHolder.TAG, "updateCover is in flip anim");
                InterfaceC0380l0 interfaceC0380l0 = this.animUpdateCoverJob;
                if (interfaceC0380l0 != null) {
                    InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
                }
                this.animUpdateCoverJob = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new MediaPlayerController$MediaPlayerViewHolder$updateCover$1(this, zC, art, mediaID, null), 3, null);
                return;
            }
            InterfaceC0380l0 interfaceC0380l02 = this.animUpdateCoverJob;
            if (interfaceC0380l02 != null) {
                InterfaceC0380l0.a.a(interfaceC0380l02, null, 1, null);
            }
            if (z2) {
                miPlayDetailViewModel.setMMediaIDForCardCover(mediaID);
                performFlip(art);
            } else {
                setCover$default(this, art, false, 2, null);
                miPlayDetailViewModel.setMMediaIDForCardCover(mediaID);
            }
        }

        private final void updateDeviceIconBlur() {
            if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                ImageView deviceIcon = this.binding.deviceIcon;
                n.f(deviceIcon, "deviceIcon");
                commonUtils.clearMiBlurBlendEffect(deviceIcon);
                return;
            }
            ImageView deviceIcon2 = this.binding.deviceIcon;
            n.f(deviceIcon2, "deviceIcon");
            MiBlurCompat.setMiViewBlurModeCompat(deviceIcon2, 3);
            ImageView deviceIcon3 = this.binding.deviceIcon;
            n.f(deviceIcon3, "deviceIcon");
            MiBlurCompat.setMiBackgroundBlendColors(deviceIcon3, MiuiColorBlendToken.INSTANCE.getCC_MIPLAY_DEVICE_ICON_BLEND_COLORS());
        }

        public static /* synthetic */ void updateIconsInfo$default(MediaPlayerViewHolder mediaPlayerViewHolder, MediaPlayerIconsInfo mediaPlayerIconsInfo, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            mediaPlayerViewHolder.updateIconsInfo(mediaPlayerIconsInfo, z2);
        }

        private final void updatePlayRes(int i2) {
            if (this.playRes != i2) {
                this.playRes = i2;
                this.binding.play.setImageResource(i2);
                Object drawable = this.binding.play.getDrawable();
                Animatable2 animatable2 = drawable instanceof Animatable2 ? (Animatable2) drawable : null;
                if (animatable2 != null) {
                    animatable2.start();
                }
            }
        }

        private final void updateRadius() {
            float drawableRadius = getDrawableRadius(this.binding.getRoot().getBackground());
            this._cornerRadius = drawableRadius;
            setCornerRadius(drawableRadius);
            ImageView imageView = this.binding.cover;
            imageView.setClipToOutline(true);
            imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$MediaPlayerViewHolder$updateRadius$1$1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    n.g(view, "view");
                    n.g(outline, "outline");
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.this$0.getCoverRadius());
                }
            });
        }

        private final void updateResources() {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            MediaPlayerPanel root = this.binding.getRoot();
            n.f(root, "getRoot(...)");
            CommonUtils.setBackgroundResourceEx$default(commonUtils, root, R.drawable.media_player_background, false, 2, null);
            ImageView cover = this.binding.cover;
            n.f(cover, "cover");
            CommonUtils.setBackgroundResourceEx$default(commonUtils, cover, R.drawable.miplay_cover_default, false, 2, null);
            ColorStateList colorStateList = getContext().getColorStateList(R.color.qs_miplay_header_action_tint);
            this.binding.prev.setImageTintList(colorStateList);
            this.binding.play.setImageTintList(colorStateList);
            this.binding.next.setImageTintList(colorStateList);
            this.binding.deviceIcon.setImageTintList(colorStateList);
        }

        private final void updateSize() {
            MediaPlayerPanel root = this.binding.getRoot();
            int dimensionPixelSize = root.getResources().getDimensionPixelSize(R.dimen.control_center_universal_2_rows_size);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            n.d(root);
            CommonUtils.setLayoutSize$default(commonUtils, root, dimensionPixelSize, dimensionPixelSize, false, 4, null);
            CommonUtils.setMargins$default(commonUtils, root, root.getResources().getDimensionPixelSize(R.dimen.control_center_universal_margin), false, 2, null);
            ImageView imageView = this.binding.cover;
            int dimensionPixelSize2 = imageView.getResources().getDimensionPixelSize(R.dimen.media_player_cover_size);
            n.d(imageView);
            CommonUtils.setLayoutSize$default(commonUtils, imageView, dimensionPixelSize2, dimensionPixelSize2, false, 4, null);
            int dimensionPixelSize3 = imageView.getResources().getDimensionPixelSize(R.dimen.media_player_cover_margin);
            CommonUtils.setMargins$default(commonUtils, imageView, dimensionPixelSize3, dimensionPixelSize3, 0, 0, false, 28, null);
            ImageView imageView2 = this.binding.deviceIcon;
            int dimensionPixelSize4 = imageView2.getResources().getDimensionPixelSize(R.dimen.media_player_device_icon_size);
            n.d(imageView2);
            CommonUtils.setLayoutSize$default(commonUtils, imageView2, dimensionPixelSize4, dimensionPixelSize4, false, 4, null);
            CommonUtils.setMargins$default(commonUtils, imageView2, imageView2.getResources().getDimensionPixelSize(R.dimen.media_player_device_icon_margin), false, 2, null);
            miui.systemui.widget.TextView textView = this.binding.title;
            int dimensionPixelSize5 = textView.getResources().getDimensionPixelSize(R.dimen.media_player_title_margin_start);
            int dimensionPixelSize6 = textView.getResources().getDimensionPixelSize(R.dimen.media_player_title_margin_top);
            int dimensionPixelSize7 = textView.getResources().getDimensionPixelSize(R.dimen.media_player_title_margin_end);
            n.d(textView);
            CommonUtils.setMargins$default(commonUtils, textView, dimensionPixelSize5, dimensionPixelSize6, dimensionPixelSize7, 0, false, 24, null);
            miui.systemui.widget.TextView textView2 = this.binding.artist;
            int dimensionPixelSize8 = textView2.getResources().getDimensionPixelSize(R.dimen.media_player_artist_margin_start);
            int dimensionPixelSize9 = textView2.getResources().getDimensionPixelSize(R.dimen.media_player_artist_margin_top);
            int dimensionPixelSize10 = textView2.getResources().getDimensionPixelSize(R.dimen.media_player_artist_margin_end);
            n.d(textView2);
            CommonUtils.setMargins$default(commonUtils, textView2, dimensionPixelSize8, dimensionPixelSize9, dimensionPixelSize10, 0, false, 24, null);
            miui.systemui.widget.TextView textView3 = this.binding.emptyState;
            int dimensionPixelSize11 = textView3.getResources().getDimensionPixelSize(R.dimen.media_player_empty_state_margin_horizontal);
            int dimensionPixelSize12 = textView3.getResources().getDimensionPixelSize(R.dimen.media_player_empty_state_margin_top);
            n.d(textView3);
            CommonUtils.setMargins$default(commonUtils, textView3, dimensionPixelSize11, dimensionPixelSize12, dimensionPixelSize11, 0, false, 24, null);
            int dimensionPixelSize13 = getResources().getDimensionPixelSize(R.dimen.media_player_button_size);
            ImageView prev = this.binding.prev;
            n.f(prev, "prev");
            commonUtils.setLayoutSize(prev, dimensionPixelSize13, dimensionPixelSize13, true);
            ImageView play = this.binding.play;
            n.f(play, "play");
            commonUtils.setLayoutSize(play, dimensionPixelSize13, dimensionPixelSize13, true);
            ImageView next = this.binding.next;
            n.f(next, "next");
            commonUtils.setLayoutSize(next, dimensionPixelSize13, dimensionPixelSize13, true);
            int dimensionPixelSize14 = getResources().getDimensionPixelSize(R.dimen.media_player_button_margin_horizontal);
            ConstraintLayout audioContainer = this.binding.audioContainer;
            n.f(audioContainer, "audioContainer");
            CommonUtils.setMargins$default(commonUtils, audioContainer, dimensionPixelSize14, 0, dimensionPixelSize14, getResources().getDimensionPixelSize(R.dimen.media_player_button_margin_bottom), false, 18, null);
            updateRadius();
        }

        public final void attachMediaPlayerAdapter(MediaPlayerAdapter mediaPlayerAdapter) {
            this.adapter = mediaPlayerAdapter;
        }

        public final void disableMediaController() {
            this.binding.prev.setEnabled(false);
            this.binding.play.setEnabled(true);
            this.binding.next.setEnabled(false);
            setPreNextBlur(true);
        }

        public final void enableMediaController() {
            this.binding.prev.setEnabled(true);
            this.binding.play.setEnabled(true);
            this.binding.next.setEnabled(true);
            setPreNextBlur(false);
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getArtistView() {
            miui.systemui.widget.TextView artist = this.binding.artist;
            n.f(artist, "artist");
            return artist;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getAudioContainerView() {
            ConstraintLayout audioContainer = this.binding.audioContainer;
            n.f(audioContainer, "audioContainer");
            return audioContainer;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getAudioNextView() {
            ImageView next = this.binding.next;
            n.f(next, "next");
            return next;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getAudioPlayView() {
            ImageView play = this.binding.play;
            n.f(play, "play");
            return play;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getAudioPrevView() {
            ImageView prev = this.binding.prev;
            n.f(prev, "prev");
            return prev;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public ViewGroup getContentView() {
            MediaPlayerPanel root = this.binding.getRoot();
            n.f(root, "getRoot(...)");
            return root;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public float getCornerRadius() {
            return this._cornerRadius;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public float getCoverRadius() {
            return this.binding.cover.getContext().getResources().getDimension(R.dimen.media_player_cover_radius);
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getCoverView() {
            ImageView cover = this.binding.cover;
            n.f(cover, "cover");
            return cover;
        }

        public final boolean getDetailAvailable() {
            return this.detailAvailable;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public View getNoPlayView() {
            miui.systemui.widget.TextView emptyState = this.binding.emptyState;
            n.f(emptyState, "emptyState");
            return emptyState;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public miui.systemui.widget.TextView getTitleView() {
            miui.systemui.widget.TextView title = this.binding.title;
            n.f(title, "title");
            return title;
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public MainPanelItemViewHolder getViewHolder() {
            return this;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
            if (zDimensionsChanged) {
                updateSize();
            }
            if (zDimensionsChanged || configUtils.colorsChanged(i2) || configUtils.themeChanged(i2) || configUtils.blurChanged(i2)) {
                updateResources();
                updateRadius();
                updateDeviceIconBlur();
                setPreNextBlur(MiPlayDetailViewModel.INSTANCE.allowControlRemoteTV());
            }
            if (configUtils.textAppearanceChanged(i2)) {
                this.binding.title.setTextAppearance(R.style.TextAppearance_MediaPlayer_Title);
                this.binding.artist.setTextAppearance(R.style.TextAppearance_MediaPlayer_Artist);
                this.binding.emptyState.setTextAppearance(R.style.TextAppearance_MediaPlayer_EmptyState);
            }
            if (configUtils.textsChanged(i2)) {
                this.binding.emptyState.setText(R.string.miplay_detail_header_no_song);
                this.binding.emptyState.requestLayout();
                this.binding.getRoot().setContentDescription(getContext().getString(R.string.media_panel_description));
                this.binding.deviceIcon.setContentDescription(getContext().getString(R.string.miplay_accessibility_cast));
                this.binding.cover.setContentDescription(getContext().getString(R.string.miplay_accessibility_cover));
                updateContentDes();
            }
        }

        public final void release() {
            this.imageAlphaSwitchAnimation.release();
        }

        @Override // miui.systemui.controlcenter.panel.secondary.MediaFromView
        public void setCornerRadius(float f2) {
            Drawable background = this.binding.getRoot().getBackground();
            GradientDrawable gradientDrawable = background instanceof GradientDrawable ? (GradientDrawable) background : null;
            if (gradientDrawable == null) {
                return;
            }
            gradientDrawable.setCornerRadius(f2);
        }

        public final void setDetailAvailable(boolean z2) {
            if (this.detailAvailable == z2) {
                return;
            }
            this.detailAvailable = z2;
            this.binding.deviceIcon.setVisibility(z2 ? 0 : 8);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
            super.updateBlendBlur();
        }

        public final void updateIconsInfo(MediaPlayerIconsInfo iconsInfo, boolean z2) {
            n.g(iconsInfo, "iconsInfo");
            if (this.deviceRes != iconsInfo.getDeviceRes() || z2) {
                this.deviceRes = iconsInfo.getDeviceRes();
                this.binding.deviceIcon.setImageResource(iconsInfo.getDeviceRes());
            }
            if (this.prevRes != iconsInfo.getPrevRes() || z2) {
                this.prevRes = iconsInfo.getPrevRes();
                this.binding.prev.setImageResource(iconsInfo.getPrevRes());
            }
            updatePlayRes(iconsInfo.getPlayRes());
            updateContentDes();
            if (this.nextRes != iconsInfo.getNextRes() || z2) {
                this.nextRes = iconsInfo.getNextRes();
                this.binding.next.setImageResource(iconsInfo.getNextRes());
            }
        }

        public final void updateMetaData(MediaPlayerMetaData mediaPlayerMetaData) {
            if (mediaPlayerMetaData == null) {
                miui.systemui.widget.TextView textView = this.binding.emptyState;
                textView.setText(textView.getResources().getString(com.android.systemui.miplay.R.string.miplay_detail_header_no_song));
                textView.setEnabled(false);
                textView.setVisibility(0);
                this.binding.title.setVisibility(8);
                this.binding.cover.setVisibility(4);
                this.binding.artist.setVisibility(8);
                this.binding.prev.setEnabled(false);
                updatePlayRes(R.drawable.ic_media_play_animation);
                this.binding.play.setEnabled(true);
                this.binding.next.setEnabled(false);
                setPreNextBlur(true);
                return;
            }
            Log.d(MainPanelItemViewHolder.TAG, "updateMetaData: mediaId " + mediaPlayerMetaData.getMediaId() + " tvId " + mediaPlayerMetaData.getTvId() + " art " + mediaPlayerMetaData.getArt());
            this.binding.emptyState.setVisibility(8);
            this.binding.cover.setVisibility(0);
            if (this.hasCover && mediaPlayerMetaData.getArt() == null) {
                this.updateDefaultCoverJob = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new MediaPlayerController$MediaPlayerViewHolder$updateMetaData$1$1(this, null), 3, null);
            } else {
                InterfaceC0380l0 interfaceC0380l0 = this.updateDefaultCoverJob;
                if (interfaceC0380l0 != null) {
                    InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
                }
                updateCover(mediaPlayerMetaData);
            }
            miui.systemui.widget.TextView textView2 = this.binding.title;
            CharSequence string = TextUtils.isEmpty(mediaPlayerMetaData.getTitle()) ? textView2.getResources().getString(com.android.systemui.miplay.R.string.miplay_song_title_empty) : mediaPlayerMetaData.getTitle();
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            n.d(textView2);
            commonUtils.updateText(textView2, string);
            textView2.setEnabled(true);
            this.binding.title.setVisibility(0);
            miui.systemui.widget.TextView title = this.binding.title;
            n.f(title, "title");
            String displayedText = getDisplayedText(title);
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            miPlayDetailViewModel.setFirstPanelTitle(displayedText);
            miui.systemui.widget.TextView textView3 = this.binding.artist;
            CharSequence charSequenceBetterArtistAlbum = MediaPlayerUtils.INSTANCE.betterArtistAlbum(mediaPlayerMetaData);
            textView3.setVisibility(TextUtils.isEmpty(charSequenceBetterArtistAlbum) ? 8 : 0);
            n.d(textView3);
            commonUtils.updateText(textView3, charSequenceBetterArtistAlbum);
            textView3.setEnabled(true);
            miui.systemui.widget.TextView artist = this.binding.artist;
            n.f(artist, "artist");
            miPlayDetailViewModel.setFirstPanelArtist(getDisplayedText(artist));
            this.binding.prev.setEnabled(true);
            this.binding.play.setEnabled(true);
            this.binding.next.setEnabled(true);
            setPreNextBlur(false);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.media.MediaPlayerController$onCreate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((MediaPlayerAdapter) obj);
            return s.f314a;
        }

        public final void invoke(MediaPlayerAdapter it) {
            n.g(it, "it");
            MediaPlayerController.this.mediaPlayerAdapter = it;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.ControlCenter Lifecycle lifecycle, E0.a secondaryPanelRouter, Optional<MediaPlayerAdapter> mediaPlayerAdapterOptional, E0.a mainPanelStyleController, E0.a mainPanelModeController, HapticFeedback hapticFeedback, E0.a contentController, SuperSaveModeController superSaveModeController, IUserTracker userTracker) {
        super(windowView, lifecycle);
        n.g(windowView, "windowView");
        n.g(lifecycle, "lifecycle");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(mediaPlayerAdapterOptional, "mediaPlayerAdapterOptional");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(hapticFeedback, "hapticFeedback");
        n.g(contentController, "contentController");
        n.g(superSaveModeController, "superSaveModeController");
        n.g(userTracker, "userTracker");
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.mediaPlayerAdapterOptional = mediaPlayerAdapterOptional;
        this.mainPanelStyleController = mainPanelStyleController;
        this.mainPanelModeController = mainPanelModeController;
        this.hapticFeedback = hapticFeedback;
        this.contentController = contentController;
        this.superSaveModeController = superSaveModeController;
        this.userTracker = userTracker;
        this.listItems = m.f(this);
        this.mediaInfoListener$delegate = H0.e.b(new MediaPlayerController$mediaInfoListener$2(this));
        this.priority = 30;
        this.rightOrLeft = true;
        this.type = TYPE_MEDIA;
        this.spanSize = 2;
    }

    private final MediaPlayerController$mediaInfoListener$2.AnonymousClass1 getMediaInfoListener() {
        return (MediaPlayerController$mediaInfoListener$2.AnonymousClass1) this.mediaInfoListener$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaPlayerViewHolder getViewHolder() {
        MainPanelItemViewHolder holder = getHolder();
        if (holder instanceof MediaPlayerViewHolder) {
            return (MediaPlayerViewHolder) holder;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    private final void updateIcon() {
        MainPanelItemViewHolder holder = getHolder();
        MediaPlayerViewHolder mediaPlayerViewHolder = holder instanceof MediaPlayerViewHolder ? (MediaPlayerViewHolder) holder : null;
        if (mediaPlayerViewHolder == null) {
            return;
        }
        mediaPlayerViewHolder.setDetailAvailable((!Build.IS_INTERNATIONAL_BUILD || MiCastUtils.INSTANCE.useInternationalCast(getContext())) && this.userTracker.getUserId() == 0 && !this.superSaveModeController.isActive());
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return (((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() == MainPanelController.Style.COMPACT || ((MainPanelModeController) this.mainPanelModeController.get()).getMode() == MainPanelController.Mode.EDIT) ? false : true;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 63342) {
            return null;
        }
        MediaPlayerViewBinding mediaPlayerViewBindingInflate = MediaPlayerViewBinding.inflate(LayoutInflater.from(getContext()), parent, false);
        n.f(mediaPlayerViewBindingInflate, "inflate(...)");
        return new MediaPlayerViewHolder(mediaPlayerViewBindingInflate, this.hapticFeedback, this.mainPanelModeController);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        return this.listItems;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onBindViewHolder() {
        MediaPlayerViewHolder viewHolder = getViewHolder();
        if (viewHolder != null) {
            MediaPlayerAdapter mediaPlayerAdapter = this.mediaPlayerAdapter;
            if (mediaPlayerAdapter == null) {
                n.w("mediaPlayerAdapter");
                mediaPlayerAdapter = null;
            }
            viewHolder.attachMediaPlayerAdapter(mediaPlayerAdapter);
        }
        updateIcon();
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        Optional<MediaPlayerAdapter> optional = this.mediaPlayerAdapterOptional;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        optional.ifPresent(new Consumer() { // from class: miui.systemui.controlcenter.panel.main.media.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MediaPlayerController.onCreate$lambda$0(anonymousClass1, obj);
            }
        });
        MediaPlayerAdapter mediaPlayerAdapter = this.mediaPlayerAdapter;
        if (mediaPlayerAdapter == null) {
            n.w("mediaPlayerAdapter");
            mediaPlayerAdapter = null;
        }
        mediaPlayerAdapter.setMediaInfoListener(getMediaInfoListener());
        if (Build.IS_INTERNATIONAL_BUILD) {
            MiCastUtils.INSTANCE.useInternationalCast(getContext());
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        MediaPlayerAdapter mediaPlayerAdapter = this.mediaPlayerAdapter;
        if (mediaPlayerAdapter == null) {
            n.w("mediaPlayerAdapter");
            mediaPlayerAdapter = null;
        }
        mediaPlayerAdapter.setMediaInfoListener(null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        updateIcon();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        MediaPlayerViewHolder viewHolder = getViewHolder();
        if (viewHolder != null) {
            viewHolder.attachMediaPlayerAdapter(null);
        }
        MediaPlayerViewHolder viewHolder2 = getViewHolder();
        if (viewHolder2 != null) {
            viewHolder2.release();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onUserSwitched(int i2) {
        updateIcon();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
        ((MediaPanelDelegate) this.contentController.get()).setListening(z2);
        MediaPlayerAdapter mediaPlayerAdapter = this.mediaPlayerAdapter;
        if (mediaPlayerAdapter == null) {
            n.w("mediaPlayerAdapter");
            mediaPlayerAdapter = null;
        }
        mediaPlayerAdapter.setListening(z2);
    }
}
