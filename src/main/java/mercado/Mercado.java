package mercado;

/**
 * Operaciones de utilidad sobre un carrito (arreglo de productos).
 * Métodos static: no necesitan una instancia de Mercado.
 *
 * POLIMORFISMO en acción: todos estos métodos reciben Producto[] sin importar
 * si contiene Frutas, Bebidas o Limpieza. Llaman precioFinal() en cada elemento
 * y Java decide en tiempo de ejecución qué implementación ejecutar.
 */
public class Mercado {

    /** Suma el precio final de todos los productos del carrito. */
    public static double totalCarrito(Producto[] carrito) {
        double total = 0;
        for (Producto p : carrito) {
            total += p.precioFinal(); // polimorfismo: versión correcta según tipo real
        }
        return total;
    }

    /**
     * TAREA 1: retornar el producto con el mayor precioFinal() del carrito.
     * Use totalCarrito como guía de recorrido.
     */
    public static Producto masCaro(Producto[] carrito) {
        // TODO: recorra guardando el Producto con mayor precioFinal() visto hasta ahora
        // Paso 1: Suponemos que el primero de la lista es el más caro de momento
        Producto elMasCaro = carrito[0];
        
        // Paso 2: Revisamos el resto de los productos uno por uno
        for (Producto p : carrito) {
            // Si encontramos uno que cueste MÁS que nuestro producto actual...
            if (p.precioFinal() > elMasCaro.precioFinal()) {
                elMasCaro = p; // ...convertimos el producto como el más caro del momento
            }
        }
        // Paso 3: Al terminar de revisar todos, devolvemos el ganador
        return elMasCaro;
    }

    /**
     * TAREA 2: retornar el producto con el menor precioFinal() del carrito.
     * Misma estructura que masCaro, pero busca el mínimo.
     */
    public static Producto masBarato(Producto[] carrito) {
        // TODO: misma lógica que masCaro, condición invertida
        // Paso 1: Suponemos que el primero es el más barato de momento
        Producto elMasBarato = carrito[0];
        
        // Paso 2: Recorremos la lista comparando
        for (Producto p : carrito) {
            // Aquí: Cambiamos la condición a MENOR (<)
            if (p.precioFinal() < elMasBarato.precioFinal()) {
                elMasBarato = p; // Actualizamos el producto con el menor precio
            }
        }
        return elMasBarato;
    }

    /**
     * TAREA 3: sumar los descuentos de todos los productos Descontable del carrito.
     *
     * POLIMORFISMO + INTERFACE: instanceof verifica en tiempo de ejecución qué tipo
     * real tiene p. El cast a Descontable permite llamar aplicarDescuento(), que
     * cada clase que implementa la interface define de forma diferente.
     */
    public static double totalDescuentos(Producto[] carrito) {
        // Paso 1: Creamos una bolsa acumuladora para ir guardando el ahorro total
        double ahorroTotal = 0;
        
        // Paso 2: Pasamos uno por uno los productos de la banda
        for (Producto p : carrito) {
            
            // FILTRO DE SEGURIDAD: ¿Este producto tiene descuento disponible?
            if (p instanceof Descontable) {
                
                // TRANSFORMACIÓN: Activamos sus propiedades de Descontable
                Descontable d = (Descontable) p;
                
                // ACUMULACIÓN: Sumamos el monto de su descuento a la bolsa
                ahorroTotal += d.aplicarDescuento();
            }
        }
        
        // Paso 3: Devolvemos la suma de toda la plata ahorrada
        return ahorroTotal;
    }
}
