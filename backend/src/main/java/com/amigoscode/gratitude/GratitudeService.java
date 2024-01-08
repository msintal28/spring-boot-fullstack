package com.amigoscode.gratitude;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GratitudeService {
    private final GratitudeDao gratitudeDao;

    public GratitudeService(@Qualifier("jdbcgratitude")GratitudeDao gratitudeDao) {
        this.gratitudeDao = gratitudeDao;
    }

    public List<Gratitude> getGratitudesByCustomer(Integer customerId) {
      return gratitudeDao.selectAllGratitudesByCustomerId(customerId);
    }

    public List<Gratitude> getGratitudes() {
        return gratitudeDao.selectAllGratitudes();
    }

    public void saveGratitude(Gratitude gratitude) {
        gratitudeDao.insertGratitude(gratitude);
    }
}
