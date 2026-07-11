package androidx.lifecycle;

import H0.o;
import I0.G;
import I0.L;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandle {
    private static final String KEYS = "keys";
    private static final String VALUES = "values";
    private final Map<String, u> flows;
    private final Map<String, SavingStateLiveData<?>> liveDatas;
    private final Map<String, Object> regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;
    private final Map<String, SavedStateRegistry.SavedStateProvider> savedStateProviders;
    public static final Companion Companion = new Companion(null);
    private static final Class<? extends Object>[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap map = new HashMap();
                for (String key : bundle2.keySet()) {
                    n.f(key, "key");
                    map.put(key, bundle2.get(key));
                }
                return new SavedStateHandle(map);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(SavedStateHandle.KEYS);
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(SavedStateHandle.VALUES);
            if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = parcelableArrayList.get(i2);
                n.e(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i2));
            }
            return new SavedStateHandle(linkedHashMap);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final boolean validateValue(Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.ACCEPTABLE_CLASSES) {
                n.d(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }

        private Companion() {
        }
    }

    public SavedStateHandle(Map<String, ? extends Object> initialState) {
        n.g(initialState, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.f
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.savedStateProvider$lambda$0(this.f1262a);
            }
        };
        linkedHashMap.putAll(initialState);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
        return Companion.createHandle(bundle, bundle2);
    }

    private final <T> MutableLiveData<T> getLiveDataInternal(String str, boolean z2, T t2) {
        SavingStateLiveData<?> savingStateLiveData;
        SavingStateLiveData<?> savingStateLiveData2 = this.liveDatas.get(str);
        SavingStateLiveData<?> savingStateLiveData3 = savingStateLiveData2 instanceof MutableLiveData ? savingStateLiveData2 : null;
        if (savingStateLiveData3 != null) {
            return savingStateLiveData3;
        }
        if (this.regular.containsKey(str)) {
            savingStateLiveData = new SavingStateLiveData<>(this, str, this.regular.get(str));
        } else if (z2) {
            this.regular.put(str, t2);
            savingStateLiveData = new SavingStateLiveData<>(this, str, t2);
        } else {
            savingStateLiveData = new SavingStateLiveData<>(this, str);
        }
        this.liveDatas.put(str, savingStateLiveData);
        return savingStateLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle savedStateProvider$lambda$0(SavedStateHandle this$0) {
        n.g(this$0, "this$0");
        for (Map.Entry entry : G.n(this$0.savedStateProviders).entrySet()) {
            this$0.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        Set<String> setKeySet = this$0.regular.keySet();
        ArrayList arrayList = new ArrayList(setKeySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : setKeySet) {
            arrayList.add(str);
            arrayList2.add(this$0.regular.get(str));
        }
        return BundleKt.bundleOf(o.a(KEYS, arrayList), o.a(VALUES, arrayList2));
    }

    @MainThread
    public final void clearSavedStateProvider(String key) {
        n.g(key, "key");
        this.savedStateProviders.remove(key);
    }

    @MainThread
    public final boolean contains(String key) {
        n.g(key, "key");
        return this.regular.containsKey(key);
    }

    @MainThread
    public final <T> T get(String key) {
        n.g(key, "key");
        try {
            return (T) this.regular.get(key);
        } catch (ClassCastException unused) {
            this.remove(key);
            return null;
        }
    }

    @MainThread
    public final <T> MutableLiveData<T> getLiveData(String key) {
        n.g(key, "key");
        MutableLiveData<T> liveDataInternal = getLiveDataInternal(key, false, null);
        n.e(liveDataInternal, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<T of androidx.lifecycle.SavedStateHandle.getLiveData>");
        return liveDataInternal;
    }

    @MainThread
    public final <T> I getStateFlow(String key, T t2) {
        n.g(key, "key");
        Map<String, u> map = this.flows;
        u uVarA = map.get(key);
        if (uVarA == null) {
            if (!this.regular.containsKey(key)) {
                this.regular.put(key, t2);
            }
            uVarA = K.a(this.regular.get(key));
            this.flows.put(key, uVarA);
            map.put(key, uVarA);
        }
        I iB = AbstractC0420h.b(uVarA);
        n.e(iB, "null cannot be cast to non-null type kotlinx.coroutines.flow.StateFlow<T of androidx.lifecycle.SavedStateHandle.getStateFlow>");
        return iB;
    }

    @MainThread
    public final Set<String> keys() {
        return L.g(L.g(this.regular.keySet(), this.savedStateProviders.keySet()), this.liveDatas.keySet());
    }

    @MainThread
    public final <T> T remove(String key) {
        n.g(key, "key");
        T t2 = (T) this.regular.remove(key);
        SavingStateLiveData<?> savingStateLiveDataRemove = this.liveDatas.remove(key);
        if (savingStateLiveDataRemove != null) {
            savingStateLiveDataRemove.detach();
        }
        this.flows.remove(key);
        return t2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    @MainThread
    public final <T> void set(String key, T t2) {
        n.g(key, "key");
        if (!Companion.validateValue(t2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can't put value with type ");
            n.d(t2);
            sb.append(t2.getClass());
            sb.append(" into saved state");
            throw new IllegalArgumentException(sb.toString());
        }
        SavingStateLiveData<?> savingStateLiveData = this.liveDatas.get(key);
        SavingStateLiveData<?> savingStateLiveData2 = savingStateLiveData instanceof MutableLiveData ? savingStateLiveData : null;
        if (savingStateLiveData2 != null) {
            savingStateLiveData2.setValue(t2);
        } else {
            this.regular.put(key, t2);
        }
        u uVar = this.flows.get(key);
        if (uVar == null) {
            return;
        }
        uVar.setValue(t2);
    }

    @MainThread
    public final void setSavedStateProvider(String key, SavedStateRegistry.SavedStateProvider provider) {
        n.g(key, "key");
        n.g(provider, "provider");
        this.savedStateProviders.put(key, provider);
    }

    @MainThread
    public final <T> MutableLiveData<T> getLiveData(String key, T t2) {
        n.g(key, "key");
        return getLiveDataInternal(key, true, t2);
    }

    public static final class SavingStateLiveData<T> extends MutableLiveData<T> {
        private SavedStateHandle handle;
        private String key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SavingStateLiveData(SavedStateHandle savedStateHandle, String key, T t2) {
            super(t2);
            n.g(key, "key");
            this.key = key;
            this.handle = savedStateHandle;
        }

        public final void detach() {
            this.handle = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(T t2) {
            SavedStateHandle savedStateHandle = this.handle;
            if (savedStateHandle != null) {
                savedStateHandle.regular.put(this.key, t2);
                u uVar = (u) savedStateHandle.flows.get(this.key);
                if (uVar != null) {
                    uVar.setValue(t2);
                }
            }
            super.setValue(t2);
        }

        public SavingStateLiveData(SavedStateHandle savedStateHandle, String key) {
            n.g(key, "key");
            this.key = key;
            this.handle = savedStateHandle;
        }
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.f
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandle.savedStateProvider$lambda$0(this.f1262a);
            }
        };
    }
}
