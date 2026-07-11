package com.android.systemui;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.android.systemui.miplay.R;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContent$DeviceItemHolder$playingInfoDrawable$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ View $root;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$DeviceItemHolder$playingInfoDrawable$2(View view) {
        super(0);
        this.$root = view;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Drawable invoke() {
        return AppCompatResources.getDrawable(this.$root.getContext(), R.drawable.ic_miplay_playing_info);
    }
}
