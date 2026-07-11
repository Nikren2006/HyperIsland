package com.miui.maml.data;

import com.miui.maml.data.Expression;

/* JADX INFO: loaded from: classes2.dex */
public class FormatFunctions extends Expression.FunctionImpl {
    private final Fun mFun;

    /* JADX INFO: renamed from: com.miui.maml.data.FormatFunctions$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$maml$data$FormatFunctions$Fun;

        static {
            int[] iArr = new int[Fun.values().length];
            $SwitchMap$com$miui$maml$data$FormatFunctions$Fun = iArr;
            try {
                iArr[Fun.FORMAT_DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$miui$maml$data$FormatFunctions$Fun[Fun.FORMAT_FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$miui$maml$data$FormatFunctions$Fun[Fun.FORMAT_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum Fun {
        INVALID,
        FORMAT_DATE,
        FORMAT_FLOAT,
        FORMAT_INT
    }

    private FormatFunctions(Fun fun, int i2) {
        super(i2);
        this.mFun = fun;
    }

    public static void load() {
        Expression.FunctionExpression.registerFunction("formatDate", new FormatFunctions(Fun.FORMAT_DATE, 2));
        Expression.FunctionExpression.registerFunction("formatFloat", new FormatFunctions(Fun.FORMAT_FLOAT, 2));
        Expression.FunctionExpression.registerFunction("formatInt", new FormatFunctions(Fun.FORMAT_INT, 2));
    }

    @Override // com.miui.maml.data.Expression.FunctionImpl
    public double evaluate(Expression[] expressionArr, Variables variables) {
        return 0.0d;
    }

    @Override // com.miui.maml.data.Expression.FunctionImpl
    public String evaluateStr(Expression[] expressionArr, Variables variables) {
        String strEvaluateStr = expressionArr[0].evaluateStr();
        if (strEvaluateStr == null) {
            return null;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$miui$maml$data$FormatFunctions$Fun[this.mFun.ordinal()];
        if (i2 == 1) {
            return DateTimeVariableUpdater.formatDate(strEvaluateStr, (long) expressionArr[1].evaluate());
        }
        if (i2 == 2) {
            return String.format(strEvaluateStr, Double.valueOf(expressionArr[1].evaluate()));
        }
        if (i2 == 3) {
            return String.format(strEvaluateStr, Integer.valueOf((int) expressionArr[1].evaluate()));
        }
        return null;
    }

    @Override // com.miui.maml.data.Expression.FunctionImpl
    public void reset() {
    }
}
