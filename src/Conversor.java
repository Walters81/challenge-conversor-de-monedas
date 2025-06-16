import com.google.gson.annotations.SerializedName;

public class Conversor implements Comparable<Conversor>{


    private String monedaBase;
    private String monedaTarget;
    private double monto;
    private double tasaDeConversion;
    private double montoConvertido;

    public Conversor(String monedaBase, String monedaTarget, double monto, double tasaDeConversion, double montoConvertido){
        this.monedaBase = monedaBase;
        this.monedaTarget = monedaTarget;
        this. monto = monto;
        this.tasaDeConversion = tasaDeConversion;
        this.montoConvertido = montoConvertido;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaTarget() {
        return monedaTarget;
    }

    public double getMonto() {
        return monto;
    }

    public double getTasaDeConversion() {
        return tasaDeConversion;
    }

    public double getMontoConvertido() {
        return montoConvertido;
    }

    @Override
    public int compareTo(Conversor otroConversor) {
        return this.getMonedaBase().compareTo(otroConversor.getMonedaBase());
    }
    @Override
    public String toString() {
        return "(monedaBase=" + monedaBase +
                ", monedaTarget=" + monedaTarget +
                ", monto=" + monto +
                ", tasaDeConversion=" + tasaDeConversion +
                ", montoConvertido=" + montoConvertido+")";
    }

}
