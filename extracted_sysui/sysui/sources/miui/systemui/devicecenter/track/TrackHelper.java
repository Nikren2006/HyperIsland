package miui.systemui.devicecenter.track;

import android.util.Log;
import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceInfo;
import f1.c;
import f1.o;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miuix.animation.internal.TransitionInfo;
import miuix.security.DigestUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackHelper {
    public static final TrackHelper INSTANCE = new TrackHelper();
    public static final String SPEAKER_GROUP = "speaker_group";
    public static final String TAG = "TrackHelper";
    public static final String TV_GROUP = "tv_group";

    private TrackHelper() {
    }

    private final String getMijiaDevice(DeviceInfo deviceInfo) {
        String privateData = deviceInfo.getPrivateData();
        if (privateData != null && privateData.length() != 0) {
            try {
                String string = new JSONObject(privateData).getString(DeviceInfo.Builder.PrivateDataKey.MODEL);
                if (n.c(deviceInfo.getCategory(), Constant.DeviceCategory.MIJIA)) {
                    n.d(string);
                    if (string.length() > 0) {
                        return (String) o.T(string, new String[]{"."}, false, 0, 6, null).get(1);
                    }
                }
            } catch (Exception e2) {
                String message = e2.getMessage();
                if (message == null) {
                    message = "-";
                }
                Log.i(TAG, message);
            }
        }
        return "";
    }

    private final int getState(int i2) {
        if ((i2 & 2) > 0) {
            return 2;
        }
        if ((i2 & 512) > 0) {
            return 512;
        }
        if ((i2 & 1024) > 0) {
            return 1024;
        }
        if ((i2 & 4) > 0) {
            return 4;
        }
        if ((i2 & 16) > 0) {
            return 16;
        }
        if ((i2 & 32) > 0) {
            return 32;
        }
        if ((i2 & 8) > 0) {
            return 8;
        }
        if ((i2 & 2048) > 0) {
            return 2048;
        }
        if ((i2 & 128) > 0) {
            return 128;
        }
        if ((i2 & 256) > 0) {
            return 256;
        }
        return (i2 & 64) > 0 ? 64 : -1;
    }

    private final String md5(String str) throws NoSuchAlgorithmException {
        if (str == null || str.length() == 0) {
            return "";
        }
        MessageDigest messageDigest = MessageDigest.getInstance(DigestUtils.ALGORITHM_MD5);
        byte[] bytes = str.getBytes(c.f4238b);
        n.f(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        System.out.println((Object) ("result" + bArrDigest.length));
        n.d(bArrDigest);
        return toHex(bArrDigest);
    }

    private final String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & TransitionInfo.INIT);
            if (hexString.length() == 1) {
                sb.append("0");
                sb.append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        String string = sb.toString();
        n.f(string, "with(...)");
        return string;
    }

    public final String getClassification(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        String deviceType = deviceInfoWrapper.getDeviceInfo().getDeviceType();
        if (n.c(deviceType, Constant.DeviceType.THIRD_HEADSET) ? true : n.c(deviceType, Constant.DeviceType.THIRD_ANDROID_TV)) {
            return "三方设备";
        }
        String category = deviceInfoWrapper.getDeviceInfo().getCategory();
        int iHashCode = category.hashCode();
        if (iHashCode != -1221262756) {
            if (iHashCode != -1049482625) {
                if (iHashCode == 103897062 && category.equals(Constant.DeviceCategory.MIJIA)) {
                    return "米家设备";
                }
            } else if (category.equals(Constant.DeviceCategory.NEARBY)) {
                return "附近设备";
            }
        } else if (category.equals(Constant.DeviceCategory.HEALTH)) {
            return "可穿戴设备";
        }
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:103:?, code lost:
    
        return "car";
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
    
        if (r0.equals("headset") == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ad, code lost:
    
        if (r0.equals("Car") == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c8, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.BLUETOOTH_CAR) == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0114, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.THIRD_HEADSET) == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:?, code lost:
    
        return "headset";
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0140 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getDevice(miui.systemui.devicecenter.devices.DeviceInfoWrapper r6) {
        /*
            Method dump skipped, instruction units count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecenter.track.TrackHelper.getDevice(miui.systemui.devicecenter.devices.DeviceInfoWrapper):java.lang.String");
    }

    public final String getDeviceModel(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        String privateData = deviceInfoWrapper.getDeviceInfo().getPrivateData();
        if (privateData != null && privateData.length() != 0) {
            try {
                String string = new JSONObject(privateData).getString(DeviceInfo.Builder.PrivateDataKey.MODEL);
                Log.i(TAG, "model: " + string + deviceInfoWrapper.getDeviceInfo().getTitle());
                if (!n.c(deviceInfoWrapper.getDeviceInfo().getCategory(), Constant.DeviceCategory.MIJIA)) {
                    n.d(string);
                    return string;
                }
                if (n.c(deviceInfoWrapper.getDeviceInfo().getCategory(), Constant.DeviceCategory.MIJIA)) {
                    n.d(string);
                    if (string.length() > 0) {
                        return (String) o.T(string, new String[]{"."}, false, 0, 6, null).get(2);
                    }
                }
            } catch (Exception e2) {
                String message = e2.getMessage();
                if (message == null) {
                    message = "-";
                }
                Log.i(TAG, message);
            }
        }
        return "";
    }

    public final String getDeviceStatus(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        int state = deviceInfoWrapper.getState();
        return state != 2 ? state != 4 ? state != 8 ? state != 16 ? state != 32 ? state != 128 ? state != 256 ? state != 512 ? state != 1024 ? state != 2048 ? "正常" : "视频流转" : "相机协同" : "副屏镜像" : "音频播放" : "音频流转" : "键鼠共享" : "电话协同" : "镜像投屏" : "相机协同" : "妙享桌面";
    }

    public final String getPlatFormNumber(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        return "pn_todo";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004d, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.SCREEN_SOUND) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
    
        if (r0.equals("headset") == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.SOUND) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.ANDROID_TV) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.ANDROID_PHONE) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0087, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.WINDOWS_PC) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0090, code lost:
    
        if (r0.equals(com.miui.circulate.device.api.Constant.DeviceType.ANDROID_PAD) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a3, code lost:
    
        return md5(r3.getId());
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return md5(r3.getDeviceInfo().getMac());
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getRefId(miui.systemui.devicecenter.devices.DeviceInfoWrapper r3) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecenter.track.TrackHelper.getRefId(miui.systemui.devicecenter.devices.DeviceInfoWrapper):java.lang.String");
    }

    public final String getSmartHomeDeviceType(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        return deviceInfoWrapper.getDeviceInfo().getId().length() == 0 ? "" : f1.n.s(deviceInfoWrapper.getDeviceInfo().getId(), "d:", false, 2, null) ? "单品" : f1.n.s(deviceInfoWrapper.getDeviceInfo().getId(), "c:", false, 2, null) ? "聚合" : f1.n.s(deviceInfoWrapper.getDeviceInfo().getId(), "mise:", false, 2, null) ? "批量控制" : "";
    }

    public final boolean isMijiaType(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        return n.c(deviceInfoWrapper.getDeviceInfo().getCategory(), Constant.DeviceCategory.MIJIA);
    }

    public final boolean isTvType(DeviceInfoWrapper deviceInfoWrapper) {
        n.g(deviceInfoWrapper, "<this>");
        return n.c(deviceInfoWrapper.getDeviceInfo().getDeviceType(), Constant.DeviceType.ANDROID_TV);
    }
}
