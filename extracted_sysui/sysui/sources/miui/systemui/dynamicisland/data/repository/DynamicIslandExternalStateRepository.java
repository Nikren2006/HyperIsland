package miui.systemui.dynamicisland.data.repository;

import H0.i;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import g1.AbstractC0369g;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandExternalStateRepository {
    private final u _bouncerIsOrWillBeShowing;
    private final u _commandQueueDisable;
    private final u _isDeviceInteractive;
    private final u _isKeyguardGoingAway;
    private final u _isKeyguardOccluded;
    private final u _isKeyguardShowing;
    private final u _miPlayShow;
    private final u _notificationAppearance;
    private final u _notificationVisible;
    private final I bouncerIsOrWillBeShowing;
    private final I commandQueueDisable;
    private final I isDeviceInteractive;
    private final I isKeyguardGoingAway;
    private final I isKeyguardOccluded;
    private final I isKeyguardShowing;
    private final I miPlayShow;
    private final I notificationAppearance;
    private final I notificationVisible;
    private final E scope;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyBouncerIsOrWillBeShowing$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyBouncerIsOrWillBeShowing$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z2, d dVar) {
            super(2, dVar);
            this.$value = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new AnonymousClass1(this.$value, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._bouncerIsOrWillBeShowing.setValue(b.a(this.$value));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyCommandQueueDisableChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyCommandQueueDisableChanged$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C05951 extends l implements Function2 {
        final /* synthetic */ i $disable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05951(i iVar, d dVar) {
            super(2, dVar);
            this.$disable = iVar;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C05951(this.$disable, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C05951) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._commandQueueDisable.setValue(this.$disable);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyDeviceInteractive$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyDeviceInteractive$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C05961 extends l implements Function2 {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05961(boolean z2, d dVar) {
            super(2, dVar);
            this.$value = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C05961(this.$value, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C05961) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._isDeviceInteractive.setValue(b.a(this.$value));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardGoingAway$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardGoingAway$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C05971 extends l implements Function2 {
        final /* synthetic */ boolean $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05971(boolean z2, d dVar) {
            super(2, dVar);
            this.$value = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C05971(this.$value, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C05971) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._isKeyguardGoingAway.setValue(b.a(this.$value));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardOccluded$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardOccluded$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C05981 extends l implements Function2 {
        final /* synthetic */ boolean $occluded;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05981(boolean z2, d dVar) {
            super(2, dVar);
            this.$occluded = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C05981(this.$occluded, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C05981) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._isKeyguardOccluded.setValue(b.a(this.$occluded));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardShowingChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyKeyguardShowingChanged$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C05991 extends l implements Function2 {
        final /* synthetic */ boolean $showing;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05991(boolean z2, d dVar) {
            super(2, dVar);
            this.$showing = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C05991(this.$showing, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C05991) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._isKeyguardShowing.setValue(b.a(this.$showing));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyMiPlayShowStateChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyMiPlayShowStateChanged$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C06001 extends l implements Function2 {
        final /* synthetic */ boolean $miplayShow;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06001(boolean z2, d dVar) {
            super(2, dVar);
            this.$miplayShow = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C06001(this.$miplayShow, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06001) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._miPlayShow.setValue(b.a(this.$miplayShow));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyNotificationAppearanceChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyNotificationAppearanceChanged$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C06011 extends l implements Function2 {
        final /* synthetic */ boolean $appearance;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06011(boolean z2, d dVar) {
            super(2, dVar);
            this.$appearance = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C06011(this.$appearance, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06011) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._notificationAppearance.setValue(b.a(this.$appearance));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyNotificationVisibleChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository$notifyNotificationVisibleChanged$1", f = "DynamicIslandExternalStateRepository.kt", l = {}, m = "invokeSuspend")
    public static final class C06021 extends l implements Function2 {
        final /* synthetic */ boolean $visible;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06021(boolean z2, d dVar) {
            super(2, dVar);
            this.$visible = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalStateRepository.this.new C06021(this.$visible, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06021) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            DynamicIslandExternalStateRepository.this._notificationVisible.setValue(b.a(this.$visible));
            return s.f314a;
        }
    }

    public DynamicIslandExternalStateRepository(@DynamicIsland E scope) {
        n.g(scope, "scope");
        this.scope = scope;
        Boolean bool = Boolean.FALSE;
        u uVarA = K.a(bool);
        this._notificationVisible = uVarA;
        this.notificationVisible = AbstractC0420h.b(uVarA);
        u uVarA2 = K.a(bool);
        this._notificationAppearance = uVarA2;
        this.notificationAppearance = AbstractC0420h.b(uVarA2);
        u uVarA3 = K.a(bool);
        this._miPlayShow = uVarA3;
        this.miPlayShow = AbstractC0420h.b(uVarA3);
        u uVarA4 = K.a(new i(0, 0));
        this._commandQueueDisable = uVarA4;
        this.commandQueueDisable = AbstractC0420h.b(uVarA4);
        u uVarA5 = K.a(bool);
        this._isKeyguardShowing = uVarA5;
        this.isKeyguardShowing = AbstractC0420h.b(uVarA5);
        u uVarA6 = K.a(bool);
        this._isKeyguardOccluded = uVarA6;
        this.isKeyguardOccluded = AbstractC0420h.b(uVarA6);
        u uVarA7 = K.a(bool);
        this._isKeyguardGoingAway = uVarA7;
        this.isKeyguardGoingAway = AbstractC0420h.b(uVarA7);
        u uVarA8 = K.a(bool);
        this._bouncerIsOrWillBeShowing = uVarA8;
        this.bouncerIsOrWillBeShowing = AbstractC0420h.b(uVarA8);
        u uVarA9 = K.a(bool);
        this._isDeviceInteractive = uVarA9;
        this.isDeviceInteractive = AbstractC0420h.b(uVarA9);
    }

    public final I getBouncerIsOrWillBeShowing() {
        return this.bouncerIsOrWillBeShowing;
    }

    public final I getCommandQueueDisable() {
        return this.commandQueueDisable;
    }

    public final I getMiPlayShow() {
        return this.miPlayShow;
    }

    public final I getNotificationAppearance() {
        return this.notificationAppearance;
    }

    public final I getNotificationVisible() {
        return this.notificationVisible;
    }

    public final I isDeviceInteractive() {
        return this.isDeviceInteractive;
    }

    public final I isKeyguardGoingAway() {
        return this.isKeyguardGoingAway;
    }

    public final I isKeyguardOccluded() {
        return this.isKeyguardOccluded;
    }

    public final I isKeyguardShowing() {
        return this.isKeyguardShowing;
    }

    public final void notifyBouncerIsOrWillBeShowing(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(z2, null), 3, null);
    }

    public final void notifyCommandQueueDisableChanged(i disable) {
        n.g(disable, "disable");
        AbstractC0369g.b(this.scope, null, null, new C05951(disable, null), 3, null);
    }

    public final void notifyDeviceInteractive(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C05961(z2, null), 3, null);
    }

    public final void notifyKeyguardGoingAway(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C05971(z2, null), 3, null);
    }

    public final void notifyKeyguardOccluded(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C05981(z2, null), 3, null);
    }

    public final void notifyKeyguardShowingChanged(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C05991(z2, null), 3, null);
    }

    public final void notifyMiPlayShowStateChanged(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C06001(z2, null), 3, null);
    }

    public final void notifyNotificationAppearanceChanged(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C06011(z2, null), 3, null);
    }

    public final void notifyNotificationVisibleChanged(boolean z2) {
        AbstractC0369g.b(this.scope, null, null, new C06021(z2, null), 3, null);
    }
}
