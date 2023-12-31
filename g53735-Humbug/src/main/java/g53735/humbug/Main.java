package g53735.humbug;

import g53735.humbug.controller.Controller;
import g53735.humbug.model.Game;
import g53735.humbug.view.text.View;
import java.util.Scanner;

/**
 * Create the controller and start the game.
 *
 * @author g53735
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("              HUMBUG             ");
        System.out.println("---------------------------------");
        Controller controller = new Controller(new Game(), new View(
                new Scanner(System.in)));
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What level do you want to play(0-100): ");
        int nLevel = keyboard.nextInt();
        controller.startGame(nLevel);
    }
}
