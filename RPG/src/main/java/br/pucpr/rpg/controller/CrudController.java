package br.pucpr.rpg.controller;

import br.pucpr.rpg.model.Entidade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CrudController<T extends Entidade> {

    private String arquivo;

    public CrudController(String arquivo) {
        this.arquivo = arquivo;
    }

    public ArrayList<T> listar() {
        return lerLista();
    }

    public void cadastrar(T item) {
        ArrayList<T> lista = lerLista();

        item.setId(proximoId(lista));
        lista.add(item);

        salvarLista(lista);
    }

    public void editar(T itemAtualizado) {
        if (itemAtualizado != null) {
            ArrayList<T> lista = lerLista();

            for (int i = 0; i < lista.size(); i++) {
                T itemDaLista = lista.get(i);

                if (itemDaLista.getId() == itemAtualizado.getId()) {
                    lista.set(i, itemAtualizado);
                    break;
                }
            }

            salvarLista(lista);
        }
    }

    public void excluir(T item) {
        if (item != null) {
            ArrayList<T> lista = lerLista();

            for (int i = 0; i < lista.size(); i++) {
                T itemDaLista = lista.get(i);

                if (itemDaLista.getId() == item.getId()) {
                    lista.remove(i);
                    break;
                }
            }

            salvarLista(lista);
        }
    }

    private ArrayList<T> lerLista() {
        try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<T>) leitor.readObject();
        } catch (FileNotFoundException erro) {
            return new ArrayList<T>();
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo " + arquivo + ".");
            return new ArrayList<T>();
        } catch (ClassNotFoundException erro) {
            System.out.println("Classe nao encontrada ao ler o arquivo " + arquivo + ".");
            return new ArrayList<T>();
        }
    }

    private void salvarLista(ArrayList<T> lista) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            escritor.writeObject(lista);
        } catch (IOException erro) {
            System.out.println("Erro ao salvar o arquivo " + arquivo + ".");
        }
    }

    private int proximoId(ArrayList<T> lista) {
        int proximoId = 1;

        for (int i = 0; i < lista.size(); i++) {
            T item = lista.get(i);

            if (item.getId() >= proximoId) {
                proximoId = item.getId() + 1;
            }
        }

        return proximoId;
    }
}