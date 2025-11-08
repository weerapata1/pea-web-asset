package com.PEA.webAsset.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PEA.webAsset.DTO.SyncResultDTO;
import com.PEA.webAsset.Repository.TempDeviceRepository;

import jakarta.transaction.Transactional;

@Service
public class TempDeviceService {

@Autowired
TempDeviceRepository tempDeviceRepository;

    @Transactional
    public SyncResultDTO insertUpdateFromTemp() {
        tempDeviceRepository.updateFromTemp(); // Step 1: Update existing records
        // tempDeviceRepository.insertFromTemp(); // Step 2: Insert new records
        int insertedCount = tempDeviceRepository.insertFromTemp();

        int insertedCount2 = tempDeviceRepository.insertFromTemp2();

        int softDelete = tempDeviceRepository.softDeleteMissingDevices();

        return new SyncResultDTO(insertedCount, insertedCount2, softDelete);
    }
}
