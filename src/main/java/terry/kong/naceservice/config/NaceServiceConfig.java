package terry.kong.naceservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import terry.kong.naceservice.repo.NaceRepo;
import terry.kong.naceservice.service.NaceService;
import terry.kong.naceservice.service.NaceServiceImpl;

@Configuration
public class NaceServiceConfig {

    @Bean
    public NaceService getNaceService(NaceRepo naceRepo){
        return new NaceServiceImpl(naceRepo);
    }
}

