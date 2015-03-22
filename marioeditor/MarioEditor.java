import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class MarioEditor extends JPanel implements MouseListener{
Vector<Block> blocks;
Vector<BufferedImage> images;
int offset = 0;
int placetype = 0;
int mx;
int my;
boolean placemode = true;
	public static void main(String[] args) throws Exception{
		new MarioEditor();
	}
	public MarioEditor() throws Exception{
		

		blocks = new Vector<Block>();
		images = new Vector<BufferedImage>();


		images.add(ImageIO.read(this.getClass().getResource("blockground.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockbreak.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockbreakstar.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockbreakcoinhidden.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockquestionhidden.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockquestionmushroom.png")));
		images.add(ImageIO.read(this.getClass().getResource("blockquestioncoin.png")));
		images.add(ImageIO.read(this.getClass().getResource("pipetop.png")));
		images.add(ImageIO.read(this.getClass().getResource("pipemiddle.png")));

		

		JFrame f = new JFrame("Mario Editor");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(new MouMo());
		f.addKeyListener(new KeyList());
		f.setSize(600, 600);
		f.setVisible(true);
	}

	public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	for(int j = 0; j < 600; j+=16){
		g.drawLine(j, 0, j, 600);
		g.drawLine(0, j, 600, j);
	}
		if(placemode)g.drawImage(images.get(placetype), mx, my, 32, 32, null); 
		g.setColor(Color.RED);
		g.fillOval(mx-2, my-2, 4, 4);
		if(!blocks.isEmpty()){
		for(Block b: blocks){
			g.drawImage(images.get(b.type), b.bound.x-offset, b.bound.y, b.bound.width, b.bound.height, null); 
		}
		}
	}

public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
	if(placemode){
		blocks.add(new Block(placetype, (mx+offset)-((mx+offset)%16), my-(my%16)));
	}else{
		for(Block b: blocks){
			if( b.bound.contains(mx+offset, my)){
				blocks.remove(b);
				break;
			}
		}
	}
repaint();
    }

public class MouMo implements MouseMotionListener{
public void mouseMoved(MouseEvent e) {
       mx = e.getX();
       my = e.getY();
	repaint();
    }

    public void mouseDragged(MouseEvent e) {
    }
}

public class KeyList extends KeyAdapter{
public void keyReleased(KeyEvent e) {
}

public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();
if (key == KeyEvent.VK_UP) {
        if(placetype<8)placetype++;
    }
if (key == KeyEvent.VK_DOWN) {
        if(placetype>0)placetype--;
    }

    if (key == KeyEvent.VK_RIGHT) {
        offset+=16;
    }
if (key == KeyEvent.VK_LEFT) {
        offset-=16;
    }

if (key == KeyEvent.VK_Q) {
        placemode = !placemode;
    }

if (key == KeyEvent.VK_E) {
        for(Block b: blocks){
		System.out.println(b.type+","+b.bound.x+","+b.bound.y);
	}
    }

repaint();

}
}
public class Block{
	public int type;
	public Rectangle bound;

public Block(int type, int x, int y){
	this.type = type;
	if(type<7){
		bound = new Rectangle(x, y, 16, 16);
	}else{
		bound = new Rectangle(x, y, 32, 32);
	}	
}
}

}