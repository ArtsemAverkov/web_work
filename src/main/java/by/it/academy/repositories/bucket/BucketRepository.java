package by.it.academy.repositories.bucket;

import by.it.academy.dtos.request.bucket.BucketDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketDto, Long> {
}
