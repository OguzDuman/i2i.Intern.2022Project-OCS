package com.i2i;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.com.i2i.internship.EyeCell.Hazelcast.HazelcastConfiguration;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static final Logger logger = LogManager.getLogger(Client.class);
    public static void main(String[] args) {
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteClient", ConfigFactory.load());

        // Client actor
        ActorRef client = system.actorOf(Props.create(ClientActor.class));

        HazelcastConfiguration hazelcastConfiguration = new HazelcastConfiguration();
        hazelcastConfiguration.initConnection
                ("34.77.94.205", "34.77.94.205:5702",
                        "Customers Map (Current Map)");
        ArrayList<String> msisdnList = hazelcastConfiguration.getMsisdnList(10);
        ArrayList<String> opNumberList = hazelcastConfiguration.getMsisdnList(10);
        String msisdn;
        String opNumber;
        //String msisdn = msisdnList.get(0);

        Random rand = new Random();
        while(true) {
            if(rand.nextInt()%100000000!=0){ //randomly select 1 of 5 cycles
                continue;
            }
            msisdn = msisdnList.get(rand.nextInt(10));
            opNumber = opNumberList.get(rand.nextInt(10));
            client.tell(msisdn + "x" + opNumber, ActorRef.noSender());
            if(rand.nextInt()%5==0){
                msisdnList = hazelcastConfiguration.getMsisdnList(10);
                opNumberList = hazelcastConfiguration.getMsisdnList(10);
            }
        }
    }
}
