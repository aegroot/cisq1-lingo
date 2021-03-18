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
    @DisplayName("new round, good length")
    public void newRoundTest() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("lingoronde/newronde/5");
        mockMvc.perform(request).andExpect(status().isOk());
    }
    @Test
    @DisplayName("new round,invalid length")
    public void newRoundTestWrongLength() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("lingoronde/newronde/8");
        mockMvc.perform(request)
                .andExpect(status().is(500));
    }

}
