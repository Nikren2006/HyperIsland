package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQSCardViewHolder_Factory_Factory implements F0.e {
    private final G0.a qsItemViewHolderFactoryProvider;
    private final G0.a sysUIContextProvider;

    public CompactQSCardViewHolder_Factory_Factory(G0.a aVar, G0.a aVar2) {
        this.qsItemViewHolderFactoryProvider = aVar;
        this.sysUIContextProvider = aVar2;
    }

    public static CompactQSCardViewHolder_Factory_Factory create(G0.a aVar, G0.a aVar2) {
        return new CompactQSCardViewHolder_Factory_Factory(aVar, aVar2);
    }

    public static CompactQSCardViewHolder.Factory newInstance(QSItemViewHolder.Factory factory, Context context) {
        return new CompactQSCardViewHolder.Factory(factory, context);
    }

    @Override // G0.a
    public CompactQSCardViewHolder.Factory get() {
        return newInstance((QSItemViewHolder.Factory) this.qsItemViewHolderFactoryProvider.get(), (Context) this.sysUIContextProvider.get());
    }
}
