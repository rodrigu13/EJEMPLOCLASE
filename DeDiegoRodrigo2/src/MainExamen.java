import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//RODRIGO DE DIEGO VILLENA

public class MainExamen {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        Vector<Grupo> colaR = new Vector<Grupo>();
        Grupo restaurante[] = new Grupo[4];
        String tipo[] = { "Normal", "Tenedor", "Gourmet" };
        double matrizfinal[][] = new double[3][4];
        String categoria, tenedor;
        int opcio=0, numComen, nmesa, nmesaaux, nraciones, nbebidas;
        Grupo grupoLlegada;

        do {
            
                System.out.println("Menu:");
                System.out.println("1.Llegada del grupo \n2.Servir \n3.Pagar \n4.Juntar 2 mesas \n5.Mostrar info");
                try {
                opcio = sc.nextInt();
                switch (opcio) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Llega un grupo: ");
                        numComen = r.nextInt(1, 5);
                        categoria = tipo[r.nextInt(0, 3)];
                        if (categoria.equalsIgnoreCase("tenedor")) {
                            do {
                                System.out.println("Introduce tenedor: ");
                                tenedor = sc.nextLine();
                                if (!ValidarTene(tenedor)) {
                                    System.out.println("Codigo de tenedor en formato incorrecto.");
                                }
                            } while (!ValidarTene(tenedor));
                            grupoLlegada = new Tenedor(numComen, tenedor);
                        } else if (categoria.equalsIgnoreCase("gourmet")) {
                            grupoLlegada = new Gourmet(numComen);
                        } else {
                            grupoLlegada = new Normal(numComen);
                        }
                        int nvacio = MesaVacia(restaurante);
                        if (nvacio != -1) {
                            restaurante[nvacio] = grupoLlegada;
                            System.out.println("Se le ha asignado la mesa " + nvacio);
                        } else {
                            colaR.add(grupoLlegada);
                            System.out.println("Grupo posicionado en la cola.");
                        }
                        grupoLlegada = null;
                        break;
                    case 2:
                        sc.nextLine();
                        do {
                            System.out.println("Anota la mesa a servir: (0-3)");
                            nmesa = sc.nextInt();
                            if (nmesa < 0 || nmesa > 3) {
                                System.out.println("Número de mesa incorrecto.");
                            }else if (restaurante[nmesa]==null){
                                System.out.println("Esa mesa está vacía.");
                                break;
                            }
                        } while (nmesa < 0 || nmesa > 3);
                        if (restaurante[nmesa].getNservirmesa() >= 3) {
                            System.out.println("No se puede servir más de 3 veces.");
                            break;
                        }
                        do {
                            System.out.println("¿Cuantas raciones?");
                            nraciones = sc.nextInt();
                            System.out.println("¿Cuantás bebidas?");
                            nbebidas = sc.nextInt();
                        } while (nraciones > restaurante[nmesa].getNcomensales()
                                || nbebidas > restaurante[nmesa].getNcomensales());
                        restaurante[nmesa].setNcomidatotal(nraciones, nbebidas);
                        restaurante[nmesa].setNservirmesa();
                        break;
                    case 3:
                        sc.nextLine();
                        do {
                            System.out.println("Anota la mesa a pagar: (0-3)");
                            nmesa = sc.nextInt();
                            if (nmesa < 0 || nmesa > 3) {
                                System.out.println("Número de mesa incorrecto.");
                            } else if (restaurante[nmesa]== null){
                                System.out.println("Mesa vacía");
                                break;
                            }
                        } while (nmesa < 0 || nmesa > 3);
                        double importetot = restaurante[nmesa].Pagar();
                        System.out.println("Se tiene que pagar " + importetot);
                        matrizfinal[nmesa][IndiceCategoria(restaurante[nmesa])] += importetot;
                        if (tipo[IndiceCategoria(restaurante[nmesa])].equalsIgnoreCase("Normal")){
                            Normal.setContN();
                        } else if (tipo[IndiceCategoria(restaurante[nmesa])].equalsIgnoreCase("Tenedor")){
                            Tenedor.setContT();
                        } else {
                            Gourmet.setContG();
                        }
                        restaurante[nmesa] = null;
                        if (colaR.size() !=0) {
                            restaurante[nmesa] = colaR.get(0);
                            colaR.remove(0);
                            long diff = ChronoUnit.MINUTES.between(restaurante[nmesa].getTiempoini(),
                                    LocalDateTime.now());
                            System.out.println("Ha permanecido en la cola " + diff + " minutos.");
                        }
                        break;
                    case 4:
                        sc.nextLine();
                        try {
                            System.out.println("Anota el primer nº de mesa: ");
                            nmesa = sc.nextInt();
                            System.out.println("Anota el segundo nº de mesa: ");
                            nmesaaux = sc.nextInt();
                            if (restaurante[nmesa] == null || restaurante[nmesaaux] == null){
                                System.out.println("Una de las mesas está vacía.");
                                break;
                            }
                            if (restaurante[nmesa].getClass() != restaurante[nmesaaux].getClass()) {
                                throw new ComensalesExcepcion("No son dos mesas del mismo tipo.");
                            } else if (restaurante[nmesa].getNcomensales() + restaurante[nmesaaux].getNcomensales() > 5) {
                                throw new ComensalesExcepcion("No puede haber más de 5 comensales en la mesa conjunta");
                            }
                        } catch (ComensalesExcepcion e) {
                            break;
                        }
                        restaurante[nmesa].setNcomensales(restaurante[nmesaaux].getNcomensales());
                        restaurante[nmesa].setNcomidatotal(restaurante[nmesaaux].getNracionestotal(),
                                restaurante[nmesaaux].getNbebidastotal());
                        restaurante[nmesa].setNservirmesa2();
                        restaurante[nmesaaux] = null;

                        if (colaR.get(0) != null) {
                            restaurante[nmesaaux] = colaR.get(0);
                            colaR.remove(0);
                        }

                        break;
                    case 5:
                        System.out.println("Información mesas ocupadas: ");
                        for (int i = 0; i < restaurante.length; i++) {
                            if (restaurante[i] == null){
                                i++;
                            }
                            System.out.println("Mesa: "+i+" || "+restaurante[i].toString());
                        }
                        System.out.println("Información de la cola: ");
                        for (int i = 0; i < colaR.size();i++) {
                            System.out.println(colaR.get(i).toString());
                        }

                        System.out.println("Han comido "+Normal.getContN()+" clientes Normales.");
                        System.out.println("Han comido "+Tenedor.getContT()+" clientes Tenedor.");
                        System.out.println("Han comido "+Gourmet.getContG()+" clientes Gourmet.");

                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Has anotado una letra en vez de un número.");
                sc.nextLine();
            }
        } while (opcio != 6);
        
