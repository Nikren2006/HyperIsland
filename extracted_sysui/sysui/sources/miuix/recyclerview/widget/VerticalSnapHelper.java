package miuix.recyclerview.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.IntValueProperty;

/* JADX INFO: loaded from: classes5.dex */
public class VerticalSnapHelper extends SpringSnapHelper {
    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public int computeFinalDistance(int i2, int i3, int i4) {
        if (i4 == 0) {
            int i5 = (i2 / i3) * i3;
            if (i2 % i3 <= i3 / 2) {
                i3 = 0;
            }
            return i5 + i3;
        }
        if (i4 == 1) {
            return ((i2 / i3) * i3) + (i3 / 2);
        }
        if (i4 != 2) {
            return -1;
        }
        return (i2 / i3) * i3;
    }

    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public void init() {
        super.init();
        this.mProperty = new IntValueProperty("scrollY", 0.2f) { // from class: miuix.recyclerview.widget.VerticalSnapHelper.1
            @Override // miuix.animation.property.IntValueProperty, miuix.animation.property.IIntValueProperty
            public int getIntValue(Object obj) {
                return VerticalSnapHelper.this.mCurrentPosition;
            }

            @Override // miuix.animation.property.IntValueProperty, miuix.animation.property.IIntValueProperty
            public void setIntValue(Object obj, int i2) {
                VerticalSnapHelper verticalSnapHelper = VerticalSnapHelper.this;
                int i3 = verticalSnapHelper.mMin;
                int i4 = verticalSnapHelper.mMax;
                verticalSnapHelper.mCurrentPosition = i2;
                if (verticalSnapHelper.mRecyclerView.getSpringEnabled()) {
                    VerticalSnapHelper verticalSnapHelper2 = VerticalSnapHelper.this;
                    int i5 = verticalSnapHelper2.mCurrentPosition;
                    int i6 = verticalSnapHelper2.mLastPosition;
                    int i7 = i5 - i6;
                    if (i5 < i3 && i6 > i3) {
                        verticalSnapHelper2.mRecyclerView.scrollBy(i3 - i6, 0);
                    } else if (i5 > i4 && i6 < i4) {
                        verticalSnapHelper2.mRecyclerView.scrollBy(i4 - i6, 0);
                    } else if (i5 > i3 && i6 < i3) {
                        verticalSnapHelper2.mRecyclerView.scrollBy(i5 - i3, 0);
                    } else if (i5 < i4 && i6 > i4) {
                        verticalSnapHelper2.mRecyclerView.scrollBy(i5 - i4, 0);
                    } else if (i5 >= i3 && i5 <= i4 && i6 >= i3 && i6 <= i4) {
                        verticalSnapHelper2.mRecyclerView.scrollBy(0, i7);
                        VerticalSnapHelper.this.mSpringHelper.resetDistance();
                    }
                    VerticalSnapHelper verticalSnapHelper3 = VerticalSnapHelper.this;
                    int i8 = verticalSnapHelper3.mCurrentPosition;
                    if (i8 < i3 || i8 > i4) {
                        verticalSnapHelper3.mRecyclerView.dispatchNestedPreScroll(0, i7, null, null, 1);
                        VerticalSnapHelper.this.mRecyclerView.invalidate();
                        VerticalSnapHelper verticalSnapHelper4 = VerticalSnapHelper.this;
                        int i9 = verticalSnapHelper4.mCurrentPosition;
                        if (i9 < i3) {
                            verticalSnapHelper4.setSpringVerticalDistance(verticalSnapHelper4.mSpringHelper, i9);
                        } else {
                            verticalSnapHelper4.setSpringVerticalDistance(verticalSnapHelper4.mSpringHelper, i9 - i4);
                        }
                    }
                } else {
                    VerticalSnapHelper verticalSnapHelper5 = VerticalSnapHelper.this;
                    verticalSnapHelper5.mCurrentPosition = Math.max(verticalSnapHelper5.mCurrentPosition, i3);
                    VerticalSnapHelper verticalSnapHelper6 = VerticalSnapHelper.this;
                    verticalSnapHelper6.mCurrentPosition = Math.min(verticalSnapHelper6.mCurrentPosition, i4);
                    VerticalSnapHelper verticalSnapHelper7 = VerticalSnapHelper.this;
                    verticalSnapHelper7.mRecyclerView.scrollBy(0, verticalSnapHelper7.mCurrentPosition - verticalSnapHelper7.mLastPosition);
                }
                VerticalSnapHelper verticalSnapHelper8 = VerticalSnapHelper.this;
                verticalSnapHelper8.mLastPosition = verticalSnapHelper8.mCurrentPosition;
            }
        };
    }

    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public void snapFromFling(RecyclerView.LayoutManager layoutManager, int i2) {
        this.mFolmeState.getTarget().setVelocity(this.mProperty, i2);
        float f2 = i2;
        float fPredictDistance = SpringSnapHelper.predictDistance(f2, this.mProperty, this.mFriction, this.mVelocityThreshold);
        int i3 = this.mCurrentPosition;
        int i4 = (int) (i3 + fPredictDistance);
        if ((i3 == this.mMax || i3 == this.mMin) && fPredictDistance == 0.0f) {
            return;
        }
        int iComputeFinalDistance = computeFinalDistance(i4, this.mItemHeight, this.mSnapPreference);
        int i5 = this.mMin;
        this.mOutBounds = iComputeFinalDistance < i5 || iComputeFinalDistance > this.mMax;
        int iMin = Math.min(this.mMax, Math.max(i5, iComputeFinalDistance));
        float frictionTo = SpringSnapHelper.getFrictionTo(f2, this.mCurrentPosition, this.mProperty, iMin, this.mVelocityThreshold);
        if (this.mOutBounds) {
            frictionTo = Math.min(frictionTo, this.mFriction);
        }
        final AnimConfig ease = new AnimConfig().setEase(-2, this.mDamping, this.mResponse);
        final AnimState animStateAdd = new AnimState().add(this.mProperty, iMin, 4);
        AnimConfig animConfigAddListeners = new AnimConfig().setEase(-4, frictionTo).addListeners(new TransitionListener() { // from class: miuix.recyclerview.widget.VerticalSnapHelper.2
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                VerticalSnapHelper.this.mOutBounds = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                VerticalSnapHelper.this.mOutBounds = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, VerticalSnapHelper.this.mProperty);
                if (updateInfoFindBy == null) {
                    return;
                }
                if (updateInfoFindBy.getFloatValue() > VerticalSnapHelper.this.mMax || updateInfoFindBy.getFloatValue() < VerticalSnapHelper.this.mMin) {
                    VerticalSnapHelper.this.mFolmeState.to(animStateAdd, ease);
                }
            }
        });
        if (Math.abs(i2) < this.mVelocityThreshold || frictionTo <= 0.0f) {
            this.mFolmeState.to(animStateAdd, ease);
        } else {
            this.mFolmeState.to(animStateAdd, animConfigAddListeners);
        }
    }

    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public void updateConstructData() {
        if (this.mItemHeight == Integer.MAX_VALUE) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
            linearLayoutManager.getDecoratedBoundsWithMargins(linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition()), this.mBounds);
            this.mItemHeight = this.mBounds.height();
        }
        int itemCount = this.mRecyclerView.getAdapter().getItemCount();
        this.mMin = 0;
        this.mMax = Math.max((itemCount * this.mItemHeight) - this.mRecyclerView.getHeight(), 0);
        int iComputeVerticalScrollOffset = this.mRecyclerView.computeVerticalScrollOffset() + this.mSpringHelper.getVerticalDistance();
        this.mCurrentPosition = iComputeVerticalScrollOffset;
        this.mLastPosition = iComputeVerticalScrollOffset;
    }
}
