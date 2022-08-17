package com.i2i;

import java.util.Random;

public class OPNumberGenerator {
    enum InternetOPs{
        YOUTUBE,
        FACEBOOK,
        INSTAGRAM,
        GOOGLE,
        TIKTOK,
        SPOTIFY,
        SNAPCHAT,
        OTHER
    }
    Random rand;

    OPNumberGenerator(){
        rand = new Random();
    }
    public String getOPNumber(String service,String msisdn){
        if(service.equals("DATA")){
            int randnum = rand.nextInt(InternetOPs.values().length + 2);
            InternetOPs internetOP;
            switch (randnum){
                case 0:
                    internetOP = InternetOPs.YOUTUBE;
                    break;
                case 1:
                    internetOP = InternetOPs.FACEBOOK;
                    break;
                case 2:
                    internetOP = InternetOPs.INSTAGRAM;
                    break;
                case 3:
                    internetOP = InternetOPs.GOOGLE;
                    break;
                case 4:
                    internetOP = InternetOPs.SPOTIFY;
                    break;
                case 5:
                    internetOP = InternetOPs.TIKTOK;
                    break;
                case 6:
                    internetOP = InternetOPs.SNAPCHAT;
                    break;
                default:
                    internetOP = InternetOPs.OTHER;
                    break;
            }
            return internetOP.toString();
        }
        return msisdn;
    }
}
