package homework;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class MessageAgent extends Agent {

	private static final String TAG_DEBUG = "DEBUG_MESSAGE_AGENT: ";

	@Override
	protected void setup() {

		Object[] args = getArguments();

		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(new AID(Main.userAID, AID.ISLOCALNAME));
		msg.setContent("ask");
		send(msg);

		doDelete();
	}

	@Override
	protected void takeDown() {
		System.out.println(TAG_DEBUG + "dying");
		super.takeDown();
	}
}
