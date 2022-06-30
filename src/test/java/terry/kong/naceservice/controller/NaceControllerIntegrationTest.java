package terry.kong.naceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import terry.kong.naceservice.NaceServiceApplication;
import terry.kong.naceservice.controller.dto.NaceDto;
import terry.kong.naceservice.entity.Nace;
import terry.kong.naceservice.repo.NaceRepo;
import terry.kong.naceservice.service.NaceService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NaceControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    NaceController naceController;
    @Autowired
    NaceRepo naceRepo;
    static EasyRandom easyRandom = new EasyRandom();
    Nace nace;
    @BeforeEach
    public void setup(){
        System.out.println("before each");
        nace = easyRandom.nextObject(Nace.class);
        nace.setOrder(12345L);
        naceRepo.saveAndFlush(nace);
    }
    @AfterEach
    public void desctroy(){
        naceRepo.deleteAll();
    }
    @Test
    @DisplayName("Given nace 12345 exists when get nace order 12345 then return naceDto object")
    public void testNormalNaceReturn() throws Exception {

        mockMvc.perform(get("/api/nace/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order", Matchers.is(nace.getOrder().intValue())))
                .andExpect(jsonPath("$.code", Matchers.is(nace.getCode())))
                .andExpect(jsonPath("$.desc", Matchers.is(nace.getDesc())))
                .andExpect(jsonPath("$.excludes", Matchers.is(nace.getExcludes())))
                .andExpect(jsonPath("$.includesAlso", Matchers.is(nace.getIncludesAlso())))
                .andExpect(jsonPath("$.level", Matchers.is(nace.getLevel())))
                .andExpect(jsonPath("$.ref", Matchers.is(nace.getRef())))
                .andExpect(jsonPath("$.rulings", Matchers.is(nace.getRulings())))
                .andExpect(jsonPath("$.parentCode", Matchers.is(nace.getParent())))
                .andExpect(jsonPath("$.includes", Matchers.is(nace.getIncludes())));
    }
    @Test
    @DisplayName("Given nace 12345 exists when get nace order 11111 then return empty response")
    public void testUnableToFindNace() throws Exception {

        mockMvc.perform(get("/api/nace/11111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order").doesNotExist());
    }
    @Test
    @DisplayName("Given nace 22222 not exists when save nace order 22222 and get 22222 then return new nace response")
    public void testSaveAndGetNace() throws Exception {
        ObjectMapper om = new ObjectMapper();
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(22222L);
        mockMvc.perform(get("/api/nace/22222"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order").doesNotExist());
        mockMvc.perform(post("/api/nace",naceDto).content(om.writeValueAsString(naceDto)).contentType("application/json"));
        mockMvc.perform(get("/api/nace/22222"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order", Matchers.is(naceDto.getOrder().intValue())))
                .andExpect(jsonPath("$.code", Matchers.is(naceDto.getCode())))
                .andExpect(jsonPath("$.desc", Matchers.is(naceDto.getDesc())))
                .andExpect(jsonPath("$.excludes", Matchers.is(naceDto.getExcludes())))
                .andExpect(jsonPath("$.includesAlso", Matchers.is(naceDto.getIncludesAlso())))
                .andExpect(jsonPath("$.level", Matchers.is(naceDto.getLevel())))
                .andExpect(jsonPath("$.ref", Matchers.is(naceDto.getRef())))
                .andExpect(jsonPath("$.rulings", Matchers.is(naceDto.getRulings())))
                .andExpect(jsonPath("$.parentCode", Matchers.is(naceDto.getParentCode())))
                .andExpect(jsonPath("$.includes", Matchers.is(naceDto.getIncludes())));
    }

    @Test
    @DisplayName("Given nace 12345 exists when update nace order 12345 and get 12345 then return updated response")
    public void testUpdateAndGetNace() throws Exception {
        ObjectMapper om = new ObjectMapper();
        NaceDto naceDto = easyRandom.nextObject(NaceDto.class);
        naceDto.setOrder(12345L);
        mockMvc.perform(put("/api/nace",naceDto).content(om.writeValueAsString(naceDto)).contentType("application/json"));
        mockMvc.perform(get("/api/nace/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.order", Matchers.is(naceDto.getOrder().intValue())))
                .andExpect(jsonPath("$.code", Matchers.is(naceDto.getCode())))
                .andExpect(jsonPath("$.desc", Matchers.is(naceDto.getDesc())))
                .andExpect(jsonPath("$.excludes", Matchers.is(naceDto.getExcludes())))
                .andExpect(jsonPath("$.includesAlso", Matchers.is(naceDto.getIncludesAlso())))
                .andExpect(jsonPath("$.level", Matchers.is(naceDto.getLevel())))
                .andExpect(jsonPath("$.ref", Matchers.is(naceDto.getRef())))
                .andExpect(jsonPath("$.rulings", Matchers.is(naceDto.getRulings())))
                .andExpect(jsonPath("$.parentCode", Matchers.is(naceDto.getParentCode())))
                .andExpect(jsonPath("$.includes", Matchers.is(naceDto.getIncludes())));
    }
}
