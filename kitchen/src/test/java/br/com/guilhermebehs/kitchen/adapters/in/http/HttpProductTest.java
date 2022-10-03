package br.com.guilhermebehs.kitchen.adapters.in.http;

import br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.HttpProduct;
import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.ProductNotFoundException;
import br.com.guilhermebehs.ftgo.kitchen.domain.services.*;
import br.com.guilhermebehs.kitchen.mocks.FindProductDtoMock;
import br.com.guilhermebehs.kitchen.mocks.ProductDtoMock;
import br.com.guilhermebehs.kitchen.mocks.ProductMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HttpProduct.class)
@DisplayName("HttpProduct")
class HttpProductTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateProductService createProductService;

    @MockBean
    private FindProductService findProductService;

    @MockBean
    private BookProductAmountService bookProductAmountService;

    @MockBean
    private CancelBookedProductAmountService cancelBookedProductAmountService;

    @MockBean
    private ConfirmBookedProductAmountService confirmBookedProductAmountService;

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("POST /products")
    class PostProducts{

        @Test
        @DisplayName("should return 204 when body is valid")
        public void shouldReturn204WhenBodyIsValid() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mock());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("should return 400 when description is null")
        public void shouldReturn400WhenDescriptionIsNull() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithNullDescription());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));
        }

        @Test
        @DisplayName("should return 400 when description is blank")
        public void shouldReturn400WhenDescriptionIsBlank() throws Exception {

            var body = mapper.writeValueAsString(FindProductDtoMock.mock());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'description'")));
        }

        @Test
        @DisplayName("should return 400 when kitchen is null")
        public void shouldReturn400WhenKitchenIsNull() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithNullKitchen());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'kitchen'")));
        }

        @Test
        @DisplayName("should return 400 when kitchen is blank")
        public void shouldReturn400WhenKitchenIsBlank() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithBlankKitchen());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'kitchen'")));
        }

        @Test
        @DisplayName("should return 400 when is_active is null")
        public void shouldReturn400WhenIsActieIsNull() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithNullIsActive());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'is_active'")));
        }

        @Test
        @DisplayName("should return 400 when available_amount is null")
        public void shouldReturn400WhenAvaiableAmountIsNull() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithNullAvailableAmount());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("invalid value for 'available_amount'")));
        }

        @Test
        @DisplayName("should return 400 when available_amount is negative")
        public void shouldReturn400WhenAvaiableAmountIsNegative() throws Exception {

            var body = mapper.writeValueAsString(ProductDtoMock.mockWithNegativeAvailableAmount());

            mvc.perform(post("/products")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(
                            containsString("'available_amount' must be equal or greater than 0")));
        }
    }


    @Nested
    @DisplayName("GET /products/{productDescription}/kitchens/{kitchenDescription}")
    class GetProductById{

        @Test
        @DisplayName("should return 200 when product exists")
        public void shouldReturn200WhenProductExists() throws Exception {

            when(findProductService.findById(ArgumentMatchers.any())).thenReturn(ProductMock.mock());

            mvc.perform(get("/products/some_product_description/kitchens/some_kitchen_description")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("should return 404 when product is not found")
        public void shouldReturn404WhenProductIsNotFound() throws Exception {

            when(findProductService.findById(ArgumentMatchers.any())).thenThrow(new ProductNotFoundException());

            mvc.perform(get("/products/some_product_description/kitchens/some_kitchen_description")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string(
                            containsString("product not found")));
        }
    }


    @Nested
    @DisplayName("Patch /products/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/book-amount")
        class PatchBookAmount {

        @Test
        @DisplayName("should return 204 when update occurs correctly")
        public void shouldReturn204WhenUpdateOccursCorrectly() throws Exception {
            mvc.perform(patch("/products/some_product_description/kitchens/some_kitchen_description/amount/1/book-amount")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

    }

    @Nested
    @DisplayName("Patch /products/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/confirm-booked-amount")
    class PatchConfirmBookedAmount {

        @Test
        @DisplayName("should return 204 when update occurs correctly")
        public void shouldReturn204WhenUpdateOccursCorrectly() throws Exception {
            mvc.perform(patch("/products/some_product_description/kitchens/some_kitchen_description/amount/1/confirm-booked-amount")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

    }



    @Nested
    @DisplayName("Patch /products/{productDescription}/kitchens/{kitchenDescription}/amount/{amount}/cancel-booked-amount")
    class PatchCancelBookedAmount {

        @Test
        @DisplayName("should return 204 when update occurs correctly")
        public void shouldReturn204WhenUpdateOccursCorrectly() throws Exception {
            mvc.perform(patch("/products/some_product_description/kitchens/some_kitchen_description/amount/1/cancel-booked-amount")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }

    }

}