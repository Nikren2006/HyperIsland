package E1;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public enum h {
    INTERNAL,
    EXTERNAL;


    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Parcelable.Creator f141c = new Parcelable.Creator() { // from class: E1.h.a
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            try {
                return h.values()[parcel.readInt()];
            } catch (Exception e2) {
                throw new BadParcelableException(e2);
            }
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i2) {
            return new h[i2];
        }
    };

    public void writeToParcel(Parcel parcel, int i2) {
        h[] hVarArrValues = values();
        for (int i3 = 0; i3 < hVarArrValues.length; i3++) {
            if (hVarArrValues[i3] == this) {
                parcel.writeInt(i3);
                return;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
