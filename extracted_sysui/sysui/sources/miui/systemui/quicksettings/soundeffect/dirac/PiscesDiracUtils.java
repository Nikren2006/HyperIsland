package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PiscesDiracUtils extends DiracUtils {
    static final String TAG = "PiscesDiracUtils";
    private static List<Pair<Integer, Integer>> sHeadsetIdsAndTypes;
    private DiracSoundWrapper mDiracSound;
    private boolean mInitialized;

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public List<Pair<Integer, Integer>> getHeadseIdsAndTypes() {
        if (sHeadsetIdsAndTypes == null) {
            ArrayList arrayListNewArrayList = DiracUtils.newArrayList();
            sHeadsetIdsAndTypes = arrayListNewArrayList;
            arrayListNewArrayList.add(new Pair(6, 5));
            sHeadsetIdsAndTypes.add(new Pair<>(7, 6));
            sHeadsetIdsAndTypes.add(new Pair<>(0, 1));
            sHeadsetIdsAndTypes.add(new Pair<>(1, 2));
            sHeadsetIdsAndTypes.add(new Pair<>(2, 3));
            sHeadsetIdsAndTypes.add(new Pair<>(5, 8));
            sHeadsetIdsAndTypes.add(new Pair<>(4, 7));
            sHeadsetIdsAndTypes.add(new Pair<>(8, 9));
            sHeadsetIdsAndTypes.add(new Pair<>(9, 10));
            sHeadsetIdsAndTypes.add(new Pair<>(10, 11));
            sHeadsetIdsAndTypes.add(new Pair<>(11, 13));
            sHeadsetIdsAndTypes.add(new Pair<>(12, 14));
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM001")) {
                sHeadsetIdsAndTypes.add(new Pair<>(13, 15));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM007")) {
                sHeadsetIdsAndTypes.add(new Pair<>(14, 16));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_HM004")) {
                sHeadsetIdsAndTypes.add(new Pair<>(15, 17));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM006") && DiracUtils.isSupportTypeC()) {
                sHeadsetIdsAndTypes.add(new Pair<>(16, 18));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM013")) {
                sHeadsetIdsAndTypes.add(new Pair<>(17, 19));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM015")) {
                sHeadsetIdsAndTypes.add(new Pair<>(18, 20));
            }
            if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM017")) {
                sHeadsetIdsAndTypes.add(new Pair<>(19, 21));
            }
            if (MusicRegionUtils.isIndiaVersion()) {
                if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM018")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(20, 22));
                }
                if (this.mDiracSound.isSupportType("DIRACSOUND_HEADSET_EM019")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(21, 23));
                }
            }
        }
        return sHeadsetIdsAndTypes;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public int getHeadsetType(Context context) {
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        int headsetType = diracSoundWrapper != null ? diracSoundWrapper.getHeadsetType() : 0;
        Log.i(TAG, "getHeadsetType: " + headsetType);
        return headsetType;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean hasInitialized() {
        return this.mInitialized;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void initialize() {
        Log.i(TAG, "initialize, mInitialized=" + this.mInitialized);
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        this.mDiracSound = new DiracSoundWrapper(0, 0);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean isEnabled(Context context) {
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        boolean z2 = false;
        if (diracSoundWrapper != null && diracSoundWrapper.getMusic() == 1) {
            z2 = true;
        }
        Log.i(TAG, "isEnable: " + z2);
        return z2;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void release() {
        Log.i(TAG, "release, mInitialized=" + this.mInitialized);
        if (this.mInitialized) {
            this.mDiracSound.release();
            this.mDiracSound = null;
            this.mInitialized = false;
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setEnabled(Context context, boolean z2) {
        Log.i(TAG, "set dirac enabled: " + z2);
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        if (diracSoundWrapper != null) {
            diracSoundWrapper.setMusic(z2 ? 1 : 0);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHeadsetType(Context context, int i2) {
        Log.i(TAG, "set headset type: " + i2);
        if (!DiracUtils.isHeadsetType(i2)) {
            throw new IllegalArgumentException("bad value, value=" + i2);
        }
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        if (diracSoundWrapper != null) {
            diracSoundWrapper.setHeadsetType(i2);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHifiMode(int i2) {
        Log.i(TAG, "set hifi mode: " + i2);
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        if (diracSoundWrapper != null) {
            diracSoundWrapper.setHifiMode(i2);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setLevel(Context context, int i2, float f2) {
        Log.i(TAG, "set EQ Levle: " + DiracUtils.formatStd("diracband=%d;value=%f", Integer.valueOf(i2), Float.valueOf(f2)));
        DiracSoundWrapper diracSoundWrapper = this.mDiracSound;
        if (diracSoundWrapper != null) {
            diracSoundWrapper.setLevel(i2, f2);
        }
    }
}
