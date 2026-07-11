package h0;

import D0.d;
import H0.k;
import H0.s;
import M0.c;
import N0.l;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceInfo;
import g1.AbstractC0369g;
import g1.C0366e0;
import g1.E;
import i0.AbstractC0404b;
import i0.AbstractC0407e;
import i0.InterfaceC0406d;
import java.util.Iterator;
import k0.b;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.DeviceCenterController;

/* JADX INFO: renamed from: h0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0401a extends AbstractC0404b {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static Context f4480k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static DeviceInfo f4481l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0401a f4478i = new C0401a();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f4479j = "circulate_card";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static String f4482m = "";

    /* JADX INFO: renamed from: h0.a$a, reason: collision with other inner class name */
    public static final class C0084a extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f4484b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ DeviceInfo f4485c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0084a(d dVar, DeviceInfo deviceInfo, L0.d dVar2) {
            super(2, dVar2);
            this.f4484b = dVar;
            this.f4485c = deviceInfo;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new C0084a(this.f4484b, this.f4485c, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C0084a) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.f4483a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            this.f4484b.e("circulate_card", this.f4485c);
            this.f4484b.f(C0401a.f4478i.C());
            return s.f314a;
        }
    }

    public static /* synthetic */ s E(C0401a c0401a, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        return c0401a.D(i2);
    }

    public static /* synthetic */ void H(C0401a c0401a, Context context, DeviceInfo deviceInfo, String str, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = "";
        }
        c0401a.G(context, deviceInfo, str);
    }

    public final String C() {
        return f4479j;
    }

    public final s D(int i2) {
        Messenger messengerK = k();
        if (messengerK == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        C0401a c0401a = f4478i;
        sb.append(c0401a.l());
        sb.append(": sendMsgHideViewToServer cardId = ");
        sb.append(i2);
        b.e("[view_hide]:", sb.toString());
        Message messageObtain = Message.obtain((Handler) null, 2);
        Bundle bundleA = AbstractC0407e.a();
        bundleA.putInt("mlCardId", i2);
        messageObtain.setData(bundleA);
        messageObtain.replyTo = c0401a.i();
        try {
            messengerK.send(messageObtain);
        } catch (RemoteException e2) {
            b.i("[view_hide]:", f4478i.l() + ": " + e2);
        }
        return s.f314a;
    }

    public final boolean F(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), DeviceCenterController.KEY_QUICK_CONTROL) == 1;
        } catch (Exception e2) {
            b.b(Constant.TAG, "get 'quick_control_all_devices' value ", e2);
            return false;
        }
    }

    public final void G(Context context, DeviceInfo deviceInfo, String toSpecifyPanel) {
        n.g(context, "context");
        n.g(deviceInfo, "deviceInfo");
        n.g(toSpecifyPanel, "toSpecifyPanel");
        AbstractC0407e.b(context);
        d dVarA = d.f64d.a(context, F(context), f4479j);
        if (TextUtils.equals(deviceInfo.getDeviceType(), Constant.DeviceType.MIJIA_IOT_CONTROL)) {
            b.e("[view_show]:", l() + ": MijiaControl request click scene");
            AbstractC0369g.b(C0366e0.f4417a, null, null, new C0084a(dVarA, deviceInfo, null), 3, null);
            return;
        }
        b.e("[view_show]:", l() + ": showCardByDeviceInfo deviceInfo=" + k0.c.a(deviceInfo.toString()));
        f4480k = context;
        f4481l = deviceInfo;
        f4482m = toSpecifyPanel;
        z();
    }

    @Override // i0.AbstractC0404b
    public Context f() {
        return f4480k;
    }

    @Override // i0.AbstractC0404b
    public String m() {
        return "MLCardManagerClient";
    }

    @Override // i0.AbstractC0404b
    public void p() {
        b.e("[view_hide]:", l() + ": onDestroy");
        f4480k = null;
        f4481l = null;
    }

    @Override // i0.AbstractC0404b
    public void q(Message msg) {
        n.g(msg, "msg");
        int i2 = msg.what;
        if (i2 == 4) {
            int i3 = msg.getData().getInt("mlCardId");
            b.e("[view_hide]:", l() + ": receive message VIEW_HIDDEN mCardId = " + i3);
            Iterator it = h().iterator();
            while (it.hasNext()) {
                ((InterfaceC0406d) it.next()).onCardHiddenCallback(i3);
            }
            B();
            return;
        }
        if (i2 == 5) {
            int i4 = msg.getData().getInt("mlCardId");
            b.e("[view_show]:", l() + ": receive message VIEW_CREATED mCardId = " + i4);
            Iterator it2 = h().iterator();
            while (it2.hasNext()) {
                ((InterfaceC0406d) it2.next()).onCardCreatedCallback(i4);
            }
            return;
        }
        if (i2 == 6) {
            int i5 = msg.getData().getInt("mlCardId");
            b.e("[view_update]:", l() + ": receive message VIEW_SHOW_AND_CHANGED mCardId = " + i5);
            Iterator it3 = h().iterator();
            while (it3.hasNext()) {
                ((InterfaceC0406d) it3.next()).onCardShowAndChangedCallback(i5);
            }
            return;
        }
        if (i2 == 9) {
            int i6 = msg.getData().getInt("keyCardContentErr");
            b.i("[view_hide]:", l() + ": receive message CARD_CONTENT_ERR errType = " + i6);
            Iterator it4 = h().iterator();
            while (it4.hasNext()) {
                ((InterfaceC0406d) it4.next()).onCardContentErrCallback(i6);
            }
            return;
        }
        if (i2 != 10) {
            b.i("[other]:", l() + ": receive message OTHER=" + msg.what);
            return;
        }
        Bundle data = msg.getData();
        data.getInt("mlCardId");
        b.e("[view_hide]:", l() + ": receive message CARD_HIDE_ANIM_START duration = " + data.getLong("keyCardContentHideAnimDuration") + " isNeedExitApp=" + data.getBoolean("keyCardIsNeedExitApp"));
        Iterator it5 = g().iterator();
        if (it5.hasNext()) {
            android.support.v4.media.a.a(it5.next());
            throw null;
        }
    }

    @Override // i0.AbstractC0404b
    public void s(String str, int i2) {
        n.g(str, "str");
        Context context = f4480k;
        if (context != null) {
            Toast.makeText(context, str, i2).show();
        }
    }

    @Override // i0.AbstractC0404b
    public s x() {
        Messenger messengerK = k();
        if (messengerK == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        C0401a c0401a = f4478i;
        sb.append(c0401a.l());
        sb.append(": sendMsgCreateRemoteViewToServer");
        b.e("[view_show]:", sb.toString());
        Message messageObtain = Message.obtain((Handler) null, 1);
        messageObtain.setData(AbstractC0407e.a());
        messageObtain.getData().putParcelable("deviceInfo", f4481l);
        messageObtain.getData().putString("keyDeviceToSpecifyPanel", f4482m);
        messageObtain.replyTo = c0401a.i();
        try {
            messengerK.send(messageObtain);
        } catch (RemoteException e2) {
            b.i("[view_show]:", f4478i.l() + ": " + e2);
        }
        return s.f314a;
    }
}
