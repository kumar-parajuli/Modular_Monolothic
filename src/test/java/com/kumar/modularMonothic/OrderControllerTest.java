package com.kumar.modularMonothic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumar.modularMonothic.OrderModule.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;  // Use Jackson to convert objects to JSON

    @BeforeEach
    public void setUp() {
        reset(orderRepository);  // Reset mocks before each test
    }

    @Test
    public void shouldReturnEmptyOrderList() throws Exception {
        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));  // Expecting an empty list
    }

//    @Test
//    public void shouldCreateNewOrder() throws Exception {
//        String newOrder = "{\n" +
//                "  \"orderNumber\": \"ORD002\",\n" +
//                "  \"customerId\": 1,\n" +
//                "  \"deliveryAddress\": \"123 Main St\",\n" +
//                "  \"status\": \"PENDING\",\n" +
//                "  \"items\": [\n" +
//                "    {\n" +
//                "      \"code\": \"P001\",\n" +
//                "      \"name\": \"Product 1\",\n" +
//                "      \"price\": 10.99,\n" +
//                "      \"quantity\": 1\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//
//        mockMvc.perform(post("/orders")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(newOrder))
//                .andExpect(status().isCreated())  // Expecting 201 Created status
//                .andExpect(jsonPath("$.orderNumber").value("ORD002"))  // Verifying the order number
//                .andExpect(jsonPath("$.deliveryAddress").value("123 Main St"));  // Verifying delivery address
//    }
}
