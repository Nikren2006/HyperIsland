package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerPanel;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.ImageView;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerViewBinding implements ViewBinding {

    @NonNull
    public final TextView artist;

    @NonNull
    public final ConstraintLayout audioContainer;

    @NonNull
    public final ImageView cover;

    @NonNull
    public final ImageView deviceIcon;

    @NonNull
    public final TextView emptyState;

    @NonNull
    public final MediaPlayerPanel mediaPlayer;

    @NonNull
    public final ImageView next;

    @NonNull
    public final ImageView play;

    @NonNull
    public final ImageView prev;

    @NonNull
    private final MediaPlayerPanel rootView;

    @NonNull
    public final TextView title;

    private MediaPlayerViewBinding(@NonNull MediaPlayerPanel mediaPlayerPanel, @NonNull TextView textView, @NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull TextView textView2, @NonNull MediaPlayerPanel mediaPlayerPanel2, @NonNull ImageView imageView3, @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull TextView textView3) {
        this.rootView = mediaPlayerPanel;
        this.artist = textView;
        this.audioContainer = constraintLayout;
        this.cover = imageView;
        this.deviceIcon = imageView2;
        this.emptyState = textView2;
        this.mediaPlayer = mediaPlayerPanel2;
        this.next = imageView3;
        this.play = imageView4;
        this.prev = imageView5;
        this.title = textView3;
    }

    @NonNull
    public static MediaPlayerViewBinding bind(@NonNull View view) {
        int i2 = R.id.artist;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R.id.audio_container;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i2);
            if (constraintLayout != null) {
                i2 = R.id.cover;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
                if (imageView != null) {
                    i2 = R.id.device_icon;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView2 != null) {
                        i2 = R.id.empty_state;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView2 != null) {
                            MediaPlayerPanel mediaPlayerPanel = (MediaPlayerPanel) view;
                            i2 = R.id.next;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i2);
                            if (imageView3 != null) {
                                i2 = R.id.play;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                if (imageView4 != null) {
                                    i2 = R.id.prev;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i2);
                                    if (imageView5 != null) {
                                        i2 = R.id.title;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i2);
                                        if (textView3 != null) {
                                            return new MediaPlayerViewBinding(mediaPlayerPanel, textView, constraintLayout, imageView, imageView2, textView2, mediaPlayerPanel, imageView3, imageView4, imageView5, textView3);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MediaPlayerViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MediaPlayerViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.media_player_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MediaPlayerPanel getRoot() {
        return this.rootView;
    }
}
