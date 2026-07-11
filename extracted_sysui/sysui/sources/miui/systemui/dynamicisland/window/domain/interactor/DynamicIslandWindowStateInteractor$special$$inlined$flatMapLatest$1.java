package miui.systemui.dynamicisland.window.domain.interactor;

import H0.k;
import H0.s;
import I0.n;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1", f = "DynamicIslandWindowStateInteractor.kt", l = {HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP}, m = "invokeSuspend")
public final class DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1 extends l implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1(d dVar) {
        super(3, dVar);
    }

    public final Object invoke(InterfaceC0419g interfaceC0419g, List<? extends DynamicIslandContentView> list, d dVar) {
        DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1 dynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1 = new DynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1(dVar);
        dynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1.L$0 = interfaceC0419g;
        dynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1.L$1 = list;
        return dynamicIslandWindowStateInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            List list = (List) this.L$1;
            ArrayList arrayList = new ArrayList(n.o(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((DynamicIslandContentView) it.next()).getViewModel().isExpanded());
            }
            InterfaceC0418f[] interfaceC0418fArr = (InterfaceC0418f[]) arrayList.toArray(new InterfaceC0418f[0]);
            final InterfaceC0418f[] interfaceC0418fArr2 = (InterfaceC0418f[]) Arrays.copyOf(interfaceC0418fArr, interfaceC0418fArr.length);
            InterfaceC0418f interfaceC0418f = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$watchOutsideTouch$lambda$4$$inlined$combine$1

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$watchOutsideTouch$lambda$4$$inlined$combine$1$2, reason: invalid class name */
                public static final class AnonymousClass2 extends o implements Function0 {
                    final /* synthetic */ InterfaceC0418f[] $flows;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass2(InterfaceC0418f[] interfaceC0418fArr) {
                        super(0);
                        this.$flows = interfaceC0418fArr;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean[] invoke() {
                        return new Boolean[this.$flows.length];
                    }
                }

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$watchOutsideTouch$lambda$4$$inlined$combine$1$3, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$watchOutsideTouch$lambda$4$$inlined$combine$1$3", f = "DynamicIslandWindowStateInteractor.kt", l = {238}, m = "invokeSuspend")
                public static final class AnonymousClass3 extends l implements Function3 {
                    private /* synthetic */ Object L$0;
                    /* synthetic */ Object L$1;
                    int label;

                    public AnonymousClass3(d dVar) {
                        super(3, dVar);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(InterfaceC0419g interfaceC0419g, Boolean[] boolArr, d dVar) {
                        AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar);
                        anonymousClass3.L$0 = interfaceC0419g;
                        anonymousClass3.L$1 = boolArr;
                        return anonymousClass3.invokeSuspend(s.f314a);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) throws Throwable {
                        Boolean bool;
                        Object objC = c.c();
                        int i2 = this.label;
                        if (i2 == 0) {
                            k.b(obj);
                            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
                            Boolean[] boolArr = (Boolean[]) ((Object[]) this.L$1);
                            int length = boolArr.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length) {
                                    bool = null;
                                    break;
                                }
                                bool = boolArr[i3];
                                if (bool.booleanValue()) {
                                    break;
                                }
                                i3++;
                            }
                            Boolean boolA = b.a(bool != null ? bool.booleanValue() : false);
                            this.label = 1;
                            if (interfaceC0419g.emit(boolA, this) == objC) {
                                return objC;
                            }
                        } else {
                            if (i2 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k.b(obj);
                        }
                        return s.f314a;
                    }
                }

                @Override // j1.InterfaceC0418f
                public Object collect(InterfaceC0419g interfaceC0419g2, d dVar) {
                    InterfaceC0418f[] interfaceC0418fArr3 = interfaceC0418fArr2;
                    Object objA = k1.k.a(interfaceC0419g2, interfaceC0418fArr3, new AnonymousClass2(interfaceC0418fArr3), new AnonymousClass3(null), dVar);
                    return objA == c.c() ? objA : s.f314a;
                }
            };
            this.label = 1;
            if (AbstractC0420h.q(interfaceC0419g, interfaceC0418f, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        return s.f314a;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((InterfaceC0419g) obj, (List<? extends DynamicIslandContentView>) obj2, (d) obj3);
    }
}
