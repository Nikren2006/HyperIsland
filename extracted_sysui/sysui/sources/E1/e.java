package E1;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f133d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f134e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f135f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f136g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f137h;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return e.r(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i2) {
            return new e[i2];
        }
    }

    public e(long j2, String str, String str2, j jVar, boolean z2, boolean z3, int i2, String str3) {
        this.f130a = j2;
        this.f134e = str;
        this.f135f = str2;
        this.f131b = jVar;
        this.f132c = z2;
        this.f133d = z3;
        this.f136g = i2;
        this.f137h = str3;
    }

    public static e r(Parcel parcel) {
        try {
            long j2 = parcel.readLong();
            String string = parcel.readString();
            String string2 = parcel.readString();
            j jVarA = j.a(parcel.readInt());
            boolean z2 = true;
            boolean z3 = parcel.readByte() != 0;
            if (parcel.readByte() == 0) {
                z2 = false;
            }
            return new e(j2, string, string2, jVarA, z3, z2, parcel.readInt(), parcel.readString());
        } catch (BadParcelableException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new BadParcelableException(e3);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 2;
    }

    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) && this.f130a == ((e) obj).f130a;
    }

    public int hashCode() {
        return Long.valueOf(this.f130a).hashCode();
    }

    public String toString() {
        return this.f134e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f130a);
        parcel.writeString(this.f134e);
        parcel.writeString(this.f135f);
        parcel.writeInt(this.f131b.b());
        parcel.writeByte(this.f132c ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f133d ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f136g);
        parcel.writeString(this.f137h);
    }
}
