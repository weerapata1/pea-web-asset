//package com.PEA.webAsset.Service;
//
//import com.PEA.webAsset.Entity.tbDevice;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class DeviceServiceImp implements DeviceService{
//    private static final String BASE_URL = "http://localhost:8080/device";
//    private final RestTemplate restTemplate;
//    private static final ObjectMapper mapper = new ObjectMapper();
//    public DeviceServiceImp(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }
//
//    @Override
//    public List<String> processDeviceFromObjectArray(){
//        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(BASE_URL, Object[].class);
//        Object[] obj = responseEntity.getBody();
//        return Arrays.stream(obj)
//                .map(object -> mapper.convertValue(object, tbDevice.class))
//                .map(tbDevice::getDev_note)
//                .collect(Collectors.toList());
//    }
//
//}
