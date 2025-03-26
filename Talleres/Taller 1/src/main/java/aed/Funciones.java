package aed;

class Funciones {
    int cuadrado(int x) {
        int cuadrado = x * x;
        return cuadrado;
    }

    double cuadradoDouble(double x) {
        double cuadradoDouble = x * x;
        return cuadradoDouble;
    }

    double distancia(double x, double y) {
        double dist = Math.sqrt(cuadradoDouble(x) + cuadradoDouble(y));
        return dist;
    }

    boolean esPar(int n) {
        boolean resto = (n % 2 == 0);
        return resto;   
    }

    boolean esBisiesto(int año) {
        boolean condicion = (((año % 4 == 0) && (año % 100 != 0)) || año % 400 == 0); 
        return condicion;
    }

    int factorialIterativo(int n) {
        int fact;
        if (n == 0) {
            fact = 1;
        } else{
        fact = n;
        while (n > 1){
            fact = fact * (n - 1);
            n--;
        }
    }
        return fact;
    }

    int factorialRecursivo(int n) {
        if (n == 0) {
            int fact = 1;
            return fact;
        }
        return factorialRecursivo(n-1) * n;
    }

    int primo (int n) {
        int divisores = 0;
        for (int i = n; i > 0; i--){
            if (n % i == 0){
                divisores = divisores + 1;
            }
        }
        return divisores;
    }

    boolean esPrimo(int n) {
        boolean condicion = primo(n) == 2;
        return condicion;
    }

    int sumatoria(int[] numeros) {
        int suma = 0;
        for (int n:numeros){
            suma = suma + n;
        }
        return suma;
    }

    int busqueda(int[] numeros, int buscado) {
        int posicion = 0;
        for (int i = 0; i < numeros.length ; i++){
            if (numeros[i] == buscado){
                posicion = i;
            }
        }
        return posicion;
    }

    boolean tienePrimo(int[] numeros) {
        for (int n:numeros){
            if (esPrimo(n)){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int n:numeros){
            if(!esPar(n)){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {

        if (s1.length()>s2.length()){
            return false;
        }
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }


    String darVuelta(String original){
        int longitud = original.length();
        String alreves = "";
        for(int i = 0; i < longitud; i++){
            alreves = alreves + original.charAt(longitud -1 - i);
        }
 
        return alreves;
    }

    
    boolean esSufijo(String s1, String s2) {
        
        boolean sufijo = esPrefijo(darVuelta(s1), darVuelta(s2));
        return sufijo;
    }
}
