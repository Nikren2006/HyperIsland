package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.VibrationAttributes;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import miuix.animation.Folme;
import miuix.animation.IVisibleStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;
import miuix.core.util.WindowUtils;
import miuix.internal.util.ViewUtils;
import miuix.miuixbasewidget.R;
import miuix.theme.Typography;
import miuix.util.HapticFeedbackCompat;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
public class AlphabetIndexer extends LinearLayout {
    private static final int MSG_FADE = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SCROLL = 1;
    private static final String STARRED_LABEL = "♥";
    public static final String STARRED_TITLE = "!";
    public static final int STATE_NONE = 0;
    private final int INVALID_INDEX;
    private AccessibilityManager mAccessibilityManager;

    @Nullable
    private Adapter mAdapter;
    private boolean mCancelOverlayTextColorAnim;
    private float mClickDownY;
    private boolean mDrawOverlay;
    private boolean mEnableAutoDismiss;
    private ImageView mFirstOmitItem;
    private int mFirstVisibleItemPos;
    private boolean mForceUpdate;
    private boolean mForceUpdateVisibleIndexes;
    private int mGroupCount;
    private int mGroupItemCount;
    private Handler mHandler;
    private HapticFeedbackCompat mHapticFeedbackCompat;
    private int mIndexMinWidth;
    private int mIndexWidth;
    private SectionIndexer mIndexer;
    private int mItemHeight;
    private int mItemMargin;
    private int mLastAlphabetIndex;
    private View mLastSelectedItem;
    private int mLeftCount;
    private int mListScrollState;
    private int mMaxItemMargin;
    private int mMinItemMargin;
    private int mOmitItemHeight;
    private int mOriginalMarginEnd;
    private TextView mOverlay;
    private Drawable mOverlayBackground;
    private int mOverlayHeight;
    private AnimConfig mOverlayHideAnimConfig;
    private AnimConfig mOverlayShowAnimConfig;
    private int mOverlayTextAppearanceRes;
    private int mOverlayTextColor;
    private TextPaint mOverlayTextPaint;
    private int mOverlayTextSize;
    private int mOverlayWidth;
    private final View.OnLayoutChangeListener mParentLayoutChangeListener;
    private View mParentView;
    private int mScreenHeightDp;
    HashMap<Object, Integer> mSectionMap;
    private int mSelectedAlphaIndex;
    private TextHighlighter mTextHighlighter;
    private float mTransFormY;
    private VibrationAttributes mUsageAlarmVibrationAttributes;
    private boolean mUseOmit;
    private int mVerticalPosition;
    private int mViewHeight;

    public interface Adapter {
        int getFirstVisibleItemPosition();

        int getItemCount();

        int getListHeaderCount();

        void scrollToPosition(int i2);

        void stopScroll();
    }

    public class ScrollTargetInfo {
        int targetItemPos;
        int targetSectionIndex;

        public ScrollTargetInfo() {
        }
    }

    public static class TextHighlighter {
        int mActivatedColor;
        int mHighlightColor;
        int mIndexerTextSize;
        String[] mIndexes;
        String[] mMinVisibleIndexes;
        int mNormalColor;
        boolean mSectionsAsIndexesEnabled = false;
        String[] mVisibleIndexes;

        public TextHighlighter(Context context, TypedArray typedArray) {
            Resources resources = context.getResources();
            CharSequence[] textArray = typedArray.getTextArray(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatIndexerTable);
            if (textArray != null) {
                this.mIndexes = new String[textArray.length];
                int length = textArray.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    this.mIndexes[i3] = textArray[i2].toString();
                    i2++;
                    i3++;
                }
            } else {
                this.mIndexes = resources.getStringArray(R.array.alphabet_table);
            }
            this.mMinVisibleIndexes = new String[]{AlphabetIndexer.STARRED_TITLE, "#"};
            ColorStateList colorStateList = AppCompatResources.getColorStateList(context, typedArray.getResourceId(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatIndexerTextColorList, R.color.miuix_appcompat_alphabet_indexer_text_light));
            this.mHighlightColor = colorStateList.getColorForState(new int[]{android.R.attr.state_selected}, resources.getColor(R.color.miuix_appcompat_alphabet_indexer_highlight_text_color));
            this.mActivatedColor = colorStateList.getColorForState(new int[]{android.R.attr.state_activated}, resources.getColor(R.color.miuix_appcompat_alphabet_indexer_activated_text_color));
            this.mNormalColor = colorStateList.getColorForState(new int[0], resources.getColor(R.color.miuix_appcompat_alphabet_indexer_text_color));
            this.mIndexerTextSize = typedArray.getDimensionPixelSize(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatIndexerTextSize, resources.getDimensionPixelSize(R.dimen.miuix_appcompat_alphabet_indexer_text_size));
        }

