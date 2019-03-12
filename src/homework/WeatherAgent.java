package homework;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class WeatherAgent extends Agent {

    private static final String TAG_DEBUG = "DEBUG_WEATHER_AGENT: ";

    @Override
    protected void setup() {
        System.out.println(TAG_DEBUG + "Create weather agent");

        addBehaviour(new MessageReceiver());

        super.setup();
    }

    private void informAboutWeather() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(Main.userAID, AID.ISLOCALNAME));
        msg.setContent(getWeather());
        send(msg);
    }

    private String getWeather() {
        Random random = new Random();
        int randInt = random.nextInt(2);

        if (randInt % 2 == 0) {
            return "Raining";
        } else {
            return "Sunny day";
        }
    }

    private class MessageReceiver extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage receivedMsg = receive();

            if (receivedMsg != null) {
                if (receivedMsg.getSender().getLocalName().equals(Main.userAID)) {
                    System.out.println(TAG_DEBUG + "new message from " + Main.userAID);

                    informAboutWeather();
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
