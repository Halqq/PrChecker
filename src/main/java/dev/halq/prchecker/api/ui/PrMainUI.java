package dev.halq.prchecker.api.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.halq.prchecker.Main;
import dev.halq.prchecker.impl.CheckJar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Halq
 * @since 27/10/2022 at 22:32
 */

public class PrMainUI {

    static JFrame frame;
    static JPanel mainP, p1, p2, p3, p4, p5;
    static JLabel fileabel, versionLabel;
    static JButton checker, fileChooser;
    static JCheckBox encrypt, url, webConnect;
    static JTextField file;
    static Font font2 = new Font("Ariel", Font.BOLD, 9);
    static JTextArea textArea;


    public static void mainUI(){
        FlatDarculaLaf.setup();

        frame = new JFrame("PRChecker");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mainP = new JPanel();

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();

        fileabel = new JLabel("File: ");
        fileChooser = new JButton("...");
        file = new JTextField(30);

        p1.add(fileabel);
        p1.add(file);
        p1.add(fileChooser);
        fileChooser.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            file.setText(f.getAbsolutePath());
        });

        p1.setBounds(0, 20, 500, 50);

        url = new JCheckBox("URLChecker ");
        encrypt = new JCheckBox("EncryptChecker ");
        webConnect = new JCheckBox("WebConnectChecker ");
        p2.add(url);
        p2.add(encrypt);
        p2.add(webConnect);
        p2.setBounds(140, 100, 500, 100);

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        textArea.setFont(font2);

        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        };

        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));

        p3.add(textArea);
        p3.setBounds(-50, 186, 900, 160);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBounds(90, 170, 630, 230);

        frame.add(scroll);
        frame.add(p3);

        checker = new JButton("Check");
        p4.add(checker);
        checker.addActionListener(e -> {
            if(url.isSelected()){
                CheckJar.checkUrl(new File(file.getText()));
            }
            if(encrypt.isSelected()){
                CheckJar.checkEncrypt(new File(file.getText()));
            }
            if(webConnect.isSelected()){
                CheckJar.checkConnection(new File(file.getText()));
            }
        });

        p4.setBounds(340, 400, 100, 100);

        versionLabel = new JLabel("Version: " + Main.version);
        p5.add(versionLabel);
        p5.setBounds(0, 410, 100, 100);

        frame.add(p1);
        frame.add(p2);
        frame.add(p4);
        frame.add(p5);

        mainP.setPreferredSize(new Dimension(800, 450));
        frame.add(mainP, BorderLayout.CENTER);
        frame.setLocation(150, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();

        frame.setVisible(true);
    }
}
