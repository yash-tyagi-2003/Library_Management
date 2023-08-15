

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class St_Status extends MouseAdapter implements ActionListener{
    long id;
    Object[][] data;
    int click;
    JTable table;
    JButton ret;
    double sum=0.0d;
    JFrame frame;
    St_Status(long sid)
    {
        id=sid;
        JLabel empty=new JLabel("No Issued Books", null, click);
        frame = new JFrame("Books Issued By Student");
        frame.setLayout(new BorderLayout());
        ret=new JButton("Return Selected Book", null);
        ret.setVisible(false);

        ret.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        data=Main.obj.getIssued(sid,data);
        if(data.length>1)
        {
            calcfine();

            String names[]={"Book ID","Name","Price","Date of Issue","Fine"};

            DefaultTableModel model = new DefaultTableModel(data, names);

            table = new JTable(model);

            table.addMouseListener(this);

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(ret, BorderLayout.SOUTH);
        }
        else
        frame.add(empty,BorderLayout.CENTER);
        frame.setSize(900, 500);
        frame.setVisible(true);
    }

    public void calcfine()
    {
        LocalDate date2 = LocalDate.now();
        for(int i=0;i<data.length-1;i++)
        {
            LocalDate date1 = LocalDate.parse(data[i][3].toString());
            long daysDifference = ChronoUnit.DAYS.between(date1, date2);
            if(daysDifference>30)
            data[i][4]=(10/100)*(double)data[i][2];
            else
            data[i][4]=0.0d;
            sum+=(double)data[i][4];
        }
        data[data.length-1][4]="Total Fine = "+sum;
    }

    public void mouseClicked(MouseEvent ae)
    {   
        click = table.rowAtPoint(ae.getPoint());
        ret.setText("Return "+data[click][1]);
        ret.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        Main.obj.ret_book(id, (int)data[click][0]);
        frame.setVisible(false);
        frame.dispose();
        new St_Status(id);
    }
}
