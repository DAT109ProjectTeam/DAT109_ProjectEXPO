package no.hvl.dat108.webshop.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import no.hvl.dat108.webshop.objects.Stand;
import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.repos.StandRepo;
import no.hvl.dat108.webshop.repos.StemmeRepo;
import no.hvl.dat108.webshop.util.QRGenerator;

class StandServiceTest {

    @InjectMocks
    private StandService standService;

    @Mock
    private StandRepo standRepoMock;

    @Mock
    private StemmeRepo stemmeRepoMock;

    @Mock
    private QRGenerator qrGeneratorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFinnAlleStands() {
        // Arrange
        List<Stand> stands = new ArrayList<>();
        stands.add(new Stand("Stand1", "Test", "<iframe></iframe>"));
        stands.add(new Stand("Stand2", "Test", "<iframe></iframe>"));
        when(standRepoMock.findAll()).thenReturn(stands);

        // Act
        List<Stand> result = standService.finnAlleStands();

        // Assert
        assertEquals(stands.size(), result.size());
        assertEquals(stands.get(0), result.get(0));
        assertEquals(stands.get(1), result.get(1));
    }

    

    @Test
    void testFinnStand() {
        // Arrange
        String navn = "Stand1";
        Stand stand = new Stand(navn, "Test", "<iframe></iframe>");
        when(standRepoMock.findByNavn(navn)).thenReturn(stand);

        // Act
        Stand result = standService.finnStand(navn);

        // Assert
        assertEquals(stand, result);
    }

  

    @Test
    void testEksistererStand_NonExistent() {
        // Arrange
        String navn = "Stand1";
        when(standRepoMock.findByNavn(navn)).thenReturn(null);

        // Act
        boolean result = standService.eksistererStand(navn);

        // Assert
        assertFalse(result);
    }

    

@Test
void testRangerStander() {
    // Arrange
    List<Stand> standliste = new ArrayList<>();
    standliste.add(new Stand("Stand1", "Test", "<iframe></iframe>"));
    standliste.add(new Stand("Stand2", "Test", "<iframe></iframe>"));

    List<Stemme> stemmeliste = new ArrayList<>();
    stemmeliste.add(new Stemme(null, "Stand1", 5)); // Null brukerid, da det ikke ser ut til å være nødvendig i denne testen
    stemmeliste.add(new Stemme(null, "Stand2", 3)); // Null brukerid, da det ikke ser ut til å være nødvendig i denne testen

    when(standRepoMock.findAll()).thenReturn(standliste);
    when(stemmeRepoMock.findAll()).thenReturn(stemmeliste);

    // Act
    List<Stand> result = standService.rangerStander();

    // Assert
    assertNotNull(result);
    assertEquals(standliste.size(), result.size());
    assertEquals("Stand1", result.get(0).getNavn());
    assertEquals("Stand2", result.get(1).getNavn());
    assertEquals(5, result.get(0).getPoengsum());
    assertEquals(3, result.get(1).getPoengsum());
}

    
/* FUNGERER IKKKKKKKKKE
@Test
void testResetDatabase() {
    // Act
    standService.resetDatabase();

    // Assert
    verify(standRepoMock, times(1)).deleteById("Stand1");
    verify(standRepoMock, times(1)).deleteById("Stand2");
    verify(standRepoMock, times(1)).deleteById("Stand3");
    verify(stemmeRepoMock, times(1)).deleteAll();
    verify(qrGeneratorMock, times(6)).reset();
}
 */

@Test
    public void testLagreStand_StandExists() {
        // Opprett en eksisterende stand
        Stand eksisterendeStand = new Stand("Stand1", "Test", "<iframe></iframe>");
        // Angi at standen eksisterer
        when(standRepoMock.findByNavn("Stand1")).thenReturn(eksisterendeStand);

        // Utfør testen
        standService.lagreStand(eksisterendeStand);

        // Sjekk om delete og save ble kalt
        verify(standRepoMock).delete(eksisterendeStand);
        verify(qrGeneratorMock).genererQrStand(eksisterendeStand);
        verify(qrGeneratorMock).genererQrTilbakemelding(eksisterendeStand);
        verify(standRepoMock).save(eksisterendeStand);
    }

    @Test
    public void testLagreStand_StandDoesNotExist() {
        // Opprett en ny stand
        Stand nyStand = new Stand("Stand2", "Test", "<iframe></iframe>");
        // Angi at standen ikke eksisterer
        when(standRepoMock.findByNavn("Stand2")).thenReturn(null);

        // Utfør testen
        standService.lagreStand(nyStand);

        // Sjekk om save ble kalt
        verify(qrGeneratorMock).genererQrStand(nyStand);
        verify(qrGeneratorMock).genererQrTilbakemelding(nyStand);
        verify(standRepoMock).save(nyStand);
    }

}
