package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class SetFont {
    InputStream fontPath = getClass().getResourceAsStream("/font/VeniteAdoremus-rgRBA.ttf");
    public SetFont(){
       
    }
    public Font getFont(String fontPath){
        try {
            InputStream fontPath2 = getClass().getResourceAsStream(fontPath);
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontPath2).deriveFont(40f);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Font getFont(float size,String fontPath){
        try {
            InputStream fontPath3 = getClass().getResourceAsStream(fontPath);
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontPath3).deriveFont(size);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Font getFont(float size){
        try {
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,fontPath).deriveFont(size);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Font getFont(){
        try {
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontPath).deriveFont(32f);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
