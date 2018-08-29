/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.professor.ProfessorTableModel;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Util;
import birdpoint.webcam.CapturarFoto;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class CadastroProfessor extends javax.swing.JDialog {

    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();

    boolean cadastroPermitido = false;

    LeitorBiometrico digital = new LeitorBiometrico();

    //Variável pra armazenar os templates de digital
    DPFPTemplate templateDigital;

    public CadastroProfessor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btLimparActionPerformed(null);
    }

    private void capturarDigitalMaoDireita() {
        DPFPTemplate temp = digital.getTemplate(null, 1);
        byte[] b;
        try {
            b = temp.serialize();
            professor.setDigitalDireita(b);
            btMaoDireita.setEnabled(false);
        } catch (Exception e) {
        }
    }

    private void capturarDigitalMaoEsquerda() {
        DPFPTemplate temp = digital.getTemplate(null, 1);
        byte[] b;
        try {
            b = temp.serialize();
            professor.setDigitalEsquerda(b);
            btMaoEsquerda.setEnabled(false);
        } catch (Exception e) {
        }
    }

    private Professor carregarProfessorGennera() throws UnirestException {
        Professor professorRest = null;
        try {
            Gson gson = new Gson();
            professorRest = gson.fromJson(professorDAO.getREST("https://api.gennera.com.br/service/professor/" + tfCodigo.getText()), Professor.class);
            tfCodigo.setEnabled(false);
            btMaoDireita.setEnabled(true);
            btMaoEsquerda.setEnabled(true);
            btCarregar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível carregar o professor da base Gennera! \n\nOs motivos que poderiam ter levado a isso são:"
                    + "\n- Código incorreto;"
                    + "\n- Professor não cadastrado;"
                    + "\n- Internet instável.");
            tfCodigo.setEnabled(true);
        }

        return professorRest;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selecionarFoto = new javax.swing.JFileChooser();
        jSpinner1 = new javax.swing.JSpinner();
        btVoltar = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        tfCodigo = new javax.swing.JTextField();
        jLObrigatorioNome = new javax.swing.JLabel();
        btFoto = new javax.swing.JButton();
        btCarregar = new javax.swing.JButton();
        btMaoDireita = new javax.swing.JButton();
        btMaoEsquerda = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        tfNome = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(602, 412));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.setActionCommand("");
        btVoltar.setContentAreaFilled(false);
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar);
        btVoltar.setBounds(0, 330, 90, 70);

        btLimpar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar.png"))); // NOI18N
        btLimpar.setText("Limpar");
        btLimpar.setActionCommand("");
        btLimpar.setContentAreaFilled(false);
        btLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLimpar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLimpar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btLimpar);
        btLimpar.setBounds(90, 330, 80, 70);

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setActionCommand("");
        btSalvar.setContentAreaFilled(false);
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvar);
        btSalvar.setBounds(330, 330, 80, 70);

        tfCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCodigoFocusLost(evt);
            }
        });
        tfCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfCodigoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfCodigoMouseReleased(evt);
            }
        });
        tfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCodigoActionPerformed(evt);
            }
        });
        tfCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfCodigoKeyPressed(evt);
            }
        });
        getContentPane().add(tfCodigo);
        tfCodigo.setBounds(160, 100, 110, 23);

        jLObrigatorioNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioNome.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioNome.setText("*");
        getContentPane().add(jLObrigatorioNome);
        jLObrigatorioNome.setBounds(270, 100, 10, 20);

        btFoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg"))); // NOI18N
        btFoto.setContentAreaFilled(false);
        btFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFoto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFotoActionPerformed(evt);
            }
        });
        getContentPane().add(btFoto);
        btFoto.setBounds(20, 130, 120, 150);

        btCarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCarregar.setContentAreaFilled(false);
        btCarregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarActionPerformed(evt);
            }
        });
        getContentPane().add(btCarregar);
        btCarregar.setBounds(270, 100, 40, 20);

        btMaoDireita.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btMaoDireita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/imgBiometria.png"))); // NOI18N
        btMaoDireita.setText("Mão Direita");
        btMaoDireita.setActionCommand("");
        btMaoDireita.setContentAreaFilled(false);
        btMaoDireita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMaoDireita.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMaoDireita.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMaoDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaoDireitaActionPerformed(evt);
            }
        });
        getContentPane().add(btMaoDireita);
        btMaoDireita.setBounds(460, 100, 120, 130);

        btMaoEsquerda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btMaoEsquerda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/imgBiometria.png"))); // NOI18N
        btMaoEsquerda.setText("Mão Esquerda");
        btMaoEsquerda.setActionCommand("");
        btMaoEsquerda.setContentAreaFilled(false);
        btMaoEsquerda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMaoEsquerda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMaoEsquerda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMaoEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMaoEsquerdaActionPerformed(evt);
            }
        });
        getContentPane().add(btMaoEsquerda);
        btMaoEsquerda.setBounds(450, 250, 140, 130);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Código Gennera.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 100, 130, 20);

        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setContentAreaFilled(false);
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btPesquisar);
        btPesquisar.setBounds(190, 330, 99, 70);

        tfNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfNome);
        tfNome.setBounds(150, 180, 310, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nome.:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(150, 160, 80, 20);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadBiometria1.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlCadProfessores.setMaximumSize(new java.awt.Dimension(600, 412));
        jlCadProfessores.setMinimumSize(new java.awt.Dimension(600, 412));
        jlCadProfessores.setPreferredSize(new java.awt.Dimension(600, 412));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        Util.limparCamposGenerico(this);
        professor = new Professor();
        tfCodigo.setEnabled(true);
        tfNome.setText("");
        tfCodigo.setText("");
        btMaoDireita.setEnabled(false);
        btMaoEsquerda.setEnabled(false);
        btCarregar.setEnabled(true);
        btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
    }//GEN-LAST:event_btLimparActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(String.valueOf(tfCodigo.getText()))) {
            if (String.valueOf(professor.getCodigo()).equalsIgnoreCase(tfCodigo.getText())) {
                professorDAO.salvar(professor);
                btLimparActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(this, "O código informado não pertence a esse professor. Clique no botão carregar dados para sincronizar a informação.");
            }
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoActionPerformed

    private void btFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFotoActionPerformed
        try {
            Icon captura = CapturarFoto.exibeTela();
            Image image = Util.iconToImage(captura, 120, 140);
            ImageIcon redimencionarIMG = new ImageIcon(image);
            redimencionarIMG.setImage(image.getScaledInstance(120, 140, 100));
            professor.setFoto(Util.imageToByte(redimencionarIMG.getImage()));
            btFoto.setIcon(redimencionarIMG);
            professor.setFoto(Util.imageToByte(redimencionarIMG.getImage()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Não foi possível carregar essa imagem",
                    "Erro ao carregar imagem", JOptionPane.ERROR_MESSAGE);
            btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
        }
    }//GEN-LAST:event_btFotoActionPerformed

    private void btCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarActionPerformed
        try {
            if (!tfCodigo.getText().isEmpty()) {
                if (professorDAO.checkExistsMatricula("codigo", Integer.parseInt(tfCodigo.getText())).isEmpty() || cadastroPermitido) {
                    professor = carregarProfessorGennera();
                    if (professor != null) {
                        tfNome.setText(professor.getNome());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "O código: " + tfCodigo.getText()
                            + " já está cadastrado!\n\nCaso necessite realizar alguma alteração, utilize a função de pesquisar.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Digite o código!");
            }
        } catch (UnirestException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível carregar o professor da base Gennera! \n\nOs motivos que poderiam ter levado a isso são:"
                    + "\n- Código incorreto;"
                    + "\n- Professor não cadastrado;"
                    + "\n- Internet instável.");
        }

    }//GEN-LAST:event_btCarregarActionPerformed

    private void btMaoDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaoDireitaActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Peça que por gentileza o(a) professor(a): " + professor.getNome() + "\n\n Escolha um dedo da mão DIREITA que ele(a) gostaria de cadastrar\n\n"
                + "Após a escolha dele clique no OK abaixo para continuar o cadastro da digital");
        capturarDigitalMaoDireita();
    }//GEN-LAST:event_btMaoDireitaActionPerformed

    private void btMaoEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMaoEsquerdaActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Peça que por gentileza o(a) professor(a): " + professor.getNome() + "\n\n Escolha um dedo da mão ESQUERDA que ele(a) gostaria de cadastrar\n\n"
                + "Após a escolha dele clique no OK abaixo para continuar o cadastro da digital");
        capturarDigitalMaoEsquerda();
    }//GEN-LAST:event_btMaoEsquerdaActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        List<Professor> lista;
        lista = (professorDAO.listar());
        ProfessorTableModel itm = new ProfessorTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Digital");
        if (objetoRetorno != null) {
            Professor professor = professorDAO.consultarObjetoId("idProfessor", objetoRetorno);
            tfCodigo.setText(String.valueOf(professor.getCodigo()));
            cadastroPermitido = true;
            btCarregarActionPerformed(null);
            this.professor.setDigitalDireita(professor.getDigitalDireita());
            this.professor.setDigitalEsquerda(professor.getDigitalEsquerda());
            this.professor.setFoto(professor.getFoto());
            this.professor.setIdProfessor(professor.getIdProfessor());
            try {
                ImageIcon foto = new ImageIcon();
                foto.setImage(Util.byteToImage(professor.getFoto()));
                btFoto.setIcon(foto);
            } catch (Exception e) {
            }
            btMaoDireita.setEnabled(true);
            btMaoEsquerda.setEnabled(true);
            btCarregar.setEnabled(false);
            tfCodigo.setEnabled(false);
            cadastroPermitido = false;
        }
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tfCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoMousePressed

    private void tfCodigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMouseReleased

    }//GEN-LAST:event_tfCodigoMouseReleased

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost

    }//GEN-LAST:event_tfCodigoFocusLost

    private void tfCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btCarregar.doClick();
        }
    }//GEN-LAST:event_tfCodigoKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProfessor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroProfessor dialog = new CadastroProfessor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCarregar;
    private javax.swing.JButton btFoto;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btMaoDireita;
    private javax.swing.JButton btMaoEsquerda;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLObrigatorioNome;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JLabel tfNome;
    // End of variables declaration//GEN-END:variables
}
