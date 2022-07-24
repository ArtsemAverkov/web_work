package by.it.academy.services.bucket;

import by.it.academy.dtos.request.bucket.BucketDto;
import by.it.academy.entities.bucket.Bucket;

public interface BucketService {
    Long createBucket(BucketDto bucket);
   void deleteBucket(Long id);
    BucketDto getBucket(Long id);
}
