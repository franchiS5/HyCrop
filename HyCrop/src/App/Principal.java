package App;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


public class Principal extends JFrame {


 File unidades[];
 JComboBox combo;
 Object items[];
 JPanel pcentro;
 
 public Principal(){
  
  unidades = File.listRoots();
     
         pcentro=new JPanel();
       
  combo=new JComboBox();
  buscarUnidades();
  combo.addItemListener(new ItemListener(){


   @Override
   public void itemStateChanged(ItemEvent e) {
    if(combo.getSelectedIndex()!=-1){
    pcentro.removeAll();
    JPanel p=new JPanel();
    p.setLayout(new GridLayout(2,2));
    ImageIcon img=(ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(unidades[combo.getSelectedIndex()]);
    JLabel lnombre=new JLabel(""+items[combo.getSelectedIndex()],img,JLabel.CENTER);
    JLabel ldescripcion=new JLabel(FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[combo.getSelectedIndex()]));
    p.add(new JLabel("Nombre: "));
    p.add(lnombre);
    p.add(new JLabel("Descripcion: "));
    p.add(ldescripcion);
    pcentro.add(p);
    p.updateUI();
    p.repaint();
    repaint();
    }
   }
   
  });
  
  JPanel pboton=new JPanel();
  JButton bactualizar=new JButton("Actualizar");
  bactualizar.addActionListener(new ActionListener(){


   @Override
   public void actionPerformed(ActionEvent e) {
    buscarUnidades();
   }
   
  });
  pboton.add(bactualizar);
  
  add(pboton,BorderLayout.SOUTH);
  
  add(combo,BorderLayout.NORTH);
  add(pcentro);
 }
 
 public void buscarUnidades(){
  unidades = File.listRoots();
  Object it[]=new Object[unidades.length];
  for (int i=0;i<unidades.length;i++) {
         String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
         String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);
         if(FileSystemView.getFileSystemView().isFloppyDrive(unidades[i])){
          s1="Unidad de Disquete (A:)";
         }
         if(s1.equalsIgnoreCase("")){
          s1=s2;
         }
         it[i]=s1;
       } 
  items=it;
  combo.removeAllItems();
  for(int i=0;i<it.length;i++){
   combo.addItem(it[i]);
  }
 }
 
 public static void main(String arg[]){
  
  Principal p=new Principal();
  p.setVisible(true);
  p.setBounds(0,0,400,200);
  p.setDefaultCloseOperation(EXIT_ON_CLOSE);
  
 }
}