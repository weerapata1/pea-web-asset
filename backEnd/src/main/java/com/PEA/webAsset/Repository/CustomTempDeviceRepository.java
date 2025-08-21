package com.PEA.webAsset.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.PEA.webAsset.Entity.TempDevice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public interface CustomTempDeviceRepository {

    void bulkInsertDevices(List<TempDevice> devices);

}
