import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main implements ActionListener{
    static database obj;
    JFrame jf;
    JPanel jp;
    JLabel wel,user,pass;
    JTextField username;
    JPasswordField password;
    JButton login,exit;
    Main()
    {
        obj=new database("jdbc:mysql://localhost:3306/stdatabase","root","gautum1234");
        wel=new JLabel("<html>Welcome To Library Management<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start by Admin Login<html>", null, 0);
        user=new JLabel("Username : ", null, 0);
        pass=new JLabel("Password : ", null, 0);
        username=new JTextField(50);
        password=new JPasswordField(50);
        password.setEchoChar('*');
        login=new JButton("LOG IN", null);
        exit=new JButton("EXIT", null);
        jp=new JPanel(new GridBagLayout(), false);
        jf=new JFrame("Library Management System - Admin Login", null);

        login.addActionListener(this);
        exit.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 50;
        gbc.ipady = 30;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jp.add(wel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jp.add(user, gbc);

        gbc.gridx = 1;
        jp.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jp.add(pass, gbc);

        gbc.gridx = 1;
        jp.add(password, gbc);

        gbc.gridx=0;
        gbc.gridy = 3;
        jp.add(exit, gbc);

        gbc.gridx=1;
        gbc.gridy = 3;
        jp.add(login, gbc);
        jf.add(jp);
        jf.setVisible(true);
        jf.setSize(900, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="LOG IN")
        {
            String ret=obj.adm_login(username.getText(), new String(password.getPassword()));
            if(ret==null)
            {
                login.setText("TRY AGAIN");
                exit.setText("Admin Not Found!!!");
            }
            else
            {
                new MainCell(ret);
            }
        }
        else if(s=="TRY AGAIN")
        {
            login.setText("LOG IN");
            exit.setText("EXIT");
        }
        else
        System.exit(0);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new Main();
            }
        });
    }
}
