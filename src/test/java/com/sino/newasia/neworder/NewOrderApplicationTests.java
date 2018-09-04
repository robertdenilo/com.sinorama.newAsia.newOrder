package com.sino.newasia.neworder;

import com.sino.newasia.neworder.Controller.TourController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewOrderApplicationTests {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    private TourController tourController;

    @Before
    public void setUp() throws Exception {
        //mvc = MockMvcBuilders.standaloneSetup(tourController).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void contextLoads() {
    }


    @Test
    public void getHello()
    {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/asiaTour/getToursFromNativeEm/BHHS-11-2019").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();

            System.out.println("ming ming");
            assertEquals(24+9,33);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}