        @NonNull
        public String[] getVisibleIndexes(SectionIndexer sectionIndexer, boolean z2) {
            String[] strArr;
            if (sectionIndexer == null || !this.mSectionsAsIndexesEnabled) {
                return this.mIndexes;
            }
            Object[] sections = sectionIndexer.getSections();
            if (sections != null && sections.length == 0 && (strArr = this.mMinVisibleIndexes) != null && strArr.length > 0) {
                String[] strArr2 = new String[strArr.length];
                this.mVisibleIndexes = strArr2;
                System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                return this.mVisibleIndexes;
            }
            if (sections == null || sections.length == 0) {
                return this.mIndexes;
            }
            if (this.mVisibleIndexes == null || z2) {
                this.mVisibleIndexes = new String[sections.length];
                for (int i2 = 0; i2 < sections.length; i2++) {
                    this.mVisibleIndexes[i2] = (String) sections[i2];
                }
            }
            return this.mVisibleIndexes;
        }
    }

    public AlphabetIndexer(Context context) {
        this(context, null);
    }

    private void announceAccessibilityEvent(String str) {
        if (TextUtils.equals(str, STARRED_TITLE)) {
            str = getContext().getString(R.string.miuix_indexer_collect);
        }
        announceForAccessibility(str);
    }

    private void announceSelectText() {
        SectionIndexer sectionIndexer = getSectionIndexer();
        if (sectionIndexer == null) {
            return;
        }
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(sectionIndexer, false);
        int i2 = this.mSelectedAlphaIndex;
        if (i2 < 0 || i2 >= visibleIndexes.length) {
            return;
        }
        announceAccessibilityEvent(visibleIndexes[i2]);
    }

    private int calculateIndex(float f2) {
        int height = this.mItemHeight + (this.mItemMargin * 2);
        View childAt = getChildAt(0);
        if (childAt != null) {
            height = (((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).topMargin * 2) + childAt.getHeight();
        }
        int length = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false).length;
        int childCount = getChildCount();
        float f3 = height;
        if (f2 <= f3 || (length == childCount && !this.mUseOmit)) {
            return (int) (f2 / f3);
        }
        int i2 = height * 2;
        if (f2 > (getHeight() - getPaddingTop()) - i2) {
            return (length - 2) + (((int) (f2 - ((getHeight() - getPaddingTop()) - i2))) / height);
        }
        int height2 = this.mOmitItemHeight + (this.mItemMargin * 2);
        ImageView imageView = this.mFirstOmitItem;
        if (imageView != null) {
            height2 = imageView.getHeight() + (this.mItemMargin * 2);
        }
        int i3 = height2 + height;
        int i4 = (int) (f2 - f3);
        int i5 = i4 / i3;
        int i6 = i4 % i3 > height ? 1 : 0;
        int i7 = this.mLeftCount;
        if (i5 < i7) {
            return ((this.mGroupItemCount + 1) * i5) + 1 + i6;
        }
        int i8 = this.mGroupItemCount;
        return ((i8 + 1) * i7) + 1 + (i8 * (i5 - i7)) + i6;
    }

    private int calculateOverlayPosition(int i2) {
        int childIndex = getChildIndex(i2);
        View childAt = getChildAt(childIndex);
        if (childAt == null) {
            return 0;
        }
        int top = (childAt.getTop() + childAt.getBottom()) / 2;
        if (top <= 0) {
            top = (int) (((((double) (childIndex + 1)) + 0.5d) * ((double) this.mItemHeight)) + ((double) getPaddingTop()));
        }
        return top + getMarginTop();
    }

    private void checkSelectedItemChanged() {
        int realSectionPosition;
        SectionIndexer sectionIndexer = getSectionIndexer();
        Adapter adapter = this.mAdapter;
        if (adapter == null || sectionIndexer == null || this.mSelectedAlphaIndex == (realSectionPosition = getRealSectionPosition(sectionIndexer.getSectionForPosition(adapter.getFirstVisibleItemPosition()), sectionIndexer))) {
            return;
        }
        setChecked(realSectionPosition);
    }

    private void clearLastChecked(int i2) {
        if (i2 < 0) {
            return;
        }
        View childAt = getChildAt(getChildIndex(i2));
        if (childAt instanceof TextView) {
            ((TextView) childAt).setTextColor(this.mTextHighlighter.mNormalColor);
        } else if (childAt instanceof ImageView) {
            ((ImageView) childAt).setImageResource(R.drawable.miuix_ic_omit);
        }
    }

    private void constructItem(int i2) {
        this.mItemMargin = i2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 1;
        layoutParams.bottomMargin = i2;
        layoutParams.topMargin = i2;
        layoutParams.weight = 1.0f;
        for (String str : this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false)) {
            TextView textView = new TextView(getContext());
            Typography.applyMiSansMedium(textView);
            textView.setGravity(17);
            textView.setHeight(this.mItemHeight);
            textView.setIncludeFontPadding(false);
            textView.setTextColor(this.mTextHighlighter.mNormalColor);
            textView.setTextSize(0, this.mTextHighlighter.mIndexerTextSize);
            if (TextUtils.equals(str, STARRED_TITLE)) {
                str = STARRED_LABEL;
            }
            textView.setText(str);
            textView.setImportantForAccessibility(2);
            attachViewToParent(textView, -1, layoutParams);
        }
        this.mUseOmit = false;
    }

    private void constructItemWithOmit(int i2) {
        int i3;
        int i4;
        int marginTop = getMarginTop() <= 0 ? getMarginTop() + (this.mOverlayHeight / 2) + 1 : getMarginTop();
        int marginBottom = getMarginBottom() <= 0 ? getMarginBottom() + (this.mOverlayHeight / 2) + 1 : getMarginBottom();
        int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
        if (paddingTop + marginTop + marginBottom >= i2) {
            paddingTop -= marginTop + marginBottom;
        }
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false);
        int length = visibleIndexes.length;
        int i5 = this.mItemHeight;
        int i6 = this.mMinItemMargin;
        int i7 = i5 + (i6 * 2);
        int i8 = this.mOmitItemHeight + i7 + (i6 * 2);
        int i9 = paddingTop - (i7 * 3);
        int i10 = i9 / i8;
        this.mGroupCount = i10;
        if (i10 < 1) {
            this.mGroupCount = 1;
        }
        int i11 = i9 % i8;
        int i12 = length - 3;
        int i13 = this.mGroupCount;
        int i14 = i12 / i13;
        this.mGroupItemCount = i14;
        if (i14 < 2) {
            this.mGroupItemCount = 2;
            int i15 = i12 / 2;
            i11 += i8 * (i13 - i15);
            this.mGroupCount = i15;
        }
        int i16 = this.mGroupItemCount;
        int i17 = this.mGroupCount;
        this.mLeftCount = i12 - (i16 * i17);
        this.mItemMargin = i6;
        if (i11 > 0) {
            this.mItemMargin = i6 + ((i11 / 2) / ((i17 * 2) + 3));
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 1;
        int i18 = this.mItemMargin;
        layoutParams.bottomMargin = i18;
        layoutParams.topMargin = i18;
        layoutParams.weight = 1.0f;
        for (int i19 = 0; i19 < length; i19++) {
            int i20 = this.mGroupItemCount;
            int i21 = this.mLeftCount;
            if (i19 < (i20 + 1) * i21) {
                i20++;
                i3 = i19;
            } else {
                i3 = i19 - ((i20 + 1) * i21);
            }
            if (i19 <= 1 || i19 >= length - 2 || (i4 = (i3 - 1) % i20) == 0) {
                String str = visibleIndexes[i19];
                TextView textView = new TextView(getContext());
                Typography.applyMiSansMedium(textView);
                textView.setGravity(17);
                textView.setHeight(this.mItemHeight);
                textView.setIncludeFontPadding(false);
                textView.setTextColor(this.mTextHighlighter.mNormalColor);
                textView.setTextSize(0, this.mTextHighlighter.mIndexerTextSize);
                if (TextUtils.equals(str, STARRED_TITLE)) {
                    str = STARRED_LABEL;
                }
                textView.setText(str);
                textView.setImportantForAccessibility(2);
                attachViewToParent(textView, -1, layoutParams);
            } else if (i4 == 1) {
                ImageView imageView = new ImageView(getContext());
                if (this.mFirstOmitItem == null) {
                    this.mFirstOmitItem = imageView;
                }
                imageView.setMaxHeight(this.mOmitItemHeight);
                imageView.setMaxWidth(this.mOmitItemHeight);
                imageView.setImageResource(R.drawable.miuix_ic_omit);
                imageView.setImportantForAccessibility(2);
                attachViewToParent(imageView, -1, layoutParams);
            }
        }
        this.mUseOmit = true;
    }

    private void constructOverlay() {
        if (this.mDrawOverlay) {
            FrameLayout frameLayout = (FrameLayout) getParent();
            this.mOverlay = new TextView(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mOverlayWidth, this.mOverlayHeight, GravityCompat.END);
            layoutParams.topMargin = ((FrameLayout.LayoutParams) getLayoutParams()).topMargin;
            layoutParams.setMarginEnd(getRealWidthOfIndexer() + getMarinEnd());
            this.mOverlay.setLayoutParams(layoutParams);
            this.mOverlay.setTextAlignment(5);
            this.mOverlay.setBackgroundDrawable(this.mOverlayBackground);
            this.mOverlay.setGravity(16);
            this.mOverlay.setTextSize(0, this.mOverlayTextSize);
            this.mOverlay.setTextColor(this.mOverlayTextColor);
            this.mOverlay.setVisibility(0);
            this.mOverlay.setAlpha(0.0f);
            this.mOverlay.setScaleX(0.0f);
            this.mOverlay.setScaleY(0.0f);
            this.mOverlay.setTextAppearance(this.mOverlayTextAppearanceRes);
            this.mOverlayTextPaint = this.mOverlay.getPaint();
            frameLayout.addView(this.mOverlay);
        }
    }

    @RequiresApi(api = 30)
    private void doPerformHapticFeedback(int i2) {
        getHapticFeedbackCompat().performHapticFeedback(getUsageAlarmVibrationAttributes(), i2);
    }

    private void drawThumb(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        int letterIndex = getLetterIndex(charSequence.toString().toUpperCase());
        Adapter adapter = this.mAdapter;
        boolean z2 = false;
        if (adapter != null) {
            if (adapter.getFirstVisibleItemPosition() > this.mFirstVisibleItemPos && letterIndex < this.mSelectedAlphaIndex) {
                z2 = true;
            }
            this.mFirstVisibleItemPos = this.mAdapter.getFirstVisibleItemPosition();
        }
        if (this.mSelectedAlphaIndex == letterIndex || z2) {
            return;
        }
        setChecked(letterIndex);
    }

    private void drawThumbInternal(CharSequence charSequence, float f2) {
        if (this.mAdapter == null || this.mOverlay == null) {
            return;
        }
        this.mCancelOverlayTextColorAnim = true;
        if (TextUtils.equals(charSequence, STARRED_TITLE)) {
            charSequence = STARRED_LABEL;
        }
        if (!TextUtils.equals(this.mOverlay.getText(), charSequence)) {
            if (HapticCompat.doesSupportHaptic(HapticCompat.HapticVersion.HAPTIC_VERSION_2)) {
                doPerformHapticFeedback(HapticFeedbackConstants.MIUI_GEAR_LIGHT);
            } else {
                doPerformHapticFeedback(HapticFeedbackConstants.MIUI_MESH_NORMAL);
            }
        }
        this.mOverlay.setTranslationY(f2 - getMarginTop());
        updateOverlayTextAlpha(1.0f);
        this.mOverlay.setText(charSequence);
        this.mOverlay.setPaddingRelative((this.mOverlayHeight - ((int) this.mOverlayTextPaint.measureText(charSequence.toString()))) / 2, 0, 0, 0);
        this.mOverlay.setVisibility(0);
        showOverlay();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0043 A[PHI: r0
      0x0043: PHI (r0v9 int) = (r0v7 int), (r0v12 int) binds: [B:32:0x005f, B:22:0x0040] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getChildIndex(int r7) {
        /*
            r6 = this;
            miuix.miuixbasewidget.widget.AlphabetIndexer$TextHighlighter r0 = r6.mTextHighlighter
            android.widget.SectionIndexer r1 = r6.getSectionIndexer()
            r2 = 0
            java.lang.String[] r0 = r0.getVisibleIndexes(r1, r2)
            int r0 = r0.length
            int r1 = r0 + (-1)
            if (r7 <= r1) goto L12
            r3 = r1
            goto L13
        L12:
            r3 = r7
        L13:
            int r4 = r6.getChildCount()
            if (r4 == r0) goto L62
            int r4 = r6.mGroupItemCount
            r5 = 1
            if (r4 <= r5) goto L62
            if (r7 <= r5) goto L62
            int r0 = r0 + (-2)
            if (r7 < r0) goto L2f
            int r7 = r6.mGroupCount
            int r7 = r7 * 2
            int r7 = r7 + r5
            if (r3 != r1) goto L2c
            r2 = r5
        L2c:
            int r3 = r7 + r2
            goto L62
        L2f:
            int r0 = r6.mLeftCount
            if (r0 <= 0) goto L58
            int r1 = r4 + 1
            int r1 = r1 * r0
            if (r7 >= r1) goto L47
            int r4 = r4 + r5
            int r7 = r7 - r5
            int r0 = r7 / r4
            int r7 = r7 % r4
            int r0 = r0 * 2
            int r0 = r0 + r5
            if (r7 != 0) goto L43
            goto L44
        L43:
            r2 = r5
        L44:
            int r3 = r0 + r2
            goto L62
        L47:
            int r1 = r7 - r0
            int r1 = r1 - r5
            int r1 = r1 / r4
            int r7 = r7 - r0
            int r7 = r7 - r5
            int r7 = r7 % r4
            int r1 = r1 * 2
            int r1 = r1 + r5
            if (r7 != 0) goto L54
            goto L55
        L54:
            r2 = r5
        L55:
            int r3 = r1 + r2
            goto L62
        L58:
            int r7 = r7 - r5
            int r0 = r7 / r4
            int r7 = r7 % r4
            int r0 = r0 * 2
            int r0 = r0 + r5
            if (r7 != 0) goto L43
            goto L44
        L62:
            int r6 = r6.normalizeIndex(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.AlphabetIndexer.getChildIndex(int):int");
    }

    private HapticFeedbackCompat getHapticFeedbackCompat() {
        if (this.mHapticFeedbackCompat == null) {
            this.mHapticFeedbackCompat = new HapticFeedbackCompat(getContext());
        }
        return this.mHapticFeedbackCompat;
    }

    private int getLetterIndex(String str) {
        int i2 = this.mLastAlphabetIndex;
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false);
        for (int i3 = 0; i3 < visibleIndexes.length; i3++) {
            if (TextUtils.equals(str, visibleIndexes[i3])) {
                i2 = i3;
            }
        }
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    private int getListOffset() {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return 0;
        }
        return adapter.getListHeaderCount();
    }

    private int getMarginBottom() {
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).bottomMargin;
    }

    private int getMarginTop() {
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin;
    }

    private int getMarinEnd() {
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).getMarginEnd();
    }

    private int getRealSectionPosition(int i2, SectionIndexer sectionIndexer) {
        if (sectionIndexer == null) {
            return i2;
        }
        Object[] sections = sectionIndexer.getSections();
        String str = (sections == null || i2 < 0 || i2 >= sections.length) ? null : (String) sections[i2];
        return !TextUtils.isEmpty(str) ? getLetterIndex(str.toUpperCase()) : i2;
    }

    private int getRealWidthOfIndexer() {
        return Math.max(this.mIndexMinWidth, this.mIndexWidth);
    }

    private int getSafeSectionIndex(int i2, SectionIndexer sectionIndexer) {
        int length;
        Object[] sections = sectionIndexer == null ? null : sectionIndexer.getSections();
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false);
        if ((sections == null || sections.length == 0) && visibleIndexes.length > 0) {
            if (i2 < visibleIndexes.length) {
                return Math.max(i2, 0);
            }
            length = visibleIndexes.length;
        } else {
            if (sections == null || sections.length == 0 || (getHeight() - getPaddingTop()) - getPaddingBottom() <= 0 || i2 < 0) {
                return -1;
            }
            if (i2 >= visibleIndexes.length) {
                length = sections.length;
            } else {
                this.mSectionMap.clear();
                for (int i3 = 0; i3 < sections.length; i3++) {
                    this.mSectionMap.put(sections[i3].toString().toUpperCase(), Integer.valueOf(i3));
                }
                int i4 = 0;
                while (true) {
                    int i5 = i4 + i2;
                    if (i5 >= visibleIndexes.length && i2 < i4) {
                        return 0;
                    }
                    int i6 = i2 - i4;
                    if (i5 < visibleIndexes.length && this.mSectionMap.containsKey(visibleIndexes[i5])) {
                        Integer num = this.mSectionMap.get(visibleIndexes[i5]);
                        Objects.requireNonNull(num);
                        return num.intValue();
                    }
                    if (i6 >= 0 && this.mSectionMap.containsKey(visibleIndexes[i6])) {
                        Integer num2 = this.mSectionMap.get(visibleIndexes[i6]);
                        Objects.requireNonNull(num2);
                        return num2.intValue();
                    }
                    i4++;
                }
            }
        }
        return length - 1;
    }

    private ScrollTargetInfo getScrollTargetInfo(int i2, SectionIndexer sectionIndexer) {
        ScrollTargetInfo scrollTargetInfo = new ScrollTargetInfo();
        int listOffset = getListOffset();
        if (sectionIndexer == null || sectionIndexer.getSections() == null || sectionIndexer.getSections().length <= 0) {
            scrollTargetInfo.targetItemPos = listOffset;
        } else {
            scrollTargetInfo.targetItemPos = sectionIndexer.getPositionForSection(i2) + listOffset;
        }
        scrollTargetInfo.targetSectionIndex = i2;
        return scrollTargetInfo;
    }

    private SectionIndexer getSectionIndexer() {
        return this.mIndexer;
    }

    @RequiresApi(api = 30)
    private VibrationAttributes getUsageAlarmVibrationAttributes() {
        if (this.mUsageAlarmVibrationAttributes == null) {
            this.mUsageAlarmVibrationAttributes = new VibrationAttributes.Builder().setUsage(17).build();
        }
        return this.mUsageAlarmVibrationAttributes;
    }

    public static int getViewHeight(View view) {
        Point point = new Point();
        WindowUtils.getWindowSize(view.getContext(), point);
        view.measure(View.MeasureSpec.makeMeasureSpec(point.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(point.y, Integer.MIN_VALUE));
        return view.getMeasuredHeight();
    }

    private void handleAccessibilityAction(int i2, int i3, SectionIndexer sectionIndexer) {
        int i4;
        if (sectionIndexer == null) {
            return;
        }
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(sectionIndexer, false);
        if (i3 == 4096) {
            i4 = i2 - 1;
            while (i4 >= 0) {
                Object[] sections = sectionIndexer.getSections();
                if (sections == null || sections.length <= 0) {
                    if (sections != null) {
                        i2 = i4;
                        break;
                    }
                    i4--;
                } else {
                    if (Arrays.asList(sections).contains(visibleIndexes[i4])) {
                        i2 = i4;
                        break;
                    }
                    i4--;
                }
            }
        } else if (i3 == 8192) {
            i4 = i2 + 1;
            while (i4 <= visibleIndexes.length - 1) {
                Object[] sections2 = sectionIndexer.getSections();
                if (sections2 == null || sections2.length <= 0) {
                    if (sections2 != null) {
                        i2 = i4;
                        break;
                    }
                    i4++;
                } else {
                    if (Arrays.asList(sections2).contains(visibleIndexes[i4])) {
                        i2 = i4;
                        break;
                    }
                    i4++;
                }
            }
        }
        scrollToSelection(i2, sectionIndexer);
        setChecked(i2);
    }

    private void handleAccessibilityMotionEvent(boolean z2, SectionIndexer sectionIndexer, float f2) {
        int iCalculateIndex;
        int i2 = this.mSelectedAlphaIndex;
        if (this.mAccessibilityManager.isEnabled() && this.mAccessibilityManager.isTouchExplorationEnabled() && i2 >= 0 && z2) {
            View childAt = getChildAt(getChildIndex(i2));
            float top = (childAt.getTop() + childAt.getBottom()) / 2.0f;
            float paddingTop = top > ((float) getPaddingTop()) ? top - getPaddingTop() : 0.0f;
            this.mTransFormY = paddingTop;
            iCalculateIndex = calculateIndex(paddingTop);
        } else {
            iCalculateIndex = calculateIndex(f2);
        }
        ScrollTargetInfo scrollTargetInfoScrollToSelection = scrollToSelection(iCalculateIndex, sectionIndexer);
        if (scrollTargetInfoScrollToSelection != null) {
            int i3 = this.mSelectedAlphaIndex;
            int i4 = scrollTargetInfoScrollToSelection.targetSectionIndex;
            if (i3 != i4) {
                setChecked(i4);
            }
        }
    }

    private boolean hasShown() {
        TextView textView = this.mOverlay;
        return textView != null && textView.getVisibility() == 0 && this.mOverlay.getAlpha() == 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideOverlay() {
        TextView textView = this.mOverlay;
        if (textView != null) {
            Folme.useAt(textView).visible().setFlags(1L).setScale(1.0f, IVisibleStyle.VisibleType.SHOW).setScale(0.0f, IVisibleStyle.VisibleType.HIDE).hide(this.mOverlayHideAnimConfig);
        }
    }

    private void init() {
        this.mVerticalPosition = GravityCompat.END;
        setGravity(1);
        setOrientation(1);
        initAnimConfig();
        constructItem(this.mMaxItemMargin);
        setClickable(true);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mScreenHeightDp = getResources().getConfiguration().screenHeightDp;
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    private void initAnimConfig() {
        AnimConfig animConfig = new AnimConfig();
        this.mOverlayShowAnimConfig = animConfig;
        animConfig.addListeners(new TransitionListener() { // from class: miuix.miuixbasewidget.widget.AlphabetIndexer.2
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                if (AlphabetIndexer.this.isPressed() || !AlphabetIndexer.this.mEnableAutoDismiss) {
                    return;
                }
                AlphabetIndexer.this.stop(0);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                for (UpdateInfo updateInfo : collection) {
                    if (updateInfo.property == ViewProperty.SCALE_X) {
                        AlphabetIndexer.this.updateOverlayTranslationX(updateInfo.getFloatValue());
                        return;
                    }
                }
            }
        });
        AnimConfig animConfig2 = new AnimConfig();
        this.mOverlayHideAnimConfig = animConfig2;
        animConfig2.addListeners(new TransitionListener() { // from class: miuix.miuixbasewidget.widget.AlphabetIndexer.3
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj, Collection<UpdateInfo> collection) {
                super.onBegin(obj, collection);
                Iterator<UpdateInfo> it = collection.iterator();
                while (it.hasNext()) {
                    if (it.next().property == ViewProperty.AUTO_ALPHA) {
                        AlphabetIndexer.this.mCancelOverlayTextColorAnim = false;
                        return;
                    }
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                for (UpdateInfo updateInfo : collection) {
                    FloatProperty floatProperty = updateInfo.property;
                    if (floatProperty == ViewProperty.SCALE_X) {
                        AlphabetIndexer.this.updateOverlayTranslationX(updateInfo.getFloatValue());
                    } else if (floatProperty == ViewProperty.AUTO_ALPHA && !AlphabetIndexer.this.mCancelOverlayTextColorAnim) {
                        AlphabetIndexer.this.updateOverlayTextAlpha(updateInfo.getFloatValue());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$new$0(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
        if (rootWindowInsets != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                Insets insets = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                if (this.mOriginalMarginEnd < 0) {
                    this.mOriginalMarginEnd = layoutParams2.getMarginEnd();
                }
                if (ViewUtils.isLayoutRtl(view)) {
                    layoutParams2.setMarginEnd(this.mOriginalMarginEnd + insets.left);
                } else {
                    layoutParams2.setMarginEnd(this.mOriginalMarginEnd + insets.right);
                }
                view.setLayoutParams(layoutParams2);
            }
        }
        return windowInsetsCompat;
    }

    private int normalizeIndex(int i2) {
        if (i2 < 0) {
            return 0;
        }
        return i2 >= getChildCount() ? getChildCount() - 1 : i2;
    }

    private void parseAttrs(AttributeSet attributeSet, int i2) {
        Resources resources = getContext().getResources();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MiuixAppcompatAlphabetIndexer, i2, R.style.Widget_AlphabetIndexer_Starred_DayNight);
        this.mTextHighlighter = new TextHighlighter(getContext(), typedArrayObtainStyledAttributes);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatDrawOverlay, true);
        this.mDrawOverlay = z2;
        if (z2) {
            this.mOverlayTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatOverlayTextSize, resources.getDimensionPixelSize(R.dimen.miuix_appcompat_alphabet_indexer_overlay_text_size));
            this.mOverlayTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatOverlayTextColor, resources.getColor(R.color.miuix_appcompat_alphabet_indexer_overlay_text_color));
            this.mOverlayTextAppearanceRes = typedArrayObtainStyledAttributes.getResourceId(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppCompatOverlayTextAppearance, R.style.Widget_TextAppearance_AlphabetIndexer_Overlay);
            this.mOverlayBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.MiuixAppcompatAlphabetIndexer_miuixAppcompatOverlayBackground);
            this.mItemHeight = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_item_height);
            this.mOmitItemHeight = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_omit_item_height);
            int i3 = R.dimen.miuix_appcompat_alphabet_indexer_item_margin;
            this.mItemMargin = resources.getDimensionPixelOffset(i3);
            this.mMaxItemMargin = resources.getDimensionPixelOffset(i3);
            this.mMinItemMargin = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_min_item_margin);
            this.mOverlayWidth = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_overlay_width);
            this.mOverlayHeight = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_overlay_height);
            this.mIndexMinWidth = resources.getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_min_width);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void refreshMask() {
        /*
            r6 = this;
            miuix.miuixbasewidget.widget.AlphabetIndexer$Adapter r0 = r6.mAdapter
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.SectionIndexer r0 = r6.getSectionIndexer()
            if (r0 != 0) goto Lc
            return
        Lc:
            miuix.miuixbasewidget.widget.AlphabetIndexer$Adapter r1 = r6.mAdapter
            int r1 = r1.getFirstVisibleItemPosition()
            int r2 = r6.getListOffset()
            int r1 = r1 - r2
            int r1 = r0.getSectionForPosition(r1)
            miuix.miuixbasewidget.widget.AlphabetIndexer$TextHighlighter r2 = r6.mTextHighlighter
            android.widget.SectionIndexer r3 = r6.getSectionIndexer()
            r4 = 0
            java.lang.String[] r2 = r2.getVisibleIndexes(r3, r4)
            java.lang.Object[] r0 = r0.getSections()
            r3 = -1
            if (r1 == r3) goto L4f
            if (r0 == 0) goto L4f
            int r5 = r0.length
            if (r1 >= r5) goto L4f
            r0 = r0[r1]
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L4f
            java.lang.String r0 = r0.toUpperCase()
        L40:
            int r1 = r2.length
            if (r4 >= r1) goto L4f
            r1 = r2[r4]
            boolean r1 = android.text.TextUtils.equals(r0, r1)
            if (r1 == 0) goto L4c
            goto L50
        L4c:
            int r4 = r4 + 1
            goto L40
        L4f:
            r4 = r3
        L50:
            if (r4 == r3) goto L58
            int r0 = r6.mLastAlphabetIndex
            if (r0 == r4) goto L58
            r6.mLastAlphabetIndex = r4
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.AlphabetIndexer.refreshMask():void");
    }

    private void resetViews() {
        this.mGroupCount = 0;
        this.mGroupItemCount = 0;
        this.mSelectedAlphaIndex = -1;
        this.mLastSelectedItem = null;
        this.mFirstOmitItem = null;
        removeAllViews();
    }

    private void scrollTo(@NonNull SectionIndexer sectionIndexer, ScrollTargetInfo scrollTargetInfo) {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return;
        }
        adapter.stopScroll();
        Object[] sections = sectionIndexer.getSections();
        this.mAdapter.scrollToPosition(scrollTargetInfo.targetItemPos);
        updateOverlay(scrollTargetInfo, sections);
    }

    private ScrollTargetInfo scrollToSelection(int i2, SectionIndexer sectionIndexer) {
        if (this.mAdapter == null || sectionIndexer == null) {
            return null;
        }
        int safeSectionIndex = getSafeSectionIndex(i2, sectionIndexer);
        if (safeSectionIndex >= 0) {
            ScrollTargetInfo scrollTargetInfo = getScrollTargetInfo(safeSectionIndex, sectionIndexer);
            scrollTo(sectionIndexer, scrollTargetInfo);
            return scrollTargetInfo;
        }
        this.mAdapter.scrollToPosition(0);
        ScrollTargetInfo scrollTargetInfo2 = new ScrollTargetInfo();
        scrollTargetInfo2.targetItemPos = 0;
        scrollTargetInfo2.targetSectionIndex = 0;
        return scrollTargetInfo2;
    }

    private void setChecked(int i2) {
        this.mSelectedAlphaIndex = i2;
        View view = this.mLastSelectedItem;
        if (view != null) {
            updateIndexItemColor(view, false);
        }
        View childAt = getChildAt(getChildIndex(i2));
        this.mLastSelectedItem = childAt;
        updateIndexItemColor(childAt, true);
        if (this.mLastSelectedItem != null) {
            invalidate();
        }
    }

    private void showOverlay() {
        TextView textView = this.mOverlay;
        if (textView != null) {
            Folme.useAt(textView).visible().setFlags(1L).setScale(0.0f, IVisibleStyle.VisibleType.HIDE).setScale(1.0f, IVisibleStyle.VisibleType.SHOW).show(this.mOverlayShowAnimConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop(int i2) {
        this.mHandler.removeMessages(1);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), i2 <= 0 ? 0L : i2);
    }

    private void updateIndexItemColor(View view, boolean z2) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            TextHighlighter textHighlighter = this.mTextHighlighter;
            textView.setTextColor(z2 ? textHighlighter.mHighlightColor : textHighlighter.mNormalColor);
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(z2 ? R.drawable.miuix_ic_omit_selected : R.drawable.miuix_ic_omit);
        }
    }

    private void updateItemMargin(int i2) {
        View childAt = getChildAt(0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
        layoutParams.bottomMargin = i2;
        layoutParams.topMargin = i2;
        childAt.setLayoutParams(layoutParams);
        this.mItemMargin = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateItemsAfterParentVisibleHeightChanged(int i2) {
        View childAt = getChildAt(0);
        int height = childAt.getHeight();
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false);
        int length = (visibleIndexes.length * (this.mItemHeight + (this.mMinItemMargin * 2))) + getPaddingTop() + getPaddingBottom();
        int marginTop = getMarginTop() <= 0 ? getMarginTop() + (this.mOverlayHeight / 2) + 1 : getMarginTop();
        int marginBottom = getMarginBottom() <= 0 ? getMarginBottom() + (this.mOverlayHeight / 2) + 1 : getMarginBottom();
        if (length + marginTop + marginBottom > i2) {
            if (getChildCount() > 0) {
                resetViews();
            }
            constructItemWithOmit(i2);
            checkSelectedItemChanged();
            return;
        }
        int paddingTop = visibleIndexes.length > 0 ? ((((((i2 - getPaddingTop()) - getPaddingBottom()) - marginTop) - marginBottom) / visibleIndexes.length) - this.mItemHeight) / 2 : this.mMaxItemMargin;
        if (getChildCount() != visibleIndexes.length || this.mUseOmit || this.mForceUpdateVisibleIndexes) {
            this.mForceUpdateVisibleIndexes = false;
            resetViews();
            constructItem(Math.min(this.mMaxItemMargin, paddingTop));
            checkSelectedItemChanged();
            return;
        }
        if (Math.min(this.mMaxItemMargin, paddingTop) != this.mItemMargin) {
            updateItemMargin(Math.min(this.mMaxItemMargin, paddingTop));
        } else if (height == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.height = this.mItemHeight;
            int i3 = this.mItemMargin;
            layoutParams.topMargin = i3;
            layoutParams.bottomMargin = i3;
            childAt.setLayoutParams(layoutParams);
        } else if (height != this.mItemHeight) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams2.height = this.mItemHeight;
            layoutParams2.topMargin = 0;
            layoutParams2.bottomMargin = 0;
            childAt.setLayoutParams(layoutParams2);
        }
        checkSelectedItemChanged();
    }

    private void updateOverlay(ScrollTargetInfo scrollTargetInfo, Object[] objArr) {
        int i2;
        if (scrollTargetInfo == null || (i2 = scrollTargetInfo.targetSectionIndex) < 0 || objArr == null) {
            return;
        }
        if (i2 >= objArr.length) {
            if (objArr.length == 0) {
                drawThumbInternal(this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false)[i2], calculateOverlayPosition(i2));
                return;
            }
            return;
        }
        String string = objArr[i2].toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String upperCase = string.toUpperCase();
        CharSequence charSequenceSubSequence = upperCase.subSequence(0, 1);
        scrollTargetInfo.targetSectionIndex = getLetterIndex(upperCase);
        drawThumbInternal(charSequenceSubSequence, calculateOverlayPosition(r5));
    }

    private void updateOverlayLayout() {
        TextView textView = this.mOverlay;
        if (textView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.setMarginEnd(getRealWidthOfIndexer() + getMarinEnd());
            this.mOverlay.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOverlayTextAlpha(float f2) {
        TextView textView = this.mOverlay;
        textView.setTextColor(textView.getTextColors().withAlpha((int) (f2 * 255.0f)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOverlayTranslationX(float f2) {
        float width = (this.mOverlay.getWidth() / 2) * (1.0f - f2);
        if (ViewUtils.isLayoutRtl(this)) {
            width *= -1.0f;
        }
        this.mOverlay.setTranslationX(width);
    }

    private void updateVerticalPadding() {
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_padding_vertical);
        setPadding(getPaddingStart(), dimensionPixelOffset, getPaddingEnd(), dimensionPixelOffset);
    }

    public void attach(Adapter adapter) {
        if (this.mAdapter == adapter) {
            return;
        }
        detach();
        if (adapter == null) {
            return;
        }
        this.mLastAlphabetIndex = -1;
        this.mAdapter = adapter;
        constructOverlay();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.gravity = this.mVerticalPosition | 48;
        int i2 = layoutParams.bottomMargin;
        int i3 = this.mOverlayHeight;
        layoutParams.bottomMargin = i2 + (i3 / 2) + 1;
        layoutParams.topMargin += (i3 / 2) + 1;
        setLayoutParams(layoutParams);
    }

    public void detach() {
        if (this.mAdapter != null) {
            stop(0);
            FrameLayout frameLayout = (FrameLayout) getParent();
            TextView textView = this.mOverlay;
            if (textView != null) {
                frameLayout.removeView(textView);
            }
            setVisibility(8);
            this.mAdapter = null;
        }
    }

    public int getIndexerIntrinsicWidth() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicWidth();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = (View) getParent();
        this.mParentView = view;
        if (view != null) {
            view.addOnLayoutChangeListener(this.mParentLayoutChangeListener);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = configuration.screenHeightDp;
        if (i2 != this.mScreenHeightDp) {
            this.mScreenHeightDp = i2;
            this.mMaxItemMargin = getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_alphabet_indexer_item_margin);
            updateVerticalPadding();
            updateOverlayLayout();
            this.mForceUpdate = true;
            View view = this.mParentView;
            if (view != null) {
                view.requestLayout();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View view = this.mParentView;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.mParentLayoutChangeListener);
            this.mParentView = null;
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i2;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String[] visibleIndexes = this.mTextHighlighter.getVisibleIndexes(getSectionIndexer(), false);
        if (!isEnabled() || (i2 = this.mSelectedAlphaIndex) <= -1 || i2 >= visibleIndexes.length) {
            return;
        }
        accessibilityNodeInfo.addAction(8192);
        if (i2 < visibleIndexes.length) {
            accessibilityNodeInfo.addAction(4096);
        }
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, -1.0f, visibleIndexes.length, i2));
        String string = visibleIndexes[i2];
        if (string != null) {
            if (TextUtils.equals(string, STARRED_TITLE)) {
                string = getContext().getString(R.string.miuix_indexer_collect);
            }
            accessibilityNodeInfo.setContentDescription(string);
        }
        accessibilityNodeInfo.setStateDescription(getContext().getString(R.string.miuix_alphabet_indexer_name));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        this.mIndexWidth = getWidth();
        updateOverlayLayout();
    }

    public void onScrollStateChanged(int i2) {
        this.mListScrollState = i2;
    }

    public void onScrolled(int i2, int i3) {
        if (this.mAdapter == null) {
            return;
        }
        refreshMask();
        SectionIndexer sectionIndexer = getSectionIndexer();
        if (sectionIndexer == null) {
            return;
        }
        int sectionForPosition = sectionIndexer.getSectionForPosition(this.mAdapter.getFirstVisibleItemPosition());
        Object[] sections = sectionIndexer.getSections();
        if (sections == null || sectionForPosition < 0 || sectionForPosition >= sections.length) {
            return;
        }
        drawThumb((String) sections[sectionForPosition]);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0081  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            miuix.miuixbasewidget.widget.AlphabetIndexer$Adapter r0 = r6.mAdapter
            r1 = 0
            if (r0 == 0) goto L9d
            int r0 = r6.getVisibility()
            if (r0 == 0) goto Ld
            goto L9d
        Ld:
            android.widget.SectionIndexer r0 = r6.getSectionIndexer()
            if (r0 != 0) goto L17
            r6.stop(r1)
            return r1
        L17:
            int r2 = r7.getActionMasked()
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r4 = 1
            if (r2 == 0) goto L4c
            if (r2 == r4) goto L2f
            r5 = 2
            if (r2 == r5) goto L5a
            r5 = 3
            if (r2 == r5) goto L2f
            r5 = 5
            if (r2 == r5) goto L4c
            r0 = 6
            if (r2 == r0) goto L2f
            goto L9c
        L2f:
            r6.mTransFormY = r3
            r6.announceSelectText()
            int r0 = r7.getActionIndex()
            int r7 = r7.getPointerId(r0)
            if (r7 == 0) goto L3f
            goto L9c
        L3f:
            r6.setPressed(r1)
            boolean r7 = r6.hasShown()
            if (r7 == 0) goto L9c
            r6.stop(r1)
            goto L9c
        L4c:
            int r2 = r7.getActionIndex()
            int r2 = r7.getPointerId(r2)
            if (r2 == 0) goto L57
            goto L9c
        L57:
            r6.setPressed(r4)
        L5a:
            float r2 = r6.mTransFormY
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            r5 = 0
            if (r3 != 0) goto L81
            float r1 = r7.getY()
            int r2 = r6.getPaddingTop()
            float r2 = (float) r2
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L79
            float r7 = r7.getY()
            int r1 = r6.getPaddingTop()
            float r1 = (float) r1
            float r5 = r7 - r1
        L79:
            r6.mClickDownY = r5
            r6.mTransFormY = r5
            r6.handleAccessibilityMotionEvent(r4, r0, r5)
            goto L9c
        L81:
            float r7 = r7.getY()
            float r2 = r2 + r7
            float r7 = r6.mClickDownY
            float r2 = r2 - r7
            int r7 = r6.getPaddingTop()
            float r7 = (float) r7
            int r7 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r7 <= 0) goto L99
            int r7 = r6.getPaddingTop()
            float r7 = (float) r7
            float r5 = r2 - r7
        L99:
            r6.handleAccessibilityMotionEvent(r1, r0, r5)
        L9c:
            return r4
        L9d:
            r6.stop(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.AlphabetIndexer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i2, Bundle bundle) {
        if (super.performAccessibilityAction(i2, bundle)) {
            return true;
        }
        SectionIndexer sectionIndexer = getSectionIndexer();
        if (!isEnabled() || sectionIndexer == null) {
            return false;
        }
        if (i2 != 4096 && i2 != 8192) {
            return false;
        }
        handleAccessibilityAction(this.mSelectedAlphaIndex, i2, sectionIndexer);
        announceSelectText();
        return true;
    }

    public void setMinVisibleIndexes(@NonNull String[] strArr) {
        this.mTextHighlighter.mMinVisibleIndexes = strArr;
    }

    public void setSectionIndexer(SectionIndexer sectionIndexer) {
        this.mIndexer = sectionIndexer;
    }

    public void setSectionsAsIndexesEnabled(boolean z2) {
        this.mTextHighlighter.mSectionsAsIndexesEnabled = z2;
    }

    @Deprecated
    public void setVerticalPosition(boolean z2) {
        this.mVerticalPosition = z2 ? GravityCompat.END : 8388611;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (i2 != 0) {
            stop(0);
            clearLastChecked(this.mLastAlphabetIndex);
        }
    }

    public void updateViewLayout() {
        updateVerticalPadding();
        updateOverlayLayout();
        this.mForceUpdate = true;
        View view = this.mParentView;
        if (view != null) {
            view.requestLayout();
        }
    }

    public void updateVisibleIndexes() {
        TextHighlighter textHighlighter = this.mTextHighlighter;
        if (textHighlighter != null) {
            this.mForceUpdateVisibleIndexes = true;
            textHighlighter.getVisibleIndexes(getSectionIndexer(), true);
            updateViewLayout();
        }
    }

    public AlphabetIndexer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.miuixAppcompatAlphabetIndexerStyle);
    }

    public AlphabetIndexer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIndexWidth = 0;
        this.INVALID_INDEX = -1;
        this.mGroupItemCount = 1;
        this.mLeftCount = 0;
        this.mGroupCount = 0;
        this.mSelectedAlphaIndex = -1;
        this.mSectionMap = new HashMap<>();
        this.mListScrollState = 0;
        this.mUseOmit = false;
        this.mForceUpdate = false;
        this.mForceUpdateVisibleIndexes = false;
        this.mParentView = null;
        this.mTransFormY = -1.0f;
        this.mFirstVisibleItemPos = 0;
        this.mOriginalMarginEnd = -1;
        this.mParentLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: miuix.miuixbasewidget.widget.AlphabetIndexer.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                int i11 = i6 - i4;
                if (i10 - i8 != i11 || AlphabetIndexer.this.mForceUpdate) {
                    AlphabetIndexer.this.mForceUpdate = false;
                    AlphabetIndexer.this.updateItemsAfterParentVisibleHeightChanged(i11);
                }
                ViewCompat.requestApplyInsets(AlphabetIndexer.this);
            }
        };
        this.mEnableAutoDismiss = true;
        this.mScreenHeightDp = -1;
        this.mHandler = new Handler() { // from class: miuix.miuixbasewidget.widget.AlphabetIndexer.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                AlphabetIndexer.this.hideOverlay();
            }
        };
        parseAttrs(attributeSet, i2);
        init();
        ViewUtils.doOnApplyWindowInsets(this, new ViewUtils.OnApplyWindowInsetsListener() { // from class: miuix.miuixbasewidget.widget.a
            @Override // miuix.internal.util.ViewUtils.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                return this.f6124a.lambda$new$0(view, windowInsetsCompat, relativePadding);
            }
        });
    }
}
