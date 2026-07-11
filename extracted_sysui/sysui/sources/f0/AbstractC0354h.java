package f0;

import android.util.Log;
import com.mi.widget.core.ShaderStrategy;
import miuix.device.DeviceUtils;

/* JADX INFO: renamed from: f0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0354h {
    public static final ShaderStrategy a() {
        ShaderStrategy shaderStrategy;
        int iA = g0.h.a("debug.shader.strategy.island", 0);
        if (iA == 1) {
            return ShaderStrategy.AGSL;
        }
        if (iA == 2) {
            return ShaderStrategy.DRAWABLE;
        }
        if (iA == 3) {
            return ShaderStrategy.NONE;
        }
        switch (DeviceUtils.getComputilityLevel()) {
            case 1:
            case 2:
                shaderStrategy = ShaderStrategy.NONE;
                break;
            case 3:
            case 4:
                shaderStrategy = ShaderStrategy.DRAWABLE;
                break;
            case 5:
            case 6:
                shaderStrategy = ShaderStrategy.AGSL;
                break;
            default:
                Log.e("ShaderStrategy", "Unknown computility level : " + DeviceUtils.getComputilityLevel() + ". Use default 4");
                shaderStrategy = ShaderStrategy.DRAWABLE;
                break;
        }
        Log.i("ShaderStrategy", "Current Platform Shader Strategy level=" + DeviceUtils.getComputilityLevel() + " strategy=" + shaderStrategy);
        return shaderStrategy;
    }
}
