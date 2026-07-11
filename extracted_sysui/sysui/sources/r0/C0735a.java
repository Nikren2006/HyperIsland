package r0;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import q0.InterfaceC0733a;
import z0.AbstractC0776a;
import z0.e;
import z0.i;

/* JADX INFO: renamed from: r0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0735a implements InterfaceC0733a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BluetoothAdapter f6427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f6428c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: r0.a$a, reason: collision with other inner class name */
    public static class C0166a extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f6429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b f6430b = null;

        public C0166a(Context context) {
            this.f6429a = context;
        }

        public void a(b bVar) {
            this.f6430b = bVar;
            try {
                this.f6429a.registerReceiver(this, new IntentFilter("Bluetooth.ACTION.TYPE_CHANGED"), 2);
            } catch (Exception e2) {
                e.b("BluetoothRealTypeReceiver", "registerListener failed ", e2);
            }
        }

        public void b(b bVar) {
            if (this.f6430b != null) {
                this.f6430b = null;
                try {
                    this.f6429a.unregisterReceiver(this);
                } catch (Exception e2) {
                    e.b("BluetoothRealTypeReceiver", "unregisterListener failed ", e2);
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !TextUtils.equals("Bluetooth.ACTION.TYPE_CHANGED", intent.getAction()) || this.f6430b == null) {
                return;
            }
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("device_new_type", 0);
            if (bluetoothDevice != null) {
                e.c("BluetoothRealTypeReceiver", "device mac:" + bluetoothDevice.getAddress() + " new type:" + intExtra);
                this.f6430b.d(bluetoothDevice, intExtra);
            }
        }
    }

    /* JADX INFO: renamed from: r0.a$b */
    public interface b {
        void d(BluetoothDevice bluetoothDevice, int i2);
    }

    public C0735a(Context context, BluetoothAdapter bluetoothAdapter) {
        this.f6426a = context;
        this.f6427b = bluetoothAdapter;
        c();
    }

    @Override // q0.InterfaceC0733a
    public int a(BluetoothDevice bluetoothDevice) {
        return d(bluetoothDevice.getAddress());
    }

    @Override // q0.InterfaceC0733a
    public int b(BluetoothDevice bluetoothDevice) {
        String address = bluetoothDevice.getAddress();
        Integer numValueOf = (Integer) this.f6428c.get(address);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(d(address));
        }
        if (numValueOf.intValue() == 0) {
            numValueOf = Integer.valueOf(AbstractC0776a.b(bluetoothDevice));
            e.c("BluetoothTypeManager", "queryBluetoothType, type: " + numValueOf);
            this.f6428c.put(address, numValueOf);
        }
        return numValueOf.intValue();
    }

    public final void c() {
        if (!this.f6427b.isEnabled()) {
            e.c("BluetoothTypeManager", "fetchAllDeviceType, bluetooth is not enabled");
            return;
        }
        try {
            Cursor cursorQuery = this.f6426a.getContentResolver().query(Uri.parse("content://com.android.bluetooth.btservice.BluetoothDeviceTypeProvider/btdevicetype"), new String[]{"address", "type"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (!cursorQuery.isClosed()) {
                        int columnIndex = cursorQuery.getColumnIndex("address");
                        int columnIndex2 = cursorQuery.getColumnIndex("type");
                        if (columnIndex < 0 || columnIndex2 < 0) {
                            e.c("BluetoothTypeManager", "fetchAllBluetoothType, address or type index is invalid");
                        } else {
                            while (cursorQuery.moveToNext()) {
                                this.f6428c.put(cursorQuery.getString(columnIndex), Integer.valueOf(cursorQuery.getInt(columnIndex2)));
                            }
                        }
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e.b("BluetoothTypeManager", "fetchAllBluetoothType failed ", e2);
        }
    }

    public final int d(String str) {
        int i2 = 0;
        if (!this.f6427b.isEnabled()) {
            e.c("BluetoothTypeManager", "fetchTargetBluetoothType, bluetooth is not enabled");
            return 0;
        }
        try {
            Cursor cursorQuery = this.f6426a.getContentResolver().query(Uri.parse("content://com.android.bluetooth.btservice.BluetoothDeviceTypeProvider/btdevicetype" + File.separator + Uri.encode(str)), new String[]{"type"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (!cursorQuery.isClosed() && cursorQuery.moveToFirst()) {
                        int columnIndex = cursorQuery.getColumnIndex("type");
                        if (columnIndex < 0) {
                            e.c("BluetoothTypeManager", "fetchTargetBluetoothType, type index is invalid " + i.c(str));
                        } else {
                            i2 = cursorQuery.getInt(columnIndex);
                            this.f6428c.put(str, Integer.valueOf(i2));
                        }
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e2) {
            e.b("BluetoothTypeManager", "fetchTargetBluetoothType failed ", e2);
        }
        return i2;
    }
}
