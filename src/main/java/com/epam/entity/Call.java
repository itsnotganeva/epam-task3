package com.epam.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Call extends Thread {

    private CallCenter callCenter;
    private Client client;

    private static final Logger LOG = LoggerFactory.getLogger(Call.class);

    public Call(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        try {
            sleep(10);
            LOG.info("Client {} called the operator and is talking with him", client.getClientNum());
            sleep(10);
            client.isServed();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setClient(Client client) {
        this.client = client;
    }

}
