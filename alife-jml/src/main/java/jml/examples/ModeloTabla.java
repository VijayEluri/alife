package jml.examples;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class ModeloTabla extends AbstractTableModel {
	public static final int POSICION_INDEX = 0;
    public static final int DATO_INDEX = 1;
    public static final int ESNUMERO_INDEX = 2;
    public static final int ESESPACIO_INDEX = 3;
    public static final int HIDDEN_INDEX = 4;
    
    protected String[] columnNames;
    protected Vector dataVector;
    
    public ModeloTabla(String[] columnNames) {
        this.columnNames = columnNames;
        dataVector = new Vector();
    }
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public boolean isCellEditable(int row, int column) {
        if (column == HIDDEN_INDEX) return false;
        else return true;
    }
    
    public Class getColumnClass(int column) {
        switch (column) {
            case POSICION_INDEX:
            case DATO_INDEX:
            case ESNUMERO_INDEX:
            case ESESPACIO_INDEX:
               return String.class;
            default:
               return Object.class;
        }
    }
    
    public Object getValueAt(int row, int column) {
        Archivo actDato = (Archivo)dataVector.get(row);
        switch (column) {
            case POSICION_INDEX:
               return actDato.getPosicion();
            case DATO_INDEX:
               return actDato.getdato();
            case ESNUMERO_INDEX:
               return actDato.getesNumero();
            case ESESPACIO_INDEX:
                return actDato.getesEspacio();
            default:
               return new Object();
        }
    }
    
    public void setValueAt(Object value, int row, int column) {
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
    
    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        Archivo actDato = (Archivo)dataVector.get(dataVector.size() - 1);
        if (actDato.getPosicion().trim().equals("") &&
        		actDato.getdato().trim().equals("") &&
        		actDato.getesNumero().trim().equals("") &&
        		actDato.getesEspacio().trim().equals("")) 
        {
           return true;
        }
        else return false;
    }
    
    public void addEmptyRow() {
        dataVector.add(new Archivo());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
}
