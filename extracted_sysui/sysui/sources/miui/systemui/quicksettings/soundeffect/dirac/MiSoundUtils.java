package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MiSoundUtils extends DiracUtils {
    static final String TAG = "MiSoundUtils";
    private static List<Pair<Integer, Integer>> sHeadsetIdsAndTypes;
    private boolean mInitialized;
    private MiSoundWrapper mMiSound;
    private Map<Integer, Integer> sHeadsetIdsAndTypesMap;

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public List<Pair<Integer, Integer>> getHeadseIdsAndTypes() {
        if (sHeadsetIdsAndTypes == null) {
            sHeadsetIdsAndTypes = DiracUtils.newArrayList();
            String str = Build.DEVICE;
            if (!str.equals("picasso") && !str.equals("andromeda") && !str.equals("crux") && !str.equals("monet") && !str.equals("monetin") && !str.equals("raphael") && !str.equals("vangogh") && !str.equals("phoenix") && !str.equals("phoenixin") && !str.equals("toco") && !str.equals("tucana") && !str.equals("davinciin") && !str.equals("davinci") && !str.equals("atom") && !str.equals("bomb") && !str.equals("cezanne") && !str.equals("cannon") && !str.equals("cannong") && !str.equals("cepheus")) {
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM035")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(26, 26));
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM037")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(27, 27));
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM031")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(24, 24));
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM033")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(25, 25));
                }
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM017")) {
                sHeadsetIdsAndTypes.add(new Pair<>(19, 21));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM015")) {
                sHeadsetIdsAndTypes.add(new Pair<>(18, 20));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM013") && !DiracUtils.isA1()) {
                sHeadsetIdsAndTypes.add(new Pair<>(17, 19));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM006") && DiracUtils.isSupportTypeC() && !DiracUtils.isA1()) {
                sHeadsetIdsAndTypes.add(new Pair<>(16, 18));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_HM004")) {
                sHeadsetIdsAndTypes.add(new Pair<>(15, 17));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM007")) {
                sHeadsetIdsAndTypes.add(new Pair<>(14, 16));
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM001")) {
                sHeadsetIdsAndTypes.add(new Pair<>(13, 15));
            }
            sHeadsetIdsAndTypes.add(new Pair<>(12, 14));
            sHeadsetIdsAndTypes.add(new Pair<>(11, 13));
            sHeadsetIdsAndTypes.add(new Pair<>(10, 11));
            sHeadsetIdsAndTypes.add(new Pair<>(9, 10));
            sHeadsetIdsAndTypes.add(new Pair<>(8, 9));
            sHeadsetIdsAndTypes.add(new Pair<>(4, 7));
            sHeadsetIdsAndTypes.add(new Pair<>(5, 8));
            sHeadsetIdsAndTypes.add(new Pair<>(2, 3));
            sHeadsetIdsAndTypes.add(new Pair<>(1, 2));
            sHeadsetIdsAndTypes.add(new Pair<>(0, 1));
            if (MusicRegionUtils.isIndiaVersion()) {
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM018")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(20, 22));
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM019")) {
                    sHeadsetIdsAndTypes.add(new Pair<>(21, 23));
                }
            }
        }
        return sHeadsetIdsAndTypes;
    }

    public int[] getHeadsetList() {
        return this.mMiSound.getHeadsetList();
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public int getHeadsetType(Context context) {
        return this.mMiSound.getHeadsetType();
    }

    public int getScenario() {
        MiSoundWrapper miSoundWrapper = this.mMiSound;
        if (miSoundWrapper != null) {
            return miSoundWrapper.getScenario();
        }
        Log.d(TAG, "mMiSound == null");
        return -1;
    }

    public Map<Integer, Integer> getsHeadsetIdsAndTypesMap() {
        if (this.sHeadsetIdsAndTypesMap == null) {
            this.sHeadsetIdsAndTypesMap = new HashMap();
            String str = Build.DEVICE;
            if (!str.equals("picasso") && !str.equals("andromeda") && !str.equals("crux") && !str.equals("merlin") && !str.equals("citrus") && !str.equals("lancelot") && !str.equals("monet") && !str.equals("monetin") && !str.equals("raphael") && !str.equals("vangogh") && !str.equals("phoenix") && !str.equals("phoenixin") && !str.equals("toco") && !str.equals("tucana") && !str.equals("davinciin") && !str.equals("davinci") && !str.equals("atom") && !str.equals("bomb") && !str.equals("cezanne") && !str.equals("cannon") && !str.equals("cannong") && !str.equals("cepheus") && !str.equals("lime") && !str.equals("dandelion") && !str.equals("rosemary") && !str.equals("secret") && !str.equals("maltose") && !str.equals("dandelion_global") && !str.equals("dandelion_in_global") && !str.equals("camellia") && !str.equals("camellian") && !str.equals("vayu") && !str.equals("begonia") && !str.equals("begoniain") && !str.equals("dandelion_ru_global") && !str.equals("shiva")) {
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM035")) {
                    this.sHeadsetIdsAndTypesMap.put(26, 26);
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM037")) {
                    this.sHeadsetIdsAndTypesMap.put(27, 27);
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM031")) {
                    this.sHeadsetIdsAndTypesMap.put(24, 24);
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM033")) {
                    this.sHeadsetIdsAndTypesMap.put(25, 25);
                }
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM017")) {
                this.sHeadsetIdsAndTypesMap.put(19, 21);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM015")) {
                this.sHeadsetIdsAndTypesMap.put(18, 20);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM013") && !DiracUtils.isA1()) {
                this.sHeadsetIdsAndTypesMap.put(17, 19);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM006") && DiracUtils.isSupportTypeC() && !DiracUtils.isA1()) {
                this.sHeadsetIdsAndTypesMap.put(16, 18);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_HM004")) {
                this.sHeadsetIdsAndTypesMap.put(15, 17);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM007")) {
                this.sHeadsetIdsAndTypesMap.put(14, 16);
            }
            if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM001")) {
                this.sHeadsetIdsAndTypesMap.put(13, 15);
            }
            this.sHeadsetIdsAndTypesMap.put(12, 14);
            this.sHeadsetIdsAndTypesMap.put(11, 13);
            this.sHeadsetIdsAndTypesMap.put(10, 11);
            this.sHeadsetIdsAndTypesMap.put(9, 10);
            this.sHeadsetIdsAndTypesMap.put(8, 9);
            this.sHeadsetIdsAndTypesMap.put(4, 7);
            this.sHeadsetIdsAndTypesMap.put(5, 8);
            this.sHeadsetIdsAndTypesMap.put(2, 3);
            this.sHeadsetIdsAndTypesMap.put(1, 2);
            this.sHeadsetIdsAndTypesMap.put(0, 1);
            if (MusicRegionUtils.isIndiaVersion()) {
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM018")) {
                    this.sHeadsetIdsAndTypesMap.put(20, 22);
                }
                if (this.mMiSound.isSupportType("MISOUND_HEADSET_EM019")) {
                    this.sHeadsetIdsAndTypesMap.put(21, 23);
                }
            }
        }
        return this.sHeadsetIdsAndTypesMap;
    }

    public boolean hasControl() {
        if (this.mMiSound == null) {
            Log.e(TAG, "hasControl: mMiSound == null");
            this.mInitialized = false;
            initialize();
        }
        return this.mMiSound.hasControl();
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean hasInitialized() {
        return this.mInitialized;
    }

    public void initMiSoundIfNeed() {
        if (hasControl()) {
            return;
        }
        Log.d(TAG, "initMiSoundIfNeed: init");
        this.mMiSound.release();
        this.mInitialized = false;
        initialize();
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void initialize() {
        Log.i(TAG, "initialize, mInitialized=" + this.mInitialized);
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        this.mMiSound = new MiSoundWrapper(0, 0);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean isEnabled(Context context) {
        return this.mMiSound.getMusic() == 1;
    }

    public boolean isMiSoundNull() {
        return this.mMiSound == null;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void release() {
        Log.i(TAG, "release, mInitialized=" + this.mInitialized);
        if (this.mInitialized) {
            this.mMiSound.release();
            this.mMiSound = null;
            this.mInitialized = false;
        }
    }

    public void setEffectEnable(boolean z2) {
        initMiSoundIfNeed();
        this.mMiSound.setEnable(z2);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setEnabled(Context context, boolean z2) {
        Log.i(TAG, "set dirac enabled: " + z2);
        MiSoundWrapper miSoundWrapper = this.mMiSound;
        if (miSoundWrapper != null) {
            miSoundWrapper.setMusic(z2 ? 1 : 0);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHeadsetType(Context context, int i2) {
        Log.i(TAG, "set headset type: " + i2);
        if (!DiracUtils.isHeadsetType(i2)) {
            throw new IllegalArgumentException("bad value, value=" + i2);
        }
        MiSoundWrapper miSoundWrapper = this.mMiSound;
        if (miSoundWrapper != null) {
            miSoundWrapper.setHeadsetType(i2);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHifiMode(int i2) {
        Log.i(TAG, "set hifi mode: " + i2);
        MiSoundWrapper miSoundWrapper = this.mMiSound;
        if (miSoundWrapper != null) {
            miSoundWrapper.setHifiMode(i2);
        }
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setLevel(Context context, int i2, float f2) {
        Log.i(TAG, "set EQ Levle: " + DiracUtils.formatStd("diracband=%d;value=%f", Integer.valueOf(i2), Float.valueOf(f2)));
        MiSoundWrapper miSoundWrapper = this.mMiSound;
        if (miSoundWrapper != null) {
            miSoundWrapper.setLevel(i2, f2);
        }
    }

    public void setMovieModeEnable(int i2) {
        this.mMiSound.setMovieModeEnable(i2);
    }

    public void setMovieSurroundLevel(int i2) {
        this.mMiSound.setMovieSurroundLevel(i2);
    }

    public void setMovieVocalLevel(int i2) {
        this.mMiSound.setMovieVocalLevel(i2);
    }

    public void setScenario(int i2) {
        this.mMiSound.setScenario(i2);
    }
}
