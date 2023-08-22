abstract public class Creature implements Battle {
    private String name;
    private int hp;
    private int str;
    private int dex;
    private int xp;
    private int coin;
    public Creature(String name, int hp, int str, int dex, int xp, int coin) {
        this.name = name;
        this.hp = hp;
        this.str = str;
        this.dex = dex;
        this.xp = xp;
        this.coin = coin;
    }
    private int getD8() {
        return (int) (Math.random() * 7 + 1);
    }
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getXp() {
        return xp;
    }

    public int getCoin() {
        return coin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, hp);
    }
    @Override
    public int attack() {
       return str+getD8();
    }
}
