package t0;

import android.os.Bundle;
import com.miui.miplay.audio.data.DeviceInfo;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f6829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f6830b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Collator f6831c = Collator.getInstance(Locale.US);

    public class b implements Comparator {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(t0.b bVar, t0.b bVar2) {
            DeviceInfo deviceInfoK = bVar.k();
            DeviceInfo deviceInfoK2 = bVar2.k();
            if (deviceInfoK.isLocalSpeaker() != deviceInfoK2.isLocalSpeaker()) {
                return deviceInfoK.isLocalSpeaker() ? -1 : 1;
            }
            if (deviceInfoK.isBluetoothHeadset() && !deviceInfoK2.isBluetoothHeadset()) {
                return -1;
            }
            if (!deviceInfoK.isBluetoothHeadset() && deviceInfoK2.isBluetoothHeadset()) {
                return 1;
            }
            if (deviceInfoK.isBluetoothHeadset()) {
                return e.this.f6831c.compare(deviceInfoK.getName(), deviceInfoK2.getName());
            }
            return 0;
        }
    }

    public class c extends b {
        public c() {
            super();
        }

        @Override // t0.e.b, java.util.Comparator
        /* JADX INFO: renamed from: a */
        public int compare(t0.b bVar, t0.b bVar2) {
            int iCompare = super.compare(bVar, bVar2);
            if (iCompare != 0) {
                return iCompare;
            }
            DeviceInfo deviceInfoK = bVar.k();
            DeviceInfo deviceInfoK2 = bVar2.k();
            int iH = bVar.h();
            int iH2 = bVar2.h();
            if (iH == 1 && iH2 != 1) {
                return -1;
            }
            if (iH != 1 && iH2 == 1) {
                return 1;
            }
            int iB = b(deviceInfoK.getExtra());
            int iB2 = b(deviceInfoK2.getExtra());
            if (c(iB) && !c(iB2)) {
                return -1;
            }
            if (!c(iB) && c(iB2)) {
                return 1;
            }
            int iCompare2 = Integer.compare(iB, iB2);
            return iCompare2 != 0 ? iCompare2 : e.this.f6831c.compare(deviceInfoK.getName(), deviceInfoK2.getName());
        }

        public final int b(Bundle bundle) {
            if (bundle == null) {
                return 0;
            }
            return bundle.getInt(DeviceInfo.EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0);
        }

        public final boolean c(int i2) {
            return i2 == 4 || i2 == 16;
        }
    }

    public e() {
        this.f6829a = new c();
        this.f6830b = new b();
    }

    public boolean b(List list) {
        ArrayList arrayList = new ArrayList(list);
        list.sort(this.f6830b);
        return !arrayList.equals(list);
    }

    public boolean c(List list) {
        ArrayList arrayList = new ArrayList(list);
        list.sort(this.f6829a);
        return !arrayList.equals(list);
    }
}
