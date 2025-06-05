package org.sistema.controller;

import org.sistema.model.Item;

import java.io.*;
import java.util.ArrayList;

public class BancoDeDados {

    public BancoDeDados() {
        // Nenhuma ação necessária por enquanto
    }

    public void cadastrar(Item item, boolean opcao) {
        try {
            OutputStream os = new FileOutputStream("medicamento.txt", opcao); // nome correto e consistente
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String linha = item.getNome() + "," + item.getQuantidade() + "," + item.getTipo();
            bw.write(linha);
            bw.newLine();

            bw.close();
            osw.close();
            os.close();

            System.out.println("O medicamento " + item.getNome() + " foi cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não conseguiu cadastrar o medicamento!");
            e.printStackTrace();
        }
    }
    public void editar(int codigo, ArrayList<Item> itens) {
        boolean encontrado = false;
    
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getCodigo() == codigo) {
                Item item = itens.get(i);
                item.setNome("Tilenol 200mg XPSKT2");
                item.setQuantidade(300);
                item.setTipo("Frasco de 200ml");
    
                itens.set(i, item); // Atualiza o item na lista
                encontrado = true;
                System.out.println("Item atualizado com sucesso!");
                break;
            }
        }
    
        if (encontrado) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("medicamento.txt", false)); // sobrescreve o arquivo
    
                for (Item item : itens) {
                    String linha = item.getNome() + "," + item.getQuantidade() + "," + item.getTipo();
                    bw.write(linha);
                    bw.newLine();
                }
    
                bw.close();
                System.out.println("Arquivo medicamento.txt atualizado com as alterações!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar as alterações no arquivo!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Item com código " + codigo + " não encontrado para edição.");
        }
    }
    
    // Método (ainda não implementado) que poderá permitir pesquisa de um item pelo código
    public Item pesquisar(int codigo, ArrayList<Item> itens) {
        for (Item item : itens) {
            if (item.getCodigo() == codigo) { // Verifica se o código do Item corresponde ao que foi solicitado
                return item;
            }
        }
        return null; // Retorna null caso o item não seja encontrado
    }

    public void excluir(int codigo, ArrayList<Item> itens) {
        Item itemParaRemover = null;
        
        for (Item item : itens) {
            if (item.getCodigo() == codigo) { // Verifica se o código do Item corresponde ao que foi solicitado
                itemParaRemover = item;
                break;
            }
        }
    
        if (itemParaRemover != null) {
            itens.remove(itemParaRemover);
            System.out.println("Item removido com sucesso!");
    
            try {
                OutputStream os = new FileOutputStream("medicamento.txt", false); // Sobrescreve o arquivo
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
    
                for (Item item : itens) { // Escreve os itens restantes no arquivo
                    String linha = item.getNome() + "," + item.getQuantidade() + "," + item.getTipo();
                    bw.write(linha);
                    bw.newLine();
                }
    
                bw.close();
                osw.close();
                os.close();
    
                System.out.println("Arquivo atualizado após exclusão!");
    
            } catch (Exception e) {
                System.out.println("Erro ao atualizar o arquivo após exclusão!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Item não encontrado para exclusão.");
        }
    }
    public ArrayList<Item> ler() {
        ArrayList<Item> itens = new ArrayList<>();
    
        try {
            InputStream is = new FileInputStream("medicamento.txt"); // nome corrigido
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
    
            String linha = br.readLine();
            int codigo = 1; // Inicializa o código para garantir que cada item receba um valor único
    
            while (linha != null) {
                String[] elementos = linha.split(",");
                if (elementos.length == 3) {
                    int quantidade = Integer.parseInt(elementos[1]);
                    Item item = new Item(codigo, elementos[0], quantidade, elementos[2]); // Passando o código corretamente
                    itens.add(item);
                    codigo++; // Incrementa o código para o próximo item
                }
                linha = br.readLine();
            }
    
            br.close();
            isr.close();
            is.close();
    
            System.out.println("O arquivo medicamento.txt foi lido com sucesso!");
        } catch (Exception e) {
            System.out.println("Não conseguiu ler o arquivo!");
            e.printStackTrace(); // ajuda a diagnosticar
        }
    
        return itens; // mesmo em caso de erro, retorna lista vazia
    }
}
