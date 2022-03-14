package com.PEA.webAsset.Share;

import org.springframework.data.domain.Slice;

import java.util.function.Function;

public interface Page<T> extends Slice<T> {
    static <T> Page<T> empty() {
        return null;
    }

    static <T> Page<T> empty(Pageable pageable) {
        return null;
    }

    long getTotalElements();
    int getTotalPages();
    <U> Page<U> map(Function<? super T,? extends U> converter);
}
