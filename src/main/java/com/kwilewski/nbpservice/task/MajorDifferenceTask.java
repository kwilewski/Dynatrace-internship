package com.kwilewski.nbpservice.task;

import com.kwilewski.nbpservice.deserialized.RateSeries;
import com.kwilewski.nbpservice.exception.BadRequestException;

public class MajorDifferenceTask {
    private final static NbpClient nbpClient = NbpClient.getInstance();
    private final RateSeries rateSeries;

    // Sends a request for NbpClient to get new RateSeries object from NBP API.
    public MajorDifferenceTask(String code, int count){
        rateSeries = nbpClient.getRateSeries('C', code, count);
    }

    public float getMajorRateDifference(){
        if (rateSeries.getRates().size() == 0) throw new BadRequestException("Request incorrect");
        if(rateSeries.getRates().size() == 1) return rateSeries.getRates().get(0).getAsk() - rateSeries.getRates().get(0).getBid();

        float diff = rateSeries.getRates().get(0).getAsk() - rateSeries.getRates().get(0).getBid();
        //searching for the highest difference
        for (int i = 1; i < rateSeries.getRates().size(); i++){
            if (rateSeries.getRates().get(i).getAsk() - rateSeries.getRates().get(i).getBid() > diff) {
                diff = rateSeries.getRates().get(i).getAsk() - rateSeries.getRates().get(i).getBid();
            }
        }
        return diff;
    }
}
