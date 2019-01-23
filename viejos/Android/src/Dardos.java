import java.util.ArrayList;

import utilidades.Leer;

public class Dardos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int marcas[] ;
		int numerojugadores = 0;
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ronda(jugadores);

		crearJugadores(numerojugadores, jugadores);

	}

	private static void ronda(ArrayList<Jugador> jugadores) {
		int[] marcas;
		for (int j = 0; j < jugadores.size() - 1; j++) {
			for (int j2 = 0; j2 < 2; j2++) {
				int tirada = Leer.pedirEntero("introduce tirada");
				int multiplicador = Leer.pedirEntero("introduce 1,2,3", "1|2|3");
				int puntuacion = tirada*multiplicador;
				marcas = jugadores.get(j).tirada(puntuacion);
				puntuacion = jugadores.get(j).puntuacion(tirada);
				if (Jugador.estaAbierto(jugadores, tirada)) {
					jugadores.get(j).setPuntuacion(jugadores.get(j).getPuntuacion() + puntuacion);
				}
				if (esGanador(jugadores, jugadores.get(j))) {
					System.out.println("El jugador " + j + "ha ganado la partida");
					break;
				}
			}

		}
	}

	private static boolean esGanador(ArrayList<Jugador> jugadores, Jugador jugador) {
		boolean ganador = true;
		if (jugador.todoCerrado() && jugador.puntuacionMejor(jugadores)) {

		} else {

			ganador = false;
		}
		return ganador;
	}

	private static void crearJugadores(int i, ArrayList<Jugador> jugadores) {
		int minimoJugadores = 1;
		while (i <= minimoJugadores) {
			jugadores.add(new Jugador());
			i++;
		}
	}

}
