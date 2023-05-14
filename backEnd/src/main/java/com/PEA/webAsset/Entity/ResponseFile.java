package com.PEA.webAsset.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class ResponseFile {
    private String quotation;
    private String MimeType;
    private long size;
    private Byte data;

}
