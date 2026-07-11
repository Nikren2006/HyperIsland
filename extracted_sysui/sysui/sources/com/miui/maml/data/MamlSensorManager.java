package com.miui.maml.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.ArrayMap;
import com.miui.maml.util.MamlLog;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class MamlSensorManager {
    private static final int INVALID_TYPE = -1;
    private static final String TAG = "MAML_SensorManager";
    private static ArrayMap<String, MamlSensor> sSensors = new ArrayMap<>();
    private static final Object sLock = new Object();

    public static class MamlSensor {
        private int mRate;
        private boolean mRegistered;
        private Sensor mSensor;
        private SensorManager mSensorManager;
        private int mType;
        private WeakHashMap<SensorEventListener, Integer> mCallbacks = new WeakHashMap<>();
        private final Object mLock = new Object();
        private SensorEventListener mListener = new SensorEventListener() { // from class: com.miui.maml.data.MamlSensorManager.MamlSensor.1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i2) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                synchronized (MamlSensor.this.mLock) {
                    try {
                        Iterator it = MamlSensor.this.mCallbacks.keySet().iterator();
                        while (it.hasNext()) {
                            ((SensorEventListener) it.next()).onSensorChanged(sensorEvent);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };

        public MamlSensor(Context context, int i2, int i3) {
            if (i2 == -1) {
                MamlLog.e(MamlSensorManager.TAG, "Wront sensor type: " + i2);
                return;
            }
            this.mType = i2;
            this.mRate = i3;
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            this.mSensorManager = sensorManager;
            Sensor defaultSensor = sensorManager.getDefaultSensor(i2);
            this.mSensor = defaultSensor;
            if (defaultSensor == null) {
                MamlLog.e(MamlSensorManager.TAG, "Fail to get sensor! TYPE: " + this.mType);
            }
        }

        private boolean registerListener() {
            Sensor sensor = this.mSensor;
            if (sensor != null && !this.mRegistered) {
                try {
                    this.mRegistered = this.mSensorManager.registerListener(this.mListener, sensor, this.mRate);
                    MamlLog.d(MamlSensorManager.TAG, "registerListener " + this.mType);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return this.mRegistered;
        }

        private void unRegisterListener() {
            Sensor sensor = this.mSensor;
            if (sensor == null || !this.mRegistered) {
                return;
            }
            try {
                this.mSensorManager.unregisterListener(this.mListener, sensor);
                this.mRegistered = false;
                MamlLog.d(MamlSensorManager.TAG, "unregisterListener " + this.mType);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void addCallback(int i2, SensorEventListener sensorEventListener) {
            synchronized (this.mLock) {
                this.mCallbacks.put(sensorEventListener, Integer.valueOf(i2));
            }
            if (this.mRate < i2) {
                this.mRate = i2;
                if (this.mRegistered) {
                    unRegisterListener();
                }
            }
            registerListener();
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void removeCallback(android.hardware.SensorEventListener r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.mLock
                monitor-enter(r0)
                java.util.WeakHashMap<android.hardware.SensorEventListener, java.lang.Integer> r1 = r5.mCallbacks     // Catch: java.lang.Throwable -> Lf
                java.lang.Object r1 = r1.get(r6)     // Catch: java.lang.Throwable -> Lf
                java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> Lf
                if (r1 != 0) goto L11
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
                return
            Lf:
                r5 = move-exception
                goto L67
            L11:
                java.util.WeakHashMap<android.hardware.SensorEventListener, java.lang.Integer> r2 = r5.mCallbacks     // Catch: java.lang.Throwable -> Lf
                r2.remove(r6)     // Catch: java.lang.Throwable -> Lf
                int r6 = r5.mRate     // Catch: java.lang.Throwable -> Lf
                int r2 = r1.intValue()     // Catch: java.lang.Throwable -> Lf
                if (r6 != r2) goto L50
                r6 = 3
                r5.mRate = r6     // Catch: java.lang.Throwable -> Lf
                java.util.WeakHashMap<android.hardware.SensorEventListener, java.lang.Integer> r6 = r5.mCallbacks     // Catch: java.lang.Throwable -> Lf
                java.util.Collection r6 = r6.values()     // Catch: java.lang.Throwable -> Lf
                java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> Lf
            L2b:
                boolean r2 = r6.hasNext()     // Catch: java.lang.Throwable -> Lf
                if (r2 == 0) goto L46
                java.lang.Object r2 = r6.next()     // Catch: java.lang.Throwable -> Lf
                java.lang.Integer r2 = (java.lang.Integer) r2     // Catch: java.lang.Throwable -> Lf
                int r3 = r5.mRate     // Catch: java.lang.Throwable -> Lf
                int r4 = r2.intValue()     // Catch: java.lang.Throwable -> Lf
                if (r3 <= r4) goto L2b
                int r2 = r2.intValue()     // Catch: java.lang.Throwable -> Lf
                r5.mRate = r2     // Catch: java.lang.Throwable -> Lf
                goto L2b
            L46:
                int r6 = r5.mRate     // Catch: java.lang.Throwable -> Lf
                int r1 = r1.intValue()     // Catch: java.lang.Throwable -> Lf
                if (r6 == r1) goto L50
                r6 = 1
                goto L51
            L50:
                r6 = 0
            L51:
                java.util.WeakHashMap<android.hardware.SensorEventListener, java.lang.Integer> r1 = r5.mCallbacks     // Catch: java.lang.Throwable -> Lf
                int r1 = r1.size()     // Catch: java.lang.Throwable -> Lf
                if (r1 != 0) goto L5d
                r5.unRegisterListener()     // Catch: java.lang.Throwable -> Lf
                goto L65
            L5d:
                if (r6 == 0) goto L65
                r5.unRegisterListener()     // Catch: java.lang.Throwable -> Lf
                r5.registerListener()     // Catch: java.lang.Throwable -> Lf
            L65:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
                return
            L67:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.maml.data.MamlSensorManager.MamlSensor.removeCallback(android.hardware.SensorEventListener):void");
        }
    }

    public static class MamlSensorManagerHolder {
        public static final MamlSensorManager INSTANCE = new MamlSensorManager();

        private MamlSensorManagerHolder() {
        }
    }

    public static MamlSensorManager getInstance() {
        return MamlSensorManagerHolder.INSTANCE;
    }

    private int getType(String str) {
        str.hashCode();
        switch (str) {
            case "orientation":
                return 3;
            case "pressure":
                return 6;
            case "proximity":
                return 8;
            case "light":
                return 5;
            case "gravity":
                return 9;
            case "gyroscope":
                return 4;
            case "linear_acceleration":
                return 10;
            case "accelerometer":
                return 1;
            default:
                return -1;
        }
    }

    private int getValidRate(int i2) {
        if (i2 == 0 || i2 == 1) {
            return 1;
        }
        return i2 != 2 ? 3 : 2;
    }

    public void registerListener(Context context, String str, int i2, SensorEventListener sensorEventListener) {
        int validRate = getValidRate(i2);
        synchronized (sLock) {
            try {
                MamlSensor mamlSensor = sSensors.get(str);
                if (mamlSensor != null) {
                    mamlSensor.addCallback(validRate, sensorEventListener);
                } else {
                    MamlSensor mamlSensor2 = new MamlSensor(context, getType(str), validRate);
                    if (mamlSensor2.mSensor != null) {
                        mamlSensor2.addCallback(validRate, sensorEventListener);
                        sSensors.put(str, mamlSensor2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void unregisterListener(String str, SensorEventListener sensorEventListener) {
        synchronized (sLock) {
            try {
                MamlSensor mamlSensor = sSensors.get(str);
                if (mamlSensor != null) {
                    mamlSensor.removeCallback(sensorEventListener);
                    if (mamlSensor.mCallbacks.size() == 0) {
                        sSensors.remove(str);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private MamlSensorManager() {
    }
}
