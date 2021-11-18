package com.epam.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Operator {

    private Integer operatorNum;
    private boolean free = true;
    private Semaphore semaphore;
    private ReentrantLock clientLock = new ReentrantLock();
    private Client client;

    private static final Logger LOG = LoggerFactory.getLogger(Operator.class);


    public Operator(Integer operatorNum) {
        this.operatorNum = operatorNum;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public boolean isFree() {
        return free;
    }

    public void busy() {
        free = false;
    }

    public void release() {
        free = true;
        semaphore.release();
    }

    public void addForTalk(Client client) {
        clientLock.lock();
        this.client = client;
        clientLock.unlock();
    }

    public Integer getOperatorNum() {
        return operatorNum;
    }

}
