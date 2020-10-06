package TesteUnitarios;

import JDBC.ConnectionFactory;
import controller.ServiçoController;
import model.Serviços;

import java.util.List;

import static org.junit.Assert.*;

public class ServiçoControllerTest {
    private ServiçoController serviçoController = new ServiçoController(new ConnectionFactory());
    @org.junit.Test
    public void criarServiço() {

        boolean resultado = serviçoController.criarServiço(new Serviços("", (float) 20));
        assertFalse(resultado);
        boolean resultado2 = serviçoController.criarServiço(new Serviços("Cortar 2 Unhas", 30));
        assertFalse(resultado2);
        boolean resultado3 = serviçoController.criarServiço(new Serviços("Tosar", -15.5));
        assertFalse(resultado3);

        boolean resultado4 = serviçoController.criarServiço(new Serviços("Banho", 20));
        assertTrue(resultado4);
        boolean resultado5 = serviçoController.criarServiço(new Serviços("Cortar Unhas", 30));
        assertTrue(resultado5);
        boolean resultado6 = serviçoController.criarServiço(new Serviços("Tosar", 15.5));
        assertTrue(resultado6);
        boolean resultado7 = serviçoController.criarServiço(new Serviços("Aplicar Injeção", 11.5));
        assertTrue(resultado7);
    }

    @org.junit.Test
    public void removerServiço() {
        serviçoController.criarServiço(new Serviços("teste", 20));
        Serviços serviços = serviçoController.pegarUmServiço(serviçoController.pegarUltimoIDCadastrado());
        assertTrue(serviçoController.removerServiço(serviços.getId()));
    }

    @org.junit.Test
    public void editarServiço() {
        boolean resultado = serviçoController.criarServiço(new Serviços("teste", 20));
        Serviços serviços = serviçoController.pegarUmServiço(serviçoController.pegarUltimoIDCadastrado());
        assertTrue(resultado);

        Serviços editServiço = new Serviços("testeeditado", 30);
        boolean resultado2 = serviçoController.editarServiço(editServiço, serviços.getId());
        assertTrue(resultado2);

        Serviços editServiço2 = new Serviços("",40);
        boolean resultado3 = serviçoController.editarServiço(editServiço2, serviços.getId());
        assertFalse(resultado3);

    }

    @org.junit.Test
    public void mostrarServiços() {
        serviçoController.criarServiço(new Serviços("testee",30));
        serviçoController.criarServiço(new Serviços("testeee",40));
        List<Serviços> serviços = serviçoController.mostrarServiços();
        for (Serviços serviço: serviços
             ) {
            System.out.println(serviço.toString());
        }
    }

    @org.junit.Test
    public void validarServiço() {
        boolean resultado = serviçoController.validarServiço(new Serviços("", (float) 20));
        assertFalse(resultado);
        boolean resultado2 = serviçoController.validarServiço(new Serviços("Cortar 2 Unhas", 30));
        assertFalse(resultado2);
        boolean resultado3 = serviçoController.validarServiço(new Serviços("Tosar", -15.5));
        assertFalse(resultado3);

        boolean resultado4 = serviçoController.validarServiço(new Serviços("Banho", 20));
        assertTrue(resultado4);
        boolean resultado5 = serviçoController.validarServiço(new Serviços("Cortar Unhas", 30));
        assertTrue(resultado5);
        boolean resultado6 = serviçoController.validarServiço(new Serviços("Tosar", 15.5));
        assertTrue(resultado6);
        boolean resultado7 = serviçoController.validarServiço(new Serviços("Aplicar Injeção", 11.5));
        assertTrue(resultado7);

    }


}
