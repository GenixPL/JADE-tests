import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AskingAgent extends Agent {
    @Override
    protected void setup() {
        Behaviour cyclicAsk = new CyclicBehaviour(this) {
            @Override
            public void action() {
                System.out.println("LOG: Asking");

                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("Responding", AID.ISLOCALNAME));
                msg.setContent("gimme");
                send(msg);

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };

        Behaviour cyclicReceive = new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMsg = receive();
                if (receivedMsg != null) {
                    System.out.println(receivedMsg.getContent());
                }
            }
        };

        addBehaviour(cyclicAsk);
        addBehaviour(cyclicReceive);

        super.setup();
    }
}
