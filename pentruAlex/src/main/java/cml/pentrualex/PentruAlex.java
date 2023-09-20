/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cml.pentrualex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.swing.SwingUtilities;
import javax.swing.*;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;
import static javax.swing.JFileChooser.FILES_ONLY;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Tsanten
 */
public class PentruAlex {
    
    private int numarExportLinii=30000;
    private int numarBulkExport=3;
    private JFileChooser alegereLocatie = new javax.swing.JFileChooser();
    
    public PentruAlex(){
        //---------------------Interfata
        JFrame cadru=new JFrame();
        cadru.setLayout(new MigLayout()); //"insets 0 0 0 0, fill", "", ""
        JPanel panou=new JPanel();
        panou.setLayout(new MigLayout());
        
        File imagineProgram=new File("Date\\Imagini\\imagineProgram.png");
        ImageIcon ico=new ImageIcon(imagineProgram.toString());
        
        cadru.setTitle("Generator Bulk");
        cadru.setIconImage(ico.getImage());
        
        JPanel panouImport=new JPanel();
        
        JButton adaugaLocatie=new JButton("Selectie Fisier");
        JLabel locatiaCurenta=new JLabel("Locatia Curenta:");
        locatiaCurenta.setBackground(Color.WHITE);
        locatiaCurenta.setForeground(Color.WHITE);
        locatiaCurenta.setPreferredSize(new Dimension(300,25));
        locatiaCurenta.setMaximumSize(new Dimension(300,25));
        panouImport.setLayout(new MigLayout("insets 0 0 0 0, fill", "", ""));
        panouImport.add(adaugaLocatie, "left, cell 0 0, width 20%");
        panouImport.add(locatiaCurenta, "left, cell 0 0, width 80%");
        panouImport.setBackground(Color.orange);
        
        
        JPanel panouLinii=new JPanel();
        JLabel numarLiniiExportLabel=new JLabel("Numar linii:");
        JTextField numarExportLiniiField=new JTextField();
        numarExportLiniiField.setPreferredSize(new Dimension(100,25));
        panouLinii.add(numarLiniiExportLabel);
        panouLinii.add(numarExportLiniiField);
        panouLinii.add(new JLabel("(Rows)"));
        panouLinii.setBackground(Color.GREEN);
        
        JPanel panouBulk=new JPanel();
        JLabel numarBulkExportLabel = new JLabel("Numar liste:");
        JTextField numarExportBulkField=new JTextField();
        numarExportBulkField.setPreferredSize(new Dimension(100,25));
        panouBulk.add(numarBulkExportLabel);
        panouBulk.add(numarExportBulkField);
        panouBulk.add(new JLabel("(Bulk)"));
        panouBulk.setBackground(Color.BLUE);
        
        JPanel panouDestinatie=new JPanel();
        panouDestinatie.setLayout(new MigLayout("insets 0 0 0 0, fill", "", ""));
        JLabel locatieDestinatieLabel=new JLabel("Locatie Destinatie:");
        JButton locatieDestinatieButon=new JButton("Adauga Destinatie");
        locatieDestinatieLabel.setPreferredSize(new Dimension(300,25));
        locatieDestinatieLabel.setMaximumSize(new Dimension(300,25));
        panouDestinatie.add(locatieDestinatieButon, "left, cell 0 0");
        panouDestinatie.add(locatieDestinatieLabel, "left, cell 0 0");
        
        
        JPanel panouNumeFisier=new JPanel();
        JLabel numeFisier=new JLabel("Nume Fisier:");
        JTextField numeleFisierului=new JTextField();
        numeleFisierului.setPreferredSize(new Dimension(100,25));
        numeleFisierului.setToolTipText("Numele Fisierului");
        panouNumeFisier.add(numeFisier);
        panouNumeFisier.add(numeleFisierului);
        
        JButton incepe=new JButton("Incepe");
        
        JLabel status=new JLabel("Status: Pregatire");
        status.setPreferredSize(new Dimension(200,25));
        numarExportLiniiField.setToolTipText("Numarul de linii dorite");
        numarExportBulkField.setToolTipText("Numarul de liste dorite");
        //=============== Aplicare Interfata
        cadru.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panou.add(panouImport, "push,grow,wrap");
        panou.add(panouLinii, "push,grow,wrap");
        panou.add(panouBulk, "push,grow,wrap");
        panou.add(panouDestinatie,"push,grow,wrap");
        panou.add(panouNumeFisier, "push,grow,wrap");
        panou.add(status, "left, width 80%, cell 0 5");
        panou.add(incepe, "right, width 20%, cell 0 5");
        cadru.add(panou, "push, grow, wrap");
        cadru.pack();
        //cadru.setPreferredSize(new Dimension(800,600));
        //cadru.setSize(new Dimension(800,600));
        cadru.setVisible(true);
        //============================================================
        
        incepe.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                numarExportLinii=Integer.parseInt(numarExportLiniiField.getText()); // de facut sa se vada doar cifrele
                numarBulkExport=Integer.parseInt(numarExportBulkField.getText());
                File locatieFisier=new File(locatiaCurenta.getText().replace("Locatia Curenta:", ""));
                String locatieScriere="" + locatieDestinatieLabel.getText().replace("Locatie Destinatie:", "") + "\\" + numeleFisierului.getText();

                rulare(locatieFisier, locatieScriere, status);
            }
        });
        
        locatieDestinatieButon.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setareDestinatie(cadru, locatieDestinatieLabel);
            }
        });
        
        adaugaLocatie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setareLocatie(cadru, locatiaCurenta);
            }
        });
        
        
    } // Pentru Alex
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PentruAlex::new); //pornire program
    }
    
    private void motorCreare(Set<String> construireLista, List<String> continutFisier, String locatieDestinatie, int numarLinii, JLabel status){
        if(construireLista.size() == (numarBulkExport*numarExportLinii)){
            // export bulk lists
            System.out.println("A mers.");
            System.out.println("Linii lista: " + construireLista.size());
            status.setText("Status: Se proceseaza..(5/5)");
            
            String locatieScriere="" + locatieDestinatie;
            List<String> selectieScriere = new ArrayList<>();
            List<String> transformareScriere = new ArrayList<>(construireLista);
            for(int i=1;i <= numarBulkExport;i++){
                selectieScriere.clear();
                selectieScriere = new ArrayList<>();
                for(int x=1;x<=(numarExportLinii);x++){
                    selectieScriere.add(transformareScriere.get((x*i)-1));
                }
                //--------- scriere
                try{
                    FileWriter yo=new FileWriter(new File(locatieScriere + "-" + i + ".csv"));
                    for(String linieLista : selectieScriere){
                        yo.write(linieLista + "\n");
                    }
                    yo.flush();
                    yo.close();
                }catch(Exception scriere){
                    System.out.println("A fost o problema la scrierea fisierului: " + scriere);
                }
                //------------------
            }
            status.setText("Status: Finalizat");
        }else{
            //int numarDeficit=(numarBulkExport*numarExportLinii) - construireLista.size();
            status.setText("Status: Se proceseaza..(3/5)");
            maiAdauga(construireLista, continutFisier, locatieDestinatie, numarLinii, status);//, numarDeficit);
        }
    } //motor
    
    private void maiAdauga(Set<String> construireLista, List<String> continutFisier,String locatieDestinatie, int numarLinii, JLabel status){//, int numarDeficit){
        Random numarAleatoriu = new Random();
        while(construireLista.size() < (numarBulkExport*numarExportLinii)){
            int numarAleatoriuGet = numarAleatoriu.nextInt(numarLinii);
            construireLista.add(continutFisier.get(numarAleatoriuGet));
        }
        status.setText("Status: Se proceseaza..(4/5)");
        motorCreare(construireLista,continutFisier,locatieDestinatie,numarLinii, status);
    }//adaugare
    
    private void rulare(File locatieFisier, String locatieDestinatie, JLabel status){
        status.setText("Status: Se proceseaza..(0/5)");
        status.revalidate();
        status.repaint();
        int numarLinii=0;
        Set<String> continutFisierArray= new HashSet<>(); // Set(HashSet) salveaza automat numai valori unice.
        Set<String> construireLista = new HashSet<>();
        System.out.println("a inceput");
        try{
            Scanner yoLo = new Scanner(locatieFisier);
            while(yoLo.hasNextLine()){
                String linie="" + yoLo.nextLine();
                continutFisierArray.add(linie);
                numarLinii++;
            }
            yoLo.close();
        }catch(Exception errorRead){
            System.out.println("a fost o eroare la citirea fisierului: " + errorRead);
        }
        status.setText("Status: Se proceseaza..(1/5)");
        List<String> testConvertire = new ArrayList<>(continutFisierArray); // converteste HashSet in List pentru a putea folosi .get(numar)
        numarLinii=continutFisierArray.size(); // din moment ce valorile sunt unice, numarul de linii poate fi luat numai de aici.
        System.out.println("Linii: " + numarLinii + " " + continutFisierArray.size()); //+ " " + continutFisierArray.get(0) + " " + continutFisierArray.get(11504416 - 1));
        //-------------------------------------- Construire
        Random numarAleatoriu= new Random();
        
        while(construireLista.size() < (numarExportLinii*numarBulkExport)){
            int numarAleatoriuGet = numarAleatoriu.nextInt(numarLinii);
            construireLista.add(testConvertire.get(numarAleatoriuGet));
        //System.out.println("Construire: " + construireLista.size());
        }
        
        
        String locatieScriere="" + locatieDestinatie;
        List<String> selectieScriere = new ArrayList<>();
        List<String> transformareScriere = new ArrayList<>(construireLista);
        System.out.println("test " + transformareScriere.get(0));
        int numarLiniiTest=0;
        for(int i=1;i <= numarBulkExport;i++){
            selectieScriere.clear();
            selectieScriere = new ArrayList<>();
            for(int x=1;x<=(numarExportLinii);x++){
                
                selectieScriere.add(transformareScriere.get(numarLiniiTest));
                numarLiniiTest++;
            }
            //--------- scriere
            try{
                FileWriter yo=new FileWriter(new File(locatieScriere + "-" + i + ".csv"));
                for(String linieLista : selectieScriere){
                    yo.write(linieLista + "\n");
                }
                yo.flush();
                yo.close();
            }catch(Exception scriere){
                System.out.println("A fost o problema la scrierea fisierului: " + scriere);
            }
            //------------------
        }
        status.setText("Status: Finalizat");
        
        //status.setText("Status: Se proceseaza..(2/5)");
        //motorCreare(construireLista, testConvertire, locatieDestinatie, numarLinii, status);
    }
    
    private void setareDestinatie(JFrame cadru, JLabel destinatie) { //----------------- Selectie Fisiere 
        JFrame selectieFisiereCadru= new JFrame();
        ImageIcon icon = new ImageIcon("Date\\Imagini\\imagineProgram.png");
        selectieFisiereCadru.setIconImage(icon.getImage());
        selectieFisiereCadru.setTitle("Alege locatia destinatiei");
        alegereLocatie.setFileSelectionMode(DIRECTORIES_ONLY);
        selectieFisiereCadru.add(alegereLocatie);
        alegereLocatie.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        int result = alegereLocatie.showOpenDialog(cadru);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = alegereLocatie.getSelectedFile();
            destinatie.setText("Locatie Destinatie:" + selectedFile.getAbsolutePath());
            destinatie.setToolTipText(selectedFile.getAbsolutePath());
        }
    }
        
    private void setareLocatie(JFrame cadru, JLabel locatie) { //----------------- Selectie Fisiere 
        JFrame selectieFisiereCadru= new JFrame();
        ImageIcon icon = new ImageIcon("Date\\Imagini\\imagineProgram.png");
        selectieFisiereCadru.setIconImage(icon.getImage());
        selectieFisiereCadru.setTitle("Alege locatia loturilor");
        alegereLocatie.setFileSelectionMode(FILES_ONLY);
        selectieFisiereCadru.add(alegereLocatie);
        alegereLocatie.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        int result = alegereLocatie.showOpenDialog(cadru);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = alegereLocatie.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            locatie.setText("Locatia Curenta:" + selectedFile.getAbsolutePath());
            locatie.setToolTipText(selectedFile.getAbsolutePath());
        }
    }
} // Final Program
