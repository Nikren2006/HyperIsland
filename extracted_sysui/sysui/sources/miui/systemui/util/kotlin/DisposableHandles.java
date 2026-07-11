package miui.systemui.util.kotlin;

import I0.r;
import g1.S;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class DisposableHandles implements S {
    private final List<S> handles = new ArrayList();

    public final void add(S... handles) {
        n.g(handles, "handles");
        r.u(this.handles, handles);
    }

    @Override // g1.S
    public void dispose() {
        Iterator<T> it = this.handles.iterator();
        while (it.hasNext()) {
            ((S) it.next()).dispose();
        }
        this.handles.clear();
    }

    public final void plusAssign(S handle) {
        n.g(handle, "handle");
        this.handles.add(handle);
    }

    public final void replaceAll(S... handles) {
        n.g(handles, "handles");
        dispose();
        add((S[]) Arrays.copyOf(handles, handles.length));
    }

    public final void plusAssign(Iterable<? extends S> handles) {
        n.g(handles, "handles");
        r.t(this.handles, handles);
    }
}
