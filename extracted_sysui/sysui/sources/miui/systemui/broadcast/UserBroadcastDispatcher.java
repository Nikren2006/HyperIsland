package miui.systemui.broadcast;

import I0.r;
import I0.u;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.ArraySet;
import androidx.annotation.VisibleForTesting;
import com.android.internal.util.Preconditions;
import e1.AbstractC0345j;
import e1.InterfaceC0340e;
import e1.l;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.Dumpable;
import miui.systemui.broadcast.UserBroadcastDispatcher;
import miui.systemui.util.CompatUtils;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes2.dex */
public final class UserBroadcastDispatcher extends BroadcastReceiver implements Dumpable {
    public static final Companion Companion = new Companion(null);
    private static final AtomicInteger index = new AtomicInteger(0);
    private final ArrayMap<String, Set<ReceiverData>> actionsToReceivers;
    private final UserBroadcastDispatcher$bgHandler$1 bgHandler;
    private final Looper bgLooper;
    private final Context context;
    private final ArrayMap<BroadcastReceiver, Set<ReceiverData>> receiverToReceiverData;
    private final AtomicBoolean registered;
    private final int userId;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AtomicInteger getIndex() {
            return UserBroadcastDispatcher.index;
        }

        private Companion() {
        }
    }

    public static final class HandleBroadcastRunnable implements Runnable {
        private final Map<String, Set<ReceiverData>> actionsToReceivers;
        private final Context context;
        private final int index;
        private final Intent intent;
        private final BroadcastReceiver.PendingResult pendingResult;

        /* JADX WARN: Multi-variable type inference failed */
        public HandleBroadcastRunnable(Map<String, ? extends Set<ReceiverData>> actionsToReceivers, Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult, int i2) {
            n.g(actionsToReceivers, "actionsToReceivers");
            n.g(context, "context");
            n.g(intent, "intent");
            n.g(pendingResult, "pendingResult");
            this.actionsToReceivers = actionsToReceivers;
            this.context = context;
            this.intent = intent;
            this.pendingResult = pendingResult;
            this.index = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$2$lambda$1(HandleBroadcastRunnable this$0, ReceiverData it) {
            n.g(this$0, "this$0");
            n.g(it, "$it");
            it.getReceiver().setPendingResult(this$0.pendingResult);
            it.getReceiver().onReceive(this$0.context, this$0.intent);
        }

        public final Map<String, Set<ReceiverData>> getActionsToReceivers() {
            return this.actionsToReceivers;
        }

        public final Context getContext() {
            return this.context;
        }

        public final int getIndex() {
            return this.index;
        }

        public final Intent getIntent() {
            return this.intent;
        }

        public final BroadcastReceiver.PendingResult getPendingResult() {
            return this.pendingResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            Set<ReceiverData> set = this.actionsToReceivers.get(this.intent.getAction());
            if (set != null) {
                ArrayList<ReceiverData> arrayList = new ArrayList();
                for (Object obj : set) {
                    ReceiverData receiverData = (ReceiverData) obj;
                    if (receiverData.getFilter().hasAction(this.intent.getAction()) && receiverData.getFilter().matchCategories(this.intent.getCategories()) == null) {
                        arrayList.add(obj);
                    }
                }
                for (final ReceiverData receiverData2 : arrayList) {
                    receiverData2.getExecutor().execute(new Runnable() { // from class: miui.systemui.broadcast.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            UserBroadcastDispatcher.HandleBroadcastRunnable.run$lambda$2$lambda$1(this.f5337a, receiverData2);
                        }
                    });
                }
            }
        }
    }

    public final class RegisterReceiverRunnable implements Runnable {
        private final IntentFilter intentFilter;
        final /* synthetic */ UserBroadcastDispatcher this$0;

        public RegisterReceiverRunnable(UserBroadcastDispatcher userBroadcastDispatcher, IntentFilter intentFilter) {
            n.g(intentFilter, "intentFilter");
            this.this$0 = userBroadcastDispatcher;
            this.intentFilter = intentFilter;
        }

        public final IntentFilter getIntentFilter() {
            return this.intentFilter;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.this$0.registered.get()) {
                this.this$0.context.unregisterReceiver(this.this$0);
                this.this$0.registered.set(false);
            }
            if (this.intentFilter.countActions() <= 0 || this.this$0.registered.get()) {
                return;
            }
            CompatUtils.Context context = CompatUtils.Context.INSTANCE;
            Context context2 = this.this$0.context;
            UserBroadcastDispatcher userBroadcastDispatcher = this.this$0;
            context.registerReceiverAsUserCompat(context2, userBroadcastDispatcher, UserHandle.of(userBroadcastDispatcher.userId), this.intentFilter, null, this.this$0.bgHandler, (32 & 32) != 0 ? 2 : 0);
            this.this$0.registered.set(true);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.broadcast.UserBroadcastDispatcher$bgHandler$1] */
    public UserBroadcastDispatcher(Context context, int i2, final Looper bgLooper) {
        n.g(context, "context");
        n.g(bgLooper, "bgLooper");
        this.context = context;
        this.userId = i2;
        this.bgLooper = bgLooper;
        this.bgHandler = new Handler(bgLooper) { // from class: miui.systemui.broadcast.UserBroadcastDispatcher$bgHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                int i3 = msg.what;
                if (i3 == 0) {
                    UserBroadcastDispatcher userBroadcastDispatcher = this.this$0;
                    Object obj = msg.obj;
                    n.e(obj, "null cannot be cast to non-null type miui.systemui.broadcast.ReceiverData");
                    userBroadcastDispatcher.handleRegisterReceiver((ReceiverData) obj);
                    return;
                }
                if (i3 != 1) {
                    return;
                }
                UserBroadcastDispatcher userBroadcastDispatcher2 = this.this$0;
                Object obj2 = msg.obj;
                n.e(obj2, "null cannot be cast to non-null type android.content.BroadcastReceiver");
                userBroadcastDispatcher2.handleUnregisterReceiver((BroadcastReceiver) obj2);
            }
        };
        this.registered = new AtomicBoolean(false);
        this.actionsToReceivers = new ArrayMap<>();
        this.receiverToReceiverData = new ArrayMap<>();
    }

    private final IntentFilter createFilter() {
        Preconditions.checkState(getLooper().isCurrentThread(), "This method should only be called from BG thread");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Collection<Set<ReceiverData>> collectionValues = this.receiverToReceiverData.values();
        n.f(collectionValues, "<get-values>(...)");
        Iterator it = I0.n.q(collectionValues).iterator();
        while (it.hasNext()) {
            Iterator<String> itCategoriesIterator = ((ReceiverData) it.next()).getFilter().categoriesIterator();
            if (itCategoriesIterator != null) {
                n.d(itCategoriesIterator);
                InterfaceC0340e interfaceC0340eC = AbstractC0345j.c(itCategoriesIterator);
                if (interfaceC0340eC != null) {
                    r.s(linkedHashSet, interfaceC0340eC);
                }
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        Set<String> setKeySet = this.actionsToReceivers.keySet();
        n.f(setKeySet, "<get-keys>(...)");
        for (String str : setKeySet) {
            if (str != null) {
                intentFilter.addAction(str);
            }
        }
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            intentFilter.addCategory((String) it2.next());
        }
        return intentFilter;
    }

    private final void createFilterAndRegisterReceiverBG() {
        post(new RegisterReceiverRunnable(this, createFilter()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRegisterReceiver(ReceiverData receiverData) {
        Preconditions.checkState(getLooper().isCurrentThread(), "This method should only be called from BG thread");
        ArrayMap<BroadcastReceiver, Set<ReceiverData>> arrayMap = this.receiverToReceiverData;
        BroadcastReceiver receiver = receiverData.getReceiver();
        Set<ReceiverData> arraySet = arrayMap.get(receiver);
        if (arraySet == null) {
            arraySet = new ArraySet<>();
            arrayMap.put(receiver, arraySet);
        }
        arraySet.add(receiverData);
        Iterator<String> itActionsIterator = receiverData.getFilter().actionsIterator();
        n.f(itActionsIterator, "actionsIterator(...)");
        boolean z2 = false;
        while (itActionsIterator.hasNext()) {
            String next = itActionsIterator.next();
            ArrayMap<String, Set<ReceiverData>> arrayMap2 = this.actionsToReceivers;
            Set<ReceiverData> arraySet2 = arrayMap2.get(next);
            if (arraySet2 == null) {
                arraySet2 = new ArraySet<>();
                arrayMap2.put(next, arraySet2);
                z2 = true;
            }
            arraySet2.add(receiverData);
        }
        if (z2) {
            createFilterAndRegisterReceiverBG();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleUnregisterReceiver(BroadcastReceiver broadcastReceiver) {
        Preconditions.checkState(getLooper().isCurrentThread(), "This method should only be called from BG thread");
        Set<ReceiverData> set = this.receiverToReceiverData.get(broadcastReceiver);
        if (set == null) {
            return;
        }
        n.f(set, "getOrElse(...)");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            Iterator<String> itActionsIterator = ((ReceiverData) it.next()).getFilter().actionsIterator();
            n.f(itActionsIterator, "actionsIterator(...)");
            r.t(arrayList, l.f(AbstractC0345j.c(itActionsIterator)));
        }
        Set<String> setO0 = u.o0(arrayList);
        Set<ReceiverData> setRemove = this.receiverToReceiverData.remove(broadcastReceiver);
        if (setRemove != null) {
            setRemove.clear();
        }
        boolean z2 = false;
        for (String str : setO0) {
            Set<ReceiverData> set2 = this.actionsToReceivers.get(str);
            if (set2 != null) {
                final UserBroadcastDispatcher$handleUnregisterReceiver$1$1 userBroadcastDispatcher$handleUnregisterReceiver$1$1 = new UserBroadcastDispatcher$handleUnregisterReceiver$1$1(broadcastReceiver);
                set2.removeIf(new Predicate() { // from class: miui.systemui.broadcast.a
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return UserBroadcastDispatcher.handleUnregisterReceiver$lambda$13$lambda$12(userBroadcastDispatcher$handleUnregisterReceiver$1$1, obj);
                    }
                });
            }
            Set<ReceiverData> set3 = this.actionsToReceivers.get(str);
            if (set3 != null ? set3.isEmpty() : false) {
                this.actionsToReceivers.remove(str);
                z2 = true;
            }
        }
        if (z2) {
            createFilterAndRegisterReceiverBG();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleUnregisterReceiver$lambda$13$lambda$12(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    @Override // miui.systemui.Dumpable
    public void dump(FileDescriptor fileDescriptor, PrintWriter pw, String[] args) {
        n.g(pw, "pw");
        n.g(args, "args");
        pw.println("  Registered=" + this.registered.get());
        for (Map.Entry<String, Set<ReceiverData>> entry : this.actionsToReceivers.entrySet()) {
            String key = entry.getKey();
            Set<ReceiverData> value = entry.getValue();
            pw.println("    " + key + MethodCodeHelper.IDENTITY_INFO_SEPARATOR);
            n.d(value);
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                pw.println("      " + ((ReceiverData) it.next()).getReceiver());
            }
        }
    }

    @VisibleForTesting
    public final boolean isReceiverReferenceHeld$miui_common_release(BroadcastReceiver receiver) {
        n.g(receiver, "receiver");
        if (!this.receiverToReceiverData.containsKey(receiver)) {
            ArrayMap<String, Set<ReceiverData>> arrayMap = this.actionsToReceivers;
            if (!arrayMap.isEmpty()) {
                Iterator<Map.Entry<String, Set<ReceiverData>>> it = arrayMap.entrySet().iterator();
                while (it.hasNext()) {
                    Set<ReceiverData> value = it.next().getValue();
                    n.f(value, "<get-value>(...)");
                    Set<ReceiverData> set = value;
                    if (!(set instanceof Collection) || !set.isEmpty()) {
                        Iterator<T> it2 = set.iterator();
                        while (it2.hasNext()) {
                            if (n.c(((ReceiverData) it2.next()).getReceiver(), receiver)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final boolean isRegistered$miui_common_release() {
        return this.registered.get();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        n.g(context, "context");
        n.g(intent, "intent");
        UserBroadcastDispatcher$bgHandler$1 userBroadcastDispatcher$bgHandler$1 = this.bgHandler;
        ArrayMap<String, Set<ReceiverData>> arrayMap = this.actionsToReceivers;
        BroadcastReceiver.PendingResult pendingResult = getPendingResult();
        n.f(pendingResult, "getPendingResult(...)");
        userBroadcastDispatcher$bgHandler$1.post(new HandleBroadcastRunnable(arrayMap, context, intent, pendingResult, 0));
    }

    public final void registerReceiver(ReceiverData receiverData) {
        n.g(receiverData, "receiverData");
        obtainMessage(0, receiverData).sendToTarget();
    }

    public final void unregisterReceiver(BroadcastReceiver receiver) {
        n.g(receiver, "receiver");
        obtainMessage(1, receiver).sendToTarget();
    }
}
