package androidx.constraintlayout.core;

import androidx.constraintlayout.core.ArrayRow;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean HASH = true;
    private static float sEpsilon = 0.001f;
    protected final Cache mCache;
    private final ArrayRow mRow;
    private final int mNone = -1;
    private int mSize = 16;
    private int mHashSize = 16;
    int[] mKeys = new int[16];
    int[] mNextKeys = new int[16];
    int[] mVariables = new int[16];
    float[] mValues = new float[16];
    int[] mPrevious = new int[16];
    int[] mNext = new int[16];
    int mCount = 0;
    int mHead = -1;

    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
        clear();
    }

    private void addToHashMap(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i3 = solverVariable.id % this.mHashSize;
        int[] iArr2 = this.mKeys;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.mNextKeys;
                int i5 = iArr[i4];
                if (i5 == -1) {
                    break;
                } else {
                    i4 = i5;
                }
            }
            iArr[i4] = i2;
        }
        this.mNextKeys[i2] = -1;
    }

    private void addVariable(int i2, SolverVariable solverVariable, float f2) {
        this.mVariables[i2] = solverVariable.id;
        this.mValues[i2] = f2;
        this.mPrevious[i2] = -1;
        this.mNext[i2] = -1;
        solverVariable.addToRow(this.mRow);
        solverVariable.usageInRowCount++;
        this.mCount++;
    }

    private void displayHash() {
        for (int i2 = 0; i2 < this.mHashSize; i2++) {
            if (this.mKeys[i2] != -1) {
                String str = hashCode() + " hash [" + i2 + "] => ";
                int i3 = this.mKeys[i2];
                boolean z2 = false;
                while (!z2) {
                    str = str + " " + this.mVariables[i3];
                    int i4 = this.mNextKeys[i3];
                    if (i4 != -1) {
                        i3 = i4;
                    } else {
                        z2 = true;
                    }
                }
                System.out.println(str);
            }
        }
    }

    private int findEmptySlot() {
        for (int i2 = 0; i2 < this.mSize; i2++) {
            if (this.mVariables[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void increaseSize() {
        int i2 = this.mSize * 2;
        this.mVariables = Arrays.copyOf(this.mVariables, i2);
        this.mValues = Arrays.copyOf(this.mValues, i2);
        this.mPrevious = Arrays.copyOf(this.mPrevious, i2);
        this.mNext = Arrays.copyOf(this.mNext, i2);
        this.mNextKeys = Arrays.copyOf(this.mNextKeys, i2);
        for (int i3 = this.mSize; i3 < i2; i3++) {
            this.mVariables[i3] = -1;
            this.mNextKeys[i3] = -1;
        }
        this.mSize = i2;
    }

    private void insertVariable(int i2, SolverVariable solverVariable, float f2) {
        int iFindEmptySlot = findEmptySlot();
        addVariable(iFindEmptySlot, solverVariable, f2);
        if (i2 != -1) {
            this.mPrevious[iFindEmptySlot] = i2;
            int[] iArr = this.mNext;
            iArr[iFindEmptySlot] = iArr[i2];
            iArr[i2] = iFindEmptySlot;
        } else {
            this.mPrevious[iFindEmptySlot] = -1;
            if (this.mCount > 0) {
                this.mNext[iFindEmptySlot] = this.mHead;
                this.mHead = iFindEmptySlot;
            } else {
                this.mNext[iFindEmptySlot] = -1;
            }
        }
        int i3 = this.mNext[iFindEmptySlot];
        if (i3 != -1) {
            this.mPrevious[i3] = iFindEmptySlot;
        }
        addToHashMap(solverVariable, iFindEmptySlot);
    }

    private void removeFromHashMap(SolverVariable solverVariable) {
        int[] iArr;
        int i2;
        int i3 = solverVariable.id;
        int i4 = i3 % this.mHashSize;
        int[] iArr2 = this.mKeys;
        int i5 = iArr2[i4];
        if (i5 == -1) {
            return;
        }
        if (this.mVariables[i5] == i3) {
            int[] iArr3 = this.mNextKeys;
            iArr2[i4] = iArr3[i5];
            iArr3[i5] = -1;
            return;
        }
        while (true) {
            iArr = this.mNextKeys;
            i2 = iArr[i5];
            if (i2 == -1 || this.mVariables[i2] == i3) {
                break;
            } else {
                i5 = i2;
            }
        }
        if (i2 == -1 || this.mVariables[i2] != i3) {
            return;
        }
        iArr[i5] = iArr[i2];
        iArr[i2] = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f2, boolean z2) {
        float f3 = sEpsilon;
        if (f2 <= (-f3) || f2 >= f3) {
            int iIndexOf = indexOf(solverVariable);
            if (iIndexOf == -1) {
                put(solverVariable, f2);
                return;
            }
            float[] fArr = this.mValues;
            float f4 = fArr[iIndexOf] + f2;
            fArr[iIndexOf] = f4;
            float f5 = sEpsilon;
            if (f4 <= (-f5) || f4 >= f5) {
                return;
            }
            fArr[iIndexOf] = 0.0f;
            remove(solverVariable, z2);
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void clear() {
        int i2 = this.mCount;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                variable.removeFromRow(this.mRow);
            }
        }
        for (int i4 = 0; i4 < this.mSize; i4++) {
            this.mVariables[i4] = -1;
            this.mNextKeys[i4] = -1;
        }
        for (int i5 = 0; i5 < this.mHashSize; i5++) {
            this.mKeys[i5] = -1;
        }
        this.mCount = 0;
        this.mHead = -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void display() {
        int i2 = this.mCount;
        System.out.print("{ ");
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                System.out.print(variable + " = " + getVariableValue(i3) + " ");
            }
        }
        System.out.println(" }");
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f2) {
        int i2 = this.mCount;
        int i3 = this.mHead;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.mValues;
            fArr[i3] = fArr[i3] / f2;
            i3 = this.mNext[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf != -1) {
            return this.mValues[iIndexOf];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.mCount;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i2) {
        int i3 = this.mCount;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.mHead;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.mCache.mIndexedVariables[this.mVariables[i4]];
            }
            i4 = this.mNext[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i2) {
        int i3 = this.mCount;
        int i4 = this.mHead;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.mValues[i4];
            }
            i4 = this.mNext[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        if (this.mCount != 0 && solverVariable != null) {
            int i2 = solverVariable.id;
            int i3 = this.mKeys[i2 % this.mHashSize];
            if (i3 == -1) {
                return -1;
            }
            if (this.mVariables[i3] == i2) {
                return i3;
            }
            do {
                i3 = this.mNextKeys[i3];
                if (i3 == -1) {
                    break;
                }
            } while (this.mVariables[i3] != i2);
            if (i3 != -1 && this.mVariables[i3] == i2) {
                return i3;
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void invert() {
        int i2 = this.mCount;
        int i3 = this.mHead;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.mValues;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.mNext[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f2) {
        float f3 = sEpsilon;
        if (f2 > (-f3) && f2 < f3) {
            remove(solverVariable, true);
            return;
        }
        if (this.mCount == 0) {
            addVariable(0, solverVariable, f2);
            addToHashMap(solverVariable, 0);
            this.mHead = 0;
            return;
        }
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf != -1) {
            this.mValues[iIndexOf] = f2;
            return;
        }
        if (this.mCount + 1 >= this.mSize) {
            increaseSize();
        }
        int i2 = this.mCount;
        int i3 = this.mHead;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = this.mVariables[i3];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                this.mValues[i3] = f2;
                return;
            }
            if (i6 < i7) {
                i4 = i3;
            }
            i3 = this.mNext[i3];
            if (i3 == -1) {
                break;
            }
        }
        insertVariable(i4, solverVariable, f2);
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z2) {
        int iIndexOf = indexOf(solverVariable);
        if (iIndexOf == -1) {
            return 0.0f;
        }
        removeFromHashMap(solverVariable);
        float f2 = this.mValues[iIndexOf];
        if (this.mHead == iIndexOf) {
            this.mHead = this.mNext[iIndexOf];
        }
        this.mVariables[iIndexOf] = -1;
        int[] iArr = this.mPrevious;
        int i2 = iArr[iIndexOf];
        if (i2 != -1) {
            int[] iArr2 = this.mNext;
            iArr2[i2] = iArr2[iIndexOf];
        }
        int i3 = this.mNext[iIndexOf];
        if (i3 != -1) {
            iArr[i3] = iArr[iIndexOf];
        }
        this.mCount--;
        solverVariable.usageInRowCount--;
        if (z2) {
            solverVariable.removeFromRow(this.mRow);
        }
        return f2;
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    public String toString() {
        String str = hashCode() + " { ";
        int i2 = this.mCount;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                String str2 = str + variable + " = " + getVariableValue(i3) + " ";
                int iIndexOf = indexOf(variable);
                String str3 = str2 + "[p: ";
                String str4 = (this.mPrevious[iIndexOf] != -1 ? str3 + this.mCache.mIndexedVariables[this.mVariables[this.mPrevious[iIndexOf]]] : str3 + "none") + ", n: ";
                str = (this.mNext[iIndexOf] != -1 ? str4 + this.mCache.mIndexedVariables[this.mVariables[this.mNext[iIndexOf]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }

    @Override // androidx.constraintlayout.core.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z2) {
        float f2 = get(arrayRow.mVariable);
        remove(arrayRow.mVariable, z2);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i2 = 0;
        int i3 = 0;
        while (i2 < currentSize) {
            int i4 = solverVariableValues.mVariables[i3];
            if (i4 != -1) {
                add(this.mCache.mIndexedVariables[i4], solverVariableValues.mValues[i3] * f2, z2);
                i2++;
            }
            i3++;
        }
        return f2;
    }
}
