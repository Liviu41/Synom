package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gui {

    public static GraphicalUI gui = new GraphicalUI();
    static String s = null;

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            while (true) {

                gui.setLocation(200, 200);
                gui.setVisible(true);

// Client 1 --------------------------------------------------------------------------------------------------------------------
                String query1 = "select os_type from resources_shadowfax order by idresources desc limit 1,1";
                ResultSet rs = myStmt.executeQuery(query1);

                String s2 = null;
                while (rs.next()) {
                    s = rs.getString("os_type");
                    s2 = s;
                }
                gui.label1a.setText(s2);

                String query2 = "select cpu_usage from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query2);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                }
                gui.label1b.setText(s2);

                String query3 = "select ram_usage from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query3);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                }
                gui.label1c.setText(s2);

                String query4 = "select ram_total from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query4);

                while (rs.next()) {
                    s = rs.getString("ram_total");
                    s2 = s;
                }
                gui.label1d.setText(s2);

                String query5 = "select storage_total from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query5);

                while (rs.next()) {
                    s = rs.getString("storage_total");
                    s2 = s;
                }
                gui.label1e.setText(s2);

                String query6 = "select storage_free from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query6);

                while (rs.next()) {
                    s = rs.getString("storage_free");
                    s2 = s;
                }
                gui.label1f.setText(s2);

                String query7 = "select ip from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query7);

                while (rs.next()) {
                    s = rs.getString("ip");
                    s2 = s;
                }
                gui.label1g.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------

// Client 2--------------------------------------------------------------------------------------------------------------------
                String query8 = "select os_type from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query8);

                s2 = null;
                while (rs.next()) {
                    s = rs.getString("os_type");
                    s2 = s;
                }
                gui.label2a.setText(s2);

                String query9 = "select cpu_usage from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query9);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                }
                gui.label2b.setText(s2);

                String query10 = "select ram_usage from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query10);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                }
                gui.label2c.setText(s2);

                String query11 = "select ram_total from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query11);

                while (rs.next()) {
                    s = rs.getString("ram_total");
                    s2 = s;
                }
                gui.label2d.setText(s2);

                String query12 = "select storage_total from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query12);

                while (rs.next()) {
                    s = rs.getString("storage_total");
                    s2 = s;
                }
                gui.label2e.setText(s2);

                String query13 = "select storage_free from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query13);

                while (rs.next()) {
                    s = rs.getString("storage_free");
                    s2 = s;
                }
                gui.label2f.setText(s2);

                String query14 = "select ip from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query14);

                while (rs.next()) {
                    s = rs.getString("ip");
                    s2 = s;
                }
                gui.label2g.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------
// Client 3--------------------------------------------------------------------------------------------------------------------
                String query15 = "select os_type from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query15);

                s2 = null;
                while (rs.next()) {
                    s = rs.getString("os_type");
                    s2 = s;
                }
                gui.label3a.setText(s2);

                String query16 = "select cpu_usage from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query16);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                }
                gui.label3b.setText(s2);

                String query17 = "select ram_usage from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query17);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                }
                gui.label3c.setText(s2);

                String query18 = "select ram_total from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query18);

                while (rs.next()) {
                    s = rs.getString("ram_total");
                    s2 = s;
                }
                gui.label3d.setText(s2);

                String query19 = "select storage_total from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query19);

                while (rs.next()) {
                    s = rs.getString("storage_total");
                    s2 = s;
                }
                gui.label3e.setText(s2);

                String query20 = "select storage_free from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query20);

                while (rs.next()) {
                    s = rs.getString("storage_free");
                    s2 = s;
                }
                gui.label3f.setText(s2);

                String query21 = "select ip from resources_darkfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query21);

                while (rs.next()) {
                    s = rs.getString("ip");
                    s2 = s;
                }
                gui.label3g.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------
// Client 4--------------------------------------------------------------------------------------------------------------------
                String query22 = "select os_type from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query22);

                s2 = null;
                while (rs.next()) {
                    s = rs.getString("os_type");
                    s2 = s;
                }
                gui.label4a.setText(s2);

                String query23 = "select cpu_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query23);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                }
                gui.label4b.setText(s2);

                String query24 = "select ram_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query24);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                }
                gui.label4c.setText(s2);

                String query25 = "select ram_total from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query25);

                while (rs.next()) {
                    s = rs.getString("ram_total");
                    s2 = s;
                }
                gui.label4d.setText(s2);

                String query26 = "select storage_total from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query26);

                while (rs.next()) {
                    s = rs.getString("storage_total");
                    s2 = s;
                }
                gui.label4e.setText(s2);

                String query27 = "select storage_free from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query27);

                while (rs.next()) {
                    s = rs.getString("storage_free");
                    s2 = s;
                }
                gui.label4f.setText(s2);

                String query28 = "select ip from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query28);

                while (rs.next()) {
                    s = rs.getString("ip");
                    s2 = s;
                }
                gui.label4g.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
