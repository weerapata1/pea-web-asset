package com.PEA.webAsset.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.PEA.webAsset.Entity.PdfResponse;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/api/proxy-pdf-producer")
    // public ResponseEntity<byte[]> proxyRequest(@RequestBody Map<String, Object>
    // requestBody) {
    public ResponseEntity<byte[]> proxyRequest() {
        String targetUrl = "http://172.30.211.224:42/api/pdf-producer";

        // Static request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("templateProjectPath", "sample/ams/506027-fixform.dito");
        requestBody.put("templateName", "output");
        requestBody.put("pdfVersion", "1.7");

        Map<String, String> data = new HashMap<>();
        data.put("cost_center_name", "กฟส.กทล.");
        data.put("date", "19 มิ.ย. 2567");
        data.put("type_other", "");
        data.put("brand", "HP");
        data.put("model", "ProDesk 600 G5");
        data.put("contract", "บ.75/2563");
        data.put("serial", "4CE03526C6");
        data.put("pea_no", "5330404643");
        data.put("problem", "ฮาร์ดิสชำรุด");
        data.put("emp_name", "นายอนุสรณ์ อมรรัตนศักดิ์");
        data.put("emp_role", "พบค.7");
        data.put("emp_id", "499857");
        data.put("tel", "(22)14890");
        data.put("inspector_name", "นายภาณุวิชญ์ ธานีวัฒน์");
        data.put("inspector_role", "นรค.7");
        data.put("inspector_date", "19 มิ.ย. 2567");
        data.put("dep_head_name", "นายสุเธียรพงศ์ ธนาอภิสิทธิ์โสภณ");
        data.put("dep_head_role", "หผ.คข.กดส.ฉ.2");
        data.put("dep_head_date", "19 มิ.ย. 2567");

        requestBody.put("data", data);
        try {
            // Forward the request to the external service and deserialize into PdfResponse class
            ResponseEntity<PdfResponse> response = restTemplate.postForEntity(targetUrl, requestBody, PdfResponse.class);
    
            // Check if the response status is OK
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // Extract the path from the response body
                String pdfPath = response.getBody().getPath();
                
                if (pdfPath != null && !pdfPath.isEmpty()) {
                    // Retrieve the PDF from the specified path (assuming it's accessible over HTTP)
                    String pdfUrl = "http://172.30.211.224:42" + pdfPath; // Adjust URL as necessary
                    ResponseEntity<byte[]> pdfResponse = restTemplate.getForEntity(pdfUrl, byte[].class);
    
                    // Return the PDF to the client
                    if (pdfResponse.getStatusCode() == HttpStatus.OK && pdfResponse.getBody() != null) {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_PDF);
                        headers.setContentDispositionFormData("inline", "generated.pdf");
                        headers.setContentLength(pdfResponse.getBody().length);
                        
                        return new ResponseEntity<>(pdfResponse.getBody(), headers, HttpStatus.OK);
                    }
                }
            }
    
            // If something goes wrong, return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
