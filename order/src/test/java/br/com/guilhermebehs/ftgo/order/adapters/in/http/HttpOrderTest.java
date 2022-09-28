package br.com.guilhermebehs.ftgo.order.adapters.in.http;

import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import br.com.guilhermebehs.ftgo.order.domain.entities.dtos.OrderItemDto;
import br.com.guilhermebehs.ftgo.order.domain.services.CreateOrderService;
import br.com.guilhermebehs.ftgo.order.mocks.CreateOrderDtoMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HttpOrder.class)
class HttpOrderTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateOrderService createOrderService;

    @BeforeEach
    void setUp() {
        when(createOrderService.create(any())).thenReturn("any order id");
    }

    private void setField(Object obj, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {

        var field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, fieldValue);
    }

    @Nested
    @DisplayName("POST /orders")
    class PostProducts {

        @Test
        @DisplayName("should return 201 and an order id when body is valid")
        public void shouldReturn201AndAnOrderIdWhenBodyIsValid() throws Exception {

            var body = mapper.writeValueAsString(CreateOrderDtoMock.mock());

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().string(containsString("any order id")));;
        }

        @Test
        @DisplayName("should return 400 when customer_name is null")
        public void shouldReturn400WhenCustomerNameIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "customerName", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'customer_name'")));;
        }


        @Test
        @DisplayName("should return 400 when customer_name is blank")
        public void shouldReturn400WhenCustomerNameIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "customerName", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'customer_name'")));;
        }


        @Test
        @DisplayName("should return 400 when customer_address is null")
        public void shouldReturn400WhenCustomerAddressIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "customerAddress", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'customer_address'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.description is null")
        public void shouldReturn400WhenCustomerAddressDescriptionIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "description", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.description is blank")
        public void shouldReturn400WhenCustomerAddressDescriptionIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "description", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));;
        }



        @Test
        @DisplayName("should return 400 when customer_address.neighbourhood is null")
        public void shouldReturn400WhenCustomerAddressNeighbourhoodIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "neighbourhood", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'neighbourhood'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.neighbourhood is blank")
        public void shouldReturn400WhenCustomerAddressNeighbourhoodIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "neighbourhood", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'neighbourhood'")));;
        }


        @Test
        @DisplayName("should return 400 when customer_address.city is null")
        public void shouldReturn400WhenCustomerAddressCityIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "city", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'city'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.city is blank")
        public void shouldReturn400WhenCustomerAddressCityIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "city", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'city'")));;
        }


        @Test
        @DisplayName("should return 400 when customer_address.state is null")
        public void shouldReturn400WhenCustomerAddressStateIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "state", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'state'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.state is blank")
        public void shouldReturn400WhenCustomerAddressStateIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "state", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'state'")));;
        }


        @Test
        @DisplayName("should return 400 when customer_address.country is null")
        public void shouldReturn400WhenCustomerAddressCountryIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "country", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'country'")));;
        }

        @Test
        @DisplayName("should return 400 when customer_address.country is blank")
        public void shouldReturn400WhenCustomerAddressCountryIsBlank() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder.getCustomerAddress(), "country", "");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'country'")));;
        }


        @Test
        @DisplayName("should return 400 when delivery_date_forecast is null")
        public void shouldReturn400WhenDeliveryDateForecastIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "deliveryDateForecast", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'delivery_date_forecast'")));;
        }

        @Test
        @DisplayName("should return 400 when items is null")
        public void shouldReturn400WhenItemsIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "items", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'items'")));;
        }


        @Test
        @DisplayName("should return 400 when items is empty")
        public void shouldReturn400WhenItemsIsEmpty() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "items", new ArrayList<OrderItem>());

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'items'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].description is null")
        public void shouldReturn400WhenItemDescriptionIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto(null, 10D, 1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].description is empty")
        public void shouldReturn400WhenItemDescriptionIsEmpty() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("", 10D, 1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].price is null")
        public void shouldReturn400WhenItemPriceIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", null, 1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'price'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].price is negative")
        public void shouldReturn400WhenItemPriceIsNegative() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", -1D, 1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'price'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].price is zero")
        public void shouldReturn400WhenItemPriceIsZero() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", 0D, 1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'price'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].amount is null")
        public void shouldReturn400WhenItemAmountIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", 10D, null));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'amount'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].amount is negative")
        public void shouldReturn400WhenItemAmountIsNegative() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", 10D, -1));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'amount'")));;
        }


        @Test
        @DisplayName("should return 400 when item[i].amount is zero")
        public void shouldReturn400WhenItemAmountIsZero() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            var items =  List.of(new OrderItemDto("Some description", 10D, 0));

            setField(invalidOrder, "items", items);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'amount'")));;
        }


        @Test
        @DisplayName("should return 400 when kitchen is null")
        public void shouldReturn400WhenKitchenIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "kitchen", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'kitchen'")));;
        }


        @Test
        @DisplayName("should return 400 when kitchen is empty")
        public void shouldReturn400WhenKitchenIsEmpty() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "kitchen","");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'kitchen'")));;
        }


        @Test
        @DisplayName("should return 400 when credit_card is null")
        public void shouldReturn400WhenCreditCardIsNull() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "creditCard", null);

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'credit_card'")));;
        }


        @Test
        @DisplayName("should return 400 when credit_card is empty")
        public void shouldReturn400WhenCreditCardIsEmpty() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "creditCard","");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'credit_card'")));;
        }


        @Test
        @DisplayName("should return 400 when credit_card is less than 4")
        public void shouldReturn400WhenCreditCardIsLessThan4() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "creditCard","123");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("'credit_card' length must be between 4 and 8")));;
        }


        @Test
        @DisplayName("should return 400 when credit_card is greater than 8")
        public void shouldReturn400WhenCreditCardIsGreaterThan8() throws Exception {

            var invalidOrder = CreateOrderDtoMock.mock();
            setField(invalidOrder, "creditCard","123456789");

            var body = mapper.writeValueAsString(invalidOrder);

            mvc.perform(post("/orders")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("'credit_card' length must be between 4 and 8")));;
        }

    }


}