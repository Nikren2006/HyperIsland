package com.android.systemui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.miplay.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.util.DeviceStateManagerCompat;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContentFlip extends QSControlMiPlayDetailContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "QSControlMiPlayDetailContentFlip";
    private final Object deviceStateManager;
    private final DeviceStateManagerCompat.FoldStateCallback foldStateCallback;
    private final ArrayList<DeviceStateManagerCompat.FoldStateCallback> foldStateCallbacks;
    private final Object foldStateListener;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentFlip(Context context) {
        this(context, null, 0, 6, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    private final void addFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        if (this.foldStateCallbacks.contains(foldStateCallback)) {
            return;
        }
        this.foldStateCallbacks.add(foldStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeLayout() {
        getMHeader().adaptFlipDevice(MiPlayDetailViewModel.INSTANCE.getLastFoldState());
        setMaxHeight(resetMaxHeight());
        ArrayList<QSControlMiPlayDetailContent.ListItem> mListItems = getMListItems();
        if (mListItems != null) {
            updateHeight(mListItems, false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void foldStateListener$lambda$1(QSControlMiPlayDetailContentFlip this$0, Boolean folded) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(folded, "folded");
        if (this$0.foldStateCallbacks.isEmpty()) {
            return;
        }
        Iterator<T> it = this$0.foldStateCallbacks.iterator();
        while (it.hasNext()) {
            ((DeviceStateManagerCompat.FoldStateCallback) it.next()).onFoldStateChanged(folded.booleanValue());
        }
    }

    private final void removeFoldStateCallback(DeviceStateManagerCompat.FoldStateCallback foldStateCallback) {
        this.foldStateCallbacks.remove(foldStateCallback);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        changeLayout();
        DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
        Object obj = this.deviceStateManager;
        Executor mainExecutor = getContext().getMainExecutor();
        kotlin.jvm.internal.n.f(mainExecutor, "getMainExecutor(...)");
        deviceStateManagerCompat.registerCallbackCompat(obj, mainExecutor, this.foldStateListener);
        addFoldStateCallback(this.foldStateCallback);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeFoldStateCallback(this.foldStateCallback);
        DeviceStateManagerCompat.INSTANCE.unregisterCallbackCompat(this.deviceStateManager, this.foldStateListener);
    }

    @Override // com.android.systemui.QSControlMiPlayDetailContent
    public int resetMaxHeight() {
        return MiPlayDetailViewModel.INSTANCE.getLastFoldState() ? getResources().getDimensionPixelSize(R.dimen.miplay_view_max_height_flip) : super.resetMaxHeight();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentFlip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        kotlin.jvm.internal.n.g(context, "context");
    }

    public /* synthetic */ QSControlMiPlayDetailContentFlip(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContentFlip(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.foldStateCallback = new DeviceStateManagerCompat.FoldStateCallback() { // from class: com.android.systemui.QSControlMiPlayDetailContentFlip.1
            @Override // miui.systemui.util.DeviceStateManagerCompat.FoldStateCallback
            public void onFoldStateChanged(boolean z2) {
                Log.d(QSControlMiPlayDetailContentFlip.TAG, "onFoldStateChanged: " + z2);
                QSControlMiPlayDetailContentFlip.this.changeLayout();
            }
        };
        DeviceStateManagerCompat deviceStateManagerCompat = DeviceStateManagerCompat.INSTANCE;
        this.deviceStateManager = deviceStateManagerCompat.getDeviceStateManagerInstance();
        this.foldStateCallbacks = new ArrayList<>();
        this.foldStateListener = deviceStateManagerCompat.getFoldStateListenerInstance(context, new Consumer() { // from class: com.android.systemui.I
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                QSControlMiPlayDetailContentFlip.foldStateListener$lambda$1(this.f1427a, (Boolean) obj);
            }
        });
    }
}
