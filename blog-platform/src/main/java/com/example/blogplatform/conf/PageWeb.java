package com.example.blogplatform.conf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.conf.WebConstant;
import lombok.Data;

import java.util.List;

@Data
public class PageWeb<T> {
    private long pageNum;
    private long pageSize;
    private long totalSize;
    private T params;
    private List<T> records;


    public static PageWeb bulidPageWeb(IPage page) {
        return new PageWeb(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public PageWeb(long pageNum, long pageSize, long totalSize, List<T> records) {
        if (pageNum == 0) {
            this.pageNum = Long.valueOf(WebConstant.DEFAULT_PAGE_NO);
        } else {
            this.pageNum = pageNum;
        }

        if (pageSize == 0) {
            this.pageSize = Long.valueOf(WebConstant.DEFAULT_PAGE_SIZE);
        } else {
            this.pageSize = pageSize;
        }

        this.totalSize = totalSize;
        this.records = records;
    }

    public PageWeb() {
        this.pageNum = Long.valueOf(WebConstant.DEFAULT_PAGE_NO);
        this.pageSize = Long.valueOf(WebConstant.DEFAULT_PAGE_SIZE);
    }
}
