package miuix.animation.property;

/* JADX INFO: loaded from: classes4.dex */
public class IntValueProperty<T> extends ValueProperty<T> implements IIntValueProperty<T> {
    public IntValueProperty(String str) {
        super(str, 1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.animation.property.IIntValueProperty
    public int getIntValue(T t2) {
        Integer num;
        if (!(t2 instanceof ValueTargetObject) || (num = (Integer) ((ValueTargetObject) t2).getPropertyValue(getName(), Integer.TYPE)) == null) {
            return Integer.MAX_VALUE;
        }
        return num.intValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.animation.property.IIntValueProperty
    public void setIntValue(T t2, int i2) {
        if (t2 instanceof ValueTargetObject) {
            ((ValueTargetObject) t2).setPropertyValue(getName(), Integer.TYPE, Integer.valueOf(i2));
        }
    }

    @Override // miuix.animation.property.ValueProperty, miuix.animation.property.FloatProperty
    public String toString() {
        return "IntValueProperty@" + hashCode() + "{name='" + getName() + "',min='" + this.mMinVisibleChange + "'}";
    }

    public IntValueProperty(String str, float f2) {
        super(str, f2);
    }
}
