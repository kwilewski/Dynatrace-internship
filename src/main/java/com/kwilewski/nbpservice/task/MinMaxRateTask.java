package com.kwilewski.nbpservice.task;

import com.kwilewski.nbpservice.deserialized.RateSeries;
import com.kwilewski.nbpservice.exception.BadRequestException;

public class MinMaxRateTask {
    private final static NbpClient nbpClient = NbpClient.getInstance();
    private final RateSeries rateSeries;

    // Sends a request for NbpClient to get new RateSeries object from NBP API.
    public MinMaxRateTask(String code, int count){
        rateSeries = nbpClient.getRateSeries('A', code, count);
    }

    public float[] getMinMaxRate(){
        if (rateSeries.getRates().size() == 0) throw new BadRequestException("Request incorrect");
        if(rateSeries.getRates().size() == 1) return new float[]{rateSeries.getRates().get(0).getMid(), rateSeries.getRates().get(0).getMid()};

        // setting initial highest and lowest rates
        float min, max;
        if(rateSeries.getRates().get(0).getMid() > rateSeries.getRates().get(1).getMid()){
            max = rateSeries.getRates().get(0).getMid();
            min = rateSeries.getRates().get(1).getMid();
        } else {
            min = rateSeries.getRates().get(0).getMid();
            max = rateSeries.getRates().get(1).getMid();
        }

        // searching for the highest and the lowest rate
        for (int i = 2; i < rateSeries.getRates().size(); i++){
            if (rateSeries.getRates().get(i).getMid() < min) min = rateSeries.getRates().get(i).getMid();
            else if (rateSeries.getRates().get(i).getMid() > max) max = rateSeries.getRates().get(i).getMid();
        }

        return new float[]{min, max};
    }


}
