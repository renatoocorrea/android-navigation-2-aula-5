package br.com.alura.aluraesporte.ui.viewmodel

import br.com.alura.aluraesporte.model.Pagamento
import br.com.alura.aluraesporte.repository.PagamentoRepository
import br.com.alura.aluraesporte.repository.ProdutoRepository
import br.com.alura.aluraesporte.repository.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkClass
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class PagamentoViewModelTesting {

    // Alguns mocks dessa classe não são utilizados.
    // Nome da função está estranho.
    // Não existe divisão de Arrange, Act, Assert.

    @io.mockk.impl.annotations.MockK
    private lateinit var detalhesProdutoViewModel: DetalhesProdutoViewModel

    @io.mockk.impl.annotations.MockK
    private lateinit var produtosRepository: ProdutoRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var pagamentoRepository: PagamentoRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var pagamentoViewModel: PagamentoViewModel

    @Before
    fun setup() {
        produtosRepository = mockkClass(ProdutoRepository::class)
        pagamentoRepository = mockkClass(PagamentoRepository::class)
        MockKAnnotations.init(this)
    }

    @Test
    fun salva_checkSalve() {
        val pagamento =  Pagamento(
            1.toLong(),
            123,
            "14/10/2029",
            123,
            200.toBigDecimal(),
            1.toLong()
        )
        every { pagamentoRepository.salva(pagamento).value } returns Resource(1.toLong())

        every { pagamentoRepository.todos().value } returns listOf(pagamento)

        pagamentoViewModel = PagamentoViewModel(pagamentoRepository, produtosRepository)

        pagamentoViewModel.salva(pagamento)
        val pagamentoSalvo = pagamentoViewModel.todos()
        Assert.assertEquals(123, pagamentoSalvo.value?.get(0)?.numeroCartao)

    }
}