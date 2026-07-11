package E1;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f128d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f129e;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return d.r(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i2) {
            return new d[i2];
        }
    }

    public d(long j2, String str, long j3, List list) {
        this.f125a = j2;
        this.f126b = str;
        this.f127c = j3;
        this.f129e = Collections.unmodifiableList(list);
        this.f128d = list.size() != 0;
    }

    public static d r(Parcel parcel) {
        try {
            long j2 = parcel.readLong();
            String string = parcel.readString();
            long j3 = parcel.readLong();
            ArrayList arrayList = new ArrayList();
            parcel.readTypedList(arrayList, e.CREATOR);
            return new d(j2, string, j3, arrayList);
        } catch (BadParcelableException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new BadParcelableException(e3);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 3;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(getClass()) && this.f125a == ((d) obj).f125a;
    }

    public int hashCode() {
        return Long.valueOf(this.f125a).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Device\n");
        sb.append("\tID: ");
        sb.append(this.f125a);
        sb.append("\n");
        sb.append("\tName: ");
        sb.append(this.f126b);
        sb.append("\n");
        for (e eVar : this.f129e) {
            sb.append("\tFilter: ");
            sb.append(eVar);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f125a);
        parcel.writeString(this.f126b);
        parcel.writeLong(this.f127c);
        parcel.writeTypedList(this.f129e);
    }
}
