package com.apress.v1.controller.integration;

import com.apress.QuickPollApplication;
import com.apress.domain.Poll;
import com.apress.repository.PollRepository;
import com.apress.v1.controller.PollController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickPollApplication.class)
@WebAppConfiguration
public class PollControllerIT {

    @Inject
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllPolls() throws Exception {

        mockMvc.perform(get("/v1/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)));
    }

    @Test
    public void createPoll() throws Exception {

    }

    @Test
    public void getPoll() throws Exception {

    }

    @Test
    public void updatePoll() throws Exception {

    }

    @Test
    public void deletePoll() throws Exception {

    }

    @Test
    public void verifyPoll() throws Exception {

    }

}