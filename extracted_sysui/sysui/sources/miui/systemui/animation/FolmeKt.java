package miui.systemui.animation;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.view.View;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import g1.S;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.lifecycle.RepeatWhenAttachedKt;
import miuix.animation.Folme;
import miuix.animation.FolmeObject;
import miuix.animation.IFolme;

/* JADX INFO: loaded from: classes2.dex */
public final class FolmeKt {

    /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanFolmeWhenDetached$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.animation.FolmeKt$cleanFolmeWhenDetached$1", f = "Folme.kt", l = {}, m = "invokeSuspend")
    public static final class C04721 extends l implements Function3 {
        final /* synthetic */ View $this_cleanFolmeWhenDetached;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanFolmeWhenDetached$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.animation.FolmeKt$cleanFolmeWhenDetached$1$1", f = "Folme.kt", l = {52}, m = "invokeSuspend")
        public static final class C01071 extends l implements Function2 {
            int label;

            public C01071(d dVar) {
                super(2, dVar);
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new C01071(dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((C01071) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    this.label = 1;
                    if (M.a(this) == objC) {
                        return objC;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k.b(obj);
                }
                throw new H0.c();
            }
        }

        /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanFolmeWhenDetached$1$2, reason: invalid class name */
        public static final class AnonymousClass2 extends o implements Function1 {
            final /* synthetic */ View $this_cleanFolmeWhenDetached;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(View view) {
                super(1);
                this.$this_cleanFolmeWhenDetached = view;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return s.f314a;
            }

            public final void invoke(Throwable th) {
                IFolme iFolmeFolme = FolmeKt.folme(this.$this_cleanFolmeWhenDetached);
                if (iFolmeFolme != null) {
                    iFolmeFolme.cancel();
                    iFolmeFolme.clean();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04721(View view, d dVar) {
            super(3, dVar);
            this.$this_cleanFolmeWhenDetached = view;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            C04721 c04721 = new C04721(this.$this_cleanFolmeWhenDetached, dVar);
            c04721.L$0 = e2;
            return c04721.invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            AbstractC0369g.b((E) this.L$0, null, null, new C01071(null), 3, null).l(new AnonymousClass2(this.$this_cleanFolmeWhenDetached));
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanWhenViewDetached$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.animation.FolmeKt$cleanWhenViewDetached$1", f = "Folme.kt", l = {}, m = "invokeSuspend")
    public static final class C04731 extends l implements Function3 {
        final /* synthetic */ FolmeObject $this_cleanWhenViewDetached;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanWhenViewDetached$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.animation.FolmeKt$cleanWhenViewDetached$1$1", f = "Folme.kt", l = {68}, m = "invokeSuspend")
        public static final class C01081 extends l implements Function2 {
            int label;

            public C01081(d dVar) {
                super(2, dVar);
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new C01081(dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((C01081) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    this.label = 1;
                    if (M.a(this) == objC) {
                        return objC;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k.b(obj);
                }
                throw new H0.c();
            }
        }

        /* JADX INFO: renamed from: miui.systemui.animation.FolmeKt$cleanWhenViewDetached$1$2, reason: invalid class name */
        public static final class AnonymousClass2 extends o implements Function1 {
            final /* synthetic */ FolmeObject $this_cleanWhenViewDetached;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(FolmeObject folmeObject) {
                super(1);
                this.$this_cleanWhenViewDetached = folmeObject;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return s.f314a;
            }

            public final void invoke(Throwable th) {
                Folme.ObjectFolmeImpl objectFolmeImplFolme = this.$this_cleanWhenViewDetached.folme();
                if (objectFolmeImplFolme != null) {
                    objectFolmeImplFolme.cancel();
                    objectFolmeImplFolme.clean();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04731(FolmeObject folmeObject, d dVar) {
            super(3, dVar);
            this.$this_cleanWhenViewDetached = folmeObject;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            C04731 c04731 = new C04731(this.$this_cleanWhenViewDetached, dVar);
            c04731.L$0 = e2;
            return c04731.invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            AbstractC0369g.b((E) this.L$0, null, null, new C01081(null), 3, null).l(new AnonymousClass2(this.$this_cleanWhenViewDetached));
            return s.f314a;
        }
    }

    public static final FolmeObject FolmeObject() {
        return new FolmeObject() { // from class: miui.systemui.animation.FolmeKt.FolmeObject.1
            private Folme.ObjectFolmeImpl folmeImpl;

            @Override // miuix.animation.FolmeObject
            public Folme.ObjectFolmeImpl folme() {
                return this.folmeImpl;
            }

            @Override // miuix.animation.FolmeObject
            public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
                this.folmeImpl = objectFolmeImpl;
            }
        };
    }

    public static final S cleanFolmeWhenDetached(View view) {
        n.g(view, "<this>");
        return RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new C04721(view, null), 1, null);
    }

    public static final S cleanWhenViewDetached(FolmeObject folmeObject, View view) {
        n.g(folmeObject, "<this>");
        n.g(view, "view");
        return RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new C04731(folmeObject, null), 1, null);
    }

    public static final IFolme folme(View view) {
        n.g(view, "<this>");
        return Folme.get(view);
    }

    public static final IFolme getFolme(View view) {
        n.g(view, "<this>");
        IFolme iFolmeUse = Folme.use(view);
        n.f(iFolmeUse, "use(...)");
        return iFolmeUse;
    }

    public static final IFolme getFolme(FolmeObject folmeObject) {
        n.g(folmeObject, "<this>");
        Folme.ObjectFolmeImpl objectFolmeImplUse = Folme.use(folmeObject);
        n.f(objectFolmeImplUse, "use(...)");
        return objectFolmeImplUse;
    }
}
