package game;
public class MovimentoInvalido extends Exception {
   public MovimentoInvalido() {
      super();
   }

   public MovimentoInvalido(String message) {
      super(message);
   }
}