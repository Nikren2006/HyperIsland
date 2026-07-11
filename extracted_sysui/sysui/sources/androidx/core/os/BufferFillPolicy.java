package androidx.core.os;

import androidx.annotation.RequiresApi;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 35)
public abstract class BufferFillPolicy {
    public static final Companion Companion = new Companion(null);
    public static final BufferFillPolicy DISCARD = new Discard();
    public static final BufferFillPolicy RING_BUFFER = new RingBuffer();
    private final int value;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class Discard extends BufferFillPolicy {
        public Discard() {
            super(1, null);
        }
    }

    public static final class RingBuffer extends BufferFillPolicy {
        public RingBuffer() {
            super(2, null);
        }
    }

    public /* synthetic */ BufferFillPolicy(int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2);
    }

    public final int getValue$core_release() {
        return this.value;
    }

    private BufferFillPolicy(int i2) {
        this.value = i2;
    }
}
