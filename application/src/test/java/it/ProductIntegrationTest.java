package it;

import electronicCommerce.application.ElectronicCommerceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(classes = ElectronicCommerceApplication.class)
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldResponseSuccessfulWhenRetrieveAProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-17.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-14T15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-06-14T18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("25.45"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }

    @Test
    public void shouldReturnABadRequestWhenBrandIdIsIncorrect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-17.00.00/productId/35455/brandId/0"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Brand Id can not be less of 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clazz").value("electronicCommerce.domain.validations.impl.ProductServiceValidationServiceImpl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.method").value("validate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.line").value(26));
    }

    @Test
    public void shouldReturnABadRequestWhenProductIdIsIncorrect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-17.00.00/productId/0/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product Id can not be less of 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clazz").value("electronicCommerce.domain.validations.impl.ProductServiceValidationServiceImpl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.method").value("validate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.line").value(26));
    }

    @Test
    public void shouldReturnABadRequestWhenDateIsIncorrect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/wrong/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Date is not in the correct format"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clazz").value("electronicCommerce.domain.utils.DateMapper"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.method").value("fromStringToLocalDateTime"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.line").value(21));
    }

    @Test
    public void shouldReturnNotFoundWhenNotExistProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-17.00.00/productId/1/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Product Not Found"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clazz").value("electronicCommerce.persistence.h2.adapters.ProductPersistenceH2Adapter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.method").value("findByDateAndProductIdAndBrandId"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.line").value(34));
    }

    @Test
    public void testCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-10.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("35.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }

    @Test
    public void testCase2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-16.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-14T15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-06-14T18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("25.45"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }

    @Test
    public void testCase3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-14-21.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("35.5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }

    @Test
    public void testCase4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-15-20.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-15T16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }

    @Test
    public void testCase5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/date/2020-06-16-21.00.00/productId/35455/brandId/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.BRAND_ID").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.START_DATE").value("2020-06-15T16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.END_DATE").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE_LIST").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRODUCT_ID").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRIORITY").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PRICE").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.CURRENCY").value("EUR"));
    }
}
