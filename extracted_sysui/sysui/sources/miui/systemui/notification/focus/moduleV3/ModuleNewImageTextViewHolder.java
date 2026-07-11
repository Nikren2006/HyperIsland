package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import com.mi.widget.view.FlashLightView;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.AnimIconInfo;
import miui.systemui.notification.focus.model.BaseInfo;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.VideoResUtils;
import miui.systemui.widget.TextureVideoView;
import miuix.colorful.texteffect.TextChangeHelper;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleNewImageTextViewHolder extends ModuleViewHolder {
    private View container;
    private TimerTextEffectView focusContent;
    private FlashLightView focusFlashView;
    private View focusIconContainer;
    private ImageView focusProfile;
    private TimerTextEffectView focusSubContent;
    private TimerTextEffectView focusTextDivider;
    private TimerTextEffectView focusTitle;
    private TextureVideoView focusVideoView;
    private final boolean island;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleNewImageTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2, boolean z3) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
        this.island = z3;
    }

    private final void adaptContentViews() {
        ViewGroup.LayoutParams layoutParams;
        if (getTextViewWidth(this.focusSubContent) > 0) {
            TimerTextEffectView timerTextEffectView = this.focusContent;
            ViewGroup.LayoutParams layoutParams2 = timerTextEffectView != null ? timerTextEffectView.getLayoutParams() : null;
            kotlin.jvm.internal.n.e(layoutParams2, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
            TimerTextEffectView timerTextEffectView2 = this.focusSubContent;
            layoutParams = timerTextEffectView2 != null ? timerTextEffectView2.getLayoutParams() : null;
            kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams3.weight = 0.0f;
            layoutParams4.weight = 0.0f;
            layoutParams3.width = -2;
            layoutParams4.width = -2;
            TimerTextEffectView timerTextEffectView3 = this.focusContent;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setLayoutParams(layoutParams3);
            }
            TimerTextEffectView timerTextEffectView4 = this.focusSubContent;
            if (timerTextEffectView4 == null) {
                return;
            }
            timerTextEffectView4.setLayoutParams(layoutParams4);
            return;
        }
        TimerTextEffectView timerTextEffectView5 = this.focusContent;
        ViewGroup.LayoutParams layoutParams5 = timerTextEffectView5 != null ? timerTextEffectView5.getLayoutParams() : null;
        kotlin.jvm.internal.n.e(layoutParams5, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
        TimerTextEffectView timerTextEffectView6 = this.focusSubContent;
        layoutParams = timerTextEffectView6 != null ? timerTextEffectView6.getLayoutParams() : null;
        kotlin.jvm.internal.n.e(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams6.weight = 1.0f;
        layoutParams7.weight = 1.0f;
        layoutParams6.width = 0;
        layoutParams7.width = 0;
        TimerTextEffectView timerTextEffectView7 = this.focusContent;
        if (timerTextEffectView7 != null) {
            timerTextEffectView7.setLayoutParams(layoutParams6);
        }
        TimerTextEffectView timerTextEffectView8 = this.focusSubContent;
        if (timerTextEffectView8 == null) {
            return;
        }
        timerTextEffectView8.setLayoutParams(layoutParams7);
    }

    private final void showEffects(Template template, StatusBarNotification statusBarNotification) {
        AnimIconInfo animIconInfo;
        ImageView imageView;
        AnimIconInfo animIconInfo2;
        AnimIconInfo animIconInfo3;
        Integer type;
        float[] flashLightOffset;
        float[] flashLightOffset2;
        float[] flashLightOffset3;
        float[] flashLightOffset4;
        AnimIconInfo animIconInfo4;
        Integer type2;
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        String src = null;
        if (iconTextInfo != null && (animIconInfo4 = iconTextInfo.getAnimIconInfo()) != null && (type2 = animIconInfo4.getType()) != null && type2.intValue() == 1) {
            TextureVideoView textureVideoView = this.focusVideoView;
            if (textureVideoView != null) {
                textureVideoView.setVisibility(0);
            }
            ImageView imageView2 = this.focusProfile;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            FlashLightView flashLightView = this.focusFlashView;
            if (flashLightView != null) {
                flashLightView.setVisibility(8);
            }
            TextureVideoView textureVideoView2 = this.focusVideoView;
            if (textureVideoView2 != null) {
                VideoResUtils videoResUtils = VideoResUtils.INSTANCE;
                AnimIconInfo animIconInfo5 = template.getIconTextInfo().getAnimIconInfo();
                textureVideoView2.setVideoURI(Uri.parse(videoResUtils.getVideoRes(animIconInfo5 != null ? animIconInfo5.getSrc() : null, ModuleViewHolder.getContext$default(this, false, 1, null))));
            }
            TextureVideoView textureVideoView3 = this.focusVideoView;
            if (textureVideoView3 != null) {
                textureVideoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: miui.systemui.notification.focus.moduleV3.r
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer) {
                        ModuleNewImageTextViewHolder.showEffects$lambda$3(this.f5859a, mediaPlayer);
                    }
                });
            }
            TextureVideoView textureVideoView4 = this.focusVideoView;
            if (textureVideoView4 != null) {
                textureVideoView4.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: miui.systemui.notification.focus.moduleV3.s
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public final boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                        return ModuleNewImageTextViewHolder.showEffects$lambda$4(this.f5860a, mediaPlayer, i2, i3);
                    }
                });
                return;
            }
            return;
        }
        IconTextInfo iconTextInfo2 = template.getIconTextInfo();
        if (iconTextInfo2 != null && (animIconInfo3 = iconTextInfo2.getAnimIconInfo()) != null && (type = animIconInfo3.getType()) != null && type.intValue() == 2) {
            TextureVideoView textureVideoView5 = this.focusVideoView;
            if (textureVideoView5 != null) {
                textureVideoView5.setVisibility(8);
            }
            ImageView imageView3 = this.focusProfile;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
            }
            View view = this.focusIconContainer;
            if (view != null) {
                view.setVisibility(8);
            }
            FlashLightView flashLightView2 = this.focusFlashView;
            if (flashLightView2 != null) {
                flashLightView2.setVisibility(0);
            }
            FlashLightView flashLightView3 = this.focusFlashView;
            if (flashLightView3 != null && (flashLightOffset4 = flashLightView3.getFlashLightOffset()) != null) {
                flashLightOffset4[0] = 0.0f;
            }
            FlashLightView flashLightView4 = this.focusFlashView;
            if (flashLightView4 != null && (flashLightOffset3 = flashLightView4.getFlashLightOffset()) != null) {
                flashLightOffset3[1] = 0.14f;
            }
            FlashLightView flashLightView5 = this.focusFlashView;
            if (flashLightView5 != null && (flashLightOffset2 = flashLightView5.getFlashLightOffset()) != null) {
                flashLightOffset2[2] = -50.0f;
            }
            FlashLightView flashLightView6 = this.focusFlashView;
            if (flashLightView6 != null && (flashLightOffset = flashLightView6.getFlashLightOffset()) != null) {
                flashLightOffset[3] = 0.0f;
            }
            Log.d(ModuleViewHolder.TAG, "focusFlashView VISIBLE");
            return;
        }
        TextureVideoView textureVideoView6 = this.focusVideoView;
        if (textureVideoView6 != null) {
            textureVideoView6.setVisibility(8);
        }
        ImageView imageView4 = this.focusProfile;
        if (imageView4 != null) {
            imageView4.setVisibility(0);
        }
        View view2 = this.focusIconContainer;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        FlashLightView flashLightView7 = this.focusFlashView;
        if (flashLightView7 != null) {
            flashLightView7.setVisibility(8);
        }
        if (isDark()) {
            IconTextInfo iconTextInfo3 = template.getIconTextInfo();
            if (iconTextInfo3 != null && (animIconInfo2 = iconTextInfo3.getAnimIconInfo()) != null) {
                src = animIconInfo2.getSrcDark();
            }
        } else {
            IconTextInfo iconTextInfo4 = template.getIconTextInfo();
            if (iconTextInfo4 != null && (animIconInfo = iconTextInfo4.getAnimIconInfo()) != null) {
                src = animIconInfo.getSrc();
            }
        }
        Icon icon = getIcon(src, statusBarNotification);
        if (icon == null) {
            View view3 = this.focusIconContainer;
            if (view3 == null) {
                return;
            }
            view3.setVisibility(8);
            return;
        }
        View view4 = getView();
        if (view4 == null || (imageView = (ImageView) view4.findViewById(R.id.focus_profile)) == null) {
            return;
        }
        imageView.setImageIcon(icon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEffects$lambda$3(ModuleNewImageTextViewHolder this$0, MediaPlayer mediaPlayer) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        mediaPlayer.setLooping(true);
        TextureVideoView textureVideoView = this$0.focusVideoView;
        if (textureVideoView != null) {
            textureVideoView.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showEffects$lambda$4(ModuleNewImageTextViewHolder this$0, MediaPlayer mediaPlayer, int i2, int i3) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        TextureVideoView textureVideoView = this$0.focusVideoView;
        if (textureVideoView == null) {
            return true;
        }
        textureVideoView.stopPlayback();
        return true;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void bind(Template template, StatusBarNotification sbn) {
        AnimIconInfo animIconInfo;
        Integer type;
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        if (iconTextInfo == null || (animIconInfo = iconTextInfo.getAnimIconInfo()) == null || (type = animIconInfo.getType()) == null || type.intValue() != 2) {
            initTextAndColor(template.getIconTextInfo());
        } else {
            initTextAndColor(template.getBaseInfo());
        }
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        if (TextUtils.isEmpty(getTitle())) {
            TimerTextEffectView timerTextEffectView = this.focusTitle;
            if (timerTextEffectView != null) {
                timerTextEffectView.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView2 = this.focusTitle;
            if (timerTextEffectView2 != null) {
                timerTextEffectView2.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView3 = this.focusTitle;
            if (timerTextEffectView3 != null) {
                timerTextEffectView3.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
            }
            Integer titleColor = getTitleColor();
            if (titleColor != null) {
                int iIntValue = titleColor.intValue();
                TimerTextEffectView timerTextEffectView4 = this.focusTitle;
                if (timerTextEffectView4 != null) {
                    timerTextEffectView4.updateTextWithNewAppearance(Html.fromHtml(getTitle()), Integer.valueOf(iIntValue));
                }
            }
        }
        if (TextUtils.isEmpty(getContent())) {
            TimerTextEffectView timerTextEffectView5 = this.focusContent;
            if (timerTextEffectView5 != null) {
                timerTextEffectView5.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView6 = this.focusContent;
            if (timerTextEffectView6 != null) {
                timerTextEffectView6.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView7 = this.focusContent;
            if (timerTextEffectView7 != null) {
                timerTextEffectView7.setText(Html.fromHtml(getContent()), TextView.BufferType.SPANNABLE);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TimerTextEffectView timerTextEffectView8 = this.focusContent;
                if (timerTextEffectView8 != null) {
                    timerTextEffectView8.updateTextWithNewAppearance(Html.fromHtml(getContent()), Integer.valueOf(iIntValue2));
                }
            }
        }
        if (TextUtils.isEmpty(getSubContent())) {
            TimerTextEffectView timerTextEffectView9 = this.focusTextDivider;
            if (timerTextEffectView9 != null) {
                timerTextEffectView9.setVisibility(8);
            }
            TimerTextEffectView timerTextEffectView10 = this.focusSubContent;
            if (timerTextEffectView10 != null) {
                timerTextEffectView10.setVisibility(8);
            }
        } else {
            TimerTextEffectView timerTextEffectView11 = this.focusTextDivider;
            if (timerTextEffectView11 != null) {
                timerTextEffectView11.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView12 = this.focusSubContent;
            if (timerTextEffectView12 != null) {
                timerTextEffectView12.setVisibility(0);
            }
            TimerTextEffectView timerTextEffectView13 = this.focusSubContent;
            if (timerTextEffectView13 != null) {
                timerTextEffectView13.setText(Html.fromHtml(getSubContent()), TextView.BufferType.SPANNABLE);
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue3 = subContentColor.intValue();
                TimerTextEffectView timerTextEffectView14 = this.focusSubContent;
                if (timerTextEffectView14 != null) {
                    timerTextEffectView14.updateTextWithNewAppearance(Html.fromHtml(getSubContent()), Integer.valueOf(iIntValue3));
                }
            }
        }
        showEffects(template, sbn);
        adaptContentViews();
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void checkParams(Template template) throws FocusParamsException {
        AnimIconInfo animIconInfo;
        Integer type;
        kotlin.jvm.internal.n.g(template, "template");
        super.checkParams(template);
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        if (iconTextInfo == null || (animIconInfo = iconTextInfo.getAnimIconInfo()) == null || (type = animIconInfo.getType()) == null || type.intValue() != 2) {
            IconTextInfo iconTextInfo2 = template.getIconTextInfo();
            if (TextUtils.isEmpty(iconTextInfo2 != null ? iconTextInfo2.getTitle() : null)) {
                throw new FocusParamsException("title is empty");
            }
        } else {
            BaseInfo baseInfo = template.getBaseInfo();
            if (TextUtils.isEmpty(baseInfo != null ? baseInfo.getTitle() : null)) {
                throw new FocusParamsException("title is empty");
            }
        }
    }

    public final boolean getIsland() {
        return this.island;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(getContext(this.island)).inflate(R.layout.focus_notification_module_new_image_text, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_new_image_text) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (TimerTextEffectView) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.focusContent = view3 != null ? (TimerTextEffectView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.focusTextDivider = view4 != null ? (TimerTextEffectView) view4.findViewById(R.id.focus_function_text_divider) : null;
        View view5 = getView();
        this.focusSubContent = view5 != null ? (TimerTextEffectView) view5.findViewById(R.id.focus_sub_content) : null;
        View view6 = getView();
        this.focusIconContainer = view6 != null ? view6.findViewById(R.id.focus_icon_container) : null;
        View view7 = getView();
        this.focusProfile = view7 != null ? (ImageView) view7.findViewById(R.id.focus_profile) : null;
        View view8 = getView();
        this.focusVideoView = view8 != null ? (TextureVideoView) view8.findViewById(R.id.focus_video) : null;
        View view9 = getView();
        this.focusFlashView = view9 != null ? (FlashLightView) view9.findViewById(R.id.focus_flash) : null;
        TimerTextEffectView timerTextEffectView = this.focusTitle;
        if (timerTextEffectView != null) {
            timerTextEffectView.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView2 = this.focusTitle;
        if (timerTextEffectView2 != null) {
            timerTextEffectView2.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView3 = this.focusContent;
        if (timerTextEffectView3 != null) {
            timerTextEffectView3.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView4 = this.focusContent;
        if (timerTextEffectView4 != null) {
            timerTextEffectView4.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView5 = this.focusTextDivider;
        if (timerTextEffectView5 != null) {
            timerTextEffectView5.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView6 = this.focusTextDivider;
        if (timerTextEffectView6 != null) {
            timerTextEffectView6.setEnableEffectWithInit(false);
        }
        TimerTextEffectView timerTextEffectView7 = this.focusSubContent;
        if (timerTextEffectView7 != null) {
            timerTextEffectView7.setTextChangeProcessor(new TextChangeHelper());
        }
        TimerTextEffectView timerTextEffectView8 = this.focusSubContent;
        if (timerTextEffectView8 == null) {
            return;
        }
        timerTextEffectView8.setEnableEffectWithInit(false);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        super.onDetach();
        TextureVideoView textureVideoView = this.focusVideoView;
        if (textureVideoView != null) {
            textureVideoView.stopPlayback();
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void updatePartial(Template template, StatusBarNotification sbn) {
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.updatePartial(template, sbn);
        initTextAndColor(template.getIconTextInfo());
        bind(template, sbn);
    }
}
