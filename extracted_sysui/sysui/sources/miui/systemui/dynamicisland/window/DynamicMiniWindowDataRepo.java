package miui.systemui.dynamicisland.window;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import miui.app.MiuiFreeFormManager;
import miui.systemui.dynamicisland.DynamicIslandUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicMiniWindowDataRepo {
    private volatile boolean cancelled;
    private final ConcurrentHashMap.KeySetView<MiniWindowData, Boolean> dataInfoSet;
    private final Executor executor;
    private boolean hasInit;
    private boolean support;

    public static final class MiniWindowData {
        private final String packageName;
        private final int userId;

        public MiniWindowData(String packageName, int i2) {
            kotlin.jvm.internal.n.g(packageName, "packageName");
            this.packageName = packageName;
            this.userId = i2;
        }

        public static /* synthetic */ MiniWindowData copy$default(MiniWindowData miniWindowData, String str, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = miniWindowData.packageName;
            }
            if ((i3 & 2) != 0) {
                i2 = miniWindowData.userId;
            }
            return miniWindowData.copy(str, i2);
        }

        public final String component1() {
            return this.packageName;
        }

        public final int component2() {
            return this.userId;
        }

        public final MiniWindowData copy(String packageName, int i2) {
            kotlin.jvm.internal.n.g(packageName, "packageName");
            return new MiniWindowData(packageName, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MiniWindowData)) {
                return false;
            }
            MiniWindowData miniWindowData = (MiniWindowData) obj;
            return kotlin.jvm.internal.n.c(this.packageName, miniWindowData.packageName) && this.userId == miniWindowData.userId;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final int getUserId() {
            return this.userId;
        }

        public int hashCode() {
            return (this.packageName.hashCode() * 31) + Integer.hashCode(this.userId);
        }

        public String toString() {
            return "MiniWindowData(packageName=" + this.packageName + ", userId=" + this.userId + ")";
        }
    }

    public DynamicMiniWindowDataRepo(Executor executor) {
        kotlin.jvm.internal.n.g(executor, "executor");
        this.executor = executor;
        this.support = true;
        this.dataInfoSet = ConcurrentHashMap.newKeySet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$1(DynamicMiniWindowDataRepo this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        List<MiuiFreeFormManager.MiuiFreeFormStackInfo> freeFormList = DynamicIslandUtils.INSTANCE.getFreeFormList();
        if (this$0.cancelled || freeFormList == null) {
            return;
        }
        Iterator<T> it = freeFormList.iterator();
        while (it.hasNext()) {
            this$0.updateDataInfo((MiuiFreeFormManager.MiuiFreeFormStackInfo) it.next());
        }
    }

    public final boolean getSupport() {
        return this.support;
    }

    public final boolean isInMiniWindow(String packageName, int i2) {
        kotlin.jvm.internal.n.g(packageName, "packageName");
        if (this.support) {
            return this.dataInfoSet.contains(new MiniWindowData(packageName, i2));
        }
        return false;
    }

    public final void setSupport(boolean z2) {
        this.support = z2;
    }

    public final void start() {
        if (this.hasInit) {
            return;
        }
        this.cancelled = false;
        this.hasInit = true;
        this.executor.execute(new Runnable() { // from class: miui.systemui.dynamicisland.window.q
            @Override // java.lang.Runnable
            public final void run() {
                DynamicMiniWindowDataRepo.start$lambda$1(this.f5759a);
            }
        });
    }

    public final void stop() {
        this.dataInfoSet.clear();
        this.cancelled = true;
        this.hasInit = false;
    }

    public final void updateDataInfo(MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo) {
        if (miuiFreeFormStackInfo == null || miuiFreeFormStackInfo.packageName == null || this.cancelled) {
            return;
        }
        String packageName = miuiFreeFormStackInfo.packageName;
        kotlin.jvm.internal.n.f(packageName, "packageName");
        MiniWindowData miniWindowData = new MiniWindowData(packageName, miuiFreeFormStackInfo.userId);
        boolean z2 = miuiFreeFormStackInfo.isInFreeFormMode() || miuiFreeFormStackInfo.isInMiniFreeFormMode();
        int i2 = miuiFreeFormStackInfo.displayId;
        if (i2 == -1) {
            if (z2) {
                return;
            }
            this.dataInfoSet.remove(miniWindowData);
        } else {
            if (i2 != 0) {
                return;
            }
            if (z2) {
                this.dataInfoSet.add(miniWindowData);
            } else {
                this.dataInfoSet.remove(miniWindowData);
            }
        }
    }
}
