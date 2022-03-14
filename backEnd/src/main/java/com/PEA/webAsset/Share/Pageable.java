package com.PEA.webAsset.Share;

import org.springframework.data.domain.Sort;

public interface Pageable {
    int getPageNumber();
    int getPageSize();
    Sort sort();
    Pageable next();
    Pageable previousOrFirst();
    Pageable first();
    boolean hasPrevious();

}
