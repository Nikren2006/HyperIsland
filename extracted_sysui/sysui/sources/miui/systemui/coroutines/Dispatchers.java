package miui.systemui.coroutines;

import android.util.Log;
import g1.AbstractC0360b0;
import g1.B;
import h1.d;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatchers {
    public static final Dispatchers INSTANCE;
    private static final String TAG = "PluginDispatchers";
    private static final DispatcherHolder<AbstractC0360b0> _Default;
    private static final DispatcherHolder<AbstractC0360b0> _IO;
    private static final DispatcherHolder<d> _Main;

    public static final class DispatcherHolder<T extends B> {
        private T _dispatcher;
        private final Function0 creator;
        private final Function1 destroyer;

        public DispatcherHolder(Function0 creator, Function1 destroyer) {
            n.g(creator, "creator");
            n.g(destroyer, "destroyer");
            this.creator = creator;
            this.destroyer = destroyer;
        }

        public final void create() {
            if (this._dispatcher != null) {
                Log.d(Dispatchers.TAG, "dispatcher is already created.");
            } else {
                this._dispatcher = (T) this.creator.invoke();
            }
        }

        public final void destroy() {
            T t2 = this._dispatcher;
            if (t2 != null) {
                this.destroyer.invoke(t2);
            }
            this._dispatcher = null;
        }

        public final T getDispatcher() {
            T t2 = this._dispatcher;
            if (t2 != null) {
                return t2;
            }
            throw new IllegalStateException("dispatcher is not created or was destroyed.");
        }
    }

    static {
        Dispatchers dispatchers = new Dispatchers();
        INSTANCE = dispatchers;
        _Main = new DispatcherHolder<>(Dispatchers$_Main$1.INSTANCE, Dispatchers$_Main$2.INSTANCE);
        _IO = new DispatcherHolder<>(Dispatchers$_IO$1.INSTANCE, Dispatchers$_IO$2.INSTANCE);
        _Default = new DispatcherHolder<>(Dispatchers$_Default$1.INSTANCE, Dispatchers$_Default$2.INSTANCE);
        dispatchers.onCreate();
    }

    private Dispatchers() {
    }

    public final AbstractC0360b0 getDefault() {
        return (AbstractC0360b0) _Default.getDispatcher();
    }

    public final AbstractC0360b0 getIO() {
        return (AbstractC0360b0) _IO.getDispatcher();
    }

    public final d getMain() {
        return (d) _Main.getDispatcher();
    }

    public final synchronized void onCreate() {
        _Main.create();
        _Default.create();
        _IO.create();
    }

    public final synchronized void onDestroy() {
        _Main.destroy();
        _Default.destroy();
        _IO.destroy();
    }
}
