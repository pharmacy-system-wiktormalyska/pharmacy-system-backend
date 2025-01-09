package ovh.wiktormalyska.pharmacysystembackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.*;

public class PharmacyServiceImplTest {

  @Mock private PharmacyRepository pharmacyRepository;

  @InjectMocks private PharmacyServiceImpl pharmacyServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddNewPharmacy_Success() {
    PharmacyRequestDTO requestDTO =
        PharmacyRequestDTO.builder().name("Test Pharmacy").address("Test Address").build();

    Pharmacy pharmacy =
        Pharmacy.builder().id(1L).name("Test Pharmacy").address("Test Address").build();

    PharmacyResponseDTO responseDTO =
        PharmacyResponseDTO.builder().id(1L).name("Test Pharmacy").address("Test Address").build();

    when(pharmacyRepository.save(any(Pharmacy.class))).thenReturn(pharmacy);
    PharmacyResponseDTO result = pharmacyServiceImpl.addNewPharmacy(requestDTO);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
    assertEquals(responseDTO.getAddress(), result.getAddress());
  }

  @Test
  public void testAddNewPharmacy_Failure() {
    PharmacyRequestDTO requestDTO =
        PharmacyRequestDTO.builder().name("Test Pharmacy").address("Test Address").build();

    when(pharmacyRepository.save(any(Pharmacy.class)))
        .thenThrow(new RuntimeException("Error saving pharmacy"));

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> pharmacyServiceImpl.addNewPharmacy(requestDTO));
    assertEquals("Error saving pharmacy", exception.getMessage());
  }

  @Test
  public void testGetPharmacyByIdDtoById_Success() {
    Long id = 1L;
    Pharmacy pharmacy =
        Pharmacy.builder().id(id).name("Test Pharmacy").address("Test Address").build();

    PharmacyResponseDTO responseDTO =
        PharmacyResponseDTO.builder().id(id).name("Test Pharmacy").address("Test Address").build();

    when(pharmacyRepository.findById(id)).thenReturn(Optional.of(pharmacy));
    PharmacyResponseDTO result = pharmacyServiceImpl.getPharmacyDtoById(id);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
    assertEquals(responseDTO.getAddress(), result.getAddress());
  }

  @Test
  public void testGetPharmacyByIdDtoById_NotFound() {
    Long id = 1L;

    when(pharmacyRepository.findById(id)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> pharmacyServiceImpl.getPharmacyDtoById(id));
    assertEquals(
        "404 NOT_FOUND \"Pharmacy with this name doesn't exist.\"", exception.getMessage());
  }

  @Test
  public void testUpdatePharmacy_Success() {
    PharmacyRequestDTO requestDTO =
        PharmacyRequestDTO.builder()
            .id(1L)
            .name("Updated Pharmacy")
            .address("Updated Address")
            .build();

    Pharmacy existingPharmacy =
        Pharmacy.builder().id(1L).name("Old Pharmacy").address("Old Address").build();

    Pharmacy updatedPharmacy =
        Pharmacy.builder().id(1L).name("Updated Pharmacy").address("Updated Address").build();

    PharmacyResponseDTO responseDTO =
        PharmacyResponseDTO.builder()
            .id(1L)
            .name("Updated Pharmacy")
            .address("Updated Address")
            .build();

    when(pharmacyRepository.findById(1L)).thenReturn(Optional.of(existingPharmacy));
    when(pharmacyRepository.save(any(Pharmacy.class))).thenReturn(updatedPharmacy);
    PharmacyResponseDTO result = pharmacyServiceImpl.updatePharmacy(requestDTO);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
    assertEquals(responseDTO.getAddress(), result.getAddress());
  }

  @Test
  public void testRemovePharmacyById_Success() {
    Long id = 1L;
    Pharmacy pharmacy =
        Pharmacy.builder()
            .id(id)
            .name("Test Pharmacy")
            .address("Test Address")
            .isActive(true)
            .build();

    PharmacyResponseDTO responseDTO =
        PharmacyResponseDTO.builder()
            .id(id)
            .name("Test Pharmacy")
            .address("Test Address")
            .isActive(false)
            .build();

    when(pharmacyRepository.findById(id)).thenReturn(Optional.of(pharmacy));
    when(pharmacyRepository.save(any(Pharmacy.class))).thenReturn(pharmacy);
    PharmacyResponseDTO result = pharmacyServiceImpl.removePharmacyById(id);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
    assertEquals(responseDTO.getAddress(), result.getAddress());
    assertFalse(result.isActive());
  }

  @Test
  public void testRemovePharmacyById_NotFound() {
    Long id = 1L;

    when(pharmacyRepository.findById(id)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(
            ResponseStatusException.class, () -> pharmacyServiceImpl.removePharmacyById(id));
    assertEquals(
        "404 NOT_FOUND \"Pharmacy with this name doesn't exist.\"", exception.getMessage());
  }
}