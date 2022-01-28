package com.station.system.pay.Service;

import com.station.system.pay.Entity.Status;

import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    void saveStatus(Status status);
    Status findStatusById(Long id);
}
