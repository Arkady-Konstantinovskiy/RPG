public class Merchant implements Seller {
    public static int cost;
    public static int goodsNumber = 0;

    @Override
    public String goodsList() {
        System.out.println(goods);
        return null;
    }

    //Метод для продажи
    @Override
    public int sell() {
        switch (goodsNumber){
            case 1 -> cost = 20;
            case 2 -> cost = 100;
            case 3 -> cost = 150;
            case 4 -> cost = 0;
        }
        return cost;
    }
    private String goods = "Товары: \n1. Зелье здоровья (полностью восстанавливает здоровье; 20 coins)\n2. Зелье силы (+1str; 100 coins)\n3. Зелье ловкости (+1dex; 150 coins)\n4. Ничего не нужно";

}