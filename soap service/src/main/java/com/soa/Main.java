package com.soa;

import com.soa.controller.OscarSOAPController;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/soa-lab3-controller/OscarSOAPControllerService", new OscarSOAPController());
        System.out.println("published");
    }
}
