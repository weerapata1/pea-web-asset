package com.PEA.webAsset.Interface;

public class SyncResultDTO {

    private int insertedCount;
    private int softDeletedCount;

    public SyncResultDTO(int insertedCount, int softDeletedCount) {
        this.insertedCount = insertedCount;
        this.softDeletedCount = softDeletedCount;
    }

    public int getInsertedCount() {
        return insertedCount;
    }

    public int getSoftDeletedCount() {
        return softDeletedCount;
    }
    
}
