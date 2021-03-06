//
//  Execute.java
//
//  Copyright (c) 1997, 2004
//  Sybase, Inc.
//  One Sybase Drive, Dublin, CA 94568
//  All Rights Reserved
//

package sample2;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Execute class demonstrates how to use the execute method<br>
 * Also uses Statement.setMaxRows() to limit the output<br>
 *
 * <P>Execute may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */

public class Execute extends Sample
{
    // Max number of rows to  return in a ResultSet
    static public final int   MAXROWS = 5;  

    Execute()
    {
        super();
    }

    public void sampleCode()
    {


        String query = "sp_help titles"; 
        String query1 = "select pub_id, pub_name from publishers";
        String query2 = "select pub_id, pub_name from publishers" +
            "\n select au_fname, au_lname, state from authors";


        try 
        {

            // Execute sp_help which returns multiple ResultSets
            displayRows(query);

            // Execute a query returning 1 ResultSet
            displayRows(query1);

            // Execute a batch containing 2 select statements 
            displayRows(query2);
        }
        catch (SQLException ex)
        {
            displaySQLEx(ex);
        }
    }


    /**
     * Execute the desired DML statement and then call dispResultSet to
     * display the rows and columns
     * @param query  SQL Query to execute
     * @exception SQLException .
     */

    public void displayRows( String query)
        throws SQLException
    {

        Statement stmt = _con.createStatement();
        output("Executing: " + query + "\n");

        stmt.setMaxRows(MAXROWS);
        boolean results = stmt.execute (query);
        int rsnum = 0;                   // Number of Result Sets processed
        int rowsAffected = 0;       
        do
        {
            if(results)
            {
                ResultSet rs = stmt.getResultSet();
                output("\n\nDisplaying ResultSet: " + rsnum + "\n");
                dispResultSet(rs);
                rsnum++;
                rs.close();
            }
            else
            {
                rowsAffected = stmt.getUpdateCount();
                if (rowsAffected >= 0)
                output(rowsAffected + " rows Affected.\n");
            }
            results = stmt.getMoreResults();
        }
        while (results || rowsAffected != -1);

        stmt.close();
    }

}
