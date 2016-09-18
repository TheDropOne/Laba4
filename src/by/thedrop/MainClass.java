package by.thedrop;

/**
 * Created by Semen on 17-Sep-16.
 */

/*Разработать и протестировать класс для работы с комплексными числами.
Комплексное число z содержит действительную (Real) x и мнимую (Imaginaries) y части и записывается в виде (алгебраическая форма):
z =  x +  i y, где i – мнимая единица: i^2 = -1.
Есть еще тригонометрическая форма:
z =  r(cos(fi) + i sin(fi)), где r = sqrt(x^2 + y^2) - модуль комплексного числа, fi = arctg(y/x) - аргумент комплексного числа
и показательная форма:
z = r * e^(i * fi).

Над двумя комплексными числами z и q  определены операции сложения, вычитания, умножения и деления.
Разработанный класс (или классы) должен реализовывать арифметику комплексных чисел и давать строковое представлении комплексного числа в алгебраической и тригонометрической формах.
*/

public class MainClass {
    public static void main(String[] args) {
        Complex z = new Complex(5, 1);
        Complex q = new Complex(15, 10);

        System.out.println("z + q = " + z.add(q));
        System.out.println("z - q = " + z.substruct(q));
        System.out.println("z * q = " + z.multiply(q));
        System.out.println("z / q = " + z.divide(q));
    }
}
