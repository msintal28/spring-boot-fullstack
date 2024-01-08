package com.amigoscode.gratitude;

import java.time.LocalDateTime;

public record Gratitude (long gratitudeId, LocalDateTime begin, LocalDateTime ende, long customerId) {

}
