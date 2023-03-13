//RODRIGO DE DIEGO VILLENA
public class Gourmet extends Grupo {
    protected static int contG=0;

    public Gourmet(int ncomensales) {
        super(ncomensales);
    }

    public static void setContG() {
        contG ++;
    }

    public double Pagar(){
        if (nracionestotal == 0 && nbebidastotal == 0){
            return 0;
        }
        double importe;
        importe = (nracionestotal * 15) + ((nbebidastotal-ncomensales) * 2);
        return importe;
    }

    public String toString() {
        return "Tipo: Gourmet -"+"Nº comensales: "+ncomensales+" - Nº Raciones total: "+nracionestotal+" - Nº Bebidas total: "+nbebidastotal;
    }

    public static int getContG() {
        return contG;
    }
    
}
