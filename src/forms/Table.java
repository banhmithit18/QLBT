package forms;

import javax.swing.*;
import java.awt.*;

public interface Table {

    JScrollPane table(String table, String[] columnName, String query, Dimension d, boolean editable) ;

    }
