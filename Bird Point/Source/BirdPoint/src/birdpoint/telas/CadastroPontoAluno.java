/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.aluno.Aluno;
import birdpoint.aluno.AlunoDAO;
import birdpoint.professor.Professor;
import birdpoint.professor.ProfessorDAO;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Util;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Adriano
 */
public class CadastroPontoAluno extends javax.swing.JFrame {

    List<Aluno> listaAlunos;
    Aluno aluno = new Aluno();
    AlunoDAO alunoDAO = new AlunoDAO();

    List<Professor> listaProfessores;
    Professor professor = new Professor();
    ProfessorDAO professorDAO = new ProfessorDAO();
  

    boolean situacaoLeitor = true;

    LeitorBiometrico digital = new LeitorBiometrico();
    DPFPTemplate templateDigital = DPFPGlobal.getTemplateFactory().createTemplate();

    Thread ponto = new Thread(() -> {
        try {
            compararDigital();
        } catch (ParseException ex) {
            System.out.println("Erro");
        } catch (UnirestException ex) {
            System.out.println("Erro2");
        }
    });
    
    Thread telaCarregando = new Thread(() -> { 
        carregarDados();
    });

    /**
     * Creates new form CadastroPontoAluno
     */
    public CadastroPontoAluno() {
       initComponents();
       ponto.start();
       telaCarregando.start();
    }
    
    public void carregarDados(){
        int contador = 0;
        TelaAguardando aguarde = new TelaAguardando();
        aguarde.setVisible(true);
        listaAlunos = alunoDAO.listar();
        listaProfessores = professorDAO.listar();
        tfCodigo.grabFocus();
        aguarde.carregou = true;
        aguarde.dispose();
    }

    //este método ativará as teclas (Ctrl+C)
    public void funcaoCopiar(String texto) {
        final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            clip.setContents(new StringSelection(texto), null);
        } catch (Exception e) {
        }
        try {
            funcaoColar();
        } catch (AWTException ex) {
            Logger.getLogger(CadastroPontoAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void funcaoColar() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

    }

    public void registrarProfessor() throws UnirestException {
        if (professor != null) {
            alterarUsuario();
            tfNome.setText(professor.getNome());
            String codigoProfessor = "P00000" + professor.getCodigo();
            tfCodigo.setText(codigoProfessor);
            try {
                ImageIcon foto = new ImageIcon();
                foto.setImage(Util.byteToImage(professor.getFoto()));
                btFoto.setIcon(foto);
            } catch (Exception e) {
                btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
            }
            funcaoCopiar(codigoProfessor);
            tfImagem.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/certoMenor.png")));
        } else {
            btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
            tfImagem.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/naoEncontrado.png")));
            tfNome.setText("");
            tfCodigo.setText("");
        }
    }

    public void registrarAluno() throws UnirestException {
        if (aluno != null) {
            alterarUsuario();
            tfNome.setText(aluno.getNomeAluno());
            String matriculaBiblioteca;
            try {
                ImageIcon foto = new ImageIcon();
                foto.setImage(Util.byteToImage(aluno.getFoto()));
                btFoto.setIcon(foto);
            } catch (Exception e) {
                btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
            }
            if (aluno.getCodigoAluno() < 10000) {
                matriculaBiblioteca = "A0000" + aluno.getCodigoAluno();
            } else {
                matriculaBiblioteca = "A000" + aluno.getCodigoAluno();
            }
            tfCodigo.setText(matriculaBiblioteca);
            funcaoCopiar(matriculaBiblioteca);
            tfImagem.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/certoMenor.png")));
        } else {
            btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
            tfImagem.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/naoEncontrado.png")));
            tfNome.setText("");
            tfCodigo.setText("");
        }
    }

    // Este método compara a digital inserida no leitor
    private void compararDigital() throws ParseException, UnirestException {
        while (situacaoLeitor) {
            if (jcTipoUsuario.getSelectedItem().equals("Aluno")) {
                aluno = new Aluno();
                aluno = digital.verificarSeCadastrado(null, listaAlunos, tfImagem);
                registrarAluno();
            } else {
                btFoto.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg")));
                professor = new Professor();
                professor = digital.verificarSeCadastradoProf(null, listaProfessores, tfImagem);
                tfImagem.setIcon(new ImageIcon(getClass().getResource("/birdpoint/imagens/loading_bigMenor.gif")));
                registrarProfessor();
            }
        }
    }

    public void alterarUsuario() {
        if (jcTipoUsuario.getSelectedItem().equals("Aluno")) {
            tfTipoUsuario.setText("ALUNO");
        } else {
            tfTipoUsuario.setText("PROFESSOR");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btVoltar = new javax.swing.JButton();
        btFoto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tfNome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        tfTipoUsuario = new javax.swing.JLabel();
        tfImagem = new javax.swing.JLabel();
        jcTipoUsuario = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(602, 412));
        setSize(new java.awt.Dimension(602, 412));
        getContentPane().setLayout(null);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
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
        btVoltar.setBounds(60, 350, 90, 50);

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

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nome.:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(150, 150, 80, 20);

        tfNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(tfNome);
        tfNome.setBounds(150, 170, 310, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Usuário.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(370, 80, 130, 20);

        tfCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfCodigo.setEnabled(false);
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
        tfCodigo.setBounds(30, 100, 160, 23);

        tfTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfTipoUsuario.setText("ALUNO");
        getContentPane().add(tfTipoUsuario);
        tfTipoUsuario.setBounds(450, 20, 130, 30);

        tfImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/naoEncontrado.png"))); // NOI18N
        getContentPane().add(tfImagem);
        tfImagem.setBounds(540, 340, 50, 60);

        jcTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aluno", "Professor" }));
        jcTipoUsuario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        getContentPane().add(jcTipoUsuario);
        jcTipoUsuario.setBounds(370, 100, 210, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Código Biblioteca.:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 80, 130, 20);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/CadastroDePonto.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFotoActionPerformed

    }//GEN-LAST:event_btFotoActionPerformed

    private void tfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCodigoFocusLost

    }//GEN-LAST:event_tfCodigoFocusLost

    private void tfCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoMousePressed

    private void tfCodigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCodigoMouseReleased

    }//GEN-LAST:event_tfCodigoMouseReleased

    private void tfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoActionPerformed

    private void tfCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCodigoKeyPressed

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
            java.util.logging.Logger.getLogger(CadastroPontoAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroPontoAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFoto;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox jcTipoUsuario;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JLabel tfImagem;
    private javax.swing.JLabel tfNome;
    private javax.swing.JLabel tfTipoUsuario;
    // End of variables declaration//GEN-END:variables
}
