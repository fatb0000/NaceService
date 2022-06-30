package terry.kong.naceservice.service;

import org.springframework.http.HttpStatus;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.entity.Nace;
import terry.kong.naceservice.exception.NaceException;
import terry.kong.naceservice.repo.NaceRepo;

import java.util.Optional;

public class NaceServiceImpl implements NaceService{
    private NaceRepo naceRepo;
    public NaceServiceImpl(NaceRepo naceRepo){
        this.naceRepo = naceRepo;
    }
    @Override
    public NaceDto getNaceByOrder(Long order){
        Optional<Nace> nace = naceRepo.getByOrder(order);
        if(nace.isPresent()){
            return NaceDto.toDto(nace.get());
        }else {
            return null;
        }
    }

    @Override
    public NaceDto save(NaceDto naceDto) throws NaceException {
        if(naceRepo.getByOrder(naceDto.getOrder()).isPresent()){
            throw new NaceException(HttpStatus.BAD_REQUEST, "Unable to update nace with order "+ naceDto.getOrder());
        }else {
            Nace nace = convert(naceDto);
            nace = naceRepo.save(nace);
            return NaceDto.toDto(nace);
        }

    }
    @Override
    public NaceDto update(NaceDto naceDto) throws NaceException {
        if(naceRepo.getByOrder(naceDto.getOrder()).isPresent()){
            Nace nace = convert(naceDto);
            nace = naceRepo.save(nace);
            return NaceDto.toDto(nace);
        }else {
            throw new NaceException(HttpStatus.BAD_REQUEST, "Unable to save nace with order "+ naceDto.getOrder());
        }
    }

    private Nace convert(NaceDto naceDto){
        Nace nace = new Nace();
        nace.setOrder(naceDto.getOrder());
        nace.setCode(naceDto.getCode());
        nace.setDesc(naceDto.getDesc());
        nace.setExcludes(naceDto.getExcludes());
        nace.setIncludes(naceDto.getIncludes());
        nace.setIncludesAlso(naceDto.getIncludesAlso());
        nace.setParent(naceDto.getParentCode());
        nace.setLevel(naceDto.getLevel());
        nace.setRef(naceDto.getRef());
        nace.setRulings(naceDto.getRulings());
        return nace;
    }
}
