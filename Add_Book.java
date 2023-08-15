
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Add_Book implements ActionListener{
    JFrame jf;
    JPanel jp;
    JLabel labname,labid,labisbn,labqty,labprice;
    JButton save,exit;
    JTextField txtname,txtid,txtisbn,txtqty,txtprice;
    Add_Book()
    {
        labid=new JLabel("Book ID : ", null, 0);
        labname=new JLabel("Book Name : ", null, 0);
        labisbn=new JLabel("ISBN Number : ", null, 0);
        labqty=new JLabel("Quantity : ", null, 0);
        labprice=new JLabel("Price : ", null, 0);
        
        txtname=new JTextField(20);
        txtid=new JTextField(10);
        txtisbn=new JTextField(10);
        txtqty=new JTextField(20);
        txtprice=new JTextField(2);

        save=new JButton("ADD", null);
        exit=new JButton("EXIT", null);

        save.addActionListener(this);
        exit.addActionListener(this);

        jp=new JPanel(new GridBagLayout(), false);
        jp.setPreferredSize(new Dimension(400, 300));
        jf=new JFrame("ADD NEW BOOK", null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(7, 7, 7, 7);
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
        jp.add(labisbn, gbc);
        
        gbc.gridx = 1;
        jp.add(txtisbn, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        jp.add(labqty, gbc);
        
        gbc.gridx = 1;
        jp.add(txtqty, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        jp.add(labprice, gbc);
        
        gbc.gridx = 1;
        jp.add(txtprice, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        jp.add(exit, gbc);
        
        gbc.gridx = 1;
        jp.add(save, gbc);

        jf.add(jp);
        jf.setSize(900,500);
        jf.setVisible(true);;
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="ADD")
        {
            Main.obj.addBook( Long.parseLong(txtid.getText()), txtname.getText(), Long.parseLong(txtisbn.getText()), Integer.parseInt(txtqty.getText()), Double.parseDouble(txtprice.getText()));
            save.setText("ADD MORE");
            exit.setText("Book Added!!!");
            exit.setEnabled(false);
        }
        else if(s=="ADD MORE")
        {
            save.setText("SAVE");
            exit.setEnabled(true);
            exit.setText("EXIT");
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
