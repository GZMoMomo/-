package pane;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.table.AbstractTableModel;

public class HeroTableModel extends AbstractTableModel {

	
	String[] columnNames =new String[] {"id","name","hp","damage"};
	public List<Hero> heros=new HeroDao().list();
       
        public int getRowCount() {
        	return heros.size();
	}
        public int getColumnCount() {
        	return columnNames.length;
        }
        
        public String getColumnName(int columnIndex) {
        	return columnNames[columnIndex];
        }
        public boolean isCellEditable(int rowIndex,int columnIndex) {
        	return false;
        }
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Hero h=heros.get(rowIndex);
			if(0==columnIndex)
				return h.id;
			if(1==columnIndex)
				return h.name;
			if(2==columnIndex)
				return h.hp;
			if(3==columnIndex)
				return h.damage;
			return null;
		}

}
