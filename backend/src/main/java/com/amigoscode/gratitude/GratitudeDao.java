package com.amigoscode.gratitude;

import java.util.List;

public interface GratitudeDao {
    List<Gratitude> selectAllGratitudes();

    List<Gratitude> selectAllGratitudesByCustomerId(Integer customerId);
    void insertGratitude(Gratitude gratitude);
}
