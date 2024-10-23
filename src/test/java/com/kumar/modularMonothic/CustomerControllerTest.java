package com.kumar.modularMonothic;

import com.kumar.modularMonothic.CustomerModule.Customer;
import com.kumar.modularMonothic.CustomerModule.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        // Clean up the repository before each test
        customerRepository.deleteAll();
        // Adding some sample customers
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");

        customerRepository.save(customer1);
    }


    @Test
    public void shouldReturnEmptyListWhenNoCustomers() throws Exception {
        // Clear all customers
        customerRepository.deleteAll();

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
    @Test
    public void shouldReturnCustomersList() throws Exception {
        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }
}
