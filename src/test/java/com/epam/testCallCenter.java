package com.epam;

import com.epam.entity.CallCenter;
import com.epam.entity.Client;
import com.epam.entity.Operator;
import com.epam.initializer.Initializer;
import org.junit.jupiter.api.Test;

import java.util.List;

public class testCallCenter {

    @Test
    public void testCallCenter() {

        //GIVEN
        List<Operator> operators = Initializer.operators();
        CallCenter callCenter = new CallCenter(operators);
        List<Client> clients = Initializer.getClients(callCenter);

        //WHEN
        clients.forEach(client -> client.start());
        clients.forEach(client -> {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
