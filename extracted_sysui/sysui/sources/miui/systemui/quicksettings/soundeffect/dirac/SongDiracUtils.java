package miui.systemui.quicksettings.soundeffect.dirac;

import E1.c;
import E1.d;
import E1.e;
import E1.h;
import E1.i;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.util.Log;
import android.util.Pair;
import com.miui.circulate.device.api.Column;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SongDiracUtils extends DiracUtils {
    public static final String ACTION_SERVICE_CONNECTED = "miui.intent.action.DiracServiceConnected";
    private static final String TAG = "SongDiracUtils";
    private c mDiracService;
    private boolean mMainThread;
    private Object mPauseLock = new Object();
    private SharedPreferences mSp;
    private int mWaitingConnected;

    public class Connection extends c.AbstractC0006c {
        public Connection() {
        }

        @Override // E1.c.AbstractC0006c
        public void onServiceConnected(c cVar) {
            Log.i(SongDiracUtils.TAG, "onServiceConnected");
            if (SongDiracUtils.this.mDiracService == null) {
                SongDiracUtils.this.mDiracService = cVar;
                Log.i(SongDiracUtils.TAG, "onServiceConnected, sendBroadcast ...");
                Intent intent = new Intent(SongDiracUtils.ACTION_SERVICE_CONNECTED);
                Application applicationCurrentApplication = SongDiracUtils.currentApplication();
                if (applicationCurrentApplication != null) {
                    applicationCurrentApplication.sendBroadcast(intent);
                }
            }
            if (SongDiracUtils.this.mWaitingConnected != 0) {
                if (SongDiracUtils.this.mMainThread) {
                    throw new RuntimeException();
                }
                synchronized (SongDiracUtils.this.mPauseLock) {
                    SongDiracUtils.this.mPauseLock.notifyAll();
                }
            }
        }

        @Override // E1.c.AbstractC0006c
        public void onServiceDisconnected() {
            Log.i(SongDiracUtils.TAG, "onServiceDisconnected");
            SongDiracUtils.this.mDiracService = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Application currentApplication() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", null).invoke(null, null);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    private c getDiracService(Context context) {
        if (this.mDiracService == null) {
            Log.i(TAG, "initialize+");
            try {
                try {
                    synchronized (this.mPauseLock) {
                        try {
                            this.mMainThread = Process.myPid() == Process.myTid();
                            this.mWaitingConnected++;
                            if (!c.l(context, new Connection())) {
                                throw new RuntimeException("could not bind against the control service");
                            }
                            Log.i(TAG, "wait for connection");
                            if (!this.mMainThread) {
                                this.mPauseLock.wait();
                            }
                        } finally {
                        }
                    }
                } catch (RuntimeException unused) {
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                c cVar = this.mDiracService;
                if (cVar != null && cVar.r(h.EXTERNAL) == null) {
                    Log.i(TAG, "disable dirac by default");
                    setDevice(this.mDiracService, 0L, false);
                }
                Log.i(TAG, "initialize-");
            } finally {
                this.mWaitingConnected--;
            }
        }
        return this.mDiracService;
    }

    private static void setDevice(c cVar, long j2, boolean z2) {
        List list;
        d dVarN = cVar.n(j2);
        if (dVarN == null || (list = dVarN.f129e) == null || list.size() <= 0) {
            Log.i(TAG, "setDevice invalid device");
            return;
        }
        i iVar = new i(dVarN, (e) dVarN.f129e.get(0));
        iVar.r(z2);
        iVar.t(true);
        iVar.u(true);
        iVar.v(false);
        cVar.z(iVar);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public List<Pair<Integer, Integer>> getHeadseIdsAndTypes() {
        Log.i(TAG, "getHeadseIdsAndTypes");
        ArrayList arrayList = new ArrayList();
        c diracService = getDiracService(currentApplication());
        if (diracService == null) {
            return arrayList;
        }
        for (d dVar : diracService.u(h.EXTERNAL)) {
            if ("MEP 100".equals(dVar.f126b)) {
                arrayList.add(new Pair(0, Integer.valueOf((int) dVar.f125a)));
            } else if ("MEP 200".equals(dVar.f126b)) {
                arrayList.add(new Pair(1, Integer.valueOf((int) dVar.f125a)));
            } else if ("Piston 100".equals(dVar.f126b)) {
                arrayList.add(new Pair(2, Integer.valueOf((int) dVar.f125a)));
            } else if ("General Earbud".equals(dVar.f126b)) {
                arrayList.add(new Pair(6, Integer.valueOf((int) dVar.f125a)));
            } else if ("General InEar".equals(dVar.f126b)) {
                arrayList.add(new Pair(7, Integer.valueOf((int) dVar.f125a)));
            } else if ("MK 101".equals(dVar.f126b)) {
                arrayList.add(new Pair(4, Integer.valueOf((int) dVar.f125a)));
            } else if ("MK 301".equals(dVar.f126b)) {
                arrayList.add(new Pair(5, Integer.valueOf((int) dVar.f125a)));
            } else if ("MK 303".equals(dVar.f126b)) {
                arrayList.add(new Pair(8, Integer.valueOf((int) dVar.f125a)));
            } else if ("MO 701".equals(dVar.f126b)) {
                arrayList.add(new Pair(9, Integer.valueOf((int) dVar.f125a)));
            } else if ("MR 102".equals(dVar.f126b)) {
                arrayList.add(new Pair(10, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 303".equals(dVar.f126b)) {
                arrayList.add(new Pair(11, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 304".equals(dVar.f126b)) {
                arrayList.add(new Pair(12, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 001".equals(dVar.f126b)) {
                arrayList.add(new Pair(13, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 007".equals(dVar.f126b)) {
                arrayList.add(new Pair(14, Integer.valueOf((int) dVar.f125a)));
            } else if ("HM 004".equals(dVar.f126b)) {
                arrayList.add(new Pair(15, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 013".equals(dVar.f126b)) {
                arrayList.add(new Pair(17, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 015".equals(dVar.f126b)) {
                arrayList.add(new Pair(18, Integer.valueOf((int) dVar.f125a)));
            } else if ("EM 017".equals(dVar.f126b)) {
                arrayList.add(new Pair(19, Integer.valueOf((int) dVar.f125a)));
            } else {
                Log.i(TAG, "unknown device: name = " + dVar.f126b + " id = " + dVar.f125a);
            }
        }
        return arrayList;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public int getHeadsetType(Context context) {
        i iVarR;
        Log.i(TAG, "getHeadsetType");
        c diracService = getDiracService(context);
        if (diracService != null && (iVarR = diracService.r(h.EXTERNAL)) != null) {
            return (int) iVarR.f143a.f125a;
        }
        if (this.mSp == null) {
            this.mSp = context.getSharedPreferences("device_id", 0);
        }
        SharedPreferences sharedPreferences = this.mSp;
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt(Column.ID, 0);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean isEnabled(Context context) {
        i iVarR;
        Log.i(TAG, "isEnabled");
        c diracService = getDiracService(context);
        if (diracService == null || (iVarR = diracService.r(h.EXTERNAL)) == null) {
            return false;
        }
        return iVarR.q();
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setEnabled(Context context, boolean z2) {
        Log.i(TAG, "setEnabled: " + z2);
        c diracService = getDiracService(context);
        if (diracService != null) {
            if (this.mSp == null) {
                this.mSp = context.getSharedPreferences("device_id", 0);
            }
            if (!z2) {
                int headsetType = getHeadsetType(context);
                SharedPreferences.Editor editorEdit = this.mSp.edit();
                editorEdit.putInt(Column.ID, headsetType);
                editorEdit.apply();
                diracService.x(h.EXTERNAL);
                return;
            }
            h hVar = h.EXTERNAL;
            if (diracService.r(hVar) == null) {
                setDevice(diracService, this.mSp.getInt(Column.ID, 0), false);
            }
            i iVarR = diracService.r(hVar);
            if (iVarR != null) {
                iVarR.r(z2);
                diracService.z(iVarR);
            }
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHeadsetType(Context context, int i2) {
        Log.i(TAG, "setHeadsetType: " + i2);
        c diracService = getDiracService(context);
        if (diracService != null) {
            setDevice(diracService, i2, true);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setLevel(Context context, int i2, float f2) {
        i iVarR;
        Log.i(TAG, "setLevel: " + i2 + " = " + f2);
        c diracService = getDiracService(context);
        if (diracService == null || (iVarR = diracService.r(h.EXTERNAL)) == null) {
            return;
        }
        iVarR.s(i2, f2);
        diracService.z(iVarR);
    }
}
