package miui.systemui.dynamicisland.event;

import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DynamicIslandEvent {

    public static final class AddDynamicIsland extends DynamicIslandEvent {
        public static final AddDynamicIsland INSTANCE = new AddDynamicIsland();
        private static boolean tempShow;

        private AddDynamicIsland() {
            super(null);
        }

        public final boolean getTempShow() {
            return tempShow;
        }

        public final void setTempShow(boolean z2) {
            tempShow = z2;
        }
    }

    public static final class AutoExpandIsland extends DynamicIslandEvent {
        public static final AutoExpandIsland INSTANCE = new AutoExpandIsland();

        private AutoExpandIsland() {
            super(null);
        }
    }

    public static final class AvoidScreenReset extends DynamicIslandEvent {
        public static final AvoidScreenReset INSTANCE = new AvoidScreenReset();

        private AvoidScreenReset() {
            super(null);
        }
    }

    public static final class ClickDynamicIsland extends DynamicIslandEvent {
        public static final ClickDynamicIsland INSTANCE = new ClickDynamicIsland();

        private ClickDynamicIsland() {
            super(null);
        }
    }

    public static final class Collapse extends DynamicIslandEvent {
        public static final Collapse INSTANCE = new Collapse();

        private Collapse() {
            super(null);
        }
    }

    public static final class ConfigChanged extends DynamicIslandEvent {
        public static final ConfigChanged INSTANCE = new ConfigChanged();

        private ConfigChanged() {
            super(null);
        }
    }

    public static final class DeletedDynamicIsland extends DynamicIslandEvent {
        public static final DeletedDynamicIsland INSTANCE = new DeletedDynamicIsland();

        private DeletedDynamicIsland() {
            super(null);
        }
    }

    public static final class EnterApp extends DynamicIslandEvent {
        public static final EnterApp INSTANCE = new EnterApp();

        private EnterApp() {
            super(null);
        }
    }

    public static final class EnterMiniWindow extends DynamicIslandEvent {
        public static final EnterMiniWindow INSTANCE = new EnterMiniWindow();

        private EnterMiniWindow() {
            super(null);
        }
    }

    public static final class ExitApp extends DynamicIslandEvent {
        public static final ExitApp INSTANCE = new ExitApp();

        private ExitApp() {
            super(null);
        }
    }

    public static final class ExitMiniWindow extends DynamicIslandEvent {
        public static final ExitMiniWindow INSTANCE = new ExitMiniWindow();

        private ExitMiniWindow() {
            super(null);
        }
    }

    public static final class IslandLongPressed extends DynamicIslandEvent {
        public static final IslandLongPressed INSTANCE = new IslandLongPressed();

        private IslandLongPressed() {
            super(null);
        }
    }

    public static final class IslandTempHiddenChanged extends DynamicIslandEvent {
        public static final IslandTempHiddenChanged INSTANCE = new IslandTempHiddenChanged();
        private static boolean hide;
        private static DynamicIslandWindowState.TempHiddenType type;

        private IslandTempHiddenChanged() {
            super(null);
        }

        public final boolean getHide() {
            return hide;
        }

        public final DynamicIslandWindowState.TempHiddenType getType() {
            return type;
        }

        public final void setHide(boolean z2) {
            hide = z2;
        }

        public final void setType(DynamicIslandWindowState.TempHiddenType tempHiddenType) {
            type = tempHiddenType;
        }
    }

    public static final class SwipeLeft extends DynamicIslandEvent {
        public static final SwipeLeft INSTANCE = new SwipeLeft();

        private SwipeLeft() {
            super(null);
        }
    }

    public static final class SwipeRight extends DynamicIslandEvent {
        public static final SwipeRight INSTANCE = new SwipeRight();

        private SwipeRight() {
            super(null);
        }
    }

    public static final class UpdateDynamicIsland extends DynamicIslandEvent {
        public static final UpdateDynamicIsland INSTANCE = new UpdateDynamicIsland();

        private UpdateDynamicIsland() {
            super(null);
        }
    }

    public /* synthetic */ DynamicIslandEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private DynamicIslandEvent() {
    }
}
