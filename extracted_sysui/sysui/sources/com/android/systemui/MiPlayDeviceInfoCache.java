package com.android.systemui;

import androidx.lifecycle.MutableLiveData;
import g1.AbstractC0369g;
import g1.InterfaceC0380l0;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import kotlin.jvm.functions.Function2;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes.dex */
public abstract class MiPlayDeviceInfoCache<T, L> {
    private final HashMap<m0.i, MutableLiveData<T>> deviceVolumeMap = new HashMap<>();
    private final HashMap<m0.i, InterfaceC0380l0> deviceVolumeJobMap = new HashMap<>();
    private final HashMap<m0.i, L> deviceVolumeListenerMap = new HashMap<>();

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDeviceInfoCache$launchFetch$1, reason: invalid class name */
    @N0.f(c = "com.android.systemui.MiPlayDeviceInfoCache$launchFetch$1", f = "MiPlayDeviceInfoCache.kt", l = {26}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        final /* synthetic */ m0.i $device;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ MiPlayDeviceInfoCache<T, L> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MiPlayDeviceInfoCache<T, L> miPlayDeviceInfoCache, m0.i iVar, L0.d dVar) {
            super(2, dVar);
            this.this$0 = miPlayDeviceInfoCache;
            this.$device = iVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new AnonymousClass1(this.this$0, this.$device, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            m0.i iVar;
            MiPlayDeviceInfoCache miPlayDeviceInfoCache;
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                MiPlayDeviceInfoCache miPlayDeviceInfoCache2 = this.this$0;
                m0.i iVar2 = this.$device;
                this.L$0 = miPlayDeviceInfoCache2;
                this.L$1 = iVar2;
                this.label = 1;
                Object objFetchValue = miPlayDeviceInfoCache2.fetchValue(iVar2, this);
                if (objFetchValue == objC) {
                    return objC;
                }
                iVar = iVar2;
                obj = objFetchValue;
                miPlayDeviceInfoCache = miPlayDeviceInfoCache2;
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                iVar = (m0.i) this.L$1;
                MiPlayDeviceInfoCache miPlayDeviceInfoCache3 = (MiPlayDeviceInfoCache) this.L$0;
                H0.k.b(obj);
                miPlayDeviceInfoCache = miPlayDeviceInfoCache3;
            }
            miPlayDeviceInfoCache.putValue(iVar, obj);
            return H0.s.f314a;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.MiPlayDeviceInfoCache$updateDevices$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function2 {
        final /* synthetic */ HashMap<m0.i, MutableLiveData<T>> $last;
        final /* synthetic */ MiPlayDeviceInfoCache<T, L> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(HashMap<m0.i, MutableLiveData<T>> map, MiPlayDeviceInfoCache<T, L> miPlayDeviceInfoCache) {
            super(2);
            this.$last = map;
            this.this$0 = miPlayDeviceInfoCache;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((m0.i) obj, (MutableLiveData) obj2);
            return H0.s.f314a;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void invoke(m0.i device, MutableLiveData<T> liveData) {
            kotlin.jvm.internal.n.g(device, "device");
            kotlin.jvm.internal.n.g(liveData, "liveData");
            if (this.$last.containsKey(device)) {
                return;
            }
            this.this$0.launchFetch(device, liveData);
            MiPlayDeviceInfoCache<T, L> miPlayDeviceInfoCache = this.this$0;
            HashMap map = ((MiPlayDeviceInfoCache) miPlayDeviceInfoCache).deviceVolumeListenerMap;
            MiPlayDeviceInfoCache<T, L> miPlayDeviceInfoCache2 = this.this$0;
            L lCreateListener = (L) map.get(device);
            if (lCreateListener == null) {
                lCreateListener = miPlayDeviceInfoCache2.createListener(device);
                map.put(device, lCreateListener);
            }
            miPlayDeviceInfoCache.registerListener(device, lCreateListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchFetch(m0.i iVar, MutableLiveData<T> mutableLiveData) {
        this.deviceVolumeJobMap.put(iVar, AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(this, iVar, null), 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDevices$lambda$2(Function2 tmp0, Object obj, Object obj2) {
        kotlin.jvm.internal.n.g(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }

    public abstract L createListener(m0.i iVar);

    public abstract Object fetchValue(m0.i iVar, L0.d dVar);

    public final HashMap<m0.i, MutableLiveData<T>> getDeviceVolumeMap$miui_miplay_release() {
        return this.deviceVolumeMap;
    }

    public final MutableLiveData<T> getLiveData(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        return this.deviceVolumeMap.get(device);
    }

    public void putValue(m0.i device, T t2) {
        MutableLiveData<T> mutableLiveData;
        kotlin.jvm.internal.n.g(device, "device");
        if (this.deviceVolumeMap.containsKey(device)) {
            MutableLiveData<T> mutableLiveData2 = this.deviceVolumeMap.get(device);
            if (kotlin.jvm.internal.n.c(mutableLiveData2 != null ? mutableLiveData2.getValue() : null, t2) || (mutableLiveData = this.deviceVolumeMap.get(device)) == null) {
                return;
            }
            mutableLiveData.setValue(t2);
        }
    }

    public abstract void registerListener(m0.i iVar, L l2);

    public abstract void unregisterListener(m0.i iVar, L l2);

    public final void updateDevices(List<? extends m0.i> devices) {
        kotlin.jvm.internal.n.g(devices, "devices");
        Object objClone = this.deviceVolumeMap.clone();
        kotlin.jvm.internal.n.e(objClone, "null cannot be cast to non-null type java.util.HashMap<com.miui.miplay.audio.api.AudioDevice, androidx.lifecycle.MutableLiveData<T of com.android.systemui.MiPlayDeviceInfoCache>>{ kotlin.collections.TypeAliasesKt.HashMap<com.miui.miplay.audio.api.AudioDevice, androidx.lifecycle.MutableLiveData<T of com.android.systemui.MiPlayDeviceInfoCache>> }");
        HashMap map = (HashMap) objClone;
        this.deviceVolumeMap.clear();
        for (m0.i iVar : devices) {
            HashMap<m0.i, MutableLiveData<T>> map2 = this.deviceVolumeMap;
            MutableLiveData<T> mutableLiveData = (MutableLiveData) map.get(iVar);
            if (mutableLiveData == null) {
                mutableLiveData = new MutableLiveData<>();
            } else {
                kotlin.jvm.internal.n.d(mutableLiveData);
            }
            map2.put(iVar, mutableLiveData);
            MutableLiveData<T> mutableLiveData2 = (MutableLiveData) map.get(iVar);
            if (mutableLiveData2 != null) {
                InterfaceC0380l0 interfaceC0380l0 = this.deviceVolumeJobMap.get(iVar);
                if (interfaceC0380l0 != null) {
                    kotlin.jvm.internal.n.d(interfaceC0380l0);
                    InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
                }
                kotlin.jvm.internal.n.d(mutableLiveData2);
                launchFetch(iVar, mutableLiveData2);
            }
        }
        HashMap<m0.i, MutableLiveData<T>> map3 = this.deviceVolumeMap;
        final AnonymousClass2 anonymousClass2 = new AnonymousClass2(map, this);
        map3.forEach(new BiConsumer() { // from class: com.android.systemui.l
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MiPlayDeviceInfoCache.updateDevices$lambda$2(anonymousClass2, obj, obj2);
            }
        });
        Set<m0.i> setKeySet = map.keySet();
        kotlin.jvm.internal.n.f(setKeySet, "<get-keys>(...)");
        for (m0.i iVar2 : setKeySet) {
            if (!this.deviceVolumeMap.keySet().contains(iVar2)) {
                InterfaceC0380l0 interfaceC0380l02 = this.deviceVolumeJobMap.get(iVar2);
                if (interfaceC0380l02 != null) {
                    kotlin.jvm.internal.n.d(interfaceC0380l02);
                    InterfaceC0380l0.a.a(interfaceC0380l02, null, 1, null);
                }
                this.deviceVolumeJobMap.remove(iVar2);
                L l2 = this.deviceVolumeListenerMap.get(iVar2);
                if (l2 != null) {
                    kotlin.jvm.internal.n.d(iVar2);
                    unregisterListener(iVar2, l2);
                }
            }
        }
    }
}
