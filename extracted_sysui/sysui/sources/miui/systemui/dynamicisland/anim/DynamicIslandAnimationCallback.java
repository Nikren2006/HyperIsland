package miui.systemui.dynamicisland.anim;

import I0.m;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationCallback {
    private final Map<DynamicIslandAnimationType, List<AnimCallback>> mAnimCallbacks = new LinkedHashMap();

    public static final class AnimCallback {
        private final Function1 callback;
        private final DynamicIslandAnimationCallbackType type;

        public AnimCallback(DynamicIslandAnimationCallbackType type, Function1 callback) {
            n.g(type, "type");
            n.g(callback, "callback");
            this.type = type;
            this.callback = callback;
        }

        public static /* synthetic */ AnimCallback copy$default(AnimCallback animCallback, DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType, Function1 function1, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                dynamicIslandAnimationCallbackType = animCallback.type;
            }
            if ((i2 & 2) != 0) {
                function1 = animCallback.callback;
            }
            return animCallback.copy(dynamicIslandAnimationCallbackType, function1);
        }

        public final DynamicIslandAnimationCallbackType component1() {
            return this.type;
        }

        public final Function1 component2() {
            return this.callback;
        }

        public final AnimCallback copy(DynamicIslandAnimationCallbackType type, Function1 callback) {
            n.g(type, "type");
            n.g(callback, "callback");
            return new AnimCallback(type, callback);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimCallback)) {
                return false;
            }
            AnimCallback animCallback = (AnimCallback) obj;
            return this.type == animCallback.type && n.c(this.callback, animCallback.callback);
        }

        public final Function1 getCallback() {
            return this.callback;
        }

        public final DynamicIslandAnimationCallbackType getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.callback.hashCode();
        }

        public String toString() {
            return "AnimCallback(type=" + this.type + ", callback=" + this.callback + ")";
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.anim.DynamicIslandAnimationCallback$removeCallback$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        final /* synthetic */ Function1 $callback;
        final /* synthetic */ DynamicIslandAnimationCallbackType $callbackType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DynamicIslandAnimationCallbackType dynamicIslandAnimationCallbackType, Function1 function1) {
            super(1);
            this.$callbackType = dynamicIslandAnimationCallbackType;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(AnimCallback it) {
            n.g(it, "it");
            return Boolean.valueOf(it.getType() == this.$callbackType && n.c(it.getCallback(), this.$callback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeCallback$lambda$7(Function1 tmp0, Object obj) {
        n.g(tmp0, "$tmp0");
        return ((Boolean) tmp0.invoke(obj)).booleanValue();
    }

    public final void addAnimationCallback(DynamicIslandAnimationType animType, DynamicIslandAnimationCallbackType callbackType, Function1 callback) {
        n.g(animType, "animType");
        n.g(callbackType, "callbackType");
        n.g(callback, "callback");
        if (!this.mAnimCallbacks.containsKey(animType)) {
            this.mAnimCallbacks.put(animType, new ArrayList());
        }
        Log.d("DynamicIslandAnimationCallback", "addAnimationCallback animType=" + animType + ", callbackType=" + callbackType + ", callback=" + callback);
        List<AnimCallback> list = this.mAnimCallbacks.get(animType);
        if (list == null) {
            throw new IllegalStateException(("addStartCallback animType=" + animType + ", callbackType=" + callbackType + " in empty collections.").toString());
        }
        List<AnimCallback> list2 = list;
        if (!list2.isEmpty()) {
            for (AnimCallback animCallback : list2) {
                if (callbackType == animCallback.getType() && n.c(callback, animCallback.getCallback())) {
                    return;
                }
            }
        }
        list2.add(new AnimCallback(callbackType, callback));
    }

    public final void executeCallback(DynamicIslandAnimationType animType, DynamicIslandAnimationCallbackType callbackType, String tag) {
        Integer numValueOf;
        n.g(animType, "animType");
        n.g(callbackType, "callbackType");
        n.g(tag, "tag");
        List<AnimCallback> list = this.mAnimCallbacks.get(animType);
        if (list != null) {
            int i2 = 0;
            if (!list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((AnimCallback) it.next()).getType() == callbackType && (i2 = i2 + 1) < 0) {
                        m.m();
                    }
                }
            }
            numValueOf = Integer.valueOf(i2);
        } else {
            numValueOf = null;
        }
        Log.d("DynamicIslandAnimationCallback", "executeCallback animType=" + animType + ", callbackType=" + callbackType + ", tag=" + tag + ", callbackNum=" + numValueOf);
        List<AnimCallback> list2 = this.mAnimCallbacks.get(animType);
        if (list2 != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                if (((AnimCallback) obj).getType() == callbackType) {
                    arrayList.add(obj);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((AnimCallback) it2.next()).getCallback().invoke(tag);
            }
        }
    }

    public final void removeCallback(DynamicIslandAnimationType animType, DynamicIslandAnimationCallbackType callbackType, Function1 callback) {
        n.g(animType, "animType");
        n.g(callbackType, "callbackType");
        n.g(callback, "callback");
        if (this.mAnimCallbacks.containsKey(animType)) {
            Log.d("DynamicIslandAnimationCallback", "removeCallback animType=" + animType + ", callbackType=" + callbackType + ", callback=" + callback);
            List<AnimCallback> list = this.mAnimCallbacks.get(animType);
            if (list != null) {
                final AnonymousClass2 anonymousClass2 = new AnonymousClass2(callbackType, callback);
                list.removeIf(new Predicate() { // from class: miui.systemui.dynamicisland.anim.a
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return DynamicIslandAnimationCallback.removeCallback$lambda$7(anonymousClass2, obj);
                    }
                });
                return;
            }
            throw new IllegalStateException(("removeCallback animType=" + animType + ", callbackType=" + callbackType + " in empty collections.").toString());
        }
    }
}
