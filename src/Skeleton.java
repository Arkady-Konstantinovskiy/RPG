public class Skeleton extends Creature {
    public Skeleton(String name, int hp, int str, int dex, int xp, int coin) {
        super(name, hp, str, dex, xp, coin);
    }
    @Override
    public int getCoin() {
        return (int) (Math.random() * 5);
    }
}
