
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class MainCell implements ActionListener
{
    JFrame jf;
    JPanel jp;
    JButton adst,login,adbk,stat_book,admlout;
    JLabel wel;
    MainCell(String adm)
    {
        jf=new JFrame("Library Management System", null);
        jp=new JPanel(new GridBagLayout());
        adbk=new JButton("Add Book", null);
        stat_book=new JButton("Book Status", null);
        adst=new JButton("Add Student", null);
        login=new JButton("Log In", null);
        admlout=new JButton("Admin Log Out", null);
        wel=new JLabel("<html>Welcome to Library Management<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Admin : "+adm+"</html>", null, 0);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 50; 
        gbc.ipady = 30; 

        adst.addActionListener(this);
        login.addActionListener(this);
        adbk.addActionListener(this);
        stat_book.addActionListener(this);
        admlout.addActionListener(this);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=5;
        jp.add(wel,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        jp.add(adst,gbc);

        gbc.gridx++;
        jp.add(login,gbc);

        gbc.gridx++;
        jp.add(adbk,gbc);

        gbc.gridx++;
        jp.add(stat_book,gbc);

        gbc.gridx++;
        jp.add(admlout,gbc);
        jf.add(jp);
        jf.setSize(900, 500);
        jf.setVisible(true);
        jf.setBackground(Color.DARK_GRAY);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="Add Student")
        {
            new AddSt();
        }
        else if(s=="Log In")
        {
            new Login();
        }
        else if(s=="Add Book")
        {
            new Add_Book();
        }
        else if(s=="Book Status")
        {
            new Bk_Status();
        }
        else if(s=="Admin Log Out")
        {
            jf.dispose();
        }
    }
}