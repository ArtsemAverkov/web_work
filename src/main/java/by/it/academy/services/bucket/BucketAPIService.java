package by.it.academy.services.bucket;

import by.it.academy.dtos.request.bucket.BucketDto;
import by.it.academy.repositories.bucket.BucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketAPIService implements BucketService{
    private final BucketRepository bucketRepository;
    @Override
    public Long createBucket(BucketDto bucket) {
        BucketDto bucketBuilder = buildBucket(bucket);
        return bucketRepository.save(bucketBuilder).getId();
    }

    @Override
    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }

    @Override
    public BucketDto getBucket(Long id) {
        return bucketRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    private BucketDto buildBucket(BucketDto bucket){
        return BucketDto.builder()
                .user(bucket.getUser())
                .modelProduct(bucket.getModelProduct())
                .build();
    }
}
