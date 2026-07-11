package miuix.core.util;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes3.dex */
public class DirectIndexedFile {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "DensityIndexFile: ";

    /* JADX INFO: renamed from: miuix.core.util.DirectIndexedFile$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type;

        static {
            int[] iArr = new int[DataItemDescriptor.Type.values().length];
            $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type = iArr;
            try {
                iArr[DataItemDescriptor.Type.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.BYTE_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.SHORT_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.INTEGER_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.LONG_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.BYTE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.SHORT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.INTEGER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[DataItemDescriptor.Type.LONG.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static class Builder {
        private IndexData mCurrentIndexData;
        private int mFileDataVersion;
        private FileHeader mFileHeader;
        private ArrayList<IndexData> mIndexDataList;

        public static class IndexData {
            private DataItemDescriptor[] mDataItemDescriptions;
            private ArrayList<DataItemHolder> mDataItemHolders;
            private HashMap<Integer, Item> mDataMap;
            private Item mDefaultValue;
            private ArrayList<ArrayList<Item>> mIndexDataGroups;
            private IndexGroupDescriptor[] mIndexGroupDescriptions;

            public /* synthetic */ IndexData(int i2, AnonymousClass1 anonymousClass1) {
                this(i2);
            }

            private IndexData(int i2) {
                this.mDataMap = new HashMap<>();
                this.mDataItemHolders = new ArrayList<>();
                this.mIndexDataGroups = new ArrayList<>();
                this.mDataItemDescriptions = new DataItemDescriptor[i2];
            }
        }

        public class Item implements Comparable<Item> {
            private int mIndex;
            private Object[] mObjects;

            public /* synthetic */ Item(Builder builder, int i2, Object[] objArr, AnonymousClass1 anonymousClass1) {
                this(i2, objArr);
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                return (obj instanceof Item) && this.mIndex == ((Item) obj).mIndex;
            }

            public int hashCode() {
                return this.mIndex;
            }

            private Item(int i2, Object[] objArr) {
                this.mIndex = i2;
                this.mObjects = objArr;
            }

            @Override // java.lang.Comparable
            public int compareTo(Item item) {
                return this.mIndex - item.mIndex;
            }
        }

        public /* synthetic */ Builder(int i2, AnonymousClass1 anonymousClass1) {
            this(i2);
        }

        private void build() {
            int size = this.mIndexDataList.size();
            this.mFileHeader = new FileHeader(size, this.mFileDataVersion, null);
            for (int i2 = 0; i2 < size; i2++) {
                IndexData indexData = this.mIndexDataList.get(i2);
                this.mFileHeader.mDescriptionOffsets[i2] = new DescriptionPair(0L, 0L, null);
                int i3 = 0;
                while (i3 < indexData.mIndexDataGroups.size() && ((ArrayList) indexData.mIndexDataGroups.get(i3)).size() != 0) {
                    i3++;
                }
                indexData.mIndexGroupDescriptions = new IndexGroupDescriptor[i3];
                for (int i4 = 0; i4 < indexData.mIndexGroupDescriptions.length; i4++) {
                    ArrayList arrayList = (ArrayList) indexData.mIndexDataGroups.get(i4);
                    Collections.sort(arrayList);
                    indexData.mIndexGroupDescriptions[i4] = new IndexGroupDescriptor(((Item) arrayList.get(0)).mIndex, ((Item) arrayList.get(arrayList.size() - 1)).mIndex + 1, 0L, null);
                }
            }
            try {
                writeAll(null);
            } catch (IOException unused) {
            }
        }

        private void checkCurrentIndexingDataKind() {
            if (this.mCurrentIndexData == null) {
                throw new IllegalArgumentException("Please add a data kind before adding group");
            }
        }

        private void checkCurrentIndexingGroup() {
            checkCurrentIndexingDataKind();
            if (this.mCurrentIndexData.mIndexDataGroups.size() == 0) {
                throw new IllegalArgumentException("Please add a data group before adding data");
            }
        }

        private int writeAll(DataOutput dataOutput) throws IOException {
            int iWriteDataItems = 0;
            for (int i2 = 0; i2 < this.mFileHeader.mDescriptionOffsets.length; i2++) {
                int iWrite = iWriteDataItems + this.mFileHeader.write(dataOutput);
                this.mFileHeader.mDescriptionOffsets[i2].mIndexGroupDescriptionOffset = iWrite;
                IndexData indexData = this.mIndexDataList.get(i2);
                if (dataOutput != null) {
                    dataOutput.writeInt(indexData.mIndexGroupDescriptions.length);
                }
                int iWrite2 = iWrite + 4;
                for (int i3 = 0; i3 < indexData.mIndexGroupDescriptions.length; i3++) {
                    iWrite2 += indexData.mIndexGroupDescriptions[i3].write(dataOutput);
                }
                this.mFileHeader.mDescriptionOffsets[i2].mDataItemDescriptionOffset = iWrite2;
                if (dataOutput != null) {
                    dataOutput.writeInt(indexData.mDataItemDescriptions.length);
                }
                iWriteDataItems = iWrite2 + 4;
                for (int i4 = 0; i4 < indexData.mDataItemDescriptions.length; i4++) {
                    iWriteDataItems += indexData.mDataItemDescriptions[i4].write(dataOutput);
                }
                for (int i5 = 0; i5 < indexData.mDataItemDescriptions.length; i5++) {
                    indexData.mDataItemDescriptions[i5].mOffset = iWriteDataItems;
                    iWriteDataItems += indexData.mDataItemDescriptions[i5].writeDataItems(dataOutput, ((DataItemHolder) indexData.mDataItemHolders.get(i5)).getAll());
                }
                for (int i6 = 0; i6 < indexData.mIndexGroupDescriptions.length; i6++) {
                    indexData.mIndexGroupDescriptions[i6].mOffset = iWriteDataItems;
                    if (dataOutput == null) {
                        int i7 = 0;
                        for (int i8 = 0; i8 < indexData.mDataItemDescriptions.length; i8++) {
                            i7 += indexData.mDataItemDescriptions[i8].mIndexSize;
                        }
                        iWriteDataItems += (indexData.mIndexGroupDescriptions[i6].mMaxIndex - indexData.mIndexGroupDescriptions[i6].mMinIndex) * i7;
                    } else {
                        for (int i9 = indexData.mIndexGroupDescriptions[i6].mMinIndex; i9 < indexData.mIndexGroupDescriptions[i6].mMaxIndex; i9++) {
                            Item item = (Item) indexData.mDataMap.get(Integer.valueOf(i9));
                            if (item == null) {
                                item = indexData.mDefaultValue;
                            }
                            for (int i10 = 0; i10 < indexData.mDataItemDescriptions.length; i10++) {
                                if (indexData.mDataItemDescriptions[i10].mIndexSize == 1) {
                                    dataOutput.writeByte(((Integer) item.mObjects[i10]).intValue());
                                } else if (indexData.mDataItemDescriptions[i10].mIndexSize == 2) {
                                    dataOutput.writeShort(((Integer) item.mObjects[i10]).intValue());
                                } else if (indexData.mDataItemDescriptions[i10].mIndexSize == 4) {
                                    dataOutput.writeInt(((Integer) item.mObjects[i10]).intValue());
                                } else if (indexData.mDataItemDescriptions[i10].mIndexSize == 8) {
                                    dataOutput.writeLong(((Long) item.mObjects[i10]).longValue());
                                }
                                iWriteDataItems += indexData.mDataItemDescriptions[i10].mIndexSize;
                            }
                        }
                    }
                }
            }
            return iWriteDataItems;
        }

        public void add(int i2, Object... objArr) {
            checkCurrentIndexingGroup();
            if (this.mCurrentIndexData.mDataItemDescriptions.length != objArr.length) {
                throw new IllegalArgumentException("Different number of objects inputted, " + this.mCurrentIndexData.mDataItemDescriptions.length + " data expected");
            }
            for (int i3 = 0; i3 < objArr.length; i3++) {
                switch (AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mCurrentIndexData.mDataItemDescriptions[i3].mType.ordinal()]) {
                    case 1:
                        if (!(objArr[i3] instanceof String)) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be String");
                        }
                        objArr[i3] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).put(objArr[i3]);
                        this.mCurrentIndexData.mDataItemDescriptions[i3].mIndexSize = DataItemDescriptor.getSizeOf(((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).size());
                        break;
                        break;
                    case 2:
                        if (!(objArr[i3] instanceof byte[])) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be byte[]");
                        }
                        objArr[i3] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).put(objArr[i3]);
                        this.mCurrentIndexData.mDataItemDescriptions[i3].mIndexSize = DataItemDescriptor.getSizeOf(((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).size());
                        break;
                        break;
                    case 3:
                        if (!(objArr[i3] instanceof short[])) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be short[]");
                        }
                        objArr[i3] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).put(objArr[i3]);
                        this.mCurrentIndexData.mDataItemDescriptions[i3].mIndexSize = DataItemDescriptor.getSizeOf(((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).size());
                        break;
                        break;
                    case 4:
                        if (!(objArr[i3] instanceof int[])) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be int[]");
                        }
                        objArr[i3] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).put(objArr[i3]);
                        this.mCurrentIndexData.mDataItemDescriptions[i3].mIndexSize = DataItemDescriptor.getSizeOf(((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).size());
                        break;
                        break;
                    case 5:
                        if (!(objArr[i3] instanceof long[])) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be long[]");
                        }
                        objArr[i3] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).put(objArr[i3]);
                        this.mCurrentIndexData.mDataItemDescriptions[i3].mIndexSize = DataItemDescriptor.getSizeOf(((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i3)).size());
                        break;
                        break;
                    case 6:
                        if (!(objArr[i3] instanceof Byte)) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be byte");
                        }
                        break;
                        break;
                    case 7:
                        if (!(objArr[i3] instanceof Short)) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be short");
                        }
                        break;
                        break;
                    case 8:
                        if (!(objArr[i3] instanceof Integer)) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be int");
                        }
                        break;
                        break;
                    case 9:
                        if (!(objArr[i3] instanceof Long)) {
                            throw new IllegalArgumentException("Object[" + i3 + "] should be long");
                        }
                        break;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported type of objects " + i3 + ", " + this.mCurrentIndexData.mDataItemDescriptions[i3].mType + " expected");
                }
            }
            Item item = new Item(this, i2, objArr, null);
            this.mCurrentIndexData.mDataMap.put(Integer.valueOf(i2), item);
            ((ArrayList) this.mCurrentIndexData.mIndexDataGroups.get(this.mCurrentIndexData.mIndexDataGroups.size() - 1)).add(item);
        }

        public void addGroup(int[] iArr, Object[][] objArr) {
            checkCurrentIndexingDataKind();
            if (iArr.length != objArr.length) {
                throw new IllegalArgumentException("Different number between indexes and objects");
            }
            newGroup();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                add(iArr[i2], objArr[i2]);
            }
        }

        public void addKind(Object... objArr) {
            DataItemDescriptor.Type type;
            AnonymousClass1 anonymousClass1 = null;
            IndexData indexData = new IndexData(objArr.length, anonymousClass1);
            this.mCurrentIndexData = indexData;
            this.mIndexDataList.add(indexData);
            for (int i2 = 0; i2 < objArr.length; i2++) {
                this.mCurrentIndexData.mDataItemHolders.add(new DataItemHolder(this, anonymousClass1));
                Object obj = objArr[i2];
                byte b2 = 1;
                if (obj instanceof Byte) {
                    type = DataItemDescriptor.Type.BYTE;
                    ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                } else if (obj instanceof Short) {
                    type = DataItemDescriptor.Type.SHORT;
                    ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                    b2 = 2;
                } else if (obj instanceof Integer) {
                    type = DataItemDescriptor.Type.INTEGER;
                    ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                    b2 = 4;
                } else if (obj instanceof Long) {
                    type = DataItemDescriptor.Type.LONG;
                    ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                    b2 = 8;
                } else if (obj instanceof String) {
                    type = DataItemDescriptor.Type.STRING;
                    objArr[i2] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                } else if (obj instanceof byte[]) {
                    type = DataItemDescriptor.Type.BYTE_ARRAY;
                    objArr[i2] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                } else if (obj instanceof short[]) {
                    type = DataItemDescriptor.Type.SHORT_ARRAY;
                    objArr[i2] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                } else if (obj instanceof int[]) {
                    type = DataItemDescriptor.Type.INTEGER_ARRAY;
                    objArr[i2] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                } else {
                    if (!(obj instanceof long[])) {
                        throw new IllegalArgumentException("Unsupported type of the [" + i2 + "] argument");
                    }
                    type = DataItemDescriptor.Type.LONG_ARRAY;
                    objArr[i2] = ((DataItemHolder) this.mCurrentIndexData.mDataItemHolders.get(i2)).put(objArr[i2]);
                }
                this.mCurrentIndexData.mDataItemDescriptions[i2] = new DataItemDescriptor(type, b2, (byte) 0, (byte) 0, 0L, null);
            }
            this.mCurrentIndexData.mDefaultValue = new Item(this, -1, objArr, anonymousClass1);
        }

        public void newGroup() {
            if (this.mCurrentIndexData.mIndexDataGroups.size() == 0 || ((ArrayList) this.mCurrentIndexData.mIndexDataGroups.get(this.mCurrentIndexData.mIndexDataGroups.size() - 1)).size() != 0) {
                this.mCurrentIndexData.mIndexDataGroups.add(new ArrayList());
            }
        }

        public void write(String str) throws Throwable {
            build();
            DataOutputStream dataOutputStream = null;
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
                try {
                    writeAll(dataOutputStream2);
                    dataOutputStream2.close();
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (new File(str).delete()) {
                        System.err.println("Cannot delete file " + str);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        private Builder(int i2) {
            this.mIndexDataList = new ArrayList<>();
            this.mFileDataVersion = i2;
        }

        public class DataItemHolder {
            private ArrayList<Object> mList;
            private HashMap<Object, Integer> mMap;

            private DataItemHolder() {
                this.mMap = new HashMap<>();
                this.mList = new ArrayList<>();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public ArrayList<Object> getAll() {
                return this.mList;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Integer put(Object obj) {
                Integer num = this.mMap.get(obj);
                if (num != null) {
                    return num;
                }
                Integer numValueOf = Integer.valueOf(this.mList.size());
                this.mMap.put(obj, numValueOf);
                this.mList.add(obj);
                return numValueOf;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int size() {
                return this.mList.size();
            }

            public /* synthetic */ DataItemHolder(Builder builder, AnonymousClass1 anonymousClass1) {
                this();
            }
        }
    }

    public static class DataInputRandom implements InputFile {
        private RandomAccessFile mFile;

        public DataInputRandom(RandomAccessFile randomAccessFile) {
            this.mFile = randomAccessFile;
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public void close() throws IOException {
            this.mFile.close();
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public long getFilePointer() {
            return this.mFile.getFilePointer();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            return this.mFile.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() {
            return this.mFile.readByte();
        }

        @Override // java.io.DataInput
        public char readChar() {
            return this.mFile.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() {
            return this.mFile.readDouble();
        }

        @Override // java.io.DataInput
        public float readFloat() {
            return this.mFile.readFloat();
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            this.mFile.readFully(bArr);
        }

        @Override // java.io.DataInput
        public int readInt() {
            return this.mFile.readInt();
        }

        @Override // java.io.DataInput
        public String readLine() {
            return this.mFile.readLine();
        }

        @Override // java.io.DataInput
        public long readLong() {
            return this.mFile.readLong();
        }

        @Override // java.io.DataInput
        public short readShort() {
            return this.mFile.readShort();
        }

        @Override // java.io.DataInput
        public String readUTF() {
            return this.mFile.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            return this.mFile.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() {
            return this.mFile.readUnsignedShort();
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public void seek(long j2) throws IOException {
            this.mFile.seek(j2);
        }

        @Override // java.io.DataInput
        public int skipBytes(int i2) {
            return this.mFile.skipBytes(i2);
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i2, int i3) throws IOException {
            this.mFile.readFully(bArr, i2, i3);
        }
    }

    public static class DataItemDescriptor {
        private static byte[] sBuffer = new byte[1024];
        private byte mIndexSize;
        private byte mLengthSize;
        private long mOffset;
        private byte mOffsetSize;
        private Type mType;

        public enum Type {
            BYTE,
            SHORT,
            INTEGER,
            LONG,
            STRING,
            BYTE_ARRAY,
            SHORT_ARRAY,
            INTEGER_ARRAY,
            LONG_ARRAY
        }

        public /* synthetic */ DataItemDescriptor(Type type, byte b2, byte b3, byte b4, long j2, AnonymousClass1 anonymousClass1) {
            this(type, b2, b3, b4, j2);
        }

        private static byte[] aquireBuffer(int i2) {
            byte[] bArr;
            synchronized (DataItemDescriptor.class) {
                try {
                    byte[] bArr2 = sBuffer;
                    if (bArr2 == null || bArr2.length < i2) {
                        sBuffer = new byte[i2];
                    }
                    bArr = sBuffer;
                    sBuffer = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return bArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte getSizeOf(int i2) {
            byte b2 = 0;
            for (long j2 = i2 * 2; j2 > 0; j2 >>= 8) {
                b2 = (byte) (b2 + 1);
            }
            if (b2 == 3) {
                return (byte) 4;
            }
            if (b2 <= 4 || b2 >= 8) {
                return b2;
            }
            return (byte) 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static DataItemDescriptor read(DataInput dataInput) {
            return new DataItemDescriptor(Type.values()[dataInput.readByte()], dataInput.readByte(), dataInput.readByte(), dataInput.readByte(), dataInput.readLong());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static long readAccordingToSize(DataInput dataInput, int i2) throws IOException {
            int i3;
            if (i2 == 1) {
                i3 = dataInput.readByte();
            } else if (i2 == 2) {
                i3 = dataInput.readShort();
            } else {
                if (i2 != 4) {
                    if (i2 == 8) {
                        return dataInput.readLong();
                    }
                    throw new IllegalArgumentException("Unsuppoert size " + i2);
                }
                i3 = dataInput.readInt();
            }
            return i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object[] readDataItems(InputFile inputFile) {
            switch (AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    Object[] objArr = new Object[inputFile.readInt()];
                    objArr[0] = readSingleDataItem(inputFile, 0);
                    return objArr;
                case 6:
                    return new Object[]{Byte.valueOf(inputFile.readByte())};
                case 7:
                    return new Object[]{Short.valueOf(inputFile.readShort())};
                case 8:
                    return new Object[]{Integer.valueOf(inputFile.readInt())};
                case 9:
                    return new Object[]{Long.valueOf(inputFile.readLong())};
                default:
                    return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v4 */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r7v8, types: [int[]] */
        /* JADX WARN: Type inference failed for: r7v9, types: [long[]] */
        public Object readSingleDataItem(InputFile inputFile, int i2) throws IOException {
            byte[] bArrAquireBuffer;
            short[] str;
            long filePointer = inputFile.getFilePointer();
            if (i2 != 0) {
                inputFile.seek(((long) (this.mOffsetSize * i2)) + filePointer);
            }
            inputFile.seek(filePointer + readAccordingToSize(inputFile, this.mOffsetSize));
            int i3 = AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mType.ordinal()];
            int i4 = 0;
            if (i3 != 1) {
                bArrAquireBuffer = null;
                if (i3 == 2) {
                    byte[] bArr = new byte[(int) readAccordingToSize(inputFile, this.mLengthSize)];
                    inputFile.readFully(bArr);
                    str = bArr;
                } else if (i3 == 3) {
                    int accordingToSize = (int) readAccordingToSize(inputFile, this.mLengthSize);
                    str = new short[accordingToSize];
                    while (i4 < accordingToSize) {
                        str[i4] = inputFile.readShort();
                        i4++;
                    }
                } else if (i3 == 4) {
                    int accordingToSize2 = (int) readAccordingToSize(inputFile, this.mLengthSize);
                    str = new int[accordingToSize2];
                    while (i4 < accordingToSize2) {
                        str[i4] = inputFile.readInt();
                        i4++;
                    }
                } else if (i3 != 5) {
                    str = 0;
                } else {
                    int accordingToSize3 = (int) readAccordingToSize(inputFile, this.mLengthSize);
                    str = new long[accordingToSize3];
                    while (i4 < accordingToSize3) {
                        str[i4] = inputFile.readLong();
                        i4++;
                    }
                }
            } else {
                int accordingToSize4 = (int) readAccordingToSize(inputFile, this.mLengthSize);
                bArrAquireBuffer = aquireBuffer(accordingToSize4);
                inputFile.readFully(bArrAquireBuffer, 0, accordingToSize4);
                str = new String(bArrAquireBuffer, 0, accordingToSize4);
            }
            releaseBuffer(bArrAquireBuffer);
            return str;
        }

        private static void releaseBuffer(byte[] bArr) {
            synchronized (DataItemDescriptor.class) {
                if (bArr != null) {
                    try {
                        byte[] bArr2 = sBuffer;
                        if (bArr2 == null || bArr2.length < bArr.length) {
                            sBuffer = bArr;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int write(DataOutput dataOutput) throws IOException {
            if (dataOutput == null) {
                return 12;
            }
            dataOutput.writeByte(this.mType.ordinal());
            dataOutput.writeByte(this.mIndexSize);
            dataOutput.writeByte(this.mLengthSize);
            dataOutput.writeByte(this.mOffsetSize);
            dataOutput.writeLong(this.mOffset);
            return 12;
        }

        private static void writeAccordingToSize(DataOutput dataOutput, int i2, long j2) throws IOException {
            if (i2 == 1) {
                dataOutput.writeByte((int) j2);
                return;
            }
            if (i2 == 2) {
                dataOutput.writeShort((int) j2);
                return;
            }
            if (i2 == 4) {
                dataOutput.writeInt((int) j2);
            } else {
                if (i2 == 8) {
                    dataOutput.writeLong(j2);
                    return;
                }
                throw new IllegalArgumentException("Unsuppoert size " + i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int writeDataItems(DataOutput dataOutput, List<Object> list) throws IOException {
            int iWriteOffsets = 4;
            switch (AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mType.ordinal()]) {
                case 1:
                    if (dataOutput != null) {
                        dataOutput.writeInt(list.size());
                    }
                    int iWriteOffsets2 = 4 + writeOffsets(dataOutput, list);
                    Iterator<Object> it = list.iterator();
                    int length = iWriteOffsets2;
                    while (it.hasNext()) {
                        byte[] bytes = ((String) it.next()).getBytes();
                        if (dataOutput != null) {
                            writeAccordingToSize(dataOutput, this.mLengthSize, bytes.length);
                            for (byte b2 : bytes) {
                                dataOutput.writeByte(b2);
                            }
                        }
                        length += this.mLengthSize + bytes.length;
                    }
                    return length;
                case 2:
                    if (dataOutput != null) {
                        dataOutput.writeInt(list.size());
                    }
                    int iWriteOffsets3 = 4 + writeOffsets(dataOutput, list);
                    Iterator<Object> it2 = list.iterator();
                    int length2 = iWriteOffsets3;
                    while (it2.hasNext()) {
                        byte[] bArr = (byte[]) it2.next();
                        if (dataOutput != null) {
                            writeAccordingToSize(dataOutput, this.mLengthSize, bArr.length);
                            dataOutput.write(bArr);
                        }
                        length2 += this.mLengthSize + bArr.length;
                    }
                    return length2;
                case 3:
                    if (dataOutput != null) {
                        dataOutput.writeInt(list.size());
                    }
                    iWriteOffsets = 4 + writeOffsets(dataOutput, list);
                    Iterator<Object> it3 = list.iterator();
                    while (it3.hasNext()) {
                        short[] sArr = (short[]) it3.next();
                        if (dataOutput != null) {
                            writeAccordingToSize(dataOutput, this.mLengthSize, sArr.length);
                            for (short s2 : sArr) {
                                dataOutput.writeShort(s2);
                            }
                        }
                        iWriteOffsets += this.mLengthSize + (sArr.length * 2);
                    }
                    break;
                case 4:
                    if (dataOutput != null) {
                        dataOutput.writeInt(list.size());
                    }
                    int iWriteOffsets4 = writeOffsets(dataOutput, list) + 4;
                    Iterator<Object> it4 = list.iterator();
                    int length3 = iWriteOffsets4;
                    while (it4.hasNext()) {
                        int[] iArr = (int[]) it4.next();
                        if (dataOutput != null) {
                            writeAccordingToSize(dataOutput, this.mLengthSize, iArr.length);
                            for (int i2 : iArr) {
                                dataOutput.writeInt(i2);
                            }
                        }
                        length3 += this.mLengthSize + (iArr.length * 4);
                    }
                    return length3;
                case 5:
                    if (dataOutput != null) {
                        dataOutput.writeInt(list.size());
                    }
                    int iWriteOffsets5 = 4 + writeOffsets(dataOutput, list);
                    Iterator<Object> it5 = list.iterator();
                    int length4 = iWriteOffsets5;
                    while (it5.hasNext()) {
                        long[] jArr = (long[]) it5.next();
                        if (dataOutput != null) {
                            writeAccordingToSize(dataOutput, this.mLengthSize, jArr.length);
                            for (long j2 : jArr) {
                                dataOutput.writeLong(j2);
                            }
                        }
                        length4 += this.mLengthSize + (jArr.length * 8);
                    }
                    return length4;
                case 6:
                    if (dataOutput != null) {
                        dataOutput.writeByte(((Byte) list.get(0)).byteValue());
                    }
                    return 1;
                case 7:
                    if (dataOutput == null) {
                        return 2;
                    }
                    dataOutput.writeShort(((Short) list.get(0)).shortValue());
                    return 2;
                case 8:
                    if (dataOutput != null) {
                        dataOutput.writeInt(((Integer) list.get(0)).intValue());
                    }
                    break;
                case 9:
                    if (dataOutput != null) {
                        dataOutput.writeLong(((Long) list.get(0)).longValue());
                    }
                    return 8;
                default:
                    return 0;
            }
            return iWriteOffsets;
        }

        /* JADX WARN: Removed duplicated region for block: B:57:0x005c A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x001a A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private int writeOffsets(java.io.DataOutput r13, java.util.List<java.lang.Object> r14) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 204
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: miuix.core.util.DirectIndexedFile.DataItemDescriptor.writeOffsets(java.io.DataOutput, java.util.List):int");
        }

        private DataItemDescriptor(Type type, byte b2, byte b3, byte b4, long j2) {
            this.mType = type;
            this.mIndexSize = b2;
            this.mLengthSize = b3;
            this.mOffsetSize = b4;
            this.mOffset = j2;
        }
    }

    public static class DescriptionPair {
        private long mDataItemDescriptionOffset;
        private long mIndexGroupDescriptionOffset;

        public /* synthetic */ DescriptionPair(long j2, long j3, AnonymousClass1 anonymousClass1) {
            this(j2, j3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static DescriptionPair read(DataInput dataInput) {
            return new DescriptionPair(dataInput.readLong(), dataInput.readLong());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int write(DataOutput dataOutput) throws IOException {
            if (dataOutput == null) {
                return 16;
            }
            dataOutput.writeLong(this.mIndexGroupDescriptionOffset);
            dataOutput.writeLong(this.mDataItemDescriptionOffset);
            return 16;
        }

        private DescriptionPair(long j2, long j3) {
            this.mIndexGroupDescriptionOffset = j2;
            this.mDataItemDescriptionOffset = j3;
        }
    }

    public static class FileHeader {
        private static final int CURRENT_VERSION = 2;
        private static final byte[] FILE_TAG = {73, 68, 70, 32};
        private int mDataVersion;
        private DescriptionPair[] mDescriptionOffsets;

        public /* synthetic */ FileHeader(int i2, int i3, AnonymousClass1 anonymousClass1) {
            this(i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static FileHeader read(DataInput dataInput) throws IOException {
            int length = FILE_TAG.length;
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                bArr[i2] = dataInput.readByte();
            }
            if (!Arrays.equals(bArr, FILE_TAG)) {
                throw new IOException("File tag unmatched, file may be corrupt");
            }
            if (dataInput.readInt() != 2) {
                throw new IOException("File version unmatched, please upgrade your reader");
            }
            int i3 = dataInput.readInt();
            FileHeader fileHeader = new FileHeader(i3, dataInput.readInt());
            for (int i4 = 0; i4 < i3; i4++) {
                fileHeader.mDescriptionOffsets[i4] = DescriptionPair.read(dataInput);
            }
            return fileHeader;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int write(DataOutput dataOutput) throws IOException {
            byte[] bArr = FILE_TAG;
            int length = bArr.length + 12;
            if (dataOutput != null) {
                dataOutput.write(bArr);
                dataOutput.writeInt(2);
                dataOutput.writeInt(this.mDescriptionOffsets.length);
                dataOutput.writeInt(this.mDataVersion);
            }
            for (DescriptionPair descriptionPair : this.mDescriptionOffsets) {
                length += descriptionPair.write(dataOutput);
            }
            return length;
        }

        private FileHeader(int i2, int i3) {
            this.mDescriptionOffsets = new DescriptionPair[i2];
            this.mDataVersion = i3;
        }
    }

    public static class IndexGroupDescriptor {
        int mMaxIndex;
        int mMinIndex;
        long mOffset;

        public /* synthetic */ IndexGroupDescriptor(int i2, int i3, long j2, AnonymousClass1 anonymousClass1) {
            this(i2, i3, j2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static IndexGroupDescriptor read(DataInput dataInput) {
            return new IndexGroupDescriptor(dataInput.readInt(), dataInput.readInt(), dataInput.readLong());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int write(DataOutput dataOutput) throws IOException {
            if (dataOutput == null) {
                return 16;
            }
            dataOutput.writeInt(this.mMinIndex);
            dataOutput.writeInt(this.mMaxIndex);
            dataOutput.writeLong(this.mOffset);
            return 16;
        }

        private IndexGroupDescriptor(int i2, int i3, long j2) {
            this.mMinIndex = i2;
            this.mMaxIndex = i3;
            this.mOffset = j2;
        }
    }

    public interface InputFile extends DataInput {
        void close();

        long getFilePointer();

        void seek(long j2);
    }

    public static class Reader {
        private InputFile mFile;
        private FileHeader mHeader;
        private IndexData[] mIndexData;

        public static class IndexData {
            private DataItemDescriptor[] mDataItemDescriptions;
            private Object[][] mDataItems;
            private IndexGroupDescriptor[] mIndexGroupDescriptions;
            private int mSizeOfItems;

            private IndexData() {
            }

            public static /* synthetic */ int access$912(IndexData indexData, int i2) {
                int i3 = indexData.mSizeOfItems + i2;
                indexData.mSizeOfItems = i3;
                return i3;
            }

            public /* synthetic */ IndexData(AnonymousClass1 anonymousClass1) {
                this();
            }
        }

        public /* synthetic */ Reader(InputStream inputStream, AnonymousClass1 anonymousClass1) {
            this(inputStream);
        }

        private void constructFromFile(String str) throws IOException {
            System.currentTimeMillis();
            try {
                this.mFile.seek(0L);
                FileHeader fileHeader = FileHeader.read(this.mFile);
                this.mHeader = fileHeader;
                this.mIndexData = new IndexData[fileHeader.mDescriptionOffsets.length];
                for (int i2 = 0; i2 < this.mHeader.mDescriptionOffsets.length; i2++) {
                    this.mIndexData[i2] = new IndexData(null);
                    this.mFile.seek(this.mHeader.mDescriptionOffsets[i2].mIndexGroupDescriptionOffset);
                    int i3 = this.mFile.readInt();
                    this.mIndexData[i2].mIndexGroupDescriptions = new IndexGroupDescriptor[i3];
                    for (int i4 = 0; i4 < i3; i4++) {
                        this.mIndexData[i2].mIndexGroupDescriptions[i4] = IndexGroupDescriptor.read(this.mFile);
                    }
                    this.mFile.seek(this.mHeader.mDescriptionOffsets[i2].mDataItemDescriptionOffset);
                    int i5 = this.mFile.readInt();
                    this.mIndexData[i2].mSizeOfItems = 0;
                    this.mIndexData[i2].mDataItemDescriptions = new DataItemDescriptor[i5];
                    for (int i6 = 0; i6 < i5; i6++) {
                        this.mIndexData[i2].mDataItemDescriptions[i6] = DataItemDescriptor.read(this.mFile);
                        IndexData indexData = this.mIndexData[i2];
                        IndexData.access$912(indexData, indexData.mDataItemDescriptions[i6].mIndexSize);
                    }
                    this.mIndexData[i2].mDataItems = new Object[i5][];
                    for (int i7 = 0; i7 < i5; i7++) {
                        this.mFile.seek(this.mIndexData[i2].mDataItemDescriptions[i7].mOffset);
                        this.mIndexData[i2].mDataItems[i7] = this.mIndexData[i2].mDataItemDescriptions[i7].readDataItems(this.mFile);
                    }
                }
            } catch (IOException e2) {
                close();
                throw e2;
            }
        }

        private long offset(int i2, int i3) {
            IndexGroupDescriptor indexGroupDescriptor;
            int length = this.mIndexData[i2].mIndexGroupDescriptions.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    indexGroupDescriptor = null;
                    break;
                }
                int i5 = (length + i4) / 2;
                if (this.mIndexData[i2].mIndexGroupDescriptions[i5].mMinIndex <= i3) {
                    if (this.mIndexData[i2].mIndexGroupDescriptions[i5].mMaxIndex > i3) {
                        indexGroupDescriptor = this.mIndexData[i2].mIndexGroupDescriptions[i5];
                        break;
                    }
                    i4 = i5 + 1;
                } else {
                    length = i5;
                }
            }
            if (indexGroupDescriptor != null) {
                return indexGroupDescriptor.mOffset + ((long) ((i3 - indexGroupDescriptor.mMinIndex) * this.mIndexData[i2].mSizeOfItems));
            }
            return -1L;
        }

        private Object readSingleDataItem(int i2, int i3, int i4) {
            if (this.mIndexData[i2].mDataItems[i3][i4] == null) {
                this.mFile.seek(this.mIndexData[i2].mDataItemDescriptions[i3].mOffset + 4);
                this.mIndexData[i2].mDataItems[i3][i4] = this.mIndexData[i2].mDataItemDescriptions[i3].readSingleDataItem(this.mFile, i4);
            }
            return this.mIndexData[i2].mDataItems[i3][i4];
        }

        public synchronized void close() {
            InputFile inputFile = this.mFile;
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException unused) {
                }
            }
            this.mFile = null;
            this.mHeader = null;
            this.mIndexData = null;
        }

        public synchronized Object get(int i2, int i3, int i4) {
            Object obj;
            if (this.mFile == null) {
                throw new IllegalStateException("Get data from a corrupt file");
            }
            if (i2 >= 0) {
                IndexData[] indexDataArr = this.mIndexData;
                if (i2 < indexDataArr.length) {
                    if (i4 < 0 || i4 >= indexDataArr[i2].mDataItemDescriptions.length) {
                        throw new IllegalArgumentException("DataIndex " + i4 + " out of range[0, " + this.mIndexData[i2].mDataItemDescriptions.length + ")");
                    }
                    System.currentTimeMillis();
                    long jOffset = offset(i2, i3);
                    if (jOffset < 0) {
                        obj = this.mIndexData[i2].mDataItems[i4][0];
                    } else {
                        try {
                            this.mFile.seek(jOffset);
                            Object singleDataItem = null;
                            for (int i5 = 0; i5 <= i4; i5++) {
                                switch (AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mIndexData[i2].mDataItemDescriptions[i5].mType.ordinal()]) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                        try {
                                            int accordingToSize = (int) DataItemDescriptor.readAccordingToSize(this.mFile, this.mIndexData[i2].mDataItemDescriptions[i5].mIndexSize);
                                            if (i5 == i4) {
                                                singleDataItem = readSingleDataItem(i2, i4, accordingToSize);
                                            }
                                        } catch (IOException e2) {
                                            throw new IllegalStateException("File may be corrupt due to invalid data index size", e2);
                                        }
                                        break;
                                    case 6:
                                        singleDataItem = Byte.valueOf(this.mFile.readByte());
                                        break;
                                    case 7:
                                        singleDataItem = Short.valueOf(this.mFile.readShort());
                                        break;
                                    case 8:
                                        singleDataItem = Integer.valueOf(this.mFile.readInt());
                                        break;
                                    case 9:
                                        singleDataItem = Long.valueOf(this.mFile.readLong());
                                        break;
                                    default:
                                        throw new IllegalStateException("Unknown type " + this.mIndexData[i2].mDataItemDescriptions[i5].mType);
                                }
                            }
                            obj = singleDataItem;
                        } catch (IOException e3) {
                            throw new IllegalStateException("Seek data from a corrupt file", e3);
                        }
                    }
                }
            }
            throw new IllegalArgumentException("Kind " + i2 + " out of range[0, " + this.mIndexData.length + ")");
            return obj;
        }

        public int getDataVersion() {
            FileHeader fileHeader = this.mHeader;
            if (fileHeader == null) {
                return -1;
            }
            return fileHeader.mDataVersion;
        }

        public /* synthetic */ Reader(String str, AnonymousClass1 anonymousClass1) {
            this(str);
        }

        private Reader(InputStream inputStream) throws IOException {
            this.mFile = new DataInputStream(inputStream);
            constructFromFile("assets");
        }

        private Reader(String str) throws IOException {
            this.mFile = new DataInputRandom(new RandomAccessFile(str, "r"));
            constructFromFile(str);
        }

        public synchronized Object[] get(int i2, int i3) {
            if (this.mFile != null) {
                if (i2 >= 0 && i2 < this.mIndexData.length) {
                    System.currentTimeMillis();
                    long jOffset = offset(i2, i3);
                    int length = this.mIndexData[i2].mDataItemDescriptions.length;
                    Object[] objArr = new Object[length];
                    if (jOffset < 0) {
                        for (int i4 = 0; i4 < length; i4++) {
                            objArr[i4] = this.mIndexData[i2].mDataItems[i4][0];
                        }
                        return objArr;
                    }
                    try {
                        this.mFile.seek(jOffset);
                        for (int i5 = 0; i5 < length; i5++) {
                            switch (AnonymousClass1.$SwitchMap$miuix$core$util$DirectIndexedFile$DataItemDescriptor$Type[this.mIndexData[i2].mDataItemDescriptions[i5].mType.ordinal()]) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                    try {
                                        int accordingToSize = (int) DataItemDescriptor.readAccordingToSize(this.mFile, this.mIndexData[i2].mDataItemDescriptions[i5].mIndexSize);
                                        long filePointer = this.mFile.getFilePointer();
                                        objArr[i5] = readSingleDataItem(i2, i5, accordingToSize);
                                        this.mFile.seek(filePointer);
                                    } catch (IOException e2) {
                                        throw new IllegalStateException("File may be corrupt due to invalid data index size", e2);
                                    }
                                    break;
                                case 6:
                                    objArr[i5] = Byte.valueOf(this.mFile.readByte());
                                    break;
                                case 7:
                                    objArr[i5] = Short.valueOf(this.mFile.readShort());
                                    break;
                                case 8:
                                    objArr[i5] = Integer.valueOf(this.mFile.readInt());
                                    break;
                                case 9:
                                    objArr[i5] = Long.valueOf(this.mFile.readLong());
                                    break;
                                default:
                                    throw new IllegalStateException("Unknown type " + this.mIndexData[i2].mDataItemDescriptions[i5].mType);
                            }
                        }
                        return objArr;
                    } catch (IOException e3) {
                        throw new IllegalStateException("Seek data from a corrupt file", e3);
                    }
                }
                throw new IllegalArgumentException("Cannot get data kind " + i2);
            }
            throw new IllegalStateException("Get data from a corrupt file");
        }
    }

    public DirectIndexedFile() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static Builder build(int i2) {
        return new Builder(i2, null);
    }

    public static Reader open(String str) {
        return new Reader(str, (AnonymousClass1) null);
    }

    public static class DataInputStream implements InputFile {
        private InputStream mInputFile;
        private long mInputPos;

        public DataInputStream(InputStream inputStream) {
            this.mInputFile = inputStream;
            inputStream.mark(0);
            this.mInputPos = 0L;
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public void close() throws IOException {
            this.mInputFile.close();
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public long getFilePointer() {
            return this.mInputPos;
        }

        @Override // java.io.DataInput
        public boolean readBoolean() {
            this.mInputPos++;
            return this.mInputFile.read() != 0;
        }

        @Override // java.io.DataInput
        public byte readByte() {
            this.mInputPos++;
            return (byte) this.mInputFile.read();
        }

        @Override // java.io.DataInput
        public char readChar() {
            byte[] bArr = new byte[2];
            this.mInputPos += 2;
            if (this.mInputFile.read(bArr) == 2) {
                return (char) (((char) (bArr[1] & TransitionInfo.INIT)) | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
            }
            return (char) 0;
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            throw new IOException();
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            throw new IOException();
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            this.mInputPos += (long) this.mInputFile.read(bArr);
        }

        @Override // java.io.DataInput
        public int readInt() {
            byte[] bArr = new byte[4];
            this.mInputPos += 4;
            if (this.mInputFile.read(bArr) == 4) {
                return (bArr[3] & TransitionInfo.INIT) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[1] << 16) & 16711680) | ((bArr[0] << 24) & ViewCompat.MEASURED_STATE_MASK);
            }
            return 0;
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            throw new IOException();
        }

        @Override // java.io.DataInput
        public long readLong() {
            byte[] bArr = new byte[8];
            this.mInputPos += 8;
            if (this.mInputFile.read(bArr) != 8) {
                return 0L;
            }
            return ((((long) bArr[0]) << 56) & (-72057594037927936L)) | ((long) (bArr[7] & TransitionInfo.INIT)) | (((long) (bArr[6] << 8)) & 65280) | (((long) (bArr[5] << 16)) & 16711680) | (((long) (bArr[4] << 24)) & 4278190080L) | ((((long) bArr[3]) << 32) & 1095216660480L) | ((((long) bArr[2]) << 40) & 280375465082880L) | ((((long) bArr[1]) << 48) & 71776119061217280L);
        }

        @Override // java.io.DataInput
        public short readShort() {
            byte[] bArr = new byte[2];
            this.mInputPos += 2;
            if (this.mInputFile.read(bArr) == 2) {
                return (short) (((short) (bArr[1] & TransitionInfo.INIT)) | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
            }
            return (short) 0;
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            throw new IOException();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() {
            this.mInputPos++;
            return (byte) this.mInputFile.read();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() {
            byte[] bArr = new byte[2];
            this.mInputPos += 2;
            if (this.mInputFile.read(bArr) == 2) {
                return (short) (((short) (bArr[1] & TransitionInfo.INIT)) | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
            }
            return 0;
        }

        @Override // miuix.core.util.DirectIndexedFile.InputFile
        public void seek(long j2) throws IOException {
            this.mInputFile.reset();
            if (this.mInputFile.skip(j2) != j2) {
                throw new IOException("Skip failed");
            }
            this.mInputPos = j2;
        }

        @Override // java.io.DataInput
        public int skipBytes(int i2) {
            int iSkip = (int) this.mInputFile.skip(i2);
            this.mInputPos += (long) iSkip;
            return iSkip;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i2, int i3) throws IOException {
            this.mInputPos += (long) this.mInputFile.read(bArr, i2, i3);
        }
    }

    public static Reader open(InputStream inputStream) {
        return new Reader(inputStream, (AnonymousClass1) null);
    }
}
