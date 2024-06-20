package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Game extends Group {
	Ciel ciel;
	Panacik panacik;
	private String klaves = "";
	int velkost = 30;
	FileReader fr;
	BufferedReader br;
	int level = 1;
	Text t1;
	Text t2;
	Text t3;
	Timeline casovac;
	int pokus = 1;
	int stopka;
	int pokusCelkovo = 0;

	public Game() {
		level();
		setOnKeyPressed(evt -> klaves = evt.getCode().toString());
		setFocusTraversable(true);
		setFocused(true);
		nastavDobu();
		casovac = new Timeline(new KeyFrame(Duration.seconds(1), e -> cas()));
		casovac.setCycleCount(Animation.INDEFINITE);
		casovac.play();

	}

	void cas() {
		stopka -= 1;
		aktualizovatCas();
	}

	void nastavDobu() {
		if (level == 1)
			stopka = 30;
		if (level == 2)
			stopka = 30;
		if (level == 3)
			stopka = 30;
		if (level == 4)
			stopka = 30;
		if (level == 5)
			stopka = 30;
		if (level == 6)
			stopka = 30;
	}

	private void restartLevel() {
		pokus += 1;
		nastavDobu();
		level();
	}

	void aktualizovatCas() {
		t3.setText("Čas: " + stopka);
	}

	public boolean stopniTo() {
		return level > 6;
	}

	public int getPokus() {
		return pokusCelkovo;
	}

	public void update(double deltaTime) {
		if (koniec()) {
			if (level == 6) {
				level = 7;
				casovac.stop();
				stopniTo();
			} else {
				level++;
				pokusCelkovo += pokus;
				pokus = 1;
				level();
				nastavDobu();
			}
		}

		if (stopka <= 0) {
			restartLevel();
		}

		panacik.toFront(); // aby bol panacik stale vpredu
		double novaX = panacik.getLayoutX();
		double novaY = panacik.getLayoutY();

		switch (klaves) {
		case "LEFT":
			novaX -= velkost;
			if (!jeTamStena(novaX, novaY)) {
				panacik.dolava(velkost);
			}
			break;
		case "UP":
			novaY -= velkost;
			if (!jeTamStena(novaX, novaY)) {
				panacik.hore(velkost);
			}
			break;
		case "RIGHT":
			novaX += velkost;
			if (!jeTamStena(novaX, novaY)) {
				panacik.doprava(velkost);
			}
			break;
		case "DOWN":
			novaY += velkost;
			if (!jeTamStena(novaX, novaY)) {
				panacik.dole(velkost);
			}
			break;
		}
		klaves = "";
	}

	void level() {
		this.getChildren().clear();
		t1 = new Text("Level: " + level);
		t1.setLayoutX(50);
		t1.setLayoutY(670);
		t1.setFont(new Font(24));
		this.getChildren().add(t1);
		t2 = new Text("Pokus: " + pokus);
		t2.setLayoutX(150);
		t2.setLayoutY(670);
		t2.setFont(new Font(24));
		this.getChildren().add(t2);
		t3 = new Text("Čas: " + stopka);
		t3.setLayoutX(250);
		t3.setLayoutY(670);
		t3.setFont(new Font(24));
		this.getChildren().add(t3);
		try {
			String subor = "level" + level;
			br = new BufferedReader(new FileReader("bin/" + subor + ".txt"));
			String riadok;
			int cisloRiadka = 0;
			while ((riadok = br.readLine()) != null) {
				for (int i = 0; i < riadok.length(); i++) {
					char ch = riadok.charAt(i);
					switch (ch) {
					case '#':
						Bludisko stena = new Bludisko(i * velkost, cisloRiadka * velkost, velkost);
						this.getChildren().add(stena);
						break;
					case ' ':
						Podlaha floor = new Podlaha(i * velkost, cisloRiadka * velkost, velkost);
						this.getChildren().add(floor);
						break;
					case '.':
						ciel = new Ciel(i * velkost, cisloRiadka * velkost, velkost);
						this.getChildren().add(ciel);
						break;
					case '@':
						Podlaha floora = new Podlaha(i * velkost, cisloRiadka * velkost, velkost);
						this.getChildren().add(floora);
						panacik = new Panacik("panacik", 12, i * velkost, cisloRiadka * velkost, velkost, velkost);
						this.getChildren().add(panacik);
						break;
					}
				}
				cisloRiadka++;
			}
		} catch (IOException e) {
			System.out.println("Nepodarilo sa načítať súbor: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Nepodarilo sa zatvoriť súbor: " + e.getMessage());
				}
			}
		}
	}

	public boolean jeTamStena(double x, double y) {
		double centerX = x + velkost / 2.0;
		double centerY = y + velkost / 2.0;
		for (int i = 0; i < this.getChildren().size(); i++) {
			Node my = this.getChildren().get(i);
			if (my instanceof Bludisko) {
				Bludisko stena = (Bludisko) my;
				Bounds bounds = stena.getBoundsInParent();
				if (bounds.contains(centerX, centerY)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean koniec() {
		double panacikX = panacik.getLayoutX();
		double panacikY = panacik.getLayoutY();
		double cielX = ciel.getLayoutX();
		double cielY = ciel.getLayoutY();
		if (panacikX == cielX && panacikY == cielY) {
			return true;
		}
		return false;
	}

}
