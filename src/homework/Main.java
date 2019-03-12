package homework;

import jade.Boot;
import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static final String userAID = "user";
    public static final String weatherAID = "weather";
    public static final String messageAID = "message";

    private static ContainerController agentContainer;

    public static void main(String args[]) {
        initAgents();
        initUi();
    }

    private static void initAgents() {
        try {
            String[] container = {
                    "-gui",
                    "-local-host 127.0.0.1",
                    "-container",
                    userAID + ":homework.UserAgent;" +
                            weatherAID + ":homework.WeatherAgent"
            };
            Boot.main(container);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initUi() {
        Frame frame = new Frame();
        frame.setBounds(100, 100, 300, 300);

        Button userButton = new Button("user's request");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAgentToSendMessage();
            }
        });

        frame.add(userButton);
        frame.show();
    }

    private static void getAgentToSendMessage() {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        ContainerController container = runtime.createAgentContainer(profile);

        Object args[] = new Object[1];
        args[0] = "dupa";

        try {
            AgentController msgAgent = container.createNewAgent(messageAID, "homework.MessageAgent", args);
            msgAgent.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
