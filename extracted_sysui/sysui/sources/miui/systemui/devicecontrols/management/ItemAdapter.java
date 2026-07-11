package miui.systemui.devicecontrols.management;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

/* JADX INFO: loaded from: classes3.dex */
final class ItemAdapter extends ArrayAdapter<EditStructureInfo> {
    private final LayoutInflater layoutInflater;
    private final int resId;
    private final EditStructureInfo selected;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ItemAdapter(Context context, int i2, EditStructureInfo editStructureInfo) {
        super(context, i2);
        kotlin.jvm.internal.n.g(context, "context");
        this.resId = i2;
        this.selected = editStructureInfo;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public final int getResId() {
        return this.resId;
    }

    public final EditStructureInfo getSelected() {
        return this.selected;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007a  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(int r9, android.view.View r10, android.view.ViewGroup r11) {
        /*
            r8 = this;
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.n.g(r11, r0)
            java.lang.Object r9 = r8.getItem(r9)
            miui.systemui.devicecontrols.management.EditStructureInfo r9 = (miui.systemui.devicecontrols.management.EditStructureInfo) r9
            r0 = 0
            if (r10 != 0) goto L16
            android.view.LayoutInflater r10 = r8.layoutInflater
            int r1 = r8.resId
            android.view.View r10 = r10.inflate(r1, r11, r0)
        L16:
            int r11 = miui.systemui.devicecontrols.R.id.tv_app_label
            android.view.View r11 = r10.findViewById(r11)
            android.widget.TextView r11 = (android.widget.TextView) r11
            int r1 = miui.systemui.devicecontrols.R.id.tv_structure
            android.view.View r1 = r10.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            int r2 = miui.systemui.devicecontrols.R.id.iv_select_icon
            android.view.View r2 = r10.findViewById(r2)
            android.widget.ImageView r2 = (android.widget.ImageView) r2
            int r3 = miui.systemui.devicecontrols.R.id.iv_app_icon
            android.view.View r3 = r10.findViewById(r3)
            android.widget.ImageView r3 = (android.widget.ImageView) r3
            r4 = 0
            if (r9 == 0) goto L3e
            java.lang.CharSequence r5 = r9.getStructure()
            goto L3f
        L3e:
            r5 = r4
        L3f:
            miui.systemui.devicecontrols.management.EditStructureInfo r6 = r8.selected
            if (r6 == 0) goto L48
            java.lang.CharSequence r6 = r6.getStructure()
            goto L49
        L48:
            r6 = r4
        L49:
            boolean r5 = android.text.TextUtils.equals(r5, r6)
            r6 = 8
            if (r5 == 0) goto L7a
            if (r9 == 0) goto L58
            android.content.ComponentName r5 = r9.getComponentName()
            goto L59
        L58:
            r5 = r4
        L59:
            miui.systemui.devicecontrols.management.EditStructureInfo r7 = r8.selected
            if (r7 == 0) goto L62
            android.content.ComponentName r7 = r7.getComponentName()
            goto L63
        L62:
            r7 = r4
        L63:
            boolean r5 = kotlin.jvm.internal.n.c(r5, r7)
            if (r5 == 0) goto L7a
            android.content.Context r5 = r8.getContext()
            int r7 = miui.systemui.devicecontrols.R.color.controls_selection_selected_color
            int r5 = r5.getColor(r7)
            r10.setBackgroundColor(r5)
            r2.setVisibility(r0)
            goto L8a
        L7a:
            android.content.Context r0 = r8.getContext()
            int r5 = miui.systemui.devicecontrols.R.color.miuix_appcompat_transparent
            int r0 = r0.getColor(r5)
            r10.setBackgroundColor(r0)
            r2.setVisibility(r6)
        L8a:
            if (r9 == 0) goto La8
            java.lang.CharSequence r9 = r9.getStructure()
            if (r9 == 0) goto La8
            int r0 = r9.length()
            if (r0 != 0) goto La7
            android.content.Context r8 = r8.getContext()
            android.content.res.Resources r8 = r8.getResources()
            int r9 = miui.systemui.devicecontrols.R.string.controls_default_structure
            java.lang.String r4 = r8.getString(r9)
            goto La8
        La7:
            r4 = r9
        La8:
            r11.setText(r4)
            r3.setVisibility(r6)
            r1.setVisibility(r6)
            kotlin.jvm.internal.n.d(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecontrols.management.ItemAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
