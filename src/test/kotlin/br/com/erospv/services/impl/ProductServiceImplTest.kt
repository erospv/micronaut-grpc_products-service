package br.com.erospv.services.impl

import br.com.erospv.domain.Product
import br.com.erospv.dto.ProductReq
import br.com.erospv.exceptions.AlreadyExistsException
import br.com.erospv.repository.ProductRepository
import io.grpc.Status.Code
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrowsExactly
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

    @Test
    fun `method create with duplicated product name returns AlreadyExistsException` () {
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

        `when`(productRepository.findByNameIgnoreCase(productInput.name))
            .thenReturn(productOutput)

        val productReq = ProductReq(name = "product name", price = 60.00, quantityInStock = 15)
        val exception = assertThrowsExactly(AlreadyExistsException::class.java) {
            productService.create(productReq)
        }
        assertEquals("Produto ${productReq.name} já cadastrado no sistema", exception.errorMessage())
        assertEquals(Code.ALREADY_EXISTS, exception.statusCode())
    }
}