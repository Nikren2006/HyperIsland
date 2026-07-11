package E1;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class i implements Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f146d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f147e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f148f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float[] f149g;

    public static class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel, (a) null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i2) {
            return new i[i2];
        }
    }

    public /* synthetic */ i(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        i iVar = (i) obj;
        return this.f143a.equals(iVar.f143a) && this.f144b.equals(iVar.f144b) && this.f145c == iVar.f145c && this.f146d == iVar.f146d && this.f147e == iVar.f147e && this.f148f == iVar.f148f && Arrays.equals(this.f149g, iVar.f149g);
    }

    public boolean q() {
        return this.f145c;
    }

    public i r(boolean z2) {
        this.f145c = z2;
        return this;
    }

    public i s(int i2, float f2) {
        if (i2 >= 0 && i2 < 7) {
            this.f149g[i2] = f2;
            return this;
        }
        throw new IllegalArgumentException(Integer.toString(i2) + " is not a valid eq band");
    }

    public i t(boolean z2) {
        if (z2 && !this.f144b.f133d) {
            throw new IllegalArgumentException("filter do not support EQ");
        }
        this.f148f = z2;
        return this;
    }

    public i u(boolean z2) {
        this.f146d = z2;
        return this;
    }

    public i v(boolean z2) {
        if (z2 && !this.f144b.f132c) {
            throw new IllegalArgumentException("filter do not support SFX");
        }
        this.f147e = z2;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f143a, 0);
        parcel.writeBooleanArray(new boolean[]{this.f145c, this.f146d, this.f147e, this.f148f});
        parcel.writeFloatArray(this.f149g);
        parcel.writeParcelable(this.f144b, 0);
    }

    public i(d dVar, e eVar) {
        this.f149g = new float[7];
        if (dVar == null) {
            throw new IllegalArgumentException("Not a valid device");
        }
        this.f143a = dVar;
        if (eVar == null || !dVar.f129e.contains(eVar)) {
            throw new IllegalArgumentException("invalid filter");
        }
        this.f144b = eVar;
    }

    public i(Parcel parcel) {
        float[] fArr = new float[7];
        this.f149g = fArr;
        try {
            d dVar = (d) parcel.readParcelable(d.class.getClassLoader());
            this.f143a = dVar;
            if (dVar != null) {
                boolean[] zArr = new boolean[4];
                parcel.readBooleanArray(zArr);
                this.f145c = zArr[0];
                this.f146d = zArr[1];
                this.f147e = zArr[2];
                this.f148f = zArr[3];
                parcel.readFloatArray(fArr);
                this.f144b = (e) parcel.readParcelable(e.class.getClassLoader());
                return;
            }
            throw new BadParcelableException("No valid device in parcel");
        } catch (BadParcelableException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new BadParcelableException(e3);
        }
    }
}
