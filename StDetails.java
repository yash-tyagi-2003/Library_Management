

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class StDetails implements ActionListener{
    JFrame jf;
    JPanel jp;
    JTextField info;
    JLabel labname, labid, labrno, labcrs, labyr,issuc;
    JButton issue, status, issub,cancel;
    long sid=0;
    int temp;
    StDetails(String data[]) {
        
        sid=Long.parseLong(data[0]);
        issuc=new JLabel("Book has been Issued to "+data[0]+" - "+data[1]+" Successfully!!!", null, 0);
        issub=new JButton("Issue Book", null);
        cancel=new JButton("CANCEL", null);
        info=new JTextField(20);
        labname = new JLabel("Name: " + data[1]);
        labrno = new JLabel("University Roll Number: " + data[2]);
        labcrs = new JLabel("Course: " + data[3]);
        labyr = new JLabel("Year: " + data[4]);
        temp=Integer.parseInt(data[4]);
        jp = new JPanel(new GridBagLayout());
        jp.setBorder(new EmptyBorder(100, 100, 100, 100));

        issue = new JButton("ISSUE");
        status = new JButton("STATUS");
        
        issue.addActionListener(this);
        status.addActionListener(this);
        cancel.addActionListener(this);
        issub.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx=50;
        gbc.ipady=30;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        jp.add(labname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jp.add(labrno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jp.add(labcrs, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        jp.add(labyr, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        jp.add(info, gbc);
        info.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        jp.add(cancel,gbc);
        jp.add(issue, gbc);
        cancel.setVisible(false);

        gbc.gridx = 1;
        gbc.gridy = 4;
        jp.add(status, gbc);
        jp.add(issub, gbc);
        issub.setVisible(false);

        gbc.gridwidth=3;
        gbc.gridx = 0;
        gbc.gridy = 5;
        jp.add(issuc, gbc);
        issuc.setVisible(false);

        jf = new JFrame("Student Details");
        jf.add(jp);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(900, 500);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String s=ae.getActionCommand();
        if(s=="ISSUE")
        {
            labname.setVisible(false);
            labcrs.setVisible(false);
            labrno.setVisible(false);
            issue.setVisible(false);
            status.setVisible(false);
            cancel.setVisible(true);
            issub.setVisible(true);
            labyr.setText("Enter Book ID : ");
            info.setVisible(true);
        }
        else if(s=="Issue Book")
        {
            String x=info.getText();
            String[] books = x.split(" ");
            long[] ids = new long[books.length];

            for (int i = 0; i < books.length; i++) {
                ids[i] = Long.parseLong(books[i]);
            }
            Main.obj.issueBook(ids,sid);
            issuc.setVisible(true);
        }
        else if(s=="STATUS")
        {
            new St_Status(sid);
        }
        else if(s=="CANCEL")
        {
            labname.setVisible(true);
            labcrs.setVisible(true);
            labrno.setVisible(true);
            issue.setVisible(true);
            status.setVisible(true);
            cancel.setVisible(false);
            issub.setVisible(false);
            labyr.setText("Year : "+temp);
            info.setVisible(false);
            issuc.setVisible(false);
        }
    }

}
