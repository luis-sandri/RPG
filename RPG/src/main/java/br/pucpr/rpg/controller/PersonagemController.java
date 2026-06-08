package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Personagem;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersonagemController {

    private final String arquivo = "personagens.obj";

    public ArrayList<Personagem> listar() {
        return lerLista();
    }

    public void cadastrar(Personagem personagem) {
        ArrayList<Personagem> personagens = lerLista();

        personagem.setId(proximoId(personagens));
        personagens.add(personagem);
        salvarLista(personagens);
    }

    public void editar(Personagem selecionado, String nome, int nivel, int vida, int mana) {
        if (selecionado != null) {
            ArrayList<Personagem> personagens = lerLista();

            for (Personagem personagem : personagens) {
                if (personagem.getId() == selecionado.getId()) {
                    personagem.setNome(nome);
                    personagem.setNivel(nivel);
                    personagem.setVida(vida);
                    personagem.setMana(mana);
                }
            }

            salvarLista(personagens);
        }
    }

    public void excluir(Personagem personagem) {
        if (personagem != null) {
            ArrayList<Personagem> personagens = lerLista();
            personagens.removeIf(item -> item.getId() == personagem.getId());
            salvarLista(personagens);
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Personagem> lerLista() {
        try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Personagem>) leitor.readObject();
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo de personagens nao encontrado. Nao ha dados cadastrados.");
            return new ArrayList<>();
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo de personagens. Nao ha dados carregados.");
            return new ArrayList<>();
        } catch (ClassNotFoundException erro) {
            System.out.println("Classe dos personagens nao encontrada. Nao ha dados carregados.");
            return new ArrayList<>();
        }
    }

    private void salvarLista(ArrayList<Personagem> lista) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            escritor.writeObject(lista);
        } catch (IOException erro) {
            System.out.println("Erro ao salvar arquivo de personagens.");
        }
    }

    private int proximoId(ArrayList<Personagem> personagens) {
        int proximoId = 1;

        for (Personagem personagem : personagens) {
            if (personagem.getId() >= proximoId) {
                proximoId = personagem.getId() + 1;
            }
        }

        return proximoId;
    }
}
