import jade.core.behaviours.SimpleBehaviour;

public class SampleBehaviour extends SimpleBehaviour {

    private double number;

    SampleBehaviour(double number) {
        this.number = number;
    }

    @Override
    public void action() {
        number -= 1;
        System.out.println("Sample behaviour: " + number);
    }

    @Override
    public boolean done() {
        return true;
    }
}
