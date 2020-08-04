package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import br.com.alura.aluraesporte.database.dao.ProdutoDAO
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.repository.ProdutoRepository
import io.mockk.MockK
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProdutosViewModelTest {

    @io.mockk.impl.annotations.MockK
    private lateinit var detalhesProdutoViewModel: DetalhesProdutoViewModel

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
    fun buscaPorId_idValido_retornaProdutoValido() {

        every { produtosRepository.buscaPorId(2).value }.returns(Produto(2,"BOLA", 100.toBigDecimal()))
        every { produtosRepository.buscaTodos().value }.returns(mockListObjects())

        produtosViewModel = ProdutosViewModel(produtosRepository)
        val listOfItems = produtosViewModel.buscaTodos()
        Assert.assertEquals(2.toLong(), listOfItems.value?.get(0)?.id)
    }

    fun mockListObjects(): List<Produto> {
        return listOf<Produto>(
            Produto(1, "Camiseta", 250.toBigDecimal()),
            Produto(2, "Bola Futebol", 69.toBigDecimal()),
            Produto(3, "Camiseta Knicks", 299.toBigDecimal())
        )
    }
}