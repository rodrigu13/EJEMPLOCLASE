import java.time.DayOfWeek;
import java.time.LocalDateTime;

//RODRIGO DE DIEGO VILLENA

public class Normal extends Grupo {
    protected static int contN;

    public Normal(int ncomensales) {
        super(ncomensales);
    }

    public static void setContN() {
        contN ++;
    }

    public double Pagar(){
        double importe;
        if (nracionestotal == 0 && nbebidastotal == 0){
            return 0;
        }
        importe = (nracionestotal * 15) + (nbebidastotal * 2);
        if (LocalDateTime.now().getDayOfWeek() != DayOfWeek.SATURDAY && LocalDateTime.now().getDayOfWeek() != DayOfWeek.SUNDAY){
            importe = importe - ((importe*10)/100);            
        }
        return importe;
    }

    @Override
    public String toString() {
        return "Tipo: Normal - "+"Nº comensales: "+ncomensales+" - Nº Raciones total: "+nracionestotal+" - Nº Bebidas total: "+nbebidastotal;
    }

    public static int getContN() {
        return contN;
    }
    
}
