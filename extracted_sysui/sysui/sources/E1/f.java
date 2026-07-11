package E1;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface f extends IInterface {

    public static abstract class a extends Binder implements f {

        /* JADX INFO: renamed from: E1.f$a$a, reason: collision with other inner class name */
        public static class C0007a implements f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f138a;

            public C0007a(IBinder iBinder) {
                this.f138a = iBinder;
            }

            @Override // E1.f
            public i I(h hVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    if (hVar != null) {
                        parcelObtain.writeInt(1);
                        hVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f138a.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    i iVarCreateFromParcel = parcelObtain2.readInt() != 0 ? i.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return iVarCreateFromParcel;
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // E1.f
            public void Q0(h hVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    if (hVar != null) {
                        parcelObtain.writeInt(1);
                        hVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f138a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // E1.f
            public void S(g gVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.f138a.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // E1.f
            public boolean Y(i iVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    if (iVar != null) {
                        parcelObtain.writeInt(1);
                        iVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f138a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    boolean z2 = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f138a;
            }

            @Override // E1.f
            public List p0(String str, h hVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    parcelObtain.writeString(str);
                    if (hVar != null) {
                        parcelObtain.writeInt(1);
                        hVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f138a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    ArrayList arrayListCreateTypedArrayList = parcelObtain2.createTypedArrayList(d.CREATOR);
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return arrayListCreateTypedArrayList;
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // E1.f
            public d u(long j2, String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeString(str);
                    this.f138a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? d.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // E1.f
            public void x0(g gVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("se.dirac.acs.api.IAudioControlService");
                    parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.f138a.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public static f Z0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("se.dirac.acs.api.IAudioControlService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new C0007a(iBinder) : (f) iInterfaceQueryLocalInterface;
        }
    }

    i I(h hVar);

    void Q0(h hVar);

    void S(g gVar);

    boolean Y(i iVar);

    List p0(String str, h hVar);

    d u(long j2, String str);

    void x0(g gVar);
}
