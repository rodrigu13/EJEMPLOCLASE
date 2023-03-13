//RODRIGO DE DIEGO VILLENA
public class Tenedor extends Grupo {
    protected String codtenedor;
    protected static int contT;

    public Tenedor(int ncomensales, String toferta) {
        super(ncomensales);
        this.codtenedor = toferta;
    }

    public static void setContT() {
        contT ++;
    }

    public static int getContT() {
        return contT;
    }

    public double Pagar(){
        double importe;
        if (nracionestotal == 0 && nbebidastotal == 0){
            return 0;
        }
        int descuentoTE = Integer.parseInt(codtenedor.substring(2, 4));
        importe = (nracionestotal * 15) + (nbebidastotal * 2);
        importe = importe - ((importe * descuentoTE)/100);
        return importe;
    }

    public String getcodTenedor() {
        return codtenedor;
    }

    public String toString() {
        return "Tipo: Tenedor -"+" Codigo Tenedor: "+codtenedor+" - Nº comensales: "+ncomensales+" - Nº Raciones total: "+nracionestotal+" - Nº Bebidas total: "+nbebidastotal;
    }

        
    
}
