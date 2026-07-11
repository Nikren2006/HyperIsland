package miuix.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import androidx.annotation.RequiresPermission;
import miuix.core.util.SoftReferenceSingleton;

/* JADX INFO: loaded from: classes.dex */
public class ConnectivityHelper {
    private static final SoftReferenceSingleton<ConnectivityHelper> INSTANCE = new SoftReferenceSingleton<ConnectivityHelper>() { // from class: miuix.net.ConnectivityHelper.1
        @Override // miuix.core.util.SoftReferenceSingleton
        public ConnectivityHelper createInstance(Object obj) {
            return new ConnectivityHelper((Context) obj);
        }
    };
    private static final String TAG = "ConnectivityHelper";
    private ConnectivityManager mConnectivityManager;
    private String mMacAddress;
    private WifiManager mWifiManager;

    public static ConnectivityHelper getInstance(Context context) {
        return INSTANCE.get(context);
    }

    public ConnectivityManager getManager() {
        return this.mConnectivityManager;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public boolean isNetworkConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public boolean isUnmeteredNetworkConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || this.mConnectivityManager.isActiveNetworkMetered()) ? false : true;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    private ConnectivityHelper(Context context) {
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
    }
}
