import java.time.LocalDateTime;

//RODRIGO DE DIEGO VILLENA

public abstract class Grupo {
    protected int ncomensales=0, nservirmesa=0, nracionestotal=0, nbebidastotal=0;
    LocalDateTime tiempoini;
    


    public Grupo(int ncomensales){
        this.ncomensales = ncomensales;
        tiempoini = LocalDateTime.now();
    }

    public LocalDateTime getTiempoini() {
        return tiempoini;
    }

    public abstract double Pagar();

    public int getNcomensales() {
        return ncomensales;
    }

    public int getNservirmesa() {
        return nservirmesa;
    }


    
    public void setNcomensales(int ncomensales) {
        this.ncomensales += ncomensales;
    }

    public void setNservirmesa() {
        this.nservirmesa++;
    }

    public void setNservirmesa2() {
        this.nservirmesa = 0;
    }

    public int getNracionestotal() {
        return nracionestotal;
    }

    public int getNbebidastotal() {
        return nbebidastotal;
    }

    public void setNcomidatotal(int nracionestotal, int nbebidastotal) {
        this.nracionestotal += nracionestotal;
        this.nbebidastotal += nbebidastotal;
    }


    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grupo other = (Grupo) obj;
        if (ncomensales != other.ncomensales)
            return false;
        if (nservirmesa != other.nservirmesa)
            return false;
        if (nracionestotal != other.nracionestotal)
            return false;
        if (nbebidastotal != other.nbebidastotal)
            return false;
        if (tiempoCola == null) {
            if (other.tiempoCola != null)
                return false;
        } else if (!tiempoCola.equals(other.tiempoCola))
            return false;
        return true;
    }

    

    

    

    
    
}
