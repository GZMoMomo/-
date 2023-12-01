# -
将数据库增添改查和查看功能集成面板化
package pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HeroPanel extends JFrame {

    private HeroDao heroDao;

    private JTextArea textArea;

    public HeroPanel() {
        heroDao = new HeroDao();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hero Database Panel");
        setSize(400, 300);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JButton addButton = new JButton("Add Hero");
        JButton updateButton = new JButton("Update Hero");
        JButton deleteButton = new JButton("Delete Hero");
        JButton viewButton = new JButton("View Heroes");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用添加英雄的方法
                addHero();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用更新英雄的方法
                updateHero();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用删除英雄的方法
                deleteHero();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 调用查看英雄的方法
                viewHeroes();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addHero() {
        // 实现添加英雄的逻辑
        // 你可以在这里弹出对话框，获取用户输入，并调用 HeroDao 的 add 方法
    }

    private void updateHero() {
        // 实现更新英雄的逻辑
        // 你可以在这里弹出对话框，获取用户输入，并调用 HeroDao 的 update 方法
    }

    private void deleteHero() {
        // 实现删除英雄的逻辑
        // 你可以在这里弹出对话框，获取用户输入，并调用 HeroDao 的 delete 方法
    }

    private void viewHeroes() {
        // 实现查看英雄的逻辑
        // 调用 HeroDao 的 list 方法获取所有英雄列表，然后更新 textArea 显示
        List<Hero> heroes = heroDao.list();
        textArea.setText("");
        for (Hero hero : heroes) {
            textArea.append(hero.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HeroPanel().setVisible(true);
            }
        });
    }
}
