package br.com.erospv.utils

import br.com.erospv.ProductServiceRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidationUtilTest {

    @Test
    fun `when validatePayload method is called with valid product name, should not throw an exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("product name")
            .setPrice(20.50)
            .setQuantityInStock(10)
            .build()

        assertDoesNotThrow {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validatePayload method is called with invalid data, should throw an exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("")
            .setPrice(20.50)
            .setQuantityInStock(10)
            .build()

        val exception = assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
        assertEquals("Nome não pode ser nulo ou vazio", exception.message)
    }

    @Test
    fun `when validatePayload method is called with invalid price, should throw an exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("Product name")
            .setPrice(-20.50)
            .setQuantityInStock(10)
            .build()

        val exception = assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
        assertEquals("Preço precisa ser um valor válido", exception.message)
    }

    @Test
    fun `when validatePayload method is called with null payload, should throw an exception`() {
               assertThrowsExactly(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(null)
        }
    }
}