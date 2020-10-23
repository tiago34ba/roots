package naruto;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Naruto - a robot by (your name here)
 */
public class Naruto extends Robot
{
	/**
	 * run: Naruto's default behavior
	 */
	double qdtMov; 
	double anguloAdver = 0;

	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		//setBodyColor	(Color.red); 	 //Corpo
		//setGunColor		(Color.black);   //Canhão
		//setRadarColor	(Color.black);   //Radar

		// Passa a quantidade de movimento para o máximo possível do campo
		qdtMov = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		ahead(qdtMov); //Busca o canto e avança
		// Direciona o canhão a 90º e vira o veículo 90º a esquerda
		turnGunRight(360);
		turnLeft(90);
	
		while (true) {
			ahead(qdtMov); //Avança de acordo com o parametro da arena
			turnRight(90); //Gira o veículo 90º a direita
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
public void onHitRobot(HitRobotEvent e) {
		// Caso esteja na frente recue e vire 90º a direita
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(150);
			turnRight(360);
		}
		else { //Caso esteja atrás recue e vire 90º a esquerda
			ahead(120);
			turnLeft(180);
		}
	}

	//Sempre que encontrado um robo atire
	public void onScannedRobot(ScannedRobotEvent e) {
		// Se o outro robô está próximo, e ele tem pouca energia e eu muita energia, dispara intensamente!
			if (e.getDistance() < 160 && e.getEnergy() < 160 && getEnergy() > 160) {
				fire(3);
			} //Caso contrário, atira com intensidade 1.
			else {
				fire(1);
			}	
		back(e.getDistance() - 10); //Calcula a distância pro inimigo recua
		scan(); // usa o radar novamente para localizar os oponentesic 
	}
}
