
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class AddSt implements ActionListener{
    JFrame jf;
    JPanel jp;
    JLabel labname,labid,labrno,labcrs,labyr;
    JButton save,exit;
    JTextField txtname,txtid,txtrno,txtcrs,txtyr;
    AddSt()
    {
        labid=new JLabel("Student ID", null, 0);
        labrno=new JLabel("Student Roll Number", null, 0);
        labcrs=new JLabel("Student Course", null, 0);
        labyr=new JLabel("Course Year", null, 0);
        labname=new JLabel("Student Name", null, 0);
        
        txtname=new JTextField(20);
        txtid=new JTextField(10);
        txtrno=new JTextField(10);
        txtcrs=new JTextField(20);
        txtyr=new JTextField(2);

        save=new JButton("SAVE", null);
        exit=new JButton("EXIT", null);

        save.addActionListener(this);
        exit.addActionListener(this);

        jp=new JPanel(new GridBagLayout(), false);
        jp.setPreferredSize(new Dimension(400, 300));
        jf=new JFrame("REGISTER STUDENT", null);

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
        jp.add(labname, gbc);
        
        gbc.gridx = 1;
        jp.add(txtname, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        jp.add(labrno, gbc);
        
        gbc.gridx = 1;
        jp.add(txtrno, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        jp.add(labcrs, gbc);
        
        gbc.gridx = 1;
        jp.add(txtcrs, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        jp.add(labyr, gbc);
        
        gbc.gridx = 1;
        jp.add(txtyr, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        jp.add(exit, gbc);
        
        gbc.gridx = 1;
        jp.add(save, gbc);

        jp.setBorder(new EmptyBorder(30, 100, 30, 100));
        jf.add(jp);
        jf.setSize(900,500);
        jf.setVisible(true);;
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="SAVE")
        {
            Main.obj.addStudent(txtname.getText(), Long.parseLong(txtrno.getText()), Long.parseLong(txtid.getText()), txtcrs.getText(), Integer.parseInt(txtyr.getText()));
            save.setText("Student Added!!!");
        }
        else
        jf.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new AddSt();
            }
        });
    }
}
