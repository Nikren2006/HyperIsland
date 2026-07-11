package miui.systemui.controlcenter.panel.main.qs;

import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;

/* JADX INFO: loaded from: classes.dex */
public final class QSItemViewHolder_Factory_Factory implements F0.e {
    private final G0.a mainPanelControllerProvider;

    public QSItemViewHolder_Factory_Factory(G0.a aVar) {
        this.mainPanelControllerProvider = aVar;
    }

    public static QSItemViewHolder_Factory_Factory create(G0.a aVar) {
        return new QSItemViewHolder_Factory_Factory(aVar);
    }

    public static QSItemViewHolder.Factory newInstance(E0.a aVar) {
        return new QSItemViewHolder.Factory(aVar);
    }

    @Override // G0.a
    public QSItemViewHolder.Factory get() {
        return newInstance(F0.d.a(this.mainPanelControllerProvider));
    }
}
