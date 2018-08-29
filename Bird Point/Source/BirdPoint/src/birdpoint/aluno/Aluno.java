/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.aluno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

/**
 *
 * @author Adriano Lima
 */
@Entity
public class Aluno {

    @Id
    @GeneratedValue
    private int idAluno;

    @Column(name = "codigoAluno", length = 10, nullable = false)
    private int codigo;

    @Column
    @Lob
    private byte[] digitalDireita;

    @Column
    @Lob
    private byte[] digitalEsquerda;

    @Column
    @Lob
    private byte[] foto;
    
    @Column(name = "nomeAluno")
    private String nome;

    /**
     * @return the idAluno
     */
    public int getIdAluno() {
        return idAluno;
    }

    /**
     * @param idAluno the idAluno to set
     */
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    /**
     * @return the codigoAluno
     */
    public int getCodigoAluno() {
        return codigo;
    }

    /**
     * @param codigoAluno the codigoAluno to set
     */
    public void setCodigoAluno(int codigoAluno) {
        this.codigo = codigoAluno;
    }

    /**
     * @return the digitalDireita
     */
    public byte[] getDigitalDireita() {
        return digitalDireita;
    }

    /**
     * @param digitalDireita the digitalDireita to set
     */
    public void setDigitalDireita(byte[] digitalDireita) {
        this.digitalDireita = digitalDireita;
    }

    /**
     * @return the digitalEsquerda
     */
    public byte[] getDigitalEsquerda() {
        return digitalEsquerda;
    }

    /**
     * @param digitalEsquerda the digitalEsquerda to set
     */
    public void setDigitalEsquerda(byte[] digitalEsquerda) {
        this.digitalEsquerda = digitalEsquerda;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    /**
     * @return the nomeAluno
     */
    public String getNomeAluno() {
        return nome;
    }

    /**
     * @param nomeAluno the nomeAluno to set
     */
    public void setNomeAluno(String nomeAluno) {
        this.nome = nomeAluno;
    }

}
