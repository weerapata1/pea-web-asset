package com.PEA.webAsset.Share.ConsiderServer;

import com.PEA.webAsset.Entity.ResponseFile;
import com.PEA.webAsset.Entity.tbConsideration;
import com.PEA.webAsset.Repository.ConsiderationRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsiderUploadFileService {
    public List<ResponseFile> getAllFile;
    List<tbConsideration> ListConsi = new ArrayList<tbConsideration>();
    @Autowired
    private ConsiderationRepository considerationRepository;
    @SneakyThrows
    public  List<tbConsideration> saveConsider(MultipartFile multipartFile)throws IOException {


        tbConsideration newConsi = new tbConsideration();
        String dateTime = LocalDateTime.now().toString();

        System.out.println("name "+ multipartFile.getOriginalFilename() );

        newConsi.setQuotation(multipartFile.getOriginalFilename() );
        newConsi.setMimeType(multipartFile.getContentType());
        newConsi.setSize(multipartFile.getSize());
        newConsi.setHash();

        ListConsi.add(newConsi);

        return ListConsi;
    }
//    public tbConsideration getFile(String file){
//        return considerationRepository.findByConsiderId(file);
//    }
//    public List<tbConsideration> getAllFile(){
//        return considerationRepository.findAll();
//    }

}
