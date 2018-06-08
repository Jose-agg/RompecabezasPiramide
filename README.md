# Rompecabezas de la Piramide #

Este proyecto contiene un algoritmo backtraking para resolución del rompecabezas de la piramide.

## Reglas del rompecabezas ##

El juego consiste en rellenar todas las “casillas” vacías de una “pirámide”, hasta completarla. Tiene las siguientes reglas:
- Hay que insertar un número del 1 al 9 en cada una de las casillas vacías de la pirámide.
- El número de cada casilla debe ser la suma o la diferencia de los números de las casillas que están inmediatamente debajo.
- Las casillas del mismo color han de tener el mismo número.
- Existen tres posibles colores (azul, rojo o amarillo) y si se utilizan, ha de hacerse en dos casillas por cada color.

A continuación, puede verse un ejemplo con un estado inicial y el resultado esperado tras la aplicación del algoritmo que lo resuelve. Obviamente, para una misma pirámide se podrían utilizar diferentes estados iniciales.

![piramideEjemplo](https://github.com/MrKarrter/RompecabezasPiramide/blob/master/imagenes_wiki/img1.png)

Se puede encontrar más información y ejemplos en este [enlace](http://www2.stetson.edu/~efriedma/puzzle/pyramid/)

## Entrada y del programa ##
Para solucionar este problema con el ordenador necesitaremos una forma para representar el estado inicial de las pirámides. Se utilizarán ficheros de texto con un formato como el que se indica a continuación para el ejemplo de la figura anterior:

![entrada](https://github.com/MrKarrter/RompecabezasPiramide/blob/master/imagenes_wiki/img2.png)

En la primera línea se indica la altura de la pirámide (en el ejemplo 5). A partir de ese momento se definen los diferentes elementos que tiene la pirámide, en cada uno de sus niveles de arriba abajo.
- **B**, **R** o **Y** hacen referencia a tres colores (Blue – Azul), (Red – rojo), y (Yellow – Amarillo).
- Los **asteriscos** hacen referencia a **huecos** que habrá que rellenar con números del 1 al 9.
- Los **números** indicados en la pirámide, son **fijos** y nunca han de modificarse en el programa.

La salida del programa para el ejemplo anterior debería ser algo como lo siguiente:

![salida](https://github.com/MrKarrter/RompecabezasPiramide/blob/master/imagenes_wiki/img3.png)

## Ejecución ##

Esta versión del algoritmo coge por defecto los 17 casos de prueba y los ejecuta una detrás de otro; lo que no llevará mas de 10 segundos. Durante la ejecución se mostrará el tiempo en milisegundos que tardó en ejecutar cada caso de pruebas.

Si se quisiera probar con otra piramide seria necesario modificar el archivo **Launcher.java**, escribiendo la ruta y nombre del fichero.