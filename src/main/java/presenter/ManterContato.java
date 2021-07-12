/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ModelContato;
import util.ConexaoSQLite;
import view.ViewListagem;
import view.ViewManterContato;

/**
 *
 * @author Julia Dalcamini
 */
public class ManterContato extends ConexaoSQLite {
    ViewManterContato view;
    Listagem listagem = new Listagem();
    
     public ManterContato(){
        bootUp();
        view.setVisible(true);
    }
    
    private void bootUp(){
        ViewManterContato newview = view.newview;
        
        newview.getBtnClose().addActionListener((ActionEvent e) -> {
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        
        newview.getBtnSave().addActionListener((ActionEvent e) -> {
            SaveContact();
        });
    }
    
    public boolean AddContact(ModelContato pModelContato) {
        ModelContato modelContato = new ModelContato();
        conectar();
        String sql = "INSERT INTO tbl_contato ("
                + "Nome, "
                + "Telefone) "
                + "VALUES ()";
        PreparedStatement preparedStatement = criarPreparedStatement (sql, Statement.RETURN_GENERATED_KEYS);
        try{
            preparedStatement.setInt(1, pModelContato.getId());
            preparedStatement.setString(2, pModelContato.getNome());
            preparedStatement.setString(3, pModelContato.getTelefone());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManterContato.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return true;
    }
    
    /*public boolean DeleteContact(int pId){
        conectar();
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM tbl_contato WHERE IdContato = '"+pId"'";
        return true;
    }*/
    
    private void SaveContact(){
        ModelContato modelContato = new ModelContato();
        JTextField TxtName = view.getTxtName();
        JFormattedTextField TxtPhone = view.getTxtPhone();
        modelContato.setNome(TxtName.getText());
        modelContato.setTelefone(TxtPhone.getText());
        if (AddContact(modelContato)) {
            JOptionPane.showMessageDialog(view, "Contato salvo com sucesso!", "Salvo!", JOptionPane.INFORMATION_MESSAGE);
            //LoadContacts();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao salvar.", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
