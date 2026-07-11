package miui.systemui.dynamicisland.event;

import H0.g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DynamicIslandState extends State {

    public static final class AppExpanded extends DynamicIslandState {
        public AppExpanded() {
            super(null);
        }
    }

    public static final class BigIsland extends DynamicIslandState {
        public BigIsland() {
            super(null);
        }
    }

    public static final class Deleted extends DynamicIslandState {
        private boolean deleteByAddNew;

        public Deleted() {
            super(null);
        }

        public final boolean getDeleteByAddNew() {
            return this.deleteByAddNew;
        }

        public final void setDeleteByAddNew(boolean z2) {
            this.deleteByAddNew = z2;
        }
    }

    public static final class Empty extends DynamicIslandState {
        public Empty() {
            super(null);
        }
    }

    public static final class Expanded extends DynamicIslandState {
        public Expanded() {
            super(null);
        }
    }

    public static final class Hidden extends DynamicIslandState {
        public Hidden() {
            super(null);
        }
    }

    public static final class Init extends DynamicIslandState {
        public Init() {
            super(null);
        }
    }

    public static final class MiniWindowExpanded extends DynamicIslandState {
        public MiniWindowExpanded() {
            super(null);
        }
    }

    public static final class SmallIsland extends DynamicIslandState {
        public SmallIsland() {
            super(null);
        }
    }

    public static final class SubAppExpanded extends DynamicIslandState {
        public SubAppExpanded() {
            super(null);
        }
    }

    public static final class SubMiniWindowExpanded extends DynamicIslandState {
        public SubMiniWindowExpanded() {
            super(null);
        }
    }

    public /* synthetic */ DynamicIslandState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final DynamicIslandState changeToState(DynamicIslandState newState) {
        n.g(newState, "newState");
        newState.setTime(getTime());
        return newState;
    }

    public final DynamicIslandState copy() {
        if (this instanceof Init) {
            return new Init();
        }
        if (this instanceof Hidden) {
            return new Hidden();
        }
        if (this instanceof BigIsland) {
            return new BigIsland();
        }
        if (this instanceof SmallIsland) {
            return new SmallIsland();
        }
        if (this instanceof Expanded) {
            return new Expanded();
        }
        if (this instanceof MiniWindowExpanded) {
            return new MiniWindowExpanded();
        }
        if (this instanceof AppExpanded) {
            return new AppExpanded();
        }
        if (this instanceof Deleted) {
            return new Deleted();
        }
        if (this instanceof Empty) {
            return new Empty();
        }
        if (this instanceof SubAppExpanded) {
            return new SubAppExpanded();
        }
        if (this instanceof SubMiniWindowExpanded) {
            return new SubMiniWindowExpanded();
        }
        throw new g();
    }

    private DynamicIslandState() {
    }
}
