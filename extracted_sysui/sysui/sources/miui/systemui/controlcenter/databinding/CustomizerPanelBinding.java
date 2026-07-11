package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.panel.customize.CustomizePanel;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.LinearLayout;
import miui.systemui.widget.VisibleFocusedTextView;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizerPanelBinding implements ViewBinding {

    @NonNull
    public final ConstraintLayout addedContainer;

    @NonNull
    public final RecyclerView addedList;

    @NonNull
    public final CustomizePanel customizer;

    @NonNull
    public final ImageView icon;

    @NonNull
    public final ImageView indicator;

    @NonNull
    public final LinearLayout notAddedContainer;

    @NonNull
    public final RecyclerView notAddedList;

    @NonNull
    public final TextView othersTitle;

    @NonNull
    private final CustomizePanel rootView;

    @NonNull
    public final VisibleFocusedTextView save;

    @NonNull
    public final Space space;

    @NonNull
    public final VisibleFocusedTextView subtitle;

    @NonNull
    public final VisibleFocusedTextView title;

    private CustomizerPanelBinding(@NonNull CustomizePanel customizePanel, @NonNull ConstraintLayout constraintLayout, @NonNull RecyclerView recyclerView, @NonNull CustomizePanel customizePanel2, @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull LinearLayout linearLayout, @NonNull RecyclerView recyclerView2, @NonNull TextView textView, @NonNull VisibleFocusedTextView visibleFocusedTextView, @NonNull Space space, @NonNull VisibleFocusedTextView visibleFocusedTextView2, @NonNull VisibleFocusedTextView visibleFocusedTextView3) {
        this.rootView = customizePanel;
        this.addedContainer = constraintLayout;
        this.addedList = recyclerView;
        this.customizer = customizePanel2;
        this.icon = imageView;
        this.indicator = imageView2;
        this.notAddedContainer = linearLayout;
        this.notAddedList = recyclerView2;
        this.othersTitle = textView;
        this.save = visibleFocusedTextView;
        this.space = space;
        this.subtitle = visibleFocusedTextView2;
        this.title = visibleFocusedTextView3;
    }

    @NonNull
    public static CustomizerPanelBinding bind(@NonNull View view) {
        int i2 = R.id.added_container;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i2);
        if (constraintLayout != null) {
            i2 = R.id.added_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
            if (recyclerView != null) {
                CustomizePanel customizePanel = (CustomizePanel) view;
                i2 = android.R.id.icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, android.R.id.icon);
                if (imageView != null) {
                    i2 = R.id.indicator;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView2 != null) {
                        i2 = R.id.not_added_container;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                        if (linearLayout != null) {
                            i2 = R.id.not_added_list;
                            RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, i2);
                            if (recyclerView2 != null) {
                                i2 = R.id.others_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                                if (textView != null) {
                                    i2 = R.id.save;
                                    VisibleFocusedTextView visibleFocusedTextView = (VisibleFocusedTextView) ViewBindings.findChildViewById(view, i2);
                                    if (visibleFocusedTextView != null) {
                                        i2 = R.id.space;
                                        Space space = (Space) ViewBindings.findChildViewById(view, i2);
                                        if (space != null) {
                                            i2 = R.id.subtitle;
                                            VisibleFocusedTextView visibleFocusedTextView2 = (VisibleFocusedTextView) ViewBindings.findChildViewById(view, i2);
                                            if (visibleFocusedTextView2 != null) {
                                                i2 = android.R.id.title;
                                                VisibleFocusedTextView visibleFocusedTextView3 = (VisibleFocusedTextView) ViewBindings.findChildViewById(view, android.R.id.title);
                                                if (visibleFocusedTextView3 != null) {
                                                    return new CustomizerPanelBinding(customizePanel, constraintLayout, recyclerView, customizePanel, imageView, imageView2, linearLayout, recyclerView2, textView, visibleFocusedTextView, space, visibleFocusedTextView2, visibleFocusedTextView3);
                                                }
                                            }
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
    public static CustomizerPanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CustomizerPanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.customizer_panel, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CustomizePanel getRoot() {
        return this.rootView;
    }
}
