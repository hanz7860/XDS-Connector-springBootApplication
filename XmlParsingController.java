package com.example.xdsapp.controller;

import com.example.xdsapp.service.XmlParsingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/xml")
public class XmlParsingController {

    private final XmlParsingService xmlParsingService;

    public XmlParsingController(XmlParsingService xmlParsingService) {
        this.xmlParsingService = xmlParsingService;
    }

    // Endpoint for XML parsing - returns result as response
    @GetMapping("/parse")
    public ResponseEntity<Map<String, String>> parseXml() {
        String filePath = "src/main/resources/sample.xml"; // Adjust the path to your XML file
        Map<String, String> keyValueMap = xmlParsingService.parseXmlFile(filePath);
        return ResponseEntity.ok(keyValueMap); // Return the parsed XML key-value pairs
    }
}
