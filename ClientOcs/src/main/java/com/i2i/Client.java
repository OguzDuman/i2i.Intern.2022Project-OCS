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
                ("34.77.94.205", "34.77.94.205",
                        "Customers Map (Current Map)");
        int wantedSize = 10;
        ArrayList<String> msisdnList = hazelcastConfiguration.getMsisdnList(wantedSize);
        ArrayList<String> opNumberList = hazelcastConfiguration.getMsisdnList(wantedSize);
        String msisdn;
        String opNumber;

        Random rand = new Random();
        while(true) {
            if(rand.nextInt()%100000000!=0){ //randomly select 1 of 5 cycles
                continue;
            }
            msisdn = msisdnList.get(rand.nextInt(msisdnList.size()));
            opNumber = opNumberList.get(rand.nextInt(opNumberList.size()));
            if (msisdn.equals(opNumber)){
                do {
                    opNumber = opNumberList.get(rand.nextInt(opNumberList.size()));
                }while (opNumber.equals(msisdn));
            }
            client.tell(msisdn + "x" + opNumber, ActorRef.noSender());
            if(rand.nextInt()%5==0){
                msisdnList = hazelcastConfiguration.getMsisdnList(wantedSize);
                opNumberList = hazelcastConfiguration.getMsisdnList(wantedSize);
            }
        }
    }
}
