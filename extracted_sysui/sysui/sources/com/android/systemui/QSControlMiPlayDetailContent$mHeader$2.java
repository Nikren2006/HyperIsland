package com.android.systemui;

import android.view.View;
import com.android.systemui.QSControlMiPlayDetailContent;
import com.android.systemui.QSControlMiPlayDetailHeader;
import com.android.systemui.miplay.R;
import com.miui.miplay.audio.data.MediaMetaData;
import g1.AbstractC0369g;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContent$mHeader$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ QSControlMiPlayDetailContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$mHeader$2(QSControlMiPlayDetailContent qSControlMiPlayDetailContent) {
        super(0);
        this.this$0 = qSControlMiPlayDetailContent;
    }

    @Override // kotlin.jvm.functions.Function0
    public final QSControlMiPlayDetailHeader invoke() {
        View viewRequireViewById = this.this$0.requireViewById(R.id.qs_control_detail_miplay_header);
        final QSControlMiPlayDetailContent qSControlMiPlayDetailContent = this.this$0;
        QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader = (QSControlMiPlayDetailHeader) viewRequireViewById;
        qSControlMiPlayDetailHeader.setHeaderSizeCallback(new QSControlMiPlayDetailHeader.HeaderSizeCallback() { // from class: com.android.systemui.QSControlMiPlayDetailContent$mHeader$2$1$1
            @Override // com.android.systemui.QSControlMiPlayDetailHeader.HeaderSizeCallback
            public void onSizeChanged() {
                MediaMetaData value = MiPlayDetailViewModel.INSTANCE.getMMediaMetaData().getValue();
                if (value == null || value.getMediaType() != 1 || qSControlMiPlayDetailContent.getMListItems() == null) {
                    return;
                }
                ArrayList<QSControlMiPlayDetailContent.ListItem> mListItems = qSControlMiPlayDetailContent.getMListItems();
                kotlin.jvm.internal.n.d(mListItems);
                if (mListItems.size() > 0) {
                    AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1(qSControlMiPlayDetailContent, null), 3, null);
                }
            }
        });
        return qSControlMiPlayDetailHeader;
    }
}
