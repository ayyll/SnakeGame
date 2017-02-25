package com.snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author yh
 * 地图类
 */
public class Grid {
	private final int width;//宽
	private final int height;//高
	private boolean status[][];//方格是否被蛇覆盖，true为覆盖,主要用于生成食物时判断是否刷新在蛇的身体上
	private Snake snake;//蛇
	private Node food;//食物
	private static Direction snakeDirection = Direction.LEFT;//蛇运动方向，默认左
	/**
	 * 初始化地图
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		status = new boolean[width / Settings.DEFAULT_NODE_SIZE][height / Settings.DEFAULT_NODE_SIZE];
		
		initSnake();
		createFood();
	}
	
	/**
	 * 初始化蛇
	 * @return 返回snake引用
	 */
	private Snake initSnake() {
		this.snake = new Snake();
		for(int i = 0; i < this.width / Settings.DEFAULT_NODE_SIZE; i++) {
			for(int j = 0; j < this.height / Settings.DEFAULT_NODE_SIZE; j++) {
				status[i][j] = false;
			}
		}
		//设置蛇的body,更新棋盘覆盖状态
		for(int i = 5; i < this.width / (3 * Settings.DEFAULT_NODE_SIZE); i++) {
			this.snake.addTail(new Node(i,this.height / ( 2 * Settings.DEFAULT_NODE_SIZE)));
			status[i][this.height / ( 2 * Settings.DEFAULT_NODE_SIZE )] = true;
		}
		return this.snake;
	}
	/**
	 * 随机生成食物
	 * @return 返回生成食物的引用
	 */
	public Node createFood() {
		int x,y;
		Random r = new Random();
		do {
			x = r.nextInt(this.width / Settings.DEFAULT_NODE_SIZE - 1);
			y = r.nextInt(this.height / Settings.DEFAULT_NODE_SIZE - 1);
		} while(status[x][y]);
		
		this.food = new Node(x,y);
		return this.food;
	}
	/**
	 * 移动一步
	 * @return 返回 true 游戏继续 false 游戏结束
	 */
	public boolean nextRound() {
		//移动一步
		Node nodeTem = this.snake.move(snakeDirection);
		
		//头部位置是否有效
		if(isValidHeadPos(this.snake.getHead())) {
			//吃到食物
			if(this.snake.getHead().getX() == this.food.getX() && this.snake.getHead().getY() == this.food.getY()) {
				//把之前删除的尾部添加回来
				this.snake.addTail(nodeTem);
				createFood();//创建新食物
			}
			this.status[this.snake.getHead().getX()][this.snake.getHead().getY()] = true;
			return true;
		}
		return false;//游戏结束
	}
	/**
	 * 判断该节点是否有效
	 * @param node 
	 * @return 返回 true 有效 false 无效
	 */
	public boolean isValidHeadPos(Node node) {
		//超出边界,分上下左右四种情况
		if( node.getX() >= this.width / Settings.DEFAULT_NODE_SIZE ||  
			node.getY() >= this.height / Settings.DEFAULT_NODE_SIZE ||
			node.getX() < 0 ||
			node.getY() < 0 )
			return false;
		//碰到自己
		LinkedList<Node> body = this.snake.getBody();
		ArrayList<Node> temBody = new ArrayList<Node>(body);
		for (int i = 1; i < temBody.size(); i++) {
			if(node.getX() == temBody.get(i).getX() && node.getY() == temBody.get(i).getY())
				return false;
		}
		return true;	
	}
	//修改移动方向
	public void changeDirection(Direction newDirection) {
		if(snakeDirection.compatibleWith(newDirection)) {
			snakeDirection = newDirection;
		}
	}

	public Snake getSnake() {
		// TODO Auto-generated method stub
		return this.snake;
	}

	public Node getFood() {
		// TODO Auto-generated method stub
		return this.food;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
}
