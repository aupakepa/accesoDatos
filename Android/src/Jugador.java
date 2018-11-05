import java.util.ArrayList;

public class Jugador {
	private int numero;
	private int puntuacion;
	private int numerodardos;
	static int jugadores;
	private int[] marcas;

	public Jugador() {
		this.numero = jugadores + 1;
		this.puntuacion = 0;
		this.numerodardos = 0;
		this.marcas = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	}

	public int getNumero() {
		return numero;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public int getNumerodardos() {
		return numerodardos;
	}

	public static int getJugadores() {
		return jugadores;
	}

	public int[] getMarcas() {
		return marcas;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setNumerodardos(int numerodardos) {
		this.numerodardos = numerodardos;
	}

	public static void setJugadores(int jugadores) {
		Jugador.jugadores = jugadores;
	}

	public void setMarcas(int[] marcas) {
		this.marcas = marcas;
	}

	public int[] tirada(int i) {
		switch (i) {
		case 15:
			marcas[0] = marcas[0] + 1;
			break;
		case 16:
			marcas[1] = marcas[1] + 1;
			break;
		case 17:
			marcas[2] = marcas[2] + 1;

			break;
		case 18:
			marcas[3] = marcas[3] + 1;

			break;
		case 19:
			marcas[4] = marcas[4] + 1;

			break;
		case 20:
			marcas[5] = marcas[5] + 1;

			break;
		case 25:
			marcas[6] = marcas[6] + 1;

			break;
		case 50:
			marcas[6] = marcas[6] + 2;

			break;
		case 30:
			marcas[0] = marcas[0] + 2;

			break;
		case 32:
			marcas[1] = marcas[1] + 2;

			break;
		case 34:
			marcas[2] = marcas[2] + 2;

			break;
		case 36:
			marcas[3] = marcas[3] + 2;

			break;
		case 38:
			marcas[4] = marcas[4] + 2;

			break;
		case 40:
			marcas[5] = marcas[5] + 2;

			break;
		case 60:
			marcas[5] = 3;

			break;
		case 57:
			marcas[4] = 3;

			break;
		case 54:
			marcas[3] = 3;

			break;
		case 51:
			marcas[2] = 3;

			break;
		case 48:
			marcas[1] = 3;

			break;
		case 45:
			marcas[0] = 3;

			break;
		default:
			break;

		}
		for (int j = 0; j < marcas.length; j++) {
			if (marcas[j] > 3) {
				marcas[j] = 3;
			}
		}
		this.numerodardos++;
		return marcas;
	}

	public int puntuacion(int i) {
		switch (i) {
		case 15:
			i = marcas[0] * 15 + i - 45;
			break;
		case 16:
			i = marcas[1] * 16 + i - 48;
			break;
		case 17:
			i = marcas[2] * 17 + i - 51;

			break;
		case 18:
			i = marcas[3] * 18 + i - 54;

			break;
		case 19:
			i = marcas[4] * 19 + i - 57;

			break;
		case 20:
			i = marcas[5] * 20 + i - 60;

			break;
		case 25:
			i = marcas[6] * 25 + i - 75;

			break;
		case 50:
			i = marcas[6] * 25 + i - 75;

			break;
		case 30:
			i = marcas[0] * 15 + i - 45;
			break;
		case 32:
			i = marcas[1] * 16 + i - 48;
			break;
		case 34:
			i = marcas[2] * 17 + i - 51;

			break;
		case 36:
			i = marcas[3] * 18 + i - 54;

			break;
		case 38:
			i = marcas[4] * 19 + i - 57;

			break;
		case 40:
			i = marcas[5] * 20 + i - 60;

		case 45:
			i = marcas[0] * 15 + i - 45;
			break;
		case 48:
			i = marcas[1] * 16 + i - 48;
			break;
		case 51:
			i = marcas[2] * 17 + i - 51;

			break;
		case 54:
			i = marcas[3] * 18 + i - 54;

			break;
		case 57:
			i = marcas[4] * 19 + i - 57;

			break;
		case 60:
			i = marcas[5] * 20 + i - 60;

		default:
			break;

		}
		return i;
	}

	static boolean estaAbierto(ArrayList<Jugador> jugadores, int numero) {
		boolean cerrado = true;
		int posicion = numero - 15;
		for (int i = 0; i < jugadores.size() - 1; i++) {
			if (jugadores.get(i).getMarcas()[posicion] != 3) {
				cerrado = false;
			}
		}
		return cerrado;
	}

	

	public boolean todoCerrado() {
		boolean cerrado = true;
		for (int i = 0; i < marcas.length - 1; i++) {
			if (this.marcas[i] != 3) {
				cerrado = false;
			}
		}

		return cerrado;
	}
	public boolean puntuacionMejor(ArrayList<Jugador> jugadores) {
		boolean mejor = true;
		for (int i = 0; i < jugadores.size()-1; i++) {
			if (jugadores.get(i).getPuntuacion() > this.puntuacion) {
				mejor = false;
			}
		}
		return mejor;
	}
}
