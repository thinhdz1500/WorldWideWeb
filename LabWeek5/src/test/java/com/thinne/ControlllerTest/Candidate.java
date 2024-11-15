package com.thinne.ControlllerTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class Candidate {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        mockMvc.perform(post("/candidate/login")
                        .param("email", "email_687@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("candidate/candidate-dashboard"))
                .andExpect(model().attributeExists("candidate"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        mockMvc.perform(post("/candidate/login")
                        .param("email", "non_existing_candidate_email@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("candidate/candidate-login"))
                .andExpect(model().attribute("message", "Candidate not found!"));
    }
}
