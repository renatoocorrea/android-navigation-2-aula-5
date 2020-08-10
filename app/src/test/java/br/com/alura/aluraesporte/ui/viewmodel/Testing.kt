package br.com.alura.aluraesporte.ui.viewmodel

import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.repository.ProdutoRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetalhesProdutoViewModelTest {

    // Nome da função está estranho.
    // Não existe divisão de Arrange, Act, Assert.

    @io.mockk.impl.annotations.MockK
    private lateinit var detalhesProdutoViewModel: DetalhesProdutoViewModel

    @io.mockk.impl.annotations.MockK
    private lateinit var produtosRepository: ProdutoRepository

    @Before
    fun setup() {
        produtosRepository = mockkClass(ProdutoRepository::class)
        MockKAnnotations.init(this)
    }

    @Test
    fun produtoEncontrado_buscandoProdutoPeloId_trazProdutoEncontradoPeloId() {
        //Arrange
        val idProd : Long = 2

        //Act
        every { produtosRepository.buscaPorId(idProd).value }.returns(Produto(2,"BOLA", 100.toBigDecimal()))
        detalhesProdutoViewModel = DetalhesProdutoViewModel(idProd, produtosRepository)

        //Assert
        val prodEncontrado = detalhesProdutoViewModel.produtoEncontrado
        Assert.assertEquals(idProd, prodEncontrado.value?.id)
        Assert.assertEquals("BOLA", prodEncontrado.value?.nome)
        Assert.assertEquals(100.toBigDecimal(), prodEncontrado.value?.preco)
    }
}