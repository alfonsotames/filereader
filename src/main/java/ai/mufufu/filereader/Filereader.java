/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ai.mufufu.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mufufu
 */
public class Filereader {

    public static void main(String[] args) {
        


        
        
        
        System.out.println("Hello World!");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<StockBar> bars = new ArrayList<>();
        
        String path = "/home/mufufu/Downloads/data/1h/AAPL.csv";
        //String path= "/Users/matiastames/Downloads/AAPL.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                //StockBar(LocalDate date, float open, float high, float low, float close, long volume, float dividends, float splits)
                bars.add(
                    new StockBar(
                            format.parse(values[0].substring(0, 19)), 
                            Float.parseFloat(values[1]),
                            Float.parseFloat(values[2]),
                            Float.parseFloat(values[3]),
                            Float.parseFloat(values[4]),
                            Long.parseLong(values[5]),
                            Float.parseFloat(values[6]),
                            Float.parseFloat(values[7])
                    )
                );
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {        
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int n=50;
        int c=1;
        float sum=0;

        float ema=0;
        float ema_prev=0;
        float wm;
        
        wm = 2f/(n+1f);
        //wm = 0.039215686f;
        
        List<StockBar> mal = new ArrayList<>();
        
        ema_prev = bars.get(0).getClose();
        
        

        for (int i=0; i<n; i++) {
            sum = sum + (bars.get(i)).getClose();
        }
        ema_prev = sum/n;
        
        

        for (StockBar b : bars) {
            
            if (c > n) {
                ema = ((b.getClose()- ema_prev)*wm)+ema_prev;
                
                /*
                System.out.println(String.format("Date: %s\tClose: %f\tn: %d\tEMA_PREV: %f\tEMA: %f", 
                    b.getDate(), b.getClose(),c, ema_prev, ema));
                */
                
                System.out.println(String.format("%d,%f,%f", c,b.getClose(),ema));
                ema_prev=ema;
            }
            c++;
        }
    }
    
    // Datetime,Open,High,Low,Close,Volume,Dividends,Stock Splits

}
