package co.simplon.prairie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;

//import java.util.List;
import java.util.*;

@MadvocAction
public class IndexAction {

    static String databaseUrl = "jdbc:oracle:thin:simplon/simplon@localhost:1521:xe";

    static String requeteSql = "select NAME, CITY from TEAMS where CITY = 'San Francisco';";

    @Out
    String equipe;
    
    @Out
    String ville;

    @Action("/")
    public String view() throws Exception {

        DriverManager.registerDriver(new OracleDriver());

        Connection connexion = DriverManager.getConnection(databaseUrl);
        Statement requete = connexion.createStatement();
        ResultSet resultat = requete.executeQuery(requeteSql);

        //while (resultat.next()) {
            equipe = resultat.getString("NAME");
            ville = resultat.getString("CITY");
        //}

        resultat.close();
        requete.close();
        connexion.close();
        return "/index";
    }
}
