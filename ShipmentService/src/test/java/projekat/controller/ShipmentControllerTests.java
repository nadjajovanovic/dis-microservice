package projekat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import projekat.dto.ShipmentRequest;
import projekat.dto.ShipmentResponse;
import projekat.entity.Shipment;
import projekat.exception.ShipmentCustomException;
import projekat.repository.ShipmentRepository;
import projekat.service.ShipmentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentController.class)
public class ShipmentControllerTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private ShipmentService shipmentService;
    @MockBean private ShipmentRepository shipmentRepository;


    @Test
    public void testShipmentSuccess() throws Exception{
        ShipmentRequest newShipment = shipmentRequestMock();

        String requestBody = objectMapper.writeValueAsString(newShipment);

        mockMvc.perform(post("/shipment/place-shipment")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testFindShipmentById() throws Exception{
        Integer id = 1;
        String url = "/shipment/" + id;

        ShipmentResponse shipment = shipmentMock(id);

        Mockito.when(shipmentService.getShipmentById(id)).thenReturn(shipment);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testFindShipmentByIdNotFound() throws Exception {
        Integer id = 9999;
        String url = "/shipment/" + id;

        Mockito.when(shipmentService.getShipmentById(id)).thenThrow(ShipmentCustomException.class);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    public ShipmentRequest shipmentRequestMock() {
        ShipmentRequest shipment = new ShipmentRequest();
        shipment.setCargoId(1);
        shipment.setNumberOfParts(5);
        shipment.setShipmentStatus("CREATED");
        shipment.setAmount(200);
        return shipment;
    }

    public ShipmentResponse shipmentMock(Integer id) {
        ShipmentResponse shipment = new ShipmentResponse();
        shipment.setShipmentId(id);
        shipment.setNumberOfParts(5);
        shipment.setShipmentStatus("CREATED");
        return shipment;
    }
}
