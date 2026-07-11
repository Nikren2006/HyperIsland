package miui.systemui.devicecontrols.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

/* JADX INFO: loaded from: classes3.dex */
final class ItemAdapter extends ArrayAdapter<SelectionItem> {
    private final LayoutInflater layoutInflater;
    private final Context parentContext;
    private final int resource;
    private final SelectionItem selected;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ItemAdapter(Context parentContext, int i2, SelectionItem selectionItem) {
        super(parentContext, i2);
        kotlin.jvm.internal.n.g(parentContext, "parentContext");
        this.parentContext = parentContext;
        this.resource = i2;
        this.selected = selectionItem;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        kotlin.jvm.internal.n.f(layoutInflaterFrom, "from(...)");
        this.layoutInflater = layoutInflaterFrom;
    }

    public final LayoutInflater getLayoutInflater() {
        return this.layoutInflater;
    }

    public final Context getParentContext() {
        return this.parentContext;
    }

    public final int getResource() {
        return this.resource;
    }

    public final SelectionItem getSelected() {
        return this.selected;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0069  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecontrols.ui.ItemAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
