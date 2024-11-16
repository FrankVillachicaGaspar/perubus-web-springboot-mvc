package com.perubus.perubus.repositories;

import com.perubus.perubus.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByVehicleId(int vehicleId);
}
