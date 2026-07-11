package miuix.animation.utils;

import android.os.SystemClock;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class VelocityMonitor {
    private static final long MAX_DELTA = 100;
    private static final int MAX_RECORD_COUNT = 10;
    private static final long MIN_DELTA = 30;
    private static final long TIME_THRESHOLD = 50;
    private float[] mVelocity;
    private Long mMinDeltaTime = Long.valueOf(MIN_DELTA);
    private Long mMaxDeltaTime = Long.valueOf(MAX_DELTA);
    private MoveRecord[] mHistory = new MoveRecord[10];
    private int mWriteIndex = 0;
    private int mReadIndex = 0;
    private int mSize = 0;

    public static class MoveRecord {
        long timeStamp;
        double[] values;

        private MoveRecord() {
        }
    }

    private void addAndUpdate(MoveRecord moveRecord) {
        MoveRecord[] moveRecordArr = this.mHistory;
        int i2 = this.mWriteIndex;
        moveRecordArr[i2] = moveRecord;
        int i3 = (i2 + 1) % 10;
        this.mWriteIndex = i3;
        int i4 = this.mSize;
        if (i4 < 10) {
            this.mSize = i4 + 1;
        } else {
            this.mReadIndex = i3;
        }
        updateVelocity();
    }

    private float calVelocity(int i2, MoveRecord moveRecord, MoveRecord moveRecord2) {
        float velocity;
        double d2 = moveRecord.values[i2];
        long j2 = moveRecord.timeStamp;
        double velocity2 = getVelocity(d2, moveRecord2.values[i2], j2 - moveRecord2.timeStamp);
        int i3 = (this.mWriteIndex + 8) % 10;
        while (true) {
            if (i3 == this.mReadIndex) {
                velocity = Float.MAX_VALUE;
                break;
            }
            MoveRecord moveRecord3 = this.mHistory[i3];
            long j3 = j2 - moveRecord3.timeStamp;
            if (j3 <= this.mMinDeltaTime.longValue() || j3 >= this.mMaxDeltaTime.longValue()) {
                i3 = (i3 + 9) % 10;
            } else {
                velocity = getVelocity(d2, moveRecord3.values[i2], j3);
                double d3 = velocity;
                if (velocity2 * d3 > 0.0d) {
                    velocity = (float) (velocity > 0.0f ? Math.max(velocity2, d3) : Math.min(velocity2, d3));
                }
            }
        }
        if (velocity == Float.MAX_VALUE && i3 != this.mReadIndex) {
            int i4 = (this.mWriteIndex + 8) % 10;
            long j4 = j2 - this.mHistory[i4].timeStamp;
            if (j4 > this.mMinDeltaTime.longValue() && j4 < this.mMaxDeltaTime.longValue()) {
                velocity = getVelocity(d2, this.mHistory[i4].values[i2], j4);
            }
        }
        if (velocity == Float.MAX_VALUE) {
            return 0.0f;
        }
        return velocity;
    }

    private void clearVelocity() {
        float[] fArr = this.mVelocity;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
        }
    }

    private MoveRecord getMoveRecord() {
        MoveRecord moveRecord = new MoveRecord();
        moveRecord.timeStamp = SystemClock.uptimeMillis();
        return moveRecord;
    }

    private float getVelocity(double d2, double d3, long j2) {
        return (float) (j2 == 0 ? 0.0d : (d2 - d3) / ((double) (j2 / 1000.0f)));
    }

    private void updateVelocity() {
        if (this.mSize < 2) {
            clearVelocity();
            return;
        }
        MoveRecord[] moveRecordArr = this.mHistory;
        int i2 = this.mWriteIndex;
        MoveRecord moveRecord = moveRecordArr[(i2 + 9) % 10];
        MoveRecord moveRecord2 = moveRecordArr[(i2 + 8) % 10];
        float[] fArr = this.mVelocity;
        if (fArr == null || fArr.length < moveRecord.values.length) {
            this.mVelocity = new float[moveRecord.values.length];
        }
        for (int i3 = 0; i3 < moveRecord.values.length; i3++) {
            this.mVelocity[i3] = calVelocity(i3, moveRecord, moveRecord2);
        }
    }

    public void clear() {
        this.mWriteIndex = 0;
        this.mReadIndex = 0;
        this.mSize = 0;
        this.mHistory = new MoveRecord[10];
        clearVelocity();
    }

    public void setMaxFeedbackTime(long j2) {
        this.mMaxDeltaTime = Long.valueOf(j2);
    }

    public void setMinFeedbackTime(long j2) {
        this.mMinDeltaTime = Long.valueOf(j2);
    }

    public void update(float... fArr) {
        if (fArr == null || fArr.length == 0) {
            return;
        }
        MoveRecord moveRecord = getMoveRecord();
        moveRecord.values = new double[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            moveRecord.values[i2] = fArr[i2];
        }
        addAndUpdate(moveRecord);
    }

    public float getVelocity(int i2) {
        float[] fArr;
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.mSize == 0 || Math.abs(jUptimeMillis - this.mHistory[(this.mWriteIndex + 9) % 10].timeStamp) > TIME_THRESHOLD || (fArr = this.mVelocity) == null || fArr.length <= i2) {
            return 0.0f;
        }
        return fArr[i2];
    }

    public void update(double... dArr) {
        if (dArr == null || dArr.length == 0) {
            return;
        }
        MoveRecord moveRecord = getMoveRecord();
        moveRecord.values = dArr;
        addAndUpdate(moveRecord);
    }
}
