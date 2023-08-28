package com.gridnine.testing;


import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightFilterImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flightsDepartureBeforeNow = new FlightFilterImpl(flights)
                .filterDepartureBeforeNow()
                .build();
        List<Flight> flightsArrivalBeforeDeparture = new FlightFilterImpl(flights)
                .filterArrivalBeforeDeparture()
                .build();
        List<Flight> flightsTimeOnGroundMoreThanTwoHours = new FlightFilterImpl(flights)
                .filterSumTimeOnGroundMoreThanTwoHours()
                .build();

        System.out.println("Все полеты:\n" + flights);
        System.out.println("Вылет до текущего время:\n" + flightsDepartureBeforeNow);
        System.out.println("Сегменты с датой прилёта раньше даты вылета:\n" + flightsArrivalBeforeDeparture);
        System.out.println("Общее время, проведённое на земле превышает два часа:\n" + flightsTimeOnGroundMoreThanTwoHours);
    }
}