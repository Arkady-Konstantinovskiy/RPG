import java.io.IOException;

public class Player extends Creature {
    public Player(String name, int hp, int str, int dex, int xp, int coin) {
        super(name, hp, str, dex, xp, coin);
    }
    private int potion_hp = 0;
    private int potion_str = 0;
    private int potion_dex = 0;
    private int playerLevel = 1;
    private int maxHp = 100;
    int potion = 0;
    public int checkLevelUp(){
        if (getXp() >= 100 * playerLevel ) {
            setXp(getXp() - (100 * playerLevel));
            maxHp += 20;
            System.out.println("ВАШ УРОВЕНЬ ПОВЫСИЛСЯ ДО " + playerLevel + "УР!!!\nМаксимальное здоровье увеличено на 20hp!");
            return maxHp;
        }
        return 0;
    }
    public void inventory(){
        System.out.printf("Имя: %s;\nУровень: %s ур;\nЗдоровье: %s hp;\nСила: %s str;\nЛовкость: %s dex;\nОпыт: %s/%s;\nДеньги: %s;", getName(), playerLevel, getHp(), getStr(), getDex(), getXp(), (200*playerLevel), getCoin());
        System.out.println("\nИнвентарь:\n1. " + potion_hp + " зелья здоровья;\n2. " + potion_str + " зелья силы;\n3. " + potion_dex + " зелья ловкости;");
        System.out.println("Введите номер, чтобы использовать зелье\n4. Выход");
    }
    public void use() {
        switch (potion){
            case 1 -> {
                if (potion_hp <= 0) {
                    System.out.println("Зелий лечения нет!");
                } else {
                    setHp(maxHp);
                    potion_hp -= 1;
                    System.out.println("Ваше здоровье " + getHp() + "hp");
                }
            }
            case 2 -> {
                if (potion_str <= 0) {
                    System.out.println("Зелий силы нет!");
                } else {
                    setStr(getStr() + 1);
                    potion_str -= 1;
                    System.out.println("Ваша сила " + getStr() + "str");
                }
            }
            case 3 -> {
                if (potion_dex <= 0) {
                    System.out.println("Зелий ловкости нет!");
                } else {
                    setDex(getDex() + 1);
                    potion_dex -= 1;
                    System.out.println("Ваша ловкость " + getStr() + "dex");
                }
            }
            case 4 -> {}
        }
    }
    public void buy(){
        if (Merchant.cost > getCoin() ) {
            System.out.println("Недостаточно денег для покупки!");
        } else if (1 <= Merchant.goodsNumber & Merchant.goodsNumber <= 3) {
            setCoin(getCoin()-Merchant.cost);
            switch (Merchant.goodsNumber){
                case 1 -> potion_hp++;
                case 2 -> potion_str++;
                case 3 -> potion_dex++;
            }
        }
        System.out.println("У вас осталось " + getCoin() + " coins");
        System.out.println("Ваши зелья:\n " + potion_hp + " зелья здоровья;\n " + potion_str + " зелья силы;\n " + potion_dex + " зелья ловкости;");
    }
    //    public void usePotion_hp(){
//        if (potion_hp <= 0) {
//            System.out.println("Зелий лечения нет!");
//        } else {
//            setHp(maxHp);
//            potion_hp -= 1;
//        }
//    }
//    public void usePotion_str(){
//        if (potion_str <= 0) {
//            System.out.println("Зелий силы нет!");
//        } else {
//            setStr(getStr() + 1);
//            potion_str -= 1;
//        }
//    }
//    public void usePotion_dex(){
//        if (potion_dex <= 0) {
//            System.out.println("Зелий ловкости нет!");
//        } else {
//            setDex(getDex() + 1);
//            potion_dex -= 1;
//        }
//    }
}
