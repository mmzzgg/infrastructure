package com.guns21.data.domain.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by ljj on 17/6/7.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageData implements Serializable {
    /**
     * 当前页
     */
    private int page = 1;
    private int size = 10;
    private int totalPages = 0;
    private long totalElements = 0;
    private Iterable pageData = null;
}
