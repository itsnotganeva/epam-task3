package com.epam.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client extends Thread{

    private Integer clientNum;
    private CallCenter callCenter;
    private Call call;
    private boolean served = false;

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    public Client(Integer clientNum, CallCenter callCenter) {
        this.clientNum = clientNum;
        this.callCenter = callCenter;
    }

    public Client(Integer clientNum, CallCenter callCenter, Call call) {
        this.clientNum = clientNum;
        this.callCenter = callCenter;
        this.call = call;
        call.setClient(this);
    }

    @Override
    public void run() {
        try {
            Operator operator = getOperator();

            if (operator== null){
                hungUpAndRecall(operator);
            } else {
                sleep((long) (Math.random() * 1000));
                call.start();
                releaseOperator(operator);
            }

        } catch (Throwable e) {
            interrupt();
            LOG.error("Client died", e);
            throw new RuntimeException();
        }
    }

    public Operator getOperator() throws InterruptedException {
        sleep(100);
        Operator operator = callCenter.getOperator(this);
        if (operator!=null) {
            operator.addForTalk(this);
            LOG.info("Client {} calls the operator {}", clientNum, operator.getOperatorNum());
        }
        return operator;
    }

    public Integer getClientNum() {
        return clientNum;
    }

    public void isServed() {
        served = true;
    }

    public void hungUpAndRecall(Operator operator) throws InterruptedException {
        sleep((long) 200);
        LOG.info("Client {} is trying to recall", clientNum);
        operator = getOperator();

        if (operator==null) {
            LOG.info("Client {} got tired to wait and hung up", clientNum);
        } else {
            sleep((long) (Math.random() * 1000));
            call.start();
            releaseOperator(operator);
        }
    }

    public void releaseOperator(Operator operator) throws InterruptedException {
        while (!served) {
            sleep(100);
            operator.release();
            LOG.info("Client {} was served by operator {} and ended the call", clientNum, operator.getOperatorNum());
        }
    }
}
