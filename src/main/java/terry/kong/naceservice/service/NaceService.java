package terry.kong.naceservice.service;

import org.springframework.stereotype.Service;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.exception.NaceException;

@Service
public interface NaceService {
    NaceDto getNaceByOrder(Long order);
    NaceDto save(NaceDto naceDto) throws NaceException;
    NaceDto update(NaceDto naceDto) throws NaceException;
}
