package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.dto.FetchRequestDTO;
import org.afs.pakinglot.domain.dto.ParkRequestDTO;
import org.afs.pakinglot.domain.dto.ParkingLotResponseDTO;
import org.afs.pakinglot.domain.service.ParkingManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ParkingManagerControllerTest {
    //case 1: when call getAllData then return all parking lots
    //case 2: when call park then return ticket
    //case 3: when call fetch then return car

    private MockMvc mockMvc;

    @Mock
    private ParkingManagerService service;

    @InjectMocks
    private ParkingManagerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void given_parkingLots_when_get_all_data_then_return_all_parkingLots() throws Exception {
        List<ParkingLotResponseDTO> parkingLots = Collections.singletonList(new ParkingLotResponseDTO());
        when(service.getAllData()).thenReturn(parkingLots);

        mockMvc.perform(get("/parking/getAllData"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));

        verify(service, times(1)).getAllData();
    }

    @Test
    void given_park_request_when_park_then_return_ticket() throws Exception {
        ParkRequestDTO request = new ParkRequestDTO("SequentiallyStrategy", "AB-1234");
        Ticket ticket = new Ticket("AB-1234",1,1);
        when(service.park(anyString(), anyString())).thenReturn(ticket);

        mockMvc.perform(post("/parking/park")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"strategy\":\"SequentiallyStrategy\",\"plateNumber\":\"AB-1234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"plateNumber\":\"AB-1234\"}"));

        verify(service, times(1)).park("SequentiallyStrategy", "AB-1234");
    }

    @Test
    void given_fetch_request_when_fetch_then_return_car() throws Exception {
        FetchRequestDTO request = new FetchRequestDTO("AB-1234");
        Car car = new Car("AB-1234");
        when(service.fetch(anyString())).thenReturn(car);

        mockMvc.perform(post("/parking/fetch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"plateNumber\":\"AB-1234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"plateNumber\":\"AB-1234\"}"));

        verify(service, times(1)).fetch("AB-1234");
    }

}
