package system;

import electronicCommerce.application.ElectronicCommerceApplication;
import electronicCommerce.domain.models.Product;
import electronicCommerce.rest.exceptionHandlers.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ElectronicCommerceApplication.class)
public class ProductSystemTest {
    @LocalServerPort
    private int port;

    private static final String ULR_LOCAL_HOST = "http://localhost:";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldResponseSuccessfulWhenRetrieveAProduct() {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-17.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-14T15:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-06-14T18:30:00");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(2);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(1);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("25.45");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void shouldResponseBadRequestWhenBrandIdIsInvalid() {
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-17.00.00/productId/35455/brandId/0", ErrorResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getHttpStatus()).isEqualTo(400);
        assertThat(responseEntity.getBody().getType()).isEqualTo("BAD_REQUEST");
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Brand Id can not be less of 1");
        assertThat(responseEntity.getBody().getClazz()).isEqualTo("electronicCommerce.domain.validations.impl.ProductServiceValidationServiceImpl");
        assertThat(responseEntity.getBody().getMethod()).isEqualTo("validate");
        assertThat(responseEntity.getBody().getLine()).isEqualTo(26);

    }

    @Test
    public void shouldResponseBadRequestWhenProductIdIsInvalid() {
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-17.00.00/productId/0/brandId/1", ErrorResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getHttpStatus()).isEqualTo(400);
        assertThat(responseEntity.getBody().getType()).isEqualTo("BAD_REQUEST");
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Product Id can not be less of 1");
        assertThat(responseEntity.getBody().getClazz()).isEqualTo("electronicCommerce.domain.validations.impl.ProductServiceValidationServiceImpl");
        assertThat(responseEntity.getBody().getMethod()).isEqualTo("validate");
        assertThat(responseEntity.getBody().getLine()).isEqualTo(26);
    }

    @Test
    public void shouldResponseBadRequestWhenDateIsInvalid() {
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/0/productId/35455/brandId/1", ErrorResponse.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().getHttpStatus()).isEqualTo(400);
        assertThat(responseEntity.getBody().getType()).isEqualTo("BAD_REQUEST");
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Date is not in the correct format");
        assertThat(responseEntity.getBody().getClazz()).isEqualTo("electronicCommerce.domain.utils.DateMapper");
        assertThat(responseEntity.getBody().getMethod()).isEqualTo("fromStringToLocalDateTime");
        assertThat(responseEntity.getBody().getLine()).isEqualTo(21);
    }

    @Test
    public void shouldResponseNotFoundWhenNotExistProducts() {
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-17.00.00/productId/1/brandId/1", ErrorResponse.class
        );

        assertThat(responseEntity.getBody().getHttpStatus()).isEqualTo(404);
        assertThat(responseEntity.getBody().getType()).isEqualTo("NOT_FOUND");
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Product Not Found");
        assertThat(responseEntity.getBody().getClazz()).isEqualTo("electronicCommerce.persistence.h2.adapters.ProductPersistenceH2Adapter");
        assertThat(responseEntity.getBody().getMethod()).isEqualTo("findByDateAndProductIdAndBrandId");
        assertThat(responseEntity.getBody().getLine()).isEqualTo(34);
    }

    @Test
    public void testCase1() {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-10.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-14T00:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-12-31T23:59:59");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(0);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("35.50");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void testCase2() throws Exception {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-16.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-14T15:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-06-14T18:30:00");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(2);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(1);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("25.45");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void testCase3() throws Exception {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-14-21.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-14T00:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-12-31T23:59:59");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(0);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("35.50");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void testCase4() throws Exception {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-15-20.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-15T16:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-12-31T23:59:59");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(4);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(1);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("38.95");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    public void testCase5() throws Exception {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(
                ULR_LOCAL_HOST + port + "/product/date/2020-06-16-21.00.00/productId/35455/brandId/1", Product.class
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getBrandId()).isEqualTo(1);
        assertThat(responseEntity.getBody().getStartDate()).isEqualTo("2020-06-15T16:00:00");
        assertThat(responseEntity.getBody().getEndDate()).isEqualTo("2020-12-31T23:59:59");
        assertThat(responseEntity.getBody().getPriceListId()).isEqualTo(4);
        assertThat(responseEntity.getBody().getProductId()).isEqualTo(35455);
        assertThat(responseEntity.getBody().getPriority()).isEqualTo(1);
        assertThat(responseEntity.getBody().getPrice().toString()).isEqualTo("38.95");
        assertThat(responseEntity.getBody().getCurrency()).isEqualTo("EUR");
    }
}

