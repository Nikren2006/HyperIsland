package miui.systemui.devicecontrols.management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class StructureAdapter extends RecyclerView.Adapter<StructureHolder> {
    private final List<StructureContainer> models;

    public static final class StructureHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StructureHolder(View view) {
            super(view);
            kotlin.jvm.internal.n.g(view, "view");
            View viewRequireViewById = this.itemView.requireViewById(R.id.listAll);
            kotlin.jvm.internal.n.f(viewRequireViewById, "requireViewById(...)");
            this.recyclerView = (RecyclerView) viewRequireViewById;
            setUpRecyclerView();
        }

        private final void setUpRecyclerView() {
        }

        public final void bind(ControlsModel model) {
            kotlin.jvm.internal.n.g(model, "model");
        }
    }

    public StructureAdapter(List<StructureContainer> models) {
        kotlin.jvm.internal.n.g(models, "models");
        this.models = models;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.models.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(StructureHolder holder, int i2) {
        kotlin.jvm.internal.n.g(holder, "holder");
        holder.bind(this.models.get(i2).getModel());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public StructureHolder onCreateViewHolder(ViewGroup parent, int i2) {
        kotlin.jvm.internal.n.g(parent, "parent");
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.controls_structure_page, parent, false);
        kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
        return new StructureHolder(viewInflate);
    }
}
