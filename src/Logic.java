import java.util.ArrayList;
import java.util.Random;

public class Logic {
    private static final int UP = 1;
    private static final int DOWN = -1;

    private ArrayList<Passenger> listPassenger;
    private int countOfFloor;
    private Lift lift;
    private  int countSteps;
    private int countDone;
    private boolean end;

    public Logic(ArrayList<Passenger> listPassenger, int countOfFloor, Lift lift){
        this.listPassenger = listPassenger;
        this.countOfFloor = countOfFloor;
        this.lift = lift;
        countSteps = 0;
        countDone = 0;
        end = false;
    }

    public  void draw(){
        //print start of new step
        countSteps++;
        System.out.println("***Step "+ countSteps +"***");
        for (int i = countOfFloor; i >= 1; i--) {
            if (lift.getCurrentFloor() == i){
                if (lift.isTurnUp())
                    System.out.print("^ ");
                else
                    System.out.print("V ");
            }
        //print wanting number floor of passengers which are in the lift
            for (int j = 0; j < listPassenger.size(); j++) {
                Passenger currentPassenger = listPassenger.get(j);
                if (currentPassenger.getCurrentFloor() == i && currentPassenger.isMove()) {
                    System.out.print(currentPassenger.getWantFloor() + " ");
                }
            }

        //print wanting number floor of passengers which are waiting the lift
            System.out.print("|");
            for (int j = 0; j < listPassenger.size(); j++) {
                Passenger currentPassenger = listPassenger.get(j);
                if (currentPassenger.getCurrentFloor() == i && !currentPassenger.isMove()) {
                    System.out.print(currentPassenger.getWantFloor() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

     //it stop when all passengers will be at wanting floor
        countDone = 0;
        for (int i = 0; i < listPassenger.size(); i++) {
            Passenger currentPassenger = listPassenger.get(i);
            if(currentPassenger.getCurrentFloor() != currentPassenger.getWantFloor())
                countDone--;
        }
        if (countDone == 0)
            end = true;
    }

    public void move() {

        // move passengers which are in the lift UP or DOWN
        for (int i = 0; i < listPassenger.size(); i++) {
            Passenger currentPassenger = listPassenger.get(i);
            if(lift.isTurnUp() && currentPassenger.getCurrentFloor() == lift.getCurrentFloor() &&
                    currentPassenger.isMove()){
                currentPassenger.setCurrentFloor(UP);
            } else if(!lift.isTurnUp() && currentPassenger.getCurrentFloor() == lift.getCurrentFloor() &&
                    currentPassenger.isMove()){
                currentPassenger.setCurrentFloor(DOWN);
            }
        }

        // move passengers in the lift if they need it and there is have place in the lift (UP)
        if (lift.getCountPassengers() < lift.getMaxPassengers()) {
            if (lift.isTurnUp()) {
                for (int i = 0; i < listPassenger.size(); i++) {
                    Passenger currentPassenger = listPassenger.get(i);
                    if (currentPassenger.getCurrentFloor() == lift.getCurrentFloor() &&
                            (currentPassenger.getWantFloor() - currentPassenger.getCurrentFloor()) > 0 &&
                            lift.getCountPassengers() < lift.getMaxPassengers()) {
                        if(!currentPassenger.isMove()) {
                            lift.setCountPassengers(UP);
                            currentPassenger.setMove(true);
                            currentPassenger.setCurrentFloor(UP);
                        }
                    }
                }
                lift.setCurrentFloor(UP);

                //DOWN
            } else {
                for (int i = 0; i < listPassenger.size(); i++) {
                    Passenger currentPassenger = listPassenger.get(i);
                    if (currentPassenger.getCurrentFloor() == lift.getCurrentFloor() &&
                            (currentPassenger.getWantFloor() - currentPassenger.getCurrentFloor()) < 0 &&
                            lift.getCountPassengers() < lift.getMaxPassengers()) {
                        if(!currentPassenger.isMove()) {
                            lift.setCountPassengers(UP);
                            currentPassenger.setMove(true);
                            currentPassenger.setCurrentFloor(DOWN);
                        }
                    }
                }
                lift.setCurrentFloor(DOWN);
            }

            // move lift if it full
        } else {
            if(lift.isTurnUp())
                lift.setCurrentFloor(UP);
            else
                lift.setCurrentFloor(DOWN);
        }
    }

    public void moveOut(){
        // passengers which are in the lift move out if they arrived to needed floor
        if (lift.getCountPassengers() > 0) {
            for (int i = 0; i < listPassenger.size(); i++) {
                Passenger currentPassenger = listPassenger.get(i);
                if (currentPassenger.isMove() &&
                        currentPassenger.getWantFloor() == currentPassenger.getCurrentFloor()) {
                    currentPassenger.setMove(false);
                    lift.setCountPassengers(DOWN);
                }
            }
        }
    }

    public void turnLift() {
        Random random = new Random();
        // turn up or down the lift if it without passengers
        // if there are passengers in the lift then it already has a turn
        if (lift.getCountPassengers() == 0) {
            // if it at 1-st floor
            if (lift.getCurrentFloor() == 1) {
                lift.setTurnUp(true);
                // if it at last floor
            } else if (lift.getCurrentFloor() == countOfFloor) {
                lift.setTurnUp(false);
            } else {
                // if it is at middle of building then count who need to UP or DOWN and turn
                int countTurn = 0;
                for (int i = 0; i < listPassenger.size(); i++) {
                    Passenger currentPassenger = listPassenger.get(i);
                    if (currentPassenger.getCurrentFloor() == lift.getCurrentFloor() &&
                            currentPassenger.getWantFloor() != currentPassenger.getCurrentFloor()){
                        if(currentPassenger.getCurrentFloor() < currentPassenger.getWantFloor())
                            countTurn++;
                        if(currentPassenger.getCurrentFloor() > currentPassenger.getWantFloor())
                            countTurn--;
                    }
                }
                if(countTurn > 0) {
                    lift.setTurnUp(true);
                } else if (countTurn < 0) {
                    lift.setTurnUp(false);
                    // if the lift is empty and at floor not have waiting passengers then random to turn
                } else {
                    lift.setTurnUp(random.nextBoolean());
                }
            }
        }
        // in any case, if the lift on the first or last floor
        if (lift.getCurrentFloor() == 1){
            lift.setTurnUp(true);
        } else if (lift.getCurrentFloor() == countOfFloor){
            lift.setTurnUp(false);
        }
    }

    public boolean isEnd() {
        return end;
    }
}


