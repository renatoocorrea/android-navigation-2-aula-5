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
    private lateinit var loginRepository: LoginRepository

    @io.mockk.impl.annotations.MockK
    private lateinit var loginViewModel: LoginViewModel


    @Before
    fun setup() {
        loginRepository = mockkClass(LoginRepository::class)
        MockKAnnotations.init(this)
    }

    @Test
    fun loga_usuarioLogando_sucesso() {
        //Arrange
        loginViewModel = LoginViewModel(loginRepository)

        //Act
        every { loginRepository.loga() } just Runs
        every { loginRepository.estaLogado() } returns true
        loginViewModel.loga()
        val logado = loginViewModel.estaLogado()

        //Assert
        Assert.assertEquals(true, logado)
    }

    @Test
    fun loga_usuarioLogando_falha() {
        loginViewModel = LoginViewModel(loginRepository)

        every { loginRepository.loga() } just Runs
        every { loginRepository.estaLogado() } returns false
        loginViewModel.loga()
        val logadoComFalha= loginViewModel.estaLogado()

        Assert.assertEquals(false, logadoComFalha)
    }

    @Test
    fun desloga_usuarioDeslogando_sucesso() {
        loginViewModel = LoginViewModel(loginRepository)

        every { loginRepository.desloga() } just Runs
        every { loginRepository.estaLogado() } returns false
        loginViewModel.desloga()
        val deslogado = loginViewModel.naoEstaLogado()

        Assert.assertEquals(true, deslogado)
    }

    @Test
    fun desloga_usuarioDeslogando_falha() {
        loginViewModel = LoginViewModel(loginRepository)

        every { loginRepository.desloga() } just Runs
        every { loginRepository.estaLogado() } returns true
        loginViewModel.desloga()
        val deslogado = loginViewModel.naoEstaLogado()

        Assert.assertEquals(false, deslogado)
    }

}