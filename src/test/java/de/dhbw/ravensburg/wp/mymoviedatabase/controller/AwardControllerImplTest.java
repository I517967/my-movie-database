package de.dhbw.ravensburg.wp.mymoviedatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ravensburg.wp.mymoviedatabase.dto.AwardDTO;
import de.dhbw.ravensburg.wp.mymoviedatabase.service.AwardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AwardControllerImpl.class)
public class AwardControllerImplTest {

    @MockBean
    private AwardService awardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AwardDTO oscar_1;
    private AwardDTO oscar_2;
    private List<AwardDTO> awardList;

    private final String controllerPath = "/api/v1/awards";


    @BeforeEach
    public void setUp(){
        this.oscar_1 = new AwardDTO(1L, "Oscar", "Best Picture", 2008);
        this.oscar_2 = new AwardDTO(2L, "Oscar", "Best Performance by an Actor in a Supporting Role", 2009);
        this.awardList = Arrays.asList(oscar_1, oscar_2);
    }


    @Test
    public void shouldSuccessfullyReturnAllAwards() throws Exception{
        when(awardService.getAllAwards()).thenReturn(awardList);

        this.mockMvc.perform(get(controllerPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(oscar_1.getId().intValue())))
                .andExpect(jsonPath("$[0].academy", is(oscar_1.getAcademy())))
                .andExpect(jsonPath("$[0].category", is(oscar_1.getCategory())))
                .andExpect(jsonPath("$[0].celebrationYear", is(oscar_1.getCelebrationYear())))
                .andDo(print());

        verify(awardService).getAllAwards();
    }

    @Test
    public void shouldSuccessfullyReturnAward() throws Exception{
        when(awardService.getAwardById(1L)).thenReturn(oscar_1);

        this.mockMvc.perform(get(controllerPath+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.id", is(oscar_1.getId().intValue())))
                .andExpect(jsonPath("$.academy", is(oscar_1.getAcademy())))
                .andExpect(jsonPath("$.category", is(oscar_1.getCategory())))
                .andExpect(jsonPath("$.celebrationYear", is(oscar_1.getCelebrationYear())))
                .andDo(print());

        verify(awardService).getAwardById(1L);
    }

    @Test
    public void shouldSuccessfullyRemoveAward() throws Exception{
        doNothing().when(awardService).removeAwardById(1L);
        this.mockMvc.perform(delete(controllerPath+"/1"))
                .andExpect(status().isOk());
        verify(awardService).removeAwardById(1L);
    }

    @Test
    public void shouldUpdateAward() throws Exception{
        when(awardService.updateAward(any())).thenReturn(oscar_2);
        this.mockMvc.perform(put(controllerPath+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(oscar_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(oscar_2.getId().intValue())));
        verify(awardService).updateAward(any());
    }

    @Test
    public void shouldAddAward() throws Exception{
        when(awardService.addAward(any())).thenReturn(oscar_2);
        this.mockMvc.perform(post(controllerPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(oscar_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(oscar_2.getId().intValue())));
        verify(awardService).addAward(any());
    }



}
