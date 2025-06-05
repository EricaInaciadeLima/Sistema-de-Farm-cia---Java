package org.sistema.model;

// Esta classe representa um "Item" (um medicamento no sistema)
public class Item {

    // === Atributos privados (encapsulamento) ===
    private int codigo;        // Identificador único do item
    private String nome;       // Nome do medicamento
    private int quantidade;    // Quantidade disponível
    private String tipo;       // Tipo de embalagem ou forma (ex: caixa, frasco)

    // === Construtor da classe ===
    // Usado para criar um novo objeto já com os dados definidos
    public Item(int codigo, String nome, int quantidade, String tipo) {
        // Os métodos set... garantem que os valores sejam atribuídos corretamente
        this.setCodigo(codigo);           // Define o código
        this.setNome(nome);               // Define o nome
        this.setQuantidade(quantidade);   // Define a quantidade
        this.setTipo(tipo);               // Define o tipo
    }

    // === Métodos Getters e Setters ===
    // Usados para acessar (get) ou modificar (set) os valores dos atributos
    // São necessários porque os atributos são privados

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
