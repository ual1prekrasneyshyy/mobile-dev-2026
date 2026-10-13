import kotlinx.coroutines.delay
import kotlin.coroutines.suspendCoroutine

sealed class Aircraft {
    object Vehicle : Aircraft()

    data class Boeing(val speed: Int): Aircraft()
    data class Airbus(val speed: Int): Aircraft()
}

suspend fun simulateAircraftMotion() {
    motion(Aircraft.Boeing(800))
    motion(Aircraft.Airbus(850))
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

        delay(1000)
    }
}
