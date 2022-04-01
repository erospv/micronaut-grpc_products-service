package br.com.erospv.services.impl

import br.com.erospv.domain.Product
import br.com.erospv.dto.ProductReq
import br.com.erospv.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productService = ProductServiceImpl(productRepository)

    @Test
    fun `method create with valid data returns ProductRes` () {
        val productInput = Product(
            id = null,
            name = "product name",
            price = 10.00,
            quantityInStock = 5
        )

        val productOutput = Product(
            id = 1,
            name = "product name",
            price = 10.00,
            quantityInStock = 5
        )

        `when`(productRepository.save(productInput))
            .thenReturn(productOutput)

        val productReq = ProductReq(name = "product name", price = 10.00, quantityInStock = 5)

        val productRes = productService.create(productReq)

        assertEquals(productReq.name, productRes.name)
    }
}