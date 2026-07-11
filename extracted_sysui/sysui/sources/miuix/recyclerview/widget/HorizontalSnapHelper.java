package miuix.recyclerview.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.controller.FolmeState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.IntValueProperty;

/* JADX INFO: loaded from: classes5.dex */
public class HorizontalSnapHelper extends SpringSnapHelper {
    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public int computeFinalDistance(int i2, int i3, int i4) {
        if (this.mRecyclerView.getLayoutDirection() == 0) {
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
        int i6 = this.mMax;
        int i7 = i6 - i2;
        if (i4 == 0) {
            int i8 = (i7 / i3) * i3;
            if (i7 % i3 <= i3 / 2) {
                i3 = 0;
            }
            return i6 - (i8 + i3);
        }
        if (i4 == 1) {
            return i6 - (((i7 / i3) * i3) + (i3 / 2));
        }
        if (i4 != 2) {
            return -1;
        }
        return i6 - ((i7 / i3) * i3);
    }

    @Override // miuix.recyclerview.widget.SpringSnapHelper
    public void init() {
        super.init();
        this.mFolmeState = (FolmeState) Folme.useValue(this);
        this.mProperty = new IntValueProperty("scrollX", 0.2f) { // from class: miuix.recyclerview.widget.HorizontalSnapHelper.1
            @Override // miuix.animation.property.IntValueProperty, miuix.animation.property.IIntValueProperty
            public int getIntValue(Object obj) {
                return HorizontalSnapHelper.this.mCurrentPosition;
            }

            @Override // miuix.animation.property.IntValueProperty, miuix.animation.property.IIntValueProperty
            public void setIntValue(Object obj, int i2) {
                HorizontalSnapHelper horizontalSnapHelper = HorizontalSnapHelper.this;
                int i3 = horizontalSnapHelper.mMin;
                int i4 = horizontalSnapHelper.mMax;
                horizontalSnapHelper.mCurrentPosition = i2;
                if (horizontalSnapHelper.mRecyclerView.getSpringEnabled()) {
                    HorizontalSnapHelper horizontalSnapHelper2 = HorizontalSnapHelper.this;
                    int i5 = horizontalSnapHelper2.mCurrentPosition;
                    int i6 = horizontalSnapHelper2.mLastPosition;
                    int i7 = i5 - i6;
                    if (i5 < i3 && i6 > i3) {
                        horizontalSnapHelper2.mRecyclerView.scrollBy(i3 - i6, 0);
                    } else if (i5 > i4 && i6 < i4) {
                        horizontalSnapHelper2.mRecyclerView.scrollBy(i4 - i6, 0);
                    } else if (i5 > i3 && i6 < i3) {
                        horizontalSnapHelper2.mRecyclerView.scrollBy(i5 - i3, 0);
                    } else if (i5 < i4 && i6 > i4) {
                        horizontalSnapHelper2.mRecyclerView.scrollBy(i5 - i4, 0);
                    } else if (i5 >= i3 && i5 <= i4 && i6 >= i3 && i6 <= i4) {
                        horizontalSnapHelper2.mRecyclerView.scrollBy(i7, 0);
                        HorizontalSnapHelper.this.mSpringHelper.resetDistance();
                    }
                    HorizontalSnapHelper horizontalSnapHelper3 = HorizontalSnapHelper.this;
                    int i8 = horizontalSnapHelper3.mCurrentPosition;
                    if (i8 < i3 || i8 > i4) {
                        horizontalSnapHelper3.mRecyclerView.dispatchNestedPreScroll(i7, 0, null, null, 1);
                        HorizontalSnapHelper.this.mRecyclerView.invalidate();
                        HorizontalSnapHelper horizontalSnapHelper4 = HorizontalSnapHelper.this;
                        int i9 = horizontalSnapHelper4.mCurrentPosition;
                        if (i9 < i3) {
                            horizontalSnapHelper4.setSpringHorizontalDistance(horizontalSnapHelper4.mSpringHelper, i9);
                        } else {
                            horizontalSnapHelper4.setSpringHorizontalDistance(horizontalSnapHelper4.mSpringHelper, i9 - i4);
                        }
                    }
                } else {
                    HorizontalSnapHelper horizontalSnapHelper5 = HorizontalSnapHelper.this;
                    horizontalSnapHelper5.mCurrentPosition = Math.max(horizontalSnapHelper5.mCurrentPosition, i3);
                    HorizontalSnapHelper horizontalSnapHelper6 = HorizontalSnapHelper.this;
                    horizontalSnapHelper6.mCurrentPosition = Math.min(horizontalSnapHelper6.mCurrentPosition, i4);
                    HorizontalSnapHelper horizontalSnapHelper7 = HorizontalSnapHelper.this;
                    horizontalSnapHelper7.mRecyclerView.scrollBy(horizontalSnapHelper7.mCurrentPosition - horizontalSnapHelper7.mLastPosition, 0);
                }
                HorizontalSnapHelper horizontalSnapHelper8 = HorizontalSnapHelper.this;
                horizontalSnapHelper8.mLastPosition = horizontalSnapHelper8.mCurrentPosition;
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
        int iComputeFinalDistance = computeFinalDistance(i4, this.mItemWidth, this.mSnapPreference);
        int i5 = this.mMin;
        this.mOutBounds = iComputeFinalDistance < i5 || iComputeFinalDistance > this.mMax;
        int iMin = Math.min(this.mMax, Math.max(i5, iComputeFinalDistance));
        float frictionTo = SpringSnapHelper.getFrictionTo(f2, this.mCurrentPosition, this.mProperty, iMin, this.mVelocityThreshold);
        if (this.mOutBounds) {
            frictionTo = Math.min(frictionTo, this.mFriction);
        }
        final AnimConfig ease = new AnimConfig().setEase(-2, this.mDamping, this.mResponse);
        final AnimState animStateAdd = new AnimState().add(this.mProperty, iMin, 4);
        AnimConfig animConfigAddListeners = new AnimConfig().setEase(-4, frictionTo).addListeners(new TransitionListener() { // from class: miuix.recyclerview.widget.HorizontalSnapHelper.2
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                HorizontalSnapHelper.this.mOutBounds = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                HorizontalSnapHelper.this.mOutBounds = false;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindBy = UpdateInfo.findBy(collection, HorizontalSnapHelper.this.mProperty);
                if (updateInfoFindBy == null) {
                    return;
                }
                if (updateInfoFindBy.getFloatValue() > HorizontalSnapHelper.this.mMax || updateInfoFindBy.getFloatValue() < HorizontalSnapHelper.this.mMin) {
                    HorizontalSnapHelper.this.mFolmeState.to(animStateAdd, ease);
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
        if (this.mRecyclerView.getLayoutManager() == null || this.mRecyclerView.getAdapter() == null) {
            return;
        }
        if (this.mItemWidth == Integer.MAX_VALUE) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
            linearLayoutManager.getDecoratedBoundsWithMargins(linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition()), this.mBounds);
            this.mItemWidth = this.mBounds.width();
        }
        int itemCount = this.mRecyclerView.getAdapter().getItemCount();
        this.mMin = 0;
        this.mMax = Math.max((itemCount * this.mItemWidth) - this.mRecyclerView.getWidth(), 0);
        int iComputeHorizontalScrollOffset = this.mRecyclerView.computeHorizontalScrollOffset() + this.mSpringHelper.getHorizontalDistance();
        this.mCurrentPosition = iComputeHorizontalScrollOffset;
        this.mLastPosition = iComputeHorizontalScrollOffset;
    }
}
