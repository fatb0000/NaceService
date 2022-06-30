package terry.kong.naceservice.dto;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.entity.Nace;

public class NaceDtoTest {
    static EasyRandom easyRandom;
    @BeforeEach
    void setup(){
        easyRandom = new EasyRandom();
    }
    @Test
    @DisplayName("When convert nace to naceDto, all fields of naceDto are same as nace")
    public void testNaceDto(){
        Nace nace = easyRandom.nextObject(Nace.class);
        NaceDto result = NaceDto.toDto(nace);
        Assertions.assertEquals(nace.getOrder(), result.getOrder());
        Assertions.assertEquals(nace.getCode(), result.getCode());
        Assertions.assertEquals(nace.getExcludes(), result.getExcludes());
        Assertions.assertEquals(nace.getParent(), result.getParentCode());
        Assertions.assertEquals(nace.getLevel(), result.getLevel());
        Assertions.assertEquals(nace.getIncludes(), result.getIncludes());
        Assertions.assertEquals(nace.getRulings(), result.getRulings());
        Assertions.assertEquals(nace.getRef(), result.getRef());
        Assertions.assertEquals(nace.getIncludesAlso(), result.getIncludesAlso());
        Assertions.assertEquals(nace.getDesc(), result.getDesc());
    }
}
