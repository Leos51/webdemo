package training.afpa.cda24060.webdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersonsTest() throws Exception {

        mockMvc.perform(get("/"))
                // affiche le resultat dans la console
                .andDo(print())
                // avec un status 200
                .andExpect(status().isOk())
                // le nom de la vue
                .andExpect(view().name("home"))
                // une personne de la liste est égal à John
                .andExpect(MockMvcResultMatchers.content().string(containsString("Alice")));
    }
}