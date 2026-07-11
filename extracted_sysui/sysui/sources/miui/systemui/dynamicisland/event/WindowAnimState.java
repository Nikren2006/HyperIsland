package miui.systemui.dynamicisland.event;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WindowAnimState {

    public static final class Closed extends WindowAnimState {
        private final boolean byAnimEvent;
        private final boolean freeform;
        private final boolean hasAnimate;

        public Closed(boolean z2, boolean z3, boolean z4) {
            super(null);
            this.freeform = z2;
            this.hasAnimate = z3;
            this.byAnimEvent = z4;
        }

        public static /* synthetic */ Closed copy$default(Closed closed, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = closed.freeform;
            }
            if ((i2 & 2) != 0) {
                z3 = closed.hasAnimate;
            }
            if ((i2 & 4) != 0) {
                z4 = closed.byAnimEvent;
            }
            return closed.copy(z2, z3, z4);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final boolean component2() {
            return this.hasAnimate;
        }

        public final boolean component3() {
            return this.byAnimEvent;
        }

        public final Closed copy(boolean z2, boolean z3, boolean z4) {
            return new Closed(z2, z3, z4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Closed)) {
                return false;
            }
            Closed closed = (Closed) obj;
            return this.freeform == closed.freeform && this.hasAnimate == closed.hasAnimate && this.byAnimEvent == closed.byAnimEvent;
        }

        public final boolean getByAnimEvent() {
            return this.byAnimEvent;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public final boolean getHasAnimate() {
            return this.hasAnimate;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.freeform) * 31) + Boolean.hashCode(this.hasAnimate)) * 31) + Boolean.hashCode(this.byAnimEvent);
        }

        public String toString() {
            return "Closed(freeform=" + this.freeform + ", hasAnimate=" + this.hasAnimate + ", byAnimEvent=" + this.byAnimEvent + ")";
        }

        public /* synthetic */ Closed(boolean z2, boolean z3, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(z2, z3, (i2 & 4) != 0 ? true : z4);
        }
    }

    public static final class Closing extends WindowAnimState {
        private final boolean freeform;

        public Closing(boolean z2) {
            super(null);
            this.freeform = z2;
        }

        public static /* synthetic */ Closing copy$default(Closing closing, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = closing.freeform;
            }
            return closing.copy(z2);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final Closing copy(boolean z2) {
            return new Closing(z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Closing) && this.freeform == ((Closing) obj).freeform;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public int hashCode() {
            return Boolean.hashCode(this.freeform);
        }

        public String toString() {
            return "Closing(freeform=" + this.freeform + ")";
        }
    }

    public static final class ClosingPosition extends WindowAnimState {
        private final boolean freeform;

        public ClosingPosition(boolean z2) {
            super(null);
            this.freeform = z2;
        }

        public static /* synthetic */ ClosingPosition copy$default(ClosingPosition closingPosition, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = closingPosition.freeform;
            }
            return closingPosition.copy(z2);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final ClosingPosition copy(boolean z2) {
            return new ClosingPosition(z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ClosingPosition) && this.freeform == ((ClosingPosition) obj).freeform;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public int hashCode() {
            return Boolean.hashCode(this.freeform);
        }

        public String toString() {
            return "ClosingPosition(freeform=" + this.freeform + ")";
        }
    }

    public static final class Idle extends WindowAnimState {
        public static final Idle INSTANCE = new Idle();

        private Idle() {
            super(null);
        }
    }

    public static final class Interrupting extends WindowAnimState {
        public static final Interrupting INSTANCE = new Interrupting();

        private Interrupting() {
            super(null);
        }
    }

    public static final class Opened extends WindowAnimState {
        private final boolean byAnimEvent;
        private final boolean freeform;
        private final boolean hasAnimate;

        public Opened(boolean z2, boolean z3, boolean z4) {
            super(null);
            this.freeform = z2;
            this.hasAnimate = z3;
            this.byAnimEvent = z4;
        }

        public static /* synthetic */ Opened copy$default(Opened opened, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = opened.freeform;
            }
            if ((i2 & 2) != 0) {
                z3 = opened.hasAnimate;
            }
            if ((i2 & 4) != 0) {
                z4 = opened.byAnimEvent;
            }
            return opened.copy(z2, z3, z4);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final boolean component2() {
            return this.hasAnimate;
        }

        public final boolean component3() {
            return this.byAnimEvent;
        }

        public final Opened copy(boolean z2, boolean z3, boolean z4) {
            return new Opened(z2, z3, z4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Opened)) {
                return false;
            }
            Opened opened = (Opened) obj;
            return this.freeform == opened.freeform && this.hasAnimate == opened.hasAnimate && this.byAnimEvent == opened.byAnimEvent;
        }

        public final boolean getByAnimEvent() {
            return this.byAnimEvent;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public final boolean getHasAnimate() {
            return this.hasAnimate;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.freeform) * 31) + Boolean.hashCode(this.hasAnimate)) * 31) + Boolean.hashCode(this.byAnimEvent);
        }

        public String toString() {
            return "Opened(freeform=" + this.freeform + ", hasAnimate=" + this.hasAnimate + ", byAnimEvent=" + this.byAnimEvent + ")";
        }

        public /* synthetic */ Opened(boolean z2, boolean z3, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(z2, z3, (i2 & 4) != 0 ? true : z4);
        }
    }

    public static final class Opening extends WindowAnimState {
        private final boolean freeform;

        public Opening(boolean z2) {
            super(null);
            this.freeform = z2;
        }

        public static /* synthetic */ Opening copy$default(Opening opening, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = opening.freeform;
            }
            return opening.copy(z2);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final Opening copy(boolean z2) {
            return new Opening(z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Opening) && this.freeform == ((Opening) obj).freeform;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public int hashCode() {
            return Boolean.hashCode(this.freeform);
        }

        public String toString() {
            return "Opening(freeform=" + this.freeform + ")";
        }
    }

    public static final class OpeningPosition extends WindowAnimState {
        private final boolean freeform;

        public OpeningPosition(boolean z2) {
            super(null);
            this.freeform = z2;
        }

        public static /* synthetic */ OpeningPosition copy$default(OpeningPosition openingPosition, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z2 = openingPosition.freeform;
            }
            return openingPosition.copy(z2);
        }

        public final boolean component1() {
            return this.freeform;
        }

        public final OpeningPosition copy(boolean z2) {
            return new OpeningPosition(z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OpeningPosition) && this.freeform == ((OpeningPosition) obj).freeform;
        }

        public final boolean getFreeform() {
            return this.freeform;
        }

        public int hashCode() {
            return Boolean.hashCode(this.freeform);
        }

        public String toString() {
            return "OpeningPosition(freeform=" + this.freeform + ")";
        }
    }

    public /* synthetic */ WindowAnimState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private WindowAnimState() {
    }
}
