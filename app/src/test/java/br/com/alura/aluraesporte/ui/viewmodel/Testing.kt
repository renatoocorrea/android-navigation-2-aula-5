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
class Testing {

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
    fun exibeTodosOsCamposComValoresPreenchidos() {

        every { produtosRepository.buscaPorId(2).value }.returns(Produto(2,"BOLA", 100.toBigDecimal()))

        detalhesProdutoViewModel = DetalhesProdutoViewModel(2, produtosRepository)
        Assert.assertEquals(2.toLong(), detalhesProdutoViewModel.produtoEncontrado.value?.id)
        Assert.assertEquals("BOLA", detalhesProdutoViewModel.produtoEncontrado.value?.nome)
        Assert.assertEquals(100.toBigDecimal(), detalhesProdutoViewModel.produtoEncontrado.value?.preco)
    }
}