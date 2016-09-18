package by.thedrop;

import java.text.DecimalFormat;

/**
 * Created by Semen on 18-Sep-16.
 */
public class Complex {
    private double real;
    private double imaginaries;


    public Complex(double real, double imaginaries) {
        this.real = real;
        this.imaginaries = imaginaries;
    }

    public Complex add(Complex complex) {
        this.real += complex.getReal();
        this.imaginaries += complex.getImaginaries();
        return this;
    }

    public Complex substruct(Complex complex) {
        this.real -= complex.getReal();
        this.imaginaries -= complex.getImaginaries();
        return this;
    }

    public Complex divide(Complex complex) {
        this.real = (real * complex.getReal() + imaginaries * complex.getImaginaries()) / complex.getModule();
        this.imaginaries = (real * complex.getReal() - imaginaries * complex.getImaginaries()) / complex.getModule();
        return this;
    }

    public Complex multiply(Complex complex) {
        this.real = real * complex.getReal() - imaginaries * complex.getImaginaries();
        this.imaginaries = real * complex.getImaginaries() + imaginaries * complex.getReal();
        return this;

    }

    @Override
    public String toString() {
        return toCartesianForm();
    }

    public String toPolarForm() {
        DecimalFormat df = new DecimalFormat("##0.00");
        String module = df.format(getModule());
        String argument = df.format(getArgument());
        return module + "(cos(" + argument + ") + i*sin(" + argument + "))";
    }

    public String toCartesianForm() {
        DecimalFormat df = new DecimalFormat("##0.00");
        String real = df.format(getReal());
        String imaginaries = df.format(getImaginaries());
        String sign = getImaginaries() < 0 ? "" : " + ";
        return real + sign + imaginaries + "i";
    }

    public double getModule() {
        return Math.sqrt(getReal() * getReal() + getImaginaries() * getImaginaries());
    }

    public double getArgument() {
        return Math.atan(getImaginaries() / getReal());
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginaries() {
        return imaginaries;
    }

    public void setImaginaries(double imaginaries) {
        this.imaginaries = imaginaries;
    }
}
