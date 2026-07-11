package com.android.systemui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.android.systemui.miplay.R;
import com.android.systemui.plugins.miui.controls.MiPlayEntranceViewCallback;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0369g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import m0.C0466a;
import miui.systemui.controlcenter.external.ExternalEntry;
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.concurrency.ConcurrencyModule;
import miui.systemui.widget.ConstraintLayout;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayView extends ConstraintLayout implements LifecycleOwner, ExternalEntry {
    public static final Companion Companion = new Companion(null);
    private static final int PREV_NEXT_TOUCH_INTERVAL = 2000;
    private static final String TAG = "ControlCenterMiPlayView";
    public TextView artist;
    private boolean collapse;
    private boolean listening;
    private final LifecycleRegistry mLifecycle;
    private MiPlayEntranceViewCallback miPlayEntranceViewCallback;
    public ImageView next;
    public ImageView play;
    public ImageView prev;
    private long prevNextTouchTime;
    public ImageView selectDevice;
    public TextView title;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlayView(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$10(MiPlayView this$0, kotlin.jvm.internal.y updateJob, Integer num) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(updateJob, "$updateJob");
        if (System.currentTimeMillis() - this$0.prevNextTouchTime >= 2000) {
            onFinishInflate$updatePlayButton(this$0);
        } else if (updateJob.f5059a == null) {
            updateJob.f5059a = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new MiPlayView$onFinishInflate$9$1(this$0, updateJob, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$2(MiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "play click");
        this$0.prevNextTouchTime = 0L;
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        C0466a value = miPlayDetailViewModel.getMActiveAudioSession().getValue();
        if (value == null || (wVarB = value.b()) == null) {
            wVarB = null;
        } else if (miPlayDetailViewModel.isLocalPlaying()) {
            wVarB.p();
            MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
        } else {
            wVarB.q();
            MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
        }
        if (wVarB == null) {
            QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
            Context context = this$0.getContext();
            kotlin.jvm.internal.n.f(context, "getContext(...)");
            companion.jumpLastPlayingApp(context);
            if (miPlayDetailViewModel.isLocalPlaying()) {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PAUSE, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            } else {
                MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PLAY, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$3(MiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "prev click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        C0466a value = MiPlayDetailViewModel.INSTANCE.getMActiveAudioSession().getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.t();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PREV, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$4(MiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "next click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        C0466a value = MiPlayDetailViewModel.INSTANCE.getMActiveAudioSession().getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.o();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NEXT, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$5(MiPlayView this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "selectDevice click");
        MiPlayEntranceViewCallback miPlayEntranceViewCallback = this$0.miPlayEntranceViewCallback;
        if (miPlayEntranceViewCallback != null) {
            miPlayEntranceViewCallback.showEntranceView();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CAST, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$6(MiPlayView this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(TAG, "view click");
        MiPlayEntranceViewCallback miPlayEntranceViewCallback = this$0.miPlayEntranceViewCallback;
        if (miPlayEntranceViewCallback != null) {
            miPlayEntranceViewCallback.showEntranceView();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CARD, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$7(MiPlayView this$0, Integer num) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ImageView selectDevice = this$0.getSelectDevice();
        Integer value = MiPlayDetailViewModel.INSTANCE.getMCastingDeviceIcon().getValue();
        kotlin.jvm.internal.n.d(value);
        selectDevice.setImageResource(value.intValue());
    }

    private static final void onFinishInflate$lambda$8(MiPlayView this$0, Boolean bool) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.d(bool);
        if (bool.booleanValue()) {
            this$0.getSelectDevice().setImageResource(R.drawable.miplay_select_device_working);
        } else {
            this$0.getSelectDevice().setImageResource(R.drawable.miplay_select_device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$9(MiPlayView this$0, MediaMetaData mediaMetaData) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (mediaMetaData == null) {
            this$0.getTitle().setText(this$0.getResources().getString(R.string.miplay_detail_header_no_song));
            this$0.getArtist().setVisibility(8);
            this$0.getTitle().setEnabled(false);
            this$0.getPrev().setEnabled(false);
            this$0.getPlay().setEnabled(true);
            this$0.getPlay().setImageResource(R.drawable.miplay_view_play);
            this$0.getPlay().setContentDescription(this$0.getContext().getString(R.string.miplay_accessibility_play));
            this$0.getNext().setEnabled(false);
            return;
        }
        CharSequence text = this$0.getTitle().getText();
        Context context = this$0.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        if (!kotlin.jvm.internal.n.c(text, MiPlayExtentionsKt.betterTitle(mediaMetaData, context))) {
            TextView title = this$0.getTitle();
            Context context2 = this$0.getContext();
            kotlin.jvm.internal.n.f(context2, "getContext(...)");
            title.setText(MiPlayExtentionsKt.betterTitle(mediaMetaData, context2));
        }
        if (TextUtils.isEmpty(mediaMetaData.getArtist())) {
            this$0.getArtist().setVisibility(8);
        } else {
            this$0.getArtist().setVisibility(0);
            this$0.getArtist().setText(mediaMetaData.getArtist());
        }
        this$0.getTitle().setEnabled(true);
        this$0.getPrev().setEnabled(true);
        this$0.getPlay().setEnabled(true);
        this$0.getNext().setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$updatePlayButton(MiPlayView miPlayView) {
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        if (miPlayDetailViewModel.getMMediaMetaData().getValue() != null) {
            if (miPlayDetailViewModel.isLocalPlaying()) {
                miPlayView.getPlay().setImageResource(R.drawable.miplay_view_pause);
                miPlayView.getPlay().setContentDescription(miPlayView.getContext().getString(R.string.miplay_accessibility_pause));
            } else if (miPlayDetailViewModel.isLocalPausing()) {
                miPlayView.getPlay().setImageResource(R.drawable.miplay_view_play);
                miPlayView.getPlay().setContentDescription(miPlayView.getContext().getString(R.string.miplay_accessibility_play));
            }
        }
    }

    private final void updateConstraint() {
        int visibility = getArtist().getVisibility();
        ConstraintSet constraintSet = new ConstraintSet();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.miplay_view_action_size);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.miplay_view_action_select_device_size);
        int i2 = R.id.audio_title;
        constraintSet.constrainWidth(i2, 0);
        constraintSet.constrainHeight(i2, -2);
        int i3 = R.id.audio_artist;
        constraintSet.constrainWidth(i3, 0);
        constraintSet.constrainHeight(i3, -2);
        int i4 = R.id.prev;
        constraintSet.constrainWidth(i4, dimensionPixelSize);
        constraintSet.constrainHeight(i4, dimensionPixelSize);
        int i5 = R.id.play;
        constraintSet.constrainWidth(i5, dimensionPixelSize);
        constraintSet.constrainHeight(i5, dimensionPixelSize);
        int i6 = R.id.next;
        constraintSet.constrainWidth(i6, dimensionPixelSize);
        constraintSet.constrainHeight(i6, dimensionPixelSize);
        int i7 = R.id.select_device;
        constraintSet.constrainWidth(i7, dimensionPixelSize2);
        constraintSet.constrainHeight(i7, dimensionPixelSize2);
        if (getCollapse()) {
            constraintSet.createHorizontalChainRtl(0, 6, 0, 7, new int[]{i4, i5, i6}, null, 1);
            constraintSet.connect(i4, 4, 0, 4);
            constraintSet.connect(i5, 4, 0, 4);
            constraintSet.connect(i6, 4, 0, 4);
            constraintSet.connect(i7, 3, 0, 3);
            constraintSet.connect(i7, 6, i6, 6);
            constraintSet.connect(i7, 7, i6, 7);
            constraintSet.connect(i2, 6, 0, 6, getResources().getDimensionPixelSize(R.dimen.miplay_view_collapse_title_margin_start));
            constraintSet.connect(i2, 7, i7, 6);
            constraintSet.connect(i2, 3, i7, 3);
            constraintSet.connect(i2, 4, i7, 4);
            constraintSet.connect(i3, 6, i2, 6);
            constraintSet.connect(i3, 7, i7, 6);
            constraintSet.connect(i3, 3, i2, 4, getResources().getDimensionPixelSize(R.dimen.miplay_view_artist_margin_top));
        } else {
            constraintSet.connect(i2, 6, 0, 6, getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_title_margin_start));
            constraintSet.connect(i2, 7, i4, 6);
            constraintSet.connect(i3, 6, i2, 6);
            constraintSet.connect(i3, 7, i4, 6);
            constraintSet.createVerticalChain(0, 3, 0, 4, new int[]{i2, i3}, null, 2);
            constraintSet.connect(i3, 3, i2, 4, getResources().getDimensionPixelSize(R.dimen.miplay_view_artist_margin_top));
            Resources resources = getResources();
            int i8 = R.dimen.miplay_view_action_margin_start_end;
            constraintSet.connect(i4, 7, i5, 6, resources.getDimensionPixelSize(i8));
            constraintSet.connect(i4, 3, 0, 3);
            constraintSet.connect(i4, 4, 0, 4);
            constraintSet.connect(i5, 7, i6, 6, getResources().getDimensionPixelSize(i8));
            constraintSet.connect(i5, 3, 0, 3);
            constraintSet.connect(i5, 4, 0, 4);
            constraintSet.connect(i6, 7, i7, 6, getResources().getDimensionPixelSize(R.dimen.miplay_view_action_select_device_margin_start));
            constraintSet.connect(i6, 3, 0, 3);
            constraintSet.connect(i6, 4, 0, 4);
            constraintSet.connect(i7, 7, 0, 7);
            constraintSet.connect(i7, 3, 0, 3);
            constraintSet.connect(i7, 4, 0, 4);
        }
        constraintSet.setHorizontalBias(i2, 0.0f);
        constraintSet.setHorizontalBias(i3, 0.0f);
        constraintSet.applyTo(this);
        getArtist().setVisibility(visibility);
        if (getCollapse()) {
            setPadding(getResources().getDimensionPixelSize(R.dimen.miplay_view_collapse_padding_start), getResources().getDimensionPixelSize(R.dimen.miplay_view_collapse_padding_top), getResources().getDimensionPixelSize(R.dimen.miplay_view_collapse_padding_end), getResources().getDimensionPixelSize(R.dimen.miplay_view_collapse_padding_bottom));
        } else {
            setPadding(getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_padding_start), getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_padding_top), getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_padding_end), getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_padding_bottom));
        }
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public void destroy() {
        setListening(false);
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMIsCasting().removeObservers(this);
        miPlayDetailViewModel.getMMediaMetaData().removeObservers(this);
        miPlayDetailViewModel.getMPlaybackState().removeObservers(this);
        miPlayDetailViewModel.getMCastingDeviceIcon().removeObservers(this);
    }

    public final TextView getArtist() {
        TextView textView = this.artist;
        if (textView != null) {
            return textView;
        }
        kotlin.jvm.internal.n.w("artist");
        return null;
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public boolean getCollapse() {
        return this.collapse;
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public int getExpandHeight() {
        return getResources().getDimensionPixelSize(R.dimen.miplay_view_expand_height);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final MiPlayEntranceViewCallback getMiPlayEntranceViewCallback() {
        return this.miPlayEntranceViewCallback;
    }

    public final ImageView getNext() {
        ImageView imageView = this.next;
        if (imageView != null) {
            return imageView;
        }
        kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_NEXT);
        return null;
    }

    public final ImageView getPlay() {
        ImageView imageView = this.play;
        if (imageView != null) {
            return imageView;
        }
        kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PLAY);
        return null;
    }

    public final ImageView getPrev() {
        ImageView imageView = this.prev;
        if (imageView != null) {
            return imageView;
        }
        kotlin.jvm.internal.n.w(MiPlayEventsKt.POSITION_PREV);
        return null;
    }

    public final long getPrevNextTouchTime() {
        return this.prevNextTouchTime;
    }

    public final ImageView getSelectDevice() {
        ImageView imageView = this.selectDevice;
        if (imageView != null) {
            return imageView;
        }
        kotlin.jvm.internal.n.w("selectDevice");
        return null;
    }

    public final TextView getTitle() {
        TextView textView = this.title;
        if (textView != null) {
            return textView;
        }
        kotlin.jvm.internal.n.w("title");
        return null;
    }

    @Override // miui.systemui.widget.ConstraintLayout, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        if (MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue() == null) {
            getTitle().setText(getResources().getString(R.string.miplay_detail_header_no_song));
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        View viewRequireViewById = requireViewById(R.id.audio_title);
        kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
        setTitle((TextView) viewRequireViewById);
        View viewRequireViewById2 = requireViewById(R.id.audio_artist);
        kotlin.jvm.internal.n.f(viewRequireViewById2, "requireViewById(...)");
        setArtist((TextView) viewRequireViewById2);
        View viewRequireViewById3 = requireViewById(R.id.prev);
        kotlin.jvm.internal.n.f(viewRequireViewById3, "requireViewById(...)");
        setPrev((ImageView) viewRequireViewById3);
        View viewRequireViewById4 = requireViewById(R.id.play);
        kotlin.jvm.internal.n.f(viewRequireViewById4, "requireViewById(...)");
        setPlay((ImageView) viewRequireViewById4);
        View viewRequireViewById5 = requireViewById(R.id.next);
        kotlin.jvm.internal.n.f(viewRequireViewById5, "requireViewById(...)");
        setNext((ImageView) viewRequireViewById5);
        View viewRequireViewById6 = requireViewById(R.id.select_device);
        kotlin.jvm.internal.n.f(viewRequireViewById6, "requireViewById(...)");
        setSelectDevice((ImageView) viewRequireViewById6);
        getPlay().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiPlayView.onFinishInflate$lambda$2(this.f1524a, view);
            }
        });
        getPrev().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiPlayView.onFinishInflate$lambda$3(this.f1525a, view);
            }
        });
        getNext().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiPlayView.onFinishInflate$lambda$4(this.f1526a, view);
            }
        });
        QuickClickManager quickClickManager = QuickClickManager.INSTANCE;
        quickClickManager.setViewClickListener(getSelectDevice(), new Runnable() { // from class: com.android.systemui.w
            @Override // java.lang.Runnable
            public final void run() {
                MiPlayView.onFinishInflate$lambda$5(this.f1527a);
            }
        });
        quickClickManager.setViewClickListener(this, new Runnable() { // from class: com.android.systemui.x
            @Override // java.lang.Runnable
            public final void run() {
                MiPlayView.onFinishInflate$lambda$6(this.f1528a);
            }
        });
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMCastingDeviceIcon().observe(this, new Observer() { // from class: com.android.systemui.y
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MiPlayView.onFinishInflate$lambda$7(this.f1529a, (Integer) obj);
            }
        });
        miPlayDetailViewModel.getMMediaMetaData().observe(this, new Observer() { // from class: com.android.systemui.z
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MiPlayView.onFinishInflate$lambda$9(this.f1530a, (MediaMetaData) obj);
            }
        });
        final kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
        miPlayDetailViewModel.getMPlaybackState().observe(this, new Observer() { // from class: com.android.systemui.A
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MiPlayView.onFinishInflate$lambda$10(this.f1417a, yVar, (Integer) obj);
            }
        });
    }

    public final void setArtist(TextView textView) {
        kotlin.jvm.internal.n.g(textView, "<set-?>");
        this.artist = textView;
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public void setCollapse(boolean z2) {
        if (this.collapse == z2) {
            return;
        }
        this.collapse = z2;
        updateConstraint();
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        if (!z2) {
            this.mLifecycle.setCurrentState(Lifecycle.State.DESTROYED);
            return;
        }
        this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
        MiPlayController.INSTANCE.ensureService();
        MiPlayEventTracker.trackExpose(MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    public final void setMiPlayEntranceViewCallback(MiPlayEntranceViewCallback miPlayEntranceViewCallback) {
        this.miPlayEntranceViewCallback = miPlayEntranceViewCallback;
    }

    public final void setNext(ImageView imageView) {
        kotlin.jvm.internal.n.g(imageView, "<set-?>");
        this.next = imageView;
    }

    public final void setPlay(ImageView imageView) {
        kotlin.jvm.internal.n.g(imageView, "<set-?>");
        this.play = imageView;
    }

    public final void setPrev(ImageView imageView) {
        kotlin.jvm.internal.n.g(imageView, "<set-?>");
        this.prev = imageView;
    }

    public final void setPrevNextTouchTime(long j2) {
        this.prevNextTouchTime = j2;
    }

    public final void setSelectDevice(ImageView imageView) {
        kotlin.jvm.internal.n.g(imageView, "<set-?>");
        this.selectDevice = imageView;
    }

    public final void setTitle(TextView textView) {
        kotlin.jvm.internal.n.g(textView, "<set-?>");
        this.title = textView;
    }

    @Override // miui.systemui.controlcenter.external.ExternalEntry
    public void updateResources() {
        updateConstraint();
        getTitle().setTextSize(0, getResources().getDimension(R.dimen.miplay_view_title_text_fixed_size));
        getArtist().setTextSize(0, getResources().getDimension(R.dimen.miplay_view_artist_text_fixed_size));
        if (TextUtils.isEmpty(getArtist().getText())) {
            getArtist().setVisibility(8);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MiPlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ MiPlayView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.mLifecycle = new LifecycleRegistry(this);
    }
}
