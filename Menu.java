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


public class Menu {



   public JMenuBar initMenu(JTextArea ta){

        JMenu file = new JMenu("File");
        JMenu preferences = new JMenu("Preferences");
        JMenu edit = new JMenu("Edit");

        JMenuItem save, open, theme, refactor, format, find;

        JMenuBar mb=new JMenuBar();


        save=new JMenuItem("Save");
        open=new JMenuItem("Open");
        theme=new JMenuItem("Theme");
        format = new JMenuItem("Format");
        find = new JMenuItem("Find");
        refactor= new JMenuItem("Refactor");

        file.add(save); file.add(open);
        preferences.add(theme);
        edit.add(refactor); edit.add(format); edit.add(find);

        mb.add(file);
        mb.add(preferences);
        mb.add(edit);

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                save(ta);
            }
        });

        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                open(ta);
            }
        });

        theme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                theme(ta);
            }
        });

        refactor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!(ta.getText().isEmpty())) {
                    refactor(ta);
                }else JOptionPane.showMessageDialog(null, "There is nothing to refactor!", null, JOptionPane.ERROR_MESSAGE);
            }
        });

       format.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                  format(ta);
           }
       });

return mb;

    }
    public void open(JTextArea ta){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileFilter filter = new FileNameExtensionFilter("Text File","txt");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        Object[] options1 = { "Yes", "No"};
        JPanel panel = new JPanel();
        panel.add(new JLabel("Are you sure you want to overwrite this file"));

        if (result == JFileChooser.APPROVE_OPTION) {

        	
           int results = JOptionPane.showOptionDialog(null, panel, "Overwrite",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options1, null);
        	
            if (results == JOptionPane.YES_OPTION){
            	
                File selectedFile = fileChooser.getSelectedFile();
                
                try {
                    BufferedReader br = new BufferedReader(new FileReader(selectedFile.toString()));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    
                    ta.setText(sb.toString());
                    
                }catch(Exception e){
                    System.out.print("ERROR");
                }
            }
        
       }
    }
    public void save(JTextArea ta){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(null);
        FileFilter filter = new FileNameExtensionFilter("Text File","txt");
        fileChooser.addChoosableFileFilter(filter);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                if(!fileToSave.exists()){
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave.toString()+".txt", false));
                   bw.write(ta.getText());
                    bw.newLine();
                    bw.flush();
                }else{
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave.toString(), false));
                    bw.write(ta.getText());
                    bw.newLine();
                    bw.flush();
                }
            } catch (Exception e) {
                System.out.print("Error");
            }
        }
    }

    public void theme(JTextArea ta){
        JFrame f = new JFrame();
        f.setSize(200, 200);

        JPanel panel = new JPanel();
        JButton dark = new JButton("Dark");
        JButton light = new JButton("Light");
        JButton makeTheme = new JButton("Make a Theme yourself");

        dark.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ta.setForeground(Color.WHITE);
                ta.setBackground(Color.BLACK);
            }
        });

        light.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ta.setForeground(Color.BLACK);
                ta.setBackground(Color.WHITE);
            }
        });

        makeTheme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.getContentPane().removeAll();
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                f.add(panel);

                JLabel l = new JLabel("Select background color");
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel l2 = new JLabel("Select foreground color");
                l2.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(l);

                String[] choices = { "Red", "Yellow", "Blue", "Cyan",
                        "Magenta", "Orange" };

                JComboBox bg = new JComboBox(choices);
                JComboBox fg = new JComboBox(choices);

                bg.setMaximumSize(bg.getPreferredSize());
                bg.setAlignmentX(Component.CENTER_ALIGNMENT);


                fg.setMaximumSize(fg.getPreferredSize());
                fg.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(bg);
                panel.add(l2);
                panel.add(fg);
                JButton btn = new JButton("OK");
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(btn);
                f.add(panel);
                f.setVisible(true);

                btn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String bgc = (String) bg.getSelectedItem();
                        String fgc = (String) fg.getSelectedItem();
                        if(bgc.equals("Red")){
                            ta.setBackground(Color.RED);
                        }else if(bgc.equals("Yellow")){
                            ta.setBackground(Color.YELLOW);
                        }else if(bgc.equals("Blue")){
                            ta.setBackground(Color.BLUE);
                        }else if(bgc.equals("Cyan")){
                            ta.setBackground(Color.CYAN);
                        }else if(bgc.equals("Magenta")){
                            ta.setBackground(Color.MAGENTA);
                        }else if(bgc.equals("Orange")){
                            ta.setBackground(Color.ORANGE);
                        }

                        if(fgc.equals("Red")){
                            ta.setForeground(Color.RED);
                        }else if(fgc.equals("Yellow")){
                            ta.setForeground(Color.YELLOW);
                        }else if(fgc.equals("Blue")){
                            ta.setForeground(Color.BLUE);
                        }else if(fgc.equals("Cyan")){
                            ta.setForeground(Color.CYAN);
                        }else if(fgc.equals("Magenta")){
                            ta.setForeground(Color.MAGENTA);
                        }else if(fgc.equals("Orange")){
                            ta.setForeground(Color.ORANGE);
                        }
                    }
                });
            }
        });
        panel.add(dark);
        panel.add(light);
        panel.add(makeTheme);
        f.add(panel);
        f.setVisible(true);
    }

    public void refactor(JTextArea ta){
       JFrame f = new JFrame();
       f.setSize(300, 200);
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel l = new JLabel("Select a word to replace");
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel l2 = new JLabel("Select a word to replace it with");
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField tf = new JTextField();
        tf.setSize(200, 25);
        tf.setMaximumSize(tf.getSize());
        tf.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField tf2 = new JTextField();
        tf2.setSize(200, 25);
        tf2.setMaximumSize(tf2.getSize());
        tf2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btn = new JButton("OK");

        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String word = tf.getText();
                String word2 = tf2.getText();
                String text = ta.getText();
                if(text.contains(word)) {
                    text = text.replaceAll(word, word2);
                    ta.setText(text);
                    f.dispose();
                }else JOptionPane.showMessageDialog(null, "Word not found", null, JOptionPane.ERROR_MESSAGE);
            }
        });
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(l);
        panel.add(tf);
        panel.add(l2);
        panel.add(tf2);
        panel.add(btn);
        f.add(panel);
       f.setVisible(true);

    }

    public void format(JTextArea ta){
       JFrame f = new JFrame();
       f.setSize(300, 200);
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       JButton bold = new JButton("Bold");
       JButton size = new JButton("Size");
       JButton underline = new JButton("Underline");

       bold.setAlignmentX(Component.CENTER_ALIGNMENT);
       size.setAlignmentX(Component.CENTER_ALIGNMENT);
       underline.setAlignmentX(Component.CENTER_ALIGNMENT);

       panel.add(bold);
       panel.add(size);
       panel.add(underline);

        bold.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ta.setFont(new Font(ta.getFont().getName(), Font.BOLD, ta.getFont().getSize()));
            }
        });
        System.out.println(ta.getFont());
        size.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ta.setFont(new Font(ta.getFont().getName(), ta.getFont().getStyle(),  Integer.parseInt(JOptionPane.showInputDialog("Enter a size"))));
        }});



        f.add(panel);
         f.setVisible(true);

    }
    }



