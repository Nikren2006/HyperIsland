package miuix.module;

/* JADX INFO: loaded from: classes.dex */
public class Dependency {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_INSTALLED = 2;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_OPTIONAL = 1;
    private Level level;
    private String name;
    private int type;

    public static class Level {
        private int maxLevel;
        private int minLevel;
        private int targetLevel;

        public int getMaxLevel() {
            return this.maxLevel;
        }

        public int getMinLevel() {
            return this.minLevel;
        }

        public int getTargetLevel() {
            return this.targetLevel;
        }

        public void setMaxLevel(int i2) {
            this.maxLevel = i2;
        }

        public void setMinLevel(int i2) {
            this.minLevel = i2;
        }

        public void setTargetLevel(int i2) {
            this.targetLevel = i2;
        }
    }

    public Level getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(int i2) {
        this.type = i2;
    }
}
