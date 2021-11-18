package com.epam;

import com.epam.entity.CallCenter;
import com.epam.entity.Client;
import com.epam.entity.Operator;
import com.epam.initializer.Initializer;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        List<Operator> operators = Initializer.operators();

        CallCenter callCenter = new CallCenter(operators);

        List<Client> clients = Initializer.getClients(callCenter);
        clients.forEach(Thread::start);

    }
}


