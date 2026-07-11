package z;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.CarouselLayoutManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7120a;

    public class a extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ CarouselLayoutManager f7121b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(int i2, CarouselLayoutManager carouselLayoutManager) {
            super(i2, null);
            this.f7121b = carouselLayoutManager;
        }

        @Override // z.e
        public float d(RecyclerView.LayoutParams layoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }

        @Override // z.e
        public int e() {
            return this.f7121b.getHeight();
        }

        @Override // z.e
        public int f() {
            return e();
        }

        @Override // z.e
        public int g() {
            return this.f7121b.getPaddingLeft();
        }

        @Override // z.e
        public int h() {
            return this.f7121b.getWidth() - this.f7121b.getPaddingRight();
        }

        @Override // z.e
        public int i() {
            return j();
        }

        @Override // z.e
        public int j() {
            return 0;
        }

        @Override // z.e
        public void k(View view, int i2, int i3) {
            this.f7121b.layoutDecoratedWithMargins(view, g(), i2, h(), i3);
        }

        @Override // z.e
        public void l(View view, Rect rect, float f2, float f3) {
            view.offsetTopAndBottom((int) (f3 - (rect.top + f2)));
        }
    }

    public class b extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ CarouselLayoutManager f7122b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(int i2, CarouselLayoutManager carouselLayoutManager) {
            super(i2, null);
            this.f7122b = carouselLayoutManager;
        }

        @Override // z.e
        public float d(RecyclerView.LayoutParams layoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        }

        @Override // z.e
        public int e() {
            return this.f7122b.getHeight() - this.f7122b.getPaddingBottom();
        }

        @Override // z.e
        public int f() {
            return this.f7122b.isLayoutRtl() ? g() : h();
        }

        @Override // z.e
        public int g() {
            return 0;
        }

        @Override // z.e
        public int h() {
            return this.f7122b.getWidth();
        }

        @Override // z.e
        public int i() {
            return this.f7122b.isLayoutRtl() ? h() : g();
        }

        @Override // z.e
        public int j() {
            return this.f7122b.getPaddingTop();
        }

        @Override // z.e
        public void k(View view, int i2, int i3) {
            this.f7122b.layoutDecoratedWithMargins(view, i2, j(), i3, e());
        }

        @Override // z.e
        public void l(View view, Rect rect, float f2, float f3) {
            view.offsetLeftAndRight((int) (f3 - (rect.left + f2)));
        }
    }

    public /* synthetic */ e(int i2, a aVar) {
        this(i2);
    }

    public static e a(CarouselLayoutManager carouselLayoutManager) {
        return new b(0, carouselLayoutManager);
    }

    public static e b(CarouselLayoutManager carouselLayoutManager, int i2) {
        if (i2 == 0) {
            return a(carouselLayoutManager);
        }
        if (i2 == 1) {
            return c(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static e c(CarouselLayoutManager carouselLayoutManager) {
        return new a(1, carouselLayoutManager);
    }

    public abstract float d(RecyclerView.LayoutParams layoutParams);

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract void k(View view, int i2, int i3);

    public abstract void l(View view, Rect rect, float f2, float f3);

    public e(int i2) {
        this.f7120a = i2;
    }
}
