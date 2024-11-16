package com.perubus.perubus.services.impl;

import com.perubus.perubus.dtos.VehicleDto;
import com.perubus.perubus.models.Vehicle;
import com.perubus.perubus.repositories.IVehicleRepository;
import com.perubus.perubus.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    private final IVehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDto getVehicleById(int id) {
        Vehicle vehicle = vehicleRepository.findById((long) id).orElse(null);
        if (vehicle == null) return null;
        return VehicleDto.builder()
                .id(vehicle.getId())
                .model(vehicle.getModel())
                .brand(vehicle.getBrand())
                .year(vehicle.getYear())
                .type(vehicle.getType())
                .build();
    }
}
