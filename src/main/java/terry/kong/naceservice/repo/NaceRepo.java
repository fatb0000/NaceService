package terry.kong.naceservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import terry.kong.naceservice.entity.Nace;

import java.util.Optional;

public interface NaceRepo extends JpaRepository<Nace, Long> {
    Optional<Nace> getByOrder(Long order);
}
