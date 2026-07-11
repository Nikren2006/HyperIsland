package com.android.systemui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import miui.systemui.miplay.tracker.MiPlayEventTracker;
import miui.systemui.util.concurrency.ConcurrencyModule;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterMiPlayView extends LinearLayout implements LifecycleOwner {
    private final int PREV_NEXT_TOUCH_INTERVAL;
    private final String TAG;
    public TextView artist;
    private final LifecycleRegistry mLifecycle;
    private MiPlayEntranceViewCallback miPlayEntranceViewCallback;
    public ImageView next;
    public ImageView play;
    public ImageView prev;
    private long prevNextTouchTime;
    public ImageView selectDevice;
    public TextView title;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ControlCenterMiPlayView(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$10(ControlCenterMiPlayView this$0, kotlin.jvm.internal.y updateJob, Integer num) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(updateJob, "$updateJob");
        if (System.currentTimeMillis() - this$0.prevNextTouchTime >= this$0.PREV_NEXT_TOUCH_INTERVAL) {
            onFinishInflate$updatePlayButton(this$0);
        } else if (updateJob.f5059a == null) {
            updateJob.f5059a = AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new ControlCenterMiPlayView$onFinishInflate$9$1(this$0, updateJob, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$2(ControlCenterMiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(this$0.TAG, "play click");
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
    public static final void onFinishInflate$lambda$3(ControlCenterMiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(this$0.TAG, "prev click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        C0466a value = MiPlayDetailViewModel.INSTANCE.getMActiveAudioSession().getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.t();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_PREV, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$4(ControlCenterMiPlayView this$0, View view) {
        m0.w wVarB;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(this$0.TAG, "next click");
        this$0.prevNextTouchTime = System.currentTimeMillis();
        C0466a value = MiPlayDetailViewModel.INSTANCE.getMActiveAudioSession().getValue();
        if (value != null && (wVarB = value.b()) != null) {
            wVarB.o();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_NEXT, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$5(ControlCenterMiPlayView this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(this$0.TAG, "selectDevice click");
        MiPlayEntranceViewCallback miPlayEntranceViewCallback = this$0.miPlayEntranceViewCallback;
        if (miPlayEntranceViewCallback != null) {
            miPlayEntranceViewCallback.showEntranceView();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CAST, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$6(ControlCenterMiPlayView this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Log.d(this$0.TAG, "view click");
        MiPlayEntranceViewCallback miPlayEntranceViewCallback = this$0.miPlayEntranceViewCallback;
        if (miPlayEntranceViewCallback != null) {
            miPlayEntranceViewCallback.showEntranceView();
        }
        MiPlayEventTracker.trackClick(MiPlayEventsKt.POSITION_CARD, MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$7(ControlCenterMiPlayView this$0, Integer num) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ImageView selectDevice = this$0.getSelectDevice();
        Integer value = MiPlayDetailViewModel.INSTANCE.getMCastingDeviceIcon().getValue();
        kotlin.jvm.internal.n.d(value);
        selectDevice.setImageResource(value.intValue());
    }

    private static final void onFinishInflate$lambda$8(ControlCenterMiPlayView this$0, Boolean bool) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.d(bool);
        if (bool.booleanValue()) {
            this$0.getSelectDevice().setImageResource(R.drawable.miplay_select_device_working);
        } else {
            this$0.getSelectDevice().setImageResource(R.drawable.miplay_select_device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFinishInflate$lambda$9(ControlCenterMiPlayView this$0, MediaMetaData mediaMetaData) {
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
    public static final void onFinishInflate$updatePlayButton(ControlCenterMiPlayView controlCenterMiPlayView) {
        if (controlCenterMiPlayView.getPlay().isEnabled()) {
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            if (miPlayDetailViewModel.isLocalPlaying()) {
                controlCenterMiPlayView.getPlay().setImageResource(R.drawable.miplay_view_pause);
                controlCenterMiPlayView.getPlay().setContentDescription(controlCenterMiPlayView.getContext().getString(R.string.miplay_accessibility_pause));
            } else if (miPlayDetailViewModel.isLocalPausing()) {
                controlCenterMiPlayView.getPlay().setImageResource(R.drawable.miplay_view_play);
                controlCenterMiPlayView.getPlay().setContentDescription(controlCenterMiPlayView.getContext().getString(R.string.miplay_accessibility_play));
            }
        }
    }

    public final TextView getArtist() {
        TextView textView = this.artist;
        if (textView != null) {
            return textView;
        }
        kotlin.jvm.internal.n.w("artist");
        return null;
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

    public final int getPREV_NEXT_TOUCH_INTERVAL() {
        return this.PREV_NEXT_TOUCH_INTERVAL;
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

    public final String getTAG() {
        return this.TAG;
    }

    public final TextView getTitle() {
        TextView textView = this.title;
        if (textView != null) {
            return textView;
        }
        kotlin.jvm.internal.n.w("title");
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.STARTED);
        MiPlayEventTracker.trackExpose(MiPlayEventsKt.PAGE_CONTROLCENTER_CARD, "controlcenter");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLifecycle.setCurrentState(Lifecycle.State.DESTROYED);
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
        getPlay().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterMiPlayView.onFinishInflate$lambda$2(this.f1448a, view);
            }
        });
        getPrev().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterMiPlayView.onFinishInflate$lambda$3(this.f1450a, view);
            }
        });
        getNext().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterMiPlayView.onFinishInflate$lambda$4(this.f1451a, view);
            }
        });
        getSelectDevice().setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterMiPlayView.onFinishInflate$lambda$5(this.f1452a, view);
            }
        });
        setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlCenterMiPlayView.onFinishInflate$lambda$6(this.f1453a, view);
            }
        });
        MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
        miPlayDetailViewModel.getMCastingDeviceIcon().observe(this, new Observer() { // from class: com.android.systemui.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ControlCenterMiPlayView.onFinishInflate$lambda$7(this.f1454a, (Integer) obj);
            }
        });
        miPlayDetailViewModel.getMMediaMetaData().observe(this, new Observer() { // from class: com.android.systemui.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ControlCenterMiPlayView.onFinishInflate$lambda$9(this.f1455a, (MediaMetaData) obj);
            }
        });
        final kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
        miPlayDetailViewModel.getMPlaybackState().observe(this, new Observer() { // from class: com.android.systemui.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ControlCenterMiPlayView.onFinishInflate$lambda$10(this.f1456a, yVar, (Integer) obj);
            }
        });
    }

    public final void setArtist(TextView textView) {
        kotlin.jvm.internal.n.g(textView, "<set-?>");
        this.artist = textView;
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ControlCenterMiPlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ ControlCenterMiPlayView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterMiPlayView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.TAG = "ControlCenterMiPlayView";
        this.PREV_NEXT_TOUCH_INTERVAL = 2000;
        this.mLifecycle = new LifecycleRegistry(this);
    }
}
