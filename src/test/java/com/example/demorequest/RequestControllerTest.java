package com.example.demorequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createRequest() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("desc","12323");
        this.mockMvc.perform(post("/request", map)).andExpect(status().isBadRequest());
    }

    @Test
    public void updateStatus() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("id","A12323");
        map.put("status","12323");
        this.mockMvc.perform(post("/request/update_status", map)).andExpect(status().isBadRequest());
    }

    @Test
    public void addComment() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("id","A12323");
        map.put("comment","12323");
        this.mockMvc.perform(post("/request/comment_add", map)).andExpect(status().isBadRequest());
    }

    @Test
    public void getRequest() throws Exception {
        this.mockMvc.perform(get("/request/ewr1")).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteRequest() throws Exception {
        this.mockMvc.perform(delete("/request/delete_request")).andExpect(status().isBadRequest());
    }

}
