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

import no.hvl.dat108.webshop.objects.Stemme;
import no.hvl.dat108.webshop.repos.StemmeRepo;

public class StemmeServiceTest {

    @InjectMocks
    private StemmeService stemmeService;

    @Mock
    private StemmeRepo stemmeRepoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFinnAlleStemmer() {
        // Opprett en liste med testdata
        List<Stemme> stemmer = new ArrayList<>();
        stemmer.add(new Stemme(1, "Stand1", 5));
        stemmer.add(new Stemme(2, "Stand2", 3));

        // Angi oppførselen til stemmeRepoMock når findAll() kalles
        when(stemmeRepoMock.findAll()).thenReturn(stemmer);

        // Utfør testen
        List<Stemme> result = stemmeService.finnAlleStemmer();

        // Sjekk om resultatet er det samme som testdataene
        assertEquals(stemmer.size(), result.size());
        assertEquals(stemmer.get(0), result.get(0));
        assertEquals(stemmer.get(1), result.get(1));
    }

    @Test
    void testFinnStemme() {
        // Opprett en stemme med ID 1
        Stemme stemme = new Stemme(1, "Stand1", 5);

        // Angi oppførselen til stemmeRepoMock når findByBrukerid() kalles
        when(stemmeRepoMock.findByBrukerid(1)).thenReturn(stemme);

        // Utfør testen
        Stemme result = stemmeService.finnStemme(1);

        // Sjekk om resultatet er det samme som forventet stemme
        assertEquals(stemme, result);
    }

    @Test
    void testEksistererStemme_NyStemme() {
        // Opprett en stemme med ID 1 og navn "Stand1"
        Stemme stemme = new Stemme(1, "Stand1", 5);

        // Angi oppførselen til stemmeRepoMock når findByBrukeridAndNavn() kalles
        when(stemmeRepoMock.findByBrukeridAndNavn(1, "Stand1")).thenReturn(null);

        // Utfør testen
        boolean result = stemmeService.eksistererStemme(1, "Stand1");

        // Sjekk om stemmen ikke eksisterer
        assertFalse(result);
    }

    @Test
    void testEksistererStemme_EksisterendeStemme() {
        // Opprett en eksisterende stemme med ID 1 og navn "Stand1"
        Stemme eksisterendeStemme = new Stemme(1, "Stand1", 5);

        // Angi oppførselen til stemmeRepoMock når findByBrukeridAndNavn() kalles
        when(stemmeRepoMock.findByBrukeridAndNavn(1, "Stand1")).thenReturn(eksisterendeStemme);

        // Utfør testen
        boolean result = stemmeService.eksistererStemme(1, "Stand1");

        // Sjekk om stemmen eksisterer
        assertTrue(result);
    }

    @Test
    void testLagreStemme_NyStemme() {
        // Opprett en ny stemme
        Stemme nyStemme = new Stemme(1, "Stand1", 5);

        // Angi oppførselen til stemmeRepoMock når findByBrukeridAndNavn() kalles
        when(stemmeRepoMock.findByBrukeridAndNavn(1, "Stand1")).thenReturn(null);

        // Utfør testen
        stemmeService.lagreStemme(nyStemme);

        // Sjekk om stemmeRepoMock.save() ble kalt
        verify(stemmeRepoMock).save(nyStemme);
    }

    @Test
    void testLagreStemme_EksisterendeStemme() {
        // Opprett en eksisterende stemme med ID 1 og navn "Stand1"
        Stemme eksisterendeStemme = new Stemme(1, "Stand1", 5);
        Stemme nyStemme = new Stemme(1, "Stand1", 3);

        // Angi oppførselen til stemmeRepoMock når findByBrukeridAndNavn() kalles
        when(stemmeRepoMock.findByBrukeridAndNavn(1, "Stand1")).thenReturn(eksisterendeStemme);

        // Utfør testen
        stemmeService.lagreStemme(nyStemme);

        // Sjekk om stemmeRepoMock.deleteById() og stemmeRepoMock.save() ble kalt
        verify(stemmeRepoMock).deleteById(eksisterendeStemme.getId());
        verify(stemmeRepoMock).save(nyStemme);
    }
}
