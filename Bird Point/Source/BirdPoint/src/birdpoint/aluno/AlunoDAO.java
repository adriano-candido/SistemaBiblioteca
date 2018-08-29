/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.aluno;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class AlunoDAO extends GenericDAO<Aluno> {

    public AlunoDAO() {
        super(Aluno.class);
    }

    public void salvar(Aluno aluno) {
        Object[] options = {"Sim", "Não"};
        if (aluno.getIdAluno() == 0) {
            if (adicionar(aluno)) {
                JOptionPane.showMessageDialog(null, "Aluno(a) cadastrado(a) com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(aluno)) {
                JOptionPane.showMessageDialog(null, "Aluno(a) editado(a) com sucesso!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

}
