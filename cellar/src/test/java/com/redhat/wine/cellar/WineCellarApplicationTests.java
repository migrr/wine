package com.redhat.wine.cellar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WineCellarApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void paramPairingWithRightFoodTypeShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(
                get("/wine")
                    .param("wineType", WineType.BOLD_RED.toString())
                    .param("region", "rioja"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(WineCellarController.SUCCESS))
                .andExpect(jsonPath("$.description").value(WineCellarController.SUCCESS))
                .andExpect(jsonPath("$.wines").isArray());

    }
}
