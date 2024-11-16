package com.perubus.perubus.repositories;

import com.perubus.perubus.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITripRepository extends JpaRepository<Trip, Long> {
    Optional<List<Trip>> findAllByDescription(String name);
    List<Trip> findTop3ByOrderByDepartureTimeAsc();
}
