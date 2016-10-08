package by.thedrop;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen on 18-Sep-16.
 */
public class Complex {
    private double real;
    private double imaginaries;

    private static String form_container;

    public Complex(double real, double imaginaries) {
        this.real = real;
        this.imaginaries = imaginaries;
    }

    public Complex(float module, float argument) {
        this.real = module * Math.sin(argument);
        this.imaginaries = module * Math.cos(argument);
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
        double cModule = complex.getModule();
        double cReal = complex.getReal();
        double cImaginaries = complex.getImaginaries();
        this.real = (real * cReal + imaginaries * cImaginaries) / cModule;
        this.imaginaries = (real * cReal - imaginaries * cImaginaries) / cModule;
        return this;
    }

    public Complex multiply(Complex complex) {
        double cReal = complex.getReal();
        double cImaginaries = complex.getImaginaries();
        this.real = real * cReal - imaginaries * cImaginaries;
        this.imaginaries = real * cImaginaries + imaginaries * cReal;
        return this;
    }

    public static Complex parseComplex(String s) throws Exception {
        if (!containsComplex(s)) throw new Exception("No complex in string");
        else {
            String[] tempString;
            switch (form_container) {
                case "CARTESIAN":
                    tempString = s
                            .replaceAll("\\+", "\\.")
                            .replaceAll("\\-", "\\.")
                            .split("\\.");
                    tempString = leaveOnlyDigitsInStrings(tempString);
                    return new Complex(Double.parseDouble(tempString[0] + "." + tempString[1]), Double.parseDouble(tempString[2] + "." + tempString[3]));
                case "EXPONENCIAL":
                    tempString = s
                            .replaceAll("e", "\\.")
                            .split("\\.");
                    tempString = leaveOnlyDigitsInStrings(tempString);
                    return new Complex((float) Double.parseDouble(tempString[0] + "." + tempString[1]), (float) Double.parseDouble(tempString[2] + "." + tempString[3]));
                case "POLAR":
                    tempString = s
                            .replaceAll("cos", "\\.")
                            .replaceAll("sin", "\\.")
                            .split("\\.");
                    tempString = leaveOnlyDigitsInStrings(tempString);
                    return new Complex((float) Double.parseDouble(tempString[0] + "." + tempString[1]), (float) Double.parseDouble(tempString[2] + "." + tempString[3]));
            }
        }
        return new Complex(0, 0);
    }

    private static String[] leaveOnlyDigitsInStrings(String[] array) {
        String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String temp = "";
            for (int j = 0; j < array[i].length(); j++) {
                char c = array[i].charAt(j);
                if (Character.isDigit(c)) {
                    temp = temp + c;
                }
            }
            array2[i] = temp;
        }
        return array2;
    }

    private static  boolean containsComplex(String s) {
        s = s.trim();
        return getCartesianMatcher(s).find() || getExponencialMatcher(s).find() || getPolarMatcher(s).find();
    }

    private static Matcher getCartesianMatcher(String s) {
        Pattern cartesian = Pattern.compile("\\d+\\.\\d+[+-](i\\*?\\d+\\.\\d+|\\d+\\.\\d+\\*?i)");
        form_container = "CARTESIAN";
        return cartesian.matcher(s);
    }

    private static Matcher getExponencialMatcher(String s) {
        Pattern exponencial = Pattern.compile("(\\d+\\.\\d+)?\\*?e\\^\\(?(i\\*\\d+\\.\\d+|\\d+\\.\\d+\\*?i\\)?)");
        form_container = "EXPONENCIAL";
        return exponencial.matcher(s);
    }

    private static Matcher getPolarMatcher(String s) {
        Pattern polar = Pattern.compile("(\\d+\\.\\d+)?\\*?\\(cos\\(\\d+\\.\\d+\\)[+-](i\\*?sin\\(\\d+\\.\\d+\\)|sin\\(\\d+\\.\\d+\\)\\*?i)\\)");
        form_container = "POLAR";
        return polar.matcher(s);
    }


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
        String sign = getImaginaries() < 0 ? "" : "+";
        return real + sign + imaginaries + "i";
    }

    public String toExponencialForm() {
        DecimalFormat df = new DecimalFormat("##0.00");
        String module = df.format(getModule());
        String argument = df.format(getArgument());
        return module + "*e^(i*" + argument + ")";
    }

    public double getModule() {
        double cReal = getReal();
        double cImaginaries = getImaginaries();
        return Math.sqrt(cReal * cReal + cImaginaries * cImaginaries);
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
