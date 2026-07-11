package miuix.animation.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Piecewise implements Function {
    private final double[] durations;
    private final List<Function> functions;

    public Piecewise(int i2) {
        this.functions = new ArrayList(i2);
        this.durations = new double[i2];
    }

    public void add(Function function, double d2) {
        this.durations[this.functions.size()] = d2;
        this.functions.add(function);
    }

    @Override // miuix.animation.function.Function
    public double apply(double d2) {
        int iBinarySearch = Arrays.binarySearch(this.durations, d2);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        if (iBinarySearch >= this.functions.size()) {
            return Double.NaN;
        }
        if (iBinarySearch > 0) {
            d2 -= this.durations[iBinarySearch - 1];
        }
        return this.functions.get(iBinarySearch).apply(d2);
    }
}
