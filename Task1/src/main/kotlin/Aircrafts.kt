import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class Aircraft {
    data class Embraer(val speed: Int) : Aircraft()
    data class Boeing(val speed: Int): Aircraft()
    data class Airbus(val speed: Int): Aircraft()
}

suspend fun simulateAircraftMotion(){
    coroutineScope {
        launch { motion(Aircraft.Embraer(700)) }
        launch { motion(Aircraft.Boeing(800)) }
        launch { motion(Aircraft.Airbus(900)) }
    }
}

suspend fun motion(aircraft: Aircraft) {
    var distance = 0

    for (hour in 1..15) {
        if (aircraft is Aircraft.Airbus) {
            distance += aircraft.speed
            println("$hour hour: Airbus distance $distance km")
        }

        if (aircraft is Aircraft.Boeing) {
            distance += aircraft.speed
            println("$hour hour: Boeing distance $distance km")
        }

        if (aircraft is Aircraft.Embraer) {
            distance += aircraft.speed
            println("$hour hour: Embraer distance $distance km")
        }

        delay(1200)
    }
}
