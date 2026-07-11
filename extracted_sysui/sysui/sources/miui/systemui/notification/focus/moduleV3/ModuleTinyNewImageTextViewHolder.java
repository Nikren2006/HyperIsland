package miui.systemui.notification.focus.moduleV3;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.AnimIconInfo;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.util.VideoResUtils;
import miui.systemui.widget.TextureVideoView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleTinyNewImageTextViewHolder extends ModuleViewHolder {
    private View container;
    private TextView focusContent;
    private View focusIconContainer;
    private ImageView focusProfile;
    private TextView focusSubContent;
    private TextView focusTitle;
    private TextureVideoView focusVideoView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleTinyNewImageTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void showEffects(Template template, StatusBarNotification statusBarNotification) {
        AnimIconInfo animIconInfo;
        ImageView imageView;
        AnimIconInfo animIconInfo2;
        AnimIconInfo animIconInfo3;
        Integer type;
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        String src = null;
        if (iconTextInfo != null && (animIconInfo3 = iconTextInfo.getAnimIconInfo()) != null && (type = animIconInfo3.getType()) != null && type.intValue() == 1) {
            TextureVideoView textureVideoView = this.focusVideoView;
            if (textureVideoView != null) {
                textureVideoView.setVisibility(0);
            }
            ImageView imageView2 = this.focusProfile;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            TextureVideoView textureVideoView2 = this.focusVideoView;
            if (textureVideoView2 != null) {
                VideoResUtils videoResUtils = VideoResUtils.INSTANCE;
                AnimIconInfo animIconInfo4 = template.getIconTextInfo().getAnimIconInfo();
                textureVideoView2.setVideoURI(Uri.parse(videoResUtils.getVideoRes(animIconInfo4 != null ? animIconInfo4.getSrc() : null, ModuleViewHolder.getContext$default(this, false, 1, null))));
            }
            TextureVideoView textureVideoView3 = this.focusVideoView;
            if (textureVideoView3 != null) {
                textureVideoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: miui.systemui.notification.focus.moduleV3.y
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer) {
                        ModuleTinyNewImageTextViewHolder.showEffects$lambda$3(this.f5875a, mediaPlayer);
                    }
                });
            }
            TextureVideoView textureVideoView4 = this.focusVideoView;
            if (textureVideoView4 != null) {
                textureVideoView4.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: miui.systemui.notification.focus.moduleV3.z
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public final boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                        return ModuleTinyNewImageTextViewHolder.showEffects$lambda$4(this.f5876a, mediaPlayer, i2, i3);
                    }
                });
                return;
            }
            return;
        }
        TextureVideoView textureVideoView5 = this.focusVideoView;
        if (textureVideoView5 != null) {
            textureVideoView5.setVisibility(8);
        }
        ImageView imageView3 = this.focusProfile;
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        View view = this.focusIconContainer;
        if (view != null) {
            view.setVisibility(0);
        }
        if (isDark()) {
            IconTextInfo iconTextInfo2 = template.getIconTextInfo();
            if (iconTextInfo2 != null && (animIconInfo2 = iconTextInfo2.getAnimIconInfo()) != null) {
                src = animIconInfo2.getSrcDark();
            }
        } else {
            IconTextInfo iconTextInfo3 = template.getIconTextInfo();
            if (iconTextInfo3 != null && (animIconInfo = iconTextInfo3.getAnimIconInfo()) != null) {
                src = animIconInfo.getSrc();
            }
        }
        Icon icon = getIcon(src, statusBarNotification);
        if (icon == null) {
            View view2 = this.focusIconContainer;
            if (view2 == null) {
                return;
            }
            view2.setVisibility(8);
            return;
        }
        View view3 = getView();
        if (view3 == null || (imageView = (ImageView) view3.findViewById(R.id.focus_profile)) == null) {
            return;
        }
        imageView.setImageIcon(icon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEffects$lambda$3(ModuleTinyNewImageTextViewHolder this$0, MediaPlayer mediaPlayer) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        mediaPlayer.setLooping(true);
        TextureVideoView textureVideoView = this$0.focusVideoView;
        if (textureVideoView != null) {
            textureVideoView.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showEffects$lambda$4(ModuleTinyNewImageTextViewHolder this$0, MediaPlayer mediaPlayer, int i2, int i3) {
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
        kotlin.jvm.internal.n.g(template, "template");
        kotlin.jvm.internal.n.g(sbn, "sbn");
        super.bind(template, sbn);
        initTextAndColor(template.getIconTextInfo());
        View view = this.container;
        if (view != null) {
            view.setVisibility(0);
        }
        TextView textView = this.focusTitle;
        if (textView != null) {
            textView.setVisibility(0);
        }
        TextView textView2 = this.focusTitle;
        if (textView2 != null) {
            textView2.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
        }
        Integer titleColor = getTitleColor();
        if (titleColor != null) {
            int iIntValue = titleColor.intValue();
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                textView3.setTextColor(iIntValue);
            }
        }
        if (TextUtils.isEmpty(getContent())) {
            TextView textView4 = this.focusContent;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
        } else {
            TextView textView5 = this.focusContent;
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
            TextView textView6 = this.focusContent;
            if (textView6 != null) {
                textView6.setText(Html.fromHtml(getContent()), TextView.BufferType.SPANNABLE);
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TextView textView7 = this.focusContent;
                if (textView7 != null) {
                    textView7.setTextColor(iIntValue2);
                }
            }
        }
        if (TextUtils.isEmpty(getSubContent())) {
            TextView textView8 = this.focusSubContent;
            if (textView8 != null) {
                textView8.setVisibility(8);
            }
        } else {
            TextView textView9 = this.focusSubContent;
            if (textView9 != null) {
                textView9.setVisibility(0);
            }
            TextView textView10 = this.focusSubContent;
            if (textView10 != null) {
                textView10.setText(Html.fromHtml(getSubContent()), TextView.BufferType.SPANNABLE);
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue3 = subContentColor.intValue();
                TextView textView11 = this.focusSubContent;
                if (textView11 != null) {
                    textView11.setTextColor(iIntValue3);
                }
            }
        }
        showEffects(template, sbn);
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void checkParams(Template template) throws FocusParamsException {
        kotlin.jvm.internal.n.g(template, "template");
        super.checkParams(template);
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        if (TextUtils.isEmpty(iconTextInfo != null ? iconTextInfo.getTitle() : null)) {
            throw new FocusParamsException("title is empty");
        }
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void initView(String module) {
        kotlin.jvm.internal.n.g(module, "module");
        super.initView(module);
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_tiny_new_image_text, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_new_image_text) : null;
        View view2 = getView();
        this.focusTitle = view2 != null ? (TextView) view2.findViewById(R.id.focus_title) : null;
        View view3 = getView();
        this.focusContent = view3 != null ? (TextView) view3.findViewById(R.id.focus_content) : null;
        View view4 = getView();
        this.focusSubContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_sub_content) : null;
        View view5 = getView();
        this.focusIconContainer = view5 != null ? view5.findViewById(R.id.focus_icon_container) : null;
        View view6 = getView();
        this.focusProfile = view6 != null ? (ImageView) view6.findViewById(R.id.focus_profile) : null;
        View view7 = getView();
        this.focusVideoView = view7 != null ? (TextureVideoView) view7.findViewById(R.id.focus_video) : null;
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
