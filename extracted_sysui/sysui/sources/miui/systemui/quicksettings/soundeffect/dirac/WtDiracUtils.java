package miui.systemui.quicksettings.soundeffect.dirac;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.util.Pair;
import com.miui.miplay.audio.data.DeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class WtDiracUtils extends DiracUtils {
    static final String TAG = "WtDiracUtils";
    private static final int VAL_MK301 = 6;
    private static List<Pair<Integer, Integer>> sHeadsetIdsAndTypes;

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public List<Pair<Integer, Integer>> getHeadseIdsAndTypes() {
        if (sHeadsetIdsAndTypes == null) {
            ArrayList arrayListNewArrayList = DiracUtils.newArrayList();
            sHeadsetIdsAndTypes = arrayListNewArrayList;
            arrayListNewArrayList.add(new Pair(6, 5));
            sHeadsetIdsAndTypes.add(new Pair<>(4, 7));
            sHeadsetIdsAndTypes.add(new Pair<>(2, 3));
            sHeadsetIdsAndTypes.add(new Pair<>(5, 6));
            sHeadsetIdsAndTypes.add(new Pair<>(0, 1));
            sHeadsetIdsAndTypes.add(new Pair<>(1, 2));
        }
        return sHeadsetIdsAndTypes;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public int getHeadsetType(Context context) {
        String parameters = ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getParameters("dirac");
        Log.i(TAG, "get parameter " + parameters);
        String value = DiracUtils.getValue(parameters);
        if (value != null) {
            try {
                return Integer.valueOf(value).intValue();
            } catch (NumberFormatException e2) {
                Log.e(TAG, "refreshDiracState", e2);
            }
        }
        return 5;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public boolean isEnabled(Context context) {
        int iIntValue;
        String parameters = ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).getParameters("dirac_enabled");
        Log.i(TAG, "get parameter " + parameters);
        String value = DiracUtils.getValue(parameters);
        if (value != null) {
            try {
                iIntValue = Integer.valueOf(value).intValue();
            } catch (NumberFormatException e2) {
                Log.e(TAG, "refreshDiracState", e2);
                iIntValue = 0;
            }
        } else {
            iIntValue = 0;
        }
        return iIntValue == 1;
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setEnabled(Context context, boolean z2) {
        Log.i(TAG, "set dirac enabled : " + z2);
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        String parameter = DiracUtils.toParameter("dirac_enabled", z2 ? 1 : 0);
        Log.i(TAG, "set parameter " + parameter);
        audioManager.setParameters(parameter);
    }

    @Override // miui.systemui.quicksettings.soundeffect.dirac.DiracUtils
    public void setHeadsetType(Context context, int i2) {
        Log.i(TAG, "set headset type: " + i2);
        if (!DiracUtils.isHeadsetType(i2)) {
            throw new IllegalArgumentException("bad value, value=" + i2);
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        String parameter = DiracUtils.toParameter("dirac", i2);
        Log.i(TAG, "set parameter " + parameter);
        audioManager.setParameters(parameter);
    }
}
