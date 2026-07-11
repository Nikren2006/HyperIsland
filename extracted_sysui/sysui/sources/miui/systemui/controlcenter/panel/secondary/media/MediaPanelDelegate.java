package miui.systemui.controlcenter.panel.secondary.media;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.MiPlayController;
import com.android.systemui.MiPlayDetailViewModel;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.QSControlMiPlayDetailHeader;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.MediaPanelBinding;
import miui.systemui.controlcenter.panel.secondary.MediaFromView;
import miui.systemui.controlcenter.panel.secondary.MediaPanelParams;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.MiBlurCompat;
import miui.systemui.util.MiuiColorBlendToken;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MediaPanelDelegate extends SecondaryPanelDelegateBase<MediaPanelParams> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MediaPanelContentController";
    private View appIconContainerView;
    private int appIconMargin;
    private View appIconView;
    private View audioContainerView;
    private View audioNextView;
    private View audioPlayView;
    private View audioPrevView;
    private View avContainerView;
    private View avNextView;
    private View avPlayView;
    private View avPrevView;
    private final MediaPanelBinding binding;
    private View contentView;
    private ViewGroup coverParentView;
    private float coverRadius;
    private View coverView;
    private int deviceIconMargin;
    private int deviceIconSize;
    private View deviceIconView;
    private View divider;
    private int fromAudioButtonSize;
    private float fromNoPlayFontSize;
    private float fromSubtitleFontSize;
    private float fromTitleFontSize;
    private QSControlMiPlayDetailHeader headerLayout;
    private int headerPaddingStartEnd;
    private int headerPaddingTop;
    private int lastDensity;
    private boolean lastForceVertical;
    private int lastId;
    private View listView;
    private boolean listening;
    private ViewGroup metaInfo;
    private boolean needReInit;
    private TextView noPlayView;
    private View progressContainerView;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private TextView subtitleView;
    private Typeface titleOriginalTypeface;
    private TextView titleView;
    private int toAudioButtonSize;
    private float toNoPlayFontSize;
    private float toSubtitleFontSize;
    private float toTitleFontSize;
    private View tvContainerView;
    private View videoContainerView;
    private View videoNextView;
    private View videoPlayView;
    private View videoPrevView;
    private View volumeBarContainerView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPanelDelegate(ControlCenterSecondaryBinding secondaryBinding, MediaPanelBinding binding) {
        super(secondaryBinding);
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.lastForceVertical = CommonUtils.INSTANCE.getForceVertical();
    }

    private final void initView() {
        if (this.contentView == null || this.lastForceVertical != CommonUtils.INSTANCE.getForceVertical() || this.needReInit) {
            this.needReInit = false;
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            this.lastForceVertical = commonUtils.getForceVertical();
            this.contentView = commonUtils.getForceVertical() ? MiPlayController.INSTANCE.createMiPlayDetailView() : MiPlayController.INSTANCE.createMiPlayDetailViewSupportLand();
            this.lastDensity = getContext().getResources().getDisplayMetrics().densityDpi;
            Log.d(TAG, "initView inflate");
            View view = this.contentView;
            if (view != null) {
                this.headerLayout = (QSControlMiPlayDetailHeader) view.findViewById(R.id.qs_control_detail_miplay_header);
                this.metaInfo = (ViewGroup) view.findViewById(R.id.meta_data_info);
                this.coverView = view.findViewById(R.id.cover);
                this.coverParentView = (ViewGroup) view.findViewById(R.id.coverParent);
                this.appIconContainerView = view.findViewById(R.id.app_icon_container);
                this.appIconView = view.findViewById(R.id.app_icon);
                this.deviceIconView = view.findViewById(R.id.device_icon);
                this.noPlayView = (TextView) view.findViewById(R.id.title_no_play_back_history);
                this.titleView = (TextView) view.findViewById(R.id.title);
                this.subtitleView = (TextView) view.findViewById(R.id.subtitle);
                this.audioContainerView = view.findViewById(R.id.container_audio);
                this.audioPrevView = view.findViewById(R.id.prev);
                this.audioPlayView = view.findViewById(R.id.play);
                this.audioNextView = view.findViewById(R.id.next);
                this.videoContainerView = view.findViewById(R.id.container_video);
                this.videoPrevView = view.findViewById(R.id.video_rewind);
                this.videoPlayView = view.findViewById(R.id.video_play);
                this.videoNextView = view.findViewById(R.id.video_fastForward);
                this.progressContainerView = view.findViewById(R.id.container_progress);
                this.tvContainerView = view.findViewById(R.id.tv_controller_panel);
                this.volumeBarContainerView = view.findViewById(R.id.volume_bar_container);
                this.divider = view.findViewById(R.id.spilt_screen_divider);
                this.listView = view.findViewById(R.id.list);
                TextView textView = this.titleView;
                this.titleOriginalTypeface = textView != null ? textView.getTypeface() : null;
                LinearLayout linearLayout = this.binding.mediaContent;
                linearLayout.removeAllViews();
                linearLayout.addView(this.contentView);
                updateBlur();
            }
        }
    }

    private final void panelIdleInit(boolean z2) {
        if (z2) {
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: miui.systemui.controlcenter.panel.secondary.media.a
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return MediaPanelDelegate.panelIdleInit$lambda$0(this.f5473a);
                }
            });
        }
    }

    public static /* synthetic */ void panelIdleInit$default(MediaPanelDelegate mediaPanelDelegate, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        mediaPanelDelegate.panelIdleInit(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean panelIdleInit$lambda$0(MediaPanelDelegate this$0) {
        n.g(this$0, "this$0");
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        Log.d(TAG, "panelIdleInit: " + miPlayDetailViewModel.getMIsListShowing().getValue());
        if (n.c(miPlayDetailViewModel.getMIsListShowing().getValue(), Boolean.TRUE)) {
            return false;
        }
        this$0.initView();
        return false;
    }

    private final void setMiPlayDetailShowing(boolean z2, boolean z3) {
        View view = this.contentView;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = view instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view : null;
        if (qSControlMiPlayDetailContent != null) {
            qSControlMiPlayDetailContent.setDetailShowing(z2, "controlcenter", z3);
        }
    }

    public static /* synthetic */ void setMiPlayDetailShowing$default(MediaPanelDelegate mediaPanelDelegate, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z3 = false;
        }
        mediaPanelDelegate.setMiPlayDetailShowing(z2, z3);
    }

    private final void shieldJumpViewClick(boolean z2) {
        Log.d(TAG, "shieldJumpViewClick: " + z2);
        View view = this.coverView;
        if (view != null) {
            view.setClickable(!z2);
        }
        TextView textView = this.titleView;
        if (textView != null) {
            textView.setClickable(!z2);
        }
        TextView textView2 = this.subtitleView;
        if (textView2 != null) {
            textView2.setClickable(!z2);
        }
        TextView textView3 = this.noPlayView;
        if (textView3 != null) {
            textView3.setClickable(!z2);
        }
        View view2 = this.audioPlayView;
        if (view2 == null) {
            return;
        }
        view2.setClickable(!z2);
    }

    private final void switchAVViews() {
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader == null || !qSControlMiPlayDetailHeader.isAudioPlaying()) {
            this.avContainerView = this.videoContainerView;
            this.avPrevView = this.videoPrevView;
            this.avPlayView = this.videoPlayView;
            this.avNextView = this.videoNextView;
            return;
        }
        this.avContainerView = this.audioContainerView;
        this.avPrevView = this.audioPrevView;
        this.avPlayView = this.audioPlayView;
        this.avNextView = this.audioNextView;
    }

    private final void updateBlur() {
        if (!MiBlurCompat.getBackgroundBlurOpenedInDefaultTheme(getContext())) {
            View view = this.deviceIconView;
            if (view != null) {
                CommonUtils.INSTANCE.clearMiBlurBlendEffect(view);
                return;
            }
            return;
        }
        View view2 = this.deviceIconView;
        if (view2 != null) {
            MiBlurCompat.setMiViewBlurModeCompat(view2, 3);
        }
        View view3 = this.deviceIconView;
        if (view3 != null) {
            MiBlurCompat.setMiBackgroundBlendColors(view3, MiuiColorBlendToken.INSTANCE.getCC_MIPLAY_DEVICE_ICON_BLEND_COLORS());
        }
    }

    private final void updateFromViewSize(MediaFromView mediaFromView) {
        View noPlayView = mediaFromView != null ? mediaFromView.getNoPlayView() : null;
        TextView textView = noPlayView instanceof TextView ? (TextView) noPlayView : null;
        if (textView != null) {
            textView.setTextSize(0, this.fromNoPlayFontSize);
        }
        miui.systemui.widget.TextView titleView = mediaFromView != null ? mediaFromView.getTitleView() : null;
        if (titleView == null) {
            titleView = null;
        }
        if (titleView != null) {
            titleView.setTextSize(0, this.fromTitleFontSize);
        }
        View artistView = mediaFromView != null ? mediaFromView.getArtistView() : null;
        TextView textView2 = artistView instanceof TextView ? (TextView) artistView : null;
        if (textView2 != null) {
            textView2.setTextSize(0, this.fromSubtitleFontSize);
        }
    }

    private final void updateResources() {
        ColorStateList colorStateList = getContext().getColorStateList(R.color.qs_miplay_header_action_tint);
        View view = this.avPrevView;
        ImageView imageView = view instanceof ImageView ? (ImageView) view : null;
        if (imageView != null) {
            imageView.setImageTintList(colorStateList);
        }
        View view2 = this.avNextView;
        ImageView imageView2 = view2 instanceof ImageView ? (ImageView) view2 : null;
        if (imageView2 != null) {
            imageView2.setImageTintList(colorStateList);
        }
        View view3 = this.avPlayView;
        ImageView imageView3 = view3 instanceof ImageView ? (ImageView) view3 : null;
        if (imageView3 != null) {
            imageView3.setImageTintList(colorStateList);
        }
    }

    private final void updateSize() {
        this.appIconMargin = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_app_icon_margin);
        this.deviceIconMargin = getContext().getResources().getDimensionPixelSize(R.dimen.media_player_device_icon_margin);
        this.deviceIconSize = getContext().getResources().getDimensionPixelSize(R.dimen.media_player_device_icon_size);
        this.headerPaddingTop = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_meta_padding_top);
        this.headerPaddingStartEnd = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_padding_start_end);
        this.fromAudioButtonSize = getContext().getResources().getDimensionPixelSize(R.dimen.media_player_button_size);
        this.toAudioButtonSize = getContext().getResources().getDimensionPixelSize(R.dimen.miplay_detail_header_action_size);
        this.fromNoPlayFontSize = getContext().getResources().getDimension(R.dimen.media_player_empty_state_text_size);
        Resources resources = getContext().getResources();
        int i2 = R.dimen.miplay_detail_header_title_text_size;
        this.toNoPlayFontSize = resources.getDimension(i2);
        this.fromTitleFontSize = getContext().getResources().getDimension(R.dimen.media_player_title_text_size);
        this.toTitleFontSize = getContext().getResources().getDimension(i2);
        this.fromSubtitleFontSize = getContext().getResources().getDimension(R.dimen.media_player_artist_text_size);
        this.toSubtitleFontSize = getContext().getResources().getDimension(R.dimen.miplay_detail_header_subtitle_text_size);
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.updateSize();
        }
    }

    private final void updateTextAppearance() {
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.updateTextAppearance();
        }
    }

    public final View getAppIconContainerView() {
        return this.appIconContainerView;
    }

    public final int getAppIconMargin() {
        return this.appIconMargin;
    }

    public final View getAppIconView() {
        return this.appIconView;
    }

    public final View getAvContainerView() {
        return this.avContainerView;
    }

    public final View getAvNextView() {
        return this.avNextView;
    }

    public final View getAvPlayView() {
        return this.avPlayView;
    }

    public final View getAvPrevView() {
        return this.avPrevView;
    }

    public final ViewGroup getCoverParentView() {
        return this.coverParentView;
    }

    public final float getCoverRadius() {
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            return qSControlMiPlayDetailHeader.getCoverRadius();
        }
        return 0.0f;
    }

    public final View getCoverView() {
        return this.coverView;
    }

    public final int getDeviceIconMargin() {
        return this.deviceIconMargin;
    }

    public final int getDeviceIconSize() {
        return this.deviceIconSize;
    }

    public final View getDeviceIconView() {
        return this.deviceIconView;
    }

    public final View getDivider() {
        return this.divider;
    }

    public final int getFromAudioButtonSize() {
        return this.fromAudioButtonSize;
    }

    public final float getFromNoPlayFontSize() {
        return this.fromNoPlayFontSize;
    }

    public final float getFromSubtitleFontSize() {
        return this.fromSubtitleFontSize;
    }

    public final float getFromTitleFontSize() {
        return this.fromTitleFontSize;
    }

    public final QSControlMiPlayDetailHeader getHeaderLayout() {
        return this.headerLayout;
    }

    public final int getHeaderPaddingStartEnd() {
        return this.headerPaddingStartEnd;
    }

    public final int getHeaderPaddingTop() {
        return this.headerPaddingTop;
    }

    public final View getListView() {
        return this.listView;
    }

    public final boolean getListening() {
        return this.listening;
    }

    public final ViewGroup getMetaInfo() {
        return this.metaInfo;
    }

    public final TextView getNoPlayView() {
        return this.noPlayView;
    }

    public final View getProgressContainerView() {
        return this.progressContainerView;
    }

    public final TextView getSubtitleView() {
        return this.subtitleView;
    }

    public final Typeface getTitleOriginalTypeface() {
        return this.titleOriginalTypeface;
    }

    public final TextView getTitleView() {
        return this.titleView;
    }

    public final int getToAudioButtonSize() {
        return this.toAudioButtonSize;
    }

    public final float getToNoPlayFontSize() {
        return this.toNoPlayFontSize;
    }

    public final float getToSubtitleFontSize() {
        return this.toSubtitleFontSize;
    }

    public final float getToTitleFontSize() {
        return this.toTitleFontSize;
    }

    public final View getTvContainerView() {
        return this.tvContainerView;
    }

    public final View getVolumeBarContainerView() {
        return this.volumeBarContainerView;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        boolean zDpiChanged = configUtils.dpiChanged(i2);
        boolean zTextsChanged = configUtils.textsChanged(i2);
        boolean zColorsChanged = configUtils.colorsChanged(i2);
        boolean zThemeChanged = configUtils.themeChanged(i2);
        boolean zBlurChanged = configUtils.blurChanged(i2);
        boolean zFontSizeChanged = configUtils.fontSizeChanged(i2);
        boolean z2 = getContext().getResources().getDisplayMetrics().densityDpi != this.lastDensity;
        Log.d(TAG, "onConfigurationChanged: densityChanged=" + z2 + ", orientationChanged=" + zOrientationChanged + ", dimensionChanged=" + zDimensionsChanged + ", dpiChanged=" + zDpiChanged + ", textsChanged=" + zTextsChanged + ", colorsChanged=" + zColorsChanged + ", themeChanged=" + zThemeChanged + ", blurChanged=" + zBlurChanged + ", fontSizeChanged=" + zFontSizeChanged + " ");
        if (zDpiChanged || zTextsChanged || z2 || zThemeChanged || zBlurChanged) {
            this.needReInit = true;
        }
        if (zOrientationChanged || zDimensionsChanged) {
            updateSize();
        }
        if (zDimensionsChanged || zColorsChanged || zTextsChanged || zFontSizeChanged) {
            updateTextAppearance();
        }
        if (zThemeChanged) {
            updateResources();
        }
        if (zBlurChanged) {
            updateBlur();
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        updateSize();
        panelIdleInit$default(this, false, 1, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        this.contentView = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onHidden() {
        super.onHidden();
        View view = this.contentView;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = view instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view : null;
        if (qSControlMiPlayDetailContent != null) {
            qSControlMiPlayDetailContent.hideDynamicIsland(false);
        }
        setMiPlayDetailShowing$default(this, false, false, 2, null);
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.hiddenAnim();
        }
        shieldJumpViewClick(false);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onShown() {
        super.onShown();
        shieldJumpViewClick(false);
        setMiPlayDetailShowing$default(this, true, false, 2, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onUserSwitched(int i2) {
        super.onUserSwitched(i2);
        if (i2 != this.lastId) {
            this.lastId = i2;
            this.needReInit = true;
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareHide() {
        super.prepareHide();
        switchAVViews();
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.prepareHidePanel();
        }
    }

    public final void setAppIconContainerView(View view) {
        this.appIconContainerView = view;
    }

    public final void setAppIconMargin(int i2) {
        this.appIconMargin = i2;
    }

    public final void setAppIconView(View view) {
        this.appIconView = view;
    }

    public final void setAvContainerView(View view) {
        this.avContainerView = view;
    }

    public final void setAvNextView(View view) {
        this.avNextView = view;
    }

    public final void setAvPlayView(View view) {
        this.avPlayView = view;
    }

    public final void setAvPrevView(View view) {
        this.avPrevView = view;
    }

    public final void setCoverParentView(ViewGroup viewGroup) {
        this.coverParentView = viewGroup;
    }

    public final void setCoverRadius(float f2) {
        this.coverRadius = f2;
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader == null) {
            return;
        }
        qSControlMiPlayDetailHeader.setCoverRadius(f2);
    }

    public final void setCoverView(View view) {
        this.coverView = view;
    }

    public final void setDeviceIconMargin(int i2) {
        this.deviceIconMargin = i2;
    }

    public final void setDeviceIconSize(int i2) {
        this.deviceIconSize = i2;
    }

    public final void setDeviceIconView(View view) {
        this.deviceIconView = view;
    }

    public final void setDivider(View view) {
        this.divider = view;
    }

    public final void setFromAudioButtonSize(int i2) {
        this.fromAudioButtonSize = i2;
    }

    public final void setFromNoPlayFontSize(float f2) {
        this.fromNoPlayFontSize = f2;
    }

    public final void setFromSubtitleFontSize(float f2) {
        this.fromSubtitleFontSize = f2;
    }

    public final void setFromTitleFontSize(float f2) {
        this.fromTitleFontSize = f2;
    }

    public final void setHeaderLayout(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader) {
        this.headerLayout = qSControlMiPlayDetailHeader;
    }

    public final void setHeaderPaddingStartEnd(int i2) {
        this.headerPaddingStartEnd = i2;
    }

    public final void setHeaderPaddingTop(int i2) {
        this.headerPaddingTop = i2;
    }

    public final void setListView(View view) {
        this.listView = view;
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
    }

    public final void setMetaInfo(ViewGroup viewGroup) {
        this.metaInfo = viewGroup;
    }

    public final void setNoPlayView(TextView textView) {
        this.noPlayView = textView;
    }

    public final void setProgressContainerView(View view) {
        this.progressContainerView = view;
    }

    public final void setSubtitleView(TextView textView) {
        this.subtitleView = textView;
    }

    public final void setTitleOriginalTypeface(Typeface typeface) {
        this.titleOriginalTypeface = typeface;
    }

    public final void setTitleView(TextView textView) {
        this.titleView = textView;
    }

    public final void setToAudioButtonSize(int i2) {
        this.toAudioButtonSize = i2;
    }

    public final void setToNoPlayFontSize(float f2) {
        this.toNoPlayFontSize = f2;
    }

    public final void setToSubtitleFontSize(float f2) {
        this.toSubtitleFontSize = f2;
    }

    public final void setToTitleFontSize(float f2) {
        this.toTitleFontSize = f2;
    }

    public final void setTvContainerView(View view) {
        this.tvContainerView = view;
    }

    public final void setVolumeBarContainerView(View view) {
        this.volumeBarContainerView = view;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void startHide() {
        super.startHide();
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.startHidePanel();
        }
        shieldJumpViewClick(true);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(MediaPanelParams mediaPanelParams) {
        super.prepareShow(mediaPanelParams);
        initView();
        updateSize();
        updateFromViewSize(mediaPanelParams != null ? mediaPanelParams.getFromView() : null);
        updateTextAppearance();
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = this.headerLayout;
        if (qSControlMiPlayDetailHeader != null) {
            qSControlMiPlayDetailHeader.prepareShowPanel();
        }
        View view = this.contentView;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = view instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view : null;
        if (qSControlMiPlayDetailContent != null) {
            qSControlMiPlayDetailContent.prepareShowPanel();
        }
        View view2 = this.contentView;
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent2 = view2 instanceof QSControlMiPlayDetailContent ? (QSControlMiPlayDetailContent) view2 : null;
        if (qSControlMiPlayDetailContent2 != null) {
            qSControlMiPlayDetailContent2.hideDynamicIsland(true);
        }
        switchAVViews();
        shieldJumpViewClick(true);
    }
}
