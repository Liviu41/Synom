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
                String query1 = "select cpu_usage from resources_shadowfax order by idresources desc limit 1,1";
                ResultSet rs = myStmt.executeQuery(query1);

                String s2 = null;
                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "%";
                    }
                }

                gui.label1a.setText(s2);

                String query2 = "select ram_usage from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query2);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "MB";
                    }
                }

                gui.label1b.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------

// Client 2--------------------------------------------------------------------------------------------------------------------
                String query3 = "select cpu_usage from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query3);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "%";
                    }
                }

                gui.label2a.setText(s2);

                String query4 = "select ram_usage from resources_lightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query4);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "MB";
                    }
                }

                gui.label2b.setText(s2);

//-----------------------------------------------------------------------------------------------------------------------------
// Client 3--------------------------------------------------------------------------------------------------------------------
                String query5 = "select cpu_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query3);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "%";
                    }
                }

                gui.label3a.setText(s2);

                String query6 = "select ram_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query4);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "MB";
                    }
                }

                gui.label3b.setText(s2);

//-----------------------------------------------------------------------------------------------------------------------------
// Client 4--------------------------------------------------------------------------------------------------------------------
                String query7 = "select cpu_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query3);

                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "%";
                    }
                }

                gui.label4a.setText(s2);

                String query8 = "select ram_usage from resources_brightfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query4);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    s2 = s;
                    int dot = 0;
                    if (s.equals("Offline") != true) {
                        for (int i = 0; i < s.length(); ++i) {
                            if (Character.isDigit(s.charAt(i)) == false) {
                                dot = i;
                                break;
                            }
                        }
                        s2 = s.substring(0, dot + 3) + "MB";
                    }
                }

                gui.label4b.setText(s2);

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
