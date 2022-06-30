package terry.kong.naceservice.controller;

import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.service.NaceService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NaceController.class)
public class NaceControllerTest {
    @MockBean
    NaceService naceService;

    @Autowired
    MockMvc mockMvc;
        static EasyRandom easyRandom;
        @BeforeEach
        void setup(){
            easyRandom = new EasyRandom();
        }
    @Test
    @DisplayName("Given nace 12345 exists when get nace order 12345 then return naceDto object")
    public void testNormalNaceReturn() throws Exception {
        NaceDto dto = easyRandom.nextObject(NaceDto.class);

        Mockito.when(naceService.getNaceByOrder(12345L)).thenReturn(dto);

        mockMvc.perform(get("/api/nace/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order", Matchers.is(dto.getOrder())))
                .andExpect(jsonPath("$.code", Matchers.is(dto.getCode())))
                .andExpect(jsonPath("$.desc", Matchers.is(dto.getDesc())))
                .andExpect(jsonPath("$.excludes", Matchers.is(dto.getExcludes())))
                .andExpect(jsonPath("$.includesAlso", Matchers.is(dto.getIncludesAlso())))
                .andExpect(jsonPath("$.level", Matchers.is(dto.getLevel())))
                .andExpect(jsonPath("$.ref", Matchers.is(dto.getRef())))
                .andExpect(jsonPath("$.rulings", Matchers.is(dto.getRulings())))
                .andExpect(jsonPath("$.parentCode", Matchers.is(dto.getParentCode())))
                .andExpect(jsonPath("$.includes", Matchers.is(dto.getIncludes())));
    }
    @Test
    @DisplayName("Given nace 12345 exists when get nace order 12345 then return naceDto object")
    public void testNoNaceReturn() throws Exception {
        Mockito.when(naceService.getNaceByOrder(12345L)).thenReturn(null);

        mockMvc.perform(get("/api/nace/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
