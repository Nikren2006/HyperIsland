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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.miui.notification.R;
import miui.systemui.notification.focus.FocusParamsException;
import miui.systemui.notification.focus.model.AnimIconInfo;
import miui.systemui.notification.focus.model.IconTextInfo;
import miui.systemui.notification.focus.model.PicInfo;
import miui.systemui.notification.focus.model.Template;
import miui.systemui.notification.focus.moduleV3.ext.ModuleViewHolderExtKt;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.VideoResUtils;
import miui.systemui.widget.TextureVideoView;

/* JADX INFO: loaded from: classes4.dex */
public final class ModuleDecoPortNewImageTextViewHolder extends ModuleViewHolder {
    private View container;
    private TextView focusContent;
    private View focusIconContainer;
    private ImageView focusProfile;
    private TextView focusSubContent;
    private TextView focusTitle;
    private LinearLayout focusTitleContainer;
    private TextureVideoView focusVideoView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDecoPortNewImageTextViewHolder(Context ctx, Context sysuiCtx, ViewGroup rootView, boolean z2) {
        super(ctx, sysuiCtx, rootView, z2);
        kotlin.jvm.internal.n.g(ctx, "ctx");
        kotlin.jvm.internal.n.g(sysuiCtx, "sysuiCtx");
        kotlin.jvm.internal.n.g(rootView, "rootView");
    }

