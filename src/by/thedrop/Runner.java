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

public class Runner {
    public static void main(String[] args) {
        Complex z = new Complex(5, 1);
        Complex q = new Complex(15, 10);

        System.out.println("Z = " + z);
        System.out.println("Q = " + q);

        System.out.println("z + q = " + z.add(q));
        System.out.println("New z - q = " + z.substruct(q));
        System.out.println("z * q = " + z.multiply(q));
        System.out.println("New z / q = " + z.divide(q));

        try {
            System.out.println(Complex.parseComplex("15.0e^(14.5i)"));
            System.out.println(Complex.parseComplex("15.0+i*14.5"));
            System.out.println(Complex.parseComplex("15.0(cos(2.2)+i*sin(2.2))"));

            System.out.println(Complex.parseComplex("15.0(cos(2.2)+i*sin(2.2)")); //error, no bracket at end
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                System.out.println(Complex.parseComplex("15.0(cos(2.2)i*sin(2.2)")); //error, no plus
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(z.toCartesianForm());
        System.out.println(q.toPolarForm());
        System.out.println(q.toExponencialForm());
    }
}