        for(int i = 0;i<4;i++){
            System.out.println("Mesa nº "+i);
            for(int f = 0;f<3;f++){
                System.out.println("Cliente: "+tipo[f]+" Importe total: "+matrizfinal[f][i]);
            }
        }
        int total = Normal.getContN() + Tenedor.getContT() + Gourmet.getContG();
        if (total != 0){
            int porcentajeG = (Gourmet.getContG() * 100)/total;
            int porcentajeT = (Tenedor.getContT() * 100)/total;
            int porcentajeN = (Normal.getContN() * 100)/total;
            System.out.println("El porcentaje de Clientes Normales: "+porcentajeN+" clientes Gourmet: "+porcentajeG+" clientes Tenedor: "+porcentajeT);
        }

        
    }

    public static boolean ValidarTene(String tenedor) {
        Pattern patron = Pattern.compile("^[T][E][0-9 ]{1,2}$");
        Matcher matchin = patron.matcher(tenedor);
        boolean machfoun = matchin.find();

        if (machfoun) {
            return true;
        } else {
            return false;
        }
    }

    public static int MesaVacia(Grupo restaurante[]) {
        for (int i = 0; i < restaurante.length; i++) {
            if (restaurante[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int IndiceCategoria(Grupo ej1) {
        if (ej1 instanceof Normal) {
            return 0;
        } else if (ej1 instanceof Tenedor) {
            return 1;
        } else {
            return 2;
        }
    }
}
