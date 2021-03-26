package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.data.SpringLingoSpelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LingospelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SpringLingoSpelRepository repository;

    @Test
    @DisplayName("create")
    public void createTest(){
        RequestBuilder request= MockMvcRequestBuilders.post("/lingospel/create");
        try {
            mockMvc.perform(request)
            .andExpect(status().isOk())
            .andDo(print());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
