

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class Bk_Status extends MouseAdapter implements ActionListener{
    Object[][] data;
    int click;
    JTable table;
    JButton ret;
    double sum=0.0d;
    JFrame frame;
    Bk_Status()
    {
        JLabel empty=new JLabel("No Books", null, click);
        frame = new JFrame("Library Book Status");
        frame.setLayout(new BorderLayout());
        ret=new JButton("ADD MORE", null);
        
        ret.setVisible(false);

        ret.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        data=Main.obj.bkData(data);
        if(data.length>1)
        {

            String names[]={"Book ID","Name","ISBN","Price","Quantity","Issued","Total Books"};

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

    
    public void mouseClicked(MouseEvent ae)
    {   
        click = table.rowAtPoint(ae.getPoint());
        ret.setText("Add More "+data[click][1]);
        ret.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        Main.obj.addQty((int)data[click][0]);
        frame.setVisible(false);
        frame.dispose();
        new Bk_Status();
    }
}
