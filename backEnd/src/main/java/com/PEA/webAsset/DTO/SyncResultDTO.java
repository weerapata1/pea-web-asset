package com.PEA.webAsset.DTO;

import lombok.Data;

@Data
public class SyncResultDTO {

    private int insertedCount;
    private int insertedCount2;
    private int softDeletedCount;


    public SyncResultDTO(int insertedCount, int insertedCount2, int softDeletedCount) {
        this.insertedCount = insertedCount;
        this.softDeletedCount = softDeletedCount;
        this.insertedCount2 = insertedCount2;
    }

    public int getInsertedCount() {
        return insertedCount;
    }

    public int getInsertedCount2() {
        return insertedCount2;
    }

    public int getSoftDeletedCount() {
        return softDeletedCount;
    }
    
}
