import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RespondingAgent extends Agent {
    @Override
    protected void setup() {
        Behaviour cyclicRespond = new CyclicBehaviour(this) {
            @Override
            public void action() {
                System.out.println("LOG: Receiving");

                ACLMessage receivedMsg = receive();
                if (receivedMsg != null) {
                    System.out.println(receivedMsg.getContent());

                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Asking", AID.ISLOCALNAME));
                    msg.setContent("given");
                    send(msg);
                }

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };

        addBehaviour(cyclicRespond);

        super.setup();
    }
}
