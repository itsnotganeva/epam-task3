package com.epam.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class CallCenter {
    private static final Logger LOG = LoggerFactory.getLogger(CallCenter.class);

    private List<Operator> operators;
    private Semaphore semaphore;
    private ReentrantLock lock = new ReentrantLock();

    public CallCenter(List<Operator> operators) {
        this.operators = operators;
        semaphore = new Semaphore(operators.size());
        operators.forEach(o -> o.setSemaphore(semaphore));
    }

    public Operator getOperator(Client client) throws InterruptedException {

        if (semaphore.availablePermits() == 0) {
            LOG.info("Client {} is waiting for free operator", client.getClientNum());
        }
        else {
            semaphore.acquire();
            lock.lock();
            Operator freeOperator = operators.stream().filter(o -> o.isFree()).findFirst().get();
            freeOperator.busy();
            lock.unlock();
            return freeOperator;
        }
        return null;
    }

}
