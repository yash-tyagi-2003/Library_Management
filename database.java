
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {
    private Connection connection;
    database(String url,String username,String password)
    {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String adm_login(String user,String pass)
    {
        String sql="SELECT name FROM admin WHERE username='"+user+"' AND pass='"+pass+"'";
        String ret=null;
        try(PreparedStatement statement=connection.prepareStatement(sql))
        {
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next())
                ret=resultSet.getString("name");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public void addStudent(String name,long rno,long id,String crs,int yr)
    {
        String sql="INSERT INTO students (id, name, rno, crs, yr) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameter values
            statement.setLong(1, id);
            statement.setString(2, name);
            statement.setLong(3, rno);
            statement.setString(4, crs);
            statement.setInt(5, yr);

            // Execute the insert statement
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchStudent(long id, String arr[])
    {
        String sql = "SELECT name,rno,crs,yr FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) 
        {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) 
            {
                int i=1;
                if (resultSet.next()) 
                {
                    arr[i++]=resultSet.getString("name");
                    arr[i++]=Long.toString(resultSet.getLong("rno"));
                    arr[i++]=resultSet.getString("crs");
                    arr[i++]=Integer.toString(resultSet.getInt("yr"));
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void addBook(long id,String name,long isbn,int qty,double price)
    {
        String sql="INSERT INTO books (id, name, isbn, qty, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.setString(2, name);
            statement.setLong(3, isbn);
            statement.setInt(4, qty);
            statement.setDouble(5, price);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook(long bid[],long sid)
    {
        String sql1 = "SELECT name,price FROM books WHERE id = ?";
        String sql="INSERT INTO issued (st_id,book_id,b_name,price,date_of_issue) VALUES ("+sid+", ?, ?, ?, CURRENT_DATE())";
        String sql2="UPDATE books SET qty = qty - 1, issqty=issqty+1 WHERE id=?;";
        try (PreparedStatement statement1 = connection.prepareStatement(sql1);PreparedStatement statement = connection.prepareStatement(sql);PreparedStatement statement2 = connection.prepareStatement(sql2)) {

            for(int i=0;i<bid.length;i++)
            {
                statement1.setLong(1, bid[i]);
                String x="";
                double y=0.0d;
                try (ResultSet resultSet = statement1.executeQuery()) 
                {
                    if (resultSet.next()) 
                    {
                        x=resultSet.getString("name");
                        y=resultSet.getDouble("price");
                    }
                }
                statement.setLong(1,bid[i]);
                statement.setString(2, x);
                statement.setDouble(3, y);
                statement2.setLong(1,bid[i]);
                statement.executeUpdate();
                statement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getIssued(long id,Object data[][]) {
        String sql = "SELECT book_id, b_name, price, date_of_issue FROM issued WHERE st_id = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            int row=0;
            while (resultSet.next()) 
            row+=1;
            data = new Object[row+1][5];
            row=0;
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                for (int col = 0; col < 4; col++) {
                    data[row][col] = resultSet.getObject(col + 1);
                }
                row++;
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public void ret_book(long sid,long bid)
    {
        String sql1="DELETE FROM issued WHERE st_id="+(int)sid+" AND book_id="+(int)bid;
        String sql2="UPDATE books SET qty=qty+1 WHERE id="+bid;
        try (PreparedStatement statement1 = connection.prepareStatement(sql1);PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] bkData(Object data[][]) {
        String sql = "SELECT * FROM books";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
            int row=0;
            while (resultSet.next()) 
            row+=1;
            data = new Object[row][7];
            row=0;
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                for (int col = 0; col < 6; col++) {
                    data[row][col] = resultSet.getObject(col + 1);
                }
                data[row][6]=(int)data[row][4]+(int)data[row][5];
                row++;
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void addQty(long id)
    {
        String sql="UPDATE books SET qty=qty+1 WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        database obj=new database("jdbc:mysql://localhost:3306/stdatabase","root","gautum1234");
        long arr[]={1234,2344};
        obj.issueBook(arr, 21011714);
    }
}
