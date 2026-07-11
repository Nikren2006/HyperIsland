package miuix.theme.token;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes5.dex */
public class MaterialDayNightToken implements Parcelable {
    public static final Parcelable.Creator<MaterialDayNightToken> CREATOR = new Parcelable.Creator<MaterialDayNightToken>() { // from class: miuix.theme.token.MaterialDayNightToken.1
        @Override // android.os.Parcelable.Creator
        public MaterialDayNightToken createFromParcel(Parcel parcel) {
            return new MaterialDayNightToken(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MaterialDayNightToken[] newArray(int i2) {
            return new MaterialDayNightToken[i2];
        }
    };
    private final MaterialToken mDarkToken;
    private final MaterialToken mDefaultToken;

    public MaterialDayNightToken(Parcel parcel) {
        int i2 = parcel.readInt();
        if (i2 < 1) {
            this.mDefaultToken = null;
            this.mDarkToken = null;
        } else if (i2 == 1) {
            this.mDefaultToken = new MaterialToken(parcel);
            this.mDarkToken = null;
        } else {
            this.mDefaultToken = new MaterialToken(parcel);
            this.mDarkToken = new MaterialToken(parcel);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MaterialToken getToken(boolean z2) {
        MaterialToken materialToken = this.mDarkToken;
        return (materialToken == null || z2) ? this.mDefaultToken : materialToken;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        MaterialToken materialToken = this.mDefaultToken;
        if (materialToken != null && this.mDarkToken != null) {
            parcel.writeInt(2);
            this.mDefaultToken.writeToParcel(parcel, i2);
            this.mDarkToken.writeToParcel(parcel, i2);
        } else if (materialToken != null) {
            parcel.writeInt(1);
            this.mDefaultToken.writeToParcel(parcel, i2);
        } else if (this.mDarkToken == null) {
            parcel.writeInt(0);
        }
    }

    public MaterialDayNightToken(MaterialToken materialToken) {
        this.mDefaultToken = materialToken;
        this.mDarkToken = null;
    }

    public MaterialDayNightToken(MaterialToken materialToken, MaterialToken materialToken2) {
        this.mDefaultToken = materialToken;
        this.mDarkToken = materialToken2;
    }
}
