import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Из дополнительных задач реализовано получение новых уровней(увеличивает хп),
// также восстановление здоровья и увеличение характеристик с помощью зелий. Создан торговец и реализована покупка зелий,
// использование зелий работает через инвентарь.
//В бою реализованы криты, также в бою учитывается ловкость противника в качестве защиты.

public class Realm {
    private static Merchant merchant = new Merchant();

    private static BufferedReader br;
    static int checkCommand = 99;

    private static Player player = null;
    private static BattleScene battleScene = null;

    private static void command(String string) throws IOException {


        if (player == null) {
            player = new Player(
                    string,
                    100,
                    5,
                    5,
                    0,
                    50
            );
            System.out.printf("Спасти наш мир от временно не созданных драконов(они в отпуске) вызвался %s! Удачи!\n", player.getName());

        }


        switch (string) {
            case "1" -> {
                merchant.goodsList();
                System.out.println("У вас " + player.getCoin() + " coins");
                try {
                    Merchant.goodsNumber = Integer.parseInt(br.readLine());
                    if ((1 <= Merchant.goodsNumber) && (Merchant.goodsNumber <= checkCommand)) {
                        merchant.sell();
                        player.buy();
                        System.out.println("Что-то еще? (ага/нет)");
                    }
                    else throw new Exception();
                } catch (Exception a) {
                    System.out.println("Введено неверное значение!");
                    command("1");
                }

            }
            case "4" -> {
                player.inventory();
                try {
                    player.potion = Integer.parseInt(br.readLine());
                    if ((1 <= player.potion) && (player.potion <= checkCommand)) {
                        player.use();
                        printNavigation();
                    }
                    else throw new Exception();
                } catch (Exception a) {
                    System.out.println("Введено неверное значение!");
                    command("4");
                }
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> {
                System.exit(1);
            }
            case "нет" -> {
                printNavigation();
            }
            case "ага" -> {
                command("1");
            }
            case "да" -> {
                command("2");
            }
            default -> {
                printNavigation();
                System.out.println("*выберите один из пунктов выше");
            }
        }

        command(br.readLine());
    }
    private static Creature createMonster() {
        int random = (int) (Math.random() * 10);

        if (random % 2 == 0) return new Goblin(
                "Гоблин",
                20,
                4,
                6,
                15,
                0
        );
        else return new Skeleton(
                "Скелет",
                15,
                6,
                4,
                20,
                0
        );//Метод getCoin() у скелетов и гоблинов переопределен, количество монет выпадает случайным образом
    }
    interface FightCallback {
        void fightWin();
        void fightLost();
    }
    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                player.checkLevelUp();
                System.out.printf("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.%n", player.getName(), player.getXp(), player.getCoin(), player.getHp());
                System.out.println("Продолжить поход? (да/нет)");
            }

            @Override
            public void fightLost() {
                System.exit(0);
            }
        });
    }
    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. На выход");
        System.out.println("4. Открыть инвентарь");
    }

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));

        battleScene = new BattleScene();

        System.out.println("Введите имя персонажа:");

        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
