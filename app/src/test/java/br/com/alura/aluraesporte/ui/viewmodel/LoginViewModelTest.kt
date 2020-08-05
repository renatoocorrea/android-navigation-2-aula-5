package br.com.alura.aluraesporte.ui.viewmodel

import br.com.alura.aluraesporte.repository.LoginRepository
import br.com.alura.aluraesporte.repository.ProdutoRepository
import io.mockk.*
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    // Alguns mocks dessa classe não são utilizados.
    // Nome da função está estranho.
    // Não existe divisão de Arrange, Act, Assert.
    // Cobertura baixa, falta cobrir outros métodos do LoginViewModel.

    @io.mockk.impl.annotations.MockK
    private lateinit var detalhesProdutoViewModel: DetalhesProdutoViewModel

    @io.mockk.impl.annotations.MockK
    private lateinit var produtosRepository: ProdutoRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var loginRepository: LoginRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var loginViewModel: LoginViewModel


    @Before
    fun setup() {
        loginRepository = mockkClass(LoginRepository::class)
        MockKAnnotations.init(this)
    }

    @Test
    fun loga() {
        loginViewModel = LoginViewModel(loginRepository)
        every { loginRepository.loga() } just Runs
        every { loginRepository.estaLogado() } returns true

        loginViewModel.loga()
        val logado = loginViewModel.estaLogado()
        Assert.assertEquals(true, logado)
    }

}