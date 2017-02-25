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
		
		JFrame window = new JFrame("贪吃蛇");
		//初始化一个容器，用来在容器上添加一些控件
		Container contentPane = window.getContentPane();
		Grid grid = new Grid(Settings.DEFAULT_GRID_WIDTH,Settings.DEFAULT_GRID_HEIGHT);
		GameView gameView = new GameView(grid);
		GameController gameController = new GameController(grid,gameView);
		/*
		JMenuBar menubar;//菜单条
		JMenu menu;  //菜单
		JMenuItem item;  //菜单项
		menubar = new JMenuBar();  
        menu = new JMenu("菜单");  
        item = new JMenuItem("开始");  
        item.addActionListener(gameController);
        menu.add(item);
        menubar.add(menu);
        window.setJMenuBar(menubar);
		*/
		//画出地图和蛇
        gameView.init();
        //设置gameView中JPanel的大小
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        
        window.addKeyListener(gameController);//添加键盘监听器
        new Thread(gameController).start();
        
        
	}
	public static void main(String[] args) {
		SnakeApp snakeApp = new SnakeApp();
		snakeApp.init();
	}

}
