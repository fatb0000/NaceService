package terry.kong.naceservice.service;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.entity.Nace;
import terry.kong.naceservice.exception.NaceException;
import terry.kong.naceservice.repo.NaceRepo;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NaceServiceImplTest {
    @InjectMocks
    NaceServiceImpl naceService;
    @Mock
    NaceRepo naceRepo;
    static EasyRandom easyRandom;
    @BeforeEach
    void setup(){
        easyRandom = new EasyRandom();
    }

    @Test
    @DisplayName("Given that order 12345 exists when order is 12345 Then return nace object")
    public void testGetOrderNormalCase(){
        Nace nace = easyRandom.nextObject(Nace.class);
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.of(nace));

        NaceDto naceDto = naceService.getNaceByOrder(12345L);
        Assertions.assertEquals(nace.getOrder(), naceDto.getOrder());
        Assertions.assertEquals(nace.getCode(), naceDto.getCode());
        Assertions.assertEquals(nace.getExcludes(), naceDto.getExcludes());
        Assertions.assertEquals(nace.getParent(), naceDto.getParentCode());
        Assertions.assertEquals(nace.getLevel(), naceDto.getLevel());
        Assertions.assertEquals(nace.getIncludes(), naceDto.getIncludes());
        Assertions.assertEquals(nace.getRulings(), naceDto.getRulings());
        Assertions.assertEquals(nace.getRef(), naceDto.getRef());
        Assertions.assertEquals(nace.getIncludesAlso(), naceDto.getIncludesAlso());
        Assertions.assertEquals(nace.getDesc(), naceDto.getDesc());
    }
    @Test
    @DisplayName("Given that order 12345 NOT exists when order is 12345 Then return nace object")
    public void testGetOrderNotExist(){
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.empty());

        NaceDto naceDto = naceService.getNaceByOrder(12345L);
        Assertions.assertNull(naceDto);
    }

    @Test
    @DisplayName("Given that order 12345 exists when order is 12345 Then  update and return nace DTO")
    public void testUpdateNace() throws NaceException {
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(12345L);
        Nace nace = easyRandom.nextObject(Nace.class);
        nace.setOrder(12345L);
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.of(nace));
        Mockito.when(naceRepo.save(Mockito.any(Nace.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        NaceDto result = naceService.update(naceDto);
        Assertions.assertEquals(naceDto.getOrder(), result.getOrder());
        Assertions.assertEquals(naceDto.getCode(), result.getCode());
        Assertions.assertEquals(naceDto.getExcludes(), result.getExcludes());
        Assertions.assertEquals(naceDto.getParentCode(), result.getParentCode());
        Assertions.assertEquals(naceDto.getLevel(), result.getLevel());
        Assertions.assertEquals(naceDto.getIncludes(), result.getIncludes());
        Assertions.assertEquals(naceDto.getRulings(), result.getRulings());
        Assertions.assertEquals(naceDto.getRef(), result.getRef());
        Assertions.assertEquals(naceDto.getIncludesAlso(), result.getIncludesAlso());
        Assertions.assertEquals(naceDto.getDesc(), result.getDesc());
    }
    @Test
    @DisplayName("Given that order 12345 NOT exist when order is 12345 Then unable to update and throws Exception")
    public void testUnableToUpdateNace() {
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(12345L);
        Nace nace = easyRandom.nextObject(Nace.class);
        nace.setOrder(12345L);
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NaceException.class, () ->naceService.update(naceDto));
    }
    @Test
    @DisplayName("Given that order 12345 NOT exist when order is 12345 Then persist and return nace object")
    public void testSaveOrderNormalCase() throws NaceException {
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(12345L);
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.empty());
        Mockito.when(naceRepo.save(Mockito.any(Nace.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        NaceDto result = naceService.save( naceDto);

        Assertions.assertEquals(naceDto.getOrder(), result.getOrder());
        Assertions.assertEquals(naceDto.getCode(), result.getCode());
        Assertions.assertEquals(naceDto.getExcludes(), result.getExcludes());
        Assertions.assertEquals(naceDto.getParentCode(), result.getParentCode());
        Assertions.assertEquals(naceDto.getLevel(), result.getLevel());
        Assertions.assertEquals(naceDto.getIncludes(), result.getIncludes());
        Assertions.assertEquals(naceDto.getRulings(), result.getRulings());
        Assertions.assertEquals(naceDto.getRef(), result.getRef());
        Assertions.assertEquals(naceDto.getIncludesAlso(), result.getIncludesAlso());
        Assertions.assertEquals(naceDto.getDesc(), result.getDesc());
    }
    @Test
    @DisplayName("Given that order 12345  exists when order is 12345 Then unable to save and throws Exception")
    public void testUnableToSaveNace() {
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(12345L);
        Nace nace = easyRandom.nextObject(Nace.class);
        nace.setOrder(12345L);
        Mockito.when(naceRepo.getByOrder(12345L)).thenReturn(Optional.of(nace));
        Assertions.assertThrows(NaceException.class, () ->naceService.save(naceDto));
    }
}
