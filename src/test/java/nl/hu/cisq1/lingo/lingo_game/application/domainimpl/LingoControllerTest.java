package nl.hu.cisq1.lingo.lingo_game.application.domainimpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class LingoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("new round")
    public void newRoundTest() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.post("/lingoronde/newronde/7");
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("add raadbeurt")
    public  void addRaarbeurtTest() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders
                .post("/lingoronde/addraadbeurt")
                .param("woord","aaaaa")
                .param("id",String.valueOf(5L));
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }



}
