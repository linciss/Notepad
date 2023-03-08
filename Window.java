import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame{

    public JTextArea ta = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(ta);
    Menu menu = new Menu();
    public Window(){
        JFrame f = new JFrame();
        f.setSize(600, 600);
        ta.setLineWrap(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.add(scrollPane);
        f.setJMenuBar(menu.initMenu(ta));
        f.setVisible(true);
    }


}

