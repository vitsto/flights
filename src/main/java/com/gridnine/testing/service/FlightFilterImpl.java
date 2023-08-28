package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {
    private List<Flight> flights;

    public FlightFilterImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> build() {
        return flights;
    }

    @Override
    public FlightFilter filterDepartureBeforeNow() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        );
        return this;
    }

    @Override
    public FlightFilter filterArrivalBeforeDeparture() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
        );
        return this;
    }

    @Override
    public FlightFilter filterSumTimeOnGroundMoreThanTwoHours() {
        flights.removeIf(flight -> {
            List<Segment> segments = flight.getSegments();
            LocalDateTime curDeparture;
            LocalDateTime lastArrival;
            Duration duration = Duration.ZERO;

            for (int i = 1; i < segments.size(); i++) {
                curDeparture = segments.get(i).getDepartureDate();
                lastArrival = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(curDeparture, lastArrival).abs());
            }
            return duration.toHours() >= 2;
        });
        return this;
    }
}
