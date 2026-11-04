package font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;


import java.io.File;

public class SetFont {
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
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font\\VeniteAdoremus-rgRBA.ttf")).deriveFont(size);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Font getFont(){
        try {
            // โหลดฟอนต์จากไฟล์
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font\\VeniteAdoremus-rgRBA.ttf")).deriveFont(32f);
            return customFont;
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
