package homework;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class UserAgent extends Agent {

    private static final String TAG_DEBUG = "DEBUG_USER_AGENT: ";

    @Override
    protected void setup() {
        System.out.println(TAG_DEBUG + "Create user agent");

        addBehaviour(new MessageReceiver());

        super.setup();
    }

    private void askForWeather() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID(Main.weatherAID, AID.ISLOCALNAME));
        msg.setContent("Give me weather status.");
        send(msg);
    }

    private class MessageReceiver extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage receivedMsg = receive();

            if (receivedMsg != null) {
                if (receivedMsg.getSender().getLocalName().equals(Main.messageAID)) {
                    System.out.println(TAG_DEBUG + "asking for weather");
                    askForWeather();

                } else if (receivedMsg.getSender().getLocalName().equals(Main.weatherAID)) {
                    System.out.println("Today is " + receivedMsg.getContent());

                } else {
                    System.out.println(TAG_DEBUG + "new message from: " + receivedMsg.getSender().getLocalName());
                }
            }

            try {
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
