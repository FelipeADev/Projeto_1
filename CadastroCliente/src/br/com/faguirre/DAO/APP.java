package br.com.faguirre.DAO;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

import javax.swing.*;

public class APP {

    private static IClienteDAO iClienteDAO;

    public static void main(String args[]) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para excluir, 4 para alteração ou 5 para sair.",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! Digite 1 para cadastro, 2 para consultar, 3 para excluir, 4 para alteração ou 5 para sair.",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {

                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme o exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                        "Green dinner", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
                

            } else if (isConsultar(opcao)) {
                     String dados = JOptionPane.showInputDialog(null,
                            "Digite o CPF",
                            "Consultar", JOptionPane.INFORMATION_MESSAGE);
                    consultar(dados);

            } else if (isExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF a ser excluído.",
                        "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if (isAtualizar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente, que será atualizado, separados por vírgula, conforme o exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                        "Excluir", JOptionPane.INFORMATION_MESSAGE);
                atualiza(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para excluir, 4 para alteração ou 5 para sair.",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    private static boolean cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");

        if(dadosSeparados.length == 7){
            Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
            Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

            if (isCadastrado) {
                JOptionPane.showMessageDialog(null,
                        "Cliente cadastrado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente já se encontra cadastrado",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
          return true;
        }
        JOptionPane.showMessageDialog(null,
                "Todos os campos devem ser preenchidos!",
                "Erro", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    private static void consultar(String dados) {
        if(dados.isEmpty()){

            JOptionPane.showMessageDialog(null,
                    "Nenhum CPF foi passado!",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);

        }else {
            Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
            if (cliente != null) {

                JOptionPane.showMessageDialog(null,
                        "Cliente encontrado: " + cliente,
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente não encontrado!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private static void excluir(String dados) {
        if("".equals(dados)){
            JOptionPane.showMessageDialog(null,
                    "Nenhum CPF foi passado",
                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }else {
            iClienteDAO.excluir(Long.parseLong(dados));
            JOptionPane.showMessageDialog(null,
                    "Cliente excluido com sucesso",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void atualiza(String dados) {
        String[] dadosSeparados = dados.split(",");

        if("".equals(dados)){
            JOptionPane.showMessageDialog(null,
                    "Não foi passado nenhum dado!",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        }else {
            if(dadosSeparados.length == 7){
                Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
                iClienteDAO.alterar(cliente);
        }else {
                JOptionPane.showMessageDialog(null,
                        "Todos os campos devem ser preenchidos!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static void sair () {
        JOptionPane.showMessageDialog(null,
                "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


    private static boolean isOpcaoValida (String opcao){
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        } 
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isExcluir(String opcao) {
        if("3".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isAtualizar(String opcao) {
        if("4".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }
}
