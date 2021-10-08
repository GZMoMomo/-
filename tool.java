package pane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class tool {
	 static int page=0;
	 static int pages=10;
	 final static HeroTableModel htm=new HeroTableModel();
	 final static JTable t=new JTable(htm);
	 static JButton bHome=new JButton("首页");
	   static JButton bPre=new JButton("上一页");
	   static JButton bNe=new JButton("下一页");
	   static JButton bEnd=new JButton("末页");
	   static JPanel p=new JPanel();
	   static JFrame f=new JFrame("lol");
	   static JPanel p2=new JPanel();
	   static JComboBox jc;
	 public static void main(String[] args) {
	   
	   f.setSize(400, 325);
	   f.setLocation(200, 200);
	   f.setLayout(null);
	   
	   t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	   t.getSelectionModel().setSelectionInterval(0, 0);
	   
	   String[] comboxItems=new String[page()];
	   for(int i=0;i<page();i++) {
		   comboxItems[i]=Integer.toString(i);
	   }
	   jc=new JComboBox(comboxItems);
	   
	   p.setBounds(90, 190, 200, 40);
	   JButton bAdd=new JButton("增加");
	   JButton bDelect=new JButton("删除");
	   JButton bEdit=new JButton("编辑");
	  
	   p2.setBounds(20,230,350,40);
	 
	   p.add(bAdd);p.add(bDelect);p.add(bEdit);
	   p2.add(bHome);p2.add(bPre);p2.add(jc);
	   p2.add(bNe);p2.add(bEnd);
	   
	   updateButtonStatus();
      List[] ppp=splitList(htm.heros);
	   htm.heros=ppp[page];
	   
	   jc.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
            int g=Integer.valueOf(jc.getSelectedItem().toString());
			page=g*pages;
			updateTable();
			updateButtonStatus();
		}
	});
	 
	   bHome.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			page=0;
			updateTable();
			updateButtonStatus();
		}
	});
	   bNe.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			page+=pages;
			updateTable();
			updateButtonStatus();
			
		}
	});
	   bPre.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			page-=pages;
			updateTable();
			updateButtonStatus();
		}
	});
	   bEnd.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			page=last();
			updateTable();
			updateButtonStatus();
		}
	});
	   
	   
	   JScrollPane sp=new JScrollPane(t);
	   sp.setBounds(0,0,400,185);
	   bEdit.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			JDialog jd=new JDialog();
			jd.setSize(300,200);
			jd.setLocation(250,250);
			jd.setLayout(null);
			jd.setVisible(true);
			JLabel name=new JLabel("名称");name.setBounds(50,0,70,70);
			JTextField tName=new JTextField();tName.setBounds(100,25,100,20);
			JLabel hp=new JLabel("血量");hp.setBounds(50,50,70,70);
			JTextField tHp=new JTextField();tHp.setBounds(100,75,100,20);
			JButton jb=new JButton("提交");jb.setBounds(110,110,75,30);
			jd.add(jb);
			jd.add(name);jd.add(tName);jd.add(hp);jd.add(tHp);
			
			jb.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					String name=tName.getText();
					String hp=tHp.getText().trim();
					if(name.length()==0) {
						JOptionPane.showMessageDialog(jd, "名称不能为空");
						
					}else if(hp.isEmpty()){
						JOptionPane.showMessageDialog(jd, "血量不能为空");
					}else {
						HeroDao hd=new HeroDao();		
				    Hero hero=htm.heros.get(t.getSelectedRow());
					hero.name=name;hero.hp=Float.parseFloat(hp);
					hd.update(hero);
					htm.heros=hd.list();
					updateTable();
					updateButtonStatus();
					jd.setVisible(false);
					}
					
				}
			});
			
		}
	});
	   
	   bDelect.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			int i=JOptionPane.showConfirmDialog(f, "确认要删除？");
			if(i==0) {
				HeroDao hd=new HeroDao();
				Hero h=new Hero();
				int id=t.getSelectedRow();
				h=htm.heros.get(id);
				hd.delete(h.id);
				htm.heros=hd.list();
				updateTable();
				updateButtonStatus();
			}
		}
	});
	   
	   bAdd.addActionListener(new ActionListener() {

		
		public void actionPerformed(ActionEvent e) {
			JDialog jd=new JDialog();
			jd.setSize(300,200);
			jd.setLocation(255,260);
			jd.setLayout(null);
			JLabel lName=new JLabel("名称");lName.setBounds(50,0,70,70);
			JTextField jtName=new JTextField("");jtName.setBounds(100,25,100,20);
			JLabel lHp=new JLabel("血量");lHp.setBounds(50,50,70,70);
			JTextField jtHp=new JTextField("");jtHp.setBounds(100,75,100,20);
			jd.add(lName);jd.add(jtName);jd.add(lHp);jd.add(jtHp);
			jtName.setPreferredSize(new Dimension(80,30));
			jtHp.setPreferredSize(new Dimension(80,30));
			JButton badd=new JButton("确定");badd.setBounds(110,110,75,30);
			jd.add(badd);
			badd.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					HeroDao dao=new HeroDao();
			        Hero h=new Hero();
			        String name=jtName.getText();
					if(name.length()==0) {
						JOptionPane.showMessageDialog(f, "名称不能为空");
						jtName.grabFocus();
						return;
					}
					String hp=jtHp.getText().trim();
					try {
						Float.parseFloat(hp);
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(f, "血量只能是小数");
						jtHp.grabFocus();
						return;
					}
					h.name=name;
					h.hp=Float.parseFloat(hp);
					
					dao.add(h);
					htm.heros=dao.list();
					updateTable();
					updateButtonStatus();
					t.getSelectionModel().setSelectionInterval(0, 0);
					
					JOptionPane.showMessageDialog(jd, "提交成功");
				    
				    jd.setVisible(false);
				    
				}
			});
			jd.setVisible(true);
			
		}
		   
	   });
	   
	   f.add(sp); 
	   f.add(p);
	   f.add(p2);
	  
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setVisible(true);
	}

	private static List[] splitList(List list) {
		int pages=list.size()/10;
		int lastCount=list.size()%10;
		if(lastCount!=0) {
			pages+=1;
		}
		List[] listHero=new ArrayList[pages];
		for(int i=0;i<pages;i++) {
			List SplitPage = new ArrayList() ;
			listHero[i]=SplitPage;
		}
		int io=0;
		int count=0;
		for(int i=0;i<list.size();i++) {
			if(count==10) {
				io++;
				count=0;
			}
			listHero[io].add(list.get(i));
				count++;
		}
		
		return listHero;
	}
	public static void updateTable() {
		htm.heros=new HeroDao().list(page,pages);
		t.updateUI();
		
		
	}
	public static void updateButtonStatus() {
		int last=last();
		if(0!=page) {
			bHome.setEnabled(true);
			bPre.setEnabled(true);
		}
		if(0==page) {
			bHome.setEnabled(false);
			bPre.setEnabled(false);
		}
		if(page==last) {
			bEnd.setEnabled(false);
			bNe.setEnabled(false);
		}
		if(page!=last) {
			bEnd.setEnabled(true);
			bNe.setEnabled(true);
		}
		
		
	}
	public static int last() {
		int last=0;
		int total=new HeroDao().getTotal();
		if(0==total%pages) {
			last=total-pages;
		}else {
			last=total-total%pages;
		}
		return last;
	}
	public static int page() {
		int pe=0;
		int total=new HeroDao().getTotal();
		if(0==total%pages) {
			return pe=total/pages;
		}else{
			return pe=(total/pages)+1;
		}
	}

}
