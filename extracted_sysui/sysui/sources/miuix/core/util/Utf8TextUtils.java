package miuix.core.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Utf8TextUtils {
    private static final int MAX_LENGTH = 6;
    private static final int MIN_LENGTH = 1;
    private static final String TAG = "Utf8TextUtils";
    private static final String UTF8 = "UTF-8";

    private Utf8TextUtils() {
    }

    private static CharRange findRange(byte[] bArr, int i2, int i3) {
        CharRange charRange = new CharRange();
        if (isValidCharacter(bArr, i2, i3)) {
            charRange.start = i2;
            charRange.length = i3;
        }
        return charRange;
    }

    private static int getByteCount(byte b2) {
        int i2 = 0;
        for (int i3 = 7; i3 >= 1 && (1 & ((byte) (b2 >> i3))) != 0; i3--) {
            i2++;
        }
        return i2;
    }

    private static CharRange getCharRangeAt(byte[] bArr, int i2) {
        int byteCount = getByteCount(bArr[i2]);
        return byteCount == 0 ? new CharRange(i2, 1) : findRange(bArr, i2, byteCount);
    }

    private static List<CharRange> getUtf8CharList(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            if (i2 >= bArr.length) {
                break;
            }
            CharRange charRangeAt = getCharRangeAt(bArr, i2);
            if (!charRangeAt.isValid()) {
                arrayList.clear();
                break;
            }
            arrayList.add(charRangeAt);
            i2 += charRangeAt.length;
        }
        return arrayList;
    }

    private static boolean isValidCharacter(byte[] bArr, int i2, int i3) {
        if (i3 <= 1 || i3 > 6) {
            return false;
        }
        for (int i4 = 1; i4 < i3; i4++) {
            if (getByteCount(bArr[i2 + i4]) != 1) {
                return false;
            }
        }
        return true;
    }

    public static String subString(String str, int i2, int i3) {
        if (!TextUtils.isEmpty(str) && i3 > i2) {
            byte[] bytes = str.getBytes();
            List<CharRange> utf8CharList = getUtf8CharList(bytes);
            if (utf8CharList.isEmpty()) {
                return str.substring(i2, i3);
            }
            if (i2 >= 0 && i2 < utf8CharList.size()) {
                int size = utf8CharList.size();
                int i4 = utf8CharList.get(i2).start;
                int length = (i3 >= size ? bytes.length : utf8CharList.get(i3).start) - i4;
                byte[] bArr = new byte[length];
                System.arraycopy(bytes, i4, bArr, 0, length);
                try {
                    return new String(bArr, UTF8);
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        return "";
    }

    public static String truncateByte(String str, int i2) {
        try {
            byte[] bytes = str.getBytes();
            List<CharRange> utf8CharList = getUtf8CharList(bytes);
            if (utf8CharList.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                while (sb.toString().getBytes().length < i2) {
                    sb.append(str.charAt(sb.length()));
                }
                if (sb.toString().getBytes().length > i2) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            int length = bytes.length;
            int size = utf8CharList.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                CharRange charRange = utf8CharList.get(size);
                if (charRange.start >= i2) {
                    size--;
                } else {
                    length = charRange.getEndIndex();
                    if (length > i2) {
                        length = charRange.start;
                    }
                }
            }
            if (length >= bytes.length) {
                return str;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, length);
            return new String(bArr, UTF8);
        } catch (UnsupportedEncodingException e2) {
            Log.w(TAG, "failed to get bytes of UTF-8 from " + str + ", " + e2);
            return null;
        }
    }

    public static class CharRange {
        int length;
        int start;

        public CharRange() {
            this.start = -1;
            this.length = -1;
        }

        public int getEndIndex() {
            return this.start + this.length;
        }

        public boolean isValid() {
            return this.start >= 0 && this.length > 0;
        }

        public CharRange(int i2, int i3) {
            this.start = i2;
            this.length = i3;
        }
    }
}
