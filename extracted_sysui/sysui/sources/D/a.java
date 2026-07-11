package D;

import android.R;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes2.dex */
public class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Dialog f55a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f56b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f57c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f58d;

    public a(Dialog dialog, Rect rect) {
        this.f55a = dialog;
        this.f56b = rect.left;
        this.f57c = rect.top;
        this.f58d = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View viewFindViewById = view.findViewById(R.id.content);
        int left = this.f56b + viewFindViewById.getLeft();
        int width = viewFindViewById.getWidth() + left;
        if (new RectF(left, this.f57c + viewFindViewById.getTop(), width, viewFindViewById.getHeight() + r3).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        if (motionEvent.getAction() == 1) {
            motionEventObtain.setAction(4);
        }
        view.performClick();
        return this.f55a.onTouchEvent(motionEventObtain);
    }
}
