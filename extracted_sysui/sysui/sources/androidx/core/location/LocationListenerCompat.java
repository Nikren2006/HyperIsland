package androidx.core.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface LocationListenerCompat extends LocationListener {
    @Override // android.location.LocationListener
    default void onFlushComplete(int i2) {
    }

    @Override // android.location.LocationListener
    default void onLocationChanged(List<Location> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            onLocationChanged(list.get(i2));
        }
    }

    @Override // android.location.LocationListener
    default void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    default void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    default void onStatusChanged(String str, int i2, Bundle bundle) {
    }
}
