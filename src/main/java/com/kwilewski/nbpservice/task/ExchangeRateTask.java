package com.kwilewski.nbpservice.task;

import com.kwilewski.nbpservice.deserialized.RateSeries;
import com.kwilewski.nbpservice.exception.BadRequestException;

public class ExchangeRateTask {
    private final static NbpClient nbpClient = NbpClient.getInstance();
    private final RateSeries rateSeries;

    // Sends a request for NbpClient to get new RateSeries object from NBP API.
    public ExchangeRateTask(String date, String code){
        rateSeries = nbpClient.getRateSeries('A', date, code);
    }

    public float getExchangeRate(){
        if (rateSeries.getRates().size() == 0) throw new BadRequestException("Request incorrect");
        return rateSeries.getRates().get(0).getMid();
    }



}
