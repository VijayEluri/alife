package jml.examples;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	
	public static final int POSICION_INDEX = 0;
	public static final int DATO_INDEX = 1;
    public static final int ESNUMERO_INDEX = 2;
    public static final int ESESPACIO_INDEX = 3;
    public static final int HIDDEN_INDEX = 4;
    protected String[] columnNames;
    protected Vector dataVector;
    
    /**
     * Constructor
     * @param columnNames Names of the columns
     */
    public TableModel (String[] columnNames) {
       	this.columnNames = columnNames;
        dataVector = new Vector();
    }
    
    /**
     * Gets the name of the given column
     * @return The name of the column
     */
    public String getColumnName (int column) {
        return columnNames[column];
    }
    
    public void setValueAt (Object value, int row, int column) {
    	Archivo actDato = (Archivo)dataVector.get(row);
        switch (column) {
        	case POSICION_INDEX:
        		actDato.setPosicion((String)value);
               break;
        	case DATO_INDEX:
        		actDato.setdato((String)value);
               break;
        	case ESNUMERO_INDEX:
        		actDato.setesNumero((String)value);
               break;
        	case ESESPACIO_INDEX:
        		actDato.setesEspacio((String)value);
                break;
            default:
               System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }
    
    
    public int getRowCount() {
        return dataVector.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public void addEmptyRow() {
        dataVector.add(new Archivo());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }

	public Object getValueAt(int arg0, int arg1) {
		return null;
	}
}
