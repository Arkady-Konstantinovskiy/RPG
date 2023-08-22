public class Goblin extends Creature {
    public Goblin(String name, int hp, int str, int dex, int xp, int coin) {
        super(name, hp, str, dex, xp, coin);
    }

    @Override
    public int getCoin() {
        return (int) (Math.random() * 30);
    }
}
