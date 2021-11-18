package com.epam.initializer;

import com.epam.entity.Call;
import com.epam.entity.CallCenter;
import com.epam.entity.Client;
import com.epam.entity.Operator;

import java.util.ArrayList;
import java.util.List;

public class Initializer {

    private static Integer operatorNum = 1;
    private static Integer clientNum = 1;

    public static List<Operator> operators() {
        List<Operator> operators = new ArrayList<>();
        operators.add(getOperator());
        operators.add(getOperator());
        operators.add(getOperator());
        return operators;
    }

    public static Operator getOperator() {
        Operator operator = new Operator(operatorNum);
        operatorNum+=1;
        return operator;
    }

    public static Client getClient(CallCenter callCenter) {
        Client client = new Client(clientNum, callCenter, getCall(callCenter));
        clientNum+=1;
        return client;
    }

    public static List<Client> getClients(CallCenter callCenter) {
        List<Client> clients = new ArrayList<>();
        clients.add(getClient(callCenter));
        clients.add(getClient(callCenter));
        clients.add(getClient(callCenter));
        clients.add(getClient(callCenter));
        clients.add(getClient(callCenter));
        return clients;
    }

    public static Call getCall(CallCenter callCenter) {
        Call call = new Call(callCenter);
        return call;
    }
}
