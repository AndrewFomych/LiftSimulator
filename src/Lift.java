
public class Lift {
    private int currentFloor;
    private int maxPassengers;
    private boolean turnUp;
    private int countPassengers;
    private int countOfFloor;

    public Lift(int maxPassengers, int countFloor){
        currentFloor = 1;
        this.maxPassengers = maxPassengers;
        turnUp = true;
        countPassengers = 0;
        this.countOfFloor = countFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int change) {
        if(this.currentFloor == this.countOfFloor)
            this.currentFloor--;
        else if(this.currentFloor == 1)
            this.currentFloor++;
        else
        this.currentFloor += change;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public void setCountPassengers(int change) {
        this.countPassengers += change;
    }

    public boolean isTurnUp() {
        return turnUp;
    }

    public void setTurnUp(boolean turnUp) {
        this.turnUp = turnUp;
    }
}
