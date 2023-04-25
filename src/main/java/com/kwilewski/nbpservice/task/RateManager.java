package com.kwilewski.nbpservice.task;

import com.kwilewski.nbpservice.exception.BadRequestException;

import java.util.Set;

// This class performs basic checks and sends further logic.
public class RateManager {

    private static final Set<String> codes = Set.of("AUD", "THB", "BRL", "BGN",
            "CAD", "CLP", "CZK", "DKK", "EUR", "HUF", "HKD", "UAH", "ISK", "INR",
            "MYR", "MXN", "ILS", "NZD", "NOK", "PHP", "GBP", "ZAR", "RON", "IDR",
            "SGD", "SEK", "CHF", "TRY", "USD", "KRW", "JPY", "CNY", "XDR");


    public RateManager(){}

    public float getExchangeRate(String date, String code){
        if (!checkCode(code)) throw new BadRequestException("Wrong currency code");
        ExchangeRateTask task = new ExchangeRateTask(date, code);
        return task.getExchangeRate();
    }

    public float[] getAverageRate(String code, int count){
        if (!checkCode(code)) throw new BadRequestException("Wrong currency code");
        if (count < 1) throw new BadRequestException("Incorrect number of quotation");
        MinMaxRateTask task = new MinMaxRateTask(code, count);
        return task.getMinMaxRate();
    }

    public float getMajorDifference(String code, int count){
        if (!checkCode(code)) throw new BadRequestException("Wrong currency code");
        if (count < 1) throw new BadRequestException("Incorrect number of quotation");
        MajorDifferenceTask task = new MajorDifferenceTask(code, count);
        return task.getMajorRateDifference();
    }

    // Method checks if given code is correct by comparing to predefined set of codes.
    private boolean checkCode(String code){
        return (codes.contains(code.toUpperCase()));
    }

}
