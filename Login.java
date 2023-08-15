
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Login implements ActionListener{
    JFrame jf;
    JPanel jp;
    JLabel labid,empty;
    JTextField txtid;
    JButton find,exit;
    Login()
    {
        jp=new JPanel(new GridBagLayout(), false);
        jf=new JFrame("Student Login", null);
        empty=new JLabel("Student Data Not Found!!!");
        labid=new JLabel("Enter Student ID : ", null, 0);
        txtid=new JTextField(10);
        find=new JButton("SEARCH", null);
        exit=new JButton("EXIT", null);

        find.addActionListener(this);
        exit.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 50;
        gbc.ipady = 30;

        gbc.gridx = 0;
        gbc.gridy = 0;
        jp.add(labid, gbc);

        gbc.gridx = 1;
        jp.add(txtid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        jp.add(exit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        jp.add(find, gbc);

        gbc.gridy = 3; 
        gbc.gridwidth=2;
        jp.add(empty, gbc);
        empty.setVisible(false);

        jp.setBorder(new EmptyBorder(100, 100, 100, 100));
        jf.add(jp);
        jf.setVisible(true);
        jf.setSize(900, 500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="SEARCH")
        {
            String data[]=new String[5];
            data[0]=txtid.getText();
            Main.obj.searchStudent(Long.parseLong(txtid.getText()), data);
            if(data[1]==null)
            {
                empty.setVisible(true);
            }
            else
            {
                jf.dispose();
                new StDetails(data);
            }
        }
        else
        jf.dispose();
    }
}
