
package simulasyon;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UcusMotoru {
    // Telemetri verilerini MySQL veritabanına asenkron kaydeden metot
    public static void telemetriKaydet(int irtifa, int hiz, int batarya, int sicaklik) {
        String sorgu = "INSERT INTO ucus_telemetrisi (irtifa, hiz, batarya, sicaklik) VALUES (?, ?, ?, ?)";
        
        // Bir önceki projede yazdığın VeritabaniBaglantisi sınıfını aynen çağırıyoruz
      try (Connection baglanti = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/iha_envanter_db", "root", "");
             PreparedStatement pst = baglanti.prepareStatement(sorgu)) {
            
            pst.setInt(1, irtifa);
            pst.setInt(2, hiz);
            pst.setInt(3, batarya);
            pst.setInt(4, sicaklik);
            
            pst.executeUpdate(); // Arka planda veritabanına yazar
            
        } catch (Exception e) {
            System.out.println("Veritabanı telemetri kayıt hatası: " + e.getMessage());
        }
    }
}
