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
class ProdutosViewModelTest {

    // Alguns mocks dessa classe não são utilizados.
    // Nome da função está estranho.
    // Não existe divisão de Arrange, Act, Assert.
    // Busca por Id ou BuscaTodos?

    @io.mockk.impl.annotations.MockK
    private lateinit var produtosRepository: ProdutoRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var produtosViewModel: ProdutosViewModel

    @Before
    fun setup() {
        produtosRepository = mockkClass(ProdutoRepository::class)
        MockKAnnotations.init(this)
    }

    @Test
    fun buscaTodos_buscaTodosOsProdutos_retornaListaDeProdutos() {
        //Arrange
        var i = 0

        //Act
        every { produtosRepository.buscaTodos().value }.returns(mockListObjects())
        produtosViewModel = ProdutosViewModel(produtosRepository)

        //Assert
        val listOfItems = produtosViewModel.buscaTodos()
        while (i < 3) {
            Assert.assertEquals(mockListObjects().get(i).nome, listOfItems.value?.get(i)?.nome)
            i++
        }
    }

    fun mockListObjects(): List<Produto> {
        return listOf<Produto>(
            Produto(1, "Camiseta", 250.toBigDecimal()),
            Produto(2, "Bola Futebol", 69.toBigDecimal()),
            Produto(3, "Camiseta Knicks", 299.toBigDecimal())
        )
    }
}