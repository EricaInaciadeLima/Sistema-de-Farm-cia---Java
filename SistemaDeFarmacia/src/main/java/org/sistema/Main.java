package org.sistema;

import java.util.ArrayList;
import org.sistema.controller.BancoDeDados;
import org.sistema.model.Item;

public class Main {
    public static void main(String[] args) {

        System.out.println("Sistema de Farmácia.");

        // Criamos um objeto da classe BancoDeDados.
        // Ele será responsável por salvar, ler, editar, excluir e pesquisar medicamentos no arquivo.
        BancoDeDados banco = new BancoDeDados();

        // === 1. CADASTRAR um novo medicamento ===
        // Criamos um objeto do tipo Item (representa um medicamento)
        Item novoItem = new Item(0, "Paracetamol 750mg", 150, "Caixa");
        
        // Chamamos o método 'cadastrar', passando o novo item e true para adicionar no final do arquivo
        banco.cadastrar(novoItem, true); // true = adiciona no fim, false = sobrescreve tudo

        // === 2. LER todos os medicamentos do arquivo ===
        // Este método retorna uma lista com todos os medicamentos já salvos
        ArrayList<Item> itens = banco.ler();

        // Mostra todos os medicamentos lidos do arquivo
        System.out.println("\nLista de medicamentos cadastrados:");
        for (Item item : itens) {
            System.out.println("Código: " + item.getCodigo() + 
                               ", Nome: " + item.getNome() + 
                               ", Quantidade: " + item.getQuantidade() + 
                               ", Tipo: " + item.getTipo());
        }

        // === 3. PESQUISAR um medicamento pelo código ===
        int codigoParaPesquisar = 1; // O código do item que queremos buscar
        Item itemPesquisado = banco.pesquisar(codigoParaPesquisar, itens);

        if (itemPesquisado != null) {
            System.out.println("\nMedicamento encontrado com código " + codigoParaPesquisar + ": " + itemPesquisado.getNome());
        } else {
            System.out.println("\nNão foi encontrado medicamento com esse código.");
        }

        // === 4. EDITAR um medicamento pelo código ===
        int codigoParaEditar = 1; // Código do item que queremos editar
        banco.editar(codigoParaEditar, itens); // A função 'editar' já atualiza o item na lista

        // Reescreve o arquivo com os itens atualizados após edição
        // Para salvar as alterações no arquivo, usamos cadastrar com 'false' e regravamos todos os itens
        for (int i = 0; i < itens.size(); i++) {
            banco.cadastrar(itens.get(i), i != 0); 
        }

        // === 5. EXCLUIR um medicamento pelo código ===
        int codigoParaExcluir = 1;
        banco.excluir(codigoParaExcluir, itens); // Remove da lista e atualiza o arquivo

        System.out.println("\nSistema encerrado.");
    }
}
