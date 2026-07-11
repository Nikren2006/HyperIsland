package androidx.constraintlayout.core;

import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import com.miui.maml.folme.AnimatedProperty;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    private static final boolean DO_NOT_USE = false;
    public static final boolean FULL_DEBUG = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public static Metrics sMetrics;
    final Cache mCache;
    private Row mGoal;
    private Row mTempGoal;
    private int mPoolSize = 1000;
    public boolean hasSimpleDefinition = false;
    int mVariablesID = 0;
    private HashMap<String, SolverVariable> mVariables = null;
    private int mTableSize = 32;
    private int mMaxColumns = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] mAlreadyTestedCandidates = new boolean[32];
    int mNumColumns = 1;
    int mNumRows = 0;
    private int mMaxRows = 32;
    private SolverVariable[] mPoolVariables = new SolverVariable[1000];
    private int mPoolVariablesCount = 0;
    ArrayRow[] mRows = new ArrayRow[32];

    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z2);

        void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z2);

        void updateFromSystem(LinearSystem linearSystem);
    }

    public static class ValuesRow extends ArrayRow {
        public ValuesRow(Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariableAcquire = this.mCache.mSolverVariablePool.acquire();
        if (solverVariableAcquire == null) {
            solverVariableAcquire = new SolverVariable(type, str);
            solverVariableAcquire.setType(type, str);
        } else {
            solverVariableAcquire.reset();
            solverVariableAcquire.setType(type, str);
        }
        int i2 = this.mPoolVariablesCount;
        int i3 = this.mPoolSize;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            this.mPoolSize = i4;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i4);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i5 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i5 + 1;
        solverVariableArr[i5] = solverVariableAcquire;
        return solverVariableAcquire;
    }

    private void addRow(ArrayRow arrayRow) {
        int i2;
        if (SIMPLIFY_SYNONYMS && arrayRow.mIsSimpleDefinition) {
            arrayRow.mVariable.setFinalValue(this, arrayRow.mConstantValue);
        } else {
            ArrayRow[] arrayRowArr = this.mRows;
            int i3 = this.mNumRows;
            arrayRowArr[i3] = arrayRow;
            SolverVariable solverVariable = arrayRow.mVariable;
            solverVariable.mDefinitionId = i3;
            this.mNumRows = i3 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i4 = 0;
            while (i4 < this.mNumRows) {
                if (this.mRows[i4] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow2 = this.mRows[i4];
                if (arrayRow2 != null && arrayRow2.mIsSimpleDefinition) {
                    arrayRow2.mVariable.setFinalValue(this, arrayRow2.mConstantValue);
                    if (OPTIMIZED_ENGINE) {
                        this.mCache.mOptimizedArrayRowPool.release(arrayRow2);
                    } else {
                        this.mCache.mArrayRowPool.release(arrayRow2);
                    }
                    this.mRows[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.mNumRows;
                        if (i5 >= i2) {
                            break;
                        }
                        ArrayRow[] arrayRowArr2 = this.mRows;
                        int i7 = i5 - 1;
                        ArrayRow arrayRow3 = arrayRowArr2[i5];
                        arrayRowArr2[i7] = arrayRow3;
                        SolverVariable solverVariable2 = arrayRow3.mVariable;
                        if (solverVariable2.mDefinitionId == i5) {
                            solverVariable2.mDefinitionId = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.mRows[i6] = null;
                    }
                    this.mNumRows = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    private void computeValues() {
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            arrayRow.mVariable.computedValue = arrayRow.mConstantValue;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f2) {
        return linearSystem.createRow().createRowDimensionPercent(solverVariable, solverVariable2, f2);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(type, null);
        solverVariableAcquireSolverVariable.setName(str);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.id = i2;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, solverVariableAcquireSolverVariable);
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            str = (str + this.mRows[i2]) + "\n";
        }
        System.out.println(str + this.mGoal + "\n");
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.mNumRows + AnimatedProperty.PROPERTY_NAME_X + this.mNumColumns + ")\n");
    }

    private int enforceBFS(Row row) {
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.mVariable.mType != SolverVariable.Type.UNRESTRICTED && arrayRow.mConstantValue < 0.0f) {
                boolean z2 = false;
                int i3 = 0;
                while (!z2) {
                    Metrics metrics = sMetrics;
                    if (metrics != null) {
                        metrics.bfs++;
                    }
                    i3++;
                    float f2 = Float.MAX_VALUE;
                    int i4 = 0;
                    int i5 = -1;
                    int i6 = -1;
                    int i7 = 0;
                    while (true) {
                        if (i4 >= this.mNumRows) {
                            break;
                        }
                        ArrayRow arrayRow2 = this.mRows[i4];
                        if (arrayRow2.mVariable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow2.mIsSimpleDefinition && arrayRow2.mConstantValue < 0.0f) {
                            int i8 = 9;
                            if (SKIP_COLUMNS) {
                                int currentSize = arrayRow2.variables.getCurrentSize();
                                int i9 = 0;
                                while (i9 < currentSize) {
                                    SolverVariable variable = arrayRow2.variables.getVariable(i9);
                                    float f3 = arrayRow2.variables.get(variable);
                                    if (f3 > 0.0f) {
                                        int i10 = 0;
                                        while (i10 < i8) {
                                            float f4 = variable.mStrengthVector[i10] / f3;
                                            if ((f4 < f2 && i10 == i7) || i10 > i7) {
                                                i7 = i10;
                                                i6 = variable.id;
                                                i5 = i4;
                                                f2 = f4;
                                            }
                                            i10++;
                                            i8 = 9;
                                        }
                                    }
                                    i9++;
                                    i8 = 9;
                                }
                            } else {
                                for (int i11 = 1; i11 < this.mNumColumns; i11++) {
                                    SolverVariable solverVariable = this.mCache.mIndexedVariables[i11];
                                    float f5 = arrayRow2.variables.get(solverVariable);
                                    if (f5 > 0.0f) {
                                        for (int i12 = 0; i12 < 9; i12++) {
                                            float f6 = solverVariable.mStrengthVector[i12] / f5;
                                            if ((f6 < f2 && i12 == i7) || i12 > i7) {
                                                i7 = i12;
                                                i5 = i4;
                                                i6 = i11;
                                                f2 = f6;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i4++;
                    }
                    if (i5 != -1) {
                        ArrayRow arrayRow3 = this.mRows[i5];
                        arrayRow3.mVariable.mDefinitionId = -1;
                        Metrics metrics2 = sMetrics;
                        if (metrics2 != null) {
                            metrics2.pivots++;
                        }
                        arrayRow3.pivot(this.mCache.mIndexedVariables[i6]);
                        SolverVariable solverVariable2 = arrayRow3.mVariable;
                        solverVariable2.mDefinitionId = i5;
                        solverVariable2.updateReferencesWithNewDefinition(this, arrayRow3);
                    } else {
                        z2 = true;
                    }
                    if (i3 > this.mNumColumns / 2) {
                        z2 = true;
                    }
                }
                return i3;
            }
        }
        return 0;
    }

    private String getDisplaySize(int i2) {
        int i3 = i2 * 4;
        int i4 = i3 / 1024;
        int i5 = i4 / 1024;
        if (i5 > 0) {
            return "" + i5 + " Mb";
        }
        if (i4 > 0) {
            return "" + i4 + " Kb";
        }
        return "" + i3 + " bytes";
    }

    private String getDisplayStrength(int i2) {
        return i2 == 1 ? "LOW" : i2 == 2 ? "MEDIUM" : i2 == 3 ? "HIGH" : i2 == 4 ? "HIGHEST" : i2 == 5 ? "EQUALITY" : i2 == 8 ? "FIXED" : i2 == 6 ? "BARRIER" : "NONE";
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        int i2 = this.mTableSize * 2;
        this.mTableSize = i2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i2);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.mTableSize);
        int i3 = this.mTableSize;
        this.mAlreadyTestedCandidates = new boolean[i3];
        this.mMaxColumns = i3;
        this.mMaxRows = i3;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, i3);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    private int optimize(Row row, boolean z2) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i2 = 0; i2 < this.mNumColumns; i2++) {
            this.mAlreadyTestedCandidates[i2] = false;
        }
        boolean z3 = false;
        int i3 = 0;
        while (!z3) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i3++;
            if (i3 >= this.mNumColumns * 2) {
                return i3;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i4 = pivotCandidate.id;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (pivotCandidate != null) {
                float f2 = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.mNumRows; i6++) {
                    ArrayRow arrayRow = this.mRows[i6];
                    if (arrayRow.mVariable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.mIsSimpleDefinition && arrayRow.hasVariable(pivotCandidate)) {
                        float f3 = arrayRow.variables.get(pivotCandidate);
                        if (f3 < 0.0f) {
                            float f4 = (-arrayRow.mConstantValue) / f3;
                            if (f4 < f2) {
                                i5 = i6;
                                f2 = f4;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i5];
                    arrayRow2.mVariable.mDefinitionId = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.pivot(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.mVariable;
                    solverVariable.mDefinitionId = i5;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z3 = true;
            }
        }
        return i3;
    }

    private void releaseRows() {
        int i2 = 0;
        if (OPTIMIZED_ENGINE) {
            while (i2 < this.mNumRows) {
                ArrayRow arrayRow = this.mRows[i2];
                if (arrayRow != null) {
                    this.mCache.mOptimizedArrayRowPool.release(arrayRow);
                }
                this.mRows[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.mNumRows) {
            ArrayRow arrayRow2 = this.mRows[i2];
            if (arrayRow2 != null) {
                this.mCache.mArrayRowPool.release(arrayRow2);
            }
            this.mRows[i2] = null;
            i2++;
        }
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f2, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable solverVariableCreateObjectVariable = createObjectVariable(constraintWidget.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable solverVariableCreateObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable solverVariableCreateObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable solverVariableCreateObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(type4));
        SolverVariable solverVariableCreateObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(type));
        SolverVariable solverVariableCreateObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(type2));
        SolverVariable solverVariableCreateObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(type3));
        SolverVariable solverVariableCreateObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(type4));
        ArrayRow arrayRowCreateRow = createRow();
        double d2 = f2;
        double d3 = i2;
        arrayRowCreateRow.createRowWithAngle(solverVariableCreateObjectVariable2, solverVariableCreateObjectVariable4, solverVariableCreateObjectVariable6, solverVariableCreateObjectVariable8, (float) (Math.sin(d2) * d3));
        addConstraint(arrayRowCreateRow);
        ArrayRow arrayRowCreateRow2 = createRow();
        arrayRowCreateRow2.createRowWithAngle(solverVariableCreateObjectVariable, solverVariableCreateObjectVariable3, solverVariableCreateObjectVariable5, solverVariableCreateObjectVariable7, (float) (Math.cos(d2) * d3));
        addConstraint(arrayRowCreateRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f2, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowCentering(solverVariable, solverVariable2, i2, f2, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            arrayRowCreateRow.addError(this, i4);
        }
        addConstraint(arrayRowCreateRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addConstraint(androidx.constraintlayout.core.ArrayRow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            androidx.constraintlayout.core.Metrics r0 = androidx.constraintlayout.core.LinearSystem.sMetrics
            r1 = 1
            if (r0 == 0) goto L17
            long r3 = r0.constraints
            long r3 = r3 + r1
            r0.constraints = r3
            boolean r3 = r8.mIsSimpleDefinition
            if (r3 == 0) goto L17
            long r3 = r0.simpleconstraints
            long r3 = r3 + r1
            r0.simpleconstraints = r3
        L17:
            int r0 = r7.mNumRows
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.mMaxRows
            if (r0 >= r4) goto L26
            int r0 = r7.mNumColumns
            int r0 = r0 + r3
            int r4 = r7.mMaxColumns
            if (r0 < r4) goto L29
        L26:
            r7.increaseTableSize()
        L29:
            boolean r0 = r8.mIsSimpleDefinition
            r4 = 0
            if (r0 != 0) goto La1
            r8.updateFromSystem(r7)
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L38
            return
        L38:
            r8.ensurePositiveConstant()
            boolean r0 = r8.chooseSubject(r7)
            if (r0 == 0) goto L98
            androidx.constraintlayout.core.SolverVariable r0 = r7.createExtraVariable()
            r8.mVariable = r0
            int r5 = r7.mNumRows
            r7.addRow(r8)
            int r6 = r7.mNumRows
            int r5 = r5 + r3
            if (r6 != r5) goto L98
            androidx.constraintlayout.core.LinearSystem$Row r4 = r7.mTempGoal
            r4.initFromRow(r8)
            androidx.constraintlayout.core.LinearSystem$Row r4 = r7.mTempGoal
            r7.optimize(r4, r3)
            int r4 = r0.mDefinitionId
            r5 = -1
            if (r4 != r5) goto L99
            androidx.constraintlayout.core.SolverVariable r4 = r8.mVariable
            if (r4 != r0) goto L76
            androidx.constraintlayout.core.SolverVariable r0 = r8.pickPivot(r0)
            if (r0 == 0) goto L76
            androidx.constraintlayout.core.Metrics r4 = androidx.constraintlayout.core.LinearSystem.sMetrics
            if (r4 == 0) goto L73
            long r5 = r4.pivots
            long r5 = r5 + r1
            r4.pivots = r5
        L73:
            r8.pivot(r0)
        L76:
            boolean r0 = r8.mIsSimpleDefinition
            if (r0 != 0) goto L7f
            androidx.constraintlayout.core.SolverVariable r0 = r8.mVariable
            r0.updateReferencesWithNewDefinition(r7, r8)
        L7f:
            boolean r0 = androidx.constraintlayout.core.LinearSystem.OPTIMIZED_ENGINE
            if (r0 == 0) goto L8b
            androidx.constraintlayout.core.Cache r0 = r7.mCache
            androidx.constraintlayout.core.Pools$Pool<androidx.constraintlayout.core.ArrayRow> r0 = r0.mOptimizedArrayRowPool
            r0.release(r8)
            goto L92
        L8b:
            androidx.constraintlayout.core.Cache r0 = r7.mCache
            androidx.constraintlayout.core.Pools$Pool<androidx.constraintlayout.core.ArrayRow> r0 = r0.mArrayRowPool
            r0.release(r8)
        L92:
            int r0 = r7.mNumRows
            int r0 = r0 - r3
            r7.mNumRows = r0
            goto L99
        L98:
            r3 = r4
        L99:
            boolean r0 = r8.hasKeyVariable()
            if (r0 != 0) goto La0
            return
        La0:
            r4 = r3
        La1:
            if (r4 != 0) goto La6
            r7.addRow(r8)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.LinearSystem.addConstraint(androidx.constraintlayout.core.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.mSimpleEquations++;
        }
        if (USE_BASIC_SYNONYMS && i3 == 8 && solverVariable2.isFinalValue && solverVariable.mDefinitionId == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i2);
            return null;
        }
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowEquals(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            arrayRowCreateRow.addError(this, i3);
        }
        addConstraint(arrayRowCreateRow);
        return arrayRowCreateRow;
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z2) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowGreaterThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i2);
        addConstraint(arrayRowCreateRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowGreaterThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i2);
        if (i3 != 8) {
            addSingleError(arrayRowCreateRow, (int) (arrayRowCreateRow.variables.get(solverVariableCreateSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z2) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowLowerThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i2);
        addConstraint(arrayRowCreateRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow arrayRowCreateRow = createRow();
        SolverVariable solverVariableCreateSlackVariable = createSlackVariable();
        solverVariableCreateSlackVariable.strength = 0;
        arrayRowCreateRow.createRowLowerThan(solverVariable, solverVariable2, solverVariableCreateSlackVariable, i2);
        if (i3 != 8) {
            addSingleError(arrayRowCreateRow, (int) (arrayRowCreateRow.variables.get(solverVariableCreateSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f2, int i2) {
        ArrayRow arrayRowCreateRow = createRow();
        arrayRowCreateRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f2);
        if (i2 != 8) {
            arrayRowCreateRow.addError(this, i2);
        }
        addConstraint(arrayRowCreateRow);
    }

    public void addSingleError(ArrayRow arrayRow, int i2, int i3) {
        arrayRow.addSingleError(createErrorVariable(i3, null), i2);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        if (solverVariable.mDefinitionId != -1 || i2 != 0) {
            addEquality(solverVariable, solverVariable2, i2, 8);
            return;
        }
        if (solverVariable2.mIsSynonym) {
            solverVariable2 = this.mCache.mIndexedVariables[solverVariable2.mSynonym];
        }
        if (solverVariable.mIsSynonym) {
            SolverVariable solverVariable3 = this.mCache.mIndexedVariables[solverVariable.mSynonym];
        } else {
            solverVariable.setSynonym(this, solverVariable2, 0.0f);
        }
    }

    public final void cleanupRows() {
        int i2;
        int i3 = 0;
        while (i3 < this.mNumRows) {
            ArrayRow arrayRow = this.mRows[i3];
            if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.mIsSimpleDefinition = true;
            }
            if (arrayRow.mIsSimpleDefinition) {
                SolverVariable solverVariable = arrayRow.mVariable;
                solverVariable.computedValue = arrayRow.mConstantValue;
                solverVariable.removeFromRow(arrayRow);
                int i4 = i3;
                while (true) {
                    i2 = this.mNumRows;
                    if (i4 >= i2 - 1) {
                        break;
                    }
                    ArrayRow[] arrayRowArr = this.mRows;
                    int i5 = i4 + 1;
                    arrayRowArr[i4] = arrayRowArr[i5];
                    i4 = i5;
                }
                this.mRows[i2 - 1] = null;
                this.mNumRows = i2 - 1;
                i3--;
                if (OPTIMIZED_ENGINE) {
                    this.mCache.mOptimizedArrayRowPool.release(arrayRow);
                } else {
                    this.mCache.mArrayRowPool.release(arrayRow);
                }
            }
            i3++;
        }
    }

    public SolverVariable createErrorVariable(int i2, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        int i3 = this.mVariablesID + 1;
        this.mVariablesID = i3;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.id = i3;
        solverVariableAcquireSolverVariable.strength = i2;
        this.mCache.mIndexedVariables[i3] = solverVariableAcquireSolverVariable;
        this.mGoal.addError(solverVariableAcquireSolverVariable);
        return solverVariableAcquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.id = i2;
        this.mCache.mIndexedVariables[i2] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i2 = solverVariable.id;
            if (i2 == -1 || i2 > this.mVariablesID || this.mCache.mIndexedVariables[i2] == null) {
                if (i2 != -1) {
                    solverVariable.reset();
                }
                int i3 = this.mVariablesID + 1;
                this.mVariablesID = i3;
                this.mNumColumns++;
                solverVariable.id = i3;
                solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[i3] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRowAcquire;
        if (OPTIMIZED_ENGINE) {
            arrayRowAcquire = this.mCache.mOptimizedArrayRowPool.acquire();
            if (arrayRowAcquire == null) {
                arrayRowAcquire = new ValuesRow(this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                arrayRowAcquire.reset();
            }
        } else {
            arrayRowAcquire = this.mCache.mArrayRowPool.acquire();
            if (arrayRowAcquire == null) {
                arrayRowAcquire = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                arrayRowAcquire.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return arrayRowAcquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable solverVariableAcquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, null);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        solverVariableAcquireSolverVariable.id = i2;
        this.mCache.mIndexedVariables[i2] = solverVariableAcquireSolverVariable;
        return solverVariableAcquireSolverVariable;
    }

    public void displayReadableRows() {
        displaySolverVariables();
        String str = " num vars " + this.mVariablesID + "\n";
        for (int i2 = 0; i2 < this.mVariablesID + 1; i2++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[i2];
            if (solverVariable != null && solverVariable.isFinalValue) {
                str = str + " $[" + i2 + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
            }
        }
        String str2 = str + "\n";
        for (int i3 = 0; i3 < this.mVariablesID + 1; i3++) {
            SolverVariable[] solverVariableArr = this.mCache.mIndexedVariables;
            SolverVariable solverVariable2 = solverVariableArr[i3];
            if (solverVariable2 != null && solverVariable2.mIsSynonym) {
                str2 = str2 + " ~[" + i3 + "] => " + solverVariable2 + " = " + solverVariableArr[solverVariable2.mSynonym] + " + " + solverVariable2.mSynonymDelta + "\n";
            }
        }
        String str3 = str2 + "\n\n #  ";
        for (int i4 = 0; i4 < this.mNumRows; i4++) {
            str3 = (str3 + this.mRows[i4].toReadableString()) + "\n #  ";
        }
        if (this.mGoal != null) {
            str3 = str3 + "Goal: " + this.mGoal + "\n";
        }
        System.out.println(str3);
    }

    public void displaySystemInformation() {
        int iSizeInBytes = 0;
        for (int i2 = 0; i2 < this.mTableSize; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow != null) {
                iSizeInBytes += arrayRow.sizeInBytes();
            }
        }
        int iSizeInBytes2 = 0;
        for (int i3 = 0; i3 < this.mNumRows; i3++) {
            ArrayRow arrayRow2 = this.mRows[i3];
            if (arrayRow2 != null) {
                iSizeInBytes2 += arrayRow2.sizeInBytes();
            }
        }
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("Linear System -> Table size: ");
        sb.append(this.mTableSize);
        sb.append(" (");
        int i4 = this.mTableSize;
        sb.append(getDisplaySize(i4 * i4));
        sb.append(") -- row sizes: ");
        sb.append(getDisplaySize(iSizeInBytes));
        sb.append(", actual size: ");
        sb.append(getDisplaySize(iSizeInBytes2));
        sb.append(" rows: ");
        sb.append(this.mNumRows);
        sb.append("/");
        sb.append(this.mMaxRows);
        sb.append(" cols: ");
        sb.append(this.mNumColumns);
        sb.append("/");
        sb.append(this.mMaxColumns);
        sb.append(" ");
        sb.append(0);
        sb.append(" occupied cells, ");
        sb.append(getDisplaySize(0));
        printStream.println(sb.toString());
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2].mVariable.mType == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.mRows[i2].toReadableString()) + "\n";
            }
        }
        System.out.println(str + this.mGoal + "\n");
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.mCache;
    }

    public Row getGoal() {
        return this.mGoal;
    }

    public int getMemoryUsed() {
        int iSizeInBytes = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow != null) {
                iSizeInBytes += arrayRow.sizeInBytes();
            }
        }
        return iSizeInBytes;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public ArrayRow getRow(int i2) {
        return this.mRows[i2];
    }

    public float getValueFor(String str) {
        SolverVariable variable = getVariable(str, SolverVariable.Type.UNRESTRICTED);
        if (variable == null) {
            return 0.0f;
        }
        return variable.computedValue;
    }

    public SolverVariable getVariable(String str, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable solverVariable = this.mVariables.get(str);
        return solverVariable == null ? createVariable(str, type) : solverVariable;
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.mGoal.isEmpty()) {
            computeValues();
            return;
        }
        if (!this.graphOptimizer && !this.newgraphOptimizer) {
            minimizeGoal(this.mGoal);
            return;
        }
        Metrics metrics2 = sMetrics;
        if (metrics2 != null) {
            metrics2.graphOptimizer++;
        }
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (!this.mRows[i2].mIsSimpleDefinition) {
                minimizeGoal(this.mGoal);
                return;
            }
        }
        Metrics metrics3 = sMetrics;
        if (metrics3 != null) {
            metrics3.fullySolved++;
        }
        computeValues();
    }

    public void minimizeGoal(Row row) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, this.mNumColumns);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, this.mNumRows);
        }
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    public void removeRow(ArrayRow arrayRow) {
        SolverVariable solverVariable;
        int i2;
        if (!arrayRow.mIsSimpleDefinition || (solverVariable = arrayRow.mVariable) == null) {
            return;
        }
        int i3 = solverVariable.mDefinitionId;
        if (i3 != -1) {
            while (true) {
                i2 = this.mNumRows;
                if (i3 >= i2 - 1) {
                    break;
                }
                ArrayRow[] arrayRowArr = this.mRows;
                int i4 = i3 + 1;
                ArrayRow arrayRow2 = arrayRowArr[i4];
                SolverVariable solverVariable2 = arrayRow2.mVariable;
                if (solverVariable2.mDefinitionId == i4) {
                    solverVariable2.mDefinitionId = i3;
                }
                arrayRowArr[i3] = arrayRow2;
                i3 = i4;
            }
            this.mNumRows = i2 - 1;
        }
        SolverVariable solverVariable3 = arrayRow.mVariable;
        if (!solverVariable3.isFinalValue) {
            solverVariable3.setFinalValue(this, arrayRow.mConstantValue);
        }
        if (OPTIMIZED_ENGINE) {
            this.mCache.mOptimizedArrayRowPool.release(arrayRow);
        } else {
            this.mCache.mArrayRowPool.release(arrayRow);
        }
    }

    public void reset() {
        Cache cache;
        int i2 = 0;
        while (true) {
            cache = this.mCache;
            SolverVariable[] solverVariableArr = cache.mIndexedVariables;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i2++;
        }
        cache.mSolverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        HashMap<String, SolverVariable> map = this.mVariables;
        if (map != null) {
            map.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i3 = 0; i3 < this.mNumRows; i3++) {
            ArrayRow arrayRow = this.mRows[i3];
            if (arrayRow != null) {
                arrayRow.mUsed = false;
            }
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i2) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.mSimpleEquations++;
        }
        if (USE_BASIC_SYNONYMS && solverVariable.mDefinitionId == -1) {
            float f2 = i2;
            solverVariable.setFinalValue(this, f2);
            for (int i3 = 0; i3 < this.mVariablesID + 1; i3++) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[i3];
                if (solverVariable2 != null && solverVariable2.mIsSynonym && solverVariable2.mSynonym == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.mSynonymDelta + f2);
                }
            }
            return;
        }
        int i4 = solverVariable.mDefinitionId;
        if (i4 != -1) {
            ArrayRow arrayRow = this.mRows[i4];
            if (arrayRow.mIsSimpleDefinition) {
                arrayRow.mConstantValue = i2;
                return;
            }
            if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.mIsSimpleDefinition = true;
                arrayRow.mConstantValue = i2;
                return;
            } else {
                ArrayRow arrayRowCreateRow = createRow();
                arrayRowCreateRow.createRowEquals(solverVariable, i2);
                addConstraint(arrayRowCreateRow);
                return;
            }
        }
        ArrayRow arrayRowCreateRow2 = createRow();
        arrayRowCreateRow2.createRowDefinition(solverVariable, i2);
        addConstraint(arrayRowCreateRow2);
    }
}
