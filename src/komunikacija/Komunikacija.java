/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Acer
 */
public class Komunikacija {
    
    private Socket s;
    private static Komunikacija instance;

    private Komunikacija() {
        try {
            s = new Socket("127.0.0.1",9000);
            System.out.println("KLijent je povezan!");
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public ServerskiOdgovor primiOdgovor(){
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
}
