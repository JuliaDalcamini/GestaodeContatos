/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import util.ConexaoSQLite;
import model.ModelContato;
import view.ViewListagem;
/**
 *
 * @author Julia Dalcamini
 */
public class Listagem extends ConexaoSQLite{
    ModelContato modelContato = new ModelContato();
    List<ModelContato> ContactList = new ArrayList<>();
    ViewListagem view;
    
    public Listagem(){
        bootUp();
        view.setVisible(true);
    }
    
    private void bootUp(){
        ViewListagem newview = view.newview;
        
        newview.getBtnClose().addActionListener((ActionEvent e) -> {
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        
        newview.getBtnDelete().addActionListener((ActionEvent e) -> {
            new ViewListagem().setVisible(true);
        });
        
        newview.getBtnView().addActionListener((ActionEvent e) -> {
            new ViewListagem().setVisible(true);
        });
    }
    
    public void LoadContacts(){
        ContactList = getListContact();
        JTable TblContacts = view.getTblContacts();
        DefaultTableModel table = (DefaultTableModel) TblContacts.getModel();
        table.setNumRows(0);
        for (int i = 0; i < ContactList.size(); i++){
            table.addRow(new Object[]{
               ContactList.get(i).getId(),
               ContactList.get(i).getNome(),
               ContactList.get(i).getTelefone()
            });
        }
    }
    
    public List<ModelContato> getListContact(){
        ModelContato modelContato = new ModelContato();
        conectar();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT IdContato, "
                + "Nome, "
                + "Telefone "
                + "FROM Contatos";
        try {
            preparedStatement = criarPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                modelContato = new ModelContato();
                modelContato.setId(resultSet.getInt(1));
                modelContato.setNome(resultSet.getString(2));
                modelContato.setTelefone(resultSet.getString(3));
                ContactList.add(modelContato);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        desconectar();
        return ContactList;
    }
}
