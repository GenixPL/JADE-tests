import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class SampleAgent extends Agent {
    protected void setup() {
        System.out.println("Agent:" + getAID().getName());

        Behaviour b = new OneShotBehaviour(this) {
            @Override
            public void action() {
                System.out.println("One shot behaviour");
            }
        };

        Behaviour b2 = new WakerBehaviour(this, 1000) {
            @Override
            protected void onWake() {
                System.out.println("Waker behaviour");
                super.onWake();
            }
        };

        Behaviour b3 = new SampleBehaviour(100) {};

        Behaviour factorialBehaviour = new SimpleBehaviour() {

            private double num = 1;
            private int n = 0;

            @Override
            public void action() {
                System.out.println("action");

                if (n == 0) {
                    n++;
                    return;
                }

                num *= n;
                n++;
            }

            @Override
            public boolean done() {
                System.out.println("done");

                if (n >= 10) {
                    System.out.println(num);
                    return true;
                } else {
                    return false;
                }
            }
        };

        addBehaviour(b);
        addBehaviour(b2);
        addBehaviour(b3);
        addBehaviour(factorialBehaviour);

        b3.action();
        b3.action();
    }

    protected void takeDown() {
        super.takeDown();
    }


}
