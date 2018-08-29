/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.professor;

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
public class Professor {

    @Id
    @GeneratedValue
    private int idProfessor;

    @Column(length = 10, nullable = false)
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

    private String nome;

    private String cpf;

    /**
     * @return the idProfessor
     */
    public int getIdProfessor() {
        return idProfessor;
    }

    /**
     * @param idProfessor the idProfessor to set
     */
    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
