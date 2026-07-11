package miui.systemui.notification;

import I0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class FullAodStatusManager {
    private static boolean fullAodStatus;
    public static final FullAodStatusManager INSTANCE = new FullAodStatusManager();
    private static final List<IFullAodStatusObserver> fullAodStatusObserverList = new ArrayList();

    public interface IFullAodStatusObserver {
        void fullAodStatusChanged(boolean z2);
    }

    private FullAodStatusManager() {
    }

    public final void addObserver(IFullAodStatusObserver observer) {
        n.g(observer, "observer");
        List<IFullAodStatusObserver> list = fullAodStatusObserverList;
        if (list.contains(observer)) {
            return;
        }
        list.add(observer);
        observer.fullAodStatusChanged(fullAodStatus);
    }

    public final void removeObserver(IFullAodStatusObserver observer) {
        n.g(observer, "observer");
        fullAodStatusObserverList.remove(observer);
    }

    public final void updateFullAod(boolean z2) {
        fullAodStatus = z2;
        Iterator it = u.k0(fullAodStatusObserverList).iterator();
        while (it.hasNext()) {
            ((IFullAodStatusObserver) it.next()).fullAodStatusChanged(z2);
        }
    }
}
