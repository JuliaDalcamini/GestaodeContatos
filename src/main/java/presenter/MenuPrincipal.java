/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import view.ViewListagem;
import view.ViewManterContato;
import view.ViewMenuPrincipal;
/**
 *
 * @author Julia Dalcamini
 */
public class MenuPrincipal {
    private ViewMenuPrincipal view;
    
    public MenuPrincipal(){
        bootUp();
        view.setVisible(true);
    }
    
    private void bootUp(){
        ViewMenuPrincipal newview = view.newview;
        
        newview.getMenuCloseSystem().addActionListener((ActionEvent e) -> {
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        
        newview.getMenuListContact().addActionListener((ActionEvent e) -> {
            new ViewListagem().setVisible(true);
        });
        
        newview.getMenuNewContact().addActionListener((ActionEvent e) -> {
            new ViewManterContato().setVisible(true);
        });
    }    
}
