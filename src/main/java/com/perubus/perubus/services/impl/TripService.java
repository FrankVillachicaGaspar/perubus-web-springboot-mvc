package com.perubus.perubus.services.impl;

import com.perubus.perubus.dtos.TripShortDto;
import com.perubus.perubus.models.Trip;
import com.perubus.perubus.repositories.ITripRepository;
import com.perubus.perubus.services.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements ITripService {
    private final ITripRepository tripRepository;

    @Autowired
    public TripService(ITripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<TripShortDto> getTop3Trips() {
        var trips = tripRepository.findTop3ByOrderByDepartureTimeAsc();
        return trips.stream().map(
                trip -> TripShortDto.builder()
                        .id(trip.getId())
                        .description(trip.getDescription())
                        .imageUrl(trip.getImageUrl())
                        .price(trip.getPrice())
                        .destination(trip.getDestination())
                        .departureTime(trip.getDepartureTime())
                        .arrivalTime(trip.getArrivalTime())
                        .build()
        ).toList();
    }

    @Override
    public List<TripShortDto> getAllTrips() {
        var trips = tripRepository.findAll();
        return trips.stream().map(
                trip -> TripShortDto.builder()
                        .id(trip.getId())
                        .description(trip.getDescription())
                        .imageUrl(trip.getImageUrl())
                        .price(trip.getPrice())
                        .destination(trip.getDestination())
                        .departureTime(trip.getDepartureTime())
                        .arrivalTime(trip.getArrivalTime())
                        .build()
        ).toList();
    }

    @Override
    public TripShortDto getTripById(int id) {
        Trip trip = tripRepository.findById((long) id).orElse(null);

        if (trip == null) {
            return null;
        }

        return TripShortDto.builder()
                .id(trip.getId())
                .description(trip.getDescription())
                .imageUrl(trip.getImageUrl())
                .price(trip.getPrice())
                .destination(trip.getDestination())
                .departureTime(trip.getDepartureTime())
                .arrivalTime(trip.getArrivalTime())
                .build();
    }
}
