package com.snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author yh
 * ��ͼ��
 */
public class Grid {
	private final int width;//��
	private final int height;//��
	private boolean status[][];//�����Ƿ��߸��ǣ�trueΪ����,��Ҫ��������ʳ��ʱ�ж��Ƿ�ˢ�����ߵ�������
	private Snake snake;//��
	private Node food;//ʳ��
	private static Direction snakeDirection = Direction.LEFT;//���˶�����Ĭ����
	/**
	 * ��ʼ����ͼ
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		status = new boolean[width / Settings.DEFAULT_NODE_SIZE][height / Settings.DEFAULT_NODE_SIZE];
		
		initSnake();
		createFood();
	}
	
	/**
	 * ��ʼ����
	 * @return ����snake����
	 */
	private Snake initSnake() {
		this.snake = new Snake();
		for(int i = 0; i < this.width / Settings.DEFAULT_NODE_SIZE; i++) {
			for(int j = 0; j < this.height / Settings.DEFAULT_NODE_SIZE; j++) {
				status[i][j] = false;
			}
		}
		//�����ߵ�body,�������̸���״̬
		for(int i = 5; i < this.width / (3 * Settings.DEFAULT_NODE_SIZE); i++) {
			this.snake.addTail(new Node(i,this.height / ( 2 * Settings.DEFAULT_NODE_SIZE)));
			status[i][this.height / ( 2 * Settings.DEFAULT_NODE_SIZE )] = true;
		}
		return this.snake;
	}
	/**
	 * �������ʳ��
	 * @return ��������ʳ�������
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
	 * �ƶ�һ��
	 * @return ���� true ��Ϸ���� false ��Ϸ����
	 */
	public boolean nextRound() {
		//�ƶ�һ��
		Node nodeTem = this.snake.move(snakeDirection);
		
		//ͷ��λ���Ƿ���Ч
		if(isValidHeadPos(this.snake.getHead())) {
			//�Ե�ʳ��
			if(this.snake.getHead().getX() == this.food.getX() && this.snake.getHead().getY() == this.food.getY()) {
				//��֮ǰɾ����β����ӻ���
				this.snake.addTail(nodeTem);
				createFood();//������ʳ��
			}
			this.status[this.snake.getHead().getX()][this.snake.getHead().getY()] = true;
			return true;
		}
		return false;//��Ϸ����
	}
	/**
	 * �жϸýڵ��Ƿ���Ч
	 * @param node 
	 * @return ���� true ��Ч false ��Ч
	 */
	public boolean isValidHeadPos(Node node) {
		//�����߽�,�����������������
		if( node.getX() >= this.width / Settings.DEFAULT_NODE_SIZE ||  
			node.getY() >= this.height / Settings.DEFAULT_NODE_SIZE ||
			node.getX() < 0 ||
			node.getY() < 0 )
			return false;
		//�����Լ�
		LinkedList<Node> body = this.snake.getBody();
		ArrayList<Node> temBody = new ArrayList<Node>(body);
		for (int i = 1; i < temBody.size(); i++) {
			if(node.getX() == temBody.get(i).getX() && node.getY() == temBody.get(i).getY())
				return false;
		}
		return true;	
	}
	//�޸��ƶ�����
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
