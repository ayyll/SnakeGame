package com.snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SnakeApp {
	public void init() {
		
		JFrame window = new JFrame("̰����");
		//��ʼ��һ�����������������������һЩ�ؼ�
		Container contentPane = window.getContentPane();
		Grid grid = new Grid(Settings.DEFAULT_GRID_WIDTH,Settings.DEFAULT_GRID_HEIGHT);
		GameView gameView = new GameView(grid);
		GameController gameController = new GameController(grid,gameView);
		/*
		JMenuBar menubar;//�˵���
		JMenu menu;  //�˵�
		JMenuItem item;  //�˵���
		menubar = new JMenuBar();  
        menu = new JMenu("�˵�");  
        item = new JMenuItem("��ʼ");  
        item.addActionListener(gameController);
        menu.add(item);
        menubar.add(menu);
        window.setJMenuBar(menubar);
		*/
		//������ͼ����
        gameView.init();
        //����gameView��JPanel�Ĵ�С
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        
        window.addKeyListener(gameController);//��Ӽ��̼�����
        new Thread(gameController).start();
        
        
	}
	public static void main(String[] args) {
		SnakeApp snakeApp = new SnakeApp();
		snakeApp.init();
	}

}
