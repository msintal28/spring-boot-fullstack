package com.amigoscode.gratitude;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gratitudes")
public class GratitudeController {
    private final GratitudeService gratitudeService;

    public GratitudeController(GratitudeService gratitudeService) {
        this.gratitudeService = gratitudeService;
    }

    @GetMapping("{customerId}")
    public List<Gratitude> getGratitudesByCustomerId(
            @PathVariable("customerId") Integer customerId) {
        return gratitudeService.getGratitudesByCustomer(customerId);
    }

    @GetMapping()
    public List<Gratitude> getGratitudes() {
        return gratitudeService.getGratitudes();
    }

    @PostMapping()
    public void saveGratitude(
            @RequestBody Gratitude gratitude
    ) {
        gratitudeService.saveGratitude(gratitude);
    }
}
