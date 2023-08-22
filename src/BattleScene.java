public class BattleScene {
    public void fight(Creature player, Creature monster, Realm.FightCallback fightCallback) {

        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeHit(Creature defender, Creature attacker, Realm.FightCallback fightCallback) {
        int d20 = (int) (Math.random() * 19 + 1);
        int hit = attacker.attack();
        System.out.println("!!" + d20 + "!!");
        if(d20 == 1) {
            hit = 0;
            System.out.println("Критический промах!");
        } else if ((attacker.getDex() + d20) <= (10 + defender.getDex())) {
            hit = 0;
        } else if (d20 == 20) {
            hit = hit * 2;
            System.out.println("Критическое попадание!");
        }
        int defenderHealth = defender.getHp() - hit;

        if (hit != 0) {
            System.out.printf("%s Нанес удар в %d единиц!%n", attacker.getName(), hit);
            System.out.printf("У %s осталось %d единиц здоровья...%n", defender.getName(), defenderHealth);
        } else {

            System.out.printf("%s промахнулся!%n", attacker.getName());
        }
        if (defenderHealth <= 0 && defender instanceof Player) {

            System.out.println("*грустная музыка*(пук-пук-пууук)\nВы пали в бою...");
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.printf("Враг повержен! Вы получаете %d опыт и %d золота%n", defender.getXp(), defender.getCoin());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setCoin(attacker.getCoin() + defender.getCoin());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }
}
