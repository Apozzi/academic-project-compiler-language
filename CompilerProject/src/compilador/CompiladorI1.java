package compilador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.UIManager;

/**
 *
 * @author casa
 */
public class CompiladorI1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        InterafaceFrame frame = new InterafaceFrame();
        frame.show();
        // TODO code application logic here
    }
    
}
