//
//  ReadCursor.java
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
 * ReadCursor class demonstrates how to use a read only cursor<p>
 *
 * ReadCursor may be invoked with the optional parameters:<br>
 * -U username<br>
 * -P password<br>
 * -D debuglibraries<br>
 * -S server<p>
 *
 *  @see Sample
 */

public class ReadCursor  extends Sample
{
    // Class specific global vars
    static public final int   MAXROWS = 1;

    ReadCursor()
    {
        super();
    }

    /**
     * Location where you can add commandline properties to the connection
     * The Super class will call this function before creating the
     * connection
     * @param  cmdLine    CommandLine settings
     * @see CommandLine
     */
    public void addMoreProps(CommandLine cmdline) 
    {
        // Optimize for READONLY cursor to fetch MAXROWS at a time
        // If we are using FOR UPDATE, then set CURSOR_ROWS to 1
        // as this is a driver wide property.

        cmdline._props.put("CURSOR_ROWS", Integer.toString(MAXROWS));
    }

    public void sampleCode()
    {
        String fname[] = 
        {
            "Ringer", "Green" 
        }
        ;
        String query = "select au_id, au_lname, au_fname from authors " +
            "where au_lname = ? for read only";

        try
        {

            // Execute the desired DML statement and then call dispResultSet to
            // display the rows and columns
            // This version uses setCursorName() with a read only cursor

            PreparedStatement pstmt = _con.prepareStatement(query );
            output("Executing: " + query+"\n");

            pstmt.setCursorName("author_Cursor");
            for(int i=0; i < fname.length; i++)
            {
                output("Current value for au_lname=" + 
                    fname[i]+"\n");
                pstmt.setString(1, fname[i]);

                ResultSet rs = pstmt.executeQuery ();
                output("Cursor Name = " + rs.getCursorName() +"\n");
                dispResultSet(rs);

                //  Clear our IN params
                pstmt.clearParameters();
                rs.close();
            }

            pstmt.close();
        }
        catch (SQLException sqe)
        {
            displaySQLEx(sqe);
        }
    }
}
