package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.tbConsideration;
import com.PEA.webAsset.Repository.ConsiderationRepository;
import com.PEA.webAsset.Share.ConsiderServer.ConsiderUploadFileService;
import com.PEA.webAsset.Share.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cons")
@RequiredArgsConstructor
public class ConsiderationController {

    private final ConsiderationRepository considerationRepository;
    @Autowired
    private ConsiderUploadFileService considerUploadFileService;
    private static Logger Log = LoggerFactory.getLogger(ConsiderationController.class);

    @PostMapping("/consFileUp")
    public ResponseEntity<ResponseMessage> uploadFileConsi(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        try {
            System.out.println("file " + file.getOriginalFilename());
            List<tbConsideration> ListConsi = considerUploadFileService.saveConsider(file);
            System.out.println(">> " + ListConsi);
            considerationRepository.saveAll(ListConsi);
            message = file.getOriginalFilename() + " upload done";
            return ResponseEntity.ok().body(new ResponseMessage(message));

        } catch (NullPointerException ex) {
            message = "upload fail File null!!" + file.getOriginalFilename() + " " + ex.getMessage();
            return ResponseEntity.badRequest().body(new ResponseMessage(message));
        } catch (Exception ex) {
            message = "upload fail !!" + file.getOriginalFilename() + " " + ex.getMessage();
            return ResponseEntity.badRequest().body(new ResponseMessage(message));
        }
    }

    @GetMapping("/file")
    public Collection<tbConsideration> getFile() {

        return considerationRepository.findAll();
    }

    //    @GetMapping("/file/{id}")
//    public ResponseEntity<byte[]> getFileByID(@PathVariable("id") String id) {
//
//        Collection<tbConsideration> tbConsiderations = considerationRepository.findByConsiderId(id);
//        byte[] imageBytes = null;
//        if(tbConsiderations.isPresent){
//            imageBytes = tbConsiderations.get().getPhoto().getBytes(1,
//                    (int) primeMinisterOfIndia.get().getPhoto().length());
//        }
//
//
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//    }
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFileByID(@PathVariable("id") String id) throws IOException {
        tbConsideration consi = considerationRepository.findByConsiderId(id);
        try {
            byte[] pdf = null;
            HttpHeaders headers = new HttpHeaders();
            String filename = "example.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setContentType(MediaType.APPLICATION_PDF);
            System.out.println("DO");

            return ResponseEntity.ok().headers(headers).body(pdf);
        }catch(Exception ex){
            System.out.println("fail");
            return ResponseEntity.badRequest().body(ex.getMessage().getBytes());
        }
    }
}
