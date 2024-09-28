package com.example.xdsapp.controller;

import com.example.xdsapp.service.XdsConnectionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/xds")
public class XdsConnectorController {

    private final XdsConnectionService xdsConnectionService;

    @Value("${xds.username}")
    private String username;

    @Value("${xds.password}")
    private String password;

    public XdsConnectorController(XdsConnectionService xdsConnectionService) {
        this.xdsConnectionService = xdsConnectionService;
    }

    // Endpoint for XDS connection - prints result to the console
    @GetMapping("/connect")
    public ResponseEntity<String> connectToXds() {
        // URL1 - You can switch between URLs as needed
        String url1 = "https://xds-int.systems.uk.hsbc/api/v1/documents/SwapCurve/official/latest@20240919/LONDON/CLOSE/CAD/CDOR/3M/R2D2CCY";

        // Get data from XDS using username and password
        String response = xdsConnectionService.getDataFromXds(url1, username, password);

        // Print response to console
        System.out.println("XDS Response: " + response);

        return ResponseEntity.ok("XDS response printed to the console");
    }
}
