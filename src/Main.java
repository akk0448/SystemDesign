import parkinglot.ParkingLotMain;
import tictactoe.Game;

public class Main {
    public static void main(String[] args) {
        ParkingLotMain parkingLotMain = new ParkingLotMain();
//        parkingLotMain.simulateParkingLot();
        Game game = new Game();
        game.play();
    }
}