package br.com.erospv.resources

import br.com.erospv.ProductServiceRequest
import br.com.erospv.ProductsServiceGrpc
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ProductResourcesTestIT(
    private val productsServiceBlockingStub: ProductsServiceGrpc.ProductsServiceBlockingStub
) {

    @Test
    fun `when ProductServiceGrpc create method is call with valid data a success is returned`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(20.50)
            .setQuantityInStock(10)
            .build()

        val response = productsServiceBlockingStub.create(request)

        assertEquals(1, response.id)
        assertEquals("product name", response.name)
    }
}