    private final void showEffects(Template template, StatusBarNotification statusBarNotification, boolean z2) {
        AnimIconInfo animIconInfo;
        ImageView imageView;
        AnimIconInfo animIconInfo2;
        AnimIconInfo animIconInfo3;
        Integer type;
        int dimension = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_port_icon_ear_size);
        int dimension2 = (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_port_icon_size);
        if (!z2) {
            dimension = dimension2;
        }
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
                textureVideoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: miui.systemui.notification.focus.moduleV3.p
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer) {
                        ModuleDecoPortNewImageTextViewHolder.showEffects$lambda$7(this.f5857a, mediaPlayer);
                    }
                });
            }
            TextureVideoView textureVideoView4 = this.focusVideoView;
            if (textureVideoView4 != null) {
                textureVideoView4.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: miui.systemui.notification.focus.moduleV3.q
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public final boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                        return ModuleDecoPortNewImageTextViewHolder.showEffects$lambda$8(this.f5858a, mediaPlayer, i2, i3);
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
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        layoutParams.width = dimension;
        layoutParams.height = dimension;
        imageView.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEffects$lambda$7(ModuleDecoPortNewImageTextViewHolder this$0, MediaPlayer mediaPlayer) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        mediaPlayer.setLooping(true);
        TextureVideoView textureVideoView = this$0.focusVideoView;
        if (textureVideoView != null) {
            textureVideoView.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showEffects$lambda$8(ModuleDecoPortNewImageTextViewHolder this$0, MediaPlayer mediaPlayer, int i2, int i3) {
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
        Integer type;
        Integer type2;
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
        String title = getTitle();
        if (title == null || !ModuleViewHolderExtKt.containsHTML(title)) {
            TextView textView2 = this.focusTitle;
            if (textView2 != null) {
                textView2.setText(getTitle());
            }
        } else {
            TextView textView3 = this.focusTitle;
            if (textView3 != null) {
                textView3.setText(Html.fromHtml(getTitle()), TextView.BufferType.SPANNABLE);
            }
        }
        Integer titleColor = getTitleColor();
        if (titleColor != null) {
            int iIntValue = titleColor.intValue();
            TextView textView4 = this.focusTitle;
            if (textView4 != null) {
                textView4.setTextColor(iIntValue);
            }
        }
        if (TextUtils.isEmpty(getContent())) {
            TextView textView5 = this.focusContent;
            if (textView5 != null) {
                textView5.setVisibility(8);
            }
        } else {
            TextView textView6 = this.focusContent;
            if (textView6 != null) {
                textView6.setVisibility(0);
            }
            String content = getContent();
            if (content == null || !ModuleViewHolderExtKt.containsHTML(content)) {
                TextView textView7 = this.focusContent;
                if (textView7 != null) {
                    textView7.setText(getContent());
                }
            } else {
                TextView textView8 = this.focusContent;
                if (textView8 != null) {
                    textView8.setText(Html.fromHtml(getContent()), TextView.BufferType.SPANNABLE);
                }
            }
            Integer contentColor = getContentColor();
            if (contentColor != null) {
                int iIntValue2 = contentColor.intValue();
                TextView textView9 = this.focusContent;
                if (textView9 != null) {
                    textView9.setTextColor(iIntValue2);
                }
            }
        }
        if (TextUtils.isEmpty(getSubContent())) {
            TextView textView10 = this.focusSubContent;
            if (textView10 != null) {
                textView10.setVisibility(8);
            }
        } else {
            TextView textView11 = this.focusSubContent;
            if (textView11 != null) {
                textView11.setVisibility(0);
            }
            String subContent = getSubContent();
            if (subContent == null || !ModuleViewHolderExtKt.containsHTML(subContent)) {
                TextView textView12 = this.focusSubContent;
                if (textView12 != null) {
                    textView12.setText(getSubContent());
                }
            } else {
                TextView textView13 = this.focusSubContent;
                if (textView13 != null) {
                    textView13.setText(Html.fromHtml(getSubContent()), TextView.BufferType.SPANNABLE);
                }
            }
            Integer subContentColor = getSubContentColor();
            if (subContentColor != null) {
                int iIntValue3 = subContentColor.intValue();
                TextView textView14 = this.focusSubContent;
                if (textView14 != null) {
                    textView14.setTextColor(iIntValue3);
                }
            }
        }
        IconTextInfo iconTextInfo = template.getIconTextInfo();
        if (iconTextInfo == null || (type2 = iconTextInfo.getType()) == null || type2.intValue() != 1) {
            setTextLinesByTitle(getTitle(), getSubContent(), this.focusTitle, this.focusContent, this.focusSubContent);
            LinearLayout linearLayout = this.focusTitleContainer;
            if (linearLayout != null) {
                linearLayout.setGravity(8388611);
            }
            PicInfo picInfo = template.getPicInfo();
            if (!((picInfo == null || (type = picInfo.getType()) == null || type.intValue() != 1) ? false : true)) {
                showEffects(template, sbn, false);
                return;
            }
            View view2 = this.focusIconContainer;
            if (view2 == null) {
                return;
            }
            view2.setVisibility(8);
            return;
        }
        LinearLayout linearLayout2 = this.focusTitleContainer;
        if (linearLayout2 != null) {
            linearLayout2.setGravity(1);
        }
        TextView textView15 = this.focusTitle;
        if (textView15 != null) {
            CommonUtils.setMarginTop$default(CommonUtils.INSTANCE, textView15, (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_port_title_padding), false, 2, null);
        }
        TextView textView16 = this.focusContent;
        if (textView16 != null) {
            CommonUtils.setMarginTop$default(CommonUtils.INSTANCE, textView16, (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_port_title_padding), false, 2, null);
        }
        TextView textView17 = this.focusSubContent;
        if (textView17 != null) {
            CommonUtils.setMarginTop$default(CommonUtils.INSTANCE, textView17, (int) getCtx().getResources().getDimension(R.dimen.focus_notify_deco_port_title_padding), false, 2, null);
        }
        showEffects(template, sbn, true);
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
        setView(LayoutInflater.from(ModuleViewHolder.getContext$default(this, false, 1, null)).inflate(R.layout.focus_notification_module_deco_port_new_image_text, getRootView()));
        View view = getView();
        this.container = view != null ? view.findViewById(R.id.focus_container_module_new_image_text) : null;
        View view2 = getView();
        this.focusTitleContainer = view2 != null ? (LinearLayout) view2.findViewById(R.id.focus_title_container) : null;
        View view3 = getView();
        this.focusTitle = view3 != null ? (TextView) view3.findViewById(R.id.focus_title) : null;
        View view4 = getView();
        this.focusContent = view4 != null ? (TextView) view4.findViewById(R.id.focus_content) : null;
        View view5 = getView();
        this.focusSubContent = view5 != null ? (TextView) view5.findViewById(R.id.focus_sub_content) : null;
        View view6 = getView();
        this.focusVideoView = view6 != null ? (TextureVideoView) view6.findViewById(R.id.focus_video) : null;
        View view7 = getView();
        this.focusProfile = view7 != null ? (ImageView) view7.findViewById(R.id.focus_profile) : null;
        View view8 = getView();
        this.focusIconContainer = view8 != null ? view8.findViewById(R.id.focus_icon_container) : null;
    }

    @Override // miui.systemui.notification.focus.moduleV3.ModuleViewHolder
    public void onDetach() {
        super.onDetach();
        TextureVideoView textureVideoView = this.focusVideoView;
        if (textureVideoView != null) {
            textureVideoView.stopPlayback();
        }
    }

    public final void setTextLinesByTitle(String str, String str2, TextView textView, TextView textView2, TextView textView3) {
        int iCustomLength = str != null ? ModuleViewHolderExtKt.customLength(str) : 0;
        boolean z2 = str2 == null || str2.length() == 0;
        if (textView != null) {
            textView.setMaxLines(3);
        }
        if (iCustomLength <= 11) {
            if (z2) {
                if (textView2 != null) {
                    textView2.setMaxLines(4);
                }
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
            } else {
                if (textView2 != null) {
                    textView2.setMaxLines(3);
                }
                if (textView3 != null) {
                    textView3.setMaxLines(1);
                    textView3.setVisibility(0);
                }
            }
        } else if (iCustomLength <= 21) {
            if (z2) {
                if (textView2 != null) {
                    textView2.setMaxLines(3);
                }
                if (textView3 != null) {
                    textView3.setVisibility(8);
                }
            } else {
                if (textView2 != null) {
                    textView2.setMaxLines(2);
                }
                if (textView3 != null) {
                    textView3.setMaxLines(1);
                    textView3.setVisibility(0);
                }
            }
        } else if (iCustomLength > 31) {
            if (textView != null) {
                textView.setMaxLines(4);
            }
            if (textView2 != null) {
                textView2.setMaxLines(1);
            }
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        } else if (z2) {
            if (textView2 != null) {
                textView2.setMaxLines(2);
            }
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        } else {
            if (textView2 != null) {
                textView2.setMaxLines(1);
            }
            if (textView3 != null) {
                textView3.setMaxLines(1);
                textView3.setVisibility(0);
            }
        }
        for (TextView textView4 : I0.m.j(textView, textView2, textView3)) {
            if (textView4 != null) {
                textView4.setEllipsize(TextUtils.TruncateAt.END);
            }
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
