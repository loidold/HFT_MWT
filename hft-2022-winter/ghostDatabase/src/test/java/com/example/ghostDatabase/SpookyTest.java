package com.example.ghostDatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.ghostDatabase.rest.Ghost;
import com.example.ghostDatabase.rest.GhostType;
import com.example.ghostDatabase.rest.SpookyController;
import com.example.ghostDatabase.rest.ThreadLevel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpookyTest {

  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setup(){
    mockMvc = MockMvcBuilders.standaloneSetup(SpookyController.class).build();
  }

  @Test
  public void testLittleSpook() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/spooky/littlespook")).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("...boh");
  }

  @Test
  public void createGhost() throws Exception {
    String requestObject = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                              ThreadLevel.HIGH));
    MockHttpServletResponse response =
        mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject);
  }

  @Test
  public void createAndRetriveGhost() throws Exception {
    String requestObject = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                                                     ThreadLevel.HIGH));
        mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/spooky/ghost?name=bob")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(requestObject);
  }

  @Test
  public void createAndDeleteGhost() throws Exception {
    String requestObject = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                                                     ThreadLevel.HIGH));
    String requestObject2 = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                                                     ThreadLevel.HIGH));
    mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON));
    mockMvc.perform(MockMvcRequestBuilders.put("/spooky/ghost").content(requestObject2).contentType(MediaType.APPLICATION_JSON));
    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/spooky/ghost?name=bob")).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(requestObject);
  }

  @Test
  public void createAndChangeGhost() throws Exception {
    String requestObject = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                                                     ThreadLevel.HIGH));
    String requestObject2 = objectMapper.writeValueAsString(new Ghost("bob", GhostType.JIN,
                                                                      ThreadLevel.HIGH));
    mockMvc.perform(MockMvcRequestBuilders.post("/spooky/ghost").content(requestObject).contentType(MediaType.APPLICATION_JSON));
    MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/spooky/ghost").content(requestObject2).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(requestObject2);
  }

}
