public class Main {
    public static void main(String[] args) {
        GameFacade facade = new GameFacade();
        facade.startGame();

        Menu menu = Menu.getInstance();
        menu.showMenu(); 
    }
}