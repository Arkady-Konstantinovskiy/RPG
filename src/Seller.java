public interface Seller {
    int sell();
    String goodsList();
    default void toddHi(){
        System.out.println("Вас встречает торговец Тодд.\nP.S: При взгляде на него вы понимаете, что он хочет чтобы вы что-то купили...");
    };
    default void toddByu(){
        System.out.println("Торговец настойчиво пытался вам продать какие-то древние свитки, но вы вроде не маг, да и драконы пока в отпуске...");
    };
}