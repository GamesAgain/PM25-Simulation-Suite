package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

public class SetFont {
    InputStream fontPath = getClass().getResourceAsStream("/font/VeniteAdoremus-rgRBA.ttf");
    public SetFont(){
       
    }
    public Font getFont(String fontPath){
        try {
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(40f);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Font getFont(float size,String fontPath){
        try {
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(size);
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
