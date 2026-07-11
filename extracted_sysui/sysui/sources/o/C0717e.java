package o;

import java.io.EOFException;
import o.AbstractC0715c;

/* JADX INFO: renamed from: o.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0717e extends AbstractC0715c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final D1.d f6310n = D1.d.c("'\\");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final D1.d f6311o = D1.d.c("\"\\");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final D1.d f6312p = D1.d.c("{}[]:, \n\t\r\f/\\;#=");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final D1.d f6313q = D1.d.c("\n\r");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final D1.d f6314r = D1.d.c("*/");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final D1.c f6315h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final D1.a f6316i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6317j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f6318k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6319l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f6320m;

    public C0717e(D1.c cVar) {
        if (cVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f6315h = cVar;
        this.f6316i = cVar.b();
        B(6);
    }

    @Override // o.AbstractC0715c
    public AbstractC0715c.b A() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        switch (I2) {
            case 1:
                return AbstractC0715c.b.BEGIN_OBJECT;
            case 2:
                return AbstractC0715c.b.END_OBJECT;
            case 3:
                return AbstractC0715c.b.BEGIN_ARRAY;
            case 4:
                return AbstractC0715c.b.END_ARRAY;
            case 5:
            case 6:
                return AbstractC0715c.b.BOOLEAN;
            case 7:
                return AbstractC0715c.b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return AbstractC0715c.b.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return AbstractC0715c.b.NAME;
            case 16:
            case 17:
                return AbstractC0715c.b.NUMBER;
            case 18:
                return AbstractC0715c.b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // o.AbstractC0715c
    public int C(AbstractC0715c.a aVar) throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 < 12 || I2 > 15) {
            return -1;
        }
        if (I2 == 15) {
            return J(this.f6320m, aVar);
        }
        int iP = this.f6315h.p(aVar.f6298b);
        if (iP != -1) {
            this.f6317j = 0;
            this.f6293c[this.f6291a - 1] = aVar.f6297a[iP];
            return iP;
        }
        String str = this.f6293c[this.f6291a - 1];
        String strW = w();
        int iJ = J(strW, aVar);
        if (iJ == -1) {
            this.f6317j = 15;
            this.f6320m = strW;
            this.f6293c[this.f6291a - 1] = str;
        }
        return iJ;
    }

    @Override // o.AbstractC0715c
    public void D() throws C0714b, EOFException {
        if (this.f6296f) {
            throw new C0713a("Cannot skip unexpected " + A() + " at " + l());
        }
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 14) {
            U();
        } else if (I2 == 13) {
            R(f6311o);
        } else if (I2 == 12) {
            R(f6310n);
        } else if (I2 != 15) {
            throw new C0713a("Expected a name but was " + A() + " at path " + l());
        }
        this.f6317j = 0;
        this.f6293c[this.f6291a - 1] = "null";
    }

    @Override // o.AbstractC0715c
    public void E() throws C0714b, EOFException {
        if (this.f6296f) {
            throw new C0713a("Cannot skip unexpected " + A() + " at " + l());
        }
        int i2 = 0;
        do {
            int I2 = this.f6317j;
            if (I2 == 0) {
                I2 = I();
            }
            if (I2 == 3) {
                B(1);
            } else if (I2 == 1) {
                B(3);
            } else {
                if (I2 == 4) {
                    i2--;
                    if (i2 < 0) {
                        throw new C0713a("Expected a value but was " + A() + " at path " + l());
                    }
                    this.f6291a--;
                } else if (I2 == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new C0713a("Expected a value but was " + A() + " at path " + l());
                    }
                    this.f6291a--;
                } else if (I2 == 14 || I2 == 10) {
                    U();
                } else if (I2 == 9 || I2 == 13) {
                    R(f6311o);
                } else if (I2 == 8 || I2 == 12) {
                    R(f6310n);
                } else if (I2 == 17) {
                    this.f6316i.F(this.f6319l);
                } else if (I2 == 18) {
                    throw new C0713a("Expected a value but was " + A() + " at path " + l());
                }
                this.f6317j = 0;
            }
            i2++;
            this.f6317j = 0;
        } while (i2 != 0);
        int[] iArr = this.f6294d;
        int i3 = this.f6291a;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.f6293c[i3 - 1] = "null";
    }

    public final void H() throws C0714b {
        if (!this.f6295e) {
            throw G("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final int I() throws C0714b, EOFException {
        int[] iArr = this.f6292b;
        int i2 = this.f6291a;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iL = L(true);
            this.f6316i.readByte();
            if (iL != 44) {
                if (iL != 59) {
                    if (iL != 93) {
                        throw G("Unterminated array");
                    }
                    this.f6317j = 4;
                    return 4;
                }
                H();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5) {
                    int iL2 = L(true);
                    this.f6316i.readByte();
                    if (iL2 != 44) {
                        if (iL2 != 59) {
                            if (iL2 != 125) {
                                throw G("Unterminated object");
                            }
                            this.f6317j = 2;
                            return 2;
                        }
                        H();
                    }
                }
                int iL3 = L(true);
                if (iL3 == 34) {
                    this.f6316i.readByte();
                    this.f6317j = 13;
                    return 13;
                }
                if (iL3 == 39) {
                    this.f6316i.readByte();
                    H();
                    this.f6317j = 12;
                    return 12;
                }
                if (iL3 != 125) {
                    H();
                    if (!K((char) iL3)) {
                        throw G("Expected name");
                    }
                    this.f6317j = 14;
                    return 14;
                }
                if (i3 == 5) {
                    throw G("Expected name");
                }
                this.f6316i.readByte();
                this.f6317j = 2;
                return 2;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iL4 = L(true);
                this.f6316i.readByte();
                if (iL4 != 58) {
                    if (iL4 != 61) {
                        throw G("Expected ':'");
                    }
                    H();
                    if (this.f6315h.request(1L) && this.f6316i.f(0L) == 62) {
                        this.f6316i.readByte();
                    }
                }
            } else if (i3 == 6) {
                iArr[i2 - 1] = 7;
            } else if (i3 == 7) {
                if (L(false) == -1) {
                    this.f6317j = 18;
                    return 18;
                }
                H();
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iL5 = L(true);
        if (iL5 == 34) {
            this.f6316i.readByte();
            this.f6317j = 9;
            return 9;
        }
        if (iL5 == 39) {
            H();
            this.f6316i.readByte();
            this.f6317j = 8;
            return 8;
        }
        if (iL5 != 44 && iL5 != 59) {
            if (iL5 == 91) {
                this.f6316i.readByte();
                this.f6317j = 3;
                return 3;
            }
            if (iL5 != 93) {
                if (iL5 == 123) {
                    this.f6316i.readByte();
                    this.f6317j = 1;
                    return 1;
                }
                int iO = O();
                if (iO != 0) {
                    return iO;
                }
                int iP = P();
                if (iP != 0) {
                    return iP;
                }
                if (!K(this.f6316i.f(0L))) {
                    throw G("Expected value");
                }
                H();
                this.f6317j = 10;
                return 10;
            }
            if (i3 == 1) {
                this.f6316i.readByte();
                this.f6317j = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw G("Unexpected value");
        }
        H();
        this.f6317j = 7;
        return 7;
    }

    public final int J(String str, AbstractC0715c.a aVar) {
        int length = aVar.f6297a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(aVar.f6297a[i2])) {
                this.f6317j = 0;
                this.f6293c[this.f6291a - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    public final boolean K(int i2) throws C0714b {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        H();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
    
        r6.f6316i.F(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        if (r2 != 47) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
    
        if (r6.f6315h.request(2) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0039, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
    
        H();
        r3 = r6.f6316i.f(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
    
        if (r3 == 42) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0049, code lost:
    
        if (r3 == 47) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        r6.f6316i.readByte();
        r6.f6316i.readByte();
        T();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        r6.f6316i.readByte();
        r6.f6316i.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
    
        if (S() == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0071, code lost:
    
        throw G("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
    
        if (r2 != 35) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
    
        H();
        T();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007d, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int L(boolean r7) throws o.C0714b, java.io.EOFException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            D1.c r2 = r6.f6315h
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.request(r4)
            if (r2 == 0) goto L80
            D1.a r2 = r6.f6316i
            long r4 = (long) r1
            byte r2 = r2.f(r4)
            r4 = 10
            if (r2 == r4) goto L7e
            r4 = 32
            if (r2 == r4) goto L7e
            r4 = 13
            if (r2 == r4) goto L7e
            r4 = 9
            if (r2 != r4) goto L25
            goto L7e
        L25:
            D1.a r3 = r6.f6316i
            long r4 = (long) r1
            r3.F(r4)
            r1 = 47
            if (r2 != r1) goto L72
            D1.c r3 = r6.f6315h
            r4 = 2
            boolean r3 = r3.request(r4)
            if (r3 != 0) goto L3a
            return r2
        L3a:
            r6.H()
            D1.a r3 = r6.f6316i
            r4 = 1
            byte r3 = r3.f(r4)
            r4 = 42
            if (r3 == r4) goto L5a
            if (r3 == r1) goto L4c
            return r2
        L4c:
            D1.a r1 = r6.f6316i
            r1.readByte()
            D1.a r1 = r6.f6316i
            r1.readByte()
            r6.T()
            goto L1
        L5a:
            D1.a r1 = r6.f6316i
            r1.readByte()
            D1.a r1 = r6.f6316i
            r1.readByte()
            boolean r1 = r6.S()
            if (r1 == 0) goto L6b
            goto L1
        L6b:
            java.lang.String r7 = "Unterminated comment"
            o.b r6 = r6.G(r7)
            throw r6
        L72:
            r1 = 35
            if (r2 != r1) goto L7d
            r6.H()
            r6.T()
            goto L1
        L7d:
            return r2
        L7e:
            r1 = r3
            goto L2
        L80:
            if (r7 != 0) goto L84
            r6 = -1
            return r6
        L84:
            java.io.EOFException r6 = new java.io.EOFException
            java.lang.String r7 = "End of input"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: o.C0717e.L(boolean):int");
    }

    public final String M(D1.d dVar) throws C0714b {
        StringBuilder sb = null;
        while (true) {
            long jV = this.f6315h.v(dVar);
            if (jV == -1) {
                throw G("Unterminated string");
            }
            if (this.f6316i.f(jV) != 92) {
                if (sb == null) {
                    String strC = this.f6316i.C(jV);
                    this.f6316i.readByte();
                    return strC;
                }
                sb.append(this.f6316i.C(jV));
                this.f6316i.readByte();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.f6316i.C(jV));
            this.f6316i.readByte();
            sb.append(Q());
        }
    }

    public final String N() {
        long jV = this.f6315h.v(f6312p);
        D1.a aVar = this.f6316i;
        return jV != -1 ? aVar.C(jV) : aVar.B();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final int O() throws EOFException {
        String str;
        String str2;
        int i2;
        byte bF = this.f6316i.f(0L);
        if (bF == 116 || bF == 84) {
            str = com.xiaomi.onetrack.util.a.f3424i;
            str2 = "TRUE";
            i2 = 5;
        } else if (bF == 102 || bF == 70) {
            str = "false";
            str2 = "FALSE";
            i2 = 6;
        } else {
            if (bF != 110 && bF != 78) {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i2 = 7;
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.f6315h.request(i4)) {
                return 0;
            }
            byte bF2 = this.f6316i.f(i3);
            if (bF2 != str.charAt(i3) && bF2 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.f6315h.request(length + 1) && K(this.f6316i.f(length))) {
            return 0;
        }
        this.f6316i.F(length);
        this.f6317j = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
    
        if (K(r11) != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0083, code lost:
    
        if (r6 != 2) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0085, code lost:
    
        if (r7 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008b, code lost:
    
        if (r8 != Long.MIN_VALUE) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008d, code lost:
    
        if (r10 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0091, code lost:
    
        if (r8 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
    
        if (r10 != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
    
        if (r10 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0098, code lost:
    
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
    
        r16.f6318k = r8;
        r16.f6316i.F(r5);
        r16.f6317j = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a5, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a6, code lost:
    
        if (r6 == 2) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a9, code lost:
    
        if (r6 == 4) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ac, code lost:
    
        if (r6 != 7) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00af, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b0, code lost:
    
        r16.f6319l = r5;
        r16.f6317j = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b6, code lost:
    
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b7, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int P() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: o.C0717e.P():int");
    }

    public final char Q() throws C0714b, EOFException {
        int i2;
        if (!this.f6315h.request(1L)) {
            throw G("Unterminated escape sequence");
        }
        byte b2 = this.f6316i.readByte();
        if (b2 == 10 || b2 == 34 || b2 == 39 || b2 == 47 || b2 == 92) {
            return (char) b2;
        }
        if (b2 == 98) {
            return '\b';
        }
        if (b2 == 102) {
            return '\f';
        }
        if (b2 == 110) {
            return '\n';
        }
        if (b2 == 114) {
            return '\r';
        }
        if (b2 == 116) {
            return '\t';
        }
        if (b2 != 117) {
            if (this.f6295e) {
                return (char) b2;
            }
            throw G("Invalid escape sequence: \\" + ((char) b2));
        }
        if (!this.f6315h.request(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + l());
        }
        char c2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            byte bF = this.f6316i.f(i3);
            char c3 = (char) (c2 << 4);
            if (bF >= 48 && bF <= 57) {
                i2 = bF - 48;
            } else if (bF >= 97 && bF <= 102) {
                i2 = bF - 87;
            } else {
                if (bF < 65 || bF > 70) {
                    throw G("\\u" + this.f6316i.C(4L));
                }
                i2 = bF - 55;
            }
            c2 = (char) (c3 + i2);
        }
        this.f6316i.F(4L);
        return c2;
    }

    public final void R(D1.d dVar) throws C0714b, EOFException {
        while (true) {
            long jV = this.f6315h.v(dVar);
            if (jV == -1) {
                throw G("Unterminated string");
            }
            if (this.f6316i.f(jV) != 92) {
                this.f6316i.F(jV + 1);
                return;
            } else {
                this.f6316i.F(jV + 1);
                Q();
            }
        }
    }

    public final boolean S() throws EOFException {
        D1.c cVar = this.f6315h;
        D1.d dVar = f6314r;
        long jQ = cVar.q(dVar);
        boolean z2 = jQ != -1;
        D1.a aVar = this.f6316i;
        aVar.F(z2 ? jQ + ((long) dVar.j()) : aVar.E());
        return z2;
    }

    public final void T() throws EOFException {
        long jV = this.f6315h.v(f6313q);
        D1.a aVar = this.f6316i;
        aVar.F(jV != -1 ? jV + 1 : aVar.E());
    }

    public final void U() throws EOFException {
        long jV = this.f6315h.v(f6312p);
        D1.a aVar = this.f6316i;
        if (jV == -1) {
            jV = aVar.E();
        }
        aVar.F(jV);
    }

    @Override // o.AbstractC0715c
    public void c() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 3) {
            B(1);
            this.f6294d[this.f6291a - 1] = 0;
            this.f6317j = 0;
        } else {
            throw new C0713a("Expected BEGIN_ARRAY but was " + A() + " at path " + l());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f6317j = 0;
        this.f6292b[0] = 8;
        this.f6291a = 1;
        this.f6316i.a();
        this.f6315h.close();
    }

    @Override // o.AbstractC0715c
    public void d() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 1) {
            B(3);
            this.f6317j = 0;
            return;
        }
        throw new C0713a("Expected BEGIN_OBJECT but was " + A() + " at path " + l());
    }

    @Override // o.AbstractC0715c
    public void e() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 != 4) {
            throw new C0713a("Expected END_ARRAY but was " + A() + " at path " + l());
        }
        int i2 = this.f6291a;
        this.f6291a = i2 - 1;
        int[] iArr = this.f6294d;
        int i3 = i2 - 2;
        iArr[i3] = iArr[i3] + 1;
        this.f6317j = 0;
    }

    @Override // o.AbstractC0715c
    public void f() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 != 2) {
            throw new C0713a("Expected END_OBJECT but was " + A() + " at path " + l());
        }
        int i2 = this.f6291a;
        int i3 = i2 - 1;
        this.f6291a = i3;
        this.f6293c[i3] = null;
        int[] iArr = this.f6294d;
        int i4 = i2 - 2;
        iArr[i4] = iArr[i4] + 1;
        this.f6317j = 0;
    }

    @Override // o.AbstractC0715c
    public boolean n() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        return (I2 == 2 || I2 == 4 || I2 == 18) ? false : true;
    }

    @Override // o.AbstractC0715c
    public boolean r() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 5) {
            this.f6317j = 0;
            int[] iArr = this.f6294d;
            int i2 = this.f6291a - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (I2 == 6) {
            this.f6317j = 0;
            int[] iArr2 = this.f6294d;
            int i3 = this.f6291a - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new C0713a("Expected a boolean but was " + A() + " at path " + l());
    }

    @Override // o.AbstractC0715c
    public double t() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 16) {
            this.f6317j = 0;
            int[] iArr = this.f6294d;
            int i2 = this.f6291a - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f6318k;
        }
        if (I2 == 17) {
            this.f6320m = this.f6316i.C(this.f6319l);
        } else if (I2 == 9) {
            this.f6320m = M(f6311o);
        } else if (I2 == 8) {
            this.f6320m = M(f6310n);
        } else if (I2 == 10) {
            this.f6320m = N();
        } else if (I2 != 11) {
            throw new C0713a("Expected a double but was " + A() + " at path " + l());
        }
        this.f6317j = 11;
        try {
            double d2 = Double.parseDouble(this.f6320m);
            if (this.f6295e || !(Double.isNaN(d2) || Double.isInfinite(d2))) {
                this.f6320m = null;
                this.f6317j = 0;
                int[] iArr2 = this.f6294d;
                int i3 = this.f6291a - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return d2;
            }
            throw new C0714b("JSON forbids NaN and infinities: " + d2 + " at path " + l());
        } catch (NumberFormatException unused) {
            throw new C0713a("Expected a double but was " + this.f6320m + " at path " + l());
        }
    }

    public String toString() {
        return "JsonReader(" + this.f6315h + ")";
    }

    @Override // o.AbstractC0715c
    public int u() throws C0714b, EOFException {
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 16) {
            long j2 = this.f6318k;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.f6317j = 0;
                int[] iArr = this.f6294d;
                int i3 = this.f6291a - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new C0713a("Expected an int but was " + this.f6318k + " at path " + l());
        }
        if (I2 == 17) {
            this.f6320m = this.f6316i.C(this.f6319l);
        } else if (I2 == 9 || I2 == 8) {
            String strM = I2 == 9 ? M(f6311o) : M(f6310n);
            this.f6320m = strM;
            try {
                int i4 = Integer.parseInt(strM);
                this.f6317j = 0;
                int[] iArr2 = this.f6294d;
                int i5 = this.f6291a - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        } else if (I2 != 11) {
            throw new C0713a("Expected an int but was " + A() + " at path " + l());
        }
        this.f6317j = 11;
        try {
            double d2 = Double.parseDouble(this.f6320m);
            int i6 = (int) d2;
            if (i6 == d2) {
                this.f6320m = null;
                this.f6317j = 0;
                int[] iArr3 = this.f6294d;
                int i7 = this.f6291a - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new C0713a("Expected an int but was " + this.f6320m + " at path " + l());
        } catch (NumberFormatException unused2) {
            throw new C0713a("Expected an int but was " + this.f6320m + " at path " + l());
        }
    }

    @Override // o.AbstractC0715c
    public String w() throws C0714b, EOFException {
        String strM;
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 14) {
            strM = N();
        } else if (I2 == 13) {
            strM = M(f6311o);
        } else if (I2 == 12) {
            strM = M(f6310n);
        } else {
            if (I2 != 15) {
                throw new C0713a("Expected a name but was " + A() + " at path " + l());
            }
            strM = this.f6320m;
        }
        this.f6317j = 0;
        this.f6293c[this.f6291a - 1] = strM;
        return strM;
    }

    @Override // o.AbstractC0715c
    public String x() throws C0714b, EOFException {
        String strC;
        int I2 = this.f6317j;
        if (I2 == 0) {
            I2 = I();
        }
        if (I2 == 10) {
            strC = N();
        } else if (I2 == 9) {
            strC = M(f6311o);
        } else if (I2 == 8) {
            strC = M(f6310n);
        } else if (I2 == 11) {
            strC = this.f6320m;
            this.f6320m = null;
        } else if (I2 == 16) {
            strC = Long.toString(this.f6318k);
        } else {
            if (I2 != 17) {
                throw new C0713a("Expected a string but was " + A() + " at path " + l());
            }
            strC = this.f6316i.C(this.f6319l);
        }
        this.f6317j = 0;
        int[] iArr = this.f6294d;
        int i2 = this.f6291a - 1;
        iArr[i2] = iArr[i2] + 1;
        return strC;
    }
}
