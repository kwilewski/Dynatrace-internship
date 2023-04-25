package com.kwilewski.nbpservice.task;

import com.kwilewski.nbpservice.deserialized.RateSeries;
import com.kwilewski.nbpservice.exception.NbpConnectionFailedException;
import org.springframework.web.reactive.function.client.WebClient;

public class NbpClient {
    private static NbpClient instance = null;
    private static final String baseUrl = "http://api.nbp.pl/api/exchangerates/rates/";

    // Data to be dowlnoaded in json
    private static final String baseUrlFormat = "?format=json";

    public static NbpClient getInstance(){
        if (instance == null){
            instance = new NbpClient();
        }
        return instance;
    }

    private NbpClient(){

    }

    public RateSeries getRateSeries(char table, String date, String code){

        // Building final Url for the request
        String finalUrl = baseUrl + table + "/" + code + "/" + date + baseUrlFormat;

        try {
            // Downloading the data from NBP API
            WebClient.Builder builder = WebClient.builder();
            return builder.build()
                    .get()
                    .uri(finalUrl)
                    .retrieve()
                    .bodyToMono(RateSeries.class)
                    .block();
        } catch (RuntimeException e){
            // Throwing Exception if the download fails
            throw new NbpConnectionFailedException("Connection to NBP API failed");
        }
    }

    public RateSeries getRateSeries(char table, String code, int count){

        // Building final Url for the request
        String finalUrl = baseUrl + table + "/" +  code + "/last/" + count + baseUrlFormat;

        try {
            // Downloading the data from NBP API
            WebClient.Builder builder = WebClient.builder();
            return builder.build()
                    .get()
                    .uri(finalUrl)
                    .retrieve()
                    .bodyToMono(RateSeries.class)
                    .block();
        } catch (RuntimeException e){
            // Throwing Exception if the download fails
            throw new NbpConnectionFailedException("Connection to NBP API failed");
        }
    }


}
