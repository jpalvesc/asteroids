import java.awt.event.KeyEvent;

 /*
Controls:
UP = forward
DOWN = reverse
LEFT = turn left
RIGHT = turn right

UP + DOWN = brakeShip()
LEFT + RIGHT (Simultaneously) = flipShip()
UP + SPACE = boostShip()

W = warpShip()
A = boostShip()
S = brakeShip()
D = flipShip
*/
public class Input  {

	protected final static int REACTION_TIME = 2;
	protected static int leftTimer = 100;
	protected static int rightTimer = 100;
	protected static int upTimer = 100;
	protected static int downTimer = 100;
	protected static int spaceTimer = 100;
	protected static boolean ignoreUpDown;
	private static KeyboardInput keyboard = new KeyboardInput();
	static PlayerShip player = Asteroids.getPlayer();
	public static void update(){	

			KeyboardInput.poll();
//Simultaneous press functions-------------------------------------------------------------
		if(keyboard.keyDown(KeyEvent.VK_UP)&&(keyboard.keyDown(KeyEvent.VK_DOWN))){
			player.brakeShip();
			upTimer++;
			downTimer++;
			ignoreUpDown = true;
		}else ignoreUpDown = false;
		if (leftTimer < REACTION_TIME && rightTimer < REACTION_TIME){	
			player.flipShip();
			leftTimer = REACTION_TIME;
			rightTimer = REACTION_TIME;
		}

//Key released functions-------------------------------------------------------------------
		if (keyboard.keyReleased(KeyEvent.VK_DOWN)){
			downTimer = REACTION_TIME;
		}
		if (keyboard.keyReleased(KeyEvent.VK_UP)){
			upTimer = REACTION_TIME;
		}
		if (keyboard.keyReleased(KeyEvent.VK_LEFT)){
			leftTimer = REACTION_TIME;
		}
		if (keyboard.keyReleased(KeyEvent.VK_RIGHT)){
			rightTimer = REACTION_TIME;
		}
		if (keyboard.keyReleased(KeyEvent.VK_SPACE)){
			spaceTimer = REACTION_TIME;
		}
//Key down functions---------------------------------------------------------------------
		if(keyboard.keyDown(KeyEvent.VK_S)){
			player.brakeShip();
			ignoreUpDown = true;
		}
		if(keyboard.keyDown(KeyEvent.VK_DOWN)&& (ignoreUpDown == false)) {
			player.reverseThrust();
			downTimer++;
			System.out.println(downTimer);
			ignoreUpDown = false;
		}
		if(keyboard.keyDown(KeyEvent.VK_UP)&& (ignoreUpDown == false)) {
			player.forwardThrust();
			upTimer++;
			ignoreUpDown = false;
		}
		if(keyboard.keyDown(KeyEvent.VK_LEFT)){
			player.turnLeft();
			leftTimer++;
		}
		if(keyboard.keyDown(KeyEvent.VK_RIGHT)){
			player.turnRight();
			rightTimer++;
		}
		if(keyboard.keyDown(KeyEvent.VK_SPACE)){
			player.shoot();
			spaceTimer++;
		}
		if(keyboard.keyDown(KeyEvent.VK_S)){
			player.brakeShip();
			ignoreUpDown = true;
		}
//Key down once functions----------------------------------------------------------
		if(keyboard.keyDownOnce(KeyEvent.VK_S)){
			player.brakeShip();
			ignoreUpDown = true;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_DOWN)) {
			player.reverseThrust();
			downTimer = 0;
			ignoreUpDown = false;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_UP)) {
			player.forwardThrust();
			upTimer = 0;
			ignoreUpDown = false;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_LEFT)){
			player.turnLeft();
			leftTimer = 0;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_RIGHT)){
			player.turnRight();
			rightTimer = 0;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_SPACE)){
			player.shootOnce();
			spaceTimer = 0;
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_W)){
			player.warpShip();
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_D)){
			player.flipShip();
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_A)){
			player.boostShip();
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_P)){
			Asteroids.togglePause();
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_PAUSE)){
			Asteroids.togglePause();
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_ESCAPE)){
			Asteroids.quitToMenu();
		}
		//for debug
		if (keyboard.keyDownOnce(KeyEvent.VK_F11)){
			Bandit.spawn();
		}
//Regenerate-------------------------------------------------------------------------------
		if(keyboard.keyDown(KeyEvent.VK_SPACE) && (player.isAlive() == false)){
			player.regenerate();
		}
	}
}