package util;

import java.sql.*;

public class Connect {

    private final String USERNAME = "root"; //root = username default device
    //pake kapital semua supaya keliatannya aja biasanya kalau final kek gt
    private final String PASSWORD = "";
    private final String DATABASE = "clinicconnect"; //sesuai dengan nama database yg dibuat
    private final String HOST = "localhost:3306"; //sesuain sm port MySQL di xampp

    private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

    private Connection con;
    //import dari java.sql
    private Statement st; //untuk eksekusi query ke database kita

    public ResultSet rs; //untuk nampung hsl query
    public ResultSetMetaData rsm; //untuk nampung informasi ttg hasil query
    public PreparedStatement ps; // Add this line

    //singleton -> biar cuma ada 1 connection buat keseluruhan jalannya program (pakai static)
    private static Connect connect;
    //Connect yg pake C gede itu sesuai dengan kelas Connect kita

    //membuat method untuk dapetin objek connect nya
    public static Connect getInstance() {
        if(connect == null) {
            //kalau connect blm pernah dibuat
            return new Connect(); //kalau connect blm pernah dibuat
        } else{
            return connect; //kalau object connectnya  udh ada
        }
    }

    //contructor
    private Connect() {
        try {
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            st = con.createStatement();
            ps = null; // Initialize ps as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method buat jalanin query (SELECT)
    public ResultSet execQuery(String query) {
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //deketin trs pilih surround try catch
        return rs;
    }

    public void execUpdate(String query) {
        try {
            st.executeUpdate(query);
            //deketin trs surround try catch
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return con.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closePreparedStatement() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}