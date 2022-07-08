package by.it.academy.repositories.avatar;

import by.it.academy.entities.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvatarRepository extends JpaRepository<Avatar, UUID> {
}
