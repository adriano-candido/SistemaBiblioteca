/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.aluno;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class AlunoTableModel extends AbstractTableModel {

    private List<Aluno> alunos = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Matrícula", "Digital Direita", "Digital Esquerda"};

    public AlunoTableModel(List<Aluno> aluno) {
        this.alunos = aluno;
    }

    @Override
    public int getRowCount() {
        return alunos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluno aluno = alunos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aluno.getIdAluno();

            case 1:
                return aluno.getNomeAluno();
            case 2:
                return aluno.getCodigoAluno();
            case 3:
                if (aluno.getDigitalDireita() != null) {
                    return "Sim";
                } else {
                    return "Não";
                }
            case 4:
                if (aluno.getDigitalEsquerda() != null) {
                    return "Sim";
                } else {
                    return "Não";
                }
        }
        return null;
    }

    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return colunas[0];
            case 1:
                return colunas[1];
            case 2:
                return colunas[2];
            case 3:
                return colunas[3];
            case 4:
                return colunas[4];

        }
        return null;
    }

}
