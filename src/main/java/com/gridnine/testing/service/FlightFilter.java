package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> build();

    FlightFilter filterDepartureBeforeNow();

    FlightFilter filterArrivalBeforeDeparture();

    FlightFilter filterSumTimeOnGroundMoreThanTwoHours();
}
