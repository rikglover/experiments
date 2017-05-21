package com.apress.v1.controller.unit;

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
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickPollApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PollControllerTest {

    @InjectMocks
    PollController pollController;

    @Mock
    private PollRepository pollRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(pollController).build();
    }

    @Test
    public void getAllPolls() throws Exception {

        when(pollRepository.findAll()).thenReturn(new ArrayList<Poll>());

        mockMvc.perform(get("/v1/polls"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        verify(pollRepository).findAll();
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