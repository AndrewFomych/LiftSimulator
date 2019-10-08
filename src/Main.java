import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int MAXFLOOR = 20;
    private static final int MINFLOOR = 5;
    private static final int MAXPASSENGERS = 10;
    private static final int MAXPASSENGERSINLIFT = 5;

    public static void main(String[] args) throws IOException {
        Random random = new Random();

        final int countOfFloor = random.nextInt((MAXFLOOR-MINFLOOR)+1) + MINFLOOR;
        ArrayList<Passenger> listPassenger = new ArrayList();

        for (int i = 0; i < countOfFloor; i++) {
            for (int j = 0; j < random.nextInt(MAXPASSENGERS+1); j++) {
                listPassenger.add(new Passenger(countOfFloor));
            }
        }

        Lift lift = new Lift(MAXPASSENGERSINLIFT, countOfFloor);
        Logic logicBuild = new Logic(listPassenger, countOfFloor, lift);

        do {
            logicBuild.draw();
            logicBuild.turnLift();
            logicBuild.moveOut();
            logicBuild.move();
        }while (!logicBuild.isEnd());
    }
}
