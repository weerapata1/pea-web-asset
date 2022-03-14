package com.PEA.webAsset.Share;

import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface Slice<T> extends Streamable {
    int getNumber();
    int getSize();
    int getNumberOfElements();
    List<T> getContent();
    boolean hasContent();
    Sort getSort();
    boolean isFirst();
    boolean isLast();
    boolean hasNext();
    boolean hasPrevious();
}
