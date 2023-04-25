package com.kwilewski.nbpservice.controller;

import com.kwilewski.nbpservice.exception.BadRequestException;
import com.kwilewski.nbpservice.exception.NbpConnectionFailedException;
import com.kwilewski.nbpservice.task.RateManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class NbpServiceController {
    private final RateManager manager = new RateManager();


    /**
     * Given a date  and a currency code, provide its average exchange rate.
     * @param date (formatted YYYY-MM-DD)
     * @param code as a String from table A of NBP API
     * @return ResponseEntity with (float) exchange rate
     */
    @GetMapping(value = "/exchange-rate/{date}/{code}")
    @ResponseBody
    public ResponseEntity<?> getExchangeRate(@PathVariable("date") String date,
                           @PathVariable("code") String code) {

        try {
            return new ResponseEntity<>(manager.getExchangeRate(date, code), HttpStatus.OK);
        } catch (BadRequestException | NbpConnectionFailedException e){
            if(e.getClass() == NbpConnectionFailedException.class) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }


    /**
     * Given a currency code and the number of last quotations count, provide the max and min average value.
     * @param code as a String from table A of NBP API
     * @param count as an int, count < 255
     * @return ResponseEntity with (float[]) {min, max} rate
     */
    @GetMapping(value = "/min-max/{code}/{count}")
    public ResponseEntity<?> getMinMaxRate(@PathVariable("code") String code,
                                @PathVariable("count") int count){

        try {
            return new ResponseEntity<>(manager.getAverageRate(code, count), HttpStatus.OK);
        } catch (BadRequestException | NbpConnectionFailedException e){
            if(e.getClass() == NbpConnectionFailedException.class) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }


    /**
     * Given a currency code and the number of last quotations count, provide the major difference between the buy and ask rate.
     * @param code as a String from table A of NBP API
     * @param count as an int, count < 255
     * @return ResponseEntity with (float) major difference between the buy and ask rate
     */
    @GetMapping(value = "/rate-difference/{code}/{count}")
    public ResponseEntity<?> getMajorRateDifference(@PathVariable("code") String code,
                                        @PathVariable("count") int count){

        try {
            return new ResponseEntity<>(manager.getMajorDifference(code, count), HttpStatus.OK);
        } catch (BadRequestException | NbpConnectionFailedException e){
            if(e.getClass() == NbpConnectionFailedException.class) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

}
