import java.util.Random;

public class Passenger {
    Random random = new Random();
    private int currentFloor;
    private int wantFloor;
    private boolean isMove;

    public Passenger(int countOfFloor){
        currentFloor = random.nextInt(countOfFloor) + 1;
        do
            wantFloor = random.nextInt(countOfFloor) + 1;
        while (wantFloor == currentFloor);
        isMove = false;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int change) {
        this.currentFloor += change;
    }

    public int getWantFloor() {
        return wantFloor;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }
}
